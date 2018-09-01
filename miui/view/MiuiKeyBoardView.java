// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public class MiuiKeyBoardView extends FrameLayout
    implements android.view.View.OnClickListener, android.view.View.OnTouchListener
{
    public static class KeyButton extends TextView
    {

        public void layout(int i, int j, int k, int l)
        {
            measure(android.view.View.MeasureSpec.makeMeasureSpec(k - i, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(l - j, 0x40000000));
            super.layout(i, j, k, l);
        }

        protected void onFinishInflate()
        {
            if(getTag() instanceof String)
                setText((String)getTag());
            super.onFinishInflate();
        }

        public KeyButton(Context context)
        {
            super(context);
        }

        public KeyButton(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public KeyButton(Context context, AttributeSet attributeset, int i)
        {
            super(context, attributeset, i);
        }
    }

    public static interface OnKeyboardActionListener
    {

        public abstract void onKeyBoardDelete();

        public abstract void onKeyBoardOK();

        public abstract void onText(CharSequence charsequence);
    }


    static TextView _2D_get0(MiuiKeyBoardView miuikeyboardview)
    {
        return miuikeyboardview.mPreviewText;
    }

    static void _2D_wrap0(MiuiKeyBoardView miuikeyboardview)
    {
        miuikeyboardview.onKeyBoardDelete();
    }

    static void _2D_wrap1(MiuiKeyBoardView miuikeyboardview, boolean flag)
    {
        miuikeyboardview.showPreviewAnim(flag);
    }

    public MiuiKeyBoardView(Context context)
    {
        this(context, null);
    }

    public MiuiKeyBoardView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, -1);
    }

    public MiuiKeyBoardView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mAllKeys = new ArrayList();
        mIsUpperMode = false;
        mIsShowingPreview = false;
        mShowPreviewLastTime = 0L;
        mShowPreviewAnimator = new ValueAnimator();
        mStretchFromBottonAnimation = null;
        mShrinkToBottonAnimation = null;
        mSendDeleteActionRunnable = new Runnable() {

            public void run()
            {
                MiuiKeyBoardView._2D_wrap0(MiuiKeyBoardView.this);
                postDelayed(this, 50L);
            }

            final MiuiKeyBoardView this$0;

            
            {
                this$0 = MiuiKeyBoardView.this;
                super();
            }
        }
;
        mContext = context;
        View.inflate(mContext, 0x1103000c, this);
        View.inflate(mContext, 0x1103001b, this);
        View.inflate(mContext, 0x1103000b, this);
        setFocusableInTouchMode(true);
    }

    private void calcAndStartShowPreviewAnim(View view)
    {
        if(view instanceof KeyButton)
        {
            mPreviewText.setText(((KeyButton)view).getText());
            mPreviewText.setTypeface(Typeface.DEFAULT_BOLD);
            mPopupViewWidth = (int)((double)view.getWidth() * 1.7D);
            mPopupViewHeight = (int)((double)view.getHeight() * 1.3999999999999999D);
            mPreviewText.setWidth(mPopupViewWidth);
            mPreviewText.setHeight(mPopupViewHeight);
            float af[] = new float[2];
            getChildCoordRelativeToKeyboard(view, af, false, true);
            mPopupViewX = (int)(af[0] + (float)((view.getWidth() - mPopupViewWidth) / 2));
            mPopupViewY = (int)(af[1] - (float)mPopupViewHeight - (float)view.getHeight() * 0.17F);
            showPreviewAnim(true);
            mPreviewText.setVisibility(0);
        }
    }

    private float getChildCoordRelativeToKeyboard(View view, float af[], boolean flag, boolean flag1)
    {
        af[1] = 0.0F;
        af[0] = 0.0F;
        if(flag)
            view.getMatrix().mapPoints(af);
        float f = 1.0F * view.getScaleX();
        af[0] = af[0] + (float)view.getLeft();
        af[1] = af[1] + (float)view.getTop();
        for(Object obj = view.getParent(); (obj instanceof View) && obj != this;)
        {
            obj = (View)obj;
            float f1 = f;
            if(flag)
            {
                ((View) (obj)).getMatrix().mapPoints(af);
                f1 = f * ((View) (obj)).getScaleX();
            }
            af[0] = af[0] + (float)(((View) (obj)).getLeft() - ((View) (obj)).getScrollX());
            af[1] = af[1] + (float)(((View) (obj)).getTop() - ((View) (obj)).getScrollY());
            obj = ((View) (obj)).getParent();
            f = f1;
        }

        if(flag1)
        {
            af[0] = af[0] - ((float)view.getWidth() * (1.0F - f)) / 2.0F;
            af[1] = af[1] - ((float)view.getHeight() * (1.0F - f)) / 2.0F;
        }
        return f;
    }

    private void onKeyBoardDelete()
    {
        for(Iterator iterator = mKeyboardListeners.iterator(); iterator.hasNext(); ((OnKeyboardActionListener)iterator.next()).onKeyBoardDelete());
    }

    private void onKeyBoardOK()
    {
        for(Iterator iterator = mKeyboardListeners.iterator(); iterator.hasNext(); ((OnKeyboardActionListener)iterator.next()).onKeyBoardOK());
    }

    private void onText(CharSequence charsequence)
    {
        for(Iterator iterator = mKeyboardListeners.iterator(); iterator.hasNext(); ((OnKeyboardActionListener)iterator.next()).onText(charsequence));
    }

    private void setOnTouchAndClickListenerForKey(ViewGroup viewgroup)
    {
        int i = viewgroup.getChildCount();
        int j = 0;
        while(j < i) 
        {
            View view = viewgroup.getChildAt(j);
            if(view instanceof KeyButton)
            {
                view.setOnClickListener(this);
                view.setOnTouchListener(this);
                mAllKeys.add((KeyButton)view);
            } else
            if(view instanceof ViewGroup)
                setOnTouchAndClickListenerForKey((ViewGroup)view);
            j++;
        }
    }

    private void shiftLetterBoard()
    {
        Iterator iterator = mAllKeys.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            KeyButton keybutton = (KeyButton)iterator.next();
            if(keybutton.getTag() instanceof String)
            {
                String s = (String)keybutton.getTag();
                if(s.length() == 1 && Character.isLowerCase(s.toCharArray()[0]))
                {
                    if(mIsUpperMode)
                        s = s.toLowerCase();
                    else
                        s = s.toUpperCase();
                    keybutton.setText(s);
                }
            }
        } while(true);
        mIsUpperMode = mIsUpperMode ^ true;
        if(mIsUpperMode)
            mBtnCapsLock.setBackgroundResource(0x11020072);
        else
            mBtnCapsLock.setBackgroundResource(0x11020071);
    }

    private void showLetterBoard(boolean flag)
    {
        byte byte0 = 4;
        FrameLayout framelayout = mLetterBoard;
        int i;
        if(flag)
            i = 0;
        else
            i = 4;
        framelayout.setVisibility(i);
        framelayout = mSymbolBoard;
        if(flag)
            i = byte0;
        else
            i = 0;
        framelayout.setVisibility(i);
    }

    private void showPreviewAnim(boolean flag)
    {
        mShowPreviewAnimator.cancel();
        removeCallbacks(mConfirmHide);
        mShowPreviewAnimator.removeAllListeners();
        mShowPreviewAnimator.removeAllUpdateListeners();
        if(flag)
            mShowPreviewAnimator.setFloatValues(new float[] {
                0.0F, 1.0F
            });
        else
            mShowPreviewAnimator.setFloatValues(new float[] {
                1.0F, 0.0F
            });
        mShowPreviewAnimator.setDuration(100L);
        mPreviewText.setVisibility(0);
        mPreviewText.setPivotX((float)mPopupViewWidth * 0.5F);
        mPreviewText.setPivotY(mPopupViewHeight);
        mShowPreviewAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator)
            {
                float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
                MiuiKeyBoardView._2D_get0(MiuiKeyBoardView.this).setAlpha(f);
            }

            final MiuiKeyBoardView this$0;

            
            {
                this$0 = MiuiKeyBoardView.this;
                super();
            }
        }
);
        mShowPreviewAnimator.start();
        mIsShowingPreview = flag;
        if(flag)
            mShowPreviewLastTime = System.currentTimeMillis();
    }

    public void addKeyboardListener(OnKeyboardActionListener onkeyboardactionlistener)
    {
        for(Iterator iterator = mKeyboardListeners.iterator(); iterator.hasNext();)
            if(onkeyboardactionlistener.equals((OnKeyboardActionListener)iterator.next()))
                return;

        mKeyboardListeners.add(onkeyboardactionlistener);
    }

    public void hide()
    {
        startAnimation(mShrinkToBottonAnimation);
    }

    void keyboardOnLayout(ViewGroup viewgroup, int i, int j, int k, int l, int i1)
    {
        int j1 = SIZE_GROUP.length;
        int k1 = 0;
        int l1 = mPaddingTop;
        for(int i2 = 0; i2 < j1; i2++)
        {
            float af[] = SIZE_GROUP[i2];
            float f = 0.0F;
            for(int j2 = 0; j2 < af.length; j2++)
                f += af[j2] * (float)j;

            float f1 = (af.length - 1) * k;
            int k2 = (int)(((float)i - (f + f1)) / 2.0F);
            for(int l2 = 0; l2 < af.length; l2++)
            {
                KeyButton keybutton = (KeyButton)viewgroup.getChildAt(k1);
                int i3 = k2;
                int j3 = i3;
                if("!".equals(keybutton.getText()))
                    j3 = (int)((float)i3 + (float)j * (af[l2] - 1.0F));
                keybutton.layout(j3, l1, (int)((float)k2 + (float)j * af[l2]), l1 + l);
                k2 = (int)((float)k2 + ((float)j * af[l2] + (float)k));
                k1++;
            }

            l1 += i1 + l;
        }

    }

    protected void onAttachedToWindow()
    {
        if(getParent() != null)
            ((ViewGroup)getParent()).setClipChildren(false);
        super.onAttachedToWindow();
    }

    public void onClick(View view)
    {
        if(!isEnabled())
            return;
        if(view == mBtnCapsLock)
            shiftLetterBoard();
        else
        if(view == mBtnToSymbolBoard)
            showLetterBoard(false);
        else
        if(view == mBtnToLetterBoard)
            showLetterBoard(true);
        else
        if(view == mBtnLetterDelete || view == mBtnSymbolDelete)
            onKeyBoardDelete();
        else
        if(view == mBtnSymbolOK || view == mBtnLetterOK)
            onKeyBoardOK();
        else
        if(view == mBtnSymbolSpace || view == mBtnLetterSpace)
            onText(" ");
        else
            onText(((KeyButton)view).getText());
    }

    protected void onFinishInflate()
    {
        Resources resources = mContext.getResources();
        mPaddingTop = resources.getDimensionPixelSize(0x110b001e);
        mPaddingLeft = resources.getDimensionPixelSize(0x110b001d);
        mStretchFromBottonAnimation = AnimationUtils.loadAnimation(getContext(), 0x11040005);
        mShrinkToBottonAnimation = AnimationUtils.loadAnimation(getContext(), 0x11040004);
        mKeyboardListeners = new ArrayList();
        setClipChildren(false);
        setClipToPadding(false);
        mPreviewText = (TextView)findViewById(0x110c0023);
        mLetterBoard = (FrameLayout)findViewById(0x110c0024);
        mLetterBoard.setVisibility(0);
        mBtnCapsLock = findViewById(0x110c0025);
        mBtnLetterDelete = findViewById(0x110c0026);
        mBtnToSymbolBoard = findViewById(0x110c0027);
        mBtnLetterSpace = findViewById(0x110c0028);
        mBtnLetterOK = findViewById(0x110c0029);
        mSymbolBoard = (FrameLayout)findViewById(0x110c004e);
        mSymbolBoard.setVisibility(4);
        mBtnSymbolDelete = findViewById(0x110c004f);
        mBtnToLetterBoard = findViewById(0x110c0050);
        mBtnSymbolSpace = findViewById(0x110c0051);
        mBtnSymbolOK = findViewById(0x110c0052);
        setOnTouchAndClickListenerForKey(mLetterBoard);
        setOnTouchAndClickListenerForKey(mSymbolBoard);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = k - i;
        i = (int)((float)(((i1 - mPaddingLeft * 2) / SIZE_GROUP[0].length) * 1) / 1.2F);
        k = (int)((float)i * 0.2F);
        int j1 = (int)((float)(((l - j - mPaddingTop * 2) / SIZE_GROUP.length) * 1) / 1.17F);
        int k1 = (int)((float)j1 * 0.2F);
        mLetterBoard.layout(0, 0, i1, l - j);
        mSymbolBoard.layout(0, 0, i1, l - j);
        keyboardOnLayout(mLetterBoard, i1, i, k, j1, k1);
        keyboardOnLayout(mSymbolBoard, i1, i, k, j1, k1);
        mPreviewText.layout(mPopupViewX, mPopupViewY, mPopupViewX + mPopupViewWidth, mPopupViewY + mPopupViewHeight);
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if(!isEnabled()) goto _L2; else goto _L1
_L1:
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 40
    //                   0 42
    //                   1 102
    //                   2 40
    //                   3 102;
           goto _L2 _L3 _L4 _L2 _L4
_L2:
        return false;
_L3:
        if((view.getTag() instanceof String) && ((String)view.getTag()).length() == 1)
            calcAndStartShowPreviewAnim(view);
        if(view == mBtnLetterDelete || view == mBtnSymbolDelete)
            postDelayed(mSendDeleteActionRunnable, 500L);
        continue; /* Loop/switch isn't completed */
_L4:
        long l = 300L - (System.currentTimeMillis() - mShowPreviewLastTime);
        if(mIsShowingPreview)
        {
            motionevent = mConfirmHide;
            if(l <= 0L)
                l = 0L;
            postDelayed(motionevent, l);
        }
        if(view == mBtnLetterDelete || view == mBtnSymbolDelete)
            removeCallbacks(mSendDeleteActionRunnable);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        super.onTouchEvent(motionevent);
        return true;
    }

    public void removeKeyboardListener(OnKeyboardActionListener onkeyboardactionlistener)
    {
        mKeyboardListeners.remove(onkeyboardactionlistener);
    }

    public void show()
    {
        mLetterBoard.setVisibility(4);
        mSymbolBoard.setVisibility(0);
        if(mIsUpperMode)
            shiftLetterBoard();
        startAnimation(mStretchFromBottonAnimation);
    }

    private static final float FUNC_KEY_RATIO = 1.6F;
    private static final float HORIZONTAL_MARGIN_RATIO = 0.2F;
    private static final float OK_KEY_RATIO = 2.8F;
    private static final int PREVIEW_ANIMATION_DURATION = 100;
    private static final long SHOW_PREVIEW_DURATION = 300L;
    private static final float SIZE_GROUP[][] = {
        {
            1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F
        }, {
            1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F
        }, {
            1.6F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.6F
        }, {
            2.8F, 5.8F, 2.8F
        }
    };
    private static final float SPACE_KEY_RATIO = 5.8F;
    private static final String SPACE_STR = " ";
    private static final float VERTICAL_MARGIN_RATIO = 0.17F;
    private ArrayList mAllKeys;
    private View mBtnCapsLock;
    private View mBtnLetterDelete;
    private View mBtnLetterOK;
    private View mBtnLetterSpace;
    private View mBtnSymbolDelete;
    private View mBtnSymbolOK;
    private View mBtnSymbolSpace;
    private View mBtnToLetterBoard;
    private View mBtnToSymbolBoard;
    private Runnable mConfirmHide = new Runnable() {

        public void run()
        {
            MiuiKeyBoardView._2D_wrap1(MiuiKeyBoardView.this, false);
        }

        final MiuiKeyBoardView this$0;

            
            {
                this$0 = MiuiKeyBoardView.this;
                super();
            }
    }
;
    private Context mContext;
    private boolean mIsShowingPreview;
    private boolean mIsUpperMode;
    private ArrayList mKeyboardListeners;
    private FrameLayout mLetterBoard;
    private int mPopupViewHeight;
    private int mPopupViewWidth;
    private int mPopupViewX;
    private int mPopupViewY;
    private TextView mPreviewText;
    private final Runnable mSendDeleteActionRunnable;
    private ValueAnimator mShowPreviewAnimator;
    private long mShowPreviewLastTime;
    private Animation mShrinkToBottonAnimation;
    private Animation mStretchFromBottonAnimation;
    private FrameLayout mSymbolBoard;

}
