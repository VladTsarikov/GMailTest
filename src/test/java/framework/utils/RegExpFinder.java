package framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpFinder {

    public static String findByRegularExp(String string, String regExp){
        int groupNumber = 1;
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(string);
        matcher.find();
        return matcher.group(groupNumber);
    }
}