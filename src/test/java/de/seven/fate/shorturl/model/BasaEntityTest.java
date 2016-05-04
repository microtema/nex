package de.seven.fate.shorturl.model;

import de.seven.fate.model.adapter.string.UrlPropertyRandomAdapter;
import de.seven.fate.shorturl.converter.URLShortConverter;
import de.seven.fate.shorturl.dao.BaseEntity;
import de.seven.fate.shorturl.dao.IdAble;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Mario on 03.05.2016.
 */
public class BasaEntityTest {

    BaseEntity sut;


    @Test
    public void assignableFromSerializable() throws Exception {

        assertTrue(Serializable.class.isAssignableFrom(BaseEntity.class));
    }

    @Test
    public void assignableFromIdAble() throws Exception {

        assertTrue(IdAble.class.isAssignableFrom(BaseEntity.class));
    }


    @Test
    public void shouldBeAnnotatedAsMappedSuperclass() throws Exception {
        assertNotNull(BaseEntity.class.getAnnotation(MappedSuperclass.class));
    }

    @Test
    public void shouldBeAnnotatedAsID() throws Exception {

        Field field = BaseEntity.class.getDeclaredField("id");
        assertNotNull(field.getAnnotation(Id.class));
    }

    @Test
    public void shouldBeAnnotatedAsNotNull() throws Exception {

        Field field = BaseEntity.class.getDeclaredField("id");
        assertNotNull(field.getAnnotation(Id.class));
    }

}