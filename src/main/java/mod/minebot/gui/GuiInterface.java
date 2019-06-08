package mod.minebot.gui;

import java.io.IOException;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import mod.minebot.network.InterfacetoTileMessage;
import mod.minebot.network.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;

public class GuiInterface extends GuiScreen{
	
    /*Textfelder*/
    GuiTextField messageTextField;
    /*Buttons*/
    private GuiButton doneBtn;
    private GuiButton cancelBtn;
    private GuiButton modeBtn;
    private GuiButton secureBtn;
    /*Variablen*/
    private boolean secure;
    private boolean sender;
    public String text;
    BlockPos pos;

    
    public GuiInterface(BlockPos ppos, boolean psecure, boolean psender, String ptext) {
    	this.pos = ppos;
    	this.secure = psecure;
    	this.sender = psender;
    	this.text = ptext;
    }
    
    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        this.messageTextField.updateCursorCounter();
        updateGui();
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
        this.modeBtn = this.addButton(new GuiButton(2, this.width / 2 - 50 - 100 - 4,this.height / 4 + 120 + 12-24, 150, 20, I18n.format("minebot.button.sender")));
        this.secureBtn = this.addButton(new GuiButton(4, this.width / 2 + 4,this.height / 4 + 120 + 12-24, 150, 20, I18n.format("minebot.button.secure")));
        this.messageTextField = new GuiTextField(3, this.fontRenderer, this.width / 2 - 150, 50, 300, 20);
        this.messageTextField.setMaxStringLength(2000);
        this.messageTextField.setFocused(true);
        this.doneBtn.enabled = false;
        this.modeBtn.enabled = false;
        this.secureBtn.enabled = false;
        this.messageTextField.setText(text);
    }
    
    public void updateGui()
    {
        this.doneBtn.enabled = true;
        this.modeBtn.enabled = true;
        this.secureBtn.enabled = true;
        updateSecure();
        updateMode();
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
        	//Cancel Button
            if (button.id == 1)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            //Done Button
            else if (button.id == 0)
            {
            	PacketHandler.INSTANCE.sendToServer(
            			new InterfacetoTileMessage(
            					pos,
            					messageTextField.getText(), 
            					sender, 
            					secure ));
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            //Mode Button
            else if (button.id == 2)
            {
            	if(sender) {
            		sender = false;
            	}
            	else
            		sender = true;
            		updateMode();
            }
            //Security Button
            else if (button.id == 4)
            {
            	if(secure) {
            		secure=false;
            	}
            	else
            		secure = true;
            	updateSecure();
            }
        }
    }
    
    public void updateSecure() {
    	if(secure) {
    		this.secureBtn.displayString = I18n.format("minebot.button.secure");
    	}
    	else
    		this.secureBtn.displayString = I18n.format("minebot.button.notsecure");
    }
    
    public void updateMode() {
    	if(sender) {
    		this.modeBtn.displayString = I18n.format("minebot.button.sender");
    	}
    	else
    		this.modeBtn.displayString = I18n.format("minebot.button.receiver");
    }
}
