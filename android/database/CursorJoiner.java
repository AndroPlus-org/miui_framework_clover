// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import java.util.Iterator;

// Referenced classes of package android.database:
//            Cursor

public final class CursorJoiner
    implements Iterator, Iterable
{
    public static final class Result extends Enum
    {

        public static Result valueOf(String s)
        {
            return (Result)Enum.valueOf(android/database/CursorJoiner$Result, s);
        }

        public static Result[] values()
        {
            return $VALUES;
        }

        private static final Result $VALUES[];
        public static final Result BOTH;
        public static final Result LEFT;
        public static final Result RIGHT;

        static 
        {
            RIGHT = new Result("RIGHT", 0);
            LEFT = new Result("LEFT", 1);
            BOTH = new Result("BOTH", 2);
            $VALUES = (new Result[] {
                RIGHT, LEFT, BOTH
            });
        }

        private Result(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getandroid_2D_database_2D_CursorJoiner$ResultSwitchesValues()
    {
        if(_2D_android_2D_database_2D_CursorJoiner$ResultSwitchesValues != null)
            return _2D_android_2D_database_2D_CursorJoiner$ResultSwitchesValues;
        int ai[] = new int[Result.values().length];
        try
        {
            ai[Result.BOTH.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Result.LEFT.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Result.RIGHT.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_database_2D_CursorJoiner$ResultSwitchesValues = ai;
        return ai;
    }

    public CursorJoiner(Cursor cursor, String as[], Cursor cursor1, String as1[])
    {
        if(as.length != as1.length)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("you must have the same number of columns on the left and right, ").append(as.length).append(" != ").append(as1.length).toString());
        } else
        {
            mCursorLeft = cursor;
            mCursorRight = cursor1;
            mCursorLeft.moveToFirst();
            mCursorRight.moveToFirst();
            mCompareResultIsValid = false;
            mColumnsLeft = buildColumnIndiciesArray(cursor, as);
            mColumnsRight = buildColumnIndiciesArray(cursor1, as1);
            mValues = new String[mColumnsLeft.length * 2];
            return;
        }
    }

    private int[] buildColumnIndiciesArray(Cursor cursor, String as[])
    {
        int ai[] = new int[as.length];
        for(int i = 0; i < as.length; i++)
            ai[i] = cursor.getColumnIndexOrThrow(as[i]);

        return ai;
    }

    private static transient int compareStrings(String as[])
    {
        byte byte0;
        int i;
        byte0 = -1;
        if(as.length % 2 != 0)
            throw new IllegalArgumentException("you must specify an even number of values");
        i = 0;
_L5:
        if(i >= as.length)
            break MISSING_BLOCK_LABEL_88;
        if(as[i] != null) goto _L2; else goto _L1
_L1:
        if(as[i + 1] != null) goto _L4; else goto _L3
_L3:
        i += 2;
          goto _L5
_L4:
        return -1;
_L2:
        int j;
        if(as[i + 1] == null)
            return 1;
        j = as[i].compareTo(as[i + 1]);
        if(j == 0) goto _L3; else goto _L6
_L6:
        byte byte1;
        if(j < 0)
            byte1 = byte0;
        else
            byte1 = 1;
        return byte1;
        return 0;
    }

    private void incrementCursors()
    {
        if(!mCompareResultIsValid) goto _L2; else goto _L1
_L1:
        _2D_getandroid_2D_database_2D_CursorJoiner$ResultSwitchesValues()[mCompareResult.ordinal()];
        JVM INSTR tableswitch 1 3: default 44
    //                   1 76
    //                   2 50
    //                   3 63;
           goto _L3 _L4 _L5 _L6
_L3:
        mCompareResultIsValid = false;
_L2:
        return;
_L5:
        mCursorLeft.moveToNext();
        continue; /* Loop/switch isn't completed */
_L6:
        mCursorRight.moveToNext();
        continue; /* Loop/switch isn't completed */
_L4:
        mCursorLeft.moveToNext();
        mCursorRight.moveToNext();
        if(true) goto _L3; else goto _L7
_L7:
    }

    private static void populateValues(String as[], Cursor cursor, int ai[], int i)
    {
        if(!_2D_assertionsDisabled && i != 0 && i != 1)
            throw new AssertionError();
        for(int j = 0; j < ai.length; j++)
            as[j * 2 + i] = cursor.getString(ai[j]);

    }

    public boolean hasNext()
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        if(mCompareResultIsValid)
        {
            switch(_2D_getandroid_2D_database_2D_CursorJoiner$ResultSwitchesValues()[mCompareResult.ordinal()])
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("bad value for mCompareResult, ").append(mCompareResult).toString());

            case 1: // '\001'
                if(mCursorLeft.isLast())
                    flag3 = mCursorRight.isLast() ^ true;
                return flag3;

            case 2: // '\002'
                flag3 = flag;
                if(mCursorLeft.isLast())
                    flag3 = mCursorRight.isAfterLast() ^ true;
                return flag3;

            case 3: // '\003'
                flag3 = flag1;
                break;
            }
            if(mCursorLeft.isAfterLast())
                flag3 = mCursorRight.isLast() ^ true;
            return flag3;
        }
        flag3 = flag2;
        if(mCursorLeft.isAfterLast())
            flag3 = mCursorRight.isAfterLast() ^ true;
        return flag3;
    }

    public Iterator iterator()
    {
        return this;
    }

    public Result next()
    {
        boolean flag;
        boolean flag1;
        if(!hasNext())
            throw new IllegalStateException("you must only call next() when hasNext() is true");
        incrementCursors();
        if(!_2D_assertionsDisabled && !hasNext())
            throw new AssertionError();
        flag = mCursorLeft.isAfterLast() ^ true;
        flag1 = mCursorRight.isAfterLast() ^ true;
        if(!flag || !flag1) goto _L2; else goto _L1
_L1:
        populateValues(mValues, mCursorLeft, mColumnsLeft, 0);
        populateValues(mValues, mCursorRight, mColumnsRight, 1);
        compareStrings(mValues);
        JVM INSTR tableswitch -1 1: default 140
    //                   -1 150
    //                   0 160
    //                   1 170;
           goto _L3 _L4 _L5 _L6
_L3:
        mCompareResultIsValid = true;
        return mCompareResult;
_L4:
        mCompareResult = Result.LEFT;
        continue; /* Loop/switch isn't completed */
_L5:
        mCompareResult = Result.BOTH;
        continue; /* Loop/switch isn't completed */
_L6:
        mCompareResult = Result.RIGHT;
        continue; /* Loop/switch isn't completed */
_L2:
        if(flag)
        {
            mCompareResult = Result.LEFT;
        } else
        {
            if(!_2D_assertionsDisabled && !flag1)
                throw new AssertionError();
            mCompareResult = Result.RIGHT;
        }
        if(true) goto _L3; else goto _L7
_L7:
    }

    public volatile Object next()
    {
        return next();
    }

    public void remove()
    {
        throw new UnsupportedOperationException("not implemented");
    }

    private static final int _2D_android_2D_database_2D_CursorJoiner$ResultSwitchesValues[];
    static final boolean _2D_assertionsDisabled = android/database/CursorJoiner.desiredAssertionStatus() ^ true;
    private int mColumnsLeft[];
    private int mColumnsRight[];
    private Result mCompareResult;
    private boolean mCompareResultIsValid;
    private Cursor mCursorLeft;
    private Cursor mCursorRight;
    private String mValues[];

}
