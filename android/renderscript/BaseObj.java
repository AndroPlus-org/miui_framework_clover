// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import dalvik.system.CloseGuard;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package android.renderscript:
//            RenderScript, RSIllegalArgumentException, RSInvalidStateException, RSRuntimeException

public class BaseObj
{

    BaseObj(long l, RenderScript renderscript)
    {
        renderscript.validate();
        mRS = renderscript;
        mID = l;
        mDestroyed = false;
    }

    private void helpDestroy()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if(mDestroyed)
            break MISSING_BLOCK_LABEL_18;
        flag = true;
        mDestroyed = true;
        this;
        JVM INSTR monitorexit ;
        if(flag)
        {
            guard.close();
            java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock readlock = mRS.mRWLock.readLock();
            readlock.lock();
            if(mRS.isAlive() && mID != 0L)
                mRS.nObjDestroy(mID);
            readlock.unlock();
            mRS = null;
            mID = 0L;
        }
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void checkValid()
    {
        if(mID == 0L)
            throw new RSIllegalArgumentException("Invalid object.");
        else
            return;
    }

    public void destroy()
    {
        if(mDestroyed)
        {
            throw new RSInvalidStateException("Object already destroyed.");
        } else
        {
            helpDestroy();
            return;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (BaseObj)obj;
        if(mID != ((BaseObj) (obj)).mID)
            flag = false;
        return flag;
    }

    protected void finalize()
        throws Throwable
    {
        if(guard != null)
            guard.warnIfOpen();
        helpDestroy();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    long getID(RenderScript renderscript)
    {
        mRS.validate();
        if(mDestroyed)
            throw new RSInvalidStateException("using a destroyed object.");
        if(mID == 0L)
            throw new RSRuntimeException("Internal error: Object id 0.");
        if(renderscript != null && renderscript != mRS)
            throw new RSInvalidStateException("using object with mismatched context.");
        else
            return mID;
    }

    public String getName()
    {
        return mName;
    }

    public int hashCode()
    {
        return (int)(mID & 0xfffffffL ^ mID >> 32);
    }

    void setID(long l)
    {
        if(mID != 0L)
        {
            throw new RSRuntimeException("Internal Error, reset of object ID.");
        } else
        {
            mID = l;
            return;
        }
    }

    public void setName(String s)
    {
        if(s == null)
            throw new RSIllegalArgumentException("setName requires a string of non-zero length.");
        if(s.length() < 1)
            throw new RSIllegalArgumentException("setName does not accept a zero length string.");
        if(mName != null)
            throw new RSIllegalArgumentException("setName object already has a name.");
        try
        {
            byte abyte0[] = s.getBytes("UTF-8");
            mRS.nAssignName(mID, abyte0);
            mName = s;
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException(s);
        }
    }

    void updateFromNative()
    {
        mRS.validate();
        mName = mRS.nGetName(getID(mRS));
    }

    final CloseGuard guard = CloseGuard.get();
    private boolean mDestroyed;
    private long mID;
    private String mName;
    RenderScript mRS;
}
