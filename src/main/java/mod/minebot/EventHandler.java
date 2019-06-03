package mod.minebot;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
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
    public void playerConnecting(PlayerEvent.PlayerLoggedOutEvent event){
		String playername = event.player.getName();
		String message = (playername+" has disconnected from the server.");
		//Discordbot sendet String
	}
	
	//Player Verbindungstrennungs Event
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
	}
		
	//Player Verbindungstrennungs Event
		@SubscribeEvent
		public void lightning(EntityStruckByLightningEvent event){
			
			String message = ("");
			//Discordbot sendet String
		}
}
