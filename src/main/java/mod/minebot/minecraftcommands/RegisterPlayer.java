package mod.minebot.minecraftcommands;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.persistence.Reader;
import mod.minebot.discord.persistence.Writer;
import mod.minebot.network.ChatMessage;
import mod.minebot.network.PacketHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class RegisterPlayer extends CommandBase {
    @Override
    public String getName() {
        return ReferenceClass.registerminecraftname;
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
            PacketHandler.INSTANCE.sendTo(new ChatMessage("You did not specify a Discord User ID"), (EntityPlayerMP) sender.getCommandSenderEntity());
        }
        else{
            if(ReferenceClass.guild.getMemberById(args[0]).getUser()==null){
                PacketHandler.INSTANCE.sendTo(new ChatMessage("The given Id does not belong to a user on your discord"), (EntityPlayerMP) sender.getCommandSenderEntity());
            }
            else{

                if(Reader.readfromfile(sender.getCommandSenderEntity().getUniqueID().toString()+"verify")==null){
                    PacketHandler.INSTANCE.sendTo(new ChatMessage("Hello "+ReferenceClass.guild.getMemberById(args[0]).getUser().getName()+" please confirm your identity in your private chat"), (EntityPlayerMP) sender.getCommandSenderEntity());
                    ReferenceClass.guild.getMemberById(args[0]).getUser().openPrivateChannel().complete().sendMessage("Are you: "+sender.getCommandSenderEntity().getName()+"?\nIf you are answer with -register or -dontregister respectively").queue();
                    Writer.writetofile(sender.getCommandSenderEntity().getUniqueID().toString(),args[0]);
                    Writer.writetofile(args[0],sender.getCommandSenderEntity().getUniqueID().toString());
                    Writer.writetofile(sender.getCommandSenderEntity().getUniqueID().toString()+"verify","false");

                }
                else{
                    if(Reader.readfromfile(sender.getCommandSenderEntity().getUniqueID().toString()+"verify").contains("true")){
                        PacketHandler.INSTANCE.sendTo(new ChatMessage("This Discord Account is already linked to a Player\nYou can unlink your account in  your private messages"), (EntityPlayerMP) sender.getCommandSenderEntity());
                        ReferenceClass.guild.getMemberById(args[0]).getUser().openPrivateChannel().complete().sendMessage(sender.getCommandSenderEntity().getName()+"Tried to link to your Account\nIf you want to reset your Link write -unlink").queue();
                    }
                    else{
                        PacketHandler.INSTANCE.sendTo(new ChatMessage("Hello "+ReferenceClass.guild.getMemberById(args[0]).getUser().getName()+" please confirm your identity in your private chat"), (EntityPlayerMP) sender.getCommandSenderEntity());
                        ReferenceClass.guild.getMemberById(args[0]).getUser().openPrivateChannel().complete().sendMessage("Are you: "+sender.getCommandSenderEntity().getName()+"?\nIf you are answer with -register or -dontregister respectively").queue();
                        Writer.writetofile(sender.getCommandSenderEntity().getUniqueID().toString(),args[0]);
                        Writer.writetofile(args[0],sender.getCommandSenderEntity().getUniqueID().toString());
                        Writer.writetofile(sender.getCommandSenderEntity().getUniqueID().toString()+"verify","false");
                    }

                }



            }



        }
        

    }

}
