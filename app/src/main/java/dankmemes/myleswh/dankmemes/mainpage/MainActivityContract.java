package dankmemes.myleswh.dankmemes.mainpage;

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
        public void loadImageUrls();
        public void loadMoreImages();
        public void dispose();
    }
}
