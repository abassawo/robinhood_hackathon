package abassawo.c4q.nyc.fe_nyc;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();


        actionBar.isHideOnContentScrollEnabled();

        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_container, new LoginFragment())
                .addToBackStack(null) //allows user to press back button and return to previous fragment
                .commit();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
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
                                    .replace(R.id.main_container, new ExpenseFragment())
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





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        }

        return super.onOptionsItemSelected(item);
    }
}
