package com.example.install.gameclub;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RulesContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RulesContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RulesContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;
    private SectionPagerAdapter sectionPagerAdapter;
    private OnFragmentInteractionListener mListener;

    public RulesContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RulesContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RulesContentFragment newInstance(String param1, String param2) {
        RulesContentFragment fragment = new RulesContentFragment();
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
        View view = inflater.inflate(R.layout.fragment_rules_content, container, false);
        sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.rulecontent);
        viewPager.setAdapter(sectionPagerAdapter);
        if(savedInstanceState == null){
            Snackbar.make(view, "Please swipe left to read through the rules", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        return view;
    }
    public class SectionPagerAdapter extends FragmentPagerAdapter {
        public SectionPagerAdapter(FragmentManager fm){
            super(fm);
        }
        public Fragment getItem(int position){
            switch(position){
                case 0:
                    return RulesFragment.newInstance("Rule #1", "DO NOT sacrifice your study for the club , club activities should only be taken during your free time.");
                case 1:
                    return RulesFragment.newInstance("Rule #2", "Play and carries out activities in a way so that everyone have fun .This involves knowing and following the rules, Staying focused on the game, playing at an appropriate volume level, showing good sportsmanship and making consensus decisions on the games that are played.\n");
                case 2:
                    return RulesFragment.newInstance("Rule #3", "Treat the game materials with the utmost respect . This means that treating club's properties like consoles , peripherals, tools ,etc carefully.If you played or handled any club's equipments,make sure to clean up before leaving ");
                case 3:
                    return RulesFragment.newInstance("Rule #4", "Food and drinks are not allowed inside the clubroom due to potential risk of damaging equipments");
                case 4:
                    return RulesFragment.newInstance("Rule #5", "If you break it , you pay for it . Meaning any serious damage caused to equipment will result in the person having to pay back to the club so we can get a replacement");
                default:
                    return RulesFragment.newInstance("Rule #1", "DO NOT sacrifice your study for the club , club activities should only be taken during your free time.");
            }
        }
        public int getCount(){
            return 5;
        }

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
