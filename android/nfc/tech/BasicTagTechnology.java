// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.tech;

import android.nfc.*;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;

// Referenced classes of package android.nfc.tech:
//            TagTechnology

abstract class BasicTagTechnology
    implements TagTechnology
{

    BasicTagTechnology(Tag tag, int i)
        throws RemoteException
    {
        mTag = tag;
        mSelectedTechnology = i;
    }

    void checkConnected()
    {
        if(mTag.getConnectedTechnology() != mSelectedTechnology || mTag.getConnectedTechnology() == -1)
            throw new IllegalStateException("Call connect() first!");
        else
            return;
    }

    public void close()
        throws IOException
    {
        mTag.getTagService().resetTimeouts();
        mTag.getTagService().reconnect(mTag.getServiceHandle());
        mIsConnected = false;
        mTag.setTechnologyDisconnected();
_L2:
        return;
        Object obj;
        obj;
        Log.e("NFC", "NFC service dead", ((Throwable) (obj)));
        mIsConnected = false;
        mTag.setTechnologyDisconnected();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mIsConnected = false;
        mTag.setTechnologyDisconnected();
        throw obj;
    }

    public void connect()
        throws IOException
    {
        int i = mTag.getTagService().connect(mTag.getServiceHandle(), mSelectedTechnology);
        if(i == 0)
        {
            UnsupportedOperationException unsupportedoperationexception;
            try
            {
                mTag.setConnectedTechnology(mSelectedTechnology);
                mIsConnected = true;
                return;
            }
            catch(RemoteException remoteexception)
            {
                Log.e("NFC", "NFC service dead", remoteexception);
            }
            break MISSING_BLOCK_LABEL_73;
        }
        if(i != -21)
            break MISSING_BLOCK_LABEL_83;
        unsupportedoperationexception = JVM INSTR new #85  <Class UnsupportedOperationException>;
        unsupportedoperationexception.UnsupportedOperationException("Connecting to this technology is not supported by the NFC adapter.");
        throw unsupportedoperationexception;
        throw new IOException("NFC service died");
        IOException ioexception = JVM INSTR new #46  <Class IOException>;
        ioexception.IOException();
        throw ioexception;
    }

    int getMaxTransceiveLengthInternal()
    {
        int i;
        try
        {
            i = mTag.getTagService().getMaxTransceiveLength(mSelectedTechnology);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "NFC service dead", remoteexception);
            return 0;
        }
        return i;
    }

    public Tag getTag()
    {
        return mTag;
    }

    public boolean isConnected()
    {
        if(!mIsConnected)
            return false;
        boolean flag;
        try
        {
            flag = mTag.getTagService().isPresent(mTag.getServiceHandle());
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "NFC service dead", remoteexception);
            return false;
        }
        return flag;
    }

    public void reconnect()
        throws IOException
    {
        if(!mIsConnected)
            throw new IllegalStateException("Technology not connected yet");
        try
        {
            if(mTag.getTagService().reconnect(mTag.getServiceHandle()) != 0)
            {
                mIsConnected = false;
                mTag.setTechnologyDisconnected();
                IOException ioexception = JVM INSTR new #46  <Class IOException>;
                ioexception.IOException();
                throw ioexception;
            }
        }
        catch(RemoteException remoteexception)
        {
            mIsConnected = false;
            mTag.setTechnologyDisconnected();
            Log.e("NFC", "NFC service dead", remoteexception);
            throw new IOException("NFC service died");
        }
    }

    byte[] transceive(byte abyte0[], boolean flag)
        throws IOException
    {
        checkConnected();
        abyte0 = mTag.getTagService().transceive(mTag.getServiceHandle(), abyte0, flag);
        if(abyte0 == null)
        {
            try
            {
                abyte0 = JVM INSTR new #46  <Class IOException>;
                abyte0.IOException("transceive failed");
                throw abyte0;
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                Log.e("NFC", "NFC service dead", abyte0);
            }
            throw new IOException("NFC service died");
        }
        abyte0 = abyte0.getResponseOrThrow();
        return abyte0;
    }

    private static final String TAG = "NFC";
    boolean mIsConnected;
    int mSelectedTechnology;
    final Tag mTag;
}
