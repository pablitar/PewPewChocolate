package ar.com.pablitar.pewpew.components

import ar.com.pablitar.pewpew.math.Vector2D
import ar.com.pablitar.pewpew.math.Vector2D.toVector2D
import ar.com.pablitar.pewpew.utils.MathUtils.bound
import com.paranoidkiwi.chocolate.core.Game
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent

trait SpeedyComponent extends RichGameComponent {

  var speed: Vector2D = (0.0, 0.0)

  def applySpeed(state: UpdateEvent): Unit = {
    this.setX(bound(this.getX + (speed.x1 * state.getDelta), this.getMinX, this.getMaxX))
    this.setY(bound(this.getY + (speed.x2 * state.getDelta), this.getMinY, this.getMaxY))
  }

  def getMaxY: Double = this.getGame.getDisplayHeight
  def getMaxX: Double = this.getGame.getDisplayWidth
}