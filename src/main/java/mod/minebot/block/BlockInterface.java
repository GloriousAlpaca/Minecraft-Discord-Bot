package mod.minebot.block;

import javax.annotation.Nullable;

import mod.minebot.MINEBOT;
import mod.minebot.tileentity.TileEntityInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class BlockInterface extends Block{
	
	@ObjectHolder(value = "minebot:dcinterface")
	public final static BlockInterface dcinterface=null;
	public TileEntityInterface entity;
	
	public BlockInterface() {
		super(Material.IRON,MapColor.STONE);
		setUnlocalizedName("minebot.dcinterface");
		setRegistryName("dcinterface");
		setCreativeTab(MINEBOT.CT);
		setHardness(3f);
		setResistance(15f);
		setHarvestLevel("pickaxe", 0);
		
	}
	
	/**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (worldIn.isBlockPowered(pos))
        {
           
        }
    }
    
    public Class<TileEntityInterface> getTileEntityClass() {
    	return TileEntityInterface.class;
    }
	
	public TileEntityInterface getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TileEntityInterface)world.getTileEntity(pos);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public TileEntityInterface createTileEntity(World world, IBlockState state) {
		entity = new TileEntityInterface();
		return entity;
		
	}
	
	public void registerItemModel(Item itemBlock) {
		MINEBOT.proxy.registerItemRenderer(itemBlock, 0, "dcinterface");
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
}
