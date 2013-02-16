package ar.com.pablitar.pewpew.behaviours

import com.paranoidkiwi.chocolate.core.components.GameComponent
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent
import ar.com.pablitar.pewpew.components.EnemyShip
import com.paranoidkiwi.chocolate.core.reactions.annotations.scene.OnUpdate

class Wave(x: Double, y: Double, waveLength: Int, waveCooldown: Double) extends GameComponent {
  var toSpawn = waveLength

  var currentCooldown = waveCooldown

  @OnUpdate
  def update(state: UpdateEvent): Unit = {
    currentCooldown -= state.getDelta
    if (currentCooldown <= 0) {
      this.getScene.addComponent(EnemyShip.default(x, y))
      currentCooldown = waveCooldown
      toSpawn -= 1
      if (toSpawn <= 0) {
        this.destroy
      }
    }
  }

}