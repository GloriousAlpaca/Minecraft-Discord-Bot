package mod.minebot;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;

@Config(modid = MINEBOT.MODID)
public class ModConfig {
	public static EventCategory Events = new EventCategory();
	public static DiscordOptions discord = new DiscordOptions();
	
	private static class EventCategory {
	@Comment({
		  "Send a message if a player is connecting ?"
	})
	@Name("Player Connection")
	public static boolean connect = true;
	
	@Comment({
		  "Send a message if a player is disconnecting ?"
		})
	@Name("Player Disconnect")
	public static boolean disconnect = true;
	
	
	@Comment({
		  "Send a message if a player changed his dimension ?"
		})
	@Name("Dimension Change")
	public static boolean dimension = true;
	
	@Comment({
		  "Send a message if a player said something in the in-game chat ?"
		})
	@Name("Chat Message")
	public static boolean chat = true;
	
	@Comment({
		  "Send a message if a player got an advancement?"
		})
	@Name("Advancement")
	public static boolean advancement = true;
	
	@Comment({
		  "Send a message if an entity got hit by lightning ?"
		})
	@Name("Lightning")
	public static boolean lightning = true;
	
	@Comment({
		  "Send a message if an entity died?"
		})
	@Name("Entity death")
	public static boolean death = true;
	
	@Comment({
		  "Send a message if a special item got destroyed?"
		})
	@Name("Item destruction")
	public static boolean item = true;
	
	@Comment({
		  "Send a message on the world load?"
		})
	@Name("World load")
	public static boolean load = true;
	
	@Comment({
		  "Send a message on the world unload?"
		})
	@Name("World unload")
	public static boolean unload = true;
	}
	
	private static class DiscordOptions {
		@Comment({
			  "Should the Bot be started ?"
			})
		@Name("Bot")
		public static boolean bot = false;
	}
}


