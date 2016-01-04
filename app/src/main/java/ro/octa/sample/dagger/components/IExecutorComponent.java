package ro.octa.sample.dagger.components;


import javax.inject.Singleton;

import dagger.Component;
import ro.octa.sample.dagger.modules.ExecutorModule;
import ro.octa.sample.executor.Executor;
import ro.octa.sample.executor.MainThread;

/**
 * Created by Octa
 */
@Singleton
@Component(
        modules = ExecutorModule.class
)
public interface IExecutorComponent {

  Executor getExecutor();

  MainThread getMainThread();
}