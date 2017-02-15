package com.othree.wajeun;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.choota.dev.ctimeago.TimeAgo;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.othree.wajeun.R;
import com.othree.wajeun.adapters.FeedFragmentAdapter;
import com.othree.wajeun.models.Feed;
import com.othree.wajeun.models.User;
import com.r0adkll.slidr.Slidr;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    public FeedFragment() {
        // Required empty public constructor
    }
//    @Bind(R.id.bmb)
//    BoomMenuButton bmb;
    View v;
    @Bind(R.id.recylerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.search_fab)
    FloatingActionButton searchFab;
    @Bind(R.id.settings_fab)
    FloatingActionButton settingsFab;
    @Bind(R.id.post_fab)
    FloatingActionButton postFab;
    @Bind(R.id.multiple_actions_left)
    FloatingActionsMenu menu;
    FirebaseDatabase database;
    DatabaseReference feedRef;
    BottomDialog dialog = null;
    List<Feed> feeds ;
    FeedFragmentAdapter feedFragmentAdapter;

    DatabaseReference usersREF;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, v);

          database = FirebaseDatabase.getInstance();
          feedRef = database.getReference("feeds");
            usersREF = database.getReference("users");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

//        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
//        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
//        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
//        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
//            bmb.addBuilder(new SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_bell));


        settingsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.collapse();

                Intent in = new Intent(getActivity(),SettingsActivity.class);
                startActivity(in);
            }
        });



        feeds = new ArrayList<>();

        Feed feed1 = new Feed();
        feed1.setName("God");
        feed1.setPictureURL("https://scontent.flos3-1.fna.fbcdn.net/v/t1.0-1/p74x74/12974529_1670454649708898_7274533859706032182_n.jpg?_nc_eui2=v1%3AAeH-kzppDCwAMTRqMXSOvnP7BRkUF4HIEJhYn34tYbcsIqunwH_jAuegxtgk7_2tMLNgbiaCDAIq_T7vKPaUI1pVg1FiSLFJ3PMI8Vemjy7sTA&oh=ced147ed39c7c7da051d2455090dadb2&oe=5947C7DA");
        feed1.setTimestamp("6 hrs");
        feed1.setPost("SMITE!");
        feed1.setLink("");
        feed1.setImage("https://external.flos3-1.fna.fbcdn.net/safe_image.php?d=AQBcfiNVW5zO_gxi&w=450&h=236&url=fbstaging%3A%2F%2Fgraph.facebook.com%2Fstaging_resources%2FMDExOTQxMjU5MjU1OTYxNzY4OjE3OTc5Njc0MDc%3D&cfs=1&upscale=1&_nc_eui2=v1%3AAeGHbLespbaYbZizFmsFzKd6mX00ByZi8LO7TJV92sUWYZy20Zobn_eJVVBhJRA7x0zsxo4yf12q4xqqOhJO-LEY-5D-OUQ70HdxsTY8Sdy4SA&_nc_hash=AQAjdkzC30V8kWuq");
        feeds.add(feed1);

        feed1 = new Feed();
        feed1.setName("Temi Babs");
        feed1.setPictureURL("https://scontent.flos3-1.fna.fbcdn.net/v/t1.0-0/s480x480/16649184_753372778154233_1954655857952489191_n.jpg?_nc_eui2=v1%3AAeHoiJX3ky2-BtT2eQpSIR11MqoITEZ_JQSCQMJ_OKuS9siP_41alojuvHsCNktnJOdXT_vjVrVy0s9P1L85ZrSNcddHQIGSGllU1QtTPEK_1Q&oh=d4671e8eca82126d5675cb2c6fc0fdc6&oe=594346BA");
        feed1.setTimestamp("16 hrs");
        feed1.setPost("THE WORLD WILL END...\n" +
                "...when man has explored the whole universe and there's nothing left to explore.");
        feed1.setLink("");
        feed1.setImage("");
        feeds.add(feed1);

        feed1 = new Feed();
        feed1.setName("Ogunsola Oluwatosin Sax Temitayo");
        feed1.setPictureURL("https://scontent.flos3-1.fna.fbcdn.net/v/t1.0-0/s480x480/16649184_753372778154233_1954655857952489191_n.jpg?_nc_eui2=v1%3AAeHoiJX3ky2-BtT2eQpSIR11MqoITEZ_JQSCQMJ_OKuS9siP_41alojuvHsCNktnJOdXT_vjVrVy0s9P1L85ZrSNcddHQIGSGllU1QtTPEK_1Q&oh=d4671e8eca82126d5675cb2c6fc0fdc6&oe=594346BA");
        feed1.setTimestamp("18 hrs");
        feed1.setPost("Today is my birthday; a new day has come\n" +
                "‘tis better for me than it will be for some\n" +
                "To those who are suffering, know I wish you peace\n" +
                "May the light of morning bring needed release");
        feed1.setLink("");
        feed1.setImage("");
        feeds.add(feed1);




        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());


         feedFragmentAdapter = new FeedFragmentAdapter(feeds,getContext());

        recyclerView.setAdapter(feedFragmentAdapter);

        feedFragmentAdapter.notifyDataSetChanged();


        LayoutInflater ing = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View customView = ing.inflate(R.layout.activity_post, null);
        ImageButton postImageButton= (ImageButton) customView.findViewById(R.id.post);
        final TextInputEditText happening = (TextInputEditText) customView.findViewById(R.id.happening);


        postImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!happening.getText().toString().isEmpty()&&!happening.isDirty()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isAnonymous()){
                        Toast.makeText(getContext(),"You need to Login to be able to post",Toast.LENGTH_LONG)
                                .show();
                        return;
                    }

                    Date now = new Date();
                    TimeAgo timeAgo = new TimeAgo();
                    String result = timeAgo.getTimeAgo(now);

                    Feed feed1 = new Feed();
                    feed1.setName(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    feed1.setPictureURL(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString());
                    feed1.setTimestamp(now.toString());
                    feed1.setPost(happening.getText().toString());
                    feed1.setLink("");
                    feed1.setImage("");
                    feed1.setPoster(user.getUid());
                    DatabaseReference f =feedRef.push();
                    feed1.setKey(f.getKey());
                    f.setValue(feed1);
                    happening.getText().clear();
                    dialog.dismiss();

                }else{
                    Toast.makeText(getActivity(),"Action not allowed",Toast.LENGTH_SHORT)
                    .show();
                }
            }
        });


        postFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.collapse();



         dialog = new BottomDialog.Builder(getContext())
                        .setTitle("New Post")
                        .setCustomView(customView)

                        .show();

            }
        });


//        init();


        feedRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Feed post = dataSnapshot.getValue(Feed.class);
                post.setTimestamp(new TimeAgo().getTimeAgo(new Date(post.getTimestamp())));


                usersREF.child(post.getPoster()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()) {
                            User user = dataSnapshot.getValue(User.class);
                            post.setPictureURL(user.getPhotoUrl());
                            feeds.add(0, post);
                            feedFragmentAdapter.notifyDataSetChanged();
                        }
                        else{
                            feeds.add(0, post);
                            feedFragmentAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }

    void init(){


        feedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Feed post = postSnapshot.getValue(Feed.class);
                    post.setTimestamp(new TimeAgo().getTimeAgo(new Date(post.getTimestamp())));
                    feeds.add(0,post);
                    feedFragmentAdapter.notifyDataSetChanged();
                    Log.e("Get Datawe", post.getKey());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
