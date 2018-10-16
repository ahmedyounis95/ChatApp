package chatapp.ayounis.com.chatapp;

public interface BaseView<V> {

    void setUp();

    void setPresenter(V presenter);

    void showAlert(String message, boolean isError);

    void hideAlert();

}
