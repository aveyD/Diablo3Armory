package com.protegra.diablo3armory;

import android.os.AsyncTask;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

public class GetProfileTask extends AsyncTask <String, Integer, JSONObject> {
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int DATA_RETRIEVAL_TIMEOUT = 10000;
    private static final String NOT_FOUND_CODE = "NOTFOUND";

    @Override
    protected JSONObject doInBackground(String [] objects) {
        disableConnectionReuseIfNecessary();
        //enableHttpResponseCache();

        String serviceUrl = objects[0];
        JSONObject jObject = null;

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATA_RETRIEVAL_TIMEOUT);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-length", "0");

            urlConnection.connect();

            // handle issues
//            int statusCode = urlConnection.getResponseCode();
//            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                // handle unauthorized (if service requires user login)
//            } else if (statusCode != HttpURLConnection.HTTP_OK) {
//                // handle any other errors, like 404, 500,..
//            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            jObject = new JSONObject(getResponseText(in));

            if (jObject.get("code").equals(NOT_FOUND_CODE)) {
                // bad battleTag
                jObject = null;
            }
        } catch (MalformedURLException e) {
            // URL is invalid
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            // response body is no valid JSON string
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return jObject;
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

//    private void enableHttpResponseCache() {
//        try {
//            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
//            File httpCacheDir = new File(getCacheDir(), "http");
//            Class.forName("android.net.http.HttpResponseCache")
//                    .getMethod("install", File.class, long.class)
//                    .invoke(null, httpCacheDir, httpCacheSize);
//        } catch (Exception httpResponseCacheNotAvailable) {
//        }
//    }
}
