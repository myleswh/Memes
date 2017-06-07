package dankmemes.myleswh.dankmemes.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GalleryAPI {
    @Headers("Authorization: Client-ID " + RetrofitUtils.PUBLIC_KEY)
    @GET("3/gallery/r/dankmemes/new/0.json") //
    public Single<GalleryModel> loadGallery();
}