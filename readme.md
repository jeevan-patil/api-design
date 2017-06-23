# api-design
Sample application to study API design. Trying to learn the way workfront designed their API's. (https://developers.workfront.com/api-docs/)

API List

1] Save Organization
POST - 

Payload - 
````
{
	"name":"Facebook Inc.",
	"email":"admin@facebook.com",
	"contact":"8347 83423 43274",
	"logoUrl":"https://www.facebook.com/images/logo/facebook_logo.png",
	"website":"www.facebook.com",
	"address": {
		"city":"San Francisco",
		"state":"California",
		"zip":"323422",
		"country":"USA"
	},
	"employeeCount":13444,
	"facebookPage":"www.facebook.com/facebook",
	"twitterHandle":"@facebook"
}
````

Response:
````
{
    "id": "db27137c-4e69-458b-8f24-0eeca60f21ca",
    "name": "Facebook Inc.",
    "address": {
        "city": "San Francisco",
        "zip": "323422"
    },
    "website": "www.facebook.com"
}
````

Errors thrown by API:
- An organization with same name tried to save
````
{
    "code": 105,
    "errorMessage": "An entity already exists."
}
````

- Any of the required fields are missing from the payload
````
{
    "code": 101,
    "errorMessage": "There was one or more validation error(s)",
    "errors": [
        {
            "fieldName": "name",
            "errorMessage": "REQUIRED_FIELD_MISSING"
        }
    ]
}
````