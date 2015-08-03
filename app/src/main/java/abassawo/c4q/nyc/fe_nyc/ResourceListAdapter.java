package abassawo.c4q.nyc.fe_nyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-tashasmith on 8/2/15.
 */
public class ResourceListAdapter extends ArrayAdapter<Program> {
    public ResourceListAdapter(Context context, int resource,  ArrayList<Program> programs) {
        super(context, resource, programs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Program program = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_resource_list, parent, false);
        }

        TextView providerDisplay = (TextView) convertView.findViewById(R.id.providerDisplay);
        //TextView descriptionDisplay = (TextView) convertView.findViewById(R.id.descriptionDisplay);
        //TextView webDisplay = (TextView) convertView.findViewById(R.id.webDisplay);

        providerDisplay.setText(program.getProviderName());
        //descriptionDisplay.setText(program.getProviderDescription());
//        webDisplay.setText(program.getWebsite());

        return convertView;

    }
}
