// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.view.autofill.AutofillId;

public interface ValueFinder
{

    public abstract String findByAutofillId(AutofillId autofillid);
}
