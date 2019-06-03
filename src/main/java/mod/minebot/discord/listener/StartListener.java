package mod.minebot.discord.listener;

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

        for(Guild g: event.getJDA().getGuilds() ) {

            //ReferenceClass.GuildID = g.getId();

        }
        event.getJDA().getGuildById(ReferenceClass.GuildID).getTextChannelById(ReferenceClass.MessageChannelID).sendMessage("Bot Gestartet");


        ReferenceClass.guild = event.getJDA().getGuildById(ReferenceClass.GuildID);
    }

}
