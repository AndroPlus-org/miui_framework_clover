// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import java.util.*;

public class RelativeLayout extends ViewGroup
{
    private static class DependencyGraph
    {

        static SparseArray _2D_get0(DependencyGraph dependencygraph)
        {
            return dependencygraph.mKeyNodes;
        }

        private ArrayDeque findRoots(int ai[])
        {
            SparseArray sparsearray = mKeyNodes;
            ArrayList arraylist = mNodes;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
            {
                Node node1 = (Node)arraylist.get(j);
                node1.dependents.clear();
                node1.dependencies.clear();
            }

            for(int k = 0; k < i; k++)
            {
                Node node3 = (Node)arraylist.get(k);
                int ai1[] = LayoutParams._2D_get3((LayoutParams)node3.view.getLayoutParams());
                int i1 = ai.length;
                int j1 = 0;
                while(j1 < i1) 
                {
                    int k1 = ai1[ai[j1]];
                    if(k1 > 0)
                    {
                        Node node2 = (Node)sparsearray.get(k1);
                        if(node2 != null && node2 != node3)
                        {
                            node2.dependents.put(node3, this);
                            node3.dependencies.put(k1, node2);
                        }
                    }
                    j1++;
                }
            }

            ai = mRoots;
            ai.clear();
            for(int l = 0; l < i; l++)
            {
                Node node = (Node)arraylist.get(l);
                if(node.dependencies.size() == 0)
                    ai.addLast(node);
            }

            return ai;
        }

        void add(View view)
        {
            int i = view.getId();
            view = Node.acquire(view);
            if(i != -1)
                mKeyNodes.put(i, view);
            mNodes.add(view);
        }

        void clear()
        {
            ArrayList arraylist = mNodes;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((Node)arraylist.get(j)).release();

            arraylist.clear();
            mKeyNodes.clear();
            mRoots.clear();
        }

        transient void getSortedViews(View aview[], int ai[])
        {
            ai = findRoots(ai);
            int i = 0;
            do
            {
                Node node = (Node)ai.pollLast();
                if(node == null)
                    break;
                Object obj = node.view;
                int j = ((View) (obj)).getId();
                aview[i] = ((View) (obj));
                obj = node.dependents;
                int k = ((ArrayMap) (obj)).size();
                for(int l = 0; l < k; l++)
                {
                    Node node1 = (Node)((ArrayMap) (obj)).keyAt(l);
                    SparseArray sparsearray = node1.dependencies;
                    sparsearray.remove(j);
                    if(sparsearray.size() == 0)
                        ai.add(node1);
                }

                i++;
            } while(true);
            if(i < aview.length)
                throw new IllegalStateException("Circular dependencies cannot exist in RelativeLayout");
            else
                return;
        }

        private SparseArray mKeyNodes;
        private ArrayList mNodes;
        private ArrayDeque mRoots;

        private DependencyGraph()
        {
            mNodes = new ArrayList();
            mKeyNodes = new SparseArray();
            mRoots = new ArrayDeque();
        }

        DependencyGraph(DependencyGraph dependencygraph)
        {
            this();
        }
    }

    static class DependencyGraph.Node
    {

        static DependencyGraph.Node acquire(View view1)
        {
            DependencyGraph.Node node = (DependencyGraph.Node)sPool.acquire();
            DependencyGraph.Node node1 = node;
            if(node == null)
                node1 = new DependencyGraph.Node();
            node1.view = view1;
            return node1;
        }

        void release()
        {
            view = null;
            dependents.clear();
            dependencies.clear();
            sPool.release(this);
        }

        private static final int POOL_LIMIT = 100;
        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(100);
        final SparseArray dependencies = new SparseArray();
        final ArrayMap dependents = new ArrayMap();
        View view;


        DependencyGraph.Node()
        {
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        static int _2D_get0(LayoutParams layoutparams)
        {
            return layoutparams.mBottom;
        }

        static int _2D_get1(LayoutParams layoutparams)
        {
            return layoutparams.mLeft;
        }

        static int _2D_get2(LayoutParams layoutparams)
        {
            return layoutparams.mRight;
        }

        static int[] _2D_get3(LayoutParams layoutparams)
        {
            return layoutparams.mRules;
        }

        static int _2D_get4(LayoutParams layoutparams)
        {
            return layoutparams.mTop;
        }

        static int _2D_set0(LayoutParams layoutparams, int i)
        {
            layoutparams.mBottom = i;
            return i;
        }

        static int _2D_set1(LayoutParams layoutparams, int i)
        {
            layoutparams.mLeft = i;
            return i;
        }

        static int _2D_set2(LayoutParams layoutparams, int i)
        {
            layoutparams.mRight = i;
            return i;
        }

        static int _2D_set3(LayoutParams layoutparams, int i)
        {
            layoutparams.mTop = i;
            return i;
        }

        private boolean hasRelativeRules()
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(mInitialRules[16] != 0) goto _L2; else goto _L1
_L1:
            if(mInitialRules[17] == 0) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(mInitialRules[18] == 0)
            {
                flag1 = flag;
                if(mInitialRules[19] == 0)
                {
                    flag1 = flag;
                    if(mInitialRules[20] == 0)
                    {
                        flag1 = flag;
                        if(mInitialRules[21] == 0)
                            flag1 = false;
                    }
                }
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        private boolean isRelativeRule(int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(i == 16) goto _L2; else goto _L1
_L1:
            if(i != 17) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 18)
            {
                flag1 = flag;
                if(i != 19)
                {
                    flag1 = flag;
                    if(i != 20)
                    {
                        flag1 = flag;
                        if(i != 21)
                            flag1 = false;
                    }
                }
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        private void resolveRules(int i)
        {
            boolean flag;
            flag = true;
            if(i == 1)
                i = 1;
            else
                i = 0;
            System.arraycopy(mInitialRules, 0, mRules, 0, 22);
            if(!mIsRtlCompatibilityMode) goto _L2; else goto _L1
_L1:
            if(mRules[18] != 0)
            {
                if(mRules[5] == 0)
                    mRules[5] = mRules[18];
                mRules[18] = 0;
            }
            if(mRules[19] != 0)
            {
                if(mRules[7] == 0)
                    mRules[7] = mRules[19];
                mRules[19] = 0;
            }
            if(mRules[16] != 0)
            {
                if(mRules[0] == 0)
                    mRules[0] = mRules[16];
                mRules[16] = 0;
            }
            if(mRules[17] != 0)
            {
                if(mRules[1] == 0)
                    mRules[1] = mRules[17];
                mRules[17] = 0;
            }
            if(mRules[20] != 0)
            {
                if(mRules[9] == 0)
                    mRules[9] = mRules[20];
                mRules[20] = 0;
            }
            if(mRules[21] != 0)
            {
                if(mRules[11] == 0)
                    mRules[11] = mRules[21];
                mRules[21] = 0;
            }
_L4:
            mRulesChanged = false;
            mNeedsLayoutResolution = false;
            return;
_L2:
            if((mRules[18] != 0 || mRules[19] != 0) && (mRules[5] != 0 || mRules[7] != 0))
            {
                mRules[5] = 0;
                mRules[7] = 0;
            }
            int j;
            if(mRules[18] != 0)
            {
                int ai[] = mRules;
                if(i != 0)
                    j = 7;
                else
                    j = 5;
                ai[j] = mRules[18];
                mRules[18] = 0;
            }
            if(mRules[19] != 0)
            {
                ai = mRules;
                if(i != 0)
                    j = 5;
                else
                    j = 7;
                ai[j] = mRules[19];
                mRules[19] = 0;
            }
            if((mRules[16] != 0 || mRules[17] != 0) && (mRules[0] != 0 || mRules[1] != 0))
            {
                mRules[0] = 0;
                mRules[1] = 0;
            }
            if(mRules[16] != 0)
            {
                ai = mRules;
                if(i != 0)
                    j = 1;
                else
                    j = 0;
                ai[j] = mRules[16];
                mRules[16] = 0;
            }
            if(mRules[17] != 0)
            {
                ai = mRules;
                j = ((flag) ? 1 : 0);
                if(i != 0)
                    j = 0;
                ai[j] = mRules[17];
                mRules[17] = 0;
            }
            if((mRules[20] != 0 || mRules[21] != 0) && (mRules[9] != 0 || mRules[11] != 0))
            {
                mRules[9] = 0;
                mRules[11] = 0;
            }
            if(mRules[20] != 0)
            {
                ai = mRules;
                if(i != 0)
                    j = 11;
                else
                    j = 9;
                ai[j] = mRules[20];
                mRules[20] = 0;
            }
            if(mRules[21] != 0)
            {
                ai = mRules;
                if(i != 0)
                    i = 9;
                else
                    i = 11;
                ai[i] = mRules[21];
                mRules[21] = 0;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private boolean shouldResolveLayoutDirection(int i)
        {
            boolean flag1;
label0:
            {
                boolean flag = false;
                if(!mNeedsLayoutResolution)
                {
                    flag1 = flag;
                    if(!hasRelativeRules())
                        break label0;
                }
                if(!mRulesChanged)
                {
                    flag1 = flag;
                    if(i == getLayoutDirection())
                        break label0;
                }
                flag1 = true;
            }
            return flag1;
        }

        public void addRule(int i)
        {
            addRule(i, -1);
        }

        public void addRule(int i, int j)
        {
            if(!mNeedsLayoutResolution && isRelativeRule(i) && mInitialRules[i] != 0 && j == 0)
                mNeedsLayoutResolution = true;
            mRules[i] = j;
            mInitialRules[i] = j;
            mRulesChanged = true;
        }

        public String debug(String s)
        {
            return (new StringBuilder()).append(s).append("ViewGroup.LayoutParams={ width=").append(sizeToString(width)).append(", height=").append(sizeToString(height)).append(" }").toString();
        }

        protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
        {
            super.encodeProperties(viewhierarchyencoder);
            viewhierarchyencoder.addProperty("layout:alignWithParent", alignWithParent);
        }

        public int getRule(int i)
        {
            return mRules[i];
        }

        public int[] getRules()
        {
            return mRules;
        }

        public int[] getRules(int i)
        {
            resolveLayoutDirection(i);
            return mRules;
        }

        public void removeRule(int i)
        {
            addRule(i, 0);
        }

        public void resolveLayoutDirection(int i)
        {
            if(shouldResolveLayoutDirection(i))
                resolveRules(i);
            super.resolveLayoutDirection(i);
        }

        public boolean alignWithParent;
        private int mBottom;
        private int mInitialRules[];
        private boolean mIsRtlCompatibilityMode;
        private int mLeft;
        private boolean mNeedsLayoutResolution;
        private int mRight;
        private int mRules[];
        private boolean mRulesChanged;
        private int mTop;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mRules = new int[22];
            mInitialRules = new int[22];
            mRulesChanged = false;
            mIsRtlCompatibilityMode = false;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            int ai[];
            int k;
            super(context, attributeset);
            mRules = new int[22];
            mInitialRules = new int[22];
            mRulesChanged = false;
            mIsRtlCompatibilityMode = false;
            attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RelativeLayout_Layout);
            boolean flag;
            int i;
            int j;
            if(context.getApplicationInfo().targetSdkVersion >= 17)
                flag = context.getApplicationInfo().hasRtlSupport() ^ true;
            else
                flag = true;
            mIsRtlCompatibilityMode = flag;
            ai = mRules;
            context = mInitialRules;
            i = attributeset.getIndexCount();
            j = 0;
            if(j >= i)
                break MISSING_BLOCK_LABEL_687;
            k = attributeset.getIndex(j);
            switch(k)
            {
            default:
                break;

            case 16: // '\020'
                break; /* Loop/switch isn't completed */

            case 0: // '\0'
                ai[0] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 1: // '\001'
                ai[1] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 2: // '\002'
                ai[2] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 3: // '\003'
                ai[3] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 4: // '\004'
                ai[4] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 5: // '\005'
                ai[5] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 6: // '\006'
                ai[6] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 7: // '\007'
                ai[7] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 8: // '\b'
                ai[8] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 9: // '\t'
                if(attributeset.getBoolean(k, false))
                    k = -1;
                else
                    k = 0;
                ai[9] = k;
                continue; /* Loop/switch isn't completed */

            case 10: // '\n'
                if(attributeset.getBoolean(k, false))
                    k = -1;
                else
                    k = 0;
                ai[10] = k;
                continue; /* Loop/switch isn't completed */

            case 11: // '\013'
                if(attributeset.getBoolean(k, false))
                    k = -1;
                else
                    k = 0;
                ai[11] = k;
                continue; /* Loop/switch isn't completed */

            case 12: // '\f'
                if(attributeset.getBoolean(k, false))
                    k = -1;
                else
                    k = 0;
                ai[12] = k;
                continue; /* Loop/switch isn't completed */

            case 13: // '\r'
                if(attributeset.getBoolean(k, false))
                    k = -1;
                else
                    k = 0;
                ai[13] = k;
                continue; /* Loop/switch isn't completed */

            case 14: // '\016'
                if(attributeset.getBoolean(k, false))
                    k = -1;
                else
                    k = 0;
                ai[14] = k;
                continue; /* Loop/switch isn't completed */

            case 15: // '\017'
                if(attributeset.getBoolean(k, false))
                    k = -1;
                else
                    k = 0;
                ai[15] = k;
                continue; /* Loop/switch isn't completed */

            case 17: // '\021'
                ai[16] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 18: // '\022'
                ai[17] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 19: // '\023'
                ai[18] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 20: // '\024'
                ai[19] = attributeset.getResourceId(k, 0);
                continue; /* Loop/switch isn't completed */

            case 21: // '\025'
                if(attributeset.getBoolean(k, false))
                    k = -1;
                else
                    k = 0;
                ai[20] = k;
                continue; /* Loop/switch isn't completed */

            case 22: // '\026'
                break;
            }
            break MISSING_BLOCK_LABEL_658;
_L4:
            j++;
            if(true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_88;
_L1:
            alignWithParent = attributeset.getBoolean(k, false);
            continue; /* Loop/switch isn't completed */
            if(attributeset.getBoolean(k, false))
                k = -1;
            else
                k = 0;
            ai[21] = k;
            if(true) goto _L4; else goto _L3
_L3:
            mRulesChanged = true;
            System.arraycopy(ai, 0, context, 0, 22);
            attributeset.recycle();
            return;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mRules = new int[22];
            mInitialRules = new int[22];
            mRulesChanged = false;
            mIsRtlCompatibilityMode = false;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mRules = new int[22];
            mInitialRules = new int[22];
            mRulesChanged = false;
            mIsRtlCompatibilityMode = false;
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mRules = new int[22];
            mInitialRules = new int[22];
            mRulesChanged = false;
            mIsRtlCompatibilityMode = false;
            mIsRtlCompatibilityMode = layoutparams.mIsRtlCompatibilityMode;
            mRulesChanged = layoutparams.mRulesChanged;
            alignWithParent = layoutparams.alignWithParent;
            System.arraycopy(layoutparams.mRules, 0, mRules, 0, 22);
            System.arraycopy(layoutparams.mInitialRules, 0, mInitialRules, 0, 22);
        }
    }

    private class TopToBottomLeftToRightComparator
        implements Comparator
    {

        public int compare(View view, View view1)
        {
            int i = view.getTop() - view1.getTop();
            if(i != 0)
                return i;
            i = view.getLeft() - view1.getLeft();
            if(i != 0)
                return i;
            i = view.getHeight() - view1.getHeight();
            if(i != 0)
                return i;
            i = view.getWidth() - view1.getWidth();
            if(i != 0)
                return i;
            else
                return 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((View)obj, (View)obj1);
        }

        final RelativeLayout this$0;

        private TopToBottomLeftToRightComparator()
        {
            this$0 = RelativeLayout.this;
            super();
        }

        TopToBottomLeftToRightComparator(TopToBottomLeftToRightComparator toptobottomlefttorightcomparator)
        {
            this();
        }
    }


    public RelativeLayout(Context context)
    {
        this(context, null);
    }

    public RelativeLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public RelativeLayout(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public RelativeLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mBaselineView = null;
        mGravity = 0x800033;
        mContentBounds = new Rect();
        mSelfBounds = new Rect();
        mTopToBottomLeftToRightSet = null;
        mGraph = new DependencyGraph(null);
        mAllowBrokenMeasureSpecs = false;
        mMeasureVerticalWithPaddingMargin = false;
        initFromAttributes(context, attributeset, i, j);
        queryCompatibilityModes(context);
    }

    private void applyHorizontalSizeRules(LayoutParams layoutparams, int i, int ai[])
    {
        LayoutParams._2D_set1(layoutparams, 0x80000000);
        LayoutParams._2D_set2(layoutparams, 0x80000000);
        LayoutParams layoutparams1 = getRelatedViewParams(ai, 0);
        if(layoutparams1 != null)
            LayoutParams._2D_set2(layoutparams, LayoutParams._2D_get1(layoutparams1) - (layoutparams1.leftMargin + layoutparams.rightMargin));
        else
        if(layoutparams.alignWithParent && ai[0] != 0 && i >= 0)
            LayoutParams._2D_set2(layoutparams, i - mPaddingRight - layoutparams.rightMargin);
        layoutparams1 = getRelatedViewParams(ai, 1);
        if(layoutparams1 != null)
            LayoutParams._2D_set1(layoutparams, LayoutParams._2D_get2(layoutparams1) + (layoutparams1.rightMargin + layoutparams.leftMargin));
        else
        if(layoutparams.alignWithParent && ai[1] != 0)
            LayoutParams._2D_set1(layoutparams, mPaddingLeft + layoutparams.leftMargin);
        layoutparams1 = getRelatedViewParams(ai, 5);
        if(layoutparams1 != null)
            LayoutParams._2D_set1(layoutparams, LayoutParams._2D_get1(layoutparams1) + layoutparams.leftMargin);
        else
        if(layoutparams.alignWithParent && ai[5] != 0)
            LayoutParams._2D_set1(layoutparams, mPaddingLeft + layoutparams.leftMargin);
        layoutparams1 = getRelatedViewParams(ai, 7);
        if(layoutparams1 != null)
            LayoutParams._2D_set2(layoutparams, LayoutParams._2D_get2(layoutparams1) - layoutparams.rightMargin);
        else
        if(layoutparams.alignWithParent && ai[7] != 0 && i >= 0)
            LayoutParams._2D_set2(layoutparams, i - mPaddingRight - layoutparams.rightMargin);
        if(ai[9] != 0)
            LayoutParams._2D_set1(layoutparams, mPaddingLeft + layoutparams.leftMargin);
        if(ai[11] != 0 && i >= 0)
            LayoutParams._2D_set2(layoutparams, i - mPaddingRight - layoutparams.rightMargin);
    }

    private void applyVerticalSizeRules(LayoutParams layoutparams, int i, int j)
    {
        int ai[] = layoutparams.getRules();
        int k = getRelatedViewBaselineOffset(ai);
        if(k != -1)
        {
            i = k;
            if(j != -1)
                i = k - j;
            LayoutParams._2D_set3(layoutparams, i);
            LayoutParams._2D_set0(layoutparams, 0x80000000);
            return;
        }
        LayoutParams._2D_set3(layoutparams, 0x80000000);
        LayoutParams._2D_set0(layoutparams, 0x80000000);
        LayoutParams layoutparams1 = getRelatedViewParams(ai, 2);
        if(layoutparams1 != null)
            LayoutParams._2D_set0(layoutparams, LayoutParams._2D_get4(layoutparams1) - (layoutparams1.topMargin + layoutparams.bottomMargin));
        else
        if(layoutparams.alignWithParent && ai[2] != 0 && i >= 0)
            LayoutParams._2D_set0(layoutparams, i - mPaddingBottom - layoutparams.bottomMargin);
        layoutparams1 = getRelatedViewParams(ai, 3);
        if(layoutparams1 != null)
            LayoutParams._2D_set3(layoutparams, LayoutParams._2D_get0(layoutparams1) + (layoutparams1.bottomMargin + layoutparams.topMargin));
        else
        if(layoutparams.alignWithParent && ai[3] != 0)
            LayoutParams._2D_set3(layoutparams, mPaddingTop + layoutparams.topMargin);
        layoutparams1 = getRelatedViewParams(ai, 6);
        if(layoutparams1 != null)
            LayoutParams._2D_set3(layoutparams, LayoutParams._2D_get4(layoutparams1) + layoutparams.topMargin);
        else
        if(layoutparams.alignWithParent && ai[6] != 0)
            LayoutParams._2D_set3(layoutparams, mPaddingTop + layoutparams.topMargin);
        layoutparams1 = getRelatedViewParams(ai, 8);
        if(layoutparams1 != null)
            LayoutParams._2D_set0(layoutparams, LayoutParams._2D_get0(layoutparams1) - layoutparams.bottomMargin);
        else
        if(layoutparams.alignWithParent && ai[8] != 0 && i >= 0)
            LayoutParams._2D_set0(layoutparams, i - mPaddingBottom - layoutparams.bottomMargin);
        if(ai[10] != 0)
            LayoutParams._2D_set3(layoutparams, mPaddingTop + layoutparams.topMargin);
        if(ai[12] != 0 && i >= 0)
            LayoutParams._2D_set0(layoutparams, i - mPaddingBottom - layoutparams.bottomMargin);
    }

    private static void centerHorizontal(View view, LayoutParams layoutparams, int i)
    {
        int j = view.getMeasuredWidth();
        i = (i - j) / 2;
        LayoutParams._2D_set1(layoutparams, i);
        LayoutParams._2D_set2(layoutparams, i + j);
    }

    private static void centerVertical(View view, LayoutParams layoutparams, int i)
    {
        int j = view.getMeasuredHeight();
        i = (i - j) / 2;
        LayoutParams._2D_set3(layoutparams, i);
        LayoutParams._2D_set0(layoutparams, i + j);
    }

    private int compareLayoutPosition(LayoutParams layoutparams, LayoutParams layoutparams1)
    {
        int i = LayoutParams._2D_get4(layoutparams) - LayoutParams._2D_get4(layoutparams1);
        if(i != 0)
            return i;
        else
            return LayoutParams._2D_get1(layoutparams) - LayoutParams._2D_get1(layoutparams1);
    }

    private int getChildMeasureSpec(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag = false;
        flag1 = false;
        if(l1 < 0)
            flag2 = true;
        else
            flag2 = false;
        if(flag2 && mAllowBrokenMeasureSpecs ^ true)
        {
            if(i != 0x80000000 && j != 0x80000000)
            {
                j = Math.max(0, j - i);
                i = 0x40000000;
            } else
            if(k >= 0)
            {
                j = k;
                i = 0x40000000;
            } else
            {
                j = 0;
                i = 0;
            }
            return android.view.View.MeasureSpec.makeMeasureSpec(j, i);
        }
        int i2 = i;
        int j2 = j;
        if(i == 0x80000000)
            i2 = j1 + l;
        if(j == 0x80000000)
            j2 = l1 - k1 - i1;
        l = j2 - i2;
        if(i == 0x80000000 || j == 0x80000000) goto _L2; else goto _L1
_L1:
        if(flag2)
            j = 0;
        else
            j = 0x40000000;
        i = Math.max(0, l);
_L4:
        return android.view.View.MeasureSpec.makeMeasureSpec(i, j);
_L2:
        if(k >= 0)
        {
            j = 0x40000000;
            if(l >= 0)
                i = Math.min(l, k);
            else
                i = k;
        } else
        if(k == -1)
        {
            if(flag2)
                j = 0;
            else
                j = 0x40000000;
            i = Math.max(0, l);
        } else
        {
            j = ((flag) ? 1 : 0);
            i = ((flag1) ? 1 : 0);
            if(k == -2)
                if(l >= 0)
                {
                    j = 0x80000000;
                    i = l;
                } else
                {
                    j = 0;
                    i = 0;
                }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private View getRelatedView(int ai[], int i)
    {
        int j = ai[i];
        if(j != 0)
        {
            ai = (DependencyGraph.Node)DependencyGraph._2D_get0(mGraph).get(j);
            if(ai == null)
                return null;
            DependencyGraph.Node node;
            for(ai = ((DependencyGraph.Node) (ai)).view; ai.getVisibility() == 8; ai = node.view)
            {
                int ai1[] = ((LayoutParams)ai.getLayoutParams()).getRules(ai.getLayoutDirection());
                node = (DependencyGraph.Node)DependencyGraph._2D_get0(mGraph).get(ai1[i]);
                if(node == null || ai == node.view)
                    return null;
            }

            return ai;
        } else
        {
            return null;
        }
    }

    private int getRelatedViewBaselineOffset(int ai[])
    {
        ai = getRelatedView(ai, 4);
        if(ai != null)
        {
            int i = ai.getBaseline();
            if(i != -1 && (ai.getLayoutParams() instanceof LayoutParams))
                return LayoutParams._2D_get4((LayoutParams)ai.getLayoutParams()) + i;
        }
        return -1;
    }

    private LayoutParams getRelatedViewParams(int ai[], int i)
    {
        ai = getRelatedView(ai, i);
        if(ai != null && (ai.getLayoutParams() instanceof LayoutParams))
            return (LayoutParams)ai.getLayoutParams();
        else
            return null;
    }

    private void initFromAttributes(Context context, AttributeSet attributeset, int i, int j)
    {
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RelativeLayout, i, j);
        mIgnoreGravity = context.getResourceId(1, -1);
        mGravity = context.getInt(0, mGravity);
        context.recycle();
    }

    private void measureChild(View view, LayoutParams layoutparams, int i, int j)
    {
        view.measure(getChildMeasureSpec(LayoutParams._2D_get1(layoutparams), LayoutParams._2D_get2(layoutparams), layoutparams.width, layoutparams.leftMargin, layoutparams.rightMargin, mPaddingLeft, mPaddingRight, i), getChildMeasureSpec(LayoutParams._2D_get4(layoutparams), LayoutParams._2D_get0(layoutparams), layoutparams.height, layoutparams.topMargin, layoutparams.bottomMargin, mPaddingTop, mPaddingBottom, j));
    }

    private void measureChildHorizontal(View view, LayoutParams layoutparams, int i, int j)
    {
        int k = getChildMeasureSpec(LayoutParams._2D_get1(layoutparams), LayoutParams._2D_get2(layoutparams), layoutparams.width, layoutparams.leftMargin, layoutparams.rightMargin, mPaddingLeft, mPaddingRight, i);
        if(j < 0 && mAllowBrokenMeasureSpecs ^ true)
        {
            if(layoutparams.height >= 0)
                i = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
            else
                i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        } else
        {
            if(mMeasureVerticalWithPaddingMargin)
                i = Math.max(0, j - mPaddingTop - mPaddingBottom - layoutparams.topMargin - layoutparams.bottomMargin);
            else
                i = Math.max(0, j);
            if(layoutparams.height == -1)
                j = 0x40000000;
            else
                j = 0x80000000;
            i = android.view.View.MeasureSpec.makeMeasureSpec(i, j);
        }
        view.measure(k, i);
    }

    private void positionAtEdge(View view, LayoutParams layoutparams, int i)
    {
        if(isLayoutRtl())
        {
            LayoutParams._2D_set2(layoutparams, i - mPaddingRight - layoutparams.rightMargin);
            LayoutParams._2D_set1(layoutparams, LayoutParams._2D_get2(layoutparams) - view.getMeasuredWidth());
        } else
        {
            LayoutParams._2D_set1(layoutparams, mPaddingLeft + layoutparams.leftMargin);
            LayoutParams._2D_set2(layoutparams, LayoutParams._2D_get1(layoutparams) + view.getMeasuredWidth());
        }
    }

    private boolean positionChildHorizontal(View view, LayoutParams layoutparams, int i, boolean flag)
    {
        boolean flag1 = true;
        int ai[] = layoutparams.getRules(getLayoutDirection());
        if(LayoutParams._2D_get1(layoutparams) == 0x80000000 && LayoutParams._2D_get2(layoutparams) != 0x80000000)
            LayoutParams._2D_set1(layoutparams, LayoutParams._2D_get2(layoutparams) - view.getMeasuredWidth());
        else
        if(LayoutParams._2D_get1(layoutparams) != 0x80000000 && LayoutParams._2D_get2(layoutparams) == 0x80000000)
            LayoutParams._2D_set2(layoutparams, LayoutParams._2D_get1(layoutparams) + view.getMeasuredWidth());
        else
        if(LayoutParams._2D_get1(layoutparams) == 0x80000000 && LayoutParams._2D_get2(layoutparams) == 0x80000000)
        {
            if(ai[13] != 0 || ai[14] != 0)
            {
                if(!flag)
                    centerHorizontal(view, layoutparams, i);
                else
                    positionAtEdge(view, layoutparams, i);
                return true;
            }
            positionAtEdge(view, layoutparams, i);
        }
        if(ai[21] != 0)
            flag = flag1;
        else
            flag = false;
        return flag;
    }

    private boolean positionChildVertical(View view, LayoutParams layoutparams, int i, boolean flag)
    {
        boolean flag1 = true;
        int ai[] = layoutparams.getRules();
        if(LayoutParams._2D_get4(layoutparams) == 0x80000000 && LayoutParams._2D_get0(layoutparams) != 0x80000000)
            LayoutParams._2D_set3(layoutparams, LayoutParams._2D_get0(layoutparams) - view.getMeasuredHeight());
        else
        if(LayoutParams._2D_get4(layoutparams) != 0x80000000 && LayoutParams._2D_get0(layoutparams) == 0x80000000)
            LayoutParams._2D_set0(layoutparams, LayoutParams._2D_get4(layoutparams) + view.getMeasuredHeight());
        else
        if(LayoutParams._2D_get4(layoutparams) == 0x80000000 && LayoutParams._2D_get0(layoutparams) == 0x80000000)
        {
            if(ai[13] != 0 || ai[15] != 0)
            {
                if(!flag)
                {
                    centerVertical(view, layoutparams, i);
                } else
                {
                    LayoutParams._2D_set3(layoutparams, mPaddingTop + layoutparams.topMargin);
                    LayoutParams._2D_set0(layoutparams, LayoutParams._2D_get4(layoutparams) + view.getMeasuredHeight());
                }
                return true;
            }
            LayoutParams._2D_set3(layoutparams, mPaddingTop + layoutparams.topMargin);
            LayoutParams._2D_set0(layoutparams, LayoutParams._2D_get4(layoutparams) + view.getMeasuredHeight());
        }
        if(ai[12] != 0)
            flag = flag1;
        else
            flag = false;
        return flag;
    }

    private void queryCompatibilityModes(Context context)
    {
        boolean flag = true;
        int i = context.getApplicationInfo().targetSdkVersion;
        boolean flag1;
        if(i <= 17)
            flag1 = true;
        else
            flag1 = false;
        mAllowBrokenMeasureSpecs = flag1;
        if(i >= 18)
            flag1 = flag;
        else
            flag1 = false;
        mMeasureVerticalWithPaddingMargin = flag1;
    }

    private void sortChildren()
    {
        int i = getChildCount();
        if(mSortedVerticalChildren == null || mSortedVerticalChildren.length != i)
            mSortedVerticalChildren = new View[i];
        if(mSortedHorizontalChildren == null || mSortedHorizontalChildren.length != i)
            mSortedHorizontalChildren = new View[i];
        DependencyGraph dependencygraph = mGraph;
        dependencygraph.clear();
        for(int j = 0; j < i; j++)
            dependencygraph.add(getChildAt(j));

        dependencygraph.getSortedViews(mSortedVerticalChildren, RULES_VERTICAL);
        dependencygraph.getSortedViews(mSortedHorizontalChildren, RULES_HORIZONTAL);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        if(mTopToBottomLeftToRightSet == null)
            mTopToBottomLeftToRightSet = new TreeSet(new TopToBottomLeftToRightComparator(null));
        int i = 0;
        for(int j = getChildCount(); i < j; i++)
            mTopToBottomLeftToRightSet.add(getChildAt(i));

        for(Iterator iterator = mTopToBottomLeftToRightSet.iterator(); iterator.hasNext();)
        {
            View view = (View)iterator.next();
            if(view.getVisibility() == 0 && view.dispatchPopulateAccessibilityEvent(accessibilityevent))
            {
                mTopToBottomLeftToRightSet.clear();
                return true;
            }
        }

        mTopToBottomLeftToRightSet.clear();
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(sPreserveMarginParamsInLayoutParamConversion)
        {
            if(layoutparams instanceof LayoutParams)
                return new LayoutParams((LayoutParams)layoutparams);
            if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
                return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        }
        return new LayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/RelativeLayout.getName();
    }

    public int getBaseline()
    {
        int i;
        if(mBaselineView != null)
            i = mBaselineView.getBaseline();
        else
            i = super.getBaseline();
        return i;
    }

    public int getGravity()
    {
        return mGravity;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        j = getChildCount();
        for(i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                view.layout(LayoutParams._2D_get1(layoutparams), LayoutParams._2D_get4(layoutparams), LayoutParams._2D_get2(layoutparams), LayoutParams._2D_get0(layoutparams));
            }
        }

    }

    protected void onMeasure(int i, int j)
    {
        int l;
        int k1;
        int i2;
        int j2;
        int l2;
        int j3;
        int k3;
        int i4;
        boolean flag;
        boolean flag1;
        int l4;
        int i5;
        boolean flag2;
        View view3;
        boolean flag4;
        boolean flag5;
        int k5;
        View aview1[];
        int l5;
        int i6;
        int j4;
        boolean flag3;
label0:
        {
            if(mDirtyHierarchy)
            {
                mDirtyHierarchy = false;
                sortChildren();
            }
            int k = -1;
            k1 = -1;
            i2 = 0;
            j2 = 0;
            l2 = android.view.View.MeasureSpec.getMode(i);
            int i3 = android.view.View.MeasureSpec.getMode(j);
            k3 = android.view.View.MeasureSpec.getSize(i);
            int l3 = android.view.View.MeasureSpec.getSize(j);
            if(l2 != 0)
                k = k3;
            if(i3 != 0)
                k1 = l3;
            if(l2 == 0x40000000)
                i2 = k;
            if(i3 == 0x40000000)
                j2 = k1;
            View view = null;
            l3 = mGravity & 0x800007;
            View aview[];
            LayoutParams layoutparams3;
            if(l3 != 0x800003 && l3 != 0)
                flag = true;
            else
                flag = false;
            l3 = mGravity & 0x70;
            if(l3 != 48 && l3 != 0)
                flag1 = true;
            else
                flag1 = false;
            k3 = 0x7fffffff;
            j4 = 0x7fffffff;
            l4 = 0x80000000;
            i5 = 0x80000000;
            flag2 = false;
            flag3 = false;
            if(!flag)
            {
                view3 = view;
                if(!flag1)
                    break label0;
            }
            view3 = view;
            if(mIgnoreGravity != -1)
                view3 = findViewById(mIgnoreGravity);
        }
        if(l2 != 0x40000000)
            flag4 = true;
        else
            flag4 = false;
        if(i3 != 0x40000000)
            flag5 = true;
        else
            flag5 = false;
        k5 = getLayoutDirection();
        l2 = k;
        if(isLayoutRtl())
        {
            l2 = k;
            if(k == -1)
                l2 = 0x10000;
        }
        aview = mSortedHorizontalChildren;
        i3 = aview.length;
        for(k = 0; k < i3;)
        {
            view = aview[k];
            l3 = ((flag2) ? 1 : 0);
            if(view.getVisibility() != 8)
            {
                layoutparams3 = (LayoutParams)view.getLayoutParams();
                applyHorizontalSizeRules(layoutparams3, l2, layoutparams3.getRules(k5));
                measureChildHorizontal(view, layoutparams3, l2, k1);
                l3 = ((flag2) ? 1 : 0);
                if(positionChildHorizontal(view, layoutparams3, l2, flag4))
                    l3 = 1;
            }
            k++;
            flag2 = l3;
        }

        aview1 = mSortedVerticalChildren;
        l5 = aview1.length;
        i6 = getContext().getApplicationInfo().targetSdkVersion;
        j3 = 0;
        i4 = j4;
        l = ((flag3) ? 1 : 0);
_L9:
        View view4;
        LayoutParams layoutparams4;
label1:
        {
label2:
            {
label3:
                {
                    if(j3 >= l5)
                        break label1;
                    View view1 = aview1[j3];
                    int j6 = i5;
                    int k6 = j2;
                    int l6 = k3;
                    int i7 = l;
                    int j7 = l4;
                    int k7 = i4;
                    int l7 = i2;
                    if(view1.getVisibility() == 8)
                        break label2;
                    LayoutParams layoutparams2 = (LayoutParams)view1.getLayoutParams();
                    applyVerticalSizeRules(layoutparams2, k1, view1.getBaseline());
                    measureChild(view1, layoutparams2, l2, k1);
                    int j5 = l;
                    if(positionChildVertical(view1, layoutparams2, k1, flag5))
                        j5 = 1;
                    l = i2;
                    int k4;
                    if(flag4)
                        if(isLayoutRtl())
                        {
                            if(i6 < 19)
                                l = Math.max(i2, l2 - LayoutParams._2D_get1(layoutparams2));
                            else
                                l = Math.max(i2, (l2 - LayoutParams._2D_get1(layoutparams2)) + layoutparams2.leftMargin);
                        } else
                        if(i6 < 19)
                            l = Math.max(i2, LayoutParams._2D_get2(layoutparams2));
                        else
                            l = Math.max(i2, LayoutParams._2D_get2(layoutparams2) + layoutparams2.rightMargin);
                    i2 = j2;
                    if(flag5)
                        if(i6 < 19)
                            i2 = Math.max(j2, LayoutParams._2D_get0(layoutparams2));
                        else
                            i2 = Math.max(j2, LayoutParams._2D_get0(layoutparams2) + layoutparams2.bottomMargin);
                    if(view1 == view3)
                    {
                        k4 = k3;
                        j2 = i4;
                        if(!flag1)
                            break label3;
                    }
                    k4 = Math.min(k3, LayoutParams._2D_get1(layoutparams2) - layoutparams2.leftMargin);
                    j2 = Math.min(i4, LayoutParams._2D_get4(layoutparams2) - layoutparams2.topMargin);
                }
                if(view1 == view3)
                {
                    j6 = i5;
                    k6 = i2;
                    l6 = k4;
                    i7 = j5;
                    j7 = l4;
                    k7 = j2;
                    l7 = l;
                    if(!flag)
                        break label2;
                }
                j7 = Math.max(l4, LayoutParams._2D_get2(layoutparams2) + layoutparams2.rightMargin);
                j6 = Math.max(i5, LayoutParams._2D_get0(layoutparams2) + layoutparams2.bottomMargin);
                l7 = l;
                k7 = j2;
                i7 = j5;
                l6 = k4;
                k6 = i2;
            }
            j3++;
            i5 = j6;
            j2 = k6;
            k3 = l6;
            l = i7;
            l4 = j7;
            i4 = k7;
            i2 = l7;
            continue; /* Loop/switch isn't completed */
        }
        view4 = null;
        layoutparams4 = null;
        k1 = 0;
_L7:
        if(k1 >= l5) goto _L2; else goto _L1
_L1:
        View view2;
        View view7;
        LayoutParams layoutparams5;
        view7 = aview1[k1];
        layoutparams5 = layoutparams4;
        view2 = view4;
        if(view7.getVisibility() == 8) goto _L4; else goto _L3
_L3:
        LayoutParams layoutparams6 = (LayoutParams)view7.getLayoutParams();
        if(view4 != null && layoutparams4 != null) goto _L6; else goto _L5
_L5:
        view2 = view7;
        layoutparams5 = layoutparams6;
_L4:
        k1++;
        layoutparams4 = layoutparams5;
        view4 = view2;
          goto _L7
_L6:
        layoutparams5 = layoutparams4;
        view2 = view4;
        if(compareLayoutPosition(layoutparams6, layoutparams4) >= 0) goto _L4; else goto _L5
_L2:
        mBaselineView = view4;
        int l1 = i2;
        if(flag4)
        {
            l1 = i2 + mPaddingRight;
            i2 = l1;
            if(mLayoutParams != null)
            {
                i2 = l1;
                if(mLayoutParams.width >= 0)
                    i2 = Math.max(l1, mLayoutParams.width);
            }
            i2 = resolveSize(Math.max(i2, getSuggestedMinimumWidth()), i);
            l1 = i2;
            if(flag2)
            {
                i = 0;
                do
                {
                    l1 = i2;
                    if(i >= l5)
                        break;
                    View view6 = aview1[i];
                    if(view6.getVisibility() != 8)
                    {
                        LayoutParams layoutparams = (LayoutParams)view6.getLayoutParams();
                        int ai[] = layoutparams.getRules(k5);
                        if(ai[13] != 0 || ai[14] != 0)
                            centerHorizontal(view6, layoutparams, i2);
                        else
                        if(ai[11] != 0)
                        {
                            l1 = view6.getMeasuredWidth();
                            LayoutParams._2D_set1(layoutparams, i2 - mPaddingRight - l1);
                            LayoutParams._2D_set2(layoutparams, LayoutParams._2D_get1(layoutparams) + l1);
                        }
                    }
                    i++;
                } while(true);
            }
        }
        i = j2;
        if(flag5)
        {
            j2 += mPaddingBottom;
            i = j2;
            if(mLayoutParams != null)
            {
                i = j2;
                if(mLayoutParams.height >= 0)
                    i = Math.max(j2, mLayoutParams.height);
            }
            j2 = resolveSize(Math.max(i, getSuggestedMinimumHeight()), j);
            i = j2;
            if(l != 0)
            {
                j = 0;
                do
                {
                    i = j2;
                    if(j >= l5)
                        break;
                    View view5 = aview1[j];
                    if(view5.getVisibility() != 8)
                    {
                        LayoutParams layoutparams1 = (LayoutParams)view5.getLayoutParams();
                        int ai1[] = layoutparams1.getRules(k5);
                        if(ai1[13] != 0 || ai1[15] != 0)
                            centerVertical(view5, layoutparams1, j2);
                        else
                        if(ai1[12] != 0)
                        {
                            i = view5.getMeasuredHeight();
                            LayoutParams._2D_set3(layoutparams1, j2 - mPaddingBottom - i);
                            LayoutParams._2D_set0(layoutparams1, LayoutParams._2D_get4(layoutparams1) + i);
                        }
                    }
                    j++;
                } while(true);
            }
        }
        if(flag || flag1)
        {
            Rect rect1 = mSelfBounds;
            rect1.set(mPaddingLeft, mPaddingTop, l1 - mPaddingRight, i - mPaddingBottom);
            Rect rect = mContentBounds;
            Gravity.apply(mGravity, l4 - k3, i5 - i4, rect1, rect, k5);
            int i1 = rect.left - k3;
            int k2 = rect.top - i4;
            if(i1 != 0 || k2 != 0)
                for(j = 0; j < l5; j++)
                {
                    Object obj = aview1[j];
                    if(((View) (obj)).getVisibility() == 8 || obj == view3)
                        continue;
                    obj = (LayoutParams)((View) (obj)).getLayoutParams();
                    if(flag)
                    {
                        LayoutParams._2D_set1(((LayoutParams) (obj)), LayoutParams._2D_get1(((LayoutParams) (obj))) + i1);
                        LayoutParams._2D_set2(((LayoutParams) (obj)), LayoutParams._2D_get2(((LayoutParams) (obj))) + i1);
                    }
                    if(flag1)
                    {
                        LayoutParams._2D_set3(((LayoutParams) (obj)), LayoutParams._2D_get4(((LayoutParams) (obj))) + k2);
                        LayoutParams._2D_set0(((LayoutParams) (obj)), LayoutParams._2D_get0(((LayoutParams) (obj))) + k2);
                    }
                }

        }
        if(isLayoutRtl())
        {
            int j1 = l2 - l1;
            for(j = 0; j < l5; j++)
            {
                Object obj1 = aview1[j];
                if(((View) (obj1)).getVisibility() != 8)
                {
                    obj1 = (LayoutParams)((View) (obj1)).getLayoutParams();
                    LayoutParams._2D_set1(((LayoutParams) (obj1)), LayoutParams._2D_get1(((LayoutParams) (obj1))) - j1);
                    LayoutParams._2D_set2(((LayoutParams) (obj1)), LayoutParams._2D_get2(((LayoutParams) (obj1))) - j1);
                }
            }

        }
        setMeasuredDimension(l1, i);
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public void requestLayout()
    {
        super.requestLayout();
        mDirtyHierarchy = true;
    }

    public void setGravity(int i)
    {
        if(mGravity != i)
        {
            int j = i;
            if((0x800007 & i) == 0)
                j = i | 0x800003;
            i = j;
            if((j & 0x70) == 0)
                i = j | 0x30;
            mGravity = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i)
    {
        i &= 0x800007;
        if((mGravity & 0x800007) != i)
        {
            mGravity = mGravity & 0xff7ffff8 | i;
            requestLayout();
        }
    }

    public void setIgnoreGravity(int i)
    {
        mIgnoreGravity = i;
    }

    public void setVerticalGravity(int i)
    {
        i &= 0x70;
        if((mGravity & 0x70) != i)
        {
            mGravity = mGravity & 0xffffff8f | i;
            requestLayout();
        }
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    public static final int ABOVE = 2;
    public static final int ALIGN_BASELINE = 4;
    public static final int ALIGN_BOTTOM = 8;
    public static final int ALIGN_END = 19;
    public static final int ALIGN_LEFT = 5;
    public static final int ALIGN_PARENT_BOTTOM = 12;
    public static final int ALIGN_PARENT_END = 21;
    public static final int ALIGN_PARENT_LEFT = 9;
    public static final int ALIGN_PARENT_RIGHT = 11;
    public static final int ALIGN_PARENT_START = 20;
    public static final int ALIGN_PARENT_TOP = 10;
    public static final int ALIGN_RIGHT = 7;
    public static final int ALIGN_START = 18;
    public static final int ALIGN_TOP = 6;
    public static final int BELOW = 3;
    public static final int CENTER_HORIZONTAL = 14;
    public static final int CENTER_IN_PARENT = 13;
    public static final int CENTER_VERTICAL = 15;
    private static final int DEFAULT_WIDTH = 0x10000;
    public static final int END_OF = 17;
    public static final int LEFT_OF = 0;
    public static final int RIGHT_OF = 1;
    private static final int RULES_HORIZONTAL[] = {
        0, 1, 5, 7, 16, 17, 18, 19
    };
    private static final int RULES_VERTICAL[] = {
        2, 3, 4, 6, 8
    };
    public static final int START_OF = 16;
    public static final int TRUE = -1;
    private static final int VALUE_NOT_SET = 0x80000000;
    private static final int VERB_COUNT = 22;
    private boolean mAllowBrokenMeasureSpecs;
    private View mBaselineView;
    private final Rect mContentBounds;
    private boolean mDirtyHierarchy;
    private final DependencyGraph mGraph;
    private int mGravity;
    private int mIgnoreGravity;
    private boolean mMeasureVerticalWithPaddingMargin;
    private final Rect mSelfBounds;
    private View mSortedHorizontalChildren[];
    private View mSortedVerticalChildren[];
    private SortedSet mTopToBottomLeftToRightSet;

}
