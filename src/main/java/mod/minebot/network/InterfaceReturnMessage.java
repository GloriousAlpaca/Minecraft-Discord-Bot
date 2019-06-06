package mod.minebot.network;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import mod.minebot.gui.GuiInterface;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class InterfaceReturnMessage implements IMessage{
	
	//Client Side Packet
	private boolean secure;
	private String text;
	private boolean sender;
	private boolean valid;
	
	public InterfaceReturnMessage() {
		valid = false;
	}
	
	public InterfaceReturnMessage(String text, boolean secure, boolean sender) {
		this.secure = secure;
		this.sender = sender;
		this.text = text;
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
	}
	
	public static class Handler implements IMessageHandler<InterfaceReturnMessage, IMessage>{

		@Override
		public IMessage onMessage(InterfaceReturnMessage message, MessageContext ctx) {
				if(!message.valid && ctx.side != Side.CLIENT)
				return null;
			Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message,ctx));
			return null;
		}
		
		public void processMessage(InterfaceReturnMessage message, MessageContext ctx) {
			try {
				GuiInterface.initsender = message.sender;
				GuiInterface.initsecure = message.secure;
				GuiInterface.inittext = message.text;
			} catch(Exception e) {
				
			}
		}
	}
}
