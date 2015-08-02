package abassawo.c4q.nyc.fe_nyc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BudgetViewFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View myInflatedView = inflater.inflate(R.layout.fragment_wallet, container, false);

        return myInflatedView;

    }
}
