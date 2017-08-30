//backup
package kmitl.lab03.thasorn58070047.simplemydot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;
import kmitl.lab03.thasorn58070047.simplemydot.model.Dot;
import kmitl.lab03.thasorn58070047.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.onDotChangedListener {

    private DotView newDotView;
    private Dot dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_dot);

        //Set default Values
        newDotView = (DotView) findViewById(R.id.newDotView);
        if (newDotView == null) {
            Log.d("DEBUG", "NULL!");
        }

        dot = new Dot(this, 0, 0, 20, 0);
    }

    public void onRandomDot(View view) {
        //Random a Dot
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        int color = Color.argb(100, r, g, b); //pastel
        int cenX = random.nextInt(this.newDotView.getWidth());
        int cenY = random.nextInt(this.newDotView.getHeight());
        new Dot(this, cenX, cenY, 20, color);
    }

    @Override
    public void onDotChanged(Dot dot) {
        Log.d("DEBUG", newDotView.toString());
        newDotView.setDot(dot);
        newDotView.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        int color = Color.argb(100, r, g, b); //pastel

        int xy[] = new int[2];
        newDotView.getLocationOnScreen(xy);
        int cenX = (int) event.getX()-xy[0];
        int cenY = (int) event.getY()-xy[1];
        new Dot(this, cenX, cenY, 20, color);

        return false;
    }

    public void onDotDelete(View view){
        newDotView.deleteDot();
        newDotView.invalidate();
    }
}
