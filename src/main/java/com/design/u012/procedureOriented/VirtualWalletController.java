package com.design.u012.procedureOriented;

import java.math.BigDecimal;

public class VirtualWalletController {
    // 通过构造函数或者IOC框架注入
    private VirtualWalletService virtualWalletService;

    //查询余额
    public BigDecimal getBalance(Long walletId) {
        return virtualWalletService.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        virtualWalletService.debit(walletId, amount);
    } //出账

    public void credit(Long walletId, BigDecimal amount) {
        virtualWalletService.credit(walletId, amount);
    } //入账

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        virtualWalletService.transfer(fromWalletId, toWalletId, amount);
    } //转账
}
