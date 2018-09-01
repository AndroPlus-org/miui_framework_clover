// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.widget:
//            FrameLayout, TabWidget, TextView, ImageView

public class TabHost extends FrameLayout
    implements android.view.ViewTreeObserver.OnTouchModeChangeListener
{
    private static interface ContentStrategy
    {

        public abstract View getContentView();

        public abstract void tabClosed();
    }

    private class FactoryContentStrategy
        implements ContentStrategy
    {

        public View getContentView()
        {
            if(mTabContent == null)
                mTabContent = mFactory.createTabContent(mTag.toString());
            mTabContent.setVisibility(0);
            return mTabContent;
        }

        public void tabClosed()
        {
            mTabContent.setVisibility(8);
        }

        private TabContentFactory mFactory;
        private View mTabContent;
        private final CharSequence mTag;
        final TabHost this$0;

        public FactoryContentStrategy(CharSequence charsequence, TabContentFactory tabcontentfactory)
        {
            this$0 = TabHost.this;
            super();
            mTag = charsequence;
            mFactory = tabcontentfactory;
        }
    }

    private static interface IndicatorStrategy
    {

        public abstract View createIndicatorView();
    }

    private class IntentContentStrategy
        implements ContentStrategy
    {

        public View getContentView()
        {
            if(mLocalActivityManager == null)
                throw new IllegalStateException("Did you forget to call 'public void setup(LocalActivityManager activityGroup)'?");
            Object obj = mLocalActivityManager.startActivity(mTag, mIntent);
            if(obj != null)
                obj = ((Window) (obj)).getDecorView();
            else
                obj = null;
            if(mLaunchedView != obj && mLaunchedView != null && mLaunchedView.getParent() != null)
                TabHost._2D_get0(TabHost.this).removeView(mLaunchedView);
            mLaunchedView = ((View) (obj));
            if(mLaunchedView != null)
            {
                mLaunchedView.setVisibility(0);
                mLaunchedView.setFocusableInTouchMode(true);
                ((ViewGroup)mLaunchedView).setDescendantFocusability(0x40000);
            }
            return mLaunchedView;
        }

        public void tabClosed()
        {
            if(mLaunchedView != null)
                mLaunchedView.setVisibility(8);
        }

        private final Intent mIntent;
        private View mLaunchedView;
        private final String mTag;
        final TabHost this$0;

        private IntentContentStrategy(String s, Intent intent)
        {
            this$0 = TabHost.this;
            super();
            mTag = s;
            mIntent = intent;
        }

        IntentContentStrategy(String s, Intent intent, IntentContentStrategy intentcontentstrategy)
        {
            this(s, intent);
        }
    }

    private class LabelAndIconIndicatorStrategy
        implements IndicatorStrategy
    {

        public View createIndicatorView()
        {
            Context context = getContext();
            View view = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(TabHost._2D_get1(TabHost.this), TabHost._2D_get2(TabHost.this), false);
            TextView textview = (TextView)view.findViewById(0x1020016);
            ImageView imageview = (ImageView)view.findViewById(0x1020006);
            boolean flag;
            boolean flag1;
            if(imageview.getVisibility() == 8)
                flag = true;
            else
                flag = false;
            if(flag)
                flag1 = TextUtils.isEmpty(mLabel);
            else
                flag1 = true;
            textview.setText(mLabel);
            if(flag1 && mIcon != null)
            {
                imageview.setImageDrawable(mIcon);
                imageview.setVisibility(0);
            }
            if(context.getApplicationInfo().targetSdkVersion <= 4)
            {
                view.setBackgroundResource(0x1080830);
                textview.setTextColor(context.getColorStateList(0x1060159));
            }
            return view;
        }

        private final Drawable mIcon;
        private final CharSequence mLabel;
        final TabHost this$0;

        private LabelAndIconIndicatorStrategy(CharSequence charsequence, Drawable drawable)
        {
            this$0 = TabHost.this;
            super();
            mLabel = charsequence;
            mIcon = drawable;
        }

        LabelAndIconIndicatorStrategy(CharSequence charsequence, Drawable drawable, LabelAndIconIndicatorStrategy labelandiconindicatorstrategy)
        {
            this(charsequence, drawable);
        }
    }

    private class LabelIndicatorStrategy
        implements IndicatorStrategy
    {

        public View createIndicatorView()
        {
            Context context = getContext();
            View view = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(TabHost._2D_get1(TabHost.this), TabHost._2D_get2(TabHost.this), false);
            TextView textview = (TextView)view.findViewById(0x1020016);
            textview.setText(mLabel);
            if(context.getApplicationInfo().targetSdkVersion <= 4)
            {
                view.setBackgroundResource(0x1080830);
                textview.setTextColor(context.getColorStateList(0x1060159));
            }
            return view;
        }

        private final CharSequence mLabel;
        final TabHost this$0;

        private LabelIndicatorStrategy(CharSequence charsequence)
        {
            this$0 = TabHost.this;
            super();
            mLabel = charsequence;
        }

        LabelIndicatorStrategy(CharSequence charsequence, LabelIndicatorStrategy labelindicatorstrategy)
        {
            this(charsequence);
        }
    }

    public static interface OnTabChangeListener
    {

        public abstract void onTabChanged(String s);
    }

    public static interface TabContentFactory
    {

        public abstract View createTabContent(String s);
    }

    public class TabSpec
    {

        static ContentStrategy _2D_get0(TabSpec tabspec)
        {
            return tabspec.mContentStrategy;
        }

        static IndicatorStrategy _2D_get1(TabSpec tabspec)
        {
            return tabspec.mIndicatorStrategy;
        }

        public String getTag()
        {
            return mTag;
        }

        public TabSpec setContent(int i)
        {
            mContentStrategy = new ViewIdContentStrategy(i, null);
            return this;
        }

        public TabSpec setContent(Intent intent)
        {
            mContentStrategy = new IntentContentStrategy(mTag, intent, null);
            return this;
        }

        public TabSpec setContent(TabContentFactory tabcontentfactory)
        {
            mContentStrategy = new FactoryContentStrategy(mTag, tabcontentfactory);
            return this;
        }

        public TabSpec setIndicator(View view)
        {
            mIndicatorStrategy = new ViewIndicatorStrategy(view, null);
            return this;
        }

        public TabSpec setIndicator(CharSequence charsequence)
        {
            mIndicatorStrategy = new LabelIndicatorStrategy(charsequence, null);
            return this;
        }

        public TabSpec setIndicator(CharSequence charsequence, Drawable drawable)
        {
            mIndicatorStrategy = new LabelAndIconIndicatorStrategy(charsequence, drawable, null);
            return this;
        }

        private ContentStrategy mContentStrategy;
        private IndicatorStrategy mIndicatorStrategy;
        private final String mTag;
        final TabHost this$0;

        private TabSpec(String s)
        {
            this$0 = TabHost.this;
            super();
            mTag = s;
        }

        TabSpec(String s, TabSpec tabspec)
        {
            this(s);
        }
    }

    private class ViewIdContentStrategy
        implements ContentStrategy
    {

        public View getContentView()
        {
            mView.setVisibility(0);
            return mView;
        }

        public void tabClosed()
        {
            mView.setVisibility(8);
        }

        private final View mView;
        final TabHost this$0;

        private ViewIdContentStrategy(int i)
        {
            this$0 = TabHost.this;
            super();
            mView = TabHost._2D_get0(TabHost.this).findViewById(i);
            if(mView != null)
            {
                mView.setVisibility(8);
                return;
            } else
            {
                throw new RuntimeException((new StringBuilder()).append("Could not create tab content because could not find view with id ").append(i).toString());
            }
        }

        ViewIdContentStrategy(int i, ViewIdContentStrategy viewidcontentstrategy)
        {
            this(i);
        }
    }

    private class ViewIndicatorStrategy
        implements IndicatorStrategy
    {

        public View createIndicatorView()
        {
            return mView;
        }

        private final View mView;
        final TabHost this$0;

        private ViewIndicatorStrategy(View view)
        {
            this$0 = TabHost.this;
            super();
            mView = view;
        }

        ViewIndicatorStrategy(View view, ViewIndicatorStrategy viewindicatorstrategy)
        {
            this(view);
        }
    }


    static FrameLayout _2D_get0(TabHost tabhost)
    {
        return tabhost.mTabContent;
    }

    static int _2D_get1(TabHost tabhost)
    {
        return tabhost.mTabLayoutId;
    }

    static TabWidget _2D_get2(TabHost tabhost)
    {
        return tabhost.mTabWidget;
    }

    public TabHost(Context context)
    {
        super(context);
        mTabSpecs = new ArrayList(2);
        mCurrentTab = -1;
        mCurrentView = null;
        mLocalActivityManager = null;
        initTabHost();
    }

    public TabHost(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010083);
    }

    public TabHost(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TabHost(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset);
        mTabSpecs = new ArrayList(2);
        mCurrentTab = -1;
        mCurrentView = null;
        mLocalActivityManager = null;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TabWidget, i, j);
        mTabLayoutId = context.getResourceId(4, 0);
        context.recycle();
        if(mTabLayoutId == 0)
            mTabLayoutId = 0x1090101;
        initTabHost();
    }

    private int getTabWidgetLocation()
    {
        mTabWidget.getOrientation();
        JVM INSTR tableswitch 1 1: default 24
    //                   1 45;
           goto _L1 _L2
_L1:
        byte byte0;
        if(mTabContent.getTop() < mTabWidget.getTop())
            byte0 = 3;
        else
            byte0 = 1;
_L4:
        return byte0;
_L2:
        if(mTabContent.getLeft() < mTabWidget.getLeft())
            byte0 = 2;
        else
            byte0 = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initTabHost()
    {
        setFocusableInTouchMode(true);
        setDescendantFocusability(0x40000);
        mCurrentTab = -1;
        mCurrentView = null;
    }

    private void invokeOnTabChangeListener()
    {
        if(mOnTabChangeListener != null)
            mOnTabChangeListener.onTabChanged(getCurrentTabTag());
    }

    public void addTab(TabSpec tabspec)
    {
        if(TabSpec._2D_get1(tabspec) == null)
            throw new IllegalArgumentException("you must specify a way to create the tab indicator.");
        if(TabSpec._2D_get0(tabspec) == null)
            throw new IllegalArgumentException("you must specify a way to create the tab content");
        View view = TabSpec._2D_get1(tabspec).createIndicatorView();
        view.setOnKeyListener(mTabKeyListener);
        if(TabSpec._2D_get1(tabspec) instanceof ViewIndicatorStrategy)
            mTabWidget.setStripEnabled(false);
        mTabWidget.addView(view);
        mTabSpecs.add(tabspec);
        if(mCurrentTab == -1)
            setCurrentTab(0);
    }

    public void clearAllTabs()
    {
        mTabWidget.removeAllViews();
        initTabHost();
        mTabContent.removeAllViews();
        mTabSpecs.clear();
        requestLayout();
        invalidate();
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        boolean flag = super.dispatchKeyEvent(keyevent);
        if(flag || keyevent.getAction() != 0 || mCurrentView == null || !mCurrentView.isRootNamespace() || !mCurrentView.hasFocus()) goto _L2; else goto _L1
_L1:
        getTabWidgetLocation();
        JVM INSTR tableswitch 0 3: default 80
    //                   0 136
    //                   1 80
    //                   2 149
    //                   3 162;
           goto _L3 _L4 _L3 _L5 _L6
_L3:
        byte byte0;
        char c;
        byte byte1;
        byte0 = 19;
        c = '!';
        byte1 = 2;
_L7:
        if(keyevent.getKeyCode() == byte0 && mCurrentView.findFocus().focusSearch(c) == null)
        {
            mTabWidget.getChildTabViewAt(mCurrentTab).requestFocus();
            playSoundEffect(byte1);
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        byte0 = 21;
        c = '\021';
        byte1 = 1;
        continue; /* Loop/switch isn't completed */
_L5:
        byte0 = 22;
        c = 'B';
        byte1 = 3;
        continue; /* Loop/switch isn't completed */
_L6:
        byte0 = 20;
        c = '\202';
        byte1 = 4;
        if(true) goto _L7; else goto _L2
_L2:
        return flag;
    }

    public void dispatchWindowFocusChanged(boolean flag)
    {
        if(mCurrentView != null)
            mCurrentView.dispatchWindowFocusChanged(flag);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/TabHost.getName();
    }

    public int getCurrentTab()
    {
        return mCurrentTab;
    }

    public String getCurrentTabTag()
    {
        if(mCurrentTab >= 0 && mCurrentTab < mTabSpecs.size())
            return ((TabSpec)mTabSpecs.get(mCurrentTab)).getTag();
        else
            return null;
    }

    public View getCurrentTabView()
    {
        if(mCurrentTab >= 0 && mCurrentTab < mTabSpecs.size())
            return mTabWidget.getChildTabViewAt(mCurrentTab);
        else
            return null;
    }

    public View getCurrentView()
    {
        return mCurrentView;
    }

    public FrameLayout getTabContentView()
    {
        return mTabContent;
    }

    public TabWidget getTabWidget()
    {
        return mTabWidget;
    }

    public TabSpec newTabSpec(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("tag must be non-null");
        else
            return new TabSpec(s, null);
    }

    public void onTouchModeChanged(boolean flag)
    {
    }

    public void sendAccessibilityEventInternal(int i)
    {
    }

    public void setCurrentTab(int i)
    {
        if(i < 0 || i >= mTabSpecs.size())
            return;
        if(i == mCurrentTab)
            return;
        if(mCurrentTab != -1)
            TabSpec._2D_get0((TabSpec)mTabSpecs.get(mCurrentTab)).tabClosed();
        mCurrentTab = i;
        TabSpec tabspec = (TabSpec)mTabSpecs.get(i);
        mTabWidget.focusCurrentTab(mCurrentTab);
        mCurrentView = TabSpec._2D_get0(tabspec).getContentView();
        if(mCurrentView.getParent() == null)
            mTabContent.addView(mCurrentView, new android.view.ViewGroup.LayoutParams(-1, -1));
        if(!mTabWidget.hasFocus())
            mCurrentView.requestFocus();
        invokeOnTabChangeListener();
    }

    public void setCurrentTabByTag(String s)
    {
        int i = 0;
        int j = mTabSpecs.size();
        do
        {
label0:
            {
                if(i < j)
                {
                    if(!((TabSpec)mTabSpecs.get(i)).getTag().equals(s))
                        break label0;
                    setCurrentTab(i);
                }
                return;
            }
            i++;
        } while(true);
    }

    public void setOnTabChangedListener(OnTabChangeListener ontabchangelistener)
    {
        mOnTabChangeListener = ontabchangelistener;
    }

    public void setup()
    {
        mTabWidget = (TabWidget)findViewById(0x1020013);
        if(mTabWidget == null)
            throw new RuntimeException("Your TabHost must have a TabWidget whose id attribute is 'android.R.id.tabs'");
        mTabKeyListener = new android.view.View.OnKeyListener() {

            public boolean onKey(View view, int i, KeyEvent keyevent)
            {
                if(KeyEvent.isModifierKey(i))
                    return false;
                switch(i)
                {
                default:
                    TabHost._2D_get0(TabHost.this).requestFocus(2);
                    return TabHost._2D_get0(TabHost.this).dispatchKeyEvent(keyevent);

                case 19: // '\023'
                case 20: // '\024'
                case 21: // '\025'
                case 22: // '\026'
                case 23: // '\027'
                case 61: // '='
                case 62: // '>'
                case 66: // 'B'
                    return false;
                }
            }

            final TabHost this$0;

            
            {
                this$0 = TabHost.this;
                super();
            }
        }
;
        mTabWidget.setTabSelectionListener(new TabWidget.OnTabSelectionChanged() {

            public void onTabSelectionChanged(int i, boolean flag)
            {
                setCurrentTab(i);
                if(flag)
                    TabHost._2D_get0(TabHost.this).requestFocus(2);
            }

            final TabHost this$0;

            
            {
                this$0 = TabHost.this;
                super();
            }
        }
);
        mTabContent = (FrameLayout)findViewById(0x1020011);
        if(mTabContent == null)
            throw new RuntimeException("Your TabHost must have a FrameLayout whose id attribute is 'android.R.id.tabcontent'");
        else
            return;
    }

    public void setup(LocalActivityManager localactivitymanager)
    {
        setup();
        mLocalActivityManager = localactivitymanager;
    }

    private static final int TABWIDGET_LOCATION_BOTTOM = 3;
    private static final int TABWIDGET_LOCATION_LEFT = 0;
    private static final int TABWIDGET_LOCATION_RIGHT = 2;
    private static final int TABWIDGET_LOCATION_TOP = 1;
    protected int mCurrentTab;
    private View mCurrentView;
    protected LocalActivityManager mLocalActivityManager;
    private OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mTabContent;
    private android.view.View.OnKeyListener mTabKeyListener;
    private int mTabLayoutId;
    private List mTabSpecs;
    private TabWidget mTabWidget;
}
