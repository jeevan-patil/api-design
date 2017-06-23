# api-design
Sample application to study API design. Trying to learn the way workfront designed their API's. (https://developers.workfront.com/api-docs/)

API List

1] Save Organization
POST - http://server-url/api/v1/org

Payload:
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

2] Fetch organization data by organization id
GET - http://server-url/api/v1/org/{organizationId}

API returns only default fields if no query parameters are passed.

Response:
```
{
    "id": "c531be33-021e-4827-9697-27d8e363399c",
    "name": "Quora INC.",
    "address": {
        "city": "Palo Alto",
        "zip": "23444"
    },
    "website": "https://www.quora.com"
}
```
The API accepts request parameter named `fields` which can be used to fetch extra attributes that user needs.
All the attributes of an entity are not fetched. Only fields marked by `@DefaultField` annotation are returned by API.
If user needs other attributes of an entity, they can be passed in the `fields` query parameter.

For example, following request will return all the default fields along with the fields mentioned as query parameter.
`http://server-url/api/v1/org/{organizationId}?fields=email,twitterHandle,address.state,address.country`

```
{
    "id": "c531be33-021e-4827-9697-27d8e363399c",
    "name": "Quora INC.",
    "address": {
        "city": "Palo Alto",
        "state": "San Francisco",
        "zip": "23444",
        "country": "USA"
    },
    "website": "https://www.quora.com",
    "email": "hey@quora.com",
    "twitterHandle": "@quora"
}
```

If you want all the fields of an entity to be returned by API, pass asterisk(*) in query parameters as `?fields=*`, `?fields=email,address.*`