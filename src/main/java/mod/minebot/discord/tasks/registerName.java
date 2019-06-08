package mod.minebot.discord.tasks;

import mod.minebot.discord.persistence.Reader;
import mod.minebot.discord.persistence.Writer;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

public class registerName {

    public static void register(PrivateMessageReceivedEvent event){



        if(Reader.readfromfile(event.getAuthor().getId()).equals(null)){
            event.getAuthor().openPrivateChannel().complete().sendMessage("Registration was not initiated yet.\nYou can register by typing /registername ´yourdiscordID´ on the minecraft server").queue();
        }
        else{
            String MinecraftID = Reader.readfromfile(event.getAuthor().getId());
            if(Reader.readfromfile(MinecraftID+"verify").contains("true")){
                event.getAuthor().openPrivateChannel().complete().sendMessage("You are already registered\nYou can unlink by typing -unlink").queue();
            }
            else{
                Writer.writetofile(MinecraftID+"verify","true");
                event.getAuthor().openPrivateChannel().complete().sendMessage("Your Minecraft and Discord Account are now registered").queue();
            }
        }



    }

    public static void unregister(PrivateMessageReceivedEvent event, int c){

        String MinecraftID = Reader.readfromfile(event.getAuthor().getId());

        Writer.delete(MinecraftID);
        Writer.delete(MinecraftID+"verify");
        Writer.delete(event.getAuthor().getId());
        if(c==1){
            event.getAuthor().openPrivateChannel().complete().sendMessage("Your have not been registered").queue();
        }
        else{
            event.getAuthor().openPrivateChannel().complete().sendMessage("You have been unlinked").queue();
        }


    }

}
