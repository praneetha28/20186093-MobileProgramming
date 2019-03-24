package com.example.shoppingcart.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.CartActivity;
import com.example.shoppingcart.Interface.ItemClickListener;
import com.example.shoppingcart.Model.Cart;
import com.example.shoppingcart.Prevalent.Prevalent;
import com.example.shoppingcart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView productName, productPrice, productQuantity;
    private ItemClickListener itemClickListener;
    private Button delete;
//    private DetailsAdapterListener dlistener;

    public CartViewHolder(View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.cart_product_name);
        productPrice = itemView.findViewById(R.id.cart_product_price);
        productQuantity = itemView.findViewById(R.id.cart_product_quantity);
        delete = (Button) itemView.findViewById ( R.id.delete_btn );
    }

    @Override
    public void onClick(View v) {
        itemClickListener.OnClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

}
