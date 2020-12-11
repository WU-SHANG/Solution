package com.example.solution.pojo;

import java.math.BigDecimal;

/**
 * 月供金额弹窗比较数据
 */
public class Compare {
    /**
     * 月供金额
     */
    private BigDecimal MonthSupply;
    /**
     * 按招行月供金额
     */
    private BigDecimal Comparison;
    /**
     * 月差
     */
    private BigDecimal Difference;

    public BigDecimal getMonthSupply() {
        return MonthSupply;
    }

    public void setMonthSupply(BigDecimal monthSupply) {
        MonthSupply = monthSupply;
    }

    public BigDecimal getComparison() {
        return Comparison;
    }

    public void setComparison(BigDecimal comparison) {
        Comparison = comparison;
    }

    public BigDecimal getDifference() {
        return Difference;
    }

    public void setDifference(BigDecimal difference) {
        Difference = difference;
    }
}
