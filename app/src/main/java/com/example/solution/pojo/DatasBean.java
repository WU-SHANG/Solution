package com.example.solution.pojo;

public class DatasBean {


    /**
     * 网络请求版的bean，最下面改为静态内部类
     * ret : 0
     * msg : succes,
     * datas : [{"ID":"  0","categoryName":"社团","state":"1"},{"ID":"1","categoryName":"原创","state":"1"},{"ID":"2","categoryName":"现货","state":"1"}]
     */

//    private int ret;
//    private String msg;
//    private List<DatasBean> datas;
//
//    public int getRet() {
//        return ret;
//    }
//
//    public void setRet(int ret) {
//        this.ret = ret;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<DatasBean> getDatas() {
//        return datas;
//    }
//
//    public void setDatas(List<DatasBean> datas) {
//        this.datas = datas;
//    }


    /**
     * ID :   0
     * repaymentType : 还款方式
     * state : 1
     */

    private int ID;
    private String RepaymentType;
    private String state;

    public DatasBean(int ID, String repaymentType, String state) {
        this.ID = ID;
        RepaymentType = repaymentType;
        this.state = state;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRepaymentType() {
        return RepaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        RepaymentType = repaymentType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
