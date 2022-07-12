package com.ll.exam.pro;

class WiseSaying {
   public int id;
   public  String content;
   public   String author;
   public WiseSaying(int id,String content,String author){
      this.id=id;
      this.content=content;
      this.author=author;
   }

   @Override
   public String toString() {
      return "WiseSaying{" +
              "id=" + id +
              ", content='" + content + '\'' +
              ", author='" + author + '\'' +
              '}';
   }
}
