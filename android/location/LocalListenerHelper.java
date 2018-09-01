// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.*;

abstract class LocalListenerHelper
{
    protected static interface ListenerOperation
    {

        public abstract void execute(Object obj)
            throws RemoteException;
    }


    static void _2D_wrap0(LocalListenerHelper locallistenerhelper, ListenerOperation listeneroperation, Object obj)
    {
        locallistenerhelper.executeOperation(listeneroperation, obj);
    }

    protected LocalListenerHelper(Context context, String s)
    {
        Preconditions.checkNotNull(s);
        mContext = context;
        mTag = s;
    }

    private void executeOperation(ListenerOperation listeneroperation, Object obj)
    {
        listeneroperation.execute(obj);
_L1:
        return;
        listeneroperation;
        Log.e(mTag, "Error in monitored listener.", listeneroperation);
          goto _L1
    }

    public boolean add(Object obj, Handler handler)
    {
        Preconditions.checkNotNull(obj);
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        boolean flag = mListeners.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_67;
        flag = registerWithServer();
        if(flag)
            break MISSING_BLOCK_LABEL_67;
        Log.e(mTag, "Unable to register listener transport.");
        hashmap;
        JVM INSTR monitorexit ;
        return false;
        obj;
        Log.e(mTag, "Error handling first listener.", ((Throwable) (obj)));
        hashmap;
        JVM INSTR monitorexit ;
        return false;
        flag = mListeners.containsKey(obj);
        if(!flag)
            break MISSING_BLOCK_LABEL_86;
        hashmap;
        JVM INSTR monitorexit ;
        return true;
        mListeners.put(obj, handler);
        hashmap;
        JVM INSTR monitorexit ;
        return true;
        obj;
        throw obj;
    }

    protected void foreach(final ListenerOperation operation)
    {
        Object obj = mListeners;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist = new ArrayList(mListeners.entrySet());
        obj;
        JVM INSTR monitorexit ;
        for(obj = arraylist.iterator(); ((Iterator) (obj)).hasNext();)
        {
            final java.util.Map.Entry listener = (java.util.Map.Entry)((Iterator) (obj)).next();
            if(listener.getValue() == null)
                executeOperation(operation, listener.getKey());
            else
                ((Handler)listener.getValue()).post(new Runnable() {

                    public void run()
                    {
                        LocalListenerHelper._2D_wrap0(LocalListenerHelper.this, operation, listener.getKey());
                    }

                    final LocalListenerHelper this$0;
                    final java.util.Map.Entry val$listener;
                    final ListenerOperation val$operation;

            
            {
                this$0 = LocalListenerHelper.this;
                operation = listeneroperation;
                listener = entry;
                super();
            }
                }
);
        }

        break MISSING_BLOCK_LABEL_104;
        operation;
        throw operation;
    }

    protected Context getContext()
    {
        return mContext;
    }

    protected abstract boolean registerWithServer()
        throws RemoteException;

    public void remove(Object obj)
    {
        Preconditions.checkNotNull(obj);
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        boolean flag;
        flag = mListeners.containsKey(obj);
        mListeners.remove(obj);
        if(!flag) goto _L2; else goto _L1
_L1:
        flag = mListeners.isEmpty();
_L5:
        if(!flag)
            break MISSING_BLOCK_LABEL_50;
        unregisterFromServer();
_L3:
        hashmap;
        JVM INSTR monitorexit ;
        return;
_L2:
        flag = false;
        continue; /* Loop/switch isn't completed */
        obj;
        Log.v(mTag, "Error handling last listener removal", ((Throwable) (obj)));
          goto _L3
        obj;
        throw obj;
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected abstract void unregisterFromServer()
        throws RemoteException;

    private final Context mContext;
    private final HashMap mListeners = new HashMap();
    private final String mTag;
}
