// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.*;

// Referenced classes of package android.inputmethodservice:
//            Keyboard

public class KeyboardView extends View
    implements android.view.View.OnClickListener
{
    public static interface OnKeyboardActionListener
    {

        public abstract void onKey(int i, int ai[]);

        public abstract void onPress(int i);

        public abstract void onRelease(int i);

        public abstract void onText(CharSequence charsequence);

        public abstract void swipeDown();

        public abstract void swipeLeft();

        public abstract void swipeRight();

        public abstract void swipeUp();
    }

    private static class SwipeTracker
    {

        private void addPoint(float f, float f1, long l)
        {
            int i = -1;
            long al[] = mPastTime;
            int j = 0;
            do
            {
                if(j >= 4 || al[j] == 0L)
                {
                    int k = i;
                    if(j == 4)
                    {
                        k = i;
                        if(i < 0)
                            k = 0;
                    }
                    i = k;
                    if(k == j)
                        i = k - 1;
                    float af[] = mPastX;
                    float af1[] = mPastY;
                    k = j;
                    if(i >= 0)
                    {
                        int i1 = i + 1;
                        k = 4 - i - 1;
                        System.arraycopy(af, i1, af, 0, k);
                        System.arraycopy(af1, i1, af1, 0, k);
                        System.arraycopy(al, i1, al, 0, k);
                        k = j - (i + 1);
                    }
                    af[k] = f;
                    af1[k] = f1;
                    al[k] = l;
                    j = k + 1;
                    if(j < 4)
                        al[j] = 0L;
                    return;
                }
                if(al[j] < l - 200L)
                    i = j;
                j++;
            } while(true);
        }

        public void addMovement(MotionEvent motionevent)
        {
            long l = motionevent.getEventTime();
            int i = motionevent.getHistorySize();
            for(int j = 0; j < i; j++)
                addPoint(motionevent.getHistoricalX(j), motionevent.getHistoricalY(j), motionevent.getHistoricalEventTime(j));

            addPoint(motionevent.getX(), motionevent.getY(), l);
        }

        public void clear()
        {
            mPastTime[0] = 0L;
        }

        public void computeCurrentVelocity(int i)
        {
            computeCurrentVelocity(i, 3.402823E+038F);
        }

        public void computeCurrentVelocity(int i, float f)
        {
            float af[] = mPastX;
            float af1[] = mPastY;
            long al[] = mPastTime;
            float f1 = af[0];
            float f2 = af1[0];
            long l = al[0];
            float f3 = 0.0F;
            float f4 = 0.0F;
            int j = 0;
            do
            {
                if(j >= 4 || al[j] == 0L)
                {
                    int k = 1;
                    while(k < j) 
                    {
                        int i1 = (int)(al[k] - l);
                        float f5;
                        if(i1 == 0)
                        {
                            f5 = f4;
                        } else
                        {
                            f5 = ((af[k] - f1) / (float)i1) * (float)i;
                            if(f3 != 0.0F)
                                f5 = (f3 + f5) * 0.5F;
                            f3 = ((af1[k] - f2) / (float)i1) * (float)i;
                            if(f4 == 0.0F)
                            {
                                f4 = f3;
                                f3 = f5;
                                f5 = f4;
                            } else
                            {
                                f4 = (f4 + f3) * 0.5F;
                                f3 = f5;
                                f5 = f4;
                            }
                        }
                        k++;
                        f4 = f5;
                    }
                    break;
                }
                j++;
            } while(true);
            float f6;
            if(f3 < 0.0F)
                f6 = Math.max(f3, -f);
            else
                f6 = Math.min(f3, f);
            mXVelocity = f6;
            if(f4 < 0.0F)
                f = Math.max(f4, -f);
            else
                f = Math.min(f4, f);
            mYVelocity = f;
        }

        public float getXVelocity()
        {
            return mXVelocity;
        }

        public float getYVelocity()
        {
            return mYVelocity;
        }

        static final int LONGEST_PAST_TIME = 200;
        static final int NUM_PAST = 4;
        final long mPastTime[];
        final float mPastX[];
        final float mPastY[];
        float mXVelocity;
        float mYVelocity;

        private SwipeTracker()
        {
            mPastX = new float[4];
            mPastY = new float[4];
            mPastTime = new long[4];
        }

        SwipeTracker(SwipeTracker swipetracker)
        {
            this();
        }
    }


    static boolean _2D_get0(KeyboardView keyboardview)
    {
        return keyboardview.mDisambiguateSwipe;
    }

    static int _2D_get1(KeyboardView keyboardview)
    {
        return keyboardview.mDownKey;
    }

    static OnKeyboardActionListener _2D_get2(KeyboardView keyboardview)
    {
        return keyboardview.mKeyboardActionListener;
    }

    static boolean _2D_get3(KeyboardView keyboardview)
    {
        return keyboardview.mPossiblePoly;
    }

    static TextView _2D_get4(KeyboardView keyboardview)
    {
        return keyboardview.mPreviewText;
    }

    static int _2D_get5(KeyboardView keyboardview)
    {
        return keyboardview.mStartX;
    }

    static int _2D_get6(KeyboardView keyboardview)
    {
        return keyboardview.mStartY;
    }

    static int _2D_get7(KeyboardView keyboardview)
    {
        return keyboardview.mSwipeThreshold;
    }

    static SwipeTracker _2D_get8(KeyboardView keyboardview)
    {
        return keyboardview.mSwipeTracker;
    }

    static boolean _2D_wrap0(KeyboardView keyboardview, MotionEvent motionevent)
    {
        return keyboardview.openPopupIfRequired(motionevent);
    }

    static boolean _2D_wrap1(KeyboardView keyboardview)
    {
        return keyboardview.repeatKey();
    }

    static void _2D_wrap2(KeyboardView keyboardview, int i, int j, int k, long l)
    {
        keyboardview.detectAndSendKey(i, j, k, l);
    }

    static void _2D_wrap3(KeyboardView keyboardview)
    {
        keyboardview.dismissPopupKeyboard();
    }

    static void _2D_wrap4(KeyboardView keyboardview, int i)
    {
        keyboardview.showKey(i);
    }

    public KeyboardView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1110051);
    }

    public KeyboardView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public KeyboardView(Context context, AttributeSet attributeset, int i, int j)
    {
        TypedArray typedarray;
        int k;
        super(context, attributeset, i, j);
        mCurrentKeyIndex = -1;
        mCoordinates = new int[2];
        mPreviewCentered = false;
        mShowPreview = true;
        mShowTouchPoints = true;
        mCurrentKey = -1;
        mDownKey = -1;
        mKeyIndices = new int[12];
        mRepeatKeyIndex = -1;
        mClipRegion = new Rect(0, 0, 0, 0);
        mSwipeTracker = new SwipeTracker(null);
        mOldPointerCount = 1;
        mDistances = new int[MAX_NEARBY_KEYS];
        mPreviewLabel = new StringBuilder(1);
        mDirtyRect = new Rect();
        typedarray = context.obtainStyledAttributes(attributeset, android.R.styleable.KeyboardView, i, j);
        attributeset = (LayoutInflater)context.getSystemService("layout_inflater");
        j = 0;
        k = typedarray.getIndexCount();
        i = 0;
_L14:
        int l;
        if(i >= k)
            break MISSING_BLOCK_LABEL_405;
        l = typedarray.getIndex(i);
        l;
        JVM INSTR tableswitch 0 10: default 232
    //                   0 375
    //                   1 390
    //                   2 238
    //                   3 311
    //                   4 344
    //                   5 327
    //                   6 267
    //                   7 280
    //                   8 295
    //                   9 252
    //                   10 360;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L3:
        break MISSING_BLOCK_LABEL_390;
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L15:
        i++;
        if(true) goto _L14; else goto _L13
_L13:
        mKeyBackground = typedarray.getDrawable(l);
          goto _L15
_L11:
        mVerticalCorrection = typedarray.getDimensionPixelOffset(l, 0);
          goto _L15
_L8:
        j = typedarray.getResourceId(l, 0);
          goto _L15
_L9:
        mPreviewOffset = typedarray.getDimensionPixelOffset(l, 0);
          goto _L15
_L10:
        mPreviewHeight = typedarray.getDimensionPixelSize(l, 80);
          goto _L15
_L5:
        mKeyTextSize = typedarray.getDimensionPixelSize(l, 18);
          goto _L15
_L7:
        mKeyTextColor = typedarray.getColor(l, 0xff000000);
          goto _L15
_L6:
        mLabelTextSize = typedarray.getDimensionPixelSize(l, 14);
          goto _L15
_L12:
        mPopupLayout = typedarray.getResourceId(l, 0);
          goto _L15
_L2:
        mShadowColor = typedarray.getColor(l, 0);
          goto _L15
        mShadowRadius = typedarray.getFloat(l, 0.0F);
          goto _L15
        mBackgroundDimAmount = mContext.obtainStyledAttributes(com.android.internal.R.styleable.Theme).getFloat(2, 0.5F);
        mPreviewPopup = new PopupWindow(context);
        if(j != 0)
        {
            mPreviewText = (TextView)attributeset.inflate(j, null);
            mPreviewTextSizeLarge = (int)mPreviewText.getTextSize();
            mPreviewPopup.setContentView(mPreviewText);
            mPreviewPopup.setBackgroundDrawable(null);
        } else
        {
            mShowPreview = false;
        }
        mPreviewPopup.setTouchable(false);
        mPopupKeyboard = new PopupWindow(context);
        mPopupKeyboard.setBackgroundDrawable(null);
        mPopupParent = this;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(0.0F);
        mPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mPaint.setAlpha(255);
        mPadding = new Rect(0, 0, 0, 0);
        mMiniKeyboardCache = new HashMap();
        mKeyBackground.getPadding(mPadding);
        mSwipeThreshold = (int)(getResources().getDisplayMetrics().density * 500F);
        mDisambiguateSwipe = getResources().getBoolean(0x11200b5);
        mAccessibilityManager = AccessibilityManager.getInstance(context);
        mAudioManager = (AudioManager)context.getSystemService("audio");
        resetMultiTap();
        return;
    }

    private CharSequence adjustCase(CharSequence charsequence)
    {
        Object obj = charsequence;
        if(mKeyboard.isShifted())
        {
            obj = charsequence;
            if(charsequence != null)
            {
                obj = charsequence;
                if(charsequence.length() < 3)
                {
                    obj = charsequence;
                    if(Character.isLowerCase(charsequence.charAt(0)))
                        obj = charsequence.toString().toUpperCase();
                }
            }
        }
        return ((CharSequence) (obj));
    }

    private void checkMultiTap(long l, int i)
    {
        if(i == -1)
            return;
        Keyboard.Key key = mKeys[i];
        if(key.codes.length > 1)
        {
            mInMultiTap = true;
            if(l < mLastTapTime + 800L && i == mLastSentIndex)
            {
                mTapCount = (mTapCount + 1) % key.codes.length;
                return;
            } else
            {
                mTapCount = -1;
                return;
            }
        }
        if(l > mLastTapTime + 800L || i != mLastSentIndex)
            resetMultiTap();
    }

    private void computeProximityThreshold(Keyboard keyboard)
    {
        if(keyboard == null)
            return;
        keyboard = mKeys;
        if(keyboard == null)
            return;
        int i = keyboard.length;
        int j = 0;
        for(int k = 0; k < i; k++)
        {
            Object obj = keyboard[k];
            j += Math.min(((Keyboard.Key) (obj)).width, ((Keyboard.Key) (obj)).height) + ((Keyboard.Key) (obj)).gap;
        }

        if(j < 0 || i == 0)
        {
            return;
        } else
        {
            mProximityThreshold = (int)(((float)j * 1.4F) / (float)i);
            mProximityThreshold = mProximityThreshold * mProximityThreshold;
            return;
        }
    }

    private void detectAndSendKey(int i, int j, int k, long l)
    {
        if(i != -1 && i < mKeys.length)
        {
            Keyboard.Key key = mKeys[i];
            if(key.text != null)
            {
                mKeyboardActionListener.onText(key.text);
                mKeyboardActionListener.onRelease(-1);
            } else
            {
                int i1 = key.codes[0];
                int ai[] = new int[MAX_NEARBY_KEYS];
                Arrays.fill(ai, -1);
                getKeyIndices(j, k, ai);
                j = i1;
                if(mInMultiTap)
                {
                    if(mTapCount != -1)
                        mKeyboardActionListener.onKey(-5, KEY_DELETE);
                    else
                        mTapCount = 0;
                    j = key.codes[mTapCount];
                }
                mKeyboardActionListener.onKey(j, ai);
                mKeyboardActionListener.onRelease(j);
            }
            mLastSentIndex = i;
            mLastTapTime = l;
        }
    }

    private void dismissPopupKeyboard()
    {
        if(mPopupKeyboard.isShowing())
        {
            mPopupKeyboard.dismiss();
            mMiniKeyboardOnScreen = false;
            invalidateAllKeys();
        }
    }

    private int getKeyIndices(int i, int j, int ai[])
    {
        Keyboard.Key akey[];
        int k;
        int l;
        int i1;
        int ai1[];
        int j1;
        int k1;
        akey = mKeys;
        k = -1;
        l = -1;
        i1 = mProximityThreshold + 1;
        Arrays.fill(mDistances, 0x7fffffff);
        ai1 = mKeyboard.getNearestKeys(i, j);
        j1 = ai1.length;
        k1 = 0;
_L14:
        Keyboard.Key key;
        boolean flag1;
        int i2;
        if(k1 >= j1)
            break MISSING_BLOCK_LABEL_370;
        key = akey[ai1[k1]];
        boolean flag = false;
        flag1 = key.isInside(i, j);
        i2 = k;
        if(flag1)
            i2 = ai1[k1];
        k = ((flag) ? 1 : 0);
        if(!mProximityCorrectOn) goto _L2; else goto _L1
_L1:
        int l1;
        l1 = key.squaredDistanceFrom(i, j);
        k = l1;
        if(l1 >= mProximityThreshold) goto _L2; else goto _L3
_L3:
        k = l1;
_L8:
        int j2;
        int k2;
        j2 = l;
        k2 = i1;
        if(key.codes[0] <= 32) goto _L5; else goto _L4
_L4:
        int l2;
        l2 = key.codes.length;
        l1 = l;
        l = i1;
        if(k < i1)
        {
            l = k;
            l1 = ai1[k1];
        }
        if(ai != null) goto _L7; else goto _L6
_L6:
        k2 = l;
        j2 = l1;
_L5:
        k1++;
        l = j2;
        i1 = k2;
        k = i2;
        continue; /* Loop/switch isn't completed */
_L2:
        j2 = l;
        k2 = i1;
        if(!flag1) goto _L5; else goto _L8
_L7:
        i1 = 0;
_L12:
        j2 = l1;
        k2 = l;
        if(i1 >= mDistances.length) goto _L5; else goto _L9
_L9:
        int i3;
        if(mDistances[i1] <= k)
            break MISSING_BLOCK_LABEL_364;
        System.arraycopy(mDistances, i1, mDistances, i1 + l2, mDistances.length - i1 - l2);
        System.arraycopy(ai, i1, ai, i1 + l2, ai.length - i1 - l2);
        i3 = 0;
_L11:
        j2 = l1;
        k2 = l;
        if(i3 >= l2) goto _L5; else goto _L10
_L10:
        ai[i1 + i3] = key.codes[i3];
        mDistances[i1 + i3] = k;
        i3++;
          goto _L11
          goto _L5
        i1++;
          goto _L12
        i = k;
        if(k == -1)
            i = l;
        return i;
        if(true) goto _L14; else goto _L13
_L13:
    }

    private CharSequence getPreviewText(Keyboard.Key key)
    {
        int i = 0;
        if(mInMultiTap)
        {
            mPreviewLabel.setLength(0);
            StringBuilder stringbuilder = mPreviewLabel;
            key = key.codes;
            if(mTapCount >= 0)
                i = mTapCount;
            stringbuilder.append((char)key[i]);
            return adjustCase(mPreviewLabel);
        } else
        {
            return adjustCase(key.label);
        }
    }

    private void initGestureDetector()
    {
        if(mGestureDetector == null)
        {
            mGestureDetector = new GestureDetector(getContext(), new android.view.GestureDetector.SimpleOnGestureListener() {

                public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
                {
                    float f2;
                    float f3;
                    float f4;
                    float f5;
                    int i;
                    int j;
                    float f6;
                    float f7;
                    boolean flag;
                    if(KeyboardView._2D_get3(KeyboardView.this))
                        return false;
                    f2 = Math.abs(f);
                    f3 = Math.abs(f1);
                    f4 = motionevent1.getX() - motionevent.getX();
                    f5 = motionevent1.getY() - motionevent.getY();
                    i = getWidth() / 2;
                    j = getHeight() / 2;
                    KeyboardView._2D_get8(KeyboardView.this).computeCurrentVelocity(1000);
                    f6 = KeyboardView._2D_get8(KeyboardView.this).getXVelocity();
                    f7 = KeyboardView._2D_get8(KeyboardView.this).getYVelocity();
                    flag = false;
                    if(f <= (float)KeyboardView._2D_get7(KeyboardView.this) || f3 >= f2 || f4 <= (float)i) goto _L2; else goto _L1
_L1:
                    if(!KeyboardView._2D_get0(KeyboardView.this) || f6 >= f / 4F) goto _L4; else goto _L3
_L3:
                    i = 1;
_L6:
                    if(i != 0)
                        KeyboardView._2D_wrap2(KeyboardView.this, KeyboardView._2D_get1(KeyboardView.this), KeyboardView._2D_get5(KeyboardView.this), KeyboardView._2D_get6(KeyboardView.this), motionevent.getEventTime());
                    return false;
_L4:
                    swipeRight();
                    return true;
_L2:
                    if(f < (float)(-KeyboardView._2D_get7(KeyboardView.this)) && f3 < f2 && f4 < (float)(-i))
                    {
                        if(KeyboardView._2D_get0(KeyboardView.this) && f6 > f / 4F)
                        {
                            i = 1;
                        } else
                        {
                            swipeLeft();
                            return true;
                        }
                    } else
                    if(f1 < (float)(-KeyboardView._2D_get7(KeyboardView.this)) && f2 < f3 && f5 < (float)(-j))
                    {
                        if(KeyboardView._2D_get0(KeyboardView.this) && f7 > f1 / 4F)
                        {
                            i = 1;
                        } else
                        {
                            swipeUp();
                            return true;
                        }
                    } else
                    {
                        i = ((flag) ? 1 : 0);
                        if(f1 > (float)KeyboardView._2D_get7(KeyboardView.this))
                        {
                            i = ((flag) ? 1 : 0);
                            if(f2 < f3 / 2.0F)
                            {
                                i = ((flag) ? 1 : 0);
                                if(f5 > (float)j)
                                    if(KeyboardView._2D_get0(KeyboardView.this) && f7 < f1 / 4F)
                                    {
                                        i = 1;
                                    } else
                                    {
                                        swipeDown();
                                        return true;
                                    }
                            }
                        }
                    }
                    if(true) goto _L6; else goto _L5
_L5:
                }

                final KeyboardView this$0;

            
            {
                this$0 = KeyboardView.this;
                super();
            }
            }
);
            mGestureDetector.setIsLongpressEnabled(false);
        }
    }

    private void onBufferDraw()
    {
        if(mBuffer == null || mKeyboardChanged)
        {
            if(mBuffer == null || mKeyboardChanged && (mBuffer.getWidth() != getWidth() || mBuffer.getHeight() != getHeight()))
            {
                mBuffer = Bitmap.createBitmap(Math.max(1, getWidth()), Math.max(1, getHeight()), android.graphics.Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBuffer);
            }
            invalidateAllKeys();
            mKeyboardChanged = false;
        }
        Canvas canvas = mCanvas;
        canvas.clipRect(mDirtyRect, android.graphics.Region.Op.REPLACE);
        if(mKeyboard == null)
            return;
        Paint paint = mPaint;
        Drawable drawable = mKeyBackground;
        Rect rect = mClipRegion;
        Rect rect1 = mPadding;
        int i = mPaddingLeft;
        int j = mPaddingTop;
        Keyboard.Key akey[] = mKeys;
        Keyboard.Key key = mInvalidatedKey;
        paint.setColor(mKeyTextColor);
        int k = 0;
        boolean flag = k;
        if(key != null)
        {
            flag = k;
            if(canvas.getClipBounds(rect))
            {
                flag = k;
                if((key.x + i) - 1 <= rect.left)
                {
                    flag = k;
                    if((key.y + j) - 1 <= rect.top)
                    {
                        flag = k;
                        if(key.x + key.width + i + 1 >= rect.right)
                        {
                            flag = k;
                            if(key.y + key.height + j + 1 >= rect.bottom)
                                flag = true;
                        }
                    }
                }
            }
        }
        canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        int l = akey.length;
        k = 0;
        while(k < l) 
        {
            Keyboard.Key key1 = akey[k];
            if(!flag || key == key1)
            {
                drawable.setState(key1.getCurrentDrawableState());
                String s;
                Rect rect2;
                if(key1.label == null)
                    s = null;
                else
                    s = adjustCase(key1.label).toString();
                rect2 = drawable.getBounds();
                if(key1.width != rect2.right || key1.height != rect2.bottom)
                    drawable.setBounds(0, 0, key1.width, key1.height);
                canvas.translate(key1.x + i, key1.y + j);
                drawable.draw(canvas);
                if(s != null)
                {
                    if(s.length() > 1 && key1.codes.length < 2)
                    {
                        paint.setTextSize(mLabelTextSize);
                        paint.setTypeface(Typeface.DEFAULT_BOLD);
                    } else
                    {
                        paint.setTextSize(mKeyTextSize);
                        paint.setTypeface(Typeface.DEFAULT);
                    }
                    paint.setShadowLayer(mShadowRadius, 0.0F, 0.0F, mShadowColor);
                    canvas.drawText(s, (key1.width - rect1.left - rect1.right) / 2 + rect1.left, (float)((key1.height - rect1.top - rect1.bottom) / 2) + (paint.getTextSize() - paint.descent()) / 2.0F + (float)rect1.top, paint);
                    paint.setShadowLayer(0.0F, 0.0F, 0.0F, 0);
                } else
                if(key1.icon != null)
                {
                    int i1 = (key1.width - rect1.left - rect1.right - key1.icon.getIntrinsicWidth()) / 2 + rect1.left;
                    int j1 = (key1.height - rect1.top - rect1.bottom - key1.icon.getIntrinsicHeight()) / 2 + rect1.top;
                    canvas.translate(i1, j1);
                    key1.icon.setBounds(0, 0, key1.icon.getIntrinsicWidth(), key1.icon.getIntrinsicHeight());
                    key1.icon.draw(canvas);
                    canvas.translate(-i1, -j1);
                }
                canvas.translate(-key1.x - i, -key1.y - j);
            }
            k++;
        }
        mInvalidatedKey = null;
        if(mMiniKeyboardOnScreen)
        {
            paint.setColor((int)(mBackgroundDimAmount * 255F) << 24);
            canvas.drawRect(0.0F, 0.0F, getWidth(), getHeight(), paint);
        }
        mDrawPending = false;
        mDirtyRect.setEmpty();
    }

    private boolean onModifiedTouchEvent(MotionEvent motionevent, boolean flag)
    {
        int i;
        int j;
        int l;
        long l1;
        int i1;
        i = (int)motionevent.getX() - mPaddingLeft;
        j = (int)motionevent.getY() - mPaddingTop;
        l = j;
        if(j >= -mVerticalCorrection)
            l = j + mVerticalCorrection;
        j = motionevent.getAction();
        l1 = motionevent.getEventTime();
        i1 = getKeyIndices(i, l, null);
        mPossiblePoly = flag;
        if(j == 0)
            mSwipeTracker.clear();
        mSwipeTracker.addMovement(motionevent);
        if(mAbortKey && j != 0 && j != 3)
            return true;
        if(mGestureDetector.onTouchEvent(motionevent))
        {
            showPreview(-1);
            mHandler.removeMessages(3);
            mHandler.removeMessages(4);
            return true;
        }
        if(mMiniKeyboardOnScreen && j != 3)
            return true;
        j;
        JVM INSTR tableswitch 0 3: default 196
    //                   0 213
    //                   1 660
    //                   2 451
    //                   3 874;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        int j1 = i;
_L11:
        mLastX = j1;
        mLastY = l;
        return true;
_L2:
        mAbortKey = false;
        mStartX = i;
        mStartY = l;
        mLastCodeX = i;
        mLastCodeY = l;
        mLastKeyTime = 0L;
        mCurrentKeyTime = 0L;
        mLastKey = -1;
        mCurrentKey = i1;
        mDownKey = i1;
        mDownTime = motionevent.getEventTime();
        mLastMoveTime = mDownTime;
        checkMultiTap(l1, i1);
        OnKeyboardActionListener onkeyboardactionlistener = mKeyboardActionListener;
        if(i1 != -1)
            j = mKeys[i1].codes[0];
        else
            j = 0;
        onkeyboardactionlistener.onPress(j);
        if(mCurrentKey >= 0 && mKeys[mCurrentKey].repeatable)
        {
            mRepeatKeyIndex = mCurrentKey;
            Message message = mHandler.obtainMessage(3);
            mHandler.sendMessageDelayed(message, 400L);
            repeatKey();
            if(mAbortKey)
            {
                mRepeatKeyIndex = -1;
                j1 = i;
                continue; /* Loop/switch isn't completed */
            }
        }
        if(mCurrentKey != -1)
        {
            motionevent = mHandler.obtainMessage(4, motionevent);
            mHandler.sendMessageDelayed(motionevent, LONGPRESS_TIMEOUT);
        }
        showPreview(i1);
        j1 = i;
        continue; /* Loop/switch isn't completed */
_L4:
        j1 = 0;
        j = j1;
        if(i1 == -1) goto _L7; else goto _L6
_L6:
        if(mCurrentKey != -1) goto _L9; else goto _L8
_L8:
        mCurrentKey = i1;
        mCurrentKeyTime = l1 - mDownTime;
        j = j1;
_L7:
        if(j == 0)
        {
            mHandler.removeMessages(4);
            if(i1 != -1)
            {
                motionevent = mHandler.obtainMessage(4, motionevent);
                mHandler.sendMessageDelayed(motionevent, LONGPRESS_TIMEOUT);
            }
        }
        showPreview(mCurrentKey);
        mLastMoveTime = l1;
        j1 = i;
        continue; /* Loop/switch isn't completed */
_L9:
        if(i1 == mCurrentKey)
        {
            mCurrentKeyTime = mCurrentKeyTime + (l1 - mLastMoveTime);
            j = 1;
        } else
        {
            j = j1;
            if(mRepeatKeyIndex == -1)
            {
                resetMultiTap();
                mLastKey = mCurrentKey;
                mLastCodeX = mLastX;
                mLastCodeY = mLastY;
                mLastKeyTime = (mCurrentKeyTime + l1) - mLastMoveTime;
                mCurrentKey = i1;
                mCurrentKeyTime = 0L;
                j = j1;
            }
        }
        if(true) goto _L7; else goto _L3
_L3:
        removeMessages();
        int k;
        if(i1 == mCurrentKey)
        {
            mCurrentKeyTime = mCurrentKeyTime + (l1 - mLastMoveTime);
        } else
        {
            resetMultiTap();
            mLastKey = mCurrentKey;
            mLastKeyTime = (mCurrentKeyTime + l1) - mLastMoveTime;
            mCurrentKey = i1;
            mCurrentKeyTime = 0L;
        }
        j1 = i;
        k = l;
        if(mCurrentKeyTime < mLastKeyTime)
        {
            j1 = i;
            k = l;
            if(mCurrentKeyTime < 70L)
            {
                j1 = i;
                k = l;
                if(mLastKey != -1)
                {
                    mCurrentKey = mLastKey;
                    j1 = mLastCodeX;
                    k = mLastCodeY;
                }
            }
        }
        showPreview(-1);
        Arrays.fill(mKeyIndices, -1);
        if(mRepeatKeyIndex == -1 && mMiniKeyboardOnScreen ^ true && mAbortKey ^ true)
            detectAndSendKey(mCurrentKey, j1, k, l1);
        invalidateKey(i1);
        mRepeatKeyIndex = -1;
        l = k;
        continue; /* Loop/switch isn't completed */
_L5:
        removeMessages();
        dismissPopupKeyboard();
        mAbortKey = true;
        showPreview(-1);
        invalidateKey(mCurrentKey);
        j1 = i;
        if(true) goto _L11; else goto _L10
_L10:
    }

    private boolean openPopupIfRequired(MotionEvent motionevent)
    {
        if(mPopupLayout == 0)
            return false;
        if(mCurrentKey < 0 || mCurrentKey >= mKeys.length)
            return false;
        boolean flag = onLongPress(mKeys[mCurrentKey]);
        if(flag)
        {
            mAbortKey = true;
            showPreview(-1);
        }
        return flag;
    }

    private void removeMessages()
    {
        if(mHandler != null)
        {
            mHandler.removeMessages(3);
            mHandler.removeMessages(4);
            mHandler.removeMessages(1);
        }
    }

    private boolean repeatKey()
    {
        Keyboard.Key key = mKeys[mRepeatKeyIndex];
        detectAndSendKey(mCurrentKey, key.x, key.y, mLastTapTime);
        return true;
    }

    private void resetMultiTap()
    {
        mLastSentIndex = -1;
        mTapCount = 0;
        mLastTapTime = -1L;
        mInMultiTap = false;
    }

    private void sendAccessibilityEventForUnicodeCharacter(int i, int j)
    {
        if(!mAccessibilityManager.isEnabled()) goto _L2; else goto _L1
_L1:
        AccessibilityEvent accessibilityevent;
        accessibilityevent = AccessibilityEvent.obtain(i);
        onInitializeAccessibilityEvent(accessibilityevent);
        j;
        JVM INSTR lookupswitch 7: default 88
    //                   -6: 116
    //                   -5: 146
    //                   -4: 161
    //                   -3: 131
    //                   -2: 176
    //                   -1: 191
    //                   10: 206;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L3:
        String s = String.valueOf((char)j);
_L12:
        accessibilityevent.getText().add(s);
        mAccessibilityManager.sendAccessibilityEvent(accessibilityevent);
_L2:
        return;
_L4:
        s = mContext.getString(0x10402a5);
        continue; /* Loop/switch isn't completed */
_L7:
        s = mContext.getString(0x10402a6);
        continue; /* Loop/switch isn't completed */
_L5:
        s = mContext.getString(0x10402a7);
        continue; /* Loop/switch isn't completed */
_L6:
        s = mContext.getString(0x10402a8);
        continue; /* Loop/switch isn't completed */
_L8:
        s = mContext.getString(0x10402aa);
        continue; /* Loop/switch isn't completed */
_L9:
        s = mContext.getString(0x10402ab);
        continue; /* Loop/switch isn't completed */
_L10:
        s = mContext.getString(0x10402a9);
        if(true) goto _L12; else goto _L11
_L11:
    }

    private void showKey(int i)
    {
        PopupWindow popupwindow = mPreviewPopup;
        Keyboard.Key akey[] = mKeys;
        if(i < 0 || i >= mKeys.length)
            return;
        Keyboard.Key key = akey[i];
        Object obj;
        int j;
        if(key.icon != null)
        {
            Object obj1 = mPreviewText;
            if(key.iconPreview != null)
                obj = key.iconPreview;
            else
                obj = key.icon;
            ((TextView) (obj1)).setCompoundDrawables(null, null, null, ((Drawable) (obj)));
            mPreviewText.setText(null);
        } else
        {
            mPreviewText.setCompoundDrawables(null, null, null, null);
            mPreviewText.setText(getPreviewText(key));
            if(key.label.length() > 1 && key.codes.length < 2)
            {
                mPreviewText.setTextSize(0, mKeyTextSize);
                mPreviewText.setTypeface(Typeface.DEFAULT_BOLD);
            } else
            {
                mPreviewText.setTextSize(0, mPreviewTextSizeLarge);
                mPreviewText.setTypeface(Typeface.DEFAULT);
            }
        }
        mPreviewText.measure(android.view.View.MeasureSpec.makeMeasureSpec(0, 0), android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
        i = Math.max(mPreviewText.getMeasuredWidth(), key.width + mPreviewText.getPaddingLeft() + mPreviewText.getPaddingRight());
        j = mPreviewHeight;
        obj = mPreviewText.getLayoutParams();
        if(obj != null)
        {
            obj.width = i;
            obj.height = j;
        }
        if(!mPreviewCentered)
        {
            mPopupPreviewX = (key.x - mPreviewText.getPaddingLeft()) + mPaddingLeft;
            mPopupPreviewY = (key.y - j) + mPreviewOffset;
        } else
        {
            mPopupPreviewX = 160 - mPreviewText.getMeasuredWidth() / 2;
            mPopupPreviewY = -mPreviewText.getMeasuredHeight();
        }
        mHandler.removeMessages(2);
        getLocationInWindow(mCoordinates);
        obj = mCoordinates;
        obj[0] = obj[0] + mMiniKeyboardOffsetX;
        obj = mCoordinates;
        obj[1] = obj[1] + mMiniKeyboardOffsetY;
        obj1 = mPreviewText.getBackground();
        if(key.popupResId != 0)
            obj = LONG_PRESSABLE_STATE_SET;
        else
            obj = EMPTY_STATE_SET;
        ((Drawable) (obj1)).setState(((int []) (obj)));
        mPopupPreviewX = mPopupPreviewX + mCoordinates[0];
        mPopupPreviewY = mPopupPreviewY + mCoordinates[1];
        getLocationOnScreen(mCoordinates);
        if(mPopupPreviewY + mCoordinates[1] < 0)
        {
            if(key.x + key.width <= getWidth() / 2)
                mPopupPreviewX = mPopupPreviewX + (int)((double)key.width * 2.5D);
            else
                mPopupPreviewX = mPopupPreviewX - (int)((double)key.width * 2.5D);
            mPopupPreviewY = mPopupPreviewY + j;
        }
        if(popupwindow.isShowing())
        {
            popupwindow.update(mPopupPreviewX, mPopupPreviewY, i, j);
        } else
        {
            popupwindow.setWidth(i);
            popupwindow.setHeight(j);
            popupwindow.showAtLocation(mPopupParent, 0, mPopupPreviewX, mPopupPreviewY);
        }
        mPreviewText.setVisibility(0);
    }

    private void showPreview(int i)
    {
        int j = mCurrentKeyIndex;
        PopupWindow popupwindow = mPreviewPopup;
        mCurrentKeyIndex = i;
        Keyboard.Key akey[] = mKeys;
        if(j != mCurrentKeyIndex)
        {
            if(j != -1 && akey.length > j)
            {
                Keyboard.Key key1 = akey[j];
                Keyboard.Key key;
                boolean flag;
                int k;
                if(mCurrentKeyIndex == -1)
                    flag = true;
                else
                    flag = false;
                key1.onReleased(flag);
                invalidateKey(j);
                k = key1.codes[0];
                sendAccessibilityEventForUnicodeCharacter(256, k);
                sendAccessibilityEventForUnicodeCharacter(0x10000, k);
            }
            if(mCurrentKeyIndex != -1 && akey.length > mCurrentKeyIndex)
            {
                key = akey[mCurrentKeyIndex];
                key.onPressed();
                invalidateKey(mCurrentKeyIndex);
                k = key.codes[0];
                sendAccessibilityEventForUnicodeCharacter(128, k);
                sendAccessibilityEventForUnicodeCharacter(32768, k);
            }
        }
        if(j != mCurrentKeyIndex && mShowPreview)
        {
            mHandler.removeMessages(1);
            if(popupwindow.isShowing() && i == -1)
                mHandler.sendMessageDelayed(mHandler.obtainMessage(2), 70L);
            if(i != -1)
                if(popupwindow.isShowing() && mPreviewText.getVisibility() == 0)
                    showKey(i);
                else
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(1, i, 0), 0L);
        }
    }

    public void closing()
    {
        if(mPreviewPopup.isShowing())
            mPreviewPopup.dismiss();
        removeMessages();
        dismissPopupKeyboard();
        mBuffer = null;
        mCanvas = null;
        mMiniKeyboardCache.clear();
    }

    public Keyboard getKeyboard()
    {
        return mKeyboard;
    }

    protected OnKeyboardActionListener getOnKeyboardActionListener()
    {
        return mKeyboardActionListener;
    }

    public boolean handleBack()
    {
        if(mPopupKeyboard.isShowing())
        {
            dismissPopupKeyboard();
            return true;
        } else
        {
            return false;
        }
    }

    public void invalidateAllKeys()
    {
        mDirtyRect.union(0, 0, getWidth(), getHeight());
        mDrawPending = true;
        invalidate();
    }

    public void invalidateKey(int i)
    {
        if(mKeys == null)
            return;
        if(i < 0 || i >= mKeys.length)
        {
            return;
        } else
        {
            Keyboard.Key key = mKeys[i];
            mInvalidatedKey = key;
            mDirtyRect.union(key.x + mPaddingLeft, key.y + mPaddingTop, key.x + key.width + mPaddingLeft, key.y + key.height + mPaddingTop);
            onBufferDraw();
            invalidate(key.x + mPaddingLeft, key.y + mPaddingTop, key.x + key.width + mPaddingLeft, key.y + key.height + mPaddingTop);
            return;
        }
    }

    public boolean isPreviewEnabled()
    {
        return mShowPreview;
    }

    public boolean isProximityCorrectionEnabled()
    {
        return mProximityCorrectOn;
    }

    public boolean isShifted()
    {
        if(mKeyboard != null)
            return mKeyboard.isShifted();
        else
            return false;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        initGestureDetector();
        if(mHandler == null)
            mHandler = new Handler() {

                public void handleMessage(Message message)
                {
                    message.what;
                    JVM INSTR tableswitch 1 4: default 36
                //                               1 37
                //                               2 51
                //                               3 65
                //                               4 91;
                       goto _L1 _L2 _L3 _L4 _L5
_L1:
                    return;
_L2:
                    KeyboardView._2D_wrap4(KeyboardView.this, message.arg1);
                    continue; /* Loop/switch isn't completed */
_L3:
                    KeyboardView._2D_get4(KeyboardView.this).setVisibility(4);
                    continue; /* Loop/switch isn't completed */
_L4:
                    if(KeyboardView._2D_wrap1(KeyboardView.this))
                        sendMessageDelayed(Message.obtain(this, 3), 50L);
                    continue; /* Loop/switch isn't completed */
_L5:
                    KeyboardView._2D_wrap0(KeyboardView.this, (MotionEvent)message.obj);
                    if(true) goto _L1; else goto _L6
_L6:
                }

                final KeyboardView this$0;

            
            {
                this$0 = KeyboardView.this;
                super();
            }
            }
;
    }

    public void onClick(View view)
    {
        dismissPopupKeyboard();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        closing();
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        break MISSING_BLOCK_LABEL_5;
        if(mDrawPending || mBuffer == null || mKeyboardChanged)
            onBufferDraw();
        canvas.drawBitmap(mBuffer, 0.0F, 0.0F, null);
        return;
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        if(!mAccessibilityManager.isTouchExplorationEnabled() || motionevent.getPointerCount() != 1) goto _L2; else goto _L1
_L1:
        motionevent.getAction();
        JVM INSTR tableswitch 7 10: default 52
    //                   7 66
    //                   8 52
    //                   9 58
    //                   10 74;
           goto _L3 _L4 _L3 _L5 _L6
_L3:
        return onTouchEvent(motionevent);
_L5:
        motionevent.setAction(0);
        continue; /* Loop/switch isn't completed */
_L4:
        motionevent.setAction(2);
        continue; /* Loop/switch isn't completed */
_L6:
        motionevent.setAction(1);
        if(true) goto _L3; else goto _L2
_L2:
        return true;
    }

    protected boolean onLongPress(Keyboard.Key key)
    {
        int i = key.popupResId;
        if(i != 0)
        {
            mMiniKeyboardContainer = (View)mMiniKeyboardCache.get(key);
            int k;
            if(mMiniKeyboardContainer == null)
            {
                mMiniKeyboardContainer = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(mPopupLayout, null);
                mMiniKeyboard = (KeyboardView)mMiniKeyboardContainer.findViewById(0x1020026);
                Object obj = mMiniKeyboardContainer.findViewById(0x1020027);
                if(obj != null)
                    ((View) (obj)).setOnClickListener(this);
                mMiniKeyboard.setOnKeyboardActionListener(new OnKeyboardActionListener() {

                    public void onKey(int i1, int ai[])
                    {
                        KeyboardView._2D_get2(KeyboardView.this).onKey(i1, ai);
                        KeyboardView._2D_wrap3(KeyboardView.this);
                    }

                    public void onPress(int i1)
                    {
                        KeyboardView._2D_get2(KeyboardView.this).onPress(i1);
                    }

                    public void onRelease(int i1)
                    {
                        KeyboardView._2D_get2(KeyboardView.this).onRelease(i1);
                    }

                    public void onText(CharSequence charsequence1)
                    {
                        KeyboardView._2D_get2(KeyboardView.this).onText(charsequence1);
                        KeyboardView._2D_wrap3(KeyboardView.this);
                    }

                    public void swipeDown()
                    {
                    }

                    public void swipeLeft()
                    {
                    }

                    public void swipeRight()
                    {
                    }

                    public void swipeUp()
                    {
                    }

                    final KeyboardView this$0;

            
            {
                this$0 = KeyboardView.this;
                super();
            }
                }
);
                int l;
                if(key.popupCharacters != null)
                {
                    obj = getContext();
                    CharSequence charsequence = key.popupCharacters;
                    int j = getPaddingLeft();
                    obj = new Keyboard(((Context) (obj)), i, charsequence, -1, getPaddingRight() + j);
                } else
                {
                    obj = new Keyboard(getContext(), i);
                }
                mMiniKeyboard.setKeyboard(((Keyboard) (obj)));
                mMiniKeyboard.setPopupParent(this);
                mMiniKeyboardContainer.measure(android.view.View.MeasureSpec.makeMeasureSpec(getWidth(), 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(getHeight(), 0x80000000));
                mMiniKeyboardCache.put(key, mMiniKeyboardContainer);
            } else
            {
                mMiniKeyboard = (KeyboardView)mMiniKeyboardContainer.findViewById(0x1020026);
            }
            getLocationInWindow(mCoordinates);
            mPopupX = key.x + mPaddingLeft;
            mPopupY = key.y + mPaddingTop;
            mPopupX = (mPopupX + key.width) - mMiniKeyboardContainer.getMeasuredWidth();
            mPopupY = mPopupY - mMiniKeyboardContainer.getMeasuredHeight();
            i = mPopupX + mMiniKeyboardContainer.getPaddingRight() + mCoordinates[0];
            l = mPopupY + mMiniKeyboardContainer.getPaddingBottom() + mCoordinates[1];
            key = mMiniKeyboard;
            if(i < 0)
                k = 0;
            else
                k = i;
            key.setPopupOffset(k, l);
            mMiniKeyboard.setShifted(isShifted());
            mPopupKeyboard.setContentView(mMiniKeyboardContainer);
            mPopupKeyboard.setWidth(mMiniKeyboardContainer.getMeasuredWidth());
            mPopupKeyboard.setHeight(mMiniKeyboardContainer.getMeasuredHeight());
            mPopupKeyboard.showAtLocation(this, 0, i, l);
            mMiniKeyboardOnScreen = true;
            invalidateAllKeys();
            return true;
        } else
        {
            return false;
        }
    }

    public void onMeasure(int i, int j)
    {
        if(mKeyboard == null)
        {
            setMeasuredDimension(mPaddingLeft + mPaddingRight, mPaddingTop + mPaddingBottom);
        } else
        {
            int k = mKeyboard.getMinWidth() + mPaddingLeft + mPaddingRight;
            j = k;
            if(android.view.View.MeasureSpec.getSize(i) < k + 10)
                j = android.view.View.MeasureSpec.getSize(i);
            setMeasuredDimension(j, mKeyboard.getHeight() + mPaddingTop + mPaddingBottom);
        }
    }

    public void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(mKeyboard != null)
            mKeyboard.resize(i, j);
        mBuffer = null;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getPointerCount();
        int j = motionevent.getAction();
        long l = motionevent.getEventTime();
        boolean flag;
        if(i != mOldPointerCount)
        {
            if(i == 1)
            {
                MotionEvent motionevent1 = MotionEvent.obtain(l, l, 0, motionevent.getX(), motionevent.getY(), motionevent.getMetaState());
                flag = onModifiedTouchEvent(motionevent1, false);
                motionevent1.recycle();
                if(j == 1)
                    flag = onModifiedTouchEvent(motionevent, true);
            } else
            {
                motionevent = MotionEvent.obtain(l, l, 1, mOldPointerX, mOldPointerY, motionevent.getMetaState());
                flag = onModifiedTouchEvent(motionevent, true);
                motionevent.recycle();
            }
        } else
        if(i == 1)
        {
            flag = onModifiedTouchEvent(motionevent, false);
            mOldPointerX = motionevent.getX();
            mOldPointerY = motionevent.getY();
        } else
        {
            flag = true;
        }
        mOldPointerCount = i;
        return flag;
    }

    public void setKeyboard(Keyboard keyboard)
    {
        if(mKeyboard != null)
            showPreview(-1);
        removeMessages();
        mKeyboard = keyboard;
        List list = mKeyboard.getKeys();
        mKeys = (Keyboard.Key[])list.toArray(new Keyboard.Key[list.size()]);
        requestLayout();
        mKeyboardChanged = true;
        invalidateAllKeys();
        computeProximityThreshold(keyboard);
        mMiniKeyboardCache.clear();
        mAbortKey = true;
    }

    public void setOnKeyboardActionListener(OnKeyboardActionListener onkeyboardactionlistener)
    {
        mKeyboardActionListener = onkeyboardactionlistener;
    }

    public void setPopupOffset(int i, int j)
    {
        mMiniKeyboardOffsetX = i;
        mMiniKeyboardOffsetY = j;
        if(mPreviewPopup.isShowing())
            mPreviewPopup.dismiss();
    }

    public void setPopupParent(View view)
    {
        mPopupParent = view;
    }

    public void setPreviewEnabled(boolean flag)
    {
        mShowPreview = flag;
    }

    public void setProximityCorrectionEnabled(boolean flag)
    {
        mProximityCorrectOn = flag;
    }

    public boolean setShifted(boolean flag)
    {
        if(mKeyboard != null && mKeyboard.setShifted(flag))
        {
            invalidateAllKeys();
            return true;
        } else
        {
            return false;
        }
    }

    public void setVerticalCorrection(int i)
    {
    }

    protected void swipeDown()
    {
        mKeyboardActionListener.swipeDown();
    }

    protected void swipeLeft()
    {
        mKeyboardActionListener.swipeLeft();
    }

    protected void swipeRight()
    {
        mKeyboardActionListener.swipeRight();
    }

    protected void swipeUp()
    {
        mKeyboardActionListener.swipeUp();
    }

    private static final int DEBOUNCE_TIME = 70;
    private static final boolean DEBUG = false;
    private static final int DELAY_AFTER_PREVIEW = 70;
    private static final int DELAY_BEFORE_PREVIEW = 0;
    private static final int KEY_DELETE[] = {
        -5
    };
    private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private static final int LONG_PRESSABLE_STATE_SET[] = {
        0x101023c
    };
    private static int MAX_NEARBY_KEYS = 0;
    private static final int MSG_LONGPRESS = 4;
    private static final int MSG_REMOVE_PREVIEW = 2;
    private static final int MSG_REPEAT = 3;
    private static final int MSG_SHOW_PREVIEW = 1;
    private static final int MULTITAP_INTERVAL = 800;
    private static final int NOT_A_KEY = -1;
    private static final int REPEAT_INTERVAL = 50;
    private static final int REPEAT_START_DELAY = 400;
    private boolean mAbortKey;
    private AccessibilityManager mAccessibilityManager;
    private AudioManager mAudioManager;
    private float mBackgroundDimAmount;
    private Bitmap mBuffer;
    private Canvas mCanvas;
    private Rect mClipRegion;
    private final int mCoordinates[];
    private int mCurrentKey;
    private int mCurrentKeyIndex;
    private long mCurrentKeyTime;
    private Rect mDirtyRect;
    private boolean mDisambiguateSwipe;
    private int mDistances[];
    private int mDownKey;
    private long mDownTime;
    private boolean mDrawPending;
    private GestureDetector mGestureDetector;
    Handler mHandler;
    private boolean mHeadsetRequiredToHearPasswordsAnnounced;
    private boolean mInMultiTap;
    private Keyboard.Key mInvalidatedKey;
    private Drawable mKeyBackground;
    private int mKeyIndices[];
    private int mKeyTextColor;
    private int mKeyTextSize;
    private Keyboard mKeyboard;
    private OnKeyboardActionListener mKeyboardActionListener;
    private boolean mKeyboardChanged;
    private Keyboard.Key mKeys[];
    private int mLabelTextSize;
    private int mLastCodeX;
    private int mLastCodeY;
    private int mLastKey;
    private long mLastKeyTime;
    private long mLastMoveTime;
    private int mLastSentIndex;
    private long mLastTapTime;
    private int mLastX;
    private int mLastY;
    private KeyboardView mMiniKeyboard;
    private Map mMiniKeyboardCache;
    private View mMiniKeyboardContainer;
    private int mMiniKeyboardOffsetX;
    private int mMiniKeyboardOffsetY;
    private boolean mMiniKeyboardOnScreen;
    private int mOldPointerCount;
    private float mOldPointerX;
    private float mOldPointerY;
    private Rect mPadding;
    private Paint mPaint;
    private PopupWindow mPopupKeyboard;
    private int mPopupLayout;
    private View mPopupParent;
    private int mPopupPreviewX;
    private int mPopupPreviewY;
    private int mPopupX;
    private int mPopupY;
    private boolean mPossiblePoly;
    private boolean mPreviewCentered;
    private int mPreviewHeight;
    private StringBuilder mPreviewLabel;
    private int mPreviewOffset;
    private PopupWindow mPreviewPopup;
    private TextView mPreviewText;
    private int mPreviewTextSizeLarge;
    private boolean mProximityCorrectOn;
    private int mProximityThreshold;
    private int mRepeatKeyIndex;
    private int mShadowColor;
    private float mShadowRadius;
    private boolean mShowPreview;
    private boolean mShowTouchPoints;
    private int mStartX;
    private int mStartY;
    private int mSwipeThreshold;
    private SwipeTracker mSwipeTracker;
    private int mTapCount;
    private int mVerticalCorrection;

    static 
    {
        MAX_NEARBY_KEYS = 12;
    }
}
