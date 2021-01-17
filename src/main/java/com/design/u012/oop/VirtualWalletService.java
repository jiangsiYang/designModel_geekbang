package com.design.u012.oop;

import java.math.BigDecimal;

/**
 * 基于充血模型的 DDD 开发模式中，将业务逻辑移动到 Domain 中，Service类的职责是
 * 1.Service 类负责与 Repository 交流。
 * VirtualWalletService 类负责与 Repository 层打交道，调用 Respository 类的方法，获取数据库中的数据，转化成领域模型 VirtualWallet，然后由领域模型 VirtualWallet 来完成业务逻辑，最后调用 Repository 类的方法，将数据存回数据库。
 * 这点很重要，领域模型专注于核心业务的开发，而不用关注与其他组件的之间的连接（包括各种数据从mapper或者spi调用而来），做到了高内聚，低耦合
 * 2.Service 类负责跨领域模型的业务聚合功能。VirtualWalletService 类中的 transfer() 转账函数会涉及两个钱包的操作，因此这部分业务逻辑无法放到 VirtualWallet 类中，所以，我们暂且把转账业务放到 VirtualWalletService 类中了。当然，虽然功能演进，使得转账业务变得复杂起来之后，我们也可以将转账业务抽取出来，设计成一个独立的领域模型。
 * 3.Service 类负责一些非功能性及与三方系统交互的工作。比如幂等、事务、发邮件、发消息、记录日志、调用其他系统的 RPC 接口等，都可以放到 Service 类中。
 */
public class VirtualWalletService {
    // 通过构造函数或者IOC框架注入
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet virtualWallet = convert(walletEntity);
        return virtualWallet;
    }


    public BigDecimal getBalance(Long walletId) {
        return walletRepo.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        //service层负责和repository层打交道
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet virtualWallet = convert(walletEntity);
        //虚拟钱包的核心业务流程在Domain层实现
        virtualWallet.debit(amount);
        //service层负责和repository层打交道
        walletRepo.updateBalance(walletId, virtualWallet.getBalance());
    }

    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet virtualWallet = convert(walletEntity);
        virtualWallet.credit(amount);
        walletRepo.updateBalance(walletId, virtualWallet.getBalance());
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionEntity.setStatus(0);
        Long transactionId = transactionRepo.saveTransaction(transactionEntity);
        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (RuntimeException e) {
            transactionRepo.updateStatus(transactionId, -1);
        } catch (Exception e) {
            transactionRepo.updateStatus(transactionId, -2);
        }
        transactionRepo.updateStatus(transactionId, 10);
    }

    private VirtualWallet convert(VirtualWalletEntity virtualWalletEntity) {
        return new VirtualWallet();
    }
}
