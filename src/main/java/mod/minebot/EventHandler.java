package mod.minebot;

import mod.minebot.discord.SendMessage;
import mod.minebot.discord.handlers.MessageHandlerMinecraft;
import net.dv8tion.jda.core.EmbedBuilder;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventHandler {
	
	@SuppressWarnings("deprecation")
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
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(55040);
		builder.setDescription(playername+" has connected to the server!");
		//Discordbot sendet Message
		SendMessage.sendMessage(builder.build());
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
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(16711680);
		builder.setDescription(playername+" has disconnected from the server!");
		//Discordbot sendet Message
		SendMessage.sendMessage(builder.build());
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
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(14876927);
		builder.setDescription(playername+" has traveled to "+dimension.getName());
		//Discordbot sendet String
		SendMessage.sendMessage(builder.build());
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
        MessageHandlerMinecraft.HandleMessage(event);
		String username = event.getUsername();
		String chat = event.getMessage();
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(13158600);
		builder.setAuthor("Chatmessage: ");
		builder.setTitle(username);
		builder.setDescription(chat);
		//Discordbot sendet String
		SendMessage.sendMessage(builder.build());
		System.out.println(event.getPlayer().getUniqueID());
	}
	}
	
	public ChatEvent getChatEvent(){
		return new ChatEvent();
	}
	
	@EventBusSubscriber
	private class LightningEvent{
		private Long lasttime=0L;
	//Blitzschlag Event
	@SubscribeEvent
	public void lightning(EntityStruckByLightningEvent event){
		if(System.currentTimeMillis()-lasttime>10000) {
		String name = event.getEntity().getName();
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(13158650);
		builder.setDescription(name+" has been struck by lightning!");
		//Discordbot sendet String
		SendMessage.sendMessage(builder.build());
		lasttime = System.currentTimeMillis();
		}
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
		if(entity instanceof EntityPlayer) {
		String deathmessage = event.getSource().getDeathMessage((EntityLivingBase) entity).getUnformattedText();
		//Discordbot sendet String
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(0);
		builder.setAuthor("Death:");
		builder.setTitle(entity.getName());
		builder.setDescription(deathmessage);
		if(entity instanceof EntityPlayerMP) {
			int c = ((EntityPlayerMP) entity).getStatFile().readStat(StatList.DEATHS)+1;
			float t = ((EntityPlayerMP) entity).getStatFile().readStat(StatList.TIME_SINCE_DEATH);
			t = t/1000;
			String time = " minutes";
			if(t>=60) {
				t=t/60;
				time = " hours";
				}
		builder.appendDescription("\n*Deathcount: "+c+"*");
		builder.appendDescription("\n*Time since last Death: "+t+time+"*");}
		
		//Discordbot sendet String
		SendMessage.sendMessage(builder.build());}
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
		if(stack.hasDisplayName()) {
                String name = stack.getDisplayName();
                EmbedBuilder builder = new EmbedBuilder();
         		builder.setColor(16737792);
         		builder.setDescription(event.getEntityPlayer().getName()+" has destroyed his "+name);
         		//Discordbot sendet String
         		SendMessage.sendMessage(builder.build());
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
		DisplayInfo display = event.getAdvancement().getDisplay();	
		String player = event.getEntityPlayer().getDisplayNameString();
		if(display!=null)
		if(!display.isHidden()) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(16766720);
		builder.setAuthor(player+" has gotten an Advancement:");
		builder.setTitle("["+display.getTitle().getUnformattedText()+"]");
		builder.setDescription(display.getDescription().getUnformattedText());
		SendMessage.sendMessage(builder.build());
		}
	}
	}
	
	public AchievementEvent getAchievementEvent(){
		return new AchievementEvent();
	}
}
