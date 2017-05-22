[![Build Status](https://travis-ci.org/bootique-examples/bootique-jobs-demo.svg)](https://travis-ci.org/bootique-examples/bootique-jobs-demo)

# bootique-jobs-demo

An example that explains how to write jobs on [Bootique](http://bootique.io) platform. Jobs can be run individually from command line, or scheduled with internal scheduler.

*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*

## Prerequisites

* Java 1.8 or newer.
* Apache Maven.

## Build the Demo

Here is how to build it:

	git clone git@github.com:bootique-examples/bootique-jobs-demo.git
	cd bootique-jobs-demo
	mvn package

## Run the Demo

Now you can check the options available in your app:

    java -jar target/bootique-jobs-demo-1.0-SNAPSHOT.jar

    OPTIONS
          -c yaml_location, --config=yaml_location
               Specifies YAML config location, which can be a file path or a URL.
    
          -e, --exec
               Executes one or more jobs. Jobs are specified with '--job' options
    
          -h, --help
               Prints this message.
    
          -H, --help-config
               Prints information about application modules and their configuration
               options.
    
          -j job_name, --job=job_name
               Specifies the name of the job to run with '--exec'. Available job
               names can be viewed using '--list' command.
    
          -l, --list
               Lists all jobs available in the app
    
          --schedule
               Schedules and executes jobs according to configuration. Waits
               indefinitely on the foreground.
    
          --serial
               Enforces sequential execution of the jobs, specified with '--job'
               options.

One of the options is ```--list``` that tells you what jobs are available:

    java -jar target/bootique-jobs-demo-1.0-SNAPSHOT.jar --list

    Available jobs:
         - simple
         - job1
         - injection
         - parameterized(d:date, l:long)

From here you have two options - run one or more jobs once from the command line, or start the app as a daemon and let
the jobs run on a defined schedule. First option is great for testing/debugging or when you have an external scheduler
(such as UNIX cron). So let's run both jobs at once:

    java -jar target/bootique-jobs-demo-1.0-SNAPSHOT.jar --exec --job=simple --job=job1

Notice that the two jobs are executed in parallel. Some jobs support parameters, declaring them in metadata. Parameters
can be configured in YAML configuration file under the "jobs" key (or via other Bootique configuration mechanisms, such
as environment variables). Check [```params.yml```](https://github.com/bootique-examples/bootique-jobs-demo/blob/master/params.yml) and ```ParameterizedJob``` for an example. You can run this job as
follows:

    java -jar target/bootique-jobs-demo-1.0-SNAPSHOT.jar --exec --job=parameterized --config=params.yml

Now let's schedule jobs to run at a certain interval. Scheduling information is placed in a YAML file under
the "scheduler" key. Check [```scheduler.yml```](https://github.com/bootique-examples/bootique-jobs-demo/blob/master/scheduler.yml) for an example. It shows scheduling jobs with fixed delay, as well
as using a cron expression. Run it and wait and see how jobs are invoked periodically (use Ctrl-C to stop the application):

    java -jar target/bootique-jobs-demo-1.0-SNAPSHOT.jar --schedule --config=scheduler.yml


## Clustering Jobs with Zookeeper

TODO
