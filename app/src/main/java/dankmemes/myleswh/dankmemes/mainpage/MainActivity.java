package dankmemes.myleswh.dankmemes.mainpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dankmemes.myleswh.dankmemes.R;
import dankmemes.myleswh.dankmemes.application.DankApplication;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.rvMain) RecyclerView recyclerView;
    @Inject MainActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inject();
        presenter.loadImageUrls();
    }

    private void inject() {
        DaggerMainViewComponent.builder()
                .mainViewModule(new MainViewModule(this))
                .networkComponent(((DankApplication)getApplication()).getNetworkComponent())
                .build().inject(this);
    }

    @Override
    public void clearImageUrls() {

    }

    @Override
    public void setImageUrls(List<String> urls) {
        System.out.println("setImageUrls");
    }
}
