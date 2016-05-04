package de.seven.fate.shorturl.model;

import de.seven.fate.model.adapter.string.UrlPropertyRandomAdapter;
import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.shorturl.converter.URLShortConverter;

import javax.inject.Inject;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLEntryBuilder extends AbstractModelBuilder<URLEntry> {

    private final UrlPropertyRandomAdapter propertyRandomAdapter;
    private final URLShortConverter converter;

    @Inject
    public URLEntryBuilder(UrlPropertyRandomAdapter propertyRandomAdapter, URLShortConverter converter) {
        this.propertyRandomAdapter = propertyRandomAdapter;
        this.converter = converter;
    }

    @Override
    public URLEntry min() {

        URLEntry min = super.min();

        min.setId(null);
        min.setCreatedDate(null);
        min.setUpdateDate(null);
        min.setLongUrl(propertyRandomAdapter.randomValue());
        min.setShortUrl(converter.convert(min.getLongUrl()));

        return min;
    }
}
