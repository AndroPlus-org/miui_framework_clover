// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.widget:
//            ListView, ExpandableListPosition, ExpandableListAdapter, ExpandableListConnector, 
//            ListAdapter

public class ExpandableListView extends ListView
{
    public static class ExpandableListContextMenuInfo
        implements android.view.ContextMenu.ContextMenuInfo
    {

        public long id;
        public long packedPosition;
        public View targetView;

        public ExpandableListContextMenuInfo(View view, long l, long l1)
        {
            targetView = view;
            packedPosition = l;
            id = l1;
        }
    }

    public static interface OnChildClickListener
    {

        public abstract boolean onChildClick(ExpandableListView expandablelistview, View view, int i, int j, long l);
    }

    public static interface OnGroupClickListener
    {

        public abstract boolean onGroupClick(ExpandableListView expandablelistview, View view, int i, long l);
    }

    public static interface OnGroupCollapseListener
    {

        public abstract void onGroupCollapse(int i);
    }

    public static interface OnGroupExpandListener
    {

        public abstract void onGroupExpand(int i);
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeList(expandedGroupMetadataList);
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
        ArrayList expandedGroupMetadataList;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            expandedGroupMetadataList = new ArrayList();
            parcel.readList(expandedGroupMetadataList, android/widget/ExpandableListConnector.getClassLoader());
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable, ArrayList arraylist)
        {
            super(parcelable);
            expandedGroupMetadataList = arraylist;
        }
    }


    public ExpandableListView(Context context)
    {
        this(context, null);
    }

    public ExpandableListView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101006f);
    }

    public ExpandableListView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ExpandableListView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mIndicatorRect = new Rect();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ExpandableListView, i, j);
        mGroupIndicator = context.getDrawable(0);
        mChildIndicator = context.getDrawable(1);
        mIndicatorLeft = context.getDimensionPixelSize(2, 0);
        mIndicatorRight = context.getDimensionPixelSize(3, 0);
        if(mIndicatorRight == 0 && mGroupIndicator != null)
            mIndicatorRight = mIndicatorLeft + mGroupIndicator.getIntrinsicWidth();
        mChildIndicatorLeft = context.getDimensionPixelSize(4, -1);
        mChildIndicatorRight = context.getDimensionPixelSize(5, -1);
        mChildDivider = context.getDrawable(6);
        if(!isRtlCompatibilityMode())
        {
            mIndicatorStart = context.getDimensionPixelSize(7, -2);
            mIndicatorEnd = context.getDimensionPixelSize(8, -2);
            mChildIndicatorStart = context.getDimensionPixelSize(9, -1);
            mChildIndicatorEnd = context.getDimensionPixelSize(10, -1);
        }
        context.recycle();
    }

    private int getAbsoluteFlatPosition(int i)
    {
        return getHeaderViewsCount() + i;
    }

    private long getChildOrGroupId(ExpandableListPosition expandablelistposition)
    {
        if(expandablelistposition.type == 1)
            return mAdapter.getChildId(expandablelistposition.groupPos, expandablelistposition.childPos);
        else
            return mAdapter.getGroupId(expandablelistposition.groupPos);
    }

    private int getFlatPositionForConnector(int i)
    {
        return i - getHeaderViewsCount();
    }

    private Drawable getIndicator(ExpandableListConnector.PositionMetadata positionmetadata)
    {
        byte byte0 = 2;
        if(positionmetadata.position.type != 2) goto _L2; else goto _L1
_L1:
        Drawable drawable2;
        Drawable drawable = mGroupIndicator;
        drawable2 = drawable;
        if(drawable != null)
        {
            drawable2 = drawable;
            if(drawable.isStateful())
            {
                byte byte1;
                boolean flag;
                if(positionmetadata.groupMetadata != null)
                {
                    if(positionmetadata.groupMetadata.lastChildFlPos == positionmetadata.groupMetadata.flPos)
                        byte1 = 1;
                    else
                        byte1 = 0;
                } else
                {
                    byte1 = 1;
                }
                if(positionmetadata.isExpanded())
                    flag = true;
                else
                    flag = false;
                if(byte1 != 0)
                    byte1 = byte0;
                else
                    byte1 = 0;
                drawable.setState(GROUP_STATE_SETS[flag | byte1]);
                drawable2 = drawable;
            }
        }
_L4:
        return drawable2;
_L2:
        Drawable drawable1 = mChildIndicator;
        drawable2 = drawable1;
        if(drawable1 != null)
        {
            drawable2 = drawable1;
            if(drawable1.isStateful())
            {
                if(positionmetadata.position.flatListPos == positionmetadata.groupMetadata.lastChildFlPos)
                    positionmetadata = CHILD_LAST_STATE_SET;
                else
                    positionmetadata = EMPTY_STATE_SET;
                drawable1.setState(positionmetadata);
                drawable2 = drawable1;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int getPackedPositionChild(long l)
    {
        if(l == 0xffffffffL)
            return -1;
        if((l & 0x8000000000000000L) != 0x8000000000000000L)
            return -1;
        else
            return (int)(l & 0xffffffffL);
    }

    public static long getPackedPositionForChild(int i, int j)
    {
        return ((long)i & 0x7fffffffL) << 32 | 0x8000000000000000L | (long)j & -1L;
    }

    public static long getPackedPositionForGroup(int i)
    {
        return ((long)i & 0x7fffffffL) << 32;
    }

    public static int getPackedPositionGroup(long l)
    {
        if(l == 0xffffffffL)
            return -1;
        else
            return (int)((0x7fffffff00000000L & l) >> 32);
    }

    public static int getPackedPositionType(long l)
    {
        if(l == 0xffffffffL)
            return 2;
        int i;
        if((l & 0x8000000000000000L) == 0x8000000000000000L)
            i = 1;
        else
            i = 0;
        return i;
    }

    private boolean hasRtlSupport()
    {
        return mContext.getApplicationInfo().hasRtlSupport();
    }

    private boolean isHeaderOrFooterPosition(int i)
    {
        boolean flag = true;
        int j = mItemCount;
        int k = getFooterViewsCount();
        boolean flag1 = flag;
        if(i >= getHeaderViewsCount())
            if(i >= j - k)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private boolean isRtlCompatibilityMode()
    {
        boolean flag;
        if(mContext.getApplicationInfo().targetSdkVersion >= 17)
            flag = hasRtlSupport() ^ true;
        else
            flag = true;
        return flag;
    }

    private void resolveChildIndicator()
    {
        if(!isLayoutRtl()) goto _L2; else goto _L1
_L1:
        if(mChildIndicatorStart >= -1)
            mChildIndicatorRight = mChildIndicatorStart;
        if(mChildIndicatorEnd >= -1)
            mChildIndicatorLeft = mChildIndicatorEnd;
_L4:
        return;
_L2:
        if(mChildIndicatorStart >= -1)
            mChildIndicatorLeft = mChildIndicatorStart;
        if(mChildIndicatorEnd >= -1)
            mChildIndicatorRight = mChildIndicatorEnd;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void resolveIndicator()
    {
        if(!isLayoutRtl()) goto _L2; else goto _L1
_L1:
        if(mIndicatorStart >= 0)
            mIndicatorRight = mIndicatorStart;
        if(mIndicatorEnd >= 0)
            mIndicatorLeft = mIndicatorEnd;
_L4:
        if(mIndicatorRight == 0 && mGroupIndicator != null)
            mIndicatorRight = mIndicatorLeft + mGroupIndicator.getIntrinsicWidth();
        return;
_L2:
        if(mIndicatorStart >= 0)
            mIndicatorLeft = mIndicatorStart;
        if(mIndicatorEnd >= 0)
            mIndicatorRight = mIndicatorEnd;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean collapseGroup(int i)
    {
        boolean flag = mConnector.collapseGroup(i);
        if(mOnGroupCollapseListener != null)
            mOnGroupCollapseListener.onGroupCollapse(i);
        return flag;
    }

    android.view.ContextMenu.ContextMenuInfo createContextMenuInfo(View view, int i, long l)
    {
        if(isHeaderOrFooterPosition(i))
        {
            return new AdapterView.AdapterContextMenuInfo(view, i, l);
        } else
        {
            i = getFlatPositionForConnector(i);
            ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getUnflattenedPos(i);
            ExpandableListPosition expandablelistposition = positionmetadata.position;
            l = getChildOrGroupId(expandablelistposition);
            long l1 = expandablelistposition.getPackedPosition();
            positionmetadata.recycle();
            return new ExpandableListContextMenuInfo(view, l1, l);
        }
    }

    protected void dispatchDraw(Canvas canvas)
    {
        int i;
        boolean flag;
        int k;
        byte byte0;
        int j1;
        int k1;
        int l1;
        int i2;
        Rect rect;
        int k2;
        int l2;
        super.dispatchDraw(canvas);
        if(mChildIndicator == null && mGroupIndicator == null)
            return;
        i = 0;
        int j2;
        if((mGroupFlags & 0x22) == 34)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            i = canvas.save();
            int j = mScrollX;
            int l = mScrollY;
            canvas.clipRect(mPaddingLeft + j, mPaddingTop + l, (mRight + j) - mLeft - mPaddingRight, (mBottom + l) - mTop - mPaddingBottom);
        }
        j1 = getHeaderViewsCount();
        k1 = mItemCount;
        l1 = getFooterViewsCount();
        i2 = mBottom;
        byte0 = -4;
        rect = mIndicatorRect;
        j2 = getChildCount();
        k2 = 0;
        l2 = mFirstPosition - j1;
        if(k2 >= j2) goto _L2; else goto _L1
_L1:
        if(l2 >= 0) goto _L4; else goto _L3
_L3:
        k = byte0;
_L6:
        k2++;
        l2++;
        byte0 = k;
        break MISSING_BLOCK_LABEL_162;
_L4:
        if(l2 <= k1 - l1 - j1 - 1)
            break MISSING_BLOCK_LABEL_221;
_L2:
        if(flag)
            canvas.restoreToCount(i);
        return;
        View view = getChildAt(k2);
        int i3 = view.getTop();
        int j3 = view.getBottom();
        k = byte0;
        if(j3 >= 0)
        {
            k = byte0;
            if(i3 <= i2)
            {
                ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getUnflattenedPos(l2);
                boolean flag1 = isLayoutRtl();
                int k3 = getWidth();
                k = byte0;
                if(positionmetadata.position.type != byte0)
                {
                    if(positionmetadata.position.type == 1)
                    {
                        int i1;
                        Drawable drawable;
                        if(mChildIndicatorLeft == -1)
                            i1 = mIndicatorLeft;
                        else
                            i1 = mChildIndicatorLeft;
                        rect.left = i1;
                        if(mChildIndicatorRight == -1)
                            i1 = mIndicatorRight;
                        else
                            i1 = mChildIndicatorRight;
                        rect.right = i1;
                    } else
                    {
                        rect.left = mIndicatorLeft;
                        rect.right = mIndicatorRight;
                    }
                    if(flag1)
                    {
                        i1 = rect.left;
                        rect.left = k3 - rect.right;
                        rect.right = k3 - i1;
                        rect.left = rect.left - mPaddingRight;
                        rect.right = rect.right - mPaddingRight;
                    } else
                    {
                        rect.left = rect.left + mPaddingLeft;
                        rect.right = rect.right + mPaddingLeft;
                    }
                    k = positionmetadata.position.type;
                }
                if(rect.left != rect.right)
                {
                    if(mStackFromBottom)
                    {
                        rect.top = i3;
                        rect.bottom = j3;
                    } else
                    {
                        rect.top = i3;
                        rect.bottom = j3;
                    }
                    drawable = getIndicator(positionmetadata);
                    if(drawable != null)
                    {
                        drawable.setBounds(rect);
                        drawable.draw(canvas);
                    }
                }
                positionmetadata.recycle();
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    void drawDivider(Canvas canvas, Rect rect, int i)
    {
        int j = i + mFirstPosition;
        if(j >= 0)
        {
            i = getFlatPositionForConnector(j);
            ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getUnflattenedPos(i);
            if(positionmetadata.position.type == 1 || positionmetadata.isExpanded() && positionmetadata.groupMetadata.lastChildFlPos != positionmetadata.groupMetadata.flPos)
            {
                Drawable drawable = mChildDivider;
                drawable.setBounds(rect);
                drawable.draw(canvas);
                positionmetadata.recycle();
                return;
            }
            positionmetadata.recycle();
        }
        super.drawDivider(canvas, rect, j);
    }

    public boolean expandGroup(int i)
    {
        return expandGroup(i, false);
    }

    public boolean expandGroup(int i, boolean flag)
    {
        ExpandableListPosition expandablelistposition = ExpandableListPosition.obtain(2, i, -1, -1);
        ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getFlattenedPos(expandablelistposition);
        expandablelistposition.recycle();
        boolean flag1 = mConnector.expandGroup(positionmetadata);
        if(mOnGroupExpandListener != null)
            mOnGroupExpandListener.onGroupExpand(i);
        if(flag)
        {
            int j = positionmetadata.position.flatListPos + getHeaderViewsCount();
            smoothScrollToPosition(mAdapter.getChildrenCount(i) + j, j);
        }
        positionmetadata.recycle();
        return flag1;
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ExpandableListView.getName();
    }

    public ListAdapter getAdapter()
    {
        return super.getAdapter();
    }

    public ExpandableListAdapter getExpandableListAdapter()
    {
        return mAdapter;
    }

    public long getExpandableListPosition(int i)
    {
        if(isHeaderOrFooterPosition(i))
        {
            return 0xffffffffL;
        } else
        {
            i = getFlatPositionForConnector(i);
            ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getUnflattenedPos(i);
            long l = positionmetadata.position.getPackedPosition();
            positionmetadata.recycle();
            return l;
        }
    }

    public int getFlatListPosition(long l)
    {
        ExpandableListPosition expandablelistposition = ExpandableListPosition.obtainPosition(l);
        ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getFlattenedPos(expandablelistposition);
        expandablelistposition.recycle();
        int i = positionmetadata.position.flatListPos;
        positionmetadata.recycle();
        return getAbsoluteFlatPosition(i);
    }

    public long getSelectedId()
    {
        long l = getSelectedPosition();
        if(l == 0xffffffffL)
            return -1L;
        int i = getPackedPositionGroup(l);
        if(getPackedPositionType(l) == 0)
            return mAdapter.getGroupId(i);
        else
            return mAdapter.getChildId(i, getPackedPositionChild(l));
    }

    public long getSelectedPosition()
    {
        return getExpandableListPosition(getSelectedItemPosition());
    }

    boolean handleItemClick(View view, int i, long l)
    {
        ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getUnflattenedPos(i);
        l = getChildOrGroupId(positionmetadata.position);
        boolean flag;
        if(positionmetadata.position.type == 2)
        {
            if(mOnGroupClickListener != null && mOnGroupClickListener.onGroupClick(this, view, positionmetadata.position.groupPos, l))
            {
                positionmetadata.recycle();
                return true;
            }
            if(positionmetadata.isExpanded())
            {
                mConnector.collapseGroup(positionmetadata);
                playSoundEffect(0);
                if(mOnGroupCollapseListener != null)
                    mOnGroupCollapseListener.onGroupCollapse(positionmetadata.position.groupPos);
            } else
            {
                mConnector.expandGroup(positionmetadata);
                playSoundEffect(0);
                if(mOnGroupExpandListener != null)
                    mOnGroupExpandListener.onGroupExpand(positionmetadata.position.groupPos);
                int j = positionmetadata.position.groupPos;
                i = positionmetadata.position.flatListPos + getHeaderViewsCount();
                smoothScrollToPosition(mAdapter.getChildrenCount(j) + i, i);
            }
            flag = true;
        } else
        {
            if(mOnChildClickListener != null)
            {
                playSoundEffect(0);
                return mOnChildClickListener.onChildClick(this, view, positionmetadata.position.groupPos, positionmetadata.position.childPos, l);
            }
            flag = false;
        }
        positionmetadata.recycle();
        return flag;
    }

    public boolean isGroupExpanded(int i)
    {
        return mConnector.isGroupExpanded(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        if(mConnector != null && ((SavedState) (parcelable)).expandedGroupMetadataList != null)
            mConnector.setExpandedGroupMetadataList(((SavedState) (parcelable)).expandedGroupMetadataList);
    }

    public void onRtlPropertiesChanged(int i)
    {
        resolveIndicator();
        resolveChildIndicator();
    }

    public Parcelable onSaveInstanceState()
    {
        ArrayList arraylist = null;
        Parcelable parcelable = super.onSaveInstanceState();
        if(mConnector != null)
            arraylist = mConnector.getExpandedGroupMetadataList();
        return new SavedState(parcelable, arraylist);
    }

    public boolean performItemClick(View view, int i, long l)
    {
        if(isHeaderOrFooterPosition(i))
            return super.performItemClick(view, i, l);
        else
            return handleItemClick(view, getFlatPositionForConnector(i), l);
    }

    public void setAdapter(ExpandableListAdapter expandablelistadapter)
    {
        mAdapter = expandablelistadapter;
        if(expandablelistadapter != null)
            mConnector = new ExpandableListConnector(expandablelistadapter);
        else
            mConnector = null;
        super.setAdapter(mConnector);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        throw new RuntimeException("For ExpandableListView, use setAdapter(ExpandableListAdapter) instead of setAdapter(ListAdapter)");
    }

    public void setChildDivider(Drawable drawable)
    {
        mChildDivider = drawable;
    }

    public void setChildIndicator(Drawable drawable)
    {
        mChildIndicator = drawable;
    }

    public void setChildIndicatorBounds(int i, int j)
    {
        mChildIndicatorLeft = i;
        mChildIndicatorRight = j;
        resolveChildIndicator();
    }

    public void setChildIndicatorBoundsRelative(int i, int j)
    {
        mChildIndicatorStart = i;
        mChildIndicatorEnd = j;
        resolveChildIndicator();
    }

    public void setGroupIndicator(Drawable drawable)
    {
        mGroupIndicator = drawable;
        if(mIndicatorRight == 0 && mGroupIndicator != null)
            mIndicatorRight = mIndicatorLeft + mGroupIndicator.getIntrinsicWidth();
    }

    public void setIndicatorBounds(int i, int j)
    {
        mIndicatorLeft = i;
        mIndicatorRight = j;
        resolveIndicator();
    }

    public void setIndicatorBoundsRelative(int i, int j)
    {
        mIndicatorStart = i;
        mIndicatorEnd = j;
        resolveIndicator();
    }

    public void setOnChildClickListener(OnChildClickListener onchildclicklistener)
    {
        mOnChildClickListener = onchildclicklistener;
    }

    public void setOnGroupClickListener(OnGroupClickListener ongroupclicklistener)
    {
        mOnGroupClickListener = ongroupclicklistener;
    }

    public void setOnGroupCollapseListener(OnGroupCollapseListener ongroupcollapselistener)
    {
        mOnGroupCollapseListener = ongroupcollapselistener;
    }

    public void setOnGroupExpandListener(OnGroupExpandListener ongroupexpandlistener)
    {
        mOnGroupExpandListener = ongroupexpandlistener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onitemclicklistener)
    {
        super.setOnItemClickListener(onitemclicklistener);
    }

    public boolean setSelectedChild(int i, int j, boolean flag)
    {
        ExpandableListPosition expandablelistposition = ExpandableListPosition.obtainChildPosition(i, j);
        ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getFlattenedPos(expandablelistposition);
        ExpandableListConnector.PositionMetadata positionmetadata2 = positionmetadata;
        if(positionmetadata == null)
        {
            if(!flag)
                return false;
            expandGroup(i);
            ExpandableListConnector.PositionMetadata positionmetadata1 = mConnector.getFlattenedPos(expandablelistposition);
            positionmetadata2 = positionmetadata1;
            if(positionmetadata1 == null)
                throw new IllegalStateException("Could not find child");
        }
        super.setSelection(getAbsoluteFlatPosition(positionmetadata2.position.flatListPos));
        expandablelistposition.recycle();
        positionmetadata2.recycle();
        return true;
    }

    public void setSelectedGroup(int i)
    {
        ExpandableListPosition expandablelistposition = ExpandableListPosition.obtainGroupPosition(i);
        ExpandableListConnector.PositionMetadata positionmetadata = mConnector.getFlattenedPos(expandablelistposition);
        expandablelistposition.recycle();
        super.setSelection(getAbsoluteFlatPosition(positionmetadata.position.flatListPos));
        positionmetadata.recycle();
    }

    public static final int CHILD_INDICATOR_INHERIT = -1;
    private static final int CHILD_LAST_STATE_SET[] = {
        0x10100a6
    };
    private static final int EMPTY_STATE_SET[];
    private static final int GROUP_EMPTY_STATE_SET[] = {
        0x10100a9
    };
    private static final int GROUP_EXPANDED_EMPTY_STATE_SET[] = {
        0x10100a8, 0x10100a9
    };
    private static final int GROUP_EXPANDED_STATE_SET[] = {
        0x10100a8
    };
    private static final int GROUP_STATE_SETS[][];
    private static final int INDICATOR_UNDEFINED = -2;
    private static final long PACKED_POSITION_INT_MASK_CHILD = -1L;
    private static final long PACKED_POSITION_INT_MASK_GROUP = 0x7fffffffL;
    private static final long PACKED_POSITION_MASK_CHILD = 0xffffffffL;
    private static final long PACKED_POSITION_MASK_GROUP = 0x7fffffff00000000L;
    private static final long PACKED_POSITION_MASK_TYPE = 0x8000000000000000L;
    private static final long PACKED_POSITION_SHIFT_GROUP = 32L;
    private static final long PACKED_POSITION_SHIFT_TYPE = 63L;
    public static final int PACKED_POSITION_TYPE_CHILD = 1;
    public static final int PACKED_POSITION_TYPE_GROUP = 0;
    public static final int PACKED_POSITION_TYPE_NULL = 2;
    public static final long PACKED_POSITION_VALUE_NULL = 0xffffffffL;
    private ExpandableListAdapter mAdapter;
    private Drawable mChildDivider;
    private Drawable mChildIndicator;
    private int mChildIndicatorEnd;
    private int mChildIndicatorLeft;
    private int mChildIndicatorRight;
    private int mChildIndicatorStart;
    private ExpandableListConnector mConnector;
    private Drawable mGroupIndicator;
    private int mIndicatorEnd;
    private int mIndicatorLeft;
    private final Rect mIndicatorRect;
    private int mIndicatorRight;
    private int mIndicatorStart;
    private OnChildClickListener mOnChildClickListener;
    private OnGroupClickListener mOnGroupClickListener;
    private OnGroupCollapseListener mOnGroupCollapseListener;
    private OnGroupExpandListener mOnGroupExpandListener;

    static 
    {
        EMPTY_STATE_SET = new int[0];
        GROUP_STATE_SETS = (new int[][] {
            EMPTY_STATE_SET, GROUP_EXPANDED_STATE_SET, GROUP_EMPTY_STATE_SET, GROUP_EXPANDED_EMPTY_STATE_SET
        });
    }
}
