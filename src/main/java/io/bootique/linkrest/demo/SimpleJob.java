package io.bootique.linkrest.demo;

import com.nhl.bootique.job.BaseJob;
import com.nhl.bootique.job.JobMetadata;
import com.nhl.bootique.job.runnable.JobResult;

import java.util.Map;

public class SimpleJob extends BaseJob {

    public SimpleJob() {
        // Pass default metadata object to super constructor. Job will be named based on the
        // class name (sans "Job" suffix).
        super(JobMetadata.build(SimpleJob.class));
    }

    @Override
    public JobResult run(Map<String, Object> params) {

        // in a real job here you would implement your logic
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return JobResult.success(getMetadata());
    }
}

