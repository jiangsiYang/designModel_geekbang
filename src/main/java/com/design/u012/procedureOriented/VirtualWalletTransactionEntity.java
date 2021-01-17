package com.design.u012.procedureOriented;

import java.math.BigDecimal;

/**
 * 虚拟钱包转账流水记录
 */
public class VirtualWalletTransactionEntity {
    private BigDecimal amount;
    private long createTime;
    private Long fromWalletId;
    private Long toWalletId;
    private int status;

    public void setAmount(BigDecimal amount) {
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setFromWalletId(Long fromWalletId) {
        this.fromWalletId = fromWalletId;
    }

    public Long getFromWalletId() {
        return fromWalletId;
    }

    public void setToWalletId(Long toWalletId) {
        this.toWalletId = toWalletId;
    }

    public Long getToWalletId() {
        return toWalletId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
