package com.example.solution.pojo;

public class LoanLetter {
    /**
     * 面积
     */
    private String Area;
    /**
     * 评估单价
     */
    private String EvaluationUnitPrice;
    /**
     * 评估总价
     */
    private String EvaluationTotalPrice;

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
    private String LoanMoney;
    /**
     * 年利率
     */
    private String AnnualRate;
    /**
     * 贷款年限
     */
    private int LoanLimit;
    /**
     * 授信年限
     */
    private int CreditLimit;

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getEvaluationUnitPrice() {
        return EvaluationUnitPrice;
    }

    public void setEvaluationUnitPrice(String evaluationUnitPrice) {
        EvaluationUnitPrice = evaluationUnitPrice;
    }

    public String getEvaluationTotalPrice() {
        return EvaluationTotalPrice;
    }

    public void setEvaluationTotalPrice(String evaluationTotalPrice) {
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

    public String getLoanMoney() {
        return LoanMoney;
    }

    public void setLoanMoney(String loanMoney) {
        LoanMoney = loanMoney;
    }

    public String getAnnualRate() {
        return AnnualRate;
    }

    public void setAnnualRate(String annualRate) {
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
