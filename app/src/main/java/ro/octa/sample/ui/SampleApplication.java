package ro.octa.sample.ui;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import ro.octa.sample.constants.AppStatistics;
import ro.octa.sample.dagger.components.DaggerIAppComponent;
import ro.octa.sample.dagger.components.IAppComponent;
import ro.octa.sample.dagger.modules.AppModule;

/**
 * @author Octa on 1/4/2016.
 */
public class SampleApplication extends Application {

  private IAppComponent mAppComponent;
  private static SampleApplication sInstance;

  @Override
  public void onCreate() {
    super.onCreate();
    sInstance = SampleApplication.this;
    setupGraph();
    initVersionStatics();
  }

  private void setupGraph() {
    mAppComponent = DaggerIAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
  }

  public IAppComponent getComponent() {
    return mAppComponent;
  }

  public static SampleApplication get(Context context) {
    return (SampleApplication) context.getApplicationContext();
  }

  /**
   * @return instance of application controller
   */
  public static synchronized SampleApplication getInstance() {
    return sInstance;
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
  }

  /**
   * Initialize the app and google map version
   */
  private void initVersionStatics() {
    String appVersion = "";
    int appCode = 1;
    try {
      if (getPackageManager() != null) {
        PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        appVersion = pInfo.versionName;
        appCode = pInfo.versionCode;
      }
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
      appVersion = "0";
    }
    AppStatistics.init(appVersion, appCode);
  }
}
