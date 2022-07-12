package com.ll.exam.copyPro;

import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AppCopy {
    private Scanner sc;
    private ArrayList<WiseSaying> wiseSayings;
    private int wiseSayingLastId;

    private BufferedReader br;
    private BufferedWriter bw;
    public AppCopy() throws IOException {
        sc=new Scanner(System.in);
        wiseSayings=new ArrayList<>();

        br=new BufferedReader(new FileReader("C:\\Users\\KIM\\IdeaProjects\\ssgjava\\jsonFile.json"));
        wiseSayingLastId=0;
    }
    public void run() throws IOException {
        System.out.println("==명언 SSG==");
        load();
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

    private void load() throws IOException {
        WiseSaying wiseSaying =new WiseSaying();
        String str="";
        while((str=br.readLine())!=null){
          if(str.contains(":")){
              String[] cmdBits=str.split("\\:",2);
              if(cmdBits[0].contains("id")){
                wiseSaying.id=Integer.parseInt(cmdBits[1].replace(",","").trim());
              }
             else if(cmdBits[0].contains("name")){
                  wiseSaying.author=cmdBits[1].replace(",","").replace("\"","").trim();
              }
             else if(cmdBits[0].contains("content")){
                  wiseSaying.content=cmdBits[1].replace(",","").replace("\"","").trim();
              }

          }
            if(str.contains("}")){
                System.out.println(wiseSaying.toString());
                wiseSayings.add(wiseSaying);
                wiseSaying=new WiseSaying();
                wiseSayingLastId++;
            }
        }
    }

    private void save() throws IOException {
        bw=new BufferedWriter(new FileWriter("C:\\Users\\KIM\\IdeaProjects\\ssgjava\\jsonFile.json"));
        for(WiseSaying wiseSaying:wiseSayings){
           if(wiseSaying.id==1){
               bw.write("[\n"+wiseSaying.toString());
           }else if(wiseSaying.id>1&&wiseSaying.id<wiseSayings.size()){
               bw.write(",\n"+wiseSaying.toString());
           }
           if(wiseSaying.id==wiseSayings.size()){
               if(wiseSayings.size()==1){
                   bw.write("\n]");
               }else{
                   bw.write(",\n"+wiseSaying.toString()+"\n]");
               }

           }


        }
        bw.flush();
        bw.close();
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
