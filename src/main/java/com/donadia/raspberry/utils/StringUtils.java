package com.donadia.raspberry.utils;

import java.util.HashSet;
import java.util.Set;

public class StringUtils {

    public static Set<String> splitCSVString(String str) {
        Set<String> results = new HashSet<>();
        if (str == null) {
            return results;
        }
        String[] result = str.split(",");
        if(result.length == 1 && result[0].isEmpty()) {
            return results;
        }
        
        for (String s : result) {
            if(s.trim().isEmpty()) {
                continue;
            }
            if(s.contains(" and ")) {
                String[] moreResults = s.split(" and ");
                for (String r : moreResults) {
                    if(r.trim().isEmpty()) {
                        continue;
                    }
                    results.add(r.trim());
                }
            } else {                
                results.add(s.trim());
            }
        }
        return results;
    }
}
