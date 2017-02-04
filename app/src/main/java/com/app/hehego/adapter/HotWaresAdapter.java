package com.app.hehego.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.app.common.sdk.adapter.BaseAdapter;
import com.app.common.sdk.adapter.BaseViewHolder;
import com.app.common.sdk.adapter.SimpleAdapter;
import com.app.common.sdk.utils.LogUtils;
import com.app.hehego.Config.Contants;
import com.app.hehego.R;
import com.app.hehego.Utils.CartProvider;
import com.app.hehego.activity.WareDetailActivity;
import com.app.hehego.bean.Wares;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HotWaresAdapter extends SimpleAdapter<Wares> {

    CartProvider provider ;

    public HotWaresAdapter(final Context context, List<Wares> datas) {
        super(context, R.layout.template_hot_wares, datas);
        provider = new CartProvider(context);

        setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                try {
                    LogUtils.toastMsg(context,"暂未实现");
                    Wares wares = getItem(position);
                    Intent intent = new Intent(context, WareDetailActivity.class);
                    intent.putExtra(Contants.WARE,wares);
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, final Wares wares) {
        SimpleDraweeView draweeView = (SimpleDraweeView) viewHolder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(wares.getImgUrl()));

        viewHolder.getTextView(R.id.text_title).setText(wares.getName());
        viewHolder.getTextView(R.id.text_price).setText("￥ " + wares.getPrice());

        Button button =viewHolder.getButton(R.id.btn_add);
        if(button !=null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    provider.put(wares);
                    LogUtils.toastMsg(context,"已添加到购物车");
                }
            });
        }

    }

    public void  resetLayout(int layoutId){

        this.layoutResId  = layoutId;
        notifyItemRangeChanged(0,getDatas().size());

    }

}
