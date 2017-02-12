package com.othree.wajeun;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Bind(R.id.bmb)
    BoomMenuButton bmb;

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, v);


        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(new SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_bell));


        return v;
    }

}
