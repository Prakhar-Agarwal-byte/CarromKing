package com.entwik.carromcash.activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.entwik.carromcash.R;
import com.entwik.carromcash.models.home.LobbyModel;

import java.util.List;

public class home_list_adapter extends RecyclerView.Adapter<home_list_adapter.HomeViewHolder> {
    private List<LobbyModel> mLobbyList;

    public void setTasks(List<LobbyModel> mLobbyList){
       // this.mLobbyList = new ArrayList<>();
        this.mLobbyList = mLobbyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public home_list_adapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_lobby_recycler_item , parent , false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull home_list_adapter.HomeViewHolder holder, int position) {
        final LobbyModel item = mLobbyList.get(position);
        String maxP = item.getMaxPlayers() + "P";
        holder.mMaxPlayer.setText(maxP);
        holder.mPlayAmount.setText(String.valueOf(item.getEntryFee()));
        //this needs to be edited by win amount from api
        holder.mWinAmount.setText("₹ 500");
        if(item.isIsLocked()){
            holder.mLobbyIcon.setImageResource(R.drawable.ic_battle_lobby_disable);
            holder.mBattleLevel.setTextColor(Color.parseColor("#717284"));
            holder.mWinText.setTextColor(Color.parseColor("#717284"));
            holder.mWinAmount.setTextColor(Color.parseColor("#717284"));
            holder.mPlay.setVisibility(View.GONE);
            holder.mLock.setVisibility(View.VISIBLE);
            if(item.getLevel().equals("beginner")){
                holder.mLinearLayout.setBackgroundResource(R.drawable.corner_radius_linear_layout);
                holder.mButtonRupee.setImageResource(R.drawable.disable_rupee_sign);
                holder.mPlayAmount.setTextColor(Color.parseColor("#4C4C4C"));
                holder.mBar.setBackgroundColor(Color.parseColor("#4C4C4C"));
            }else if(item.getLevel().equals("diamond")){
                holder.mLock.setImageResource(R.drawable.diamond_button_lock);
            }
        }
        switch (item.getLevel()) {
            case "silver":
                holder.mLinearLayout.setBackgroundResource(R.drawable.silver_button_background);
                holder.mButtonRupee.setImageResource(R.drawable.silver_diamond_button_rupee);
                holder.mBar.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.mPlay.setTextColor(Color.parseColor("#000000"));
                holder.mPlayAmount.setTextColor(Color.parseColor("#000000"));
                holder.mConstraintLayout.setBackgroundResource(R.drawable.silver_card_view_boundary);
                break;
            case "gold":
                holder.mLinearLayout.setBackgroundResource(R.drawable.gold_button_background);
                holder.mButtonRupee.setImageResource(R.drawable.gold_button_rupee);
                holder.mBar.setBackgroundColor(Color.parseColor("#C59B21"));
                holder.mPlay.setTextColor(Color.parseColor("#FFFFFF"));
                holder.mPlayAmount.setTextColor(Color.parseColor("#FFFFFF"));
                holder.mConstraintLayout.setBackgroundResource(R.drawable.gold_cardview_boundary);
                break;
            case "diamond":
                holder.mLinearLayout.setBackgroundResource(R.drawable.diamond_button_background);
                holder.mButtonRupee.setImageResource(R.drawable.silver_diamond_button_rupee);
                holder.mBar.setBackgroundColor(Color.parseColor("#F8F9F9"));
                holder.mPlay.setTextColor(Color.parseColor("#FFFFFF"));
                holder.mPlayAmount.setTextColor(Color.parseColor("#FFFFFF"));
                holder.mConstraintLayout.setBackgroundResource(R.drawable.diamond_card_view_boundary);
                break;
        }

    }

    @Override
    public int getItemCount() {
        if(mLobbyList == null) {
            return 0;
        }
        return mLobbyList.size();
    }


    public static class HomeViewHolder extends RecyclerView.ViewHolder{
        ImageView mLobbyIcon, mButtonRupee, mLock;
        TextView mBattleLevel, mWinAmount, mMaxPlayer, mPlayAmount,mPlay, mWinText;
        LinearLayout mLinearLayout;
        View mBar;
        ConstraintLayout mConstraintLayout;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            mLobbyIcon = itemView.findViewById(R.id.imageView);
            mBattleLevel = itemView.findViewById(R.id.battle_level);
            mWinAmount = itemView.findViewById(R.id.textView6);
            mMaxPlayer = itemView.findViewById(R.id.textView7);
            mLinearLayout = itemView.findViewById(R.id.linearLayout);
            mButtonRupee = itemView.findViewById(R.id.imageView2);
            mPlayAmount = itemView.findViewById(R.id.textView10);
            mBar = itemView.findViewById(R.id.view);
            mPlay = itemView.findViewById(R.id.textView11);
            mLock = itemView.findViewById(R.id.lobby_button_lock);
            mWinText =itemView.findViewById(R.id.textView5);
            mConstraintLayout = itemView.findViewById(R.id.card_view_boundary);
        }
    }


}
