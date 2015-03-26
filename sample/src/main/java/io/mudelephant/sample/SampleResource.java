package io.mudelephant.sample;

import com.google.inject.Inject;
import io.mudelephant.athlete.annotation.JPAOperation;
import io.mudelephant.batoo.EntityManagerManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    @JPAOperation(transactional = true)
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
    @JPAOperation(transactional = true)
    public List<User> get4(@CookieParam("cookiekey") String cookie,
                           @DefaultValue("cookiedefault") @CookieParam("cookiekey2") String cookie2,
                           @HeaderParam("headerkey") String header,
                           @DefaultValue("headerdefault") @HeaderParam("headerkey2") String header2,
                           @QueryParam("querykey") String query,
                           @DefaultValue("c") @QueryParam("querykey2") Character query2,
                           @DefaultValue("3") @QueryParam("querykey3") Integer query3) {
        EntityManager entityManager = EntityManagerManager.getOrOpenCurrentEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query1 = builder.createQuery(User.class);
        Root<User> root = query1.from(User.class);
//        Predicate equal = builder.equal(root.get("email"), "aa");
//        query1.where(equal);
//        query1.select(root.get());
        List<User> users = entityManager.createQuery("select u from User u ", User.class).getResultList();
        return users;
    }

    @PUT
    @Path("/3/")
    public SamplePojo put3(SamplePojo pojo) {
        return pojo;
    }

}
