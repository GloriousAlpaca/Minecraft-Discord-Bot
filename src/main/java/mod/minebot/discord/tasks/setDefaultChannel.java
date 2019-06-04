package mod.minebot.discord.tasks;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import mod.minebot.discord.persistance.Reader;
import mod.minebot.discord.persistance.Writer;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class setDefaultChannel {

    public static void setChannel(MessageReceivedEvent event){
        TextChannel TChannel = event.getTextChannel();
        String message = event.getMessage().getContentRaw();

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
