package com.example.project_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView menuImage, menuImage2;
        TextView name, name2;
        TextView price, price2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // 추천상품
            menuImage = itemView.findViewById(R.id.menuImage);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

            menuImage2 = itemView.findViewById(R.id.menuImage2);
            name2 = itemView.findViewById(R.id.name2);
            price2 = itemView.findViewById(R.id.price2);

        }
    }

    private ArrayList<RecycleViewItem> mList = null;

    public RecycleViewAdapter(ArrayList<RecycleViewItem> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 1.추천상품
        View view = inflater.inflate(R.layout.listview_item, parent, false);
        RecycleViewAdapter.ViewHolder vh = new RecycleViewAdapter.ViewHolder(view);

        return vh;

    }

    // 추천상품의 이미지
    int[] imageID = { R.drawable.lg_hd_tv, R.drawable.lg_standbyme, R.drawable.lg_allred,R.drawable.lg_allred_tv};

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시 (추천상품)
    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        RecycleViewItem item = mList.get(position);
        holder.menuImage.setImageResource(imageID[position]);
        holder.name.setText(item.getMainText());
        holder.price.setText(item.getSubText());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}