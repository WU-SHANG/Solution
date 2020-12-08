package com.example.solution.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.solution.R;
import com.example.solution.adapter.AddressAdapter;
import com.example.solution.pojo.Address;
import com.example.solution.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Address> addressList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAddressList();
        //初始化地址数据
        initAddress();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AddressAdapter adapter = new AddressAdapter(addressList);
        recyclerView.setAdapter(adapter);

    }

    /**
     * 请求后端Address数据
     */
    private void initAddress() {
        for (int i = 0; i < 2; i++) {
            Address a1 = new Address("北京市-昌平区", "天通苑东苑二区", "十号楼-1001");
            addressList.add(a1);
            Address a2 = new Address("南京市-江宁区", "雄州西路199号", "10幢二单元103");
            addressList.add(a2);
            Address a3 = new Address("天津市-北辰区", "天泰天逸园", "1-1-1001");
            addressList.add(a3);
            Address a4 = new Address("北京市-朝阳区", "道家村", "1号楼1单元102");
            addressList.add(a4);
            Address a5 = new Address("北京市-朝阳区", "幸福东园", "11号楼2单元101");
            addressList.add(a5);
        }
    }

    private void getAddressList() {
        String url = "http://192.168.0.115:56270/api/houseloans";
        HttpUtil.sendHttpRequest(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String errorMMessage = e.getMessage();

                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    Log.d(TAG, "Main Thread");
                } else {
                    Log.d(TAG, "Not Main Thread");
                }

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, errorMMessage);
                        Toast.makeText(MainActivity.this, errorMMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    Log.d(TAG, "Main Thread");
                } else {
                    Log.d(TAG, "Not Main Thread");
                }

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()){
                            try {
                                Log.d(TAG, response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        //Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}