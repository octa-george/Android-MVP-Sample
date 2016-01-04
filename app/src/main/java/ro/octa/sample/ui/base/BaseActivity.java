package ro.octa.sample.ui.base;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import ro.octa.sample.R;
import ro.octa.sample.dagger.components.IAppComponent;
import ro.octa.sample.ui.SampleApplication;

import static ro.octa.sample.utils.ColorUtils.getColorNew;
import static ro.octa.sample.utils.ColorUtils.getColorOld;
import static ro.octa.sample.utils.UIUtils.disableStatusBarTranslucency;

/**
 * This activity will only execute operations that affect the UI. These operations are defined
 * by a view and are triggered by its presenter.
 *
 * @author Octa
 */
public abstract class BaseActivity extends AppCompatActivity {

  protected Toolbar mActionBarToolbar;

  /**
   * The onCreate base will set the view specified in {@link #getLayout()} and will
   * inject dependencies and views.
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    setupActionBarToolbar();
    injectDependencies();
    try {
      bindViews();
    } catch (RuntimeException e) {
      Log.e("Exception", e.getMessage());
    }
    if (null != getPresenter()) {
      getPresenter().initialize();
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (getPresenter() != null) {
      getPresenter().onResume();
    }
  }

  @Override
  protected void onStop() {
    if (getPresenter() != null) {
      getPresenter().onPause();
    }
    super.onStop();
  }

  protected void quit() {
    BaseActivity.this.finish();
  }

  /**
   * Its common use a toolbar within activity, if it exists in the
   * layout this will be configured.
   */
  protected void setupActionBarToolbar() {
    mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
    if (mActionBarToolbar != null) {
      setSupportActionBar(mActionBarToolbar);
      setupActionBarColors();
      setActionBarTitle(getActionBarTitle());
      // setDisplayHomeAsUpEnabled();
      disableStatusBarTranslucency(getWindow(), getResources());
    }
  }

  private void setupActionBarColors() {
    Resources res = getResources();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      mActionBarToolbar.setTitleTextColor(getColorNew(res, getToolbarTitleColor()));
      mActionBarToolbar.setSubtitleTextColor(getColorNew(res, getToolbarSubtitleColor()));
      mActionBarToolbar.setBackgroundColor(getColorNew(res, getToolbarBgColor()));
    } else {
      mActionBarToolbar.setTitleTextColor(getColorOld(res, getToolbarTitleColor()));
      mActionBarToolbar.setSubtitleTextColor(getColorOld(res, getToolbarSubtitleColor()));
      mActionBarToolbar.setBackgroundColor(getColorOld(res, getToolbarBgColor()));
    }

    setActionbarColor(R.color.color_primary);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private void setActionbarColor(int colorId) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = this.getWindow();
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(this.getResources().getColor(colorId));
    }
  }

  protected int getToolbarBgColor() {
    return R.color.color_primary;
  }

  protected int getToolbarTitleColor() {
    return R.color.text_color_secondary;
  }

  protected int getToolbarSubtitleColor() {
    return R.color.text_color_secondary;
  }

  protected abstract String getActionBarTitle();

  protected void setActionBarTitle(String title) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(title);
    }
  }

  protected void setActionBarTitle(int titleResId) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(titleResId);
    }
  }

  protected void setActionBarSubTitle(String subTitle) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setSubtitle(subTitle);
    }
  }

  protected void setDisplayHomeAsUpEnabled() {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  /**
   * @return The presenter attached to the activity. This must extends from {@link BasePresenter}
   */
  @Nullable protected abstract BasePresenter getPresenter();

  /**
   * Setup the object graph and inject the dependencies needed on this activity.
   */
  private void injectDependencies() {
    setUpComponent(SampleApplication.get(this).getComponent());
  }

  /**
   * Every object annotated with {@link butterknife.Bind} its gonna injected trough butterknife
   */
  private void bindViews() {
    ButterKnife.bind(this);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        quit();
        return true;
    }
    return false;
  }

  /**
   * @return The layout resource that will be inflated as the root view of this activity.
   */
  protected abstract int getLayout();

  public abstract void setUpComponent(IAppComponent appComponent);

  protected IAppComponent getAppComponent() {
    return SampleApplication.get(this).getComponent();
  }

}