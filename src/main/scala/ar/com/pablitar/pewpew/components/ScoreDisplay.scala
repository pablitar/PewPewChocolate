package ar.com.pablitar.pewpew.components

import java.awt.Color
import java.awt.Font

import com.paranoidkiwi.chocolate.core.appearances.Label
import com.paranoidkiwi.chocolate.core.components.VisibleComponent

import ar.com.pablitar.pewpew.PewPewGameScene

class ScoreDisplay(scene: PewPewGameScene) extends VisibleComponent with RichGameComponent {
  var score = 0
  
  this.setScene(scene)

  this.updateLabel

  this.setX(this.getGame.getDisplayWidth - this.getWidth - 40)
  this.setY(10)

  def updateLabel = {
    this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 16), Color.WHITE, "Score: " + score))
  }

  def sumScore = {
    score += 1
    this.updateLabel
  }
}