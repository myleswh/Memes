package dankmemes.myleswh.dankmemes.mainpage;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by myleswh on 06/06/2017.
 */
public class MainPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private Retrofit retrofit;

    @Inject
    public MainPresenter(MainActivityContract.View view, Retrofit retrofit) {
        this.view = view;
        this.retrofit = retrofit;
    }

    @Override
    public void loadImageUrls() {
        
    }
}
