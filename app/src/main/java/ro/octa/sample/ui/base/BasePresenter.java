package ro.octa.sample.ui.base;

/**
 * Abstract presenter to work as base for every presenter created in the application. This
 * presenter declares some methods to attach the fragment/activity lifecycle.
 * <p>
 * Created by Octa
 */
public abstract class BasePresenter {

  /**
   * Called when the presenter is initialized, this method represents the start of the presenter
   * lifecycle.
   */
  public abstract void initialize();

  /**
   * Called when the presenter is resumed. After the initialization and when the presenter comes
   * from a pause state.
   */
  public abstract void onResume();

  /**
   * Called when the presenter is paused.
   */
  public abstract void onPause();
}