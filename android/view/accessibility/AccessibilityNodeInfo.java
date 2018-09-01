// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.graphics.Rect;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.ArraySet;
import android.util.LongArray;
import android.view.View;
import com.android.internal.util.BitUtils;
import com.android.internal.util.CollectionUtils;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.view.accessibility:
//            AccessibilityInteractionClient, AccessibilityWindowInfo

public class AccessibilityNodeInfo
    implements Parcelable
{
    public static final class AccessibilityAction
    {

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(obj == null)
                return false;
            if(obj == this)
                return true;
            if(getClass() != obj.getClass())
                return false;
            if(mActionId != ((AccessibilityAction)obj).mActionId)
                flag = false;
            return flag;
        }

        public int getId()
        {
            return mActionId;
        }

        public CharSequence getLabel()
        {
            return mLabel;
        }

        public int hashCode()
        {
            return mActionId;
        }

        public String toString()
        {
            return (new StringBuilder()).append("AccessibilityAction: ").append(AccessibilityNodeInfo._2D_wrap0(mActionId)).append(" - ").append(mLabel).toString();
        }

        public static final AccessibilityAction ACTION_ACCESSIBILITY_FOCUS = new AccessibilityAction(64);
        public static final AccessibilityAction ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityAction(128);
        public static final AccessibilityAction ACTION_CLEAR_FOCUS = new AccessibilityAction(2);
        public static final AccessibilityAction ACTION_CLEAR_SELECTION = new AccessibilityAction(8);
        public static final AccessibilityAction ACTION_CLICK = new AccessibilityAction(16);
        public static final AccessibilityAction ACTION_COLLAPSE = new AccessibilityAction(0x80000);
        public static final AccessibilityAction ACTION_CONTEXT_CLICK = new AccessibilityAction(0x102003c);
        public static final AccessibilityAction ACTION_COPY = new AccessibilityAction(16384);
        public static final AccessibilityAction ACTION_CUT = new AccessibilityAction(0x10000);
        public static final AccessibilityAction ACTION_DISMISS = new AccessibilityAction(0x100000);
        public static final AccessibilityAction ACTION_EXPAND = new AccessibilityAction(0x40000);
        public static final AccessibilityAction ACTION_FOCUS = new AccessibilityAction(1);
        public static final AccessibilityAction ACTION_LONG_CLICK = new AccessibilityAction(32);
        public static final AccessibilityAction ACTION_MOVE_WINDOW = new AccessibilityAction(0x1020042);
        public static final AccessibilityAction ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityAction(256);
        public static final AccessibilityAction ACTION_NEXT_HTML_ELEMENT = new AccessibilityAction(1024);
        public static final AccessibilityAction ACTION_PASTE = new AccessibilityAction(32768);
        public static final AccessibilityAction ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityAction(512);
        public static final AccessibilityAction ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityAction(2048);
        public static final AccessibilityAction ACTION_SCROLL_BACKWARD = new AccessibilityAction(8192);
        public static final AccessibilityAction ACTION_SCROLL_DOWN = new AccessibilityAction(0x102003a);
        public static final AccessibilityAction ACTION_SCROLL_FORWARD = new AccessibilityAction(4096);
        public static final AccessibilityAction ACTION_SCROLL_LEFT = new AccessibilityAction(0x1020039);
        public static final AccessibilityAction ACTION_SCROLL_RIGHT = new AccessibilityAction(0x102003b);
        public static final AccessibilityAction ACTION_SCROLL_TO_POSITION = new AccessibilityAction(0x1020037);
        public static final AccessibilityAction ACTION_SCROLL_UP = new AccessibilityAction(0x1020038);
        public static final AccessibilityAction ACTION_SELECT = new AccessibilityAction(4);
        public static final AccessibilityAction ACTION_SET_PROGRESS = new AccessibilityAction(0x102003d);
        public static final AccessibilityAction ACTION_SET_SELECTION = new AccessibilityAction(0x20000);
        public static final AccessibilityAction ACTION_SET_TEXT = new AccessibilityAction(0x200000);
        public static final AccessibilityAction ACTION_SHOW_ON_SCREEN = new AccessibilityAction(0x1020036);
        public static final ArraySet sStandardActions = new ArraySet();
        private final int mActionId;
        private final CharSequence mLabel;
        public int mSerializationFlag;


        private AccessibilityAction(int i)
        {
            this(i, null);
            mSerializationFlag = (int)BitUtils.bitAt(sStandardActions.size());
            sStandardActions.add(this);
        }

        public AccessibilityAction(int i, CharSequence charsequence)
        {
            mSerializationFlag = -1;
            if((0xff000000 & i) == 0 && Integer.bitCount(i) != 1)
            {
                throw new IllegalArgumentException("Invalid standard action id");
            } else
            {
                mActionId = i;
                mLabel = charsequence;
                return;
            }
        }
    }

    public static final class CollectionInfo
    {

        private void clear()
        {
            mRowCount = 0;
            mColumnCount = 0;
            mHierarchical = false;
            mSelectionMode = 0;
        }

        public static CollectionInfo obtain(int i, int j, boolean flag)
        {
            return obtain(i, j, flag, 0);
        }

        public static CollectionInfo obtain(int i, int j, boolean flag, int k)
        {
            CollectionInfo collectioninfo = (CollectionInfo)sPool.acquire();
            if(collectioninfo == null)
            {
                return new CollectionInfo(i, j, flag, k);
            } else
            {
                collectioninfo.mRowCount = i;
                collectioninfo.mColumnCount = j;
                collectioninfo.mHierarchical = flag;
                collectioninfo.mSelectionMode = k;
                return collectioninfo;
            }
        }

        public static CollectionInfo obtain(CollectionInfo collectioninfo)
        {
            return obtain(collectioninfo.mRowCount, collectioninfo.mColumnCount, collectioninfo.mHierarchical, collectioninfo.mSelectionMode);
        }

        public int getColumnCount()
        {
            return mColumnCount;
        }

        public int getRowCount()
        {
            return mRowCount;
        }

        public int getSelectionMode()
        {
            return mSelectionMode;
        }

        public boolean isHierarchical()
        {
            return mHierarchical;
        }

        void recycle()
        {
            clear();
            sPool.release(this);
        }

        private static final int MAX_POOL_SIZE = 20;
        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(20);
        private int mColumnCount;
        private boolean mHierarchical;
        private int mRowCount;
        private int mSelectionMode;


        private CollectionInfo(int i, int j, boolean flag, int k)
        {
            mRowCount = i;
            mColumnCount = j;
            mHierarchical = flag;
            mSelectionMode = k;
        }
    }

    public static final class CollectionItemInfo
    {

        private void clear()
        {
            mColumnIndex = 0;
            mColumnSpan = 0;
            mRowIndex = 0;
            mRowSpan = 0;
            mHeading = false;
            mSelected = false;
        }

        public static CollectionItemInfo obtain(int i, int j, int k, int l, boolean flag)
        {
            return obtain(i, j, k, l, flag, false);
        }

        public static CollectionItemInfo obtain(int i, int j, int k, int l, boolean flag, boolean flag1)
        {
            CollectionItemInfo collectioniteminfo = (CollectionItemInfo)sPool.acquire();
            if(collectioniteminfo == null)
            {
                return new CollectionItemInfo(i, j, k, l, flag, flag1);
            } else
            {
                collectioniteminfo.mRowIndex = i;
                collectioniteminfo.mRowSpan = j;
                collectioniteminfo.mColumnIndex = k;
                collectioniteminfo.mColumnSpan = l;
                collectioniteminfo.mHeading = flag;
                collectioniteminfo.mSelected = flag1;
                return collectioniteminfo;
            }
        }

        public static CollectionItemInfo obtain(CollectionItemInfo collectioniteminfo)
        {
            return obtain(collectioniteminfo.mRowIndex, collectioniteminfo.mRowSpan, collectioniteminfo.mColumnIndex, collectioniteminfo.mColumnSpan, collectioniteminfo.mHeading, collectioniteminfo.mSelected);
        }

        public int getColumnIndex()
        {
            return mColumnIndex;
        }

        public int getColumnSpan()
        {
            return mColumnSpan;
        }

        public int getRowIndex()
        {
            return mRowIndex;
        }

        public int getRowSpan()
        {
            return mRowSpan;
        }

        public boolean isHeading()
        {
            return mHeading;
        }

        public boolean isSelected()
        {
            return mSelected;
        }

        void recycle()
        {
            clear();
            sPool.release(this);
        }

        private static final int MAX_POOL_SIZE = 20;
        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(20);
        private int mColumnIndex;
        private int mColumnSpan;
        private boolean mHeading;
        private int mRowIndex;
        private int mRowSpan;
        private boolean mSelected;


        private CollectionItemInfo(int i, int j, int k, int l, boolean flag, boolean flag1)
        {
            mRowIndex = i;
            mRowSpan = j;
            mColumnIndex = k;
            mColumnSpan = l;
            mHeading = flag;
            mSelected = flag1;
        }
    }

    public static final class RangeInfo
    {

        private void clear()
        {
            mType = 0;
            mMin = 0.0F;
            mMax = 0.0F;
            mCurrent = 0.0F;
        }

        public static RangeInfo obtain(int i, float f, float f1, float f2)
        {
            RangeInfo rangeinfo = (RangeInfo)sPool.acquire();
            if(rangeinfo == null)
            {
                return new RangeInfo(i, f, f1, f2);
            } else
            {
                rangeinfo.mType = i;
                rangeinfo.mMin = f;
                rangeinfo.mMax = f1;
                rangeinfo.mCurrent = f2;
                return rangeinfo;
            }
        }

        public static RangeInfo obtain(RangeInfo rangeinfo)
        {
            return obtain(rangeinfo.mType, rangeinfo.mMin, rangeinfo.mMax, rangeinfo.mCurrent);
        }

        public float getCurrent()
        {
            return mCurrent;
        }

        public float getMax()
        {
            return mMax;
        }

        public float getMin()
        {
            return mMin;
        }

        public int getType()
        {
            return mType;
        }

        void recycle()
        {
            clear();
            sPool.release(this);
        }

        private static final int MAX_POOL_SIZE = 10;
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(10);
        private float mCurrent;
        private float mMax;
        private float mMin;
        private int mType;


        private RangeInfo(int i, float f, float f1, float f2)
        {
            mType = i;
            mMin = f;
            mMax = f1;
            mCurrent = f2;
        }
    }


    static String _2D_wrap0(int i)
    {
        return getActionSymbolicName(i);
    }

    static void _2D_wrap1(AccessibilityNodeInfo accessibilitynodeinfo, Parcel parcel)
    {
        accessibilitynodeinfo.initFromParcel(parcel);
    }

    private AccessibilityNodeInfo()
    {
        mWindowId = -1;
        mSourceNodeId = UNDEFINED_NODE_ID;
        mParentNodeId = UNDEFINED_NODE_ID;
        mLabelForId = UNDEFINED_NODE_ID;
        mLabeledById = UNDEFINED_NODE_ID;
        mTraversalBefore = UNDEFINED_NODE_ID;
        mTraversalAfter = UNDEFINED_NODE_ID;
        mMaxTextLength = -1;
        mTextSelectionStart = -1;
        mTextSelectionEnd = -1;
        mInputType = 0;
        mLiveRegion = 0;
        mConnectionId = -1;
    }

    private void addActionUnchecked(AccessibilityAction accessibilityaction)
    {
        if(accessibilityaction == null)
            return;
        if(mActions == null)
            mActions = new ArrayList();
        mActions.remove(accessibilityaction);
        mActions.add(accessibilityaction);
    }

    private void addChildInternal(View view, int i, boolean flag)
    {
        enforceNotSealed();
        if(mChildNodeIds == null)
            mChildNodeIds = new LongArray();
        int j;
        long l;
        if(view != null)
            j = view.getAccessibilityViewId();
        else
            j = 0x7fffffff;
        l = makeNodeId(j, i);
        if(flag && mChildNodeIds.indexOf(l) >= 0)
        {
            return;
        } else
        {
            mChildNodeIds.add(l);
            return;
        }
    }

    private void addStandardActions(int i)
    {
        while(i > 0) 
        {
            int j = 1 << Integer.numberOfTrailingZeros(i);
            i &= j;
            addAction(getActionSingletonBySerializationFlag(j));
        }
    }

    private boolean canPerformRequestOverConnection(long l)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mWindowId != -1)
        {
            flag1 = flag;
            if(getAccessibilityViewId(l) != 0x7fffffff)
            {
                flag1 = flag;
                if(mConnectionId != -1)
                    flag1 = true;
            }
        }
        return flag1;
    }

    private void clear()
    {
        init(DEFAULT);
    }

    private void enforceValidFocusDirection(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown direction: ").append(i).toString());

        case 1: // '\001'
        case 2: // '\002'
        case 17: // '\021'
        case 33: // '!'
        case 66: // 'B'
        case 130: 
            return;
        }
    }

    private void enforceValidFocusType(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown focus type: ").append(i).toString());

        case 1: // '\001'
        case 2: // '\002'
            return;
        }
    }

    public static int getAccessibilityViewId(long l)
    {
        return (int)l;
    }

    private static AccessibilityAction getActionSingleton(int i)
    {
        int j = AccessibilityAction.sStandardActions.size();
        for(int k = 0; k < j; k++)
        {
            AccessibilityAction accessibilityaction = (AccessibilityAction)AccessibilityAction.sStandardActions.valueAt(k);
            if(i == accessibilityaction.getId())
                return accessibilityaction;
        }

        return null;
    }

    private static AccessibilityAction getActionSingletonBySerializationFlag(int i)
    {
        int j = AccessibilityAction.sStandardActions.size();
        for(int k = 0; k < j; k++)
        {
            AccessibilityAction accessibilityaction = (AccessibilityAction)AccessibilityAction.sStandardActions.valueAt(k);
            if(i == accessibilityaction.mSerializationFlag)
                return accessibilityaction;
        }

        return null;
    }

    private static String getActionSymbolicName(int i)
    {
        switch(i)
        {
        default:
            return "ACTION_UNKNOWN";

        case 1: // '\001'
            return "ACTION_FOCUS";

        case 2: // '\002'
            return "ACTION_CLEAR_FOCUS";

        case 4: // '\004'
            return "ACTION_SELECT";

        case 8: // '\b'
            return "ACTION_CLEAR_SELECTION";

        case 16: // '\020'
            return "ACTION_CLICK";

        case 32: // ' '
            return "ACTION_LONG_CLICK";

        case 64: // '@'
            return "ACTION_ACCESSIBILITY_FOCUS";

        case 128: 
            return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";

        case 256: 
            return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";

        case 512: 
            return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";

        case 1024: 
            return "ACTION_NEXT_HTML_ELEMENT";

        case 2048: 
            return "ACTION_PREVIOUS_HTML_ELEMENT";

        case 4096: 
            return "ACTION_SCROLL_FORWARD";

        case 8192: 
            return "ACTION_SCROLL_BACKWARD";

        case 65536: 
            return "ACTION_CUT";

        case 16384: 
            return "ACTION_COPY";

        case 32768: 
            return "ACTION_PASTE";

        case 131072: 
            return "ACTION_SET_SELECTION";

        case 262144: 
            return "ACTION_EXPAND";

        case 524288: 
            return "ACTION_COLLAPSE";

        case 1048576: 
            return "ACTION_DISMISS";

        case 2097152: 
            return "ACTION_SET_TEXT";

        case 16908342: 
            return "ACTION_SHOW_ON_SCREEN";

        case 16908343: 
            return "ACTION_SCROLL_TO_POSITION";

        case 16908344: 
            return "ACTION_SCROLL_UP";

        case 16908345: 
            return "ACTION_SCROLL_LEFT";

        case 16908346: 
            return "ACTION_SCROLL_DOWN";

        case 16908347: 
            return "ACTION_SCROLL_RIGHT";

        case 16908349: 
            return "ACTION_SET_PROGRESS";

        case 16908348: 
            return "ACTION_CONTEXT_CLICK";
        }
    }

    private boolean getBooleanProperty(int i)
    {
        boolean flag = false;
        if((mBooleanProperties & i) != 0)
            flag = true;
        return flag;
    }

    private static String getMovementGranularitySymbolicName(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown movement granularity: ").append(i).toString());

        case 1: // '\001'
            return "MOVEMENT_GRANULARITY_CHARACTER";

        case 2: // '\002'
            return "MOVEMENT_GRANULARITY_WORD";

        case 4: // '\004'
            return "MOVEMENT_GRANULARITY_LINE";

        case 8: // '\b'
            return "MOVEMENT_GRANULARITY_PARAGRAPH";

        case 16: // '\020'
            return "MOVEMENT_GRANULARITY_PAGE";
        }
    }

    private AccessibilityNodeInfo getNodeForAccessibilityId(long l)
    {
        if(!canPerformRequestOverConnection(l))
            return null;
        else
            return AccessibilityInteractionClient.getInstance().findAccessibilityNodeInfoByAccessibilityId(mConnectionId, mWindowId, l, false, 7, null);
    }

    public static int getVirtualDescendantId(long l)
    {
        return (int)((0xffffffff00000000L & l) >> 32);
    }

    private void init(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        Object obj = null;
        mSealed = accessibilitynodeinfo.mSealed;
        mSourceNodeId = accessibilitynodeinfo.mSourceNodeId;
        mParentNodeId = accessibilitynodeinfo.mParentNodeId;
        mLabelForId = accessibilitynodeinfo.mLabelForId;
        mLabeledById = accessibilitynodeinfo.mLabeledById;
        mTraversalBefore = accessibilitynodeinfo.mTraversalBefore;
        mTraversalAfter = accessibilitynodeinfo.mTraversalAfter;
        mWindowId = accessibilitynodeinfo.mWindowId;
        mConnectionId = accessibilitynodeinfo.mConnectionId;
        mBoundsInParent.set(accessibilitynodeinfo.mBoundsInParent);
        mBoundsInScreen.set(accessibilitynodeinfo.mBoundsInScreen);
        mPackageName = accessibilitynodeinfo.mPackageName;
        mClassName = accessibilitynodeinfo.mClassName;
        mText = accessibilitynodeinfo.mText;
        mHintText = accessibilitynodeinfo.mHintText;
        mError = accessibilitynodeinfo.mError;
        mContentDescription = accessibilitynodeinfo.mContentDescription;
        mViewIdResourceName = accessibilitynodeinfo.mViewIdResourceName;
        if(mActions != null)
            mActions.clear();
        Object obj1 = accessibilitynodeinfo.mActions;
        if(obj1 != null && ((ArrayList) (obj1)).size() > 0)
            if(mActions == null)
                mActions = new ArrayList(((java.util.Collection) (obj1)));
            else
                mActions.addAll(accessibilitynodeinfo.mActions);
        mBooleanProperties = accessibilitynodeinfo.mBooleanProperties;
        mMaxTextLength = accessibilitynodeinfo.mMaxTextLength;
        mMovementGranularities = accessibilitynodeinfo.mMovementGranularities;
        if(mChildNodeIds != null)
            mChildNodeIds.clear();
        obj1 = accessibilitynodeinfo.mChildNodeIds;
        if(obj1 != null && ((LongArray) (obj1)).size() > 0)
            if(mChildNodeIds == null)
                mChildNodeIds = ((LongArray) (obj1)).clone();
            else
                mChildNodeIds.addAll(((LongArray) (obj1)));
        mTextSelectionStart = accessibilitynodeinfo.mTextSelectionStart;
        mTextSelectionEnd = accessibilitynodeinfo.mTextSelectionEnd;
        mInputType = accessibilitynodeinfo.mInputType;
        mLiveRegion = accessibilitynodeinfo.mLiveRegion;
        mDrawingOrderInParent = accessibilitynodeinfo.mDrawingOrderInParent;
        mExtraDataKeys = accessibilitynodeinfo.mExtraDataKeys;
        if(accessibilitynodeinfo.mExtras != null)
            obj1 = new Bundle(accessibilitynodeinfo.mExtras);
        else
            obj1 = null;
        mExtras = ((Bundle) (obj1));
        if(mRangeInfo != null)
            mRangeInfo.recycle();
        if(accessibilitynodeinfo.mRangeInfo != null)
            obj1 = RangeInfo.obtain(accessibilitynodeinfo.mRangeInfo);
        else
            obj1 = null;
        mRangeInfo = ((RangeInfo) (obj1));
        if(mCollectionInfo != null)
            mCollectionInfo.recycle();
        if(accessibilitynodeinfo.mCollectionInfo != null)
            obj1 = CollectionInfo.obtain(accessibilitynodeinfo.mCollectionInfo);
        else
            obj1 = null;
        mCollectionInfo = ((CollectionInfo) (obj1));
        if(mCollectionItemInfo != null)
            mCollectionItemInfo.recycle();
        obj1 = obj;
        if(accessibilitynodeinfo.mCollectionItemInfo != null)
            obj1 = CollectionItemInfo.obtain(accessibilitynodeinfo.mCollectionItemInfo);
        mCollectionItemInfo = ((CollectionItemInfo) (obj1));
    }

    private void initFromParcel(Parcel parcel)
    {
        long l = parcel.readLong();
        boolean flag;
        int i;
        int k;
        int i1;
        if(BitUtils.isBitSet(l, 0))
        {
            if(parcel.readInt() == 1)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = DEFAULT.mSealed;
        }
        i = 1 + 1;
        if(BitUtils.isBitSet(l, 1))
            mSourceNodeId = parcel.readLong();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mWindowId = parcel.readInt();
        i = k + 1;
        if(BitUtils.isBitSet(l, k))
            mParentNodeId = parcel.readLong();
        i1 = i + 1;
        if(BitUtils.isBitSet(l, i))
            mLabelForId = parcel.readLong();
        k = i1 + 1;
        if(BitUtils.isBitSet(l, i1))
            mLabeledById = parcel.readLong();
        i1 = k + 1;
        if(BitUtils.isBitSet(l, k))
            mTraversalBefore = parcel.readLong();
        i = i1 + 1;
        if(BitUtils.isBitSet(l, i1))
            mTraversalAfter = parcel.readLong();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mConnectionId = parcel.readInt();
        i = k + 1;
        if(BitUtils.isBitSet(l, k))
        {
            i1 = parcel.readInt();
            if(i1 <= 0)
            {
                mChildNodeIds = null;
            } else
            {
                mChildNodeIds = new LongArray(i1);
                k = 0;
                while(k < i1) 
                {
                    long l1 = parcel.readLong();
                    mChildNodeIds.add(l1);
                    k++;
                }
            }
        }
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
        {
            mBoundsInParent.top = parcel.readInt();
            mBoundsInParent.bottom = parcel.readInt();
            mBoundsInParent.left = parcel.readInt();
            mBoundsInParent.right = parcel.readInt();
        }
        i1 = k + 1;
        if(BitUtils.isBitSet(l, k))
        {
            mBoundsInScreen.top = parcel.readInt();
            mBoundsInScreen.bottom = parcel.readInt();
            mBoundsInScreen.left = parcel.readInt();
            mBoundsInScreen.right = parcel.readInt();
        }
        i = i1 + 1;
        if(BitUtils.isBitSet(l, i1))
        {
            addStandardActions(parcel.readInt());
            i1 = parcel.readInt();
            for(k = 0; k < i1; k++)
                addActionUnchecked(new AccessibilityAction(parcel.readInt(), parcel.readCharSequence()));

        }
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mMaxTextLength = parcel.readInt();
        i1 = k + 1;
        if(BitUtils.isBitSet(l, k))
            mMovementGranularities = parcel.readInt();
        i = i1 + 1;
        if(BitUtils.isBitSet(l, i1))
            mBooleanProperties = parcel.readInt();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mPackageName = parcel.readCharSequence();
        i = k + 1;
        if(BitUtils.isBitSet(l, k))
            mClassName = parcel.readCharSequence();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mText = parcel.readCharSequence();
        i = k + 1;
        if(BitUtils.isBitSet(l, k))
            mHintText = parcel.readCharSequence();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mError = parcel.readCharSequence();
        i = k + 1;
        if(BitUtils.isBitSet(l, k))
            mContentDescription = parcel.readCharSequence();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mViewIdResourceName = parcel.readString();
        i = k + 1;
        if(BitUtils.isBitSet(l, k))
            mTextSelectionStart = parcel.readInt();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mTextSelectionEnd = parcel.readInt();
        i = k + 1;
        if(BitUtils.isBitSet(l, k))
            mInputType = parcel.readInt();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
            mLiveRegion = parcel.readInt();
        i = k + 1;
        if(BitUtils.isBitSet(l, k))
            mDrawingOrderInParent = parcel.readInt();
        k = i + 1;
        Object obj;
        boolean flag1;
        if(BitUtils.isBitSet(l, i))
            obj = parcel.createStringArrayList();
        else
            obj = null;
        mExtraDataKeys = ((ArrayList) (obj));
        i1 = k + 1;
        if(BitUtils.isBitSet(l, k))
            obj = parcel.readBundle();
        else
            obj = null;
        mExtras = ((Bundle) (obj));
        if(mRangeInfo != null)
            mRangeInfo.recycle();
        i = i1 + 1;
        if(BitUtils.isBitSet(l, i1))
            obj = RangeInfo.obtain(parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
        else
            obj = null;
        mRangeInfo = ((RangeInfo) (obj));
        if(mCollectionInfo != null)
            mCollectionInfo.recycle();
        k = i + 1;
        if(BitUtils.isBitSet(l, i))
        {
            int j1 = parcel.readInt();
            int j = parcel.readInt();
            int k1;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            obj = CollectionInfo.obtain(j1, j, flag1, parcel.readInt());
        } else
        {
            obj = null;
        }
        mCollectionInfo = ((CollectionInfo) (obj));
        if(mCollectionItemInfo != null)
            mCollectionItemInfo.recycle();
        if(BitUtils.isBitSet(l, k))
        {
            j = parcel.readInt();
            k = parcel.readInt();
            j1 = parcel.readInt();
            k1 = parcel.readInt();
            boolean flag2;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            if(parcel.readInt() == 1)
                flag2 = true;
            else
                flag2 = false;
            parcel = CollectionItemInfo.obtain(j, k, j1, k1, flag1, flag2);
        } else
        {
            parcel = null;
        }
        mCollectionItemInfo = parcel;
        mSealed = flag;
    }

    private static boolean isDefaultStandardAction(AccessibilityAction accessibilityaction)
    {
        boolean flag;
        if(accessibilityaction.mSerializationFlag != -1)
            flag = TextUtils.isEmpty(accessibilityaction.getLabel());
        else
            flag = false;
        return flag;
    }

    public static long makeNodeId(int i, int j)
    {
        return (long)j << 32 | (long)i;
    }

    public static AccessibilityNodeInfo obtain()
    {
        AccessibilityNodeInfo accessibilitynodeinfo = (AccessibilityNodeInfo)sPool.acquire();
        if(sNumInstancesInUse != null)
            sNumInstancesInUse.incrementAndGet();
        if(accessibilitynodeinfo == null)
            accessibilitynodeinfo = new AccessibilityNodeInfo();
        return accessibilitynodeinfo;
    }

    public static AccessibilityNodeInfo obtain(View view)
    {
        AccessibilityNodeInfo accessibilitynodeinfo = obtain();
        accessibilitynodeinfo.setSource(view);
        return accessibilitynodeinfo;
    }

    public static AccessibilityNodeInfo obtain(View view, int i)
    {
        AccessibilityNodeInfo accessibilitynodeinfo = obtain();
        accessibilitynodeinfo.setSource(view, i);
        return accessibilitynodeinfo;
    }

    public static AccessibilityNodeInfo obtain(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        AccessibilityNodeInfo accessibilitynodeinfo1 = obtain();
        accessibilitynodeinfo1.init(accessibilitynodeinfo);
        return accessibilitynodeinfo1;
    }

    private void setBooleanProperty(int i, boolean flag)
    {
        enforceNotSealed();
        if(flag)
            mBooleanProperties = mBooleanProperties | i;
        else
            mBooleanProperties = mBooleanProperties & i;
    }

    public static void setNumInstancesInUseCounter(AtomicInteger atomicinteger)
    {
        sNumInstancesInUse = atomicinteger;
    }

    public void addAction(int i)
    {
        enforceNotSealed();
        if((0xff000000 & i) != 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Action is not a combination of the standard actions: ").append(i).toString());
        } else
        {
            addStandardActions(i);
            return;
        }
    }

    public void addAction(AccessibilityAction accessibilityaction)
    {
        enforceNotSealed();
        addActionUnchecked(accessibilityaction);
    }

    public void addChild(View view)
    {
        addChildInternal(view, -1, true);
    }

    public void addChild(View view, int i)
    {
        addChildInternal(view, i, true);
    }

    public void addChildUnchecked(View view)
    {
        addChildInternal(view, -1, false);
    }

    public boolean canOpenPopup()
    {
        return getBooleanProperty(8192);
    }

    public int describeContents()
    {
        return 0;
    }

    protected void enforceNotSealed()
    {
        if(isSealed())
            throw new IllegalStateException("Cannot perform this action on a sealed instance.");
        else
            return;
    }

    protected void enforceSealed()
    {
        if(!isSealed())
            throw new IllegalStateException("Cannot perform this action on a not sealed instance.");
        else
            return;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (AccessibilityNodeInfo)obj;
        if(mSourceNodeId != ((AccessibilityNodeInfo) (obj)).mSourceNodeId)
            return false;
        return mWindowId == ((AccessibilityNodeInfo) (obj)).mWindowId;
    }

    public List findAccessibilityNodeInfosByText(String s)
    {
        enforceSealed();
        if(!canPerformRequestOverConnection(mSourceNodeId))
            return Collections.emptyList();
        else
            return AccessibilityInteractionClient.getInstance().findAccessibilityNodeInfosByText(mConnectionId, mWindowId, mSourceNodeId, s);
    }

    public List findAccessibilityNodeInfosByViewId(String s)
    {
        enforceSealed();
        if(!canPerformRequestOverConnection(mSourceNodeId))
            return Collections.emptyList();
        else
            return AccessibilityInteractionClient.getInstance().findAccessibilityNodeInfosByViewId(mConnectionId, mWindowId, mSourceNodeId, s);
    }

    public AccessibilityNodeInfo findFocus(int i)
    {
        enforceSealed();
        enforceValidFocusType(i);
        if(!canPerformRequestOverConnection(mSourceNodeId))
            return null;
        else
            return AccessibilityInteractionClient.getInstance().findFocus(mConnectionId, mWindowId, mSourceNodeId, i);
    }

    public AccessibilityNodeInfo focusSearch(int i)
    {
        enforceSealed();
        enforceValidFocusDirection(i);
        if(!canPerformRequestOverConnection(mSourceNodeId))
            return null;
        else
            return AccessibilityInteractionClient.getInstance().focusSearch(mConnectionId, mWindowId, mSourceNodeId, i);
    }

    public List getActionList()
    {
        return CollectionUtils.emptyIfNull(mActions);
    }

    public int getActions()
    {
        int i = 0;
        if(mActions == null)
            return 0;
        int j = mActions.size();
        for(int k = 0; k < j;)
        {
            int l = ((AccessibilityAction)mActions.get(k)).getId();
            int i1 = i;
            if(l <= 0x200000)
                i1 = i | l;
            k++;
            i = i1;
        }

        return i;
    }

    public List getAvailableExtraData()
    {
        if(mExtraDataKeys != null)
            return Collections.unmodifiableList(mExtraDataKeys);
        else
            return Collections.EMPTY_LIST;
    }

    public void getBoundsInParent(Rect rect)
    {
        rect.set(mBoundsInParent.left, mBoundsInParent.top, mBoundsInParent.right, mBoundsInParent.bottom);
    }

    public Rect getBoundsInScreen()
    {
        return mBoundsInScreen;
    }

    public void getBoundsInScreen(Rect rect)
    {
        rect.set(mBoundsInScreen.left, mBoundsInScreen.top, mBoundsInScreen.right, mBoundsInScreen.bottom);
    }

    public AccessibilityNodeInfo getChild(int i)
    {
        enforceSealed();
        if(mChildNodeIds == null)
            return null;
        if(!canPerformRequestOverConnection(mSourceNodeId))
        {
            return null;
        } else
        {
            long l = mChildNodeIds.get(i);
            return AccessibilityInteractionClient.getInstance().findAccessibilityNodeInfoByAccessibilityId(mConnectionId, mWindowId, l, false, 4, null);
        }
    }

    public int getChildCount()
    {
        int i;
        if(mChildNodeIds == null)
            i = 0;
        else
            i = mChildNodeIds.size();
        return i;
    }

    public long getChildId(int i)
    {
        if(mChildNodeIds == null)
            throw new IndexOutOfBoundsException();
        else
            return mChildNodeIds.get(i);
    }

    public LongArray getChildNodeIds()
    {
        return mChildNodeIds;
    }

    public CharSequence getClassName()
    {
        return mClassName;
    }

    public CollectionInfo getCollectionInfo()
    {
        return mCollectionInfo;
    }

    public CollectionItemInfo getCollectionItemInfo()
    {
        return mCollectionItemInfo;
    }

    public int getConnectionId()
    {
        return mConnectionId;
    }

    public CharSequence getContentDescription()
    {
        return mContentDescription;
    }

    public int getDrawingOrder()
    {
        return mDrawingOrderInParent;
    }

    public CharSequence getError()
    {
        return mError;
    }

    public Bundle getExtras()
    {
        if(mExtras == null)
            mExtras = new Bundle();
        return mExtras;
    }

    public CharSequence getHintText()
    {
        return mHintText;
    }

    public int getInputType()
    {
        return mInputType;
    }

    public AccessibilityNodeInfo getLabelFor()
    {
        enforceSealed();
        return getNodeForAccessibilityId(mLabelForId);
    }

    public AccessibilityNodeInfo getLabeledBy()
    {
        enforceSealed();
        return getNodeForAccessibilityId(mLabeledById);
    }

    public int getLiveRegion()
    {
        return mLiveRegion;
    }

    public int getMaxTextLength()
    {
        return mMaxTextLength;
    }

    public int getMovementGranularities()
    {
        return mMovementGranularities;
    }

    public CharSequence getOriginalText()
    {
        return mOriginalText;
    }

    public CharSequence getPackageName()
    {
        return mPackageName;
    }

    public AccessibilityNodeInfo getParent()
    {
        enforceSealed();
        return getNodeForAccessibilityId(mParentNodeId);
    }

    public long getParentNodeId()
    {
        return mParentNodeId;
    }

    public RangeInfo getRangeInfo()
    {
        return mRangeInfo;
    }

    public long getSourceNodeId()
    {
        return mSourceNodeId;
    }

    public CharSequence getText()
    {
        if(mText instanceof Spanned)
        {
            Spanned spanned = (Spanned)mText;
            Object aobj[] = (AccessibilityClickableSpan[])spanned.getSpans(0, mText.length(), android/text/style/AccessibilityClickableSpan);
            for(int i = 0; i < aobj.length; i++)
                aobj[i].copyConnectionDataFrom(this);

            aobj = (AccessibilityURLSpan[])spanned.getSpans(0, mText.length(), android/text/style/AccessibilityURLSpan);
            for(int j = 0; j < aobj.length; j++)
                aobj[j].copyConnectionDataFrom(this);

        }
        return mText;
    }

    public int getTextSelectionEnd()
    {
        return mTextSelectionEnd;
    }

    public int getTextSelectionStart()
    {
        return mTextSelectionStart;
    }

    public AccessibilityNodeInfo getTraversalAfter()
    {
        enforceSealed();
        return getNodeForAccessibilityId(mTraversalAfter);
    }

    public AccessibilityNodeInfo getTraversalBefore()
    {
        enforceSealed();
        return getNodeForAccessibilityId(mTraversalBefore);
    }

    public String getViewIdResourceName()
    {
        return mViewIdResourceName;
    }

    public AccessibilityWindowInfo getWindow()
    {
        enforceSealed();
        if(!canPerformRequestOverConnection(mSourceNodeId))
            return null;
        else
            return AccessibilityInteractionClient.getInstance().getWindow(mConnectionId, mWindowId);
    }

    public int getWindowId()
    {
        return mWindowId;
    }

    public boolean hasExtras()
    {
        boolean flag;
        if(mExtras != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        return ((getAccessibilityViewId(mSourceNodeId) + 31) * 31 + getVirtualDescendantId(mSourceNodeId)) * 31 + mWindowId;
    }

    public boolean isAccessibilityFocused()
    {
        return getBooleanProperty(1024);
    }

    public boolean isCheckable()
    {
        return getBooleanProperty(1);
    }

    public boolean isChecked()
    {
        return getBooleanProperty(2);
    }

    public boolean isClickable()
    {
        return getBooleanProperty(32);
    }

    public boolean isContentInvalid()
    {
        return getBooleanProperty(0x10000);
    }

    public boolean isContextClickable()
    {
        return getBooleanProperty(0x20000);
    }

    public boolean isDismissable()
    {
        return getBooleanProperty(16384);
    }

    public boolean isEditable()
    {
        return getBooleanProperty(4096);
    }

    public boolean isEnabled()
    {
        return getBooleanProperty(128);
    }

    public boolean isFocusable()
    {
        return getBooleanProperty(4);
    }

    public boolean isFocused()
    {
        return getBooleanProperty(8);
    }

    public boolean isImportantForAccessibility()
    {
        return getBooleanProperty(0x40000);
    }

    public boolean isLongClickable()
    {
        return getBooleanProperty(64);
    }

    public boolean isMultiLine()
    {
        return getBooleanProperty(32768);
    }

    public boolean isPassword()
    {
        return getBooleanProperty(256);
    }

    public boolean isScrollable()
    {
        return getBooleanProperty(512);
    }

    public boolean isSealed()
    {
        return mSealed;
    }

    public boolean isSelected()
    {
        return getBooleanProperty(16);
    }

    public boolean isShowingHintText()
    {
        return getBooleanProperty(0x100000);
    }

    public boolean isVisibleToUser()
    {
        return getBooleanProperty(2048);
    }

    public boolean performAction(int i)
    {
        enforceSealed();
        if(!canPerformRequestOverConnection(mSourceNodeId))
            return false;
        else
            return AccessibilityInteractionClient.getInstance().performAccessibilityAction(mConnectionId, mWindowId, mSourceNodeId, i, null);
    }

    public boolean performAction(int i, Bundle bundle)
    {
        enforceSealed();
        if(!canPerformRequestOverConnection(mSourceNodeId))
            return false;
        else
            return AccessibilityInteractionClient.getInstance().performAccessibilityAction(mConnectionId, mWindowId, mSourceNodeId, i, bundle);
    }

    public void recycle()
    {
        clear();
        sPool.release(this);
        if(sNumInstancesInUse != null)
            sNumInstancesInUse.decrementAndGet();
    }

    public boolean refresh()
    {
        return refresh(null, true);
    }

    public boolean refresh(Bundle bundle, boolean flag)
    {
        enforceSealed();
        if(!canPerformRequestOverConnection(mSourceNodeId))
            return false;
        bundle = AccessibilityInteractionClient.getInstance().findAccessibilityNodeInfoByAccessibilityId(mConnectionId, mWindowId, mSourceNodeId, flag, 0, bundle);
        if(bundle == null)
        {
            return false;
        } else
        {
            init(bundle);
            bundle.recycle();
            return true;
        }
    }

    public boolean refreshWithExtraData(String s, Bundle bundle)
    {
        bundle.putString("android.view.accessibility.AccessibilityNodeInfo.extra_data_requested", s);
        return refresh(bundle, true);
    }

    public void removeAction(int i)
    {
        enforceNotSealed();
        removeAction(getActionSingleton(i));
    }

    public boolean removeAction(AccessibilityAction accessibilityaction)
    {
        enforceNotSealed();
        if(mActions == null || accessibilityaction == null)
            return false;
        else
            return mActions.remove(accessibilityaction);
    }

    public void removeAllActions()
    {
        if(mActions != null)
            mActions.clear();
    }

    public boolean removeChild(View view)
    {
        return removeChild(view, -1);
    }

    public boolean removeChild(View view, int i)
    {
        enforceNotSealed();
        LongArray longarray = mChildNodeIds;
        if(longarray == null)
            return false;
        int j;
        if(view != null)
            j = view.getAccessibilityViewId();
        else
            j = 0x7fffffff;
        i = longarray.indexOf(makeNodeId(j, i));
        if(i < 0)
        {
            return false;
        } else
        {
            longarray.remove(i);
            return true;
        }
    }

    public void setAccessibilityFocused(boolean flag)
    {
        setBooleanProperty(1024, flag);
    }

    public void setAvailableExtraData(List list)
    {
        enforceNotSealed();
        mExtraDataKeys = new ArrayList(list);
    }

    public void setBoundsInParent(Rect rect)
    {
        enforceNotSealed();
        mBoundsInParent.set(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setBoundsInScreen(Rect rect)
    {
        enforceNotSealed();
        mBoundsInScreen.set(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setCanOpenPopup(boolean flag)
    {
        enforceNotSealed();
        setBooleanProperty(8192, flag);
    }

    public void setCheckable(boolean flag)
    {
        setBooleanProperty(1, flag);
    }

    public void setChecked(boolean flag)
    {
        setBooleanProperty(2, flag);
    }

    public void setClassName(CharSequence charsequence)
    {
        enforceNotSealed();
        mClassName = charsequence;
    }

    public void setClickable(boolean flag)
    {
        setBooleanProperty(32, flag);
    }

    public void setCollectionInfo(CollectionInfo collectioninfo)
    {
        enforceNotSealed();
        mCollectionInfo = collectioninfo;
    }

    public void setCollectionItemInfo(CollectionItemInfo collectioniteminfo)
    {
        enforceNotSealed();
        mCollectionItemInfo = collectioniteminfo;
    }

    public void setConnectionId(int i)
    {
        enforceNotSealed();
        mConnectionId = i;
    }

    public void setContentDescription(CharSequence charsequence)
    {
        Object obj = null;
        enforceNotSealed();
        if(charsequence == null)
            charsequence = obj;
        else
            charsequence = charsequence.subSequence(0, charsequence.length());
        mContentDescription = charsequence;
    }

    public void setContentInvalid(boolean flag)
    {
        setBooleanProperty(0x10000, flag);
    }

    public void setContextClickable(boolean flag)
    {
        setBooleanProperty(0x20000, flag);
    }

    public void setDismissable(boolean flag)
    {
        setBooleanProperty(16384, flag);
    }

    public void setDrawingOrder(int i)
    {
        enforceNotSealed();
        mDrawingOrderInParent = i;
    }

    public void setEditable(boolean flag)
    {
        setBooleanProperty(4096, flag);
    }

    public void setEnabled(boolean flag)
    {
        setBooleanProperty(128, flag);
    }

    public void setError(CharSequence charsequence)
    {
        Object obj = null;
        enforceNotSealed();
        if(charsequence == null)
            charsequence = obj;
        else
            charsequence = charsequence.subSequence(0, charsequence.length());
        mError = charsequence;
    }

    public void setFocusable(boolean flag)
    {
        setBooleanProperty(4, flag);
    }

    public void setFocused(boolean flag)
    {
        setBooleanProperty(8, flag);
    }

    public void setHintText(CharSequence charsequence)
    {
        Object obj = null;
        enforceNotSealed();
        if(charsequence == null)
            charsequence = obj;
        else
            charsequence = charsequence.subSequence(0, charsequence.length());
        mHintText = charsequence;
    }

    public void setImportantForAccessibility(boolean flag)
    {
        setBooleanProperty(0x40000, flag);
    }

    public void setInputType(int i)
    {
        enforceNotSealed();
        mInputType = i;
    }

    public void setLabelFor(View view)
    {
        setLabelFor(view, -1);
    }

    public void setLabelFor(View view, int i)
    {
        enforceNotSealed();
        int j;
        if(view != null)
            j = view.getAccessibilityViewId();
        else
            j = 0x7fffffff;
        mLabelForId = makeNodeId(j, i);
    }

    public void setLabeledBy(View view)
    {
        setLabeledBy(view, -1);
    }

    public void setLabeledBy(View view, int i)
    {
        enforceNotSealed();
        int j;
        if(view != null)
            j = view.getAccessibilityViewId();
        else
            j = 0x7fffffff;
        mLabeledById = makeNodeId(j, i);
    }

    public void setLiveRegion(int i)
    {
        enforceNotSealed();
        mLiveRegion = i;
    }

    public void setLongClickable(boolean flag)
    {
        setBooleanProperty(64, flag);
    }

    public void setMaxTextLength(int i)
    {
        enforceNotSealed();
        mMaxTextLength = i;
    }

    public void setMovementGranularities(int i)
    {
        enforceNotSealed();
        mMovementGranularities = i;
    }

    public void setMultiLine(boolean flag)
    {
        setBooleanProperty(32768, flag);
    }

    public void setPackageName(CharSequence charsequence)
    {
        enforceNotSealed();
        mPackageName = charsequence;
    }

    public void setParent(View view)
    {
        setParent(view, -1);
    }

    public void setParent(View view, int i)
    {
        enforceNotSealed();
        int j;
        if(view != null)
            j = view.getAccessibilityViewId();
        else
            j = 0x7fffffff;
        mParentNodeId = makeNodeId(j, i);
    }

    public void setPassword(boolean flag)
    {
        setBooleanProperty(256, flag);
    }

    public void setRangeInfo(RangeInfo rangeinfo)
    {
        enforceNotSealed();
        mRangeInfo = rangeinfo;
    }

    public void setScrollable(boolean flag)
    {
        setBooleanProperty(512, flag);
    }

    public void setSealed(boolean flag)
    {
        mSealed = flag;
    }

    public void setSelected(boolean flag)
    {
        setBooleanProperty(16, flag);
    }

    public void setShowingHintText(boolean flag)
    {
        setBooleanProperty(0x100000, flag);
    }

    public void setSource(View view)
    {
        setSource(view, -1);
    }

    public void setSource(View view, int i)
    {
        enforceNotSealed();
        int j;
        if(view != null)
            j = view.getAccessibilityWindowId();
        else
            j = 0x7fffffff;
        mWindowId = j;
        if(view != null)
            j = view.getAccessibilityViewId();
        else
            j = 0x7fffffff;
        mSourceNodeId = makeNodeId(j, i);
    }

    public void setSourceNodeId(long l, int i)
    {
        enforceNotSealed();
        mSourceNodeId = l;
        mWindowId = i;
    }

    public void setText(CharSequence charsequence)
    {
        enforceNotSealed();
        mOriginalText = charsequence;
        if(charsequence instanceof Spanned)
        {
            ClickableSpan aclickablespan[] = (ClickableSpan[])((Spanned)charsequence).getSpans(0, charsequence.length(), android/text/style/ClickableSpan);
            if(aclickablespan.length > 0)
            {
                SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(charsequence);
                int i = 0;
                do
                {
label0:
                    {
                        if(i < aclickablespan.length)
                        {
                            charsequence = aclickablespan[i];
                            if(!(charsequence instanceof AccessibilityClickableSpan) && !(charsequence instanceof AccessibilityURLSpan))
                                break label0;
                        }
                        mText = spannablestringbuilder;
                        return;
                    }
                    int j = spannablestringbuilder.getSpanStart(charsequence);
                    int k = spannablestringbuilder.getSpanEnd(charsequence);
                    int l = spannablestringbuilder.getSpanFlags(charsequence);
                    spannablestringbuilder.removeSpan(charsequence);
                    if(charsequence instanceof URLSpan)
                        charsequence = new AccessibilityURLSpan((URLSpan)charsequence);
                    else
                        charsequence = new AccessibilityClickableSpan(charsequence.getId());
                    spannablestringbuilder.setSpan(charsequence, j, k, l);
                    i++;
                } while(true);
            }
        }
        if(charsequence == null)
            charsequence = null;
        else
            charsequence = charsequence.subSequence(0, charsequence.length());
        mText = charsequence;
    }

    public void setTextSelection(int i, int j)
    {
        enforceNotSealed();
        mTextSelectionStart = i;
        mTextSelectionEnd = j;
    }

    public void setTraversalAfter(View view)
    {
        setTraversalAfter(view, -1);
    }

    public void setTraversalAfter(View view, int i)
    {
        enforceNotSealed();
        int j;
        if(view != null)
            j = view.getAccessibilityViewId();
        else
            j = 0x7fffffff;
        mTraversalAfter = makeNodeId(j, i);
    }

    public void setTraversalBefore(View view)
    {
        setTraversalBefore(view, -1);
    }

    public void setTraversalBefore(View view, int i)
    {
        enforceNotSealed();
        int j;
        if(view != null)
            j = view.getAccessibilityViewId();
        else
            j = 0x7fffffff;
        mTraversalBefore = makeNodeId(j, i);
    }

    public void setViewIdResourceName(String s)
    {
        enforceNotSealed();
        mViewIdResourceName = s;
    }

    public void setVisibleToUser(boolean flag)
    {
        setBooleanProperty(2048, flag);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(super.toString());
        stringbuilder.append("; boundsInParent: ").append(mBoundsInParent);
        stringbuilder.append("; boundsInScreen: ").append(mBoundsInScreen);
        stringbuilder.append("; packageName: ").append(mPackageName);
        stringbuilder.append("; className: ").append(mClassName);
        stringbuilder.append("; text: ").append(mText);
        stringbuilder.append("; error: ").append(mError);
        stringbuilder.append("; maxTextLength: ").append(mMaxTextLength);
        stringbuilder.append("; contentDescription: ").append(mContentDescription);
        stringbuilder.append("; viewIdResName: ").append(mViewIdResourceName);
        stringbuilder.append("; checkable: ").append(isCheckable());
        stringbuilder.append("; checked: ").append(isChecked());
        stringbuilder.append("; focusable: ").append(isFocusable());
        stringbuilder.append("; focused: ").append(isFocused());
        stringbuilder.append("; selected: ").append(isSelected());
        stringbuilder.append("; clickable: ").append(isClickable());
        stringbuilder.append("; longClickable: ").append(isLongClickable());
        stringbuilder.append("; contextClickable: ").append(isContextClickable());
        stringbuilder.append("; enabled: ").append(isEnabled());
        stringbuilder.append("; password: ").append(isPassword());
        stringbuilder.append("; scrollable: ").append(isScrollable());
        stringbuilder.append("; importantForAccessibility: ").append(isImportantForAccessibility());
        stringbuilder.append("; actions: ").append(mActions);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        long l = 0L;
        if(isSealed() != DEFAULT.isSealed())
            l = 0L | BitUtils.bitAt(0);
        long l1 = l;
        if(mSourceNodeId != DEFAULT.mSourceNodeId)
            l1 = l | BitUtils.bitAt(1);
        i = 1 + 1;
        l = l1;
        if(mWindowId != DEFAULT.mWindowId)
            l = l1 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(mParentNodeId != DEFAULT.mParentNodeId)
            l1 = l | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(mLabelForId != DEFAULT.mLabelForId)
            l = l1 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(mLabeledById != DEFAULT.mLabeledById)
            l1 = l | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(mTraversalBefore != DEFAULT.mTraversalBefore)
            l = l1 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(mTraversalAfter != DEFAULT.mTraversalAfter)
            l1 = l | BitUtils.bitAt(i);
        i++;
        long l2 = l1;
        if(mConnectionId != DEFAULT.mConnectionId)
            l2 = l1 | BitUtils.bitAt(i);
        i++;
        l = l2;
        if(!Objects.equals(mChildNodeIds, DEFAULT.mChildNodeIds))
            l = l2 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(!Objects.equals(mBoundsInParent, DEFAULT.mBoundsInParent))
            l1 = l | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(!Objects.equals(mBoundsInScreen, DEFAULT.mBoundsInScreen))
            l = l1 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(!Objects.equals(mActions, DEFAULT.mActions))
            l1 = l | BitUtils.bitAt(i);
        i++;
        l2 = l1;
        if(mMaxTextLength != DEFAULT.mMaxTextLength)
            l2 = l1 | BitUtils.bitAt(i);
        i++;
        l = l2;
        if(mMovementGranularities != DEFAULT.mMovementGranularities)
            l = l2 | BitUtils.bitAt(i);
        i++;
        l2 = l;
        if(mBooleanProperties != DEFAULT.mBooleanProperties)
            l2 = l | BitUtils.bitAt(i);
        i++;
        l1 = l2;
        if(!Objects.equals(mPackageName, DEFAULT.mPackageName))
            l1 = l2 | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(!Objects.equals(mClassName, DEFAULT.mClassName))
            l = l1 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(!Objects.equals(mText, DEFAULT.mText))
            l1 = l | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(!Objects.equals(mHintText, DEFAULT.mHintText))
            l = l1 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(!Objects.equals(mError, DEFAULT.mError))
            l1 = l | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(!Objects.equals(mContentDescription, DEFAULT.mContentDescription))
            l = l1 | BitUtils.bitAt(i);
        i++;
        l2 = l;
        if(!Objects.equals(mViewIdResourceName, DEFAULT.mViewIdResourceName))
            l2 = l | BitUtils.bitAt(i);
        i++;
        l1 = l2;
        if(mTextSelectionStart != DEFAULT.mTextSelectionStart)
            l1 = l2 | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(mTextSelectionEnd != DEFAULT.mTextSelectionEnd)
            l = l1 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(mInputType != DEFAULT.mInputType)
            l1 = l | BitUtils.bitAt(i);
        i++;
        l2 = l1;
        if(mLiveRegion != DEFAULT.mLiveRegion)
            l2 = l1 | BitUtils.bitAt(i);
        i++;
        l = l2;
        if(mDrawingOrderInParent != DEFAULT.mDrawingOrderInParent)
            l = l2 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(!Objects.equals(mExtraDataKeys, DEFAULT.mExtraDataKeys))
            l1 = l | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(!Objects.equals(mExtras, DEFAULT.mExtras))
            l = l1 | BitUtils.bitAt(i);
        i++;
        l1 = l;
        if(!Objects.equals(mRangeInfo, DEFAULT.mRangeInfo))
            l1 = l | BitUtils.bitAt(i);
        i++;
        l = l1;
        if(!Objects.equals(mCollectionInfo, DEFAULT.mCollectionInfo))
            l = l1 | BitUtils.bitAt(i);
        l1 = l;
        if(!Objects.equals(mCollectionItemInfo, DEFAULT.mCollectionItemInfo))
            l1 = l | BitUtils.bitAt(i + 1);
        parcel.writeLong(l1);
        int j2;
        if(BitUtils.isBitSet(l1, 0))
        {
            int j;
            int j1;
            if(isSealed())
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
        }
        i = 1 + 1;
        if(BitUtils.isBitSet(l1, 1))
            parcel.writeLong(mSourceNodeId);
        j = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeInt(mWindowId);
        i = j + 1;
        if(BitUtils.isBitSet(l1, j))
            parcel.writeLong(mParentNodeId);
        j = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeLong(mLabelForId);
        i = j + 1;
        if(BitUtils.isBitSet(l1, j))
            parcel.writeLong(mLabeledById);
        j = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeLong(mTraversalBefore);
        i = j + 1;
        if(BitUtils.isBitSet(l1, j))
            parcel.writeLong(mTraversalAfter);
        j1 = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeInt(mConnectionId);
        j = j1 + 1;
        if(BitUtils.isBitSet(l1, j1))
        {
            Object obj = mChildNodeIds;
            if(obj == null)
            {
                parcel.writeInt(0);
            } else
            {
                k1 = ((LongArray) (obj)).size();
                parcel.writeInt(k1);
                i = 0;
                while(i < k1) 
                {
                    parcel.writeLong(((LongArray) (obj)).get(i));
                    i++;
                }
            }
        }
        i = j + 1;
        if(BitUtils.isBitSet(l1, j))
        {
            parcel.writeInt(mBoundsInParent.top);
            parcel.writeInt(mBoundsInParent.bottom);
            parcel.writeInt(mBoundsInParent.left);
            parcel.writeInt(mBoundsInParent.right);
        }
        j = i + 1;
        if(BitUtils.isBitSet(l1, i))
        {
            parcel.writeInt(mBoundsInScreen.top);
            parcel.writeInt(mBoundsInScreen.bottom);
            parcel.writeInt(mBoundsInScreen.left);
            parcel.writeInt(mBoundsInScreen.right);
        }
        j2 = j + 1;
        if(BitUtils.isBitSet(l1, j))
            if(mActions != null && mActions.isEmpty() ^ true)
            {
                int k2 = mActions.size();
                int k1 = 0;
                int k = 0;
                i = 0;
                while(i < k2) 
                {
                    obj = (AccessibilityAction)mActions.get(i);
                    if(isDefaultStandardAction(((AccessibilityAction) (obj))))
                        k |= ((AccessibilityAction) (obj)).mSerializationFlag;
                    else
                        k1++;
                    i++;
                }
                parcel.writeInt(k);
                parcel.writeInt(k1);
                for(i = 0; i < k2; i++)
                {
                    AccessibilityAction accessibilityaction = (AccessibilityAction)mActions.get(i);
                    if(!isDefaultStandardAction(accessibilityaction))
                    {
                        parcel.writeInt(accessibilityaction.getId());
                        parcel.writeCharSequence(accessibilityaction.getLabel());
                    }
                }

            } else
            {
                parcel.writeInt(0);
                parcel.writeInt(0);
            }
        i = j2 + 1;
        if(BitUtils.isBitSet(l1, j2))
            parcel.writeInt(mMaxTextLength);
        int i1 = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeInt(mMovementGranularities);
        i = i1 + 1;
        if(BitUtils.isBitSet(l1, i1))
            parcel.writeInt(mBooleanProperties);
        int i2 = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeCharSequence(mPackageName);
        i1 = i2 + 1;
        if(BitUtils.isBitSet(l1, i2))
            parcel.writeCharSequence(mClassName);
        i = i1 + 1;
        if(BitUtils.isBitSet(l1, i1))
            parcel.writeCharSequence(mText);
        i1 = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeCharSequence(mHintText);
        i = i1 + 1;
        if(BitUtils.isBitSet(l1, i1))
            parcel.writeCharSequence(mError);
        i1 = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeCharSequence(mContentDescription);
        i = i1 + 1;
        if(BitUtils.isBitSet(l1, i1))
            parcel.writeString(mViewIdResourceName);
        i2 = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeInt(mTextSelectionStart);
        i1 = i2 + 1;
        if(BitUtils.isBitSet(l1, i2))
            parcel.writeInt(mTextSelectionEnd);
        i = i1 + 1;
        if(BitUtils.isBitSet(l1, i1))
            parcel.writeInt(mInputType);
        i1 = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeInt(mLiveRegion);
        i = i1 + 1;
        if(BitUtils.isBitSet(l1, i1))
            parcel.writeInt(mDrawingOrderInParent);
        i1 = i + 1;
        if(BitUtils.isBitSet(l1, i))
            parcel.writeStringList(mExtraDataKeys);
        i = i1 + 1;
        if(BitUtils.isBitSet(l1, i1))
            parcel.writeBundle(mExtras);
        i2 = i + 1;
        if(BitUtils.isBitSet(l1, i))
        {
            parcel.writeInt(mRangeInfo.getType());
            parcel.writeFloat(mRangeInfo.getMin());
            parcel.writeFloat(mRangeInfo.getMax());
            parcel.writeFloat(mRangeInfo.getCurrent());
        }
        i1 = i2 + 1;
        if(BitUtils.isBitSet(l1, i2))
        {
            parcel.writeInt(mCollectionInfo.getRowCount());
            parcel.writeInt(mCollectionInfo.getColumnCount());
            if(mCollectionInfo.isHierarchical())
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(mCollectionInfo.getSelectionMode());
        }
        if(BitUtils.isBitSet(l1, i1))
        {
            parcel.writeInt(mCollectionItemInfo.getRowIndex());
            parcel.writeInt(mCollectionItemInfo.getRowSpan());
            parcel.writeInt(mCollectionItemInfo.getColumnIndex());
            parcel.writeInt(mCollectionItemInfo.getColumnSpan());
            if(mCollectionItemInfo.isHeading())
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(mCollectionItemInfo.isSelected())
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
        }
        recycle();
    }

    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_ACCESSIBLE_CLICKABLE_SPAN = "android.view.accessibility.action.ACTION_ARGUMENT_ACCESSIBLE_CLICKABLE_SPAN";
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 0x80000;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 0x10000;
    public static final int ACTION_DISMISS = 0x100000;
    public static final int ACTION_EXPAND = 0x40000;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 0x20000;
    public static final int ACTION_SET_TEXT = 0x200000;
    private static final int ACTION_TYPE_MASK = 0xff000000;
    private static final int BOOLEAN_PROPERTY_ACCESSIBILITY_FOCUSED = 1024;
    private static final int BOOLEAN_PROPERTY_CHECKABLE = 1;
    private static final int BOOLEAN_PROPERTY_CHECKED = 2;
    private static final int BOOLEAN_PROPERTY_CLICKABLE = 32;
    private static final int BOOLEAN_PROPERTY_CONTENT_INVALID = 0x10000;
    private static final int BOOLEAN_PROPERTY_CONTEXT_CLICKABLE = 0x20000;
    private static final int BOOLEAN_PROPERTY_DISMISSABLE = 16384;
    private static final int BOOLEAN_PROPERTY_EDITABLE = 4096;
    private static final int BOOLEAN_PROPERTY_ENABLED = 128;
    private static final int BOOLEAN_PROPERTY_FOCUSABLE = 4;
    private static final int BOOLEAN_PROPERTY_FOCUSED = 8;
    private static final int BOOLEAN_PROPERTY_IMPORTANCE = 0x40000;
    private static final int BOOLEAN_PROPERTY_IS_SHOWING_HINT = 0x100000;
    private static final int BOOLEAN_PROPERTY_LONG_CLICKABLE = 64;
    private static final int BOOLEAN_PROPERTY_MULTI_LINE = 32768;
    private static final int BOOLEAN_PROPERTY_OPENS_POPUP = 8192;
    private static final int BOOLEAN_PROPERTY_PASSWORD = 256;
    private static final int BOOLEAN_PROPERTY_SCROLLABLE = 512;
    private static final int BOOLEAN_PROPERTY_SELECTED = 16;
    private static final int BOOLEAN_PROPERTY_VISIBLE_TO_USER = 2048;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AccessibilityNodeInfo createFromParcel(Parcel parcel)
        {
            AccessibilityNodeInfo accessibilitynodeinfo = AccessibilityNodeInfo.obtain();
            AccessibilityNodeInfo._2D_wrap1(accessibilitynodeinfo, parcel);
            return accessibilitynodeinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AccessibilityNodeInfo[] newArray(int i)
        {
            return new AccessibilityNodeInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    private static final AccessibilityNodeInfo DEFAULT = new AccessibilityNodeInfo();
    public static final String EXTRA_DATA_REQUESTED_KEY = "android.view.accessibility.AccessibilityNodeInfo.extra_data_requested";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY";
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 8;
    public static final int FLAG_PREFETCH_DESCENDANTS = 4;
    public static final int FLAG_PREFETCH_PREDECESSORS = 1;
    public static final int FLAG_PREFETCH_SIBLINGS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    public static final int LAST_LEGACY_STANDARD_ACTION = 0x200000;
    private static final int MAX_POOL_SIZE = 50;
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    public static final int ROOT_ITEM_ID = 0x7ffffffe;
    public static final long ROOT_NODE_ID = makeNodeId(0x7ffffffe, -1);
    public static final int UNDEFINED_CONNECTION_ID = -1;
    public static final int UNDEFINED_ITEM_ID = 0x7fffffff;
    public static final long UNDEFINED_NODE_ID = makeNodeId(0x7fffffff, 0x7fffffff);
    public static final int UNDEFINED_SELECTION_INDEX = -1;
    private static final long VIRTUAL_DESCENDANT_ID_MASK = 0xffffffff00000000L;
    private static final int VIRTUAL_DESCENDANT_ID_SHIFT = 32;
    private static AtomicInteger sNumInstancesInUse;
    private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(50);
    private ArrayList mActions;
    private int mBooleanProperties;
    private final Rect mBoundsInParent = new Rect();
    private final Rect mBoundsInScreen = new Rect();
    private LongArray mChildNodeIds;
    private CharSequence mClassName;
    private CollectionInfo mCollectionInfo;
    private CollectionItemInfo mCollectionItemInfo;
    private int mConnectionId;
    private CharSequence mContentDescription;
    private int mDrawingOrderInParent;
    private CharSequence mError;
    private ArrayList mExtraDataKeys;
    private Bundle mExtras;
    private CharSequence mHintText;
    private int mInputType;
    private long mLabelForId;
    private long mLabeledById;
    private int mLiveRegion;
    private int mMaxTextLength;
    private int mMovementGranularities;
    private CharSequence mOriginalText;
    private CharSequence mPackageName;
    private long mParentNodeId;
    private RangeInfo mRangeInfo;
    private boolean mSealed;
    private long mSourceNodeId;
    private CharSequence mText;
    private int mTextSelectionEnd;
    private int mTextSelectionStart;
    private long mTraversalAfter;
    private long mTraversalBefore;
    private String mViewIdResourceName;
    private int mWindowId;

}
