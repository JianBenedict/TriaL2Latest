package com.example.trial2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<TransactionItem> transactionList;

    public TransactionAdapter(List<TransactionItem> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransactionItem item = transactionList.get(position);
        holder.labelTextView.setText(item.getLabel());
        holder.valueTextView.setText(item.getValue());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView labelTextView;
        TextView valueTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            labelTextView = itemView.findViewById(R.id.labelTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);
        }
    }
}