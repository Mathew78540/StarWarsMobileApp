package bangbang.yodasbox;


        import android.app.Application;
        import android.content.Context;

        import com.android.volley.RequestQueue;
        import com.android.volley.toolbox.ImageLoader;
        import com.android.volley.toolbox.Volley;

        import bangbang.yodasbox.Network.LruBitmapCache;


public class VolleyApplication extends Application {

    private static Context context;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //init the requestQueue
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        //init the image cache
        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(1024));
        //start the queue
        mRequestQueue.start();
        //get the current context
        context = getApplicationContext();
    }


    public static Context getAppContext() {
        return VolleyApplication.context;
    }
}
