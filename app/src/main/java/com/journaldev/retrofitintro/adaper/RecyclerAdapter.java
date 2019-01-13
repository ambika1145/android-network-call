package com.journaldev.retrofitintro.adaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.journaldev.retrofitintro.R;
import com.journaldev.retrofitintro.pojo.DataModel;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<DataModel> itemList;

    public RecyclerAdapter(List<DataModel>itemList, Context mContext) {
        this.mContext = mContext;
        this.itemList = itemList ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = null;

        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {

        if (itemList.get(pos).description!=null && !itemList.get(pos).description.equals("null"))
            viewHolder.txtDesc.setText(itemList.get(pos).description);
        else   viewHolder.txtDesc.setVisibility(View.GONE);



        if (itemList.get(pos).title!=null && !itemList.get(pos).title.equals("null"))
        viewHolder.txttitle.setText(itemList.get(pos).title);

        else   viewHolder.txttitle.setVisibility(View.GONE);

        if (itemList.get(pos).imageHref!=null&& !itemList.get(pos).imageHref.equals("null")) {
            Glide.with(mContext)
                    .load(itemList.get(pos).imageHref)
                    .asBitmap()
                    .into(viewHolder.imgage);
        }
        else viewHolder.imgage.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txttitle;
        TextView txtDesc;
        ImageView imgage;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.title);
            txtDesc = itemView.findViewById(R.id.description);
            imgage = itemView.findViewById(R.id.img);
        }
    }

}
