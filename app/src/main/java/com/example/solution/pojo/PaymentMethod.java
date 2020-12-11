package com.example.solution.pojo;

import java.math.BigDecimal;

public class PaymentMethod {
    /**
     * 还款方式
     */
    private String PaymentMethod;
    /**
     * 月供金额
     */
    private BigDecimal MonthSupply;
    /**
     * 年还本金
     */
    private BigDecimal Principal;
    /**
     * 总支付利息
     */
    private BigDecimal Interesttotal;

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public BigDecimal getMonthSupply() {
        return MonthSupply;
    }

    public void setMonthSupply(BigDecimal monthSupply) {
        MonthSupply = monthSupply;
    }

    public BigDecimal getPrincipal() {
        return Principal;
    }

    public void setPrincipal(BigDecimal principal) {
        Principal = principal;
    }

    public BigDecimal getInteresttotal() {
        return Interesttotal;
    }

    public void setInteresttotal(BigDecimal interesttotal) {
        Interesttotal = interesttotal;
    }
}
