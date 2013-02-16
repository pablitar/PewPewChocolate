package ar.com.pablitar.pewpew

import java.awt.Graphics2D
import scala.collection.JavaConversions._
import ar.com.pablitar.pewpew.behaviours.EnemySpawner
import ar.com.pablitar.pewpew.components.HealthBar
import ar.com.pablitar.pewpew.components.PlayerShip
import ar.com.pablitar.pewpew.components.ScoreDisplay
import ar.com.pablitar.pewpew.components.ShieldBar
import com.paranoidkiwi.chocolate.core.GameScene
import com.paranoidkiwi.chocolate.core.Game
import ar.com.pablitar.pewpew.components.GameOverComponent
import ar.com.pablitar.pewpew.components.StarFieldBackground

class PewPewGameScene(game: Game) extends GameScene {
  this.setGame(game)

  this.addComponent(new StarFieldBackground(this))

  val playerShip = new PlayerShip(this)

  this.addComponent(playerShip)

  this.addComponent(new HealthBar(playerShip, 10, 10))
  this.addComponent(new ShieldBar(playerShip, 10, 40))

  this.addComponent(new EnemySpawner(8.0, 10, 0.5))
  
  val scoreDisplay = new ScoreDisplay(this)
  
  this.addComponent(scoreDisplay)


  def gameOver = {
    this.addComponent(new GameOverComponent(this));
  }

  def restart = {
    this.getGame.setCurrentScene(new PewPewGameScene(this.getGame))
  }

  var isRestartOnEnd = false

  def restartOnEnd = {
    isRestartOnEnd = true
  }

  override def takeStep(graphics: Graphics2D) = {
    super.takeStep(graphics)

    if (isRestartOnEnd) restart
  }
  
  def sumScore = {
    scoreDisplay.sumScore
  }
}