package com.example.online_banking.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static String getAuthority(Authentication authentication) {
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            roles.add(ga.getAuthority());
        }
        if (!CollectionUtils.isEmpty(roles)) {
            return roles.get(0);
        }
        return null;
    }

    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String camelToSnake(String str) {
        String result = "";
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);
        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result
                        = result
                        + Character.toLowerCase(ch);
            } else {
                result = result + ch;
            }
        }
        return result;
    }
}
