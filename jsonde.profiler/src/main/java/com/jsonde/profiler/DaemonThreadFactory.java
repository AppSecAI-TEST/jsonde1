package com.jsonde.profiler;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class DaemonThreadFactory implements ThreadFactory {

    private AtomicInteger threadIdGenerator = new AtomicInteger();
  
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(
                threadGroup,
                r,
                "jSonde-daemon-thread-" + threadIdGenerator.getAndIncrement());

        thread.setDaemon(true);

        return thread;
    }

}
