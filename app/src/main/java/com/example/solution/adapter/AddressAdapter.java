package com.example.solution.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solution.R;
import com.example.solution.pojo.Address;

import java.util.List;


public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<Address> mAddressList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView addressDistrict;
        TextView addressCellname;
        TextView addressBuilding;

        public ViewHolder(View view) {
            super(view);
            addressDistrict = (TextView) view.findViewById(R.id.Address_district);
            addressCellname = (TextView) view.findViewById(R.id.Address_cellname);
            addressBuilding = (TextView) view.findViewById(R.id.Address_building);
        }
    }

    public AddressAdapter(List<Address> addressList) {
        mAddressList = addressList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
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
}
