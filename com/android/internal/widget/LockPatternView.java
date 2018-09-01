// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.animation.*;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.accessibility.*;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import java.util.*;

// Referenced classes of package com.android.internal.widget:
//            LockPatternUtils, ExploreByTouchHelper

public class LockPatternView extends View
{
    public static final class Cell
    {

        private static void checkRange(int i, int j)
        {
            if(i < 0 || i > 2)
                throw new IllegalArgumentException("row must be in range 0-2");
            if(j < 0 || j > 2)
                throw new IllegalArgumentException("column must be in range 0-2");
            else
                return;
        }

        private static Cell[][] createCells()
        {
            Cell acell[][] = new Cell[3][3];
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 3; j++)
                    acell[i][j] = new Cell(i, j);

            }

            return acell;
        }

        public static Cell of(int i, int j)
        {
            checkRange(i, j);
            return sCells[i][j];
        }

        public int getColumn()
        {
            return column;
        }

        public int getRow()
        {
            return row;
        }

        public String toString()
        {
            return (new StringBuilder()).append("(row=").append(row).append(",clmn=").append(column).append(")").toString();
        }

        private static final Cell sCells[][] = createCells();
        final int column;
        final int row;


        private Cell(int i, int j)
        {
            checkRange(i, j);
            row = i;
            column = j;
        }
    }

    public static class CellState
    {

        float alpha;
        int col;
        boolean hwAnimating;
        CanvasProperty hwCenterX;
        CanvasProperty hwCenterY;
        CanvasProperty hwPaint;
        CanvasProperty hwRadius;
        public ValueAnimator lineAnimator;
        public float lineEndX;
        public float lineEndY;
        float radius;
        int row;
        float translationY;

        public CellState()
        {
            alpha = 1.0F;
            lineEndX = 1.401298E-045F;
            lineEndY = 1.401298E-045F;
        }
    }

    public static final class DisplayMode extends Enum
    {

        public static DisplayMode valueOf(String s)
        {
            return (DisplayMode)Enum.valueOf(com/android/internal/widget/LockPatternView$DisplayMode, s);
        }

        public static DisplayMode[] values()
        {
            return $VALUES;
        }

        private static final DisplayMode $VALUES[];
        public static final DisplayMode Animate;
        public static final DisplayMode Correct;
        public static final DisplayMode Wrong;

        static 
        {
            Correct = new DisplayMode("Correct", 0);
            Animate = new DisplayMode("Animate", 1);
            Wrong = new DisplayMode("Wrong", 2);
            $VALUES = (new DisplayMode[] {
                Correct, Animate, Wrong
            });
        }

        private DisplayMode(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface OnPatternListener
    {

        public abstract void onPatternCellAdded(List list);

        public abstract void onPatternCleared();

        public abstract void onPatternDetected(List list);

        public abstract void onPatternStart();
    }

    private final class PatternExploreByTouchHelper extends ExploreByTouchHelper
    {

        private Rect getBoundsForVirtualView(int i)
        {
            int j = i - 1;
            Rect rect = mTempRect;
            i = j / 3;
            j %= 3;
            CellState cellstate = LockPatternView._2D_get0(LockPatternView.this)[i][j];
            float f = LockPatternView._2D_wrap0(LockPatternView.this, j);
            float f1 = LockPatternView._2D_wrap1(LockPatternView.this, i);
            float f2 = LockPatternView._2D_get7(LockPatternView.this) * LockPatternView._2D_get4(LockPatternView.this) * 0.5F;
            float f3 = LockPatternView._2D_get8(LockPatternView.this) * LockPatternView._2D_get4(LockPatternView.this) * 0.5F;
            rect.left = (int)(f - f3);
            rect.right = (int)(f + f3);
            rect.top = (int)(f1 - f2);
            rect.bottom = (int)(f1 + f2);
            return rect;
        }

        private CharSequence getTextForVirtualView(int i)
        {
            return getResources().getString(0x1040306, new Object[] {
                Integer.valueOf(i)
            });
        }

        private int getVirtualViewIdForHit(float f, float f1)
        {
            int i = LockPatternView._2D_wrap3(LockPatternView.this, f1);
            if(i < 0)
                return 0x80000000;
            int j = LockPatternView._2D_wrap2(LockPatternView.this, f);
            if(j < 0)
                return 0x80000000;
            if(LockPatternView._2D_get5(LockPatternView.this)[i][j])
                j = i * 3 + j + 1;
            else
                j = 0x80000000;
            return j;
        }

        private boolean isClickable(int i)
        {
            if(i != 0x80000000)
            {
                int j = (i - 1) / 3;
                return LockPatternView._2D_get5(LockPatternView.this)[j][(i - 1) % 3] ^ true;
            } else
            {
                return false;
            }
        }

        protected int getVirtualViewAt(float f, float f1)
        {
            return getVirtualViewIdForHit(f, f1);
        }

        protected void getVisibleVirtualViews(IntArray intarray)
        {
            if(!LockPatternView._2D_get6(LockPatternView.this))
                return;
            for(int i = 1; i < 10; i++)
                intarray.add(i);

        }

        boolean onItemClicked(int i)
        {
            invalidateVirtualView(i);
            sendEventForVirtualView(i, 1);
            return true;
        }

        protected boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
        {
            switch(j)
            {
            default:
                return false;

            case 16: // '\020'
                return onItemClicked(i);
            }
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onPopulateAccessibilityEvent(view, accessibilityevent);
            if(!LockPatternView._2D_get6(LockPatternView.this))
                accessibilityevent.setContentDescription(getContext().getText(0x1040304));
        }

        protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
        {
            VirtualViewContainer virtualviewcontainer = (VirtualViewContainer)mItems.get(i);
            if(virtualviewcontainer != null)
                accessibilityevent.getText().add(virtualviewcontainer.description);
        }

        protected void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            accessibilitynodeinfo.setText(getTextForVirtualView(i));
            accessibilitynodeinfo.setContentDescription(getTextForVirtualView(i));
            if(LockPatternView._2D_get6(LockPatternView.this))
            {
                accessibilitynodeinfo.setFocusable(true);
                if(isClickable(i))
                {
                    accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
                    accessibilitynodeinfo.setClickable(isClickable(i));
                }
            }
            accessibilitynodeinfo.setBoundsInParent(getBoundsForVirtualView(i));
        }

        private final SparseArray mItems = new SparseArray();
        private Rect mTempRect;
        final LockPatternView this$0;

        public PatternExploreByTouchHelper(View view)
        {
            this$0 = LockPatternView.this;
            super(view);
            mTempRect = new Rect();
            for(int i = 1; i < 10; i++)
                mItems.put(i, new VirtualViewContainer(getTextForVirtualView(i)));

        }
    }

    class PatternExploreByTouchHelper.VirtualViewContainer
    {

        CharSequence description;
        final PatternExploreByTouchHelper this$1;

        public PatternExploreByTouchHelper.VirtualViewContainer(CharSequence charsequence)
        {
            this$1 = PatternExploreByTouchHelper.this;
            super();
            description = charsequence;
        }
    }

    private static class SavedState extends android.view.View.BaseSavedState
    {

        public int getDisplayMode()
        {
            return mDisplayMode;
        }

        public String getSerializedPattern()
        {
            return mSerializedPattern;
        }

        public boolean isInStealthMode()
        {
            return mInStealthMode;
        }

        public boolean isInputEnabled()
        {
            return mInputEnabled;
        }

        public boolean isTactileFeedbackEnabled()
        {
            return mTactileFeedbackEnabled;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeString(mSerializedPattern);
            parcel.writeInt(mDisplayMode);
            parcel.writeValue(Boolean.valueOf(mInputEnabled));
            parcel.writeValue(Boolean.valueOf(mInStealthMode));
            parcel.writeValue(Boolean.valueOf(mTactileFeedbackEnabled));
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int mDisplayMode;
        private final boolean mInStealthMode;
        private final boolean mInputEnabled;
        private final String mSerializedPattern;
        private final boolean mTactileFeedbackEnabled;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            mSerializedPattern = parcel.readString();
            mDisplayMode = parcel.readInt();
            mInputEnabled = ((Boolean)parcel.readValue(null)).booleanValue();
            mInStealthMode = ((Boolean)parcel.readValue(null)).booleanValue();
            mTactileFeedbackEnabled = ((Boolean)parcel.readValue(null)).booleanValue();
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        private SavedState(Parcelable parcelable, String s, int i, boolean flag, boolean flag1, boolean flag2)
        {
            super(parcelable);
            mSerializedPattern = s;
            mDisplayMode = i;
            mInputEnabled = flag;
            mInStealthMode = flag1;
            mTactileFeedbackEnabled = flag2;
        }

        SavedState(Parcelable parcelable, String s, int i, boolean flag, boolean flag1, boolean flag2, SavedState savedstate)
        {
            this(parcelable, s, i, flag, flag1, flag2);
        }
    }


    static CellState[][] _2D_get0(LockPatternView lockpatternview)
    {
        return lockpatternview.mCellStates;
    }

    static int _2D_get1(LockPatternView lockpatternview)
    {
        return lockpatternview.mDotSize;
    }

    static int _2D_get2(LockPatternView lockpatternview)
    {
        return lockpatternview.mDotSizeActivated;
    }

    static Interpolator _2D_get3(LockPatternView lockpatternview)
    {
        return lockpatternview.mFastOutSlowInInterpolator;
    }

    static float _2D_get4(LockPatternView lockpatternview)
    {
        return lockpatternview.mHitFactor;
    }

    static boolean[][] _2D_get5(LockPatternView lockpatternview)
    {
        return lockpatternview.mPatternDrawLookup;
    }

    static boolean _2D_get6(LockPatternView lockpatternview)
    {
        return lockpatternview.mPatternInProgress;
    }

    static float _2D_get7(LockPatternView lockpatternview)
    {
        return lockpatternview.mSquareHeight;
    }

    static float _2D_get8(LockPatternView lockpatternview)
    {
        return lockpatternview.mSquareWidth;
    }

    static float _2D_wrap0(LockPatternView lockpatternview, int i)
    {
        return lockpatternview.getCenterXForColumn(i);
    }

    static float _2D_wrap1(LockPatternView lockpatternview, int i)
    {
        return lockpatternview.getCenterYForRow(i);
    }

    static int _2D_wrap2(LockPatternView lockpatternview, float f)
    {
        return lockpatternview.getColumnHit(f);
    }

    static int _2D_wrap3(LockPatternView lockpatternview, float f)
    {
        return lockpatternview.getRowHit(f);
    }

    static void _2D_wrap4(LockPatternView lockpatternview, float f, float f1, long l, Interpolator interpolator, CellState cellstate, Runnable runnable)
    {
        lockpatternview.startRadiusAnimation(f, f1, l, interpolator, cellstate, runnable);
    }

    public LockPatternView(Context context)
    {
        this(context, null);
    }

    public LockPatternView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mDrawingProfilingStarted = false;
        mPaint = new Paint();
        mPathPaint = new Paint();
        mPattern = new ArrayList(9);
        mPatternDrawLookup = new boolean[3][3];
        mInProgressX = -1F;
        mInProgressY = -1F;
        mPatternDisplayMode = DisplayMode.Correct;
        mInputEnabled = true;
        mInStealthMode = false;
        mEnableHapticFeedback = true;
        mPatternInProgress = false;
        mHitFactor = 0.6F;
        mCurrentPath = new Path();
        mInvalidate = new Rect();
        mTmpInvalidateRect = new Rect();
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.LockPatternView, 0x1110060, 0x1030487);
        String s = attributeset.getString(0);
        if("square".equals(s))
            mAspect = 0;
        else
        if("lock_width".equals(s))
            mAspect = 1;
        else
        if("lock_height".equals(s))
            mAspect = 2;
        else
            mAspect = 0;
        do
        {
            setClickable(true);
            mPathPaint.setAntiAlias(true);
            mPathPaint.setDither(true);
            mRegularColor = attributeset.getColor(3, 0);
            mErrorColor = attributeset.getColor(1, 0);
            mSuccessColor = attributeset.getColor(4, 0);
            int i = attributeset.getColor(2, mRegularColor);
            mPathPaint.setColor(i);
            mPathPaint.setStyle(android.graphics.Paint.Style.STROKE);
            mPathPaint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
            mPathPaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
            mPathWidth = getResources().getDimensionPixelSize(0x10500ee);
            mPathPaint.setStrokeWidth(mPathWidth);
            mDotSize = getResources().getDimensionPixelSize(0x10500ef);
            mDotSizeActivated = getResources().getDimensionPixelSize(0x10500f0);
            mUseLockPatternDrawable = getResources().getBoolean(0x11200f3);
            if(mUseLockPatternDrawable)
            {
                mSelectedDrawable = getResources().getDrawable(0x108058f);
                mNotSelectedDrawable = getResources().getDrawable(0x108058d);
            }
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mCellStates = new CellState[3][3];
            for(int j = 0; j < 3; j++)
            {
                for(int k = 0; k < 3; k++)
                {
                    mCellStates[j][k] = new CellState();
                    mCellStates[j][k].radius = mDotSize / 2;
                    mCellStates[j][k].row = j;
                    mCellStates[j][k].col = k;
                }

            }

            mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(context, 0x10c000d);
            mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(context, 0x10c000e);
            mExploreByTouchHelper = new PatternExploreByTouchHelper(this);
            setAccessibilityDelegate(mExploreByTouchHelper);
            mAudioManager = (AudioManager)mContext.getSystemService("audio");
            attributeset.recycle();
            return;
        } while(true);
    }

    private void addCellToPattern(Cell cell)
    {
        mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
        mPattern.add(cell);
        if(!mInStealthMode)
            startCellActivatedAnimation(cell);
        notifyCellAdded();
    }

    private float calculateLastSegmentAlpha(float f, float f1, float f2, float f3)
    {
        f -= f2;
        f1 -= f3;
        return Math.min(1.0F, Math.max(0.0F, ((float)Math.sqrt(f * f + f1 * f1) / mSquareWidth - 0.3F) * 4F));
    }

    private void cancelLineAnimations()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                CellState cellstate = mCellStates[i][j];
                if(cellstate.lineAnimator != null)
                {
                    cellstate.lineAnimator.cancel();
                    cellstate.lineEndX = 1.401298E-045F;
                    cellstate.lineEndY = 1.401298E-045F;
                }
            }

        }

    }

    private Cell checkForNewHit(float f, float f1)
    {
        int i = getRowHit(f1);
        if(i < 0)
            return null;
        int j = getColumnHit(f);
        if(j < 0)
            return null;
        if(mPatternDrawLookup[i][j])
            return null;
        else
            return Cell.of(i, j);
    }

    private void clearPatternDrawLookup()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
                mPatternDrawLookup[i][j] = false;

        }

    }

    private Cell detectAndAddHit(float f, float f1)
    {
        byte byte0 = -1;
        Cell cell = checkForNewHit(f, f1);
        if(cell != null)
        {
            Cell cell1 = null;
            ArrayList arraylist = mPattern;
            if(!arraylist.isEmpty())
            {
                cell1 = (Cell)arraylist.get(arraylist.size() - 1);
                int i = cell.row - cell1.row;
                int j = cell.column - cell1.column;
                int k = cell1.row;
                int i1 = cell1.column;
                int j1 = k;
                if(Math.abs(i) == 2)
                {
                    j1 = k;
                    if(Math.abs(j) != 1)
                    {
                        int l = cell1.row;
                        if(i > 0)
                            j1 = 1;
                        else
                            j1 = -1;
                        j1 = l + j1;
                    }
                }
                l = i1;
                if(Math.abs(j) == 2)
                {
                    l = i1;
                    if(Math.abs(i) != 1)
                    {
                        i1 = cell1.column;
                        l = byte0;
                        if(j > 0)
                            l = 1;
                        l = i1 + l;
                    }
                }
                cell1 = Cell.of(j1, l);
            }
            if(cell1 != null && mPatternDrawLookup[cell1.row][cell1.column] ^ true)
                addCellToPattern(cell1);
            addCellToPattern(cell);
            if(mEnableHapticFeedback)
                performHapticFeedback(1, 3);
            return cell;
        } else
        {
            return null;
        }
    }

    private void drawCellDrawable(Canvas canvas, int i, int j, float f, boolean flag)
    {
        Rect rect = new Rect((int)((float)mPaddingLeft + (float)j * mSquareWidth), (int)((float)mPaddingTop + (float)i * mSquareHeight), (int)((float)mPaddingLeft + (float)(j + 1) * mSquareWidth), (int)((float)mPaddingTop + (float)(i + 1) * mSquareHeight));
        f /= mDotSize / 2;
        canvas.save();
        canvas.clipRect(rect);
        canvas.scale(f, f, rect.centerX(), rect.centerY());
        if(!flag || f > 1.0F)
            mNotSelectedDrawable.draw(canvas);
        else
            mSelectedDrawable.draw(canvas);
        canvas.restore();
    }

    private void drawCircle(Canvas canvas, float f, float f1, float f2, boolean flag, float f3)
    {
        mPaint.setColor(getCurrentColor(flag));
        mPaint.setAlpha((int)(255F * f3));
        canvas.drawCircle(f, f1, f2, mPaint);
    }

    private float getCenterXForColumn(int i)
    {
        return (float)mPaddingLeft + (float)i * mSquareWidth + mSquareWidth / 2.0F;
    }

    private float getCenterYForRow(int i)
    {
        return (float)mPaddingTop + (float)i * mSquareHeight + mSquareHeight / 2.0F;
    }

    private int getColumnHit(float f)
    {
        float f1 = mSquareWidth;
        float f2 = f1 * mHitFactor;
        float f3 = mPaddingLeft;
        float f4 = (f1 - f2) / 2.0F;
        for(int i = 0; i < 3; i++)
        {
            float f5 = f3 + f4 + (float)i * f1;
            if(f >= f5 && f <= f5 + f2)
                return i;
        }

        return -1;
    }

    private int getCurrentColor(boolean flag)
    {
        if(!flag || mInStealthMode || mPatternInProgress)
            return mRegularColor;
        if(mPatternDisplayMode == DisplayMode.Wrong)
            return mErrorColor;
        if(mPatternDisplayMode == DisplayMode.Correct || mPatternDisplayMode == DisplayMode.Animate)
            return mSuccessColor;
        else
            throw new IllegalStateException((new StringBuilder()).append("unknown display mode ").append(mPatternDisplayMode).toString());
    }

    private int getRowHit(float f)
    {
        float f1 = mSquareHeight;
        float f2 = f1 * mHitFactor;
        float f3 = mPaddingTop;
        float f4 = (f1 - f2) / 2.0F;
        for(int i = 0; i < 3; i++)
        {
            float f5 = f3 + f4 + (float)i * f1;
            if(f >= f5 && f <= f5 + f2)
                return i;
        }

        return -1;
    }

    private void handleActionDown(MotionEvent motionevent)
    {
        float f;
        float f1;
        resetPattern();
        f = motionevent.getX();
        f1 = motionevent.getY();
        motionevent = detectAndAddHit(f, f1);
        if(motionevent == null) goto _L2; else goto _L1
_L1:
        setPatternInProgress(true);
        mPatternDisplayMode = DisplayMode.Correct;
        notifyPatternStarted();
_L4:
        if(motionevent != null)
        {
            float f2 = getCenterXForColumn(((Cell) (motionevent)).column);
            float f3 = getCenterYForRow(((Cell) (motionevent)).row);
            float f4 = mSquareWidth / 2.0F;
            float f5 = mSquareHeight / 2.0F;
            invalidate((int)(f2 - f4), (int)(f3 - f5), (int)(f2 + f4), (int)(f3 + f5));
        }
        mInProgressX = f;
        mInProgressY = f1;
        return;
_L2:
        if(mPatternInProgress)
        {
            setPatternInProgress(false);
            notifyPatternCleared();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void handleActionMove(MotionEvent motionevent)
    {
        float f = mPathWidth;
        int i = motionevent.getHistorySize();
        mTmpInvalidateRect.setEmpty();
        boolean flag = false;
        int j = 0;
        while(j < i + 1) 
        {
            float f1;
            float f2;
            Cell cell;
            int k;
            float f3;
            float f4;
            Cell cell1;
            float f5;
            float f6;
            float f7;
            float f8;
            if(j < i)
                f1 = motionevent.getHistoricalX(j);
            else
                f1 = motionevent.getX();
            if(j < i)
                f2 = motionevent.getHistoricalY(j);
            else
                f2 = motionevent.getY();
            cell = detectAndAddHit(f1, f2);
            k = mPattern.size();
            if(cell != null && k == 1)
            {
                setPatternInProgress(true);
                notifyPatternStarted();
            }
            f3 = Math.abs(f1 - mInProgressX);
            f4 = Math.abs(f2 - mInProgressY);
            if(f3 > 0.0F || f4 > 0.0F)
                flag = true;
            if(!mPatternInProgress || k <= 0)
                continue;
            cell1 = (Cell)mPattern.get(k - 1);
            f4 = getCenterXForColumn(cell1.column);
            f3 = getCenterYForRow(cell1.row);
            f5 = Math.min(f4, f1) - f;
            f6 = Math.max(f4, f1) + f;
            f7 = Math.min(f3, f2) - f;
            f8 = Math.max(f3, f2) + f;
            f4 = f8;
            f3 = f5;
            f1 = f6;
            f2 = f7;
            if(cell != null)
            {
                f2 = mSquareWidth * 0.5F;
                f4 = mSquareHeight * 0.5F;
                f1 = getCenterXForColumn(cell.column);
                float f9 = getCenterYForRow(cell.row);
                f3 = Math.min(f1 - f2, f5);
                f1 = Math.max(f1 + f2, f6);
                f2 = Math.min(f9 - f4, f7);
                f4 = Math.max(f9 + f4, f8);
            }
            mTmpInvalidateRect.union(Math.round(f3), Math.round(f2), Math.round(f1), Math.round(f4));
            j++;
        }
        mInProgressX = motionevent.getX();
        mInProgressY = motionevent.getY();
        if(flag)
        {
            mInvalidate.union(mTmpInvalidateRect);
            invalidate(mInvalidate);
            mInvalidate.set(mTmpInvalidateRect);
        }
    }

    private void handleActionUp()
    {
        if(!mPattern.isEmpty())
        {
            setPatternInProgress(false);
            cancelLineAnimations();
            notifyPatternDetected();
            invalidate();
        }
    }

    private void notifyCellAdded()
    {
        if(mOnPatternListener != null)
            mOnPatternListener.onPatternCellAdded(mPattern);
        mExploreByTouchHelper.invalidateRoot();
    }

    private void notifyPatternCleared()
    {
        sendAccessEvent(0x1040307);
        if(mOnPatternListener != null)
            mOnPatternListener.onPatternCleared();
    }

    private void notifyPatternDetected()
    {
        sendAccessEvent(0x1040308);
        if(mOnPatternListener != null)
            mOnPatternListener.onPatternDetected(mPattern);
    }

    private void notifyPatternStarted()
    {
        sendAccessEvent(0x1040309);
        if(mOnPatternListener != null)
            mOnPatternListener.onPatternStart();
    }

    private void resetPattern()
    {
        mPattern.clear();
        clearPatternDrawLookup();
        mPatternDisplayMode = DisplayMode.Correct;
        invalidate();
    }

    private int resolveMeasured(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(i);
        android.view.View.MeasureSpec.getMode(i);
        JVM INSTR lookupswitch 2: default 36
    //                   -2147483648: 45
    //                   0: 40;
           goto _L1 _L2 _L3
_L1:
        i = k;
_L5:
        return i;
_L3:
        i = j;
        continue; /* Loop/switch isn't completed */
_L2:
        i = Math.max(k, j);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void sendAccessEvent(int i)
    {
        announceForAccessibility(mContext.getString(i));
    }

    private void setPatternInProgress(boolean flag)
    {
        mPatternInProgress = flag;
        mExploreByTouchHelper.invalidateRoot();
    }

    private void startCellActivatedAnimation(Cell cell)
    {
        final CellState cellState = mCellStates[cell.row][cell.column];
        startRadiusAnimation(mDotSize / 2, mDotSizeActivated / 2, 96L, mLinearOutSlowInInterpolator, cellState, new Runnable() {

            public void run()
            {
                LockPatternView._2D_wrap4(LockPatternView.this, LockPatternView._2D_get2(LockPatternView.this) / 2, LockPatternView._2D_get1(LockPatternView.this) / 2, 192L, LockPatternView._2D_get3(LockPatternView.this), cellState, null);
            }

            final LockPatternView this$0;
            final CellState val$cellState;

            
            {
                this$0 = LockPatternView.this;
                cellState = cellstate;
                super();
            }
        }
);
        startLineEndAnimation(cellState, mInProgressX, mInProgressY, getCenterXForColumn(cell.column), getCenterYForRow(cell.row));
    }

    private void startCellStateAnimationHw(final CellState cellState, float f, float f1, float f2, float f3, float f4, float f5, 
            long l, long l1, Interpolator interpolator, final Runnable finishRunnable)
    {
        cellState.alpha = f1;
        cellState.translationY = f3;
        cellState.radius = (float)(mDotSize / 2) * f5;
        cellState.hwAnimating = true;
        cellState.hwCenterY = CanvasProperty.createFloat(getCenterYForRow(cellState.row) + f2);
        cellState.hwCenterX = CanvasProperty.createFloat(getCenterXForColumn(cellState.col));
        cellState.hwRadius = CanvasProperty.createFloat((float)(mDotSize / 2) * f4);
        mPaint.setColor(getCurrentColor(false));
        mPaint.setAlpha((int)(255F * f));
        cellState.hwPaint = CanvasProperty.createPaint(new Paint(mPaint));
        startRtFloatAnimation(cellState.hwCenterY, getCenterYForRow(cellState.row) + f3, l, l1, interpolator);
        startRtFloatAnimation(cellState.hwRadius, (float)(mDotSize / 2) * f5, l, l1, interpolator);
        startRtAlphaAnimation(cellState, f1, l, l1, interpolator, new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                cellState.hwAnimating = false;
                if(finishRunnable != null)
                    finishRunnable.run();
            }

            final LockPatternView this$0;
            final CellState val$cellState;
            final Runnable val$finishRunnable;

            
            {
                this$0 = LockPatternView.this;
                cellState = cellstate;
                finishRunnable = runnable;
                super();
            }
        }
);
        invalidate();
    }

    private void startCellStateAnimationSw(final CellState cellState, final float startAlpha, final float endAlpha, final float startTranslationY, final float endTranslationY, final float startScale, final float endScale, 
            long l, long l1, Interpolator interpolator, final Runnable finishRunnable)
    {
        cellState.alpha = startAlpha;
        cellState.translationY = startTranslationY;
        cellState.radius = (float)(mDotSize / 2) * startScale;
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        });
        valueanimator.setDuration(l1);
        valueanimator.setStartDelay(l);
        valueanimator.setInterpolator(interpolator);
        valueanimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                float f = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                cellState.alpha = (1.0F - f) * startAlpha + endAlpha * f;
                cellState.translationY = (1.0F - f) * startTranslationY + endTranslationY * f;
                cellState.radius = (float)(LockPatternView._2D_get1(LockPatternView.this) / 2) * ((1.0F - f) * startScale + endScale * f);
                invalidate();
            }

            final LockPatternView this$0;
            final CellState val$cellState;
            final float val$endAlpha;
            final float val$endScale;
            final float val$endTranslationY;
            final float val$startAlpha;
            final float val$startScale;
            final float val$startTranslationY;

            
            {
                this$0 = LockPatternView.this;
                cellState = cellstate;
                startAlpha = f;
                endAlpha = f1;
                startTranslationY = f2;
                endTranslationY = f3;
                startScale = f4;
                endScale = f5;
                super();
            }
        }
);
        valueanimator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                if(finishRunnable != null)
                    finishRunnable.run();
            }

            final LockPatternView this$0;
            final Runnable val$finishRunnable;

            
            {
                this$0 = LockPatternView.this;
                finishRunnable = runnable;
                super();
            }
        }
);
        valueanimator.start();
    }

    private void startLineEndAnimation(final CellState state, final float startX, final float startY, final float targetX, final float targetY)
    {
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        });
        valueanimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                float f = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                state.lineEndX = (1.0F - f) * startX + targetX * f;
                state.lineEndY = (1.0F - f) * startY + targetY * f;
                invalidate();
            }

            final LockPatternView this$0;
            final float val$startX;
            final float val$startY;
            final CellState val$state;
            final float val$targetX;
            final float val$targetY;

            
            {
                this$0 = LockPatternView.this;
                state = cellstate;
                startX = f;
                targetX = f1;
                startY = f2;
                targetY = f3;
                super();
            }
        }
);
        valueanimator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                state.lineAnimator = null;
            }

            final LockPatternView this$0;
            final CellState val$state;

            
            {
                this$0 = LockPatternView.this;
                state = cellstate;
                super();
            }
        }
);
        valueanimator.setInterpolator(mFastOutSlowInInterpolator);
        valueanimator.setDuration(100L);
        valueanimator.start();
        state.lineAnimator = valueanimator;
    }

    private void startRadiusAnimation(float f, float f1, long l, Interpolator interpolator, final CellState state, final Runnable endRunnable)
    {
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            f, f1
        });
        valueanimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                state.radius = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                invalidate();
            }

            final LockPatternView this$0;
            final CellState val$state;

            
            {
                this$0 = LockPatternView.this;
                state = cellstate;
                super();
            }
        }
);
        if(endRunnable != null)
            valueanimator.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    endRunnable.run();
                }

                final LockPatternView this$0;
                final Runnable val$endRunnable;

            
            {
                this$0 = LockPatternView.this;
                endRunnable = runnable;
                super();
            }
            }
);
        valueanimator.setInterpolator(interpolator);
        valueanimator.setDuration(l);
        valueanimator.start();
    }

    private void startRtAlphaAnimation(CellState cellstate, float f, long l, long l1, Interpolator interpolator, 
            android.animation.Animator.AnimatorListener animatorlistener)
    {
        cellstate = new RenderNodeAnimator(cellstate.hwPaint, 1, (int)(255F * f));
        cellstate.setDuration(l1);
        cellstate.setStartDelay(l);
        cellstate.setInterpolator(interpolator);
        cellstate.setTarget(this);
        cellstate.addListener(animatorlistener);
        cellstate.start();
    }

    private void startRtFloatAnimation(CanvasProperty canvasproperty, float f, long l, long l1, Interpolator interpolator)
    {
        canvasproperty = new RenderNodeAnimator(canvasproperty, f);
        canvasproperty.setDuration(l1);
        canvasproperty.setStartDelay(l);
        canvasproperty.setInterpolator(interpolator);
        canvasproperty.setTarget(this);
        canvasproperty.start();
    }

    public void clearPattern()
    {
        resetPattern();
    }

    public void disableInput()
    {
        mInputEnabled = false;
    }

    protected boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        return super.dispatchHoverEvent(motionevent) | mExploreByTouchHelper.dispatchHoverEvent(motionevent);
    }

    public void enableInput()
    {
        mInputEnabled = true;
    }

    public CellState[][] getCellStates()
    {
        return mCellStates;
    }

    public boolean isInStealthMode()
    {
        return mInStealthMode;
    }

    public boolean isTactileFeedbackEnabled()
    {
        return mEnableHapticFeedback;
    }

    protected void onDraw(Canvas canvas)
    {
        ArrayList arraylist;
        int i;
        boolean aflag[][];
        Path path;
        arraylist = mPattern;
        i = arraylist.size();
        aflag = mPatternDrawLookup;
        int k1;
        if(mPatternDisplayMode == DisplayMode.Animate)
        {
            int j = (int)(SystemClock.elapsedRealtime() - mAnimatingPeriodStart) % ((i + 1) * 700);
            int i1 = j / 700;
            clearPatternDrawLookup();
            for(int j1 = 0; j1 < i1; j1++)
            {
                Cell cell = (Cell)arraylist.get(j1);
                aflag[cell.getRow()][cell.getColumn()] = true;
            }

            if(i1 > 0)
            {
                if(i1 < i)
                    k1 = 1;
                else
                    k1 = 0;
            } else
            {
                k1 = 0;
            }
            if(k1 != 0)
            {
                float f = (float)(j % 700) / 700F;
                Cell cell1 = (Cell)arraylist.get(i1 - 1);
                float f2 = getCenterXForColumn(cell1.column);
                float f5 = getCenterYForRow(cell1.row);
                cell1 = (Cell)arraylist.get(i1);
                float f8 = getCenterXForColumn(cell1.column);
                float f9 = getCenterYForRow(cell1.row);
                mInProgressX = f2 + f * (f8 - f2);
                mInProgressY = f5 + f * (f9 - f5);
            }
            invalidate();
        }
        path = mCurrentPath;
        path.rewind();
        for(k1 = 0; k1 < 3; k1++)
        {
            float f6 = getCenterYForRow(k1);
            int k = 0;
            while(k < 3) 
            {
                CellState cellstate = mCellStates[k1][k];
                float f10 = getCenterXForColumn(k);
                float f3 = cellstate.translationY;
                if(mUseLockPatternDrawable)
                    drawCellDrawable(canvas, k1, k, cellstate.radius, aflag[k1][k]);
                else
                if(isHardwareAccelerated() && cellstate.hwAnimating)
                    ((DisplayListCanvas)canvas).drawCircle(cellstate.hwCenterX, cellstate.hwCenterY, cellstate.hwRadius, cellstate.hwPaint);
                else
                    drawCircle(canvas, (int)f10, (float)(int)f6 + f3, cellstate.radius, aflag[k1][k], cellstate.alpha);
                k++;
            }
        }

        if(!(mInStealthMode ^ true)) goto _L2; else goto _L1
_L1:
        int l;
        boolean flag;
        float f4;
        float f11;
        mPathPaint.setColor(getCurrentColor(true));
        flag = false;
        f11 = 0.0F;
        f4 = 0.0F;
        l = 0;
_L7:
        if(l >= i) goto _L4; else goto _L3
_L3:
        Object obj = (Cell)arraylist.get(l);
        if(aflag[((Cell) (obj)).row][((Cell) (obj)).column]) goto _L5; else goto _L4
_L4:
        if((mPatternInProgress || mPatternDisplayMode == DisplayMode.Animate) && flag)
        {
            path.rewind();
            path.moveTo(f11, f4);
            path.lineTo(mInProgressX, mInProgressY);
            mPathPaint.setAlpha((int)(calculateLastSegmentAlpha(mInProgressX, mInProgressY, f11, f4) * 255F));
            canvas.drawPath(path, mPathPaint);
        }
_L2:
        return;
_L5:
        flag = true;
        float f1 = getCenterXForColumn(((Cell) (obj)).column);
        float f7 = getCenterYForRow(((Cell) (obj)).row);
        if(l != 0)
        {
            obj = mCellStates[((Cell) (obj)).row][((Cell) (obj)).column];
            path.rewind();
            path.moveTo(f11, f4);
            if(((CellState) (obj)).lineEndX != 1.401298E-045F && ((CellState) (obj)).lineEndY != 1.401298E-045F)
                path.lineTo(((CellState) (obj)).lineEndX, ((CellState) (obj)).lineEndY);
            else
                path.lineTo(f1, f7);
            canvas.drawPath(path, mPathPaint);
        }
        f11 = f1;
        f4 = f7;
        l++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        if(!AccessibilityManager.getInstance(mContext).isTouchExplorationEnabled()) goto _L2; else goto _L1
_L1:
        int i = motionevent.getAction();
        i;
        JVM INSTR tableswitch 7 10: default 48
    //                   7 73
    //                   8 48
    //                   9 65
    //                   10 81;
           goto _L3 _L4 _L3 _L5 _L6
_L3:
        onTouchEvent(motionevent);
        motionevent.setAction(i);
_L2:
        return super.onHoverEvent(motionevent);
_L5:
        motionevent.setAction(0);
        continue; /* Loop/switch isn't completed */
_L4:
        motionevent.setAction(2);
        continue; /* Loop/switch isn't completed */
_L6:
        motionevent.setAction(1);
        if(true) goto _L3; else goto _L7
_L7:
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        k = getSuggestedMinimumWidth();
        int l = getSuggestedMinimumHeight();
        k = resolveMeasured(i, k);
        i = resolveMeasured(j, l);
        mAspect;
        JVM INSTR tableswitch 0 2: default 56
    //                   0 65
    //                   1 76
    //                   2 87;
           goto _L1 _L2 _L3 _L4
_L1:
        j = k;
_L6:
        setMeasuredDimension(j, i);
        return;
_L2:
        i = Math.min(k, i);
        j = i;
        continue; /* Loop/switch isn't completed */
_L3:
        i = Math.min(k, i);
        j = k;
        continue; /* Loop/switch isn't completed */
_L4:
        j = Math.min(k, i);
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        setPattern(DisplayMode.Correct, LockPatternUtils.stringToPattern(parcelable.getSerializedPattern()));
        mPatternDisplayMode = DisplayMode.values()[parcelable.getDisplayMode()];
        mInputEnabled = parcelable.isInputEnabled();
        mInStealthMode = parcelable.isInStealthMode();
        mEnableHapticFeedback = parcelable.isTactileFeedbackEnabled();
    }

    protected Parcelable onSaveInstanceState()
    {
        return new SavedState(super.onSaveInstanceState(), LockPatternUtils.patternToString(mPattern), mPatternDisplayMode.ordinal(), mInputEnabled, mInStealthMode, mEnableHapticFeedback, null);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        i = i - mPaddingLeft - mPaddingRight;
        mSquareWidth = (float)i / 3F;
        j = j - mPaddingTop - mPaddingBottom;
        mSquareHeight = (float)j / 3F;
        mExploreByTouchHelper.invalidateRoot();
        if(mUseLockPatternDrawable)
        {
            mNotSelectedDrawable.setBounds(mPaddingLeft, mPaddingTop, i, j);
            mSelectedDrawable.setBounds(mPaddingLeft, mPaddingTop, i, j);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!mInputEnabled || isEnabled() ^ true)
            return false;
        switch(motionevent.getAction())
        {
        default:
            return false;

        case 0: // '\0'
            handleActionDown(motionevent);
            return true;

        case 1: // '\001'
            handleActionUp();
            return true;

        case 2: // '\002'
            handleActionMove(motionevent);
            return true;

        case 3: // '\003'
            break;
        }
        if(mPatternInProgress)
        {
            setPatternInProgress(false);
            resetPattern();
            notifyPatternCleared();
        }
        return true;
    }

    public void setDisplayMode(DisplayMode displaymode)
    {
        mPatternDisplayMode = displaymode;
        if(displaymode == DisplayMode.Animate)
        {
            if(mPattern.size() == 0)
                throw new IllegalStateException("you must have a pattern to animate if you want to set the display mode to animate");
            mAnimatingPeriodStart = SystemClock.elapsedRealtime();
            displaymode = (Cell)mPattern.get(0);
            mInProgressX = getCenterXForColumn(displaymode.getColumn());
            mInProgressY = getCenterYForRow(displaymode.getRow());
            clearPatternDrawLookup();
        }
        invalidate();
    }

    public void setInStealthMode(boolean flag)
    {
        mInStealthMode = flag;
    }

    public void setOnPatternListener(OnPatternListener onpatternlistener)
    {
        mOnPatternListener = onpatternlistener;
    }

    public void setPattern(DisplayMode displaymode, List list)
    {
        mPattern.clear();
        mPattern.addAll(list);
        clearPatternDrawLookup();
        for(list = list.iterator(); list.hasNext();)
        {
            Cell cell = (Cell)list.next();
            mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
        }

        setDisplayMode(displaymode);
    }

    public void setTactileFeedbackEnabled(boolean flag)
    {
        mEnableHapticFeedback = flag;
    }

    public void startCellStateAnimation(CellState cellstate, float f, float f1, float f2, float f3, float f4, float f5, 
            long l, long l1, Interpolator interpolator, Runnable runnable)
    {
        if(isHardwareAccelerated())
            startCellStateAnimationHw(cellstate, f, f1, f2, f3, f4, f5, l, l1, interpolator, runnable);
        else
            startCellStateAnimationSw(cellstate, f, f1, f2, f3, f4, f5, l, l1, interpolator, runnable);
    }

    private static final int ASPECT_LOCK_HEIGHT = 2;
    private static final int ASPECT_LOCK_WIDTH = 1;
    private static final int ASPECT_SQUARE = 0;
    public static final boolean DEBUG_A11Y = false;
    private static final float DRAG_THRESHHOLD = 0F;
    private static final int MILLIS_PER_CIRCLE_ANIMATING = 700;
    private static final boolean PROFILE_DRAWING = false;
    private static final String TAG = "LockPatternView";
    public static final int VIRTUAL_BASE_VIEW_ID = 1;
    private long mAnimatingPeriodStart;
    private int mAspect;
    private AudioManager mAudioManager;
    private final CellState mCellStates[][];
    private final Path mCurrentPath;
    private final int mDotSize;
    private final int mDotSizeActivated;
    private boolean mDrawingProfilingStarted;
    private boolean mEnableHapticFeedback;
    private int mErrorColor;
    private PatternExploreByTouchHelper mExploreByTouchHelper;
    private final Interpolator mFastOutSlowInInterpolator;
    private float mHitFactor;
    private float mInProgressX;
    private float mInProgressY;
    private boolean mInStealthMode;
    private boolean mInputEnabled;
    private final Rect mInvalidate;
    private final Interpolator mLinearOutSlowInInterpolator;
    private Drawable mNotSelectedDrawable;
    private OnPatternListener mOnPatternListener;
    private final Paint mPaint;
    private final Paint mPathPaint;
    private final int mPathWidth;
    private final ArrayList mPattern;
    private DisplayMode mPatternDisplayMode;
    private final boolean mPatternDrawLookup[][];
    private boolean mPatternInProgress;
    private int mRegularColor;
    private Drawable mSelectedDrawable;
    private float mSquareHeight;
    private float mSquareWidth;
    private int mSuccessColor;
    private final Rect mTmpInvalidateRect;
    private boolean mUseLockPatternDrawable;
}
