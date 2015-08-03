package abassawo.c4q.nyc.fe_nyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by c4q-Abass on 8/3/15.
 */
public class ExpandableProgramAdapter extends ExpandableRecyclerAdapter<ProgramViewHolder, ProgramBodyHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private LayoutInflater mInflater;
    private ExpandCollapseListener mExpandCollapseListener;

    public ExpandableProgramAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    public void addExpandCollapseListener(ExpandCollapseListener expandCollapseListener) {
        mExpandCollapseListener = expandCollapseListener;
    }




    @Override
    public ProgramViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.activity_resource, viewGroup, false);
        return new ProgramViewHolder(view);
    }

    @Override
    public ProgramBodyHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.item_resource_child, viewGroup, false);
        return new ProgramBodyHolder(view);
    }

    @Override
    public void onBindParentViewHolder(ProgramViewHolder programViewHolder, int i, Object o) {
        Program parentObjProgram = (Program) o;
        programViewHolder.dataText.setText(parentObjProgram.getParentText());

    }

    @Override
    public void onBindChildViewHolder(ProgramBodyHolder programBodyHolder, int i, Object o) {
        ProgramContent childObjBody = (ProgramContent) o;

        programBodyHolder.dataText.setText(childObjBody.getChildText());

    }

    public interface ExpandCollapseListener {

        /**
         * Method called when an item in the ExpandableRecycleView is expanded
         */
        void onRecyclerViewItemExpanded(int position);

        /**
         * Method called when an item in the ExpandableRecyclerView is collapsed
         */
        void onRecyclerViewItemCollapsed(int position);
    }

}