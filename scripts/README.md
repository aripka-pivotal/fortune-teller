These scripts can be used on Mac/Linux systems for the following tasks:

**load.sh** - executes repeated calls to the fortune-teller-ui random fortune REST endpoint.  This in turn calls the fortune-teller-fortune-service random endpoint to show load in the hystrix dashboard.  It is called as follows.

```
load.sh \<\<int number of calls\>\>  \<\<optional hostname of fortune-teller-ui\>\> (default host is http://localhost:9090)
```

_Example_
```
$./load.sh 1000 http://fortunes-ui.cfapps.io
```


**refresh.sh** - executes a refresh call to the fortune-teller-ui causing a refresh of the properties from the config server after a change to the remote git repository. _(Note this requires reconfiguring default config repo to one where you can commit changes to the ui.yml file)_  It is called as follows.

```
refresh.sh  \<\<optional hostname of fortune-teller-ui\>\> (default host is http://localhost:9090)
```

_Example_
```
$./refresh.sh http://fortunes-ui.cfapps.io
```


**stop-service.sh** - executes shutdown call to the fortune-teller-fortune-service application. On a Pivotal Cloud Foundry environment the system will detect this shutdown as abnormal and restart the application. It is called as follows.

```
stop-service.sh \<\<optional hostname of fortune-teller-ui\>\> (default host is http://localhost:8080)
```

_Example_
```
$./stop-service.sh 1000 http://fortunes.cfapps.io
```
