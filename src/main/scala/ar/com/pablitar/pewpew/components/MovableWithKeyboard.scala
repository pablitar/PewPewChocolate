package ar.com.pablitar.pewpew.components

import ar.com.pablitar.pewpew.math.Vector2D._
import ar.com.pablitar.pewpew.math.Vector2D
import com.paranoidkiwi.chocolate.core.components.VisibleComponent
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent
import com.paranoidkiwi.chocolate.core.reactions.annotations.scene.OnUpdate
import com.paranoidkiwi.chocolate.core.reactions.annotations.io.keyboard.OnKeyHold
import com.paranoidkiwi.chocolate.core.reactions.events.io.keyboard.KeyEvent
import com.paranoidkiwi.chocolate.core.reactions.enums.Key

trait MovableWithKeyboard extends VisibleComponent with SpeedyComponent {
  var accel: Vector2D = (0.0, 0.0)

  def maxAccel: Vector2D
  def maxSpeed: Vector2D
  def deaccelRate: Double = 0.5

  def toIdleAccel: Vector2D =
    speed.toZero(maxAccel)

  @OnUpdate
  override def update(state: UpdateEvent): Unit = {
    accel = (0.0, 0.0)

    super.update(state)

    this.speed = (this.speed + accel * state.getDelta) absoluteMin maxSpeed
    this.speed = (this.speed.sumAndClip(this.speed.toZero(this.getDeaccelVector * state.getDelta)))

    this.applySpeed(state)
  }

  @OnKeyHold
  def checkKeyBeingHeld(anEvent: KeyEvent) {
    anEvent.getKey() match {
      case Key.LEFT => accel.x1 = -maxAccel.x1
      case Key.RIGHT => accel.x1 = maxAccel.x1
      case Key.UP => accel.x2 = -maxAccel.x2
      case Key.DOWN => accel.x2 = maxAccel.x2
      case Key.END => accel = (-maxAccel.x1, maxAccel.x2)
      case Key.HOME => accel = (-maxAccel.x1, -maxAccel.x2)
      case Key.PGDN => accel = (maxAccel.x1, maxAccel.x2)
      case Key.PGUP => accel = (maxAccel.x1, -maxAccel.x2)
      case _ => 
    }
  }

  def getDeaccelVector: Vector2D =
    (if (accel.x1 == 0) maxAccel.x1 else 0.0, if (accel.x2 == 0) maxAccel.x2 else 0.0) * this.deaccelRate
}