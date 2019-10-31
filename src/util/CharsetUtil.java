package util;

import java.io.UnsupportedEncodingException;

public class CharsetUtil {
    public static final String US_ASCII = "US-ASCII";

    public static final String ISO_8859_1 = "ISO-8859-1";

    public static final String UTF_8 = "UTF-8";

    public static final String UTF_16BE = "UTF-16BE";

    public static final String UTF_16LE = "UTF-16LE";

    public static final String UTF_16 = "UTF-16";

    public static final String GBK = "GBK";

    public static String toASCII(String str) throws UnsupportedEncodingException {
        return changeCharset(str, US_ASCII);
    }

    public static String toISO_8859_1(String str) throws UnsupportedEncodingException{
        return changeCharset(str, ISO_8859_1);
    }

    public static String toUTF_8(String str) throws UnsupportedEncodingException{
        return changeCharset(str, UTF_8);
    }

    public static String toUTF_16BE(String str) throws UnsupportedEncodingException{
        return changeCharset(str, UTF_16BE);
    }

    public static String toUTF_16LE(String str) throws UnsupportedEncodingException{
        return changeCharset(str, UTF_16LE);
    }

    public static String toUTF_16(String str) throws UnsupportedEncodingException{
        return changeCharset(str, UTF_16);
    }

    public static String toGBK(String str) throws UnsupportedEncodingException{
        return changeCharset(str, GBK);
    }

    public static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes();
            return new String(bs, newCharset);
        }
        return null;
    }

    public static String changeCharset(String str, String oldCharset, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes(oldCharset);
            return new String(bs, newCharset);
        }
        return null;
    }

}
