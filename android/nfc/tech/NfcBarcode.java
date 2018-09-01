// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.tech;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.RemoteException;

// Referenced classes of package android.nfc.tech:
//            BasicTagTechnology

public final class NfcBarcode extends BasicTagTechnology
{

    public NfcBarcode(Tag tag)
        throws RemoteException
    {
        super(tag, 10);
        tag = tag.getTechExtras(10);
        if(tag != null)
        {
            mType = tag.getInt("barcodetype");
            return;
        } else
        {
            throw new NullPointerException("NfcBarcode tech extras are null.");
        }
    }

    public static NfcBarcode get(Tag tag)
    {
        if(!tag.hasTech(10))
            return null;
        try
        {
            tag = new NfcBarcode(tag);
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

    public byte[] getBarcode()
    {
        switch(mType)
        {
        default:
            return null;

        case 1: // '\001'
            return mTag.getId();
        }
    }

    public volatile Tag getTag()
    {
        return super.getTag();
    }

    public int getType()
    {
        return mType;
    }

    public volatile boolean isConnected()
    {
        return super.isConnected();
    }

    public volatile void reconnect()
    {
        super.reconnect();
    }

    public static final String EXTRA_BARCODE_TYPE = "barcodetype";
    public static final int TYPE_KOVIO = 1;
    public static final int TYPE_UNKNOWN = -1;
    private int mType;
}
