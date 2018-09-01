// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;


public abstract class ClipboardManager
{

    public ClipboardManager()
    {
    }

    public abstract CharSequence getText();

    public abstract boolean hasText();

    public abstract void setText(CharSequence charsequence);
}
