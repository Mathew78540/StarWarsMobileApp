package bangbang.yodasbox;


import android.app.Application;
import android.graphics.Typeface;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Field;

import bangbang.yodasbox.Network.LruBitmapCache;

public class MyApp extends Application {

    private static MyApp sharedInstance;

    private RequestQueue mVolleyRequestQueue;
    private ImageLoader mVolleyImageLoader;


    @Override
    public void onCreate() {
        super.onCreate();

        // Change default font
        Typeface defaultFace = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/ocr.ttf");
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField("SERIF");
            staticField.setAccessible(true);
            staticField.set(null, defaultFace);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        MyApp.sharedInstance = this;

        // Creation queue Volley
        mVolleyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mVolleyImageLoader = new ImageLoader(mVolleyRequestQueue, new LruBitmapCache(1024 * 1024 * 1));

        //Demarrage de la queue
        mVolleyRequestQueue.start();

    }

    public static MyApp getInstance() {
        return sharedInstance;
    }

    public RequestQueue getRequestQueue() {
        return mVolleyRequestQueue;
    }
}
