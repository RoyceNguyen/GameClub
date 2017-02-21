package com.example.install.gameclub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.install.gameclub.MainActivity.fab;
import static com.example.install.gameclub.R.attr.title;
import static com.example.install.gameclub.R.id.location;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //creating variables for the listview and textview
    ListView list;
    TextView ScheduleTextView;
    long begin;
    long end;

    private OnFragmentInteractionListener mListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
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
    FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        fm = getActivity().getSupportFragmentManager();
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.content_main,new CreateSchedule());
                ft.commit();
            }
        });


        //creating the listview and adding contents to the listview
        list = (ListView) view.findViewById(R.id.schedulelist);
        DatabaseHandler db = new DatabaseHandler(getContext());

        final ArrayList<ScheduleContentFragment> schedulelist = db.getAllSchedule();
        db.closeDB();
        //schedulelist.add(new ScheduleContentFragment("Monday", "4-6pm Console Games Day",1487624400000L,1487631600000L));
        //schedulelist.add(new ScheduleContentFragment("Tuesday", "6-9pm PC Games Day",1487718000000L,1487728800000L));
        //schedulelist.add(new ScheduleContentFragment("Wednesday", "No club activities",0,0));
        //schedulelist.add(new ScheduleContentFragment("Thursday", "4-8pm Board Game Day",1487883600000L,1487898000000L));
        //schedulelist.add(new ScheduleContentFragment("Friday", "4-9pm PC Games Day",0,0));
        //schedulelist.add(new ScheduleContentFragment("Weekends", "No club activities",0,0));
        //using a custom adapter for the list view
        final CustomAdapter adapter = new CustomAdapter(getContext(), schedulelist);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScheduleTextView =
                        (TextView) view.findViewById(R.id.description);
                TextView details = (TextView) view.findViewById(R.id.details);
                ImageView chevron = (ImageView) view.findViewById(R.id.chevron);
                if(ScheduleTextView.getText() == ""){
                    ScheduleTextView.setText(
                            //Update the text of the description
                            ((ScheduleContentFragment) list.getItemAtPosition(position)).getDescription());
                    //update the text of the show less
                    details.setText("Click to show less");
                    //update the chevron image
                    chevron.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                }
                else{
                    ScheduleTextView.setText("");
                    //update the text of the show more
                    details.setText("Click to show more");
                    //update the chevron image
                    chevron.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                }
            }
        });
        //creating a listener when item is clicked
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                schedulelist.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return view;
    }
    public class CustomAdapter extends ArrayAdapter<ScheduleContentFragment> {

        public CustomAdapter(Context context, ArrayList<ScheduleContentFragment> items) {
            super(context, 0, items);
        }

        /**
         * getView is used to take every item in a list
         * and assign a view to it.
         * With this specific adapter we specified item_view as the view
         * we want every item in a list to look like.
         * After that item has item_view attached to it
         * we populate the item_view's name TextView
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            ScheduleContentFragment item = getItem(position);

            if (convertView == null) {
                convertView =
                        LayoutInflater.from(getContext()).inflate(
                                R.layout.item_view, parent, false);
            }
            //begin = item.getStartTime();
            //end = item.getEndTime();
            TextView name = (TextView) convertView.findViewById(R.id.name);
            name.setText(item.getName());
            ImageView image = (ImageView) convertView.findViewById(R.id.location);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, "Game Club")
                            .putExtra(CalendarContract.Events.EVENT_LOCATION, "St Clair College")
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                         startActivity(intent);
                    }
                }
            });
            return convertView;

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
