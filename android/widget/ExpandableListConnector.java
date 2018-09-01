// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.database.DataSetObserver;
import android.os.*;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;

// Referenced classes of package android.widget:
//            BaseAdapter, Filterable, ExpandableListAdapter, ExpandableListPosition, 
//            HeterogeneousExpandableList, Filter

class ExpandableListConnector extends BaseAdapter
    implements Filterable
{
    static class GroupMetadata
        implements Parcelable, Comparable
    {

        static GroupMetadata obtain(int i, int j, int k, long l)
        {
            GroupMetadata groupmetadata = new GroupMetadata();
            groupmetadata.flPos = i;
            groupmetadata.lastChildFlPos = j;
            groupmetadata.gPos = k;
            groupmetadata.gId = l;
            return groupmetadata;
        }

        public int compareTo(GroupMetadata groupmetadata)
        {
            if(groupmetadata == null)
                throw new IllegalArgumentException();
            else
                return gPos - groupmetadata.gPos;
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((GroupMetadata)obj);
        }

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(flPos);
            parcel.writeInt(lastChildFlPos);
            parcel.writeInt(gPos);
            parcel.writeLong(gId);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public GroupMetadata createFromParcel(Parcel parcel)
            {
                return GroupMetadata.obtain(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong());
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public GroupMetadata[] newArray(int i)
            {
                return new GroupMetadata[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        static final int REFRESH = -1;
        int flPos;
        long gId;
        int gPos;
        int lastChildFlPos;


        private GroupMetadata()
        {
        }
    }

    protected class MyDataSetObserver extends DataSetObserver
    {

        public void onChanged()
        {
            ExpandableListConnector._2D_wrap0(ExpandableListConnector.this, true, true);
            notifyDataSetChanged();
        }

        public void onInvalidated()
        {
            ExpandableListConnector._2D_wrap0(ExpandableListConnector.this, true, true);
            notifyDataSetInvalidated();
        }

        final ExpandableListConnector this$0;

        protected MyDataSetObserver()
        {
            this$0 = ExpandableListConnector.this;
            super();
        }
    }

    public static class PositionMetadata
    {

        private static PositionMetadata getRecycledOrCreate()
        {
            ArrayList arraylist = sPool;
            arraylist;
            JVM INSTR monitorenter ;
            PositionMetadata positionmetadata;
            if(sPool.size() <= 0)
                break MISSING_BLOCK_LABEL_34;
            positionmetadata = (PositionMetadata)sPool.remove(0);
            arraylist;
            JVM INSTR monitorexit ;
            positionmetadata.resetState();
            return positionmetadata;
            positionmetadata = new PositionMetadata();
            arraylist;
            JVM INSTR monitorexit ;
            return positionmetadata;
            Exception exception;
            exception;
            throw exception;
        }

        static PositionMetadata obtain(int i, int j, int k, int l, GroupMetadata groupmetadata, int i1)
        {
            PositionMetadata positionmetadata = getRecycledOrCreate();
            positionmetadata.position = ExpandableListPosition.obtain(j, k, l, i);
            positionmetadata.groupMetadata = groupmetadata;
            positionmetadata.groupInsertIndex = i1;
            return positionmetadata;
        }

        private void resetState()
        {
            if(position != null)
            {
                position.recycle();
                position = null;
            }
            groupMetadata = null;
            groupInsertIndex = 0;
        }

        public boolean isExpanded()
        {
            boolean flag;
            if(groupMetadata != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void recycle()
        {
            resetState();
            ArrayList arraylist = sPool;
            arraylist;
            JVM INSTR monitorenter ;
            if(sPool.size() < 5)
                sPool.add(this);
            arraylist;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private static final int MAX_POOL_SIZE = 5;
        private static ArrayList sPool = new ArrayList(5);
        public int groupInsertIndex;
        public GroupMetadata groupMetadata;
        public ExpandableListPosition position;


        private PositionMetadata()
        {
        }
    }


    static void _2D_wrap0(ExpandableListConnector expandablelistconnector, boolean flag, boolean flag1)
    {
        expandablelistconnector.refreshExpGroupMetadataList(flag, flag1);
    }

    public ExpandableListConnector(ExpandableListAdapter expandablelistadapter)
    {
        mMaxExpGroupCount = 0x7fffffff;
        mExpGroupMetadataList = new ArrayList();
        setExpandableListAdapter(expandablelistadapter);
    }

    private void refreshExpGroupMetadataList(boolean flag, boolean flag1)
    {
        ArrayList arraylist = mExpGroupMetadataList;
        int i = arraylist.size();
        boolean flag2 = false;
        mTotalExpChildrenCount = 0;
        int j = i;
        if(flag1)
        {
            boolean flag3 = false;
            for(int l = i - 1; l >= 0;)
            {
                GroupMetadata groupmetadata = (GroupMetadata)arraylist.get(l);
                int j1 = findGroupPosition(groupmetadata.gId, groupmetadata.gPos);
                int k1 = i;
                boolean flag4 = flag3;
                if(j1 != groupmetadata.gPos)
                {
                    j = i;
                    if(j1 == -1)
                    {
                        arraylist.remove(l);
                        j = i - 1;
                    }
                    groupmetadata.gPos = j1;
                    k1 = j;
                    flag4 = flag3;
                    if(!flag3)
                    {
                        flag4 = true;
                        k1 = j;
                    }
                }
                l--;
                i = k1;
                flag3 = flag4;
            }

            j = i;
            if(flag3)
            {
                Collections.sort(arraylist);
                j = i;
            }
        }
        int k = 0;
        i = 0;
        int i1 = ((flag2) ? 1 : 0);
        while(i < j) 
        {
            GroupMetadata groupmetadata1 = (GroupMetadata)arraylist.get(i);
            int l1;
            if(groupmetadata1.lastChildFlPos == -1 || flag)
                l1 = mExpandableListAdapter.getChildrenCount(groupmetadata1.gPos);
            else
                l1 = groupmetadata1.lastChildFlPos - groupmetadata1.flPos;
            mTotalExpChildrenCount = mTotalExpChildrenCount + l1;
            i1 += groupmetadata1.gPos - k;
            k = groupmetadata1.gPos;
            groupmetadata1.flPos = i1;
            i1 += l1;
            groupmetadata1.lastChildFlPos = i1;
            i++;
        }
    }

    public boolean areAllItemsEnabled()
    {
        return mExpandableListAdapter.areAllItemsEnabled();
    }

    boolean collapseGroup(int i)
    {
        ExpandableListPosition expandablelistposition = ExpandableListPosition.obtain(2, i, -1, -1);
        PositionMetadata positionmetadata = getFlattenedPos(expandablelistposition);
        expandablelistposition.recycle();
        if(positionmetadata == null)
        {
            return false;
        } else
        {
            boolean flag = collapseGroup(positionmetadata);
            positionmetadata.recycle();
            return flag;
        }
    }

    boolean collapseGroup(PositionMetadata positionmetadata)
    {
        if(positionmetadata.groupMetadata == null)
        {
            return false;
        } else
        {
            mExpGroupMetadataList.remove(positionmetadata.groupMetadata);
            refreshExpGroupMetadataList(false, false);
            notifyDataSetChanged();
            mExpandableListAdapter.onGroupCollapsed(positionmetadata.groupMetadata.gPos);
            return true;
        }
    }

    boolean expandGroup(int i)
    {
        ExpandableListPosition expandablelistposition = ExpandableListPosition.obtain(2, i, -1, -1);
        PositionMetadata positionmetadata = getFlattenedPos(expandablelistposition);
        expandablelistposition.recycle();
        boolean flag = expandGroup(positionmetadata);
        positionmetadata.recycle();
        return flag;
    }

    boolean expandGroup(PositionMetadata positionmetadata)
    {
        if(positionmetadata.position.groupPos < 0)
            throw new RuntimeException("Need group");
        if(mMaxExpGroupCount == 0)
            return false;
        if(positionmetadata.groupMetadata != null)
            return false;
        if(mExpGroupMetadataList.size() >= mMaxExpGroupCount)
        {
            GroupMetadata groupmetadata = (GroupMetadata)mExpGroupMetadataList.get(0);
            int i = mExpGroupMetadataList.indexOf(groupmetadata);
            collapseGroup(groupmetadata.gPos);
            if(positionmetadata.groupInsertIndex > i)
                positionmetadata.groupInsertIndex = positionmetadata.groupInsertIndex - 1;
        }
        GroupMetadata groupmetadata1 = GroupMetadata.obtain(-1, -1, positionmetadata.position.groupPos, mExpandableListAdapter.getGroupId(positionmetadata.position.groupPos));
        mExpGroupMetadataList.add(positionmetadata.groupInsertIndex, groupmetadata1);
        refreshExpGroupMetadataList(false, false);
        notifyDataSetChanged();
        mExpandableListAdapter.onGroupExpanded(groupmetadata1.gPos);
        return true;
    }

    int findGroupPosition(long l, int i)
    {
        int j;
        int k;
        long l1;
        int i1;
        int j1;
        ExpandableListAdapter expandablelistadapter;
        boolean flag;
        boolean flag1;
        j = mExpandableListAdapter.getGroupCount();
        if(j == 0)
            return -1;
        if(l == 0x8000000000000000L)
            return -1;
        k = Math.min(j - 1, Math.max(0, i));
        l1 = SystemClock.uptimeMillis();
        i1 = k;
        j1 = k;
        i = 0;
        expandablelistadapter = getAdapter();
        if(expandablelistadapter == null)
            return -1;
_L4:
        if(SystemClock.uptimeMillis() > l1 + 100L)
            break; /* Loop/switch isn't completed */
        if(expandablelistadapter.getGroupId(k) == l)
            return k;
        if(j1 == j - 1)
            flag1 = true;
        else
            flag1 = false;
        if(i1 == 0)
            flag = true;
        else
            flag = false;
        if(!flag1 || !flag) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        if(flag || i != 0 && flag1 ^ true)
        {
            k = ++j1;
            i = 0;
        } else
        if(flag1 || i == 0 && flag ^ true)
        {
            k = --i1;
            i = 1;
        }
        if(true) goto _L4; else goto _L3
_L3:
        return -1;
    }

    ExpandableListAdapter getAdapter()
    {
        return mExpandableListAdapter;
    }

    public int getCount()
    {
        return mExpandableListAdapter.getGroupCount() + mTotalExpChildrenCount;
    }

    ArrayList getExpandedGroupMetadataList()
    {
        return mExpGroupMetadataList;
    }

    public Filter getFilter()
    {
        ExpandableListAdapter expandablelistadapter = getAdapter();
        if(expandablelistadapter instanceof Filterable)
            return ((Filterable)expandablelistadapter).getFilter();
        else
            return null;
    }

    PositionMetadata getFlattenedPos(ExpandableListPosition expandablelistposition)
    {
        Object obj = mExpGroupMetadataList;
        int i = ((ArrayList) (obj)).size();
        int k = 0;
        int l = i - 1;
        int i1 = 0;
        if(i == 0)
            return PositionMetadata.obtain(expandablelistposition.groupPos, expandablelistposition.type, expandablelistposition.groupPos, expandablelistposition.childPos, null, 0);
        while(k <= l) 
        {
            int j = (l - k) / 2 + k;
            GroupMetadata groupmetadata = (GroupMetadata)((ArrayList) (obj)).get(j);
            if(expandablelistposition.groupPos > groupmetadata.gPos)
            {
                k = j + 1;
                i1 = j;
            } else
            if(expandablelistposition.groupPos < groupmetadata.gPos)
            {
                l = j - 1;
                i1 = j;
            } else
            {
                i1 = j;
                if(expandablelistposition.groupPos == groupmetadata.gPos)
                {
                    if(expandablelistposition.type == 2)
                        return PositionMetadata.obtain(groupmetadata.flPos, expandablelistposition.type, expandablelistposition.groupPos, expandablelistposition.childPos, groupmetadata, j);
                    if(expandablelistposition.type == 1)
                        return PositionMetadata.obtain(groupmetadata.flPos + expandablelistposition.childPos + 1, expandablelistposition.type, expandablelistposition.groupPos, expandablelistposition.childPos, groupmetadata, j);
                    else
                        return null;
                }
            }
        }
        if(expandablelistposition.type != 2)
            return null;
        if(k > i1)
        {
            obj = (GroupMetadata)((ArrayList) (obj)).get(k - 1);
            return PositionMetadata.obtain(((GroupMetadata) (obj)).lastChildFlPos + (expandablelistposition.groupPos - ((GroupMetadata) (obj)).gPos), expandablelistposition.type, expandablelistposition.groupPos, expandablelistposition.childPos, null, k);
        }
        if(l < i1)
        {
            int j1 = l + 1;
            obj = (GroupMetadata)((ArrayList) (obj)).get(j1);
            return PositionMetadata.obtain(((GroupMetadata) (obj)).flPos - (((GroupMetadata) (obj)).gPos - expandablelistposition.groupPos), expandablelistposition.type, expandablelistposition.groupPos, expandablelistposition.childPos, null, j1);
        } else
        {
            return null;
        }
    }

    public Object getItem(int i)
    {
        PositionMetadata positionmetadata = getUnflattenedPos(i);
        Object obj;
        if(positionmetadata.position.type == 2)
            obj = mExpandableListAdapter.getGroup(positionmetadata.position.groupPos);
        else
        if(positionmetadata.position.type == 1)
            obj = mExpandableListAdapter.getChild(positionmetadata.position.groupPos, positionmetadata.position.childPos);
        else
            throw new RuntimeException("Flat list position is of unknown type");
        positionmetadata.recycle();
        return obj;
    }

    public long getItemId(int i)
    {
        PositionMetadata positionmetadata = getUnflattenedPos(i);
        long l = mExpandableListAdapter.getGroupId(positionmetadata.position.groupPos);
        long l1;
        if(positionmetadata.position.type == 2)
            l1 = mExpandableListAdapter.getCombinedGroupId(l);
        else
        if(positionmetadata.position.type == 1)
        {
            l1 = mExpandableListAdapter.getChildId(positionmetadata.position.groupPos, positionmetadata.position.childPos);
            l1 = mExpandableListAdapter.getCombinedChildId(l, l1);
        } else
        {
            throw new RuntimeException("Flat list position is of unknown type");
        }
        positionmetadata.recycle();
        return l1;
    }

    public int getItemViewType(int i)
    {
        PositionMetadata positionmetadata = getUnflattenedPos(i);
        ExpandableListPosition expandablelistposition = positionmetadata.position;
        if(mExpandableListAdapter instanceof HeterogeneousExpandableList)
        {
            HeterogeneousExpandableList heterogeneousexpandablelist = (HeterogeneousExpandableList)mExpandableListAdapter;
            if(expandablelistposition.type == 2)
            {
                i = heterogeneousexpandablelist.getGroupType(expandablelistposition.groupPos);
            } else
            {
                i = heterogeneousexpandablelist.getChildType(expandablelistposition.groupPos, expandablelistposition.childPos);
                i = heterogeneousexpandablelist.getGroupTypeCount() + i;
            }
        } else
        if(expandablelistposition.type == 2)
            i = 0;
        else
            i = 1;
        positionmetadata.recycle();
        return i;
    }

    PositionMetadata getUnflattenedPos(int i)
    {
        Object obj = mExpGroupMetadataList;
        int j = ((ArrayList) (obj)).size();
        int l = 0;
        int i1 = j - 1;
        int j1 = 0;
        if(j == 0)
            return PositionMetadata.obtain(i, 2, i, -1, null, 0);
        while(l <= i1) 
        {
            int k = (i1 - l) / 2 + l;
            GroupMetadata groupmetadata = (GroupMetadata)((ArrayList) (obj)).get(k);
            if(i > groupmetadata.lastChildFlPos)
            {
                l = k + 1;
                j1 = k;
            } else
            if(i < groupmetadata.flPos)
            {
                i1 = k - 1;
                j1 = k;
            } else
            {
                if(i == groupmetadata.flPos)
                    return PositionMetadata.obtain(i, 2, groupmetadata.gPos, -1, groupmetadata, k);
                j1 = k;
                if(i <= groupmetadata.lastChildFlPos)
                {
                    l = groupmetadata.flPos;
                    return PositionMetadata.obtain(i, 1, groupmetadata.gPos, i - (l + 1), groupmetadata, k);
                }
            }
        }
        if(l > j1)
        {
            obj = (GroupMetadata)((ArrayList) (obj)).get(l - 1);
            j1 = (i - ((GroupMetadata) (obj)).lastChildFlPos) + ((GroupMetadata) (obj)).gPos;
        } else
        if(i1 < j1)
        {
            l = i1 + 1;
            obj = (GroupMetadata)((ArrayList) (obj)).get(l);
            j1 = ((GroupMetadata) (obj)).gPos - (((GroupMetadata) (obj)).flPos - i);
        } else
        {
            throw new RuntimeException("Unknown state");
        }
        return PositionMetadata.obtain(i, 2, j1, -1, null, l);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        PositionMetadata positionmetadata = getUnflattenedPos(i);
        if(positionmetadata.position.type == 2)
            view = mExpandableListAdapter.getGroupView(positionmetadata.position.groupPos, positionmetadata.isExpanded(), view, viewgroup);
        else
        if(positionmetadata.position.type == 1)
        {
            boolean flag;
            if(positionmetadata.groupMetadata.lastChildFlPos == i)
                flag = true;
            else
                flag = false;
            view = mExpandableListAdapter.getChildView(positionmetadata.position.groupPos, positionmetadata.position.childPos, flag, view, viewgroup);
        } else
        {
            throw new RuntimeException("Flat list position is of unknown type");
        }
        positionmetadata.recycle();
        return view;
    }

    public int getViewTypeCount()
    {
        if(mExpandableListAdapter instanceof HeterogeneousExpandableList)
        {
            HeterogeneousExpandableList heterogeneousexpandablelist = (HeterogeneousExpandableList)mExpandableListAdapter;
            return heterogeneousexpandablelist.getGroupTypeCount() + heterogeneousexpandablelist.getChildTypeCount();
        } else
        {
            return 2;
        }
    }

    public boolean hasStableIds()
    {
        return mExpandableListAdapter.hasStableIds();
    }

    public boolean isEmpty()
    {
        ExpandableListAdapter expandablelistadapter = getAdapter();
        boolean flag;
        if(expandablelistadapter != null)
            flag = expandablelistadapter.isEmpty();
        else
            flag = true;
        return flag;
    }

    public boolean isEnabled(int i)
    {
        PositionMetadata positionmetadata = getUnflattenedPos(i);
        ExpandableListPosition expandablelistposition = positionmetadata.position;
        boolean flag;
        if(expandablelistposition.type == 1)
            flag = mExpandableListAdapter.isChildSelectable(expandablelistposition.groupPos, expandablelistposition.childPos);
        else
            flag = true;
        positionmetadata.recycle();
        return flag;
    }

    public boolean isGroupExpanded(int i)
    {
        for(int j = mExpGroupMetadataList.size() - 1; j >= 0; j--)
            if(((GroupMetadata)mExpGroupMetadataList.get(j)).gPos == i)
                return true;

        return false;
    }

    public void setExpandableListAdapter(ExpandableListAdapter expandablelistadapter)
    {
        if(mExpandableListAdapter != null)
            mExpandableListAdapter.unregisterDataSetObserver(mDataSetObserver);
        mExpandableListAdapter = expandablelistadapter;
        expandablelistadapter.registerDataSetObserver(mDataSetObserver);
    }

    void setExpandedGroupMetadataList(ArrayList arraylist)
    {
        if(arraylist == null || mExpandableListAdapter == null)
            return;
        int i = mExpandableListAdapter.getGroupCount();
        for(int j = arraylist.size() - 1; j >= 0; j--)
            if(((GroupMetadata)arraylist.get(j)).gPos >= i)
                return;

        mExpGroupMetadataList = arraylist;
        refreshExpGroupMetadataList(true, false);
    }

    public void setMaxExpGroupCount(int i)
    {
        mMaxExpGroupCount = i;
    }

    private final DataSetObserver mDataSetObserver = new MyDataSetObserver();
    private ArrayList mExpGroupMetadataList;
    private ExpandableListAdapter mExpandableListAdapter;
    private int mMaxExpGroupCount;
    private int mTotalExpChildrenCount;
}
