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

        performSyncHttpRequest();

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
        String url = "http://www.baidu.com";
        HttpUtil.sendHttpRequest(url, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "code==" + response.code());
                    Log.d(TAG, "body().string()==" + response.body());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }

        });
    }


    public void handleMessage(int msg) {
        Looper.prepare();
        switch (msg) {
            case 200:
                Toast.makeText(MainActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();
                break;
            case 404:
                Toast.makeText(MainActivity.this, "request failed", Toast.LENGTH_SHORT).show();
                break;

        }
        Looper.loop();
    }

    private void performSyncHttpRequest() {
        Runnable requestTask = new Runnable() {
            @Override
            public void run() {
                int msg = 404;
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")
                            .build();
                    Call call = client.newCall(request);
                    // 1
                    Response response = call.execute();

                    if (!response.isSuccessful()) {
                        msg = 200;
                    } else {
                        msg = 404;
                    }
                } catch (IOException ex) {
                    msg = 404;
                } finally {
                    handleMessage(msg);
                }
            }
        };

        Thread requestThread = new Thread(requestTask);
        requestThread.start();
    }
}