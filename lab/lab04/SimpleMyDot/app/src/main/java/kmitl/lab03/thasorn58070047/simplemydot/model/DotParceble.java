package kmitl.lab03.thasorn58070047.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DotParceble implements Parcelable {

    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
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

    public DotParceble(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    protected DotParceble(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(radius);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DotParceble> CREATOR = new Creator<DotParceble>() {
        @Override
        public DotParceble createFromParcel(Parcel in) {
            return new DotParceble(in);
        }

        @Override
        public DotParceble[] newArray(int size) {
            return new DotParceble[size];
        }
    };
}
