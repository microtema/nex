package de.seven.fate.shorturl.dao;

import de.seven.fate.model.util.CollectionUtil;
import de.seven.fate.shorturl.model.URLEntry;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLEntryDAO extends AbstractEntityDAO<URLEntry, Long> {

    public URLEntry getURLEntityByLongUrl(String longUrl) {

        Query query = createNamedQuery(URLEntry.FIND_BY_LONG_URL, "longUrl", longUrl);

        List<URLEntry> resultList = query.getResultList();

        return CollectionUtil.first(resultList);
    }

    public URLEntry getURLEntityByShortUrl(String shortUrl) {

        Query query = createNamedQuery(URLEntry.FIND_BY_SHORT_URL, "shortUrl", shortUrl);

        List<URLEntry> resultList = query.getResultList();

        return CollectionUtil.first(resultList);
    }
}
