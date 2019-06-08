package mod.minebot.discord.tasks;

import java.awt.Color;

import mod.minebot.discord.SendMessage;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class serverStatus {

    public static void getServerStatus(MessageReceivedEvent event){

        //bei problem effective side hinzufügen

        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if(server==null){
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.RED);
            builder.setTitle("Status Error");
            builder.setDescription("Server not running yet");
            SendMessage.sendMessage(builder.build());
        }
        else {


            String playerlist = null;


            String name = server.getName();
            String pc = String.valueOf(server.getCurrentPlayerCount());
            String ct = String.valueOf(server.getCurrentTime());
            String pm = String.valueOf(server.getMaxPlayers());
            String version = server.getMinecraftVersion();
            String[] players = server.getOnlinePlayerNames();
            String text = server.getMOTD();

            if(server.getCurrentPlayerCount()>0){
                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(3381759);
                builder.setAuthor(name + " is running on Version " + version);
                builder.setTitle(pc + "/" + pm + " players are currently playing");
                for (int i = 0; i < players.length; i++) {
                    if(i==0){
                        playerlist = players[i] + "\n";
                    }
                    else{
                        playerlist += players[i] + "\n";
                    }
                }
                builder.setDescription(playerlist);
                SendMessage.sendMessage(builder.build());

            }
            else{
                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(3381759);
                builder.setAuthor(name + " is running on Version " + version);
                builder.setTitle("There are currently no players playing");
                SendMessage.sendMessage(builder.build());

            }


        }
    }
}
