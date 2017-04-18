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
+
The Maven wrapper will automatically download all the required Maven Libraries and all of _Fortune Teller_'s dependencies. This may take a few moments.


== Deploying to Pivotal Cloud Foundry with Spring Cloud Services ==

Create the required services required for application deployment.

Service Type | Service Name | Service Plan | Name
----- | ----- | ----- | -----
Config Server | p-config-server | standard | config (**see create time configuration)
Service Registry | p-service-registry | standard | svcreg
Circuit Breaker Dashboard | p-circuit-breaker-dashboard | standard | cbdash
Database | p-mysql | 100mb | fortunedb 

** from command line you can configure the service at create time with -c and the following json

```
'{"count":1,"git":{"uri":"https://github.com/aripka-pivotal/config-repo.git"}}'
```

Wait for created services to reach a last operation of "_create succeeded_" 

. Push the microservices:
+
----
$ cf push -f manifest-pcf.yml
----
+
This will push the fortunes service and the ui application and bind all of the services.

== Deploying to Pivotal Web Services (or other Cloud Foundry environments)

. Push the Spring Cloud services:
+
----
$ cf push -f manifest-services.yml
----
+
This will push a Spring Cloud Config Server, a Eureka server, and a Hystrix Dashboard, all with random routes.

. Edit `scripts/create_services.sh` to add the random routes that were generated for you:
+
----
cf cups config-service -p '{"uri":"http://config-server-fluxional-suttee.cfapps.io"}'
cf cups service-registry -p '{"uri":"http://eureka-unprevalent-toper.cfapps.io"}'
cf cs elephantsql turtle fortunes-db
----

. Run `scripts/create-services.sh` to create the services that you need:
+
----
$ scripts/create_services.sh
Creating user provided service config-service in org platform-eng / space nfjs-workshop as mstine@pivotal.io...
OK
Creating user provided service service-registry in org platform-eng / space nfjs-workshop as mstine@pivotal.io...
OK
Creating service fortunes-db in org platform-eng / space nfjs-workshop as mstine@pivotal.io...
OK
----

. Push the microservices:
+
----
$ cf push -f manifest-apps.yml
----
+
This will push the fortunes service and the ui application.

== Testing the Application

. In a browser, access the fortunes-ui application at the route that was created for you:
+
image:docs/images/fortunes_1.png[]

. Now, in another browser tab, access the Hystrix Dashboard at the route that was created for you.
Enter the route for the UI application and click the ``Monitor Stream.''
+
NOTE: On Pivotal Cloud Foundry, you can access a pre-configured Hystrix Dashboard by clicking on the *Manage* link for *Circuit Breaker Dashboard*. You will *NOT* need to paste in the route.
+
image:docs/images/fortunes_2.png[]

. Access the fortunes-ui and show that the circuit breaker is registering successful requests.
+
image:docs/images/fortunes_3.png[]

. Stop the fortunes application:
+
----
$ cf stop fortunes
----

. Access the fortunes-ui and see that the ``fallback fortune'' is being returned.
+
image:docs/images/fortunes_4.png[]

. Access the fortunes-ui and show that the circuit breaker is registering short-circuited requests.
+
image:docs/images/fortunes_5.png[]

. Start the fortunes application:
+
----
$ cf start fortunes
----

. Continue to access the fortunes-ui and watch the dashboard.
After the fortunes service has re-registered with Eureka and the fortunes-ui load balancer caches are refreshed, you will see the circuit breaker recover.
You should then start getting random fortunes again!
