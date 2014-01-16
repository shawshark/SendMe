package me.shawshark.sendme;

import lilypad.client.connect.api.request.RequestException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LilySendCommand implements CommandExecutor {
	
	public main m;
	
	public LilySendCommand(main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
		} else {
			Player p = (Player) sender;
				 if(args.length < 1) {
					if(!p.hasPermission("sendme.lilysend")) {
						p.sendMessage(ChatColor.RED + "Error: You don't have permissions for this command.");
					} else {
						p.sendMessage(ChatColor.GOLD + "Correct Usage: " + ChatColor.RED + "/lilysend [player] [servername]");
					}
				 } else {
				 try {
					 if(Bukkit.getPlayer(args[0]).isOnline()) {
						 m.s.sendplayer(args[0], args[1]); 
					 } else {
						 p.sendMessage(ChatColor.RED + "Error: " + args[0] + " Doesn't seem to be online!");
					 }
				} catch (RequestException e) {
					p.sendMessage(ChatColor.GOLD + "Error: A error occurred!");
					e.printStackTrace();
				}
			} 
		 }
		return false;
	}
}