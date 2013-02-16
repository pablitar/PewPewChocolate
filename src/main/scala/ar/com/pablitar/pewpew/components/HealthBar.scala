package ar.com.pablitar.pewpew.components

import java.awt.Color

class HealthBar(ship:PlayerShip, x:Double, y:Double) extends Bar("Health", Color.RED, x, y) {
  
  def getCurrentValue = ship.health
  def getMaxValue = ship.maxHealth
  
}