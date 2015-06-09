package bangbang.yodasbox.Network;

        import android.content.Intent;

        import android.support.v4.content.LocalBroadcastManager;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;

        import com.navercorp.volleyextensions.request.JacksonRequest;

        import bangbang.yodasbox.Model.CharacterResultJSON;
        import bangbang.yodasbox.MyApp;


public class Network {

    public static final String YODA_SEARCH_INTENT = "yodaSearchResult";
    public static final String YODA_SEARCH_RESULT_EXTRA = "yodaSearchResultExtra";

    public static void requestYoda()
    {
        String url = "http://swapi.co/api/people/1/?format=json";

        JacksonRequest<CharacterResultJSON> request = new
                JacksonRequest<CharacterResultJSON>(Request.Method.GET, url, CharacterResultJSON.class,
                new Response.Listener<CharacterResultJSON>() {
                    @Override
                    public void onResponse(CharacterResultJSON characterResultsJSON) {
                        //Use or store the object UserSession

                        System.out.println(characterResultsJSON.toString());
                        Intent intent = new Intent(YODA_SEARCH_INTENT);
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
        System.out.println(request);
        System.out.println(MyApp.getInstance());
        MyApp.getInstance().getRequestQueue().add(request);

    }


}
