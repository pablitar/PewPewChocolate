package ar.com.pablitar.pewpew.behaviours

import ar.com.pablitar.pewpew.PewPewGame
import ar.com.pablitar.pewpew.resources.ResourceManager
import com.paranoidkiwi.chocolate.core.components.GameComponent
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent
import com.paranoidkiwi.chocolate.core.reactions.annotations.scene.OnUpdate

class EnemySpawner(spawnCoolDown: Double, waveLength: Int, waveCooldown: Double) extends GameComponent {

  var spawnX: () => Double = () => PewPewGame.randomizer.nextDouble * this.getGame().getDisplayWidth().toDouble
  var spawnY: () => Double = () => -ResourceManager.ENEMY_SHIP_SPRITE.getHeight()

  var currentMaxCoolDown = spawnCoolDown
  var currentCoolDown = currentMaxCoolDown

  @OnUpdate
  def update(state: UpdateEvent): Unit = {
    currentCoolDown -= state.getDelta

    if (currentCoolDown <= 0) {
      this.createWave(state)
      currentCoolDown = currentMaxCoolDown
      //TODO: Hardcoded
      currentMaxCoolDown = (currentMaxCoolDown - 2.0) max 2
    }
  }

  def createWave(state: UpdateEvent): Unit = {
    this.getScene().addComponent(new Wave(spawnX(), spawnY(), waveLength, waveCooldown))
  }
}