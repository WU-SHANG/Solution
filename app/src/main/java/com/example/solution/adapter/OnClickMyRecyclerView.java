package com.example.solution.adapter;

import android.view.View;

public interface OnClickMyRecyclerView {
    /**
     * 列表点击事件的回调函数
     * @param id
     */
    void myRecylerViewClick(int id);

    /**
     * 列表长按事件的回调函数
     * @param id
     */
    void myRecylerViewLongClick(int id, View view);

}
