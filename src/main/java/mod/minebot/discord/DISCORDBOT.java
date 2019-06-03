package mod.minebot.discord;

import javax.security.auth.login.LoginException;

import mod.minebot.discord.listener.StartListener;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class DISCORDBOT {
	
	public DISCORDBOT(String[] Args) throws LoginException{

		JDABuilder builder = new JDABuilder();

		builder.setToken(ReferenceClass.TOKEN);
		builder.setAutoReconnect(true);
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setGame(Game.playing((ReferenceClass.InitialGame)));

        //Listener
        builder.addEventListener(new StartListener());
        




		ReferenceClass.main = builder.build();
		








	}

}
