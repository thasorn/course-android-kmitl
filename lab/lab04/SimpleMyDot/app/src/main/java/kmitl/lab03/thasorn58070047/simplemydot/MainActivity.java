package kmitl.lab03.thasorn58070047.simplemydot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import java.io.File;
import java.util.Random;
import kmitl.lab03.thasorn58070047.simplemydot.model.Dot;
import kmitl.lab03.thasorn58070047.simplemydot.model.DotParceble;
import kmitl.lab03.thasorn58070047.simplemydot.model.DotSerializable;
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

        Button buttonOpenActivity = (Button) findViewById(R.id.buttonOpenActivity);

        findViewById(R.id.buttonShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takeScreenShot();

            }
        });

        final DotSerializable dotSerializable = new DotSerializable();
        dotSerializable.setCenterX(150);
        dotSerializable.setCenterY(150);
        dotSerializable.setColor(Color.RED);
        dotSerializable.setRadius(30);

        final DotParceble dotParceble = new DotParceble(150, 150, 30);

        buttonOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);

                intent.putExtra("xValue", 30);
                intent.putExtra("dotSerializable", dotSerializable);

                intent.putExtra("dotParceble", dotParceble);

                startActivity(intent);
            }
        });

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

    public void takeScreenShot() {

        Bitmap bm = Screenshot.getScreenshot(newDotView);
        File path = Screenshot.getDirectoryName(this);
        File file = Screenshot.store(bm, "Screenshot.jpg", path);
        shareImage(file);

    }

    private void shareImage(File imageFile) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "send image"));

    }


}
