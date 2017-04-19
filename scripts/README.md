These scripts can be used on Mac/Linux systems for the following tasks:

**load.sh** - executes repeated calls to the fortune-teller-ui random fortune REST endpoint.  This in turn calls the fortune-teller-fortune-service random endpoint to show load in the hystrix dashboard.  It is called as follows.

```
load.sh \<\<int number of calls\>\>  \<\<optional hostname of fortune-teller-ui\>\> (default host is http://localhost:9090)
```

_Example_
```
$./load.sh 1000 http://fortune-ui.cfapps.io
```
