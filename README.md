# appgate-calculator-api
Api for appgate calculator

# How to run...
Clone project.
Go to root project directory.
Run the following command:
./gradlew assemble docker dockerRun

# How to test...
* Use postman archives en el directorio: /postman
* Use swagger:
  http://localhost:8080/appgate-calculator-api/swagger-ui.html

# How to make high availability and elastic
Deploying on a clpud environment. We can support the implementation with Kubernetes on GCP, or in AWS use the EKS service.
This services makes the solution high availability and elastic.
