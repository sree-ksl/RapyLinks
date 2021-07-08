package com.ksl.payrapyd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TransactionAdapter  extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactionItem;

    public TransactionAdapter(List<Transaction> transactionItem) {
        this.transactionItem = transactionItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.amount.setText(transactionItem.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return transactionItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView amount;
        TextView status;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            amount = itemView.findViewById(R.id.amount);
            status = itemView.findViewById(R.id.status);
        }
    }
}
