# KeyPair & License Management Web Application

Spring (Boot, Security, GraphQL), License3j, Hibernate, PostgreSQL & pgModeler, Liquibase 

## Project state & goal
In progress (70% done), just for fun

## Usage

Check GraphQL schema here.

#### The below GraphQL mutation
``` json
mutation {
  keyPair(keyPairInput: {name: "keyPairName"}) {
    name
    createdAt
    publicKey {
      value
    }
    privateKey {
      value
      license(
        licenseInput: {name: "licenseName", 
          features: [
            {name: "featureName1", value: "strValue", type: STRING},
            {name: "featureName2", value: "4.45", type: FLOAT},
            {name: "featureName3", value: "77888", type: INTEGER}
          ], expires: 1653494673772}
      ) {
        body
        signature
        name
        createdAt
      }
    }
  }
}
```

#### will produce json like this

``` json
{
  "data": {
    "keyPair": {
      "name": "keyPairName",
      "createdAt": "1653500088102",
      "publicKey": {
        "value": "UlNBADCCASIwDQYJKoZIhvcNAQEBBQADggEPADCC.............
      },
      "privateKey": {
        "value": "UlNBADCCBL0CAQAwDQYJKoZIhvcNAQEBBQAEggSn.............
        "license": {
          "body": "expiryDate:DATE=2022-05-25 16:04:33.772.............
          "signature": "GyftL17W3OKG1Egc/sEdBX4aBAFL/0iG+S.............
          "name": "licenseName",
          "createdAt": "1653500088531"
        }
      }
    }
  }
}
```

## Board

#### Done
* DB model
* pgModeler DB model & Liquibase initial sql script
* License & KeyPair services
* Common GraphQL controller
* Basic InMemory users & Simple access control   
* Paginated querying with filtering
* 

#### Todo & In progress
* DB - add indexes, constraints
* Entities - add constraints
* Entities - add extra fields
* Security - close holes
* Remove datasource login data from local properties  
* GraphQL - disable graphiql

