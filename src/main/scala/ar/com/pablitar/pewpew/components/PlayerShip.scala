package ar.com.pablitar.pewpew.components

import com.paranoidkiwi.chocolate.core.components.CollisionableComponent
import ar.com.pablitar.pewpew.PewPewGameScene
import ar.com.pablitar.pewpew.collisions.CollisionGroups
import ar.com.pablitar.pewpew.resources.ResourceManager
import com.paranoidkiwi.chocolate.core.collisions.CircularBoundingBox
import ar.com.pablitar.pewpew.math.Vector2D
import ar.com.pablitar.pewpew.math.Vector2D._
import com.paranoidkiwi.chocolate.core.reactions.annotations.scene.OnUpdate
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent
import com.paranoidkiwi.chocolate.core.reactions.annotations.io.keyboard.OnKeyHold
import com.paranoidkiwi.chocolate.core.reactions.annotations.io.keyboard.OnKeyPressed
import com.paranoidkiwi.chocolate.core.reactions.enums.Key
import com.paranoidkiwi.chocolate.core.reactions.annotations.io.keyboard.OnKeyReleased
import com.paranoidkiwi.chocolate.core.reactions.events.io.keyboard.KeyEvent

class PlayerShip(scene: PewPewGameScene) extends CollisionableComponent(CollisionGroups.PLAYER) with MovableWithKeyboard {//MovableWithKeyboard[PewPewGameScene] with CircularGameComponent {

  val coolDownTime = 0.12

  var firing = false
  var cooldown = 0.0

  val maxHealth = 100
  val maxShield = 50.0
  val shieldRegenRate = 3.0

  var health = maxHealth
  var shield = maxShield

  this.setZ(2)
  this.setScene(scene)
  this.setAppearance(ResourceManager.SPACE_SHIP_SPRITE)
  
  val radius = (this.getWidth min this.getHeight) / 2

  this.setBoundingBox(new CircularBoundingBox(-radius, -radius, radius))

  this.setX(this.getGame.getDisplayWidth / 2)
  this.setY(this.getGame.getDisplayHeight * 0.8)

  def maxAccel(): Vector2D = (3000.0, 3000.0)
  def maxSpeed(): Vector2D = (600.0, 600.0)

  @OnUpdate
  override def update(state: UpdateEvent): Unit = {
    super.update(state)

    if (this.firing) {
      this.coolDownAndFire(state.getDelta)
    }

    this.regenShield(state)

    this.cooldown = (this.cooldown - state.getDelta) max 0
  }

  def regenShield(state: UpdateEvent): Unit = {
    this.shield = (this.shield + shieldRegenRate * state.getDelta) min maxShield
  }

  def coolDownAndFire(delta: Double): Unit = {
    if (this.cooldown - delta <= 0) {
      this.doFire
      this.cooldown = coolDownTime
    }
  }

  def doFire = {
    this.getScene.addComponent(new LaserShot(this.getScene(), this.getX, this.getY))
  }

  @OnKeyPressed(Key.SPACE)
  def firePressed(): Unit = {
    this.firing = true
  }

  @OnKeyReleased(value = Key.SPACE)
  def fireReleased(): Unit = {
    this.firing = false
  }

  def getCenterX: Double = {
    this.getX + this.getAppearance.getWidth / 2
  }

  def takeDamage(damage: Int) = {
    val damageToHealth = (damage - this.shield) max 0
    this.shield = (shield - damage) max 0

    this.health = (this.health - damageToHealth).toInt max 0

    if (this.health <= 0) {
      this.death
    }
  }

  def death: Unit = {
    this.scene.addComponent(ImpactEffect.explosion(this))
    this.scene.gameOver
    this.destroy
  }
}