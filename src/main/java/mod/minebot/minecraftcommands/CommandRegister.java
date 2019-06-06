package mod.minebot.minecraftcommands;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommandRegister {

    @SubscribeEvent
    public void registercommands(FMLServerStartingEvent event){
        event.registerServerCommand(new RegisterPlayer());

    }
}
