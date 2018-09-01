// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.tech;

import android.nfc.*;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;

// Referenced classes of package android.nfc.tech:
//            BasicTagTechnology

public final class Ndef extends BasicTagTechnology
{

    public Ndef(Tag tag)
        throws RemoteException
    {
        super(tag, 6);
        tag = tag.getTechExtras(6);
        if(tag != null)
        {
            mMaxNdefSize = tag.getInt("ndefmaxlength");
            mCardState = tag.getInt("ndefcardstate");
            mNdefMsg = (NdefMessage)tag.getParcelable("ndefmsg");
            mNdefType = tag.getInt("ndeftype");
            return;
        } else
        {
            throw new NullPointerException("NDEF tech extras are null.");
        }
    }

    public static Ndef get(Tag tag)
    {
        if(!tag.hasTech(6))
            return null;
        try
        {
            tag = new Ndef(tag);
        }
        // Misplaced declaration of an exception variable
        catch(Tag tag)
        {
            return null;
        }
        return tag;
    }

    public boolean canMakeReadOnly()
    {
        INfcTag infctag = mTag.getTagService();
        if(infctag == null)
            return false;
        boolean flag;
        try
        {
            flag = infctag.canMakeReadOnly(mNdefType);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "NFC service dead", remoteexception);
            return false;
        }
        return flag;
    }

    public volatile void close()
    {
        super.close();
    }

    public volatile void connect()
    {
        super.connect();
    }

    public NdefMessage getCachedNdefMessage()
    {
        return mNdefMsg;
    }

    public int getMaxSize()
    {
        return mMaxNdefSize;
    }

    public NdefMessage getNdefMessage()
        throws IOException, FormatException
    {
        checkConnected();
        INfcTag infctag = mTag.getTagService();
        if(infctag == null)
        {
            try
            {
                IOException ioexception = JVM INSTR new #150 <Class IOException>;
                ioexception.IOException("Mock tags don't support this operation.");
                throw ioexception;
            }
            catch(RemoteException remoteexception)
            {
                Log.e("NFC", "NFC service dead", remoteexception);
            }
            return null;
        }
        Object obj;
        int i;
        i = mTag.getServiceHandle();
        if(!infctag.isNdef(i))
            break MISSING_BLOCK_LABEL_94;
        obj = infctag.ndefRead(i);
        if(obj != null)
            break MISSING_BLOCK_LABEL_92;
        if(infctag.isPresent(i) ^ true)
        {
            obj = JVM INSTR new #173 <Class TagLostException>;
            ((TagLostException) (obj)).TagLostException();
            throw obj;
        }
        return ((NdefMessage) (obj));
        if(!infctag.isPresent(i))
        {
            TagLostException taglostexception = JVM INSTR new #173 <Class TagLostException>;
            taglostexception.TagLostException();
            throw taglostexception;
        }
        return null;
    }

    public volatile Tag getTag()
    {
        return super.getTag();
    }

    public String getType()
    {
        switch(mNdefType)
        {
        default:
            return "android.ndef.unknown";

        case 1: // '\001'
            return "org.nfcforum.ndef.type1";

        case 2: // '\002'
            return "org.nfcforum.ndef.type2";

        case 3: // '\003'
            return "org.nfcforum.ndef.type3";

        case 4: // '\004'
            return "org.nfcforum.ndef.type4";

        case 101: // 'e'
            return "com.nxp.ndef.mifareclassic";

        case 102: // 'f'
            return "com.nxp.ndef.icodesli";
        }
    }

    public volatile boolean isConnected()
    {
        return super.isConnected();
    }

    public boolean isWritable()
    {
        boolean flag;
        if(mCardState == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean makeReadOnly()
        throws IOException
    {
        checkConnected();
        Object obj;
        try
        {
            obj = mTag.getTagService();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "NFC service dead", remoteexception);
            return false;
        }
        if(obj == null)
            return false;
        if(!((INfcTag) (obj)).isNdef(mTag.getServiceHandle())) goto _L2; else goto _L1
_L1:
        ((INfcTag) (obj)).ndefMakeReadOnly(mTag.getServiceHandle());
        JVM INSTR lookupswitch 3: default 80
    //                   -8: 114
    //                   -1: 104
    //                   0: 102;
           goto _L3 _L4 _L5 _L6
_L3:
        obj = JVM INSTR new #150 <Class IOException>;
        ((IOException) (obj)).IOException();
        throw obj;
_L6:
        return true;
_L5:
        IOException ioexception = JVM INSTR new #150 <Class IOException>;
        ioexception.IOException();
        throw ioexception;
_L4:
        return false;
_L2:
        IOException ioexception1 = JVM INSTR new #150 <Class IOException>;
        ioexception1.IOException("Tag is not ndef");
        throw ioexception1;
    }

    public volatile void reconnect()
    {
        super.reconnect();
    }

    public void writeNdefMessage(NdefMessage ndefmessage)
        throws IOException, FormatException
    {
        checkConnected();
        INfcTag infctag = mTag.getTagService();
        if(infctag != null) goto _L2; else goto _L1
_L1:
        try
        {
            ndefmessage = JVM INSTR new #150 <Class IOException>;
            ndefmessage.IOException("Mock tags don't support this operation.");
            throw ndefmessage;
        }
        // Misplaced declaration of an exception variable
        catch(NdefMessage ndefmessage)
        {
            Log.e("NFC", "NFC service dead", ndefmessage);
        }
_L8:
        return;
_L2:
        int i = mTag.getServiceHandle();
        if(!infctag.isNdef(i)) goto _L4; else goto _L3
_L3:
        infctag.ndefWrite(i, ndefmessage);
        JVM INSTR lookupswitch 3: default 100
    //                   -8: 120
    //                   -1: 110
    //                   0: 38;
           goto _L5 _L6 _L7 _L8
_L5:
        ndefmessage = JVM INSTR new #150 <Class IOException>;
        ndefmessage.IOException();
        throw ndefmessage;
_L7:
        ndefmessage = JVM INSTR new #150 <Class IOException>;
        ndefmessage.IOException();
        throw ndefmessage;
_L6:
        ndefmessage = JVM INSTR new #152 <Class FormatException>;
        ndefmessage.FormatException();
        throw ndefmessage;
_L4:
        ndefmessage = JVM INSTR new #150 <Class IOException>;
        ndefmessage.IOException("Tag is not ndef");
        throw ndefmessage;
    }

    public static final String EXTRA_NDEF_CARDSTATE = "ndefcardstate";
    public static final String EXTRA_NDEF_MAXLENGTH = "ndefmaxlength";
    public static final String EXTRA_NDEF_MSG = "ndefmsg";
    public static final String EXTRA_NDEF_TYPE = "ndeftype";
    public static final String ICODE_SLI = "com.nxp.ndef.icodesli";
    public static final String MIFARE_CLASSIC = "com.nxp.ndef.mifareclassic";
    public static final int NDEF_MODE_READ_ONLY = 1;
    public static final int NDEF_MODE_READ_WRITE = 2;
    public static final int NDEF_MODE_UNKNOWN = 3;
    public static final String NFC_FORUM_TYPE_1 = "org.nfcforum.ndef.type1";
    public static final String NFC_FORUM_TYPE_2 = "org.nfcforum.ndef.type2";
    public static final String NFC_FORUM_TYPE_3 = "org.nfcforum.ndef.type3";
    public static final String NFC_FORUM_TYPE_4 = "org.nfcforum.ndef.type4";
    private static final String TAG = "NFC";
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;
    public static final int TYPE_4 = 4;
    public static final int TYPE_ICODE_SLI = 102;
    public static final int TYPE_MIFARE_CLASSIC = 101;
    public static final int TYPE_OTHER = -1;
    public static final String UNKNOWN = "android.ndef.unknown";
    private final int mCardState;
    private final int mMaxNdefSize;
    private final NdefMessage mNdefMsg;
    private final int mNdefType;
}
