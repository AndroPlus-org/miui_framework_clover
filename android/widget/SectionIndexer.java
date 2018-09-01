// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;


public interface SectionIndexer
{

    public abstract int getPositionForSection(int i);

    public abstract int getSectionForPosition(int i);

    public abstract Object[] getSections();
}
