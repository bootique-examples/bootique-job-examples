package org.objectstyle.bootique.jobs;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.nhl.bootique.Bootique;
import com.nhl.bootique.job.runtime.JobModule;

/**
 * A runnable Bootique + Jobs application.
 */
public class App implements Module {

    public static void main(String[] args) throws Exception {
        Bootique.app(args).module(App.class).autoLoadModules().run();
    }

    @Override
    public void configure(Binder binder) {

        // contribute jobs to the JobModule
        JobModule.contributeJobs(binder).addBinding().to(SimpleJob.class);
    }
}
