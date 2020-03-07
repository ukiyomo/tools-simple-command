package com.ukiyomo.nukkit.simpleCommand;


import com.ukiyomo.tools.simpleCommand.context.DefaultCommandContext;

public class TestClass {

    public static void main(String[] args) {

        EconomyCommandExecutor economyCommandExecutor = new EconomyCommandExecutor();
        economyCommandExecutor.init();
        economyCommandExecutor.execute("economy", new String[]{"money", "add", "help"}, new DefaultCommandContext("economy"));
    }


}
