package mod.minebot.gui;

import mod.minebot.container.ContainerEmpty;
import mod.minebot.tileentity.TileEntityInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler  implements IGuiHandler{
	public static final int INTERFACE = 0;

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case INTERFACE:
			return new ContainerEmpty(player.inventory, world);
		default:
			return null;}
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case INTERFACE:
				return new GuiInterface((TileEntityInterface) world.getTileEntity(new BlockPos(x,y,z)));
			default:
				return null;
		}
	}

}
