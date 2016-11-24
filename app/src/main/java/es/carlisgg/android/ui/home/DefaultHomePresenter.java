package es.carlisgg.android.ui.home;


public class DefaultHomePresenter implements HomePresenter{
    private HomeView view;

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void setView(HomeView view) {
        this.view = view;
    }
}
