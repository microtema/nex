package de.seven.fate.shorturl.model;

import de.seven.fate.shorturl.dao.IdAble;

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
public class URLEntry implements IdAble<Long> {

    public static final String FIND_BY_LONG_URL = "URLEntry.findByLongUrl";
    public static final String FIND_BY_SHORT_URL = "URLEntry.findByShortUrl";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 256)
    private String shortUrl;

    @NotNull
    @Lob
    private String longUrl;

    private Date dateCreated;
    private Date dateUpdated;

    @Version
    private Long version;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLEntry urlEntry = (URLEntry) o;
        return Objects.equals(shortUrl, urlEntry.shortUrl) &&
                Objects.equals(longUrl, urlEntry.longUrl) &&
                Objects.equals(dateCreated, urlEntry.dateCreated) &&
                Objects.equals(dateUpdated, urlEntry.dateUpdated) &&
                Objects.equals(version, urlEntry.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortUrl, longUrl, dateCreated, dateUpdated, version);
    }
}
