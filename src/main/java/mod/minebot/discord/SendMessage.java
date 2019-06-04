package mod.minebot.discord;

import mod.minebot.MINEBOT;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class SendMessage {
	
    public static void sendMessage(String ChannelID,String message){
        if(ReferenceClass.guild==null) {
            MINEBOT.LOG.info("Guild not set yet");
        }
        else if(ReferenceClass.GuildID==null){
            MINEBOT.LOG.info("GuildID not set yet");
        }
        else if(ReferenceClass.defaultchannelid==null){
            MINEBOT.LOG.info("DefaultChannelID not set yet");
        }
        else {
            ReferenceClass.guild.getTextChannelById(ChannelID).sendMessage(message).queue();
        }
    }
    
    public static void sendMessage(String message){
        if(ReferenceClass.guild==null) {
            MINEBOT.LOG.info("Guild not set yet");
        }
        else if(ReferenceClass.GuildID==null){
            MINEBOT.LOG.info("GuildID not set yet");
        }
        else if(ReferenceClass.defaultchannelid==null){
            MINEBOT.LOG.info("DefaultChannelID not set yet");
        }
        else {
            ReferenceClass.guild.getTextChannelById(ReferenceClass.defaultchannelid).sendMessage(message).queue();
        }
    }
    
    public static void sendMessage(MessageEmbed message){
        if(ReferenceClass.guild==null) {
            MINEBOT.LOG.info("Guild not set yet");
        }
        else if(ReferenceClass.GuildID==null){
            MINEBOT.LOG.info("GuildID not set yet");
        }
        else if(ReferenceClass.defaultchannelid==null){
            MINEBOT.LOG.info("DefaultChannelID not set yet");
        }
        else {
            ReferenceClass.guild.getTextChannelById(ReferenceClass.defaultchannelid).sendMessage(message).queue();
        }
    }
    
    public static void sendTestMessage(){
        if(ReferenceClass.guild==null) {
            MINEBOT.LOG.info("Guild not set yet");
        }
        else if(ReferenceClass.GuildID==null){
            MINEBOT.LOG.info("GuildID not set yet");
        }
        else if(ReferenceClass.defaultchannelid==null){
            MINEBOT.LOG.info("DefaultChannelID not set yet");
        }
        else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(16711680);
            builder.setAuthor("Testautor");
            builder.setTitle("Ein Test Titel", "https://duckduckgo.com");
            builder.setDescription(builder.getDescriptionBuilder().append("Testbeschreibung").toString());
            builder.setFooter("Testfussnote","https://i.imgur.com/deCZ0vS.png");
            ReferenceClass.guild.getTextChannelById(ReferenceClass.ChatChannelID).sendMessage(builder.build()).queue();
        }
    }
    
    public static void sendChat(String Message){
        if(ReferenceClass.guild==null) {
            MINEBOT.LOG.info("Guild not set yet");
        }
        else if(ReferenceClass.GuildID==null){
            MINEBOT.LOG.info("GuildID not set yet");
        }
        else if(ReferenceClass.defaultchannelid==null){
            MINEBOT.LOG.info("DefaultChannelID not set yet");
        }
        else {
            ReferenceClass.guild.getTextChannelById(ReferenceClass.ChatChannelID).sendMessage(Message).queue();
        }
    }
}
