package de.seven.fate.shorturl.service;

import de.seven.fate.shorturl.converter.URLShort2LongConverter;
import de.seven.fate.shorturl.dao.URLEntryDAO;
import de.seven.fate.shorturl.model.URLEntry;
import org.apache.commons.lang3.Validate;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Mario on 03.05.2016.
 */
@Stateless
public class URLService {

    @Inject
    private URLEntryDAO dao;

    @Inject
    private URLShort2LongConverter converter;


    public URLEntry getURLEntityByLongUrl(String longUrl) {
        Validate.notNull(longUrl);

        URLEntry entry = findURLEntityByLongUrl(longUrl);

        if (entry != null) {
            return entry;
        }

        throw new NoSuchEntityException("unable to get URLEntity by URL: " + longUrl);
    }

    public URLEntry findURLEntityByLongUrl(String longUrl) {
        Validate.notNull(longUrl);

        return dao.getURLEntityByLongUrl(longUrl);
    }

    public URLEntry findURLEntityByShortUrl(String shortUrl) {
        Validate.notNull(shortUrl);

        return dao.getURLEntityByShortUrl(shortUrl);
    }

    public URLEntry getURLEntityByShortUrl(String shortUrl) {
        Validate.notNull(shortUrl);

        return dao.getURLEntityByShortUrl(shortUrl);
    }

    public URLEntry saveURLEntity(String longUrl) {

        URLEntry entry = findURLEntityByLongUrl(longUrl);

        if (entry != null) {

            return entry;
        }

        entry = createURLEntry(longUrl);

        dao.save(entry);

        return entry;
    }

    private URLEntry createURLEntry(String longUrl) {

        URLEntry entry = new URLEntry();
        entry.setLongUrl(longUrl);
        entry.setShortUrl(converter.convert(longUrl));

        return entry;
    }
}
