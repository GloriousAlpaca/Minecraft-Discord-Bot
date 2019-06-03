package mod.minebot;

import mod.minebot.discord.SendMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@EventBusSubscriber
public class EventHandler {
	
	//Player Verbindungs Event
	@SubscribeEvent
    public void playerConnecting(PlayerEvent.PlayerLoggedInEvent event){
		String playername = event.player.getName();
		String message = (playername+" is connecting to the server.");
		//Discordbot sendet String
	}
	
	//Player Verbindungstrennungs Event
	@SubscribeEvent
    public void playerDisconnecting(PlayerEvent.PlayerLoggedOutEvent event){
		String playername = event.player.getName();
		String message = (playername+" has disconnected from the server.");
		//Discordbot sendet String
	}
	
	//Dimensionswechsel Event
	@SubscribeEvent
	public void changeDimension(PlayerEvent.PlayerChangedDimensionEvent event){
		String playername = event.player.getName();
		DimensionType dimension = DimensionManager.getProviderType(event.toDim);
		String message = (playername+" has traveled to "+dimension.getName());
		//Discordbot sendet String
	}
	
	//Chatmessage Event
	@SubscribeEvent
	public void chatMessage(ServerChatEvent event){
		String username = event.getUsername();
		String chat = event.getMessage();
		String message = ("Message by "+username+": "+chat);
		//Discordbot sendet String
		MINEBOT.LOG.info(message);
		SendMessage.sendMessage(message);
	}
		
	//Blitzschlag Event
	@SubscribeEvent
	public void lightning(EntityStruckByLightningEvent event){
		String name = event.getEntity().getName();
		String message = (name + "has been struck by lightning.");
		//Discordbot sendet String
	}
		
	//Entity Tod Event
	@SubscribeEvent
	public void death(LivingDeathEvent event){
		Entity entity = event.getEntity();
		EntityLivingBase live = event.getEntityLiving();
		if(entity instanceof EntityLivingBase) {
		String deathmessage = event.getSource().getDeathMessage((EntityLivingBase) entity).getUnformattedText();}
		String message = ("");
		//Discordbot sendet String
	}
		
	//Item Zerstörung-Event
	@SubscribeEvent
	public void destroyEvent(PlayerDestroyItemEvent event){
		ItemStack stack = event.getOriginal();
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt != null)
        {
            if (nbt.hasKey("Name", 8))
            {
                 String name = nbt.getString("Name");
                 String message = event.getEntityPlayer().getName()+" has destroyed his "+name;
               //Discordbot sendet String
            }
        }
	}
	
	//Weltlade Event
	@SubscribeEvent
	public void worldLoad(WorldEvent.Load event){
		String message = ("The World is being loaded!");
		//Discordbot sendet String
	}
	
	//Weltlade Event
		@SubscribeEvent
		public void worldunload(WorldEvent.Unload event){
			String message = ("Server shutting down");
			//Discordbot sendet String
			SendMessage.sendMessage(message);
		}
		
	@SubscribeEvent
	public static void onAdvancementEvent(AdvancementEvent event)
	{
		if (event.getAdvancement().getDisplay() != null && event.getAdvancement().getDisplay().shouldAnnounceToChat())
		{
			MINEBOT.LOG.info("{} got the {} advancement", event.getEntityPlayer().getDisplayNameString(), event.getAdvancement().getDisplayText().getUnformattedText());
		}
	}
	//Schlafengehen Event
    @SubscribeEvent
    public static void onPlayerSleep(PlayerSleepInBedEvent event){
        System.out.println("Event wird gecalled");
        String playername = event.getEntity().getName();
        String message = (playername+" is connecting to the server.");
        SendMessage.sendMessage(message);
        //Discordbot sendet String

    }
}
