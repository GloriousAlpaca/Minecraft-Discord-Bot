package mod.minebot.discord.listener;

import mod.minebot.discord.ReferenceClass;
import net.dv8tion.jda.core.events.guild.GuildReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildReadyListener extends ListenerAdapter {

    public void onGuildReady(GuildReadyEvent event) {

        ReferenceClass.guild = event.getGuild();
        ReferenceClass.defaultchannelid = event.getGuild().getDefaultChannel().getId();
    }
}

