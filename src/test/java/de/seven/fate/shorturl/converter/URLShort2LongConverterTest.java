package de.seven.fate.shorturl.converter;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLShort2LongConverterTest {

    URLShort2LongConverter sut = new URLShort2LongConverter();

    @Test
    public void convert() throws Exception {
        String shortUrl = sut.convert("http://nexus.com");
        Assert.assertNotNull(shortUrl);
    }

}