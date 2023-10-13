package fr.yanissou.customwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        initFile();
        getCommand("cwhitelist").setExecutor(new CustomWhitelist(this));
        System.out.println(("Aliases: " + Bukkit.getServer().getPluginCommand("cwhitelist").getAliases()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initFile(){
        File fichier = new File(getDataFolder(), "customwhitelist.txt");
        File dataFolder = getDataFolder();

        // Vérifiez si le répertoire du plugin n'existe pas
        if (!dataFolder.exists()) {
            // Créez le répertoire du plugin s'il n'existe pas
            dataFolder.mkdir();
        }

        if (!fichier.exists()) {
            // S'il n'existe pas, créer le fichier et l'initialiser avec du texte par défaut
            try {
                fichier.createNewFile();
                FileWriter writer = new FileWriter(fichier);
                writer.write("Pseudo1 Pseudo2 DiscowZombie LaptorJojo");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
