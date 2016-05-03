package de.seven.fate.shorturl.converter;

import de.seven.fate.converter.AbstractConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.notBlank;

/**
 * Created by Mario on 03.05.2016.
 */
public class URLShortConverter extends AbstractConverter<String, String> {

    private static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
    private static final int BASE = ALPHABET.length();

    @Override
    public String convert(String orig) {
        notBlank(orig);

        byte[] digest = DigestUtils.getMd5Digest().digest(orig.getBytes());

        String base64String = Base64.encodeBase64String(digest);

        int decodeId = decode(base64String);

        String encodeStr = encode(decodeId);

        return encodeStr;
    }

    private static String encode(int num) {

        num = Math.abs(num); //prevent negative value

        StringBuilder str = new StringBuilder();

        while (num > 0) {
            str.insert(0, ALPHABET.charAt(num % BASE));
            num = num / BASE;
        }

        return StringUtils.leftPad(str.toString(), 6, ALPHABET.charAt(0));
    }

    private static int decode(String str) {
        int num = 0;

        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }

        return num;
    }
}
