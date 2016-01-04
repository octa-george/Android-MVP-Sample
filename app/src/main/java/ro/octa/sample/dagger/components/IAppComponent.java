package ro.octa.sample.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.RestAdapter;
import ro.octa.sample.ui.SampleApplication;
import ro.octa.sample.api.endpoint.ApiRequestInterceptor;
import ro.octa.sample.dagger.modules.AppModule;
import ro.octa.sample.dagger.modules.ExecutorModule;

/**
 * Created by Octa
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                ExecutorModule.class
        }
)
public interface IAppComponent extends IExecutorComponent {

  SampleApplication getApp();

  RestAdapter getRestAdapter();

  ApiRequestInterceptor getApiRequestInterceptor();
}