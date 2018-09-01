// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.os.Bundle;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.Spanned;
import android.view.View;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.text.style:
//            ClickableSpan

public class AccessibilityClickableSpan extends ClickableSpan
    implements ParcelableSpan
{

    public AccessibilityClickableSpan(int i)
    {
        mWindowId = -1;
        mSourceNodeId = AccessibilityNodeInfo.UNDEFINED_NODE_ID;
        mConnectionId = -1;
        mOriginalClickableSpanId = i;
    }

    public AccessibilityClickableSpan(Parcel parcel)
    {
        mWindowId = -1;
        mSourceNodeId = AccessibilityNodeInfo.UNDEFINED_NODE_ID;
        mConnectionId = -1;
        mOriginalClickableSpanId = parcel.readInt();
    }

    public void copyConnectionDataFrom(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        mConnectionId = accessibilitynodeinfo.getConnectionId();
        mWindowId = accessibilitynodeinfo.getWindowId();
        mSourceNodeId = accessibilitynodeinfo.getSourceNodeId();
    }

    public int describeContents()
    {
        return 0;
    }

    public ClickableSpan findClickableSpan(CharSequence charsequence)
    {
        if(!(charsequence instanceof Spanned))
            return null;
        charsequence = (ClickableSpan[])((Spanned)charsequence).getSpans(0, charsequence.length(), android/text/style/ClickableSpan);
        for(int i = 0; i < charsequence.length; i++)
            if(charsequence[i].getId() == mOriginalClickableSpanId)
                return charsequence[i];

        return null;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 25;
    }

    public void onClick(View view)
    {
        view = new Bundle();
        view.putParcelable("android.view.accessibility.action.ACTION_ARGUMENT_ACCESSIBLE_CLICKABLE_SPAN", this);
        while(mWindowId == -1 || mSourceNodeId == AccessibilityNodeInfo.UNDEFINED_NODE_ID || mConnectionId == -1) 
            throw new RuntimeException("ClickableSpan for accessibility service not properly initialized");
        AccessibilityInteractionClient.getInstance().performAccessibilityAction(mConnectionId, mWindowId, mSourceNodeId, 0x1020168, view);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeInt(mOriginalClickableSpanId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AccessibilityClickableSpan createFromParcel(Parcel parcel)
        {
            return new AccessibilityClickableSpan(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AccessibilityClickableSpan[] newArray(int i)
        {
            return new AccessibilityClickableSpan[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mConnectionId;
    private final int mOriginalClickableSpanId;
    private long mSourceNodeId;
    private int mWindowId;

}
