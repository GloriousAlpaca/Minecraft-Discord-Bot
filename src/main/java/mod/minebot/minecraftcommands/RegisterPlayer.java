package mod.minebot.minecraftcommands;

import mod.minebot.discord.persistence.Writer;
import mod.minebot.network.ChatMessage;
import mod.minebot.network.InterfaceMessage;
import mod.minebot.network.PacketHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class RegisterPlayer extends CommandBase {
    @Override
    public String getName() {
        return "registername";
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

        if(args.length==0){
            PacketHandler.INSTANCE.sendTo(new ChatMessage("haha"), (EntityPlayerMP) sender.getCommandSenderEntity());
        }
        else{
            Writer.writetofile(sender.getName(),args[0]);
            System.out.println(sender.getCommandSenderEntity().getUniqueID());

        }
        

    }
}
