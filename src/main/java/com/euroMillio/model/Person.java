package com.euroMillio.model;

public class Person {
    //insert into Person (Name,age,location) values ("Idola Imbo",56,"Abuja");
    String name;
    int age;
    String location;
    Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString(){
        return name +" "+ age +" "+ location;
    }



}
