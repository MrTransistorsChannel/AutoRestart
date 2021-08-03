package ru.tulavcube.autoRestart.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import ru.tulavcube.autoRestart.autoRestart;

import java.util.ArrayList;
import java.util.List;

public class autorestart implements TabExecutor {

    autoRestart plugin;
    public autorestart(autoRestart plg){
        plugin = plg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player && !sender.isOp()){
            sender.sendMessage(ChatColor.RED+"You need to be an operator to run this command");
        }
        else{
            if(args.length == 2){
                try {
                    Integer.parseInt(args[1]);
                }
                catch (NumberFormatException e){
                    return false;
                }
                if(args[0].equalsIgnoreCase("threshold")){
                    plugin.getConfig().set("threshold", Integer.parseInt(args[1]));
                    plugin.saveConfig();
                    plugin.updateSettings();
                    sender.sendMessage(ChatColor.GREEN+ "New threshold is " + args[1]);
                }
                else if(args[0].equalsIgnoreCase("timeout")){
                    plugin.getConfig().set("timeout", Integer.parseInt(args[1]));
                    plugin.saveConfig();
                    plugin.updateSettings();
                    sender.sendMessage(ChatColor.GREEN+ "New timeout is " + args[1]);
                }
                //testtest
            }
            else return false;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player && !sender.isOp()) {
            return new ArrayList<>();
        }
        else {
            ArrayList<String> arr = new ArrayList<String>();

            if (args.length == 1) {
                arr.add("threshold");
                arr.add("timeout");
            }
            return arr;
        }
    }
}
