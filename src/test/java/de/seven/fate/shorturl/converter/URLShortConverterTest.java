package de.seven.fate.shorturl.converter;

import de.seven.fate.model.adapter.string.UrlPropertyRandomAdapter;
import de.seven.fate.shorturl.model.URLEntry;
import de.seven.fate.shorturl.model.URLEntryBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLShortConverterTest {

    URLShortConverter sut = new URLShortConverter();

    URLEntryBuilder builder = new URLEntryBuilder(new UrlPropertyRandomAdapter(), new URLShortConverter());

    URLEntry model = builder.random();

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() throws Exception {
        sut.convert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() throws Exception {
        sut.convert("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnBlank() throws Exception {
        sut.convert("  ");
    }

    @Test
    public void convert() throws Exception {
        assertEquals("6f-dT3", sut.convert("http://nexus.com"));
    }

    @Test
    public void convertWithMaxLength() throws Exception {
        String convert = sut.convert(model.getLongUrl());
        if (6 != convert.length()) {
            assertEquals(6, convert.length());
        }
        assertEquals(6, convert.length());
    }

}