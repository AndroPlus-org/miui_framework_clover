// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.content.Context;
import android.os.RemoteException;
import java.io.IOException;

// Referenced classes of package android.hardware:
//            ISerialManager, SerialPort

public class SerialManager
{

    public SerialManager(Context context, ISerialManager iserialmanager)
    {
        mContext = context;
        mService = iserialmanager;
    }

    public String[] getSerialPorts()
    {
        String as[];
        try
        {
            as = mService.getSerialPorts();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public SerialPort openSerialPort(String s, int i)
        throws IOException
    {
        Object obj;
        Object obj1;
        try
        {
            obj = mService.openSerialPort(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_36;
        obj1 = JVM INSTR new #43  <Class SerialPort>;
        ((SerialPort) (obj1)).SerialPort(s);
        ((SerialPort) (obj1)).open(((android.os.ParcelFileDescriptor) (obj)), i);
        return ((SerialPort) (obj1));
        obj1 = JVM INSTR new #38  <Class IOException>;
        obj = JVM INSTR new #52  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        ((IOException) (obj1)).IOException(((StringBuilder) (obj)).append("Could not open serial port ").append(s).toString());
        throw obj1;
    }

    private static final String TAG = "SerialManager";
    private final Context mContext;
    private final ISerialManager mService;
}
