package net.nitrogen.feature.commands;

import net.nitrogen.config.Modules;
import net.nitrogen.config.template.Command;
import net.nitrogen.utils.ChatUtils;
import net.nitrogen.config.template.Module;

public class ToggleCommand extends Command {
    public ToggleCommand(){
        super("toggle", "toggle a module");
    }

    @Override
    public void call(String[] args){
        Module selected = Modules.find(args[0]);
        if(selected != null) selected.toggle();
    }
}
