package dankmemes.myleswh.dankmemes.mainpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dankmemes.myleswh.dankmemes.R;
import dankmemes.myleswh.dankmemes.application.DankApplication;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.rvMain) RecyclerView recyclerView;
    @BindView(R.id.pbLoading) View pbLoading;
    @Inject MainActivityContract.Presenter presenter;
    @Inject MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inject();
        initView();
        presenter.loadImageUrls();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainAdapter);
    }

    private void inject() {
        DaggerMainViewComponent.builder()
                .mainViewModule(new MainViewModule(this))
                .networkComponent(((DankApplication)getApplication()).getNetworkComponent())
                .build().inject(this);
    }

    @Override
    public void clearImageUrls() {
        mainAdapter.setItems(new ArrayList<String>());
    }

    @Override
    public void setImageUrls(List<String> urls) {
        mainAdapter.setItems(urls);
    }

    @Override
    public void setLoading(boolean loading) {
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setLoadingMore(boolean loadingMore) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }
}
