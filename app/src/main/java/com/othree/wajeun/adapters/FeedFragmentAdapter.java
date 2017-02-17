package com.othree.wajeun.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.othree.wajeun.R;
import com.othree.wajeun.models.Feed;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FeedFragmentAdapter extends RecyclerView.Adapter<FeedFragmentAdapter.ViewHolder>{

    List<Feed> feeds;
    Context context;
    FirebaseDatabase database;
    DatabaseReference likeRef;
    public FeedFragmentAdapter(List<Feed> feeds, Context context){
        this.feeds = feeds;
        this.context = context;
        database = FirebaseDatabase.getInstance();
        likeRef = database.getReference("likes");


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed, parent, false);
        return new FeedFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Feed feed = feeds.get(position);

        Glide.with(this.context).load(feed.pictureURL)
                .placeholder(this.context.getResources().getDrawable(R.drawable.place))
                .crossFade()
                .into(holder.profilePic);



        holder.like.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
//                likeRef.child(feed.getKey()).setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
//                likeRef.child(feed.getKey()).
            }
        });



        holder.name.setText(feed.name);
        holder.timestamp.setText(feed.timestamp);

        holder.like.setLiked(feed.isLiked());

            holder.txtStatusMsg.setVisibility(View.VISIBLE);
            holder.txtStatusMsg.setText(feed.post);

        if(!feed.image.isEmpty()) {
            Glide.with(this.context).load(feed.image)
                    .placeholder(this.context.getResources().getDrawable(R.drawable.place))
                    .crossFade()
                    .into(holder.feedImage);

            holder.feedImage.setVisibility(View.VISIBLE);
        }
        else
            holder.feedImage.setVisibility(View.GONE);
        if(!feed.link.isEmpty())
            holder.txtUrl.setText(feed.link);
        else
            holder.txtUrl.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.profilePic)
        ImageView profilePic;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.timestamp)
        TextView timestamp;
        @Bind(R.id.txtStatusMsg)
        TextView txtStatusMsg;
        @Bind(R.id.txtUrl)
        TextView txtUrl;
        @Bind(R.id.feedImage1)
        ImageView feedImage;
        @Bind(R.id.like)
        LikeButton like;
        @Bind(R.id.comment)
        ImageButton comment;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

}
