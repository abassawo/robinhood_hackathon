package abassawo.c4q.nyc.fe_nyc;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

/**
 * Created by c4q-Abass on 8/3/15.
 */
//child class
public class ProgramContent extends Program{
    private String mBodyText;

    public ProgramContent(Program x) {
        mBodyText = x.getBody();
    }

    public String getChildText() {
        return mBodyText;
    }

    public void setChildText(String childText) {
        mBodyText = childText;
    }


}
