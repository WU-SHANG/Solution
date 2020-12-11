package com.example.solution.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.solution.R;
import com.example.solution.pojo.Compare;
import com.example.solution.pojo.DatasBean;
import com.example.solution.pojo.LoanLetter;
import com.example.solution.pojo.PaymentMethod;
import com.example.solution.util.CommonPopWindow;
import com.example.solution.util.HttpUtil;
import com.example.solution.util.PickerScrollView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener, CommonPopWindow.ViewClickListener {

    private static final String TAG = "PreviewActivity";
    /**
     * 批贷函对象
     */
    private LoanLetter loanLetter = new LoanLetter();
    /**
     * 还款方式对象
     */
    private PaymentMethod paymentMethod = new PaymentMethod();
    /**
     * 比较月供对象
     */
    private Compare compare = new Compare();
    /**
     * 文字视图对象
     */
    private TextView pre_district;
    private TextView pre_cellname;
    private TextView pre_area;
    private TextView pre_price;
    private TextView pre_total;
    private TextView tv_LoanAgency; //贷款机构
    private TextView tv_LoanType;   //贷款类型
    private TextView tv_ProductAdvantages;  //产品优势
    private TextView tv_LoanMoney;  //贷款金额
    private TextView tv_AnnualRate;  //年利率
    private TextView tv_LoanLimit;  //贷款年限
    private TextView tv_CreditLimit;  //授信年限
    private TextView tv_PaymentMethod;  //还款方式
    private TextView tv_MonthSupply;  //月供金额
    private TextView tv_Principal;  //年还本金
    private TextView tv_Interesttotal;  //总支付利息
    private TextView tv_MonthSupply_cop;    //弹窗中月供金额
    private TextView tv_Comparison; //弹窗中招行月供金额
    private TextView tv_Difference; //月供差值
    private TextView tv_dialog_title_left;  //弹窗中左边的标题
    private TextView tv_dialog_title_right; //弹窗中右边的标题

    private List<DatasBean> datasBeanList = new ArrayList<DatasBean>(){};
    /**
     * 还款方式String，还款方式int，int是否改变flag
     */
    private String repaymentType;
    private int type;
    private boolean flag;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        //获取第一个界面传过来的值
        pos = getIntent().getIntExtra("position",-1);
        String district = getIntent().getStringExtra("district");
        String cellname = getIntent().getStringExtra("cellname");
        Log.d(TAG, String.valueOf(pos));

        //视图初始化
        initView();

        //第一个界面的数据直接赋值
        pre_district.setText(district);
        pre_cellname.setText(cellname);

        initData();

        //发送网络请求获取数据并显示
        getLoanLetterData(this, pos);
        getPaymentMethodData(this, pos, type);

        tv_PaymentMethod.setOnClickListener(this);
        tv_MonthSupply.setOnClickListener(this);

    }

    /**
     * 获取后端的批贷函数据
     * @param context
     */
    private void getLoanLetterData(Context context, int pos) {
        String url = getResources().getString(R.string.url_release_loanLetter) + pos;
        HttpUtil.sendHttpRequest(context, url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String errorMMessage = e.getMessage();
                PreviewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, errorMMessage);
                        Toast.makeText(PreviewActivity.this, errorMMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                PreviewActivity.this.runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if (response.isSuccessful()){
                            try {
                                String responseData = response.body().string();
                                Log.d("LoanLetterData", responseData);
                                //解析
                                parseLoanLetterJSON(responseData);

                                //解析出来的数据给界面赋值
                                pre_area.setText("面积：" + loanLetter.getArea() + "平米");
                                pre_price.setText("评估单价：" + loanLetter.getEvaluationUnitPrice() + "元/平米");
                                pre_total.setText(loanLetter.getEvaluationTotalPrice() + "");
                                tv_LoanAgency.setText(loanLetter.getLoanAgency());
                                tv_LoanType.setText(loanLetter.getLoanType());
                                tv_ProductAdvantages.setText(loanLetter.getProductAdvantages());
                                tv_LoanMoney.setText(loanLetter.getLoanMoney() + "");
                                tv_AnnualRate.setText(loanLetter.getAnnualRate() + "");
                                tv_LoanLimit.setText(loanLetter.getLoanLimit() + "年");
                                tv_CreditLimit.setText(loanLetter.getCreditLimit() + "年");


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 获取还款方式数据
     */
    private void getPaymentMethodData(Context context,int pos, int type) {
        String url = "http://192.168.0.115:8000/api/GetPaymentMethod?id=" + pos + "&typeid=" + type;
        HttpUtil.sendHttpRequest(context, url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String errorMMessage = e.getMessage();
                PreviewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, errorMMessage);
                        Toast.makeText(PreviewActivity.this, errorMMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                PreviewActivity.this.runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if (response.isSuccessful()){
                            try {
                                String responseData = response.body().string();
                                Log.d("PaymentMethodData", responseData);
                                //解析
                                parsePaymentMethodJSON(responseData);

                                //解析出来的数据给界面赋值
                                tv_PaymentMethod.setText(paymentMethod.getPaymentMethod());
                                tv_MonthSupply.setText(paymentMethod.getMonthSupply() + "元");
                                tv_Principal.setText(paymentMethod.getPrincipal() + "万元");
                                tv_Interesttotal.setText(paymentMethod.getInteresttotal() + "元");


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 获取月供比较数据
     */
    private void getCompareData(Context context,int pos, int type) {
        String url = "https://192.168.0.115:8000/api/GetComparison?id=" + pos + "&typeid=" + type;
        HttpUtil.sendHttpRequest(context, url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String errorMMessage = e.getMessage();
                PreviewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, errorMMessage);
                        Toast.makeText(PreviewActivity.this, errorMMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                PreviewActivity.this.runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if (response.isSuccessful()){
                            try {
                                String responseData = response.body().string();
                                Log.d("CompareData", responseData);
                                //解析
                                parseComparisonJSON(responseData);

                                //解析出来的数据给界面赋值
                                tv_dialog_title_left.setText("按20年" + paymentMethod);
                                tv_dialog_title_right.setText("招行按20年" + paymentMethod);
                                tv_MonthSupply_cop.setText(compare.getMonthSupply() + "");
                                tv_Comparison.setText(compare.getComparison() + "");
                                tv_Difference.setText(compare.getDifference() + "");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 视图初始化
     */
    private void initView() {
        pre_district = findViewById(R.id.pre_district);
        pre_cellname = findViewById(R.id.pre_cellname);
        pre_area = findViewById(R.id.pre_area);
        pre_price = findViewById(R.id.pre_price);
        pre_total = findViewById(R.id.pre_total);
        tv_LoanAgency = findViewById(R.id.tv_LoanAgency);
        tv_LoanType = findViewById(R.id.tv_LoanType);
        tv_ProductAdvantages = findViewById(R.id.tv_ProductAdvantages);
        tv_LoanMoney = findViewById(R.id.tv_LoanMoney);
        tv_AnnualRate = findViewById(R.id.tv_AnnualRate);
        tv_LoanLimit = findViewById(R.id.tv_LoanLimit);
        tv_CreditLimit = findViewById(R.id.tv_CreditLimit);
        tv_PaymentMethod = findViewById(R.id.tv_PaymentMethod);
        tv_MonthSupply = findViewById(R.id.tv_MonthSupply);
        tv_Principal = findViewById(R.id.tv_Principal);
        tv_Interesttotal = findViewById(R.id.tv_Interesttotal);
    }

    /**
     * 滚动选择器数据初始化
     */
    private void initData() {
        //这里就不模拟网络请求和GSON解析了，直接写死
        DatasBean item1 = new DatasBean(0, "等额本息", "1");
        DatasBean item2 = new DatasBean(1, "等额本金", "1");
        datasBeanList.add(item1);
        datasBeanList.add(item2);

        type = 0;
        flag = false;
    }

    /**
     * GSON解析json转换成批贷函对象
     * @param jsonData
     */
    private void parseLoanLetterJSON(String jsonData) {
        Gson gson = new Gson();
        loanLetter = gson.fromJson(jsonData, LoanLetter.class);
        //验证一下解析是否成功
        Log.d(TAG, "面积： " + loanLetter.getArea());
        Log.d(TAG, "产品优势： " + loanLetter.getProductAdvantages());
    }

    /**
     * GSON解析json转换成还款方式对象
     * @param jsonData
     */
    private void parsePaymentMethodJSON(String jsonData) {
        Gson gson = new Gson();
        paymentMethod = gson.fromJson(jsonData, PaymentMethod.class);
        //验证一下解析是否成功
        Log.d(TAG, "还款方式： " + paymentMethod.getPaymentMethod());
        Log.d(TAG, "月供金额： " + paymentMethod.getMonthSupply());
    }

    /**
     * GSON解析json转换成月供比较对象
     * @param jsonData
     */
    private void parseComparisonJSON(String jsonData) {
        Gson gson = new Gson();
        compare = gson.fromJson(jsonData, Compare.class);
        //验证一下解析是否成功
        Log.d(TAG, "月供金额： " + compare.getMonthSupply());
        Log.d(TAG, "按招行月供金额： " + compare.getComparison());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_PaymentMethod:
                setAddressSelectorPopup(v);
                break;
            case R.id.tv_MonthSupply:
                showDialog();
                getCompareData(v.getContext(), pos, type);
        }
    }

    /**
     * 设置弹出的滚动选择器参数
     * @param v
     */
    private void setAddressSelectorPopup(View v) {
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;

        CommonPopWindow.newBuilder()
                .setView(R.layout.pop_picker_selector_bottom)
                .setAnimationStyle(R.style.AnimUp)
                .setBackgroundDrawable(new BitmapDrawable())
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(screenHeigh * 0.25f))
                .setViewOnClickListener(this)
                .setBackgroundDarkEnable(true)
                .setBackgroundAlpha(0.7f)
                .setBackgroundDrawable(new ColorDrawable(999999))
                .build(this)
                .showAsBottom(v);
    }

    /**
     * 选择器内容设置和点击事件监听器
     * @param mPopupWindow
     * @param view
     * @param mLayoutResId
     */
    @Override
    public void getChildView(final PopupWindow mPopupWindow, View view, int mLayoutResId) {
        switch (mLayoutResId) {
            case R.layout.pop_picker_selector_bottom:
                TextView imageBtn = view.findViewById(R.id.img_finish);
                PickerScrollView addressSelector = view.findViewById(R.id.address);

                // 设置数据，默认选择第一条
                addressSelector.setData(datasBeanList);
                addressSelector.setSelected(0);

                addressSelector.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(DatasBean pickers) {
                        repaymentType = pickers.getRepaymentType();
                        int newType = pickers.getID();
                        if (newType != type) {
                            type = newType;
                            flag = true;
                        }
                    }
                });

                imageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        if (flag) {
                            tv_PaymentMethod.setText(repaymentType);
                            getPaymentMethodData(PreviewActivity.this, pos, type);
                            flag = false;
                        }
                    }
                });

                break;
        }
    }

    /**
     * 显示弹窗
     */
    private void showDialog(){
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_layout,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        //设置背景透明
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageView btn = (ImageView) view.findViewById(R.id.dialog_btn); //关闭按钮
        Log.d("Btn", String.valueOf(btn));
        tv_MonthSupply_cop = findViewById(R.id.tv_MonthSupply_cop);
        tv_Comparison = findViewById(R.id.tv_Comparison);
        tv_Difference = findViewById(R.id.tv_Difference);
        tv_dialog_title_left = findViewById(R.id.tv_dialog_title_left);
        tv_dialog_title_right = findViewById(R.id.tv_dialog_title_right);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}