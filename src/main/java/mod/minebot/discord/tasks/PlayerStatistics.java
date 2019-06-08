package mod.minebot.discord.tasks;


import mod.minebot.discord.persistence.Reader;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;

public class PlayerStatistics {

    public static void showstatistics(MessageReceivedEvent event, String discordid){

        UUID id = UUID.fromString(Reader.readfromfile(discordid));
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        EntityPlayerMP player = server.getPlayerList().getPlayerByUUID(id);

    }
}
