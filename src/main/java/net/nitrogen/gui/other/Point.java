package net.nitrogen.gui.other;

public class Point {
    double vector = 0;
    double x = 0;
    double y = 0;
    int speed = 0;

    public Point(double x, double y, double vector, int speed){
        this.x = x;
        this.y = y;
        this.vector = vector;
        this.speed = speed;
    }
}
