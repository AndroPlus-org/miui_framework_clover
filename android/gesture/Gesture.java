// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import android.graphics.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.gesture:
//            GestureStroke, GestureUtils

public class Gesture
    implements Parcelable
{

    static long _2D_set0(Gesture gesture, long l)
    {
        gesture.mGestureID = l;
        return l;
    }

    public Gesture()
    {
        mGestureID = GESTURE_ID_BASE + (long)sGestureCount.incrementAndGet();
    }

    static Gesture deserialize(DataInputStream datainputstream)
        throws IOException
    {
        Gesture gesture = new Gesture();
        gesture.mGestureID = datainputstream.readLong();
        int i = datainputstream.readInt();
        for(int j = 0; j < i; j++)
            gesture.addStroke(GestureStroke.deserialize(datainputstream));

        return gesture;
    }

    public void addStroke(GestureStroke gesturestroke)
    {
        mStrokes.add(gesturestroke);
        mBoundingBox.union(gesturestroke.boundingBox);
    }

    public Object clone()
    {
        Gesture gesture = new Gesture();
        gesture.mBoundingBox.set(mBoundingBox.left, mBoundingBox.top, mBoundingBox.right, mBoundingBox.bottom);
        int i = mStrokes.size();
        for(int j = 0; j < i; j++)
        {
            GestureStroke gesturestroke = (GestureStroke)mStrokes.get(j);
            gesture.mStrokes.add((GestureStroke)gesturestroke.clone());
        }

        return gesture;
    }

    public int describeContents()
    {
        return 0;
    }

    public RectF getBoundingBox()
    {
        return mBoundingBox;
    }

    public long getID()
    {
        return mGestureID;
    }

    public float getLength()
    {
        int i = 0;
        ArrayList arraylist = mStrokes;
        int j = arraylist.size();
        for(int k = 0; k < j; k++)
        {
            float f = i;
            i = (int)(((GestureStroke)arraylist.get(k)).length + f);
        }

        return (float)i;
    }

    public ArrayList getStrokes()
    {
        return mStrokes;
    }

    public int getStrokesCount()
    {
        return mStrokes.size();
    }

    void serialize(DataOutputStream dataoutputstream)
        throws IOException
    {
        ArrayList arraylist = mStrokes;
        int i = arraylist.size();
        dataoutputstream.writeLong(mGestureID);
        dataoutputstream.writeInt(i);
        for(int j = 0; j < i; j++)
            ((GestureStroke)arraylist.get(j)).serialize(dataoutputstream);

    }

    void setID(long l)
    {
        mGestureID = l;
    }

    public Bitmap toBitmap(int i, int j, int k, int l)
    {
        Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(l);
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        paint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        paint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        paint.setStrokeWidth(2.0F);
        Path path = toPath();
        RectF rectf = new RectF();
        path.computeBounds(rectf, true);
        float f = (float)(i - k * 2) / rectf.width();
        float f1 = (float)(j - k * 2) / rectf.height();
        if(f > f1)
            f = f1;
        paint.setStrokeWidth(2.0F / f);
        path.offset(-rectf.left + ((float)i - rectf.width() * f) / 2.0F, -rectf.top + ((float)j - rectf.height() * f) / 2.0F);
        canvas.translate(k, k);
        canvas.scale(f, f);
        canvas.drawPath(path, paint);
        return bitmap;
    }

    public Bitmap toBitmap(int i, int j, int k, int l, int i1)
    {
        Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(k, k);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(i1);
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        paint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        paint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        paint.setStrokeWidth(2.0F);
        ArrayList arraylist = mStrokes;
        int j1 = arraylist.size();
        for(i1 = 0; i1 < j1; i1++)
            canvas.drawPath(((GestureStroke)arraylist.get(i1)).toPath(i - k * 2, j - k * 2, l), paint);

        return bitmap;
    }

    public Path toPath()
    {
        return toPath(null);
    }

    public Path toPath(int i, int j, int k, int l)
    {
        return toPath(null, i, j, k, l);
    }

    public Path toPath(Path path)
    {
        Path path1 = path;
        if(path == null)
            path1 = new Path();
        path = mStrokes;
        int i = path.size();
        for(int j = 0; j < i; j++)
            path1.addPath(((GestureStroke)path.get(j)).getPath());

        return path1;
    }

    public Path toPath(Path path, int i, int j, int k, int l)
    {
        Path path1 = path;
        if(path == null)
            path1 = new Path();
        path = mStrokes;
        int i1 = path.size();
        for(int j1 = 0; j1 < i1; j1++)
            path1.addPath(((GestureStroke)path.get(j1)).toPath(i - k * 2, j - k * 2, l));

        return path1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ByteArrayOutputStream bytearrayoutputstream;
        DataOutputStream dataoutputstream;
        parcel.writeLong(mGestureID);
        i = 0;
        bytearrayoutputstream = new ByteArrayOutputStream(32768);
        dataoutputstream = new DataOutputStream(bytearrayoutputstream);
        serialize(dataoutputstream);
        i = 1;
        GestureUtils.closeStream(dataoutputstream);
        GestureUtils.closeStream(bytearrayoutputstream);
_L2:
        if(i != 0)
            parcel.writeByteArray(bytearrayoutputstream.toByteArray());
        return;
        IOException ioexception;
        ioexception;
        Log.e("Gestures", "Error writing Gesture to parcel:", ioexception);
        GestureUtils.closeStream(dataoutputstream);
        GestureUtils.closeStream(bytearrayoutputstream);
        if(true) goto _L2; else goto _L1
_L1:
        parcel;
        GestureUtils.closeStream(dataoutputstream);
        GestureUtils.closeStream(bytearrayoutputstream);
        throw parcel;
    }

    private static final boolean BITMAP_RENDERING_ANTIALIAS = true;
    private static final boolean BITMAP_RENDERING_DITHER = true;
    private static final int BITMAP_RENDERING_WIDTH = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Gesture createFromParcel(Parcel parcel)
        {
            Object obj;
            long l;
            DataInputStream datainputstream;
            obj = null;
            l = parcel.readLong();
            datainputstream = new DataInputStream(new ByteArrayInputStream(parcel.createByteArray()));
            parcel = Gesture.deserialize(datainputstream);
            GestureUtils.closeStream(datainputstream);
_L2:
            if(parcel != null)
                Gesture._2D_set0(parcel, l);
            return parcel;
            parcel;
            Log.e("Gestures", "Error reading Gesture from parcel:", parcel);
            GestureUtils.closeStream(datainputstream);
            parcel = obj;
            if(true) goto _L2; else goto _L1
_L1:
            parcel;
            GestureUtils.closeStream(datainputstream);
            throw parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Gesture[] newArray(int i)
        {
            return new Gesture[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final long GESTURE_ID_BASE = System.currentTimeMillis();
    private static final AtomicInteger sGestureCount = new AtomicInteger(0);
    private final RectF mBoundingBox = new RectF();
    private long mGestureID;
    private final ArrayList mStrokes = new ArrayList();

}
