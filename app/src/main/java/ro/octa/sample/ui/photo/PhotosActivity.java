package ro.octa.sample.ui.photo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import ro.octa.sample.R;
import ro.octa.sample.commons.model.Photo;
import ro.octa.sample.constants.IntentKeys;
import ro.octa.sample.dagger.components.DaggerIPhotosComponent;
import ro.octa.sample.dagger.components.IAppComponent;
import ro.octa.sample.dagger.modules.PhotosModule;
import ro.octa.sample.ui.base.BaseActivity;
import ro.octa.sample.ui.base.BasePresenter;
import ro.octa.sample.ui.widget.DividerItemDecoration;

/**
 * @author Octa on 9/21/2015.
 */
public class PhotosActivity extends BaseActivity implements PhotosPresenter.View {

  @Inject PhotosPresenter presenter;

  @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
  private PhotosAdapter photosAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setDisplayHomeAsUpEnabled();

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.line_divider));
    photosAdapter = new PhotosAdapter();
    mRecyclerView.setAdapter(photosAdapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    presenter.getAlbums();
  }

  @Override protected String getActionBarTitle() {
    return getString(R.string.nav_photos);
  }

  @Nullable @Override protected BasePresenter getPresenter() {
    presenter.setAlbumId(getIntent().getLongExtra(IntentKeys.ALBUM_ID, -1L));
    return presenter;
  }

  @Override protected int getLayout() {
    return R.layout.activity_albums;
  }

  @Override public void setUpComponent(IAppComponent appComponent) {
    DaggerIPhotosComponent.builder()
            .iAppComponent(appComponent)
            .photosModule(new PhotosModule(this))
            .build()
            .inject(this);
  }

  @Override
  public void showError(String error) {
    Toast.makeText(PhotosActivity.this, error, Toast.LENGTH_LONG).show();
  }

  @Override public void renderPhotos(List<Photo> photos) {
    photosAdapter.addItems(photos);
  }

  @Override public void goBack() {
    quit();
  }

  public static Intent makeIntent(Context context, long albumId) {
    Intent intent = new Intent(context, PhotosActivity.class);
    intent.putExtra(IntentKeys.ALBUM_ID, albumId);
    return intent;
  }

}
