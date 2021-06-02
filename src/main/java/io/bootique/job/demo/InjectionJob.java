package io.bootique.job.demo;

import io.bootique.job.BaseJob;
import io.bootique.job.JobMetadata;
import io.bootique.job.runnable.JobResult;
import io.bootique.job.demo.services.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Map;

public class InjectionJob extends BaseJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionJob.class);

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
