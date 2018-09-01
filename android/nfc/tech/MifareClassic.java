// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.tech;

import android.nfc.*;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// Referenced classes of package android.nfc.tech:
//            BasicTagTechnology, NfcA

public final class MifareClassic extends BasicTagTechnology
{

    public MifareClassic(Tag tag)
        throws RemoteException
    {
        super(tag, 8);
        tag = NfcA.get(tag);
        mIsEmulated = false;
        tag.getSak();
        JVM INSTR lookupswitch 12: default 128
    //                   1: 158
    //                   8: 158
    //                   9: 171
    //                   16: 186
    //                   17: 201
    //                   24: 216
    //                   25: 231
    //                   40: 246
    //                   56: 266
    //                   136: 286
    //                   152: 301
    //                   184: 301;
           goto _L1 _L2 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L11
_L1:
        throw new RuntimeException((new StringBuilder()).append("Tag incorrectly enumerated as MIFARE Classic, SAK = ").append(tag.getSak()).toString());
_L2:
        mType = 0;
        mSize = 1024;
_L13:
        return;
_L3:
        mType = 0;
        mSize = 320;
        continue; /* Loop/switch isn't completed */
_L4:
        mType = 1;
        mSize = 2048;
        continue; /* Loop/switch isn't completed */
_L5:
        mType = 1;
        mSize = 4096;
        continue; /* Loop/switch isn't completed */
_L6:
        mType = 0;
        mSize = 4096;
        continue; /* Loop/switch isn't completed */
_L7:
        mType = 0;
        mSize = 2048;
        continue; /* Loop/switch isn't completed */
_L8:
        mType = 0;
        mSize = 1024;
        mIsEmulated = true;
        continue; /* Loop/switch isn't completed */
_L9:
        mType = 0;
        mSize = 4096;
        mIsEmulated = true;
        continue; /* Loop/switch isn't completed */
_L10:
        mType = 0;
        mSize = 1024;
        continue; /* Loop/switch isn't completed */
_L11:
        mType = 2;
        mSize = 4096;
        if(true) goto _L13; else goto _L12
_L12:
    }

    private boolean authenticate(int i, byte abyte0[], boolean flag)
        throws IOException
    {
        validateSector(i);
        checkConnected();
        byte abyte1[] = new byte[12];
        byte abyte2[];
        if(flag)
            abyte1[0] = (byte)96;
        else
            abyte1[0] = (byte)97;
        abyte1[1] = (byte)sectorToBlock(i);
        abyte2 = getTag().getId();
        System.arraycopy(abyte2, abyte2.length - 4, abyte1, 2, 4);
        System.arraycopy(abyte0, 0, abyte1, 6, 6);
        abyte0 = transceive(abyte1, false);
        if(abyte0 != null)
            return true;
        break MISSING_BLOCK_LABEL_95;
        abyte0;
        return false;
        abyte0;
        throw abyte0;
    }

    public static MifareClassic get(Tag tag)
    {
        if(!tag.hasTech(8))
            return null;
        try
        {
            tag = new MifareClassic(tag);
        }
        // Misplaced declaration of an exception variable
        catch(Tag tag)
        {
            return null;
        }
        return tag;
    }

    private static void validateBlock(int i)
    {
        if(i < 0 || i >= 256)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("block out of bounds: ").append(i).toString());
        else
            return;
    }

    private static void validateSector(int i)
    {
        if(i < 0 || i >= 40)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("sector out of bounds: ").append(i).toString());
        else
            return;
    }

    private static void validateValueOperand(int i)
    {
        if(i < 0)
            throw new IllegalArgumentException("value operand negative");
        else
            return;
    }

    public boolean authenticateSectorWithKeyA(int i, byte abyte0[])
        throws IOException
    {
        return authenticate(i, abyte0, true);
    }

    public boolean authenticateSectorWithKeyB(int i, byte abyte0[])
        throws IOException
    {
        return authenticate(i, abyte0, false);
    }

    public int blockToSector(int i)
    {
        validateBlock(i);
        if(i < 128)
            return i / 4;
        else
            return (i - 128) / 16 + 32;
    }

    public volatile void close()
    {
        super.close();
    }

    public volatile void connect()
    {
        super.connect();
    }

    public void decrement(int i, int j)
        throws IOException
    {
        validateBlock(i);
        validateValueOperand(j);
        checkConnected();
        ByteBuffer bytebuffer = ByteBuffer.allocate(6);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        bytebuffer.put((byte)-64);
        bytebuffer.put((byte)i);
        bytebuffer.putInt(j);
        transceive(bytebuffer.array(), false);
    }

    public int getBlockCount()
    {
        return mSize / 16;
    }

    public int getBlockCountInSector(int i)
    {
        validateSector(i);
        return i >= 32 ? 16 : 4;
    }

    public int getMaxTransceiveLength()
    {
        return getMaxTransceiveLengthInternal();
    }

    public int getSectorCount()
    {
        switch(mSize)
        {
        default:
            return 0;

        case 1024: 
            return 16;

        case 2048: 
            return 32;

        case 4096: 
            return 40;

        case 320: 
            return 5;
        }
    }

    public int getSize()
    {
        return mSize;
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
            i = mTag.getTagService().getTimeout(8);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "NFC service dead", remoteexception);
            return 0;
        }
        return i;
    }

    public int getType()
    {
        return mType;
    }

    public void increment(int i, int j)
        throws IOException
    {
        validateBlock(i);
        validateValueOperand(j);
        checkConnected();
        ByteBuffer bytebuffer = ByteBuffer.allocate(6);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        bytebuffer.put((byte)-63);
        bytebuffer.put((byte)i);
        bytebuffer.putInt(j);
        transceive(bytebuffer.array(), false);
    }

    public volatile boolean isConnected()
    {
        return super.isConnected();
    }

    public boolean isEmulated()
    {
        return mIsEmulated;
    }

    public byte[] readBlock(int i)
        throws IOException
    {
        validateBlock(i);
        checkConnected();
        return transceive(new byte[] {
            48, (byte)i
        }, false);
    }

    public volatile void reconnect()
    {
        super.reconnect();
    }

    public void restore(int i)
        throws IOException
    {
        validateBlock(i);
        checkConnected();
        transceive(new byte[] {
            -62, (byte)i
        }, false);
    }

    public int sectorToBlock(int i)
    {
        if(i < 32)
            return i * 4;
        else
            return (i - 32) * 16 + 128;
    }

    public void setTimeout(int i)
    {
        try
        {
            if(mTag.getTagService().setTimeout(8, i) != 0)
            {
                IllegalArgumentException illegalargumentexception = JVM INSTR new #157 <Class IllegalArgumentException>;
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

    public void transfer(int i)
        throws IOException
    {
        validateBlock(i);
        checkConnected();
        transceive(new byte[] {
            -80, (byte)i
        }, false);
    }

    public void writeBlock(int i, byte abyte0[])
        throws IOException
    {
        validateBlock(i);
        checkConnected();
        if(abyte0.length != 16)
        {
            throw new IllegalArgumentException("must write 16-bytes");
        } else
        {
            byte abyte1[] = new byte[abyte0.length + 2];
            abyte1[0] = (byte)-96;
            abyte1[1] = (byte)i;
            System.arraycopy(abyte0, 0, abyte1, 2, abyte0.length);
            transceive(abyte1, false);
            return;
        }
    }

    public static final int BLOCK_SIZE = 16;
    public static final byte KEY_DEFAULT[] = {
        -1, -1, -1, -1, -1, -1
    };
    public static final byte KEY_MIFARE_APPLICATION_DIRECTORY[] = {
        -96, -95, -94, -93, -92, -91
    };
    public static final byte KEY_NFC_FORUM[] = {
        -45, -9, -45, -9, -45, -9
    };
    private static final int MAX_BLOCK_COUNT = 256;
    private static final int MAX_SECTOR_COUNT = 40;
    public static final int SIZE_1K = 1024;
    public static final int SIZE_2K = 2048;
    public static final int SIZE_4K = 4096;
    public static final int SIZE_MINI = 320;
    private static final String TAG = "NFC";
    public static final int TYPE_CLASSIC = 0;
    public static final int TYPE_PLUS = 1;
    public static final int TYPE_PRO = 2;
    public static final int TYPE_UNKNOWN = -1;
    private boolean mIsEmulated;
    private int mSize;
    private int mType;

}
