package ro.octa.sample.dagger.modules;


import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import ro.octa.sample.api.service.SampleService;
import ro.octa.sample.domain.GetAlbumPhotosInteractor;
import ro.octa.sample.domain.GetAlbumPhotosInteractorImpl;
import ro.octa.sample.executor.Executor;
import ro.octa.sample.executor.MainThread;
import ro.octa.sample.ui.photo.PhotosPresenter;

/**
 * @author Octa on 10/1/2015.
 */
@Module
public class PhotosModule {

  private PhotosPresenter.View view;

  public PhotosModule(PhotosPresenter.View view) {
    this.view = view;
  }

  @Provides public PhotosPresenter.View provideView() {
    return view;
  }

  @Provides public SampleService provideSampleService(RestAdapter restAdapter) {
    return restAdapter.create(SampleService.class);
  }

  @Provides public GetAlbumPhotosInteractor provideGetAlbumPhotosInteractor(Executor executor,
                                                                            MainThread mainThread,
                                                                            SampleService sampleService) {
    return new GetAlbumPhotosInteractorImpl(executor, mainThread, sampleService);
  }

  @Provides
  public PhotosPresenter providePresenter(PhotosPresenter.View view, GetAlbumPhotosInteractor interactor) {
    return new PhotosPresenter(view, interactor);
  }
}
