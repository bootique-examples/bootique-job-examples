package io.bootique.examples.job;


import io.bootique.BQModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;
import io.bootique.di.Provides;
import io.bootique.job.JobsModule;

import javax.inject.Singleton;

/**
 * A runnable Bootique + Jobs application.
 */
public class App implements BQModule {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(App.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        // register available jobs with the JobModule
        JobsModule.extend(binder)
                .addJob(Job1.class)
                .addJob(Job2.class)
                .addJob(InjectionJob.class)
                .addJob(ParameterizedJob.class);
    }

    // Create a "service" that will be injected into jobs
    @Provides
    @Singleton
    EchoService provideEcho() {
        return new EchoService();
    }
}
