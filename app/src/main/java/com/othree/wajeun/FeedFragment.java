package com.othree.wajeun;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.othree.wajeun.R;
import com.othree.wajeun.adapters.FeedFragmentAdapter;
import com.othree.wajeun.models.Feed;
import com.r0adkll.slidr.Slidr;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, v);


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


        List<Feed> feeds = new ArrayList<>();

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


        FeedFragmentAdapter feedFragmentAdapter = new FeedFragmentAdapter(feeds,getContext());

        recyclerView.setAdapter(feedFragmentAdapter);

        feedFragmentAdapter.notifyDataSetChanged();



        return v;
    }

}
