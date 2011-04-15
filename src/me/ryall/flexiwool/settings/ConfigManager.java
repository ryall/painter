package me.ryall.flexiwool.settings;

// Local
import org.bukkit.util.config.Configuration;

// Bukkit
import me.ryall.flexiwool.Flexiwool;

public class ConfigManager
{
    private Configuration config;
    
    public void load() 
    {
        config = Flexiwool.get().getConfiguration();
        config.load();
    }
    
    public boolean shouldConsumeDye()
    {
        return config.getBoolean("Dye.Consume", true);
    }
    
    public boolean isEconomyEnabled()
    {
        return config.getBoolean("Economy.Enabled", false) && Flexiwool.get().getEconomy().getInterface() != null;
    }
    
    public String getEconomyAdapter()
    {
        return config.getString("Economy.Adapter", "");
    }
    
    public double getEconomyDyeCost()
    {
        return config.getDouble("Economy.DyeCost", 1);
    }
    
    public int getMaxFill()
    {
        return config.getInt("Fill.Max", 100);
    }
    
    public boolean shouldFillChargePerBlock()
    {
        return config.getBoolean("Fill.ChargePerBlock", true);
    }
    
    public boolean shouldFillConsumePerBlock()
    {
        return config.getBoolean("Fill.ConsumePerBlock", false);
    }
}
