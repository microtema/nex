package de.seven.fate.shorturl.rest;

import de.seven.fate.shorturl.facade.URLFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Mario on 03.05.2016.
 */
@Path("/url")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class URLResource {

    @Inject
    private URLFacade facade;

    @GET
    @Path("/short/{url}")
    public Response getShortUrl(@PathParam("url") String url) {

        return Response.ok().build();
    }

    @GET
    @Path("/long/{url}")
    public Response getLongUrl(@PathParam("url") String url) {

        return Response.ok().build();
    }
}
