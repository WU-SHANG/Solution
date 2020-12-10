package com.example.solution.pojo;

public class PaymentMethod {
    /**
     * 还款方式
     */
    private String PaymentMethod;
    /**
     * 月供金额
     */
    private String MonthSupply;
    /**
     * 年还本金
     */
    private String Principal;
    /**
     * 总支付利息
     */
    private String Interesttotal;

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public String getMonthSupply() {
        return MonthSupply;
    }

    public void setMonthSupply(String monthSupply) {
        MonthSupply = monthSupply;
    }

    public String getPrincipal() {
        return Principal;
    }

    public void setPrincipal(String principal) {
        Principal = principal;
    }

    public String getInteresttotal() {
        return Interesttotal;
    }

    public void setInteresttotal(String interesttotal) {
        Interesttotal = interesttotal;
    }
}
