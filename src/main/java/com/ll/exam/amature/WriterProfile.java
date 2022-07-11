package com.ll.exam.amature;

public class WriterProfile {
    WriterProfile(int id,String name,String content){
        this.id=id;
        this.content=content;
        this.name=name;
    }
        int id;
        String content;
        String name;

    @Override
    public String toString() {
        return "{\n" +
                "\"id\" : " + id + ",\n" +
                "\"name\" : " + "\"" + name + "\",\n" +
                "\"content\" : " + "\"" + content + "\"\n" +
                "}";
    }
}
