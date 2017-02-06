package ru.kpecmuk.circles;

import java.util.ArrayList;

/**
 * Created by Kpecmuk on 06.02.2017.
 */
public class GameManager {
    public static final int MAX_ENEMY_CIRCLES = 10;
    private static int width;
    private static int height;
    private PlayerCircle playerCircle;
    private ArrayList<EnemyCircle> circles;
    private CanvasView canvasView;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w;
        height = h;
        initPlayerCircle();
        initEnemyCircles();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private void initEnemyCircles() {
        SimpleCircle playerCircleArea = playerCircle.getCircleArea();
        circles = new ArrayList<>();
        for (int i = 0; i < MAX_ENEMY_CIRCLES; i++) {
            EnemyCircle circle;
            do {
                circle = EnemyCircle.getRandomCircle();
            } while (circle.isNearPlayer(playerCircleArea));
            circles.add(circle);
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {
        for (EnemyCircle circle : circles) {
            circle.setEnemyOrFoodColor(playerCircle);
        }
    }

    private void initPlayerCircle() {
        playerCircle = new PlayerCircle(width / 2, height / 2);
    }

    public void onDraw() {
        canvasView.drawCircle(playerCircle);
        for (EnemyCircle circle : circles) {
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        playerCircle.movePlayerCircleWhenTouchAt(x, y);
        moveCircles();
        checkCollision();
    }

    private void checkCollision() {
        SimpleCircle circleForRemove = null;
        for (EnemyCircle circle : circles) {
            if (playerCircle.isNearPlayer(circle)) {
                if (circle.isSmallerThan(playerCircle)) {
                    playerCircle.growRadius(circle);
                    circleForRemove = circle;
                    calculateAndSetCirclesColor();
                    break;
                } else {
                    gameEnd("YOU LOSE!");
                    return;
                }
            }
        }
        if (circleForRemove != null) {
            circles.remove(circleForRemove);
        }
        if (circles.isEmpty()) {
            gameEnd("YOU WIN!");
        }
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        playerCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle circle : circles) {
            circle.moveOneStep();
        }
    }
}
