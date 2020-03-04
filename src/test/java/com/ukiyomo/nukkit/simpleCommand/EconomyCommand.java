package com.ukiyomo.nukkit.simpleCommand;

import com.ukiyomo.tools.simpleCommand.context.CommandContext;
import com.ukiyomo.tools.simpleCommand.annotation.CommandMapping;
import com.ukiyomo.tools.simpleCommand.context.DefaultCommandContext;
import com.ukiyomo.tools.simpleCommand.executor.MethodCommandExecutor;

public class EconomyCommand extends MethodCommandExecutor {

    @CommandMapping("economy")
    public void economy(CommandContext ctx) {
        System.out.println("进入economy");
    }

    @CommandMapping("economy money")
    public void economyMoney(CommandContext ctx) {
        System.out.println("进入economy money");
    }

    @CommandMapping("economy money add")
    public void economyMoneyAddError() {
        System.out.println("指令参数个数不符合");
    }

    @CommandMapping("economy money add help")
    public void economyMoneyAddHelp(DefaultCommandContext ctx) {
        System.out.println(ctx.getLabel());
        System.out.println("help相关代码");
    }

    @CommandMapping("economy money add :economyTypeTag as :userId grant to :val")
    public void economyMoneyAdd(String economyTypeTag, String userId, Double val) {
        System.out.println(economyTypeTag);
        System.out.println(userId);
        System.out.println(val);
    }




}
