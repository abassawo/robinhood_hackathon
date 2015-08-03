package abassawo.c4q.nyc.fe_nyc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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


public class ResourceActivity extends ActionBarActivity implements ExpandableProgramAdapter.ExpandCollapseListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "fb8edd11a14dc07088e183b288c2503c";
    private ExpandableProgramAdapter mExpandableAdapter;
    private ArrayList<Long> mDurationList;


    //@Bind(R.id.resourceListRV) RecyclerView resourceRV;
    @Bind(R.id.resource_List)
    ListView mResourceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);
        ButterKnife.bind(this);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.isHideOnContentScrollEnabled();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle(getString(R.string.app_name));
        actionBar.setLogo(R.drawable.fe_nyc_logo);
        actionBar.setIcon(R.drawable.fe_nyc_logo);
        actionBar.setDisplayHomeAsUpEnabled(true);

        String foodservicesUrl = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/zipcodes/" + 10003 + "/programs?api_key=" + API_KEY + "&serviceTag=food%20pantry";
        getABServices(foodservicesUrl);

    }


    private ArrayList<Long> generateSpinnerSpeeds() {
        ArrayList<Long> speedList = new ArrayList<>();
        speedList.add(mExpandableAdapter.CUSTOM_ANIMATION_DURATION_NOT_SET);
        for (int i = 1; i <= 10; i++) {
            speedList.add((long) 100.00 * i);
        }
        return speedList;
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

    private void updateDisplay(List<Program> programList) {
        ResourceListAdapter simpleAdapter = new ResourceListAdapter(getApplicationContext(), R.layout.item_resource_list, programList);
        mResourceList.setAdapter(simpleAdapter);

//        mResourceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent goToWebIntent= new Intent(Intent.ACTION_VIEW);
//                Program program2 = new Program();
//                goToWebIntent.setData(Uri.parse(program2.getWebsite()));
//                startActivity(goToWebIntent);
//            }
//        });

    }

    private void getCurrentPrograms(String jsonData) throws JSONException {
        ArrayList<Program> programList = new ArrayList<>();
        List<ParentObject> parentList = (List) programList;
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
//        resourceRV.setHasFixedSize(false);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        resourceRV.setLayoutManager(llm);
//        mDurationList = generateSpinnerSpeeds();

//        mExpandableAdapter = new ExpandableProgramAdapter(ResourceActivity.this, parentList);
//        Log.d("abass test",String.valueOf(mExpandableAdapter.getItemCount()));
//
//        // Attach this activity to the Adapter as the ExpandCollapseListener
//        mExpandableAdapter.addExpandCollapseListener(ResourceActivity.this);
//
//        resourceRV.setAdapter(mExpandableAdapter);
//        // Set the layout manager to a LinearLayout manager for vertical list
//        resourceRV.setLayoutManager(new LinearLayoutManager(ResourceActivity.this));
//
//        resourceRV.setAdapter(mExpandableAdapter);
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

    @Override
    public void onRecyclerViewItemExpanded(int position) {

    }

    @Override
    public void onRecyclerViewItemCollapsed(int position) {

    }
}