package newpack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;


public class myclass extends JavaPlugin implements Listener{
    @Override
    public void onEnable(){
        //Fired when the server enables the plugin
        getServer().getPluginManager().registerEvents(new myclass(), (Plugin) this);
        System.out.println("Starting PlayerDrop");
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player dead = e.getEntity();
        if (dead.getKiller() instanceof Player){
            Player killer = dead.getKiller();
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwner(dead.getName());
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + dead.getName());
            skull.setItemMeta(meta);
            Random rand = new Random();
            int upperbound = 2;
            int randInt = rand.nextInt(upperbound);
            if(randInt == 1){
                killer.sendMessage("The skull of " + dead.getName() + "has been dropped!");
                World world = killer.getWorld();
                world.dropItem(killer.getLocation(), skull);
            }
        }
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(p.getName());
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
        skull.setItemMeta(meta);
        p.sendMessage("The skull of " + p.getName() + "has been dropped!");
        World world = p.getWorld();
        world.dropItem(p.getLocation(), skull);
        return false;
    }
    @Override
    public void onDisable(){
        //Fired when the server disables the plugin
        System.out.println("Shutting down PlayerDrop");
    }
}

