package com.example.lenovo.inequalitysign.view;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.lenovo.inequalitysign.R;
import com.example.lenovo.inequalitysign.ui.BHFragment;
import com.example.lenovo.inequalitysign.ui.DiningActivity;
import com.example.lenovo.inequalitysign.ui.DiningInformationActivity;
import com.example.lenovo.inequalitysign.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends Fragment {
    private View view;
    private Button BH;
    private LinearLayout ll;
    private ImageButton Top_1;
    private ImageButton BoT_1;
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.BH:
                    MainActivity mA = (MainActivity)getActivity();
                    mA.setChangeBH();
                    break;
                case R.id.TopF_one:
                    Intent i = new Intent();
                    i.setClass(getActivity().getApplicationContext(),DiningActivity.class);
                    startActivity(i);
                    break;
                case R.id.Bottom_1:
                    Intent ii = new Intent();
                    ii.setClass(getActivity().getApplicationContext(), DiningInformationActivity.class);
                    startActivity(ii);
                    break;

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_square,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        setOnClick();
    }

    private void setOnClick() {
        BH.setOnClickListener(mListener);
        Top_1.setOnClickListener(mListener);
        BoT_1.setOnClickListener(mListener);
    }


    private void findView() {
        BH = (Button)view.findViewById(R.id.BH);
        Top_1 = (ImageButton)view.findViewById(R.id.TopF_one);
        BoT_1 = (ImageButton)view.findViewById(R.id.Bottom_1);

    }
}
