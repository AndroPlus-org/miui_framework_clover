// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.ServiceSpecificException;
import android.util.AndroidException;

// Referenced classes of package android.net.lowpan:
//            LowpanRuntimeException, InterfaceDisabledException, WrongStateException, OperationCanceledException, 
//            JoinFailedException, JoinFailedAtScanException, JoinFailedAtAuthException, NetworkAlreadyExistsException

public class LowpanException extends AndroidException
{

    public LowpanException()
    {
    }

    public LowpanException(Exception exception)
    {
        super(exception);
    }

    public LowpanException(String s)
    {
        super(s);
    }

    public LowpanException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    static LowpanException rethrowFromServiceSpecificException(ServiceSpecificException servicespecificexception)
        throws LowpanException
    {
        switch(servicespecificexception.errorCode)
        {
        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
        case 9: // '\t'
        default:
            throw new LowpanRuntimeException(servicespecificexception);

        case 3: // '\003'
            throw new InterfaceDisabledException(servicespecificexception);

        case 4: // '\004'
            throw new WrongStateException(servicespecificexception);

        case 10: // '\n'
            throw new OperationCanceledException(servicespecificexception);

        case 12: // '\f'
            throw new JoinFailedException(servicespecificexception);

        case 13: // '\r'
            throw new JoinFailedAtScanException(servicespecificexception);

        case 14: // '\016'
            throw new JoinFailedAtAuthException(servicespecificexception);

        case 15: // '\017'
            throw new NetworkAlreadyExistsException(servicespecificexception);

        case 11: // '\013'
            String s;
            if(servicespecificexception.getMessage() != null)
                s = servicespecificexception.getMessage();
            else
                s = "Feature not supported";
            throw new LowpanException(s, servicespecificexception);

        case 7: // '\007'
            String s1;
            if(servicespecificexception.getMessage() != null)
                s1 = servicespecificexception.getMessage();
            else
                s1 = "NCP problem";
            throw new LowpanRuntimeException(s1, servicespecificexception);

        case 2: // '\002'
            break;
        }
        String s2;
        if(servicespecificexception.getMessage() != null)
            s2 = servicespecificexception.getMessage();
        else
            s2 = "Invalid argument";
        throw new LowpanRuntimeException(s2, servicespecificexception);
    }
}
