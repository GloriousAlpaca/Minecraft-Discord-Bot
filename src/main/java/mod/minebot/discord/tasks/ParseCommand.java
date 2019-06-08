package mod.minebot.discord.tasks;

import mod.minebot.MinebotConfig;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ParseCommand {

    public static String parse(MessageReceivedEvent event){

        String behead = null;
        String raw = event.getMessage().getContentRaw();
        if(raw.contains("<@!")){
            behead = raw.replace("<@!"+MinebotConfig.discord.clientid+"> playerstats","");
        }
        else{
            behead = raw.replace("<@"+MinebotConfig.discord.clientid+"> playerstats","");

        }
        return behead;
    }
}
