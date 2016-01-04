package ro.octa.sample.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;

/**
 * @author Octa on 1/4/2016.
 */
public class ColorUtils {

  @SuppressLint("NewApi")
  public static int getColorNew(Resources resources, int colorResourceId) {
    return resources.getColor(colorResourceId, null);
  }

  @SuppressWarnings("deprecation")
  public static int getColorOld(Resources resources, int colorResourceId) {
    return resources.getColor(colorResourceId);
  }
}
