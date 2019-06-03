package mod.minebot;

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
			
		String message = (playername+" has traveled to ");
		//Discordbot sendet String
	}
}
