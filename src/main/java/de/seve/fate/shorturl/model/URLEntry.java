package de.seve.fate.shorturl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Mario on 03.05.2016.
 */
@Entity
public class URLEntry {

    @Id
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

}
