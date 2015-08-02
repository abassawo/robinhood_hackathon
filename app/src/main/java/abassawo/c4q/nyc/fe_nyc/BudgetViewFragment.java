package abassawo.c4q.nyc.fe_nyc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class BudgetViewFragment extends Fragment {
    public WebView webView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myInflatedView = inflater.inflate(R.layout.fragment_budget_view, container, false);

        webView = (WebView) myInflatedView.findViewById(R.id.web);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/chart.html");

        return myInflatedView;
    }

    public class WebAppInterface {

        @JavascriptInterface
        public int getNum1() {
            return 1800;
        }

        @JavascriptInterface
        public int getNum2() {
            return 320;
        }

        @JavascriptInterface
        public int getNum3() {
            return 1285;
        }

        @JavascriptInterface
        public int getNum4() {
            return 20;
        }

        @JavascriptInterface
        public int getNum5() {
            return 115;
        }
    }

}
