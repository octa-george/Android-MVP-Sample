package ro.octa.sample.dagger.components;


import dagger.Component;
import ro.octa.sample.api.service.SampleService;
import ro.octa.sample.dagger.ActivityScope;
import ro.octa.sample.dagger.modules.AlbumsModule;
import ro.octa.sample.domain.GetAlbumsInteractor;
import ro.octa.sample.ui.album.AlbumsActivity;
import ro.octa.sample.ui.album.AlbumsPresenter;

/**
 * @author Octa on 10/1/2015.
 */
@ActivityScope
@Component(
        dependencies = IAppComponent.class,
        modules = AlbumsModule.class
)
public interface IAlbumsComponent extends IAppComponent {

  void inject(AlbumsActivity albumsActivity);

  AlbumsPresenter.View getView();

  SampleService getSampleService();

  GetAlbumsInteractor getGetAlbumsInteractor();

  AlbumsPresenter getPresenter();
}
