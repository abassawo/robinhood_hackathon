package abassawo.c4q.nyc.fe_nyc;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import org.w3c.dom.Text;

/**
 * Created by c4q-Abass on 8/3/15.
 */
public class ProgramBodyHolder extends ChildViewHolder {

    public TextView dataText;
    public TextView webText;

    /**
     * Public constructor for the custom child ViewHolder
     *
     * @param itemView the child ViewHolder's view
     */
    public ProgramBodyHolder(View itemView) {
        super(itemView);

        dataText = (TextView) itemView.findViewById(R.id.descriptionDisplay);
        webText = (TextView) itemView.findViewById(R.id.webDisplay);

    }
}