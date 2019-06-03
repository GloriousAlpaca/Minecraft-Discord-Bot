package mod.minebot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.minebot.proxy.IProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

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
    @SidedProxy(serverSide = "mod.minebot.proxy.ServerProxy", clientSide = "mod.minebot.proxy.ClientProxy")
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
    
    @SuppressWarnings("deprecation")
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
    	LOG.info(NAME + " pre-initialization");
    	LOG.info("Starting Bot...");
    	proxy.startBot();
    	EventHandler handler = new EventHandler();
    	MinecraftForge.EVENT_BUS.register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
	}
    
    @Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOG.info(NAME + " is done!");
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			
		}
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			
		}
		
		@SubscribeEvent
		public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
			
		}
		
		@SubscribeEvent
		public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
			
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			
		}
	}
	
}
