package com.flickrapitest.utils;

/**
 * Created by roman on 1/21/2015.
 */
public class TagsUtils {
    public static String convertToTags(String query){
        String[] tags = query.replaceAll(",", " ").split(" ");

        StringBuilder result = new StringBuilder();
        for(int i=0; i<tags.length; i++){
            result.append(tags[i].trim());
            if(i<tags.length-2){
                result.append(",");
            }
        }
        return result.toString();
    }
}
