package ar.com.pablitar.pewpew.components

import java.awt.Color

class ShieldBar(ship:PlayerShip, x:Double, y:Double) extends Bar("Shield", Color.CYAN, x, y) {
  
  def getCurrentValue = ship.shield.toInt
  def getMaxValue = ship.maxShield.toInt
  
}