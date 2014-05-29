package com.somethingnifty.diablo3armory.helpers;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.somethingnifty.diablo3armory.domain.TooltipWebView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TooltipDetailsWebViewPopulator {
    private static final String URL_PREFIX = "http://us.battle.net/d3/en/tooltip/";

    public TooltipDetailsWebViewPopulator(){

    }

    public void populateTooltipWebview(WebView webView, ProgressBar progressBar, String tooltipParams){
        TooltipWebView tooltipWebView = new TooltipWebView(webView, URL_PREFIX + tooltipParams);

        TooltipDetailsDownloader downloader = new TooltipDetailsDownloader(progressBar);
        downloader.execute(tooltipWebView);
    }

    private class TooltipDetailsDownloader extends AsyncTask<TooltipWebView, Void, TooltipWebView> {

        private ProgressBar progressBar;

        public TooltipDetailsDownloader(ProgressBar progressBar){
            this.progressBar = progressBar;
        }

        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected TooltipWebView doInBackground(TooltipWebView... tooltipWebViews) {
            HttpResponse response;
            HttpGet httpGet;
            String s = "";

            TooltipWebView tooltipWebView = tooltipWebViews[0];

            try {

                HttpClient mHttpClient = new DefaultHttpClient();

                httpGet = new HttpGet(tooltipWebView.getTooltipParams());

                response = mHttpClient.execute(httpGet);
                s = EntityUtils.toString(response.getEntity(), "UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }

            tooltipWebView.setTooltipHtml(s);

            return tooltipWebView;
        }

        @Override
        protected void onPostExecute(TooltipWebView tooltipWebView) {
            WebView webView =  tooltipWebView.getWebView();
            String result = tooltipWebView.getTooltipHtml();

            result = result.replace("–", " - ");
            result = result.replace("—", "-");

            if (result.equals("")) {
                Log.d("Tooltip Details", "Body result text is empty.");
            }

            webView.getSettings().setJavaScriptEnabled(true);

            String head = "<html><head><link href=\"http://us.battle.net/d3/static/css/tooltips.css\" rel=\"stylesheet\">" +
                    "</head><body><div style=\"width: 100%;background-color:#000000;" +
                    "border: 2px solid #2727027;\">";

            String foot = "</div></body></html>";

            String customHtml = head + result + foot;

            webView.loadData(customHtml, "text/html", "UTF-8");

            progressBar.setVisibility(View.GONE);
        }
    }
}
