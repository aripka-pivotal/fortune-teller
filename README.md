# Fortune Teller
---

**Fortune Teller**  is a very basic application composed of two services:

+ [Fortune Service](/fortune-teller-fortune-service) - serves up random Chinese fortune cookie fortunes
+ [Fortune UI](/fortune-teller-ui) - presents a UI that consumes the fortune service

It leverages libraries and services from Spring Cloud and Netflix OSS to compose the system.

This application can be deployed locally leveraging the local instances of the Spring Cloud Services as Spring Boot apps (eureka, hystrix dashboard, config server)

Fortune Teller is also deployable to any Cloud Foundry environment that have installed the [Spring Cloud Services](https://network.pivotal.io/products/p-spring-cloud-services) package.

**Building**

Using the included Maven wrapper in each of the projects (note the root parent pom file is included as a local relative path)

_Mac/Linux_
```
$ ./mvnw package  
```
_Windows_
```
$ ./mvnw.cmd package  
```
The Maven wrapper will automatically download all the required Maven Libraries and all of _Fortune Teller_'s dependencies. This may take a few moments.


== Deploying to Pivotal Cloud Foundry with Spring Cloud Services ==

Create the required services required for application deployment.

Service Type | Service Name | Service Plan | Name
----- | ----- | ----- | -----
Config Server | p-config-server | standard | config (**see create time configuration)
Service Registry | p-service-registry | standard | svcreg
Circuit Breaker Dashboard | p-circuit-breaker-dashboard | standard | cbdash
Database | p-mysql | 100mb | fortunedb 

from command line you can configure the service at create time with -c and the following json

```
'{"count":1,"git":{"uri":"https://github.com/aripka-pivotal/config-repo.git"}}'
```

Wait for created services to reach a last operation of "_create succeeded_" 

**Push the microservices:**

The [Fortune Service](/fortune-teller-fortune-service) and [Fortune UI](/fortune-teller-ui) contain manifests so they may be pushed with the following simple commands.  Update the manifest.yml file in each to reflect desired changes.

```
$ cf push 
```
**Testing the Application**

In a browser, access the fortunes-ui application at the route that was created for you:

image:docs/images/fortunes_1.png[]


From Pivotal Cloud Foundry Apps Manager, access the Hystrix Dashboard by clicking on the *Manage* link for the *cbdash* service.

image:docs/images/fortunes_2.png[]

Access the fortunes-ui and show that the circuit breaker is registering successful requests.

image:docs/images/fortunes_3.png[]

Stop the fortunes application:

```
$ cf stop fortunes
```

Access the fortunes-ui and see that the _fallback fortune_ defined in the configuration git repo is being returned.

image:docs/images/fortunes_4.png[]

Access the fortunes-ui and show that the circuit breaker is registering short-circuited requests.

image:docs/images/fortunes_5.png[]

Start the fortunes application:

```
$ cf start fortunes
```

Continue to access the fortunes-ui and watch the dashboard.

After the fortunes service has re-registered with Eureka and the fortunes-ui load balancer caches are refreshed, you will see the circuit breaker recover.

You should then start getting random fortunes again!
