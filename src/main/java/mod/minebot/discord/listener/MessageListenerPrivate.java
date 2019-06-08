package mod.minebot.discord.listener;

import mod.minebot.discord.handlers.MessageHandlerDiscordPrivate;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListenerPrivate extends ListenerAdapter {

    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event){

        MessageHandlerDiscordPrivate.HandleMessage(event);


    }
}
