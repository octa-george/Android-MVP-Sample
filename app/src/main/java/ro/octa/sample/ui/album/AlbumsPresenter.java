package ro.octa.sample.ui.album;

import java.util.List;

import javax.inject.Inject;

import ro.octa.sample.commons.model.Album;
import ro.octa.sample.domain.GetAlbumsInteractor;
import ro.octa.sample.ui.base.BasePresenter;

/**
 * @author Octa on 9/21/2015.
 */
public class AlbumsPresenter extends BasePresenter implements GetAlbumsInteractor.Callback {

  private View mView;
  private GetAlbumsInteractor mInteractor;

  @Inject
  public AlbumsPresenter(View view, GetAlbumsInteractor interactor) {
    this.mView = view;
    this.mInteractor = interactor;
  }

  @Override public void initialize() {
    checkViewAlreadySetted();
  }

  @Override public void onResume() {
    // nothing here
  }

  @Override public void onPause() {
    // nothing here
  }

  private void checkViewAlreadySetted() {
    if (null == mView) {
      throw new IllegalArgumentException("Remember to set a view for this presenter");
    }
  }

  protected void getAlbums() {
    mInteractor.execute(this);
  }

  @Override public void onFetchAlbumsSuccess(List<Album> albums) {
    mView.renderAlbums(albums);
  }

  @Override public void onFetchAlbumsError() {
    mView.showError("Error fetching the albums.");
  }

  public interface View {
    void showError(String error);

    void renderAlbums(List<Album> albums);
  }
}
