package io.mudelephant.sample;

import com.google.inject.Inject;
import io.mudelephant.athlete.annotation.DBOperation;
import io.mudelephant.db.EntityManagerManager;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import java.util.List;

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
    @DBOperation(transactional = true)
    public String get3(@CookieParam("cookiekey") String cookie,
                       @DefaultValue("cookiedefault") @CookieParam("cookiekey2") String cookie2,
                       @HeaderParam("headerkey") String header,
                       @DefaultValue("headerdefault") @HeaderParam("headerkey2") String header2,
                       @QueryParam("querykey") String query,
                       @DefaultValue("c") @QueryParam("querykey2") Character query2,
                       @DefaultValue("3") @QueryParam("querykey3") Integer query3) {
        EntityManager entityManager = EntityManagerManager.getOrOpenCurrentEntityManager();
        entityManager.persist(new User(
                "aa",
                "bb",
                "cc",
                "dd",
                true,
                "hh"));
        return "get3 : " +
                cookie + "," +
                cookie2 + "," +
                header + "," +
                header2 + "," +
                query + "," +
                query2 + "," +
                query3 + ",";
    }

    @GET
    @Path("3/4")
    @DBOperation(transactional = true)
    public List<User> get4(@CookieParam("cookiekey") String cookie,
                           @DefaultValue("cookiedefault") @CookieParam("cookiekey2") String cookie2,
                           @HeaderParam("headerkey") String header,
                           @DefaultValue("headerdefault") @HeaderParam("headerkey2") String header2,
                           @QueryParam("querykey") String query,
                           @DefaultValue("c") @QueryParam("querykey2") Character query2,
                           @DefaultValue("3") @QueryParam("querykey3") Integer query3) {
        EntityManager entityManager = EntityManagerManager.getOrOpenCurrentEntityManager();
        List<User> users = (List<User>) entityManager.createQuery("select u from User u ").getResultList();
        return users;
    }

    @PUT
    @Path("/3/")
    public SamplePojo put3(SamplePojo pojo) {
        return pojo;
    }

}
