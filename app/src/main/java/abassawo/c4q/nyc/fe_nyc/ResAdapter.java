package abassawo.c4q.nyc.fe_nyc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by c4q-Abass on 8/3/15.
 */
public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ProgramViewHolder> {

    private List<Program> progList;

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public ResAdapter(List<Program> progList) {
        this.progList = progList;
    }


    @Override
    public int getItemCount() {
        return progList.size();
    }

    @Override
    public void onBindViewHolder(ProgramViewHolder cardTitleViewHolder, int i) {
        Program ci = progList.get(i);
        cardTitleViewHolder.vName.setText(ci.getProviderName());
    }

    @Override
    public ProgramViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_resource_list, viewGroup, false);

        return new ProgramViewHolder(itemView);
    }

    public static class ProgramViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;


        public ProgramViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.program_title);

        }
    }
}