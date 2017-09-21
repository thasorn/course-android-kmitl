package kmitl.lab03.thasorn58070047.simplemydot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
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
        setContentView(R.layout.fragment_main_layout);
        if (savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new DotViewFragment().newInstance()).commit();
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
