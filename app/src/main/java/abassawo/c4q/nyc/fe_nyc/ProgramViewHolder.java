package abassawo.c4q.nyc.fe_nyc;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

/**
 * Created by c4q-Abass on 8/3/15.
 */
public class ProgramViewHolder extends ParentViewHolder {

    public TextView numberText;
    public TextView dataText;
    public ImageView arrowExpand;

    /**
     * Public constructor for the CustomViewHolder.
     *
     * @param itemView the view of the parent item. Find/modify views using this.
     */
    public ProgramViewHolder(View itemView) {
        super(itemView);

        numberText = (TextView) itemView.findViewById(R.id.recycler_item_number_parent);
        dataText = (TextView) itemView.findViewById(R.id.providerDisplay);
        arrowExpand = (ImageView) itemView.findViewById(R.id.recycler_item_arrow_parent);
    }
}
