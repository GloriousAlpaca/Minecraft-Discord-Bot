package mod.minebot.discord.tasks;


import java.io.File;
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
    	UUID id;
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if(argument.equals(null)){
            SendMessage.sendMessage("No Discord ID or Minecraft name given");
        }
        else{

            if(argument.contains("d-")){
                String argumentparsed = argument.replace("d-","");
                id = UUID.fromString(Reader.readfromfile(argumentparsed));

            }
            else {
                id = server.getPlayerList().getPlayerByUsername(argument).getUniqueID();

            }
            if(id==null){
                SendMessage.sendMessage("Player not found");

            }
            else{

                
                File file1 = new File(server.getWorld(0).getSaveHandler().getWorldDirectory(), "stats");
                File file2 = new File(file1, id + ".json");
                
                StatisticsManagerServer stat = new StatisticsManagerServer(server, file2);
                EmbedBuilder builder = new EmbedBuilder();
        		builder.setColor(13158650);
        		builder.setAuthor(player.getName());
        		builder.setTitle((Arrays.asList(server.getOnlinePlayerProfiles()).contains(server.getPlayerProfileCache().getProfileByUUID(id))) ? "Online":"Offline");
        		builder.setDescription("Time Played: "+stat.readStat(StatList.PLAY_ONE_MINUTE)/1000+" minutes");
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
        		builder.appendDescription("\n"+randomstat.getStatName().getUnformattedText()+": "+stat.readStat(randomstat));
        		SendMessage.sendMessage(builder.build());
            }
        }


    }
}
