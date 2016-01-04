package ro.octa.sample.dagger.modules;


import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import ro.octa.sample.api.service.SampleService;
import ro.octa.sample.domain.GetAlbumsInteractor;
import ro.octa.sample.domain.GetAlbumsInteractorImpl;
import ro.octa.sample.executor.Executor;
import ro.octa.sample.executor.MainThread;
import ro.octa.sample.ui.album.AlbumsPresenter;

/**
 * @author Octa on 10/1/2015.
 */
@Module
public class AlbumsModule {

  private AlbumsPresenter.View view;

  public AlbumsModule(AlbumsPresenter.View view) {
    this.view = view;
  }

  @Provides public AlbumsPresenter.View provideView() {
    return view;
  }

  @Provides public SampleService provideSampleService(RestAdapter restAdapter) {
    return restAdapter.create(SampleService.class);
  }

  @Provides public GetAlbumsInteractor provideGetAlbumsInteractor(Executor executor,
                                                                  MainThread mainThread,
                                                                  SampleService sampleService) {
    return new GetAlbumsInteractorImpl(executor, mainThread, sampleService);
  }

  @Provides
  public AlbumsPresenter providePresenter(AlbumsPresenter.View view, GetAlbumsInteractor interactor) {
    return new AlbumsPresenter(view, interactor);
  }
}
