// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;


public interface SQLiteTransactionListener
{

    public abstract void onBegin();

    public abstract void onCommit();

    public abstract void onRollback();
}
