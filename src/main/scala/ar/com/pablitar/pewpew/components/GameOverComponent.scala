package ar.com.pablitar.pewpew.components

import java.awt.Font
import java.awt.Color
import com.paranoidkiwi.chocolate.core.components.VisibleComponent
import ar.com.pablitar.pewpew.PewPewGameScene
import com.paranoidkiwi.chocolate.core.appearances.Label
import com.paranoidkiwi.chocolate.core.GameScene
import com.paranoidkiwi.chocolate.core.reactions.annotations.io.keyboard.OnKeyPressed
import com.paranoidkiwi.chocolate.core.reactions.enums.Key

class GameOverComponent(scene: PewPewGameScene) extends VisibleComponent {
  this.setScene(scene)

  val gameOverLabel = new Label(new Font(Font.SERIF, Font.BOLD, 48), Color.ORANGE, "Game Over!", "Press enter to restart")

  this.setAppearance(gameOverLabel)

  this.setX(this.getGame.getDisplayWidth / 2 - gameOverLabel.getWidth / 2)

  this.setY(300)
  this.setZ(4)

  @OnKeyPressed(Key.ENTER)
  def restartOnEnd = {
    scene.restartOnEnd
  }

}