package es.carlisgg.android;


import org.junit.Before;

import es.carlisgg.android.ui.home.DefaultHomePresenter;
import es.carlisgg.android.ui.home.HomePresenter;
import es.carlisgg.android.ui.home.HomeView;

import static org.mockito.Mockito.mock;

public class HomePresenterTest extends PresenterTest {
    private HomePresenter presenter;
    private HomeView view;

    @Before
    public void setup() {
        view = mock(HomeView.class);

        presenter = new DefaultHomePresenter();
        presenter.setView(view);
    }
}