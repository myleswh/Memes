package dankmemes.myleswh.dankmemes.mainpage;

import android.content.Context;

import java.util.List;

/**
 * Created by myleswh on 06/06/2017.
 */
public class MainActivityContract {
    public interface View {
        public void clearImageUrls();
        public void addImages(List<String> urls);
        public void setLoading(boolean loading);
        public void setLoadingMore(boolean loadingMore);
    }

    public interface Presenter {
        public void loadImageUrls(Context context);
        public void loadMoreImages(Context context);
        public void dispose();
        public void markViewed(Context context, String url);
        public void clearViewed(Context context);
        public void setShowViewed(boolean showViewed);
    }
}
