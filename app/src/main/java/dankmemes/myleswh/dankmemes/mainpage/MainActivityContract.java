package dankmemes.myleswh.dankmemes.mainpage;

import java.util.List;

/**
 * Created by myleswh on 06/06/2017.
 */
public class MainActivityContract {
    public interface View {
        public void clearImageUrls();
        public void setImageUrls(List<String> urls);
    }

    public interface Presenter {
        public void loadImageUrls();
    }
}
