package org.objectstyle.bootique.jobs;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.nhl.bootique.job.BaseJob;
import com.nhl.bootique.job.JobMetadata;
import com.nhl.bootique.job.runnable.JobResult;
import org.objectstyle.bootique.jobs.services.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class InjectionJob extends BaseJob {

    private static Logger LOGGER = LoggerFactory.getLogger(InjectionJob.class);


    // injecting service indirectly via provider. This ensures that potentially expensive service initialization
    // is deferred and in some situations (like running --list command) is not executed at all.
    @Inject
    private Provider<EchoService> echoServiceProvider;

    public InjectionJob() {
        super(JobMetadata.build(InjectionJob.class));
    }

    @Override
    public JobResult run(Map<String, Object> params) {

        EchoService echo = echoServiceProvider.get();
        LOGGER.info(echo.echo("string to echo..."));

        return JobResult.success(getMetadata());
    }
}
