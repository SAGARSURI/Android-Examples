package com.sagar.android_examples.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_examples.R;
import com.sagar.android_examples.fragment_to_activity_communication.ContainerActivity;
import com.sagar.android_examples.full_screen_fragments.FullScreenFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private List<String> itemList;
    private Context context;

    public ItemAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        itemList = new ArrayList<>();
    }

    public void setItemList(List<String> currentItemList) {
        int currentSize = itemList.size();
        itemList.clear();
        itemList.addAll(currentItemList);
        notifyItemRangeRemoved(0, currentSize);
        notifyItemRangeInserted(0, currentItemList.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.custom_item_row, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemName.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemName)
        TextView itemName;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.itemName)
        void itemClick() {
            switch (getAdapterPosition()) {
                case 0:
                    FullScreenFragment screenFragment = new FullScreenFragment();
                    FragmentTransaction fullScreenTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    fullScreenTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fullScreenTransaction.add(android.R.id.content, screenFragment).addToBackStack(null).commit();
                    break;
                case 1:
                    Intent intent = new Intent(context, ContainerActivity.class);
                    context.startActivity(intent);
            }
        }
    }
}
