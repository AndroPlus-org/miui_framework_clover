// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.watermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.*;
import android.util.Log;

// Referenced classes of package com.miui.whetstone.watermark:
//            WatermarkCallback

public final class WatermarkProxy
{
    private static class CallbackTransport extends IWatermarkCallback.Stub
    {

        static void _2D_wrap0(CallbackTransport callbacktransport, Message message)
        {
            callbacktransport._handleMessage(message);
        }

        private void _handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 57
        //                       2 106;
               goto _L1 _L2 _L3
_L1:
            Log.w("WatermarkProxy", (new StringBuilder()).append("unknow msg type: ").append(message.what).toString());
_L5:
            return;
_L2:
            Bitmap bitmap = (Bitmap)message.obj;
            Log.d("WatermarkProxy", (new StringBuilder()).append("watermark _handleMessage encode: ").append(message.what).toString());
            mCallback.onEncodeWatermarkDone(bitmap);
            continue; /* Loop/switch isn't completed */
_L3:
            String s = (String)message.obj;
            Log.d("WatermarkProxy", (new StringBuilder()).append("watermark _handleMessage decode: ").append(message.what).toString());
            mCallback.onDecodeWatermarkDone(s);
            if(true) goto _L5; else goto _L4
_L4:
        }

        public void onDecodeWatermark(String s)
        {
            Log.d("WatermarkProxy", "watermark CallbackTransport onDecodeWatermark");
            Message message = Message.obtain();
            message.what = 2;
            message.obj = s;
            mCallbackHandler.sendMessage(message);
        }

        public void onEncodeWatermark(Bitmap bitmap)
        {
            Log.d("WatermarkProxy", "watermark CallbackTransport onEncodeWatermark");
            Message message = Message.obtain();
            message.what = 1;
            message.obj = bitmap;
            mCallbackHandler.sendMessage(message);
        }

        private static final int TYPE_ON_DECODE = 2;
        private static final int TYPE_ON_ENCODE = 1;
        private final WatermarkCallback mCallback;
        private final Handler mCallbackHandler;

        CallbackTransport(WatermarkCallback watermarkcallback, Looper looper)
        {
            mCallback = watermarkcallback;
            if(looper == null)
                mCallbackHandler = new _cls1();
            else
                mCallbackHandler = new _cls2(looper);
        }
    }


    private WatermarkProxy(Context context)
    {
        mContext = context;
    }

    public static WatermarkProxy get(Context context)
    {
        com/miui/whetstone/watermark/WatermarkProxy;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            WatermarkProxy watermarkproxy = JVM INSTR new #2   <Class WatermarkProxy>;
            watermarkproxy.WatermarkProxy(context);
            sInstance = watermarkproxy;
        }
        context = sInstance;
        com/miui/whetstone/watermark/WatermarkProxy;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    public void decodeWatermark(Bitmap bitmap, WatermarkCallback watermarkcallback, Looper looper)
    {
        Log.d("WatermarkProxy", "decodeWatermark");
    }

    public void encodeWatermark(Bitmap bitmap, String s, WatermarkCallback watermarkcallback, Looper looper)
    {
        Log.d("WatermarkProxy", "encodeWatermark");
    }

    private static final String TAG = "WatermarkProxy";
    private static WatermarkProxy sInstance = null;
    private Context mContext;


    // Unreferenced inner class com/miui/whetstone/watermark/WatermarkProxy$CallbackTransport$1

/* anonymous class */
    class CallbackTransport._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            CallbackTransport._2D_wrap0(CallbackTransport.this, message);
        }

        final CallbackTransport this$1;

            
            {
                this$1 = CallbackTransport.this;
                super();
            }
    }


    // Unreferenced inner class com/miui/whetstone/watermark/WatermarkProxy$CallbackTransport$2

/* anonymous class */
    class CallbackTransport._cls2 extends Handler
    {

        public void handleMessage(Message message)
        {
            CallbackTransport._2D_wrap0(CallbackTransport.this, message);
        }

        final CallbackTransport this$1;

            
            {
                this$1 = CallbackTransport.this;
                super(looper);
            }
    }

}
