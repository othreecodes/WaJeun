package com.othree.wajeun;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.othree.wajeun.R;

import butterknife.ButterKnife;


public class NotificationFragment extends Fragment {



    public NotificationFragment() {
        // Required empty public constructor
    }



View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, v);

        return v;
    }


}
