// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.util.ArrayMap;
import android.util.ArraySet;
import java.util.*;

// Referenced classes of package android.view:
//            ViewGroup, View, ViewParent, ViewConfiguration

public class FocusFinder
{
    static final class FocusSorter
    {

        int lambda$_2D_android_view_FocusFinder$FocusSorter_31343(View view, View view1)
        {
            if(view == view1)
                return 0;
            view = (Rect)mRectByView.get(view);
            view1 = (Rect)mRectByView.get(view1);
            int i = ((Rect) (view)).top - ((Rect) (view1)).top;
            if(i == 0)
                return ((Rect) (view)).bottom - ((Rect) (view1)).bottom;
            else
                return i;
        }

        int lambda$_2D_android_view_FocusFinder$FocusSorter_31803(View view, View view1)
        {
            if(view == view1)
                return 0;
            view = (Rect)mRectByView.get(view);
            view1 = (Rect)mRectByView.get(view1);
            int i = ((Rect) (view)).left - ((Rect) (view1)).left;
            if(i == 0)
                return ((Rect) (view)).right - ((Rect) (view1)).right;
            else
                return mRtlMult * i;
        }

        public void sort(View aview[], int i, int j, ViewGroup viewgroup, boolean flag)
        {
            int k = j - i;
            if(k < 2)
                return;
            if(mRectByView == null)
                mRectByView = new HashMap();
            int l;
            if(flag)
                l = -1;
            else
                l = 1;
            mRtlMult = l;
            for(l = mRectPool.size(); l < k; l++)
                mRectPool.add(new Rect());

            for(l = i; l < j; l++)
            {
                Object obj = mRectPool;
                int i1 = mLastPoolRect;
                mLastPoolRect = i1 + 1;
                obj = (Rect)((ArrayList) (obj)).get(i1);
                aview[l].getDrawingRect(((Rect) (obj)));
                viewgroup.offsetDescendantRectToMyCoords(aview[l], ((Rect) (obj)));
                mRectByView.put(aview[l], obj);
            }

            Arrays.sort(aview, i, k, mTopsComparator);
            l = ((Rect)mRectByView.get(aview[i])).bottom;
            k = i;
            i++;
            while(i < j) 
            {
                viewgroup = (Rect)mRectByView.get(aview[i]);
                if(((Rect) (viewgroup)).top >= l)
                {
                    if(i - k > 1)
                        Arrays.sort(aview, k, i, mSidesComparator);
                    l = ((Rect) (viewgroup)).bottom;
                    k = i;
                } else
                {
                    l = Math.max(l, ((Rect) (viewgroup)).bottom);
                }
                i++;
            }
            if(i - k > 1)
                Arrays.sort(aview, k, i, mSidesComparator);
            mLastPoolRect = 0;
            mRectByView.clear();
        }

        private int mLastPoolRect;
        private HashMap mRectByView;
        private ArrayList mRectPool;
        private int mRtlMult;
        private Comparator mSidesComparator;
        private Comparator mTopsComparator;

        FocusSorter()
        {
            mRectPool = new ArrayList();
            mRectByView = null;
            mTopsComparator = new _.Lambda._cls6k_RnLLpNi5zg27ubDxN4lDdBbk._cls1((byte)0, this);
            mSidesComparator = new _.Lambda._cls6k_RnLLpNi5zg27ubDxN4lDdBbk._cls1((byte)1, this);
        }
    }

    private static final class UserSpecifiedFocusComparator
        implements Comparator
    {

        private void setHeadOfChain(View view)
        {
            for(View view1 = view; view1 != null;)
            {
                View view2 = (View)mHeadsOfChains.get(view1);
                View view3 = view;
                if(view2 != null)
                {
                    if(view2 == view)
                        return;
                    view3 = view2;
                    view1 = view;
                }
                mHeadsOfChains.put(view1, view3);
                view1 = (View)mNextFoci.get(view1);
                view = view3;
            }

        }

        public int compare(View view, View view1)
        {
            if(view == view1)
                return 0;
            View view2 = (View)mHeadsOfChains.get(view);
            View view3 = (View)mHeadsOfChains.get(view1);
            if(view2 == view3 && view2 != null)
            {
                if(view == view2)
                    return -1;
                if(view1 == view2)
                    return 1;
                return mNextFoci.get(view) == null ? 1 : -1;
            }
            boolean flag = false;
            if(view2 != null)
            {
                view = view2;
                flag = true;
            }
            if(view3 != null)
            {
                view1 = view3;
                flag = true;
            }
            if(flag)
            {
                byte byte0;
                if(((Integer)mOriginalOrdinal.get(view)).intValue() < ((Integer)mOriginalOrdinal.get(view1)).intValue())
                    byte0 = -1;
                else
                    byte0 = 1;
                return byte0;
            } else
            {
                return 0;
            }
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((View)obj, (View)obj1);
        }

        public void recycle()
        {
            mRoot = null;
            mHeadsOfChains.clear();
            mIsConnectedTo.clear();
            mOriginalOrdinal.clear();
            mNextFoci.clear();
        }

        public void setFocusables(List list, View view)
        {
            mRoot = view;
            for(int i = 0; i < list.size(); i++)
                mOriginalOrdinal.put((View)list.get(i), Integer.valueOf(i));

            for(int j = list.size() - 1; j >= 0; j--)
            {
                view = (View)list.get(j);
                View view1 = mNextFocusGetter.get(mRoot, view);
                if(view1 != null && mOriginalOrdinal.containsKey(view1))
                {
                    mNextFoci.put(view, view1);
                    mIsConnectedTo.add(view1);
                }
            }

            for(int k = list.size() - 1; k >= 0; k--)
            {
                view = (View)list.get(k);
                if((View)mNextFoci.get(view) != null && mIsConnectedTo.contains(view) ^ true)
                    setHeadOfChain(view);
            }

        }

        private final ArrayMap mHeadsOfChains = new ArrayMap();
        private final ArraySet mIsConnectedTo = new ArraySet();
        private final ArrayMap mNextFoci = new ArrayMap();
        private final NextFocusGetter mNextFocusGetter;
        private final ArrayMap mOriginalOrdinal = new ArrayMap();
        private View mRoot;

        UserSpecifiedFocusComparator(NextFocusGetter nextfocusgetter)
        {
            mNextFocusGetter = nextfocusgetter;
        }
    }

    public static interface UserSpecifiedFocusComparator.NextFocusGetter
    {

        public abstract View get(View view, View view1);
    }


    private FocusFinder()
    {
        mFocusedRect = new Rect();
        mOtherRect = new Rect();
        mBestCandidateRect = new Rect();
        mUserSpecifiedFocusComparator = new UserSpecifiedFocusComparator(_.Lambda._cls6k_RnLLpNi5zg27ubDxN4lDdBbk.$INST$0);
        mUserSpecifiedClusterComparator = new UserSpecifiedFocusComparator(_.Lambda._cls6k_RnLLpNi5zg27ubDxN4lDdBbk.$INST$1);
        mFocusSorter = new FocusSorter();
        mTempList = new ArrayList();
    }

    FocusFinder(FocusFinder focusfinder)
    {
        this();
    }

    private View findNextFocus(ViewGroup viewgroup, View view, Rect rect, int i)
    {
        ArrayList arraylist;
        ViewGroup viewgroup1;
        arraylist = null;
        viewgroup1 = getEffectiveRoot(viewgroup, view);
        viewgroup = arraylist;
        if(view != null)
            viewgroup = findNextUserSpecifiedFocus(viewgroup1, view, i);
        if(viewgroup != null)
            return viewgroup;
        arraylist = mTempList;
        arraylist.clear();
        viewgroup1.addFocusables(arraylist, i);
        if(!arraylist.isEmpty())
            viewgroup = findNextFocus(viewgroup1, view, rect, i, arraylist);
        arraylist.clear();
        return viewgroup;
        viewgroup;
        arraylist.clear();
        throw viewgroup;
    }

    private View findNextFocus(ViewGroup viewgroup, View view, Rect rect, int i, ArrayList arraylist)
    {
        if(view == null) goto _L2; else goto _L1
_L1:
        Rect rect1;
        rect1 = rect;
        if(rect == null)
            rect1 = mFocusedRect;
        view.getFocusedRect(rect1);
        viewgroup.offsetDescendantRectToMyCoords(view, rect1);
_L4:
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown direction: ").append(i).toString());

        case 1: // '\001'
        case 2: // '\002'
            return findNextFocusInRelativeDirection(arraylist, viewgroup, view, rect1, i);

        case 17: // '\021'
        case 33: // '!'
        case 66: // 'B'
        case 130: 
            return findNextFocusInAbsoluteDirection(arraylist, viewgroup, view, rect1, i);
        }
_L2:
        rect1 = rect;
        if(rect != null) goto _L4; else goto _L3
_L3:
        rect1 = mFocusedRect;
        switch(i)
        {
        case 1: // '\001'
            if(viewgroup.isLayoutRtl())
                setFocusTopLeft(viewgroup, rect1);
            else
                setFocusBottomRight(viewgroup, rect1);
            break;

        case 66: // 'B'
        case 130: 
            setFocusTopLeft(viewgroup, rect1);
            break;

        case 2: // '\002'
            if(viewgroup.isLayoutRtl())
                setFocusBottomRight(viewgroup, rect1);
            else
                setFocusTopLeft(viewgroup, rect1);
            break;

        case 17: // '\021'
        case 33: // '!'
            setFocusBottomRight(viewgroup, rect1);
            break;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    private View findNextFocusInRelativeDirection(ArrayList arraylist, ViewGroup viewgroup, View view, Rect rect, int i)
    {
        mUserSpecifiedFocusComparator.setFocusables(arraylist, viewgroup);
        Collections.sort(arraylist, mUserSpecifiedFocusComparator);
        mUserSpecifiedFocusComparator.recycle();
        int j = arraylist.size();
        switch(i)
        {
        default:
            return (View)arraylist.get(j - 1);

        case 2: // '\002'
            return getNextFocusable(view, arraylist, j);

        case 1: // '\001'
            return getPreviousFocusable(view, arraylist, j);
        }
        arraylist;
        mUserSpecifiedFocusComparator.recycle();
        throw arraylist;
    }

    private View findNextKeyboardNavigationCluster(View view, View view1, List list, int i)
    {
        mUserSpecifiedClusterComparator.setFocusables(list, view);
        Collections.sort(list, mUserSpecifiedClusterComparator);
        mUserSpecifiedClusterComparator.recycle();
        int j = list.size();
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown direction: ").append(i).toString());

        case 2: // '\002'
        case 66: // 'B'
        case 130: 
            return getNextKeyboardNavigationCluster(view, view1, list, j);

        case 1: // '\001'
        case 17: // '\021'
        case 33: // '!'
            return getPreviousKeyboardNavigationCluster(view, view1, list, j);
        }
        view;
        mUserSpecifiedClusterComparator.recycle();
        throw view;
    }

    private View findNextUserSpecifiedFocus(ViewGroup viewgroup, View view, int i)
    {
        view = view.findUserSetNextFocus(viewgroup, i);
        View view1 = view;
        boolean flag = true;
        View view2;
        View view3;
label0:
        do
        {
            boolean flag1;
            do
            {
                if(view == null)
                    break label0;
                if(view.isFocusable() && view.getVisibility() == 0 && (!view.isInTouchMode() || view.isFocusableInTouchMode()))
                    return view;
                view2 = view.findUserSetNextFocus(viewgroup, i);
                flag1 = flag ^ true;
                flag = flag1;
                view = view2;
            } while(!flag1);
            view3 = view1.findUserSetNextFocus(viewgroup, i);
            view1 = view3;
            flag = flag1;
            view = view2;
        } while(view3 != view2);
        return null;
    }

    private View findNextUserSpecifiedKeyboardNavigationCluster(View view, View view1, int i)
    {
        view = view1.findUserSetNextKeyboardNavigationCluster(view, i);
        if(view != null && view.hasFocusable())
            return view;
        else
            return null;
    }

    private ViewGroup getEffectiveRoot(ViewGroup viewgroup, View view)
    {
        if(view == null || view == viewgroup)
            return viewgroup;
        ViewGroup viewgroup1 = null;
        ViewParent viewparent = view.getParent();
        Object obj;
        do
        {
            if(viewparent == viewgroup)
            {
                if(viewgroup1 == null)
                    viewgroup1 = viewgroup;
                return viewgroup1;
            }
            obj = (ViewGroup)viewparent;
            ViewGroup viewgroup2 = viewgroup1;
            if(((ViewGroup) (obj)).getTouchscreenBlocksFocus())
            {
                viewgroup2 = viewgroup1;
                if(view.getContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen"))
                {
                    viewgroup2 = viewgroup1;
                    if(((ViewGroup) (obj)).isKeyboardNavigationCluster())
                        viewgroup2 = ((ViewGroup) (obj));
                }
            }
            obj = viewparent.getParent();
            viewgroup1 = viewgroup2;
            viewparent = ((ViewParent) (obj));
        } while(obj instanceof ViewGroup);
        return viewgroup;
    }

    public static FocusFinder getInstance()
    {
        return (FocusFinder)tlFocusFinder.get();
    }

    private static View getNextFocusable(View view, ArrayList arraylist, int i)
    {
        if(view != null)
        {
            int j = arraylist.lastIndexOf(view);
            if(j >= 0 && j + 1 < i)
                return (View)arraylist.get(j + 1);
        }
        if(!arraylist.isEmpty())
            return (View)arraylist.get(0);
        else
            return null;
    }

    private static View getNextKeyboardNavigationCluster(View view, View view1, List list, int i)
    {
        if(view1 == null)
            return (View)list.get(0);
        int j = list.lastIndexOf(view1);
        if(j >= 0 && j + 1 < i)
            return (View)list.get(j + 1);
        else
            return view;
    }

    private static View getPreviousFocusable(View view, ArrayList arraylist, int i)
    {
        if(view != null)
        {
            int j = arraylist.indexOf(view);
            if(j > 0)
                return (View)arraylist.get(j - 1);
        }
        if(!arraylist.isEmpty())
            return (View)arraylist.get(i - 1);
        else
            return null;
    }

    private static View getPreviousKeyboardNavigationCluster(View view, View view1, List list, int i)
    {
        if(view1 == null)
            return (View)list.get(i - 1);
        i = list.indexOf(view1);
        if(i > 0)
            return (View)list.get(i - 1);
        else
            return view;
    }

    private boolean isTouchCandidate(int i, int j, Rect rect, int k)
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        switch(k)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            if(rect.left > i || rect.top > j || j > rect.bottom)
                flag3 = false;
            return flag3;

        case 66: // 'B'
            if(rect.left >= i && rect.top <= j && j <= rect.bottom)
                flag3 = flag;
            else
                flag3 = false;
            return flag3;

        case 33: // '!'
            if(rect.top <= j && rect.left <= i && i <= rect.right)
                flag3 = flag1;
            else
                flag3 = false;
            return flag3;

        case 130: 
            break;
        }
        if(rect.top >= j && rect.left <= i && i <= rect.right)
            flag3 = flag2;
        else
            flag3 = false;
        return flag3;
    }

    private static final boolean isValidId(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i != 0)
        {
            flag1 = flag;
            if(i != -1)
                flag1 = true;
        }
        return flag1;
    }

    static View lambda$_2D_android_view_FocusFinder_1877(View view, View view1)
    {
        if(isValidId(view1.getNextFocusForwardId()))
            view = view1.findUserSetNextFocus(view, 2);
        else
            view = null;
        return view;
    }

    static View lambda$_2D_android_view_FocusFinder_2135(View view, View view1)
    {
        if(isValidId(view1.getNextClusterForwardId()))
            view = view1.findUserSetNextKeyboardNavigationCluster(view, 2);
        else
            view = null;
        return view;
    }

    static int majorAxisDistance(int i, Rect rect, Rect rect1)
    {
        return Math.max(0, majorAxisDistanceRaw(i, rect, rect1));
    }

    static int majorAxisDistanceRaw(int i, Rect rect, Rect rect1)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            return rect.left - rect1.right;

        case 66: // 'B'
            return rect1.left - rect.right;

        case 33: // '!'
            return rect.top - rect1.bottom;

        case 130: 
            return rect1.top - rect.bottom;
        }
    }

    static int majorAxisDistanceToFarEdge(int i, Rect rect, Rect rect1)
    {
        return Math.max(1, majorAxisDistanceToFarEdgeRaw(i, rect, rect1));
    }

    static int majorAxisDistanceToFarEdgeRaw(int i, Rect rect, Rect rect1)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            return rect.left - rect1.left;

        case 66: // 'B'
            return rect1.right - rect.right;

        case 33: // '!'
            return rect.top - rect1.top;

        case 130: 
            return rect1.bottom - rect.bottom;
        }
    }

    static int minorAxisDistance(int i, Rect rect, Rect rect1)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
        case 66: // 'B'
            return Math.abs((rect.top + rect.height() / 2) - (rect1.top + rect1.height() / 2));

        case 33: // '!'
        case 130: 
            return Math.abs((rect.left + rect.width() / 2) - (rect1.left + rect1.width() / 2));
        }
    }

    private void setFocusBottomRight(ViewGroup viewgroup, Rect rect)
    {
        int i = viewgroup.getScrollY() + viewgroup.getHeight();
        int j = viewgroup.getScrollX() + viewgroup.getWidth();
        rect.set(j, i, j, i);
    }

    private void setFocusTopLeft(ViewGroup viewgroup, Rect rect)
    {
        int i = viewgroup.getScrollY();
        int j = viewgroup.getScrollX();
        rect.set(j, i, j, i);
    }

    public static void sort(View aview[], int i, int j, ViewGroup viewgroup, boolean flag)
    {
        getInstance().mFocusSorter.sort(aview, i, j, viewgroup, flag);
    }

    boolean beamBeats(int i, Rect rect, Rect rect1, Rect rect2)
    {
        boolean flag = true;
        boolean flag1 = beamsOverlap(i, rect, rect1);
        if(beamsOverlap(i, rect, rect2) || flag1 ^ true)
            return false;
        if(!isToDirectionOf(i, rect, rect2))
            return true;
        if(i == 17 || i == 66)
            return true;
        if(majorAxisDistance(i, rect, rect1) >= majorAxisDistanceToFarEdge(i, rect, rect2))
            flag = false;
        return flag;
    }

    boolean beamsOverlap(int i, Rect rect, Rect rect1)
    {
        boolean flag = true;
        boolean flag1 = false;
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
        case 66: // 'B'
            if(rect1.bottom <= rect.top || rect1.top >= rect.bottom)
                flag = false;
            return flag;

        case 33: // '!'
        case 130: 
            flag = flag1;
            break;
        }
        if(rect1.right > rect.left)
        {
            flag = flag1;
            if(rect1.left < rect.right)
                flag = true;
        }
        return flag;
    }

    public View findNearestTouchable(ViewGroup viewgroup, int i, int j, int k, int ai[])
    {
        ArrayList arraylist;
        int l;
        View view;
        int i1;
        int j1;
        Rect rect;
        Rect rect1;
        int k1;
        arraylist = viewgroup.getTouchables();
        l = 0x7fffffff;
        view = null;
        i1 = arraylist.size();
        j1 = ViewConfiguration.get(viewgroup.mContext).getScaledEdgeSlop();
        rect = new Rect();
        rect1 = mOtherRect;
        k1 = 0;
_L5:
        if(k1 >= i1) goto _L2; else goto _L1
_L1:
        View view1;
        view1 = (View)arraylist.get(k1);
        view1.getDrawingRect(rect1);
        viewgroup.offsetRectBetweenParentAndChild(view1, rect1, true, true);
        if(isTouchCandidate(i, j, rect1, k)) goto _L4; else goto _L3
_L3:
        int l1;
        View view2;
        l1 = l;
        view2 = view;
_L12:
        k1++;
        view = view2;
        l = l1;
          goto _L5
_L4:
        int i2 = 0x7fffffff;
        k;
        JVM INSTR lookupswitch 4: default 172
    //                   17: 309
    //                   33: 333
    //                   66: 323
    //                   130: 347;
           goto _L6 _L7 _L8 _L9 _L10
_L6:
        break; /* Loop/switch isn't completed */
_L10:
        break MISSING_BLOCK_LABEL_347;
_L15:
        view2 = view;
        l1 = l;
        if(i2 >= j1) goto _L12; else goto _L11
_L11:
        if(view == null || rect.contains(rect1))
            break; /* Loop/switch isn't completed */
        view2 = view;
        l1 = l;
        if(rect1.contains(rect)) goto _L12; else goto _L13
_L13:
        view2 = view;
        l1 = l;
        if(i2 >= l) goto _L12; else goto _L14
_L14:
        l1 = i2;
        view2 = view1;
        rect.set(rect1);
        switch(k)
        {
        case 17: // '\021'
            ai[0] = -i2;
            break;

        case 66: // 'B'
            ai[0] = i2;
            break;

        case 33: // '!'
            ai[1] = -i2;
            break;

        case 130: 
            ai[1] = i2;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        i2 = (i - rect1.right) + 1;
          goto _L15
_L9:
        i2 = rect1.left;
          goto _L15
_L8:
        i2 = (j - rect1.bottom) + 1;
          goto _L15
        i2 = rect1.top;
          goto _L15
_L2:
        return view;
        if(true) goto _L12; else goto _L16
_L16:
    }

    public final View findNextFocus(ViewGroup viewgroup, View view, int i)
    {
        return findNextFocus(viewgroup, view, null, i);
    }

    public View findNextFocusFromRect(ViewGroup viewgroup, Rect rect, int i)
    {
        mFocusedRect.set(rect);
        return findNextFocus(viewgroup, null, mFocusedRect, i);
    }

    View findNextFocusInAbsoluteDirection(ArrayList arraylist, ViewGroup viewgroup, View view, Rect rect, int i)
    {
        mBestCandidateRect.set(rect);
        int j;
        int k;
        switch(i)
        {
        case 17: // '\021'
            mBestCandidateRect.offset(rect.width() + 1, 0);
            continue;

        case 66: // 'B'
            mBestCandidateRect.offset(-(rect.width() + 1), 0);
            continue;

        case 33: // '!'
            mBestCandidateRect.offset(0, rect.height() + 1);
            continue;

        case 130: 
            mBestCandidateRect.offset(0, -(rect.height() + 1));
            continue;
        }
        break;
        do
        {
            View view1 = null;
            j = arraylist.size();
            k = 0;
            while(k < j) 
            {
                View view2 = (View)arraylist.get(k);
                View view3 = view1;
                if(view2 != view)
                    if(view2 == viewgroup)
                    {
                        view3 = view1;
                    } else
                    {
                        view2.getFocusedRect(mOtherRect);
                        viewgroup.offsetDescendantRectToMyCoords(view2, mOtherRect);
                        view3 = view1;
                        if(isBetterCandidate(i, rect, mOtherRect, mBestCandidateRect))
                        {
                            mBestCandidateRect.set(mOtherRect);
                            view3 = view2;
                        }
                    }
                k++;
                view1 = view3;
            }
            return view1;
        } while(true);
    }

    public View findNextKeyboardNavigationCluster(View view, View view1, int i)
    {
        View view2;
        ArrayList arraylist;
        view2 = null;
        if(view1 != null)
        {
            View view3 = findNextUserSpecifiedKeyboardNavigationCluster(view, view1, i);
            view2 = view3;
            if(view3 != null)
                return view3;
        }
        arraylist = mTempList;
        arraylist.clear();
        view.addKeyboardNavigationClusters(arraylist, i);
        if(!arraylist.isEmpty())
            view2 = findNextKeyboardNavigationCluster(view, view1, ((List) (arraylist)), i);
        arraylist.clear();
        return view2;
        view;
        arraylist.clear();
        throw view;
    }

    int getWeightedDistanceFor(int i, int j)
    {
        return i * 13 * i + j * j;
    }

    boolean isBetterCandidate(int i, Rect rect, Rect rect1, Rect rect2)
    {
        boolean flag = true;
        if(!isCandidate(rect, rect1, i))
            return false;
        if(!isCandidate(rect, rect2, i))
            return true;
        if(beamBeats(i, rect, rect1, rect2))
            return true;
        if(beamBeats(i, rect, rect2, rect1))
            return false;
        if(getWeightedDistanceFor(majorAxisDistance(i, rect, rect1), minorAxisDistance(i, rect, rect1)) >= getWeightedDistanceFor(majorAxisDistance(i, rect, rect2), minorAxisDistance(i, rect, rect2)))
            flag = false;
        return flag;
    }

    boolean isCandidate(Rect rect, Rect rect1, int i)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        flag = true;
        flag1 = true;
        flag2 = true;
        flag3 = true;
        flag4 = false;
        flag5 = false;
        flag6 = false;
        flag7 = false;
        i;
        JVM INSTR lookupswitch 4: default 68
    //                   17: 79
    //                   33: 175
    //                   66: 125
    //                   130: 225;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L2:
        if(rect.right > rect1.right || rect.left >= rect1.right)
            if(rect.left > rect1.left)
                flag7 = flag3;
            else
                flag7 = false;
        return flag7;
_L4:
        if(rect.left < rect1.left) goto _L7; else goto _L6
_L6:
        flag7 = flag4;
        if(rect.right > rect1.left) goto _L8; else goto _L7
_L7:
        if(rect.right < rect1.right)
            flag7 = flag;
        else
            flag7 = false;
_L8:
        return flag7;
_L3:
        if(rect.bottom > rect1.bottom) goto _L10; else goto _L9
_L9:
        flag7 = flag5;
        if(rect.top < rect1.bottom) goto _L11; else goto _L10
_L10:
        if(rect.top > rect1.top)
            flag7 = flag1;
        else
            flag7 = false;
_L11:
        return flag7;
_L5:
        if(rect.top < rect1.top) goto _L13; else goto _L12
_L12:
        flag7 = flag6;
        if(rect.bottom > rect1.top) goto _L14; else goto _L13
_L13:
        if(rect.bottom < rect1.bottom)
            flag7 = flag2;
        else
            flag7 = false;
_L14:
        return flag7;
    }

    boolean isToDirectionOf(int i, Rect rect, Rect rect1)
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            if(rect.left < rect1.right)
                flag3 = false;
            return flag3;

        case 66: // 'B'
            if(rect.right <= rect1.left)
                flag3 = flag;
            else
                flag3 = false;
            return flag3;

        case 33: // '!'
            if(rect.top >= rect1.bottom)
                flag3 = flag1;
            else
                flag3 = false;
            return flag3;

        case 130: 
            break;
        }
        if(rect.bottom <= rect1.top)
            flag3 = flag2;
        else
            flag3 = false;
        return flag3;
    }

    private static final ThreadLocal tlFocusFinder = new ThreadLocal() {

        protected FocusFinder initialValue()
        {
            return new FocusFinder(null);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;
    final Rect mBestCandidateRect;
    private final FocusSorter mFocusSorter;
    final Rect mFocusedRect;
    final Rect mOtherRect;
    private final ArrayList mTempList;
    private final UserSpecifiedFocusComparator mUserSpecifiedClusterComparator;
    private final UserSpecifiedFocusComparator mUserSpecifiedFocusComparator;

}
