package lootdrops;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootDrops extends JavaPlugin implements Listener {

    private final List<Material> possibleDrops = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        populateLootTable();
        getLogger().info("LootDrops alustatud!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LootDrops stopitud.");
    }

    private void populateLootTable() {
        for (Material material : Material.values()) {
            if (material.isItem()) {
                possibleDrops.add(material);
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.ZOMBIE ||
                event.getEntity().getType() == EntityType.SKELETON ||
                event.getEntity().getType() == EntityType.CREEPER) {

            event.getDrops().clear();

            Random random = new Random();
            int index = random.nextInt(possibleDrops.size());
            Material randomMaterial = possibleDrops.get(index);

            event.getDrops().add(new ItemStack(randomMaterial, 1));
        }
    }
}
