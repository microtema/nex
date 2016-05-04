package de.seven.fate.shorturl.rest;

import de.seven.fate.shorturl.facade.URLFacade;
import de.seven.fate.shorturl.rest.interceptor.RestErrorInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * Created by Mario on 03.05.2016.
 */
@Stateless
@Path("/url")
@Produces(MediaType.APPLICATION_JSON)
@Interceptors(RestErrorInterceptor.class)
public class URLResource {

    @Inject
    private URLFacade facade;

    @GET
    @Path("/{url}")
    public Response redirect(@PathParam("url") String url) throws URISyntaxException {

        return Response.seeOther(facade.getLongUrl(url)).build();
    }

    @POST
    public Response convertAndSaveShortUrl(String url) {

        return Response.ok(facade.convertAndSaveShortUrl(url)).build();
    }

    @GET
    @Path("/long/{url}")
    public Response getLongUrl(@PathParam("url") String url) {

        return Response.ok(facade.getLongUrlAsString(url)).build();
    }
}
