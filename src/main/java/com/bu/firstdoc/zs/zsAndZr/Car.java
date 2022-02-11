package com.bu.firstdoc.zs.zsAndZr;

/**
 * @author haizhuangbu
 * @date 9:57 上午 2022/2/11
 * @mark Car
 */
public abstract class Car {

    public Car car;

    /**
     * 装载配件
     */
   abstract public String loadAcce();

    void addCar(Car car){
        this.car = car;
    }

  public   static class Builder{

        private Car head;

        private Car tail;

        public Builder addCar(Car car){

            if (head == null){
                this.head = this.tail = car;
                return this;
            }

            this.tail.addCar(car);
            this.tail = car;
            return this;
        }

        public Car build(){
            return this.head;
        }

    }


}
