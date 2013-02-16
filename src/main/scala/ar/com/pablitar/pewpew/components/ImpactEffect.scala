package ar.com.pablitar.pewpew.components

import ar.com.pablitar.pewpew.resources.ResourceManager
import com.paranoidkiwi.chocolate.core.GameScene
import com.paranoidkiwi.chocolate.core.appearances.Appearance
import com.paranoidkiwi.chocolate.core.components.VisibleComponent
import com.paranoidkiwi.chocolate.core.reactions.events.scene.UpdateEvent

object ImpactEffect {
  def laserShotImpact(shot: LaserShot) = new ImpactEffect(shot, ResourceManager.LASERSHOT_IMPACT_SPRITE, 0.1)
  def explosion(component: (VisibleComponent with SpeedyComponent)) = new ImpactEffect(component, ResourceManager.EXPLOSION.copy(), 0.5)
}

class ImpactEffect(component: (VisibleComponent with SpeedyComponent), appearance: Appearance, duration: Double) extends VisibleComponent with SpeedyComponent {
  var current = 0.0

  this.initializeFrom(component)

  speed = component.speed * 0.1

  this.setAppearance(appearance)

  override def update(state: UpdateEvent): Unit = {
    super.update(state)
    if (current >= duration) {
      this.destroy
    } else {
      applySpeed(state)
      current += state.getDelta
    }
  }

}