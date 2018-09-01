// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.LongArray;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.view.accessibility:
//            AccessibilityInteractionClient, AccessibilityNodeInfo

public final class AccessibilityWindowInfo
    implements Parcelable
{

    static void _2D_wrap0(AccessibilityWindowInfo accessibilitywindowinfo, Parcel parcel)
    {
        accessibilitywindowinfo.initFromParcel(parcel);
    }

    private AccessibilityWindowInfo()
    {
        mType = -1;
        mLayer = -1;
        mId = -1;
        mParentId = -1;
        mAnchorId = -1;
        mConnectionId = -1;
    }

    private void clear()
    {
        mType = -1;
        mLayer = -1;
        mBooleanProperties = 0;
        mId = -1;
        mParentId = -1;
        mBoundsInScreen.setEmpty();
        if(mChildIds != null)
            mChildIds.clear();
        mConnectionId = -1;
        mAnchorId = -1;
        mInPictureInPicture = false;
        mTitle = null;
    }

    private boolean getBooleanProperty(int i)
    {
        boolean flag = false;
        if((mBooleanProperties & i) != 0)
            flag = true;
        return flag;
    }

    private void initFromParcel(Parcel parcel)
    {
        boolean flag = true;
        mType = parcel.readInt();
        mLayer = parcel.readInt();
        mBooleanProperties = parcel.readInt();
        mId = parcel.readInt();
        mParentId = parcel.readInt();
        mBoundsInScreen.readFromParcel(parcel);
        mTitle = parcel.readCharSequence();
        mAnchorId = parcel.readInt();
        int i;
        if(parcel.readInt() != 1)
            flag = false;
        mInPictureInPicture = flag;
        i = parcel.readInt();
        if(i > 0)
        {
            if(mChildIds == null)
                mChildIds = new LongArray(i);
            for(int j = 0; j < i; j++)
            {
                int k = parcel.readInt();
                mChildIds.add(k);
            }

        }
        mConnectionId = parcel.readInt();
    }

    public static AccessibilityWindowInfo obtain()
    {
        AccessibilityWindowInfo accessibilitywindowinfo = (AccessibilityWindowInfo)sPool.acquire();
        AccessibilityWindowInfo accessibilitywindowinfo1 = accessibilitywindowinfo;
        if(accessibilitywindowinfo == null)
            accessibilitywindowinfo1 = new AccessibilityWindowInfo();
        if(sNumInstancesInUse != null)
            sNumInstancesInUse.incrementAndGet();
        return accessibilitywindowinfo1;
    }

    public static AccessibilityWindowInfo obtain(AccessibilityWindowInfo accessibilitywindowinfo)
    {
        AccessibilityWindowInfo accessibilitywindowinfo1 = obtain();
        accessibilitywindowinfo1.mType = accessibilitywindowinfo.mType;
        accessibilitywindowinfo1.mLayer = accessibilitywindowinfo.mLayer;
        accessibilitywindowinfo1.mBooleanProperties = accessibilitywindowinfo.mBooleanProperties;
        accessibilitywindowinfo1.mId = accessibilitywindowinfo.mId;
        accessibilitywindowinfo1.mParentId = accessibilitywindowinfo.mParentId;
        accessibilitywindowinfo1.mBoundsInScreen.set(accessibilitywindowinfo.mBoundsInScreen);
        accessibilitywindowinfo1.mTitle = accessibilitywindowinfo.mTitle;
        accessibilitywindowinfo1.mAnchorId = accessibilitywindowinfo.mAnchorId;
        accessibilitywindowinfo1.mInPictureInPicture = accessibilitywindowinfo.mInPictureInPicture;
        if(accessibilitywindowinfo.mChildIds != null && accessibilitywindowinfo.mChildIds.size() > 0)
            if(accessibilitywindowinfo1.mChildIds == null)
                accessibilitywindowinfo1.mChildIds = accessibilitywindowinfo.mChildIds.clone();
            else
                accessibilitywindowinfo1.mChildIds.addAll(accessibilitywindowinfo.mChildIds);
        accessibilitywindowinfo1.mConnectionId = accessibilitywindowinfo.mConnectionId;
        return accessibilitywindowinfo1;
    }

    private void setBooleanProperty(int i, boolean flag)
    {
        if(flag)
            mBooleanProperties = mBooleanProperties | i;
        else
            mBooleanProperties = mBooleanProperties & i;
    }

    public static void setNumInstancesInUseCounter(AtomicInteger atomicinteger)
    {
        if(sNumInstancesInUse != null)
            sNumInstancesInUse = atomicinteger;
    }

    private static String typeToString(int i)
    {
        switch(i)
        {
        default:
            return "<UNKNOWN>";

        case 1: // '\001'
            return "TYPE_APPLICATION";

        case 2: // '\002'
            return "TYPE_INPUT_METHOD";

        case 3: // '\003'
            return "TYPE_SYSTEM";

        case 4: // '\004'
            return "TYPE_ACCESSIBILITY_OVERLAY";

        case 5: // '\005'
            return "TYPE_SPLIT_SCREEN_DIVIDER";
        }
    }

    public void addChild(int i)
    {
        if(mChildIds == null)
            mChildIds = new LongArray();
        mChildIds.add(i);
    }

    public boolean changed(AccessibilityWindowInfo accessibilitywindowinfo)
    {
        if(accessibilitywindowinfo.mId != mId)
            throw new IllegalArgumentException("Not same window.");
        if(accessibilitywindowinfo.mType != mType)
            throw new IllegalArgumentException("Not same type.");
        if(!mBoundsInScreen.equals(accessibilitywindowinfo.mBoundsInScreen))
            return true;
        if(mLayer != accessibilitywindowinfo.mLayer)
            return true;
        if(mBooleanProperties != accessibilitywindowinfo.mBooleanProperties)
            return true;
        if(mParentId != accessibilitywindowinfo.mParentId)
            return true;
        if(mChildIds == null)
        {
            if(accessibilitywindowinfo.mChildIds != null)
                return true;
        } else
        if(!mChildIds.equals(accessibilitywindowinfo.mChildIds))
            return true;
        return false;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (AccessibilityWindowInfo)obj;
        if(mId != ((AccessibilityWindowInfo) (obj)).mId)
            flag = false;
        return flag;
    }

    public AccessibilityNodeInfo getAnchor()
    {
        while(mConnectionId == -1 || mAnchorId == -1 || mParentId == -1) 
            return null;
        return AccessibilityInteractionClient.getInstance().findAccessibilityNodeInfoByAccessibilityId(mConnectionId, mParentId, mAnchorId, true, 0, null);
    }

    public void getBoundsInScreen(Rect rect)
    {
        rect.set(mBoundsInScreen);
    }

    public AccessibilityWindowInfo getChild(int i)
    {
        if(mChildIds == null)
            throw new IndexOutOfBoundsException();
        if(mConnectionId == -1)
        {
            return null;
        } else
        {
            i = (int)mChildIds.get(i);
            return AccessibilityInteractionClient.getInstance().getWindow(mConnectionId, i);
        }
    }

    public int getChildCount()
    {
        int i;
        if(mChildIds != null)
            i = mChildIds.size();
        else
            i = 0;
        return i;
    }

    public int getId()
    {
        return mId;
    }

    public int getLayer()
    {
        return mLayer;
    }

    public AccessibilityWindowInfo getParent()
    {
        if(mConnectionId == -1 || mParentId == -1)
            return null;
        else
            return AccessibilityInteractionClient.getInstance().getWindow(mConnectionId, mParentId);
    }

    public AccessibilityNodeInfo getRoot()
    {
        if(mConnectionId == -1)
            return null;
        else
            return AccessibilityInteractionClient.getInstance().findAccessibilityNodeInfoByAccessibilityId(mConnectionId, mId, AccessibilityNodeInfo.ROOT_NODE_ID, true, 4, null);
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public int getType()
    {
        return mType;
    }

    public int hashCode()
    {
        return mId;
    }

    public boolean inPictureInPicture()
    {
        return isInPictureInPictureMode();
    }

    public boolean isAccessibilityFocused()
    {
        return getBooleanProperty(4);
    }

    public boolean isActive()
    {
        return getBooleanProperty(1);
    }

    public boolean isFocused()
    {
        return getBooleanProperty(2);
    }

    public boolean isInPictureInPictureMode()
    {
        return mInPictureInPicture;
    }

    public void recycle()
    {
        clear();
        sPool.release(this);
        if(sNumInstancesInUse != null)
            sNumInstancesInUse.decrementAndGet();
    }

    public void setAccessibilityFocused(boolean flag)
    {
        setBooleanProperty(4, flag);
    }

    public void setActive(boolean flag)
    {
        setBooleanProperty(1, flag);
    }

    public void setAnchorId(int i)
    {
        mAnchorId = i;
    }

    public void setBoundsInScreen(Rect rect)
    {
        mBoundsInScreen.set(rect);
    }

    public void setConnectionId(int i)
    {
        mConnectionId = i;
    }

    public void setFocused(boolean flag)
    {
        setBooleanProperty(2, flag);
    }

    public void setId(int i)
    {
        mId = i;
    }

    public void setLayer(int i)
    {
        mLayer = i;
    }

    public void setParentId(int i)
    {
        mParentId = i;
    }

    public void setPictureInPicture(boolean flag)
    {
        mInPictureInPicture = flag;
    }

    public void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public String toString()
    {
        boolean flag = false;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("AccessibilityWindowInfo[");
        stringbuilder.append("title=").append(mTitle);
        stringbuilder.append("id=").append(mId);
        stringbuilder.append(", type=").append(typeToString(mType));
        stringbuilder.append(", layer=").append(mLayer);
        stringbuilder.append(", bounds=").append(mBoundsInScreen);
        stringbuilder.append(", focused=").append(isFocused());
        stringbuilder.append(", active=").append(isActive());
        stringbuilder.append(", pictureInPicture=").append(inPictureInPicture());
        StringBuilder stringbuilder1 = stringbuilder.append(", hasParent=");
        boolean flag1;
        if(mParentId != -1)
            flag1 = true;
        else
            flag1 = false;
        stringbuilder1.append(flag1);
        stringbuilder1 = stringbuilder.append(", isAnchored=");
        if(mAnchorId != -1)
            flag1 = true;
        else
            flag1 = false;
        stringbuilder1.append(flag1);
        stringbuilder1 = stringbuilder.append(", hasChildren=");
        flag1 = flag;
        if(mChildIds != null)
        {
            flag1 = flag;
            if(mChildIds.size() > 0)
                flag1 = true;
        }
        stringbuilder1.append(flag1);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeInt(mLayer);
        parcel.writeInt(mBooleanProperties);
        parcel.writeInt(mId);
        parcel.writeInt(mParentId);
        mBoundsInScreen.writeToParcel(parcel, i);
        parcel.writeCharSequence(mTitle);
        parcel.writeInt(mAnchorId);
        LongArray longarray;
        if(mInPictureInPicture)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        longarray = mChildIds;
        if(longarray == null)
        {
            parcel.writeInt(0);
        } else
        {
            int j = longarray.size();
            parcel.writeInt(j);
            i = 0;
            while(i < j) 
            {
                parcel.writeInt((int)longarray.get(i));
                i++;
            }
        }
        parcel.writeInt(mConnectionId);
    }

    public static final int ACTIVE_WINDOW_ID = 0x7fffffff;
    public static final int ANY_WINDOW_ID = -2;
    private static final int BOOLEAN_PROPERTY_ACCESSIBILITY_FOCUSED = 4;
    private static final int BOOLEAN_PROPERTY_ACTIVE = 1;
    private static final int BOOLEAN_PROPERTY_FOCUSED = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AccessibilityWindowInfo createFromParcel(Parcel parcel)
        {
            AccessibilityWindowInfo accessibilitywindowinfo = AccessibilityWindowInfo.obtain();
            AccessibilityWindowInfo._2D_wrap0(accessibilitywindowinfo, parcel);
            return accessibilitywindowinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AccessibilityWindowInfo[] newArray(int i)
        {
            return new AccessibilityWindowInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    private static final int MAX_POOL_SIZE = 10;
    public static final int PICTURE_IN_PICTURE_ACTION_REPLACER_WINDOW_ID = -3;
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    public static final int UNDEFINED_WINDOW_ID = -1;
    private static AtomicInteger sNumInstancesInUse;
    private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(10);
    private int mAnchorId;
    private int mBooleanProperties;
    private final Rect mBoundsInScreen = new Rect();
    private LongArray mChildIds;
    private int mConnectionId;
    private int mId;
    private boolean mInPictureInPicture;
    private int mLayer;
    private int mParentId;
    private CharSequence mTitle;
    private int mType;

}
