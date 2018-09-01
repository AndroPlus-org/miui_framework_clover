// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Rect;
import android.os.*;
import java.util.ArrayList;
import java.util.List;

public class WindowInfo
    implements Parcelable
{

    static void _2D_wrap0(WindowInfo windowinfo, Parcel parcel)
    {
        windowinfo.initFromParcel(parcel);
    }

    private WindowInfo()
    {
        accessibilityIdOfAnchor = -1;
    }

    private void clear()
    {
        type = 0;
        layer = 0;
        token = null;
        parentToken = null;
        activityToken = null;
        focused = false;
        boundsInScreen.setEmpty();
        if(childTokens != null)
            childTokens.clear();
        inPictureInPicture = false;
    }

    private void initFromParcel(Parcel parcel)
    {
        boolean flag = false;
        type = parcel.readInt();
        layer = parcel.readInt();
        token = parcel.readStrongBinder();
        parentToken = parcel.readStrongBinder();
        activityToken = parcel.readStrongBinder();
        boolean flag1;
        boolean flag2;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        focused = flag1;
        boundsInScreen.readFromParcel(parcel);
        title = parcel.readCharSequence();
        accessibilityIdOfAnchor = parcel.readInt();
        flag1 = flag;
        if(parcel.readInt() == 1)
            flag1 = true;
        inPictureInPicture = flag1;
        if(parcel.readInt() == 1)
            flag2 = true;
        else
            flag2 = false;
        if(flag2)
        {
            if(childTokens == null)
                childTokens = new ArrayList();
            parcel.readBinderList(childTokens);
        }
    }

    public static WindowInfo obtain()
    {
        WindowInfo windowinfo = (WindowInfo)sPool.acquire();
        WindowInfo windowinfo1 = windowinfo;
        if(windowinfo == null)
            windowinfo1 = new WindowInfo();
        return windowinfo1;
    }

    public static WindowInfo obtain(WindowInfo windowinfo)
    {
        WindowInfo windowinfo1 = obtain();
        windowinfo1.type = windowinfo.type;
        windowinfo1.layer = windowinfo.layer;
        windowinfo1.token = windowinfo.token;
        windowinfo1.parentToken = windowinfo.parentToken;
        windowinfo1.activityToken = windowinfo.activityToken;
        windowinfo1.focused = windowinfo.focused;
        windowinfo1.boundsInScreen.set(windowinfo.boundsInScreen);
        windowinfo1.title = windowinfo.title;
        windowinfo1.accessibilityIdOfAnchor = windowinfo.accessibilityIdOfAnchor;
        windowinfo1.inPictureInPicture = windowinfo.inPictureInPicture;
        if(windowinfo.childTokens != null && windowinfo.childTokens.isEmpty() ^ true)
            if(windowinfo1.childTokens == null)
                windowinfo1.childTokens = new ArrayList(windowinfo.childTokens);
            else
                windowinfo1.childTokens.addAll(windowinfo.childTokens);
        return windowinfo1;
    }

    public int describeContents()
    {
        return 0;
    }

    public void recycle()
    {
        clear();
        sPool.release(this);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("WindowInfo[");
        stringbuilder.append("title=").append(title);
        stringbuilder.append(", type=").append(type);
        stringbuilder.append(", layer=").append(layer);
        stringbuilder.append(", token=").append(token);
        stringbuilder.append(", bounds=").append(boundsInScreen);
        stringbuilder.append(", parent=").append(parentToken);
        stringbuilder.append(", focused=").append(focused);
        stringbuilder.append(", children=").append(childTokens);
        stringbuilder.append(", accessibility anchor=").append(accessibilityIdOfAnchor);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(type);
        parcel.writeInt(layer);
        parcel.writeStrongBinder(token);
        parcel.writeStrongBinder(parentToken);
        parcel.writeStrongBinder(activityToken);
        int j;
        if(focused)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        boundsInScreen.writeToParcel(parcel, i);
        parcel.writeCharSequence(title);
        parcel.writeInt(accessibilityIdOfAnchor);
        if(inPictureInPicture)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(childTokens != null && childTokens.isEmpty() ^ true)
        {
            parcel.writeInt(1);
            parcel.writeBinderList(childTokens);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WindowInfo createFromParcel(Parcel parcel)
        {
            WindowInfo windowinfo = WindowInfo.obtain();
            WindowInfo._2D_wrap0(windowinfo, parcel);
            return windowinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WindowInfo[] newArray(int i)
        {
            return new WindowInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_POOL_SIZE = 10;
    private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(10);
    public int accessibilityIdOfAnchor;
    public IBinder activityToken;
    public final Rect boundsInScreen = new Rect();
    public List childTokens;
    public boolean focused;
    public boolean inPictureInPicture;
    public int layer;
    public IBinder parentToken;
    public CharSequence title;
    public IBinder token;
    public int type;

}
