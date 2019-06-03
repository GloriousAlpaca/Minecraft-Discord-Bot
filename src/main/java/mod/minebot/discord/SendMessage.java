package mod.minebot.discord;

import mod.minebot.MINEBOT;

public class SendMessage {
	
    public static void sendMessage(String ChannelID,String Message){
        if(ReferenceClass.guild==null) {
            MINEBOT.LOG.info("Guild not set yet");
        }
        else if(ReferenceClass.GuildID==null){
            MINEBOT.LOG.info("GuildID not set yet");
        }
        else if(ReferenceClass.MessageChannelID==null){
            MINEBOT.LOG.info("DefaultChannelID not set yet");
        }
        else {
            ReferenceClass.guild.getTextChannelById(ChannelID).sendMessage(Message).queue();
        }
    }
    
    public static void sendMessage(String Message){
        if(ReferenceClass.guild==null) {
            MINEBOT.LOG.info("Guild not set yet");
        }
        else if(ReferenceClass.GuildID==null){
            MINEBOT.LOG.info("GuildID not set yet");
        }
        else if(ReferenceClass.MessageChannelID==null){
            MINEBOT.LOG.info("DefaultChannelID not set yet");
        }
        else {
            ReferenceClass.guild.getTextChannelById(ReferenceClass.ChatChannelID).sendMessage(Message).queue();
        }
    }
}
