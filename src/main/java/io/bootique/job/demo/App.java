package io.bootique.job.demo;

import io.bootique.BaseModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;
import io.bootique.di.Provides;
import io.bootique.job.runtime.JobModule;
import io.bootique.job.demo.services.EchoService;

import javax.inject.Singleton;

/**
 * A runnable Bootique + Jobs application.
 */
public class App extends BaseModule {

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
