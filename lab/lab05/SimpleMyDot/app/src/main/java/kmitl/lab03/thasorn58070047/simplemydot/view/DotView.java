package kmitl.lab03.thasorn58070047.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import kmitl.lab03.thasorn58070047.simplemydot.model.Dot;
import java.util.LinkedList;
import java.util.Random;

import static kmitl.lab03.thasorn58070047.simplemydot.R.id.newDotView;

public class DotView extends View implements Dot.onDotChangedListener {

    private DotView newDotView;
    private Paint paint;
    private Dot dot;
    private LinkedList<Dot> position = new LinkedList<>();

    @Override
    public void onDotChanged(Dot dot) {

    }

    public interface onDotChangedListener {
        void onDotChanged(Dot dot);
    }

    private Dot.onDotChangedListener listener;

    public void setListener(Dot.onDotChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //Random a Dot
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        int color = Color.argb(100, r, g, b); //pastel
        int cenX = random.nextInt(this.newDotView.getWidth());
        int cenY = random.nextInt(this.newDotView.getHeight());
        new Dot(this, cenX, cenY, 20, color);

        return false;
    }

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
