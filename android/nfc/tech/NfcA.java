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

public final class NfcA extends BasicTagTechnology
{

    public NfcA(Tag tag)
        throws RemoteException
    {
        super(tag, 1);
        mSak = (short)0;
        if(tag.hasTech(8))
            mSak = tag.getTechExtras(8).getShort("sak");
        tag = tag.getTechExtras(1);
        mSak = (short)(mSak | tag.getShort("sak"));
        mAtqa = tag.getByteArray("atqa");
    }

    public static NfcA get(Tag tag)
    {
        if(!tag.hasTech(1))
            return null;
        try
        {
            tag = new NfcA(tag);
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

    public byte[] getAtqa()
    {
        return mAtqa;
    }

    public int getMaxTransceiveLength()
    {
        return getMaxTransceiveLengthInternal();
    }

    public short getSak()
    {
        return mSak;
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
            i = mTag.getTagService().getTimeout(1);
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
            if(mTag.getTagService().setTimeout(1, i) != 0)
            {
                IllegalArgumentException illegalargumentexception = JVM INSTR new #111 <Class IllegalArgumentException>;
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

    public static final String EXTRA_ATQA = "atqa";
    public static final String EXTRA_SAK = "sak";
    private static final String TAG = "NFC";
    private byte mAtqa[];
    private short mSak;
}
