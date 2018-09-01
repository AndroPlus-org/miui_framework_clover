// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.tech;

import android.nfc.INfcTag;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;

// Referenced classes of package android.nfc.tech:
//            BasicTagTechnology

public final class NfcF extends BasicTagTechnology
{

    public NfcF(Tag tag)
        throws RemoteException
    {
        super(tag, 4);
        mSystemCode = null;
        mManufacturer = null;
        tag = tag.getTechExtras(4);
        if(tag != null)
        {
            mSystemCode = tag.getByteArray("systemcode");
            mManufacturer = tag.getByteArray("pmm");
        }
    }

    public static NfcF get(Tag tag)
    {
        if(!tag.hasTech(4))
            return null;
        try
        {
            tag = new NfcF(tag);
        }
        // Misplaced declaration of an exception variable
        catch(Tag tag)
        {
            return null;
        }
        return tag;
    }

    public volatile void close()
    {
        super.close();
    }

    public volatile void connect()
    {
        super.connect();
    }

    public byte[] getManufacturer()
    {
        return mManufacturer;
    }

    public int getMaxTransceiveLength()
    {
        return getMaxTransceiveLengthInternal();
    }

    public byte[] getSystemCode()
    {
        return mSystemCode;
    }

    public volatile Tag getTag()
    {
        return super.getTag();
    }

    public int getTimeout()
    {
        int i;
        try
        {
            i = mTag.getTagService().getTimeout(4);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "NFC service dead", remoteexception);
            return 0;
        }
        return i;
    }

    public volatile boolean isConnected()
    {
        return super.isConnected();
    }

    public volatile void reconnect()
    {
        super.reconnect();
    }

    public void setTimeout(int i)
    {
        try
        {
            if(mTag.getTagService().setTimeout(4, i) != 0)
            {
                IllegalArgumentException illegalargumentexception = JVM INSTR new #105 <Class IllegalArgumentException>;
                illegalargumentexception.IllegalArgumentException("The supplied timeout is not valid");
                throw illegalargumentexception;
            }
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "NFC service dead", remoteexception);
        }
    }

    public byte[] transceive(byte abyte0[])
        throws IOException
    {
        return transceive(abyte0, true);
    }

    public static final String EXTRA_PMM = "pmm";
    public static final String EXTRA_SC = "systemcode";
    private static final String TAG = "NFC";
    private byte mManufacturer[];
    private byte mSystemCode[];
}
