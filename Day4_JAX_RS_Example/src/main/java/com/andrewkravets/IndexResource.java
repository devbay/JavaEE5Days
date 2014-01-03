package com.andrewkravets;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by Andrew Kravets.
 * Date: 1/3/14.
 * Url: andrewkravets.com
 */
@Path("/")
public class IndexResource{

    @GET
    @Produces("text/plain")
    public String getHelloWorld(){
        return "Hello World";
    }

    @GET
    @Path("/{name}")
    @Produces("text/plain")
    public String sayHelloTo(@PathParam("name") String msg) {
       return "Hello " + msg +"!";
    }
}
