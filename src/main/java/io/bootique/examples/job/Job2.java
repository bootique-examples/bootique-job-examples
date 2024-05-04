package io.bootique.examples.job;

import io.bootique.job.Job;
import io.bootique.job.JobOutcome;

import java.util.Map;

public class Job2 implements Job {

    @Override
    public JobOutcome run(Map<String, Object> params) {

        // in a real job here you would implement your logic
        try {
            Thread.sleep(1100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return JobOutcome.succeeded();
    }
}
