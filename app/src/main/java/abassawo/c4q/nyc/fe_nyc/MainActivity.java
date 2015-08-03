package abassawo.c4q.nyc.fe_nyc;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    private CharSequence mTitle;
    NavigationView navigationView;
    private ActionBarDrawerToggle mDrawerToggle;


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTitle = getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }
        };


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        final ActionBar actionBar = getSupportActionBar();
        actionBar.isHideOnContentScrollEnabled();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle(getString(R.string.app_name));
        actionBar.setLogo(R.drawable.fe_nyc_logo);
        actionBar.setIcon(R.drawable.fe_nyc_logo);
        actionBar.setDisplayHomeAsUpEnabled(true);



         fragmentManager = getSupportFragmentManager();

//
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.main_container, new LanguageFragment())
                .addToBackStack(null) //allows user to press back button and return to previous fragment
                .commit();

    }




    public void submitClick(View v){
            fragmentManager.beginTransaction()
                    .add(R.id.main_container, new BudgetViewFragment())
                    .addToBackStack(null) //allows user to press back button and return to previous fragment
                    .commit();

    }


    private void setupDrawerContent(NavigationView navigationView) {
        fragmentManager = getSupportFragmentManager();
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        if (menuItem.getItemId() == R.id.nav_expense) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_container, new BudgetViewFragment())//allows user to press back button and return to previous fragment
                                    .commit();
                        } else if (menuItem.getItemId() == R.id.nav_wallet) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_container, new WalletFragment())
                                            //allows user to press back button and return to previous fragment
                                    .commit();
                        } else if (menuItem.getItemId() == R.id.nav_resources) {
                            Intent resourceIntent = new Intent(getApplicationContext(), ResourceActivity.class);
                            //resourceIntent.putExtra("zip", zip);
                            startActivity(resourceIntent);
                        } else if ((menuItem.getItemId() == R.id.nav_settings)) {
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                        } else if (menuItem.getItemId() == R.id.nav_connect) {
                            Intent intent = new Intent(MainActivity.this, VenmoWebViewActivity.class);
                            startActivityForResult(intent, 1);
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }



    public void missionIntent(View v){
        Intent mission = new Intent(MainActivity.this, MissionActivity.class);
        startActivity(mission);
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

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.action_log_out) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}



