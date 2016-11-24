package es.carlisgg.android.ui.home;

import android.os.Bundle;

import javax.inject.Inject;

import es.carlisgg.android.DefaultApplication;
import es.carlisgg.android.R;
import es.carlisgg.android.ui.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeView {
    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DefaultApplication.getAppComponent().inject(this);

        presenter.setView(this);
    }
}
