package abassawo.c4q.nyc.fe_nyc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ResourceActivity extends ActionBarActivity  {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "fb8edd11a14dc07088e183b288c2503c";
    private ActionBarDrawerToggle mDrawerToggle;
    private String zipParam;

//    @Bind(R.id.resourceListRV) RecyclerView resourceRV;
@Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.resource_List)
    ListView mResourceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);
        ButterKnife.bind(this);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.isHideOnContentScrollEnabled();
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle(getString(R.string.app_name));
        actionBar.setLogo(R.drawable.fe_nyc_logo);
        actionBar.setIcon(R.drawable.fe_nyc_logo);
        actionBar.setDisplayHomeAsUpEnabled(true);

            zipParam = "10003";



        String foodservicesUrl = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/zipcodes/" + zipParam + "/programs?api_key=" + API_KEY + "&serviceTag=food%20pantry";
        getABServices(foodservicesUrl);

    }



    public void getABServices(String service) {
        if (isNetworkAvailable()) {
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
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        getCurrentPrograms(jsonData);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        } else {
            Toast.makeText(this, getString(R.string.network_unavailable_message), Toast.LENGTH_LONG).show();
        }
    }

    private void updateDisplay(ArrayList<Program> programList) {
        ResourceListAdapter simpleAdapter = new ResourceListAdapter(getApplicationContext(), R.layout.item_resource_list, programList);
        mResourceList.setAdapter(simpleAdapter);

        mResourceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Program program2 =  (Program) (mResourceList.getItemAtPosition(position));

                Snackbar snackbar = Snackbar.make(view,program2.getProviderDescription(), Snackbar.LENGTH_LONG)
                        .setAction("->", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent goToWebIntent= new Intent(Intent.ACTION_VIEW);
                                goToWebIntent.setData(Uri.parse(program2.getWebsite()));
                           startActivity(goToWebIntent);
                            }
                        });

                snackbar.show();


            }
        });

    }

    private void getCurrentPrograms(String jsonData) throws JSONException {
        ArrayList<Program> programList = new ArrayList<>();
        JSONObject service = new JSONObject(jsonData);
        JSONArray programsArray = service.getJSONArray("programs");
        Log.v(TAG, String.valueOf(programsArray));
        for (int n = 0; n < programsArray.length(); n++) {
            JSONObject progObject = programsArray.getJSONObject(n);
            Program program = new Program();
            program.setProviderName(progObject.getString("provider_name"));
            program.setProviderDescription(progObject.getString("description"));
            program.setWebsite(progObject.getString("website_url"));
            programList.add(program);
            Log.v(TAG, program.getProviderName());
            Log.v(TAG, program.getProviderDescription());
            Log.v(TAG, program.getWebsite());
        }

        updateDisplay(programList);
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

}