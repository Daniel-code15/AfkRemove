package me.daniel.removefromtab; 

import com.earth2me.essentials.Essentials; 
import org.bukkit.Bukkit; 
import org.bukkit.plugin.java.JavaPlugin; 
import org.bukkit.entity.Player; 
import org.bukkit.event.Listener; 
import org.bukkit.permissions.Permission;

public class Main extends JavaPlugin implements Listener {
    public static Main plugin;
    static Essentials ess = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials"); //Variavel ess
    @Override
    public void onEnable() { 
        plugin = this;
        getServer().getPluginManager().registerEvents(this, this); 
        loop(this); 
        Bukkit.getConsoleSender().sendMessage("§e--------------------------------- ");
        Bukkit.getConsoleSender().sendMessage("§e Removendo Jogadores AFK do TAB ");
        Bukkit.getConsoleSender().sendMessage("§e--------------------------------- ");
        
    }
    @Override
    public void onDisable() { 
        Bukkit.getConsoleSender().sendMessage("§e--------------------------------- ");
        Bukkit.getConsoleSender().sendMessage("§e Revelando Jogadores AFK do TAB ");
        Bukkit.getConsoleSender().sendMessage("§e--------------------------------- ");
    }

    
static void loop(Main main) { 
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(ess.getUser(p).isAfk()) {
                        
                        for(Player allp : Bukkit.getOnlinePlayers()) {
                            if(p.hasPermission("afkremove.staff")) {
                                p.sendTitle("§b§lVocê Está AFK!", "§e§lMas como é staff, Você continua em comandos InGame");
                                return;
                            }
                            p.sendTitle("§b§lVocê Está AFK!", "§e§lSeu Personagem Foi Escondido de Comandos InGame");
                            allp.hidePlayer(p);
                            if(allp.hasPermission("afkremove.show")) {
                                allp.showPlayer(p);
                            }
                        }
                    } else {
                        for(Player allp : Bukkit.getOnlinePlayers()) {
                            if(p.hasPermission("afkremove.staff")) {
                                return;
                            }
                            allp.showPlayer(p);
                        }
                    }
                    //
                    //  p = Alvo
                    //  allp = Jogadores
                    //
                }
                loop(main);
            }
        }, 100L);
    }
}
    

