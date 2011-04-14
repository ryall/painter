package me.ryall.flexiwool;

// Java
import java.util.logging.Logger;

// Bukkit
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

// Local
import me.ryall.flexiwool.communication.CommunicationManager;
import me.ryall.flexiwool.economy.EconomyManager;
import me.ryall.flexiwool.listeners.FlexiwoolPlayerListener;
import me.ryall.flexiwool.listeners.FlexiwoolPluginListener;
import me.ryall.flexiwool.settings.ConfigManager;
import me.ryall.flexiwool.settings.PermissionManager;

// TODO:
// Selection tool to change the colour of an entire wall or area.
// Allow "fill" with right-click, "change" with left click.
// Allow undoing of X number of previous changes (including refund).
// Allow white-listed blocks to be changed to coloured wool (with independent prices).

public class Flexiwool extends JavaPlugin
{
    public static String PLUGIN_NAME = "Flexiwool";
    public static String PLUGIN_VERSION = "1.0.0";
    public static String LOG_HEADER = "[" + PLUGIN_NAME + "] ";
    private static Flexiwool instance = null;
    
    private Logger log;
    private FlexiwoolPluginListener pluginListener;
    private FlexiwoolPlayerListener playerListener;
    private ConfigManager configManager;
    private PermissionManager permissionManager;
    private EconomyManager economyManager;
    private CommunicationManager communicationManager;
    
    public static Flexiwool get()
    {
        return instance;
    }
    
    public void onEnable()
    {
        instance = this;
        log = Logger.getLogger("Minecraft");
        pluginListener = new FlexiwoolPluginListener();
        playerListener = new FlexiwoolPlayerListener();
        
        configManager = new ConfigManager();
        permissionManager = new PermissionManager();
        economyManager = new EconomyManager();
        communicationManager = new CommunicationManager();
        
        registerEvents();
        
        getConfig().load();
        
        logInfo("Started: v" + PLUGIN_VERSION);
    }

    public void onDisable()
    {
        logInfo("Stopped");
    }
    
    public void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLUGIN_ENABLE, pluginListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
    }
    
    public ConfigManager getConfig()
    {
        return configManager;
    }
    
    public PermissionManager getPermissions()
    {
        return permissionManager;
    }
    
    public EconomyManager getEconomy()
    {
        return economyManager;
    }
    
    public CommunicationManager getComms()
    {
        return communicationManager;
    }
    
    public void logInfo(String _message)
    {
        log.info(LOG_HEADER + _message);
    }
    
    public void logError(String _message)
    {
        log.severe(LOG_HEADER + _message);
    }
}