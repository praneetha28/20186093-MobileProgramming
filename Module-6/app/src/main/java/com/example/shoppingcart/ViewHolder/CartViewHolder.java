package com.example.shoppingcart.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingcart.Interface.ItemClickListener;
import com.example.shoppingcart.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView productName, productPrice, productQuantity;
    private ItemClickListener itemClickListener;

    public CartViewHolder(View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.cart_product_name);
        productPrice = itemView.findViewById(R.id.cart_product_price);
        productQuantity = itemView.findViewById(R.id.cart_product_quantity);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.OnClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
