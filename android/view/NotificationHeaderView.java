// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import com.android.internal.widget.CachingIconView;
import java.util.ArrayList;

// Referenced classes of package android.view:
//            ViewGroup, View, ViewOutlineProvider, ViewConfiguration, 
//            MotionEvent

public class NotificationHeaderView extends ViewGroup
{
    public class HeaderTouchListener
        implements View.OnTouchListener
    {

        static boolean _2D_wrap0(HeaderTouchListener headertouchlistener, float f, float f1)
        {
            return headertouchlistener.isInside(f, f1);
        }

        private Rect addRectAroundView(View view)
        {
            view = getRectAroundView(view);
            mTouchRects.add(view);
            return view;
        }

        private void addWidthRect()
        {
            Rect rect = new Rect();
            rect.top = 0;
            rect.bottom = (int)(getResources().getDisplayMetrics().density * 32F);
            rect.left = 0;
            rect.right = getWidth();
            mTouchRects.add(rect);
        }

        private Rect getRectAroundView(View view)
        {
            float f = 48F * getResources().getDisplayMetrics().density;
            Rect rect = new Rect();
            if(view.getVisibility() == 8)
            {
                view = NotificationHeaderView._2D_wrap0(NotificationHeaderView.this);
                rect.left = (int)((float)view.getLeft() - f / 2.0F);
            } else
            {
                rect.left = (int)((float)(view.getLeft() + view.getRight()) / 2.0F - f / 2.0F);
            }
            rect.top = (int)((float)(view.getTop() + view.getBottom()) / 2.0F - f / 2.0F);
            rect.bottom = (int)((float)rect.top + f);
            rect.right = (int)((float)rect.left + f);
            return rect;
        }

        private boolean isInside(float f, float f1)
        {
            if(NotificationHeaderView._2D_get0(NotificationHeaderView.this))
                return true;
            if(NotificationHeaderView._2D_get3(NotificationHeaderView.this))
                return mExpandButtonRect.contains((int)f, (int)f1);
            for(int i = 0; i < mTouchRects.size(); i++)
                if(((Rect)mTouchRects.get(i)).contains((int)f, (int)f1))
                    return true;

            return false;
        }

        public void bindTouchRects()
        {
            mTouchRects.clear();
            addRectAroundView(NotificationHeaderView._2D_get5(NotificationHeaderView.this));
            mExpandButtonRect = addRectAroundView(NotificationHeaderView._2D_get2(NotificationHeaderView.this));
            addWidthRect();
            mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        }

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            float f;
            float f1;
            f = motionevent.getX();
            f1 = motionevent.getY();
            motionevent.getActionMasked() & 0xff;
            JVM INSTR tableswitch 0 2: default 44
        //                       0 49
        //                       1 134
        //                       2 82;
               goto _L1 _L2 _L3 _L4
_L1:
            return mTrackGesture;
_L2:
            mTrackGesture = false;
            if(isInside(f, f1))
            {
                mDownX = f;
                mDownY = f1;
                mTrackGesture = true;
                return true;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if(mTrackGesture && (Math.abs(mDownX - f) > (float)mTouchSlop || Math.abs(mDownY - f1) > (float)mTouchSlop))
                mTrackGesture = false;
            continue; /* Loop/switch isn't completed */
_L3:
            if(mTrackGesture)
                NotificationHeaderView._2D_get2(NotificationHeaderView.this).performClick();
            if(true) goto _L1; else goto _L5
_L5:
        }

        private float mDownX;
        private float mDownY;
        private Rect mExpandButtonRect;
        private final ArrayList mTouchRects = new ArrayList();
        private int mTouchSlop;
        private boolean mTrackGesture;
        final NotificationHeaderView this$0;

        public HeaderTouchListener()
        {
            this$0 = NotificationHeaderView.this;
            super();
        }
    }


    static boolean _2D_get0(NotificationHeaderView notificationheaderview)
    {
        return notificationheaderview.mAcceptAllTouches;
    }

    static Drawable _2D_get1(NotificationHeaderView notificationheaderview)
    {
        return notificationheaderview.mBackground;
    }

    static ImageView _2D_get2(NotificationHeaderView notificationheaderview)
    {
        return notificationheaderview.mExpandButton;
    }

    static boolean _2D_get3(NotificationHeaderView notificationheaderview)
    {
        return notificationheaderview.mExpandOnlyOnButton;
    }

    static int _2D_get4(NotificationHeaderView notificationheaderview)
    {
        return notificationheaderview.mHeaderBackgroundHeight;
    }

    static CachingIconView _2D_get5(NotificationHeaderView notificationheaderview)
    {
        return notificationheaderview.mIcon;
    }

    static View _2D_wrap0(NotificationHeaderView notificationheaderview)
    {
        return notificationheaderview.getFirstChildNotGone();
    }

    public NotificationHeaderView(Context context)
    {
        this(context, null);
    }

    public NotificationHeaderView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public NotificationHeaderView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public NotificationHeaderView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mTouchListener = new HeaderTouchListener();
        context = getResources();
        mChildMinWidth = context.getDimensionPixelSize(0x1050120);
        mContentEndMargin = context.getDimensionPixelSize(0x1050109);
        mHeaderBackgroundHeight = context.getDimensionPixelSize(0x1050116);
        mEntireHeaderClickable = context.getBoolean(0x1120084);
    }

    private View getFirstChildNotGone()
    {
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 8)
                return view;
        }

        return this;
    }

    private void updateExpandButton()
    {
        int i;
        int j;
        if(mExpanded)
        {
            i = 0x1080329;
            j = 0x10401e9;
        } else
        {
            i = 0x1080361;
            j = 0x10401e8;
        }
        mExpandButton.setImageDrawable(getContext().getDrawable(i));
        mExpandButton.setColorFilter(mOriginalNotificationColor);
        mExpandButton.setContentDescription(mContext.getText(j));
    }

    private void updateTouchListener()
    {
        if(mExpandClickListener != null)
            mTouchListener.bindTouchRects();
    }

    protected void drawableStateChanged()
    {
        if(mBackground != null && mBackground.isStateful())
            mBackground.setState(getDrawableState());
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeset);
    }

    public ImageView getExpandButton()
    {
        return mExpandButton;
    }

    public CachingIconView getIcon()
    {
        return mIcon;
    }

    public int getOriginalIconColor()
    {
        return mIconColor;
    }

    public int getOriginalNotificationColor()
    {
        return mOriginalNotificationColor;
    }

    public View getWorkProfileIcon()
    {
        return mProfileBadge;
    }

    public boolean hasOverlappingRendering()
    {
        return false;
    }

    public boolean isInTouchRect(float f, float f1)
    {
        if(mExpandClickListener == null)
            return false;
        else
            return HeaderTouchListener._2D_wrap0(mTouchListener, f, f1);
    }

    protected void onDraw(Canvas canvas)
    {
        if(mBackground != null)
        {
            mBackground.setBounds(0, 0, getWidth(), mHeaderBackgroundHeight);
            mBackground.draw(canvas);
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mAppName = findViewById(0x10201ab);
        mHeaderText = findViewById(0x1020282);
        mExpandButton = (ImageView)findViewById(0x1020238);
        mIcon = (CachingIconView)findViewById(0x1020006);
        mProfileBadge = findViewById(0x102038e);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        i = getPaddingStart();
        j = getMeasuredWidth();
        int i1 = getChildCount();
        int j1 = getMeasuredHeight();
        int k1 = getPaddingTop();
        int l1 = getPaddingBottom();
        int i2 = 0;
        while(i2 < i1) 
        {
            View view = getChildAt(i2);
            if(view.getVisibility() != 8)
            {
                int j2 = view.getMeasuredHeight();
                ViewGroup.MarginLayoutParams marginlayoutparams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
                int k2 = i + marginlayoutparams.getMarginStart();
                int l2 = k2 + view.getMeasuredWidth();
                int i3 = (int)((float)getPaddingTop() + (float)(j1 - k1 - l1 - j2) / 2.0F);
                int j3 = l2;
                i = j;
                l = k2;
                k = j3;
                if(view == mExpandButton)
                {
                    i = j;
                    l = k2;
                    k = j3;
                    if(mShowExpandButtonAtEnd)
                    {
                        k = j - mContentEndMargin;
                        l = k - view.getMeasuredWidth();
                        i = l;
                    }
                }
                j = i;
                if(view == mProfileBadge)
                {
                    j = getPaddingEnd();
                    if(mShowWorkBadgeAtEnd)
                        j = mContentEndMargin;
                    k = i - j;
                    l = k - view.getMeasuredWidth();
                    j = l;
                }
                k2 = l;
                i = k;
                if(getLayoutDirection() == 1)
                {
                    k2 = getWidth() - k;
                    i = getWidth() - l;
                }
                view.layout(k2, i3, i, i3 + j2);
                i = l2 + marginlayoutparams.getMarginEnd();
            }
            i2++;
        }
        updateTouchListener();
    }

    protected void onMeasure(int i, int j)
    {
        int k = View.MeasureSpec.getSize(i);
        int l = View.MeasureSpec.getSize(j);
        int i1 = View.MeasureSpec.makeMeasureSpec(k, 0x80000000);
        int k1 = View.MeasureSpec.makeMeasureSpec(l, 0x80000000);
        j = getPaddingStart() + getPaddingEnd();
        i = 0;
        while(i < getChildCount()) 
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 8)
            {
                ViewGroup.MarginLayoutParams marginlayoutparams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
                view.measure(getChildMeasureSpec(i1, marginlayoutparams.leftMargin + marginlayoutparams.rightMargin, marginlayoutparams.width), getChildMeasureSpec(k1, marginlayoutparams.topMargin + marginlayoutparams.bottomMargin, marginlayoutparams.height));
                j += marginlayoutparams.leftMargin + marginlayoutparams.rightMargin + view.getMeasuredWidth();
            }
            i++;
        }
        if(j > k)
        {
            j -= k;
            int j1 = mAppName.getMeasuredWidth();
            i = j;
            if(j > 0)
            {
                i = j;
                if(mAppName.getVisibility() != 8)
                {
                    i = j;
                    if(j1 > mChildMinWidth)
                    {
                        i = j1 - Math.min(j1 - mChildMinWidth, j);
                        int l1 = View.MeasureSpec.makeMeasureSpec(i, 0x80000000);
                        mAppName.measure(l1, k1);
                        i = j - (j1 - i);
                    }
                }
            }
            if(i > 0 && mHeaderText.getVisibility() != 8)
            {
                i = View.MeasureSpec.makeMeasureSpec(Math.max(0, mHeaderText.getMeasuredWidth() - i), 0x80000000);
                mHeaderText.measure(i, k1);
            }
        }
        setMeasuredDimension(k, l);
    }

    public void setAcceptAllTouches(boolean flag)
    {
        if(mEntireHeaderClickable)
            flag = true;
        mAcceptAllTouches = flag;
    }

    public void setExpandOnlyOnButton(boolean flag)
    {
        mExpandOnlyOnButton = flag;
    }

    public void setExpanded(boolean flag)
    {
        mExpanded = flag;
        updateExpandButton();
    }

    public void setHeaderBackgroundDrawable(Drawable drawable)
    {
        if(drawable != null)
        {
            setWillNotDraw(false);
            mBackground = drawable;
            mBackground.setCallback(this);
            setOutlineProvider(mProvider);
        } else
        {
            setWillNotDraw(true);
            mBackground = null;
            setOutlineProvider(null);
        }
        invalidate();
    }

    public void setOnClickListener(View.OnClickListener onclicklistener)
    {
        Object obj = null;
        mExpandClickListener = onclicklistener;
        onclicklistener = obj;
        if(mExpandClickListener != null)
            onclicklistener = mTouchListener;
        setOnTouchListener(onclicklistener);
        mExpandButton.setOnClickListener(mExpandClickListener);
        updateTouchListener();
    }

    public void setOriginalIconColor(int i)
    {
        mIconColor = i;
    }

    public void setOriginalNotificationColor(int i)
    {
        mOriginalNotificationColor = i;
    }

    public void setShowExpandButtonAtEnd(boolean flag)
    {
        if(flag != mShowExpandButtonAtEnd)
        {
            setClipToPadding(flag ^ true);
            mShowExpandButtonAtEnd = flag;
        }
    }

    public void setShowWorkBadgeAtEnd(boolean flag)
    {
        if(flag != mShowWorkBadgeAtEnd)
        {
            setClipToPadding(flag ^ true);
            mShowWorkBadgeAtEnd = flag;
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!super.verifyDrawable(drawable))
            if(drawable == mBackground)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static final int NO_COLOR = 1;
    private boolean mAcceptAllTouches;
    private View mAppName;
    private Drawable mBackground;
    private final int mChildMinWidth;
    private final int mContentEndMargin;
    private boolean mEntireHeaderClickable;
    private ImageView mExpandButton;
    private View.OnClickListener mExpandClickListener;
    private boolean mExpandOnlyOnButton;
    private boolean mExpanded;
    private int mHeaderBackgroundHeight;
    private View mHeaderText;
    private CachingIconView mIcon;
    private int mIconColor;
    private View mInfo;
    private int mOriginalNotificationColor;
    private View mProfileBadge;
    ViewOutlineProvider mProvider = new ViewOutlineProvider() {

        public void getOutline(View view, Outline outline)
        {
            if(NotificationHeaderView._2D_get1(NotificationHeaderView.this) != null)
            {
                outline.setRect(0, 0, getWidth(), NotificationHeaderView._2D_get4(NotificationHeaderView.this));
                outline.setAlpha(1.0F);
            }
        }

        final NotificationHeaderView this$0;

            
            {
                this$0 = NotificationHeaderView.this;
                super();
            }
    }
;
    private boolean mShowExpandButtonAtEnd;
    private boolean mShowWorkBadgeAtEnd;
    private HeaderTouchListener mTouchListener;
}
