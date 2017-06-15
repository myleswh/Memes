package dankmemes.myleswh.dankmemes.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GalleryAPI {
    @Headers("Authorization: Client-ID " + RetrofitUtils.PUBLIC_KEY)
    @GET("3/gallery/r/meirl/new/{page}.json") //
    public Single<GalleryModel> loadGallery(@Path("page") int page);
}