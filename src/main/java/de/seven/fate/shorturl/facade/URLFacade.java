package de.seven.fate.shorturl.facade;

import de.seven.fate.shorturl.model.URLEntry;
import de.seven.fate.shorturl.service.URLService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Mario on 03.05.2016.
 */
@Stateless
public class URLFacade {

    @Inject
    private URLService service;


    public String getShortUrl(String longUrl) {

        URLEntry urlEntry = service.getURLEntityByLongUrl(longUrl);

        return urlEntry.getShortUrl();
    }

    public URI getLongUrl(String shortUrl) throws URISyntaxException {

        return new URI(getLongUrlAsString(shortUrl));
    }

    public String getLongUrlAsString(String shortUrl) {

        URLEntry urlEntry = service.getURLEntityByShortUrl(shortUrl);

        return urlEntry.getLongUrl();
    }

    public String convertAndSaveShortUrl(String longUrl) {

        URLEntry urlEntry = service.getOrSaveURLEntity(longUrl);

        return urlEntry.getShortUrl();
    }
}
