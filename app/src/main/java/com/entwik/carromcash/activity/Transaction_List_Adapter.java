package com.entwik.carromcash.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.entwik.carromcash.R;
import com.entwik.carromcash.models.wallet.TransactionResponseDataModel;

import java.util.List;

public class Transaction_List_Adapter extends RecyclerView.Adapter<Transaction_List_Adapter.TransactionViewHolder> {

    private List<TransactionResponseDataModel> trans_list;
    public void setTasks(List<TransactionResponseDataModel> trans_List){
        this.trans_list = trans_List;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Transaction_List_Adapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_recycler_item , parent , false);
        return new Transaction_List_Adapter.TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Transaction_List_Adapter.TransactionViewHolder holder, int position) {
        final TransactionResponseDataModel item = trans_list.get(position);
        String name = item.getName();
        holder.transac_name.setText(name);
        if(item.getType().equals("credit")) {
            holder.amount.setText("+" + item.getAmount());
        }
        if(item.getType().equals("debit")) {
            holder.amount.setText("-" + item.getAmount());
        }
        String status = item.getState();
        if(status.equals("failed")){
            holder.status.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.red, null));
        }
        if(status.equals("pending")){
            holder.status.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.yellow, null));
        }
        if(status.equals("success")){
            holder.status.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.green, null));
        }
        holder.status.setText(status);
    }

    @Override
    public int getItemCount() {
        if(trans_list==null)
            return 0;
        Log.d("TAGGG", String.valueOf(trans_list.size()));
        return trans_list.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder{

        TextView amount, time, transac_name, status;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            time = itemView.findViewById(R.id.time);
            transac_name = itemView.findViewById(R.id.trans_name);
            status = itemView.findViewById(R.id.status_wallet);
        }
    }
}
