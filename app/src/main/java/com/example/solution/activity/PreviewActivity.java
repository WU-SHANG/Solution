package com.example.solution.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solution.R;
import com.example.solution.pojo.LoanLetter;
import com.example.solution.pojo.PaymentMethod;
import com.example.solution.util.HttpUtil;
import com.google.gson.Gson;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PreviewActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        //获取第一个界面传过来的值
        int pos = getIntent().getIntExtra("position",-1);
        String district = getIntent().getStringExtra("district");
        String cellname = getIntent().getStringExtra("cellname");
        Log.d(TAG, String.valueOf(pos));
        //第一个界面的数据直接赋值
        pre_district = findViewById(R.id.pre_district);
        pre_district.setText(district);
        pre_cellname = findViewById(R.id.pre_cellname);
        pre_cellname.setText(cellname);
        //绑定视图
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

        getLoanLetterData(this, pos);
        getPaymentMethodData(this, pos, 0);

    }

    /**
     * 获取后端的批贷函数据
     * @param context
     */
    private void getLoanLetterData(Context context, int pos) {
        String url = "http://192.168.0.115:56270/api/HL_LoanLetter/" + pos;
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
                                Log.d("data", responseData);
                                //解析
                                parseLoanLetterJSON(responseData);

                                //解析出来的数据给界面赋值
                                pre_area.setText("面积：" + loanLetter.getArea() + "平米");
                                pre_price.setText("评估单价：" + loanLetter.getEvaluationUnitPrice() + "元/平米");
                                pre_total.setText(loanLetter.getEvaluationTotalPrice());
                                tv_LoanAgency.setText(loanLetter.getLoanAgency());
                                tv_LoanType.setText(loanLetter.getLoanType());
                                tv_ProductAdvantages.setText(loanLetter.getProductAdvantages());
                                tv_LoanMoney.setText(loanLetter.getLoanMoney());
                                tv_AnnualRate.setText(loanLetter.getAnnualRate());
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
        String url = "http://192.168.0.115:56270/api/GetPaymentMethod?id=" + pos + "&typeid=" + type;
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
                                Log.d("data", responseData);
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
}