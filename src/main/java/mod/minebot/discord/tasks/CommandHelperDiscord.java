package mod.minebot.discord.tasks;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHelperDiscord {

    public static void help(MessageReceivedEvent event){

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(3381759);
        builder.setAuthor("Commands for use on this Server");
        builder.setDescription("at Minebot "+ReferenceClass.setdefaultchannel+" :\n"+ReferenceClass.setdefaultchannelD+
                "\nat Minebot "+ReferenceClass.getserverstatus+" :\n"+ReferenceClass.getserverstatusD+
                "\nat Minebot "+ReferenceClass.getplayerstats+" d-\"discordid\" or \"MinecraftName\""+" :\n"+ReferenceClass.getplayerstatsD

        );

        SendMessage.sendMessage(builder.build());

    }
}
