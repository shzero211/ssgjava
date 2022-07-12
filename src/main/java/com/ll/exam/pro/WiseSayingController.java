package com.ll.exam.pro;

import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    private WiseSayingRepository wiseSayingRepository;
    WiseSayingController(Scanner sc){
      this.sc=sc;
        wiseSayingRepository=new WiseSayingRepository();
    }
    void modify(Rq rq) {
        int paramId=rq.getIntParam("id",0);
        if(paramId==0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWiseSaying=wiseSayingRepository.findById(paramId);
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
        int id = ++wiseSayingRepository.wiseSayingLastId; // 명언 글 번호 증가

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
       wiseSayingRepository.wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }
    void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSayingRepository.wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying_ = wiseSayingRepository.wiseSayings.get(i);
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
        for(WiseSaying wiseSaying: wiseSayingRepository.wiseSayings){
            if(wiseSaying.id==paramId){
                foundwiseSaying=wiseSaying;
            }
        }
        if(foundwiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다..\n",paramId);
            return;
        }
        wiseSayingRepository.wiseSayings.remove(foundwiseSaying);
        System.out.printf("%d 번 명언이 삭제되었습니다.\n",paramId);
    }
    private WiseSaying findById(int paramId){
        for(WiseSaying wiseSaying: wiseSayingRepository.wiseSayings){
            if(wiseSaying.id==paramId){
                return wiseSaying;
            }
        }
        return null;
    }

}
