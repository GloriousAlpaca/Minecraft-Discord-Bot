package mod.minebot.tileentity;

import java.util.Random;

import mod.minebot.discord.SendMessage;
import net.dv8tion.jda.core.EmbedBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInterface extends TileEntity{
	
	public java.util.UUID UUID;
	public String text="";
	public boolean secure=false;
	public boolean sender=true;
	private Long lasttime=0L;
	public int test = new Random().nextInt();
	
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
			if(UUID != null)
			compound.setUniqueId("owner",UUID);
			compound.setBoolean("secure",secure);
			compound.setBoolean("sender",sender);
			System.out.println(text);
			System.out.println(UUID);
			System.out.println(secure);
			System.out.println(sender);
			return super.writeToNBT(compound);
		}

		@Override
		public void readFromNBT(NBTTagCompound compound) {
			text = compound.getString("text");
			UUID = compound.getUniqueId("owner");
			secure = compound.getBoolean("secure");
			sender = compound.getBoolean("sender");
			System.out.println(text);
			System.out.println(UUID);
			System.out.println(secure);
			System.out.println(sender);
			super.readFromNBT(compound);
		}
		
		//Client und Server synchronisierung
		@Override
		public final NBTTagCompound getUpdateTag() {
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean("secure",secure);
			nbt.setUniqueId("owner",UUID);
			return nbt;
		}
		
	    /**
	     * Called when you receive a TileEntityData packet for the location this
	     * TileEntity is currently in. On the client, the NetworkManager will always
	     * be the remote server. On the server, it will be whomever is responsible for
	     * sending the packet.
	     *
	     * @param net The NetworkManager the packet originated from
	     * @param pkt The data packet
	     */
		@Override
	    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt)
	    {
			super.onDataPacket(net, pkt);
			this.handleUpdateTag(pkt.getNbtCompound());
	    }

	    /**
	     * Called when the chunk's TE update tag, gotten from {@link #getUpdateTag()}, is received on the client.
	     * <p>
	     * Used to handle this tag in a special way. By default this simply calls {@link #readFromNBT(NBTTagCompound)}.
	     *
	     * @param tag The {@link NBTTagCompound} sent from {@link #getUpdateTag()}
	     */
		@Override
	    public void handleUpdateTag(NBTTagCompound tag)
	    {
			UUID = tag.getUniqueId("owner");
			secure = tag.getBoolean("secure");
	    }
	    
		@Override
		public SPacketUpdateTileEntity getUpdatePacket() {
			return new SPacketUpdateTileEntity(this.getPos(), 1, this.getUpdateTag());
			
		}
		
		
}
