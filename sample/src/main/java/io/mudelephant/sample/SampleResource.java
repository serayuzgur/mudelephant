package io.mudelephant.sample;

import com.google.inject.Inject;

import javax.ws.rs.*;

@Path("test/1/2")
public class SampleResource {

    @Inject
    SamplePojo samplePojo;


    @GET
    public String get(String s) {
        return "get: " + s + samplePojo.toString();
    }

    @PUT
    public String put(SamplePojo pojo) {
        return "put: " + pojo.toString();
    }

    @POST
    public String post(@FormParam("pojo") SamplePojo pojo) {
        return "post: " + "pojo: " + pojo.toString() + " ip : ";
    }

    @DELETE
    public String delete(String s) {
        return "delete: " + s;
    }

    @GET
    @Path("3")
    public String get3(@CookieParam("cookiekey") String cookie,
                       @DefaultValue("cookiedefault") @CookieParam("cookiekey2") String cookie2,
                       @HeaderParam("headerkey") String header,
                       @DefaultValue("headerdefault") @HeaderParam("headerkey2") String header2,
                       @QueryParam("querykey") String query,
                       @DefaultValue("c") @QueryParam("querykey2") Character query2,
                       @DefaultValue("3") @QueryParam("querykey3") Integer query3) {
        return "get3 : " +
                cookie + "," +
                cookie2 + "," +
                header + "," +
                header2 + "," +
                query + "," +
                query2 + ","+
                query3 + ",";
    }

    @PUT
    @Path("/3/")
    public SamplePojo put3(SamplePojo pojo) {
        return pojo;
    }

}
