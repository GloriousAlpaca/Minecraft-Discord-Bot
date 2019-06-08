package mod.minebot.network;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import mod.minebot.MINEBOT;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class InterfaceGuiMessage implements IMessage{
	
	//Client Side Packet
	private boolean secure;
	private String text;
	private boolean sender;
	private boolean valid;
	private BlockPos pos;
	
	public InterfaceGuiMessage() {
		valid = false;
	}
	
	public InterfaceGuiMessage(String text, boolean secure, boolean sender, BlockPos ppos) {
		this.secure = secure;
		this.sender = sender;
		this.text = text;
		this.pos = ppos;
		valid = true;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		try {
			this.secure = buf.readBoolean();
			this.sender = buf.readBoolean();
			int length = buf.readInt();
			this.text = buf.readCharSequence(length, StandardCharsets.UTF_8).toString();
			this.pos = new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
		}
		catch(IndexOutOfBoundsException ioe) {
			return;
		}
		this.valid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(secure);
		buf.writeBoolean(sender);
		buf.writeInt(text.length());
		buf.writeCharSequence(text, StandardCharsets.UTF_8);
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}
	
	public static class Handler implements IMessageHandler<InterfaceGuiMessage, IMessage>{

		@Override
		public IMessage onMessage(InterfaceGuiMessage message, MessageContext ctx) {
				if(!message.valid && ctx.side != Side.CLIENT)
				return null;
			Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message,ctx));
			return null;
		}
		
		public void processMessage(InterfaceGuiMessage message, MessageContext ctx) {
			try {
				MINEBOT.proxy.displayGui(message.pos, message.secure, message.sender, message.text);
			} catch(Exception e) {
				
			}
		}
	}
}
