package mod.minebot;

import mod.minebot.block.BlockInterface;
import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import mod.minebot.minecraftcommands.RegisterPlayer;
import mod.minebot.network.PacketHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.minebot.proxy.IProxy;
import mod.minebot.tileentity.TileEntityInterface;
import net.dv8tion.jda.core.EmbedBuilder;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = MINEBOT.MODID, name = MINEBOT.NAME, version = MINEBOT.VERSION, guiFactory = "mod.minebot.MinebotConfigGui")
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
    public static final CreativeTabs CT = new CreativeTabs("Minebot") {
    	
    	@Override
    	public ItemStack getTabIconItem() {
    		return new ItemStack(BlockInterface.itemdcinterface,1);
    	}
    };
    
    /*Capabilities*/
    
    /*Initializers*/
    @Mod.Instance(MODID)
    public static MINEBOT instance;
    
    /*Configuration*/
    public static Configuration config;
    
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
    	LOG.info(NAME + " pre-initialization");
        config = new Configuration(event.getSuggestedConfigurationFile());
        ReferenceClass.defaultconfig=event.getSuggestedConfigurationFile().getPath();
        if(MinebotConfig.discord.bot)
    	proxy.startBot();
    	EventHandler.register();
    	PacketHandler.registerMessages(MODID);
	}
	
	@Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new RegisterPlayer());
        EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(3381759);
		builder.setDescription("Server is starting...");
        SendMessage.sendMessage(builder.build());
    }
	
	@Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event){
        EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(3381759);
		builder.setDescription("Server is online!");
        SendMessage.sendMessage(builder.build());
    }
    
	@Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event){
        EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(3381759);
		builder.setDescription("Server is stopping...");
        SendMessage.sendMessage(builder.build());
        ReferenceClass.main.shutdown();
    }
	
    @Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOG.info(NAME + " is done!");
		//SendMessage.sendTestMessage();

	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			BlockInterface dcinterface = new BlockInterface();
			event.getRegistry().registerAll(
						dcinterface
					);
			GameRegistry.registerTileEntity(TileEntityInterface.class, new ResourceLocation(MINEBOT.MODID, "tileentitydcinterface"));
		}
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(
					BlockInterface.dcinterface.createItemBlock()
			);
		}
		
		@SubscribeEvent
		public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
			
		}
		
		@SubscribeEvent
		public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
			
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			MINEBOT.proxy.registerItemRenderer(BlockInterface.itemdcinterface, 0, "dcinterface");
		}
	}
	
}
