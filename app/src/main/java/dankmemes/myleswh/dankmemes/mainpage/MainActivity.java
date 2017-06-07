package dankmemes.myleswh.dankmemes.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dankmemes.myleswh.dankmemes.utils.OnItemClickListener;
import dankmemes.myleswh.dankmemes.R;
import dankmemes.myleswh.dankmemes.application.DankApplication;
import dankmemes.myleswh.dankmemes.utils.ShareUtils;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, OnItemClickListener {

    @BindView(R.id.rvMain) RecyclerView recyclerView;
    @BindView(R.id.pbLoading) View pbLoading;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefreshLayout;
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
        initRecyclerView();
        initSwipeRefresh();
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadImageUrls();
            }
        });
    }

    private void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (linearLayoutManager.findLastVisibleItemPosition() == mainAdapter.getItemCount() - 1) {
                    presenter.loadMoreImages();
                }

            }
        });
    }

    private void inject() {
        DaggerMainViewComponent.builder()
                .mainViewModule(new MainViewModule(this, this))
                .networkComponent(((DankApplication)getApplication()).getNetworkComponent())
                .build().inject(this);
    }

    @Override
    public void clearImageUrls() {
        mainAdapter.clearImages();
    }

    @Override
    public void addImages(List<String> urls) {
        mainAdapter.addImages(urls);
    }

    @Override
    public void setLoading(boolean loading) {
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
        if (!loading) swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setLoadingMore(boolean loadingMore) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void onItemClick(int position) {
        share(position);
    }

    private void share(int position) {
        String url = mainAdapter.getItem(position);
        ShareUtils.shareUrl(this, url);
    }
}
