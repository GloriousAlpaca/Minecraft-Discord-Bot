package mod.minebot.discord.listener;

import mod.minebot.discord.handlers.MessageHandlerDiscord;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        MessageHandlerDiscord.HandleMessage(event);
    }


}






