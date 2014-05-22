package com.somethingnifty.diablo3armory.activity.handlers.mainActivityHandlers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.HeroListActivity;
import com.somethingnifty.diablo3armory.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetProfileWebServiceTask extends AsyncTask <String, Integer, JSONObject> {
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int DATA_RETRIEVAL_TIMEOUT = 10000;
    private static final String NOT_FOUND_CODE = "NOTFOUND";

    private ProgressBar progress;

    public GetProfileWebServiceTask(Activity activity) {
        if (activity instanceof MainActivity) {
            this.progress = (ProgressBar) activity.findViewById(R.id.search_hero_progressbar);
        }
        else if (activity instanceof HeroListActivity) {
            this.progress = (ProgressBar) activity.findViewById(R.id.hero_item_progressbar);
        }
    }

    @Override
    protected void onPreExecute(){
        progress.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(JSONObject object){
        super.onPostExecute(object);
        progress.setVisibility(View.GONE);
    }

    @Override
    protected JSONObject doInBackground(String [] objects) {
        disableConnectionReuseForEclairAndLower();

        String serviceUrl = objects[0];
        JSONObject jObject = null;
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = createConnection(serviceUrl);
            urlConnection.connect();

            jObject = getJsonObject(urlConnection);

            if (jObject.get("code").equals(NOT_FOUND_CODE)) {
                jObject = null;
            }
        }
        catch (Exception e) {
            //do nothing
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return jObject;
    }

    private static void disableConnectionReuseForEclairAndLower() {
        // see HttpURLConnection API doc
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private HttpURLConnection createConnection(String serviceUrl) throws IOException {
        URL urlToRequest = new URL(serviceUrl);

        HttpURLConnection urlConnection = (HttpURLConnection) urlToRequest.openConnection();
        urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
        urlConnection.setReadTimeout(DATA_RETRIEVAL_TIMEOUT);
        urlConnection.setDoInput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setInstanceFollowRedirects(true);
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Content-length", "0");

        return urlConnection;
    }

    private JSONObject getJsonObject(HttpURLConnection urlConnection) throws IOException, JSONException {
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        return new JSONObject(getResponseText(in));
    }

    private static String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
