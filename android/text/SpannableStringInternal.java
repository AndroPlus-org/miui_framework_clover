// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.lang.reflect.Array;
import libcore.util.EmptyArray;

// Referenced classes of package android.text:
//            Spanned, SpanWatcher, Spannable

abstract class SpannableStringInternal
{

    SpannableStringInternal(CharSequence charsequence, int i, int j)
    {
        if(i == 0 && j == charsequence.length())
            mText = charsequence.toString();
        else
            mText = charsequence.toString().substring(i, j);
        mSpans = EmptyArray.OBJECT;
        mSpanData = EmptyArray.INT;
        if(charsequence instanceof Spanned)
            if(charsequence instanceof SpannableStringInternal)
                copySpans((SpannableStringInternal)charsequence, i, j);
            else
                copySpans((Spanned)charsequence, i, j);
    }

    private void checkRange(String s, int i, int j)
    {
        if(j < i)
            throw new IndexOutOfBoundsException((new StringBuilder()).append(s).append(" ").append(region(i, j)).append(" has end before start").toString());
        int k = length();
        if(i > k || j > k)
            throw new IndexOutOfBoundsException((new StringBuilder()).append(s).append(" ").append(region(i, j)).append(" ends beyond length ").append(k).toString());
        if(i < 0 || j < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append(s).append(" ").append(region(i, j)).append(" starts before 0").toString());
        else
            return;
    }

    private final void copySpans(SpannableStringInternal spannablestringinternal, int i, int j)
    {
        if(i == 0 && j == spannablestringinternal.length())
        {
            mSpans = ArrayUtils.newUnpaddedObjectArray(spannablestringinternal.mSpans.length);
            mSpanData = new int[spannablestringinternal.mSpanData.length];
            mSpanCount = spannablestringinternal.mSpanCount;
            System.arraycopy(((Object) (spannablestringinternal.mSpans)), 0, ((Object) (mSpans)), 0, spannablestringinternal.mSpans.length);
            System.arraycopy(spannablestringinternal.mSpanData, 0, mSpanData, 0, mSpanData.length);
        } else
        {
            int k = 0;
            int ai[] = spannablestringinternal.mSpanData;
            int l = spannablestringinternal.mSpanCount;
            int i1 = 0;
            while(i1 < l) 
            {
                if(!isOutOfCopyRange(i, j, ai[i1 * 3 + 0], ai[i1 * 3 + 1]))
                    k++;
                i1++;
            }
            if(k == 0)
                return;
            spannablestringinternal = ((SpannableStringInternal) (spannablestringinternal.mSpans));
            mSpanCount = k;
            mSpans = ArrayUtils.newUnpaddedObjectArray(mSpanCount);
            mSpanData = new int[mSpans.length * 3];
            i1 = 0;
            k = 0;
            while(i1 < l) 
            {
                int j1 = ai[i1 * 3 + 0];
                int k1 = ai[i1 * 3 + 1];
                if(!isOutOfCopyRange(i, j, j1, k1))
                {
                    int l1 = j1;
                    if(j1 < i)
                        l1 = i;
                    j1 = k1;
                    if(k1 > j)
                        j1 = j;
                    mSpans[k] = spannablestringinternal[i1];
                    mSpanData[k * 3 + 0] = l1 - i;
                    mSpanData[k * 3 + 1] = j1 - i;
                    mSpanData[k * 3 + 2] = ai[i1 * 3 + 2];
                    k++;
                }
                i1++;
            }
        }
    }

    private final void copySpans(Spanned spanned, int i, int j)
    {
        Object aobj[] = spanned.getSpans(i, j, java/lang/Object);
        for(int k = 0; k < aobj.length; k++)
        {
            int l = spanned.getSpanStart(aobj[k]);
            int i1 = spanned.getSpanEnd(aobj[k]);
            int j1 = spanned.getSpanFlags(aobj[k]);
            int k1 = l;
            if(l < i)
                k1 = i;
            l = i1;
            if(i1 > j)
                l = j;
            setSpan(aobj[k], k1 - i, l - i, j1, false);
        }

    }

    private boolean isIndexFollowsNextLine(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i != 0)
        {
            flag1 = flag;
            if(i != length())
            {
                flag1 = flag;
                if(charAt(i - 1) != '\n')
                    flag1 = true;
            }
        }
        return flag1;
    }

    private final boolean isOutOfCopyRange(int i, int j, int k, int l)
    {
        if(k > j || l < i)
            return true;
        return k != l && i != j && (k == j || l == i);
    }

    private static String region(int i, int j)
    {
        return (new StringBuilder()).append("(").append(i).append(" ... ").append(j).append(")").toString();
    }

    private void sendSpanAdded(Object obj, int i, int j)
    {
        SpanWatcher aspanwatcher[] = (SpanWatcher[])getSpans(i, j, android/text/SpanWatcher);
        int k = aspanwatcher.length;
        for(int l = 0; l < k; l++)
            aspanwatcher[l].onSpanAdded((Spannable)this, obj, i, j);

    }

    private void sendSpanChanged(Object obj, int i, int j, int k, int l)
    {
        SpanWatcher aspanwatcher[] = (SpanWatcher[])getSpans(Math.min(i, k), Math.max(j, l), android/text/SpanWatcher);
        int i1 = aspanwatcher.length;
        for(int j1 = 0; j1 < i1; j1++)
            aspanwatcher[j1].onSpanChanged((Spannable)this, obj, i, j, k, l);

    }

    private void sendSpanRemoved(Object obj, int i, int j)
    {
        SpanWatcher aspanwatcher[] = (SpanWatcher[])getSpans(i, j, android/text/SpanWatcher);
        int k = aspanwatcher.length;
        for(int l = 0; l < k; l++)
            aspanwatcher[l].onSpanRemoved((Spannable)this, obj, i, j);

    }

    private void setSpan(Object obj, int i, int j, int k, boolean flag)
    {
        checkRange("setSpan", i, j);
        if((k & 0x33) == 51)
        {
            if(isIndexFollowsNextLine(i))
                if(!flag)
                    return;
                else
                    throw new RuntimeException((new StringBuilder()).append("PARAGRAPH span must start at paragraph boundary (").append(i).append(" follows ").append(charAt(i - 1)).append(")").toString());
            if(isIndexFollowsNextLine(j))
                if(!flag)
                    return;
                else
                    throw new RuntimeException((new StringBuilder()).append("PARAGRAPH span must end at paragraph boundary (").append(j).append(" follows ").append(charAt(j - 1)).append(")").toString());
        }
        int l = mSpanCount;
        Object aobj[] = mSpans;
        int ai1[] = mSpanData;
        for(int j1 = 0; j1 < l; j1++)
            if(aobj[j1] == obj)
            {
                int i1 = ai1[j1 * 3 + 0];
                int k1 = ai1[j1 * 3 + 1];
                ai1[j1 * 3 + 0] = i;
                ai1[j1 * 3 + 1] = j;
                ai1[j1 * 3 + 2] = k;
                sendSpanChanged(obj, i1, k1, i, j);
                return;
            }

        if(mSpanCount + 1 >= mSpans.length)
        {
            Object aobj1[] = ArrayUtils.newUnpaddedObjectArray(GrowingArrayUtils.growSize(mSpanCount));
            int ai[] = new int[aobj1.length * 3];
            System.arraycopy(((Object) (mSpans)), 0, ((Object) (aobj1)), 0, mSpanCount);
            System.arraycopy(mSpanData, 0, ai, 0, mSpanCount * 3);
            mSpans = aobj1;
            mSpanData = ai;
        }
        mSpans[mSpanCount] = obj;
        mSpanData[mSpanCount * 3 + 0] = i;
        mSpanData[mSpanCount * 3 + 1] = j;
        mSpanData[mSpanCount * 3 + 2] = k;
        mSpanCount = mSpanCount + 1;
        if(this instanceof Spannable)
            sendSpanAdded(obj, i, j);
    }

    public final char charAt(int i)
    {
        return mText.charAt(i);
    }

    public boolean equals(Object obj)
    {
        if((obj instanceof Spanned) && toString().equals(obj.toString()))
        {
            Spanned spanned = (Spanned)obj;
            Object aobj[] = spanned.getSpans(0, spanned.length(), java/lang/Object);
            if(mSpanCount == aobj.length)
            {
                int i = 0;
                do
                {
                    if(i >= mSpanCount)
                        break;
                    Object obj1 = mSpans[i];
                    obj = aobj[i];
                    if(obj1 == this)
                        while(spanned != obj || getSpanStart(obj1) != spanned.getSpanStart(obj) || getSpanEnd(obj1) != spanned.getSpanEnd(obj) || getSpanFlags(obj1) != spanned.getSpanFlags(obj)) 
                            return false;
                    else
                        while(!obj1.equals(obj) || getSpanStart(obj1) != spanned.getSpanStart(obj) || getSpanEnd(obj1) != spanned.getSpanEnd(obj) || getSpanFlags(obj1) != spanned.getSpanFlags(obj)) 
                            return false;
                    i++;
                } while(true);
                return true;
            }
        }
        return false;
    }

    public final void getChars(int i, int j, char ac[], int k)
    {
        mText.getChars(i, j, ac, k);
    }

    public int getSpanEnd(Object obj)
    {
        int i = mSpanCount;
        Object aobj[] = mSpans;
        int ai[] = mSpanData;
        for(i--; i >= 0; i--)
            if(aobj[i] == obj)
                return ai[i * 3 + 1];

        return -1;
    }

    public int getSpanFlags(Object obj)
    {
        int i = mSpanCount;
        Object aobj[] = mSpans;
        int ai[] = mSpanData;
        for(i--; i >= 0; i--)
            if(aobj[i] == obj)
                return ai[i * 3 + 2];

        return 0;
    }

    public int getSpanStart(Object obj)
    {
        int i = mSpanCount;
        Object aobj[] = mSpans;
        int ai[] = mSpanData;
        for(i--; i >= 0; i--)
            if(aobj[i] == obj)
                return ai[i * 3 + 0];

        return -1;
    }

    public Object[] getSpans(int i, int j, Class class1)
    {
        int k;
        Object aobj[];
        int ai[];
        Object aobj1[];
        Object obj;
        int l;
        int i1;
        k = mSpanCount;
        aobj = mSpans;
        ai = mSpanData;
        aobj1 = null;
        obj = null;
        l = 0;
        i1 = 0;
        break MISSING_BLOCK_LABEL_30;
_L3:
        l++;
        if(true) goto _L2; else goto _L1
_L2:
label0:
        {
            if(l >= k)
                break; /* Loop/switch isn't completed */
            int j1 = ai[l * 3 + 0];
            int l1 = ai[l * 3 + 1];
            if(j1 <= j && l1 >= i && (j1 == l1 || i == j || j1 != j && l1 != i) && (class1 == null || class1 == java/lang/Object || !(class1.isInstance(aobj[l]) ^ true)))
                if(i1 == 0)
                {
                    obj = aobj[l];
                    i1++;
                } else
                {
label1:
                    {
                        if(i1 == 1)
                        {
                            aobj1 = (Object[])Array.newInstance(class1, (k - l) + 1);
                            aobj1[0] = obj;
                        }
                        int k1 = ai[l * 3 + 2] & 0xff0000;
                        if(k1 == 0)
                            break label0;
                        for(int i2 = 0; i2 < i1 && k1 <= (getSpanFlags(aobj1[i2]) & 0xff0000); i2++)
                            break label1;

                        System.arraycopy(((Object) (aobj1)), i2, ((Object) (aobj1)), i2 + 1, i1 - i2);
                        aobj1[i2] = aobj[l];
                        i1++;
                    }
                }
        }
          goto _L3
        int j2 = i1 + 1;
        aobj1[i1] = aobj[l];
        i1 = j2;
          goto _L3
_L1:
        if(i1 == 0)
            return ArrayUtils.emptyArray(class1);
        if(i1 == 1)
        {
            class1 = ((Class) ((Object[])Array.newInstance(class1, 1)));
            class1[0] = obj;
            return class1;
        }
        if(i1 == aobj1.length)
        {
            return aobj1;
        } else
        {
            class1 = ((Class) ((Object[])Array.newInstance(class1, i1)));
            System.arraycopy(((Object) (aobj1)), 0, class1, 0, i1);
            return class1;
        }
    }

    public int hashCode()
    {
        int i = toString().hashCode() * 31 + mSpanCount;
        for(int j = 0; j < mSpanCount; j++)
        {
            Object obj = mSpans[j];
            int k = i;
            if(obj != this)
                k = i * 31 + obj.hashCode();
            i = ((k * 31 + getSpanStart(obj)) * 31 + getSpanEnd(obj)) * 31 + getSpanFlags(obj);
        }

        return i;
    }

    public final int length()
    {
        return mText.length();
    }

    public int nextSpanTransition(int i, int j, Class class1)
    {
        int k = mSpanCount;
        Object aobj[] = mSpans;
        int ai[] = mSpanData;
        Object obj = class1;
        if(class1 == null)
            obj = java/lang/Object;
        for(int l = 0; l < k; l++)
        {
            int i1 = ai[l * 3 + 0];
            int j1 = ai[l * 3 + 1];
            int k1 = j;
            if(i1 > i)
            {
                k1 = j;
                if(i1 < j)
                {
                    k1 = j;
                    if(((Class) (obj)).isInstance(aobj[l]))
                        k1 = i1;
                }
            }
            j = k1;
            if(j1 <= i)
                continue;
            j = k1;
            if(j1 >= k1)
                continue;
            j = k1;
            if(((Class) (obj)).isInstance(aobj[l]))
                j = j1;
        }

        return j;
    }

    void removeSpan(Object obj)
    {
        int i = mSpanCount;
        Object aobj[] = mSpans;
        int ai[] = mSpanData;
        for(int j = i - 1; j >= 0; j--)
            if(aobj[j] == obj)
            {
                int k = ai[j * 3 + 0];
                int l = ai[j * 3 + 1];
                i -= j + 1;
                System.arraycopy(((Object) (aobj)), j + 1, ((Object) (aobj)), j, i);
                System.arraycopy(ai, (j + 1) * 3, ai, j * 3, i * 3);
                mSpanCount = mSpanCount - 1;
                sendSpanRemoved(obj, k, l);
                return;
            }

    }

    void setSpan(Object obj, int i, int j, int k)
    {
        setSpan(obj, i, j, k, true);
    }

    public final String toString()
    {
        return mText;
    }

    private static final int COLUMNS = 3;
    static final Object EMPTY[] = new Object[0];
    private static final int END = 1;
    private static final int FLAGS = 2;
    private static final int START = 0;
    private int mSpanCount;
    private int mSpanData[];
    private Object mSpans[];
    private String mText;

}
