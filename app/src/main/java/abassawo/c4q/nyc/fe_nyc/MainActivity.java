package abassawo.c4q.nyc.fe_nyc;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;






import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.submit) Button mSubmit;



    private CharSequence mTitle;
    NavigationView navigationView;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), CreateAccount.class);
                MainActivity.this.startActivity(myIntent);
            }
        });



//        mTitle = getTitle();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
//
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();


        actionBar.isHideOnContentScrollEnabled();

        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle(getString(R.string.app_name));
        actionBar.setLogo(R.drawable.fe_nyc_logo);
        actionBar.setDisplayHomeAsUpEnabled(true);


//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.main_container, new LoginFragment())
//                .addToBackStack(null) //allows user to press back button and return to previous fragment
//                .commit();

    }



    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new WalletFragment();
                break;
            case 1:
                fragment = new ResourcesFragment();
                break;
            case 2:
                fragment = new BudgetViewFragment();
                break;

        }
        // update the main content by replacing fragments
        //fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction()
        //        .replace(R.id.main_container, fragment)
        //        .addToBackStack(null) //allows user to press back button and return to previous fragment
        //        .commit();
    }


    private void setupDrawerContent(NavigationView navigationView) {
        fragmentManager = getSupportFragmentManager();
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        if (menuItem.getItemId() == R.id.nav_budget){
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_container, new BudgetViewFragment())//allows user to press back button and return to previous fragment
                                    .commit();
                        } else if(menuItem.getItemId() == R.id.nav_expense){
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_container, new WalletFragment())
                                            //allows user to press back button and return to previous fragment

                                    .commit();
                        } else if(menuItem.getItemId() == R.id.nav_resurces){
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_container, new ResourcesFragment())//allows user to press back button and return to previous fragment
                                    .commit();
                        } else if((menuItem.getItemId() == R.id.nav_settings)){
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    public void onSectionAttached(int number) {
        switch (number) {
            case 4:
                mTitle = ("Expenses");
                break;
            case 2:
                mTitle =  ("Budget");
                break;
            case 3:
                mTitle = ("Settings");
                break;
            case 1:
                mTitle = ("Resources");
                break;
        }
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }





    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}



