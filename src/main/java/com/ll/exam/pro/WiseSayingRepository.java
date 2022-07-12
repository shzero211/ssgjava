package com.ll.exam.pro;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
     List<WiseSaying> wiseSayings;
    int wiseSayingLastId;
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

}
