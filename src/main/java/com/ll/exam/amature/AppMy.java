package com.ll.exam.amature;

import com.ll.exam.amature.WriterProfile;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AppMy {

    public void run() throws IOException {
        System.out.println("-----명언 제조기-----");
        Scanner sc=new Scanner(System.in);
        String filePath="C:\\Users\\KIM\\IdeaProjects\\ssgjava\\jsonFile.json";
        int cnt=1;

        outer:
        while(true){
            System.out.printf("명령)");
            String cmd=sc.nextLine().trim();

            switch (cmd){
                case "등록":
                    WriterProfile w;
                    String content="";
                    String name="";
                    System.out.printf("명언 : ");
                    content=sc.nextLine().trim();
                    System.out.printf("작가 : ");
                    name=sc.nextLine().trim();
                    w=new WriterProfile(cnt++,content,name);
                    JSONObject obj=new JSONObject();
                    obj.put("id",w.id);
                    obj.put("content",w.content);
                    obj.put("name",w.name);
                    try {
                        FileWriter file =new FileWriter(filePath);
                        file.write(obj.toJSONString());
                        file.flush();
                        file.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(obj);
                    break;
                case "종료":
                    break outer;
            }
        }
        sc.close();
    }
}
