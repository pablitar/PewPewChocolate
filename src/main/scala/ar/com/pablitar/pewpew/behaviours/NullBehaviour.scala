package ar.com.pablitar.pewpew.behaviours

import ar.com.pablitar.pewpew.components.EnemyShip
import ar.com.pablitar.pewpew.math.Vector2D
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent


object Behaviour {
  def zigZagMovement(speed:Vector2D) = (aShip:EnemyShip) => new ZigZagBehaviour(aShip, speed)
}

class NullBehaviour(val ship: EnemyShip) {

  var nextBehaviour: Option[NullBehaviour] = None

  def update(state: UpdateEvent): Unit = {
    if (this.doUpdate(state)) {
      this.callNextBehaviour(state)
    }
  }

  def doUpdate(state: UpdateEvent): Boolean = true

  def callNextBehaviour(state: UpdateEvent): Unit = {
    nextBehaviour match {
      case Some(behaviour) => behaviour.update(state)
      case None => ()
    }
  }
  
  def setNextBehaviour(aBehaviour: NullBehaviour): Unit = {
    nextBehaviour match {
      case Some(behaviour) => behaviour.setNextBehaviour(aBehaviour)
      case None => nextBehaviour = Some(aBehaviour) 
    }
  }

}