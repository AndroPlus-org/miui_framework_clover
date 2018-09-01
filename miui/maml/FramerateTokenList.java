// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public class FramerateTokenList
{
    public static interface FramerateChangeListener
    {

        public abstract void onFrameRateChage(float f, float f1);
    }

    public class FramerateToken
    {

        public float getFramerate()
        {
            return mFramerate;
        }

        public void requestFramerate(float f)
        {
            if(mFramerate != f)
            {
                if(FramerateTokenList._2D_get0(FramerateTokenList.this) != null)
                    FramerateTokenList._2D_get0(FramerateTokenList.this).onFrameRateChage(mFramerate, f);
                mFramerate = f;
                FramerateTokenList._2D_wrap0(FramerateTokenList.this);
            }
        }

        public float mFramerate;
        public String mName;
        final FramerateTokenList this$0;

        public FramerateToken(String s)
        {
            this$0 = FramerateTokenList.this;
            super();
            mName = s;
        }
    }


    static FramerateChangeListener _2D_get0(FramerateTokenList frameratetokenlist)
    {
        return frameratetokenlist.mFramerateChangeListener;
    }

    static void _2D_wrap0(FramerateTokenList frameratetokenlist)
    {
        frameratetokenlist.onChange();
    }

    public FramerateTokenList()
    {
        mList = new ArrayList();
    }

    public FramerateTokenList(FramerateChangeListener frameratechangelistener)
    {
        mList = new ArrayList();
        mFramerateChangeListener = frameratechangelistener;
    }

    private void onChange()
    {
        float f = 0.0F;
        ArrayList arraylist = mList;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mList.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            FramerateToken frameratetoken = (FramerateToken)iterator.next();
            if(frameratetoken.mFramerate > f)
                f = frameratetoken.mFramerate;
        } while(true);
        arraylist;
        JVM INSTR monitorexit ;
        mCurFramerate = f;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void clear()
    {
        ArrayList arraylist = mList;
        arraylist;
        JVM INSTR monitorenter ;
        mList.clear();
        arraylist;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public FramerateToken createToken(String s)
    {
        FramerateToken frameratetoken;
        Log.d("FramerateTokenList", (new StringBuilder()).append("createToken: ").append(s).toString());
        frameratetoken = new FramerateToken(s);
        s = mList;
        s;
        JVM INSTR monitorenter ;
        mList.add(frameratetoken);
        s;
        JVM INSTR monitorexit ;
        return frameratetoken;
        Exception exception;
        exception;
        throw exception;
    }

    public float getFramerate()
    {
        return mCurFramerate;
    }

    private static final String LOG_TAG = "FramerateTokenList";
    private float mCurFramerate;
    private FramerateChangeListener mFramerateChangeListener;
    private ArrayList mList;
}
