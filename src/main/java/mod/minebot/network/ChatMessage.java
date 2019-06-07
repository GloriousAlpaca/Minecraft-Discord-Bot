package mod.minebot.network;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ChatMessage implements IMessage{

	//Client Side Packet
			private String text;
			private boolean valid;
			
			public ChatMessage() {
				valid = false;
			}
			
			public ChatMessage(String ptext) {
				this.text = ptext;
				valid = true;
			}
			
	@Override
	public void fromBytes(ByteBuf buf) {
		int length = buf.readInt();
		this.text = buf.readCharSequence(length, StandardCharsets.UTF_8).toString();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(text.length());
		buf.writeCharSequence(text, StandardCharsets.UTF_8);
	}

	//Der MessageHandler für diese Klasse
	public static class Handler implements IMessageHandler<ChatMessage, IMessage>{

		@Override
		public IMessage onMessage(ChatMessage message, MessageContext ctx) {
			if(!message.valid && ctx.side != Side.CLIENT)
			return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message,ctx));
			return null;
		}
		
		public void processMessage(ChatMessage message, MessageContext ctx) {
			ctx.getClientHandler().handleChat(new SPacketChat(new TextComponentString(message.text)));
		}
	}
	
}
