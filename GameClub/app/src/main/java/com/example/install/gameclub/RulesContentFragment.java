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
import android.widget.ImageButton;

//import static com.example.install.gameclub.R.id.fab;


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
    //creating variables for the viewpager and section adapter
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
        //creating the viewpager
        sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.rulecontent);
        viewPager.setAdapter(sectionPagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        ImageButton leftButton = (ImageButton) view.findViewById(R.id.left);
        ImageButton rightButton = (ImageButton) view.findViewById(R.id.right);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int location = viewPager.getCurrentItem();
                if(location > 0){
                    location--;
                    viewPager.setCurrentItem(location);
                }
                else if(location == 0){
                    viewPager.setCurrentItem(viewPager.getChildCount()+2);
                }
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int location = viewPager.getCurrentItem();
                location++;
                if(location >= viewPager.getChildCount()+2){
                    viewPager.setCurrentItem(0);
                }
                else{
                    viewPager.setCurrentItem(location);
                }
            }
        });

        return view;
    }
    public class SectionPagerAdapter extends FragmentPagerAdapter {
        public SectionPagerAdapter(FragmentManager fm){
            super(fm);
        }
        public Fragment getItem(int position){
            //adding content to the viewpager
            switch(position){
                case 0:
                    return RulesFragment.newInstance("Rule #1",R.drawable.prohibition, "DO NOT sacrifice your study for the club , club activities should only be taken during your free time.");
                case 1:
                    return RulesFragment.newInstance("Rule #2",R.drawable.handshake, "Play and carries out activities in a way so that everyone have fun .This involves knowing and following the rules, Staying focused on the game, playing at an appropriate volume level, showing good sportsmanship and making consensus decisions on the games that are played.\n");
                case 2:
                    return RulesFragment.newInstance("Rule #3",R.drawable.respect, "Treat the game materials with the utmost respect . This means that treating club's properties like consoles , peripherals, tools ,etc carefully.If you played or handled any club's equipments,make sure to clean up before leaving ");
                case 3:
                    return RulesFragment.newInstance("Rule #4",R.drawable.food, "Food and drinks are not allowed inside the clubroom due to potential risk of damaging equipments");
                case 4:
                    return RulesFragment.newInstance("Rule #5",R.drawable.money, "If you break it , you pay for it . Meaning any serious damage caused to equipment will result in the person having to pay back to the club so we can get a replacement");
                default:
                    return RulesFragment.newInstance("Rule #1",R.drawable.prohibition, "DO NOT sacrifice your study for the club , club activities should only be taken during your free time.");
            }
        }
        public int getCount(){
            return 5;
        }

    }

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
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
