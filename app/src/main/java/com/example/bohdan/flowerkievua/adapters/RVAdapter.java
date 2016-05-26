package com.example.bohdan.flowerkievua.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bohdan.flowerkievua.DetailActivity;
import com.example.bohdan.flowerkievua.R;
import com.example.bohdan.flowerkievua.utils.Flowers;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bohdan on 18.05.2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemsViewHolder> {
    //    private CardView cv;
//    private TextView name;
//    private TextView cost;
    private List<Flowers> items;
    //    private ImageView photo;
    private Context context;
    private OnItemClickListener mItemClickListener;

    //    RVAdapter(View itemView) {
//
//        cv = (CardView)itemView.findViewById(R.id.cardView);
//        name = (TextView)itemView.findViewById(R.id.Item_Name);
//        photo = (ImageView)itemView.findViewById(R.id.imageView);
//        cost = (TextView)itemView.findViewById(R.id.Item_Cost);
//    }
    public RVAdapter(Context context, List<Flowers> items) {
        this.items = items;
        this.context = context;
//        Log.d("ADApter", "RV ADAPTER");
    }

    @Override
    public RVAdapter.ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ItemsViewHolder ivh = new ItemsViewHolder(v, mItemClickListener);
        return ivh;

    }

    @Override
    public void onBindViewHolder(RVAdapter.ItemsViewHolder holder, int position) {
        holder.name.setText(items.get(position).getFlowersName());
        holder.cost.setText(items.get(position).getFlowersCost());
        Picasso.with(context).load("http://flower.kiev.ua/wp-content/uploads/" + items.get(position).getFlowersImageUrl()).into(holder.personPhoto);
//        holder.personPhoto.setImageResource(items.get(position).photoId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;

    }

    public interface OnItemClickListener {
        public void OnItemClick(View v, int position);

    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView name;
        TextView cost;
        ImageView personPhoto;

        public ItemsViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            name = (TextView) itemView.findViewById(R.id.Item_Name);
            cost = (TextView) itemView.findViewById(R.id.Item_Cost);
            personPhoto = (ImageView) itemView.findViewById(R.id.imageView);
            personPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] products = new String[items.size()];
                    for (int i = 0; i < items.size(); i++) {
                        products[i] = items.get(i).getFlowersDetail();
                    }

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position", getAdapterPosition());
                    intent.putExtra("DetailURL", products);
                    context.startActivity(intent);
                }
            });

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "LOLO " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    String[] products = new String[items.size()];
                    for (int i = 0; i < items.size(); i++) {
                        products[i] = items.get(i).getFlowersDetail();
                    }

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position", getAdapterPosition());
                    intent.putExtra("DetailURL", products);
                    context.startActivity(intent);
                }
            });

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener == null) {
                mItemClickListener.OnItemClick(v, getPosition());
            }
        }
    }

}

