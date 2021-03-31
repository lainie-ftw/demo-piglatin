# Knative Demo on Openshift

## Prerequisites

You will need a OpenShift 4.5 or greater cluster with the knative operator installed to run this workshop. You can access the full documentation [here.](https://docs.openshift.com/container-platform/4.7/serverless/admin_guide/installing-knative-serving.html)

`Note:` If you are doing this workshop on a cluster that was provided to you this has already been done.

## Labs

### Part 1

1. Make a project - user[x]-piglatin (top of console)

    ![makeprojectimage](images/create_project.png)

2. Apply the Slack signing secret to the project
Workloads > Via the secret option on the left then create Key/Value in top right corner:

    ![secretimage](images/secrets.png)

    Fill with the following:

    ```shell
    Name: slack-signing-secret
    Key: SLACK_SIGNING_SECRET
    Value: will provide!
    ```

    Example:
    ![secretimage2](images/secrets2.png)

3. Deploy the application from Developer View:

    Click `+Add:`

    ![add](images/add.png)

    Click `From Catalog:`

    ![fromcatalog](images/from_catalog.png)

    Choose `OpenJDK template:`

    ![openjdk](images/openjdk_templates.png)

    Click `Instantiate Template:`

    ![instantiate](images/instantiate.png)

    Fill out the form and click `create` when fields are filled:

    ```shell
    Application Name: piglatin-app
    Java Version: latest
    Git Repository URL:
    Git Reference: part1
    ```

    Example:
    ![images](images/eventdriven.png)

    The template will build and deploy the application for you, creating and connecting the necessary components.

4. Connect the dots and test it!

### Part 1 but Serverless-ly

1. Apply the following yaml to your project (or use oc apply -f kvscs/pl-serverless.yaml):

    Click `+Add:`

    ![add](images/add.png)

    Click `YAML:`

    ![add](images/yaml.png)

    Paste below into the window:

    ```yaml
    apiVersion: serving.knative.dev/v1
    kind: Service
    metadata:
    name: pl-serverless
    namespace: userx-piglatin
    spec:
    template:
        spec:
        containers:
            - image: image-registry.openshift-image-registry.svc:5000/userx-piglatin/demo-piglatin:latest
            env:
                - name: SLACK_SIGNING_SECRET
                valueFrom:
                    secretKeyRef:
                    name: slack-signing-secret
                    key: SLACK_SIGNING_SECRET
    ```
