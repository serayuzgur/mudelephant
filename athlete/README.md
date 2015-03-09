# Athlete

A **JAX-RS** (like) implementation with **lots** of **missing** features. It is on top of HTTP Handlers,Undertow.

##Motivation
Having a fast and thin layer, without any fat (No Servlet). 
Implementing the most used features.

##Status
* Done
	* @Path
	* @GET
	* @PUT
	* @POST
	* @DELETE
	* @CookieParam
	* @DefaultValue
	* @FormParam
	* @HeaderParam
	* @QueryParam
* Waiting
	* @OPTIONS
	* @ApplicationPath
	* @Produces
	* @Consumes
	* @Encoded
	* Boon Performance optimization. (Uses ObjectMapper, will change to Parser)
	* String Constructor *(ClassX(String))* option will be an annotation.
	* etc. (What ever is necessary...)
* Long-Term
	* @HEAD
	* @PathParam
	* @MatrixParam
	* etc. (the ones needed least)
	
##Key Features
* Decides how to parse per method *(path)* at the startup.
* If your parameters has String Constructor *(ClassX(String))* it does not use JSON parser. (For faster primitive parsing).



