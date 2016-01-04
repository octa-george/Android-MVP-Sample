package ro.octa.sample.api.endpoint;

import java.util.Date;

import retrofit.RequestInterceptor;

/**
 * @author Octa on 1/4/2016.
 */
public class ApiRequestInterceptor implements RequestInterceptor {

  private String appVersion;
  private String appLanguage = "en";

  public ApiRequestInterceptor(String appVersion) {
    this.appVersion = appVersion;
  }

  @Override
  public void intercept(RequestFacade requestFacade) {
    // set the User-Agent to each request
    requestFacade.addHeader("User-Agent", System.getProperty("http.agent") + " Sample_" + appVersion);

    String timestamp = String.valueOf(new Date().getTime());
    requestFacade.addHeader("timestamp", timestamp);

    requestFacade.addHeader("Accept", "application/json");
    requestFacade.addHeader("Accept-Language", appLanguage);
  }

  public void setAppLanguage(String appLanguage) {
    this.appLanguage = appLanguage;
  }

  public String getAppLanguage() {
    return appLanguage;
  }
}
