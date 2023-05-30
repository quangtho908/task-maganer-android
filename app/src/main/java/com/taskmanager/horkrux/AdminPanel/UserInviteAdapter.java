package com.taskmanager.horkrux.AdminPanel;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.taskmanager.horkrux.Activites.AssignTaskActivity;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.InviteUserItemBinding;

import java.util.ArrayList;

public class UserInviteAdapter extends RecyclerView.Adapter<UserInviteAdapter.UserInviteViewHolder> {
    Context context;
    ArrayList<Users> users;
    ArrayList<Users> backUsers;
    String from;


    public UserInviteAdapter(Context context, ArrayList<Users> users, String from) {
        this.context = context;
        this.users = users;
        this.backUsers = users;
        this.from = from;
    }

    @NonNull
    @Override
    public UserInviteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invite_user_item, parent, false);
        return new UserInviteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInviteViewHolder holder, int position) {
        Users user = users.get(position);
        holder.binding.username.setText(user.getUserName());
        holder.binding.email.setText(user.getUserEmail());
        if(user.getUserProfile().equals(Users.NO_PROFILE)) {
            holder.binding.textLabel.setText(user.getUserName().substring(0,1).toUpperCase());
            holder.binding.avatarAssignee.setVisibility(View.GONE);
        }else {
            Glide.with(holder.itemView.getContext()).load(user.getUserProfile()).apply(RequestOptions.circleCropTransform()).into(holder.binding.avatarImage);
            holder.binding.labelUser.setVisibility(View.GONE);
        }
        holder.binding.userDetailSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserTasksActivity.class);
                intent.putExtra("selectedUser", users.get(holder.getAdapterPosition()));
                context.startActivity(intent);

            }
        });


    }

    public void reset() {
    }

    public void removeItem(int poi) {
        AssignTaskActivity.showingItems.add(users.get(poi).getUserName());
        AssignTaskActivity.items.add(users.remove(poi));

        notifyItemRemoved(poi);
        notifyItemRangeChanged(poi, users.size());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserInviteViewHolder extends RecyclerView.ViewHolder {

        InviteUserItemBinding binding;

        public UserInviteViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = InviteUserItemBinding.bind(itemView);
        }
    }
}

