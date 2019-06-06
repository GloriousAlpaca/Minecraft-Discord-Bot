package mod.minebot.minecraftcommands;

import mod.minebot.discord.persistence.Writer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
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
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Writer.writetofile(sender.getName(),args[0]);

    }
}
