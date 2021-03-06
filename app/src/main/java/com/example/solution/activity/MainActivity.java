package com.example.solution.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solution.R;
import com.example.solution.adapter.AddressAdapter;
import com.example.solution.adapter.OnClickMyRecyclerView;
import com.example.solution.pojo.Address;
import com.example.solution.util.BubblePopupWindow;
import com.example.solution.util.BubbleRelativeLayout;
import com.example.solution.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Address> addressList = new ArrayList<>();

    RecyclerView recyclerView;

    private AddressAdapter addressAdapter = new AddressAdapter();

    private BubblePopupWindow topWindow;

    private BubbleRelativeLayout mBubbleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载布局
        recyclerView = findViewById(R.id.recycler_view);
        //从后端获取地址数据
        getAddressList(this);
        //初始化本地地址数据（死数据）
        //initAddress();

        //上方气泡
        topWindow = new BubblePopupWindow(MainActivity.this);

        //注册适配器的点击事件
        addressAdapter.setOnClickMyRecyclerView(new OnClickMyRecyclerView() {
            @Override
            public void myRecylerViewClick(int id) {
                Address address = addressList.get(id);
                Toast.makeText(MainActivity.this, "This is " + address.getCellname() +
                        " id is " + address.getId(), Toast.LENGTH_SHORT).show();
                //跳转到第二个活动界面
                Intent intent = new Intent(MainActivity.this, PreviewActivity.class);
                intent.putExtra("position", address.getId());
                intent.putExtra("district", address.getDistrict());
                intent.putExtra("cellname", address.getCellname());
                startActivity(intent);
            }

            @Override
            public void myRecylerViewLongClick(int pos, View view) {
//                Address address = addressList.get(id);
                View bubbleView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_popup_view, null);
                TextView tvContent = (TextView) bubbleView.findViewById(R.id.tvContent);
                tvContent.setText("删除");
                mBubbleView = topWindow.setBubbleView(bubbleView);
                mBubbleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Log.d(TAG, "onClick: " + mBubbleView.getId());
                        addressList.remove(pos);
                        Toast.makeText(MainActivity.this, "Id is " + pos + "成功删除", Toast.LENGTH_SHORT).show();
                        addressAdapter.notifyDataSetChanged();
                    }
                });
                topWindow.show(view);
            }
        });

    }

    /**
     * 模拟Address数据
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

    /**
     * 获取后端传过来的第一个界面的地址列表json数据
     */
    private void getAddressList(Context context) {
        String url = getResources().getString(R.string.url_release_main);
        HttpUtil.sendHttpRequest(context, url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String errorMMessage = e.getMessage();

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

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //判断在主线程还是子线程
                        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                            Log.d(TAG, "Main Thread");
                        } else {
                            Log.d(TAG, "Not Main Thread");
                        }
                        if (response.isSuccessful()){
                            try {
                                String responseData = response.body().string();
                                Log.d("data", responseData);
                                //解析
                                parseAddressJSON(responseData);

                                //需要context对象参数
                                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                                recyclerView.setLayoutManager(layoutManager);
                                addressAdapter.setmAddressList(addressList);
                                recyclerView.setAdapter(addressAdapter);
                                //更改数据时通知主线程
                                addressAdapter.notifyDataSetChanged();

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
     * GSON解析json转化成Address对象数组
     * @param jsonData
     */
    private void parseAddressJSON(String jsonData) {
        Gson gson = new Gson();
        addressList = gson.fromJson(jsonData, new TypeToken<List<Address>>(){}.getType());
        for (Address ad : addressList) {
            Log.d(TAG, "id is " + ad.getId());
            Log.d(TAG, "district is " + ad.getDistrict());
            Log.d(TAG, "cellname is " + ad.getCellname());
            Log.d(TAG, "building is " + ad.getBuilding());
        }
    }

}