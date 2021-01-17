package com.design.u012.oop;

import com.design.u012.procedureOriented.VirtualWalletEntity;

import java.math.BigDecimal;

/**
 * 用OOP的思维来思考虚拟钱包的domain
 * 注意，之所以让 VirtualWalletService 类与 Repository 打交道，而不是让领域模型 VirtualWallet 与 Repository 打交道，那是因为我们想保持领域模型的独立性，
 * 不与任何其他层的代码（Repository 层的代码）或开发框架（比如 Spring、MyBatis）耦合在一起，将流程性的代码逻辑（比如从 DB 中取数据、映射数据）与领域模型
 * 的业务逻辑解耦，让领域模型更加可复用。
 */
public class VirtualWallet {
    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;

    //业务越来越复杂的时候，充血模型的优势就体现出来了,比如新业务，支持透支一定额度和冻结部分余额的功能
    private boolean isAllowedOverdraft = true;
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getAvailableBalance() {
        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvaliableBalance = this.overdraftAmount.add(totalAvaliableBalance);
        }
        return totalAvaliableBalance;
    }

    public void freeze(BigDecimal amount) {
    }

    public void unfreeze(BigDecimal amount) {
    }

    public void increaseOverdraftAmount(BigDecimal amount) {
    }

    public void decreaseOverdraftAmount(BigDecimal amount) {
    }

    public void closeOverdraft() {
    }

    public void openOverdraft() {
    }


    public void debit(BigDecimal amount) {
        BigDecimal totalAvaliableBalance = getAvailableBalance();
        if (balance.compareTo(totalAvaliableBalance) < 0) {
            throw new RuntimeException("余额不足");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("充值的金额要大于0");
        }
        this.balance = this.balance.add(amount);
    }
}
