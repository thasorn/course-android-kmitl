package kmitl.lab03.thasorn58070047.simplemydot.model;

import kmitl.lab03.thasorn58070047.simplemydot.view.DotView;

public class Dot {

    public Dot(DotView dotView, int cenX, int cenY, int radius, int color) {
    }

    public interface onDotChangedListener {
        void onDotChanged(Dot dot);
    }

    private onDotChangedListener listener;

    public void setListener(onDotChangedListener listener) {
        this.listener = listener;
    }

    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    public Dot(onDotChangedListener listener, int centerX, int centerY, int radius, int color) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
        this.listener.onDotChanged(this);
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
