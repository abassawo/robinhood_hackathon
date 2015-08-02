
package abassawo.c4q.nyc.fe_nyc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BudgetViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BudgetViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BudgetViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BudgetViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetViewFragment extends Fragment {
    private String mParam1;
    private String mParam2;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        // TODO: Rename and change types of parameters

        final View myInflatedView = inflater.inflate(R.layout.fragment_wallet, container, false);

        return myInflatedView;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BudgetViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetViewFragment newInstance(String param1, String param2) {
        BudgetViewFragment fragment = new BudgetViewFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }


    public BudgetViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



}


