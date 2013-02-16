package ar.com.pablitar.pewpew.components

import com.paranoidkiwi.chocolate.core.Game
import com.paranoidkiwi.chocolate.core.appearances.Appearance
import com.paranoidkiwi.chocolate.core.components.VisibleComponent
import com.paranoidkiwi.chocolate.core.GameScene

trait RichGameComponent {

  def setX(x: Double): Unit
  def setY(x: Double): Unit
  def getX: Double
  def getY: Double
  def getMinX = 0.0
  def getMinY = 0.0
  def getGame: Game
  protected def getAppearance: Appearance
  def setScene(scene: GameScene): Unit

  def isOutsideOfTheScreen: Boolean = {
    this.isAtLeftOfTheScreen || this.isAtRightOfTheScreen ||
      this.isOverTheScreen || this.isBelowTheScreen
  }

  def leftBorder = this.getX - this.getAppearance.getWidth() / 2
  def rightBorder = this.getX + this.getAppearance.getWidth() / 2
  def topBorder = this.getY - this.getAppearance.getHeight() / 2
  def bottomBorder = this.getY + this.getAppearance.getHeight() / 2

  def isBelowTheScreen: Boolean = {
    this.topBorder >= this.getGame.getDisplayHeight()
  }

  def isOverTheScreen: Boolean = {
    this.bottomBorder <= 0
  }

  def isAtRightOfTheScreen: Boolean = {
    this.leftBorder >= this.getGame.getDisplayWidth()
  }

  def isAtLeftOfTheScreen: Boolean = {
    this.rightBorder <= 0
  }

  def initializeFrom(component: VisibleComponent) = {
    this.setScene(component.getScene)
    this.setX(component.getX)
    this.setY(component.getY)
  }

  def getWidth = this.getAppearance.getWidth
  def getHeight = this.getAppearance.getHeight

}