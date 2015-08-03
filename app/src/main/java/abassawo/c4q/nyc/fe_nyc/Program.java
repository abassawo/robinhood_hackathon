package abassawo.c4q.nyc.fe_nyc;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

/**
 * Created by c4q-tashasmith on 8/2/15.
 */
//parent class
public class Program implements ParentObject {
    private List<Object> mChildObjectList;
    private String providerName;
    private String providerDescription;
    private String website;
    private String mParentText;


    public String getProviderDescription() {
        return providerDescription;
    }

    public void setProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return providerName ;
    }

    public String getParentText() {
        return mParentText;
    }
    public void setParentText(String parentText) {
        mParentText = parentText;
    }

    public String getBody(){
        return " Description: " + providerDescription + '\'' +
                " Website: " + website + '\'';
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildObjectList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildObjectList = list;
    }



}
