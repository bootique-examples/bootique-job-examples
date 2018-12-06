package io.bootique.job.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.Bootique;
import io.bootique.job.runtime.JobModule;
import io.bootique.job.demo.services.EchoService;

/**
 * A runnable Bootique + Jobs application.
 */
public class App implements Module {

    public static void main(String[] args) {
        Bootique.app(args)
                .module(App.class)
                .autoLoadModules()
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        // contribute available jobs to the JobModule
        JobModule.extend(binder)
                .addJob(SimpleJob.class)
                .addJob(SimpleJob1.class)
                .addJob(InjectionJob.class)
                .addJob(ParameterizedJob.class);
    }

    // Provide a service. This makes it injectable.
    @Provides
    @Singleton
    EchoService provideEcho() {
        return new EchoService();
    }
}
