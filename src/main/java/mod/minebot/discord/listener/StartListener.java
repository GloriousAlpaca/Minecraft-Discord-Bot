package mod.minebot.discord.listener;

import mod.minebot.discord.ReferenceClass;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class StartListener extends ListenerAdapter {


    public void onReady(ReadyEvent event){



            event.getJDA().getGuildById(ReferenceClass.GuildID).getTextChannelById(ReferenceClass.defaultchannelid).sendMessage("Bot Gestartet");

    }

}
