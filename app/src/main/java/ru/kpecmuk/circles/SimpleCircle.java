package ru.kpecmuk.circles;

/**
 * Created by Kpecmuk on 06.02.2017.
 */
public class SimpleCircle {
    protected int x;
    protected int y;
    protected int radius;
    protected int color;

    public SimpleCircle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public SimpleCircle getCircleArea() {
        return new SimpleCircle(x, y, radius * 3);
    }

    public boolean isNearPlayer(SimpleCircle circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x - circle.x, 2) +
                Math.pow(y - circle.y, 2));
    }

}
