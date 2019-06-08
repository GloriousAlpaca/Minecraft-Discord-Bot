package mod.minebot.discord.handlers;

import mod.minebot.MinebotConfig;
import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.tasks.registerName;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

public class MessageHandlerDiscordPrivate {

    public static void HandleMessage(PrivateMessageReceivedEvent event){

        if(event.getAuthor().getId().equals(MinebotConfig.discord.clientid)){


        }
        else{
            if(event.getMessage().getContentDisplay().contains("-register")){

                registerName.register(event);
            }
            else if(event.getMessage().getContentDisplay().contains("-dontregister")){

                registerName.unregister(event,1);

            }
            else if(event.getMessage().getContentDisplay().contains("-unlink")){

                registerName.unregister(event,2);

            }
            else{
                event.getAuthor().openPrivateChannel().complete().sendMessage("Command not recognised").queue();
            }
        }

    }
}
