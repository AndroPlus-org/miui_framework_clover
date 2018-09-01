// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.text.style:
//            URLSpan, AccessibilityClickableSpan

public class AccessibilityURLSpan extends URLSpan
    implements Parcelable
{

    public AccessibilityURLSpan(Parcel parcel)
    {
        super(parcel);
        mAccessibilityClickableSpan = new AccessibilityClickableSpan(parcel);
    }

    public AccessibilityURLSpan(URLSpan urlspan)
    {
        super(urlspan.getURL());
        mAccessibilityClickableSpan = new AccessibilityClickableSpan(urlspan.getId());
    }

    public void copyConnectionDataFrom(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        mAccessibilityClickableSpan.copyConnectionDataFrom(accessibilitynodeinfo);
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 26;
    }

    public void onClick(View view)
    {
        mAccessibilityClickableSpan.onClick(view);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        super.writeToParcelInternal(parcel, i);
        mAccessibilityClickableSpan.writeToParcel(parcel, i);
    }

    final AccessibilityClickableSpan mAccessibilityClickableSpan;
}
