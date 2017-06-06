package dankmemes.myleswh.dankmemes.mainpage;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by myleswh on 06/06/2017.
 */
public class MainPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;

    @Inject
    public MainPresenter(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void loadImageUrls() {
        view.setImageUrls(new ArrayList<String>());
    }
}
