package ar.com.pablitar.pewpew.components

import com.paranoidkiwi.chocolate.core.components.VisibleComponent
import com.paranoidkiwi.chocolate.core.reactions.annotations.scene.OnUpdate
import ar.com.pablitar.pewpew.appearances.Rectangle
import java.awt.Color
import ar.com.pablitar.pewpew.PewPewGame._
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent

object Star {
  val maxSpeed = 250
  val minSpeed = 100
}

class Star(x: Double, y: Double) extends VisibleComponent with SpeedyComponent {
  this.setX(x)
  this.setY(y)
  this.setZ(-1)

  initSpeed
  
  val size = randomizer.nextInt(4) + 1

  this.setAppearance(new Rectangle(Color.LIGHT_GRAY, size, size))

  override def getMaxY = Double.PositiveInfinity
  override def getMinY = Double.NegativeInfinity

  @OnUpdate
  override def update(state: UpdateEvent) = {
    super.update(state)
    this.applySpeed(state)
    if (this.isBelowTheScreen) {
      this.setY(-3);
      this.setX(randomizer.nextDouble * this.getMaxX)
      initSpeed
    }
  }
  
  def initSpeed = {
    speed = (0.0, randomizer.nextDouble * (Star.maxSpeed-Star.minSpeed) + Star.minSpeed)
  }
}