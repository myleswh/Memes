package dankmemes.myleswh.dankmemes.network;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by myleswh on 06/06/2017.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    GalleryAPI galleryAPI();
}
