package mod.minebot.discord.handlers;

import mod.minebot.MinebotConfig;
import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageHandlerDiscord {

    public static void HandleMessage(MessageReceivedEvent event){
        TextChannel TChannel = event.getTextChannel();
        String message = event.getMessage().getContentRaw();


        if(message.contains("<@")&&message.contains(MinebotConfig.discord.clientid+">")&&message.contains("setdefault")){
            if(event.getMember()==event.getGuild().getOwner()){
                if(TChannel.getId()==ReferenceClass.defaultchannelid){

                    SendMessage.sendMessage("The channel "+event.getTextChannel().getName()+" is already the default channel");
                }
                else {
                    ReferenceClass.ChatChannelID = event.getTextChannel().getId();
                    ReferenceClass.defaultchannelid = event.getTextChannel().getId();
                    ReferenceClass.GuildID = event.getGuild().getId();
                    SendMessage.sendMessage("The channel " + event.getTextChannel().getName() + " was set as your default channel :)");
                }

            }
            else{
                event.getTextChannel().sendMessage("You do not have the required permissions! >:(").queue();

            }

        }





    }
}
