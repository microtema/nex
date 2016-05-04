package de.seven.fate.shorturl.service;

import de.seven.fate.shorturl.converter.URLShortConverter;
import de.seven.fate.shorturl.dao.URLEntryDAO;
import de.seven.fate.shorturl.model.URLEntry;
import org.apache.commons.lang3.Validate;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

import static org.apache.commons.lang3.Validate.notBlank;

/**
 * Created by Mario on 03.05.2016.
 */
@Stateless
public class URLService {

    @Inject
    private URLEntryDAO dao;

    @Inject
    private URLShortConverter converter;


    public URLEntry getURLEntityByLongUrl(String longUrl) {
        notBlank(longUrl);

        URLEntry entry = findURLEntityByLongUrl(longUrl);

        return Optional.ofNullable(entry).orElseThrow(() -> new NoSuchEntityException("unable to get URLEntity by URL: " + longUrl));
    }

    public URLEntry findURLEntityByLongUrl(String longUrl) {
        notBlank(longUrl);

        return dao.getURLEntityByLongUrl(longUrl);
    }

    public URLEntry findURLEntityByShortUrl(String shortUrl) {
        notBlank(shortUrl);

        return dao.getURLEntityByShortUrl(shortUrl);
    }

    public URLEntry getURLEntityByShortUrl(String shortUrl) {
        notBlank(shortUrl);

        return dao.getURLEntityByShortUrl(shortUrl);
    }

    public URLEntry getOrSaveURLEntity(String longUrl) {

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
