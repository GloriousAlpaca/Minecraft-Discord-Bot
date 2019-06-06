package mod.minebot.block;

import javax.annotation.Nullable;

import mod.minebot.MINEBOT;
import mod.minebot.gui.GuiHandler;
import mod.minebot.tileentity.TileEntityInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class BlockInterface extends Block{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	@ObjectHolder(value = "minebot:dcinterface")
	public final static BlockInterface dcinterface=null;
	
	@ObjectHolder(value = "minebot:dcinterface")
	public final static Item itemdcinterface=null;
	
	public TileEntityInterface tile;
	
	public BlockInterface() {
		super(Material.IRON,MapColor.STONE);
		setUnlocalizedName("minebot.dcinterface");
		setRegistryName("dcinterface");
		setCreativeTab(MINEBOT.CT);
		setHardness(3f);
		setResistance(15f);
		setHarvestLevel("pickaxe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
    
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			if(tile.secure) {
				if(player.getUniqueID()==tile.UUID)
				player.openGui(MINEBOT.instance, GuiHandler.INTERFACE, world, pos.getX(), pos.getY(), pos.getZ());}
			else
				player.openGui(MINEBOT.instance, GuiHandler.INTERFACE, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
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
    
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side)
    {
        if(side != state.getValue(FACING).getOpposite()) {
        	return true;
        }
        return false;
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
		tile = new TileEntityInterface();
		return tile;
		
	}
	
	public void registerItemModel(Item itemBlock) {
		MINEBOT.proxy.registerItemRenderer(itemBlock, 0, "dcinterface");
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
	
	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
    	 world.setBlockState(pos, state.withProperty(FACING, entity.getHorizontalFacing().getOpposite()), 2);
    }
	
	@Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
	
	/**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
}
