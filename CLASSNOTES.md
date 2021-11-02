
10/25/2021
----------

Murodil, Instructor here.

API testing review -  6 sessions.
7 - 10 PM EST Mon/Tue/Wed for 2 weeks.

API -> Application programming Interface, it used to to share information and services between applications.

You can look at it as an application without UI(User interface).

Normally it is used as a middleman between 2 different applications.
Or nowadays, it is common that Rest API is middleman between Front end of the application and the database.

We can look at API similar to waiter in restaurant.
Waiter takes request from customer and delivers to Kitchen. Then once the items are ready, waiter takes the items back to the customer.

Customer -> Client
-> this is who sends REQUEST to API
-> requests can be GET, POST, PUT, DELETE...

Waiter -> API -> it takes the REQUEST and delivers the RESPONSE

Kitchen -> Database/Server. This is where data is stored and prepared for the response.

API is based on REQUEST  ---> RESPONSE.

When I am testing API, I perform testing based on REQUEST and VERIFYING THE RESPONSE.

2 Types of API -> SOAP and REST

REST(ful) api is more popular than SOAP Api, and it is faster, easier to develop.
Easier to work with request+response.
SOAP is slower and heavier, but it is more secure

API is platform independent, so Client and server could be developed using different technologies. Like Java, C#, JS, Python etc...

XML format is used for SOAP requests. Xml is based on tags similar to HTML.

When we are sending request and getting response from API, that data can be in different formats. XML or JSON is more popular.
XML -> works with REST and SOAP api
JSON -> works with REST


BENEFITS OF API TESTING:

- Since many applications nowadays are developed using multiple rest APIs, we can start testing early and we can catch Bugs before it is shown in UI.
-----------------------

HOW TO TEST REST API:
1) I need to first learn about the API and its end points.
- I normally go through the documentation in Confluence/Sharepoint, or if there is SWAGGER UI, I go over the sample request and response.
If there is no documentation in place, I normally reach out to other manual testers, or developers. Do a quick screen share meeting to learn how API should work,and the requirements.

	Or during daily stand up or spring planning meeting, I request a dev support from the team, and whoever has time and has knowledge about my item can normally help out.

	REST API REQUEST :
		-> Request type / Request method:
			- get -> reading data
			- post -> creating data 
			- put -> updating data if exists, or creating data if not exists
			- delete -> removing data
			...
		-> API Request URL/Endpoint -> is where the ur where request is sent.

		-> Parameters
		-> Headers
		-------------------

Lets try out:
Open postman, and paste this url for request:
https://petstore.swagger.io/v2/store/inventory


GET REQUEST:
-> Http Method(get, put, post, delete)
-> Api Url/endpoint
-> parameters. Can be like id, or some filter
-> Headers: application/json etc

RESPONSE:
-> Response body in JSOn format
-> Status code: 200 --> Successfull ok.
4xx --> Client error. error in request
5xx --> Server error, API could not process the request for some reason
3xx -> redirected
-> Headers content type: application/json

-------------------------------

After reviewing the api requirements, I firstly manually test it using POSTMAN. I send get/post/put/delete type of requests and verify the response if it is matching the expected behavior.
I do positive and negative testing by passing valid or invalid parameters.
When I get the response, I verify status code , response body data, headers.

If there is any mismatch between actual and expected response, I report a defect.
------------------------------
After successfully testing the API manually,I automate using Java and RestAssured library.
-------------------------------


10/26/2021
----------

API Review day 2.

HEADERS:
It is an addinational information about request or response.
We can include accepts types, username and passwords , tokens etc
-> in REQUEST :
KEY+VALUE format:
Accept = Application/Json
-> in RESPONSE :
ContentType = Application Json
Date = 10/26/2021 7:23 PM EST
etc


Given Accept type is Application Json
When I send get request to https://petstore.swagger.io/v2/store/inventory
Then status code should be 200
And Content type should be application json


First I manually tested the scenario using postman. After making sure that everything is working as expected , i am automating the scenario using java and restAssured library.

When it comes to Rest API automation using java, we can have several options:
-> we can use Apache HttpClient.
-> But restAssured is much more easier to use and it follows BDD syntax like given, when, and, then keywords.
-> RestAssured library is a WRAPPER over Apache HttpClient
================================================

I do positive and negative tests with API:
When I do positive tests, i pass valid parameters in my request and verify that response is successful.

	When I do negative tests, I pass invalid parameters or headers and assert that API response contains 404 status code along with error message.
-------------------------------------------------


https://petstore.swagger.io/v2/pet/findByStatus?status=available

SELECT * FROM pets
WHERE status = 'available';

SELECT * FROM pets
WHERE status = 'java';

https://www.etsy.com/search? q=coin%20collecting & ref=trnd2

Map<String,String> map = new HashMap<>();
map.put("q","coin collecting");
map.put("ref","trnd2");

given().queryParams(map).
when().get("https://www.etsy.com/search");
============================================

Summary:
- headers
- parameters
- doing positive and negative testing

I pass valid or invalid headers in api request and verify response.

parameters are 2 types:
#1) URL/Path parameters: they are included in the url itself:
ex: https://petstore.swagger.io/v2/store/order/{id}
https://petstore.swagger.io/v2/store/order/5
#2) Query parameters: they are passed as key + value format and outside the endpoint.
ex: {{baseUrl}}/pet/findByStatus?status=available


https://www.cars.com/shopping/results/?
stock_type=all
& makes%5B%5D=acura
& models%5B%5D=
& list_price_max=
& maximum_distance=20
& zip=20124

https://www.cars.com/shopping/results/?list_price_max=15000&makes[]=acura&maximum_distance=all&maximum_distance_expanded=1&maximum_distance_expanded_from=20&models[]=acura-ilx&stock_type=new&zip=20124


10/27/2021
----------

API testing Review day 3.

As we know when we send a request to API, we get a Json/XMl response.
Response will contain: status code, headers, body

READING / NAVIGATING JSON RESPONSE BODY:

4 Ways to achieve that:
1) using path() method in RestAssured
2) using JsonPath object
3) using deserialization from Json to java collections
4) using deserialization from json to POJO(custom java classes. Plain old java object)

Add new package for today:
day03_json_body_verification

path() method is part of Response object, and we need to call that method each time we need some value.

JsonPath is separate class and when we load the response body into jsonpath, it will be separate and no need to use the response object any more. And also jsonPath helps us with features like reading data with specific data types, reading as list, filtering results etc.

--------------------------

When it comes to verifying the json response body,depending on the requirement, I deserialize the json into java object like Map or List.

SERIALIZATION -> Converting from Java Object to Json format
DE-SERIALIZATION -> Converting from Json Format to Java Object

Java Object can be a custom class/pojo, or it can also be java collections type, like list or map.


11/01/2021
----------

API review day 4:

Warm up review task:

	Given the accept type is json
	When I send get request to "http://api.zippopotam.us/us/22102"
	Then status code should be 200
	and body place name should be "Mc Lean"

1) We firstly analyze the steps of the scenario and make sure we clearly understand it
2) Try manually using PostMan
3) After it works as expected in postman, We go ahead and automate it using RestAssured Library in java
   ==============================

https://www.jsonschema2pojo.org

HTTP METHODS / CRUD OPERATIONS IN REST API:
GET
POST
PUT
DELETE


CRUD ->
Create -> post
Read -> get
Update -> put
Delete -> delete
----------------------------------------------

So far we practiced and learned GET/Read operations.
GET:
When we send a Get request to REST Api, we only need to provide:
endpoint
parameters and values
headers

Response:
-> status code
-> headers
-> json body
-------------------------------	

POST REQUEST :
- Endpoint that accepts POST http method
- parameters if present
- headers
- Json Request Body / PayLoad

Response:
-> status code
-> headers
-> json body

=============================

API URL: http://54.205.239.177:8000/api/spartans

Given accept type and content type are json
And request json body is :
"{
"name":"Murodil",
"gender" : "Male",
"phone" : 2023751774
}"
When i send post request
Then status code is 201
And content type is json
And body contains message "success": "A Spartan is Born!"
And id is auto generated
And name, gender, phone info matches my request

Add new class SpartanPostRequest

