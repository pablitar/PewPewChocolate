package ar.com.pablitar.pewpew.appearances;

import java.awt.Color;
import java.awt.Graphics2D;

import com.paranoidkiwi.chocolate.core.appearances.SimpleAppearance;

public class Rectangle extends SimpleAppearance<Rectangle> {

	private Color color;
	private int width, height;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Rectangle(Color color, int width, int height) {
		this.color = color;
		this.setHeight(height);
		this.setWidth(width);
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	@Override
	public Rectangle copy() {
		return new Rectangle(this.color, (int) this.getHeight(),
				(int) this.getWidth());
	}
	
	// ****************************************************************
	// ** MODIFICATIONS/TRANSFORMATIONS
	// ****************************************************************

	@Override
	public Rectangle scale(double scaleX, double scaleY) {
		Rectangle newRect = this.copy();
		newRect.setWidth((int) (this.getWidth() * scaleX));
		newRect.setHeight((int) (this.getHeight() * scaleY));
		return newRect;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	protected void doRenderAt(int x, int y, Graphics2D graphics) {
		graphics.setColor(this.color);
		graphics.fillRect(x, y, (int) this.getWidth(), (int) this.getHeight());
	}

	public void update(double delta) {
	}

}
