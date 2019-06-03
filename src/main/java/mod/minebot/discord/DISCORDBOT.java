package mod.minebot.discord;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

public class DISCORDBOT {
	
	public DISCORDBOT(String[] Args) throws LoginException{

		JDABuilder builder = new JDABuilder();

		builder.setToken(ReferenceClass.TOKEN);
		builder.setAutoReconnect(true);
		builder.setStatus(ReferenceClass.Status);
		builder.setGame(Game.playing((ReferenceClass.GamePlaying)));
		builder.build();







	}

}
