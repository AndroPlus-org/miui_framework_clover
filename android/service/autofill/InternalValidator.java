// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.Parcelable;

// Referenced classes of package android.service.autofill:
//            Validator, ValueFinder

public abstract class InternalValidator
    implements Validator, Parcelable
{

    public InternalValidator()
    {
    }

    public abstract boolean isValid(ValueFinder valuefinder);
}
