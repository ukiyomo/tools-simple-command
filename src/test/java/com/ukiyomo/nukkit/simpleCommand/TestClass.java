package com.ukiyomo.nukkit.simpleCommand;


import com.ukiyomo.tools.simpleCommand.context.DefaultCommandContext;

public class TestClass {

    public static void main(String[] args) {
        EconomyCommand economyCommand = new EconomyCommand();
        economyCommand.init();
        economyCommand.execute("economy", new String[]{"money", "add", "help"}, new DefaultCommandContext("economy"));
    }

}
