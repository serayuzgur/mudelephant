# MudElephant

A performance oriented, shaved, light-weight Java Web framework which is at **alpha** state.

##Motivation
* Having a **fast** and flexible resource server.
* Using bleeding-edge technologies.
* Fun.

It is

* easy to use
* client-server separated
* RESTful service and static asset server with extra spices.

##TODO
* Tests
* JavaDocs

##What we got
* [Undertow](https://github.com/undertow-io/undertow) as server
* [Boon](https://github.com/boonproject/boon) for Json Parsing.
* [Athlete](./athlete/README.md) A **JAX-RS** (like) implementation with **lots** of **missing** features. It is working on top of HTTP Handlers-Undertow.
 * [Reflectasm](https://github.com/EsotericSoftware/reflectasm) for higher speed at reflection.
* [DB](./db/README.md) for JPA.
 * [Batoo](https://github.com/BatooOrg/BatooJPA) for JPA impl.
 * [Hibernate](https://github.com/hibernate) for JPA impl (Default).

##What is next
* Complete Athlete missing features.
* DAO Structure and utils

