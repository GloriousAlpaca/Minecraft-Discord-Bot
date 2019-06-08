package mod.minebot.discord.tasks;


import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import mod.minebot.discord.persistence.Reader;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsManagerServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PlayerStatistics {

    public static void showstatistics(MessageReceivedEvent event, String argument){

        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        EntityPlayerMP player = null;
        if(ReferenceClass.guild.getMemberById(argument)==null){
            UUID id = UUID.fromString(Reader.readfromfile(argument));
            player = server.getPlayerList().getPlayerByUUID(id);
        }
        else {
            player = server.getPlayerList().getPlayerByUsername(argument);
        }

        UUID id = player.getUniqueID();

        StatisticsManagerServer stat = player.getStatFile();
        EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(13158650);
		builder.setAuthor(player.getName());
		builder.setTitle((Arrays.asList(server.getOnlinePlayerProfiles()).contains(server.getPlayerProfileCache().getProfileByUUID(id))) ? "Online":"Offline");
		builder.setDescription("Time Played: "+stat.readStat(StatList.PLAY_ONE_MINUTE)+" minutes");
		builder.appendDescription("\nDamage dealt: "+stat.readStat(StatList.DAMAGE_DEALT));
		builder.appendDescription("\nMob kills: "+stat.readStat(StatList.MOB_KILLS));
		builder.appendDescription("\nPlayer Kills: "+stat.readStat(StatList.PLAYER_KILLS));
		builder.appendDescription("\nDamage taken: "+stat.readStat(StatList.DAMAGE_TAKEN));
		builder.appendDescription("\nDeaths: "+stat.readStat(StatList.DEATHS));
		float m = ((float)(stat.readStat(StatList.WALK_ONE_CM)+stat.readStat(StatList.CROUCH_ONE_CM)
		+stat.readStat(StatList.SPRINT_ONE_CM)+stat.readStat(StatList.FLY_ONE_CM)+stat.readStat(StatList.CROUCH_ONE_CM)
		+stat.readStat(StatList.SWIM_ONE_CM)+stat.readStat(StatList.MINECART_ONE_CM)+stat.readStat(StatList.BOAT_ONE_CM)
		+stat.readStat(StatList.PIG_ONE_CM)+stat.readStat(StatList.HORSE_ONE_CM)+stat.readStat(StatList.AVIATE_ONE_CM)))/100;
		builder.appendDescription("\nDistance traveled : "+m+" meters");
		builder.appendDescription("\n**Random Fact:**");
		StatBase randomstat = StatList.ALL_STATS.get(new Random().nextInt(StatList.ALL_STATS.size()));
		builder.appendDescription("\n"+randomstat.getStatName()+": "+stat.readStat(randomstat));
		SendMessage.sendMessage(builder.build());
    }
}
