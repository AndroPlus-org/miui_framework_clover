// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package android.media:
//            AudioManager, AudioPort, AudioPatch

class AudioPortEventHandler
{

    static ArrayList _2D_get0(AudioPortEventHandler audioporteventhandler)
    {
        return audioporteventhandler.mListeners;
    }

    AudioPortEventHandler()
    {
    }

    private native void native_finalize();

    private native void native_setup(Object obj);

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (AudioPortEventHandler)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(obj != null)
        {
            obj = ((AudioPortEventHandler) (obj)).handler();
            if(obj != null)
                ((Handler) (obj)).sendMessage(((Handler) (obj)).obtainMessage(i, j, k, obj1));
        }
    }

    protected void finalize()
    {
        native_finalize();
    }

    Handler handler()
    {
        return mHandler;
    }

    void init()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = mHandler;
        if(obj == null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        obj = Looper.getMainLooper();
        if(obj == null)
            break MISSING_BLOCK_LABEL_54;
        Handler handler1 = JVM INSTR new #6   <Class AudioPortEventHandler$1>;
        handler1.this. _cls1(((Looper) (obj)));
        mHandler = handler1;
        obj = JVM INSTR new #45  <Class WeakReference>;
        ((WeakReference) (obj)).WeakReference(this);
        native_setup(obj);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        mHandler = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    void registerListener(AudioManager.OnAudioPortUpdateListener onaudioportupdatelistener)
    {
        this;
        JVM INSTR monitorenter ;
        mListeners.add(onaudioportupdatelistener);
        this;
        JVM INSTR monitorexit ;
        if(mHandler != null)
        {
            onaudioportupdatelistener = mHandler.obtainMessage(4, 0, 0, onaudioportupdatelistener);
            mHandler.sendMessage(onaudioportupdatelistener);
        }
        return;
        onaudioportupdatelistener;
        throw onaudioportupdatelistener;
    }

    void unregisterListener(AudioManager.OnAudioPortUpdateListener onaudioportupdatelistener)
    {
        this;
        JVM INSTR monitorenter ;
        mListeners.remove(onaudioportupdatelistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onaudioportupdatelistener;
        throw onaudioportupdatelistener;
    }

    private static final int AUDIOPORT_EVENT_NEW_LISTENER = 4;
    private static final int AUDIOPORT_EVENT_PATCH_LIST_UPDATED = 2;
    private static final int AUDIOPORT_EVENT_PORT_LIST_UPDATED = 1;
    private static final int AUDIOPORT_EVENT_SERVICE_DIED = 3;
    private static final String TAG = "AudioPortEventHandler";
    private Handler mHandler;
    private long mJniCallback;
    private final ArrayList mListeners = new ArrayList();

    // Unreferenced inner class android/media/AudioPortEventHandler$1

/* anonymous class */
    class _cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            this;
            JVM INSTR monitorenter ;
            if(message.what != 4) goto _L2; else goto _L1
_L1:
            ArrayList arraylist;
            arraylist = JVM INSTR new #29  <Class ArrayList>;
            arraylist.ArrayList();
            ArrayList arraylist1 = arraylist;
            if(!AudioPortEventHandler._2D_get0(AudioPortEventHandler.this).contains(message.obj))
                break MISSING_BLOCK_LABEL_51;
            arraylist.add((AudioManager.OnAudioPortUpdateListener)message.obj);
            arraylist1 = arraylist;
_L10:
            this;
            JVM INSTR monitorexit ;
              goto _L3
_L2:
            arraylist1 = AudioPortEventHandler._2D_get0(AudioPortEventHandler.this);
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L3:
            ArrayList arraylist2;
            if(message.what == 1 || message.what == 2 || message.what == 3)
                AudioManager.resetAudioPortGeneration();
            if(arraylist1.isEmpty())
                return;
            arraylist2 = new ArrayList();
            arraylist = new ArrayList();
            if(message.what != 3 && AudioManager.updateAudioPortCache(arraylist2, arraylist, null) != 0)
                return;
            message.what;
            JVM INSTR tableswitch 1 4: default 180
        //                       1 181
        //                       2 237
        //                       3 282
        //                       4 181;
               goto _L4 _L5 _L6 _L7 _L5
_L4:
            return;
_L5:
            AudioPort aaudioport[] = (AudioPort[])arraylist2.toArray(new AudioPort[0]);
            for(int i = 0; i < arraylist1.size(); i++)
                ((AudioManager.OnAudioPortUpdateListener)arraylist1.get(i)).onAudioPortListUpdate(aaudioport);

            if(message.what == 1)
                continue; /* Loop/switch isn't completed */
_L6:
            message = (AudioPatch[])arraylist.toArray(new AudioPatch[0]);
            int j = 0;
            while(j < arraylist1.size()) 
            {
                ((AudioManager.OnAudioPortUpdateListener)arraylist1.get(j)).onAudioPatchListUpdate(message);
                j++;
            }
            continue; /* Loop/switch isn't completed */
_L7:
            int k = 0;
            while(k < arraylist1.size()) 
            {
                ((AudioManager.OnAudioPortUpdateListener)arraylist1.get(k)).onServiceDied();
                k++;
            }
            if(true) goto _L4; else goto _L8
_L8:
            if(true) goto _L10; else goto _L9
_L9:
        }

        final AudioPortEventHandler this$0;

            
            {
                this$0 = AudioPortEventHandler.this;
                super(looper);
            }
    }

}
