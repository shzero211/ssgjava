package com.ll.exam.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    private List<WiseSaying> wiseSayings;
    private int wiseSayingLastId;
    WiseSayingController(Scanner sc){
      this.sc=sc;
      wiseSayings=new ArrayList<>();
      wiseSayingLastId=0;

    }
    void modify(Rq rq) {
        int paramId=rq.getIntParam("id",0);
        if(paramId==0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWiseSaying=findById(paramId);
        if(foundWiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",paramId);
            return;
        }
        System.out.printf("명언(기존) : %s\n",foundWiseSaying.content);
        System.out.printf("명언 : ");
        foundWiseSaying.content=sc.nextLine();
        System.out.printf("작가(기존) : %s\n",foundWiseSaying.author);
        System.out.printf("작가 : ");
        foundWiseSaying.author=sc.nextLine();

        System.out.printf("%d번 명언이 수정되었습니다.\n",paramId);

    }

    void write(Rq rq) {
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();
        int id = ++wiseSayingLastId; // 명언 글 번호 증가

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }
    void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying_ = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }
    void remove(Rq rq) {
        int paramId=rq.getIntParam("id",0);
        if(paramId==0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundwiseSaying=null;
        for(WiseSaying wiseSaying: wiseSayings){
            if(wiseSaying.id==paramId){
                foundwiseSaying=wiseSaying;
            }
        }
        if(foundwiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다..\n",paramId);
            return;
        }
        wiseSayings.remove(foundwiseSaying);
        System.out.printf("%d 번 명언이 삭제되었습니다.\n",paramId);
    }
    private WiseSaying findById(int paramId){
        for(WiseSaying wiseSaying:wiseSayings){
            if(wiseSaying.id==paramId){
                return wiseSaying;
            }
        }
        return null;
    }

}
