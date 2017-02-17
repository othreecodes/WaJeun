package com.othree.wajeun;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mvc.imagepicker.ImagePicker;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    public FeedFragment() {
        // Required empty public constructor
    }

    //    @Bind(R.id.bmb)
//    BoomMenuButton bmb;
    Bitmap imagebitmap = null;
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
    List<Feed> feeds;
    FeedFragmentAdapter feedFragmentAdapter;
    FirebaseStorage storage;
    AppCompatImageView imgView;
    StorageReference storageRef;
    DatabaseReference usersREF;
    StorageReference imagesRef;
    DatabaseReference likeRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, v);

        database = FirebaseDatabase.getInstance();
        feedRef = database.getReference("feeds");
        usersREF = database.getReference("users");
        likeRef = database.getReference("likes");
        storage = FirebaseStorage.getInstance();

        storageRef = storage.getReferenceFromUrl("gs://chow-37ac2.appspot.com");
        imagesRef = storageRef.child("images");

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

                Intent in = new Intent(getActivity(), SettingsActivity.class);
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


        feedFragmentAdapter = new FeedFragmentAdapter(feeds, getContext());

        final AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(feedFragmentAdapter, recyclerView);

        recyclerView.setAdapter(animatorAdapter);

        feedFragmentAdapter.notifyDataSetChanged();


        LayoutInflater ing = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View customView = ing.inflate(R.layout.activity_post, null);
        final ImageButton postImageButton = (ImageButton) customView.findViewById(R.id.post);
        final TextInputEditText happening = (TextInputEditText) customView.findViewById(R.id.happening);
        final SmoothProgressBar bar = (SmoothProgressBar) customView.findViewById(R.id.pgb);
        final ImageButton addphoto = (ImageButton) customView.findViewById(R.id.addPhoto);

        postImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setVisibility(View.VISIBLE);
                postImageButton.setClickable(false);
                addphoto.setClickable(false);
                if (!happening.getText().toString().isEmpty() && !happening.isDirty() ||imagebitmap!=null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isAnonymous()) {
                        Toast.makeText(getContext(), "You need to Login to be able to post", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }

                    happening.onEditorAction(EditorInfo.IME_ACTION_DONE);

                    Date now = new Date();

                    final Feed feed1 = new Feed();
                    feed1.setName(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    feed1.setPictureURL(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString());
                    feed1.setTimestamp(now.toString());
                    feed1.setPost(happening.getText().toString());
                    feed1.setLink("");
                    feed1.setImage("");
                    feed1.setPoster(user.getUid());

                    if(imagebitmap!=null) {
                        final StorageReference image = storageRef.child("images/" + user.getUid() + new Date().toString() + ".jpg");
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        imagebitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();

                        UploadTask uploadTask = image.putBytes(data);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                feed1.setImage(taskSnapshot.getDownloadUrl().toString());
                                DatabaseReference f = feedRef.push();
                                feed1.setKey(f.getKey());
                                f.setValue(feed1);
                                imagebitmap = null;
                                imgView.setVisibility(View.GONE);
                                happening.getText().clear();

                                bar.setVisibility(View.GONE);
                                postImageButton.setClickable(true);
                                addphoto.setClickable(true);
                                Toast.makeText(getContext(),"Post Created",Toast.LENGTH_SHORT)
                                        .show();

                                dialog.dismiss();
                            }
                        });
                    }else{
                        DatabaseReference f = feedRef.push();

                        feed1.setKey(f.getKey());
                        f.setValue(feed1);
                        happening.getText().clear();

                        bar.setVisibility(View.GONE);
                        postImageButton.setClickable(true);
                        addphoto.setClickable(true);
                        Toast.makeText(getContext(),"Post Created",Toast.LENGTH_SHORT)
                                .show();

                        dialog.dismiss();

                    }




                } else {
                    Toast.makeText(getActivity(), "Action not allowed", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });


        postFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.collapse();

                imgView = (AppCompatImageView) customView.findViewById(R.id.postimg);
                ImageButton addphoto = (ImageButton) customView.findViewById(R.id.addPhoto);

                addphoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onPickImage(imgView);
                    }
                });
                dialog = new BottomDialog.Builder(getContext())
                        .setTitle("New Post")
                        .setIcon(R.drawable.ic_create_black_24dp)
                        .setCustomView(customView)

                        .show();

            }
        });


//        init();


        feedRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Feed post = dataSnapshot.getValue(Feed.class);

                post.key = dataSnapshot.getKey();

                String time =  com.github.marlonlom.utilities.timeago.TimeAgo.using(new Date(post.getTimestamp()).getTime());

                post.setTimestamp(time);

                Log.d("WHAT IS S",dataSnapshot.getKey());

                usersREF.child(post.getPoster()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            User user = dataSnapshot.getValue(User.class);
                            post.setPictureURL(user.getPhotoUrl());
                            feeds.add(0, post);
                            feedFragmentAdapter.notifyDataSetChanged();
                            animatorAdapter.notifyDataSetChanged();
                        } else {
                            feeds.add(0, post);
                            feedFragmentAdapter.notifyDataSetChanged();
                            animatorAdapter.notifyDataSetChanged();
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

    public void onPickImage(View view) {
        // Click on image button
        ImagePicker.pickImage(this, "Select your image:");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = ImagePicker.getImageFromResult(getContext(), requestCode, resultCode, data);
        imagebitmap = bitmap;
        imgView.setImageBitmap(bitmap);
        imgView.setVisibility(View.VISIBLE);
    }
}
