package com.ll.exam.pro;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
     private List<WiseSaying> wiseSayings;
   private  int wiseSayingLastId;
    WiseSayingRepository(){
        wiseSayings=new ArrayList<>();
        wiseSayingLastId=0;
    }
    public WiseSaying findById(int paramId){
        for(WiseSaying wiseSaying:wiseSayings){
            if(wiseSaying.id==paramId){
                return wiseSaying;
            }
        }
        return null;
    }

    public void modify(int paramId, String content, String author) {
        WiseSaying foundwiseSaying=findById(paramId);
        foundwiseSaying.content=content;
        foundwiseSaying.author=author;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    public WiseSaying write(String content, String author) {
        int id=++wiseSayingLastId;
        WiseSaying wiseSaying=new WiseSaying(id,content,author)
;
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    public void remove(int paramId) {
        WiseSaying foundWiseSaying=findById(paramId);
        wiseSayings.remove(foundWiseSaying);
    }
}
