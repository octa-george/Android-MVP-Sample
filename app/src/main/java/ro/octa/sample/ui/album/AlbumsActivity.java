package ro.octa.sample.ui.album;

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
import ro.octa.sample.commons.model.Album;
import ro.octa.sample.dagger.components.DaggerIAlbumsComponent;
import ro.octa.sample.dagger.components.IAppComponent;
import ro.octa.sample.dagger.modules.AlbumsModule;
import ro.octa.sample.ui.base.BaseActivity;
import ro.octa.sample.ui.base.BasePresenter;
import ro.octa.sample.ui.widget.DividerItemDecoration;

/**
 * @author Octa on 9/21/2015.
 */
public class AlbumsActivity extends BaseActivity implements AlbumsPresenter.View {

  @Inject AlbumsPresenter presenter;

  @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
  private AlbumsAdapter albumsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.line_divider));
    albumsAdapter = new AlbumsAdapter();
    mRecyclerView.setAdapter(albumsAdapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    presenter.getAlbums();
  }

  @Override protected String getActionBarTitle() {
    return getString(R.string.nav_albums);
  }

  @Nullable @Override protected BasePresenter getPresenter() {
    return presenter;
  }

  @Override protected int getLayout() {
    return R.layout.activity_albums;
  }

  @Override public void setUpComponent(IAppComponent appComponent) {
    DaggerIAlbumsComponent.builder()
            .iAppComponent(appComponent)
            .albumsModule(new AlbumsModule(this))
            .build()
            .inject(this);
  }

  @Override
  public void showError(String error) {
    Toast.makeText(AlbumsActivity.this, error, Toast.LENGTH_LONG).show();
  }

  @Override public void renderAlbums(List<Album> albums) {
    albumsAdapter.addItems(albums);
  }

}
