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
double velBalaY = 10;
double velBalaX = 10;
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
        variaX=Math.sin(Math.toRadians(angulo));
        variaY=Math.cos(Math.toRadians(angulo))*-1;
        cuerpoVala.setId("rectangle1");
        cuerpoVala.setFill(Color.web("#FF0000"));
        cuerpoVala.setVisible(true);
        cuerpoVala.setLayoutX(x);
        cuerpoVala.setLayoutY(y);
        cuerpoVala.setRotate(angulo);
        balaX = x;
        balaY = y;
        angulo1 = angulo;
        velBalaX = velBalaX+velBala*variaX;
        velBalaX = velBalaY+velBala*variaX;
    }
    public void moverBala()
    {   
    cuerpoVala.setLayoutX(balaX);
    cuerpoVala.setLayoutY(balaY);
    balaX += velBalaX;
    balaY += velBalaY;
    }
    public Polygon getPolygon() {
        return cuerpoVala;
        
    }
}
