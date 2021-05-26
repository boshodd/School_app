package com.example.school_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ftab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ftab1 extends Fragment {

    TextView t111,t112,t121,t122,t123,t124;
    ImageView i111,i112,i121,i122,i123,i124;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ftab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ftab1.
     */
    // TODO: Rename and change types and number of parameters
    public static ftab1 newInstance(String param1, String param2) {
        ftab1 fragment = new ftab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v=inflater.inflate(R.layout.fragment_ftab1, container, false);

        t111=v.findViewById(R.id.theadboy);
        t112=v.findViewById(R.id.theadgirl);
        t121=v.findViewById(R.id.tredcaptain);
        t122=v.findViewById(R.id.tgreencaptain);
        t123=v.findViewById(R.id.tyellowcaptain);
        t124=v.findViewById(R.id.tbluecaptain);

        i111=v.findViewById(R.id.headboy);
        i112=v.findViewById(R.id.headgirl);
        i121=v.findViewById(R.id.redcaptain);
        i122=v.findViewById(R.id.greencaptain);
        i123=v.findViewById(R.id.yellowcaptain);
        i124=v.findViewById(R.id.bluecaptain);


        Glide.with(getActivity()).load("https://schoolplusplus.000webhostapp.com/php_files/images/hof/111.jpg").into(i111);
        Glide.with(getActivity()).load("https://schoolplusplus.000webhostapp.com/php_files/images/hof/112.jpg").into(i112);
        Glide.with(getActivity()).load("https://schoolplusplus.000webhostapp.com/php_files/images/hof/121.jpg").into(i121);
        Glide.with(getActivity()).load("https://schoolplusplus.000webhostapp.com/php_files/images/hof/122.jpg").into(i122);
        Glide.with(getActivity()).load("https://schoolplusplus.000webhostapp.com/php_files/images/hof/123.jpg").into(i123);
        Glide.with(getActivity()).load("https://schoolplusplus.000webhostapp.com/php_files/images/hof/124.jpg").into(i124);



        return v;
    }
}