package ru.kpecmuk.circles;

import android.graphics.Color;

/**
 * Created by Kpecmuk on 06.02.2017.
 */
public class PlayerCircle extends SimpleCircle {
    public static final int INIT_RADIUS = 50;
    public static final int PLAYER_SPEED = 30;
    public static final int PLAYER_COLOR = Color.BLUE;

    public PlayerCircle(int x, int y) {
        super(x, y, INIT_RADIUS);
        setColor(PLAYER_COLOR);

    }

    public void movePlayerCircleWhenTouchAt(int xx, int yy) {
        int dx = (xx - x) * PLAYER_SPEED / GameManager.getWidth();
        int dy = (yy - y) * PLAYER_SPEED / GameManager.getHeight();
        x += dx;
        y += dy;
    }

    public void initRadius() {
        radius = INIT_RADIUS;
    }

    public void growRadius(SimpleCircle circle) {
        // pi * new ^ 2 == pi * r ^ 2 + pi * react ^ 2
        // newRadius = sqrt(r ^ 2 + react ^ 2)
        radius = (int) Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2));
    }

}
