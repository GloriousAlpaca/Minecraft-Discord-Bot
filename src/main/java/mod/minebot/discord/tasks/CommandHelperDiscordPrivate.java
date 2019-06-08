package mod.minebot.discord.tasks;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

public class CommandHelperDiscordPrivate {

    public static void help(PrivateMessageReceivedEvent event){

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(3381759);
        builder.setAuthor("Commands for use in private Chat");
        builder.setDescription(ReferenceClass.registeraccount+" : "+ReferenceClass.registeraccountD+
                "\n"+ReferenceClass.donotregister+" : "+ReferenceClass.donotregisterD+
                "\n"+ReferenceClass.unregister+" : "+ReferenceClass.unregisterD);

        event.getAuthor().openPrivateChannel().complete().sendMessage(builder.build()).queue();

    }
}
