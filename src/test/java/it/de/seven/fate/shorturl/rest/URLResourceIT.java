package it.de.seven.fate.shorturl.rest;

import de.seven.fate.model.adapter.string.UrlPropertyRandomAdapter;
import de.seven.fate.shorturl.converter.URLShortConverter;
import de.seven.fate.shorturl.model.URLEntryBuilder;
import de.seven.fate.shorturl.rest.URLResource;
import de.seven.fate.shorturl.rest.interceptor.ErrorMessage;
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

    @ArquillianResource
    URL baseURL;

    URLEntryBuilder builder = new URLEntryBuilder(new UrlPropertyRandomAdapter(), new URLShortConverter());

    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentUtil.createDeployment();
    }

    @Test
    @RunAsClient
    public void shouldNotConvertShortUrl() throws Exception {

        //given
        String longUrl = "";

        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/url").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("POST");
        request.body(MediaType.APPLICATION_JSON, longUrl);
        ClientResponse<ErrorMessage> clientResponse = request.post(ErrorMessage.class);

        //then
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), clientResponse.getStatus());

        ErrorMessage errorMessage = clientResponse.getEntity();

        Assert.assertNotNull(errorMessage);
        Assert.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), errorMessage.getCode());
    }

    @Test
    @RunAsClient
    public void getShortUrl() throws Exception {

        //given
        String longUrl = builder.random().getLongUrl();

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/url").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("POST");
        request.body(MediaType.APPLICATION_JSON, longUrl);
        ClientResponse<String> clientResponse = request.post(String.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        String url = clientResponse.getEntity();

        Assert.assertNotNull(url);
        Assert.assertFalse(url.isEmpty());
    }

    @Test
    @RunAsClient
    public void getLongUrl() throws Exception {

        //given
        String longUrl = builder.random().getLongUrl();
        String shortUrl = convertAndSaveUrl(longUrl);
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/url/long/" + shortUrl).toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        ClientResponse<String> clientResponse = request.get(String.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());
        Assert.assertEquals(longUrl, clientResponse.getEntity());
    }

    @Test
    @RunAsClient
    public void redirect() throws Exception {

        //given
        String longUrl = builder.random().getLongUrl();
        String shortUrl = convertAndSaveUrl(longUrl);

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/url/" + shortUrl).toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        ClientResponse<String> clientResponse = request.get();

        //then
        assertEquals(Response.Status.SEE_OTHER.getStatusCode(), clientResponse.getStatus());
        Assert.assertEquals(longUrl, clientResponse.getLocation().getHref());
    }

    private String convertAndSaveUrl(String longUrl) throws Exception {

        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/url").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("POST");
        request.body(MediaType.APPLICATION_JSON, longUrl);
        ClientResponse<String> clientResponse = request.post(String.class);

        return clientResponse.getEntity();
    }

}