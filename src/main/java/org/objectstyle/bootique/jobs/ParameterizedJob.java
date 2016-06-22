package org.objectstyle.bootique.jobs;

import com.nhl.bootique.job.BaseJob;
import com.nhl.bootique.job.JobMetadata;
import com.nhl.bootique.job.runnable.JobResult;

import java.util.Map;


public class ParameterizedJob extends BaseJob {

    private static final String DATE_PARAM = "d";
    private static final String LONG_PARAM = "l";


    public ParameterizedJob() {
        // pass a metadata object to the super constructor that defines supported parameter names and types
        super(JobMetadata.builder(ParameterizedJob.class).dateParam(DATE_PARAM).longParam(LONG_PARAM).build());
    }

    @Override
    public JobResult run(Map<String, Object> params) {


        return JobResult.success(getMetadata());
    }
}
