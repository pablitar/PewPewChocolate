package ar.com.pablitar.pewpew.appearances

import java.awt.Graphics2D
import java.awt.Color
import java.awt.FontMetrics
import java.awt.Font
import com.paranoidkiwi.chocolate.core.appearances.SimpleAppearance
import ar.com.pablitar.pewpew.components.Bar
import com.paranoidkiwi.chocolate.core.appearances.Label
import ar.com.pablitar.pewpew.utils.MathUtils.double2Int

class BarAppearance(bar: Bar, width: Int, height: Int, backColor: Color, frontColor: Color, marginRatio: Double) extends SimpleAppearance[BarAppearance] {

  //TODO: Font Color
  val label = new Label(new Font(Font.SANS_SERIF, Font.BOLD, 12), Color.WHITE, bar.label + ":")

  def this(bar: Bar, width: Int, backColor: Color, frontColor: Color) = this(bar, width, width / 20, backColor, frontColor, 0.02)

  def scale(scaleX: Double, scaleY: Double): BarAppearance = {
    new BarAppearance(bar, (width * scaleX).toInt, (height * scaleY).toInt, backColor, frontColor, marginRatio)
  }

  def copy[BarAppearance](): BarAppearance = { this.clone.asInstanceOf[BarAppearance] }

  def getWidth = width
  def getHeight = height

  protected def doRenderAt(x: Int, y: Int, graphics: Graphics2D): Unit = {
    label.renderAt(bar.getX.toInt, bar.getY.toInt, graphics)
    
    graphics.setColor(backColor);
    val realX = x + label.getWidth() + 10
    
    graphics.fillRect(realX, y, width, height);

    val totalMargin = width * marginRatio

    graphics.setColor(frontColor);
    graphics.fillRect(realX + (totalMargin / 2), y + (totalMargin / 2), (width - totalMargin) * (bar.getCurrentValue.toDouble / bar.getMaxValue), height - totalMargin);
  }

  def update(delta: Double): Unit = {}

}