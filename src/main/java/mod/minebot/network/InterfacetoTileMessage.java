package mod.minebot.network;



import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import mod.minebot.tileentity.TileEntityInterface;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class InterfacetoTileMessage implements IMessage{
	//Server Side Packet
		private BlockPos pos;
		private String text;
		private boolean sender;
		private boolean secure;
		private boolean valid;
		
		public InterfacetoTileMessage() {
			valid = false;
		}
		
		public InterfacetoTileMessage(BlockPos ppos, String ptext ,boolean psender,boolean psecure) {
			this.pos = ppos;
			this.text = ptext;
			this.sender = psender;
			this.secure = psecure;
			valid = true;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			// Lies Pos aus dem Buffer
			try {
				this.pos = new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
				int length = buf.readInt();
				this.text = buf.readCharSequence(length, StandardCharsets.UTF_8).toString();
				this.sender = buf.readBoolean();
				this.secure = buf.readBoolean();
			}
			catch(IndexOutOfBoundsException ioe) {
				return;
			}
			valid = true;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			// Schreibe Energy und dann Position in Buffer
			buf.writeInt(pos.getX());
			buf.writeInt(pos.getY());
			buf.writeInt(pos.getZ());
			buf.writeInt(text.length());
			buf.writeCharSequence(text, StandardCharsets.UTF_8);
			buf.writeBoolean(sender);
			buf.writeBoolean(secure);
		}
		
		//Der MessageHandler für diese Klasse
		public static class Handler implements IMessageHandler<InterfacetoTileMessage, IMessage>{

			@Override
			public IMessage onMessage(InterfacetoTileMessage message, MessageContext ctx) {
				if(!message.valid && ctx.side != Side.SERVER)
				return null;
				FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message,ctx));
				return null;
			}
			
			public void processMessage(InterfacetoTileMessage message, MessageContext ctx) {
				TileEntity te = ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
				if(te == null)
					return;
				if(te instanceof TileEntityInterface) {
					((TileEntityInterface)te).text = message.text;
					((TileEntityInterface)te).sender = message.sender;
					((TileEntityInterface)te).secure = message.secure;
					te.markDirty();
				}
			}
		}
}
