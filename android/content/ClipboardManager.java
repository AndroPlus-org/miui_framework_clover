// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import java.util.ArrayList;

// Referenced classes of package android.content:
//            Context, IClipboard, ClipData, ClipDescription

public class ClipboardManager extends android.text.ClipboardManager
{
    public static interface OnPrimaryClipChangedListener
    {

        public abstract void onPrimaryClipChanged();
    }


    static Handler _2D_get0(ClipboardManager clipboardmanager)
    {
        return clipboardmanager.mHandler;
    }

    public ClipboardManager(Context context, Handler handler)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mContext = context;
    }

    public void addPrimaryClipChangedListener(OnPrimaryClipChangedListener onprimaryclipchangedlistener)
    {
        ArrayList arraylist = mPrimaryClipChangedListeners;
        arraylist;
        JVM INSTR monitorenter ;
        boolean flag = mPrimaryClipChangedListeners.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_39;
        mService.addPrimaryClipChangedListener(mPrimaryClipChangedServiceListener, mContext.getOpPackageName());
        mPrimaryClipChangedListeners.add(onprimaryclipchangedlistener);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        onprimaryclipchangedlistener;
        throw onprimaryclipchangedlistener.rethrowFromSystemServer();
        onprimaryclipchangedlistener;
        arraylist;
        JVM INSTR monitorexit ;
        throw onprimaryclipchangedlistener;
    }

    public ClipData getPrimaryClip()
    {
        ClipData clipdata;
        try
        {
            clipdata = mService.getPrimaryClip(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return clipdata;
    }

    public ClipDescription getPrimaryClipDescription()
    {
        ClipDescription clipdescription;
        try
        {
            clipdescription = mService.getPrimaryClipDescription(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return clipdescription;
    }

    public CharSequence getText()
    {
        ClipData clipdata = getPrimaryClip();
        if(clipdata != null && clipdata.getItemCount() > 0)
            return clipdata.getItemAt(0).coerceToText(mContext);
        else
            return null;
    }

    public boolean hasPrimaryClip()
    {
        boolean flag;
        try
        {
            flag = mService.hasPrimaryClip(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean hasText()
    {
        boolean flag;
        try
        {
            flag = mService.hasClipboardText(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void removePrimaryClipChangedListener(OnPrimaryClipChangedListener onprimaryclipchangedlistener)
    {
        ArrayList arraylist = mPrimaryClipChangedListeners;
        arraylist;
        JVM INSTR monitorenter ;
        boolean flag;
        mPrimaryClipChangedListeners.remove(onprimaryclipchangedlistener);
        flag = mPrimaryClipChangedListeners.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_41;
        mService.removePrimaryClipChangedListener(mPrimaryClipChangedServiceListener);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        onprimaryclipchangedlistener;
        throw onprimaryclipchangedlistener.rethrowFromSystemServer();
        onprimaryclipchangedlistener;
        arraylist;
        JVM INSTR monitorexit ;
        throw onprimaryclipchangedlistener;
    }

    void reportPrimaryClipChanged()
    {
        ArrayList arraylist = mPrimaryClipChangedListeners;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mPrimaryClipChangedListeners.size();
        if(i > 0)
            break MISSING_BLOCK_LABEL_22;
        arraylist;
        JVM INSTR monitorexit ;
        return;
        Object aobj[] = mPrimaryClipChangedListeners.toArray();
        arraylist;
        JVM INSTR monitorexit ;
        for(int j = 0; j < aobj.length; j++)
            ((OnPrimaryClipChangedListener)aobj[j]).onPrimaryClipChanged();

        break MISSING_BLOCK_LABEL_62;
        Exception exception;
        exception;
        throw exception;
    }

    public void setPrimaryClip(ClipData clipdata)
    {
        if(clipdata == null)
            break MISSING_BLOCK_LABEL_9;
        clipdata.prepareToLeaveProcess(true);
        mService.setPrimaryClip(clipdata, mContext.getOpPackageName());
        return;
        clipdata;
        throw clipdata.rethrowFromSystemServer();
    }

    public void setText(CharSequence charsequence)
    {
        setPrimaryClip(ClipData.newPlainText(null, charsequence));
    }

    static final int MSG_REPORT_PRIMARY_CLIP_CHANGED = 1;
    private final Context mContext;
    private final Handler mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 25;
               goto _L1 _L2
_L1:
            return;
_L2:
            reportPrimaryClipChanged();
            if(true) goto _L1; else goto _L3
_L3:
        }

        final ClipboardManager this$0;

            
            {
                this$0 = ClipboardManager.this;
                super();
            }
    }
;
    private final ArrayList mPrimaryClipChangedListeners = new ArrayList();
    private final IOnPrimaryClipChangedListener.Stub mPrimaryClipChangedServiceListener = new IOnPrimaryClipChangedListener.Stub() {

        public void dispatchPrimaryClipChanged()
        {
            ClipboardManager._2D_get0(ClipboardManager.this).sendEmptyMessage(1);
        }

        final ClipboardManager this$0;

            
            {
                this$0 = ClipboardManager.this;
                super();
            }
    }
;
    private final IClipboard mService = IClipboard.Stub.asInterface(ServiceManager.getServiceOrThrow("clipboard"));
}
