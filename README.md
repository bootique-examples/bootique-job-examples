[![verify](https://github.com/bootique-examples/bootique-job-examples/actions/workflows/verify.yml/badge.svg)](https://github.com/bootique-examples/bootique-job-examples/actions/workflows/verify.yml)

# Bootique 3.x Job Examples

Shows how to write executable jobs on [Bootique](http://bootique.io) platform. Jobs can either be run individually from command line, 
or scheduled to run periodically via the internal scheduler.

Different Git branches contain example code for different versions of Bootique:

* [3.x](https://github.com/bootique-examples/bootique-job-examples/tree/3.x)
* [2.x](https://github.com/bootique-examples/bootique-job-examples/tree/2.x)
* [1.x](https://github.com/bootique-examples/bootique-job-examples/tree/1.x)

To build and run the project, ensure you have the following installed on your machine:

* Java 11 or newer
* Maven

and then follow these steps:

## Checkout
```
git clone git@github.com:bootique-examples/bootique-job-examples.git
cd bootique-job-examples
```

## Build and package

Run the following command to build the code and package the app:
```
mvn clean package
```

## Run

Now you can check the options available in your app:

```bash
java -jar target/bootique-job-examples-3.0.jar
```

```
NAME
      bootique-job-examples-3.0.jar

OPTIONS
      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      -e, --exec
           Executes one or more jobs. Jobs are specified with '--job' options

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration options.

      -j job_name, --job=job_name
           Specifies the name of a job to execute or schedule. Job name may be optionally followed by a JSON map containing job parameters (e.g.
           'myjob{"p":1}') Used in conjunction with '--execute' or '--schedule' commands. Available job names can be viewed with '--list' command.

      -l, --list
           Lists all jobs available in the app

      --schedule
           Starts a job scheduler that will execute job(s) periodically according to configuration and an optional '--job' arguments.

      --serial
           Enforces sequential execution of the jobs, specified with '--job' options.
```

Run the `-l` (or `--list`) command to display the available jobs:

```bash
java -jar target/bootique-job-examples-3.0.jar --list
```

```
Available jobs:
     - injection()
     - job1()
     - job2()
     - parameterized(d:date, l:long)
```

Run `-e` (or `--exec`) command with one or more `-j` (`--job`) options to execute some jobs once:

```bash
java -jar target/bootique-job-examples-3.0.jar --exec --job=job1 --job=job2
```

Note that the two specified jobs are run in parallel. To execute them sequentially, use the `--serial` option. Next,
let's run a job with parameters specified in the YAML file:

```bash
java -jar target/bootique-job-examples-3.0.jar --exec --job=parameterized --config=params.yml
```
Finally, let's run a few scheduled jobs with `--schedule` command and scheduling triggers configured in `scheduler.yml`:
```bash
java -jar target/bootique-job-examples-3.0.jar --schedule --config=scheduler.yml
```
Scheduled jobs will run indefinitely, until the Java process is killed. 