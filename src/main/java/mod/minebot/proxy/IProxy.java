package mod.minebot.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.TextureStitchEvent;


public interface IProxy {

	public void registerItemRenderer(Item item, int meta, String id);
	
	public void registerEntityRenderer();
	
	public void registerTileEntitySpecialRenderer();

	public void registerSprites(TextureStitchEvent.Pre event);
	
	public void startBot() ;

	void displayGui(BlockPos ppos, boolean psecure, boolean psender, String ptext);
}
