package com.othree.wajeun.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othree.wajeun.R;
import com.othree.wajeun.models.Feed;
import com.wingjay.blurimageviewlib.BlurImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FeedFragmentAdapter extends RecyclerView.Adapter<FeedFragmentAdapter.ViewHolder>{

    List<Feed> feeds;
    Context context;
    public FeedFragmentAdapter(List<Feed> feeds, Context context){
        this.feeds = feeds;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed, parent, false);
        return new FeedFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Feed feed = feeds.get(position);

        Glide.with(this.context).load(feed.pictureURL)
                .placeholder(this.context.getResources().getDrawable(R.drawable.place))
                .crossFade()
                .into(holder.profilePic);




        holder.name.setText(feed.name);
        holder.timestamp.setText(feed.timestamp);

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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

}
