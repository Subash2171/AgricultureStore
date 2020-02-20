package com.subash.agriculturestore.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.subash.agriculturestore.R;
import com.subash.agriculturestore.model.Product;
import com.subash.agriculturestore.url.URL;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ItemViewHolder>  {

    Context context;

    List<Product> itemList;
    public productAdapter(Context context, List<Product> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public productAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.ItemViewHolder holder, int position) {
        final Product item=itemList.get(position);
        holder.tvName.setText(item.getName());
        holder.tvDescription.setText(item.getDescription());
        if (item.getDiscount_rate() != 0){
            holder.tvDiscountRate.setText(" -" + item.getDiscount_rate() + "% ");
            double price = item.getPrice();
            double discount_rate = item.getDiscount_rate();
            double new_price = price - ((discount_rate/100) * price);
            holder.tvPrice.setText("Rs." + new_price);
            holder.tvOldPrice.setText("Rs." + item.getPrice());
            holder.tvOldPrice.setPaintFlags(holder.tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            holder.tvPrice.setText("Rs." + item.getPrice());
        }
        holder.pop_ratingbar.setRating(item.getRating());
        Picasso.get().load(URL.base_url_image+itemList.get(position).getImage()).into(holder.imgImage);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imgImage;
        TextView tvName, tvDescription, tvPrice, tvOldPrice, tvDiscountRate;
        RatingBar pop_ratingbar;
        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            imgImage=itemView.findViewById(R.id.ivProductImage);
            tvName=itemView.findViewById(R.id.tvProduct_name);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvOldPrice=itemView.findViewById(R.id.tvOldPrice);
            tvDiscountRate=itemView.findViewById(R.id.tvDiscountRate);
            pop_ratingbar=itemView.findViewById(R.id.pop_ratingbar);
        }
    }
}
