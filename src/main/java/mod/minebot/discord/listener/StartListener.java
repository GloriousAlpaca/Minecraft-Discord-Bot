package mod.minebot.discord.listener;

import com.sun.media.jfxmedia.logging.Logger;
import mod.minebot.MINEBOT;
import mod.minebot.discord.DISCORDBOT;
import mod.minebot.discord.ReferenceClass;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDAInfo;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.Route;

public class StartListener extends ListenerAdapter {


    public void onReady(ReadyEvent event){



            event.getJDA().getGuildById(ReferenceClass.GuildID).getTextChannelById(ReferenceClass.MessageChannelID).sendMessage("Bot Gestartet");

    }

}
