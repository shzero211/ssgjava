package com.ll.exam.copyPro;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String path;
    Map<String,String> queryStrings;
    Rq(String cmd){
        String[] cmdBits=cmd.split("\\?",2);
        this.path=cmdBits[0];
        queryStrings=new HashMap<>();

        if(cmdBits.length==2){
            String [] querys=cmdBits[1].split("&");
            for(String query:querys) {
                String[] paramNameAndValue= query.split("=", 2);
                if(paramNameAndValue.length==1){
                    continue;
                }
                queryStrings.put(paramNameAndValue[0],paramNameAndValue[1]);
            }
        }
    }
    public int getIntParam(String paramName,int defaultValue){
        if(queryStrings.containsKey(paramName)==false){
            return defaultValue;
        }
        String paramValue=queryStrings.get(paramName);
        if(paramValue.length()==0){
            return defaultValue;
        }
        return Integer.parseInt(paramValue);
    }

    String getPath(){
        return path;
    }
}
