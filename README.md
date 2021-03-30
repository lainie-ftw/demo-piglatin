# Knative Demo on Openshift

## Prerequisites

You will need a OpenShift 4.5 or greater cluster with the knative operator installed to run this workshop. You can access the full documentation [here.](https://docs.openshift.com/container-platform/4.7/serverless/admin_guide/installing-knative-serving.html)

`Note:` If you are doing this workshop on a cluster that was provided to you this has already been done.

### Install operator via CLI (Cluster Admin ONLY)

1. Create a file named serving.yaml and copy the following example YAML into it:

    ```yaml
    apiVersion: operator.knative.dev/v1alpha1
    kind: KnativeServing
    metadata:
        name: knative-serving
        namespace: knative-serving
    ```

2. Apply the serving.yaml:

    ```shell
    oc apply -f ksvcs/serving.yaml
    ```

3. Verify it applied as expected:

    ```shell
    oc get knativeserving.operator.knative.dev/knative-serving -n knative-serving --template='{{range .status.conditions}}{{printf "%s=%s\n" .type .status}}{{end}}'
    ```

    Example Output:

    ```shell
    DependenciesInstalled=True
    DeploymentsAvailable=True
    InstallSucceeded=True
    Ready=True
    ```

## Deploy application via CLI

1. Clone repository locally

   ```shell
   git clone https://github.com/lainie-ftw/demo-piglatin.git
   ```

2. Create a new project to work from (change username to yourname):

   ```shell
   oc new-project username-piglatin
   ```

3. Make sure you are in the username-piglatin project

    ```shell
    oc project username-piglatin
    ```

4. Create a secret for slack:

   ```shell
   oc create secret generic <SLACK_API_KEY>
   ```

5. Create an app with this repository:

    ```shell
    oc new-app ubi8/openjdk-8~https://github.com/lainie-ftw/demo-piglatin#part1
    ```

6. Now make this serverless:

   ```shell
   oc apply -f ksvcs/pl-serverless.yaml
   ```
