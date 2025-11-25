package utils;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern EMAIL = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    public static boolean isNonEmpty(String s) { return s!=null && !s.trim().isEmpty(); }
    public static boolean isValidEmail(String e) { return e!=null && EMAIL.matcher(e).matches(); }
    public static boolean isStrongPassword(String p) { return p!=null && p.length()>=6; }
}
