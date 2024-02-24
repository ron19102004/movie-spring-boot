package com.movie.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCustom {
    public static boolean isEmail(String email) {
        final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    public static boolean isPhone(String phone) {
        final String phoneRegex = "^(\\+[0-9]{1,3})?[0-9]{10,}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher phoneMatcher = phonePattern.matcher(phone);
        return phoneMatcher.matches();
    }
}
