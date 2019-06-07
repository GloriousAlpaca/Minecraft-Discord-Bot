package mod.minebot.tileentity;

import mod.minebot.discord.SendMessage;
import net.dv8tion.jda.core.EmbedBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInterface extends TileEntity{

	public boolean secure=false;
	public java.util.UUID UUID = java.util.UUID.randomUUID();
	public String text="";
	public boolean sender=true;
	private Long lasttime=0L;
	
		public TileEntityInterface() {
		}
	
		public void sendToServer() {
			if(System.currentTimeMillis()-lasttime>5000) {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setColor(7506394);
			builder.setTitle("Interface Output: ");
			builder.setDescription(text);
			//Discordbot sendet Message
			SendMessage.sendMessage(builder.build());
			lasttime = System.currentTimeMillis();
			}
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
