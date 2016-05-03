package de.seven.fate.shorturl.model;

import de.seven.fate.shorturl.dao.IdAble;
import junit.framework.Assert;
import org.junit.Test;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLEntryTest {

    URLEntry sut;

    @Test
    public void testImplementation() throws Exception {

        Assert.assertTrue(Serializable.class.isAssignableFrom(URLEntry.class));
    }

    @Test
    public void testImplementationFromIDAble() throws Exception {

        Assert.assertTrue(Serializable.class.isAssignableFrom(IdAble.class));
    }

    @Test
    public void shouldBeAnnotatedWithEntity() throws Exception {
        Assert.assertNotNull(URLEntry.class.getAnnotation(Entity.class));
    }

    //TODO test hash&equals

}