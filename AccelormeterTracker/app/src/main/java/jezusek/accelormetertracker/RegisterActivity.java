package jezusek.accelormetertracker;

/**
 * Created by kubst_000 on 1/19/2016.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterActivity extends AsyncTask<String, Void, String> {

    private Context context;

    public RegisterActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
    }

        @Override
        protected String doInBackground (String...arg0){
            String accel = arg0[0];
            String output = arg0[1];


            String link;
            String data;
            BufferedReader bufferedReader;
            String result;

            try {
                data = "?accel=" + URLEncoder.encode(accel, "UTF-8");
                data += "&output=" + URLEncoder.encode(output, "UTF-8");

                link = "http://www.jjezusek.byethost7.com/acc_track.php" + data;


                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));

                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                


                result = in.readLine();

                Log.d("error", result);

                return result;
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "Data inserted successfully. Data Registration successful.", Toast.LENGTH_SHORT).show();
                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Data could not be inserted. Data Registration failed.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
    }

