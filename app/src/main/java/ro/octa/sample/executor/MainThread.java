package ro.octa.sample.executor;

/**
 * UI Thread abstraction created to change the execution Context from any Thread to the UI Thread.
 * <p>
 * Created by Octa
 */
public interface MainThread {

  void post(final Runnable runnable);
}