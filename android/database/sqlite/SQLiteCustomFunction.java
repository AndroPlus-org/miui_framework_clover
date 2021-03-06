// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;


public final class SQLiteCustomFunction
{

    public SQLiteCustomFunction(String s, int i, SQLiteDatabase.CustomFunction customfunction)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("name must not be null.");
        } else
        {
            name = s;
            numArgs = i;
            callback = customfunction;
            return;
        }
    }

    private void dispatchCallback(String as[])
    {
        callback.callback(as);
    }

    public final SQLiteDatabase.CustomFunction callback;
    public final String name;
    public final int numArgs;
}
