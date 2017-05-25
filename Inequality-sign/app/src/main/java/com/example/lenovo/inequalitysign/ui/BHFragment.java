package com.example.lenovo.inequalitysign.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.lenovo.inequalitysign.R;
import com.example.lenovo.inequalitysign.view.SquareFragment;

/**
 * Created by Lenovo on 2017/5/23.
 */
public class BHFragment extends Fragment{
    private View view;
    private Button Dn;
    private ImageButton BH_T_1;
    private ImageButton BH_B_1;
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Dn:
                    MainActivity m = (MainActivity)getActivity();
                    m.setChangeDn();
                    break;
                case R.id.BH_TopF_one:
                    Intent i = new Intent();
                    i.setClass(getActivity().getApplicationContext(),DiningActivity.class);
                    startActivity(i);
                    break;
                case R.id.BH_Bottom_1:
                    Intent iii = new Intent();
                    iii.setClass(getActivity().getApplicationContext(), YytActivity.class);
                    startActivity(iii);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_yyt, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        setOnClick();
    }

    private void setOnClick() {
        Dn.setOnClickListener(mListener);
        BH_T_1.setOnClickListener(mListener);
        BH_B_1.setOnClickListener(mListener);
    }


    private void findView() {
        Dn = (Button)view.findViewById(R.id.Dn);
        BH_T_1 = (ImageButton)view.findViewById(R.id.BH_TopF_one);
        BH_B_1 = (ImageButton)view.findViewById(R.id.BH_Bottom_1);

    }

}
