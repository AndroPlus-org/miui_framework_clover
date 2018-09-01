// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.style.ReplacementSpan;
import android.text.style.UpdateLayout;
import android.text.style.WrapTogetherSpan;
import android.util.ArraySet;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.lang.ref.WeakReference;

// Referenced classes of package android.text:
//            Layout, TextDirectionHeuristics, PackedIntVector, PackedObjectVector, 
//            TextPaint, Spannable, Spanned, TextUtils, 
//            StaticLayout, TextDirectionHeuristic, TextWatcher, SpanWatcher, 
//            Editable

public class DynamicLayout extends Layout
{
    private static class ChangeWatcher
        implements TextWatcher, SpanWatcher
    {

        private void reflow(CharSequence charsequence, int i, int j, int k)
        {
            DynamicLayout dynamiclayout = (DynamicLayout)mLayout.get();
            if(dynamiclayout == null) goto _L2; else goto _L1
_L1:
            DynamicLayout._2D_wrap0(dynamiclayout, charsequence, i, j, k);
_L4:
            return;
_L2:
            if(charsequence instanceof Spannable)
                ((Spannable)charsequence).removeSpan(this);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void afterTextChanged(Editable editable)
        {
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onSpanAdded(Spannable spannable, Object obj, int i, int j)
        {
            if(obj instanceof UpdateLayout)
                reflow(spannable, i, j - i, j - i);
        }

        public void onSpanChanged(Spannable spannable, Object obj, int i, int j, int k, int l)
        {
            if(obj instanceof UpdateLayout)
            {
                reflow(spannable, i, j - i, j - i);
                reflow(spannable, k, l - k, l - k);
            }
        }

        public void onSpanRemoved(Spannable spannable, Object obj, int i, int j)
        {
            if(obj instanceof UpdateLayout)
                reflow(spannable, i, j - i, j - i);
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
            reflow(charsequence, i, j, k);
        }

        private WeakReference mLayout;

        public ChangeWatcher(DynamicLayout dynamiclayout)
        {
            mLayout = new WeakReference(dynamiclayout);
        }
    }


    static void _2D_wrap0(DynamicLayout dynamiclayout, CharSequence charsequence, int i, int j, int k)
    {
        dynamiclayout.reflow(charsequence, i, j, k);
    }

    public DynamicLayout(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, boolean flag)
    {
        this(charsequence, charsequence, textpaint, i, alignment, f, f1, flag);
    }

    public DynamicLayout(CharSequence charsequence, CharSequence charsequence1, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, 
            boolean flag)
    {
        this(charsequence, charsequence1, textpaint, i, alignment, f, f1, flag, null, 0);
    }

    public DynamicLayout(CharSequence charsequence, CharSequence charsequence1, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, 
            boolean flag, TextUtils.TruncateAt truncateat, int j)
    {
        this(charsequence, charsequence1, textpaint, i, alignment, TextDirectionHeuristics.FIRSTSTRONG_LTR, f, f1, flag, 0, 0, 0, truncateat, j);
    }

    public DynamicLayout(CharSequence charsequence, CharSequence charsequence1, TextPaint textpaint, int i, Layout.Alignment alignment, TextDirectionHeuristic textdirectionheuristic, float f, 
            float f1, boolean flag, int j, int k, int l, TextUtils.TruncateAt truncateat, int i1)
    {
        Object obj;
        if(truncateat == null)
            obj = charsequence1;
        else
        if(charsequence1 instanceof Spanned)
            obj = new Layout.SpannedEllipsizer(charsequence1);
        else
            obj = new Layout.Ellipsizer(charsequence1);
        super(((CharSequence) (obj)), textpaint, i, alignment, textdirectionheuristic, f, f1);
        mTempRect = new Rect();
        mBase = charsequence;
        mDisplay = charsequence1;
        if(truncateat != null)
        {
            mInts = new PackedIntVector(6);
            mEllipsizedWidth = i1;
            mEllipsizeAt = truncateat;
        } else
        {
            mInts = new PackedIntVector(4);
            mEllipsizedWidth = i;
            mEllipsizeAt = null;
        }
        mObjects = new PackedObjectVector(1);
        mIncludePad = flag;
        mBreakStrategy = j;
        mJustificationMode = l;
        mHyphenationFrequency = k;
        if(truncateat != null)
        {
            charsequence1 = (Layout.Ellipsizer)getText();
            charsequence1.mLayout = this;
            charsequence1.mWidth = i1;
            charsequence1.mMethod = truncateat;
            mEllipsize = true;
        }
        if(truncateat != null)
        {
            charsequence1 = new int[6];
            charsequence1[4] = 0x80000000;
        } else
        {
            charsequence1 = new int[4];
        }
        alignment = DIRS_ALL_LEFT_TO_RIGHT;
        textpaint = textpaint.getFontMetricsInt();
        j = ((android.graphics.Paint.FontMetricsInt) (textpaint)).ascent;
        i = ((android.graphics.Paint.FontMetricsInt) (textpaint)).descent;
        charsequence1[0] = 0x40000000;
        charsequence1[1] = 0;
        charsequence1[2] = i;
        mInts.insertAt(0, charsequence1);
        charsequence1[1] = i - j;
        mInts.insertAt(1, charsequence1);
        mObjects.insertAt(0, new Layout.Directions[] {
            alignment
        });
        reflow(charsequence, 0, 0, charsequence.length());
        if(charsequence instanceof Spannable)
        {
            if(mWatcher == null)
                mWatcher = new ChangeWatcher(this);
            textpaint = (Spannable)charsequence;
            charsequence1 = (ChangeWatcher[])textpaint.getSpans(0, textpaint.length(), android/text/DynamicLayout$ChangeWatcher);
            for(i = 0; i < charsequence1.length; i++)
                textpaint.removeSpan(charsequence1[i]);

            textpaint.setSpan(mWatcher, 0, charsequence.length(), 0x800012);
        }
    }

    private void addBlockAtOffset(int i)
    {
        i = getLineForOffset(i);
        if(mBlockEndLines == null)
        {
            mBlockEndLines = ArrayUtils.newUnpaddedIntArray(1);
            mBlockEndLines[mNumberOfBlocks] = i;
            updateAlwaysNeedsToBeRedrawn(mNumberOfBlocks);
            mNumberOfBlocks = mNumberOfBlocks + 1;
            return;
        }
        if(i > mBlockEndLines[mNumberOfBlocks - 1])
        {
            mBlockEndLines = GrowingArrayUtils.append(mBlockEndLines, mNumberOfBlocks, i);
            updateAlwaysNeedsToBeRedrawn(mNumberOfBlocks);
            mNumberOfBlocks = mNumberOfBlocks + 1;
        }
    }

    private boolean contentMayProtrudeFromLineTopOrBottom(CharSequence charsequence, int i, int j)
    {
        if((charsequence instanceof Spanned) && ((ReplacementSpan[])((Spanned)charsequence).getSpans(i, j, android/text/style/ReplacementSpan)).length > 0)
            return true;
        TextPaint textpaint = getPaint();
        textpaint.getTextBounds(charsequence, i, j, mTempRect);
        charsequence = textpaint.getFontMetricsInt();
        boolean flag;
        if(mTempRect.top < ((android.graphics.Paint.FontMetricsInt) (charsequence)).top || mTempRect.bottom > ((android.graphics.Paint.FontMetricsInt) (charsequence)).bottom)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void createBlocks()
    {
        int i = 400;
        mNumberOfBlocks = 0;
        CharSequence charsequence = mDisplay;
        do
        {
            i = TextUtils.indexOf(charsequence, '\n', i);
            if(i < 0)
            {
                addBlockAtOffset(charsequence.length());
                mBlockIndices = new int[mBlockEndLines.length];
                for(i = 0; i < mBlockEndLines.length; i++)
                    mBlockIndices[i] = -1;

                break;
            }
            addBlockAtOffset(i);
            i += 400;
        } while(true);
    }

    private boolean getContentMayProtrudeFromTopOrBottom(int i)
    {
        boolean flag = false;
        if((mInts.getValue(i, 3) & 0x100) != 0)
            flag = true;
        return flag;
    }

    private void reflow(CharSequence charsequence, int i, int j, int k)
    {
        int i1;
        int j1;
        Object obj;
        int k3;
        if(charsequence != mBase)
            return;
        CharSequence charsequence1 = mDisplay;
        int l = charsequence1.length();
        i1 = TextUtils.lastIndexOf(charsequence1, '\n', i - 1);
        int k1;
        int l1;
        if(i1 < 0)
            i1 = 0;
        else
            i1++;
        j1 = i - i1;
        i1 = k + j1;
        i -= j1;
        k = TextUtils.indexOf(charsequence1, '\n', i + i1);
        if(k < 0)
            k = l;
        else
            k++;
        k -= i + i1;
        k1 = j + j1 + k;
        j1 = i1 + k;
        k = i;
        l1 = k1;
        j = j1;
        if(charsequence1 instanceof Spanned)
        {
            charsequence = (Spanned)charsequence1;
            int j2 = i;
            do
            {
                i1 = 0;
                Object aobj[] = charsequence.getSpans(j2, j2 + j1, android/text/style/WrapTogetherSpan);
                l1 = 0;
                j = j1;
                i = k1;
                k = j2;
                for(j2 = l1; j2 < aobj.length;)
                {
                    int l2 = charsequence.getSpanStart(aobj[j2]);
                    int i3 = charsequence.getSpanEnd(aobj[j2]);
                    k1 = i1;
                    l1 = k;
                    j1 = i;
                    i1 = j;
                    if(l2 < k)
                    {
                        k1 = 1;
                        l1 = k - l2;
                        j1 = i + l1;
                        i1 = j + l1;
                        l1 = k - l1;
                    }
                    i = j1;
                    j = i1;
                    if(i3 > l1 + i1)
                    {
                        k1 = 1;
                        j = i3 - (l1 + i1);
                        i = j1 + j;
                        j = i1 + j;
                    }
                    j2++;
                    i1 = k1;
                    k = l1;
                }

                j2 = k;
                k1 = i;
                j1 = j;
            } while(i1 != 0);
            l1 = i;
        }
        k3 = getLineForOffset(k);
        int l3 = getLineTop(k3);
        i1 = getLineForOffset(k + l1);
        if(k + j == l)
            i1 = getLineCount();
        int i4 = getLineTop(i1);
        int k2;
        boolean flag;
        int j3;
        StaticLayout staticlayout;
        Layout.Directions adirections[];
        if(i1 == getLineCount())
            j3 = 1;
        else
            j3 = 0;
        charsequence = ((CharSequence) (sLock));
        charsequence;
        JVM INSTR monitorenter ;
        staticlayout = sStaticLayout;
        obj = sBuilder;
        sStaticLayout = null;
        sBuilder = null;
        charsequence;
        JVM INSTR monitorexit ;
        charsequence = staticlayout;
        if(staticlayout == null)
        {
            charsequence = new StaticLayout(null);
            obj = StaticLayout.Builder.obtain(charsequence1, k, k + j, getPaint(), getWidth());
        }
        ((StaticLayout.Builder) (obj)).setText(charsequence1, k, k + j).setPaint(getPaint()).setWidth(getWidth()).setTextDirection(getTextDirectionHeuristic()).setLineSpacing(getSpacingAdd(), getSpacingMultiplier()).setEllipsizedWidth(mEllipsizedWidth).setEllipsize(mEllipsizeAt).setBreakStrategy(mBreakStrategy).setHyphenationFrequency(mHyphenationFrequency).setJustificationMode(mJustificationMode);
        charsequence.generate(((StaticLayout.Builder) (obj)), false, true);
        i = charsequence.getLineCount();
        j1 = i;
        if(k + j != l)
        {
            j1 = i;
            if(charsequence.getLineStart(i - 1) == k + j)
                j1 = i - 1;
        }
        mInts.deleteAt(k3, i1 - k3);
        mObjects.deleteAt(k3, i1 - k3);
        k2 = charsequence.getLineTop(j1);
        l = 0;
        flag = false;
        i = k2;
        k1 = l;
        if(mIncludePad)
        {
            i = k2;
            k1 = l;
            if(k3 == 0)
            {
                k1 = charsequence.getTopPadding();
                mTopPadding = k1;
                i = k2 - k1;
            }
        }
        k2 = ((flag) ? 1 : 0);
        l = i;
        if(mIncludePad)
        {
            k2 = ((flag) ? 1 : 0);
            l = i;
            if(j3 != 0)
            {
                k2 = charsequence.getBottomPadding();
                mBottomPadding = k2;
                l = i + k2;
            }
        }
        mInts.adjustValuesBelow(k3, 0, j - l1);
        mInts.adjustValuesBelow(k3, 1, (l3 - i4) + l);
        int ai[];
        if(mEllipsize)
        {
            ai = new int[6];
            ai[4] = 0x80000000;
        } else
        {
            ai = new int[4];
        }
        adirections = new Layout.Directions[1];
        i = 0;
        while(i < j1) 
        {
            l = charsequence.getLineStart(i);
            ai[0] = l;
            ai[0] = ai[0] | charsequence.getParagraphDirection(i) << 30;
            j3 = ai[0];
            int i2;
            if(charsequence.getLineContainsTab(i))
                i2 = 0x20000000;
            else
                i2 = 0;
            ai[0] = i2 | j3;
            j3 = charsequence.getLineTop(i) + l3;
            i2 = j3;
            if(i > 0)
                i2 = j3 - k1;
            ai[1] = i2;
            j3 = charsequence.getLineDescent(i);
            i2 = j3;
            if(i == j1 - 1)
                i2 = j3 + k2;
            ai[2] = i2;
            adirections[0] = charsequence.getLineDirections(i);
            if(i == j1 - 1)
                i2 = k + j;
            else
                i2 = charsequence.getLineStart(i + 1);
            ai[3] = charsequence.getHyphen(i) & 0xff;
            j3 = ai[3];
            if(contentMayProtrudeFromLineTopOrBottom(charsequence1, l, i2))
                i2 = 256;
            else
                i2 = 0;
            ai[3] = i2 | j3;
            if(mEllipsize)
            {
                ai[4] = charsequence.getEllipsisStart(i);
                ai[5] = charsequence.getEllipsisCount(i);
            }
            mInts.insertAt(k3 + i, ai);
            mObjects.insertAt(k3 + i, adirections);
            i++;
        }
        break MISSING_BLOCK_LABEL_1080;
        obj;
        throw obj;
        updateBlocks(k3, i1 - 1, j1);
        ((StaticLayout.Builder) (obj)).finish();
        Object aobj1[] = sLock;
        aobj1;
        JVM INSTR monitorenter ;
        sStaticLayout = charsequence;
        sBuilder = ((StaticLayout.Builder) (obj));
        aobj1;
        JVM INSTR monitorexit ;
        return;
        charsequence;
        throw charsequence;
    }

    private void updateAlwaysNeedsToBeRedrawn(int i)
    {
        int j;
        int k;
        if(i == 0)
            j = 0;
        else
            j = mBlockEndLines[i - 1] + 1;
        for(k = mBlockEndLines[i]; j <= k; j++)
            if(getContentMayProtrudeFromTopOrBottom(j))
            {
                if(mBlocksAlwaysNeedToBeRedrawn == null)
                    mBlocksAlwaysNeedToBeRedrawn = new ArraySet();
                mBlocksAlwaysNeedToBeRedrawn.add(Integer.valueOf(i));
                return;
            }

        if(mBlocksAlwaysNeedToBeRedrawn != null)
            mBlocksAlwaysNeedToBeRedrawn.remove(Integer.valueOf(i));
    }

    public int[] getBlockEndLines()
    {
        return mBlockEndLines;
    }

    public int getBlockIndex(int i)
    {
        return mBlockIndices[i];
    }

    public int[] getBlockIndices()
    {
        return mBlockIndices;
    }

    public ArraySet getBlocksAlwaysNeedToBeRedrawn()
    {
        return mBlocksAlwaysNeedToBeRedrawn;
    }

    public int getBottomPadding()
    {
        return mBottomPadding;
    }

    public int getEllipsisCount(int i)
    {
        if(mEllipsizeAt == null)
            return 0;
        else
            return mInts.getValue(i, 5);
    }

    public int getEllipsisStart(int i)
    {
        if(mEllipsizeAt == null)
            return 0;
        else
            return mInts.getValue(i, 4);
    }

    public int getEllipsizedWidth()
    {
        return mEllipsizedWidth;
    }

    public int getHyphen(int i)
    {
        return mInts.getValue(i, 3) & 0xff;
    }

    public int getIndexFirstChangedBlock()
    {
        return mIndexFirstChangedBlock;
    }

    public boolean getLineContainsTab(int i)
    {
        boolean flag = false;
        if((mInts.getValue(i, 0) & 0x20000000) != 0)
            flag = true;
        return flag;
    }

    public int getLineCount()
    {
        return mInts.size() - 1;
    }

    public int getLineDescent(int i)
    {
        return mInts.getValue(i, 2);
    }

    public final Layout.Directions getLineDirections(int i)
    {
        return (Layout.Directions)mObjects.getValue(i, 0);
    }

    public int getLineStart(int i)
    {
        return mInts.getValue(i, 0) & 0x1fffffff;
    }

    public int getLineTop(int i)
    {
        return mInts.getValue(i, 1);
    }

    public int getNumberOfBlocks()
    {
        return mNumberOfBlocks;
    }

    public int getParagraphDirection(int i)
    {
        return mInts.getValue(i, 0) >> 30;
    }

    public int getTopPadding()
    {
        return mTopPadding;
    }

    public void setBlockIndex(int i, int j)
    {
        mBlockIndices[i] = j;
    }

    public void setBlocksDataForTest(int ai[], int ai1[], int i, int j)
    {
        mBlockEndLines = new int[ai.length];
        mBlockIndices = new int[ai1.length];
        System.arraycopy(ai, 0, mBlockEndLines, 0, ai.length);
        System.arraycopy(ai1, 0, mBlockIndices, 0, ai1.length);
        mNumberOfBlocks = i;
        for(; mInts.size() < j; mInts.insertAt(mInts.size(), new int[4]));
    }

    public void setIndexFirstChangedBlock(int i)
    {
        mIndexFirstChangedBlock = i;
    }

    public void updateBlocks(int i, int j, int k)
    {
        byte byte0;
        int l;
        int i1;
        if(mBlockEndLines == null)
        {
            createBlocks();
            return;
        }
        byte0 = -1;
        l = -1;
        i1 = 0;
_L12:
        int l1 = byte0;
        if(i1 >= mNumberOfBlocks) goto _L2; else goto _L1
_L1:
        if(mBlockEndLines[i1] < i) goto _L4; else goto _L3
_L3:
        l1 = i1;
_L2:
        i1 = l1;
_L10:
        int i2 = l;
        if(i1 >= mNumberOfBlocks) goto _L6; else goto _L5
_L5:
        if(mBlockEndLines[i1] < j) goto _L8; else goto _L7
_L7:
        i2 = i1;
_L6:
        boolean flag;
        int j2;
        boolean flag1;
        boolean flag2;
        int k2;
        int l2;
        j2 = mBlockEndLines[i2];
        if(l1 == 0)
            i1 = 0;
        else
            i1 = mBlockEndLines[l1 - 1] + 1;
        if(i > i1)
            flag = true;
        else
            flag = false;
        if(k > 0)
            flag1 = true;
        else
            flag1 = false;
        if(j < mBlockEndLines[i2])
            flag2 = true;
        else
            flag2 = false;
        l = 0;
        if(flag)
            l = 1;
        i1 = l;
        if(flag1)
            i1 = l + 1;
        l = i1;
        if(flag2)
            l = i1 + 1;
        k2 = (i2 - l1) + 1;
        l2 = (mNumberOfBlocks + l) - k2;
        if(l2 == 0)
        {
            mBlockEndLines[0] = 0;
            mBlockIndices[0] = -1;
            mNumberOfBlocks = 1;
            return;
        }
        break; /* Loop/switch isn't completed */
_L4:
        i1++;
        continue; /* Loop/switch isn't completed */
_L8:
        i1++;
        if(true) goto _L10; else goto _L9
_L9:
        if(l2 > mBlockEndLines.length)
        {
            int ai[] = ArrayUtils.newUnpaddedIntArray(Math.max(mBlockEndLines.length * 2, l2));
            int ai1[] = new int[ai.length];
            System.arraycopy(mBlockEndLines, 0, ai, 0, l1);
            System.arraycopy(mBlockIndices, 0, ai1, 0, l1);
            System.arraycopy(mBlockEndLines, i2 + 1, ai, l1 + l, mNumberOfBlocks - i2 - 1);
            System.arraycopy(mBlockIndices, i2 + 1, ai1, l1 + l, mNumberOfBlocks - i2 - 1);
            mBlockEndLines = ai;
            mBlockIndices = ai1;
        } else
        if(l + k2 != 0)
        {
            System.arraycopy(mBlockEndLines, i2 + 1, mBlockEndLines, l1 + l, mNumberOfBlocks - i2 - 1);
            System.arraycopy(mBlockIndices, i2 + 1, mBlockIndices, l1 + l, mNumberOfBlocks - i2 - 1);
        }
        if(l + k2 != 0 && mBlocksAlwaysNeedToBeRedrawn != null)
        {
            ArraySet arrayset = new ArraySet();
            for(int j1 = 0; j1 < mBlocksAlwaysNeedToBeRedrawn.size(); j1++)
            {
                Integer integer = (Integer)mBlocksAlwaysNeedToBeRedrawn.valueAt(j1);
                Integer integer1 = integer;
                if(integer.intValue() > l1)
                    integer1 = Integer.valueOf(integer.intValue() + (l - k2));
                arrayset.add(integer1);
            }

            mBlocksAlwaysNeedToBeRedrawn = arrayset;
        }
        mNumberOfBlocks = l2;
        i2 = k - ((j - i) + 1);
        if(i2 != 0)
        {
            j = l1 + l;
            int k1 = j;
            do
            {
                l = j;
                if(k1 >= mNumberOfBlocks)
                    break;
                int ai2[] = mBlockEndLines;
                ai2[k1] = ai2[k1] + i2;
                k1++;
            } while(true);
        } else
        {
            l = mNumberOfBlocks;
        }
        mIndexFirstChangedBlock = Math.min(mIndexFirstChangedBlock, l);
        j = l1;
        if(flag)
        {
            mBlockEndLines[l1] = i - 1;
            updateAlwaysNeedsToBeRedrawn(l1);
            mBlockIndices[l1] = -1;
            j = l1 + 1;
        }
        l1 = j;
        if(flag1)
        {
            mBlockEndLines[j] = (i + k) - 1;
            updateAlwaysNeedsToBeRedrawn(j);
            mBlockIndices[j] = -1;
            l1 = j + 1;
        }
        if(flag2)
        {
            mBlockEndLines[l1] = j2 + i2;
            updateAlwaysNeedsToBeRedrawn(l1);
            mBlockIndices[l1] = -1;
        }
        return;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private static final int BLOCK_MINIMUM_CHARACTER_LENGTH = 400;
    private static final int COLUMNS_ELLIPSIZE = 6;
    private static final int COLUMNS_NORMAL = 4;
    private static final int DESCENT = 2;
    private static final int DIR = 0;
    private static final int DIR_SHIFT = 30;
    private static final int ELLIPSIS_COUNT = 5;
    private static final int ELLIPSIS_START = 4;
    private static final int ELLIPSIS_UNDEFINED = 0x80000000;
    private static final int HYPHEN = 3;
    private static final int HYPHEN_MASK = 255;
    public static final int INVALID_BLOCK_INDEX = -1;
    private static final int MAY_PROTRUDE_FROM_TOP_OR_BOTTOM = 3;
    private static final int MAY_PROTRUDE_FROM_TOP_OR_BOTTOM_MASK = 256;
    private static final int PRIORITY = 128;
    private static final int START = 0;
    private static final int START_MASK = 0x1fffffff;
    private static final int TAB = 0;
    private static final int TAB_MASK = 0x20000000;
    private static final int TOP = 1;
    private static StaticLayout.Builder sBuilder = null;
    private static final Object sLock[] = new Object[0];
    private static StaticLayout sStaticLayout = null;
    private CharSequence mBase;
    private int mBlockEndLines[];
    private int mBlockIndices[];
    private ArraySet mBlocksAlwaysNeedToBeRedrawn;
    private int mBottomPadding;
    private int mBreakStrategy;
    private CharSequence mDisplay;
    private boolean mEllipsize;
    private TextUtils.TruncateAt mEllipsizeAt;
    private int mEllipsizedWidth;
    private int mHyphenationFrequency;
    private boolean mIncludePad;
    private int mIndexFirstChangedBlock;
    private PackedIntVector mInts;
    private int mJustificationMode;
    private int mNumberOfBlocks;
    private PackedObjectVector mObjects;
    private Rect mTempRect;
    private int mTopPadding;
    private ChangeWatcher mWatcher;

}
