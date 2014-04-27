package com.protegra.diablo3armory;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;


public class MainActivity extends ActionBarActivity
{
    public final static String EXTRA_MESSAGE = "com.protegra.diablo3armory.MESSAGE";
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int DATARETRIEVAL_TIMEOUT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * takes the entered search text and finds the hero
     * @param view
     */
    public void searchHero(View view) throws IOException
    {
        EditText editText = (EditText) findViewById(R.id.hero_text);
        String battleTag = editText.getText().toString();

        // instead of hard coding the host we should use a radio button to choose host
        getHero("http://us.battle.net", battleTag);
    }

    private void getHero(String host, String hero) throws IOException
    {
        disableConnectionReuseIfNecessary();

        String serviceUrl = host + "/api/d3/profile/" + hero + "/";

        HttpURLConnection urlConnection = null;
        try
        {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED)
            {
                // handle unauthorized (if service requires user login)
            }
            else if (statusCode != HttpURLConnection.HTTP_OK)
            {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            JSONObject jObject = new JSONObject(getResponseText(in));

            if (jObject.get("code").equals("NOTFOUND"))
            {
                // bad battleTag
            }
            else
            {
                // good battleTag
            }
        }
        catch (MalformedURLException e)
        {
            // URL is invalid
        }
        catch (SocketTimeoutException e)
        {
            // data retrieval or connection timed out
        }
        catch (IOException e)
        {
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e)
        {
            // response body is no valid JSON string
        }
        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary()
    {
        // see HttpURLConnection API doc
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO)
        {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream)
    {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
