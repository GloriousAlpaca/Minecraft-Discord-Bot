package mod.minebot.proxy;

import mod.minebot.discord.DISCORDBOT;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.TextureStitchEvent;

public class ClientProxy implements IProxy{
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		
	}
	
	@Override
	public void registerEntityRenderer() {
		
	}

	@Override
	public void registerTileEntitySpecialRenderer() {
		
	}
	
	@Override
	public void registerSprites(TextureStitchEvent.Pre event) {
		
	}

	@Override
	public void startBot() {
	}
}