package ro.octa.sample.ui.photo;

import java.util.List;

import javax.inject.Inject;

import ro.octa.sample.commons.model.Photo;
import ro.octa.sample.domain.GetAlbumPhotosInteractor;
import ro.octa.sample.ui.base.BasePresenter;

/**
 * @author Octa on 9/21/2015.
 */
public class PhotosPresenter extends BasePresenter implements GetAlbumPhotosInteractor.Callback {

  private View mView;
  private GetAlbumPhotosInteractor mInteractor;
  private long albumId;

  @Inject
  public PhotosPresenter(View view, GetAlbumPhotosInteractor interactor) {
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

  public void setAlbumId(long albumId) {
    this.albumId = albumId;
  }

  protected void getAlbums() {
    mInteractor.execute(this, albumId);
  }

  @Override public void onFetchAlbumPhotosSuccess(List<Photo> photos) {
    mView.renderPhotos(photos);
  }

  @Override public void onFetchAlbumPhotosError() {
    mView.showError("Error fetching the photos for this album.");
    mView.goBack();
  }

  public interface View {
    void showError(String error);

    void renderPhotos(List<Photo> photos);

    void goBack();
  }
}
