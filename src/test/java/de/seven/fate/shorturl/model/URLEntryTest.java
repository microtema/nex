package de.seven.fate.shorturl.model;

import de.seven.fate.model.adapter.string.UrlPropertyRandomAdapter;
import de.seven.fate.shorturl.converter.URLShortConverter;
import de.seven.fate.shorturl.dao.BaseEntity;
import de.seven.fate.shorturl.dao.IdAble;
import junit.framework.Assert;
import org.apache.commons.lang3.SerializationUtils;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLEntryTest {

    URLEntry sut;

    URLEntryBuilder builder = new URLEntryBuilder(new UrlPropertyRandomAdapter(), new URLShortConverter());


    @Test
    public void assignableFromSerializable() throws Exception {

        assertTrue(Serializable.class.isAssignableFrom(URLEntry.class));
    }

    @Test
    public void assignableFromIdAble() throws Exception {

        assertTrue(Serializable.class.isAssignableFrom(URLEntry.class));
    }

    @Test
    public void assignableFromBaseEntity() throws Exception {

        assertTrue(BaseEntity.class.isAssignableFrom(URLEntry.class));
    }

    @Test
    public void shouldBeAnnotatedAsEntity() throws Exception {
        assertNotNull(URLEntry.class.getAnnotation(Entity.class));
    }

    @Test
    public void equals() throws Exception {

        URLEntry model = builder.min();

        URLEntry entity = SerializationUtils.clone(model); //simulate entity from DB
        entity.setId(Long.MAX_VALUE);
        entity.setCreatedDate(new Date());
        entity.setUpdateDate(new Date());
        entity.setVersion(Long.MAX_VALUE);

        assertEquals(model, entity);
    }

    @Test
    public void equalsNotOnDifferentShortUrl() throws Exception {

        URLEntry model = builder.min();

        URLEntry entity = SerializationUtils.clone(model);
        entity.setShortUrl(UUID.randomUUID().toString());

        assertFalse(model.equals(entity));
    }

    @Test
    public void equalsNotOnDifferentLongUrl() throws Exception {

        URLEntry model = builder.min();

        URLEntry entity = SerializationUtils.clone(model);
        entity.setLongUrl(UUID.randomUUID().toString());

        assertFalse(model.equals(entity));
    }

}