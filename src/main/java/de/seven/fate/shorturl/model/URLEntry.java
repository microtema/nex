package de.seven.fate.shorturl.model;

import de.seven.fate.shorturl.dao.BaseEntity;
import de.seven.fate.shorturl.dao.IdAble;
import org.hibernate.validator.constraints.URL;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Mario on 03.05.2016.
 */
@NamedQueries({
        @NamedQuery(name = URLEntry.FIND_BY_LONG_URL, query = "SELECT u FROM URLEntry u WHERE u.longUrl LIKE :longUrl"),
        @NamedQuery(name = URLEntry.FIND_BY_SHORT_URL, query = "SELECT u FROM URLEntry u WHERE u.shortUrl = :shortUrl")
})
@Entity
public class URLEntry extends BaseEntity<Long> {

    public static final String FIND_BY_LONG_URL = "URLEntry.findByLongUrl";
    public static final String FIND_BY_SHORT_URL = "URLEntry.findByShortUrl";


    @NotNull
    @Column(length = 6, unique = true)
    private String shortUrl;

    @URL
    @NotNull
    @Lob
    @Column(unique = true)
    private String longUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLEntry urlEntry = (URLEntry) o;
        return Objects.equals(shortUrl, urlEntry.shortUrl) &&
                Objects.equals(longUrl, urlEntry.longUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortUrl, longUrl);
    }
}
