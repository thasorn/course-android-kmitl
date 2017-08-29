package kmitl.lab03.thasorn58070047.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import kmitl.lab03.thasorn58070047.simplemydot.model.Dot;
import java.util.LinkedList;

public class DotView extends View {

    private Paint paint;
    private Dot dot;
    private LinkedList<Dot> position = new LinkedList<>();

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        position.add(dot);
        for (Dot onDot : position) {
            if (onDot != null) {
                paint.setColor(onDot.getColor());
                canvas.drawCircle(onDot.getCenterX(), onDot.getCenterY(), onDot.getRadius(), paint);
            }

        }
    }


    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public void deleteDot(){
        dot = null;
        position.clear();
    }

}
