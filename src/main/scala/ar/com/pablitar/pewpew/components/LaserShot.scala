package ar.com.pablitar.pewpew.components

import com.paranoidkiwi.chocolate.core.components.CollisionableComponent
import ar.com.pablitar.pewpew.PewPewGameScene
import ar.com.pablitar.pewpew.collisions.CollisionGroups
import ar.com.pablitar.pewpew.resources.ResourceManager
import com.paranoidkiwi.chocolate.core.collisions.RectangularBoundingBox
import com.paranoidkiwi.chocolate.core.reactions.annotations.collision.OnCollision
import com.paranoidkiwi.chocolate.core.reactions.events.collision.CollisionEvent
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent
import com.paranoidkiwi.chocolate.core.reactions.annotations.scene.OnUpdate
import com.paranoidkiwi.chocolate.core.GameScene

class LaserShot(scene: GameScene, x: Double, y: Double) extends CollisionableComponent(CollisionGroups.LASER_SHOT) with SpeedyComponent {

  this.setAppearance(ResourceManager.LASERSHOT_SPRITE)
  this.setScene(scene)
  this.setX(x)
  this.setY(y)

  this.setBoundingBox(new RectangularBoundingBox(-this.getWidth / 2, -this.getHeight / 2, this.getWidth, this.getHeight))

  @OnCollision(CollisionGroups.ENEMY)
  def collideWithEnemy(event: CollisionEvent) = {
    event.getCollidedComponent().asInstanceOf[EnemyShip].takeDamage(10)
    this.impact
  }

  this.speed = (0.0, -1500.0)

  @OnUpdate
  override def update(state: UpdateEvent) = {
    super.update(state)
    applySpeed(state)
    if (this.isOutsideOfTheScreen) {
      this.destroy
    }
  }

  def impact: Unit = {
    this.getScene.addComponent(ImpactEffect.laserShotImpact(this))
    this.destroy
  }

  override def getMaxX = Double.PositiveInfinity
  override def getMaxY = Double.PositiveInfinity

  override def getMinX = Double.NegativeInfinity
  override def getMinY = Double.NegativeInfinity
}