package mod.minebot.minecraftcommands;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegister {

    @Mod.EventHandler
    public void registercommands(FMLServerStartingEvent event){
        event.registerServerCommand(new RegisterPlayer());

    }
}
