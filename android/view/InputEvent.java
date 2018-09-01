// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.view:
//            InputDevice, KeyEvent, MotionEvent

public abstract class InputEvent
    implements Parcelable
{

    InputEvent()
    {
        mSeq = mNextSeq.getAndIncrement();
    }

    public abstract void cancel();

    public abstract InputEvent copy();

    public int describeContents()
    {
        return 0;
    }

    public final InputDevice getDevice()
    {
        return InputDevice.getDevice(getDeviceId());
    }

    public abstract int getDeviceId();

    public abstract long getEventTime();

    public abstract long getEventTimeNano();

    public int getSequenceNumber()
    {
        return mSeq;
    }

    public abstract int getSource();

    public boolean isFromSource(int i)
    {
        boolean flag;
        if((getSource() & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public abstract boolean isTainted();

    protected void prepareForReuse()
    {
        mRecycled = false;
        mRecycledLocation = null;
        mSeq = mNextSeq.getAndIncrement();
    }

    public void recycle()
    {
        if(mRecycled)
        {
            throw new RuntimeException((new StringBuilder()).append(toString()).append(" recycled twice!").toString());
        } else
        {
            mRecycled = true;
            return;
        }
    }

    public void recycleIfNeededAfterDispatch()
    {
        recycle();
    }

    public abstract void setSource(int i);

    public abstract void setTainted(boolean flag);

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InputEvent createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == 2)
                return KeyEvent.createFromParcelBody(parcel);
            if(i == 1)
                return MotionEvent.createFromParcelBody(parcel);
            else
                throw new IllegalStateException("Unexpected input event type token in parcel.");
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InputEvent[] newArray(int i)
        {
            return new InputEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    protected static final int PARCEL_TOKEN_KEY_EVENT = 2;
    protected static final int PARCEL_TOKEN_MOTION_EVENT = 1;
    private static final boolean TRACK_RECYCLED_LOCATION = false;
    private static final AtomicInteger mNextSeq = new AtomicInteger();
    protected boolean mRecycled;
    private RuntimeException mRecycledLocation;
    protected int mSeq;

}
