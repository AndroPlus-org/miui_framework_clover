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

public final class IsoDep extends BasicTagTechnology
{

    public IsoDep(Tag tag)
        throws RemoteException
    {
        super(tag, 3);
        mHiLayerResponse = null;
        mHistBytes = null;
        tag = tag.getTechExtras(3);
        if(tag != null)
        {
            mHiLayerResponse = tag.getByteArray("hiresp");
            mHistBytes = tag.getByteArray("histbytes");
        }
    }

    public static IsoDep get(Tag tag)
    {
        if(!tag.hasTech(3))
            return null;
        try
        {
            tag = new IsoDep(tag);
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

    public byte[] getHiLayerResponse()
    {
        return mHiLayerResponse;
    }

    public byte[] getHistoricalBytes()
    {
        return mHistBytes;
    }

    public int getMaxTransceiveLength()
    {
        return getMaxTransceiveLengthInternal();
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
            i = mTag.getTagService().getTimeout(3);
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

    public boolean isExtendedLengthApduSupported()
    {
        boolean flag;
        try
        {
            flag = mTag.getTagService().getExtendedLengthApdusSupported();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "NFC service dead", remoteexception);
            return false;
        }
        return flag;
    }

    public volatile void reconnect()
    {
        super.reconnect();
    }

    public void setTimeout(int i)
    {
        try
        {
            if(mTag.getTagService().setTimeout(3, i) != 0)
            {
                IllegalArgumentException illegalargumentexception = JVM INSTR new #109 <Class IllegalArgumentException>;
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

    public static final String EXTRA_HIST_BYTES = "histbytes";
    public static final String EXTRA_HI_LAYER_RESP = "hiresp";
    private static final String TAG = "NFC";
    private byte mHiLayerResponse[];
    private byte mHistBytes[];
}
