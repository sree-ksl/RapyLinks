package com.ksl.payrapyd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<ProductItem> productItem;

    public ProductsAdapter(List<ProductItem> productItem){
        this.productItem = productItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.name.setText(productItem.get(position).getName());
        holder.price.setText(productItem.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return productItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        Button link;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            link = itemView.findViewById(R.id.showLink);
        }
    }
}
