// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.view.IDragAndDropPermissions;

public class DragEvent
    implements Parcelable
{

    private DragEvent()
    {
    }

    private void init(int i, float f, float f1, ClipDescription clipdescription, ClipData clipdata, IDragAndDropPermissions idraganddroppermissions, Object obj, 
            boolean flag)
    {
        mAction = i;
        mX = f;
        mY = f1;
        mClipDescription = clipdescription;
        mClipData = clipdata;
        mDragAndDropPermissions = idraganddroppermissions;
        mLocalState = obj;
        mDragResult = flag;
    }

    static DragEvent obtain()
    {
        return obtain(0, 0.0F, 0.0F, null, null, null, null, false);
    }

    public static DragEvent obtain(int i, float f, float f1, Object obj, ClipDescription clipdescription, ClipData clipdata, IDragAndDropPermissions idraganddroppermissions, boolean flag)
    {
        Object obj1 = gRecyclerLock;
        obj1;
        JVM INSTR monitorenter ;
        DragEvent dragevent;
        if(gRecyclerTop != null)
            break MISSING_BLOCK_LABEL_47;
        dragevent = JVM INSTR new #2   <Class DragEvent>;
        dragevent.DragEvent();
        dragevent.init(i, f, f1, clipdescription, clipdata, idraganddroppermissions, obj, flag);
        obj1;
        JVM INSTR monitorexit ;
        return dragevent;
        dragevent = gRecyclerTop;
        gRecyclerTop = dragevent.mNext;
        gRecyclerUsed--;
        obj1;
        JVM INSTR monitorexit ;
        dragevent.mRecycledLocation = null;
        dragevent.mRecycled = false;
        dragevent.mNext = null;
        dragevent.init(i, f, f1, clipdescription, clipdata, idraganddroppermissions, obj, flag);
        return dragevent;
        obj;
        throw obj;
    }

    public static DragEvent obtain(DragEvent dragevent)
    {
        return obtain(dragevent.mAction, dragevent.mX, dragevent.mY, dragevent.mLocalState, dragevent.mClipDescription, dragevent.mClipData, dragevent.mDragAndDropPermissions, dragevent.mDragResult);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAction()
    {
        return mAction;
    }

    public ClipData getClipData()
    {
        return mClipData;
    }

    public ClipDescription getClipDescription()
    {
        return mClipDescription;
    }

    public IDragAndDropPermissions getDragAndDropPermissions()
    {
        return mDragAndDropPermissions;
    }

    public Object getLocalState()
    {
        return mLocalState;
    }

    public boolean getResult()
    {
        return mDragResult;
    }

    public float getX()
    {
        return mX;
    }

    public float getY()
    {
        return mY;
    }

    public final void recycle()
    {
        if(mRecycled)
            throw new RuntimeException((new StringBuilder()).append(toString()).append(" recycled twice!").toString());
        mRecycled = true;
        mClipData = null;
        mClipDescription = null;
        mLocalState = null;
        mEventHandlerWasCalled = false;
        Object obj = gRecyclerLock;
        obj;
        JVM INSTR monitorenter ;
        if(gRecyclerUsed < 10)
        {
            gRecyclerUsed++;
            mNext = gRecyclerTop;
            gRecyclerTop = this;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        return (new StringBuilder()).append("DragEvent{").append(Integer.toHexString(System.identityHashCode(this))).append(" action=").append(mAction).append(" @ (").append(mX).append(", ").append(mY).append(") desc=").append(mClipDescription).append(" data=").append(mClipData).append(" local=").append(mLocalState).append(" result=").append(mDragResult).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mAction);
        parcel.writeFloat(mX);
        parcel.writeFloat(mY);
        int j;
        if(mDragResult)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mClipData == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mClipData.writeToParcel(parcel, i);
        }
        if(mClipDescription == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mClipDescription.writeToParcel(parcel, i);
        }
        if(mDragAndDropPermissions == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            parcel.writeStrongBinder(mDragAndDropPermissions.asBinder());
        }
    }

    public static final int ACTION_DRAG_ENDED = 4;
    public static final int ACTION_DRAG_ENTERED = 5;
    public static final int ACTION_DRAG_EXITED = 6;
    public static final int ACTION_DRAG_LOCATION = 2;
    public static final int ACTION_DRAG_STARTED = 1;
    public static final int ACTION_DROP = 3;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DragEvent createFromParcel(Parcel parcel)
        {
            boolean flag = false;
            DragEvent dragevent = DragEvent.obtain();
            dragevent.mAction = parcel.readInt();
            dragevent.mX = parcel.readFloat();
            dragevent.mY = parcel.readFloat();
            if(parcel.readInt() != 0)
                flag = true;
            dragevent.mDragResult = flag;
            if(parcel.readInt() != 0)
                dragevent.mClipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel);
            if(parcel.readInt() != 0)
                dragevent.mClipDescription = (ClipDescription)ClipDescription.CREATOR.createFromParcel(parcel);
            if(parcel.readInt() != 0)
                dragevent.mDragAndDropPermissions = com.android.internal.view.IDragAndDropPermissions.Stub.asInterface(parcel.readStrongBinder());
            return dragevent;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DragEvent[] newArray(int i)
        {
            return new DragEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_RECYCLED = 10;
    private static final boolean TRACK_RECYCLED_LOCATION = false;
    private static final Object gRecyclerLock = new Object();
    private static DragEvent gRecyclerTop = null;
    private static int gRecyclerUsed = 0;
    int mAction;
    ClipData mClipData;
    ClipDescription mClipDescription;
    IDragAndDropPermissions mDragAndDropPermissions;
    boolean mDragResult;
    boolean mEventHandlerWasCalled;
    Object mLocalState;
    private DragEvent mNext;
    private boolean mRecycled;
    private RuntimeException mRecycledLocation;
    float mX;
    float mY;

}
