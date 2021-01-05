# Use a secret as an environment variable in OpenShift applications

Frequently applications need to access some sensitive information, such as passwords and user names, that you do not want developers to have.
The best practice for protecting sensitive data is to use Secret objects, which provide this information to the applications without exposing
them in clear text in the application configuration files.

This application illustrates how to implement a secret as an environment variable in Spring Boot OpenShift applications.

## Define key-value pairs in the application.properties that use environment variables
server.servlet.contextPath=/springbootstarter
demo.secretsPath=${SECRETS_PATH:/deployments}

Please note that the default value for demo.secretsPath is "/deployments"

## Create the secret
1. Base64 encode theb secret "/tmp/mydeployments":
echo /tmp/mydeployments | base64

The output from the above command is "L3RtcC9teWRlcGxveW1lbnRzCg=="

2. Create secret.yaml:

apiVersion: v1
kind: Secret
metadata:
  name: mysecret
type: Opaque
data:
  deploymentPath: L3RtcC9teWRlcGxveW1lbnRzCg==
  
3. Create secret
oc create -f secret.yaml

4. oc describe secret mysecret

## Use secret mysecret from springbootstarter-sec application
1. From OpenShift Developer Perspective, click Project - Development
2. Click springbootstarter-sec - Environment
3. Click Add from Config Map or Secret
4. name: SECRETS_PATH
5. Select mysecret from "Select a resource" dropdown
6. Select deploymentPath from "Select a key" dropdown
7. Click Save

## Recreate pod
1. Click Builds - springbootstarter-sec - Builds - springbootstarter-sec-1
2. Click Actions - Rebuild
3. Click Project - Route
4. Click Location link http://springbootstarter-sec-dannyhui-dev.apps.sandbox.x8i5.p1.openshiftapps.com
5. Append /springbootstarter/hello to the address in the browser


