package mod.minebot.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInterface extends TileEntity{

	public boolean secure;
	public java.util.UUID UUID = java.util.UUID.randomUUID();
	public String text="";
	public boolean sender;
	
	
	public TileEntityInterface() {
		secure=false;
		sender=true;
	}
	
	
	//NBT Sachen
		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound compound) {
			compound.setString("text",text);
			compound.setUniqueId("Owner",UUID);
			compound.setBoolean("secure",secure);
			compound.setBoolean("sender",sender);
			return super.writeToNBT(compound);
		}

		@Override
		public void readFromNBT(NBTTagCompound compound) {
			text = compound.getString("text");
			UUID = compound.getUniqueId("Owner");
			secure = compound.getBoolean("secure");
			sender = compound.getBoolean("sender");
			super.readFromNBT(compound);
		}
		
}
