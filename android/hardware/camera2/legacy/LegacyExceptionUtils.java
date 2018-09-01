// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.os.ServiceSpecificException;
import android.system.OsConstants;
import android.util.AndroidException;

public class LegacyExceptionUtils
{
    public static class BufferQueueAbandonedException extends AndroidException
    {

        public BufferQueueAbandonedException()
        {
        }

        public BufferQueueAbandonedException(Exception exception)
        {
            super(exception);
        }

        public BufferQueueAbandonedException(String s)
        {
            super(s);
        }

        public BufferQueueAbandonedException(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }


    private LegacyExceptionUtils()
    {
        throw new AssertionError();
    }

    public static int throwOnError(int i)
        throws BufferQueueAbandonedException
    {
        if(i == 0)
            return 0;
        if(i == -OsConstants.ENODEV)
            throw new BufferQueueAbandonedException();
        if(i < 0)
            throw new UnsupportedOperationException((new StringBuilder()).append("Unknown error ").append(i).toString());
        else
            return i;
    }

    public static void throwOnServiceError(int i)
    {
        if(i >= 0)
            return;
        String s;
        if(i == PERMISSION_DENIED)
        {
            i = 1;
            s = "Lacking privileges to access camera service";
        } else
        {
            if(i == ALREADY_EXISTS)
                return;
            if(i == BAD_VALUE)
            {
                i = 3;
                s = "Bad argument passed to camera service";
            } else
            if(i == DEAD_OBJECT)
            {
                i = 4;
                s = "Camera service not available";
            } else
            if(i == TIMED_OUT)
            {
                i = 10;
                s = "Operation timed out in camera service";
            } else
            if(i == -OsConstants.EACCES)
            {
                i = 6;
                s = "Camera disabled by policy";
            } else
            if(i == -OsConstants.EBUSY)
            {
                i = 7;
                s = "Camera already in use";
            } else
            if(i == -OsConstants.EUSERS)
            {
                i = 8;
                s = "Maximum number of cameras in use";
            } else
            if(i == -OsConstants.ENODEV)
            {
                i = 4;
                s = "Camera device not available";
            } else
            if(i == -OsConstants.EOPNOTSUPP)
            {
                i = 9;
                s = "Deprecated camera HAL does not support this";
            } else
            if(i == INVALID_OPERATION)
            {
                i = 10;
                s = "Illegal state encountered in camera service.";
            } else
            {
                byte byte0 = 10;
                s = (new StringBuilder()).append("Unknown camera device error ").append(i).toString();
                i = byte0;
            }
        }
        throw new ServiceSpecificException(i, s);
    }

    public static final int ALREADY_EXISTS;
    public static final int BAD_VALUE;
    public static final int DEAD_OBJECT;
    public static final int INVALID_OPERATION;
    public static final int NO_ERROR = 0;
    public static final int PERMISSION_DENIED;
    private static final String TAG = "LegacyExceptionUtils";
    public static final int TIMED_OUT;

    static 
    {
        PERMISSION_DENIED = -OsConstants.EPERM;
        ALREADY_EXISTS = -OsConstants.EEXIST;
        BAD_VALUE = -OsConstants.EINVAL;
        DEAD_OBJECT = -OsConstants.ENOSYS;
        INVALID_OPERATION = -OsConstants.EPIPE;
        TIMED_OUT = -OsConstants.ETIMEDOUT;
    }
}
