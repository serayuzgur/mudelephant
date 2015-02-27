package io.mudelephant.sample;

import io.mudelephant.athlete.resource.Resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("form")
public class SampleHtml implements Resource {


    @GET
    public String get() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<form method=\"post\">\n" +
                "  First name: <input type=\"text\" name=\"fname\"><br>\n" +
                "  Last name: <input type=\"text\" name=\"lname\"><br>\n" +
                "  <input type=\"submit\" value=\"Submit\">\n" +
                "</form>\n" +
                "\n" +
                "<p>Click on the submit button, and the input will be sent to a page on the server called \"demo_form_method.asp\".</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }


    @POST
    public String post(@FormParam("fname") String name,
                       @FormParam("lname") String lname) {
        return "post: " + name + " " + lname;
    }


}
