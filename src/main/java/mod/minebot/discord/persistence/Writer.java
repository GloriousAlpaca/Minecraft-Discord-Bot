package mod.minebot.discord.persistence;

import mod.minebot.discord.ReferenceClass;

import java.io.*;
import java.util.Properties;

import static mod.minebot.MINEBOT.LOG;

public class Writer {

public static void writetofile(String key,String content){
    if(key==null){
        key="nichts";
    }
    else if(content==null){
        content="nichts";
    }
    String path = ReferenceClass.defaultconfig.replace("minebot.cfg","settings.properties");

    File file = new File(path);

    try {
        FileReader reader = new FileReader(file);
        Properties props = new Properties();
        props.load(reader);
        props.setProperty(key, content);
        FileWriter writer = new FileWriter(file);
        props.store(writer, "playertable");
        writer.close();
    } catch (FileNotFoundException ex) {
        LOG.info("Config File " + path + " not found");
    } catch (IOException ex) {
        LOG.info("IO Error");
    }
}

    public static void writetofile(String location,String key,String content){
        if(key==null){
            key="nichts";
        }
        else if(content==null){
            content="nichts";
        }

        File file = new File(location);

        try {
            FileReader reader = new FileReader(file);
            Properties props = new Properties();
            props.load(reader);
            props.setProperty(key, content);
            FileWriter writer = new FileWriter(file);
            props.store(writer, "playertable");
            writer.close();
        } catch (FileNotFoundException ex) {
            LOG.info("Config File " + location + " not found");
        } catch (IOException ex) {
            LOG.info("IO Error");
        }
    }

    public static void delete(String key){

        String path = ReferenceClass.defaultconfig.replace("minebot.cfg","settings.properties");

        File file = new File(path);

        try {
            FileReader reader = new FileReader(file);
            Properties props = new Properties();
            props.load(reader);
            props.remove(key);
            FileWriter writer = new FileWriter(file);
            props.store(writer, "playertable");
            writer.close();
        } catch (FileNotFoundException ex) {
            LOG.info("Config File " + path + " not found");
        } catch (IOException ex) {
            LOG.info("IO Error");
        }

    }
}
