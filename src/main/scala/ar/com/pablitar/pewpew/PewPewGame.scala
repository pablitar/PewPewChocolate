package ar.com.pablitar.pewpew
import java.awt.Dimension
import scala.util.Random
import com.paranoidkiwi.chocolate.core.Game
import ar.com.pablitar.pewpew.resources.ResourceManager

object PewPewGame {
  val width = 1024
  val height = 768
  val randomizer = new Random()
}

class PewPewGame extends Game {

  protected  def initializeResources : Unit = {
    ResourceManager.initializeResources
  }

  protected  def setUpScenes(): Unit = {
    this.setCurrentScene(new PewPewGameScene(this))
  }

  def getDisplaySize(): Dimension = new Dimension(PewPewGame.width, PewPewGame.height)
  
  def getTitle(): String = "PewPew"

}