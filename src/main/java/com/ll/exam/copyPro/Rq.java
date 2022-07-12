package com.ll.exam.copyPro;

public class Rq {
    String path;
    String queryStr;
    Rq(String cmd){
        String[] cmdBits=cmd.split("\\?",2);
        this.path=cmdBits[0];
        if(cmdBits.length==2){
            this.queryStr=cmdBits[1];
        }
    }
    int getIntParam(String id,int defaultValue){
        if(queryStr==null){
            return defaultValue;
        }
        String[] querys= queryStr.split("&",2);
        for(String query:querys ){
           String[] strs= query.split("=",2);
          if(strs.length==1){
              return defaultValue;
          }
          if(strs[0].equals(id)){
              return Integer.parseInt(strs[1]);
          }
        }
        return 0;
    }
    String getPath(){
        return path;
    }
}
