package com.ll.exam.copyPro;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class AppCopy {
    private Scanner sc;
    private ArrayList<WiseSaying> wiseSayings;
    private int wiseSayingLastId;
    public AppCopy(){
        sc=new Scanner(System.in);
        wiseSayings=new ArrayList<>();
        wiseSayingLastId=0;
    }
    public void run() {
        System.out.println("==명언 SSG==");
        out:
        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);
            switch (rq.getPath()) {
                case "등록":
                    write(rq);
                    break;
                case "삭제":
                    remove(rq);
                    break;
                case "수정":
                    modify(rq);
                    break;
                case "목록":
                    list(rq);
                    break;
                case "종료":
                    save();
                    break out;
                default:
                    break;

            }
        }
    }

    private void save() {
        for(WiseSaying wiseSaying:wiseSayings){
            System.out.println(wiseSaying.toString()+",");
        }
    }

    private void write(Rq rq) {
        System.out.printf("명언 : ");
        String content=sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author=sc.nextLine().trim();
        WiseSaying wiseSaying=new WiseSaying(++wiseSayingLastId,content,author);
        wiseSayings.add(wiseSaying);
        System.out.printf("%d번 명언이 등록되었습니다.\n",wiseSayingLastId);
    }
    private void list(Rq rq) {
        System.out.println("-----------------------");
        for(int i=wiseSayings.size()-1;i>=0;i--){
            WiseSaying wiseSaying=wiseSayings.get(i);
            System.out.printf("%d/%s/%s\n",wiseSaying.id,wiseSaying.content,wiseSaying.author);
        }
        System.out.println("-----------------------");
    }

    private void remove(Rq rq) {
        int urlid=rq.getIntParam("id",0);
        if(urlid==0){
            System.out.println("id 값을 입력하세요");
            return ;
        }
        WiseSaying findWiseSaying=null;
        for(WiseSaying wiseSaying:wiseSayings){
            if(wiseSaying.id==urlid){
                findWiseSaying=wiseSaying;
            }
        }
        if(findWiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다..\n",urlid);
            return;
        }
        wiseSayings.remove(findWiseSaying);
        System.out.printf("%d 번 명언이 삭제되었습니다.\n",urlid);
    }
    private void modify(Rq rq){
        int urlid=rq.getIntParam("id",0);
        if(urlid==0){
            System.out.println("id 값을 입력하세요");
            return ;
        }
        WiseSaying findWiseSaying=null;
        for(WiseSaying wiseSaying:wiseSayings){
            if(wiseSaying.id==urlid){
                findWiseSaying=wiseSaying;
            }
        }
        if(findWiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다..\n",urlid);
            return;
        }
        WiseSaying wiseSaying=wiseSayings.get(findWiseSaying.id-1);
        System.out.println("기존 명언 : "+wiseSaying.content);
        System.out.printf("새 명언 : ");
       wiseSaying.content= sc.nextLine().trim();
        System.out.println("기존 작가 : "+wiseSaying.author);
        System.out.printf("새 작가 : ");
        wiseSaying.author= sc.nextLine().trim();
        wiseSayings.set(findWiseSaying.id-1,wiseSaying);
        return;
    }


}
