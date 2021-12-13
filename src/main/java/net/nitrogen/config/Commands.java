package net.nitrogen.config;

import java.util.ArrayList;
import java.util.List;

import net.nitrogen.feature.commands.*;
import net.nitrogen.config.template.Command;
import net.nitrogen.utils.ChatUtils;

public class Commands {
    private static final List<Command> cmds = new ArrayList<>();

    static {
        cmds.add(new TestCommand());
        cmds.add(new ToggleCommand());
    }

    public static List<Command> getCommands(){
        return cmds;
    }


    public static Command get(String name){
        for(Command c : cmds){
            if(c.name().toLowerCase().equals(name)){
                return c;
            }
        }
        return null;
    }
}
