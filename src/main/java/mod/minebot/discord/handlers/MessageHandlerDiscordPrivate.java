package mod.minebot.discord.handlers;

import mod.minebot.MinebotConfig;
import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.tasks.CommandHelperDiscord;
import mod.minebot.discord.tasks.CommandHelperDiscordPrivate;
import mod.minebot.discord.tasks.registerName;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

public class MessageHandlerDiscordPrivate {

    public static void HandleMessage(PrivateMessageReceivedEvent event){

        if(event.getAuthor().getId().equals(MinebotConfig.discord.clientid)){


        }
        else{
            if(event.getMessage().getContentDisplay().contains(ReferenceClass.registeraccount)){

                registerName.register(event);
            }
            else if(event.getMessage().getContentDisplay().contains(ReferenceClass.donotregister)){

                registerName.unregister(event,1);

            }
            else if(event.getMessage().getContentDisplay().contains(ReferenceClass.unregister)){

                registerName.unregister(event,2);

            }
            else if(event.getMessage().getContentDisplay().contains("-help")){

                CommandHelperDiscordPrivate.help(event);

            }
            else{
                event.getAuthor().openPrivateChannel().complete().sendMessage("Command not recognised\nTry -help for more information").queue();
            }
        }

    }
}
