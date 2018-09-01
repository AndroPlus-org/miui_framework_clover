// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.graphics.BaseCanvas;
import android.graphics.Paint;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.IdentityHashMap;
import libcore.util.EmptyArray;

// Referenced classes of package android.text:
//            GetChars, Spannable, Editable, GraphicsOperations, 
//            InputFilter, TextUtils, Spanned, NoCopySpan, 
//            TextWatcher, SpanWatcher, Selection

public class SpannableStringBuilder
    implements CharSequence, GetChars, Spannable, Editable, Appendable, GraphicsOperations
{

    public SpannableStringBuilder()
    {
        this("");
    }

    public SpannableStringBuilder(CharSequence charsequence)
    {
        this(charsequence, 0, charsequence.length());
    }

    public SpannableStringBuilder(CharSequence charsequence, int i, int j)
    {
        mFilters = NO_FILTERS;
        int k = j - i;
        if(k < 0)
            throw new StringIndexOutOfBoundsException();
        mText = ArrayUtils.newUnpaddedCharArray(GrowingArrayUtils.growSize(k));
        mGapStart = k;
        mGapLength = mText.length - k;
        TextUtils.getChars(charsequence, i, j, mText, 0);
        mSpanCount = 0;
        mSpanInsertCount = 0;
        mSpans = EmptyArray.OBJECT;
        mSpanStarts = EmptyArray.INT;
        mSpanEnds = EmptyArray.INT;
        mSpanFlags = EmptyArray.INT;
        mSpanMax = EmptyArray.INT;
        mSpanOrder = EmptyArray.INT;
        if(charsequence instanceof Spanned)
        {
            Spanned spanned = (Spanned)charsequence;
            charsequence = ((CharSequence) (spanned.getSpans(i, j, java/lang/Object)));
            int i1 = 0;
            while(i1 < charsequence.length) 
            {
                if(!(charsequence[i1] instanceof NoCopySpan))
                {
                    int j1 = spanned.getSpanStart(charsequence[i1]) - i;
                    int k1 = spanned.getSpanEnd(charsequence[i1]) - i;
                    int l1 = spanned.getSpanFlags(charsequence[i1]);
                    int l = j1;
                    if(j1 < 0)
                        l = 0;
                    j1 = l;
                    if(l > j - i)
                        j1 = j - i;
                    l = k1;
                    if(k1 < 0)
                        l = 0;
                    k1 = l;
                    if(l > j - i)
                        k1 = j - i;
                    setSpan(false, charsequence[i1], j1, k1, l1, false);
                }
                i1++;
            }
            restoreInvariants();
        }
    }

    private int calcMax(int i)
    {
        int j = 0;
        if((i & 1) != 0)
            j = calcMax(leftChild(i));
        int k = j;
        if(i < mSpanCount)
        {
            j = Math.max(j, mSpanEnds[i]);
            k = j;
            if((i & 1) != 0)
                k = Math.max(j, calcMax(rightChild(i)));
        }
        mSpanMax[i] = k;
        return k;
    }

    private void change(int i, int j, CharSequence charsequence, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        i1 = j - i;
        j1 = l - k;
        k1 = j1 - i1;
        l1 = 0;
        i2 = mSpanCount - 1;
_L17:
        int j2;
        int i3;
        int l3;
        int k4;
        int l4;
        if(i2 < 0)
            break MISSING_BLOCK_LABEL_422;
        j2 = mSpanStarts[i2];
        i3 = j2;
        if(j2 > mGapStart)
            i3 = j2 - mGapLength;
        l3 = mSpanEnds[i2];
        j2 = l3;
        if(l3 > mGapStart)
            j2 = l3 - mGapLength;
        k4 = i3;
        l4 = j2;
        l3 = l1;
        if((mSpanFlags[i2] & 0x33) != 51) goto _L2; else goto _L1
_L1:
        k4 = length();
        l4 = i3;
        if(i3 <= i) goto _L4; else goto _L3
_L3:
        l4 = i3;
        if(i3 > j) goto _L4; else goto _L5
_L5:
        l3 = j;
_L14:
        l4 = l3;
        if(l3 >= k4) goto _L4; else goto _L6
_L6:
        if(l3 <= j || charAt(l3 - 1) != '\n') goto _L8; else goto _L7
_L7:
        l4 = l3;
_L4:
        int i5;
        i5 = l4;
        l4 = j2;
        if(j2 <= i) goto _L10; else goto _L9
_L9:
        l4 = j2;
        if(j2 > j) goto _L10; else goto _L11
_L11:
        l3 = j;
_L15:
        l4 = l3;
        if(l3 < k4)
        {
            if(l3 <= j || charAt(l3 - 1) != '\n')
                break MISSING_BLOCK_LABEL_376;
            l4 = l3;
        }
_L10:
        int j5 = l4;
        if(i5 != i3) goto _L13; else goto _L12
_L12:
        k4 = i5;
        l4 = j5;
        l3 = l1;
        if(j5 == j2) goto _L2; else goto _L13
_L13:
        setSpan(false, mSpans[i2], i5, j5, mSpanFlags[i2], true);
        l3 = 1;
        l4 = j5;
        k4 = i5;
_L2:
        char c = '\0';
        int k2;
        int ai[];
        if(k4 == i)
            c = '\u1000';
        else
        if(k4 == j + k1)
            c = '\u2000';
        if(l4 == i)
        {
            k2 = c | 0x4000;
        } else
        {
            k2 = c;
            if(l4 == j + k1)
                k2 = c | 0x8000;
        }
        ai = mSpanFlags;
        ai[i2] = ai[i2] | k2;
        i2--;
        l1 = l3;
        continue; /* Loop/switch isn't completed */
_L8:
        l3++;
          goto _L14
        l3++;
          goto _L15
        if(l1 != 0)
            restoreInvariants();
        moveGapTo(j);
        if(k1 >= mGapLength)
            resizeFor((mText.length + k1) - mGapLength);
        boolean flag;
        if(j1 == 0)
            flag = true;
        else
            flag = false;
        if(i1 > 0)
            while(mSpanCount > 0 && removeSpansForChange(i, j, flag, treeRoot())) ;
        mGapStart = mGapStart + k1;
        mGapLength = mGapLength - k1;
        if(mGapLength < 1)
            (new Exception("mGapLength < 1")).printStackTrace();
        TextUtils.getChars(charsequence, k, l, mText, i);
        if(i1 > 0)
        {
            boolean flag1;
            if(mGapStart + mGapLength == mText.length)
                flag1 = true;
            else
                flag1 = false;
            for(j = 0; j < mSpanCount; j++)
            {
                int j3 = mSpanFlags[j];
                mSpanStarts[j] = updatedIntervalBound(mSpanStarts[j], i, k1, (j3 & 0xf0) >> 4, flag1, flag);
                j3 = mSpanFlags[j];
                mSpanEnds[j] = updatedIntervalBound(mSpanEnds[j], i, k1, j3 & 0xf, flag1, flag);
            }

            restoreInvariants();
        }
        if(charsequence instanceof Spanned)
        {
            Spanned spanned = (Spanned)charsequence;
            charsequence = ((CharSequence) (spanned.getSpans(k, l, java/lang/Object)));
            for(j = 0; j < charsequence.length; j++)
            {
                int l2 = spanned.getSpanStart(charsequence[j]);
                int i4 = spanned.getSpanEnd(charsequence[j]);
                int k3 = l2;
                if(l2 < k)
                    k3 = k;
                l2 = i4;
                if(i4 > l)
                    l2 = l;
                if(getSpanStart(charsequence[j]) < 0)
                {
                    int j4 = spanned.getSpanFlags(charsequence[j]);
                    setSpan(false, charsequence[j], (k3 - k) + i, (l2 - k) + i, j4 | 0x800, false);
                }
            }

            restoreInvariants();
        }
        return;
        if(true) goto _L17; else goto _L16
_L16:
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

    private static int[] checkSortBuffer(int ai[], int i)
    {
        if(ai == null || i > ai.length)
            return ArrayUtils.newUnpaddedIntArray(GrowingArrayUtils.growSize(i));
        else
            return ai;
    }

    private final int compareSpans(int i, int j, int ai[], int ai1[])
    {
        int k = ai[i];
        int l = ai[j];
        if(k == l)
            return Integer.compare(ai1[i], ai1[j]);
        else
            return Integer.compare(l, k);
    }

    private int countSpans(int i, int j, Class class1, int k)
    {
        int l;
        int i1;
        l = 0;
        i1 = l;
        if((k & 1) != 0)
        {
            int j1 = leftChild(k);
            i1 = mSpanMax[j1];
            int l1 = i1;
            if(i1 > mGapStart)
                l1 = i1 - mGapLength;
            i1 = l;
            if(l1 >= i)
                i1 = countSpans(i, j, class1, j1);
        }
        l = i1;
        if(k >= mSpanCount) goto _L2; else goto _L1
_L1:
        int k1;
        int i2 = mSpanStarts[k];
        k1 = i2;
        if(i2 > mGapStart)
            k1 = i2 - mGapLength;
        l = i1;
        if(k1 > j) goto _L2; else goto _L3
_L3:
        int j2;
        j2 = mSpanEnds[k];
        l = j2;
        if(j2 > mGapStart)
            l = j2 - mGapLength;
        j2 = i1;
        if(l < i) goto _L5; else goto _L4
_L4:
        if(k1 != l && i != j) goto _L7; else goto _L6
_L6:
        if(java/lang/Object == class1) goto _L9; else goto _L8
_L8:
        j2 = i1;
        if(!class1.isInstance(mSpans[k])) goto _L5; else goto _L9
_L9:
        j2 = i1 + 1;
_L5:
        l = j2;
        if((k & 1) != 0)
            l = j2 + countSpans(i, j, class1, rightChild(k));
_L2:
        return l;
_L7:
        j2 = i1;
        if(k1 == j) goto _L5; else goto _L10
_L10:
        j2 = i1;
        if(l == i) goto _L5; else goto _L6
    }

    private int getSpansRec(int i, int j, Class class1, int k, Object aobj[], int ai[], int ai1[], 
            int l, boolean flag)
    {
        int i1;
        int k1;
        int i2;
        i1 = l;
        if((k & 1) != 0)
        {
            int j1 = leftChild(k);
            i1 = mSpanMax[j1];
            int l1 = i1;
            if(i1 > mGapStart)
                l1 = i1 - mGapLength;
            i1 = l;
            if(l1 >= i)
                i1 = getSpansRec(i, j, class1, j1, aobj, ai, ai1, l, flag);
        }
        if(k >= mSpanCount)
            return i1;
        l = mSpanStarts[k];
        k1 = l;
        if(l > mGapStart)
            k1 = l - mGapLength;
        i2 = i1;
        if(k1 > j) goto _L2; else goto _L1
_L1:
        l = mSpanEnds[k];
        i2 = l;
        if(l > mGapStart)
            i2 = l - mGapLength;
        l = i1;
        if(i2 < i) goto _L4; else goto _L3
_L3:
        if(k1 != i2 && i != j) goto _L6; else goto _L5
_L5:
        if(java/lang/Object == class1) goto _L8; else goto _L7
_L7:
        l = i1;
        if(!class1.isInstance(mSpans[k])) goto _L4; else goto _L8
_L8:
        i2 = mSpanFlags[k] & 0xff0000;
        l = i1;
        if(flag)
        {
            ai[l] = i2;
            ai1[l] = mSpanOrder[k];
        } else
        if(i2 != 0)
        {
label0:
            {
                for(l = 0; l < i1 && i2 <= (getSpanFlags(aobj[l]) & 0xff0000); l++)
                    break label0;

                System.arraycopy(((Object) (aobj)), l, ((Object) (aobj)), l + 1, i1 - l);
            }
        }
        aobj[l] = mSpans[k];
        l = i1 + 1;
_L4:
        i2 = l;
        if(l < aobj.length)
        {
            i2 = l;
            if((k & 1) != 0)
                i2 = getSpansRec(i, j, class1, rightChild(k), aobj, ai, ai1, l, flag);
        }
_L2:
        return i2;
_L6:
        l = i1;
        if(k1 == j) goto _L4; else goto _L9
_L9:
        l = i1;
        if(i2 == i) goto _L4; else goto _L5
    }

    private static boolean hasNonExclusiveExclusiveSpanAt(CharSequence charsequence, int i)
    {
        if(charsequence instanceof Spanned)
        {
            Spanned spanned = (Spanned)charsequence;
            charsequence = ((CharSequence) (spanned.getSpans(i, i, java/lang/Object)));
            int j = charsequence.length;
            for(i = 0; i < j; i++)
                if(spanned.getSpanFlags(charsequence[i]) != 33)
                    return true;

        }
        return false;
    }

    private void invalidateIndex(int i)
    {
        mLowWaterMark = Math.min(i, mLowWaterMark);
    }

    private boolean isInvalidParagraph(int i, int j)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(j == 3)
        {
            flag1 = flag;
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
        }
        return flag1;
    }

    private static int leftChild(int i)
    {
        return i - ((i + 1 & i) >> 1);
    }

    private void moveGapTo(int i)
    {
        boolean flag;
        int k;
        int l;
        int i1;
        int j1;
        if(i == mGapStart)
            return;
        if(i == length())
            flag = true;
        else
            flag = false;
        if(i < mGapStart)
        {
            int j = mGapStart - i;
            System.arraycopy(mText, i, mText, (mGapStart + mGapLength) - j, j);
        } else
        {
            k = i - mGapStart;
            System.arraycopy(mText, (mGapLength + i) - k, mText, mGapStart, k);
        }
        if(mSpanCount == 0) goto _L2; else goto _L1
_L1:
        l = 0;
_L18:
        if(l >= mSpanCount)
            break MISSING_BLOCK_LABEL_341;
        k = mSpanStarts[l];
        i1 = mSpanEnds[l];
        j1 = k;
        if(k > mGapStart)
            j1 = k - mGapLength;
        if(j1 <= i) goto _L4; else goto _L3
_L3:
        k = j1 + mGapLength;
_L8:
        j1 = i1;
        if(i1 > mGapStart)
            j1 = i1 - mGapLength;
        if(j1 <= i) goto _L6; else goto _L5
_L5:
        i1 = j1 + mGapLength;
_L13:
        mSpanStarts[l] = k;
        mSpanEnds[l] = i1;
        l++;
        continue; /* Loop/switch isn't completed */
_L4:
        k = j1;
        if(j1 != i) goto _L8; else goto _L7
_L7:
        int k1 = (mSpanFlags[l] & 0xf0) >> 4;
        if(k1 == 2) goto _L10; else goto _L9
_L9:
        k = j1;
        if(!flag) goto _L8; else goto _L11
_L11:
        k = j1;
        if(k1 != 3) goto _L8; else goto _L10
_L10:
        k = j1 + mGapLength;
          goto _L8
_L6:
        i1 = j1;
        if(j1 != i) goto _L13; else goto _L12
_L12:
        k1 = mSpanFlags[l] & 0xf;
        if(k1 == 2) goto _L15; else goto _L14
_L14:
        i1 = j1;
        if(!flag) goto _L13; else goto _L16
_L16:
        i1 = j1;
        if(k1 != 3) goto _L13; else goto _L15
_L15:
        i1 = j1 + mGapLength;
          goto _L13
        calcMax(treeRoot());
_L2:
        mGapStart = i;
        return;
        if(true) goto _L18; else goto _L17
_L17:
    }

    private int nextSpanTransitionRec(int i, int j, Class class1, int k)
    {
        int l = j;
        if((k & 1) != 0)
        {
            int i1 = leftChild(k);
            l = j;
            if(resolveGap(mSpanMax[i1]) > i)
                l = nextSpanTransitionRec(i, j, class1, i1);
        }
        j = l;
        if(k < mSpanCount)
        {
            int j1 = resolveGap(mSpanStarts[k]);
            int k1 = resolveGap(mSpanEnds[k]);
            j = l;
            if(j1 > i)
            {
                j = l;
                if(j1 < l)
                {
                    j = l;
                    if(class1.isInstance(mSpans[k]))
                        j = j1;
                }
            }
            l = j;
            if(k1 > i)
            {
                l = j;
                if(k1 < j)
                {
                    l = j;
                    if(class1.isInstance(mSpans[k]))
                        l = k1;
                }
            }
            j = l;
            if(j1 < l)
            {
                j = l;
                if((k & 1) != 0)
                    j = nextSpanTransitionRec(i, l, class1, rightChild(k));
            }
        }
        return j;
    }

    private static int[] obtain(int i)
    {
        int ai[] = null;
        int ai1[][] = sCachedIntBuffer;
        ai1;
        JVM INSTR monitorenter ;
        int j = -1;
        int k = sCachedIntBuffer.length - 1;
_L2:
        int l;
        l = j;
        if(k < 0)
            break MISSING_BLOCK_LABEL_53;
        l = j;
        if(sCachedIntBuffer[k] == null)
            break MISSING_BLOCK_LABEL_93;
        if(sCachedIntBuffer[k].length < i)
            break MISSING_BLOCK_LABEL_81;
        l = k;
        if(l == -1)
            break MISSING_BLOCK_LABEL_73;
        ai = sCachedIntBuffer[l];
        sCachedIntBuffer[l] = null;
        ai1;
        JVM INSTR monitorexit ;
        return checkSortBuffer(ai, i);
        l = j;
        if(j == -1)
            l = k;
        k--;
        j = l;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    private static void recycle(int ai[])
    {
        int ai1[][] = sCachedIntBuffer;
        ai1;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if(i < sCachedIntBuffer.length)
        {
            if(sCachedIntBuffer[i] != null && ai.length <= sCachedIntBuffer[i].length)
                break MISSING_BLOCK_LABEL_44;
            sCachedIntBuffer[i] = ai;
        }
        ai1;
        JVM INSTR monitorexit ;
        return;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        ai;
        throw ai;
    }

    private static String region(int i, int j)
    {
        return (new StringBuilder()).append("(").append(i).append(" ... ").append(j).append(")").toString();
    }

    private void removeSpan(int i)
    {
        Object obj = mSpans[i];
        int j = mSpanStarts[i];
        int k = mSpanEnds[i];
        int l = j;
        if(j > mGapStart)
            l = j - mGapLength;
        j = k;
        if(k > mGapStart)
            j = k - mGapLength;
        k = mSpanCount - (i + 1);
        System.arraycopy(((Object) (mSpans)), i + 1, ((Object) (mSpans)), i, k);
        System.arraycopy(mSpanStarts, i + 1, mSpanStarts, i, k);
        System.arraycopy(mSpanEnds, i + 1, mSpanEnds, i, k);
        System.arraycopy(mSpanFlags, i + 1, mSpanFlags, i, k);
        System.arraycopy(mSpanOrder, i + 1, mSpanOrder, i, k);
        mSpanCount = mSpanCount - 1;
        invalidateIndex(i);
        mSpans[mSpanCount] = null;
        restoreInvariants();
        sendSpanRemoved(obj, l, j);
    }

    private boolean removeSpansForChange(int i, int j, boolean flag, int k)
    {
        boolean flag1 = false;
        if((k & 1) != 0 && resolveGap(mSpanMax[k]) >= i && removeSpansForChange(i, j, flag, leftChild(k)))
            return true;
        if(k < mSpanCount)
        {
            while((mSpanFlags[k] & 0x21) == 33 && mSpanStarts[k] >= i && mSpanStarts[k] < mGapStart + mGapLength && mSpanEnds[k] >= i && mSpanEnds[k] < mGapStart + mGapLength && (flag || mSpanStarts[k] > i || mSpanEnds[k] < mGapStart)) 
            {
                mIndexOfSpan.remove(mSpans[k]);
                removeSpan(k);
                return true;
            }
            boolean flag2 = flag1;
            if(resolveGap(mSpanStarts[k]) <= j)
            {
                flag2 = flag1;
                if((k & 1) != 0)
                    flag2 = removeSpansForChange(i, j, flag, rightChild(k));
            }
            return flag2;
        } else
        {
            return false;
        }
    }

    private void resizeFor(int i)
    {
        int j = mText.length;
        if(i + 1 <= j)
            return;
        char ac[] = ArrayUtils.newUnpaddedCharArray(GrowingArrayUtils.growSize(i));
        System.arraycopy(mText, 0, ac, 0, mGapStart);
        int k = ac.length;
        int l = k - j;
        i = j - (mGapStart + mGapLength);
        System.arraycopy(mText, j - i, ac, k - i, i);
        mText = ac;
        mGapLength = mGapLength + l;
        if(mGapLength < 1)
            (new Exception("mGapLength < 1")).printStackTrace();
        if(mSpanCount != 0)
        {
            for(i = 0; i < mSpanCount; i++)
            {
                if(mSpanStarts[i] > mGapStart)
                {
                    int ai[] = mSpanStarts;
                    ai[i] = ai[i] + l;
                }
                if(mSpanEnds[i] > mGapStart)
                {
                    int ai1[] = mSpanEnds;
                    ai1[i] = ai1[i] + l;
                }
            }

            calcMax(treeRoot());
        }
    }

    private int resolveGap(int i)
    {
        int j = i;
        if(i > mGapStart)
            j = i - mGapLength;
        return j;
    }

    private void restoreInvariants()
    {
        if(mSpanCount == 0)
            return;
        for(int i = 1; i < mSpanCount; i++)
        {
            if(mSpanStarts[i] >= mSpanStarts[i - 1])
                continue;
            Object obj = mSpans[i];
            int k = mSpanStarts[i];
            int l = mSpanEnds[i];
            int i1 = mSpanFlags[i];
            int j1 = mSpanOrder[i];
            int k1 = i;
            int l1;
            do
            {
                mSpans[k1] = mSpans[k1 - 1];
                mSpanStarts[k1] = mSpanStarts[k1 - 1];
                mSpanEnds[k1] = mSpanEnds[k1 - 1];
                mSpanFlags[k1] = mSpanFlags[k1 - 1];
                mSpanOrder[k1] = mSpanOrder[k1 - 1];
                l1 = k1 - 1;
                if(l1 <= 0)
                    break;
                k1 = l1;
            } while(k < mSpanStarts[l1 - 1]);
            mSpans[l1] = obj;
            mSpanStarts[l1] = k;
            mSpanEnds[l1] = l;
            mSpanFlags[l1] = i1;
            mSpanOrder[l1] = j1;
            invalidateIndex(l1);
        }

        calcMax(treeRoot());
        if(mIndexOfSpan == null)
            mIndexOfSpan = new IdentityHashMap();
        for(int j = mLowWaterMark; j < mSpanCount; j++)
        {
            Integer integer = (Integer)mIndexOfSpan.get(mSpans[j]);
            if(integer == null || integer.intValue() != j)
                mIndexOfSpan.put(mSpans[j], Integer.valueOf(j));
        }

        mLowWaterMark = 0x7fffffff;
    }

    private static int rightChild(int i)
    {
        return ((i + 1 & i) >> 1) + i;
    }

    private void sendAfterTextChanged(TextWatcher atextwatcher[])
    {
        int i = atextwatcher.length;
        mTextWatcherDepth = mTextWatcherDepth + 1;
        for(int j = 0; j < i; j++)
            atextwatcher[j].afterTextChanged(this);

        mTextWatcherDepth = mTextWatcherDepth - 1;
    }

    private void sendBeforeTextChanged(TextWatcher atextwatcher[], int i, int j, int k)
    {
        int l = atextwatcher.length;
        mTextWatcherDepth = mTextWatcherDepth + 1;
        for(int i1 = 0; i1 < l; i1++)
            atextwatcher[i1].beforeTextChanged(this, i, j, k);

        mTextWatcherDepth = mTextWatcherDepth - 1;
    }

    private void sendSpanAdded(Object obj, int i, int j)
    {
        SpanWatcher aspanwatcher[] = (SpanWatcher[])getSpans(i, j, android/text/SpanWatcher);
        int k = aspanwatcher.length;
        for(int l = 0; l < k; l++)
            aspanwatcher[l].onSpanAdded(this, obj, i, j);

    }

    private void sendSpanChanged(Object obj, int i, int j, int k, int l)
    {
        SpanWatcher aspanwatcher[] = (SpanWatcher[])getSpans(Math.min(i, k), Math.min(Math.max(j, l), length()), android/text/SpanWatcher);
        int i1 = aspanwatcher.length;
        for(int j1 = 0; j1 < i1; j1++)
            aspanwatcher[j1].onSpanChanged(this, obj, i, j, k, l);

    }

    private void sendSpanRemoved(Object obj, int i, int j)
    {
        SpanWatcher aspanwatcher[] = (SpanWatcher[])getSpans(i, j, android/text/SpanWatcher);
        int k = aspanwatcher.length;
        for(int l = 0; l < k; l++)
            aspanwatcher[l].onSpanRemoved(this, obj, i, j);

    }

    private void sendTextChanged(TextWatcher atextwatcher[], int i, int j, int k)
    {
        int l = atextwatcher.length;
        mTextWatcherDepth = mTextWatcherDepth + 1;
        for(int i1 = 0; i1 < l; i1++)
            atextwatcher[i1].onTextChanged(this, i, j, k);

        mTextWatcherDepth = mTextWatcherDepth - 1;
    }

    private void sendToSpanWatchers(int i, int j, int k)
    {
        int l = 0;
_L2:
        int i1;
        if(l >= mSpanCount)
            break MISSING_BLOCK_LABEL_369;
        i1 = mSpanFlags[l];
        if((i1 & 0x800) == 0)
            break; /* Loop/switch isn't completed */
_L7:
        l++;
        if(true) goto _L2; else goto _L1
_L1:
        int j1;
        int j2;
        int k2;
        int l2;
        int i3;
        j1 = mSpanStarts[l];
        int k1 = mSpanEnds[l];
        j2 = j1;
        if(j1 > mGapStart)
            j2 = j1 - mGapLength;
        j1 = k1;
        if(k1 > mGapStart)
            j1 = k1 - mGapLength;
        k2 = j + k;
        l2 = 0;
        i3 = j2;
        if(j2 <= k2) goto _L4; else goto _L3
_L3:
        int l1;
        int j3;
        j3 = i3;
        l1 = l2;
        if(k != 0)
        {
            j3 = i3 - k;
            l1 = 1;
        }
_L9:
        l2 = j1;
        if(j1 <= k2) goto _L6; else goto _L5
_L5:
        int k3;
        k3 = l2;
        i3 = l1;
        if(k != 0)
        {
            k3 = l2 - k;
            i3 = 1;
        }
_L15:
        if(i3 != 0)
            sendSpanChanged(mSpans[l], j3, k3, j2, j1);
        int ai[] = mSpanFlags;
        ai[l] = ai[l] & 0xffff0fff;
          goto _L7
_L4:
        j3 = i3;
        l1 = l2;
        if(j2 < i) goto _L9; else goto _L8
_L8:
        if(j2 != i) goto _L11; else goto _L10
_L10:
        j3 = i3;
        l1 = l2;
        if((i1 & 0x1000) == 4096) goto _L9; else goto _L11
_L11:
        if(j2 != k2) goto _L13; else goto _L12
_L12:
        j3 = i3;
        l1 = l2;
        if((i1 & 0x2000) == 8192) goto _L9; else goto _L13
_L13:
        l1 = 1;
        j3 = i3;
          goto _L9
_L6:
        k3 = l2;
        i3 = l1;
        if(j1 < i) goto _L15; else goto _L14
_L14:
        if(j1 != i) goto _L17; else goto _L16
_L16:
        k3 = l2;
        i3 = l1;
        if((i1 & 0x4000) == 16384) goto _L15; else goto _L17
_L17:
        if(j1 != k2) goto _L19; else goto _L18
_L18:
        k3 = l2;
        i3 = l1;
        if((i1 & 0x8000) == 32768) goto _L15; else goto _L19
_L19:
        i3 = 1;
        k3 = l2;
          goto _L15
        for(i = 0; i < mSpanCount; i++)
        {
            if((mSpanFlags[i] & 0x800) == 0)
                continue;
            int ai1[] = mSpanFlags;
            ai1[i] = ai1[i] & 0xfffff7ff;
            k = mSpanStarts[i];
            int i2 = mSpanEnds[i];
            j = k;
            if(k > mGapStart)
                j = k - mGapLength;
            k = i2;
            if(i2 > mGapStart)
                k = i2 - mGapLength;
            sendSpanAdded(mSpans[i], j, k);
        }

        return;
          goto _L7
    }

    private void setSpan(boolean flag, Object obj, int i, int j, int k, boolean flag1)
    {
        int l;
        int i1;
        checkRange("setSpan", i, j);
        l = (k & 0xf0) >> 4;
        if(isInvalidParagraph(i, l))
            if(!flag1)
                return;
            else
                throw new RuntimeException((new StringBuilder()).append("PARAGRAPH span must start at paragraph boundary (").append(i).append(" follows ").append(charAt(i - 1)).append(")").toString());
        i1 = k & 0xf;
        if(isInvalidParagraph(j, i1))
            if(!flag1)
                return;
            else
                throw new RuntimeException((new StringBuilder()).append("PARAGRAPH span must end at paragraph boundary (").append(j).append(" follows ").append(charAt(j - 1)).append(")").toString());
        if(l == 2 && i1 == 1 && i == j)
        {
            if(flag)
                Log.e("SpannableStringBuilder", "SPAN_EXCLUSIVE_EXCLUSIVE spans cannot have a zero length");
            return;
        }
        if(i <= mGapStart) goto _L2; else goto _L1
_L1:
        int j1 = i + mGapLength;
_L8:
        l = j1;
        if(j <= mGapStart) goto _L4; else goto _L3
_L3:
        j1 = j + mGapLength;
_L6:
        int k1;
        k1 = j1;
        if(mIndexOfSpan != null)
        {
            Integer integer = (Integer)mIndexOfSpan.get(obj);
            if(integer != null)
            {
                int l1 = integer.intValue();
                i1 = mSpanStarts[l1];
                int i2 = mSpanEnds[l1];
                j1 = i1;
                if(i1 > mGapStart)
                    j1 = i1 - mGapLength;
                i1 = i2;
                if(i2 > mGapStart)
                    i1 = i2 - mGapLength;
                mSpanStarts[l1] = l;
                mSpanEnds[l1] = k1;
                mSpanFlags[l1] = k;
                if(flag)
                {
                    restoreInvariants();
                    sendSpanChanged(obj, j1, i1, i, j);
                }
                return;
            }
        }
        break; /* Loop/switch isn't completed */
_L2:
        j1 = i;
        if(i != mGapStart)
            continue; /* Loop/switch isn't completed */
        if(l != 2)
        {
            j1 = i;
            if(l != 3)
                continue; /* Loop/switch isn't completed */
            j1 = i;
            if(i != length())
                continue; /* Loop/switch isn't completed */
        }
        j1 = i + mGapLength;
        continue; /* Loop/switch isn't completed */
_L4:
        j1 = j;
        if(j != mGapStart)
            continue; /* Loop/switch isn't completed */
        if(i1 != 2)
        {
            j1 = j;
            if(i1 != 3)
                continue; /* Loop/switch isn't completed */
            j1 = j;
            if(j != length())
                continue; /* Loop/switch isn't completed */
        }
        j1 = j + mGapLength;
        if(true) goto _L6; else goto _L5
_L5:
        mSpans = GrowingArrayUtils.append(mSpans, mSpanCount, obj);
        mSpanStarts = GrowingArrayUtils.append(mSpanStarts, mSpanCount, l);
        mSpanEnds = GrowingArrayUtils.append(mSpanEnds, mSpanCount, k1);
        mSpanFlags = GrowingArrayUtils.append(mSpanFlags, mSpanCount, k);
        mSpanOrder = GrowingArrayUtils.append(mSpanOrder, mSpanCount, mSpanInsertCount);
        invalidateIndex(mSpanCount);
        mSpanCount = mSpanCount + 1;
        mSpanInsertCount = mSpanInsertCount + 1;
        k = treeRoot() * 2 + 1;
        if(mSpanMax.length < k)
            mSpanMax = new int[k];
        if(flag)
        {
            restoreInvariants();
            sendSpanAdded(obj, i, j);
        }
        return;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private final void siftDown(int i, Object aobj[], int j, int ai[], int ai1[])
    {
        int k = i * 2 + 1;
        int l = i;
        do
        {
label0:
            {
                if(k < j)
                {
                    i = k;
                    if(k < j - 1)
                    {
                        i = k;
                        if(compareSpans(k, k + 1, ai, ai1) < 0)
                            i = k + 1;
                    }
                    if(compareSpans(l, i, ai, ai1) < 0)
                        break label0;
                }
                return;
            }
            Object obj = aobj[l];
            aobj[l] = aobj[i];
            aobj[i] = obj;
            k = ai[l];
            ai[l] = ai[i];
            ai[i] = k;
            k = ai1[l];
            ai1[l] = ai1[i];
            ai1[i] = k;
            l = i;
            k = i * 2 + 1;
        } while(true);
    }

    private final void sort(Object aobj[], int ai[], int ai1[])
    {
        int i = aobj.length;
        for(int k = i / 2 - 1; k >= 0; k--)
            siftDown(k, aobj, i, ai, ai1);

        for(int l = i - 1; l > 0; l--)
        {
            Object obj = aobj[0];
            aobj[0] = aobj[l];
            aobj[l] = obj;
            int j = ai[0];
            ai[0] = ai[l];
            ai[l] = j;
            j = ai1[0];
            ai1[0] = ai1[l];
            ai1[l] = j;
            siftDown(0, aobj, l, ai, ai1);
        }

    }

    private int treeRoot()
    {
        return Integer.highestOneBit(mSpanCount) - 1;
    }

    private int updatedIntervalBound(int i, int j, int k, int l, boolean flag, boolean flag1)
    {
        if(i >= j && i < mGapStart + mGapLength)
            if(l == 2)
            {
                if(flag1 || i > j)
                    return mGapStart + mGapLength;
            } else
            if(l == 3)
            {
                if(flag)
                    return mGapStart + mGapLength;
            } else
            if(flag1 || i < mGapStart - k)
                return j;
            else
                return mGapStart;
        return i;
    }

    public static SpannableStringBuilder valueOf(CharSequence charsequence)
    {
        if(charsequence instanceof SpannableStringBuilder)
            return (SpannableStringBuilder)charsequence;
        else
            return new SpannableStringBuilder(charsequence);
    }

    public volatile Editable append(char c)
    {
        return append(c);
    }

    public volatile Editable append(CharSequence charsequence)
    {
        return append(charsequence);
    }

    public volatile Editable append(CharSequence charsequence, int i, int j)
    {
        return append(charsequence, i, j);
    }

    public SpannableStringBuilder append(char c)
    {
        return append(((CharSequence) (String.valueOf(c))));
    }

    public SpannableStringBuilder append(CharSequence charsequence)
    {
        int i = length();
        return replace(i, i, charsequence, 0, charsequence.length());
    }

    public SpannableStringBuilder append(CharSequence charsequence, int i, int j)
    {
        int k = length();
        return replace(k, k, charsequence, i, j);
    }

    public SpannableStringBuilder append(CharSequence charsequence, Object obj, int i)
    {
        int j = length();
        append(charsequence);
        setSpan(obj, j, length(), i);
        return this;
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Appendable append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public char charAt(int i)
    {
        int j = length();
        if(i < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("charAt: ").append(i).append(" < 0").toString());
        if(i >= j)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("charAt: ").append(i).append(" >= length ").append(j).toString());
        if(i >= mGapStart)
            return mText[mGapLength + i];
        else
            return mText[i];
    }

    public void clear()
    {
        replace(0, length(), "", 0, 0);
        mSpanInsertCount = 0;
    }

    public void clearSpans()
    {
        for(int i = mSpanCount - 1; i >= 0; i--)
        {
            Object obj = mSpans[i];
            int j = mSpanStarts[i];
            int k = mSpanEnds[i];
            int l = j;
            if(j > mGapStart)
                l = j - mGapLength;
            j = k;
            if(k > mGapStart)
                j = k - mGapLength;
            mSpanCount = i;
            mSpans[i] = null;
            sendSpanRemoved(obj, l, j);
        }

        if(mIndexOfSpan != null)
            mIndexOfSpan.clear();
        mSpanInsertCount = 0;
    }

    public volatile Editable delete(int i, int j)
    {
        return delete(i, j);
    }

    public SpannableStringBuilder delete(int i, int j)
    {
        SpannableStringBuilder spannablestringbuilder = replace(i, j, "", 0, 0);
        if(mGapLength > length() * 2)
            resizeFor(length());
        return spannablestringbuilder;
    }

    public void drawText(BaseCanvas basecanvas, int i, int j, float f, float f1, Paint paint)
    {
        checkRange("drawText", i, j);
        if(j <= mGapStart)
            basecanvas.drawText(mText, i, j - i, f, f1, paint);
        else
        if(i >= mGapStart)
        {
            basecanvas.drawText(mText, i + mGapLength, j - i, f, f1, paint);
        } else
        {
            char ac[] = TextUtils.obtain(j - i);
            getChars(i, j, ac, 0);
            basecanvas.drawText(ac, 0, j - i, f, f1, paint);
            TextUtils.recycle(ac);
        }
    }

    public void drawTextRun(BaseCanvas basecanvas, int i, int j, int k, int l, float f, float f1, 
            boolean flag, Paint paint)
    {
        checkRange("drawTextRun", i, j);
        int i1 = l - k;
        j -= i;
        if(l <= mGapStart)
            basecanvas.drawTextRun(mText, i, j, k, i1, f, f1, flag, paint);
        else
        if(k >= mGapStart)
        {
            basecanvas.drawTextRun(mText, i + mGapLength, j, k + mGapLength, i1, f, f1, flag, paint);
        } else
        {
            char ac[] = TextUtils.obtain(i1);
            getChars(k, l, ac, 0);
            basecanvas.drawTextRun(ac, i - k, j, 0, i1, f, f1, flag, paint);
            TextUtils.recycle(ac);
        }
    }

    public boolean equals(Object obj)
    {
        if((obj instanceof Spanned) && toString().equals(obj.toString()))
        {
            Spanned spanned = (Spanned)obj;
            obj = ((Object) (spanned.getSpans(0, spanned.length(), java/lang/Object)));
            if(mSpanCount == obj.length)
            {
                int i = 0;
                do
                {
                    if(i >= mSpanCount)
                        break;
                    Object obj1 = mSpans[i];
                    Spanned spanned1 = obj[i];
                    if(obj1 == this)
                        while(spanned != spanned1 || getSpanStart(obj1) != spanned.getSpanStart(spanned1) || getSpanEnd(obj1) != spanned.getSpanEnd(spanned1) || getSpanFlags(obj1) != spanned.getSpanFlags(spanned1)) 
                            return false;
                    else
                        while(!obj1.equals(spanned1) || getSpanStart(obj1) != spanned.getSpanStart(spanned1) || getSpanEnd(obj1) != spanned.getSpanEnd(spanned1) || getSpanFlags(obj1) != spanned.getSpanFlags(spanned1)) 
                            return false;
                    i++;
                } while(true);
                return true;
            }
        }
        return false;
    }

    public void getChars(int i, int j, char ac[], int k)
    {
        checkRange("getChars", i, j);
        if(j <= mGapStart)
            System.arraycopy(mText, i, ac, k, j - i);
        else
        if(i >= mGapStart)
        {
            System.arraycopy(mText, mGapLength + i, ac, k, j - i);
        } else
        {
            System.arraycopy(mText, i, ac, k, mGapStart - i);
            System.arraycopy(mText, mGapStart + mGapLength, ac, (mGapStart - i) + k, j - mGapStart);
        }
    }

    public InputFilter[] getFilters()
    {
        return mFilters;
    }

    public int getSpanEnd(Object obj)
    {
        int i = -1;
        if(mIndexOfSpan == null)
            return -1;
        obj = (Integer)mIndexOfSpan.get(obj);
        if(obj != null)
            i = resolveGap(mSpanEnds[((Integer) (obj)).intValue()]);
        return i;
    }

    public int getSpanFlags(Object obj)
    {
        int i = 0;
        if(mIndexOfSpan == null)
            return 0;
        obj = (Integer)mIndexOfSpan.get(obj);
        if(obj != null)
            i = mSpanFlags[((Integer) (obj)).intValue()];
        return i;
    }

    public int getSpanStart(Object obj)
    {
        int i = -1;
        if(mIndexOfSpan == null)
            return -1;
        obj = (Integer)mIndexOfSpan.get(obj);
        if(obj != null)
            i = resolveGap(mSpanStarts[((Integer) (obj)).intValue()]);
        return i;
    }

    public Object[] getSpans(int i, int j, Class class1)
    {
        return getSpans(i, j, class1, true);
    }

    public Object[] getSpans(int i, int j, Class class1, boolean flag)
    {
        if(class1 == null)
            return ArrayUtils.emptyArray(java/lang/Object);
        if(mSpanCount == 0)
            return ArrayUtils.emptyArray(class1);
        int k = countSpans(i, j, class1, treeRoot());
        if(k == 0)
            return ArrayUtils.emptyArray(class1);
        Object aobj[] = (Object[])Array.newInstance(class1, k);
        int ai[];
        int ai1[];
        if(flag)
            ai = obtain(k);
        else
            ai = EmptyArray.INT;
        if(flag)
            ai1 = obtain(k);
        else
            ai1 = EmptyArray.INT;
        getSpansRec(i, j, class1, treeRoot(), aobj, ai, ai1, 0, flag);
        if(flag)
        {
            sort(aobj, ai, ai1);
            recycle(ai);
            recycle(ai1);
        }
        return aobj;
    }

    public float getTextRunAdvances(int i, int j, int k, int l, boolean flag, float af[], int i1, 
            Paint paint)
    {
        int j1 = l - k;
        int k1 = j - i;
        float f;
        if(j <= mGapStart)
            f = paint.getTextRunAdvances(mText, i, k1, k, j1, flag, af, i1);
        else
        if(i >= mGapStart)
        {
            f = paint.getTextRunAdvances(mText, i + mGapLength, k1, k + mGapLength, j1, flag, af, i1);
        } else
        {
            char ac[] = TextUtils.obtain(j1);
            getChars(k, l, ac, 0);
            f = paint.getTextRunAdvances(ac, i - k, k1, 0, j1, flag, af, i1);
            TextUtils.recycle(ac);
        }
        return f;
    }

    public int getTextRunCursor(int i, int j, int k, int l, int i1, Paint paint)
    {
        int j1 = j - i;
        if(j <= mGapStart)
            i = paint.getTextRunCursor(mText, i, j1, k, l, i1);
        else
        if(i >= mGapStart)
        {
            i = paint.getTextRunCursor(mText, i + mGapLength, j1, k, l + mGapLength, i1) - mGapLength;
        } else
        {
            char ac[] = TextUtils.obtain(j1);
            getChars(i, j, ac, 0);
            i = paint.getTextRunCursor(ac, 0, j1, k, l - i, i1) + i;
            TextUtils.recycle(ac);
        }
        return i;
    }

    public int getTextWatcherDepth()
    {
        return mTextWatcherDepth;
    }

    public int getTextWidths(int i, int j, float af[], Paint paint)
    {
        checkRange("getTextWidths", i, j);
        if(j <= mGapStart)
            i = paint.getTextWidths(mText, i, j - i, af);
        else
        if(i >= mGapStart)
        {
            i = paint.getTextWidths(mText, mGapLength + i, j - i, af);
        } else
        {
            char ac[] = TextUtils.obtain(j - i);
            getChars(i, j, ac, 0);
            i = paint.getTextWidths(ac, 0, j - i, af);
            TextUtils.recycle(ac);
        }
        return i;
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

    public volatile Editable insert(int i, CharSequence charsequence)
    {
        return insert(i, charsequence);
    }

    public volatile Editable insert(int i, CharSequence charsequence, int j, int k)
    {
        return insert(i, charsequence, j, k);
    }

    public SpannableStringBuilder insert(int i, CharSequence charsequence)
    {
        return replace(i, i, charsequence, 0, charsequence.length());
    }

    public SpannableStringBuilder insert(int i, CharSequence charsequence, int j, int k)
    {
        return replace(i, i, charsequence, j, k);
    }

    public int length()
    {
        return mText.length - mGapLength;
    }

    public float measureText(int i, int j, Paint paint)
    {
        checkRange("measureText", i, j);
        float f;
        if(j <= mGapStart)
            f = paint.measureText(mText, i, j - i);
        else
        if(i >= mGapStart)
        {
            f = paint.measureText(mText, mGapLength + i, j - i);
        } else
        {
            char ac[] = TextUtils.obtain(j - i);
            getChars(i, j, ac, 0);
            f = paint.measureText(ac, 0, j - i);
            TextUtils.recycle(ac);
        }
        return f;
    }

    public int nextSpanTransition(int i, int j, Class class1)
    {
        if(mSpanCount == 0)
            return j;
        Object obj = class1;
        if(class1 == null)
            obj = java/lang/Object;
        return nextSpanTransitionRec(i, j, ((Class) (obj)), treeRoot());
    }

    public void removeSpan(Object obj)
    {
        if(mIndexOfSpan == null)
            return;
        obj = (Integer)mIndexOfSpan.remove(obj);
        if(obj != null)
            removeSpan(((Integer) (obj)).intValue());
    }

    public volatile Editable replace(int i, int j, CharSequence charsequence)
    {
        return replace(i, j, charsequence);
    }

    public volatile Editable replace(int i, int j, CharSequence charsequence, int k, int l)
    {
        return replace(i, j, charsequence, k, l);
    }

    public SpannableStringBuilder replace(int i, int j, CharSequence charsequence)
    {
        return replace(i, j, charsequence, 0, charsequence.length());
    }

    public SpannableStringBuilder replace(int i, int j, CharSequence charsequence, int k, int l)
    {
        checkRange("replace", i, j);
        int i1 = mFilters.length;
        for(int j1 = 0; j1 < i1; j1++)
        {
            CharSequence charsequence1 = mFilters[j1].filter(charsequence, k, l, this, i, j);
            if(charsequence1 != null)
            {
                charsequence = charsequence1;
                k = 0;
                l = charsequence1.length();
            }
        }

        int l1 = j - i;
        int i2 = l - k;
        if(l1 == 0 && i2 == 0 && hasNonExclusiveExclusiveSpanAt(charsequence, k) ^ true)
            return this;
        TextWatcher atextwatcher[] = (TextWatcher[])getSpans(i, i + l1, android/text/TextWatcher);
        sendBeforeTextChanged(atextwatcher, i, l1, i2);
        boolean flag;
        int k1;
        int j2;
        if(l1 != 0 && i2 != 0)
            flag = true;
        else
            flag = false;
        j2 = 0;
        k1 = 0;
        if(flag)
        {
            j2 = Selection.getSelectionStart(this);
            k1 = Selection.getSelectionEnd(this);
        }
        change(i, j, charsequence, k, l);
        if(flag)
        {
            l = 0;
            k = l;
            if(j2 > i)
            {
                k = l;
                if(j2 < j)
                {
                    long l2 = j2 - i;
                    l = i + Math.toIntExact(((long)i2 * l2) / (long)l1);
                    k = 1;
                    setSpan(false, Selection.SELECTION_START, l, l, 34, true);
                }
            }
            l = k;
            if(k1 > i)
            {
                l = k;
                if(k1 < j)
                {
                    long l3 = k1 - i;
                    k = i + Math.toIntExact(((long)i2 * l3) / (long)l1);
                    l = 1;
                    setSpan(false, Selection.SELECTION_END, k, k, 34, true);
                }
            }
            if(l != 0)
                restoreInvariants();
        }
        sendTextChanged(atextwatcher, i, l1, i2);
        sendAfterTextChanged(atextwatcher);
        sendToSpanWatchers(i, j, i2 - l1);
        return this;
    }

    public void setFilters(InputFilter ainputfilter[])
    {
        if(ainputfilter == null)
        {
            throw new IllegalArgumentException();
        } else
        {
            mFilters = ainputfilter;
            return;
        }
    }

    public void setSpan(Object obj, int i, int j, int k)
    {
        setSpan(true, obj, i, j, k, true);
    }

    public CharSequence subSequence(int i, int j)
    {
        return new SpannableStringBuilder(this, i, j);
    }

    public String substring(int i, int j)
    {
        char ac[] = new char[j - i];
        getChars(i, j, ac, 0);
        return new String(ac);
    }

    public String toString()
    {
        int i = length();
        char ac[] = new char[i];
        getChars(0, i, ac, 0);
        return new String(ac);
    }

    private static final int END_MASK = 15;
    private static final int MARK = 1;
    private static final InputFilter NO_FILTERS[] = new InputFilter[0];
    private static final int PARAGRAPH = 3;
    private static final int POINT = 2;
    private static final int SPAN_ADDED = 2048;
    private static final int SPAN_END_AT_END = 32768;
    private static final int SPAN_END_AT_START = 16384;
    private static final int SPAN_START_AT_END = 8192;
    private static final int SPAN_START_AT_START = 4096;
    private static final int SPAN_START_END_MASK = 61440;
    private static final int START_MASK = 240;
    private static final int START_SHIFT = 4;
    private static final String TAG = "SpannableStringBuilder";
    private static final int sCachedIntBuffer[][] = new int[6][0];
    private InputFilter mFilters[];
    private int mGapLength;
    private int mGapStart;
    private IdentityHashMap mIndexOfSpan;
    private int mLowWaterMark;
    private int mSpanCount;
    private int mSpanEnds[];
    private int mSpanFlags[];
    private int mSpanInsertCount;
    private int mSpanMax[];
    private int mSpanOrder[];
    private int mSpanStarts[];
    private Object mSpans[];
    private char mText[];
    private int mTextWatcherDepth;

}
