package ro.octa.sample.domain;

import java.util.List;

import javax.inject.Inject;

import retrofit.RetrofitError;
import retrofit.client.Response;
import ro.octa.sample.api.service.SampleService;
import ro.octa.sample.commons.model.Album;
import ro.octa.sample.executor.Executor;
import ro.octa.sample.executor.Interactor;
import ro.octa.sample.executor.MainThread;

/**
 * @author Octa on 1/4/2016.
 */
public class GetAlbumsInteractorImpl implements Interactor, GetAlbumsInteractor {

  private final Executor mExecutor;
  private final MainThread mMainThread;
  private final SampleService mSampleService;

  private Callback mCallback;

  @Inject
  public GetAlbumsInteractorImpl(Executor executor, MainThread mainThread, SampleService service) {
    this.mExecutor = executor;
    this.mMainThread = mainThread;
    this.mSampleService = service;
  }

  @Override
  public void execute(final Callback callback) {
    if (null == callback) {
      throw new IllegalArgumentException(
              "Callback can't be null, the client of this interactor needs to get the response " +
                      "in the callback");
    }
    this.mCallback = callback;
    this.mExecutor.run(this);
  }

  @Override public void run() {
    mSampleService.getAlbums(new retrofit.Callback<List<Album>>() {
      @Override
      public void success(List<Album> objects, Response response) {
        notifyFetchAlbumsSuccess(objects);
      }

      @Override
      public void failure(RetrofitError error) {
        notifyError();
      }
    });
  }

  private void notifyFetchAlbumsSuccess(final List<Album> list) {
    mMainThread.post(new Runnable() {
      @Override public void run() {
        mCallback.onFetchAlbumsSuccess(list);
      }
    });
  }

  private void notifyError() {
    mMainThread.post(new Runnable() {
      @Override public void run() {
        mCallback.onFetchAlbumsError();
      }
    });
  }
}
