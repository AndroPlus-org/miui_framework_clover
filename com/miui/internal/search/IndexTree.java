// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.search;

import android.content.ContentValues;
import android.database.MatrixCursor;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class IndexTree
{

    protected IndexTree(IndexTree indextree)
    {
        mParent = indextree;
    }

    protected final void addSon(int i, IndexTree indextree)
    {
        if(indextree == null)
            return;
        mChanged = true;
        if(mSon == null)
            mSon = new LinkedList();
        mSon.add(i, indextree);
    }

    protected final void addSon(IndexTree indextree)
    {
        int i;
        if(mSon == null)
            i = 0;
        else
            i = mSon.size();
        addSon(i, indextree);
    }

    public abstract void commit(StringBuilder stringbuilder);

    public abstract boolean delete(String s, String as[]);

    public final void dispatchCommit(StringBuilder stringbuilder)
    {
        commit(stringbuilder);
        mChanged = false;
        if(mSon == null) goto _L2; else goto _L1
_L1:
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mSon.iterator(); iterator.hasNext(); ((IndexTree)iterator.next()).dispatchCommit(stringbuilder));
        break MISSING_BLOCK_LABEL_64;
        stringbuilder;
        throw stringbuilder;
        obj;
        JVM INSTR monitorexit ;
_L2:
    }

    public final int dispatchDelete(String s, String as[], boolean flag)
    {
        int i;
        boolean flag1;
        int j;
        i = 0;
        flag1 = flag;
        if(!flag)
        {
            flag = delete(s, as);
            flag1 = flag;
            if(flag)
            {
                flag1 = flag;
                if(mParent != null)
                {
                    removeSelf();
                    flag1 = flag;
                }
            }
        }
        if(flag1)
            i = 1;
        j = i;
        if(mSon == null)
            break MISSING_BLOCK_LABEL_132;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = ((LinkedList)mSon.clone()).iterator();
_L1:
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_125;
        j = ((IndexTree)iterator.next()).dispatchDelete(s, as, flag1);
        i += j;
          goto _L1
        obj;
        JVM INSTR monitorexit ;
        j = i;
        return j;
        s;
        throw s;
    }

    public final String dispatchInsert(ContentValues contentvalues)
    {
        IndexTree indextree = insert(contentvalues);
        if(indextree != null)
        {
            mChanged = true;
            indextree.mChanged = true;
            return indextree.getUri();
        }
        if(mSon == null) goto _L2; else goto _L1
_L1:
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = mSon.iterator();
        String s;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_83;
            s = ((IndexTree)iterator.next()).dispatchInsert(contentvalues);
        } while(s == null);
        obj;
        JVM INSTR monitorexit ;
        return s;
        obj;
        JVM INSTR monitorexit ;
_L2:
        return null;
        contentvalues;
        throw contentvalues;
    }

    public final void dispatchQuery(MatrixCursor matrixcursor, String s, String s1, String as[], String s2)
    {
        if(!query(matrixcursor, s, s1, as, s2) || mSon == null) goto _L2; else goto _L1
_L1:
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mSon.iterator(); iterator.hasNext(); ((IndexTree)iterator.next()).dispatchQuery(matrixcursor, s, s1, as, s2));
        break MISSING_BLOCK_LABEL_80;
        matrixcursor;
        throw matrixcursor;
        obj;
        JVM INSTR monitorexit ;
_L2:
    }

    public final int dispatchUpdate(ContentValues contentvalues, String s, String as[])
    {
        int i;
        int j;
        i = 0;
        if(update(contentvalues, s, as))
        {
            i = 1;
            mChanged = true;
        }
        j = i;
        if(mSon == null)
            break MISSING_BLOCK_LABEL_97;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = mSon.iterator();
_L1:
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_90;
        j = ((IndexTree)iterator.next()).dispatchUpdate(contentvalues, s, as);
        i += j;
          goto _L1
        obj;
        JVM INSTR monitorexit ;
        j = i;
        return j;
        contentvalues;
        throw contentvalues;
    }

    public IndexTree getParent()
    {
        return mParent;
    }

    public LinkedList getSons()
    {
        return mSon;
    }

    protected abstract String getUri();

    public abstract IndexTree insert(ContentValues contentvalues);

    public final boolean needCommit()
    {
        if(mChanged)
            return true;
        if(mSon == null)
            return false;
        for(Iterator iterator = mSon.iterator(); iterator.hasNext();)
            if(((IndexTree)iterator.next()).needCommit())
                return true;

        return false;
    }

    public abstract boolean query(MatrixCursor matrixcursor, String s, String s1, String as[], String s2);

    public void removeSelf()
    {
        if(mParent == null) goto _L2; else goto _L1
_L1:
        Object obj = mParent.mLock;
        obj;
        JVM INSTR monitorenter ;
        mParent.mChanged = true;
        mParent.mSon.remove(this);
        obj;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void replaceBy(IndexTree indextree)
    {
        LinkedList linkedlist = getParent().getSons();
        linkedlist.set(linkedlist.indexOf(this), indextree);
    }

    public abstract boolean update(ContentValues contentvalues, String s, String as[]);

    private static final String TAG = "IndexTree";
    private volatile boolean mChanged;
    final Object mLock = new Object();
    private IndexTree mParent;
    private LinkedList mSon;
}
