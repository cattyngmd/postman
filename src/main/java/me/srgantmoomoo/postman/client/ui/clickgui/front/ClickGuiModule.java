package me.srgantmoomoo.postman.client.ui.clickgui.front;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickGuiModule extends Module {
	public static ClickGuiModule INSTANCE;
	
	public ModeSetting theme = new ModeSetting("theme", this, "new", "new", "old");
	public NumberSetting animationSpeed = new NumberSetting("animation", this, 150, 0, 1000, 50);
	public NumberSetting scrolls = new NumberSetting("scrollSpeed", this, 10, 0, 100, 1);
	public ModeSetting scrollMode = new ModeSetting("scroll", this, "container", "container", "screen");
	public ModeSetting description = new ModeSetting("description", this, "mouse", "mouse", "fixed");
	public ColorSetting enabledColor = new ColorSetting("enabledColor", this, new JColor(Reference.POSTMAN_COLOR, 255)); //(0, 157, 255, 255));
	public ColorSetting backgroundColor = new ColorSetting("bgColor", this, new JColor(0, 0, 0, 200)); //(0, 121, 194, 255));
	public ColorSetting settingBackgroundColor = new ColorSetting("settinBgColor", this, new JColor(0, 0, 0, 255));
	public ColorSetting outlineColor = new ColorSetting("settingsHighlight", this, new JColor(255, 255, 255, 255));
	public ColorSetting fontColor = new ColorSetting("categoryColor", this, new JColor(Reference.POSTMAN_COLOR, 255)); 
	public NumberSetting opacity = new NumberSetting("opacity", this, 255, 0, 255, 5);
	
	public ClickGuiModule() {
		super("clickGui", "classic hud", Keyboard.KEY_RSHIFT, Category.CLIENT);
		this.addSettings(scrollMode, scrolls, description, animationSpeed, fontColor, enabledColor, backgroundColor, settingBackgroundColor, outlineColor);
		INSTANCE = this;
	}
	
	public static Module getClickGuiModule() {
		return INSTANCE;
	}
	
	@Override
	public void onEnable() {
		Main.clickGui.enterGUI();
	}

	@Override
	public void onUpdate() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			this.setToggled(!toggled);
		}
		if(ModuleManager.getModuleByName("hudEditor").isToggled()) {
			this.setToggled(!toggled);
		}
		
	}
	
	private ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");
	private final ResourceLocation watermark = new ResourceLocation(Reference.MOD_ID, "textures/postman-logo-transparent.png");
	@Override
	public void onRender() {
		ScaledResolution sr = new ScaledResolution(mc);
		mc.renderEngine.bindTexture(watermark);
		Gui.drawScaledCustomSizeModalRect(0, sr.getScaledHeight() - 80, 0, 0, 80, 80, 80, 80, 80, 80);
	}
}
