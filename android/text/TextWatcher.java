// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;


// Referenced classes of package android.text:
//            NoCopySpan, Editable

public interface TextWatcher
    extends NoCopySpan
{

    public abstract void afterTextChanged(Editable editable);

    public abstract void beforeTextChanged(CharSequence charsequence, int i, int j, int k);

    public abstract void onTextChanged(CharSequence charsequence, int i, int j, int k);
}
