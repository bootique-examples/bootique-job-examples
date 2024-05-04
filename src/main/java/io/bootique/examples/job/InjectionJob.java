package io.bootique.examples.job;

import io.bootique.job.Job;
import io.bootique.job.JobOutcome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Map;

public class InjectionJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionJob.class);

    private final Provider<EchoService> echoServiceProvider;

    // injecting service indirectly via provider. This ensures that potentially expensive service initialization
    // is skipped for commands like "--help" and "--list"
    @Inject
    public InjectionJob(Provider<EchoService> echoServiceProvider) {
        this.echoServiceProvider = echoServiceProvider;
    }

    @Override
    public JobOutcome run(Map<String, Object> params) {

        EchoService echo = echoServiceProvider.get();
        LOGGER.info(echo.echo("string to echo..."));

        return JobOutcome.succeeded();
    }
}
