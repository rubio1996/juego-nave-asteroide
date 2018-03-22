/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegonave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 1daw
 */
public class Bala {
double balaX = 0.0;
double balaY = 0.0;
double velBala = 10;
double potenciaNave = 0.2;
public boolean disparar = false;
Polygon cuerpoVala = new Polygon();
private double variaX = 0;
private double variaY = 0;
double angulo1;
double coseno;
double seno;
    public Bala(double angulo, double x, double y){
        cuerpoVala.getPoints().addAll(new Double[]{
        -0.5, -7.5,
        0.5, -7.5,
        0.5, 7.5,
        -0.5, 7.5});
        
        cuerpoVala.setId("rectangle1");
        cuerpoVala.setFill(Color.web("#FF0000"));
        cuerpoVala.setVisible(true);
        balaX = x;
        balaY = y;
        angulo1 = angulo;
        
    }
    public void moverBala()
    {
    double resto = angulo1 % 360;  
    double radianes = Math.toRadians(resto);
    variaX = Math.sin(radianes);
    variaY = Math.cos(radianes);
    
    cuerpoVala.setLayoutX(balaX);
    cuerpoVala.setLayoutY(balaY);
    balaX += variaX * velBala;
    balaY += -(variaY * velBala);
    cuerpoVala.setRotate(angulo1);
    }
    public Polygon getPolygon() {
        return cuerpoVala;
        
    }
}
