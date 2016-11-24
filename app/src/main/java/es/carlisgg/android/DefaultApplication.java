package es.carlisgg.android;


import android.app.Application;

import es.carlisgg.android.api.ApiModule;

public class DefaultApplication extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = getAppComponentBuilder()
                .appModule(new AppModule(this))
                //.apiModule(new ApiModule("http://10.0.3.2:5000/"))
                .apiModule(new ApiModule("https://rae-fake.herokuapp.com/"))
                .build();
    }

    private static DaggerAppComponent.Builder getAppComponentBuilder() {
        return DaggerAppComponent.builder();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
