package es.carlisgg.android.api;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineCacheInterceptor implements Interceptor {

    private final ConnectivityManager cm;

    public OfflineCacheInterceptor(Context context) {
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private boolean isConnected() {
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();
        if (!isConnected() && "GET".equalsIgnoreCase(req.method())) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(20, TimeUnit.DAYS)
                    .build();

            req = req.newBuilder().cacheControl(cacheControl).build();
        }

        return chain.proceed(req);
    }
}
