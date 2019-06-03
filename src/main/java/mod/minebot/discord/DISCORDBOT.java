package mod.minebot.discord;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

public class DISCORDBOT {
	
	public DISCORDBOT(String[] Args){
		System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

		JDABuilder builder = new JDABuilder(AccountType.BOT);

		builder.setToken(ReferenceClass.TOKEN);
		builder.setAutoReconnect(true);
		builder.setStatus(ReferenceClass.Status);
		builder.setGame(Game.playing((ReferenceClass.GamePlaying)));







	}

}
