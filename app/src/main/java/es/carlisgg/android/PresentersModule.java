package es.carlisgg.android;


import dagger.Module;
import dagger.Provides;
import es.carlisgg.android.ui.home.DefaultHomePresenter;
import es.carlisgg.android.ui.home.HomePresenter;

@Module
public class PresentersModule {

    @Provides
    HomePresenter provideHomePresender() {
        return new DefaultHomePresenter();
    }

//    @Provides
//    LoginPresenter provideLoginPresender(AuthService authService, SharedPreferences prefs) {
//        return new DefaultLoginPresenter(authService, prefs);
//    }
}
