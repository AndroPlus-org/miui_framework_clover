// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.net.Uri;
import android.os.RemoteException;
import java.util.*;

public class MediaInserter
{

    public MediaInserter(ContentProviderClient contentproviderclient, int i)
    {
        mProvider = contentproviderclient;
        mBufferSizePerUri = i;
    }

    private void flush(Uri uri, List list)
        throws RemoteException
    {
        if(!list.isEmpty())
        {
            ContentValues acontentvalues[] = (ContentValues[])list.toArray(new ContentValues[list.size()]);
            mProvider.bulkInsert(uri, acontentvalues);
            list.clear();
        }
    }

    private void flushAllPriority()
        throws RemoteException
    {
        Uri uri;
        for(Iterator iterator = mPriorityRowMap.keySet().iterator(); iterator.hasNext(); flush(uri, (List)mPriorityRowMap.get(uri)))
            uri = (Uri)iterator.next();

        mPriorityRowMap.clear();
    }

    private void insert(Uri uri, ContentValues contentvalues, boolean flag)
        throws RemoteException
    {
        HashMap hashmap;
        List list;
        Object obj;
        if(flag)
            hashmap = mPriorityRowMap;
        else
            hashmap = mRowMap;
        list = (List)hashmap.get(uri);
        obj = list;
        if(list == null)
        {
            obj = new ArrayList();
            hashmap.put(uri, obj);
        }
        ((List) (obj)).add(new ContentValues(contentvalues));
        if(((List) (obj)).size() >= mBufferSizePerUri)
        {
            flushAllPriority();
            flush(uri, ((List) (obj)));
        }
    }

    public void flushAll()
        throws RemoteException
    {
        flushAllPriority();
        Uri uri;
        for(Iterator iterator = mRowMap.keySet().iterator(); iterator.hasNext(); flush(uri, (List)mRowMap.get(uri)))
            uri = (Uri)iterator.next();

        mRowMap.clear();
    }

    public void insert(Uri uri, ContentValues contentvalues)
        throws RemoteException
    {
        insert(uri, contentvalues, false);
    }

    public void insertwithPriority(Uri uri, ContentValues contentvalues)
        throws RemoteException
    {
        insert(uri, contentvalues, true);
    }

    private final int mBufferSizePerUri;
    private final HashMap mPriorityRowMap = new HashMap();
    private final ContentProviderClient mProvider;
    private final HashMap mRowMap = new HashMap();
}
