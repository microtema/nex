package it.de.seven.fate.shorturl.rest;

import de.seven.fate.shorturl.model.URLEntryBuilder;
import de.seven.fate.shorturl.rest.URLResource;
import it.de.seven.fate.shorturl.util.DeploymentUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by Mario on 03.05.2016.
 */
@RunWith(Arquillian.class)
public class URLResourceIT {

    URLResource sut;

    URLEntryBuilder builder = new URLEntryBuilder();

    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentUtil.createDeployment();
    }

    @Test
    @RunAsClient
    @InSequence(1)
    public void getShortUrl(@ArquillianResource URL baseURL) throws Exception {

        String longUrl = builder.random().getLongUrl();
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/url/short/"+longUrl).toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        ClientResponse<String> clientResponse = request.get(String.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        String url = clientResponse.getEntity();

        Assert.assertNotNull(url);
        Assert.assertFalse(url.isEmpty());
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void getLongUrl(@ArquillianResource URL baseURL) throws Exception {

        String longUrl = builder.random().getLongUrl();
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/url/short/"+longUrl).toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        ClientResponse<String> clientResponse = request.get(String.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        String shortUrl = clientResponse.getEntity();

        Assert.assertNotNull(shortUrl);
        Assert.assertFalse(shortUrl.isEmpty());


        //given

        //when
        request = new ClientRequest(new URL(baseURL, "rest/url/long/"+shortUrl).toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        clientResponse = request.get(String.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());
        Assert.assertEquals(longUrl, clientResponse.getEntity());
    }

}