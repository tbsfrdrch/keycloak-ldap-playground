# Keycloak LDAP Playground

## Prerequisites

* docker
* docker-compose
* Java 8+

## Setup the OpenLDAP and Keycloak servers

* Clone this repo
* `cd` to the `infrastructure` folder
* Run `docker-compose up`
* Login to the [Keycloak Administration Console](http://localhost:8080/auth/admin/)
    * User: `keycloak` / Password: `password`
* Import the realm configuration:
  * Hover the mouse over the `Master` realm name
  * Press the `Add realm` button
  * Select the `infrastructure/realm-starfleet.json` file as import file
  * Press `Create`

## Starting the application

* `cd` back to the checkout folder
* Run `./mvnw clean spring-boot:run`

## Test the different endpoints

| Endpoint                                                  | Accessible by                                         |
| :-------------------------------------------------------- | :---------------------------------------------------- |
| [Show user](http://localhost:8081)                        | All                                                   |
| [Replicator](http://localhost:8081/replicate/earlgray)    | All authenticated users                               |
| [Warp](http://localhost:8081/warp)                        | `OPERATIONS_OFFICER`                                  |
| [Security](http://localhost:8081/security)                | `SECURITY_OFFICER`                                    |
| [Klingon Security](http://localhost:8081/klingonsecurity) | user with roles`SECURITY_OFFICER` **and** `KLINGON`   |
| [Engage](http://localhost:8081/engage)                    | user with roles `COMMANDING_OFFICER` **or** `CAPTAIN` |

Use the [logout](http://localhost:8081/logout) endpoint to logout a user again.

## User / role mapping

(every user's password is `password`)

| User   | Role(s)                         |
| :----- | :------------------------------ |
| picard | `CAPTAIN`                       |
| riker  | `COMMANDING_OFFICER`            |
| data   | `OPERATIONS_OFFICER`, `ANDROID` |
| worf   | `SECURITY_OFFICER`, `KLINGON`   |
| tasha  | `SECURITY_OFFICER`              |
| deanna | `COUNSELOR`                     |

