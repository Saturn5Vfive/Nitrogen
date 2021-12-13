package net.nitrogen.config.template;

public class Command {
    String name;
    String description;

    public Command(String name, String de){
        this.name = name;
        this.description = de;
    }

    public void call(String[] args){
    }

    public String name(){
        return this.name;
    }

    public String getDesc(){
        return this.description;
    }
}