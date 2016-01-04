package ro.octa.sample.domain;

import java.util.List;

import javax.inject.Inject;

import retrofit.RetrofitError;
import retrofit.client.Response;
import ro.octa.sample.api.service.SampleService;
import ro.octa.sample.commons.model.Photo;
import ro.octa.sample.executor.Executor;
import ro.octa.sample.executor.Interactor;
import ro.octa.sample.executor.MainThread;

/**
 * @author Octa on 1/4/2016.
 */
public class GetAlbumPhotosInteractorImpl implements Interactor, GetAlbumPhotosInteractor {

  private final Executor mExecutor;
  private final MainThread mMainThread;
  private final SampleService mSampleService;

  private Callback mCallback;
  private long mAlbumId;

  @Inject
  public GetAlbumPhotosInteractorImpl(Executor executor, MainThread mainThread, SampleService service) {
    this.mExecutor = executor;
    this.mMainThread = mainThread;
    this.mSampleService = service;
  }

  @Override
  public void execute(final Callback callback, final long albumId) {
    if (null == callback) {
      throw new IllegalArgumentException(
              "Callback can't be null, the client of this interactor needs to get the response " +
                      "in the callback");
    }
    this.mCallback = callback;
    this.mAlbumId = albumId;
    this.mExecutor.run(this);
  }

  @Override public void run() {
    mSampleService.getAlbumPhotos(mAlbumId, new retrofit.Callback<List<Photo>>() {
      @Override
      public void success(List<Photo> objects, Response response) {
        notifyFetchAlbumPhotosSuccess(objects);
      }

      @Override
      public void failure(RetrofitError error) {
        notifyError();
      }
    });
  }

  private void notifyFetchAlbumPhotosSuccess(final List<Photo> list) {
    mMainThread.post(new Runnable() {
      @Override public void run() {
        mCallback.onFetchAlbumPhotosSuccess(list);
      }
    });
  }

  private void notifyError() {
    mMainThread.post(new Runnable() {
      @Override public void run() {
        mCallback.onFetchAlbumPhotosError();
      }
    });
  }
}
