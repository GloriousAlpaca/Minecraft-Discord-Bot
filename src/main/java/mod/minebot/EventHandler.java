package mod.minebot;

import mod.minebot.discord.SendMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventHandler {
	
	public static void register() {
		EventHandler eventhandler = new EventHandler();
		if(MinebotConfig.events.connect) {
			ConnectionEvent handler = eventhandler.getConnectionEvent();
			MinecraftForge.EVENT_BUS.register(handler);
			FMLCommonHandler.instance().bus().register(handler);}
		if(MinebotConfig.events.disconnect) {
			DisconnectionEvent handler = eventhandler.getDisconnectionEvent();
			MinecraftForge.EVENT_BUS.register(handler);
			FMLCommonHandler.instance().bus().register(handler);}
		if(MinebotConfig.events.dimension){
			DimensionEvent handler = eventhandler.getDimensionEvent();
			MinecraftForge.EVENT_BUS.register(handler);
			FMLCommonHandler.instance().bus().register(handler);}
		if(MinebotConfig.events.chat){
			ChatEvent handler = eventhandler.getChatEvent();
			MinecraftForge.EVENT_BUS.register(handler);
			FMLCommonHandler.instance().bus().register(handler);}
		if(MinebotConfig.events.advancement){
			AchievementEvent handler = eventhandler.getAchievementEvent();
			MinecraftForge.EVENT_BUS.register(handler);
			FMLCommonHandler.instance().bus().register(handler);}
		if(MinebotConfig.events.lightning){
			LightningEvent handler = eventhandler.getLightningEvent();
			MinecraftForge.EVENT_BUS.register(handler);
			FMLCommonHandler.instance().bus().register(handler);}
		if(MinebotConfig.events.death){
			DeathEvent handler = eventhandler.getDeathEvent();
			MinecraftForge.EVENT_BUS.register(handler);
			FMLCommonHandler.instance().bus().register(handler);}
		if(MinebotConfig.events.item){
			ItemDestructionEvent handler = eventhandler.getItemDestructionEvent();
			MinecraftForge.EVENT_BUS.register(handler);
			FMLCommonHandler.instance().bus().register(handler);}
	}
	
	
	@EventBusSubscriber
	private class ConnectionEvent{
	//Player Verbindungs Event
	@SubscribeEvent
    public void playerConnecting(PlayerEvent.PlayerLoggedInEvent event){
		String playername = event.player.getName();
		String message = (playername+" is connecting to the server.");
		//Discordbot sendet String
		SendMessage.sendMessage(message);
	}
	}
	
	public ConnectionEvent getConnectionEvent(){
		return new ConnectionEvent();
	}
	
	@EventBusSubscriber
	private class DisconnectionEvent{
	//Player Verbindungstrennungs Event
	@SubscribeEvent
    public void playerDisconnecting(PlayerEvent.PlayerLoggedOutEvent event){
		String playername = event.player.getName();
		String message = (playername+" has disconnected from the server.");
		//Discordbot sendet String
		SendMessage.sendMessage(message);
	}
	}
	
	public DisconnectionEvent getDisconnectionEvent(){
		return new DisconnectionEvent();
	}
	
	@EventBusSubscriber
	private class DimensionEvent{
	//Dimensionswechsel Event
	@SubscribeEvent
	public void changeDimension(PlayerEvent.PlayerChangedDimensionEvent event){
		String playername = event.player.getName();
		DimensionType dimension = DimensionManager.getProviderType(event.toDim);
		String message = (playername+" has traveled to "+dimension.getName());
		//Discordbot sendet String
		SendMessage.sendMessage(message);
	}
	}
	
	public DimensionEvent getDimensionEvent(){
		return new DimensionEvent();
	}
	
	@EventBusSubscriber
	private class ChatEvent{
	//Chatmessage Event
	@SubscribeEvent
	public void chatMessage(ServerChatEvent event){
		String username = event.getUsername();
		String chat = event.getMessage();
		String message = ("Message by "+username+": "+chat);
		//Discordbot sendet String
		SendMessage.sendMessage(message);
	}
	}
	
	public ChatEvent getChatEvent(){
		return new ChatEvent();
	}
	
	@EventBusSubscriber
	private class LightningEvent{
	//Blitzschlag Event
	@SubscribeEvent
	public void lightning(EntityStruckByLightningEvent event){
		String name = event.getEntity().getName();
		String message = (name + " has been struck by lightning.");
		//Discordbot sendet String
		SendMessage.sendMessage(message);
	}
	}
	
	public LightningEvent getLightningEvent(){
		return new LightningEvent();
	}
	
	@EventBusSubscriber
	private class DeathEvent{
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
	}
	
	public DeathEvent getDeathEvent(){
		return new DeathEvent();
	}
	
	@EventBusSubscriber
	private class ItemDestructionEvent{
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
                 SendMessage.sendMessage(message);
            }
        }
	}
	}
	
	public ItemDestructionEvent getItemDestructionEvent(){
		return new ItemDestructionEvent();
	}
	
	
	/*@EventBusSubscriber
	private class LoadEvent{
	//Weltlade Event
	@SubscribeEvent
	public void worldLoad(WorldEvent.Load event){
		String message = ("The World is being loaded!");
		//Discordbot sendet String
	}
	}*/
	
	/*@EventBusSubscriber
	private class UnloadEvent{
	//Weltlade Event
		@SubscribeEvent
		public void worldunload(WorldEvent.Unload event){
			String message = ("Server shutting down");
			//Discordbot sendet String
			SendMessage.sendMessage(message);
		}
	}*/
	
	@EventBusSubscriber
	private class AchievementEvent{
	@SubscribeEvent
	public void onAdvancementEvent(AdvancementEvent event)
	{
		String advancement = event.getAdvancement().getDisplayText().getUnformattedText();
		String player = event.getEntityPlayer().getDisplayNameString();
		String message = player + " has gotten the Advancement: **"+advancement+"**";
		SendMessage.sendMessage(message);
	}
	}
	
	public AchievementEvent getAchievementEvent(){
		return new AchievementEvent();
	}
}
