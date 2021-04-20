package com.example.socialmediaappfirebaseversion.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialmediaappfirebaseversion.Chat;
import com.example.socialmediaappfirebaseversion.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private Context context; //to get the current object
    private List<Chat> mChat;//to save the users
    private String imgUrl;
    FirebaseUser user;
    DatabaseReference ref;
    private static final int MSG_TYPE_LEFT = 0, MSG_TYPE_RIGHT = 1;
    //constructor to initialize the context and mUsers
    public MessageAdapter(Context context, List<Chat> mChat, String imgUrl) {
        this.context = context;
        this.mChat = mChat;
        this.imgUrl = imgUrl;
    }


    @NonNull
    @Override
    //to specify what will happen when the view holder is created
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == MSG_TYPE_RIGHT) {
            //to inflate the view without this the repeating view will not be displayed
            view = LayoutInflater.from(context).inflate(R.layout.chat_item__right, parent, false);
            //we return the object of the class with the current new view instance that we just made
            //this will be made for each value of mUsers
        }else{
            //to inflate the view without this the repeating view will not be displayed
            view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
            //we return the object of the class with the current new view instance that we just made
            //this will be made for each value of mUsers

        }
        return new MessageAdapter.ViewHolder(view);
    }



    //now to initialise the values of the things inside the viewholder we use bind view holder

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Chat chat = mChat.get(position);
        holder.showMessage.setText(chat.getMessage());
        if(imgUrl.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_person);
        }
        else{
            Glide.with(context).load(imgUrl).into(holder.profile_image);
        }

        if(position == mChat.size()-1){
            if(chat.isIsseen()){
                holder.seenCheck.setText("Seen");
            }else{
                holder.seenCheck.setText("Delivered");
            }
        }else{
            holder.seenCheck.setVisibility(View.GONE);
        }


    }

    @Override
    //to count the number of the items in the list to know how many times the viewholder is to be called with different values of the repeating blick
    public int getItemCount() {
        return mChat.size();
    }


    //to handle the block of layout we need to repeat for each user
    //we can say this class represents the implementation of each individual block to be repeated
    public class ViewHolder extends RecyclerView.ViewHolder{

        //we had 2 view inside the repeating layout so we initialize them here
        public TextView showMessage, seenCheck;
        public ImageView profile_image;

        //now we make a constructor to receive values from the adapter for each user and to initialize the layout values in the repeating block
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showMessage = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            seenCheck = itemView.findViewById(R.id.seenCheck);

        }
    }

    public int getItemViewType(int postion){
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(postion).getSender().equals(user.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }
    }
}
