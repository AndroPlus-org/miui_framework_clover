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

public final class NfcV extends BasicTagTechnology
{

    public NfcV(Tag tag)
        throws RemoteException
    {
        super(tag, 5);
        tag = tag.getTechExtras(5);
        mRespFlags = tag.getByte("respflags");
        mDsfId = tag.getByte("dsfid");
    }

    public static NfcV get(Tag tag)
    {
        if(!tag.hasTech(5))
            return null;
        try
        {
            tag = new NfcV(tag);
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

    public byte getDsfId()
    {
        return mDsfId;
    }

    public int getMaxTransceiveLength()
    {
        return getMaxTransceiveLengthInternal();
    }

    public byte getResponseFlags()
    {
        return mRespFlags;
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

    public static final String EXTRA_DSFID = "dsfid";
    public static final String EXTRA_RESP_FLAGS = "respflags";
    private byte mDsfId;
    private byte mRespFlags;
}
