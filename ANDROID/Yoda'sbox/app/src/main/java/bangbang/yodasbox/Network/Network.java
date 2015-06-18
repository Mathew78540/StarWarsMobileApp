package bangbang.yodasbox.Network;

        import android.content.Context;
        import android.content.Intent;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;
        import android.support.v4.content.LocalBroadcastManager;
        import android.util.Log;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;

        import com.navercorp.volleyextensions.request.JacksonRequest;

        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.ByteArrayOutputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.URL;

        import bangbang.yodasbox.Model.CharacterResultJSON;
        import bangbang.yodasbox.Model.PlanetResultJSON;
        import bangbang.yodasbox.MyApp;


public class Network {

    public static final String YODA_REQUEST_INTENT = "yodaSearchResult";
    public static final String YODA_SEARCH_PEOPLE_EXTRA = "yodaExtraPeople";
    public static final String YODA_SEARCH_PLANET_EXTRA = "yodaExtraPlanet";


    public static final String YODA_SEARCH_URL_IMAGE_EXTRA = "yodaExtraImage";
    public static final String YODA_SEARCH_IMAGE_EXTRA = "yodaExtraImage";


    public void requestYoda(String categorie, int number)
    {
        String url = "http://swapi.co/api/"+ categorie +"/"+ number +"/?format=json";

        JacksonRequest<CharacterResultJSON> request = new
                JacksonRequest<CharacterResultJSON>(Request.Method.GET, url, CharacterResultJSON.class,
                new Response.Listener<CharacterResultJSON>() {
                    @Override
                    public void onResponse(CharacterResultJSON characterResultsJSON) {
                        //Use or store the object UserSession

                        byte[] b = characterResultsJSON.getName().getBytes();
                        try
                        {
                            characterResultsJSON.setName(new String(b,"UTF-8"));
                        }
                        catch (Exception e)
                        {
                            Log.e("Error", e.toString());
                        }

                        Intent intent = new Intent(YODA_REQUEST_INTENT);
                        intent.putExtra(YODA_SEARCH_PEOPLE_EXTRA, characterResultsJSON);

                        LocalBroadcastManager.getInstance(MyApp.getInstance().getApplicationContext()).sendBroadcast(intent);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("ERROR VOLLEY");
                System.out.println(volleyError);
            }
        });

        request.setTag("getUserSessionTag");
        MyApp.getInstance().getRequestQueue().add(request);

    }



    public void requestYodaPlanet(String url)
    {

        JacksonRequest<PlanetResultJSON> request = new
                JacksonRequest<PlanetResultJSON>(Request.Method.GET, url, PlanetResultJSON.class,
                new Response.Listener<PlanetResultJSON>() {
                    @Override
                    public void onResponse(PlanetResultJSON planetResultsJSON) {
                        //Use or store the object UserSession


                        Intent intent = new Intent(YODA_REQUEST_INTENT);
                        intent.putExtra(YODA_SEARCH_PLANET_EXTRA, planetResultsJSON);

                        LocalBroadcastManager.getInstance(MyApp.getInstance().getApplicationContext()).sendBroadcast(intent);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("ERROR VOLLEY");
                System.out.println(volleyError);
            }
        });

        request.setTag("getUserSessionTag");
        MyApp.getInstance().getRequestQueue().add(request);

    }

    public void requestYodaImage(String name)
    {
        new HttpAsyncTask().execute(name);
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            InputStream inputStream = null;
            String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=medium&as_filetype=jpg&q="+urls[0];
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
                inputStream = httpResponse.getEntity().getContent();

                if(inputStream != null)
                {
                    try
                    {
                        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
                        String line = "";
                        result = "";
                        while((line = bufferedReader.readLine()) != null)
                            result += line;

                        inputStream.close();
                    }catch(IOException e)
                    {
                        Log.e("Error", e.toString());
                    }

                }
                else
                    result = "Did not work!";

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONArray json = new JSONObject(result).getJSONObject("responseData").getJSONArray("results");

                Intent intent = new Intent(YODA_REQUEST_INTENT);
                intent.putExtra(YODA_SEARCH_URL_IMAGE_EXTRA, json.getJSONObject(0).get("unescapedUrl").toString());

                LocalBroadcastManager.getInstance(MyApp.getInstance().getApplicationContext()).sendBroadcast(intent);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void downloadImage(String url, Context ctx)
    {
        new DownloadImageTask(ctx).execute(url);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
    {
        private Context context;

        public DownloadImageTask(Context ctx)
        {
            this.context = ctx;
        }

        protected Bitmap doInBackground(String... urls) {

            try {
                InputStream in = new URL(urls[0]).openStream();
                Bitmap bMap = BitmapFactory.decodeStream(in);
                in.close();
                return bMap;
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Bitmap result) {
            try {
                //Write file
                String filename = "bitmap.png";
                FileOutputStream stream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                result.compress(Bitmap.CompressFormat.PNG, 100, stream);

                //Cleanup
                stream.close();
                result.recycle();


                Intent intent = new Intent(YODA_REQUEST_INTENT);
                intent.putExtra(YODA_SEARCH_IMAGE_EXTRA, filename);

                LocalBroadcastManager.getInstance(MyApp.getInstance().getApplicationContext()).sendBroadcast(intent);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }



        }
    }
}
