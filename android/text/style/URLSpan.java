// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.content.*;
import android.net.Uri;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.util.Log;
import android.view.View;

// Referenced classes of package android.text.style:
//            ClickableSpan

public class URLSpan extends ClickableSpan
    implements ParcelableSpan
{

    public URLSpan(Parcel parcel)
    {
        mURL = parcel.readString();
    }

    public URLSpan(String s)
    {
        mURL = s;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 11;
    }

    public String getURL()
    {
        return mURL;
    }

    public void onClick(View view)
    {
        Object obj;
        obj = Uri.parse(getURL());
        view = view.getContext();
        obj = new Intent("android.intent.action.VIEW", ((Uri) (obj)));
        ((Intent) (obj)).putExtra("com.android.browser.application_id", view.getPackageName());
        view.startActivity(((Intent) (obj)));
_L1:
        return;
        view;
        Log.w("URLSpan", (new StringBuilder()).append("Actvity was not found for intent, ").append(((Intent) (obj)).toString()).toString());
          goto _L1
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeString(mURL);
    }

    private final String mURL;
}
