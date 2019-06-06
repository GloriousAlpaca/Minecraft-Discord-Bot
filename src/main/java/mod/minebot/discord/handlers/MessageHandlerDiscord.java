package mod.minebot.discord.handlers;

import mod.minebot.MinebotConfig;
import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import mod.minebot.discord.persistence.Reader;
import mod.minebot.discord.persistence.Writer;
import mod.minebot.discord.tasks.serverStatus;
import mod.minebot.discord.tasks.setDefaultChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageHandlerDiscord {

    public static void HandleMessage(MessageReceivedEvent event){
        TextChannel TChannel = event.getTextChannel();
        String message = event.getMessage().getContentRaw();


        if(message.contains("<@")&&message.contains(MinebotConfig.discord.clientid+">")&&message.contains("setdefault")){

            setDefaultChannel.setChannel(event);

        }
        else if(message.contains("<@")&&message.contains(MinebotConfig.discord.clientid+">")&&message.contains("serverstatus")){

            serverStatus.getServerStatus(event);


        }





    }
}
