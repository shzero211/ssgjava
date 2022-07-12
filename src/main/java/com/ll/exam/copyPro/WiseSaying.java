package com.ll.exam.copyPro;

class WiseSaying {
    int id;
    String content;
    String author;
    WiseSaying(int id,String content,String author){
        this.id=id;
        this.content=content;
        this.author=author;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"id\" : " + id + ",\n" +
                "\"name\" : " + "\"" + content + "\",\n" +
                "\"content\" : " + "\"" + author+ "\"\n" +
                "}";
    }
}
