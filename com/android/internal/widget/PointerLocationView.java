// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.hardware.input.InputManager;
import android.os.SystemProperties;
import android.util.*;
import android.view.*;
import java.util.ArrayList;

public class PointerLocationView extends View
    implements android.hardware.input.InputManager.InputDeviceListener, android.view.WindowManagerPolicy.PointerEventListener
{
    private static final class FasterStringBuilder
    {

        private int reserve(int i)
        {
            int j = mLength;
            int k = mLength;
            char ac[] = mChars;
            int l = ac.length;
            if(k + i > l)
            {
                char ac1[] = new char[l * 2];
                System.arraycopy(ac, 0, ac1, 0, j);
                mChars = ac1;
            }
            return j;
        }

        public FasterStringBuilder append(float f, int i)
        {
            int j = 1;
            for(int k = 0; k < i; k++)
                j *= 10;

            f = (float)(Math.rint((float)j * f) / (double)j);
            append((int)f);
            if(i != 0)
            {
                append(".");
                f = Math.abs(f);
                f = (float)((double)f - Math.floor(f));
                append((int)((float)j * f), i);
            }
            return this;
        }

        public FasterStringBuilder append(int i)
        {
            return append(i, 0);
        }

        public FasterStringBuilder append(int i, int j)
        {
            boolean flag;
            int l;
            if(i < 0)
                flag = true;
            else
                flag = false;
            l = i;
            if(flag)
            {
                i = -i;
                l = i;
                if(i < 0)
                {
                    append("-2147483648");
                    return this;
                }
            }
            int i1 = reserve(11);
            char ac[] = mChars;
            if(l == 0)
            {
                ac[i1] = (char)48;
                mLength = mLength + 1;
                return this;
            }
            i = i1;
            if(flag)
            {
                ac[i1] = (char)45;
                i = i1 + 1;
            }
            int k = 0x3b9aca00;
            int j1 = 10;
            int k1;
            int l1;
            do
            {
                k1 = k;
                i1 = i;
                l1 = l;
                if(l >= k)
                    break;
                k /= 10;
                if(--j1 < j)
                {
                    i1 = i + 1;
                    ac[i] = (char)48;
                    i = i1;
                }
            } while(true);
            do
            {
                j = l1 / k1;
                l1 -= j * k1;
                k1 /= 10;
                i = i1 + 1;
                ac[i1] = (char)(j + 48);
                if(k1 != 0)
                {
                    i1 = i;
                } else
                {
                    mLength = i;
                    return this;
                }
            } while(true);
        }

        public FasterStringBuilder append(String s)
        {
            int i = s.length();
            int j = reserve(i);
            s.getChars(0, i, mChars, j);
            mLength = mLength + i;
            return this;
        }

        public FasterStringBuilder clear()
        {
            mLength = 0;
            return this;
        }

        public String toString()
        {
            return new String(mChars, 0, mLength);
        }

        private char mChars[];
        private int mLength;

        public FasterStringBuilder()
        {
            mChars = new char[64];
        }
    }

    public static class PointerState
    {

        static android.view.VelocityTracker.Estimator _2D_get0(PointerState pointerstate)
        {
            return pointerstate.mAltEstimator;
        }

        static float _2D_get1(PointerState pointerstate)
        {
            return pointerstate.mAltXVelocity;
        }

        static boolean _2D_get10(PointerState pointerstate)
        {
            return pointerstate.mHasBoundingBox;
        }

        static int _2D_get11(PointerState pointerstate)
        {
            return pointerstate.mToolType;
        }

        static int _2D_get12(PointerState pointerstate)
        {
            return pointerstate.mTraceCount;
        }

        static boolean[] _2D_get13(PointerState pointerstate)
        {
            return pointerstate.mTraceCurrent;
        }

        static float[] _2D_get14(PointerState pointerstate)
        {
            return pointerstate.mTraceX;
        }

        static float[] _2D_get15(PointerState pointerstate)
        {
            return pointerstate.mTraceY;
        }

        static float _2D_get16(PointerState pointerstate)
        {
            return pointerstate.mXVelocity;
        }

        static float _2D_get17(PointerState pointerstate)
        {
            return pointerstate.mYVelocity;
        }

        static float _2D_get2(PointerState pointerstate)
        {
            return pointerstate.mAltYVelocity;
        }

        static float _2D_get3(PointerState pointerstate)
        {
            return pointerstate.mBoundingBottom;
        }

        static float _2D_get4(PointerState pointerstate)
        {
            return pointerstate.mBoundingLeft;
        }

        static float _2D_get5(PointerState pointerstate)
        {
            return pointerstate.mBoundingRight;
        }

        static float _2D_get6(PointerState pointerstate)
        {
            return pointerstate.mBoundingTop;
        }

        static android.view.MotionEvent.PointerCoords _2D_get7(PointerState pointerstate)
        {
            return pointerstate.mCoords;
        }

        static boolean _2D_get8(PointerState pointerstate)
        {
            return pointerstate.mCurDown;
        }

        static android.view.VelocityTracker.Estimator _2D_get9(PointerState pointerstate)
        {
            return pointerstate.mEstimator;
        }

        static float _2D_set0(PointerState pointerstate, float f)
        {
            pointerstate.mAltXVelocity = f;
            return f;
        }

        static float _2D_set1(PointerState pointerstate, float f)
        {
            pointerstate.mAltYVelocity = f;
            return f;
        }

        static float _2D_set10(PointerState pointerstate, float f)
        {
            pointerstate.mYVelocity = f;
            return f;
        }

        static float _2D_set2(PointerState pointerstate, float f)
        {
            pointerstate.mBoundingBottom = f;
            return f;
        }

        static float _2D_set3(PointerState pointerstate, float f)
        {
            pointerstate.mBoundingLeft = f;
            return f;
        }

        static float _2D_set4(PointerState pointerstate, float f)
        {
            pointerstate.mBoundingRight = f;
            return f;
        }

        static float _2D_set5(PointerState pointerstate, float f)
        {
            pointerstate.mBoundingTop = f;
            return f;
        }

        static boolean _2D_set6(PointerState pointerstate, boolean flag)
        {
            pointerstate.mCurDown = flag;
            return flag;
        }

        static boolean _2D_set7(PointerState pointerstate, boolean flag)
        {
            pointerstate.mHasBoundingBox = flag;
            return flag;
        }

        static int _2D_set8(PointerState pointerstate, int i)
        {
            pointerstate.mToolType = i;
            return i;
        }

        static float _2D_set9(PointerState pointerstate, float f)
        {
            pointerstate.mXVelocity = f;
            return f;
        }

        public void addTrace(float f, float f1, boolean flag)
        {
            int i = mTraceX.length;
            if(mTraceCount == i)
            {
                i *= 2;
                float af[] = new float[i];
                System.arraycopy(mTraceX, 0, af, 0, mTraceCount);
                mTraceX = af;
                af = new float[i];
                System.arraycopy(mTraceY, 0, af, 0, mTraceCount);
                mTraceY = af;
                af = new boolean[i];
                System.arraycopy(mTraceCurrent, 0, af, 0, mTraceCount);
                mTraceCurrent = af;
            }
            mTraceX[mTraceCount] = f;
            mTraceY[mTraceCount] = f1;
            mTraceCurrent[mTraceCount] = flag;
            mTraceCount = mTraceCount + 1;
        }

        public void clearTrace()
        {
            mTraceCount = 0;
        }

        private android.view.VelocityTracker.Estimator mAltEstimator;
        private float mAltXVelocity;
        private float mAltYVelocity;
        private float mBoundingBottom;
        private float mBoundingLeft;
        private float mBoundingRight;
        private float mBoundingTop;
        private android.view.MotionEvent.PointerCoords mCoords;
        private boolean mCurDown;
        private android.view.VelocityTracker.Estimator mEstimator;
        private boolean mHasBoundingBox;
        private int mToolType;
        private int mTraceCount;
        private boolean mTraceCurrent[];
        private float mTraceX[];
        private float mTraceY[];
        private float mXVelocity;
        private float mYVelocity;

        public PointerState()
        {
            mTraceX = new float[32];
            mTraceY = new float[32];
            mTraceCurrent = new boolean[32];
            mCoords = new android.view.MotionEvent.PointerCoords();
            mEstimator = new android.view.VelocityTracker.Estimator();
            mAltEstimator = new android.view.VelocityTracker.Estimator();
        }
    }


    public PointerLocationView(Context context)
    {
        super(context);
        mPrintCoords = true;
        mReusableOvalRect = new RectF();
        setFocusableInTouchMode(true);
        mIm = (InputManager)context.getSystemService(android/hardware/input/InputManager);
        mVC = ViewConfiguration.get(context);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(getResources().getDisplayMetrics().density * 10F);
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextBackgroundPaint.setAntiAlias(false);
        mTextBackgroundPaint.setARGB(128, 255, 255, 255);
        mTextLevelPaint.setAntiAlias(false);
        mTextLevelPaint.setARGB(192, 255, 0, 0);
        mPaint.setAntiAlias(true);
        mPaint.setARGB(255, 255, 255, 255);
        mPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mPaint.setStrokeWidth(2.0F);
        mCurrentPointPaint.setAntiAlias(true);
        mCurrentPointPaint.setARGB(255, 255, 0, 0);
        mCurrentPointPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mCurrentPointPaint.setStrokeWidth(2.0F);
        mTargetPaint.setAntiAlias(false);
        mTargetPaint.setARGB(255, 0, 0, 192);
        mPathPaint.setAntiAlias(false);
        mPathPaint.setARGB(255, 0, 96, 255);
        mPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mPaint.setStrokeWidth(1.0F);
        context = new PointerState();
        mPointers.add(context);
        mActivePointerId = 0;
        context = SystemProperties.get("debug.velocitytracker.alt");
        if(context.length() != 0)
        {
            Log.d("Pointer", (new StringBuilder()).append("Comparing default velocity tracker strategy with ").append(context).toString());
            mAltVelocity = VelocityTracker.obtain(context);
        } else
        {
            mAltVelocity = null;
        }
    }

    private void drawOval(Canvas canvas, float f, float f1, float f2, float f3, float f4, Paint paint)
    {
        canvas.save(1);
        canvas.rotate((float)((double)(180F * f4) / 3.1415926535897931D), f, f1);
        mReusableOvalRect.left = f - f3 / 2.0F;
        mReusableOvalRect.right = f3 / 2.0F + f;
        mReusableOvalRect.top = f1 - f2 / 2.0F;
        mReusableOvalRect.bottom = f2 / 2.0F + f1;
        canvas.drawOval(mReusableOvalRect, paint);
        canvas.restore();
    }

    private void logCoords(String s, int i, int j, android.view.MotionEvent.PointerCoords pointercoords, int k, MotionEvent motionevent)
    {
        int l;
        int i1;
        l = motionevent.getToolType(j);
        i1 = motionevent.getButtonState();
        i & 0xff;
        JVM INSTR tableswitch 0 10: default 80
    //                   0 472
    //                   1 480
    //                   2 488
    //                   3 496
    //                   4 504
    //                   5 512
    //                   6 540
    //                   7 568
    //                   8 592
    //                   9 576
    //                   10 584;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        String s1 = Integer.toString(i);
_L14:
        Log.i("Pointer", mText.clear().append(s).append(" id ").append(k + 1).append(": ").append(s1).append(" (").append(pointercoords.x, 3).append(", ").append(pointercoords.y, 3).append(") Pressure=").append(pointercoords.pressure, 3).append(" Size=").append(pointercoords.size, 3).append(" TouchMajor=").append(pointercoords.touchMajor, 3).append(" TouchMinor=").append(pointercoords.touchMinor, 3).append(" ToolMajor=").append(pointercoords.toolMajor, 3).append(" ToolMinor=").append(pointercoords.toolMinor, 3).append(" Orientation=").append((float)((double)(pointercoords.orientation * 180F) / 3.1415926535897931D), 1).append("deg").append(" Tilt=").append((float)((double)(pointercoords.getAxisValue(25) * 180F) / 3.1415926535897931D), 1).append("deg").append(" Distance=").append(pointercoords.getAxisValue(24), 1).append(" VScroll=").append(pointercoords.getAxisValue(9), 1).append(" HScroll=").append(pointercoords.getAxisValue(10), 1).append(" BoundingBox=[(").append(motionevent.getAxisValue(32), 3).append(", ").append(motionevent.getAxisValue(33), 3).append(")").append(", (").append(motionevent.getAxisValue(34), 3).append(", ").append(motionevent.getAxisValue(35), 3).append(")]").append(" ToolType=").append(MotionEvent.toolTypeToString(l)).append(" ButtonState=").append(MotionEvent.buttonStateToString(i1)).toString());
        return;
_L2:
        s1 = "DOWN";
        continue; /* Loop/switch isn't completed */
_L3:
        s1 = "UP";
        continue; /* Loop/switch isn't completed */
_L4:
        s1 = "MOVE";
        continue; /* Loop/switch isn't completed */
_L5:
        s1 = "CANCEL";
        continue; /* Loop/switch isn't completed */
_L6:
        s1 = "OUTSIDE";
        continue; /* Loop/switch isn't completed */
_L7:
        if(j == (0xff00 & i) >> 8)
            s1 = "DOWN";
        else
            s1 = "MOVE";
        continue; /* Loop/switch isn't completed */
_L8:
        if(j == (0xff00 & i) >> 8)
            s1 = "UP";
        else
            s1 = "MOVE";
        continue; /* Loop/switch isn't completed */
_L9:
        s1 = "HOVER MOVE";
        continue; /* Loop/switch isn't completed */
_L11:
        s1 = "HOVER ENTER";
        continue; /* Loop/switch isn't completed */
_L12:
        s1 = "HOVER EXIT";
        continue; /* Loop/switch isn't completed */
_L10:
        s1 = "SCROLL";
        if(true) goto _L14; else goto _L13
_L13:
    }

    private void logInputDeviceState(int i, String s)
    {
        InputDevice inputdevice = mIm.getInputDevice(i);
        if(inputdevice != null)
            Log.i("Pointer", (new StringBuilder()).append(s).append(": ").append(inputdevice).toString());
        else
            Log.i("Pointer", (new StringBuilder()).append(s).append(": ").append(i).toString());
    }

    private void logInputDevices()
    {
        int ai[] = InputDevice.getDeviceIds();
        for(int i = 0; i < ai.length; i++)
            logInputDeviceState(ai[i], "Device Enumerated");

    }

    private void logMotionEvent(String s, MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        int j = motionevent.getHistorySize();
        int k = motionevent.getPointerCount();
        for(int l = 0; l < j; l++)
        {
            for(int j1 = 0; j1 < k; j1++)
            {
                int l1 = motionevent.getPointerId(j1);
                motionevent.getHistoricalPointerCoords(j1, l, mTempCoords);
                logCoords(s, i, j1, mTempCoords, l1, motionevent);
            }

        }

        for(int i1 = 0; i1 < k; i1++)
        {
            int k1 = motionevent.getPointerId(i1);
            motionevent.getPointerCoords(i1, mTempCoords);
            logCoords(s, i, i1, mTempCoords, k1, motionevent);
        }

    }

    private static boolean shouldLogKey(int i)
    {
        boolean flag = true;
        switch(i)
        {
        default:
            if(!KeyEvent.isGamepadButton(i))
                flag = KeyEvent.isModifierKey(i);
            return flag;

        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
            return true;
        }
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mIm.registerInputDeviceListener(this, getHandler());
        logInputDevices();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mIm.unregisterInputDeviceListener(this);
    }

    protected void onDraw(Canvas canvas)
    {
        int i = getWidth();
        int k = i / 7;
        int l = -mTextMetrics.ascent + 1;
        int i1 = mHeaderBottom;
        int j1 = mPointers.size();
        PointerState pointerstate;
        int k1;
        if(mActivePointerId >= 0)
        {
            pointerstate = (PointerState)mPointers.get(mActivePointerId);
            canvas.drawRect(0.0F, 0.0F, k - 1, i1, mTextBackgroundPaint);
            canvas.drawText(mText.clear().append("P: ").append(mCurNumPointers).append(" / ").append(mMaxNumPointers).toString(), 1.0F, l, mTextPaint);
            k1 = PointerState._2D_get12(pointerstate);
            if(mCurDown && PointerState._2D_get8(pointerstate) || k1 == 0)
            {
                canvas.drawRect(k, 0.0F, k * 2 - 1, i1, mTextBackgroundPaint);
                canvas.drawText(mText.clear().append("X: ").append(PointerState._2D_get7(pointerstate).x, 1).toString(), k + 1, l, mTextPaint);
                canvas.drawRect(k * 2, 0.0F, k * 3 - 1, i1, mTextBackgroundPaint);
                canvas.drawText(mText.clear().append("Y: ").append(PointerState._2D_get7(pointerstate).y, 1).toString(), k * 2 + 1, l, mTextPaint);
            } else
            {
                f2 = PointerState._2D_get14(pointerstate)[k1 - 1] - PointerState._2D_get14(pointerstate)[0];
                f = PointerState._2D_get15(pointerstate)[k1 - 1] - PointerState._2D_get15(pointerstate)[0];
                f5 = k;
                float f9 = k * 2 - 1;
                f1 = i1;
                Paint paint;
                if(Math.abs(f2) < (float)mVC.getScaledTouchSlop())
                    paint = mTextBackgroundPaint;
                else
                    paint = mTextLevelPaint;
                canvas.drawRect(f5, 0.0F, f9, f1, paint);
                canvas.drawText(mText.clear().append("dX: ").append(f2, 1).toString(), k + 1, l, mTextPaint);
                f2 = k * 2;
                f1 = k * 3 - 1;
                f5 = i1;
                if(Math.abs(f) < (float)mVC.getScaledTouchSlop())
                    paint = mTextBackgroundPaint;
                else
                    paint = mTextLevelPaint;
                canvas.drawRect(f2, 0.0F, f1, f5, paint);
                canvas.drawText(mText.clear().append("dY: ").append(f, 1).toString(), k * 2 + 1, l, mTextPaint);
            }
            canvas.drawRect(k * 3, 0.0F, k * 4 - 1, i1, mTextBackgroundPaint);
            canvas.drawText(mText.clear().append("Xv: ").append(PointerState._2D_get16(pointerstate), 3).toString(), k * 3 + 1, l, mTextPaint);
            canvas.drawRect(k * 4, 0.0F, k * 5 - 1, i1, mTextBackgroundPaint);
            canvas.drawText(mText.clear().append("Yv: ").append(PointerState._2D_get17(pointerstate), 3).toString(), k * 4 + 1, l, mTextPaint);
            canvas.drawRect(k * 5, 0.0F, k * 6 - 1, i1, mTextBackgroundPaint);
            canvas.drawRect(k * 5, 0.0F, ((float)(k * 5) + PointerState._2D_get7(pointerstate).pressure * (float)k) - 1.0F, i1, mTextLevelPaint);
            canvas.drawText(mText.clear().append("Prs: ").append(PointerState._2D_get7(pointerstate).pressure, 2).toString(), k * 5 + 1, l, mTextPaint);
            canvas.drawRect(k * 6, 0.0F, i, i1, mTextBackgroundPaint);
            canvas.drawRect(k * 6, 0.0F, ((float)(k * 6) + PointerState._2D_get7(pointerstate).size * (float)k) - 1.0F, i1, mTextLevelPaint);
            canvas.drawText(mText.clear().append("Size: ").append(PointerState._2D_get7(pointerstate).size, 2).toString(), k * 6 + 1, l, mTextPaint);
        }
        i1 = 0;
        while(i1 < j1) 
        {
            pointerstate = (PointerState)mPointers.get(i1);
            l = PointerState._2D_get12(pointerstate);
            float f = 0.0F;
            float f1 = 0.0F;
            k = 0;
            k1 = 0;
            mPaint.setARGB(255, 128, 255, 255);
            int j = 0;
            while(j < l) 
            {
                float f2 = PointerState._2D_get14(pointerstate)[j];
                float f5 = PointerState._2D_get15(pointerstate)[j];
                if(Float.isNaN(f2))
                {
                    k = 0;
                } else
                {
                    if(k != 0)
                    {
                        canvas.drawLine(f, f1, f2, f5, mPathPaint);
                        Paint paint1;
                        if(PointerState._2D_get13(pointerstate)[j])
                            paint1 = mCurrentPointPaint;
                        else
                            paint1 = mPaint;
                        canvas.drawPoint(f, f1, paint1);
                        k1 = 1;
                    }
                    f = f2;
                    f1 = f5;
                    k = 1;
                }
                j++;
            }
            if(k1 != 0)
            {
                mPaint.setARGB(128, 128, 0, 128);
                float f3 = PointerState._2D_get9(pointerstate).estimateX(-0.08F);
                float f6 = PointerState._2D_get9(pointerstate).estimateY(-0.08F);
                for(k1 = -3; k1 <= 2; k1++)
                {
                    float f12 = PointerState._2D_get9(pointerstate).estimateX((float)k1 * 0.02F);
                    float f10 = PointerState._2D_get9(pointerstate).estimateY((float)k1 * 0.02F);
                    canvas.drawLine(f3, f6, f12, f10, mPaint);
                    f3 = f12;
                    f6 = f10;
                }

                mPaint.setARGB(255, 255, 64, 128);
                canvas.drawLine(f, f1, f + PointerState._2D_get16(pointerstate) * 16F, f1 + PointerState._2D_get17(pointerstate) * 16F, mPaint);
                if(mAltVelocity != null)
                {
                    mPaint.setARGB(128, 0, 128, 128);
                    float f4 = PointerState._2D_get0(pointerstate).estimateX(-0.08F);
                    float f7 = PointerState._2D_get0(pointerstate).estimateY(-0.08F);
                    for(k1 = -3; k1 <= 2; k1++)
                    {
                        float f13 = PointerState._2D_get0(pointerstate).estimateX((float)k1 * 0.02F);
                        float f11 = PointerState._2D_get0(pointerstate).estimateY((float)k1 * 0.02F);
                        canvas.drawLine(f4, f7, f13, f11, mPaint);
                        f4 = f13;
                        f7 = f11;
                    }

                    mPaint.setARGB(255, 64, 255, 128);
                    canvas.drawLine(f, f1, f + PointerState._2D_get1(pointerstate) * 16F, f1 + PointerState._2D_get2(pointerstate) * 16F, mPaint);
                }
            }
            if(!mCurDown || !PointerState._2D_get8(pointerstate))
                continue;
            canvas.drawLine(0.0F, PointerState._2D_get7(pointerstate).y, getWidth(), PointerState._2D_get7(pointerstate).y, mTargetPaint);
            canvas.drawLine(PointerState._2D_get7(pointerstate).x, 0.0F, PointerState._2D_get7(pointerstate).x, getHeight(), mTargetPaint);
            k1 = (int)(PointerState._2D_get7(pointerstate).pressure * 255F);
            mPaint.setARGB(255, k1, 255, 255 - k1);
            canvas.drawPoint(PointerState._2D_get7(pointerstate).x, PointerState._2D_get7(pointerstate).y, mPaint);
            mPaint.setARGB(255, k1, 255 - k1, 128);
            drawOval(canvas, PointerState._2D_get7(pointerstate).x, PointerState._2D_get7(pointerstate).y, PointerState._2D_get7(pointerstate).touchMajor, PointerState._2D_get7(pointerstate).touchMinor, PointerState._2D_get7(pointerstate).orientation, mPaint);
            mPaint.setARGB(255, k1, 128, 255 - k1);
            drawOval(canvas, PointerState._2D_get7(pointerstate).x, PointerState._2D_get7(pointerstate).y, PointerState._2D_get7(pointerstate).toolMajor, PointerState._2D_get7(pointerstate).toolMinor, PointerState._2D_get7(pointerstate).orientation, mPaint);
            f1 = PointerState._2D_get7(pointerstate).toolMajor * 0.7F;
            f = f1;
            if(f1 < 20F)
                f = 20F;
            mPaint.setARGB(255, k1, 255, 0);
            f1 = (float)(Math.sin(PointerState._2D_get7(pointerstate).orientation) * (double)f);
            float f8 = (float)(-Math.cos(PointerState._2D_get7(pointerstate).orientation) * (double)f);
            if(PointerState._2D_get11(pointerstate) == 2 || PointerState._2D_get11(pointerstate) == 4)
                canvas.drawLine(PointerState._2D_get7(pointerstate).x, PointerState._2D_get7(pointerstate).y, PointerState._2D_get7(pointerstate).x + f1, PointerState._2D_get7(pointerstate).y + f8, mPaint);
            else
                canvas.drawLine(PointerState._2D_get7(pointerstate).x - f1, PointerState._2D_get7(pointerstate).y - f8, PointerState._2D_get7(pointerstate).x + f1, PointerState._2D_get7(pointerstate).y + f8, mPaint);
            f = (float)Math.sin(PointerState._2D_get7(pointerstate).getAxisValue(25));
            canvas.drawCircle(PointerState._2D_get7(pointerstate).x + f1 * f, PointerState._2D_get7(pointerstate).y + f8 * f, 3F, mPaint);
            if(PointerState._2D_get10(pointerstate))
                canvas.drawRect(PointerState._2D_get4(pointerstate), PointerState._2D_get6(pointerstate), PointerState._2D_get5(pointerstate), PointerState._2D_get3(pointerstate), mPaint);
            i1++;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        int i = motionevent.getSource();
        if((i & 2) != 0)
            onPointerEvent(motionevent);
        else
        if((i & 0x10) != 0)
            logMotionEvent("Joystick", motionevent);
        else
        if((i & 8) != 0)
            logMotionEvent("Position", motionevent);
        else
            logMotionEvent("Generic", motionevent);
        return true;
    }

    public void onInputDeviceAdded(int i)
    {
        logInputDeviceState(i, "Device Added");
    }

    public void onInputDeviceChanged(int i)
    {
        logInputDeviceState(i, "Device Changed");
    }

    public void onInputDeviceRemoved(int i)
    {
        logInputDeviceState(i, "Device Removed");
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(shouldLogKey(i))
        {
            i = keyevent.getRepeatCount();
            if(i == 0)
                Log.i("Pointer", (new StringBuilder()).append("Key Down: ").append(keyevent).toString());
            else
                Log.i("Pointer", (new StringBuilder()).append("Key Repeat #").append(i).append(": ").append(keyevent).toString());
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(shouldLogKey(i))
        {
            Log.i("Pointer", (new StringBuilder()).append("Key Up: ").append(keyevent).toString());
            return true;
        } else
        {
            return super.onKeyUp(i, keyevent);
        }
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        mTextPaint.getFontMetricsInt(mTextMetrics);
        mHeaderBottom = -mTextMetrics.ascent + mTextMetrics.descent + 2;
    }

    public void onPointerEvent(MotionEvent motionevent)
    {
        int i;
        int j1;
        int k2;
label0:
        {
            i = motionevent.getAction();
            int j = mPointers.size();
            if(i != 0)
            {
                j1 = j;
                if((i & 0xff) != 5)
                    break label0;
            }
            if(i == 0)
            {
                for(j1 = 0; j1 < j; j1++)
                {
                    PointerState pointerstate = (PointerState)mPointers.get(j1);
                    pointerstate.clearTrace();
                    PointerState._2D_set6(pointerstate, false);
                }

                mCurDown = true;
                mCurNumPointers = 0;
                mMaxNumPointers = 0;
                mVelocity.clear();
                if(mAltVelocity != null)
                    mAltVelocity.clear();
            }
            mCurNumPointers = mCurNumPointers + 1;
            if(mMaxNumPointers < mCurNumPointers)
                mMaxNumPointers = mCurNumPointers;
            int l1 = motionevent.getPointerId((0xff00 & i) >> 8);
            for(j1 = j; j1 <= l1; j1++)
            {
                PointerState pointerstate1 = new PointerState();
                mPointers.add(pointerstate1);
            }

            if(mActivePointerId < 0 || PointerState._2D_get8((PointerState)mPointers.get(mActivePointerId)) ^ true)
                mActivePointerId = l1;
            PointerState pointerstate2 = (PointerState)mPointers.get(l1);
            PointerState._2D_set6(pointerstate2, true);
            InputDevice inputdevice = InputDevice.getDevice(motionevent.getDeviceId());
            boolean flag;
            int l2;
            int i3;
            if(inputdevice != null)
            {
                if(inputdevice.getMotionRange(32) != null)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            PointerState._2D_set7(pointerstate2, flag);
        }
        k2 = motionevent.getPointerCount();
        mVelocity.addMovement(motionevent);
        mVelocity.computeCurrentVelocity(1);
        if(mAltVelocity != null)
        {
            mAltVelocity.addMovement(motionevent);
            mAltVelocity.computeCurrentVelocity(1);
        }
        l2 = motionevent.getHistorySize();
        for(int k = 0; k < l2; k++)
        {
            l1 = 0;
            while(l1 < k2) 
            {
                i3 = motionevent.getPointerId(l1);
                PointerState pointerstate3;
                android.view.MotionEvent.PointerCoords pointercoords;
                if(mCurDown)
                    pointerstate3 = (PointerState)mPointers.get(i3);
                else
                    pointerstate3 = null;
                if(pointerstate3 != null)
                    pointercoords = PointerState._2D_get7(pointerstate3);
                else
                    pointercoords = mTempCoords;
                motionevent.getHistoricalPointerCoords(l1, k, pointercoords);
                if(mPrintCoords)
                    logCoords("Pointer", i, l1, pointercoords, i3, motionevent);
                if(pointerstate3 != null)
                    pointerstate3.addTrace(pointercoords.x, pointercoords.y, false);
                l1++;
            }
        }

        int l = 0;
        while(l < k2) 
        {
            int i2 = motionevent.getPointerId(l);
            PointerState pointerstate4;
            android.view.MotionEvent.PointerCoords pointercoords1;
            if(mCurDown)
                pointerstate4 = (PointerState)mPointers.get(i2);
            else
                pointerstate4 = null;
            if(pointerstate4 != null)
                pointercoords1 = PointerState._2D_get7(pointerstate4);
            else
                pointercoords1 = mTempCoords;
            motionevent.getPointerCoords(l, pointercoords1);
            if(mPrintCoords)
                logCoords("Pointer", i, l, pointercoords1, i2, motionevent);
            if(pointerstate4 != null)
            {
                pointerstate4.addTrace(pointercoords1.x, pointercoords1.y, true);
                PointerState._2D_set9(pointerstate4, mVelocity.getXVelocity(i2));
                PointerState._2D_set10(pointerstate4, mVelocity.getYVelocity(i2));
                mVelocity.getEstimator(i2, PointerState._2D_get9(pointerstate4));
                if(mAltVelocity != null)
                {
                    PointerState._2D_set0(pointerstate4, mAltVelocity.getXVelocity(i2));
                    PointerState._2D_set1(pointerstate4, mAltVelocity.getYVelocity(i2));
                    mAltVelocity.getEstimator(i2, PointerState._2D_get0(pointerstate4));
                }
                PointerState._2D_set8(pointerstate4, motionevent.getToolType(l));
                if(PointerState._2D_get10(pointerstate4))
                {
                    PointerState._2D_set3(pointerstate4, motionevent.getAxisValue(32, l));
                    PointerState._2D_set5(pointerstate4, motionevent.getAxisValue(33, l));
                    PointerState._2D_set4(pointerstate4, motionevent.getAxisValue(34, l));
                    PointerState._2D_set2(pointerstate4, motionevent.getAxisValue(35, l));
                }
            }
            l++;
        }
          goto _L1
_L4:
        int j2 = (0xff00 & i) >> 8;
        int i1 = motionevent.getPointerId(j2);
        if(i1 >= j1)
        {
            Slog.wtf("Pointer", (new StringBuilder()).append("Got pointer ID out of bounds: id=").append(i1).append(" arraysize=").append(j1).append(" pointerindex=").append(j2).append(" action=0x").append(Integer.toHexString(i)).toString());
            return;
        }
        PointerState pointerstate5 = (PointerState)mPointers.get(i1);
        PointerState._2D_set6(pointerstate5, false);
        if(i == 1 || i == 3)
        {
            mCurDown = false;
            mCurNumPointers = 0;
        } else
        {
            mCurNumPointers = mCurNumPointers - 1;
            if(mActivePointerId == i1)
            {
                int k1;
                if(j2 == 0)
                    k1 = 1;
                else
                    k1 = 0;
                mActivePointerId = motionevent.getPointerId(k1);
            }
            pointerstate5.addTrace((0.0F / 0.0F), (0.0F / 0.0F), false);
        }
          goto _L2
_L1:
        if(i == 1 || i == 3 || (i & 0xff) == 6) goto _L4; else goto _L3
_L3:
        invalidate();
        return;
_L2:
        if(true) goto _L3; else goto _L5
_L5:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        onPointerEvent(motionevent);
        if(motionevent.getAction() == 0 && isFocused() ^ true)
            requestFocus();
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        logMotionEvent("Trackball", motionevent);
        return true;
    }

    public void setPrintCoords(boolean flag)
    {
        mPrintCoords = flag;
    }

    private static final String ALT_STRATEGY_PROPERY_KEY = "debug.velocitytracker.alt";
    private static final String TAG = "Pointer";
    private final int ESTIMATE_FUTURE_POINTS = 2;
    private final float ESTIMATE_INTERVAL = 0.02F;
    private final int ESTIMATE_PAST_POINTS = 4;
    private int mActivePointerId;
    private final VelocityTracker mAltVelocity;
    private boolean mCurDown;
    private int mCurNumPointers;
    private final Paint mCurrentPointPaint = new Paint();
    private int mHeaderBottom;
    private final InputManager mIm;
    private int mMaxNumPointers;
    private final Paint mPaint = new Paint();
    private final Paint mPathPaint = new Paint();
    private final ArrayList mPointers = new ArrayList();
    private boolean mPrintCoords;
    private RectF mReusableOvalRect;
    private final Paint mTargetPaint = new Paint();
    private final android.view.MotionEvent.PointerCoords mTempCoords = new android.view.MotionEvent.PointerCoords();
    private final FasterStringBuilder mText = new FasterStringBuilder();
    private final Paint mTextBackgroundPaint = new Paint();
    private final Paint mTextLevelPaint = new Paint();
    private final android.graphics.Paint.FontMetricsInt mTextMetrics = new android.graphics.Paint.FontMetricsInt();
    private final Paint mTextPaint = new Paint();
    private final ViewConfiguration mVC;
    private final VelocityTracker mVelocity = VelocityTracker.obtain();
}
