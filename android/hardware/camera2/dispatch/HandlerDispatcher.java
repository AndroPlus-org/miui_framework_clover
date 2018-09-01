// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.dispatch;

import android.hardware.camera2.utils.UncheckedThrow;
import android.os.Handler;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.hardware.camera2.dispatch:
//            Dispatchable

public class HandlerDispatcher
    implements Dispatchable
{

    static Dispatchable _2D_get0(HandlerDispatcher handlerdispatcher)
    {
        return handlerdispatcher.mDispatchTarget;
    }

    public HandlerDispatcher(Dispatchable dispatchable, Handler handler)
    {
        mDispatchTarget = (Dispatchable)Preconditions.checkNotNull(dispatchable, "dispatchTarget must not be null");
        mHandler = (Handler)Preconditions.checkNotNull(handler, "handler must not be null");
    }

    public Object dispatch(final Method method, final Object args[])
        throws Throwable
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                HandlerDispatcher._2D_get0(HandlerDispatcher.this).dispatch(method, args);
_L1:
                return;
                Object obj;
                obj;
                UncheckedThrow.throwAnyException(((Throwable) (obj)));
                  goto _L1
                obj;
                Log.wtf("HandlerDispatcher", (new StringBuilder()).append("IllegalArgumentException while invoking ").append(method).toString(), ((Throwable) (obj)));
                  goto _L1
                obj;
                Log.wtf("HandlerDispatcher", (new StringBuilder()).append("IllegalAccessException while invoking ").append(method).toString(), ((Throwable) (obj)));
                  goto _L1
                obj;
                UncheckedThrow.throwAnyException(((InvocationTargetException) (obj)).getTargetException());
                  goto _L1
            }

            final HandlerDispatcher this$0;
            final Object val$args[];
            final Method val$method;

            
            {
                this$0 = HandlerDispatcher.this;
                method = method1;
                args = aobj;
                super();
            }
        }
);
        return null;
    }

    private static final String TAG = "HandlerDispatcher";
    private final Dispatchable mDispatchTarget;
    private final Handler mHandler;
}
