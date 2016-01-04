package ro.octa.sample.domain;

import java.util.List;

import ro.octa.sample.commons.model.Album;

/**
 * @author Octa on 1/4/2016.
 */
public interface GetAlbumsInteractor {

  interface Callback {
    void onFetchAlbumsSuccess(List<Album> albums);

    void onFetchAlbumsError();
  }

  void execute(Callback callback);
}
