package com.example.solution.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.solution.R;
import com.example.solution.pojo.Address;

import java.util.List;


public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<Address> mAddressList;
    private OnClickMyRecyclerView mOnClickMyRecyclerView;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View addressView;
        TextView addressDistrict;
        TextView addressCellname;
        TextView addressBuilding;

        public ViewHolder(View view) {
            super(view);
            addressView = view;
            addressDistrict = (TextView) view.findViewById(R.id.address_district);
            addressCellname = (TextView) view.findViewById(R.id.address_cellname);
            addressBuilding = (TextView) view.findViewById(R.id.address_building);
        }
    }

//    public AddressAdapter(List<Address> addressList) {
//        mAddressList = addressList;
//    }


    public void setmAddressList(List<Address> mAddressList) {
        this.mAddressList = mAddressList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.addressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                //回调给activity，让活动处理事件
                mOnClickMyRecyclerView.myRecylerViewClick(position);

//                Address address = mAddressList.get(position);
//                Toast.makeText(v.getContext(), "This is "+ address.getCellname(), Toast.LENGTH_SHORT).show();
//                //跳转到第二个活动界面
//                Intent intent = new Intent(v.getContext(), PreviewActivity.class);
//                intent.putExtra("position", position);
//                v.getContext().startActivity(intent);
            }
        });

        holder.addressView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                mOnClickMyRecyclerView.myRecylerViewLongClick(position, v);
                return true;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Address address = mAddressList.get(position);
        holder.addressDistrict.setText(address.getDistrict());
        holder.addressCellname.setText(address.getCellname());
        holder.addressBuilding.setText(address.getBuilding());
    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    /**
     * 注册函数
     * @param onClickMyRecyclerView
     */
    public void setOnClickMyRecyclerView(OnClickMyRecyclerView onClickMyRecyclerView){
        this.mOnClickMyRecyclerView = onClickMyRecyclerView;
    }
}
