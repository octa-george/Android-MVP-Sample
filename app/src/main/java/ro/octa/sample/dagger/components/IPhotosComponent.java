package ro.octa.sample.dagger.components;

import dagger.Component;
import ro.octa.sample.api.service.SampleService;
import ro.octa.sample.dagger.ActivityScope;
import ro.octa.sample.dagger.modules.PhotosModule;
import ro.octa.sample.domain.GetAlbumPhotosInteractor;
import ro.octa.sample.ui.photo.PhotosActivity;
import ro.octa.sample.ui.photo.PhotosPresenter;

/**
 * @author Octa on 1/4/2016.
 */
@ActivityScope
@Component(
        dependencies = IAppComponent.class,
        modules = PhotosModule.class
)
public interface IPhotosComponent extends IAppComponent {

  void inject(PhotosActivity photosActivity);

  PhotosPresenter.View getView();

  SampleService getSampleService();

  GetAlbumPhotosInteractor getGetAlbumPhotosInteractor();

  PhotosPresenter getPresenter();
}
