// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.ParcelableException;
import com.android.internal.util.Preconditions;
import java.io.IOException;

public class ExceptionUtils
{

    public ExceptionUtils()
    {
    }

    public static String getCompleteMessage(String s, Throwable throwable)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(s != null)
            stringbuilder.append(s).append(": ");
        stringbuilder.append(throwable.getMessage());
        do
        {
            throwable = throwable.getCause();
            if(throwable != null)
                stringbuilder.append(": ").append(throwable.getMessage());
            else
                return stringbuilder.toString();
        } while(true);
    }

    public static String getCompleteMessage(Throwable throwable)
    {
        return getCompleteMessage(null, throwable);
    }

    public static void maybeUnwrapIOException(RuntimeException runtimeexception)
        throws IOException
    {
        if(runtimeexception instanceof ParcelableException)
            ((ParcelableException)runtimeexception).maybeRethrow(java/io/IOException);
    }

    public static RuntimeException propagate(Throwable throwable)
    {
        Preconditions.checkNotNull(throwable);
        propagateIfInstanceOf(throwable, java/lang/Error);
        propagateIfInstanceOf(throwable, java/lang/RuntimeException);
        throw new RuntimeException(throwable);
    }

    public static RuntimeException propagate(Throwable throwable, Class class1)
        throws Exception
    {
        propagateIfInstanceOf(throwable, class1);
        return propagate(throwable);
    }

    public static void propagateIfInstanceOf(Throwable throwable, Class class1)
        throws Throwable
    {
        if(throwable != null && class1.isInstance(throwable))
            throw (Throwable)class1.cast(throwable);
        else
            return;
    }

    public static RuntimeException wrap(IOException ioexception)
    {
        throw new ParcelableException(ioexception);
    }
}
