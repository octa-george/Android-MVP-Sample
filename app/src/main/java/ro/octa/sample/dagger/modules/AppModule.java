package ro.octa.sample.dagger.modules;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import ro.octa.sample.ui.SampleApplication;
import ro.octa.sample.api.ApiConstants;
import ro.octa.sample.api.endpoint.ApiRequestInterceptor;
import ro.octa.sample.commons.adapter.CustomDateTypeAdapter;
import ro.octa.sample.constants.AppStatistics;

/**
 * Created by Octa
 */
@Module
public class AppModule {

  final SampleApplication app;

  private ApiRequestInterceptor requestInterceptor;

  public AppModule(SampleApplication app) {
    this.app = app;
  }

  @Provides @Singleton SampleApplication provideApp() {
    return this.app;
  }

  @Provides @Singleton RestAdapter provideRestAdapter() {
    requestInterceptor = new ApiRequestInterceptor(AppStatistics.getAppVersion());
    return new RestAdapter.Builder()
            .setRequestInterceptor(requestInterceptor)
            .setEndpoint(ApiConstants.getRestUrl())
            .setConverter(new GsonConverter(new GsonBuilder()
                    .registerTypeAdapter(Date.class, new CustomDateTypeAdapter())
                    .serializeNulls()
                    .create()))
            .setClient(new OkClient(new OkHttpClient()))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();
  }

  @Provides @Singleton ApiRequestInterceptor provideApiRequestInterceptor() {
    return requestInterceptor;
  }
}