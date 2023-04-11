# cryptocurrency-converter

## Run spring boot applicatioin
Before running back-end part make sure the next environment variables (db_host, db_password) are set up.

For example in Windows run

set db_host=localhost

For linux run

export db_host=localhost


## Supported cryptocurrency
BTC, ETH

## Supported Currency
USD

# Rest endpoints

## /api/convert
Convert given amounf of cryptocurrency to currency.
### Request type
GET
### Request headers
```
"content-type": "application/json"
```
### Request body
```
{    
    "cryptocurrency":"cryptocurrency-short-name",
    "currency":"currency-short-name",
    "amount":"amount of cryptocurrency"
}
```
### Response type
application/json
### Response status codes
```
Success - 200 with body
Failed - 204 external api is not working
Failed - 400 request send not supported cryptocurrency or currency for conversion
Failed - 406 body value is incorrect or empty
```
### Response body for code 200
```
{
   
   "156834.91069999998"
          
}
```

