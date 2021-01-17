package com.design.u012.oop;

import java.math.BigDecimal;

public class VirtualWalletRepository {
    public VirtualWalletEntity getWalletEntity(Long walletId) {
        return new VirtualWalletEntity();
    }

    public BigDecimal getBalance(Long walletId) {
        return new BigDecimal(100);
    }

    public void updateBalance(Long walletId, BigDecimal subtract) {
    }
}
