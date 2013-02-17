package ar.com.pablitar.pewpew.components

import java.awt.Color
import com.paranoidkiwi.chocolate.core.components.GameComponent
import com.paranoidkiwi.chocolate.core.components.VisibleComponent
import ar.com.pablitar.pewpew.PewPewGameScene
import ar.com.pablitar.pewpew.appearances.Rectangle
import com.paranoidkiwi.chocolate.core.appearances.Appearance

class PlainBackground(scene: PewPewGameScene, val color: Color) extends VisibleComponent {
  this.setScene(scene)
  this.setAppearance(new Rectangle(color, this.getGame().getDisplayWidth(), this.getGame().getDisplayHeight()))
}