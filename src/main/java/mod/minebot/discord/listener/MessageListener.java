package mod.minebot.discord.listener;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import mod.minebot.discord.handlers.MessageHandlerDiscord;
import mod.minebot.discord.persistance.Reader;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {



        MessageHandlerDiscord.HandleMessage(event);
    }


}






