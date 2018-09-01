// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.Parcelable;
import android.widget.RemoteViews;

// Referenced classes of package android.service.autofill:
//            Transformation, ValueFinder

abstract class InternalTransformation
    implements Transformation, Parcelable
{

    InternalTransformation()
    {
    }

    abstract void apply(ValueFinder valuefinder, RemoteViews remoteviews, int i)
        throws Exception;
}
