package ru.tulavcube.autoRestart.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class mem implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player && !sender.isOp()){
            sender.sendMessage(ChatColor.RED+"You need to be an operator to run this command");
        }
        else{
            sender.sendMessage(Long.toString((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1048576));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return new ArrayList<>();
    }
}
