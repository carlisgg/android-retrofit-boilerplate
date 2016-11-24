package es.carlisgg.android;

public interface Presenter<T extends View> {
    void onDestroy();
    void setView(T view);
}
