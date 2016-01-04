package ro.octa.sample.constants;

import ro.octa.sample.R;
import ro.octa.sample.ui.SampleApplication;

/**
 * @author Octa on 1/4/2016.
 */
public class AppStatistics {

  private static String appVersion = "";
  private static int appCode = 0;

  public static void init(String appV, int appC) {
    appVersion = appV;
    appCode = appC;
  }

  public static String getAppVersion() {
    return appVersion;
  }

  public static int getAppCode() {
    return appCode;
  }

  public static String getActorId() {
    return String.format(SampleApplication.getInstance().getResources().getString(R.string.android_app_v), getAppVersion());
  }

}
