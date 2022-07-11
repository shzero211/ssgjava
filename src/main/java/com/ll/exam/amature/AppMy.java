package com.ll.exam.amature;

import com.ll.exam.amature.WriterProfile;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class AppMy {

    public void run() throws IOException {
        System.out.println("-----명언 제조기-----");
        String filePath="C:\\Users\\KIM\\IdeaProjects\\ssgjava\\jsonFile.json";
        File file=new File(filePath);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader brfile=new BufferedReader(new FileReader(filePath));
        BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
        int idx=0;
        String content="";
        String name="";
        ArrayList<WriterProfile> profiles=new ArrayList<>();
        String temp="";
        int cnt=0;
        //목록을 위한 리스트에 파일 내용 먼저 넣기 및 id 정해주기
        while((temp=brfile.readLine())!=null){
            String[] temps;
            if(temp.contains(" : ")){
                temps=temp.split(" \\: ");
            }else {
                continue;
            }
            temps[1]=temps[1].replace("\"","");
            temps[1]=temps[1].replace(",","");
            System.out.println( temps[1]);
        }


        outer:
        while(true){
            System.out.printf("명령)");
            String cmd=br.readLine().trim();

            switch (cmd){
                case "등록":
                    WriterProfile w;
                    content="";
                    name="";
                    System.out.printf("명언 : ");
                    content=br.readLine().trim();
                    System.out.printf("작가 : ");
                    name=br.readLine().trim();
                    w=new WriterProfile(cnt++,name,content);
                    profiles.add(w);
                    bw.write(w.toString());
                    bw.flush();
                    System.out.println(cnt+" 번 명언이 등록 되었습니다.");
                    break;
                case "목록":
                    break;
                case "삭제":
                    break;
                case "수정":
                    break;
                case "종료":
                    break outer;
            }
        }
    }
}
