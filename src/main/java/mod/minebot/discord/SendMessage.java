package mod.minebot.discord;

public class SendMessage {
	
    public void sendMessage(String ChannelID,String Message){
        ReferenceClass.guild.getTextChannelById(ChannelID).sendMessage(Message);
        
    }
    
    public void sendMessage(String Message){
        ReferenceClass.guild.getTextChannelById(ReferenceClass.ChatChannelID).sendMessage(Message);
        
    }
}
