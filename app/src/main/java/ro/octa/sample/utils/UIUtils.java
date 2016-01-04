package ro.octa.sample.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import ro.octa.sample.R;

import static ro.octa.sample.utils.ColorUtils.getColorNew;
import static ro.octa.sample.utils.ColorUtils.getColorOld;

/**
 * @author Octa
 */
public class UIUtils {

  public static void disableStatusBarTranslucency(Window window, Resources resources) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
      // Enable status bar translucency (requires API 19)
      window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
              WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      // Disable status bar translucency (requires API 19)
      window.getAttributes().flags &= (~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.setStatusBarColor(getColorNew(resources, R.color.color_primary_dark));
      } else {
        getColorOld(resources, R.color.color_primary_dark);
      }
    }
  }

  /**
   * Forced shows the user soft keyboard, giving focus to the view that requested this focus.
   *
   * @param input the view that is requesting focus.
   */
  public static void showKeyboard(Activity activity, EditText input) {
    if (activity != null && input != null) {
      InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
      inputManager.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
    }
  }

  /**
   * Forced shows the user soft keyboard, giving focus to the view that requested this focus.
   *
   * @param focusRequester the view that is requesting focus.
   */
  public static void showForcedKeyboard(Activity activity, View focusRequester) {
    if (activity != null && focusRequester != null) {
      focusRequester.clearFocus();
      focusRequester.requestFocus();
      InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
      inputManager.toggleSoftInputFromWindow(focusRequester.getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }
  }

  /**
   * Forced hides the user soft keyboard.
   *
   * @param activity the context containing a focused view.
   */
  public static void hideKeyboard(Activity activity) {
    if (activity != null) {
      InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
      if (activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
      }
    }
  }

  public static void setupParent(final Activity context, View view) {
    //Set up touch listener for non-text box views to hide keyboard.
    if (!(view instanceof EditText)) {
      view.setOnTouchListener(new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
          UIUtils.hideKeyboard(context);
          return false;
        }
      });
    }

    //If a layout container, iterate over children and seed recursion.
    if (view instanceof ViewGroup) {
      for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
        View innerView = ((ViewGroup) view).getChildAt(i);
        setupParent(context, innerView);
      }
    }
  }
}
