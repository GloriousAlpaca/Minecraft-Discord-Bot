package mod.minebot.discord.listener;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().contains("@MineBot")&&event.getMessage().getContentStripped().contains("setdefaultchannel")) {
            if(event.getMember().getRoles().contains("Admin")){

                ReferenceClass.guild = event.getGuild();
                ReferenceClass.ChatChannelID = event.getTextChannel().getId();
                ReferenceClass.MessageChannelID = event.getTextChannel().getId();
                ReferenceClass.GuildID = event.getGuild().getId();
                SendMessage.sendMessage("The Channel "+event.getTextChannel().getName()+" was set as your default channel :)");


            }
            else{
                SendMessage.sendMessage("You do not have the required permissions!>:(");
            }

        }
    }
}
