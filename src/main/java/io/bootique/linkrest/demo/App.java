package io.bootique.linkrest.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.Bootique;
import io.bootique.job.runtime.JobModule;
import io.bootique.linkrest.demo.services.EchoService;

/**
 * A runnable Bootique + Jobs application.
 */
public class App implements Module {

    public static void main(String[] args) throws Exception {
        Bootique.app(args).module(App.class).autoLoadModules().run();
    }

    @Override
    public void configure(Binder binder) {

        // contribute available jobs to the JobModule
        JobModule.extend(binder).addJob(SimpleJob.class);
        JobModule.extend(binder).addJob(SimpleJob1.class);
        JobModule.extend(binder).addJob(InjectionJob.class);
        JobModule.extend(binder).addJob(ParameterizedJob.class);
    }

    // Provide a service. This makes it injectable.
    @Provides
    @Singleton
    EchoService provideEcho() {
        return new EchoService();
    }
}
