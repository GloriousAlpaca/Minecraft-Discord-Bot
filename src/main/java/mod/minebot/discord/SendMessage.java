package mod.minebot.discord;

public class SendMessage {
    public SendMessage(String ChannelID,String Message){
        ReferenceClass.guild.getTextChannelById(ChannelID).sendMessage(Message);
        
    }
    
    public SendMessage(String Message){
        ReferenceClass.guild.getTextChannelById(ReferenceClass.ChatChannelID).sendMessage(Message);
        
    }
}
