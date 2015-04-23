# Athlete

A **JAX-RS** (like) implementation with **lots** of **missing** features. It is on top of HTTP Handlers,Undertow. Using [Reflectify](https://code.google.com/p/reflectify-protocol/) for all reflection needs.

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
	* Primitive types, their Wrappers and some de-facto parameter classes are parsed with custom parsers. 
	 * BigInteger
	 * Boolean, boolean
	 * Byte, byte
	 * Character, char
	 * Double, double 
	 * Float, float
	 * Integer, int
	 * Long, long
	 * Short, short
	 * String
* Waiting
	* @OPTIONS
	* @Produces
	* @Consumes
	* @Encoded
	* Boon Performance optimization. (Uses ObjectMapper, will change to Parser)
	* Custom DateParser
	* etc. (What ever is necessary...)
* Long-Term
	* @HEAD
	* @PathParam
	* @MatrixParam
	* @ApplicationPath
	* etc. (the ones needed least)



