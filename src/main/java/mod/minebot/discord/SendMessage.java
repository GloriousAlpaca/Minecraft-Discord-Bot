package mod.minebot.discord;

public class SendMessage {
	
    public static void sendMessage(String ChannelID,String Message){
        ReferenceClass.guild.getTextChannelById(ChannelID).sendMessage(Message);
        
    }
    
    public static void sendMessage(String Message){
        ReferenceClass.guild.getTextChannelById(ReferenceClass.ChatChannelID).sendMessage(Message);
        
    }
}
