// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;


public class DatabaseObjectNotClosedException extends RuntimeException
{

    public DatabaseObjectNotClosedException()
    {
        super("Application did not close the cursor or database object that was opened here");
    }

    private static final String s = "Application did not close the cursor or database object that was opened here";
}
