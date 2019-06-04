package mod.minebot.discord.persistance;

import mod.minebot.discord.ReferenceClass;
import mod.minebot.discord.SendMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static mod.minebot.MINEBOT.LOG;

public class Reader {

    public static String readfromfile(String key) {
        if(key==null){
            return null;
        }
        else {
            String path = ReferenceClass.defaultconfig.replace("minebot.cfg", "settings.properties");

            File file = new File(path);

            String search = null;
            try {
                FileReader reader = new FileReader(file);
                Properties props = new Properties();
                props.load(reader);

                search = props.getProperty(key);

                reader.close();
            } catch (FileNotFoundException ex) {
                LOG.info("Config File " + path + " not found");
            } catch (IOException ex) {
                LOG.info("IO Error");
            }


            return search;
        }
    }

    public static String readfromfile(String location,String key) {
        if(key==null){
            return null;
        }
        else {


            File file = new File(location);

            String search = null;
            try {
                FileReader reader = new FileReader(file);
                Properties props = new Properties();
                props.load(reader);

                search = props.getProperty(key);

                SendMessage.sendMessage("217319333422956554", search);
                reader.close();
            } catch (FileNotFoundException ex) {
                LOG.info("Config File " + location + " not found");
            } catch (IOException ex) {
                LOG.info("IO Error");
            }

            if(search=="nichts"){
                return null;
            }
            else{
                return search;
            }


        }
    }
}
