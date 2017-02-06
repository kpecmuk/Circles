package ru.kpecmuk.circles;

/**
 * Created by Kpecmuk on 06.02.2017.
 */
public interface ICanvasView {
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);
}
