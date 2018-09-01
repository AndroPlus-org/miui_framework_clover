// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.util.SparseIntArray;
import java.text.Collator;

// Referenced classes of package android.widget:
//            SectionIndexer

public class AlphabetIndexer extends DataSetObserver
    implements SectionIndexer
{

    public AlphabetIndexer(Cursor cursor, int i, CharSequence charsequence)
    {
        mDataCursor = cursor;
        mColumnIndex = i;
        mAlphabet = charsequence;
        mAlphabetLength = charsequence.length();
        mAlphabetArray = new String[mAlphabetLength];
        for(i = 0; i < mAlphabetLength; i++)
            mAlphabetArray[i] = Character.toString(mAlphabet.charAt(i));

        mAlphaMap = new SparseIntArray(mAlphabetLength);
        if(cursor != null)
            cursor.registerDataSetObserver(this);
        mCollator = Collator.getInstance();
        mCollator.setStrength(0);
    }

    protected int compare(String s, String s1)
    {
        if(s.length() == 0)
            s = " ";
        else
            s = s.substring(0, 1);
        return mCollator.compare(s, s1);
    }

    public int getPositionForSection(int i)
    {
        SparseIntArray sparseintarray;
        Cursor cursor;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        char c;
        String s;
        sparseintarray = mAlphaMap;
        cursor = mDataCursor;
        if(cursor == null || mAlphabet == null)
            return 0;
        if(i <= 0)
            return 0;
        j = i;
        if(i >= mAlphabetLength)
            j = mAlphabetLength - 1;
        k = cursor.getPosition();
        l = cursor.getCount();
        i1 = 0;
        j1 = l;
        c = mAlphabet.charAt(j);
        s = Character.toString(c);
        i = sparseintarray.get(c, 0x80000000);
        if(0x80000000 == i) goto _L2; else goto _L1
_L1:
        if(i >= 0) goto _L4; else goto _L3
_L3:
        j1 = -i;
_L2:
        i = i1;
        if(j > 0)
        {
            j = sparseintarray.get(mAlphabet.charAt(j - 1), 0x80000000);
            i = i1;
            if(j != 0x80000000)
                i = Math.abs(j);
        }
        i1 = (j1 + i) / 2;
        j = i;
        i = i1;
_L11:
        i1 = i;
        if(i >= j1) goto _L6; else goto _L5
_L5:
        String s1;
        cursor.moveToPosition(i);
        s1 = cursor.getString(mColumnIndex);
        if(s1 != null) goto _L8; else goto _L7
_L7:
        if(i != 0) goto _L10; else goto _L9
_L9:
        i1 = i;
_L6:
        sparseintarray.put(c, i1);
        cursor.moveToPosition(k);
        return i1;
_L4:
        return i;
_L10:
        i--;
          goto _L11
_L8:
        i1 = compare(s1, s);
        if(i1 == 0) goto _L13; else goto _L12
_L12:
label0:
        {
            if(i1 >= 0)
                break label0;
            i1 = i + 1;
            i = j1;
            j = i1;
            if(i1 < l)
                break label0;
            i1 = l;
        }
          goto _L6
_L14:
        i1 = (j + i) / 2;
        j1 = i;
        i = i1;
          goto _L11
_L13:
        i1 = i;
        if(j == i) goto _L6; else goto _L14
    }

    public int getSectionForPosition(int i)
    {
        int j = mDataCursor.getPosition();
        mDataCursor.moveToPosition(i);
        String s = mDataCursor.getString(mColumnIndex);
        mDataCursor.moveToPosition(j);
        for(i = 0; i < mAlphabetLength; i++)
            if(compare(s, Character.toString(mAlphabet.charAt(i))) == 0)
                return i;

        return 0;
    }

    public Object[] getSections()
    {
        return mAlphabetArray;
    }

    public void onChanged()
    {
        super.onChanged();
        mAlphaMap.clear();
    }

    public void onInvalidated()
    {
        super.onInvalidated();
        mAlphaMap.clear();
    }

    public void setCursor(Cursor cursor)
    {
        if(mDataCursor != null)
            mDataCursor.unregisterDataSetObserver(this);
        mDataCursor = cursor;
        if(cursor != null)
            mDataCursor.registerDataSetObserver(this);
        mAlphaMap.clear();
    }

    private SparseIntArray mAlphaMap;
    protected CharSequence mAlphabet;
    private String mAlphabetArray[];
    private int mAlphabetLength;
    private Collator mCollator;
    protected int mColumnIndex;
    protected Cursor mDataCursor;
}
