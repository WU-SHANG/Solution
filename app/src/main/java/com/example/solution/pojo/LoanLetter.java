package com.example.solution.pojo;

import java.math.BigDecimal;

public class LoanLetter {
    /**
     * 面积
     */
    private double Area;
    /**
     * 评估单价
     */
    private BigDecimal EvaluationUnitPrice;
    /**
     * 评估总价
     */
    private BigDecimal EvaluationTotalPrice;

    /**
     * 贷款机构
     */
    private String LoanAgency;
    /**
     * 贷款类型
     */
    private String LoanType;
    /**
     * 产品优势
     */
    private String ProductAdvantages;
    /**
     * 贷款金额
     */
    private int LoanMoney;
    /**
     * 年利率
     */
    private double AnnualRate;
    /**
     * 贷款年限
     */
    private int LoanLimit;
    /**
     * 授信年限
     */
    private int CreditLimit;

    public double getArea() {
        return Area;
    }

    public void setArea(double area) {
        Area = area;
    }

    public BigDecimal getEvaluationUnitPrice() {
        return EvaluationUnitPrice;
    }

    public void setEvaluationUnitPrice(BigDecimal evaluationUnitPrice) {
        EvaluationUnitPrice = evaluationUnitPrice;
    }

    public BigDecimal getEvaluationTotalPrice() {
        return EvaluationTotalPrice;
    }

    public void setEvaluationTotalPrice(BigDecimal evaluationTotalPrice) {
        EvaluationTotalPrice = evaluationTotalPrice;
    }

    public String getLoanAgency() {
        return LoanAgency;
    }

    public void setLoanAgency(String loanAgency) {
        LoanAgency = loanAgency;
    }

    public String getLoanType() {
        return LoanType;
    }

    public void setLoanType(String loanType) {
        LoanType = loanType;
    }

    public String getProductAdvantages() {
        return ProductAdvantages;
    }

    public void setProductAdvantages(String productAdvantages) {
        ProductAdvantages = productAdvantages;
    }

    public int getLoanMoney() {
        return LoanMoney;
    }

    public void setLoanMoney(int loanMoney) {
        LoanMoney = loanMoney;
    }

    public double getAnnualRate() {
        return AnnualRate;
    }

    public void setAnnualRate(double annualRate) {
        AnnualRate = annualRate;
    }

    public int getLoanLimit() {
        return LoanLimit;
    }

    public void setLoanLimit(int loanLimit) {
        LoanLimit = loanLimit;
    }

    public int getCreditLimit() {
        return CreditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        CreditLimit = creditLimit;
    }
}
