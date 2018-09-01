// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.tech;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.RemoteException;
import java.io.IOException;

// Referenced classes of package android.nfc.tech:
//            BasicTagTechnology

public final class NfcB extends BasicTagTechnology
{

    public NfcB(Tag tag)
        throws RemoteException
    {
        super(tag, 2);
        tag = tag.getTechExtras(2);
        mAppData = tag.getByteArray("appdata");
        mProtInfo = tag.getByteArray("protinfo");
    }

    public static NfcB get(Tag tag)
    {
        if(!tag.hasTech(2))
            return null;
        try
        {
            tag = new NfcB(tag);
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

    public byte[] getApplicationData()
    {
        return mAppData;
    }

    public int getMaxTransceiveLength()
    {
        return getMaxTransceiveLengthInternal();
    }

    public byte[] getProtocolInfo()
    {
        return mProtInfo;
    }

    public volatile Tag getTag()
    {
        return super.getTag();
    }

    public volatile boolean isConnected()
    {
        return super.isConnected();
    }

    public volatile void reconnect()
    {
        super.reconnect();
    }

    public byte[] transceive(byte abyte0[])
        throws IOException
    {
        return transceive(abyte0, true);
    }

    public static final String EXTRA_APPDATA = "appdata";
    public static final String EXTRA_PROTINFO = "protinfo";
    private byte mAppData[];
    private byte mProtInfo[];
}
