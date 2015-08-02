package abassawo.c4q.nyc.fe_nyc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {

   // @Bind (R.id.toolbar) Toolbar mToolbar;
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "fb8edd11a14dc07088e183b288c2503c";
    DrawerLayout mDrawerList;
    private CharSequence mTitle;
    private Toolbar mToolbar;
    NavigationView navigationView;
    private FragmentManager fragmentManager;

    @Bind(R.id.resource_List) ListView mResourceList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerList = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);

        String foodservicesUrl = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/zipcodes/" + 10003 + "/programs?api_key=" + API_KEY + "&serviceTag=food%20pantry";
        getABServices(foodservicesUrl);

//        mTitle = getTitle();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
//
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle(getString(R.string.app_name));
        actionBar.setLogo(R.drawable.fe_nyc_logo);
        actionBar.setDisplayHomeAsUpEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_container, new LoginFragment())
                .addToBackStack(null) //allows user to press back button and return to previous fragment
                .commit();
    }

    public void getABServices(String service){


        if(isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(service)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                    runOnUiThread(new Runnable() { // A new runnable to run on UI thread
                        @Override
                        public void run() {
                            //TODO: add refresh button method here
                        }
                    });
                    alertUserAboutError();

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        final String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                           // program = getCurrentPrograms(jsonData);
                            new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        getCurrentPrograms(jsonData);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else{
            Toast.makeText(this, getString(R.string.network_unavailable_message), Toast.LENGTH_LONG).show();
        }
    }

    private void updateDisplay(ArrayList<Program> programList) {
        ArrayAdapter simpleAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.item_resource_list,programList);
        mResourceList.setAdapter(simpleAdapter);
    }

    private ArrayList<Program> getCurrentPrograms(String jsonData) throws JSONException{
        ArrayList<Program> programList = new ArrayList<>();
        JSONObject service = new JSONObject(jsonData);
        JSONArray programsArray = service.getJSONArray("programsArray");
        for (int n= 0; n <programsArray.length();n++) {
            JSONObject progObject = programsArray.getJSONObject(n);
            Program program = new Program();
            program.setProviderName(progObject.getString("provider_name"));
            program.setProviderDescription(progObject.getString("description"));
            program.setWebsite(progObject.getString("website_url"));
            programList.add(program);
        }
        updateDisplay(programList);

        return programList;
    }


    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new ExpenseFragment();
                break;
            case 1:
                fragment = new ResourcesFragment();
                break;
            case 2:
                fragment = new BudgetViewFragment();
                break;

        }
        // update the main content by replacing fragments
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null) //allows user to press back button and return to previous fragment
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 4:
                mTitle = (getString(R.string.Expenses));
                break;
            case 2:
                mTitle =  (getString(R.string.Budget));
                break;
            case 3:
                mTitle = (getString(R.string.Settings));
                break;
            case 1:
                mTitle = (getString(R.string.Resources));
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        fragmentManager = getSupportFragmentManager();
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        if (menuItem.getItemId() == R.id.nav_budget) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_container, new BudgetViewFragment())//allows user to press back button and return to previous fragment
                                    .commit();
                        } else if (menuItem.getItemId() == R.id.nav_expense) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_container, new ExpenseFragment())
                                            //allows user to press back button and return to previous fragment
                                    .commit();
                        } else if (menuItem.getItemId() == R.id.nav_resurces) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_container, new ResourcesFragment())//allows user to press back button and return to previous fragment
                                    .commit();
                        } else if ((menuItem.getItemId() == R.id.nav_settings)) {
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                        }
                        mDrawerList.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.drawer_view, menu);
            restoreActionBar();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.nav_expense){
            fragmentManager.beginTransaction()
                    .add(R.id.main_container, new LoginFragment())
                    .addToBackStack(null) //allows user to press back button and return to previous fragment
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.activity_main, container, false);
//            return rootView;
//        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager  = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_dialog");
    }
}

