package bangbang.yodasbox.Network;

        import android.content.Intent;

        import android.support.v4.content.LocalBroadcastManager;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;

        import com.navercorp.volleyextensions.request.JacksonRequest;

        import bangbang.yodasbox.Model.CharacterResultJSON;
        import bangbang.yodasbox.Model.PlanetResultJSON;
        import bangbang.yodasbox.MyApp;


public class Network {

    public static final String YODA_REQUEST_INTENT = "yodaSearchResult";
    public static final String YODA_SEARCH_RESULT_EXTRA = "yodaExtra";

    public CharacterResultJSON person;

    public void requestYoda(String categorie, int number)
    {
        String url = "http://swapi.co/api/"+ categorie +"/"+ number +"/?format=json";

        JacksonRequest<CharacterResultJSON> request = new
                JacksonRequest<CharacterResultJSON>(Request.Method.GET, url, CharacterResultJSON.class,
                new Response.Listener<CharacterResultJSON>() {
                    @Override
                    public void onResponse(CharacterResultJSON characterResultsJSON) {
                        //Use or store the object UserSession


                        Intent intent = new Intent(YODA_REQUEST_INTENT);
                        intent.putExtra(YODA_SEARCH_RESULT_EXTRA, characterResultsJSON);

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
                        intent.putExtra(YODA_SEARCH_RESULT_EXTRA, planetResultsJSON);

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

}
