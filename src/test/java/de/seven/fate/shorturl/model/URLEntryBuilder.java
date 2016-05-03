package de.seven.fate.shorturl.model;

import de.seven.fate.model.builder.AbstractModelBuilder;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLEntryBuilder extends AbstractModelBuilder<URLEntry> {

    @Override
    public URLEntry min() {
        URLEntry min = super.min();
        min.setId(null);
        return min;
    }
}
