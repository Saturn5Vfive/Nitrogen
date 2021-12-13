package net.nitrogen.config;

public enum Type {
    COMBAT("Combat"),
    CHAT("Chat"),
    OTHER("Other"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    EXPLOIT("Exploit");

    String name;

    Type(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }
}
