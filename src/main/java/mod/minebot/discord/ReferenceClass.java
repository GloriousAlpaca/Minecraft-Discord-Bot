package mod.minebot.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;

public class ReferenceClass {

    public static String InitialGame ="Minecraft";
    public static String GuildID = "212543344742629378";
    public static String ChatChannelID = "585048049303289856";
    public static String defaultchannelid;
    public static Guild guild;
    public static JDA main;
    public static String defaultconfig;

    //MessageDiscordKeys
    public static String setdefaultchannel ="setdefault";
    public static String getserverstatus ="serverstatus";
    public static String getplayerstats ="playerstats";
    //MessageDiscordDescription
    public static String setdefaultchannelD ="The owner can set the default channel in which messages will be displayed";
    public static String getserverstatusD ="Shows the status of the Server and a List of current players";
    public static String getplayerstatsD ="Get the stats of a Player or Discord user if registered";
    //MessageDiscordPrivateKeys
    public static String registeraccount="-register";
    public static String donotregister="-dontregister";
    public static String unregister="-unregister";
    //MessageDiscordPrivateDescription
    public static String registeraccountD="Confirm your registration in Discord";
    public static String donotregisterD="Cancel a registration";
    public static String unregisterD="Undo a registration";
    //MessageMinecraftKeys
    public static String registerminecraftname="registername";
    //MessageMinecraftDescription
    public static String registerminecraftnameD="Start to register your Minecraft account with your Discord account";

}
