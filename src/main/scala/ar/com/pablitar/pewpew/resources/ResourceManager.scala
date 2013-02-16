package ar.com.pablitar.pewpew.resources

import scala.math.Pi
import com.paranoidkiwi.chocolate.core.appearances.Animation
import com.paranoidkiwi.chocolate.core.loaders.ResourceLoader
import com.paranoidkiwi.chocolate.core.loaders.CachedResourceLoader
import com.paranoidkiwi.chocolate.core.loaders.SimpleResourceLoader
import com.paranoidkiwi.chocolate.core.appearances.SimpleAppearance
import com.paranoidkiwi.chocolate.core.appearances.Sprite
import com.paranoidkiwi.chocolate.core.appearances.Appearance

object ResourceManager {

  val loader = new CachedResourceLoader(new SimpleResourceLoader());
  
  val SPRITES_SCALE = 0.8
  
  def center[T<:Appearance](appearance:T) : T= {
    	val copy = appearance.copy().asInstanceOf[T];
		
		copy.setX(-copy.getWidth()/2);
		copy.setY(-copy.getHeight()/2);
		
		copy;
  }
  
  val SPACE_SHIP_SPRITE = this.center(loader.loadSprite("/png/player.png").scale(SPRITES_SCALE))
  val LASERSHOT_SPRITE = this.center(loader.loadSprite("/png/laserRed.png").scale(SPRITES_SCALE))
  val LASERSHOT_IMPACT_SPRITE = this.center(loader.loadSprite("/png/laserRedShot.png").scale(SPRITES_SCALE));

  val EXPLOSION_SPRITE = this.center(loader.loadSprite("/png/explosion.png").scale(SPRITES_SCALE));

  val EXPLOSION = this.center(new Animation(0.1, EXPLOSION_SPRITE, this.center(EXPLOSION_SPRITE.rotate(Pi / 4)),
      this.center(EXPLOSION_SPRITE.rotate(Pi / 2)), 
      this.center(EXPLOSION_SPRITE.rotate(Pi * 0.75)),
      this.center(EXPLOSION_SPRITE.rotate(Pi))))

  val ENEMY_SHIP_SPRITE = this.center(loader.loadSprite("/png/enemyShip.png").scale(SPRITES_SCALE));

  def initializeResources: Unit = {

  }

}