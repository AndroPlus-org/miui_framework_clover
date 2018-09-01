// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import java.util.*;

// Referenced classes of package com.android.internal.widget:
//            LockPatternUtils

public class MiuiLockPatternView extends View
{
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


    public MiuiLockPatternView(Context context)
    {
        this(context, null);
    }

    public MiuiLockPatternView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mDrawingProfilingStarted = false;
        mPaint = new Paint();
        mPathPaint = new Paint();
        mPattern = new ArrayList(9);
        mPatternDrawLookup = new boolean[3][3];
        mInProgressX = -1F;
        mInProgressY = -1F;
        mPatternDisplayMode = LockPatternView.DisplayMode.Correct;
        mInputEnabled = true;
        mInStealthMode = false;
        mEnableHapticFeedback = true;
        mPatternInProgress = false;
        mDiameterFactor = 0.1F;
        mStrokeAlpha = 128;
        mHitFactor = 0.6F;
        mCurrentPath = new Path();
        mInvalidate = new Rect();
        mArrowMatrix = new Matrix();
        mCircleMatrix = new Matrix();
        context = context.obtainStyledAttributes(attributeset, android.miui.R.styleable.LockPatternView).getString(0);
        int i;
        if("square".equals(context))
            mAspect = 0;
        else
        if("lock_width".equals(context))
            mAspect = 1;
        else
        if("lock_height".equals(context))
            mAspect = 2;
        else
            mAspect = 0;
        setClickable(true);
        mPathPaintColor = getResources().getColor(0x11060003);
        mPathPaintErrorColor = getResources().getColor(0x11060004);
        mPathPaint.setAntiAlias(true);
        mPathPaint.setDither(true);
        mPathPaint.setAlpha(128);
        mPathPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mPathPaint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        mPathPaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        mBitmapBtnError = getBitmapFor(0x11020012);
        mBitmapBtnTouched = getBitmapFor(0x11020013);
        mBitmapCircleDefault = getBitmapFor(0x1102006a);
        mBitmapCircleGreen = getBitmapFor(0x1102006b);
        mBitmapCircleRed = getBitmapFor(0x1102006c);
        mBitmapArrowGreenUp = getBitmapFor(0x11020068);
        mBitmapArrowRedUp = getBitmapFor(0x11020069);
        attributeset = new Bitmap[5];
        attributeset[0] = mBitmapBtnError;
        attributeset[1] = mBitmapBtnTouched;
        attributeset[2] = mBitmapCircleDefault;
        attributeset[3] = mBitmapCircleGreen;
        attributeset[4] = mBitmapCircleRed;
        i = attributeset.length;
        for(int j = 0; j < i; j++)
        {
            context = attributeset[j];
            mBitmapWidth = Math.max(mBitmapWidth, context.getWidth());
            mBitmapHeight = Math.max(mBitmapHeight, context.getHeight());
        }

    }

    private void addCellToPattern(LockPatternView.Cell cell)
    {
        mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
        mPattern.add(cell);
        notifyCellAdded();
    }

    private LockPatternView.Cell checkForNewHit(float f, float f1)
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
            return LockPatternView.Cell.of(i, j);
    }

    private void clearPatternDrawLookup()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
                mPatternDrawLookup[i][j] = false;

        }

    }

    private LockPatternView.Cell detectAndAddHit(float f, float f1)
    {
        byte byte0 = -1;
        LockPatternView.Cell cell = checkForNewHit(f, f1);
        if(cell != null)
        {
            LockPatternView.Cell cell1 = null;
            ArrayList arraylist = mPattern;
            if(!arraylist.isEmpty())
            {
                cell1 = (LockPatternView.Cell)arraylist.get(arraylist.size() - 1);
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
                cell1 = LockPatternView.Cell.of(j1, l);
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

    private void drawArrow(Canvas canvas, float f, float f1, LockPatternView.Cell cell, LockPatternView.Cell cell1)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        float f2;
        float f3;
        float f4;
        if(mPatternDisplayMode != LockPatternView.DisplayMode.Wrong)
            i = 1;
        else
            i = 0;
        j = cell1.row;
        k = cell.row;
        l = cell1.column;
        i1 = cell.column;
        j1 = ((int)mSquareWidth - mBitmapWidth) / 2;
        k1 = ((int)mSquareHeight - mBitmapHeight) / 2;
        if(i != 0)
            cell = mBitmapArrowGreenUp;
        else
            cell = mBitmapArrowRedUp;
        l1 = mBitmapWidth;
        i = mBitmapHeight;
        f2 = (float)Math.toDegrees((float)Math.atan2(j - k, l - i1));
        f3 = Math.min(mSquareWidth / (float)mBitmapWidth, 1.0F);
        f4 = Math.min(mSquareHeight / (float)mBitmapHeight, 1.0F);
        mArrowMatrix.setTranslate((float)j1 + f, (float)k1 + f1);
        mArrowMatrix.preTranslate(mBitmapWidth / 2, mBitmapHeight / 2);
        mArrowMatrix.preScale(f3, f4);
        mArrowMatrix.preTranslate(-mBitmapWidth / 2, -mBitmapHeight / 2);
        mArrowMatrix.preRotate(f2 + 90F, (float)l1 / 2.0F, (float)i / 2.0F);
        mArrowMatrix.preTranslate((float)(l1 - cell.getWidth()) / 2.0F, 0.0F);
        canvas.drawBitmap(cell, mArrowMatrix, mPaint);
    }

    private void drawCircle(Canvas canvas, int i, int j, boolean flag)
    {
        Bitmap bitmap;
        Bitmap bitmap1;
        int k;
        int l;
        float f;
        float f1;
        if(!flag || mInStealthMode && mPatternDisplayMode != LockPatternView.DisplayMode.Wrong)
        {
            bitmap = mBitmapCircleDefault;
            bitmap1 = null;
        } else
        if(mPatternInProgress)
        {
            bitmap = mBitmapCircleGreen;
            bitmap1 = mBitmapBtnTouched;
        } else
        if(mPatternDisplayMode == LockPatternView.DisplayMode.Wrong)
        {
            bitmap = mBitmapCircleRed;
            bitmap1 = mBitmapBtnError;
        } else
        if(mPatternDisplayMode == LockPatternView.DisplayMode.Correct || mPatternDisplayMode == LockPatternView.DisplayMode.Animate)
        {
            bitmap = mBitmapCircleGreen;
            bitmap1 = null;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("unknown display mode ").append(mPatternDisplayMode).toString());
        }
        k = mBitmapWidth;
        l = mBitmapHeight;
        f = mSquareWidth;
        f1 = mSquareHeight;
        k = (int)((f - (float)k) / 2.0F);
        l = (int)((f1 - (float)l) / 2.0F);
        f1 = Math.min(mSquareWidth / (float)mBitmapWidth, 1.0F);
        f = Math.min(mSquareHeight / (float)mBitmapHeight, 1.0F);
        mCircleMatrix.setTranslate(i + k, j + l);
        mCircleMatrix.preTranslate(mBitmapWidth / 2, mBitmapHeight / 2);
        mCircleMatrix.preScale(f1, f);
        mCircleMatrix.preTranslate(-mBitmapWidth / 2, -mBitmapHeight / 2);
        canvas.drawBitmap(bitmap, mCircleMatrix, mPaint);
        if(bitmap1 != null)
            canvas.drawBitmap(bitmap1, mCircleMatrix, mPaint);
    }

    private Bitmap getBitmapFor(int i)
    {
        return BitmapFactory.decodeResource(getContext().getResources(), i);
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
        resetPattern();
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        motionevent = detectAndAddHit(f, f1);
        if(motionevent != null)
        {
            mPatternInProgress = true;
            mPatternDisplayMode = LockPatternView.DisplayMode.Correct;
            notifyPatternStarted();
        } else
        {
            mPatternInProgress = false;
            notifyPatternCleared();
        }
        if(motionevent != null)
        {
            float f2 = getCenterXForColumn(((LockPatternView.Cell) (motionevent)).column);
            float f3 = getCenterYForRow(((LockPatternView.Cell) (motionevent)).row);
            float f4 = mSquareWidth / 2.0F;
            float f5 = mSquareHeight / 2.0F;
            invalidate((int)(f2 - f4), (int)(f3 - f5), (int)(f2 + f4), (int)(f3 + f5));
        }
        mInProgressX = f;
        mInProgressY = f1;
    }

    private void handleActionMove(MotionEvent motionevent)
    {
        int i = motionevent.getHistorySize();
        int j = 0;
        while(j < i + 1) 
        {
            float f;
            float f1;
            int k;
            LockPatternView.Cell cell;
            int l;
            if(j < i)
                f = motionevent.getHistoricalX(j);
            else
                f = motionevent.getX();
            if(j < i)
                f1 = motionevent.getHistoricalY(j);
            else
                f1 = motionevent.getY();
            k = mPattern.size();
            cell = detectAndAddHit(f, f1);
            l = mPattern.size();
            if(cell != null && l == 1)
            {
                mPatternInProgress = true;
                notifyPatternStarted();
            }
            if(Math.abs(f - mInProgressX) + Math.abs(f1 - mInProgressY) > mSquareWidth * 0.01F)
            {
                float f2 = mInProgressX;
                float f3 = mInProgressY;
                mInProgressX = f;
                mInProgressY = f1;
                if(mPatternInProgress && l > 0)
                {
                    ArrayList arraylist = mPattern;
                    float f8 = mSquareWidth * mDiameterFactor * 0.5F;
                    Object obj = (LockPatternView.Cell)arraylist.get(l - 1);
                    float f9 = getCenterXForColumn(((LockPatternView.Cell) (obj)).column);
                    float f10 = getCenterYForRow(((LockPatternView.Cell) (obj)).row);
                    obj = mInvalidate;
                    float f12;
                    float f13;
                    if(f9 < f)
                    {
                        float f11 = f9;
                        f13 = f;
                        f = f11;
                    } else
                    {
                        f13 = f9;
                    }
                    if(f10 < f1)
                    {
                        float f14 = f10;
                        f12 = f1;
                        f1 = f14;
                    } else
                    {
                        f12 = f10;
                    }
                    ((Rect) (obj)).set((int)(f - f8), (int)(f1 - f8), (int)(f13 + f8), (int)(f12 + f8));
                    if(f9 < f2)
                    {
                        f1 = f9;
                        f9 = f2;
                    } else
                    {
                        f1 = f2;
                    }
                    if(f10 < f3)
                    {
                        f = f3;
                        f3 = f10;
                        f10 = f;
                    }
                    ((Rect) (obj)).union((int)(f1 - f8), (int)(f3 - f8), (int)(f9 + f8), (int)(f10 + f8));
                    if(cell != null)
                    {
                        f9 = getCenterXForColumn(cell.column);
                        f10 = getCenterYForRow(cell.row);
                        if(l >= 2)
                        {
                            LockPatternView.Cell cell1 = (LockPatternView.Cell)arraylist.get(l - 1 - (l - k));
                            f1 = getCenterXForColumn(cell1.column);
                            f = getCenterYForRow(cell1.row);
                            float f5;
                            if(f9 >= f1)
                            {
                                float f6 = f9;
                                f9 = f1;
                                f1 = f6;
                            }
                            if(f10 < f)
                            {
                                float f4 = f10;
                                f10 = f;
                                f = f4;
                            }
                        } else
                        {
                            f1 = f9;
                            float f7 = f10;
                            f = f10;
                            f10 = f7;
                        }
                        f2 = mSquareWidth / 2.0F;
                        f5 = mSquareHeight / 2.0F;
                        ((Rect) (obj)).set((int)(f9 - f2), (int)(f - f5), (int)(f1 + f2), (int)(f10 + f5));
                    }
                    invalidate(((Rect) (obj)));
                } else
                {
                    invalidate();
                }
            }
            j++;
        }
    }

    private void handleActionUp(MotionEvent motionevent)
    {
        if(!mPattern.isEmpty())
        {
            mPatternInProgress = false;
            notifyPatternDetected();
            invalidate();
        }
    }

    private void notifyCellAdded()
    {
        if(mOnPatternListener != null)
            mOnPatternListener.onPatternCellAdded(mPattern);
        sendAccessEvent(0x11080081);
    }

    private void notifyPatternCleared()
    {
        if(mOnPatternListener != null)
            mOnPatternListener.onPatternCleared();
        sendAccessEvent(0x11080080);
    }

    private void notifyPatternDetected()
    {
        if(mOnPatternListener != null)
            mOnPatternListener.onPatternDetected(mPattern);
        sendAccessEvent(0x11080082);
    }

    private void notifyPatternStarted()
    {
        if(mOnPatternListener != null)
            mOnPatternListener.onPatternStart();
        sendAccessEvent(0x1108007f);
    }

    private void resetPattern()
    {
        mPattern.clear();
        clearPatternDrawLookup();
        mPatternDisplayMode = LockPatternView.DisplayMode.Correct;
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
        setContentDescription(mContext.getString(i));
        sendAccessibilityEvent(4);
        setContentDescription(null);
    }

    public void clearPattern()
    {
        resetPattern();
    }

    public void disableInput()
    {
        mInputEnabled = false;
    }

    public void enableInput()
    {
        mInputEnabled = true;
    }

    protected int getSuggestedMinimumHeight()
    {
        return mBitmapWidth * 3;
    }

    protected int getSuggestedMinimumWidth()
    {
        return mBitmapWidth * 3;
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
        int k;
        int l;
        int j1;
        Path path;
        float f6;
        float f9;
        int k1;
        Object obj;
        LockPatternView.Cell cell2;
        ArrayList arraylist = mPattern;
        int i = arraylist.size();
        boolean aflag[][] = mPatternDrawLookup;
        if(mPatternDisplayMode == LockPatternView.DisplayMode.Animate)
        {
            int j = (int)(SystemClock.elapsedRealtime() - mAnimatingPeriodStart) % ((i + 1) * 700);
            l = j / 700;
            clearPatternDrawLookup();
            for(int i1 = 0; i1 < l; i1++)
            {
                LockPatternView.Cell cell = (LockPatternView.Cell)arraylist.get(i1);
                aflag[cell.getRow()][cell.getColumn()] = true;
            }

            float f2;
            float f4;
            if(l > 0)
            {
                if(l < i)
                    j1 = 1;
                else
                    j1 = 0;
            } else
            {
                j1 = 0;
            }
            if(j1 != 0)
            {
                float f = (float)(j % 700) / 700F;
                LockPatternView.Cell cell1 = (LockPatternView.Cell)arraylist.get(l - 1);
                float f1 = getCenterXForColumn(cell1.column);
                float f3 = getCenterYForRow(cell1.row);
                cell1 = (LockPatternView.Cell)arraylist.get(l);
                float f5 = getCenterXForColumn(cell1.column);
                float f8 = getCenterYForRow(cell1.row);
                mInProgressX = f1 + f * (f5 - f1);
                mInProgressY = f3 + f * (f8 - f3);
            }
            invalidate();
        }
        f9 = mSquareWidth;
        f6 = mSquareHeight;
        f4 = mDiameterFactor;
        mPathPaint.setStrokeWidth(f9 * f4);
        path = mCurrentPath;
        path.rewind();
        l = mPaddingTop;
        k1 = mPaddingLeft;
        for(j1 = 0; j1 < 3; j1++)
        {
            f2 = l;
            f4 = j1;
            for(j = 0; j < 3; j++)
                drawCircle(canvas, (int)((float)k1 + (float)j * f9), (int)(f2 + f4 * f6), aflag[j1][j]);

        }

        boolean flag;
        if(!mInStealthMode || mPatternDisplayMode == LockPatternView.DisplayMode.Wrong)
            j1 = 1;
        else
            j1 = 0;
        if((mPaint.getFlags() & 2) != 0)
            flag = true;
        else
            flag = false;
        mPaint.setFilterBitmap(true);
        obj = mPathPaint;
        if(mPatternDisplayMode == LockPatternView.DisplayMode.Wrong)
            k = mPathPaintErrorColor;
        else
            k = mPathPaintColor;
        ((Paint) (obj)).setColor(k);
        if(j1 == 0) goto _L2; else goto _L1
_L1:
        k = 0;
_L7:
        if(k >= i - 1) goto _L2; else goto _L3
_L3:
        cell2 = (LockPatternView.Cell)arraylist.get(k);
        obj = (LockPatternView.Cell)arraylist.get(k + 1);
        if(aflag[((LockPatternView.Cell) (obj)).row][((LockPatternView.Cell) (obj)).column]) goto _L4; else goto _L2
_L2:
        if(j1 == 0) goto _L6; else goto _L5
_L5:
        k = 0;
        j1 = 0;
_L8:
        if(j1 < i)
        {
            obj = (LockPatternView.Cell)arraylist.get(j1);
            if(aflag[((LockPatternView.Cell) (obj)).row][((LockPatternView.Cell) (obj)).column])
                break MISSING_BLOCK_LABEL_662;
        }
        if((mPatternInProgress || mPatternDisplayMode == LockPatternView.DisplayMode.Animate) && k != 0)
            path.lineTo(mInProgressX, mInProgressY);
        canvas.drawPath(path, mPathPaint);
_L6:
        mPaint.setFilterBitmap(flag);
        return;
_L4:
        drawArrow(canvas, (float)k1 + (float)cell2.column * f9, (float)l + (float)cell2.row * f6, cell2, ((LockPatternView.Cell) (obj)));
        k++;
          goto _L7
        k = 1;
        float f10 = getCenterXForColumn(((LockPatternView.Cell) (obj)).column);
        float f7 = getCenterYForRow(((LockPatternView.Cell) (obj)).row);
        if(j1 == 0)
            path.moveTo(f10, f7);
        else
            path.lineTo(f10, f7);
        j1++;
          goto _L8
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
        setPattern(LockPatternView.DisplayMode.Correct, LockPatternUtils.stringToPattern(parcelable.getSerializedPattern()));
        mPatternDisplayMode = LockPatternView.DisplayMode.values()[parcelable.getDisplayMode()];
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
        mSquareWidth = (float)(i - mPaddingLeft - mPaddingRight) / 3F;
        mSquareHeight = (float)(j - mPaddingTop - mPaddingBottom) / 3F;
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
            handleActionUp(motionevent);
            return true;

        case 2: // '\002'
            handleActionMove(motionevent);
            return true;

        case 3: // '\003'
            resetPattern();
            break;
        }
        mPatternInProgress = false;
        notifyPatternCleared();
        return true;
    }

    public void setDisplayMode(LockPatternView.DisplayMode displaymode)
    {
        mPatternDisplayMode = displaymode;
        if(displaymode == LockPatternView.DisplayMode.Animate)
        {
            if(mPattern.size() == 0)
                throw new IllegalStateException("you must have a pattern to animate if you want to set the display mode to animate");
            mAnimatingPeriodStart = SystemClock.elapsedRealtime();
            displaymode = (LockPatternView.Cell)mPattern.get(0);
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

    public void setOnPatternListener(LockPatternView.OnPatternListener onpatternlistener)
    {
        mOnPatternListener = onpatternlistener;
    }

    public void setPattern(LockPatternView.DisplayMode displaymode, List list)
    {
        mPattern.clear();
        mPattern.addAll(list);
        clearPatternDrawLookup();
        for(list = list.iterator(); list.hasNext();)
        {
            LockPatternView.Cell cell = (LockPatternView.Cell)list.next();
            mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
        }

        setDisplayMode(displaymode);
    }

    public void setTactileFeedbackEnabled(boolean flag)
    {
        mEnableHapticFeedback = flag;
    }

    private static final int ASPECT_LOCK_HEIGHT = 2;
    private static final int ASPECT_LOCK_WIDTH = 1;
    private static final int ASPECT_SQUARE = 0;
    private static final int MILLIS_PER_CIRCLE_ANIMATING = 700;
    private static final boolean PROFILE_DRAWING = false;
    static final int STATUS_BAR_HEIGHT = 25;
    private static final String TAG = "LockPatternView";
    private long mAnimatingPeriodStart;
    private final Matrix mArrowMatrix;
    private int mAspect;
    private Bitmap mBitmapArrowGreenUp;
    private Bitmap mBitmapArrowRedUp;
    private Bitmap mBitmapBtnError;
    private Bitmap mBitmapBtnTouched;
    private Bitmap mBitmapCircleDefault;
    private Bitmap mBitmapCircleGreen;
    private Bitmap mBitmapCircleRed;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private final Matrix mCircleMatrix;
    private final Path mCurrentPath;
    private float mDiameterFactor;
    private boolean mDrawingProfilingStarted;
    private boolean mEnableHapticFeedback;
    private float mHitFactor;
    private float mInProgressX;
    private float mInProgressY;
    private boolean mInStealthMode;
    private boolean mInputEnabled;
    private final Rect mInvalidate;
    private LockPatternView.OnPatternListener mOnPatternListener;
    private Paint mPaint;
    private Paint mPathPaint;
    private int mPathPaintColor;
    private int mPathPaintErrorColor;
    private ArrayList mPattern;
    private LockPatternView.DisplayMode mPatternDisplayMode;
    private boolean mPatternDrawLookup[][];
    private boolean mPatternInProgress;
    private float mSquareHeight;
    private float mSquareWidth;
    private final int mStrokeAlpha;
}
