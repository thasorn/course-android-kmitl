package kmitl.lab03.thasorn58070047.simplemydot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kmitl.lab03.thasorn58070047.simplemydot.model.DotParceble;
import kmitl.lab03.thasorn58070047.simplemydot.model.DotSerializable;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button buttonBack = (Button) findViewById(R.id.buttonBack);

        DotSerializable dotSerializable = new DotSerializable();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textViewValueX = (TextView) findViewById(R.id.textViewValueX);
        TextView textViewDot = (TextView) findViewById(R.id.textViewDot);
        TextView textViewDot2 = (TextView) findViewById(R.id.textViewDot2);

        int x = getIntent().getIntExtra("xValue", 0);

        dotSerializable = (DotSerializable) getIntent().getSerializableExtra("dotSerializable");

        textViewValueX.setText(String.valueOf(x));

        textViewDot.setText("CenterX : " + dotSerializable.getCenterX() + "\n" +
                            "CenterY : " + dotSerializable.getCenterY() + "\n" +
                            "Radius : " + dotSerializable.getRadius());
        textViewDot.setTextColor(dotSerializable.getColor());

        DotParceble dotParceble = getIntent().getParcelableExtra("dotParceble");

        int cx = dotParceble.getCenterX();
        int cy = dotParceble.getCenterY();
        int ra = dotParceble.getRadius();

        textViewDot2.setText("CenterX : " + cx + "\nCenterY : " + cy + "\nRadius : " + ra);
    }
}
