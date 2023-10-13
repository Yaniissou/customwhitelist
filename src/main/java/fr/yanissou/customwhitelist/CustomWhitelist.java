package fr.yanissou.customwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class CustomWhitelist implements CommandExecutor {

    private Main main;
    public CustomWhitelist(Main main){
        this.main = main;
    }
    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage(Trads.USAGE.message());
            return true;
        }
//PASSED
        switch (args[0].toLowerCase(Locale.ROOT)){
            case "add":

                if (args.length == 1){
                    sender.sendMessage(Trads.PREFIX.message() + "§cSpécifiez un pseudo");
                    return true;
                }
                for (int i = 1; i < args.length; i++) {
                        addPlayer(sender,args[i]);
                    }
                sender.sendMessage(Trads.PREFIX.message() + "§aAjout effectué avec succès !");
                sender.sendMessage("§7§oNOTE : Les pseudos inexistants n'ont pas pu être ajoutés");
                return true;

            case "remove":
                for (int i = 1; i < args.length; i++) {
                    Bukkit.getServer().dispatchCommand(sender,"whitelist remove " + args[i]);
                }
                sender.sendMessage(Trads.PREFIX.message() + "§aRetrait des pseudos de la whitelist effectué avec succès !");
                return true;

            case "clear":
                clear(sender);
                sender.sendMessage(Trads.PREFIX.message() + "§aLa whitelist a été vidée avec succès !");
                return true;

            case "list":

                StringBuilder builder = new StringBuilder();
                builder.append("§7▎ §a§lListe des joueurs whitelistés §7(").append(Bukkit.getServer().getWhitelistedPlayers().size()).append(")\n");
                Bukkit.getServer().getWhitelistedPlayers().forEach(offlinePlayer -> builder.append("§7- §e").append(offlinePlayer.getName()).append("\n"));
                sender.sendMessage(builder.toString());
                return true;
            case "read":
                try (BufferedReader reader = new BufferedReader(new FileReader(main.getDataFolder() + "/customwhitelist.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] usernames = line.split(" "); // Divise la ligne en mots séparés par un espace
                        for (String username : usernames) {
                            // Faites ce que vous voulez avec chaque mot
                            addPlayer(sender, username);
                        }
                    }
                    sender.sendMessage(Trads.PREFIX.message() + "§aAjout effectué avec succès !");
                    sender.sendMessage("§7§oNOTE : Les pseudos inexistants n'ont pas pu être ajoutés");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            default:
                sender.sendMessage(Trads.USAGE.message());
                return true;

        }
    }
    private void addPlayer(CommandSender sender, String username){
        Bukkit.getServer().dispatchCommand(sender,"whitelist add " + username);
    }

    private void clear(CommandSender sender){
        Bukkit.getServer().getWhitelistedPlayers().forEach(offlinePlayer ->
                Bukkit.getServer().dispatchCommand(sender,"whitelist remove " + offlinePlayer.getName())
        );
    }
}
