package mod.minebot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.minebot.proxy.IProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = MINEBOT.MODID, name = MINEBOT.NAME, version = MINEBOT.VERSION)
public class MINEBOT {
	
	/*Formalitäten*/
	public static final String MODID = "minebot";
    public static final String NAME = "Minebot";
    public static final String DESCRIPTION = "A Minecraft Discord Bot";
    public static final String AUTHOR = "SaltStation and GloriousAlpaca";
    public static final String VERSION = "1.0";
    public static final Logger LOG = LogManager.getLogger(MODID);
    
    /*Proxy*/
    @SidedProxy(serverSide = "mod.leer.proxy.ServerProxy", clientSide = "mod.leer.proxy.ClientProxy")
    public static IProxy proxy;
    
    /*Creative Tab*/
    public static final CreativeTabs CT = new CreativeTabs("LEER") {
    	
    	@Override
    	public ItemStack getTabIconItem() {
    		return ItemStack.EMPTY;
    	}
    };
    
    /*Capabilities*/
    
    /*Initializers*/
    @Mod.Instance(MODID)
    public static MINEBOT instance;
}
