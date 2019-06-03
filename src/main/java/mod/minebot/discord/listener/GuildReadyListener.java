package mod.minebot.discord.listener;

import com.sun.media.jfxmedia.logging.Logger;
import mod.minebot.MINEBOT;
import mod.minebot.discord.ReferenceClass;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.guild.GenericGuildEvent;
import net.dv8tion.jda.core.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.core.events.guild.GuildReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildReadyListener extends ListenerAdapter {

    public void onGuildReady(GuildReadyEvent event) {

        ReferenceClass.guild = event.getGuild();

    }
}

