This repository contains java sdk and samples for Merchant API.

#### This Classic SDK is not actively supported and will be deprecated in the future. For full support on new integrations, please use the [JAVA Rest SDK](https://github.com/paypal/rest-api-sdk-java)

Prerequisites:
---------------
*	Java jdk-1.5 or higher
*	Apache Maven 3 or higher
*	Please refer http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html for any help in Maven.

To build sdk and samples:
--------------------------
*	Build core files from https://github.com/paypal/sdk-core-java, as it is a dependency for sdk.
*	Then, run 'mvn install' to build sdk jar and sample war files.

SDK Integration:
----------------
For Non-Maven Users:
--------------------
*   Create a new application.

*   Copy  all the jar files present inside 'lib' folder to your application.

For Maven Users:
----------------
*   Install all the jar files present inside 'lib' folder manually to local repository. 

        mvn install:install-file -Dfile=commons-codec-1.3.jar -DgroupId=commons-codec -DartifactId=commons-codec -Dversion=1.3 -Dpackaging=jar
        mvn install:install-file -Dfile=paypal-core-1.0.jar -DgroupId=com.paypal.sdk -DartifactId=paypal-core -Dversion=1.0 -Dpackaging=jar
		mvn install:install-file -Dfile=merchantsdk-2.11.117.jar -DgroupId=com.paypal.sdk -DartifactId=merchantsdk -Dversion=2.11.117 -Dpackaging=jar	

For Non-Maven Users:
--------------------
*   Create a new application.

*   Copy  all the jar files present inside 'lib' folder to your application.

For Maven Users:
----------------
*   Install all the jar files present inside 'lib' folder manually to local repository. 

        mvn install:install-file -Dfile=commons-codec-1.3.jar -DgroupId=commons-codec -DartifactId=commons-codec -Dversion=1.3 -Dpackaging=jar
        mvn install:install-file -Dfile=paypal-core-1.0.jar -DgroupId=com.paypal.sdk -DartifactId=paypal-core -Dversion=1.0 -Dpackaging=jar
		mvn install:install-file -Dfile=merchantsdk-2.11.117.jar -DgroupId=com.paypal.sdk -DartifactId=merchantsdk -Dversion=2.11.117 -Dpackaging=jar	

For Non-Maven Users:
--------------------
*   Create a new application.

*   Copy  all the jar files present inside 'lib' folder to your application.

For Maven Users:
----------------
*   Install all the jar files present inside 'lib' folder manually to local repository. 

        mvn install:install-file -Dfile=commons-codec-1.3.jar -DgroupId=commons-codec -DartifactId=commons-codec -Dversion=1.3 -Dpackaging=jar
        mvn install:install-file -Dfile=paypal-core-1.0.jar -DgroupId=com.paypal.sdk -DartifactId=paypal-core -Dversion=1.0 -Dpackaging=jar
		mvn install:install-file -Dfile=merchantsdk-2.11.117.jar -DgroupId=com.paypal.sdk -DartifactId=merchantsdk -Dversion=2.11.117 -Dpackaging=jar	

For Non-Maven Users:
--------------------
*   Create a new application.

*   Copy  all the jar files present inside 'lib' folder to your application.

For Maven Users:
----------------
*   Install all the jar files present inside 'lib' folder manually to local repository. 

        mvn install:install-file -Dfile=commons-codec-1.3.jar -DgroupId=commons-codec -DartifactId=commons-codec -Dversion=1.3 -Dpackaging=jar
        mvn install:install-file -Dfile=paypal-core-1.0.jar -DgroupId=com.paypal.sdk -DartifactId=paypal-core -Dversion=1.0 -Dpackaging=jar
		mvn install:install-file -Dfile=merchantsdk-2.11.117.jar -DgroupId=com.paypal.sdk -DartifactId=merchantsdk -Dversion=2.11.117 -Dpackaging=jar	

*	Create a new maven application.

*	Add dependency to sdk in your application's pom.xml as below.
		
    ```xml
    <dependency>
        <groupId>com.paypal.sdk</groupId>
        <artifactId>merchantsdk</artifactId>
        <version>2.11.117</version>
    </dependency>
    ```
		
To make an API call:
--------------------			
*	Import PayPalAPIInterfaceServiceService.java into your code.
		
*	Create a configuration file 'sdk_config.properties' with parameters specified in configuration section (make sure the file is in class path). Use the default constructor to run with configuration used from 'sdk_config.properties' found in classpath.
	```java
	new PayPalAPIInterfaceServiceService();
	```
*	For Dynamic configuration(configuration is tied to the lifetime of the service object)
	```java
	new PayPalAPIInterfaceServiceService(new File("/pathto/custom.properties"));
			Or
	new PayPalAPIInterfaceServiceService(new FileInputStream(new File("/pathto/custom.properties")));
			Or
	new PayPalAPIInterfaceServiceService("/pathto/custom.properties");
			Or
	new PayPalAPIInterfaceServiceService(Map<String, String> customConfigurationMap);
			Or
	new PayPalAPIInterfaceServiceService(Properties customProperties);
	```
*	The SDK takes defaults for certain parameters (eg: Account Credentials and either of 'mode' or 'service.Endpoint' are mandatory parameters).

*	Create a service wrapper object.

*	Create a request object as per your project needs. 

*	Invoke the appropriate method on the service wrapper object.

    For example,

          
    ```java
    import urn.ebay.api.PayPalAPI.*;
    ...
    TransactionSearchReq txnreq = new TransactionSearchReq();
    TransactionSearchRequestType requestType = new TransactionSearchRequestType();

    requestType.setStartDate("2011-10-04T00:00:00.000Z"); 
    requestType.setEndDate("2011-10-05T23:59:59.000Z"); 
    requestType.setVersion("95.0");
    requestType.setTransactionID(request.getParameter("transactionID"));
    txnreq.setTransactionSearchRequest(requestType);

    PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService();
    			Or
    Map<String, String> customConfigurationMap = new HashMap<String, String>();
    customConfigurationMap.put("mode", "sandbox"); // Load the map with all mandatory parameters
    ...
    PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(Map<String, String> customConfigurationMap);
    TransactionSearchResponseType txnresponse = service.transactionSearch(txnreq, username);
    ```
		  

SDK Logging:
------------
*	For logging - java.util.logging has been used. To change the default configuration, edit the logging.properties file in 'jre/lib' folder under your JDK root.		  

		  
SDK Configuration:
------------------
The SDK uses dynamic configuration map or '*.properties' format configuration file as shown in code snippet above, to configure

*	Mode is specified using the parameter name 'mode' with values 'sandbox' or 'live', if specified 'service.EndPoint' parameter is not required and the SDK chooses the sandbox or live endpoints automatically.

*	(Multiple) API account credentials, by appending a '.' (dot) character and the service name to 'service.EndPoint' parameter.

*	HTTP connection parameters, if certain connection parameters are not specified, the SDK will assume defaults for them.

*	Service configuration.

*	You can refer full list of configuration parameters in [wiki](https://github.com/paypal/sdk-core-java/wiki/SDK-Configuration-Parameters) page.

Multiple SDK usage (Multiple End-points Support)
---------------------------
Multiple end-points configuration can be done by specifying mulitple end-points identified by specific property keys. 
When using multiple SDKs in combination, like Merchant and Permissions etc..configure the endpoints as shown below 
one for each service used, The existing service.EndPoint property is still supported for backward compatibility (using 
a single SDK). The list below specifies endpoints for different services, in SANDBOX and PRODUCTION, with their 
property keys and end-point as property values.

For additional information on Merchant API, please refer to https://developer.paypal.com/webapps/developer/docs/classic/api/

Instant Payment Notification(IPN) 
---------------------------------
* Please refer readme  at 'merchantsample/IPN-README.md'


