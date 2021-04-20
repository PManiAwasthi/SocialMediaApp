package com.example.socialmediaappfirebaseversion.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialmediaappfirebaseversion.MessageActivity;
import com.example.socialmediaappfirebaseversion.R;
import com.example.socialmediaappfirebaseversion.Users;

import java.util.List;

//here we make the adapter of type UserAdapter.ViewHolder to as to implement the ViewHolder class we made at the end of this class

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context; //to get the current object
    private List<Users> mUsers; //to save the users
    private boolean isChat;

    //constructor to initialize the context and mUsers
    public UserAdapter(Context context, List<Users> mUsers, boolean isChat) {
        this.context = context;
        this.mUsers = mUsers;
        this.isChat = isChat;
    }


    @NonNull
    @Override
    //to specify what will happen when the view holder is created
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //to inflate the view without this the repeating view will not be displayed
        View view = LayoutInflater.from(context).inflate(R.layout.user_items, parent, false);
        //we return the object of the class with the current new view instance that we just made
        //this will be made for each value of mUsers
        return new UserAdapter.ViewHolder(view);
    }

    //now to initialise the values of the things inside the viewholder we use bind view holder

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //we have a list of users so to get the details of the one we want to display
        Users users = mUsers.get(position);
        holder.username.setText(users.getUsername());
        if(users.getImagelink().equals("default")){
            //in case the image is not uploading we have coded to save the value default in the imagelink
            holder.imageView.setImageResource(R.mipmap.ic_person);

        }else{
            //to display the image from image link
            Glide.with(context).
                    load(users.getImagelink())
                    .into(holder.imageView);
        }


        if(isChat){
            if(users.getStatus().equals("online")){
                holder.imageViewStatusOn.setVisibility(View.VISIBLE);
                holder.imageViewStatusOff.setVisibility(View.GONE);

            }else{
                holder.imageViewStatusOn.setVisibility(View.GONE);
                holder.imageViewStatusOff.setVisibility(View.VISIBLE);

            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MessageActivity.class);
                i.putExtra("userid", users.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    //to count the number of the items in the list to know how many times the viewholder is to be called with different values of the repeating blick
    public int getItemCount() {
        return mUsers.size();
    }


    //to handle the block of layout we need to repeat for each user
    //we can say this class represents the implementation of each individual block to be repeated
    public class ViewHolder extends RecyclerView.ViewHolder{

        //we had 2 view inside the repeating layout so we initialize them here
        public TextView username;
        public ImageView imageView, imageViewStatusOn, imageViewStatusOff;

        //now we make a constructor to receive values from the adapter for each user and to initialize the layout values in the repeating block
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txtUsernameRecyclerView);
            imageView = itemView.findViewById(R.id.imageViewRecyclerView);
            imageViewStatusOn = itemView.findViewById(R.id.imageViewStatusOn);
            imageViewStatusOff = itemView.findViewById(R.id.imageViewStatusOff);
        }
    }


}
