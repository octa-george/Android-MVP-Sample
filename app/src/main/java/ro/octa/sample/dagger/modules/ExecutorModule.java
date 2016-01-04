package ro.octa.sample.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ro.octa.sample.executor.Executor;
import ro.octa.sample.executor.MainThread;
import ro.octa.sample.executor.MainThreadImpl;
import ro.octa.sample.executor.ThreadExecutor;

/**
 * Created by Octa
 */
@Module
public final class ExecutorModule {

  @Provides @Singleton Executor provideExecutor() {
    return new ThreadExecutor();
  }

  @Provides @Singleton MainThread provideMainThread() {
    return new MainThreadImpl();
  }
}