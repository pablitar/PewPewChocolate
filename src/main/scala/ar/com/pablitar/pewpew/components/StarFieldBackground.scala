package ar.com.pablitar.pewpew.components

import ar.com.pablitar.pewpew.PewPewGame._
import ar.com.pablitar.pewpew.PewPewGameScene
import java.awt.Color

object StarFieldBackground {
  val maxStars = 100
}

class StarFieldBackground(scene: PewPewGameScene) extends PlainBackground(scene, Color.DARK_GRAY) {
  this.setZ(-2)
  this.setScene(scene)

  this.initializeStars

  def initializeStars = {
    for (i <- 1 to StarFieldBackground.maxStars) {
      scene.addComponent(new Star(randomizer.nextDouble * getGame.getDisplayWidth,
        randomizer.nextDouble * getGame.getDisplayHeight))
    }
  }
}