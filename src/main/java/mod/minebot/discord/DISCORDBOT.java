package mod.minebot.discord;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class DISCORDBOT {
public static void DISCORDBOT(String[] Args){

    JDABuilder builder = new JDABuilder(AccountType.BOT);

    builder.setToken(Password.TOKEN);
    builder.setAutoReconnect(true);
    builder.setStatus(OnlineStatus.ONLINE);
    builder.setGame(Game.playing(("Minecraft")));







}
}
