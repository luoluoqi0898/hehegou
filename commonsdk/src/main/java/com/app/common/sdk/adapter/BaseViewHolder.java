package com.app.common.sdk.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SparseArray<View> views;

    private BaseAdapter.OnItemClickListener mOnItemClickListener ;

    public BaseViewHolder(View itemView,BaseAdapter.OnItemClickListener onItemClickListener){
        super(itemView);
        itemView.setOnClickListener(this);

        this.mOnItemClickListener = onItemClickListener;
        this.views = new SparseArray<View>();
    }

    public TextView getTextView(int viewId) {
        return findViewById(viewId);
    }

    public CheckBox getCheckBox(int viewId) {
        return findViewById(viewId);
    }

    public Button getButton(int viewId) {
        return findViewById(viewId);
    }

    public ImageView getImageView(int viewId) {
        return findViewById(viewId);
    }

    public View getView(int viewId) {
        return findViewById(viewId);
    }

    public SimpleDraweeView getSimpleDraweeView(int viewId) {
        return findViewById(viewId);
    }


    protected <T extends View> T findViewById(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}
