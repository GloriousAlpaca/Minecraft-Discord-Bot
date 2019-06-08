package mod.minebot.minecraftcommands;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.network.ChatMessage;
import mod.minebot.network.PacketHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandHelperMinecraft extends CommandBase {
    @Override
    public String getName() {
        return "helpdiscord";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        PacketHandler.INSTANCE.sendTo(new ChatMessage("Commands for use in Minecraft :\n"+
                ReferenceClass.registerminecraftname+" : "+ReferenceClass.registerminecraftnameD), (EntityPlayerMP) sender.getCommandSenderEntity());

    }
}
