package dankmemes.myleswh.dankmemes.mainpage;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dankmemes.myleswh.dankmemes.R;
import dankmemes.myleswh.dankmemes.application.DankApplication;
import dankmemes.myleswh.dankmemes.utils.OnItemClickListener;
import dankmemes.myleswh.dankmemes.utils.ShareUtils;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, OnItemClickListener {

    @BindView(R.id.rvMain) RecyclerView recyclerView;
    @BindView(R.id.pbLoading) View pbLoading;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.floatingActionMenu) FloatingActionsMenu floatingActionsMenu;
    @BindView(R.id.bClearMenu) FloatingActionButton floatingActionButtonClear;
    @BindView(R.id.bToggle) FloatingActionButton floatingActionButtonToggle;
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
        initMenu();
    }

    private void initMenu() {
        floatingActionButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();
                presenter.clearViewed();
                clearThenLoad();
            }
        });
        floatingActionButtonToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toggleShowViewed();
                clearThenLoad();
            }
        });
    }

    private void clearThenLoad() {
        mainAdapter.clearImages();
        presenter.loadImageUrls();
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
                // Load more if at bottom
                if (linearLayoutManager.findLastVisibleItemPosition() == mainAdapter.getItemCount() - 1) {
                    presenter.loadMoreImages();
                }

                // Mark seen images
                int seenIndex = linearLayoutManager.findFirstVisibleItemPosition();
                if (seenIndex >= 0) {
                    presenter.markViewed(mainAdapter.getItem(seenIndex));
                }
            }
        });
    }

    private void inject() {
        ((DankApplication)getApplication())
                .getApplicationComponent()
                .plus(new MainViewModule(this, this))
                .inject(this);
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
    public void notifyShowViewedChange(boolean toggle) {
        Toast.makeText(this, toggle ? "Showing Viewed" : "Hiding Viewed", Toast.LENGTH_SHORT).show();
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
