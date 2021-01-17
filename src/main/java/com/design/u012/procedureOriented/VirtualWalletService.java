package com.design.u012.procedureOriented;

import java.math.BigDecimal;

/**
 * 面向过程的MVC结构写法，在很多场景下我们下意识写出来的代码结构
 */
public class VirtualWalletService {
    // 通过构造函数或者IOC框架注入
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWalletBo walletBo = convert(walletEntity);
        return walletBo;
    }

    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        return new VirtualWalletBo();
    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepo.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new RuntimeException("余额不足");
        }
        walletRepo.updateBalance(walletId, balance.subtract(amount));
    }

    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        walletRepo.updateBalance(walletId, balance.add(amount));
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
}
