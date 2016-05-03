package de.seven.fate.shorturl.converter;

import de.seven.fate.converter.AbstractConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.Validate;

import javax.ejb.Stateless;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLShort2LongConverter extends AbstractConverter<String, String> {

    @Override
    public String convert(String orig) {
        Validate.notNull(orig);

        byte[] digest = DigestUtils.getMd5Digest().digest(orig.getBytes());

        return Base64.encodeBase64String(digest);
    }
}
