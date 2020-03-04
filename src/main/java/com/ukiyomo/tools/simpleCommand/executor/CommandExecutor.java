package com.ukiyomo.tools.simpleCommand.executor;

import com.ukiyomo.tools.simpleCommand.context.CommandContext;

public interface CommandExecutor {

    void init();

    void execute(String s, String[] params, CommandContext commandContext);

}
