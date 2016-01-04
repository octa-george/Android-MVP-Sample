package ro.octa.sample.domain;

import java.util.List;

import ro.octa.sample.commons.model.Photo;

/**
 * @author Octa on 1/4/2016.
 */
public interface GetAlbumPhotosInteractor {
  interface Callback {
    void onFetchAlbumPhotosSuccess(List<Photo> photos);

    void onFetchAlbumPhotosError();
  }

  void execute(Callback callback, long albumId);
}
