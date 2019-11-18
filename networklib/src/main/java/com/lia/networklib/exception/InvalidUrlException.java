package com.lia.networklib.exception;

public class InvalidUrlException extends RuntimeException {

    public InvalidUrlException(String url) {
        super("You've configured an invalid url : " + (isEmpty(url) ? "EMPTY_OR_NULL_URL" : url));
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    private static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }
}
