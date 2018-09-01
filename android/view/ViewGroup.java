// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.Transformation;
import java.util.*;
import java.util.function.Predicate;

// Referenced classes of package android.view:
//            View, ViewParent, ViewManager, MotionEvent, 
//            ViewRootImpl, FocusFinder, WindowInsets, DragEvent, 
//            ViewOverlay, InputEventConsistencyVerifier, ViewStructure, ViewHierarchyEncoder, 
//            ViewGroupOverlay, ViewOutlineProvider, RenderNode, PointerIcon, 
//            ActionMode, KeyEvent, Display, Menu, 
//            MenuInflater

public abstract class ViewGroup extends View
    implements ViewParent, ViewManager
{
    static class ChildListForAccessibility
    {

        private void clear()
        {
            mChildren.clear();
        }

        private void init(ViewGroup viewgroup, boolean flag)
        {
            ArrayList arraylist = mChildren;
            int i = viewgroup.getChildCount();
            for(int j = 0; j < i; j++)
                arraylist.add(viewgroup.getChildAt(j));

            if(flag)
            {
                ArrayList arraylist1 = mHolders;
                for(int k = 0; k < i; k++)
                    arraylist1.add(ViewLocationHolder.obtain(viewgroup, (View)arraylist.get(k)));

                sort(arraylist1);
                for(int l = 0; l < i; l++)
                {
                    viewgroup = (ViewLocationHolder)arraylist1.get(l);
                    arraylist.set(l, ((ViewLocationHolder) (viewgroup)).mView);
                    viewgroup.recycle();
                }

                arraylist1.clear();
            }
        }

        public static ChildListForAccessibility obtain(ViewGroup viewgroup, boolean flag)
        {
            ChildListForAccessibility childlistforaccessibility = (ChildListForAccessibility)sPool.acquire();
            ChildListForAccessibility childlistforaccessibility1 = childlistforaccessibility;
            if(childlistforaccessibility == null)
                childlistforaccessibility1 = new ChildListForAccessibility();
            childlistforaccessibility1.init(viewgroup, flag);
            return childlistforaccessibility1;
        }

        private void sort(ArrayList arraylist)
        {
            ViewLocationHolder.setComparisonStrategy(1);
            Collections.sort(arraylist);
_L1:
            return;
            IllegalArgumentException illegalargumentexception;
            illegalargumentexception;
            ViewLocationHolder.setComparisonStrategy(2);
            Collections.sort(arraylist);
              goto _L1
        }

        public View getChildAt(int i)
        {
            return (View)mChildren.get(i);
        }

        public int getChildCount()
        {
            return mChildren.size();
        }

        public void recycle()
        {
            clear();
            sPool.release(this);
        }

        private static final int MAX_POOL_SIZE = 32;
        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(32);
        private final ArrayList mChildren = new ArrayList();
        private final ArrayList mHolders = new ArrayList();


        ChildListForAccessibility()
        {
        }
    }

    static class ChildListForAutoFill extends ArrayList
    {

        public static ChildListForAutoFill obtain()
        {
            ChildListForAutoFill childlistforautofill = (ChildListForAutoFill)sPool.acquire();
            ChildListForAutoFill childlistforautofill1 = childlistforautofill;
            if(childlistforautofill == null)
                childlistforautofill1 = new ChildListForAutoFill();
            return childlistforautofill1;
        }

        public void recycle()
        {
            clear();
            sPool.release(this);
        }

        private static final int MAX_POOL_SIZE = 32;
        private static final android.util.Pools.SimplePool sPool = new android.util.Pools.SimplePool(32);


        ChildListForAutoFill()
        {
        }
    }

    private static final class HoverTarget
    {

        public static HoverTarget obtain(View view)
        {
            if(view == null)
                throw new IllegalArgumentException("child must be non-null");
            Object obj = sRecycleLock;
            obj;
            JVM INSTR monitorenter ;
            HoverTarget hovertarget;
            if(sRecycleBin != null)
                break MISSING_BLOCK_LABEL_43;
            hovertarget = new HoverTarget();
_L1:
            obj;
            JVM INSTR monitorexit ;
            hovertarget.child = view;
            return hovertarget;
            hovertarget = sRecycleBin;
            sRecycleBin = hovertarget.next;
            sRecycledCount--;
            hovertarget.next = null;
              goto _L1
            view;
            throw view;
        }

        public void recycle()
        {
            if(child == null)
                throw new IllegalStateException("already recycled once");
            Object obj = sRecycleLock;
            obj;
            JVM INSTR monitorenter ;
            if(sRecycledCount >= 32)
                break MISSING_BLOCK_LABEL_58;
            next = sRecycleBin;
            sRecycleBin = this;
            sRecycledCount++;
_L1:
            child = null;
            obj;
            JVM INSTR monitorexit ;
            return;
            next = null;
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        private static final int MAX_RECYCLED = 32;
        private static HoverTarget sRecycleBin;
        private static final Object sRecycleLock = new Object[0];
        private static int sRecycledCount;
        public View child;
        public HoverTarget next;


        private HoverTarget()
        {
        }
    }

    public static class LayoutParams
    {

        protected static String sizeToString(int i)
        {
            if(i == -2)
                return "wrap-content";
            if(i == -1)
                return "match-parent";
            else
                return String.valueOf(i);
        }

        public String debug(String s)
        {
            return (new StringBuilder()).append(s).append("ViewGroup.LayoutParams={ width=").append(sizeToString(width)).append(", height=").append(sizeToString(height)).append(" }").toString();
        }

        void encode(ViewHierarchyEncoder viewhierarchyencoder)
        {
            viewhierarchyencoder.beginObject(this);
            encodeProperties(viewhierarchyencoder);
            viewhierarchyencoder.endObject();
        }

        protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
        {
            viewhierarchyencoder.addProperty("width", width);
            viewhierarchyencoder.addProperty("height", height);
        }

        public void onDebugDraw(View view, Canvas canvas, Paint paint)
        {
        }

        public void resolveLayoutDirection(int i)
        {
        }

        protected void setBaseAttributes(TypedArray typedarray, int i, int j)
        {
            width = typedarray.getLayoutDimension(i, "layout_width");
            height = typedarray.getLayoutDimension(j, "layout_height");
        }

        public static final int FILL_PARENT = -1;
        public static final int MATCH_PARENT = -1;
        public static final int WRAP_CONTENT = -2;
        public int height;
        public android.view.animation.LayoutAnimationController.AnimationParameters layoutAnimationParameters;
        public int width;

        LayoutParams()
        {
        }

        public LayoutParams(int i, int j)
        {
            width = i;
            height = j;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewGroup_Layout);
            setBaseAttributes(context, 0, 1);
            context.recycle();
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            width = layoutparams.width;
            height = layoutparams.height;
        }
    }

    public static class MarginLayoutParams extends LayoutParams
    {

        private void doResolveMargins()
        {
            boolean flag;
            boolean flag1;
            flag = false;
            flag1 = false;
            if((mMarginFlags & 0x10) != 16) goto _L2; else goto _L1
_L1:
            if((mMarginFlags & 4) == 4 && startMargin > 0x80000000)
                leftMargin = startMargin;
            if((mMarginFlags & 8) == 8 && endMargin > 0x80000000)
                rightMargin = endMargin;
_L4:
            mMarginFlags = (byte)(mMarginFlags & 0xffffffdf);
            return;
_L2:
            switch(mMarginFlags & 3)
            {
            default:
                int i;
                if(startMargin > 0x80000000)
                    i = startMargin;
                else
                    i = 0;
                leftMargin = i;
                i = ((flag1) ? 1 : 0);
                if(endMargin > 0x80000000)
                    i = endMargin;
                rightMargin = i;
                break;

            case 1: // '\001'
                if(endMargin > 0x80000000)
                    i = endMargin;
                else
                    i = 0;
                leftMargin = i;
                i = ((flag) ? 1 : 0);
                if(startMargin > 0x80000000)
                    i = startMargin;
                rightMargin = i;
                break;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public final void copyMarginsFrom(MarginLayoutParams marginlayoutparams)
        {
            leftMargin = marginlayoutparams.leftMargin;
            topMargin = marginlayoutparams.topMargin;
            rightMargin = marginlayoutparams.rightMargin;
            bottomMargin = marginlayoutparams.bottomMargin;
            startMargin = marginlayoutparams.startMargin;
            endMargin = marginlayoutparams.endMargin;
            mMarginFlags = marginlayoutparams.mMarginFlags;
        }

        protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
        {
            super.encodeProperties(viewhierarchyencoder);
            viewhierarchyencoder.addProperty("leftMargin", leftMargin);
            viewhierarchyencoder.addProperty("topMargin", topMargin);
            viewhierarchyencoder.addProperty("rightMargin", rightMargin);
            viewhierarchyencoder.addProperty("bottomMargin", bottomMargin);
            viewhierarchyencoder.addProperty("startMargin", startMargin);
            viewhierarchyencoder.addProperty("endMargin", endMargin);
        }

        public int getLayoutDirection()
        {
            return mMarginFlags & 3;
        }

        public int getMarginEnd()
        {
            if(endMargin != 0x80000000)
                return endMargin;
            if((mMarginFlags & 0x20) == 32)
                doResolveMargins();
            switch(mMarginFlags & 3)
            {
            default:
                return rightMargin;

            case 1: // '\001'
                return leftMargin;
            }
        }

        public int getMarginStart()
        {
            if(startMargin != 0x80000000)
                return startMargin;
            if((mMarginFlags & 0x20) == 32)
                doResolveMargins();
            switch(mMarginFlags & 3)
            {
            default:
                return leftMargin;

            case 1: // '\001'
                return rightMargin;
            }
        }

        public boolean isLayoutRtl()
        {
            boolean flag = true;
            if((mMarginFlags & 3) != 1)
                flag = false;
            return flag;
        }

        public boolean isMarginRelative()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(startMargin == 0x80000000)
                if(endMargin != 0x80000000)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public void onDebugDraw(View view, Canvas canvas, Paint paint)
        {
            Insets insets;
            int i;
            int j;
            int k;
            if(ViewGroup.isLayoutModeOptical(view.mParent))
                insets = view.getOpticalInsets();
            else
                insets = Insets.NONE;
            i = view.getLeft();
            j = insets.left;
            k = view.getTop();
            ViewGroup._2D_wrap0(canvas, j + i, insets.top + k, view.getRight() - insets.right, view.getBottom() - insets.bottom, leftMargin, topMargin, rightMargin, bottomMargin, paint);
        }

        public void resolveLayoutDirection(int i)
        {
            setLayoutDirection(i);
            if(!isMarginRelative() || (mMarginFlags & 0x20) != 32)
            {
                return;
            } else
            {
                doResolveMargins();
                return;
            }
        }

        public void setLayoutDirection(int i)
        {
            if(i != 0 && i != 1)
                return;
            if(i != (mMarginFlags & 3))
            {
                mMarginFlags = (byte)(mMarginFlags & -4);
                mMarginFlags = (byte)(mMarginFlags | i & 3);
                if(isMarginRelative())
                    mMarginFlags = (byte)(mMarginFlags | 0x20);
                else
                    mMarginFlags = (byte)(mMarginFlags & 0xffffffdf);
            }
        }

        public void setMarginEnd(int i)
        {
            endMargin = i;
            mMarginFlags = (byte)(mMarginFlags | 0x20);
        }

        public void setMarginStart(int i)
        {
            startMargin = i;
            mMarginFlags = (byte)(mMarginFlags | 0x20);
        }

        public void setMargins(int i, int j, int k, int l)
        {
            leftMargin = i;
            topMargin = j;
            rightMargin = k;
            bottomMargin = l;
            mMarginFlags = (byte)(mMarginFlags & -5);
            mMarginFlags = (byte)(mMarginFlags & -9);
            if(isMarginRelative())
                mMarginFlags = (byte)(mMarginFlags | 0x20);
            else
                mMarginFlags = (byte)(mMarginFlags & 0xffffffdf);
        }

        public void setMarginsRelative(int i, int j, int k, int l)
        {
            startMargin = i;
            topMargin = j;
            endMargin = k;
            bottomMargin = l;
            mMarginFlags = (byte)(mMarginFlags | 0x20);
        }

        public static final int DEFAULT_MARGIN_RELATIVE = 0x80000000;
        private static final int DEFAULT_MARGIN_RESOLVED = 0;
        private static final int LAYOUT_DIRECTION_MASK = 3;
        private static final int LEFT_MARGIN_UNDEFINED_MASK = 4;
        private static final int NEED_RESOLUTION_MASK = 32;
        private static final int RIGHT_MARGIN_UNDEFINED_MASK = 8;
        private static final int RTL_COMPATIBILITY_MODE_MASK = 16;
        private static final int UNDEFINED_MARGIN = 0x80000000;
        public int bottomMargin;
        private int endMargin;
        public int leftMargin;
        byte mMarginFlags;
        public int rightMargin;
        private int startMargin;
        public int topMargin;

        public MarginLayoutParams(int i, int j)
        {
            super(i, j);
            startMargin = 0x80000000;
            endMargin = 0x80000000;
            mMarginFlags = (byte)(mMarginFlags | 4);
            mMarginFlags = (byte)(mMarginFlags | 8);
            mMarginFlags = (byte)(mMarginFlags & 0xffffffdf);
            mMarginFlags = (byte)(mMarginFlags & 0xffffffef);
        }

        public MarginLayoutParams(Context context, AttributeSet attributeset)
        {
            int i;
            startMargin = 0x80000000;
            endMargin = 0x80000000;
            attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewGroup_MarginLayout);
            setBaseAttributes(attributeset, 0, 1);
            i = attributeset.getDimensionPixelSize(2, -1);
            if(i < 0) goto _L2; else goto _L1
_L1:
            leftMargin = i;
            topMargin = i;
            rightMargin = i;
            bottomMargin = i;
_L5:
            boolean flag = context.getApplicationInfo().hasRtlSupport();
            if(context.getApplicationInfo().targetSdkVersion < 17 || flag ^ true)
                mMarginFlags = (byte)(mMarginFlags | 0x10);
            mMarginFlags = (byte)(mMarginFlags | 0);
            attributeset.recycle();
            return;
_L2:
            int j;
            j = attributeset.getDimensionPixelSize(9, -1);
            i = attributeset.getDimensionPixelSize(10, -1);
            if(j < 0) goto _L4; else goto _L3
_L3:
            leftMargin = j;
            rightMargin = j;
_L6:
            startMargin = attributeset.getDimensionPixelSize(7, 0x80000000);
            endMargin = attributeset.getDimensionPixelSize(8, 0x80000000);
            if(i >= 0)
            {
                topMargin = i;
                bottomMargin = i;
            } else
            {
                topMargin = attributeset.getDimensionPixelSize(4, 0);
                bottomMargin = attributeset.getDimensionPixelSize(6, 0);
            }
            if(isMarginRelative())
                mMarginFlags = (byte)(mMarginFlags | 0x20);
            if(true) goto _L5; else goto _L4
_L4:
            leftMargin = attributeset.getDimensionPixelSize(3, 0x80000000);
            if(leftMargin == 0x80000000)
            {
                mMarginFlags = (byte)(mMarginFlags | 4);
                leftMargin = 0;
            }
            rightMargin = attributeset.getDimensionPixelSize(5, 0x80000000);
            if(rightMargin == 0x80000000)
            {
                mMarginFlags = (byte)(mMarginFlags | 8);
                rightMargin = 0;
            }
              goto _L6
        }

        public MarginLayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            startMargin = 0x80000000;
            endMargin = 0x80000000;
            mMarginFlags = (byte)(mMarginFlags | 4);
            mMarginFlags = (byte)(mMarginFlags | 8);
            mMarginFlags = (byte)(mMarginFlags & 0xffffffdf);
            mMarginFlags = (byte)(mMarginFlags & 0xffffffef);
        }

        public MarginLayoutParams(MarginLayoutParams marginlayoutparams)
        {
            startMargin = 0x80000000;
            endMargin = 0x80000000;
            width = marginlayoutparams.width;
            height = marginlayoutparams.height;
            leftMargin = marginlayoutparams.leftMargin;
            topMargin = marginlayoutparams.topMargin;
            rightMargin = marginlayoutparams.rightMargin;
            bottomMargin = marginlayoutparams.bottomMargin;
            startMargin = marginlayoutparams.startMargin;
            endMargin = marginlayoutparams.endMargin;
            mMarginFlags = marginlayoutparams.mMarginFlags;
        }
    }

    public static interface OnHierarchyChangeListener
    {

        public abstract void onChildViewAdded(View view, View view1);

        public abstract void onChildViewRemoved(View view, View view1);
    }

    private static final class TouchTarget
    {

        public static TouchTarget obtain(View view, int i)
        {
            if(view == null)
                throw new IllegalArgumentException("child must be non-null");
            Object obj = sRecycleLock;
            obj;
            JVM INSTR monitorenter ;
            TouchTarget touchtarget;
            if(sRecycleBin != null)
                break MISSING_BLOCK_LABEL_48;
            touchtarget = new TouchTarget();
_L1:
            obj;
            JVM INSTR monitorexit ;
            touchtarget.child = view;
            touchtarget.pointerIdBits = i;
            return touchtarget;
            touchtarget = sRecycleBin;
            sRecycleBin = touchtarget.next;
            sRecycledCount--;
            touchtarget.next = null;
              goto _L1
            view;
            throw view;
        }

        public void recycle()
        {
            if(child == null)
                throw new IllegalStateException("already recycled once");
            Object obj = sRecycleLock;
            obj;
            JVM INSTR monitorenter ;
            if(sRecycledCount >= 32)
                break MISSING_BLOCK_LABEL_58;
            next = sRecycleBin;
            sRecycleBin = this;
            sRecycledCount++;
_L1:
            child = null;
            obj;
            JVM INSTR monitorexit ;
            return;
            next = null;
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public static final int ALL_POINTER_IDS = -1;
        private static final int MAX_RECYCLED = 32;
        private static TouchTarget sRecycleBin;
        private static final Object sRecycleLock = new Object[0];
        private static int sRecycledCount;
        public View child;
        public TouchTarget next;
        public int pointerIdBits;


        private TouchTarget()
        {
        }
    }

    static class ViewLocationHolder
        implements Comparable
    {

        private void clear()
        {
            mView = null;
            mLocation.set(0, 0, 0, 0);
        }

        private void init(ViewGroup viewgroup, View view)
        {
            Rect rect = mLocation;
            view.getDrawingRect(rect);
            viewgroup.offsetDescendantRectToMyCoords(view, rect);
            mView = view;
            mLayoutDirection = viewgroup.getLayoutDirection();
        }

        public static ViewLocationHolder obtain(ViewGroup viewgroup, View view)
        {
            ViewLocationHolder viewlocationholder = (ViewLocationHolder)sPool.acquire();
            ViewLocationHolder viewlocationholder1 = viewlocationholder;
            if(viewlocationholder == null)
                viewlocationholder1 = new ViewLocationHolder();
            viewlocationholder1.init(viewgroup, view);
            return viewlocationholder1;
        }

        public static void setComparisonStrategy(int i)
        {
            sComparisonStrategy = i;
        }

        public int compareTo(ViewLocationHolder viewlocationholder)
        {
            if(viewlocationholder == null)
                return 1;
            if(sComparisonStrategy == 1)
            {
                if(mLocation.bottom - viewlocationholder.mLocation.top <= 0)
                    return -1;
                if(mLocation.top - viewlocationholder.mLocation.bottom >= 0)
                    return 1;
            }
            if(mLayoutDirection == 0)
            {
                int i = mLocation.left - viewlocationholder.mLocation.left;
                if(i != 0)
                    return i;
            } else
            {
                int j = mLocation.right - viewlocationholder.mLocation.right;
                if(j != 0)
                    return -j;
            }
            int k = mLocation.top - viewlocationholder.mLocation.top;
            if(k != 0)
                return k;
            k = mLocation.height() - viewlocationholder.mLocation.height();
            if(k != 0)
                return -k;
            k = mLocation.width() - viewlocationholder.mLocation.width();
            if(k != 0)
                return -k;
            else
                return mView.getAccessibilityViewId() - viewlocationholder.mView.getAccessibilityViewId();
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((ViewLocationHolder)obj);
        }

        public void recycle()
        {
            clear();
            sPool.release(this);
        }

        public static final int COMPARISON_STRATEGY_LOCATION = 2;
        public static final int COMPARISON_STRATEGY_STRIPE = 1;
        private static final int MAX_POOL_SIZE = 32;
        private static int sComparisonStrategy = 1;
        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(32);
        private int mLayoutDirection;
        private final Rect mLocation = new Rect();
        public View mView;


        ViewLocationHolder()
        {
        }
    }


    static android.view.animation.Animation.AnimationListener _2D_get0(ViewGroup viewgroup)
    {
        return viewgroup.mAnimationListener;
    }

    static LayoutAnimationController _2D_get1(ViewGroup viewgroup)
    {
        return viewgroup.mLayoutAnimationController;
    }

    static boolean _2D_get2(ViewGroup viewgroup)
    {
        return viewgroup.mLayoutCalledWhileSuppressed;
    }

    static ArrayList _2D_get3(ViewGroup viewgroup)
    {
        return viewgroup.mTransitioningViews;
    }

    static boolean _2D_set0(ViewGroup viewgroup, boolean flag)
    {
        viewgroup.mLayoutCalledWhileSuppressed = flag;
        return flag;
    }

    static void _2D_wrap0(Canvas canvas, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, Paint paint)
    {
        fillDifference(canvas, i, j, k, l, i1, j1, k1, l1, paint);
    }

    static void _2D_wrap1(ViewGroup viewgroup)
    {
        viewgroup.notifyAnimationListener();
    }

    public ViewGroup(Context context)
    {
        this(context, null);
    }

    public ViewGroup(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ViewGroup(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ViewGroup(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mLastTouchDownIndex = -1;
        mLayoutMode = -1;
        mSuppressLayout = false;
        mLayoutCalledWhileSuppressed = false;
        mChildCountWithTransientState = 0;
        mTransientIndices = null;
        mTransientViews = null;
        initViewGroup();
        initFromAttributes(context, attributeset, i, j);
    }

    private void addDisappearingView(View view)
    {
        ArrayList arraylist = mDisappearingChildren;
        ArrayList arraylist1 = arraylist;
        if(arraylist == null)
        {
            arraylist1 = new ArrayList();
            mDisappearingChildren = arraylist1;
        }
        arraylist1.add(view);
    }

    private void addInArray(View view, int i)
    {
        View aview[];
        int j;
        int k;
        aview = mChildren;
        j = mChildrenCount;
        k = aview.length;
        if(i != j) goto _L2; else goto _L1
_L1:
        View aview1[] = aview;
        if(k == j)
        {
            mChildren = new View[k + 12];
            System.arraycopy(aview, 0, mChildren, 0, k);
            aview1 = mChildren;
        }
        i = mChildrenCount;
        mChildrenCount = i + 1;
        aview1[i] = view;
_L4:
        return;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        if(k == j)
        {
            mChildren = new View[k + 12];
            System.arraycopy(aview, 0, mChildren, 0, i);
            System.arraycopy(aview, i, mChildren, i + 1, j - i);
            aview = mChildren;
        } else
        {
            System.arraycopy(aview, i, aview, i + 1, j - i);
        }
        aview[i] = view;
        mChildrenCount = mChildrenCount + 1;
        if(mLastTouchDownIndex >= i)
            mLastTouchDownIndex = mLastTouchDownIndex + 1;
        if(true) goto _L4; else goto _L3
_L3:
        throw new IndexOutOfBoundsException((new StringBuilder()).append("index=").append(i).append(" count=").append(j).toString());
    }

    private TouchTarget addTouchTarget(View view, int i)
    {
        view = TouchTarget.obtain(view, i);
        view.next = mFirstTouchTarget;
        mFirstTouchTarget = view;
        return view;
    }

    private void addViewInner(View view, int i, LayoutParams layoutparams, boolean flag)
    {
        if(mTransition != null)
            mTransition.cancel(3);
        if(view.getParent() != null)
            throw new IllegalStateException("The specified child already has a parent. You must call removeView() on the child's parent first.");
        if(mTransition != null)
            mTransition.addChild(this, view);
        LayoutParams layoutparams1 = layoutparams;
        if(!checkLayoutParams(layoutparams))
            layoutparams1 = generateLayoutParams(layoutparams);
        int j;
        if(flag)
            view.mLayoutParams = layoutparams1;
        else
            view.setLayoutParams(layoutparams1);
        j = i;
        if(i < 0)
            j = mChildrenCount;
        addInArray(view, j);
        if(flag)
            view.assignParent(this);
        else
            view.mParent = this;
        if(view.hasFocus())
            requestChildFocus(view, view.findFocus());
        layoutparams = mAttachInfo;
        if(layoutparams != null && (mGroupFlags & 0x400000) == 0)
        {
            flag = ((View.AttachInfo) (layoutparams)).mKeepScreenOn;
            layoutparams.mKeepScreenOn = false;
            view.dispatchAttachedToWindow(mAttachInfo, mViewFlags & 0xc);
            if(((View.AttachInfo) (layoutparams)).mKeepScreenOn)
                needGlobalAttributesUpdate(true);
            layoutparams.mKeepScreenOn = flag;
        }
        if(view.isLayoutDirectionInherited())
            view.resetRtlProperties();
        dispatchViewAdded(view);
        if((view.mViewFlags & 0x400000) == 0x400000)
            mGroupFlags = mGroupFlags | 0x10000;
        if(view.hasTransientState())
            childHasTransientStateChanged(view, true);
        if(view.getVisibility() != 8)
            notifySubtreeAccessibilityStateChangedIfNeeded();
        if(mTransientIndices != null)
        {
            int k = mTransientIndices.size();
            for(i = 0; i < k; i++)
            {
                int l = ((Integer)mTransientIndices.get(i)).intValue();
                if(j <= l)
                    mTransientIndices.set(i, Integer.valueOf(l + 1));
            }

        }
        if(mCurrentDragStartEvent != null && view.getVisibility() == 0)
            notifyChildOfDragStart(view);
        if(view.hasDefaultFocus())
            setDefaultFocus(view);
    }

    private void bindLayoutAnimation(View view)
    {
        view.setAnimation(mLayoutAnimationController.getAnimationForView(view));
    }

    private static boolean canViewReceivePointerEvents(View view)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if((view.mViewFlags & 0xc) != 0)
            if(view.getAnimation() != null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private void cancelAndClearTouchTargets(MotionEvent motionevent)
    {
        if(mFirstTouchTarget != null)
        {
            boolean flag = false;
            MotionEvent motionevent1 = motionevent;
            if(motionevent == null)
            {
                long l = SystemClock.uptimeMillis();
                motionevent1 = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
                motionevent1.setSource(4098);
                flag = true;
            }
            for(motionevent = mFirstTouchTarget; motionevent != null; motionevent = ((TouchTarget) (motionevent)).next)
            {
                resetCancelNextUpFlag(((TouchTarget) (motionevent)).child);
                dispatchTransformedTouchEvent(motionevent1, true, ((TouchTarget) (motionevent)).child, ((TouchTarget) (motionevent)).pointerIdBits);
            }

            clearTouchTargets();
            if(flag)
                motionevent1.recycle();
        }
    }

    private void cancelHoverTarget(View view)
    {
        Object obj = null;
        HoverTarget hovertarget;
        for(Object obj1 = mFirstHoverTarget; obj1 != null; obj1 = hovertarget)
        {
            hovertarget = ((HoverTarget) (obj1)).next;
            if(((HoverTarget) (obj1)).child == view)
            {
                long l;
                if(obj == null)
                    mFirstHoverTarget = hovertarget;
                else
                    obj.next = hovertarget;
                ((HoverTarget) (obj1)).recycle();
                l = SystemClock.uptimeMillis();
                obj1 = MotionEvent.obtain(l, l, 10, 0.0F, 0.0F, 0);
                ((MotionEvent) (obj1)).setSource(4098);
                view.dispatchHoverEvent(((MotionEvent) (obj1)));
                ((MotionEvent) (obj1)).recycle();
                return;
            }
            obj = obj1;
        }

    }

    private void cancelTouchTarget(View view)
    {
        Object obj = null;
        TouchTarget touchtarget;
        for(Object obj1 = mFirstTouchTarget; obj1 != null; obj1 = touchtarget)
        {
            touchtarget = ((TouchTarget) (obj1)).next;
            if(((TouchTarget) (obj1)).child == view)
            {
                long l;
                if(obj == null)
                    mFirstTouchTarget = touchtarget;
                else
                    obj.next = touchtarget;
                ((TouchTarget) (obj1)).recycle();
                l = SystemClock.uptimeMillis();
                obj1 = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
                ((MotionEvent) (obj1)).setSource(4098);
                view.dispatchTouchEvent(((MotionEvent) (obj1)));
                ((MotionEvent) (obj1)).recycle();
                return;
            }
            obj = obj1;
        }

    }

    private void clearCachedLayoutMode()
    {
        if(!hasBooleanFlag(0x800000))
            mLayoutMode = -1;
    }

    private void clearTouchTargets()
    {
        TouchTarget touchtarget = mFirstTouchTarget;
        if(touchtarget != null)
        {
            TouchTarget touchtarget1;
            do
            {
                touchtarget1 = touchtarget.next;
                touchtarget.recycle();
                touchtarget = touchtarget1;
            } while(touchtarget1 != null);
            mFirstTouchTarget = null;
        }
    }

    private PointerIcon dispatchResolvePointerIcon(MotionEvent motionevent, int i, View view)
    {
        if(!view.hasIdentityMatrix())
        {
            MotionEvent motionevent1 = getTransformedMotionEvent(motionevent, view);
            motionevent = view.onResolvePointerIcon(motionevent1, i);
            motionevent1.recycle();
        } else
        {
            float f = mScrollX - view.mLeft;
            float f1 = mScrollY - view.mTop;
            motionevent.offsetLocation(f, f1);
            view = view.onResolvePointerIcon(motionevent, i);
            motionevent.offsetLocation(-f, -f1);
            motionevent = view;
        }
        return motionevent;
    }

    private boolean dispatchTooltipHoverEvent(MotionEvent motionevent, View view)
    {
        boolean flag;
        if(!view.hasIdentityMatrix())
        {
            motionevent = getTransformedMotionEvent(motionevent, view);
            flag = view.dispatchTooltipHoverEvent(motionevent);
            motionevent.recycle();
        } else
        {
            float f = mScrollX - view.mLeft;
            float f1 = mScrollY - view.mTop;
            motionevent.offsetLocation(f, f1);
            flag = view.dispatchTooltipHoverEvent(motionevent);
            motionevent.offsetLocation(-f, -f1);
        }
        return flag;
    }

    private boolean dispatchTransformedGenericPointerEvent(MotionEvent motionevent, View view)
    {
        boolean flag;
        if(!view.hasIdentityMatrix())
        {
            motionevent = getTransformedMotionEvent(motionevent, view);
            flag = view.dispatchGenericMotionEvent(motionevent);
            motionevent.recycle();
        } else
        {
            float f = mScrollX - view.mLeft;
            float f1 = mScrollY - view.mTop;
            motionevent.offsetLocation(f, f1);
            flag = view.dispatchGenericMotionEvent(motionevent);
            motionevent.offsetLocation(-f, -f1);
        }
        return flag;
    }

    private boolean dispatchTransformedTouchEvent(MotionEvent motionevent, boolean flag, View view, int i)
    {
        int j = motionevent.getAction();
        if(flag || j == 3)
        {
            motionevent.setAction(3);
            if(view == null)
                flag = super.dispatchTouchEvent(motionevent);
            else
                flag = view.dispatchTouchEvent(motionevent);
            motionevent.setAction(j);
            return flag;
        }
        j = motionevent.getPointerIdBits();
        i = j & i;
        if(i == 0)
            return false;
        if(i == j)
        {
            if(view == null || view.hasIdentityMatrix())
            {
                if(view == null)
                {
                    flag = super.dispatchTouchEvent(motionevent);
                } else
                {
                    float f = mScrollX - view.mLeft;
                    float f1 = mScrollY - view.mTop;
                    motionevent.offsetLocation(f, f1);
                    flag = view.dispatchTouchEvent(motionevent);
                    motionevent.offsetLocation(-f, -f1);
                }
                return flag;
            }
            motionevent = MotionEvent.obtain(motionevent);
        } else
        {
            motionevent = motionevent.split(i);
        }
        if(view == null)
        {
            flag = super.dispatchTouchEvent(motionevent);
        } else
        {
            motionevent.offsetLocation(mScrollX - view.mLeft, mScrollY - view.mTop);
            if(!view.hasIdentityMatrix())
                motionevent.transform(view.getInverseMatrix());
            flag = view.dispatchTouchEvent(motionevent);
        }
        motionevent.recycle();
        return flag;
    }

    private static void drawCorner(Canvas canvas, Paint paint, int i, int j, int k, int l, int i1)
    {
        fillRect(canvas, paint, i, j, i + k, j + sign(l) * i1);
        fillRect(canvas, paint, i, j, i + sign(k) * i1, j + l);
    }

    private static void drawRect(Canvas canvas, Paint paint, int i, int j, int k, int l)
    {
        if(sDebugLines == null)
            sDebugLines = new float[16];
        sDebugLines[0] = i;
        sDebugLines[1] = j;
        sDebugLines[2] = k;
        sDebugLines[3] = j;
        sDebugLines[4] = k;
        sDebugLines[5] = j;
        sDebugLines[6] = k;
        sDebugLines[7] = l;
        sDebugLines[8] = k;
        sDebugLines[9] = l;
        sDebugLines[10] = i;
        sDebugLines[11] = l;
        sDebugLines[12] = i;
        sDebugLines[13] = l;
        sDebugLines[14] = i;
        sDebugLines[15] = j;
        canvas.drawLines(sDebugLines, paint);
    }

    private static void drawRectCorners(Canvas canvas, int i, int j, int k, int l, Paint paint, int i1, int j1)
    {
        drawCorner(canvas, paint, i, j, i1, i1, j1);
        drawCorner(canvas, paint, i, l, i1, -i1, j1);
        drawCorner(canvas, paint, k, j, -i1, i1, j1);
        drawCorner(canvas, paint, k, l, -i1, -i1, j1);
    }

    private void exitHoverTargets()
    {
        if(mHoveredSelf || mFirstHoverTarget != null)
        {
            long l = SystemClock.uptimeMillis();
            MotionEvent motionevent = MotionEvent.obtain(l, l, 10, 0.0F, 0.0F, 0);
            motionevent.setSource(4098);
            dispatchHoverEvent(motionevent);
            motionevent.recycle();
        }
    }

    private void exitTooltipHoverTargets()
    {
        if(mTooltipHoveredSelf || mTooltipHoverTarget != null)
        {
            long l = SystemClock.uptimeMillis();
            MotionEvent motionevent = MotionEvent.obtain(l, l, 10, 0.0F, 0.0F, 0);
            motionevent.setSource(4098);
            dispatchTooltipHoverEvent(motionevent);
            motionevent.recycle();
        }
    }

    private static void fillDifference(Canvas canvas, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, Paint paint)
    {
        i1 = i - i1;
        k1 = k + k1;
        fillRect(canvas, paint, i1, j - j1, k1, j);
        fillRect(canvas, paint, i1, j, i, l);
        fillRect(canvas, paint, k, j, k1, l);
        fillRect(canvas, paint, i1, l, k1, l + l1);
    }

    private static void fillRect(Canvas canvas, Paint paint, int i, int j, int k, int l)
    {
        if(i != k && j != l)
        {
            int i1 = i;
            int j1 = k;
            if(i > k)
            {
                j1 = i;
                i1 = k;
            }
            k = j;
            i = l;
            if(j > l)
            {
                i = j;
                k = l;
            }
            canvas.drawRect(i1, k, j1, i, paint);
        }
    }

    private View findChildWithAccessibilityFocus()
    {
        Object obj = getViewRootImpl();
        if(obj == null)
            return null;
        obj = ((ViewRootImpl) (obj)).getAccessibilityFocusedHost();
        if(obj == null)
            return null;
        for(ViewParent viewparent = ((View) (obj)).getParent(); viewparent instanceof View; viewparent = ((View) (obj)).getParent())
        {
            if(viewparent == this)
                return ((View) (obj));
            obj = (View)viewparent;
        }

        return null;
    }

    private int getAndVerifyPreorderedIndex(int i, int j, boolean flag)
    {
        if(flag)
        {
            j = getChildDrawingOrder(i, j);
            if(j >= i)
                throw new IndexOutOfBoundsException((new StringBuilder()).append("getChildDrawingOrder() returned invalid index ").append(j).append(" (child count is ").append(i).append(")").toString());
            i = j;
        } else
        {
            i = j;
        }
        return i;
    }

    private static View getAndVerifyPreorderedView(ArrayList arraylist, View aview[], int i)
    {
        if(arraylist != null)
        {
            aview = (View)arraylist.get(i);
            arraylist = aview;
            if(aview == null)
                throw new RuntimeException((new StringBuilder()).append("Invalid preorderedList contained null child at index ").append(i).toString());
        } else
        {
            arraylist = aview[i];
        }
        return arraylist;
    }

    public static int getChildMeasureSpec(int i, int j, int k)
    {
        int l;
        int i1;
        l = View.MeasureSpec.getMode(i);
        i1 = Math.max(0, View.MeasureSpec.getSize(i) - j);
        j = 0;
        i = 0;
        l;
        JVM INSTR lookupswitch 3: default 56
    //                   -2147483648: 106
    //                   0: 150
    //                   1073741824: 62;
           goto _L1 _L2 _L3 _L4
_L1:
        return View.MeasureSpec.makeMeasureSpec(j, i);
_L4:
        if(k >= 0)
        {
            j = k;
            i = 0x40000000;
        } else
        if(k == -1)
        {
            j = i1;
            i = 0x40000000;
        } else
        if(k == -2)
        {
            j = i1;
            i = 0x80000000;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(k >= 0)
        {
            j = k;
            i = 0x40000000;
        } else
        if(k == -1)
        {
            j = i1;
            i = 0x80000000;
        } else
        if(k == -2)
        {
            j = i1;
            i = 0x80000000;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(k >= 0)
        {
            j = k;
            i = 0x40000000;
        } else
        if(k == -1)
        {
            if(View.sUseZeroUnspecifiedMeasureSpec)
                j = 0;
            else
                j = i1;
            i = 0;
        } else
        if(k == -2)
        {
            if(View.sUseZeroUnspecifiedMeasureSpec)
                j = 0;
            else
                j = i1;
            i = 0;
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    private ChildListForAutoFill getChildrenForAutofill(int i)
    {
        ChildListForAutoFill childlistforautofill = ChildListForAutoFill.obtain();
        populateChildrenForAutofill(childlistforautofill, i);
        return childlistforautofill;
    }

    private PointF getLocalPoint()
    {
        if(mLocalPoint == null)
            mLocalPoint = new PointF();
        return mLocalPoint;
    }

    private float[] getTempPoint()
    {
        if(mTempPoint == null)
            mTempPoint = new float[2];
        return mTempPoint;
    }

    private TouchTarget getTouchTarget(View view)
    {
        for(TouchTarget touchtarget = mFirstTouchTarget; touchtarget != null; touchtarget = touchtarget.next)
            if(touchtarget.child == view)
                return touchtarget;

        return null;
    }

    private MotionEvent getTransformedMotionEvent(MotionEvent motionevent, View view)
    {
        float f = mScrollX - view.mLeft;
        float f1 = mScrollY - view.mTop;
        motionevent = MotionEvent.obtain(motionevent);
        motionevent.offsetLocation(f, f1);
        if(!view.hasIdentityMatrix())
            motionevent.transform(view.getInverseMatrix());
        return motionevent;
    }

    private boolean hasBooleanFlag(int i)
    {
        boolean flag;
        if((mGroupFlags & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean hasChildWithZ()
    {
        for(int i = 0; i < mChildrenCount; i++)
            if(mChildren[i].getZ() != 0.0F)
                return true;

        return false;
    }

    private void initFromAttributes(Context context, AttributeSet attributeset, int i, int j)
    {
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewGroup, i, j);
        j = context.getIndexCount();
        i = 0;
_L16:
        int k;
        if(i >= j)
            break MISSING_BLOCK_LABEL_318;
        k = context.getIndex(i);
        k;
        JVM INSTR tableswitch 0 12: default 100
    //                   0 106
    //                   1 120
    //                   2 190
    //                   3 134
    //                   4 148
    //                   5 176
    //                   6 162
    //                   7 220
    //                   8 238
    //                   9 252
    //                   10 276
    //                   11 290
    //                   12 304;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_304;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L17:
        i++;
        if(true) goto _L16; else goto _L15
_L15:
        setClipChildren(context.getBoolean(k, true));
          goto _L17
_L3:
        setClipToPadding(context.getBoolean(k, true));
          goto _L17
_L5:
        setAnimationCacheEnabled(context.getBoolean(k, true));
          goto _L17
_L6:
        setPersistentDrawingCache(context.getInt(k, 2));
          goto _L17
_L8:
        setAddStatesFromChildren(context.getBoolean(k, false));
          goto _L17
_L7:
        setAlwaysDrawnWithCacheEnabled(context.getBoolean(k, true));
          goto _L17
_L4:
        k = context.getResourceId(k, -1);
        if(k > 0)
            setLayoutAnimation(AnimationUtils.loadLayoutAnimation(mContext, k));
          goto _L17
_L9:
        setDescendantFocusability(DESCENDANT_FOCUSABILITY_FLAGS[context.getInt(k, 0)]);
          goto _L17
_L10:
        setMotionEventSplittingEnabled(context.getBoolean(k, false));
          goto _L17
_L11:
        if(context.getBoolean(k, false))
            setLayoutTransition(new LayoutTransition());
          goto _L17
_L12:
        setLayoutMode(context.getInt(k, -1));
          goto _L17
_L13:
        setTransitionGroup(context.getBoolean(k, false));
          goto _L17
        setTouchscreenBlocksFocus(context.getBoolean(k, false));
          goto _L17
        context.recycle();
        return;
    }

    private void initViewGroup()
    {
        if(!debugDraw())
            setFlags(128, 128);
        mGroupFlags = mGroupFlags | 1;
        mGroupFlags = mGroupFlags | 2;
        mGroupFlags = mGroupFlags | 0x10;
        mGroupFlags = mGroupFlags | 0x40;
        mGroupFlags = mGroupFlags | 0x4000;
        if(mContext.getApplicationInfo().targetSdkVersion >= 11)
            mGroupFlags = mGroupFlags | 0x200000;
        setDescendantFocusability(0x20000);
        mChildren = new View[12];
        mChildrenCount = 0;
        mPersistentDrawingCache = 2;
    }

    private void notifyAnimationListener()
    {
        mGroupFlags = mGroupFlags & 0xfffffdff;
        mGroupFlags = mGroupFlags | 0x10;
        if(mAnimationListener != null)
            post(new Runnable() {

                public void run()
                {
                    ViewGroup._2D_get0(ViewGroup.this).onAnimationEnd(ViewGroup._2D_get1(ViewGroup.this).getAnimation());
                }

                final ViewGroup this$0;

            
            {
                this$0 = ViewGroup.this;
                super();
            }
            }
);
        invalidate(true);
    }

    private static MotionEvent obtainMotionEventNoHistoryOrSelf(MotionEvent motionevent)
    {
        if(motionevent.getHistorySize() == 0)
            return motionevent;
        else
            return MotionEvent.obtainNoHistory(motionevent);
    }

    private void populateChildrenForAutofill(ArrayList arraylist, int i)
    {
        int j = mChildrenCount;
        if(j <= 0)
            return;
        ArrayList arraylist1 = buildOrderedChildList();
        boolean flag;
        int k;
        if(arraylist1 == null)
            flag = isChildrenDrawingOrderEnabled();
        else
            flag = false;
        k = 0;
        while(k < j) 
        {
            int l = getAndVerifyPreorderedIndex(j, k, flag);
            View view;
            if(arraylist1 == null)
                view = mChildren[l];
            else
                view = (View)arraylist1.get(l);
            if((i & 1) != 0 || view.isImportantForAutofill())
                arraylist.add(view);
            else
            if(view instanceof ViewGroup)
                ((ViewGroup)view).populateChildrenForAutofill(arraylist, i);
            k++;
        }
    }

    private void recreateChildDisplayList(View view)
    {
        boolean flag;
        if((view.mPrivateFlags & 0x80000000) != 0)
            flag = true;
        else
            flag = false;
        view.mRecreateDisplayList = flag;
        view.mPrivateFlags = view.mPrivateFlags & 0x7fffffff;
        view.updateDisplayListIfDirty();
        view.mRecreateDisplayList = false;
    }

    private void removeFromArray(int i)
    {
        boolean flag = false;
        View aview[] = mChildren;
        if(mTransitioningViews != null)
            flag = mTransitioningViews.contains(aview[i]);
        if(!flag)
            aview[i].mParent = null;
        int j = mChildrenCount;
        if(i == j - 1)
        {
            j = mChildrenCount - 1;
            mChildrenCount = j;
            aview[j] = null;
        } else
        if(i >= 0 && i < j)
        {
            System.arraycopy(aview, i + 1, aview, i, j - i - 1);
            j = mChildrenCount - 1;
            mChildrenCount = j;
            aview[j] = null;
        } else
        {
            throw new IndexOutOfBoundsException();
        }
        if(mLastTouchDownIndex != i) goto _L2; else goto _L1
_L1:
        mLastTouchDownTime = 0L;
        mLastTouchDownIndex = -1;
_L4:
        return;
_L2:
        if(mLastTouchDownIndex > i)
            mLastTouchDownIndex = mLastTouchDownIndex - 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void removeFromArray(int i, int j)
    {
        View aview[] = mChildren;
        int k = mChildrenCount;
        i = Math.max(0, i);
        int l = Math.min(k, i + j);
        if(i == l)
            return;
        if(l == k)
        {
            for(j = i; j < l; j++)
            {
                aview[j].mParent = null;
                aview[j] = null;
            }

        } else
        {
            for(j = i; j < l; j++)
                aview[j].mParent = null;

            System.arraycopy(aview, l, aview, i, k - l);
            for(j = k - (l - i); j < k; j++)
                aview[j] = null;

        }
        mChildrenCount = mChildrenCount - (l - i);
    }

    private void removePointersFromTouchTargets(int i)
    {
        TouchTarget touchtarget = null;
        TouchTarget touchtarget1 = mFirstTouchTarget;
        do
        {
            if(touchtarget1 == null)
                break;
            TouchTarget touchtarget2 = touchtarget1.next;
            if((touchtarget1.pointerIdBits & i) != 0)
            {
                touchtarget1.pointerIdBits = touchtarget1.pointerIdBits & i;
                if(touchtarget1.pointerIdBits == 0)
                {
                    if(touchtarget == null)
                        mFirstTouchTarget = touchtarget2;
                    else
                        touchtarget.next = touchtarget2;
                    touchtarget1.recycle();
                    touchtarget1 = touchtarget2;
                    continue;
                }
            }
            touchtarget = touchtarget1;
            touchtarget1 = touchtarget2;
        } while(true);
    }

    private void removeViewInternal(int i, View view)
    {
        if(mTransition != null)
            mTransition.removeChild(this, view);
        boolean flag = false;
        if(view == mFocused)
        {
            view.unFocus(null);
            flag = true;
        }
        if(view == mFocusedInCluster)
            clearFocusedInCluster(view);
        view.clearAccessibilityFocus();
        cancelTouchTarget(view);
        cancelHoverTarget(view);
        int j;
        int k;
        if(view.getAnimation() != null || mTransitioningViews != null && mTransitioningViews.contains(view))
            addDisappearingView(view);
        else
        if(view.mAttachInfo != null)
            view.dispatchDetachedFromWindow();
        if(view.hasTransientState())
            childHasTransientStateChanged(view, false);
        needGlobalAttributesUpdate(false);
        removeFromArray(i);
        if(view == mDefaultFocus)
            clearDefaultFocus(view);
        if(flag)
        {
            clearChildFocus(view);
            if(!rootViewRequestFocus())
                notifyGlobalFocusCleared(this);
        }
        dispatchViewRemoved(view);
        if(view.getVisibility() != 8)
            notifySubtreeAccessibilityStateChangedIfNeeded();
        if(mTransientIndices == null)
            j = 0;
        else
            j = mTransientIndices.size();
        for(k = 0; k < j; k++)
        {
            int l = ((Integer)mTransientIndices.get(k)).intValue();
            if(i < l)
                mTransientIndices.set(k, Integer.valueOf(l - 1));
        }

        if(mCurrentDragStartEvent != null)
            mChildrenInterestedInDrag.remove(view);
    }

    private boolean removeViewInternal(View view)
    {
        int i = indexOfChild(view);
        if(i >= 0)
        {
            removeViewInternal(i, view);
            return true;
        } else
        {
            return false;
        }
    }

    private void removeViewsInternal(int i, int j)
    {
        int k;
        for(k = i + j; i < 0 || j < 0 || k > mChildrenCount;)
            throw new IndexOutOfBoundsException();

        View view = mFocused;
        boolean flag;
        boolean flag1;
        View view1;
        View aview[];
        int l;
        if(mAttachInfo != null)
            flag = true;
        else
            flag = false;
        flag1 = false;
        view1 = null;
        aview = mChildren;
        l = i;
        while(l < k) 
        {
            View view2 = aview[l];
            if(mTransition != null)
                mTransition.removeChild(this, view2);
            if(view2 == view)
            {
                view2.unFocus(null);
                flag1 = true;
            }
            if(view2 == mDefaultFocus)
                view1 = view2;
            if(view2 == mFocusedInCluster)
                clearFocusedInCluster(view2);
            view2.clearAccessibilityFocus();
            cancelTouchTarget(view2);
            cancelHoverTarget(view2);
            if(view2.getAnimation() != null || mTransitioningViews != null && mTransitioningViews.contains(view2))
                addDisappearingView(view2);
            else
            if(flag)
                view2.dispatchDetachedFromWindow();
            if(view2.hasTransientState())
                childHasTransientStateChanged(view2, false);
            needGlobalAttributesUpdate(false);
            dispatchViewRemoved(view2);
            l++;
        }
        removeFromArray(i, j);
        if(view1 != null)
            clearDefaultFocus(view1);
        if(flag1)
        {
            clearChildFocus(view);
            if(!rootViewRequestFocus())
                notifyGlobalFocusCleared(view);
        }
    }

    private static boolean resetCancelNextUpFlag(View view)
    {
        if(view != null && (view.mPrivateFlags & 0x4000000) != 0)
        {
            view.mPrivateFlags = view.mPrivateFlags & 0xfbffffff;
            return true;
        } else
        {
            return false;
        }
    }

    private void resetTouchState()
    {
        clearTouchTargets();
        resetCancelNextUpFlag(this);
        mGroupFlags = mGroupFlags & 0xfff7ffff;
        mNestedScrollAxes = 0;
    }

    private boolean restoreFocusInClusterInternal(int i)
    {
        if(mFocusedInCluster != null && getDescendantFocusability() != 0x60000 && (mFocusedInCluster.mViewFlags & 0xc) == 0 && mFocusedInCluster.restoreFocusInCluster(i))
            return true;
        else
            return super.restoreFocusInCluster(i);
    }

    private void setBooleanFlag(int i, boolean flag)
    {
        if(flag)
            mGroupFlags = mGroupFlags | i;
        else
            mGroupFlags = mGroupFlags & i;
    }

    private void setLayoutMode(int i, boolean flag)
    {
        mLayoutMode = i;
        setBooleanFlag(0x800000, flag);
    }

    private void setTouchscreenBlocksFocusNoRefocus(boolean flag)
    {
        if(flag)
            mGroupFlags = mGroupFlags | 0x4000000;
        else
            mGroupFlags = mGroupFlags & 0xfbffffff;
    }

    private static int sign(int i)
    {
        if(i >= 0)
            i = 1;
        else
            i = -1;
        return i;
    }

    public void addChildrenForAccessibility(ArrayList arraylist)
    {
        ChildListForAccessibility childlistforaccessibility;
        if(getAccessibilityNodeProvider() != null)
            return;
        childlistforaccessibility = ChildListForAccessibility.obtain(this, true);
        int i = childlistforaccessibility.getChildCount();
        int j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_84;
        View view;
        view = childlistforaccessibility.getChildAt(j);
        if((view.mViewFlags & 0xc) == 0)
        {
            if(!view.includeForAccessibility())
                break; /* Loop/switch isn't completed */
            arraylist.add(view);
        }
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        view.addChildrenForAccessibility(arraylist);
          goto _L3
        arraylist;
        childlistforaccessibility.recycle();
        throw arraylist;
        childlistforaccessibility.recycle();
        return;
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        int k = arraylist.size();
        int l = getDescendantFocusability();
        boolean flag = shouldBlockFocusForTouchscreen();
        boolean flag1;
        if(!isFocusableInTouchMode())
            flag1 = flag ^ true;
        else
            flag1 = true;
        if(l == 0x60000)
        {
            if(flag1)
                super.addFocusables(arraylist, i, j);
            return;
        }
        int i1 = j;
        if(flag)
            i1 = j | 1;
        if(l == 0x20000 && flag1)
            super.addFocusables(arraylist, i, i1);
        j = 0;
        View aview[] = new View[mChildrenCount];
        for(int j1 = 0; j1 < mChildrenCount;)
        {
            View view = mChildren[j1];
            int l1 = j;
            if((view.mViewFlags & 0xc) == 0)
            {
                aview[j] = view;
                l1 = j + 1;
            }
            j1++;
            j = l1;
        }

        FocusFinder.sort(aview, 0, j, this, isLayoutRtl());
        for(int k1 = 0; k1 < j; k1++)
            aview[k1].addFocusables(arraylist, i, i1);

        if(l == 0x40000 && flag1 && k == arraylist.size())
            super.addFocusables(arraylist, i, i1);
    }

    public void addKeyboardNavigationClusters(Collection collection, int i)
    {
        int j = collection.size();
        if(!isKeyboardNavigationCluster()) goto _L2; else goto _L1
_L1:
        boolean flag = getTouchscreenBlocksFocus();
        setTouchscreenBlocksFocusNoRefocus(false);
        super.addKeyboardNavigationClusters(collection, i);
        setTouchscreenBlocksFocusNoRefocus(flag);
_L4:
        if(j != collection.size())
            return;
        break; /* Loop/switch isn't completed */
        collection;
        setTouchscreenBlocksFocusNoRefocus(flag);
        throw collection;
_L2:
        super.addKeyboardNavigationClusters(collection, i);
        if(true) goto _L4; else goto _L3
_L3:
        if(getDescendantFocusability() == 0x60000)
            return;
        int k = 0;
        View aview[] = new View[mChildrenCount];
        for(int l = 0; l < mChildrenCount;)
        {
            View view = mChildren[l];
            int j1 = k;
            if((view.mViewFlags & 0xc) == 0)
            {
                aview[k] = view;
                j1 = k + 1;
            }
            l++;
            k = j1;
        }

        FocusFinder.sort(aview, 0, k, this, isLayoutRtl());
        for(int i1 = 0; i1 < k; i1++)
            aview[i1].addKeyboardNavigationClusters(collection, i);

        return;
    }

    public boolean addStatesFromChildren()
    {
        boolean flag = false;
        if((mGroupFlags & 0x2000) != 0)
            flag = true;
        return flag;
    }

    public void addTouchables(ArrayList arraylist)
    {
        super.addTouchables(arraylist);
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
        {
            View view = aview[j];
            if((view.mViewFlags & 0xc) == 0)
                view.addTouchables(arraylist);
        }

    }

    public void addTransientView(View view, int i)
    {
        int j;
        int k;
        if(i < 0)
            return;
        if(mTransientIndices == null)
        {
            mTransientIndices = new ArrayList();
            mTransientViews = new ArrayList();
        }
        j = mTransientIndices.size();
        if(j <= 0)
            break MISSING_BLOCK_LABEL_137;
        k = 0;
_L3:
        if(k < j && i >= ((Integer)mTransientIndices.get(k)).intValue()) goto _L2; else goto _L1
_L1:
        mTransientIndices.add(k, Integer.valueOf(i));
        mTransientViews.add(k, view);
_L4:
        view.mParent = this;
        view.dispatchAttachedToWindow(mAttachInfo, mViewFlags & 0xc);
        invalidate(true);
        return;
_L2:
        k++;
          goto _L3
        mTransientIndices.add(Integer.valueOf(i));
        mTransientViews.add(view);
          goto _L4
    }

    public void addView(View view)
    {
        addView(view, -1);
    }

    public void addView(View view, int i)
    {
        if(view == null)
            throw new IllegalArgumentException("Cannot add a null child view to a ViewGroup");
        LayoutParams layoutparams = view.getLayoutParams();
        LayoutParams layoutparams2 = layoutparams;
        if(layoutparams == null)
        {
            LayoutParams layoutparams1 = generateDefaultLayoutParams();
            layoutparams2 = layoutparams1;
            if(layoutparams1 == null)
                throw new IllegalArgumentException("generateDefaultLayoutParams() cannot return null");
        }
        addView(view, i, layoutparams2);
    }

    public void addView(View view, int i, int j)
    {
        LayoutParams layoutparams = generateDefaultLayoutParams();
        layoutparams.width = i;
        layoutparams.height = j;
        addView(view, -1, layoutparams);
    }

    public void addView(View view, int i, LayoutParams layoutparams)
    {
        if(view == null)
        {
            throw new IllegalArgumentException("Cannot add a null child view to a ViewGroup");
        } else
        {
            requestLayout();
            invalidate(true);
            addViewInner(view, i, layoutparams, false);
            return;
        }
    }

    public void addView(View view, LayoutParams layoutparams)
    {
        addView(view, -1, layoutparams);
    }

    protected boolean addViewInLayout(View view, int i, LayoutParams layoutparams)
    {
        return addViewInLayout(view, i, layoutparams, false);
    }

    protected boolean addViewInLayout(View view, int i, LayoutParams layoutparams, boolean flag)
    {
        if(view == null)
        {
            throw new IllegalArgumentException("Cannot add a null child view to a ViewGroup");
        } else
        {
            view.mParent = null;
            addViewInner(view, i, layoutparams, flag);
            view.mPrivateFlags = view.mPrivateFlags & 0xff9fffff | 0x20;
            return true;
        }
    }

    protected void attachLayoutAnimationParameters(View view, LayoutParams layoutparams, int i, int j)
    {
        android.view.animation.LayoutAnimationController.AnimationParameters animationparameters = layoutparams.layoutAnimationParameters;
        view = animationparameters;
        if(animationparameters == null)
        {
            view = new android.view.animation.LayoutAnimationController.AnimationParameters();
            layoutparams.layoutAnimationParameters = view;
        }
        view.count = j;
        view.index = i;
    }

    protected void attachViewToParent(View view, int i, LayoutParams layoutparams)
    {
        boolean flag = false;
        view.mLayoutParams = layoutparams;
        int j = i;
        if(i < 0)
            j = mChildrenCount;
        addInArray(view, j);
        view.mParent = this;
        view.mPrivateFlags = view.mPrivateFlags & 0xff9fffff & 0xffff7fff | 0x20 | 0x80000000;
        mPrivateFlags = mPrivateFlags | 0x80000000;
        if(view.hasFocus())
            requestChildFocus(view, view.findFocus());
        boolean flag1 = flag;
        if(isAttachedToWindow())
        {
            flag1 = flag;
            if(getWindowVisibility() == 0)
                flag1 = isShown();
        }
        dispatchVisibilityAggregated(flag1);
    }

    public void bringChildToFront(View view)
    {
        int i = indexOfChild(view);
        if(i >= 0)
        {
            removeFromArray(i);
            addInArray(view, mChildrenCount);
            view.mParent = this;
            requestLayout();
            invalidate();
        }
    }

    ArrayList buildOrderedChildList()
    {
        int i = mChildrenCount;
        if(i <= 1 || hasChildWithZ() ^ true)
            return null;
        boolean flag;
        if(mPreSortedChildren == null)
        {
            mPreSortedChildren = new ArrayList(i);
        } else
        {
            mPreSortedChildren.clear();
            mPreSortedChildren.ensureCapacity(i);
        }
        flag = isChildrenDrawingOrderEnabled();
        for(int j = 0; j < i; j++)
        {
            int k = getAndVerifyPreorderedIndex(i, j, flag);
            View view = mChildren[k];
            float f = view.getZ();
            for(k = j; k > 0 && ((View)mPreSortedChildren.get(k - 1)).getZ() > f; k--);
            mPreSortedChildren.add(k, view);
        }

        return mPreSortedChildren;
    }

    public ArrayList buildTouchDispatchChildList()
    {
        return buildOrderedChildList();
    }

    protected boolean canAnimate()
    {
        boolean flag;
        if(mLayoutAnimationController != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void captureTransitioningViews(List list)
    {
        if(getVisibility() != 0)
            return;
        if(isTransitionGroup())
        {
            list.add(this);
        } else
        {
            int i = getChildCount();
            int j = 0;
            while(j < i) 
            {
                getChildAt(j).captureTransitioningViews(list);
                j++;
            }
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutparams)
    {
        boolean flag;
        if(layoutparams != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void childDrawableStateChanged(View view)
    {
        if((mGroupFlags & 0x2000) != 0)
            refreshDrawableState();
    }

    public void childHasTransientStateChanged(View view, boolean flag)
    {
        boolean flag1 = hasTransientState();
        if(flag)
            mChildCountWithTransientState = mChildCountWithTransientState + 1;
        else
            mChildCountWithTransientState = mChildCountWithTransientState - 1;
        flag = hasTransientState();
        if(mParent == null || flag1 == flag)
            break MISSING_BLOCK_LABEL_47;
        mParent.childHasTransientStateChanged(this, flag);
_L1:
        return;
        view;
        Log.e("ViewGroup", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), view);
          goto _L1
    }

    protected void cleanupLayoutState(View view)
    {
        view.mPrivateFlags = view.mPrivateFlags & 0xffffefff;
    }

    public void clearChildFocus(View view)
    {
        mFocused = null;
        if(mParent != null)
            mParent.clearChildFocus(this);
    }

    void clearDefaultFocus(View view)
    {
        if(mDefaultFocus != view && mDefaultFocus != null && mDefaultFocus.isFocusedByDefault())
            return;
        mDefaultFocus = null;
        for(int i = 0; i < mChildrenCount; i++)
        {
            view = mChildren[i];
            if(view.isFocusedByDefault())
            {
                mDefaultFocus = view;
                return;
            }
            if(mDefaultFocus == null && view.hasDefaultFocus())
                mDefaultFocus = view;
        }

        if(mParent instanceof ViewGroup)
            ((ViewGroup)mParent).clearDefaultFocus(((View) (this)));
    }

    public void clearDisappearingChildren()
    {
        ArrayList arraylist = mDisappearingChildren;
        if(arraylist != null)
        {
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
            {
                View view = (View)arraylist.get(j);
                if(view.mAttachInfo != null)
                    view.dispatchDetachedFromWindow();
                view.clearAnimation();
            }

            arraylist.clear();
            invalidate();
        }
    }

    public void clearFocus()
    {
        if(mFocused == null)
        {
            super.clearFocus();
        } else
        {
            View view = mFocused;
            mFocused = null;
            view.clearFocus();
        }
    }

    void clearFocusedInCluster()
    {
        View view;
        Object obj;
        view = findKeyboardNavigationCluster();
        obj = this;
_L3:
        ((ViewGroup)obj).mFocusedInCluster = null;
        if(obj != view) goto _L2; else goto _L1
_L1:
        return;
_L2:
        obj = ((ViewParent) (obj)).getParent();
        if(!(obj instanceof ViewGroup)) goto _L1; else goto _L3
    }

    void clearFocusedInCluster(View view)
    {
        if(mFocusedInCluster != view)
        {
            return;
        } else
        {
            clearFocusedInCluster();
            return;
        }
    }

    Insets computeOpticalInsets()
    {
        if(isLayoutModeOptical())
        {
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 0;
            for(int i1 = 0; i1 < mChildrenCount;)
            {
                Object obj = getChildAt(i1);
                int j1 = l;
                int k1 = i;
                int l1 = k;
                int i2 = j;
                if(((View) (obj)).getVisibility() == 0)
                {
                    obj = ((View) (obj)).getOpticalInsets();
                    k1 = Math.max(i, ((Insets) (obj)).left);
                    i2 = Math.max(j, ((Insets) (obj)).top);
                    l1 = Math.max(k, ((Insets) (obj)).right);
                    j1 = Math.max(l, ((Insets) (obj)).bottom);
                }
                i1++;
                l = j1;
                i = k1;
                k = l1;
                j = i2;
            }

            return Insets.of(i, j, k, l);
        } else
        {
            return Insets.NONE;
        }
    }

    public Bitmap createSnapshot(android.graphics.Bitmap.Config config, int i, boolean flag)
    {
        int j = mChildrenCount;
        Object obj = null;
        if(flag)
        {
            int ai[] = new int[j];
            int k = 0;
            do
            {
                obj = ai;
                if(k >= j)
                    break;
                obj = getChildAt(k);
                ai[k] = ((View) (obj)).getVisibility();
                if(ai[k] == 0)
                    obj.mViewFlags = ((View) (obj)).mViewFlags & 0xfffffff3 | 4;
                k++;
            } while(true);
        }
        Bitmap bitmap = super.createSnapshot(config, i, flag);
        if(flag)
            for(i = 0; i < j; i++)
            {
                config = getChildAt(i);
                config.mViewFlags = ((View) (config)).mViewFlags & 0xfffffff3 | obj[i] & 0xc;
            }

        return bitmap;
    }

    protected void debug(int i)
    {
        super.debug(i);
        if(mFocused != null)
        {
            String s = debugIndent(i);
            Log.d("View", (new StringBuilder()).append(s).append("mFocused").toString());
            mFocused.debug(i + 1);
        }
        if(mDefaultFocus != null)
        {
            String s1 = debugIndent(i);
            Log.d("View", (new StringBuilder()).append(s1).append("mDefaultFocus").toString());
            mDefaultFocus.debug(i + 1);
        }
        if(mFocusedInCluster != null)
        {
            String s2 = debugIndent(i);
            Log.d("View", (new StringBuilder()).append(s2).append("mFocusedInCluster").toString());
            mFocusedInCluster.debug(i + 1);
        }
        if(mChildrenCount != 0)
        {
            String s3 = debugIndent(i);
            Log.d("View", (new StringBuilder()).append(s3).append("{").toString());
        }
        int j = mChildrenCount;
        for(int k = 0; k < j; k++)
            mChildren[k].debug(i + 1);

        if(mChildrenCount != 0)
        {
            String s4 = debugIndent(i);
            Log.d("View", (new StringBuilder()).append(s4).append("}").toString());
        }
    }

    protected void destroyHardwareResources()
    {
        super.destroyHardwareResources();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
            getChildAt(j).destroyHardwareResources();

    }

    protected void detachAllViewsFromParent()
    {
        int i = mChildrenCount;
        if(i <= 0)
            return;
        View aview[] = mChildren;
        mChildrenCount = 0;
        for(i--; i >= 0; i--)
        {
            aview[i].mParent = null;
            aview[i] = null;
        }

    }

    protected void detachViewFromParent(int i)
    {
        removeFromArray(i);
    }

    protected void detachViewFromParent(View view)
    {
        removeFromArray(indexOfChild(view));
    }

    protected void detachViewsFromParent(int i, int j)
    {
        removeFromArray(i, j);
    }

    public boolean dispatchActivityResult(String s, int i, int j, Intent intent)
    {
        if(super.dispatchActivityResult(s, i, j, intent))
            return true;
        int k = getChildCount();
        for(int l = 0; l < k; l++)
            if(getChildAt(l).dispatchActivityResult(s, i, j, intent))
                return true;

        return false;
    }

    public WindowInsets dispatchApplyWindowInsets(WindowInsets windowinsets)
    {
        WindowInsets windowinsets1;
        windowinsets1 = super.dispatchApplyWindowInsets(windowinsets);
        windowinsets = windowinsets1;
        if(windowinsets1.isConsumed()) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = getChildCount();
        j = 0;
_L6:
        windowinsets = windowinsets1;
        if(j >= i) goto _L2; else goto _L3
_L3:
        windowinsets = getChildAt(j).dispatchApplyWindowInsets(windowinsets1);
        if(!windowinsets.isConsumed()) goto _L4; else goto _L2
_L2:
        return windowinsets;
_L4:
        j++;
        windowinsets1 = windowinsets;
        if(true) goto _L6; else goto _L5
_L5:
    }

    void dispatchAttachedToWindow(View.AttachInfo attachinfo, int i)
    {
        mGroupFlags = mGroupFlags | 0x400000;
        super.dispatchAttachedToWindow(attachinfo, i);
        mGroupFlags = mGroupFlags & 0xffbfffff;
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
        {
            View view1 = aview[k];
            view1.dispatchAttachedToWindow(attachinfo, combineVisibility(i, view1.getVisibility()));
        }

        int l;
        if(mTransientIndices == null)
            l = 0;
        else
            l = mTransientIndices.size();
        for(j = 0; j < l; j++)
        {
            View view = (View)mTransientViews.get(j);
            view.dispatchAttachedToWindow(attachinfo, combineVisibility(i, view.getVisibility()));
        }

    }

    void dispatchCancelPendingInputEvents()
    {
        super.dispatchCancelPendingInputEvents();
        View aview[] = mChildren;
        int i = mChildrenCount;
        for(int j = 0; j < i; j++)
            aview[j].dispatchCancelPendingInputEvents();

    }

    public boolean dispatchCapturedPointerEvent(MotionEvent motionevent)
    {
        if((mPrivateFlags & 0x12) == 18)
        {
            if(super.dispatchCapturedPointerEvent(motionevent))
                return true;
        } else
        if(mFocused != null && (mFocused.mPrivateFlags & 0x10) == 16 && mFocused.dispatchCapturedPointerEvent(motionevent))
            return true;
        return false;
    }

    void dispatchCollectViewAttributes(View.AttachInfo attachinfo, int i)
    {
        if((i & 0xc) == 0)
        {
            super.dispatchCollectViewAttributes(attachinfo, i);
            int j = mChildrenCount;
            View aview[] = mChildren;
            for(int k = 0; k < j; k++)
            {
                View view = aview[k];
                view.dispatchCollectViewAttributes(attachinfo, view.mViewFlags & 0xc | i);
            }

        }
    }

    public void dispatchConfigurationChanged(Configuration configuration)
    {
        super.dispatchConfigurationChanged(configuration);
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            aview[j].dispatchConfigurationChanged(configuration);

    }

    void dispatchDetachedFromWindow()
    {
        cancelAndClearTouchTargets(null);
        exitHoverTargets();
        exitTooltipHoverTargets();
        mLayoutCalledWhileSuppressed = false;
        mChildrenInterestedInDrag = null;
        mIsInterestedInDrag = false;
        if(mCurrentDragStartEvent != null)
        {
            mCurrentDragStartEvent.recycle();
            mCurrentDragStartEvent = null;
        }
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            aview[j].dispatchDetachedFromWindow();

        clearDisappearingChildren();
        int k;
        if(mTransientViews == null)
            k = 0;
        else
            k = mTransientIndices.size();
        for(i = 0; i < k; i++)
            ((View)mTransientViews.get(i)).dispatchDetachedFromWindow();

        super.dispatchDetachedFromWindow();
    }

    public void dispatchDisplayHint(int i)
    {
        super.dispatchDisplayHint(i);
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
            aview[k].dispatchDisplayHint(i);

    }

    boolean dispatchDragEnterExitInPreN(DragEvent dragevent)
    {
        if(dragevent.mAction == 6 && mCurrentDragChild != null)
        {
            mCurrentDragChild.dispatchDragEnterExitInPreN(dragevent);
            mCurrentDragChild = null;
        }
        boolean flag;
        if(mIsInterestedInDrag)
            flag = super.dispatchDragEnterExitInPreN(dragevent);
        else
            flag = false;
        return flag;
    }

    public boolean dispatchDragEvent(DragEvent dragevent)
    {
        boolean flag;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        float f;
        float f1;
        Object obj;
        PointF pointf;
        flag = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        f = dragevent.mX;
        f1 = dragevent.mY;
        obj = dragevent.mClipData;
        pointf = getLocalPoint();
        dragevent.mAction;
        JVM INSTR tableswitch 1 4: default 68
    //                   1 73
    //                   2 359
    //                   3 359
    //                   4 244;
           goto _L1 _L2 _L3 _L3 _L4
_L1:
        flag2 = flag4;
_L6:
        return flag2;
_L2:
        mCurrentDragChild = null;
        mCurrentDragStartEvent = DragEvent.obtain(dragevent);
        int i;
        View aview[];
        int j;
        if(mChildrenInterestedInDrag == null)
            mChildrenInterestedInDrag = new HashSet();
        else
            mChildrenInterestedInDrag.clear();
        i = mChildrenCount;
        aview = mChildren;
        j = 0;
        for(flag2 = flag; j < i; flag2 = flag4)
        {
            obj = aview[j];
            obj.mPrivateFlags2 = ((View) (obj)).mPrivateFlags2 & -4;
            flag4 = flag2;
            if(((View) (obj)).getVisibility() == 0)
            {
                flag4 = flag2;
                if(notifyChildOfDragStart(aview[j]))
                    flag4 = true;
            }
            j++;
        }

        mIsInterestedInDrag = super.dispatchDragEvent(dragevent);
        flag4 = flag2;
        if(mIsInterestedInDrag)
            flag4 = true;
        flag2 = flag4;
        if(!flag4)
        {
            mCurrentDragStartEvent.recycle();
            mCurrentDragStartEvent = null;
            flag2 = flag4;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        HashSet hashset = mChildrenInterestedInDrag;
        flag4 = flag3;
        if(hashset != null)
        {
            obj = hashset.iterator();
            flag4 = flag2;
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                if(((View)((Iterator) (obj)).next()).dispatchDragEvent(dragevent))
                    flag4 = true;
            } while(true);
            hashset.clear();
        }
        if(mCurrentDragStartEvent != null)
        {
            mCurrentDragStartEvent.recycle();
            mCurrentDragStartEvent = null;
        }
        flag2 = flag4;
        if(mIsInterestedInDrag)
        {
            flag2 = flag4;
            if(super.dispatchDragEvent(dragevent))
                flag2 = true;
            mIsInterestedInDrag = false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        View view = findFrontmostDroppableChildAt(dragevent.mX, dragevent.mY, pointf);
        if(view != mCurrentDragChild)
        {
            if(sCascadedDragDrop)
            {
                int k = dragevent.mAction;
                dragevent.mX = 0.0F;
                dragevent.mY = 0.0F;
                dragevent.mClipData = null;
                if(mCurrentDragChild != null)
                {
                    dragevent.mAction = 6;
                    mCurrentDragChild.dispatchDragEnterExitInPreN(dragevent);
                }
                if(view != null)
                {
                    dragevent.mAction = 5;
                    view.dispatchDragEnterExitInPreN(dragevent);
                }
                dragevent.mAction = k;
                dragevent.mX = f;
                dragevent.mY = f1;
                dragevent.mClipData = ((android.content.ClipData) (obj));
            }
            mCurrentDragChild = view;
        }
        obj = view;
        if(view == null)
        {
            obj = view;
            if(mIsInterestedInDrag)
                obj = this;
        }
        flag2 = flag4;
        if(obj != null)
            if(obj != this)
            {
                dragevent.mX = pointf.x;
                dragevent.mY = pointf.y;
                boolean flag5 = ((View) (obj)).dispatchDragEvent(dragevent);
                dragevent.mX = f;
                dragevent.mY = f1;
                flag2 = flag5;
                if(mIsInterestedInDrag)
                {
                    boolean flag1;
                    if(sCascadedDragDrop)
                        flag1 = flag5;
                    else
                        flag1 = dragevent.mEventHandlerWasCalled;
                    flag2 = flag5;
                    if(!flag1)
                        flag2 = super.dispatchDragEvent(dragevent);
                }
            } else
            {
                flag2 = super.dispatchDragEvent(dragevent);
            }
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void dispatchDraw(Canvas canvas)
    {
        boolean flag = canvas.isRecordingFor(mRenderNode);
        int i = mChildrenCount;
        View aview[] = mChildren;
        int j = mGroupFlags;
        if((j & 8) != 0 && canAnimate())
        {
            isHardwareAccelerated();
            for(int k = 0; k < i; k++)
            {
                View view1 = aview[k];
                if((view1.mViewFlags & 0xc) == 0)
                {
                    attachLayoutAnimationParameters(view1, view1.getLayoutParams(), k, i);
                    bindLayoutAnimation(view1);
                }
            }

            LayoutAnimationController layoutanimationcontroller = mLayoutAnimationController;
            if(layoutanimationcontroller.willOverlap())
                mGroupFlags = mGroupFlags | 0x80;
            layoutanimationcontroller.start();
            mGroupFlags = mGroupFlags & -9;
            mGroupFlags = mGroupFlags & 0xffffffef;
            if(mAnimationListener != null)
                mAnimationListener.onAnimationStart(layoutanimationcontroller.getAnimation());
        }
        int i1 = 0;
        int l;
        ArrayList arraylist;
        boolean flag1;
        long l1;
        int j1;
        int k1;
        boolean flag2;
        int j2;
        int k2;
        if((j & 0x22) == 34)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            i1 = canvas.save(2);
            canvas.clipRect(mScrollX + mPaddingLeft, mScrollY + mPaddingTop, (mScrollX + mRight) - mLeft - mPaddingRight, (mScrollY + mBottom) - mTop - mPaddingBottom);
        }
        mPrivateFlags = mPrivateFlags & 0xffffffbf;
        mGroupFlags = mGroupFlags & -5;
        l = 0;
        l1 = getDrawingTime();
        if(flag)
            canvas.insertReorderBarrier();
        if(mTransientIndices == null)
            j1 = 0;
        else
            j1 = mTransientIndices.size();
        if(j1 != 0)
            k1 = 0;
        else
            k1 = -1;
        if(flag)
            arraylist = null;
        else
            arraylist = buildOrderedChildList();
        if(arraylist == null)
            flag2 = isChildrenDrawingOrderEnabled();
        else
            flag2 = false;
        j2 = 0;
        do
        {
            j = l;
            k2 = k1;
            if(j2 >= i)
                break;
            j = k1;
label0:
            do
            {
label1:
                {
                    if(j < 0 || ((Integer)mTransientIndices.get(j)).intValue() != j2)
                        break label0;
                    View view2 = (View)mTransientViews.get(j);
                    if((view2.mViewFlags & 0xc) != 0)
                    {
                        k1 = l;
                        if(view2.getAnimation() == null)
                            break label1;
                    }
                    k1 = l | drawChild(canvas, view2, l1);
                }
                k2 = j + 1;
                l = k1;
                j = k2;
                if(k2 >= j1)
                {
                    j = -1;
                    l = k1;
                }
            } while(true);
label2:
            {
                View view3 = getAndVerifyPreorderedView(arraylist, aview, getAndVerifyPreorderedIndex(i, j2, flag2));
                if((view3.mViewFlags & 0xc) != 0)
                {
                    k1 = l;
                    if(view3.getAnimation() == null)
                        break label2;
                }
                k1 = l | drawChild(canvas, view3, l1);
            }
            j2++;
            l = k1;
            k1 = j;
        } while(true);
label3:
        do
        {
label4:
            {
                l = j;
                if(k2 < 0)
                    break label3;
                View view = (View)mTransientViews.get(k2);
                if((view.mViewFlags & 0xc) != 0)
                {
                    l = j;
                    if(view.getAnimation() == null)
                        break label4;
                }
                l = j | drawChild(canvas, view, l1);
            }
            k1 = k2 + 1;
            j = l;
            k2 = k1;
        } while(k1 < j1);
        if(arraylist != null)
            arraylist.clear();
        j = l;
        if(mDisappearingChildren != null)
        {
            ArrayList arraylist1 = mDisappearingChildren;
            int i2 = arraylist1.size() - 1;
            do
            {
                j = l;
                if(i2 < 0)
                    break;
                l |= drawChild(canvas, (View)arraylist1.get(i2), l1);
                i2--;
            } while(true);
        }
        if(flag)
            canvas.insertInorderBarrier();
        if(debugDraw())
            onDebugDraw(canvas);
        if(flag1)
            canvas.restoreToCount(i1);
        l = mGroupFlags;
        if((l & 4) == 4)
            invalidate(true);
        if((l & 0x10) == 0 && (l & 0x200) == 0 && mLayoutAnimationController != null && mLayoutAnimationController.isDone() && (j ^ 1) != 0)
        {
            mGroupFlags = mGroupFlags | 0x200;
            post(new Runnable() {

                public void run()
                {
                    ViewGroup._2D_wrap1(ViewGroup.this);
                }

                final ViewGroup this$0;

            
            {
                this$0 = ViewGroup.this;
                super();
            }
            }
);
        }
    }

    public void dispatchDrawableHotspotChanged(float f, float f1)
    {
        int i = mChildrenCount;
        if(i == 0)
            return;
        View aview[] = mChildren;
        int j = 0;
        while(j < i) 
        {
            View view = aview[j];
            boolean flag;
            boolean flag1;
            if(!view.isClickable())
                flag = view.isLongClickable() ^ true;
            else
                flag = false;
            if((view.mViewFlags & 0x400000) != 0)
                flag1 = true;
            else
                flag1 = false;
            if(flag || flag1)
            {
                float af[] = getTempPoint();
                af[0] = f;
                af[1] = f1;
                transformPointToViewLocal(af, view);
                view.drawableHotspotChanged(af[0], af[1]);
            }
            j++;
        }
    }

    public void dispatchFinishTemporaryDetach()
    {
        super.dispatchFinishTemporaryDetach();
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            aview[j].dispatchFinishTemporaryDetach();

    }

    protected void dispatchFreezeSelfOnly(SparseArray sparsearray)
    {
        super.dispatchSaveInstanceState(sparsearray);
    }

    protected boolean dispatchGenericFocusedEvent(MotionEvent motionevent)
    {
        if((mPrivateFlags & 0x12) == 18)
            return super.dispatchGenericFocusedEvent(motionevent);
        if(mFocused != null && (mFocused.mPrivateFlags & 0x10) == 16)
            return mFocused.dispatchGenericMotionEvent(motionevent);
        else
            return false;
    }

    protected boolean dispatchGenericPointerEvent(MotionEvent motionevent)
    {
        ArrayList arraylist;
        View view;
        int i = mChildrenCount;
        if(i == 0)
            break MISSING_BLOCK_LABEL_138;
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        arraylist = buildOrderedChildList();
        boolean flag;
        View aview[];
        int j;
        if(arraylist == null)
            flag = isChildrenDrawingOrderEnabled();
        else
            flag = false;
        aview = mChildren;
        j = i - 1;
        if(j < 0)
            break; /* Loop/switch isn't completed */
        view = getAndVerifyPreorderedView(arraylist, aview, getAndVerifyPreorderedIndex(i, j, flag));
          goto _L1
_L5:
        j--;
        if(true) goto _L3; else goto _L2
_L3:
        break MISSING_BLOCK_LABEL_48;
_L1:
        if(!canViewReceivePointerEvents(view) || isTransformedTouchPointInView(f, f1, view, null) ^ true || !dispatchTransformedGenericPointerEvent(motionevent, view)) goto _L5; else goto _L4
_L4:
        if(arraylist != null)
            arraylist.clear();
        return true;
_L2:
        if(arraylist != null)
            arraylist.clear();
        return super.dispatchGenericPointerEvent(motionevent);
    }

    protected void dispatchGetDisplayList()
    {
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < i; k++)
        {
            View view = aview[k];
            if((view.mViewFlags & 0xc) == 0 || view.getAnimation() != null)
                recreateChildDisplayList(view);
        }

        if(mOverlay != null)
            recreateChildDisplayList(mOverlay.getOverlayView());
        if(mDisappearingChildren != null)
        {
            ArrayList arraylist = mDisappearingChildren;
            int j = arraylist.size();
            for(int l = 0; l < j; l++)
                recreateChildDisplayList((View)arraylist.get(l));

        }
    }

    protected boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        Object obj;
        boolean flag1;
        boolean flag3;
        Object obj2;
        Object obj3;
        Object obj4;
        boolean flag4;
        i = motionevent.getAction();
        flag = onInterceptHoverEvent(motionevent);
        motionevent.setAction(i);
        obj = motionevent;
        flag1 = false;
        flag3 = false;
        obj2 = mFirstHoverTarget;
        mFirstHoverTarget = null;
        obj3 = obj;
        obj4 = obj2;
        flag4 = flag1;
        if(flag) goto _L2; else goto _L1
_L1:
        obj3 = obj;
        obj4 = obj2;
        flag4 = flag1;
        if(i == 10) goto _L2; else goto _L3
_L3:
        float f;
        float f1;
        int j;
        f = motionevent.getX();
        f1 = motionevent.getY();
        j = mChildrenCount;
        obj3 = obj;
        obj4 = obj2;
        flag4 = flag1;
        if(j == 0) goto _L2; else goto _L4
_L4:
        ArrayList arraylist;
        Object obj5;
        View view;
        Object obj6;
        Object obj7;
        arraylist = buildOrderedChildList();
        View aview[];
        int k;
        if(arraylist == null)
            flag = isChildrenDrawingOrderEnabled();
        else
            flag = false;
        aview = mChildren;
        obj5 = null;
        k = j - 1;
        obj3 = obj2;
        obj4 = obj;
        obj = obj4;
        obj2 = obj3;
        flag1 = flag3;
        if(k < 0) goto _L6; else goto _L5
_L5:
        view = getAndVerifyPreorderedView(arraylist, aview, getAndVerifyPreorderedIndex(j, k, flag));
        obj2 = obj4;
        obj6 = obj3;
        flag1 = flag3;
        obj7 = obj5;
        if(!canViewReceivePointerEvents(view)) goto _L8; else goto _L7
_L7:
        if(!(isTransformedTouchPointInView(f, f1, view, null) ^ true)) goto _L10; else goto _L9
_L9:
        obj7 = obj5;
        flag1 = flag3;
        obj6 = obj3;
        obj2 = obj4;
_L8:
        k--;
        obj4 = obj2;
        obj3 = obj6;
        flag3 = flag1;
        obj5 = obj7;
        break MISSING_BLOCK_LABEL_144;
_L10:
        obj = obj3;
        obj2 = null;
_L12:
        boolean flag6;
        if(obj == null)
        {
            obj = HoverTarget.obtain(view);
            flag6 = false;
        } else
        {
label0:
            {
                if(((HoverTarget) (obj)).child != view)
                    break label0;
                if(obj2 != null)
                    obj2.next = ((HoverTarget) (obj)).next;
                else
                    obj3 = ((HoverTarget) (obj)).next;
                obj.next = null;
                flag6 = true;
            }
        }
        if(obj5 != null)
            obj5.next = ((HoverTarget) (obj));
        else
            mFirstHoverTarget = ((HoverTarget) (obj));
        obj7 = obj;
        if(i == 9)
        {
            obj = obj4;
            flag4 = flag3;
            if(!flag6)
            {
                flag4 = flag3 | dispatchTransformedGenericPointerEvent(motionevent, view);
                obj = obj4;
            }
        } else
        {
            obj = obj4;
            flag4 = flag3;
            if(i == 7)
                if(!flag6)
                {
                    obj = obtainMotionEventNoHistoryOrSelf(((MotionEvent) (obj4)));
                    ((MotionEvent) (obj)).setAction(9);
                    flag4 = dispatchTransformedGenericPointerEvent(((MotionEvent) (obj)), view);
                    ((MotionEvent) (obj)).setAction(i);
                    flag4 = flag3 | flag4 | dispatchTransformedGenericPointerEvent(((MotionEvent) (obj)), view);
                } else
                {
                    flag4 = flag3 | dispatchTransformedGenericPointerEvent(motionevent, view);
                    obj = obj4;
                }
        }
        obj2 = obj;
        obj6 = obj3;
        flag1 = flag4;
        if(!flag4) goto _L8; else goto _L11
_L11:
        flag1 = flag4;
        obj2 = obj3;
_L6:
        obj3 = obj;
        obj4 = obj2;
        flag4 = flag1;
        if(arraylist != null)
        {
            arraylist.clear();
            flag4 = flag1;
            obj4 = obj2;
            obj3 = obj;
        }
_L2:
        while(obj4 != null) 
        {
            obj = ((HoverTarget) (obj4)).child;
            if(i == 10)
            {
                flag4 |= dispatchTransformedGenericPointerEvent(motionevent, ((View) (obj)));
            } else
            {
                if(i == 7)
                {
                    flag3 = motionevent.isHoverExitPending();
                    motionevent.setHoverExitPending(true);
                    dispatchTransformedGenericPointerEvent(motionevent, ((View) (obj)));
                    motionevent.setHoverExitPending(flag3);
                }
                obj3 = obtainMotionEventNoHistoryOrSelf(((MotionEvent) (obj3)));
                ((MotionEvent) (obj3)).setAction(10);
                dispatchTransformedGenericPointerEvent(((MotionEvent) (obj3)), ((View) (obj)));
                ((MotionEvent) (obj3)).setAction(i);
            }
            obj = ((HoverTarget) (obj4)).next;
            ((HoverTarget) (obj4)).recycle();
            obj4 = obj;
        }
        break MISSING_BLOCK_LABEL_663;
        obj2 = obj;
        obj = ((HoverTarget) (obj)).next;
          goto _L12
        if(!flag4 && i != 10)
            flag = motionevent.isHoverExitPending() ^ true;
        else
            flag = false;
        if(flag != mHoveredSelf) goto _L14; else goto _L13
_L13:
        obj4 = obj3;
        flag3 = flag4;
        if(flag)
        {
            flag3 = flag4 | super.dispatchHoverEvent(motionevent);
            obj4 = obj3;
        }
_L16:
        if(obj4 != motionevent)
            ((MotionEvent) (obj4)).recycle();
        return flag3;
_L14:
        Object obj1 = obj3;
        boolean flag2 = flag4;
        if(mHoveredSelf)
        {
            if(i == 10)
            {
                flag4 |= super.dispatchHoverEvent(motionevent);
            } else
            {
                if(i == 7)
                    super.dispatchHoverEvent(motionevent);
                obj3 = obtainMotionEventNoHistoryOrSelf(((MotionEvent) (obj3)));
                ((MotionEvent) (obj3)).setAction(10);
                super.dispatchHoverEvent(((MotionEvent) (obj3)));
                ((MotionEvent) (obj3)).setAction(i);
            }
            mHoveredSelf = false;
            flag2 = flag4;
            obj1 = obj3;
        }
        obj4 = obj1;
        flag3 = flag2;
        if(flag)
            if(i == 9)
            {
                flag3 = flag2 | super.dispatchHoverEvent(motionevent);
                mHoveredSelf = true;
                obj4 = obj1;
            } else
            {
                obj4 = obj1;
                flag3 = flag2;
                if(i == 7)
                {
                    obj4 = obtainMotionEventNoHistoryOrSelf(((MotionEvent) (obj1)));
                    ((MotionEvent) (obj4)).setAction(9);
                    boolean flag5 = super.dispatchHoverEvent(((MotionEvent) (obj4)));
                    ((MotionEvent) (obj4)).setAction(i);
                    flag3 = flag2 | flag5 | super.dispatchHoverEvent(((MotionEvent) (obj4)));
                    mHoveredSelf = true;
                }
            }
        if(true) goto _L16; else goto _L15
_L15:
          goto _L12
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onKeyEvent(keyevent, 1);
        if((mPrivateFlags & 0x12) == 18)
        {
            if(super.dispatchKeyEvent(keyevent))
                return true;
        } else
        if(mFocused != null && (mFocused.mPrivateFlags & 0x10) == 16 && mFocused.dispatchKeyEvent(keyevent))
            return true;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onUnhandledEvent(keyevent, 1);
        return false;
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyevent)
    {
        if((mPrivateFlags & 0x12) == 18)
            return super.dispatchKeyEventPreIme(keyevent);
        if(mFocused != null && (mFocused.mPrivateFlags & 0x10) == 16)
            return mFocused.dispatchKeyEventPreIme(keyevent);
        else
            return false;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        if((mPrivateFlags & 0x12) == 18)
            return super.dispatchKeyShortcutEvent(keyevent);
        if(mFocused != null && (mFocused.mPrivateFlags & 0x10) == 16)
            return mFocused.dispatchKeyShortcutEvent(keyevent);
        else
            return false;
    }

    void dispatchMovedToDisplay(Display display, Configuration configuration)
    {
        super.dispatchMovedToDisplay(display, configuration);
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            aview[j].dispatchMovedToDisplay(display, configuration);

    }

    public void dispatchPointerCaptureChanged(boolean flag)
    {
        exitHoverTargets();
        super.dispatchPointerCaptureChanged(flag);
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            aview[j].dispatchPointerCaptureChanged(flag);

    }

    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        ChildListForAccessibility childlistforaccessibility;
        if(includeForAccessibility())
        {
            boolean flag = super.dispatchPopulateAccessibilityEventInternal(accessibilityevent);
            if(flag)
                return flag;
        }
        childlistforaccessibility = ChildListForAccessibility.obtain(this, true);
        int i = childlistforaccessibility.getChildCount();
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        boolean flag1;
        View view = childlistforaccessibility.getChildAt(j);
        if((view.mViewFlags & 0xc) != 0)
            continue; /* Loop/switch isn't completed */
        flag1 = view.dispatchPopulateAccessibilityEvent(accessibilityevent);
        if(flag1)
        {
            childlistforaccessibility.recycle();
            return flag1;
        }
        j++;
          goto _L3
_L2:
        childlistforaccessibility.recycle();
        return false;
        accessibilityevent;
        childlistforaccessibility.recycle();
        throw accessibilityevent;
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        super.dispatchProvideAutofillStructure(viewstructure, i);
        if(viewstructure.getChildCount() != 0)
            return;
        if(!isLaidOut())
        {
            Log.v("View", (new StringBuilder()).append("dispatchProvideAutofillStructure(): not laid out, ignoring ").append(mChildrenCount).append(" children of ").append(getAutofillId()).toString());
            return;
        }
        ChildListForAutoFill childlistforautofill = getChildrenForAutofill(i);
        int j = childlistforautofill.size();
        viewstructure.setChildCount(j);
        for(int k = 0; k < j; k++)
            ((View)childlistforautofill.get(k)).dispatchProvideAutofillStructure(viewstructure.newChild(k), i);

        childlistforautofill.recycle();
    }

    public void dispatchProvideStructure(ViewStructure viewstructure)
    {
        int i;
        Object obj;
        boolean flag;
        int j;
        int k;
        super.dispatchProvideStructure(viewstructure);
        if(isAssistBlocked() || viewstructure.getChildCount() != 0)
            return;
        i = mChildrenCount;
        if(i <= 0)
            return;
        if(!isLaidOut())
        {
            Log.v("View", (new StringBuilder()).append("dispatchProvideStructure(): not laid out, ignoring ").append(i).append(" children of ").append(getAccessibilityViewId()).toString());
            return;
        }
        viewstructure.setChildCount(i);
        obj = buildOrderedChildList();
        if(obj == null)
            flag = isChildrenDrawingOrderEnabled();
        else
            flag = false;
        j = 0;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        k = getAndVerifyPreorderedIndex(i, j, flag);
_L4:
        getAndVerifyPreorderedView(((ArrayList) (obj)), mChildren, k).dispatchProvideStructure(viewstructure.newChild(j));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break MISSING_BLOCK_LABEL_101;
        Object obj1;
        obj1;
label0:
        {
            int l = j;
            if(mContext.getApplicationInfo().targetSdkVersion >= 23)
                break label0;
            Log.w("ViewGroup", (new StringBuilder()).append("Bad getChildDrawingOrder while collecting assist @ ").append(j).append(" of ").append(i).toString(), ((Throwable) (obj1)));
            boolean flag1 = false;
            k = l;
            flag = flag1;
            if(j > 0)
            {
                int ai[] = new int[i];
                obj = new SparseBooleanArray();
                for(int i1 = 0; i1 < j; i1++)
                {
                    ai[i1] = getChildDrawingOrder(i, i1);
                    ((SparseBooleanArray) (obj)).put(ai[i1], true);
                }

                int j1 = 0;
                for(k = j; k < i; k++)
                {
                    for(; ((SparseBooleanArray) (obj)).get(j1, false); j1++);
                    ai[k] = j1;
                    j1++;
                }

                obj1 = new ArrayList(i);
                j1 = 0;
                do
                {
                    k = l;
                    flag = flag1;
                    obj = obj1;
                    if(j1 >= i)
                        break;
                    k = ai[j1];
                    ((ArrayList) (obj1)).add(mChildren[k]);
                    j1++;
                } while(true);
            }
        }
        if(true) goto _L4; else goto _L3
        throw obj1;
_L3:
        if(obj != null)
            ((ArrayList) (obj)).clear();
        return;
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        super.dispatchRestoreInstanceState(sparsearray);
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
        {
            View view = aview[j];
            if((view.mViewFlags & 0x20000000) != 0x20000000)
                view.dispatchRestoreInstanceState(sparsearray);
        }

    }

    protected void dispatchSaveInstanceState(SparseArray sparsearray)
    {
        super.dispatchSaveInstanceState(sparsearray);
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
        {
            View view = aview[j];
            if((view.mViewFlags & 0x20000000) != 0x20000000)
                view.dispatchSaveInstanceState(sparsearray);
        }

    }

    void dispatchScreenStateChanged(int i)
    {
        super.dispatchScreenStateChanged(i);
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
            aview[k].dispatchScreenStateChanged(i);

    }

    public void dispatchSetActivated(boolean flag)
    {
        View aview[] = mChildren;
        int i = mChildrenCount;
        for(int j = 0; j < i; j++)
            aview[j].setActivated(flag);

    }

    protected void dispatchSetPressed(boolean flag)
    {
        View aview[] = mChildren;
        int i = mChildrenCount;
        for(int j = 0; j < i; j++)
        {
            View view = aview[j];
            if(!flag || !view.isClickable() && view.isLongClickable() ^ true)
                view.setPressed(flag);
        }

    }

    public void dispatchSetSelected(boolean flag)
    {
        View aview[] = mChildren;
        int i = mChildrenCount;
        for(int j = 0; j < i; j++)
            aview[j].setSelected(flag);

    }

    public void dispatchStartTemporaryDetach()
    {
        super.dispatchStartTemporaryDetach();
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            aview[j].dispatchStartTemporaryDetach();

    }

    public void dispatchSystemUiVisibilityChanged(int i)
    {
        super.dispatchSystemUiVisibilityChanged(i);
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
            aview[k].dispatchSystemUiVisibilityChanged(i);

    }

    protected void dispatchThawSelfOnly(SparseArray sparsearray)
    {
        super.dispatchRestoreInstanceState(sparsearray);
    }

    boolean dispatchTooltipHoverEvent(MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        i;
        JVM INSTR tableswitch 7 10: default 36
    //                   7 38
    //                   8 36
    //                   9 36
    //                   10 275;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        return false;
_L2:
        Object obj;
        Object obj1;
        int j;
        obj = null;
        obj1 = null;
        j = mChildrenCount;
        if(j == 0) goto _L5; else goto _L4
_L4:
        ArrayList arraylist;
        View view;
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        arraylist = buildOrderedChildList();
        boolean flag;
        int k;
        if(arraylist == null)
            flag = isChildrenDrawingOrderEnabled();
        else
            flag = false;
        obj = mChildren;
        k = j - 1;
_L8:
        view = obj1;
        if(k < 0)
            break; /* Loop/switch isn't completed */
        view = getAndVerifyPreorderedView(arraylist, ((View []) (obj)), getAndVerifyPreorderedIndex(j, k, flag));
          goto _L6
_L9:
        k--;
        if(true) goto _L8; else goto _L7
_L6:
        if(!canViewReceivePointerEvents(view) || isTransformedTouchPointInView(f, f1, view, null) ^ true || !dispatchTooltipHoverEvent(motionevent, view)) goto _L9; else goto _L7
_L7:
        obj = view;
        if(arraylist != null)
        {
            arraylist.clear();
            obj = view;
        }
_L5:
        if(mTooltipHoverTarget != obj)
        {
            if(mTooltipHoverTarget != null)
            {
                motionevent.setAction(10);
                mTooltipHoverTarget.dispatchTooltipHoverEvent(motionevent);
                motionevent.setAction(i);
            }
            mTooltipHoverTarget = ((View) (obj));
        }
        if(mTooltipHoverTarget != null)
        {
            if(mTooltipHoveredSelf)
            {
                mTooltipHoveredSelf = false;
                motionevent.setAction(10);
                super.dispatchTooltipHoverEvent(motionevent);
                motionevent.setAction(i);
            }
            return true;
        } else
        {
            mTooltipHoveredSelf = super.dispatchTooltipHoverEvent(motionevent);
            return mTooltipHoveredSelf;
        }
_L3:
        if(mTooltipHoverTarget != null)
        {
            mTooltipHoverTarget.dispatchTooltipHoverEvent(motionevent);
            mTooltipHoverTarget = null;
        } else
        if(mTooltipHoveredSelf)
        {
            super.dispatchTooltipHoverEvent(motionevent);
            mTooltipHoveredSelf = false;
        }
        if(true) goto _L1; else goto _L10
_L10:
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onTouchEvent(motionevent, 1);
        if(motionevent.isTargetAccessibilityFocus() && isAccessibilityFocusedViewOrHost())
            motionevent.setTargetAccessibilityFocus(false);
        flag = false;
        flag1 = false;
        if(!onFilterTouchEventForSecurity(motionevent)) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        int k;
        boolean flag2;
        boolean flag3;
        View aview[];
        ArrayList arraylist;
        Object obj;
        boolean flag4;
        int l;
        Object obj1;
        int i1;
        float f;
        float f1;
        int j1;
        View view;
        i = motionevent.getAction();
        j = i & 0xff;
        if(j == 0)
        {
            cancelAndClearTouchTargets(motionevent);
            resetTouchState();
        }
        if(j == 0 || mFirstTouchTarget != null)
        {
            if((mGroupFlags & 0x80000) != 0)
                k = 1;
            else
                k = 0;
            if(k == 0)
            {
                flag1 = onInterceptTouchEvent(motionevent);
                motionevent.setAction(i);
            } else
            {
                flag1 = false;
            }
        } else
        {
            flag1 = true;
        }
        if(flag1 || mFirstTouchTarget != null)
            motionevent.setTargetAccessibilityFocus(false);
        if(!resetCancelNextUpFlag(this))
        {
            if(j == 3)
                flag2 = true;
            else
                flag2 = false;
        } else
        {
            flag2 = true;
        }
        if((mGroupFlags & 0x200000) != 0)
            flag3 = true;
        else
            flag3 = false;
        aview = null;
        arraylist = null;
        obj = null;
        flag4 = false;
        k = 0;
        l = 0;
        i = l;
        obj1 = aview;
        if(!flag2)
        {
            i = l;
            obj1 = aview;
            if(flag1 ^ true)
            {
                Object obj2;
                if(motionevent.isTargetAccessibilityFocus())
                    obj2 = findChildWithAccessibilityFocus();
                else
                    obj2 = null;
                if(j == 0 || flag3 && j == 5 || j == 7)
                    break MISSING_BLOCK_LABEL_236;
                obj1 = aview;
                i = l;
            }
        }
          goto _L3
        i = motionevent.getActionIndex();
        if(flag3)
            l = 1 << motionevent.getPointerId(i);
        else
            l = -1;
        removePointersFromTouchTargets(l);
        i1 = mChildrenCount;
        aview = arraylist;
        if(i1 == 0) goto _L5; else goto _L4
_L4:
        f = motionevent.getX(i);
        f1 = motionevent.getY(i);
        arraylist = buildTouchDispatchChildList();
        boolean flag5;
        if(arraylist == null)
            flag5 = isChildrenDrawingOrderEnabled();
        else
            flag5 = false;
        aview = mChildren;
        k = i1 - 1;
        obj1 = obj;
        i = ((flag4) ? 1 : 0);
        obj = obj1;
        if(k < 0) goto _L7; else goto _L6
_L6:
        j1 = getAndVerifyPreorderedIndex(i1, k, flag5);
        view = getAndVerifyPreorderedView(arraylist, aview, j1);
        obj = obj2;
        i = k;
        if(obj2 == null) goto _L9; else goto _L8
_L8:
        if(obj2 == view) goto _L11; else goto _L10
_L10:
        obj = obj2;
_L15:
        k--;
        obj2 = obj;
        break MISSING_BLOCK_LABEL_327;
_L3:
        if(mFirstTouchTarget != null) goto _L13; else goto _L12
_L12:
        flag = dispatchTransformedTouchEvent(motionevent, flag2, null, -1);
          goto _L14
_L2:
        if(!flag1 && mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onUnhandledEvent(motionevent, 1);
        return flag1;
_L11:
        obj = null;
        i = i1 - 1;
_L9:
label0:
        {
            if(canViewReceivePointerEvents(view) && !(isTransformedTouchPointInView(f, f1, view, null) ^ true))
                break label0;
            motionevent.setTargetAccessibilityFocus(false);
            k = i;
        }
          goto _L15
        obj1 = getTouchTarget(view);
        if(obj1 == null) goto _L17; else goto _L16
_L16:
        obj1.pointerIdBits = ((TouchTarget) (obj1)).pointerIdBits | l;
        obj = obj1;
        i = ((flag4) ? 1 : 0);
_L7:
        k = i;
        aview = ((View []) (obj));
        if(arraylist != null)
        {
            arraylist.clear();
            aview = ((View []) (obj));
            k = i;
        }
_L5:
        i = k;
        obj1 = aview;
        if(aview != null) goto _L3; else goto _L18
_L18:
        i = k;
        obj1 = aview;
        if(mFirstTouchTarget == null) goto _L3; else goto _L19
_L17:
        resetCancelNextUpFlag(view);
        if(!dispatchTransformedTouchEvent(motionevent, false, view, l))
            break MISSING_BLOCK_LABEL_787;
        mLastTouchDownTime = motionevent.getDownTime();
        if(arraylist == null)
            break MISSING_BLOCK_LABEL_778;
        k = 0;
_L24:
        if(k >= i1) goto _L21; else goto _L20
_L20:
        if(aview[j1] != mChildren[k]) goto _L23; else goto _L22
_L22:
        mLastTouchDownIndex = k;
_L21:
        mLastTouchDownX = motionevent.getX();
        mLastTouchDownY = motionevent.getY();
        obj = addTouchTarget(view, l);
        i = 1;
          goto _L7
_L23:
        k++;
          goto _L24
        mLastTouchDownIndex = j1;
          goto _L21
        motionevent.setTargetAccessibilityFocus(false);
        k = i;
          goto _L15
_L19:
        for(obj1 = mFirstTouchTarget; ((TouchTarget) (obj1)).next != null; obj1 = ((TouchTarget) (obj1)).next);
        obj1.pointerIdBits = ((TouchTarget) (obj1)).pointerIdBits | l;
        i = k;
          goto _L3
_L13:
        TouchTarget touchtarget1;
        boolean flag6;
        touchtarget1 = null;
        obj = mFirstTouchTarget;
        flag6 = flag;
_L28:
        flag = flag6;
        if(obj == null) goto _L14; else goto _L25
_L25:
        TouchTarget touchtarget = ((TouchTarget) (obj)).next;
        if(i == 0 || obj != obj1) goto _L27; else goto _L26
_L26:
        flag = true;
_L30:
        touchtarget1 = ((TouchTarget) (obj));
        obj = touchtarget;
        flag6 = flag;
          goto _L28
_L27:
        boolean flag7;
        if(!resetCancelNextUpFlag(((TouchTarget) (obj)).child))
            flag7 = flag1;
        else
            flag7 = true;
        if(dispatchTransformedTouchEvent(motionevent, flag7, ((TouchTarget) (obj)).child, ((TouchTarget) (obj)).pointerIdBits))
            flag6 = true;
        flag = flag6;
        if(!flag7) goto _L30; else goto _L29
_L29:
        if(touchtarget1 == null)
            mFirstTouchTarget = touchtarget;
        else
            touchtarget1.next = touchtarget;
        ((TouchTarget) (obj)).recycle();
        obj = touchtarget;
          goto _L28
_L14:
        if(flag2 || j == 1 || j == 7)
        {
            resetTouchState();
            flag1 = flag;
        } else
        {
            flag1 = flag;
            if(flag3)
            {
                flag1 = flag;
                if(j == 6)
                {
                    removePointersFromTouchTargets(1 << motionevent.getPointerId(motionevent.getActionIndex()));
                    flag1 = flag;
                }
            }
        }
          goto _L2
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onTrackballEvent(motionevent, 1);
        if((mPrivateFlags & 0x12) == 18)
        {
            if(super.dispatchTrackballEvent(motionevent))
                return true;
        } else
        if(mFocused != null && (mFocused.mPrivateFlags & 0x10) == 16 && mFocused.dispatchTrackballEvent(motionevent))
            return true;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onUnhandledEvent(motionevent, 1);
        return false;
    }

    public boolean dispatchUnhandledMove(View view, int i)
    {
        boolean flag;
        if(mFocused != null)
            flag = mFocused.dispatchUnhandledMove(view, i);
        else
            flag = false;
        return flag;
    }

    void dispatchViewAdded(View view)
    {
        onViewAdded(view);
        if(mOnHierarchyChangeListener != null)
            mOnHierarchyChangeListener.onChildViewAdded(this, view);
    }

    void dispatchViewRemoved(View view)
    {
        onViewRemoved(view);
        if(mOnHierarchyChangeListener != null)
            mOnHierarchyChangeListener.onChildViewRemoved(this, view);
    }

    boolean dispatchVisibilityAggregated(boolean flag)
    {
        flag = super.dispatchVisibilityAggregated(flag);
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            if(aview[j].getVisibility() == 0)
                aview[j].dispatchVisibilityAggregated(flag);

        return flag;
    }

    protected void dispatchVisibilityChanged(View view, int i)
    {
        super.dispatchVisibilityChanged(view, i);
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
            aview[k].dispatchVisibilityChanged(view, i);

    }

    public void dispatchWindowFocusChanged(boolean flag)
    {
        super.dispatchWindowFocusChanged(flag);
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            aview[j].dispatchWindowFocusChanged(flag);

    }

    public void dispatchWindowSystemUiVisiblityChanged(int i)
    {
        super.dispatchWindowSystemUiVisiblityChanged(i);
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
            aview[k].dispatchWindowSystemUiVisiblityChanged(i);

    }

    public void dispatchWindowVisibilityChanged(int i)
    {
        super.dispatchWindowVisibilityChanged(i);
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
            aview[k].dispatchWindowVisibilityChanged(i);

    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        return view.draw(canvas, this, l);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if((mGroupFlags & 0x10000) != 0)
        {
            if((mGroupFlags & 0x2000) != 0)
                throw new IllegalStateException("addStateFromChildren cannot be enabled if a child has duplicateParentState set to true");
            View aview[] = mChildren;
            int i = mChildrenCount;
            for(int j = 0; j < i; j++)
            {
                View view = aview[j];
                if((view.mViewFlags & 0x400000) != 0)
                    view.refreshDrawableState();
            }

        }
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("focus:descendantFocusability", getDescendantFocusability());
        viewhierarchyencoder.addProperty("drawing:clipChildren", getClipChildren());
        viewhierarchyencoder.addProperty("drawing:clipToPadding", getClipToPadding());
        viewhierarchyencoder.addProperty("drawing:childrenDrawingOrderEnabled", isChildrenDrawingOrderEnabled());
        viewhierarchyencoder.addProperty("drawing:persistentDrawingCache", getPersistentDrawingCache());
        int i = getChildCount();
        viewhierarchyencoder.addProperty("meta:__childCount__", (short)i);
        for(int j = 0; j < i; j++)
        {
            viewhierarchyencoder.addPropertyKey((new StringBuilder()).append("meta:__child__").append(j).toString());
            getChildAt(j).encode(viewhierarchyencoder);
        }

    }

    public void endViewTransition(View view)
    {
        if(mTransitioningViews == null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        mTransitioningViews.remove(view);
        arraylist = mDisappearingChildren;
        if(arraylist == null || !arraylist.contains(view)) goto _L2; else goto _L3
_L3:
        arraylist.remove(view);
        if(mVisibilityChangingChildren == null || !mVisibilityChangingChildren.contains(view)) goto _L5; else goto _L4
_L4:
        mVisibilityChangingChildren.remove(view);
_L7:
        invalidate();
_L2:
        return;
_L5:
        if(view.mAttachInfo != null)
            view.dispatchDetachedFromWindow();
        if(view.mParent != null)
            view.mParent = null;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public View findFocus()
    {
        if(isFocused())
            return this;
        if(mFocused != null)
            return mFocused.findFocus();
        else
            return null;
    }

    View findFrontmostDroppableChildAt(float f, float f1, PointF pointf)
    {
        int i;
        View aview[];
        i = mChildrenCount;
        aview = mChildren;
        i--;
_L3:
        View view;
        if(i < 0)
            break; /* Loop/switch isn't completed */
        view = aview[i];
          goto _L1
_L5:
        i--;
        if(true) goto _L3; else goto _L2
_L1:
        if(!view.canAcceptDrag() || !isTransformedTouchPointInView(f, f1, view, pointf)) goto _L5; else goto _L4
_L4:
        return view;
_L2:
        return null;
    }

    public void findNamedViews(Map map)
    {
        if(getVisibility() != 0 && mGhostView == null)
            return;
        super.findNamedViews(map);
        int i = getChildCount();
        for(int j = 0; j < i; j++)
            getChildAt(j).findNamedViews(map);

    }

    public View findViewByAccessibilityIdTraversal(int i)
    {
        View view = super.findViewByAccessibilityIdTraversal(i);
        if(view != null)
            return view;
        if(getAccessibilityNodeProvider() != null)
            return null;
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
        {
            View view1 = aview[k].findViewByAccessibilityIdTraversal(i);
            if(view1 != null)
                return view1;
        }

        return null;
    }

    public View findViewByAutofillIdTraversal(int i)
    {
        View view = super.findViewByAutofillIdTraversal(i);
        if(view != null)
            return view;
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
        {
            View view1 = aview[k].findViewByAutofillIdTraversal(i);
            if(view1 != null)
                return view1;
        }

        return null;
    }

    protected View findViewByPredicateTraversal(Predicate predicate, View view)
    {
        if(predicate.test(this))
            return this;
        View aview[] = mChildren;
        int i = mChildrenCount;
        for(int j = 0; j < i; j++)
        {
            View view1 = aview[j];
            if(view1 == view || (view1.mPrivateFlags & 8) != 0)
                continue;
            view1 = view1.findViewByPredicate(predicate);
            if(view1 != null)
                return view1;
        }

        return null;
    }

    protected View findViewTraversal(int i)
    {
        if(i == mID)
            return this;
        View aview[] = mChildren;
        int j = mChildrenCount;
        for(int k = 0; k < j; k++)
        {
            View view = aview[k];
            if((view.mPrivateFlags & 8) != 0)
                continue;
            view = view.findViewById(i);
            if(view != null)
                return view;
        }

        return null;
    }

    protected View findViewWithTagTraversal(Object obj)
    {
        if(obj != null && obj.equals(mTag))
            return this;
        View aview[] = mChildren;
        int i = mChildrenCount;
        for(int j = 0; j < i; j++)
        {
            View view = aview[j];
            if((view.mPrivateFlags & 8) != 0)
                continue;
            view = view.findViewWithTag(obj);
            if(view != null)
                return view;
        }

        return null;
    }

    public void findViewsWithText(ArrayList arraylist, CharSequence charsequence, int i)
    {
        super.findViewsWithText(arraylist, charsequence, i);
        int j = mChildrenCount;
        View aview[] = mChildren;
        for(int k = 0; k < j; k++)
        {
            View view = aview[k];
            if((view.mViewFlags & 0xc) == 0 && (view.mPrivateFlags & 8) == 0)
                view.findViewsWithText(arraylist, charsequence, i);
        }

    }

    void finishAnimatingView(View view, Animation animation)
    {
        ArrayList arraylist = mDisappearingChildren;
        if(arraylist != null && arraylist.contains(view))
        {
            arraylist.remove(view);
            if(view.mAttachInfo != null)
                view.dispatchDetachedFromWindow();
            view.clearAnimation();
            mGroupFlags = mGroupFlags | 4;
        }
        if(animation != null && animation.getFillAfter() ^ true)
            view.clearAnimation();
        if((view.mPrivateFlags & 0x10000) == 0x10000)
        {
            view.onAnimationEnd();
            view.mPrivateFlags = view.mPrivateFlags & 0xfffeffff;
            mGroupFlags = mGroupFlags | 4;
        }
    }

    public View focusSearch(View view, int i)
    {
        if(isRootNamespace())
            return FocusFinder.getInstance().findNextFocus(this, view, i);
        if(mParent != null)
            return mParent.focusSearch(view, i);
        else
            return null;
    }

    public void focusableViewAvailable(View view)
    {
        boolean flag = false;
        if(mParent != null && getDescendantFocusability() != 0x60000 && (mViewFlags & 0xc) == 0 && (isFocusableInTouchMode() || shouldBlockFocusForTouchscreen() ^ true))
        {
            boolean flag1 = flag;
            if(isFocused())
            {
                flag1 = flag;
                if(getDescendantFocusability() != 0x40000)
                    flag1 = true;
            }
            if(flag1 ^ true)
                mParent.focusableViewAvailable(view);
        }
    }

    public boolean gatherTransparentRegion(Region region)
    {
        boolean flag;
        boolean flag1;
label0:
        {
            if((mPrivateFlags & 0x200) == 0)
                flag = true;
            else
                flag = false;
            if(flag && region == null)
                return true;
            super.gatherTransparentRegion(region);
            int i = mChildrenCount;
            flag1 = true;
            boolean flag2 = true;
            if(i <= 0)
                break label0;
            ArrayList arraylist = buildOrderedChildList();
            View aview[];
            if(arraylist == null)
                flag1 = isChildrenDrawingOrderEnabled();
            else
                flag1 = false;
            aview = mChildren;
            for(int j = 0; j < i;)
            {
                boolean flag3;
label1:
                {
                    View view = getAndVerifyPreorderedView(arraylist, aview, getAndVerifyPreorderedIndex(i, j, flag1));
                    if((view.mViewFlags & 0xc) != 0)
                    {
                        flag3 = flag2;
                        if(view.getAnimation() == null)
                            break label1;
                    }
                    flag3 = flag2;
                    if(!view.gatherTransparentRegion(region))
                        flag3 = false;
                }
                j++;
                flag2 = flag3;
            }

            flag1 = flag2;
            if(arraylist != null)
            {
                arraylist.clear();
                flag1 = flag2;
            }
        }
        if(flag)
            flag1 = true;
        return flag1;
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutparams)
    {
        return layoutparams;
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/view/ViewGroup.getName();
    }

    public View getChildAt(int i)
    {
        if(i < 0 || i >= mChildrenCount)
            return null;
        else
            return mChildren[i];
    }

    public int getChildCount()
    {
        return mChildrenCount;
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        return j;
    }

    protected boolean getChildStaticTransformation(View view, Transformation transformation)
    {
        return false;
    }

    Transformation getChildTransformation()
    {
        if(mChildTransformation == null)
            mChildTransformation = new Transformation();
        return mChildTransformation;
    }

    public boolean getChildVisibleRect(View view, Rect rect, Point point)
    {
        return getChildVisibleRect(view, rect, point, false);
    }

    public boolean getChildVisibleRect(View view, Rect rect, Point point, boolean flag)
    {
label0:
        {
            RectF rectf;
            int i;
            int j;
            if(mAttachInfo != null)
                rectf = mAttachInfo.mTmpTransformRect;
            else
                rectf = new RectF();
            rectf.set(rect);
            if(!view.hasIdentityMatrix())
                view.getMatrix().mapRect(rectf);
            i = view.mLeft - mScrollX;
            j = view.mTop - mScrollY;
            rectf.offset(i, j);
            if(point != null)
            {
                if(!view.hasIdentityMatrix())
                {
                    float af[];
                    boolean flag1;
                    boolean flag3;
                    if(mAttachInfo != null)
                        af = mAttachInfo.mTmpTransformLocation;
                    else
                        af = new float[2];
                    af[0] = point.x;
                    af[1] = point.y;
                    view.getMatrix().mapPoints(af);
                    point.x = Math.round(af[0]);
                    point.y = Math.round(af[1]);
                }
                point.x = point.x + i;
                point.y = point.y + j;
            }
            j = mRight - mLeft;
            i = mBottom - mTop;
            flag1 = true;
            if(mParent != null)
            {
                flag3 = flag1;
                if(!(mParent instanceof ViewGroup))
                    break label0;
                flag3 = flag1;
                if(!((ViewGroup)mParent).getClipChildren())
                    break label0;
            }
            flag3 = rectf.intersect(0.0F, 0.0F, j, i);
        }
label1:
        {
            if(!flag)
            {
                flag1 = flag3;
                if(!flag3)
                    break label1;
            }
            flag1 = flag3;
            if((mGroupFlags & 0x22) == 34)
                flag1 = rectf.intersect(mPaddingLeft, mPaddingTop, j - mPaddingRight, i - mPaddingBottom);
        }
label2:
        {
            if(!flag)
            {
                flag3 = flag1;
                if(!flag1)
                    break label2;
            }
            flag3 = flag1;
            if(mClipBounds != null)
                flag3 = rectf.intersect(mClipBounds.left, mClipBounds.top, mClipBounds.right, mClipBounds.bottom);
        }
label3:
        {
            rect.set((int)Math.floor(rectf.left), (int)Math.floor(rectf.top), (int)Math.ceil(rectf.right), (int)Math.ceil(rectf.bottom));
            boolean flag2;
            if(!flag)
            {
                flag2 = flag3;
                if(!flag3)
                    break label3;
            }
            flag2 = flag3;
            if(mParent != null)
                if(mParent instanceof ViewGroup)
                    flag2 = ((ViewGroup)mParent).getChildVisibleRect(((View) (this)), rect, point, flag);
                else
                    flag2 = mParent.getChildVisibleRect(this, rect, point);
        }
        return flag2;
    }

    public boolean getClipChildren()
    {
        boolean flag = false;
        if((mGroupFlags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean getClipToPadding()
    {
        return hasBooleanFlag(2);
    }

    View getDeepestFocusedChild()
    {
        for(Object obj = this; obj != null;)
        {
            if(((View) (obj)).isFocused())
                return ((View) (obj));
            if(obj instanceof ViewGroup)
                obj = ((ViewGroup)obj).getFocusedChild();
            else
                obj = null;
        }

        return null;
    }

    public int getDescendantFocusability()
    {
        return mGroupFlags & 0x60000;
    }

    public View getFocusedChild()
    {
        return mFocused;
    }

    public LayoutAnimationController getLayoutAnimation()
    {
        return mLayoutAnimationController;
    }

    public android.view.animation.Animation.AnimationListener getLayoutAnimationListener()
    {
        return mAnimationListener;
    }

    public int getLayoutMode()
    {
        if(mLayoutMode == -1)
        {
            int i;
            if(mParent instanceof ViewGroup)
                i = ((ViewGroup)mParent).getLayoutMode();
            else
                i = LAYOUT_MODE_DEFAULT;
            setLayoutMode(i, false);
        }
        return mLayoutMode;
    }

    public LayoutTransition getLayoutTransition()
    {
        return mTransition;
    }

    public int getNestedScrollAxes()
    {
        return mNestedScrollAxes;
    }

    int getNumChildrenForAccessibility()
    {
        int i = 0;
        int j = 0;
        while(j < getChildCount()) 
        {
            View view = getChildAt(j);
            int k;
            if(view.includeForAccessibility())
            {
                k = i + 1;
            } else
            {
                k = i;
                if(view instanceof ViewGroup)
                    k = i + ((ViewGroup)view).getNumChildrenForAccessibility();
            }
            j++;
            i = k;
        }
        return i;
    }

    public ViewGroupOverlay getOverlay()
    {
        if(mOverlay == null)
            mOverlay = new ViewGroupOverlay(mContext, this);
        return (ViewGroupOverlay)mOverlay;
    }

    public volatile ViewOverlay getOverlay()
    {
        return getOverlay();
    }

    public int getPersistentDrawingCache()
    {
        return mPersistentDrawingCache;
    }

    void getScrollIndicatorBounds(Rect rect)
    {
        super.getScrollIndicatorBounds(rect);
        boolean flag;
        if((mGroupFlags & 0x22) == 34)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            rect.left = rect.left + mPaddingLeft;
            rect.right = rect.right - mPaddingRight;
            rect.top = rect.top + mPaddingTop;
            rect.bottom = rect.bottom - mPaddingBottom;
        }
    }

    public boolean getTouchscreenBlocksFocus()
    {
        boolean flag = false;
        if((mGroupFlags & 0x4000000) != 0)
            flag = true;
        return flag;
    }

    public View getTransientView(int i)
    {
        if(mTransientViews == null || i >= mTransientViews.size())
            return null;
        else
            return (View)mTransientViews.get(i);
    }

    public int getTransientViewCount()
    {
        int i;
        if(mTransientIndices == null)
            i = 0;
        else
            i = mTransientIndices.size();
        return i;
    }

    public int getTransientViewIndex(int i)
    {
        while(i < 0 || mTransientIndices == null || i >= mTransientIndices.size()) 
            return -1;
        return ((Integer)mTransientIndices.get(i)).intValue();
    }

    void handleFocusGainInternal(int i, Rect rect)
    {
        if(mFocused != null)
        {
            mFocused.unFocus(this);
            mFocused = null;
            mFocusedInCluster = null;
        }
        super.handleFocusGainInternal(i, rect);
    }

    boolean hasDefaultFocus()
    {
        boolean flag;
        if(mDefaultFocus == null)
            flag = super.hasDefaultFocus();
        else
            flag = true;
        return flag;
    }

    public boolean hasFocus()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if((mPrivateFlags & 2) == 0)
            if(mFocused != null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    boolean hasFocusable(boolean flag, boolean flag1)
    {
        if((mViewFlags & 0xc) != 0)
            return false;
        if((flag || getFocusable() != 16) && isFocusable())
            return true;
        if(getDescendantFocusability() != 0x60000)
            return hasFocusableChild(flag1);
        else
            return false;
    }

    boolean hasFocusableChild(boolean flag)
    {
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
        {
            View view = aview[j];
            if(flag && view.hasExplicitFocusable() || !flag && view.hasFocusable())
                return true;
        }

        return false;
    }

    protected boolean hasHoveredChild()
    {
        boolean flag;
        if(mFirstHoverTarget != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasTransientState()
    {
        boolean flag;
        if(mChildCountWithTransientState <= 0)
            flag = super.hasTransientState();
        else
            flag = true;
        return flag;
    }

    public int indexOfChild(View view)
    {
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            if(aview[j] == view)
                return j;

        return -1;
    }

    protected void internalSetPadding(int i, int j, int k, int l)
    {
        super.internalSetPadding(i, j, k, l);
        if((mPaddingLeft | mPaddingTop | mPaddingRight | mPaddingBottom) != 0)
            mGroupFlags = mGroupFlags | 0x20;
        else
            mGroupFlags = mGroupFlags & 0xffffffdf;
    }

    public final void invalidateChild(View view, Rect rect)
    {
label0:
        {
            Object obj1;
label1:
            {
                View.AttachInfo attachinfo = mAttachInfo;
                if(attachinfo != null && attachinfo.mHardwareAccelerated)
                {
                    onDescendantInvalidated(view, view);
                    return;
                }
                Object obj = this;
                if(attachinfo == null)
                    break label0;
                boolean flag;
                Matrix matrix;
                boolean flag1;
                int i;
                int ai[];
                int j;
                RectF rectf;
                if((view.mPrivateFlags & 0x40) != 0)
                    flag = true;
                else
                    flag = false;
                matrix = view.getMatrix();
                if(view.isOpaque() && flag ^ true && view.getAnimation() == null)
                    flag1 = matrix.isIdentity();
                else
                    flag1 = false;
                if(flag1)
                    i = 0x400000;
                else
                    i = 0x200000;
                if(view.mLayerType != 0)
                {
                    mPrivateFlags = mPrivateFlags | 0x80000000;
                    mPrivateFlags = mPrivateFlags & 0xffff7fff;
                }
                ai = attachinfo.mInvalidateChildLocation;
                ai[0] = view.mLeft;
                ai[1] = view.mTop;
                if(matrix.isIdentity())
                {
                    j = i;
                    obj1 = obj;
                    if((mGroupFlags & 0x800) == 0)
                        break label1;
                }
                rectf = attachinfo.mTmpTransformRect;
                rectf.set(rect);
                if((mGroupFlags & 0x800) != 0)
                {
                    Transformation transformation = attachinfo.mTmpTransformation;
                    int k;
                    if(getChildStaticTransformation(view, transformation))
                    {
                        obj1 = attachinfo.mTmpMatrix;
                        ((Matrix) (obj1)).set(transformation.getMatrix());
                        view = ((View) (obj1));
                        if(!matrix.isIdentity())
                        {
                            ((Matrix) (obj1)).preConcat(matrix);
                            view = ((View) (obj1));
                        }
                    } else
                    {
                        view = matrix;
                    }
                } else
                {
                    view = matrix;
                }
                view.mapRect(rectf);
                rect.set((int)Math.floor(rectf.left), (int)Math.floor(rectf.top), (int)Math.ceil(rectf.right), (int)Math.ceil(rectf.bottom));
                obj1 = obj;
                j = i;
            }
            do
            {
                view = null;
                if(obj1 instanceof View)
                    view = (View)obj1;
                if(flag)
                    if(view != null)
                        view.mPrivateFlags = view.mPrivateFlags | 0x40;
                    else
                    if(obj1 instanceof ViewRootImpl)
                        ((ViewRootImpl)obj1).mIsAnimating = true;
                k = j;
                if(view != null)
                {
                    i = j;
                    if((view.mViewFlags & 0x3000) != 0)
                    {
                        i = j;
                        if(view.getSolidColor() == 0)
                            i = 0x200000;
                    }
                    k = i;
                    if((view.mPrivateFlags & 0x600000) != 0x200000)
                    {
                        view.mPrivateFlags = view.mPrivateFlags & 0xff9fffff | i;
                        k = i;
                    }
                }
                obj = ((ViewParent) (obj1)).invalidateChildInParent(ai, rect);
                if(view != null)
                {
                    view = view.getMatrix();
                    if(!view.isIdentity())
                    {
                        obj1 = attachinfo.mTmpTransformRect;
                        ((RectF) (obj1)).set(rect);
                        view.mapRect(((RectF) (obj1)));
                        rect.set((int)Math.floor(((RectF) (obj1)).left), (int)Math.floor(((RectF) (obj1)).top), (int)Math.ceil(((RectF) (obj1)).right), (int)Math.ceil(((RectF) (obj1)).bottom));
                    }
                }
                j = k;
                obj1 = obj;
            } while(obj != null);
        }
    }

    public ViewParent invalidateChildInParent(int ai[], Rect rect)
    {
        if((mPrivateFlags & 0x8020) != 0)
        {
            if((mGroupFlags & 0x90) != 128)
            {
                rect.offset(ai[0] - mScrollX, ai[1] - mScrollY);
                if((mGroupFlags & 1) == 0)
                    rect.union(0, 0, mRight - mLeft, mBottom - mTop);
                int i = mLeft;
                int j = mTop;
                if((mGroupFlags & 1) == 1 && !rect.intersect(0, 0, mRight - i, mBottom - j))
                    rect.setEmpty();
                ai[0] = i;
                ai[1] = j;
            } else
            {
                if((mGroupFlags & 1) == 1)
                    rect.set(0, 0, mRight - mLeft, mBottom - mTop);
                else
                    rect.union(0, 0, mRight - mLeft, mBottom - mTop);
                ai[0] = mLeft;
                ai[1] = mTop;
                mPrivateFlags = mPrivateFlags & 0xffffffdf;
            }
            mPrivateFlags = mPrivateFlags & 0xffff7fff;
            if(mLayerType != 0)
                mPrivateFlags = mPrivateFlags | 0x80000000;
            return mParent;
        } else
        {
            return null;
        }
    }

    void invalidateInheritedLayoutMode(int i)
    {
        while(mLayoutMode == -1 || mLayoutMode == i || hasBooleanFlag(0x800000)) 
            return;
        setLayoutMode(-1, false);
        int j = 0;
        for(int k = getChildCount(); j < k; j++)
            getChildAt(j).invalidateInheritedLayoutMode(i);

    }

    public boolean isAlwaysDrawnWithCacheEnabled()
    {
        boolean flag;
        if((mGroupFlags & 0x4000) == 16384)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isAnimationCacheEnabled()
    {
        boolean flag;
        if((mGroupFlags & 0x40) == 64)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean isChildrenDrawingOrderEnabled()
    {
        boolean flag;
        if((mGroupFlags & 0x400) == 1024)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean isChildrenDrawnWithCacheEnabled()
    {
        boolean flag;
        if((mGroupFlags & 0x8000) == 32768)
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean isLayoutModeOptical()
    {
        boolean flag = true;
        if(mLayoutMode != 1)
            flag = false;
        return flag;
    }

    public boolean isLayoutSuppressed()
    {
        return mSuppressLayout;
    }

    public boolean isMotionEventSplittingEnabled()
    {
        boolean flag;
        if((mGroupFlags & 0x200000) == 0x200000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final boolean isShowingContextMenuWithCoords()
    {
        boolean flag = false;
        if((mGroupFlags & 0x20000000) != 0)
            flag = true;
        return flag;
    }

    protected boolean isTransformedTouchPointInView(float f, float f1, View view, PointF pointf)
    {
        float af[] = getTempPoint();
        af[0] = f;
        af[1] = f1;
        transformPointToViewLocal(af, view);
        boolean flag = view.pointInView(af[0], af[1]);
        if(flag && pointf != null)
            pointf.set(af[0], af[1]);
        return flag;
    }

    public boolean isTransitionGroup()
    {
        boolean flag;
        boolean flag1;
        ViewOutlineProvider viewoutlineprovider;
        flag = true;
        flag1 = true;
        if((mGroupFlags & 0x2000000) != 0)
        {
            if((mGroupFlags & 0x1000000) == 0)
                flag1 = false;
            return flag1;
        }
        viewoutlineprovider = getOutlineProvider();
        flag1 = flag;
        if(getBackground() != null) goto _L2; else goto _L1
_L1:
        if(getTransitionName() == null) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        if(viewoutlineprovider != null)
        {
            flag1 = flag;
            if(viewoutlineprovider != ViewOutlineProvider.BACKGROUND)
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    boolean isViewTransitioning(View view)
    {
        boolean flag;
        if(mTransitioningViews != null)
            flag = mTransitioningViews.contains(view);
        else
            flag = false;
        return flag;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        View aview[] = mChildren;
        int i = mChildrenCount;
        for(int j = 0; j < i; j++)
            aview[j].jumpDrawablesToCurrentState();

    }

    public final void layout(int i, int j, int k, int l)
    {
        if(!mSuppressLayout && (mTransition == null || mTransition.isChangingLayout() ^ true))
        {
            if(mTransition != null)
                mTransition.layoutChange(this);
            super.layout(i, j, k, l);
        } else
        {
            mLayoutCalledWhileSuppressed = true;
        }
    }

    public void makeOptionalFitsSystemWindows()
    {
        super.makeOptionalFitsSystemWindows();
        int i = mChildrenCount;
        View aview[] = mChildren;
        for(int j = 0; j < i; j++)
            aview[j].makeOptionalFitsSystemWindows();

    }

    protected void measureChild(View view, int i, int j)
    {
        LayoutParams layoutparams = view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, mPaddingLeft + mPaddingRight, layoutparams.width), getChildMeasureSpec(j, mPaddingTop + mPaddingBottom, layoutparams.height));
    }

    protected void measureChildWithMargins(View view, int i, int j, int k, int l)
    {
        MarginLayoutParams marginlayoutparams = (MarginLayoutParams)view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, mPaddingLeft + mPaddingRight + marginlayoutparams.leftMargin + marginlayoutparams.rightMargin + j, marginlayoutparams.width), getChildMeasureSpec(k, mPaddingTop + mPaddingBottom + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin + l, marginlayoutparams.height));
    }

    protected void measureChildren(int i, int j)
    {
        int k = mChildrenCount;
        View aview[] = mChildren;
        for(int l = 0; l < k; l++)
        {
            View view = aview[l];
            if((view.mViewFlags & 0xc) != 8)
                measureChild(view, i, j);
        }

    }

    boolean notifyChildOfDragStart(View view)
    {
        float f = mCurrentDragStartEvent.mX;
        float f1 = mCurrentDragStartEvent.mY;
        float af[] = getTempPoint();
        af[0] = f;
        af[1] = f1;
        transformPointToViewLocal(af, view);
        mCurrentDragStartEvent.mX = af[0];
        mCurrentDragStartEvent.mY = af[1];
        boolean flag = view.dispatchDragEvent(mCurrentDragStartEvent);
        mCurrentDragStartEvent.mX = f;
        mCurrentDragStartEvent.mY = f1;
        mCurrentDragStartEvent.mEventHandlerWasCalled = false;
        if(flag)
        {
            mChildrenInterestedInDrag.add(view);
            if(!view.canAcceptDrag())
            {
                view.mPrivateFlags2 = view.mPrivateFlags2 | 1;
                view.refreshDrawableState();
            }
        }
        return flag;
    }

    public void notifySubtreeAccessibilityStateChanged(View view, View view1, int i)
    {
        if(getAccessibilityLiveRegion() == 0) goto _L2; else goto _L1
_L1:
        notifyViewAccessibilityStateChangedIfNeeded(1);
_L4:
        return;
_L2:
        if(mParent != null)
            try
            {
                mParent.notifySubtreeAccessibilityStateChanged(this, view1, i);
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), view);
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void notifySubtreeAccessibilityStateChangedIfNeeded()
    {
        if(!AccessibilityManager.getInstance(mContext).isEnabled() || mAttachInfo == null)
            return;
        if(getImportantForAccessibility() != 4 && isImportantForAccessibility() ^ true && getChildCount() > 0)
        {
            ViewParent viewparent = getParentForAccessibility();
            if(viewparent instanceof View)
            {
                ((View)viewparent).notifySubtreeAccessibilityStateChangedIfNeeded();
                return;
            }
        }
        super.notifySubtreeAccessibilityStateChangedIfNeeded();
    }

    public void offsetChildrenTopAndBottom(int i)
    {
        int j = mChildrenCount;
        View aview[] = mChildren;
        boolean flag = false;
        for(int k = 0; k < j; k++)
        {
            View view = aview[k];
            view.mTop = view.mTop + i;
            view.mBottom = view.mBottom + i;
            if(view.mRenderNode != null)
            {
                flag = true;
                view.mRenderNode.offsetTopAndBottom(i);
            }
        }

        if(flag)
            invalidateViewProperty(false, false);
        notifySubtreeAccessibilityStateChangedIfNeeded();
    }

    public final void offsetDescendantRectToMyCoords(View view, Rect rect)
    {
        offsetRectBetweenParentAndChild(view, rect, true, false);
    }

    void offsetRectBetweenParentAndChild(View view, Rect rect, boolean flag, boolean flag1)
    {
        if(view == this)
            return;
        ViewParent viewparent = view.mParent;
        View view2 = view;
        view = viewparent;
        while(view != null && (view instanceof View) && view != this) 
        {
            if(flag)
            {
                rect.offset(view2.mLeft - view2.mScrollX, view2.mTop - view2.mScrollY);
                if(flag1)
                {
                    view2 = (View)view;
                    if(!rect.intersect(0, 0, view2.mRight - view2.mLeft, view2.mBottom - view2.mTop))
                        rect.setEmpty();
                }
            } else
            {
                if(flag1)
                {
                    View view1 = (View)view;
                    if(!rect.intersect(0, 0, view1.mRight - view1.mLeft, view1.mBottom - view1.mTop))
                        rect.setEmpty();
                }
                rect.offset(view2.mScrollX - view2.mLeft, view2.mScrollY - view2.mTop);
            }
            view2 = (View)view;
            view = view2.mParent;
        }
        if(view == this)
        {
            if(flag)
                rect.offset(view2.mLeft - view2.mScrollX, view2.mTop - view2.mScrollY);
            else
                rect.offset(view2.mScrollX - view2.mLeft, view2.mScrollY - view2.mTop);
            return;
        } else
        {
            throw new IllegalArgumentException("parameter must be a descendant of this view");
        }
    }

    public final void offsetRectIntoDescendantCoords(View view, Rect rect)
    {
        offsetRectBetweenParentAndChild(view, rect, false, false);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        clearCachedLayoutMode();
    }

    protected void onChildVisibilityChanged(View view, int i, int j)
    {
        if(mTransition == null) goto _L2; else goto _L1
_L1:
        if(j != 0) goto _L4; else goto _L3
_L3:
        mTransition.showChild(this, view, i);
_L2:
        if(j == 0 && mCurrentDragStartEvent != null && !mChildrenInterestedInDrag.contains(view))
            notifyChildOfDragStart(view);
        return;
_L4:
        mTransition.hideChild(this, view, j);
        if(mTransitioningViews != null && mTransitioningViews.contains(view))
        {
            if(mVisibilityChangingChildren == null)
                mVisibilityChangingChildren = new ArrayList();
            mVisibilityChangingChildren.add(view);
            addDisappearingView(view);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected int[] onCreateDrawableState(int i)
    {
        if((mGroupFlags & 0x2000) == 0)
            return super.onCreateDrawableState(i);
        int j = 0;
        int k = getChildCount();
        for(int l = 0; l < k;)
        {
            int ai[] = getChildAt(l).getDrawableState();
            int i1 = j;
            if(ai != null)
                i1 = j + ai.length;
            l++;
            j = i1;
        }

        int ai1[] = super.onCreateDrawableState(i + j);
        for(i = 0; i < k;)
        {
            int ai2[] = getChildAt(i).getDrawableState();
            int ai3[] = ai1;
            if(ai2 != null)
                ai3 = mergeDrawableStates(ai1, ai2);
            i++;
            ai1 = ai3;
        }

        return ai1;
    }

    protected void onDebugDraw(Canvas canvas)
    {
        Paint paint = getDebugPaint();
        paint.setColor(0xffff0000);
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 8)
            {
                Insets insets = view.getOpticalInsets();
                int k = view.getLeft();
                int i1 = insets.left;
                int j1 = view.getTop();
                drawRect(canvas, paint, i1 + k, insets.top + j1, view.getRight() - insets.right - 1, view.getBottom() - insets.bottom - 1);
            }
        }

        paint.setColor(Color.argb(63, 255, 0, 255));
        paint.setStyle(android.graphics.Paint.Style.FILL);
        onDebugDrawMargins(canvas, paint);
        paint.setColor(DEBUG_CORNERS_COLOR);
        paint.setStyle(android.graphics.Paint.Style.FILL);
        int l = dipsToPixels(8);
        int k1 = dipsToPixels(1);
        for(int j = 0; j < getChildCount(); j++)
        {
            View view1 = getChildAt(j);
            if(view1.getVisibility() != 8)
                drawRectCorners(canvas, view1.getLeft(), view1.getTop(), view1.getRight(), view1.getBottom(), paint, l, k1);
        }

    }

    protected void onDebugDrawMargins(Canvas canvas, Paint paint)
    {
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            view.getLayoutParams().onDebugDraw(view, canvas, paint);
        }

    }

    public void onDescendantInvalidated(View view, View view1)
    {
        mPrivateFlags = mPrivateFlags | view1.mPrivateFlags & 0x40;
        if((view1.mPrivateFlags & 0xff9fffff) != 0)
        {
            mPrivateFlags = mPrivateFlags & 0xff9fffff | 0x200000;
            mPrivateFlags = mPrivateFlags & 0xffff7fff;
        }
        view = view1;
        if(mLayerType == 1)
        {
            mPrivateFlags = mPrivateFlags | 0x80200000;
            view = this;
        }
        if(mParent != null)
            mParent.onDescendantInvalidated(this, view);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        clearCachedLayoutMode();
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(getAccessibilityNodeProvider() != null)
            return;
        if(mAttachInfo != null)
        {
            ArrayList arraylist = mAttachInfo.mTempArrayList;
            arraylist.clear();
            addChildrenForAccessibility(arraylist);
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                accessibilitynodeinfo.addChildUnchecked((View)arraylist.get(j));

            arraylist.clear();
        }
    }

    public boolean onInterceptHoverEvent(MotionEvent motionevent)
    {
        if(motionevent.isFromSource(8194))
        {
            int i = motionevent.getAction();
            float f = motionevent.getX();
            float f1 = motionevent.getY();
            if((i == 7 || i == 9) && isOnScrollbar(f, f1))
                return true;
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        return motionevent.isFromSource(8194) && motionevent.getAction() == 0 && motionevent.isButtonPressed(1) && isOnScrollbarThumb(motionevent.getX(), motionevent.getY());
    }

    protected abstract void onLayout(boolean flag, int i, int j, int k, int l);

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        return dispatchNestedFling(f, f1, flag);
    }

    public boolean onNestedPreFling(View view, float f, float f1)
    {
        return dispatchNestedPreFling(f, f1);
    }

    public boolean onNestedPrePerformAccessibilityAction(View view, int i, Bundle bundle)
    {
        return false;
    }

    public void onNestedPreScroll(View view, int i, int j, int ai[])
    {
        dispatchNestedPreScroll(i, j, ai, null);
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        dispatchNestedScroll(i, j, k, l, null);
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        mNestedScrollAxes = i;
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        int j = mChildrenCount;
        int k;
        byte byte0;
        View aview[];
        if((i & 2) != 0)
        {
            k = 0;
            byte0 = 1;
        } else
        {
            k = j - 1;
            byte0 = -1;
            j = -1;
        }
        aview = mChildren;
        for(; k != j; k += byte0)
        {
            View view = aview[k];
            if((view.mViewFlags & 0xc) == 0 && view.requestFocus(i, rect))
                return true;
        }

        return false;
    }

    public boolean onRequestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        if(mAccessibilityDelegate != null)
            return mAccessibilityDelegate.onRequestSendAccessibilityEvent(this, view, accessibilityevent);
        else
            return onRequestSendAccessibilityEventInternal(view, accessibilityevent);
    }

    public boolean onRequestSendAccessibilityEventInternal(View view, AccessibilityEvent accessibilityevent)
    {
        return true;
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        ArrayList arraylist;
        Object obj;
        float f = motionevent.getX(i);
        float f1 = motionevent.getY(i);
        if(isOnScrollbarThumb(f, f1) || isDraggingScrollBar())
            return PointerIcon.getSystemIcon(mContext, 1000);
        int j = mChildrenCount;
        if(j == 0)
            break MISSING_BLOCK_LABEL_178;
        arraylist = buildOrderedChildList();
        boolean flag;
        View aview[];
        int k;
        if(arraylist == null)
            flag = isChildrenDrawingOrderEnabled();
        else
            flag = false;
        aview = mChildren;
        k = j - 1;
        if(k < 0)
            break MISSING_BLOCK_LABEL_168;
        obj = getAndVerifyPreorderedView(arraylist, aview, getAndVerifyPreorderedIndex(j, k, flag));
        if(canViewReceivePointerEvents(((View) (obj))) && !(isTransformedTouchPointInView(f, f1, ((View) (obj)), null) ^ true))
            break; /* Loop/switch isn't completed */
_L4:
        k--;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_81;
_L1:
        if((obj = dispatchResolvePointerIcon(motionevent, i, ((View) (obj)))) == null) goto _L4; else goto _L3
_L3:
        if(arraylist != null)
            arraylist.clear();
        return ((PointerIcon) (obj));
        if(arraylist != null)
            arraylist.clear();
        return super.onResolvePointerIcon(motionevent, i);
    }

    protected void onSetLayoutParams(View view, LayoutParams layoutparams)
    {
        requestLayout();
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        return false;
    }

    public void onStopNestedScroll(View view)
    {
        stopNestedScroll();
        mNestedScrollAxes = 0;
    }

    public void onViewAdded(View view)
    {
    }

    public void onViewRemoved(View view)
    {
    }

    public void recomputeViewAttributes(View view)
    {
        if(mAttachInfo != null && mAttachInfo.mRecomputeGlobalAttributes ^ true)
        {
            view = mParent;
            if(view != null)
                view.recomputeViewAttributes(this);
        }
    }

    public void removeAllViews()
    {
        removeAllViewsInLayout();
        requestLayout();
        invalidate(true);
    }

    public void removeAllViewsInLayout()
    {
        int i = mChildrenCount;
        if(i <= 0)
            return;
        View aview[] = mChildren;
        mChildrenCount = 0;
        View view = mFocused;
        boolean flag;
        boolean flag1;
        if(mAttachInfo != null)
            flag = true;
        else
            flag = false;
        flag1 = false;
        needGlobalAttributesUpdate(false);
        i--;
        while(i >= 0) 
        {
            View view1 = aview[i];
            if(mTransition != null)
                mTransition.removeChild(this, view1);
            if(view1 == view)
            {
                view1.unFocus(null);
                flag1 = true;
            }
            view1.clearAccessibilityFocus();
            cancelTouchTarget(view1);
            cancelHoverTarget(view1);
            if(view1.getAnimation() != null || mTransitioningViews != null && mTransitioningViews.contains(view1))
                addDisappearingView(view1);
            else
            if(flag)
                view1.dispatchDetachedFromWindow();
            if(view1.hasTransientState())
                childHasTransientStateChanged(view1, false);
            dispatchViewRemoved(view1);
            view1.mParent = null;
            aview[i] = null;
            i--;
        }
        if(mDefaultFocus != null)
            clearDefaultFocus(mDefaultFocus);
        if(mFocusedInCluster != null)
            clearFocusedInCluster(mFocusedInCluster);
        if(flag1)
        {
            clearChildFocus(view);
            if(!rootViewRequestFocus())
                notifyGlobalFocusCleared(view);
        }
    }

    protected void removeDetachedView(View view, boolean flag)
    {
        if(mTransition != null)
            mTransition.removeChild(this, view);
        if(view == mFocused)
            view.clearFocus();
        if(view == mDefaultFocus)
            clearDefaultFocus(view);
        if(view == mFocusedInCluster)
            clearFocusedInCluster(view);
        view.clearAccessibilityFocus();
        cancelTouchTarget(view);
        cancelHoverTarget(view);
        break MISSING_BLOCK_LABEL_68;
        if(flag && view.getAnimation() != null || mTransitioningViews != null && mTransitioningViews.contains(view))
            addDisappearingView(view);
        else
        if(view.mAttachInfo != null)
            view.dispatchDetachedFromWindow();
        if(view.hasTransientState())
            childHasTransientStateChanged(view, false);
        dispatchViewRemoved(view);
        return;
    }

    public void removeTransientView(View view)
    {
        if(mTransientViews == null)
            return;
        int i = mTransientViews.size();
        for(int j = 0; j < i; j++)
            if(view == mTransientViews.get(j))
            {
                mTransientViews.remove(j);
                mTransientIndices.remove(j);
                view.mParent = null;
                view.dispatchDetachedFromWindow();
                invalidate(true);
                return;
            }

    }

    public void removeView(View view)
    {
        if(removeViewInternal(view))
        {
            requestLayout();
            invalidate(true);
        }
    }

    public void removeViewAt(int i)
    {
        removeViewInternal(i, getChildAt(i));
        requestLayout();
        invalidate(true);
    }

    public void removeViewInLayout(View view)
    {
        removeViewInternal(view);
    }

    public void removeViews(int i, int j)
    {
        removeViewsInternal(i, j);
        requestLayout();
        invalidate(true);
    }

    public void removeViewsInLayout(int i, int j)
    {
        removeViewsInternal(i, j);
    }

    public void requestChildFocus(View view, View view1)
    {
        if(getDescendantFocusability() == 0x60000)
            return;
        super.unFocus(view1);
        if(mFocused != view)
        {
            if(mFocused != null)
                mFocused.unFocus(view1);
            mFocused = view;
        }
        if(mParent != null)
            mParent.requestChildFocus(this, view1);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        return false;
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        boolean flag1 = false;
        if((mGroupFlags & 0x80000) != 0)
            flag1 = true;
        if(flag == flag1)
            return;
        if(flag)
            mGroupFlags = mGroupFlags | 0x80000;
        else
            mGroupFlags = mGroupFlags & 0xfff7ffff;
        if(mParent != null)
            mParent.requestDisallowInterceptTouchEvent(flag);
    }

    public boolean requestFocus(int i, Rect rect)
    {
        int j = getDescendantFocusability();
        boolean flag1;
        switch(j)
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("descendant focusability must be one of FOCUS_BEFORE_DESCENDANTS, FOCUS_AFTER_DESCENDANTS, FOCUS_BLOCK_DESCENDANTS but is ").append(j).toString());

        case 393216: 
            return super.requestFocus(i, rect);

        case 131072: 
            boolean flag = super.requestFocus(i, rect);
            if(!flag)
                flag = onRequestFocusInDescendants(i, rect);
            return flag;

        case 262144: 
            flag1 = onRequestFocusInDescendants(i, rect);
            break;
        }
        if(!flag1)
            flag1 = super.requestFocus(i, rect);
        return flag1;
    }

    public boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        ViewParent viewparent = mParent;
        if(viewparent == null)
            return false;
        if(!onRequestSendAccessibilityEvent(view, accessibilityevent))
            return false;
        else
            return viewparent.requestSendAccessibilityEvent(this, accessibilityevent);
    }

    public void requestTransitionStart(LayoutTransition layouttransition)
    {
        ViewRootImpl viewrootimpl = getViewRootImpl();
        if(viewrootimpl != null)
            viewrootimpl.requestTransitionStart(layouttransition);
    }

    public void requestTransparentRegion(View view)
    {
        if(view != null)
        {
            view.mPrivateFlags = view.mPrivateFlags | 0x200;
            if(mParent != null)
                mParent.requestTransparentRegion(this);
        }
    }

    protected void resetResolvedDrawables()
    {
        super.resetResolvedDrawables();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.isLayoutDirectionInherited())
                view.resetResolvedDrawables();
        }

    }

    public void resetResolvedLayoutDirection()
    {
        super.resetResolvedLayoutDirection();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.isLayoutDirectionInherited())
                view.resetResolvedLayoutDirection();
        }

    }

    public void resetResolvedPadding()
    {
        super.resetResolvedPadding();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.isLayoutDirectionInherited())
                view.resetResolvedPadding();
        }

    }

    public void resetResolvedTextAlignment()
    {
        super.resetResolvedTextAlignment();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.isTextAlignmentInherited())
                view.resetResolvedTextAlignment();
        }

    }

    public void resetResolvedTextDirection()
    {
        super.resetResolvedTextDirection();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.isTextDirectionInherited())
                view.resetResolvedTextDirection();
        }

    }

    void resetSubtreeAccessibilityStateChanged()
    {
        super.resetSubtreeAccessibilityStateChanged();
        View aview[] = mChildren;
        int i = mChildrenCount;
        for(int j = 0; j < i; j++)
            aview[j].resetSubtreeAccessibilityStateChanged();

    }

    protected void resolveDrawables()
    {
        super.resolveDrawables();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.isLayoutDirectionInherited() && view.areDrawablesResolved() ^ true)
                view.resolveDrawables();
        }

    }

    public boolean resolveLayoutDirection()
    {
        boolean flag = super.resolveLayoutDirection();
        if(flag)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                if(view.isLayoutDirectionInherited())
                    view.resolveLayoutDirection();
            }

        }
        return flag;
    }

    public void resolveLayoutParams()
    {
        super.resolveLayoutParams();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
            getChildAt(j).resolveLayoutParams();

    }

    public void resolvePadding()
    {
        super.resolvePadding();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.isLayoutDirectionInherited() && view.isPaddingResolved() ^ true)
                view.resolvePadding();
        }

    }

    public boolean resolveRtlPropertiesIfNeeded()
    {
        boolean flag = super.resolveRtlPropertiesIfNeeded();
        if(flag)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                if(view.isLayoutDirectionInherited())
                    view.resolveRtlPropertiesIfNeeded();
            }

        }
        return flag;
    }

    public boolean resolveTextAlignment()
    {
        boolean flag = super.resolveTextAlignment();
        if(flag)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                if(view.isTextAlignmentInherited())
                    view.resolveTextAlignment();
            }

        }
        return flag;
    }

    public boolean resolveTextDirection()
    {
        boolean flag = super.resolveTextDirection();
        if(flag)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                if(view.isTextDirectionInherited())
                    view.resolveTextDirection();
            }

        }
        return flag;
    }

    public boolean restoreDefaultFocus()
    {
        if(mDefaultFocus != null && getDescendantFocusability() != 0x60000 && (mDefaultFocus.mViewFlags & 0xc) == 0 && mDefaultFocus.restoreDefaultFocus())
            return true;
        else
            return super.restoreDefaultFocus();
    }

    public boolean restoreFocusInCluster(int i)
    {
        boolean flag;
        if(!isKeyboardNavigationCluster())
            break MISSING_BLOCK_LABEL_40;
        flag = getTouchscreenBlocksFocus();
        boolean flag1;
        setTouchscreenBlocksFocusNoRefocus(false);
        flag1 = restoreFocusInClusterInternal(i);
        setTouchscreenBlocksFocusNoRefocus(flag);
        return flag1;
        Exception exception;
        exception;
        setTouchscreenBlocksFocusNoRefocus(flag);
        throw exception;
        return restoreFocusInClusterInternal(i);
    }

    public boolean restoreFocusNotInCluster()
    {
        if(mFocusedInCluster != null)
            return restoreFocusInCluster(130);
        if(isKeyboardNavigationCluster() || (mViewFlags & 0xc) != 0)
            return false;
        int i = getDescendantFocusability();
        if(i == 0x60000)
            return super.requestFocus(130, null);
        if(i == 0x20000 && super.requestFocus(130, null))
            return true;
        for(int j = 0; j < mChildrenCount; j++)
        {
            View view = mChildren[j];
            if(!view.isKeyboardNavigationCluster() && view.restoreFocusNotInCluster())
                return true;
        }

        if(i == 0x40000 && hasFocusableChild(false) ^ true)
            return super.requestFocus(130, null);
        else
            return false;
    }

    public void scheduleLayoutAnimation()
    {
        mGroupFlags = mGroupFlags | 8;
    }

    public void setAddStatesFromChildren(boolean flag)
    {
        if(flag)
            mGroupFlags = mGroupFlags | 0x2000;
        else
            mGroupFlags = mGroupFlags & 0xffffdfff;
        refreshDrawableState();
    }

    public void setAlwaysDrawnWithCacheEnabled(boolean flag)
    {
        setBooleanFlag(16384, flag);
    }

    public void setAnimationCacheEnabled(boolean flag)
    {
        setBooleanFlag(64, flag);
    }

    protected void setChildrenDrawingCacheEnabled(boolean flag)
    {
        if(flag || (mPersistentDrawingCache & 3) != 3)
        {
            View aview[] = mChildren;
            int i = mChildrenCount;
            for(int j = 0; j < i; j++)
                aview[j].setDrawingCacheEnabled(flag);

        }
    }

    protected void setChildrenDrawingOrderEnabled(boolean flag)
    {
        setBooleanFlag(1024, flag);
    }

    protected void setChildrenDrawnWithCacheEnabled(boolean flag)
    {
        setBooleanFlag(32768, flag);
    }

    public void setClipChildren(boolean flag)
    {
        boolean flag1;
        if((mGroupFlags & 1) == 1)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
        {
            setBooleanFlag(1, flag);
            for(int i = 0; i < mChildrenCount; i++)
            {
                View view = getChildAt(i);
                if(view.mRenderNode != null)
                    view.mRenderNode.setClipToBounds(flag);
            }

            invalidate(true);
        }
    }

    public void setClipToPadding(boolean flag)
    {
        if(hasBooleanFlag(2) != flag)
        {
            setBooleanFlag(2, flag);
            invalidate(true);
        }
    }

    void setDefaultFocus(View view)
    {
        if(mDefaultFocus != null && mDefaultFocus.isFocusedByDefault())
            return;
        mDefaultFocus = view;
        if(mParent instanceof ViewGroup)
            ((ViewGroup)mParent).setDefaultFocus(((View) (this)));
    }

    public void setDescendantFocusability(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("must be one of FOCUS_BEFORE_DESCENDANTS, FOCUS_AFTER_DESCENDANTS, FOCUS_BLOCK_DESCENDANTS");

        case 131072: 
        case 262144: 
        case 393216: 
            mGroupFlags = mGroupFlags & 0xfff9ffff;
            break;
        }
        mGroupFlags = mGroupFlags | 0x60000 & i;
    }

    public void setLayoutAnimation(LayoutAnimationController layoutanimationcontroller)
    {
        mLayoutAnimationController = layoutanimationcontroller;
        if(mLayoutAnimationController != null)
            mGroupFlags = mGroupFlags | 8;
    }

    public void setLayoutAnimationListener(android.view.animation.Animation.AnimationListener animationlistener)
    {
        mAnimationListener = animationlistener;
    }

    public void setLayoutMode(int i)
    {
        if(mLayoutMode != i)
        {
            invalidateInheritedLayoutMode(i);
            boolean flag;
            if(i != -1)
                flag = true;
            else
                flag = false;
            setLayoutMode(i, flag);
            requestLayout();
        }
    }

    public void setLayoutTransition(LayoutTransition layouttransition)
    {
        if(mTransition != null)
        {
            LayoutTransition layouttransition1 = mTransition;
            layouttransition1.cancel();
            layouttransition1.removeTransitionListener(mLayoutTransitionListener);
        }
        mTransition = layouttransition;
        if(mTransition != null)
            mTransition.addTransitionListener(mLayoutTransitionListener);
    }

    public void setMotionEventSplittingEnabled(boolean flag)
    {
        if(flag)
            mGroupFlags = mGroupFlags | 0x200000;
        else
            mGroupFlags = mGroupFlags & 0xffdfffff;
    }

    public void setOnHierarchyChangeListener(OnHierarchyChangeListener onhierarchychangelistener)
    {
        mOnHierarchyChangeListener = onhierarchychangelistener;
    }

    public void setPersistentDrawingCache(int i)
    {
        mPersistentDrawingCache = i & 3;
    }

    protected void setStaticTransformationsEnabled(boolean flag)
    {
        setBooleanFlag(2048, flag);
    }

    public void setTouchscreenBlocksFocus(boolean flag)
    {
        if(flag)
        {
            mGroupFlags = mGroupFlags | 0x4000000;
            if(hasFocus() && isKeyboardNavigationCluster() ^ true && !getDeepestFocusedChild().isFocusableInTouchMode())
            {
                View view = focusSearch(2);
                if(view != null)
                    view.requestFocus();
            }
        } else
        {
            mGroupFlags = mGroupFlags & 0xfbffffff;
        }
    }

    public void setTransitionGroup(boolean flag)
    {
        mGroupFlags = mGroupFlags | 0x2000000;
        if(flag)
            mGroupFlags = mGroupFlags | 0x1000000;
        else
            mGroupFlags = mGroupFlags & 0xfeffffff;
    }

    boolean shouldBlockFocusForTouchscreen()
    {
        boolean flag = true;
        boolean flag1 = false;
        boolean flag2 = flag1;
        if(getTouchscreenBlocksFocus())
        {
            flag2 = flag1;
            if(mContext.getPackageManager().hasSystemFeature("android.hardware.touchscreen"))
            {
                boolean flag3;
                if(isKeyboardNavigationCluster())
                {
                    flag3 = flag;
                    if(!hasFocus())
                        if(findKeyboardNavigationCluster() != this)
                            flag3 = flag;
                        else
                            flag3 = false;
                } else
                {
                    flag3 = false;
                }
                flag2 = flag3 ^ true;
            }
        }
        return flag2;
    }

    public boolean shouldDelayChildPressedState()
    {
        return true;
    }

    public boolean showContextMenuForChild(View view)
    {
        boolean flag = false;
        if(isShowingContextMenuWithCoords())
            return false;
        if(mParent != null)
            flag = mParent.showContextMenuForChild(view);
        return flag;
    }

    public boolean showContextMenuForChild(View view, float f, float f1)
    {
        boolean flag;
        mGroupFlags = mGroupFlags | 0x20000000;
        flag = showContextMenuForChild(view);
        if(flag)
        {
            mGroupFlags = mGroupFlags & 0xdfffffff;
            return true;
        }
        mGroupFlags = mGroupFlags & 0xdfffffff;
        if(mParent != null)
            flag = mParent.showContextMenuForChild(view, f, f1);
        else
            flag = false;
        return flag;
        view;
        mGroupFlags = mGroupFlags & 0xdfffffff;
        throw view;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback)
    {
        if((mGroupFlags & 0x8000000) != 0)
            break MISSING_BLOCK_LABEL_58;
        mGroupFlags = mGroupFlags | 0x10000000;
        view = startActionModeForChild(view, callback, 0);
        mGroupFlags = mGroupFlags & 0xefffffff;
        return view;
        view;
        mGroupFlags = mGroupFlags & 0xefffffff;
        throw view;
        return SENTINEL_ACTION_MODE;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i)
    {
        if((mGroupFlags & 0x10000000) != 0 || i != 0)
            break MISSING_BLOCK_LABEL_71;
        ActionMode actionmode;
        mGroupFlags = mGroupFlags | 0x8000000;
        actionmode = startActionModeForChild(view, callback);
        mGroupFlags = mGroupFlags & 0xf7ffffff;
        if(actionmode != SENTINEL_ACTION_MODE)
            return actionmode;
        break MISSING_BLOCK_LABEL_71;
        view;
        mGroupFlags = mGroupFlags & 0xf7ffffff;
        throw view;
        if(mParent != null)
        {
            ActionMode actionmode1;
            try
            {
                actionmode1 = mParent.startActionModeForChild(view, callback, i);
            }
            catch(AbstractMethodError abstractmethoderror)
            {
                return mParent.startActionModeForChild(view, callback);
            }
            return actionmode1;
        } else
        {
            return null;
        }
    }

    public void startLayoutAnimation()
    {
        if(mLayoutAnimationController != null)
        {
            mGroupFlags = mGroupFlags | 8;
            requestLayout();
        }
    }

    public void startViewTransition(View view)
    {
        if(view.mParent == this)
        {
            if(mTransitioningViews == null)
                mTransitioningViews = new ArrayList();
            mTransitioningViews.add(view);
        }
    }

    public void suppressLayout(boolean flag)
    {
        mSuppressLayout = flag;
        if(!flag && mLayoutCalledWhileSuppressed)
        {
            requestLayout();
            mLayoutCalledWhileSuppressed = false;
        }
    }

    public void transformPointToViewLocal(float af[], View view)
    {
        af[0] = af[0] + (float)(mScrollX - view.mLeft);
        af[1] = af[1] + (float)(mScrollY - view.mTop);
        if(!view.hasIdentityMatrix())
            view.getInverseMatrix().mapPoints(af);
    }

    void unFocus(View view)
    {
        if(mFocused == null)
        {
            super.unFocus(view);
        } else
        {
            mFocused.unFocus(view);
            mFocused = null;
        }
    }

    boolean updateLocalSystemUiVisibility(int i, int j)
    {
        boolean flag = super.updateLocalSystemUiVisibility(i, j);
        int k = mChildrenCount;
        View aview[] = mChildren;
        for(int l = 0; l < k; l++)
            flag |= aview[l].updateLocalSystemUiVisibility(i, j);

        return flag;
    }

    public void updateViewLayout(View view, LayoutParams layoutparams)
    {
        if(!checkLayoutParams(layoutparams))
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid LayoutParams supplied to ").append(this).toString());
        if(view.mParent != this)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Given view not a child of ").append(this).toString());
        } else
        {
            view.setLayoutParams(layoutparams);
            return;
        }
    }

    private static final int ARRAY_CAPACITY_INCREMENT = 12;
    private static final int ARRAY_INITIAL_CAPACITY = 12;
    private static final int CHILD_LEFT_INDEX = 0;
    private static final int CHILD_TOP_INDEX = 1;
    protected static final int CLIP_TO_PADDING_MASK = 34;
    private static final boolean DBG = false;
    private static final int DESCENDANT_FOCUSABILITY_FLAGS[] = {
        0x20000, 0x40000, 0x60000
    };
    private static final int FLAG_ADD_STATES_FROM_CHILDREN = 8192;
    private static final int FLAG_ALWAYS_DRAWN_WITH_CACHE = 16384;
    private static final int FLAG_ANIMATION_CACHE = 64;
    static final int FLAG_ANIMATION_DONE = 16;
    private static final int FLAG_CHILDREN_DRAWN_WITH_CACHE = 32768;
    static final int FLAG_CLEAR_TRANSFORMATION = 256;
    static final int FLAG_CLIP_CHILDREN = 1;
    private static final int FLAG_CLIP_TO_PADDING = 2;
    protected static final int FLAG_DISALLOW_INTERCEPT = 0x80000;
    static final int FLAG_INVALIDATE_REQUIRED = 4;
    static final int FLAG_IS_TRANSITION_GROUP = 0x1000000;
    static final int FLAG_IS_TRANSITION_GROUP_SET = 0x2000000;
    private static final int FLAG_LAYOUT_MODE_WAS_EXPLICITLY_SET = 0x800000;
    private static final int FLAG_MASK_FOCUSABILITY = 0x60000;
    private static final int FLAG_NOTIFY_ANIMATION_LISTENER = 512;
    private static final int FLAG_NOTIFY_CHILDREN_ON_DRAWABLE_STATE_CHANGE = 0x10000;
    static final int FLAG_OPTIMIZE_INVALIDATE = 128;
    private static final int FLAG_PADDING_NOT_NULL = 32;
    private static final int FLAG_PREVENT_DISPATCH_ATTACHED_TO_WINDOW = 0x400000;
    private static final int FLAG_RUN_ANIMATION = 8;
    private static final int FLAG_SHOW_CONTEXT_MENU_WITH_COORDS = 0x20000000;
    private static final int FLAG_SPLIT_MOTION_EVENTS = 0x200000;
    private static final int FLAG_START_ACTION_MODE_FOR_CHILD_IS_NOT_TYPED = 0x10000000;
    private static final int FLAG_START_ACTION_MODE_FOR_CHILD_IS_TYPED = 0x8000000;
    protected static final int FLAG_SUPPORT_STATIC_TRANSFORMATIONS = 2048;
    static final int FLAG_TOUCHSCREEN_BLOCKS_FOCUS = 0x4000000;
    protected static final int FLAG_USE_CHILD_DRAWING_ORDER = 1024;
    public static final int FOCUS_AFTER_DESCENDANTS = 0x40000;
    public static final int FOCUS_BEFORE_DESCENDANTS = 0x20000;
    public static final int FOCUS_BLOCK_DESCENDANTS = 0x60000;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static int LAYOUT_MODE_DEFAULT = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;
    private static final int LAYOUT_MODE_UNDEFINED = -1;
    public static final int PERSISTENT_ALL_CACHES = 3;
    public static final int PERSISTENT_ANIMATION_CACHE = 1;
    public static final int PERSISTENT_NO_CACHE = 0;
    public static final int PERSISTENT_SCROLLING_CACHE = 2;
    private static final ActionMode SENTINEL_ACTION_MODE = new ActionMode() {

        public void finish()
        {
        }

        public View getCustomView()
        {
            return null;
        }

        public Menu getMenu()
        {
            return null;
        }

        public MenuInflater getMenuInflater()
        {
            return null;
        }

        public CharSequence getSubtitle()
        {
            return null;
        }

        public CharSequence getTitle()
        {
            return null;
        }

        public void invalidate()
        {
        }

        public void setCustomView(View view)
        {
        }

        public void setSubtitle(int i)
        {
        }

        public void setSubtitle(CharSequence charsequence)
        {
        }

        public void setTitle(int i)
        {
        }

        public void setTitle(CharSequence charsequence)
        {
        }

    }
;
    private static final String TAG = "ViewGroup";
    private static float sDebugLines[];
    private android.view.animation.Animation.AnimationListener mAnimationListener;
    Paint mCachePaint;
    private int mChildCountWithTransientState;
    private Transformation mChildTransformation;
    private View mChildren[];
    private int mChildrenCount;
    private HashSet mChildrenInterestedInDrag;
    private View mCurrentDragChild;
    private DragEvent mCurrentDragStartEvent;
    private View mDefaultFocus;
    protected ArrayList mDisappearingChildren;
    private HoverTarget mFirstHoverTarget;
    private TouchTarget mFirstTouchTarget;
    private View mFocused;
    View mFocusedInCluster;
    protected int mGroupFlags;
    private boolean mHoveredSelf;
    RectF mInvalidateRegion;
    Transformation mInvalidationTransformation;
    private boolean mIsInterestedInDrag;
    private int mLastTouchDownIndex;
    private long mLastTouchDownTime;
    private float mLastTouchDownX;
    private float mLastTouchDownY;
    private LayoutAnimationController mLayoutAnimationController;
    private boolean mLayoutCalledWhileSuppressed;
    private int mLayoutMode;
    private android.animation.LayoutTransition.TransitionListener mLayoutTransitionListener = new android.animation.LayoutTransition.TransitionListener() {

        public void endTransition(LayoutTransition layouttransition, ViewGroup viewgroup, View view, int k)
        {
            if(ViewGroup._2D_get2(ViewGroup.this) && layouttransition.isChangingLayout() ^ true)
            {
                requestLayout();
                ViewGroup._2D_set0(ViewGroup.this, false);
            }
            if(k == 3 && ViewGroup._2D_get3(ViewGroup.this) != null)
                endViewTransition(view);
        }

        public void startTransition(LayoutTransition layouttransition, ViewGroup viewgroup, View view, int k)
        {
            if(k == 3)
                startViewTransition(view);
        }

        final ViewGroup this$0;

            
            {
                this$0 = ViewGroup.this;
                super();
            }
    }
;
    private PointF mLocalPoint;
    private int mNestedScrollAxes;
    protected OnHierarchyChangeListener mOnHierarchyChangeListener;
    protected int mPersistentDrawingCache;
    private ArrayList mPreSortedChildren;
    boolean mSuppressLayout;
    private float mTempPoint[];
    private View mTooltipHoverTarget;
    private boolean mTooltipHoveredSelf;
    private List mTransientIndices;
    private List mTransientViews;
    private LayoutTransition mTransition;
    private ArrayList mTransitioningViews;
    private ArrayList mVisibilityChangingChildren;

    static 
    {
        LAYOUT_MODE_DEFAULT = 0;
    }
}
