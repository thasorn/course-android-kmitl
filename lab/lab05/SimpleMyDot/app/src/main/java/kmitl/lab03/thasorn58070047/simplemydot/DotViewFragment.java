package kmitl.lab03.thasorn58070047.simplemydot;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import kmitl.lab03.thasorn58070047.simplemydot.R;
import kmitl.lab03.thasorn58070047.simplemydot.model.Dot;
import kmitl.lab03.thasorn58070047.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotViewFragment extends Fragment implements Dot.onDotChangedListener{

    private DotView newDotView;

    public DotViewFragment() {
        // Required empty public constructor
    }

    public static DotViewFragment newInstance() {

        Bundle args = new Bundle();

        DotViewFragment fragment = new DotViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDotChanged(Dot dot) {
        Log.d("DEBUG", newDotView.toString());
        newDotView.setDot(dot);
        newDotView.invalidate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dot_view, container, false);
    }

}
