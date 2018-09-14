package day10.redis.Stringdemo;

import java.io.Serializable;

class PorduceInfo implements Serializable {
   //商品名称
   private String name;
   //商品描述
   private String procuctDesc;
   //商品价格：
   private Double Price;

   public String getName() {
       return name;
   }

   public String getProcuctDesc() {
       return procuctDesc;
   }

   public Double getPrice() {
       return Price;
   }

   public void setName(String name) {
       this.name = name;
   }

   public void setProcuctDesc(String procuctDesc) {
       this.procuctDesc = procuctDesc;
   }

   public void setPrice(Double price) {
       Price = price;
   }

    @Override
    public String toString() {
        return "PorduceInfo{" +
                "name='" + name + '\'' +
                ", procuctDesc='" + procuctDesc + '\'' +
                ", Price=" + Price +
                '}';
    }
}
