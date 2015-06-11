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
    public static final String YODA_SEARCH_PEOPLE_EXTRA = "yodaExtraPeople";
    public static final String YODA_SEARCH_PLANET_EXTRA = "yodaExtraPlanet";

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

                        byte[] b = characterResultsJSON.getName().getBytes();
                        try
                        {
                            characterResultsJSON.setName(new String(b,"UTF-8"));
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
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

}
