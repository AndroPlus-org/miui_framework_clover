// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.proto.ProtoOutputStream;
import java.util.Arrays;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public class WorkSource
    implements Parcelable
{

    public WorkSource()
    {
        mNum = 0;
    }

    public WorkSource(int i)
    {
        mNum = 1;
        mUids = (new int[] {
            i, 0
        });
        mNames = null;
    }

    public WorkSource(int i, String s)
    {
        if(s == null)
        {
            throw new NullPointerException("Name can't be null");
        } else
        {
            mNum = 1;
            mUids = (new int[] {
                i, 0
            });
            mNames = (new String[] {
                s, null
            });
            return;
        }
    }

    WorkSource(Parcel parcel)
    {
        mNum = parcel.readInt();
        mUids = parcel.createIntArray();
        mNames = parcel.createStringArray();
    }

    public WorkSource(WorkSource worksource)
    {
        if(worksource == null)
        {
            mNum = 0;
            return;
        }
        mNum = worksource.mNum;
        if(worksource.mUids != null)
        {
            mUids = (int[])worksource.mUids.clone();
            if(worksource.mNames != null)
                worksource = (String[])worksource.mNames.clone();
            else
                worksource = null;
            mNames = worksource;
        } else
        {
            mUids = null;
            mNames = null;
        }
    }

    private static WorkSource addWork(WorkSource worksource, int i)
    {
        if(worksource == null)
        {
            return new WorkSource(i);
        } else
        {
            worksource.insert(worksource.mNum, i);
            return worksource;
        }
    }

    private static WorkSource addWork(WorkSource worksource, int i, String s)
    {
        if(worksource == null)
        {
            return new WorkSource(i, s);
        } else
        {
            worksource.insert(worksource.mNum, i, s);
            return worksource;
        }
    }

    private int compare(WorkSource worksource, int i, int j)
    {
        int k = mUids[i] - worksource.mUids[j];
        if(k != 0)
            return k;
        else
            return mNames[i].compareTo(worksource.mNames[j]);
    }

    private void insert(int i, int j)
    {
        if(mUids == null)
        {
            mUids = new int[4];
            mUids[0] = j;
            mNum = 1;
        } else
        if(mNum >= mUids.length)
        {
            int ai[] = new int[(mNum * 3) / 2];
            if(i > 0)
                System.arraycopy(mUids, 0, ai, 0, i);
            if(i < mNum)
                System.arraycopy(mUids, i, ai, i + 1, mNum - i);
            mUids = ai;
            mUids[i] = j;
            mNum = mNum + 1;
        } else
        {
            if(i < mNum)
                System.arraycopy(mUids, i, mUids, i + 1, mNum - i);
            mUids[i] = j;
            mNum = mNum + 1;
        }
    }

    private void insert(int i, int j, String s)
    {
        if(mUids == null)
        {
            mUids = new int[4];
            mUids[0] = j;
            mNames = new String[4];
            mNames[0] = s;
            mNum = 1;
        } else
        if(mNum >= mUids.length)
        {
            int ai[] = new int[(mNum * 3) / 2];
            String as[] = new String[(mNum * 3) / 2];
            if(i > 0)
            {
                System.arraycopy(mUids, 0, ai, 0, i);
                System.arraycopy(mNames, 0, as, 0, i);
            }
            if(i < mNum)
            {
                System.arraycopy(mUids, i, ai, i + 1, mNum - i);
                System.arraycopy(mNames, i, as, i + 1, mNum - i);
            }
            mUids = ai;
            mNames = as;
            mUids[i] = j;
            mNames[i] = s;
            mNum = mNum + 1;
        } else
        {
            if(i < mNum)
            {
                System.arraycopy(mUids, i, mUids, i + 1, mNum - i);
                System.arraycopy(mNames, i, mNames, i + 1, mNum - i);
            }
            mUids[i] = j;
            mNames[i] = s;
            mNum = mNum + 1;
        }
    }

    private boolean removeUids(WorkSource worksource)
    {
        int i = mNum;
        int ai[] = mUids;
        int j = worksource.mNum;
        worksource = worksource.mUids;
        boolean flag = false;
        int k = 0;
        for(int l = 0; k < i && l < j;)
            if(worksource[l] == ai[k])
            {
                i--;
                flag = true;
                if(k < i)
                    System.arraycopy(ai, k + 1, ai, k, i - k);
                l++;
            } else
            if(worksource[l] > ai[k])
                k++;
            else
                l++;

        mNum = i;
        return flag;
    }

    private boolean removeUidsAndNames(WorkSource worksource)
    {
        int i = mNum;
        int ai[] = mUids;
        String as[] = mNames;
        int j = worksource.mNum;
        int ai1[] = worksource.mUids;
        worksource = worksource.mNames;
        boolean flag = false;
        int k = 0;
        for(int l = 0; k < i && l < j;)
            if(ai1[l] == ai[k] && worksource[l].equals(as[k]))
            {
                i--;
                flag = true;
                if(k < i)
                {
                    System.arraycopy(ai, k + 1, ai, k, i - k);
                    System.arraycopy(as, k + 1, as, k, i - k);
                }
                l++;
            } else
            if(ai1[l] > ai[k] || ai1[l] == ai[k] && worksource[l].compareTo(as[k]) > 0)
                k++;
            else
                l++;

        mNum = i;
        return flag;
    }

    private boolean updateLocked(WorkSource worksource, boolean flag, boolean flag1)
    {
        if(mNames == null && worksource.mNames == null)
            return updateUidsLocked(worksource, flag, flag1);
        if(mNum > 0 && mNames == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Other ").append(worksource).append(" has names, but target ").append(this).append(" does not").toString());
        if(worksource.mNum > 0 && worksource.mNames == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Target ").append(this).append(" has names, but other ").append(worksource).append(" does not").toString());
        else
            return updateUidsAndNamesLocked(worksource, flag, flag1);
    }

    private boolean updateUidsAndNamesLocked(WorkSource worksource, boolean flag, boolean flag1)
    {
        int i = worksource.mNum;
        int ai[] = worksource.mUids;
        String as[] = worksource.mNames;
        boolean flag2 = false;
        int j = 0;
        int k = 0;
label0:
        do
        {
label1:
            {
                int l;
label2:
                {
                    if(j >= mNum && k >= i)
                        break label1;
                    l = -1;
                    if(j < mNum)
                    {
                        if(k >= i)
                            break label2;
                        int i1 = compare(worksource, j, k);
                        l = i1;
                        if(i1 <= 0)
                            break label2;
                    }
                    flag2 = true;
                    insert(j, ai[k], as[k]);
                    if(flag1)
                        sNewbWork = addWork(sNewbWork, ai[k], as[k]);
                    j++;
                    k++;
                    continue;
                }
                if(!flag)
                {
                    int j1 = k;
                    if(k < i)
                    {
                        j1 = k;
                        if(l == 0)
                            j1 = k + 1;
                    }
                    j++;
                    k = j1;
                    continue;
                }
                int k1 = j;
                do
                {
label3:
                    {
                        int l1 = k1;
                        k1 = l1;
                        if(l < 0)
                        {
                            sGoneWork = addWork(sGoneWork, mUids[l1], mNames[l1]);
                            k1 = l1 + 1;
                            if(k1 < mNum)
                                break label3;
                        }
                        l1 = k1;
                        if(j < k1)
                        {
                            System.arraycopy(mUids, k1, mUids, j, mNum - k1);
                            System.arraycopy(mNames, k1, mNames, j, mNum - k1);
                            mNum = mNum - (k1 - j);
                            l1 = j;
                        }
                        j = l1;
                        if(l1 < mNum)
                        {
                            j = l1;
                            if(l == 0)
                            {
                                j = l1 + 1;
                                k++;
                            }
                        }
                        continue label0;
                    }
                    if(k < i)
                        l = compare(worksource, k1, k);
                    else
                        l = -1;
                } while(true);
            }
            return flag2;
        } while(true);
    }

    private boolean updateUidsLocked(WorkSource worksource, boolean flag, boolean flag1)
    {
        int i = mNum;
        int ai[] = mUids;
        int j = worksource.mNum;
        int ai2[] = worksource.mUids;
        boolean flag2 = false;
        int k = 0;
        int l = 0;
        worksource = ai;
        do
        {
            if(k >= i && l >= j)
                break;
            if(k >= i || l < j && ai2[l] < worksource[k])
            {
                flag2 = true;
                if(worksource == null)
                {
                    worksource = new int[4];
                    worksource[0] = ai2[l];
                } else
                if(i >= worksource.length)
                {
                    int ai1[] = new int[(worksource.length * 3) / 2];
                    if(k > 0)
                        System.arraycopy(worksource, 0, ai1, 0, k);
                    if(k < i)
                        System.arraycopy(worksource, k, ai1, k + 1, i - k);
                    worksource = ai1;
                    ai1[k] = ai2[l];
                } else
                {
                    if(k < i)
                        System.arraycopy(worksource, k, worksource, k + 1, i - k);
                    worksource[k] = ai2[l];
                }
                if(flag1)
                    sNewbWork = addWork(sNewbWork, ai2[l]);
                i++;
                k++;
                l++;
            } else
            if(!flag)
            {
                int i1 = l;
                if(l < j)
                {
                    i1 = l;
                    if(ai2[l] == worksource[k])
                        i1 = l + 1;
                }
                k++;
                l = i1;
            } else
            {
                int j1 = k;
                int k1;
                do
                {
                    k1 = j1;
                    if(k1 >= i || l < j && ai2[l] <= worksource[k1])
                        break;
                    sGoneWork = addWork(sGoneWork, worksource[k1]);
                    j1 = k1 + 1;
                } while(true);
                int l1 = i;
                j1 = k1;
                if(k < k1)
                {
                    System.arraycopy(worksource, k1, worksource, k, i - k1);
                    l1 = i - (k1 - k);
                    j1 = k;
                }
                i = l1;
                k = j1;
                if(j1 < l1)
                {
                    i = l1;
                    k = j1;
                    if(l < j)
                    {
                        i = l1;
                        k = j1;
                        if(ai2[l] == worksource[j1])
                        {
                            k = j1 + 1;
                            l++;
                            i = l1;
                        }
                    }
                }
            }
        } while(true);
        mNum = i;
        mUids = worksource;
        return flag2;
    }

    public boolean add(int i)
    {
        if(mNum <= 0)
        {
            mNames = null;
            insert(0, i);
            return true;
        }
        if(mNames != null)
            throw new IllegalArgumentException((new StringBuilder()).append("Adding without name to named ").append(this).toString());
        int j = Arrays.binarySearch(mUids, 0, mNum, i);
        if(j >= 0)
        {
            return false;
        } else
        {
            insert(-j - 1, i);
            return true;
        }
    }

    public boolean add(int i, String s)
    {
        int j;
        if(mNum <= 0)
        {
            insert(0, i, s);
            return true;
        }
        if(mNames == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Adding name to unnamed ").append(this).toString());
        j = 0;
_L5:
        if(j < mNum && mUids[j] <= i) goto _L2; else goto _L1
_L1:
        insert(j, i, s);
        return true;
_L2:
        int k;
        if(mUids[j] != i)
            break; /* Loop/switch isn't completed */
        if((k = mNames[j].compareTo(s)) > 0)
            continue; /* Loop/switch isn't completed */
        if(k == 0)
            return false;
        break; /* Loop/switch isn't completed */
        if(true) goto _L1; else goto _L3
_L3:
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean add(WorkSource worksource)
    {
        WorkSource worksource1 = sTmpWorkSource;
        worksource1;
        JVM INSTR monitorenter ;
        boolean flag = updateLocked(worksource, false, false);
        worksource1;
        JVM INSTR monitorexit ;
        return flag;
        worksource;
        throw worksource;
    }

    public WorkSource addReturningNewbs(int i)
    {
        WorkSource worksource = sTmpWorkSource;
        worksource;
        JVM INSTR monitorenter ;
        WorkSource worksource1;
        sNewbWork = null;
        sTmpWorkSource.mUids[0] = i;
        updateLocked(sTmpWorkSource, false, true);
        worksource1 = sNewbWork;
        worksource;
        JVM INSTR monitorexit ;
        return worksource1;
        Exception exception;
        exception;
        throw exception;
    }

    public WorkSource addReturningNewbs(WorkSource worksource)
    {
        WorkSource worksource1 = sTmpWorkSource;
        worksource1;
        JVM INSTR monitorenter ;
        sNewbWork = null;
        updateLocked(worksource, false, true);
        worksource = sNewbWork;
        worksource1;
        JVM INSTR monitorexit ;
        return worksource;
        worksource;
        throw worksource;
    }

    public void clear()
    {
        mNum = 0;
    }

    public void clearNames()
    {
        if(mNames != null)
        {
            mNames = null;
            int i = 1;
            int j = mNum;
            int k = 1;
            while(k < mNum) 
            {
                if(mUids[k] == mUids[k - 1])
                {
                    j--;
                } else
                {
                    mUids[i] = mUids[k];
                    i++;
                }
                k++;
            }
            mNum = j;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean diff(WorkSource worksource)
    {
        int i = mNum;
        if(i != worksource.mNum)
            return true;
        int ai[] = mUids;
        int ai1[] = worksource.mUids;
        String as[] = mNames;
        worksource = worksource.mNames;
        for(int j = 0; j < i; j++)
        {
            if(ai[j] != ai1[j])
                return true;
            if(as != null && worksource != null && as[j].equals(worksource[j]) ^ true)
                return true;
        }

        return false;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof WorkSource)
            flag = diff((WorkSource)obj) ^ true;
        else
            flag = false;
        return flag;
    }

    public int get(int i)
    {
        return mUids[i];
    }

    public String getName(int i)
    {
        String s = null;
        if(mNames != null)
            s = mNames[i];
        return s;
    }

    public int hashCode()
    {
        int i = 0;
        for(int j = 0; j < mNum; j++)
            i = (i << 4 | i >>> 28) ^ mUids[j];

        int l = i;
        if(mNames != null)
        {
            int k = 0;
            do
            {
                l = i;
                if(k >= mNum)
                    break;
                i = (i << 4 | i >>> 28) ^ mNames[k].hashCode();
                k++;
            } while(true);
        }
        return l;
    }

    public boolean remove(WorkSource worksource)
    {
        if(mNum <= 0 || worksource.mNum <= 0)
            return false;
        if(mNames == null && worksource.mNames == null)
            return removeUids(worksource);
        if(mNames == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Other ").append(worksource).append(" has names, but target ").append(this).append(" does not").toString());
        if(worksource.mNames == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Target ").append(this).append(" has names, but other ").append(worksource).append(" does not").toString());
        else
            return removeUidsAndNames(worksource);
    }

    public void set(int i)
    {
        mNum = 1;
        if(mUids == null)
            mUids = new int[2];
        mUids[0] = i;
        mNames = null;
    }

    public void set(int i, String s)
    {
        if(s == null)
            throw new NullPointerException("Name can't be null");
        mNum = 1;
        if(mUids == null)
        {
            mUids = new int[2];
            mNames = new String[2];
        }
        mUids[0] = i;
        mNames[0] = s;
    }

    public void set(WorkSource worksource)
    {
        if(worksource == null)
        {
            mNum = 0;
            return;
        }
        mNum = worksource.mNum;
        if(worksource.mUids != null)
        {
            if(mUids != null && mUids.length >= mNum)
                System.arraycopy(worksource.mUids, 0, mUids, 0, mNum);
            else
                mUids = (int[])worksource.mUids.clone();
            if(worksource.mNames != null)
            {
                if(mNames != null && mNames.length >= mNum)
                    System.arraycopy(worksource.mNames, 0, mNames, 0, mNum);
                else
                    mNames = (String[])worksource.mNames.clone();
            } else
            {
                mNames = null;
            }
        } else
        {
            mUids = null;
            mNames = null;
        }
    }

    public WorkSource[] setReturningDiffs(WorkSource worksource)
    {
        WorkSource worksource1 = sTmpWorkSource;
        worksource1;
        JVM INSTR monitorenter ;
        WorkSource worksource2;
        sNewbWork = null;
        sGoneWork = null;
        updateLocked(worksource, true, true);
        if(sNewbWork == null && sGoneWork == null)
            break MISSING_BLOCK_LABEL_57;
        worksource = sNewbWork;
        worksource2 = sGoneWork;
        return (new WorkSource[] {
            worksource, worksource2
        });
        worksource1;
        JVM INSTR monitorexit ;
        return null;
        worksource;
        throw worksource;
    }

    public int size()
    {
        return mNum;
    }

    public WorkSource stripNames()
    {
        if(mNum <= 0)
            return new WorkSource();
        WorkSource worksource = new WorkSource();
        for(int i = 0; i < mNum; i++)
        {
            int j = mUids[i];
            if(i == 0 || -1 != j)
                worksource.add(j);
        }

        return worksource;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("WorkSource{");
        for(int i = 0; i < mNum; i++)
        {
            if(i != 0)
                stringbuilder.append(", ");
            stringbuilder.append(mUids[i]);
            if(mNames != null)
            {
                stringbuilder.append(" ");
                stringbuilder.append(mNames[i]);
            }
        }

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mNum);
        parcel.writeIntArray(mUids);
        parcel.writeStringArray(mNames);
    }

    public void writeToProto(ProtoOutputStream protooutputstream, long l)
    {
        long l1 = protooutputstream.start(l);
        for(int i = 0; i < mNum; i++)
        {
            l = protooutputstream.start(0x21100000001L);
            protooutputstream.write(0x10300000001L, mUids[i]);
            if(mNames != null)
                protooutputstream.write(0x10e00000002L, mNames[i]);
            protooutputstream.end(l);
        }

        protooutputstream.end(l1);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public WorkSource createFromParcel(Parcel parcel)
        {
            return new WorkSource(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WorkSource[] newArray(int i)
        {
            return new WorkSource[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final boolean DEBUG = false;
    static final String TAG = "WorkSource";
    static WorkSource sGoneWork;
    static WorkSource sNewbWork;
    static final WorkSource sTmpWorkSource = new WorkSource(0);
    String mNames[];
    int mNum;
    int mUids[];

}
