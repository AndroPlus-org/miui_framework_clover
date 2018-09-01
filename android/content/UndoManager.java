// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.Parcel;
import android.text.TextUtils;
import android.util.ArrayMap;
import java.util.ArrayList;

// Referenced classes of package android.content:
//            UndoOperation, UndoOwner

public class UndoManager
{
    static final class UndoState
    {

        void addOperation(UndoOperation undooperation)
        {
            if(mOperations.contains(undooperation))
                throw new IllegalStateException((new StringBuilder()).append("Already holds ").append(undooperation).toString());
            mOperations.add(undooperation);
            if(mRecent == null)
            {
                mRecent = new ArrayList();
                mRecent.add(undooperation);
            }
            undooperation = undooperation.mOwner;
            undooperation.mOpCount = ((UndoOwner) (undooperation)).mOpCount + 1;
        }

        boolean canMerge()
        {
            boolean flag;
            if(mCanMerge)
                flag = mExecuted ^ true;
            else
                flag = false;
            return flag;
        }

        void commit()
        {
            int i;
            int j;
            if(mRecent != null)
                i = mRecent.size();
            else
                i = 0;
            for(j = 0; j < i; j++)
                ((UndoOperation)mRecent.get(j)).commit();

            mRecent = null;
        }

        int countOperations()
        {
            return mOperations.size();
        }

        void destroy()
        {
            for(int i = mOperations.size() - 1; i >= 0; i--)
            {
                UndoOwner undoowner = ((UndoOperation)mOperations.get(i)).mOwner;
                undoowner.mOpCount = undoowner.mOpCount - 1;
                if(undoowner.mOpCount > 0)
                    continue;
                if(undoowner.mOpCount < 0)
                    throw new IllegalStateException((new StringBuilder()).append("Underflow of op count on owner ").append(undoowner).append(" in op ").append(mOperations.get(i)).toString());
                mManager.removeOwner(undoowner);
            }

        }

        int getCommitId()
        {
            return mCommitId;
        }

        CharSequence getLabel()
        {
            return mLabel;
        }

        UndoOperation getLastOperation(Class class1, UndoOwner undoowner)
        {
            Object obj = null;
            int i = mOperations.size();
            if(class1 == null && undoowner == null)
            {
                class1 = obj;
                if(i > 0)
                    class1 = (UndoOperation)mOperations.get(i - 1);
                return class1;
            }
            for(i--; i >= 0;)
            {
                UndoOperation undooperation = (UndoOperation)mOperations.get(i);
                if(undoowner != null && undooperation.getOwner() != undoowner)
                    i--;
                else
                if(class1 != null && undooperation.getClass() != class1)
                    return null;
                else
                    return undooperation;
            }

            return null;
        }

        boolean hasData()
        {
            for(int i = mOperations.size() - 1; i >= 0; i--)
                if(((UndoOperation)mOperations.get(i)).hasData())
                    return true;

            return false;
        }

        boolean hasMultipleOwners()
        {
            int i = mOperations.size();
            if(i <= 1)
                return false;
            UndoOwner undoowner = ((UndoOperation)mOperations.get(0)).getOwner();
            for(int j = 1; j < i; j++)
                if(((UndoOperation)mOperations.get(j)).getOwner() != undoowner)
                    return true;

            return false;
        }

        boolean hasOperation(UndoOwner undoowner)
        {
            int i = mOperations.size();
            if(undoowner == null)
            {
                boolean flag;
                if(i != 0)
                    flag = true;
                else
                    flag = false;
                return flag;
            }
            for(int j = 0; j < i; j++)
                if(((UndoOperation)mOperations.get(j)).getOwner() == undoowner)
                    return true;

            return false;
        }

        void makeExecuted()
        {
            mExecuted = true;
        }

        boolean matchOwner(UndoOwner undoowner)
        {
            for(int i = mOperations.size() - 1; i >= 0; i--)
                if(((UndoOperation)mOperations.get(i)).matchOwner(undoowner))
                    return true;

            return false;
        }

        void redo()
        {
            int i = mOperations.size();
            for(int j = 0; j < i; j++)
                ((UndoOperation)mOperations.get(j)).redo();

        }

        boolean setCanMerge(boolean flag)
        {
            if(flag && mExecuted)
            {
                return false;
            } else
            {
                mCanMerge = flag;
                return true;
            }
        }

        void setLabel(CharSequence charsequence)
        {
            mLabel = charsequence;
        }

        void undo()
        {
            for(int i = mOperations.size() - 1; i >= 0; i--)
                ((UndoOperation)mOperations.get(i)).undo();

        }

        void updateLabel(CharSequence charsequence)
        {
            if(mLabel != null)
                mLabel = charsequence;
        }

        void writeToParcel(Parcel parcel)
        {
            int i = 1;
            if(mRecent != null)
                throw new IllegalStateException("Can't save state before committing");
            parcel.writeInt(mCommitId);
            int j;
            if(mCanMerge)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            if(mExecuted)
                j = i;
            else
                j = 0;
            parcel.writeInt(j);
            TextUtils.writeToParcel(mLabel, parcel, 0);
            i = mOperations.size();
            parcel.writeInt(i);
            for(j = 0; j < i; j++)
            {
                UndoOperation undooperation = (UndoOperation)mOperations.get(j);
                mManager.saveOwner(undooperation.mOwner, parcel);
                parcel.writeParcelable(undooperation, 0);
            }

        }

        private boolean mCanMerge;
        private final int mCommitId;
        private boolean mExecuted;
        private CharSequence mLabel;
        private final UndoManager mManager;
        private final ArrayList mOperations;
        private ArrayList mRecent;

        UndoState(UndoManager undomanager, int i)
        {
            mOperations = new ArrayList();
            mCanMerge = true;
            mManager = undomanager;
            mCommitId = i;
        }

        UndoState(UndoManager undomanager, Parcel parcel, ClassLoader classloader)
        {
            boolean flag = true;
            super();
            mOperations = new ArrayList();
            mCanMerge = true;
            mManager = undomanager;
            mCommitId = parcel.readInt();
            boolean flag1;
            int i;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            mCanMerge = flag1;
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            mExecuted = flag1;
            mLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            i = parcel.readInt();
            for(int j = 0; j < i; j++)
            {
                UndoOwner undoowner = mManager.restoreOwner(parcel);
                undomanager = (UndoOperation)parcel.readParcelable(classloader);
                undomanager.mOwner = undoowner;
                mOperations.add(undomanager);
            }

        }
    }


    public UndoManager()
    {
        mHistorySize = 20;
        mCommitId = 1;
    }

    private void createWorkingState()
    {
        int i = mCommitId;
        mCommitId = i + 1;
        mWorking = new UndoState(this, i);
        if(mCommitId < 0)
            mCommitId = 1;
    }

    private void pushWorkingState()
    {
        int i = mUndos.size() + 1;
        if(mWorking.hasData())
        {
            mUndos.add(mWorking);
            forgetRedos(null, -1);
            mWorking.commit();
            if(i >= 2)
                ((UndoState)mUndos.get(i - 2)).makeExecuted();
        } else
        {
            mWorking.destroy();
        }
        mWorking = null;
        if(mHistorySize >= 0 && i > mHistorySize)
            forgetUndos(null, i - mHistorySize);
    }

    public void addOperation(UndoOperation undooperation, int i)
    {
        if(mWorking == null)
            throw new IllegalStateException("Must be called during an update");
        if(undooperation.getOwner().mManager != this)
            throw new IllegalArgumentException("Given operation's owner is not in this undo manager.");
        if(i != 0 && mMerged ^ true && mWorking.hasData() ^ true)
        {
            UndoState undostate = getTopUndo(null);
            if(undostate != null && (i == 2 || undostate.hasMultipleOwners() ^ true) && undostate.canMerge() && undostate.hasOperation(undooperation.getOwner()))
            {
                mWorking.destroy();
                mWorking = undostate;
                mUndos.remove(undostate);
                mMerged = true;
            }
        }
        mWorking.addOperation(undooperation);
    }

    public void beginUpdate(CharSequence charsequence)
    {
        if(mInUndo)
            throw new IllegalStateException("Can't being update while performing undo/redo");
        if(mUpdateCount <= 0)
        {
            createWorkingState();
            mMerged = false;
            mUpdateCount = 0;
        }
        mWorking.updateLabel(charsequence);
        mUpdateCount = mUpdateCount + 1;
    }

    public int commitState(UndoOwner undoowner)
    {
        if(mWorking != null && mWorking.hasData())
        {
            if(undoowner == null || mWorking.hasOperation(undoowner))
            {
                mWorking.setCanMerge(false);
                int i = mWorking.getCommitId();
                pushWorkingState();
                createWorkingState();
                mMerged = true;
                return i;
            }
        } else
        {
            UndoState undostate = getTopUndo(null);
            if(undostate != null && (undoowner == null || undostate.hasOperation(undoowner)))
            {
                undostate.setCanMerge(false);
                return undostate.getCommitId();
            }
        }
        return -1;
    }

    public int countRedos(UndoOwner aundoowner[])
    {
        if(aundoowner == null)
            return mRedos.size();
        int i = 0;
        int j = 0;
        do
        {
            j = findNextState(mRedos, aundoowner, j);
            if(j >= 0)
            {
                i++;
                j++;
            } else
            {
                return i;
            }
        } while(true);
    }

    public int countUndos(UndoOwner aundoowner[])
    {
        if(aundoowner == null)
            return mUndos.size();
        int i = 0;
        int j = 0;
        do
        {
            j = findNextState(mUndos, aundoowner, j);
            if(j >= 0)
            {
                i++;
                j++;
            } else
            {
                return i;
            }
        } while(true);
    }

    public void endUpdate()
    {
        if(mWorking == null)
            throw new IllegalStateException("Must be called during an update");
        mUpdateCount = mUpdateCount - 1;
        if(mUpdateCount == 0)
            pushWorkingState();
    }

    int findNextState(ArrayList arraylist, UndoOwner aundoowner[], int i)
    {
        int j;
        j = arraylist.size();
        int k = i;
        if(i < 0)
            k = 0;
        if(k >= j)
            return -1;
        i = k;
        if(aundoowner == null)
            return k;
_L4:
        if(i < j)
        {
            if(matchOwners((UndoState)arraylist.get(i), aundoowner))
                return i;
        } else
        {
            return -1;
        }
        if(true) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        i++;
        if(true) goto _L4; else goto _L3
_L3:
    }

    int findPrevState(ArrayList arraylist, UndoOwner aundoowner[], int i)
    {
        int j = arraylist.size();
        int k = i;
        if(i == -1)
            k = j - 1;
        if(k >= j)
            return -1;
        i = k;
        if(aundoowner == null)
            return k;
_L4:
        if(i >= 0)
        {
            if(matchOwners((UndoState)arraylist.get(i), aundoowner))
                return i;
        } else
        {
            return -1;
        }
        if(true) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        i--;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int forgetRedos(UndoOwner aundoowner[], int i)
    {
        int j = i;
        if(i < 0)
            j = mRedos.size();
        i = 0;
        for(int k = 0; k < mRedos.size() && i < j;)
        {
            UndoState undostate = (UndoState)mRedos.get(k);
            if(j > 0 && matchOwners(undostate, aundoowner))
            {
                undostate.destroy();
                mRedos.remove(k);
                i++;
            } else
            {
                k++;
            }
        }

        return i;
    }

    public int forgetUndos(UndoOwner aundoowner[], int i)
    {
        int j = i;
        if(i < 0)
            j = mUndos.size();
        i = 0;
        for(int k = 0; k < mUndos.size() && i < j;)
        {
            UndoState undostate = (UndoState)mUndos.get(k);
            if(j > 0 && matchOwners(undostate, aundoowner))
            {
                undostate.destroy();
                mUndos.remove(k);
                i++;
            } else
            {
                k++;
            }
        }

        return i;
    }

    public int getHistorySize()
    {
        return mHistorySize;
    }

    public UndoOperation getLastOperation(int i)
    {
        return getLastOperation(null, null, i);
    }

    public UndoOperation getLastOperation(UndoOwner undoowner, int i)
    {
        return getLastOperation(null, undoowner, i);
    }

    public UndoOperation getLastOperation(Class class1, UndoOwner undoowner, int i)
    {
        if(mWorking == null)
            throw new IllegalStateException("Must be called during an update");
        if(i != 0 && mMerged ^ true && mWorking.hasData() ^ true)
        {
            UndoState undostate = getTopUndo(null);
            if(undostate != null && (i == 2 || undostate.hasMultipleOwners() ^ true) && undostate.canMerge())
            {
                UndoOperation undooperation = undostate.getLastOperation(class1, undoowner);
                if(undooperation != null && undooperation.allowMerge())
                {
                    mWorking.destroy();
                    mWorking = undostate;
                    mUndos.remove(undostate);
                    mMerged = true;
                    return undooperation;
                }
            }
        }
        return mWorking.getLastOperation(class1, undoowner);
    }

    public UndoOwner getOwner(String s, Object obj)
    {
        if(s == null)
            throw new NullPointerException("tag can't be null");
        if(obj == null)
            throw new NullPointerException("data can't be null");
        UndoOwner undoowner = (UndoOwner)mOwners.get(s);
        if(undoowner != null)
        {
            if(undoowner.mData != obj)
            {
                if(undoowner.mData != null)
                    throw new IllegalStateException((new StringBuilder()).append("Owner ").append(undoowner).append(" already exists with data ").append(undoowner.mData).append(" but giving different data ").append(obj).toString());
                undoowner.mData = obj;
            }
            return undoowner;
        } else
        {
            UndoOwner undoowner1 = new UndoOwner(s, this);
            undoowner1.mData = obj;
            mOwners.put(s, undoowner1);
            return undoowner1;
        }
    }

    public CharSequence getRedoLabel(UndoOwner aundoowner[])
    {
        Object obj = null;
        UndoState undostate = getTopRedo(aundoowner);
        aundoowner = obj;
        if(undostate != null)
            aundoowner = undostate.getLabel();
        return aundoowner;
    }

    UndoState getTopRedo(UndoOwner aundoowner[])
    {
        Object obj = null;
        if(mRedos.size() <= 0)
            return null;
        int i = findPrevState(mRedos, aundoowner, -1);
        aundoowner = obj;
        if(i >= 0)
            aundoowner = (UndoState)mRedos.get(i);
        return aundoowner;
    }

    UndoState getTopUndo(UndoOwner aundoowner[])
    {
        Object obj = null;
        if(mUndos.size() <= 0)
            return null;
        int i = findPrevState(mUndos, aundoowner, -1);
        aundoowner = obj;
        if(i >= 0)
            aundoowner = (UndoState)mUndos.get(i);
        return aundoowner;
    }

    public CharSequence getUndoLabel(UndoOwner aundoowner[])
    {
        Object obj = null;
        UndoState undostate = getTopUndo(aundoowner);
        aundoowner = obj;
        if(undostate != null)
            aundoowner = undostate.getLabel();
        return aundoowner;
    }

    public int getUpdateNestingLevel()
    {
        return mUpdateCount;
    }

    public boolean hasOperation(UndoOwner undoowner)
    {
        if(mWorking == null)
            throw new IllegalStateException("Must be called during an update");
        else
            return mWorking.hasOperation(undoowner);
    }

    public boolean isInUndo()
    {
        return mInUndo;
    }

    public boolean isInUpdate()
    {
        boolean flag = false;
        if(mUpdateCount > 0)
            flag = true;
        return flag;
    }

    boolean matchOwners(UndoState undostate, UndoOwner aundoowner[])
    {
        if(aundoowner == null)
            return true;
        for(int i = 0; i < aundoowner.length; i++)
            if(undostate.matchOwner(aundoowner[i]))
                return true;

        return false;
    }

    public int redo(UndoOwner aundoowner[], int i)
    {
        if(mWorking != null)
            throw new IllegalStateException("Can't be called during an update");
        boolean flag = false;
        int j = -1;
        mInUndo = true;
        int k = i;
        i = ((flag) ? 1 : 0);
        do
        {
            if(k <= 0)
                break;
            j = findPrevState(mRedos, aundoowner, j);
            if(j < 0)
                break;
            UndoState undostate = (UndoState)mRedos.remove(j);
            undostate.redo();
            mUndos.add(undostate);
            k--;
            i++;
        } while(true);
        mInUndo = false;
        return i;
    }

    void removeOwner(UndoOwner undoowner)
    {
    }

    public void restoreInstanceState(Parcel parcel, ClassLoader classloader)
    {
        if(mUpdateCount > 0)
            throw new IllegalStateException("Can't save state while updating");
        forgetUndos(null, -1);
        forgetRedos(null, -1);
        mHistorySize = parcel.readInt();
        mStateOwners = new UndoOwner[parcel.readInt()];
        do
        {
            int i = parcel.readInt();
            if(i != 0)
            {
                UndoState undostate = new UndoState(this, parcel, classloader);
                if(i == 1)
                    mUndos.add(0, undostate);
                else
                    mRedos.add(0, undostate);
            } else
            {
                return;
            }
        } while(true);
    }

    UndoOwner restoreOwner(Parcel parcel)
    {
        int i = parcel.readInt();
        UndoOwner undoowner = mStateOwners[i];
        UndoOwner undoowner1 = undoowner;
        if(undoowner == null)
        {
            String s = parcel.readString();
            int j = parcel.readInt();
            undoowner1 = new UndoOwner(s, this);
            undoowner1.mOpCount = j;
            mStateOwners[i] = undoowner1;
            mOwners.put(s, undoowner1);
        }
        return undoowner1;
    }

    public void saveInstanceState(Parcel parcel)
    {
        if(mUpdateCount > 0)
            throw new IllegalStateException("Can't save state while updating");
        mStateSeq = mStateSeq + 1;
        if(mStateSeq <= 0)
            mStateSeq = 0;
        mNextSavedIdx = 0;
        parcel.writeInt(mHistorySize);
        parcel.writeInt(mOwners.size());
        for(int i = mUndos.size(); i > 0;)
        {
            parcel.writeInt(1);
            i--;
            ((UndoState)mUndos.get(i)).writeToParcel(parcel);
        }

        for(int j = mRedos.size(); j > 0;)
        {
            parcel.writeInt(2);
            j--;
            ((UndoState)mRedos.get(j)).writeToParcel(parcel);
        }

        parcel.writeInt(0);
    }

    void saveOwner(UndoOwner undoowner, Parcel parcel)
    {
        if(undoowner.mStateSeq == mStateSeq)
        {
            parcel.writeInt(undoowner.mSavedIdx);
        } else
        {
            undoowner.mStateSeq = mStateSeq;
            undoowner.mSavedIdx = mNextSavedIdx;
            parcel.writeInt(undoowner.mSavedIdx);
            parcel.writeString(undoowner.mTag);
            parcel.writeInt(undoowner.mOpCount);
            mNextSavedIdx = mNextSavedIdx + 1;
        }
    }

    public void setHistorySize(int i)
    {
        mHistorySize = i;
        if(mHistorySize >= 0 && countUndos(null) > mHistorySize)
            forgetUndos(null, countUndos(null) - mHistorySize);
    }

    public void setUndoLabel(CharSequence charsequence)
    {
        if(mWorking == null)
        {
            throw new IllegalStateException("Must be called during an update");
        } else
        {
            mWorking.setLabel(charsequence);
            return;
        }
    }

    public void suggestUndoLabel(CharSequence charsequence)
    {
        if(mWorking == null)
        {
            throw new IllegalStateException("Must be called during an update");
        } else
        {
            mWorking.updateLabel(charsequence);
            return;
        }
    }

    public boolean uncommitState(int i, UndoOwner undoowner)
    {
        if(mWorking != null && mWorking.getCommitId() == i)
        {
            if(undoowner == null || mWorking.hasOperation(undoowner))
                return mWorking.setCanMerge(true);
        } else
        {
            UndoState undostate = getTopUndo(null);
            if(undostate != null && (undoowner == null || undostate.hasOperation(undoowner)) && undostate.getCommitId() == i)
                return undostate.setCanMerge(true);
        }
        return false;
    }

    public int undo(UndoOwner aundoowner[], int i)
    {
        if(mWorking != null)
            throw new IllegalStateException("Can't be called during an update");
        boolean flag = false;
        byte byte0 = -1;
        mInUndo = true;
        UndoState undostate = getTopUndo(null);
        int j = byte0;
        int k = ((flag) ? 1 : 0);
        int l = i;
        if(undostate != null)
        {
            undostate.makeExecuted();
            l = i;
            k = ((flag) ? 1 : 0);
            j = byte0;
        }
        do
        {
            if(l <= 0)
                break;
            j = findPrevState(mUndos, aundoowner, j);
            if(j < 0)
                break;
            UndoState undostate1 = (UndoState)mUndos.remove(j);
            undostate1.undo();
            mRedos.add(undostate1);
            l--;
            k++;
        } while(true);
        mInUndo = false;
        return k;
    }

    public static final int MERGE_MODE_ANY = 2;
    public static final int MERGE_MODE_NONE = 0;
    public static final int MERGE_MODE_UNIQUE = 1;
    private int mCommitId;
    private int mHistorySize;
    private boolean mInUndo;
    private boolean mMerged;
    private int mNextSavedIdx;
    private final ArrayMap mOwners = new ArrayMap(1);
    private final ArrayList mRedos = new ArrayList();
    private UndoOwner mStateOwners[];
    private int mStateSeq;
    private final ArrayList mUndos = new ArrayList();
    private int mUpdateCount;
    private UndoState mWorking;
}
