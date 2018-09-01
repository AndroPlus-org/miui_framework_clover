// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.BitUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.view.accessibility:
//            AccessibilityRecord

public final class AccessibilityEvent extends AccessibilityRecord
    implements Parcelable
{

    static String _2D_android_view_accessibility_AccessibilityEvent_2D_mthref_2D_0(int i)
    {
        return singleContentChangeTypeToString(i);
    }

    private AccessibilityEvent()
    {
    }

    private static String contentChangeTypesToString(int i)
    {
        return BitUtils.flagsToString(i, _.Lambda.dvFnBnKyYgdCdeedObYBAmNBWrA.$INST$0);
    }

    public static String eventTypeToString(int i)
    {
        if(i == -1)
            return "TYPES_ALL_MASK";
        StringBuilder stringbuilder = new StringBuilder();
        int j;
        for(j = 0; i != 0; j++)
        {
            int k = 1 << Integer.numberOfTrailingZeros(i);
            i &= k;
            if(j > 0)
                stringbuilder.append(", ");
            stringbuilder.append(singleEventTypeToString(k));
        }

        if(j > 1)
        {
            stringbuilder.insert(0, '[');
            stringbuilder.append(']');
        }
        return stringbuilder.toString();
    }

    public static AccessibilityEvent obtain()
    {
        AccessibilityEvent accessibilityevent = (AccessibilityEvent)sPool.acquire();
        if(accessibilityevent == null)
            accessibilityevent = new AccessibilityEvent();
        return accessibilityevent;
    }

    public static AccessibilityEvent obtain(int i)
    {
        AccessibilityEvent accessibilityevent = obtain();
        accessibilityevent.setEventType(i);
        return accessibilityevent;
    }

    public static AccessibilityEvent obtain(AccessibilityEvent accessibilityevent)
    {
        AccessibilityEvent accessibilityevent1 = obtain();
        accessibilityevent1.init(accessibilityevent);
        if(accessibilityevent.mRecords != null)
        {
            int i = accessibilityevent.mRecords.size();
            accessibilityevent1.mRecords = new ArrayList(i);
            for(int j = 0; j < i; j++)
            {
                AccessibilityRecord accessibilityrecord = AccessibilityRecord.obtain((AccessibilityRecord)accessibilityevent.mRecords.get(j));
                accessibilityevent1.mRecords.add(accessibilityrecord);
            }

        }
        return accessibilityevent1;
    }

    private void readAccessibilityRecordFromParcel(AccessibilityRecord accessibilityrecord, Parcel parcel)
    {
        accessibilityrecord.mBooleanProperties = parcel.readInt();
        accessibilityrecord.mCurrentItemIndex = parcel.readInt();
        accessibilityrecord.mItemCount = parcel.readInt();
        accessibilityrecord.mFromIndex = parcel.readInt();
        accessibilityrecord.mToIndex = parcel.readInt();
        accessibilityrecord.mScrollX = parcel.readInt();
        accessibilityrecord.mScrollY = parcel.readInt();
        accessibilityrecord.mMaxScrollX = parcel.readInt();
        accessibilityrecord.mMaxScrollY = parcel.readInt();
        accessibilityrecord.mAddedCount = parcel.readInt();
        accessibilityrecord.mRemovedCount = parcel.readInt();
        accessibilityrecord.mClassName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        accessibilityrecord.mContentDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        accessibilityrecord.mBeforeText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        accessibilityrecord.mParcelableData = parcel.readParcelable(null);
        parcel.readList(accessibilityrecord.mText, null);
        accessibilityrecord.mSourceWindowId = parcel.readInt();
        accessibilityrecord.mSourceNodeId = parcel.readLong();
        boolean flag;
        if(parcel.readInt() == 1)
            flag = true;
        else
            flag = false;
        accessibilityrecord.mSealed = flag;
    }

    private static String singleContentChangeTypeToString(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        default:
            return Integer.toHexString(i);

        case 4: // '\004'
            return "CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION";

        case 1: // '\001'
            return "CONTENT_CHANGE_TYPE_SUBTREE";

        case 2: // '\002'
            return "CONTENT_CHANGE_TYPE_TEXT";

        case 0: // '\0'
            return "CONTENT_CHANGE_TYPE_UNDEFINED";
        }
    }

    private static String singleEventTypeToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toHexString(i);

        case 1: // '\001'
            return "TYPE_VIEW_CLICKED";

        case 2: // '\002'
            return "TYPE_VIEW_LONG_CLICKED";

        case 4: // '\004'
            return "TYPE_VIEW_SELECTED";

        case 8: // '\b'
            return "TYPE_VIEW_FOCUSED";

        case 16: // '\020'
            return "TYPE_VIEW_TEXT_CHANGED";

        case 32: // ' '
            return "TYPE_WINDOW_STATE_CHANGED";

        case 128: 
            return "TYPE_VIEW_HOVER_ENTER";

        case 256: 
            return "TYPE_VIEW_HOVER_EXIT";

        case 64: // '@'
            return "TYPE_NOTIFICATION_STATE_CHANGED";

        case 512: 
            return "TYPE_TOUCH_EXPLORATION_GESTURE_START";

        case 1024: 
            return "TYPE_TOUCH_EXPLORATION_GESTURE_END";

        case 2048: 
            return "TYPE_WINDOW_CONTENT_CHANGED";

        case 8192: 
            return "TYPE_VIEW_TEXT_SELECTION_CHANGED";

        case 4096: 
            return "TYPE_VIEW_SCROLLED";

        case 16384: 
            return "TYPE_ANNOUNCEMENT";

        case 32768: 
            return "TYPE_VIEW_ACCESSIBILITY_FOCUSED";

        case 65536: 
            return "TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED";

        case 131072: 
            return "TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY";

        case 262144: 
            return "TYPE_GESTURE_DETECTION_START";

        case 524288: 
            return "TYPE_GESTURE_DETECTION_END";

        case 1048576: 
            return "TYPE_TOUCH_INTERACTION_START";

        case 2097152: 
            return "TYPE_TOUCH_INTERACTION_END";

        case 4194304: 
            return "TYPE_WINDOWS_CHANGED";

        case 8388608: 
            return "TYPE_VIEW_CONTEXT_CLICKED";

        case 16777216: 
            return "TYPE_ASSIST_READING_CONTEXT";
        }
    }

    private void writeAccessibilityRecordToParcel(AccessibilityRecord accessibilityrecord, Parcel parcel, int i)
    {
        parcel.writeInt(accessibilityrecord.mBooleanProperties);
        parcel.writeInt(accessibilityrecord.mCurrentItemIndex);
        parcel.writeInt(accessibilityrecord.mItemCount);
        parcel.writeInt(accessibilityrecord.mFromIndex);
        parcel.writeInt(accessibilityrecord.mToIndex);
        parcel.writeInt(accessibilityrecord.mScrollX);
        parcel.writeInt(accessibilityrecord.mScrollY);
        parcel.writeInt(accessibilityrecord.mMaxScrollX);
        parcel.writeInt(accessibilityrecord.mMaxScrollY);
        parcel.writeInt(accessibilityrecord.mAddedCount);
        parcel.writeInt(accessibilityrecord.mRemovedCount);
        TextUtils.writeToParcel(accessibilityrecord.mClassName, parcel, i);
        TextUtils.writeToParcel(accessibilityrecord.mContentDescription, parcel, i);
        TextUtils.writeToParcel(accessibilityrecord.mBeforeText, parcel, i);
        parcel.writeParcelable(accessibilityrecord.mParcelableData, i);
        parcel.writeList(accessibilityrecord.mText);
        parcel.writeInt(accessibilityrecord.mSourceWindowId);
        parcel.writeLong(accessibilityrecord.mSourceNodeId);
        if(accessibilityrecord.mSealed)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public void appendRecord(AccessibilityRecord accessibilityrecord)
    {
        enforceNotSealed();
        if(mRecords == null)
            mRecords = new ArrayList();
        mRecords.add(accessibilityrecord);
    }

    protected void clear()
    {
        super.clear();
        mEventType = 0;
        mMovementGranularity = 0;
        mAction = 0;
        mContentChangeTypes = 0;
        mPackageName = null;
        mEventTime = 0L;
        if(mRecords != null)
            for(; !mRecords.isEmpty(); ((AccessibilityRecord)mRecords.remove(0)).recycle());
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAction()
    {
        return mAction;
    }

    public int getContentChangeTypes()
    {
        return mContentChangeTypes;
    }

    public long getEventTime()
    {
        return mEventTime;
    }

    public int getEventType()
    {
        return mEventType;
    }

    public int getMovementGranularity()
    {
        return mMovementGranularity;
    }

    public CharSequence getPackageName()
    {
        return mPackageName;
    }

    public AccessibilityRecord getRecord(int i)
    {
        if(mRecords == null)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Invalid index ").append(i).append(", size is 0").toString());
        else
            return (AccessibilityRecord)mRecords.get(i);
    }

    public int getRecordCount()
    {
        int i;
        if(mRecords == null)
            i = 0;
        else
            i = mRecords.size();
        return i;
    }

    void init(AccessibilityEvent accessibilityevent)
    {
        super.init(accessibilityevent);
        mEventType = accessibilityevent.mEventType;
        mMovementGranularity = accessibilityevent.mMovementGranularity;
        mAction = accessibilityevent.mAction;
        mContentChangeTypes = accessibilityevent.mContentChangeTypes;
        mEventTime = accessibilityevent.mEventTime;
        mPackageName = accessibilityevent.mPackageName;
    }

    public void initFromParcel(Parcel parcel)
    {
        boolean flag = true;
        int i;
        if(parcel.readInt() != 1)
            flag = false;
        mSealed = flag;
        mEventType = parcel.readInt();
        mMovementGranularity = parcel.readInt();
        mAction = parcel.readInt();
        mContentChangeTypes = parcel.readInt();
        mPackageName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mEventTime = parcel.readLong();
        mConnectionId = parcel.readInt();
        readAccessibilityRecordFromParcel(this, parcel);
        i = parcel.readInt();
        if(i > 0)
        {
            mRecords = new ArrayList(i);
            for(int j = 0; j < i; j++)
            {
                AccessibilityRecord accessibilityrecord = AccessibilityRecord.obtain();
                readAccessibilityRecordFromParcel(accessibilityrecord, parcel);
                accessibilityrecord.mConnectionId = mConnectionId;
                mRecords.add(accessibilityrecord);
            }

        }
    }

    public void recycle()
    {
        clear();
        sPool.release(this);
    }

    public void setAction(int i)
    {
        enforceNotSealed();
        mAction = i;
    }

    public void setContentChangeTypes(int i)
    {
        enforceNotSealed();
        mContentChangeTypes = i;
    }

    public void setEventTime(long l)
    {
        enforceNotSealed();
        mEventTime = l;
    }

    public void setEventType(int i)
    {
        enforceNotSealed();
        mEventType = i;
    }

    public void setMovementGranularity(int i)
    {
        enforceNotSealed();
        mMovementGranularity = i;
    }

    public void setPackageName(CharSequence charsequence)
    {
        enforceNotSealed();
        mPackageName = charsequence;
    }

    public void setSealed(boolean flag)
    {
        super.setSealed(flag);
        ArrayList arraylist = mRecords;
        if(arraylist != null)
        {
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((AccessibilityRecord)arraylist.get(j)).setSealed(flag);

        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("EventType: ").append(eventTypeToString(mEventType));
        stringbuilder.append("; EventTime: ").append(mEventTime);
        stringbuilder.append("; PackageName: ").append(mPackageName);
        stringbuilder.append("; MovementGranularity: ").append(mMovementGranularity);
        stringbuilder.append("; Action: ").append(mAction);
        stringbuilder.append(super.toString());
        stringbuilder.append("; recordCount: ").append(getRecordCount());
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        int k;
        if(isSealed())
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(mEventType);
        parcel.writeInt(mMovementGranularity);
        parcel.writeInt(mAction);
        parcel.writeInt(mContentChangeTypes);
        TextUtils.writeToParcel(mPackageName, parcel, 0);
        parcel.writeLong(mEventTime);
        parcel.writeInt(mConnectionId);
        writeAccessibilityRecordToParcel(this, parcel, i);
        k = getRecordCount();
        parcel.writeInt(k);
        for(j = 0; j < k; j++)
            writeAccessibilityRecordToParcel((AccessibilityRecord)mRecords.get(j), parcel, i);

    }

    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AccessibilityEvent createFromParcel(Parcel parcel)
        {
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain();
            accessibilityevent.initFromParcel(parcel);
            return accessibilityevent;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AccessibilityEvent[] newArray(int i)
        {
            return new AccessibilityEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    public static final int INVALID_POSITION = -1;
    private static final int MAX_POOL_SIZE = 10;
    public static final int MAX_TEXT_LENGTH = 500;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_ASSIST_READING_CONTEXT = 0x1000000;
    public static final int TYPE_GESTURE_DETECTION_END = 0x80000;
    public static final int TYPE_GESTURE_DETECTION_START = 0x40000;
    public static final int TYPE_NOTIFICATION_STATE_CHANGED = 64;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 0x200000;
    public static final int TYPE_TOUCH_INTERACTION_START = 0x100000;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 0x10000;
    public static final int TYPE_VIEW_CLICKED = 1;
    public static final int TYPE_VIEW_CONTEXT_CLICKED = 0x800000;
    public static final int TYPE_VIEW_FOCUSED = 8;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_LONG_CLICKED = 2;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_SELECTED = 4;
    public static final int TYPE_VIEW_TEXT_CHANGED = 16;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 0x20000;
    public static final int TYPE_WINDOWS_CHANGED = 0x400000;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;
    public static final int TYPE_WINDOW_STATE_CHANGED = 32;
    private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(10);
    int mAction;
    int mContentChangeTypes;
    private long mEventTime;
    private int mEventType;
    int mMovementGranularity;
    private CharSequence mPackageName;
    private ArrayList mRecords;

}
