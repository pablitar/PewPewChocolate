package ar.com.pablitar.pewpew.behaviours

import ar.com.pablitar.pewpew.PewPewGame
import ar.com.pablitar.pewpew.components.EnemyShip
import ar.com.pablitar.pewpew.math.Vector2D
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent

class ZigZagBehaviour(ship: EnemyShip, targetSpeed: Vector2D) extends NullBehaviour(ship) {

  var xDirection = if (PewPewGame.randomizer.nextDouble >= 0.5) 1 else -1

  override def doUpdate(state: UpdateEvent): Boolean = {
    this.removeIfOutsideTheScreen
    ship.speed = this.getSpeedForShip
    ship.applySpeed(state)
    true
  }

  def removeIfOutsideTheScreen: Unit = {
    if((targetSpeed.x2 >= 0 && ship.isBelowTheScreen) || (targetSpeed.x2 <= 0 && ship.isOverTheScreen)) {
      ship.destroy
    }
  }

  def getSpeedForShip: Vector2D = {
    if (ship.getX <= ship.getMinX) {
      xDirection = 1
    } else if (ship.getX >= ship.getMaxX) {
      xDirection = -1
    }

    (targetSpeed.x1 * xDirection, targetSpeed.x2)
  }

}