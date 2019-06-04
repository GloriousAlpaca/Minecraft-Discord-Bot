package mod.minebot.proxy;

import mod.minebot.MINEBOT;
import mod.minebot.discord.DISCORDBOT;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy implements IProxy{
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MINEBOT.MODID + ":" + id, "inventory"));
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