package net.nitrogen.feature.commands;

import net.nitrogen.config.template.Command;
import net.nitrogen.utils.ChatUtils;

public class TestCommand extends Command {
    public TestCommand(){
        super("test", "test command");
    }

    @Override
    public void call(String[] args){
        ChatUtils.message("Hello world");
    }
}
