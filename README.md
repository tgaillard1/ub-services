# UB Services

The three interdependent services, each built with Camel and packaged in a SpringBoot application, demonstrate how Fuse applications can be deployed in OpenShift and made to work with one another.

## Prerequisites 

1. OpenShift Container Platform 3.7
2. The [`oc` Openshift client binary](https://github.com/openshift/origin/releases/download/v3.7.1/openshift-origin-client-tools-v3.7.1-ab0f056-linux-64bit.tar.gz), version 3.7+
3. [Apache AMQ 7](https://developers.redhat.com/download-manager/content/origin/files/sha256/ec/ecd8b35798d3ef0bdab210636f5484141e2b802c2552493dd34a3094eb719c95/amq-broker-7.0.3-bin.zip) 

## Deploy and run the services

Git clone this repo or download the repo as .zip and uzip it onto your filesystem. 

### Prepare Openshift Environment

The the following command to start the Openshift Container Platform locally 

```sh
sudo oc cluster up --image registry.access.redhat.com/openshift3/ose --version v3.7.23-3
```

### Prepare and start AMQ 7 broker 

1. Click/Download the AMQ 7 binary from the link above and unzip it into a preferred location. Lets call the path to the unzipped folder as `$ARTEMIS_HOME`
2. Run the following command in a folder where you would like to create broker
   ```sh 
   $ARTEMIS_HOME/bin/artemis create mybroker
   
   # Now reset the ARTEMIS_HOME to full path to the folder mybroker 
   # and run the following command
   $ARTEMIS_HOME/bin/artemis-service start
   ```
3. Assuming no specific IP binding was done, the broker should now accept connections at : **tcp://127.0.0.1:61616**

### Deploy services on Openshift 

From the command line, using the `oc` client tool, run the following command to login into Openshift:

```sh 
# Depending on the platform, sudo may not be needed
sudo oc login -u developer
```

Now for each service/project, change directory into each folder and run the following command:

```sh
mvn fabric8:deploy
```

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the OpenShift [web console](https://docs.openshift.com/container-platform/3.3/getting_started/developers_console.html#developers-console-video) to manage the running pods, and view logs and much more.

