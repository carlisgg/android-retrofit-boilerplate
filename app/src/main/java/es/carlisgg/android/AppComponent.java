package es.carlisgg.android;

import javax.inject.Singleton;

import dagger.Component;
import es.carlisgg.android.api.ApiModule;
import es.carlisgg.android.ui.home.HomeActivity;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, PresentersModule.class})
public interface AppComponent {
    // to update the fields for your presenters
    void inject(HomeActivity main);
}
