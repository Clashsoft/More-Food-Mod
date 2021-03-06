package clashsoft.mods.morefood.client.gui;

import clashsoft.cslib.minecraft.lang.I18n;
import clashsoft.cslib.minecraft.util.CSStringMC;
import clashsoft.mods.morefood.food.Food;
import cpw.mods.fml.client.GuiScrollingList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;

public class GuiFoodListSlot extends GuiScrollingList
{
	public GuiRecipeBook	parentGui;
	
	public GuiFoodListSlot(GuiRecipeBook parent)
	{
		super(Minecraft.getMinecraft(), parent.getGuiLeft(), parent.height, 0, parent.height, 0, 36);
		this.parentGui = parent;
	}
	
	@Override
	protected int getSize()
	{
		return this.parentGui.currentDisplayList.size();
	}
	
	@Override
	protected void elementClicked(int i, boolean flag)
	{
		this.parentGui.setRecipe(i);
	}
	
	@Override
	protected boolean isSelected(int i)
	{
		return i == this.parentGui.currentEntryID;
	}
	
	@Override
	protected void drawBackground()
	{
	}
	
	@Override
	protected void drawSlot(int id, int x, int y, int l, Tessellator tessellator)
	{
		Minecraft mc = Minecraft.getMinecraft();
		
		Food food = this.parentGui.currentDisplayList.get(id);
		String name = food.asStack().getDisplayName();
		
		int offsX = 0;
		
		name = CSStringMC.trimStringToRenderWidth(name, this.listWidth - offsX - 2);
		
		if (this.parentGui.filtered)
		{
			String s = I18n.getString(this.parentGui.categorySearch ? "search.match.category" : "search.match");
			mc.fontRenderer.drawString(s, offsX + 2, y + 2, 0xFF8100, true);
			y += 10;
		}
		
		mc.fontRenderer.drawString(name, offsX + 2, y + 2, 0xFFFFFF, true);
		
		String category = EnumChatFormatting.ITALIC + food.getCategory().getLocalizedName();
		category = CSStringMC.trimStringToRenderWidth(category, this.listWidth);
		
		mc.fontRenderer.drawString(category, offsX + 2, y + 12, food.getCategory().getColor(), true);
	}
}
