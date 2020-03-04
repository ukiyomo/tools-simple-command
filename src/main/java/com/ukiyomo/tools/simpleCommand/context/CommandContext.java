package com.ukiyomo.tools.simpleCommand.context;

public abstract class CommandContext {

    private String label;

    public CommandContext(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
