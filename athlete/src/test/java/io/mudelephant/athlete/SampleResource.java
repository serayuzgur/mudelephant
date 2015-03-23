package io.mudelephant.athlete;

import javax.ws.rs.*;

/**
 * Created by serayuzgur on 27/01/15.
 */
@Path("deneme/1/2")
public class SampleResource {


    @GET
    public String get(String s){
        return s;
    }
    @PUT
    public String put(String s){
        return s;
    }
    @POST
    public String post(String s){
        return s;
    }
    @DELETE
    public String delete(String s){
        return s;
    }

    @GET
    @Path("3")
    public String get3(String s){
        return s;
    }
    @PUT
    @Path("/3/")
    public String put3(String s){
        return s;
    }

}
