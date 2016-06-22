package org.objectstyle.bootique.jobs;

import com.nhl.bootique.job.BaseJob;
import com.nhl.bootique.job.JobMetadata;
import com.nhl.bootique.job.runnable.JobResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SimpleJob extends BaseJob {

    private static Logger LOGGER = LoggerFactory.getLogger(SimpleJob.class);

    public SimpleJob() {
        // init with default metadata (no parameters)
        super(JobMetadata.build(SimpleJob.class));
    }

    @Override
    public JobResult run(Map<String, Object> params) {

        LOGGER.info("SimpleJob started");

        // in a real job here you would implement your logic
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("SimpleJob finished");

        return JobResult.success(getMetadata());
    }
}
