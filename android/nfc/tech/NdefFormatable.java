// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.tech;

import android.nfc.*;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;

// Referenced classes of package android.nfc.tech:
//            BasicTagTechnology, MifareClassic

public final class NdefFormatable extends BasicTagTechnology
{

    public NdefFormatable(Tag tag)
        throws RemoteException
    {
        super(tag, 7);
    }

    public static NdefFormatable get(Tag tag)
    {
        if(!tag.hasTech(7))
            return null;
        try
        {
            tag = new NdefFormatable(tag);
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

    public void format(NdefMessage ndefmessage)
        throws IOException, FormatException
    {
        format(ndefmessage, false);
    }

    void format(NdefMessage ndefmessage, boolean flag)
        throws IOException, FormatException
    {
        checkConnected();
        int i;
        INfcTag infctag;
        i = mTag.getServiceHandle();
        infctag = mTag.getTagService();
        infctag.formatNdef(i, MifareClassic.KEY_DEFAULT);
        JVM INSTR lookupswitch 3: default 68
    //                   -8: 99
    //                   -1: 89
    //                   0: 109;
           goto _L1 _L2 _L3 _L4
_L1:
        ndefmessage = JVM INSTR new #38  <Class IOException>;
        ndefmessage.IOException();
        throw ndefmessage;
        ndefmessage;
        Log.e("NFC", "NFC service dead", ndefmessage);
_L6:
        return;
_L3:
        ndefmessage = JVM INSTR new #38  <Class IOException>;
        ndefmessage.IOException();
        throw ndefmessage;
_L2:
        ndefmessage = JVM INSTR new #40  <Class FormatException>;
        ndefmessage.FormatException();
        throw ndefmessage;
_L4:
        if(!infctag.isNdef(i))
        {
            ndefmessage = JVM INSTR new #38  <Class IOException>;
            ndefmessage.IOException();
            throw ndefmessage;
        }
        if(ndefmessage == null)
            continue; /* Loop/switch isn't completed */
        switch(infctag.ndefWrite(i, ndefmessage))
        {
        default:
            ndefmessage = JVM INSTR new #38  <Class IOException>;
            ndefmessage.IOException();
            throw ndefmessage;

        case -1: 
            ndefmessage = JVM INSTR new #38  <Class IOException>;
            ndefmessage.IOException();
            throw ndefmessage;

        case -8: 
            ndefmessage = JVM INSTR new #40  <Class FormatException>;
            ndefmessage.FormatException();
            throw ndefmessage;

        case 0: // '\0'
            break;
        }
        if(!flag) goto _L6; else goto _L5
_L5:
        infctag.ndefMakeReadOnly(i);
        JVM INSTR lookupswitch 3: default 252
    //                   -8: 272
    //                   -1: 262
    //                   0: 88;
           goto _L7 _L8 _L9 _L10
_L10:
        if(true) goto _L6; else goto _L11
_L11:
_L7:
        ndefmessage = JVM INSTR new #38  <Class IOException>;
        ndefmessage.IOException();
        throw ndefmessage;
_L9:
        ndefmessage = JVM INSTR new #38  <Class IOException>;
        ndefmessage.IOException();
        throw ndefmessage;
_L8:
        ndefmessage = JVM INSTR new #38  <Class IOException>;
        ndefmessage.IOException();
        throw ndefmessage;
    }

    public void formatReadOnly(NdefMessage ndefmessage)
        throws IOException, FormatException
    {
        format(ndefmessage, true);
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

    private static final String TAG = "NFC";
}
