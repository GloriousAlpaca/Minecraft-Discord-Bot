package mod.minebot.discord.handlers;

import mod.minebot.MinebotConfig;
import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import mod.minebot.discord.persistance.Reader;
import mod.minebot.discord.persistance.Writer;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageHandlerDiscord {

    public static void HandleMessage(MessageReceivedEvent event){
        TextChannel TChannel = event.getTextChannel();
        String message = event.getMessage().getContentRaw();


        if(message.contains("<@")&&message.contains(MinebotConfig.discord.clientid+">")&&message.contains("setdefault")){
            if(event.getMember()==event.getGuild().getOwner()||event.getMember().getUser().getId().equals("212961447834025984")){
                if(TChannel.getId().equals(Reader.readfromfile("defaultchannel"))){

                    SendMessage.sendMessage("The channel "+event.getTextChannel().getName()+" is already the default channel");
                }
                else {
                    ReferenceClass.ChatChannelID = event.getTextChannel().getId();
                    ReferenceClass.defaultchannelid = event.getTextChannel().getId();
                    ReferenceClass.GuildID = event.getGuild().getId();
                    Writer.writetofile("defaultchannel",event.getTextChannel().getId());
                    SendMessage.sendMessage("The channel " + event.getTextChannel().getName() + " was set as your default channel :)");
                }

            }
            else{
                event.getTextChannel().sendMessage("You do not have the required permissions! >:(").queue();
                SendMessage.sendMessage(event.getMember().getUser().getId());
                SendMessage.sendMessage(Reader.readfromfile("defaultchannel"));
                SendMessage.sendMessage(TChannel.getId());


            }

        }





    }
}
