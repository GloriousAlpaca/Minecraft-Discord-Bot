package mod.minebot.gui;

import java.io.IOException;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import io.netty.buffer.Unpooled;
import mod.minebot.tileentity.TileEntityInterface;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.util.TabCompleter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public class GuiInterface extends GuiScreen{
	
	
    private final TileEntityInterface te;
    /*Textfelder*/
    private GuiTextField messageTextField;
    /*Buttons*/
    private GuiButton doneBtn;
    private GuiButton cancelBtn;
    private GuiButton modeBtn;
    
    public GuiInterface(TileEntityInterface tileentity) {
    	this.te = tileentity;
    }
    
    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        this.messageTextField.updateCursorCounter();
    }
    
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    @Override
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.doneBtn = this.addButton(new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.done")));
        this.cancelBtn = this.addButton(new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel")));
        this.modeBtn = this.addButton(new GuiButton(2, this.width / 2 - 50 - 100 - 4, 165, 100, 20, I18n.format("minebot.button.mode")));
        this.messageTextField = new GuiTextField(3, this.fontRenderer, this.width / 2 - 150, 50, 300, 20);
        this.messageTextField.setMaxStringLength(2000);
        this.messageTextField.setFocused(true);
        this.doneBtn.enabled = false;
        this.modeBtn.enabled = false;
    }
    
    public void updateGui()
    {
        this.doneBtn.enabled = true;
        this.modeBtn.enabled = true;
    }
    
    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }
    
    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        this.messageTextField.textboxKeyTyped(typedChar, keyCode);
        if (keyCode != 28 && keyCode != 156)
        {
            if (keyCode == 1)
            {
                this.actionPerformed(this.cancelBtn);
            }
        }
        else
        {
            this.actionPerformed(this.doneBtn);
        }
    }
    
    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.messageTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format("minebot.text.setText"), this.width / 2, 20, 16777215);
        this.drawString(this.fontRenderer, I18n.format("minebot.text.text"), this.width / 2 - 150, 40, 10526880);
        this.messageTextField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 1)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (button.id == 0)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (button.id == 2)
            {

            }
        }
    }
    
}
