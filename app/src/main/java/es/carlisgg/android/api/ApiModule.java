package es.carlisgg.android.api;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private String mBaseUrl;

    public ApiModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    protected Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    protected Cache provideCache(Context context) {
        Cache cache = null;
        try {
            cache = new Cache( new File(context.getCacheDir(), "http-cache"),
                    10 * 1024 * 1024 ); // 10 MB
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    @Provides
    @Singleton
    protected OnlineCacheInterceptor provideOnlineCacheInterceptor() {
        return new OnlineCacheInterceptor();
    }

    @Provides
    @Singleton
    protected OfflineCacheInterceptor provideOfflineCacheInterceptor(Context context) {
        return new OfflineCacheInterceptor(context);
    }

    // TODO provide cache if application is injected
    @Provides
    @Singleton
    protected OkHttpClient provideOkhttpClient(OnlineCacheInterceptor cacheInterceptor,
                                               OfflineCacheInterceptor offlineCacheInterceptor,
                                               Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if (cache != null) {
            client.cache(cache);
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.addInterceptor(interceptor);
        client.addInterceptor(offlineCacheInterceptor);
        client.addNetworkInterceptor(cacheInterceptor);

        return client.build();
    }

    @Provides
    @Singleton
    protected GsonConverterFactory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    protected Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

// Provide here different services

//    @Provides
//    @Singleton
//    public ApiService provideRestService(Retrofit retrofit) {
//        return retrofit.create(ApiService.class);
//    }

//    @Provides
//    @Singleton
//    public AuthApiService provideAuthService(Retrofit retrofit) {
//        return retrofit.create(AuthApiService.class);
//    }
}
