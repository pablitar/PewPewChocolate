package ar.com.pablitar.pewpew.components

import ar.com.pablitar.pewpew.math.Vector2D._
import ar.com.pablitar.pewpew.behaviours.NullBehaviour
import ar.com.pablitar.pewpew.behaviours.Behaviour._
import com.paranoidkiwi.chocolate.core.reactions.annotations.collision.OnCollision
import com.paranoidkiwi.chocolate.core.reactions.events.collision.CollisionEvent
import com.paranoidkiwi.chocolate.core.components.CollisionableComponent
import ar.com.pablitar.pewpew.collisions.CollisionGroups
import ar.com.pablitar.pewpew.resources.ResourceManager
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent
import com.paranoidkiwi.chocolate.core.reactions.annotations.scene.OnUpdate
import ar.com.pablitar.pewpew.PewPewGameScene
import com.paranoidkiwi.chocolate.core.collisions.RectangularBoundingBox

object EnemyShip {
  def default(x: Double, y: Double): EnemyShip = new EnemyShip(25, x, y)
    .withBehaviour(zigZagMovement((250.0, 70.0)))
}

class EnemyShip(health: Int, x: Double, y: Double) extends CollisionableComponent(CollisionGroups.ENEMY) with SpeedyComponent {

  val behaviour = new NullBehaviour(this)

  var currentHealth = health

  this.setX(x)
  this.setY(y)

  @OnCollision(CollisionGroups.PLAYER)
  def collideWithPlayer(event: CollisionEvent) = {
    event.getCollidedComponent().asInstanceOf[PlayerShip].takeDamage(currentHealth)
    this.explode
  }

  this.setAppearance(ResourceManager.ENEMY_SHIP_SPRITE)
  this.setBoundingBox(new RectangularBoundingBox(-this.getWidth / 2, -this.getHeight / 2, this.getWidth, this.getHeight))

  @OnUpdate
  override def update(state: UpdateEvent): Unit = {
    super.update(state)
    behaviour.update(state)
  }

  def takeDamage(damage: Int): Unit = {
    currentHealth -= damage
    if (currentHealth <= 0) {
      this.explode
    }
  }

  def withBehaviour(behaviourFactory: EnemyShip => NullBehaviour) = {
    behaviour.setNextBehaviour(behaviourFactory(this))
    this
  }

  //TODO: Listener, para sacar este cast feo.
  def explode: Unit = {
    this.getScene.addComponent(ImpactEffect.explosion(this))
    this.getScene.asInstanceOf[PewPewGameScene].sumScore
    this.destroy()
  }

  override def getMaxY = Double.PositiveInfinity
  override def getMinY = Double.NegativeInfinity

}