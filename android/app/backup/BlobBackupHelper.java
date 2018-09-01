// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.ParcelFileDescriptor;
import android.util.ArrayMap;
import android.util.Log;
import java.io.*;
import java.util.zip.*;

// Referenced classes of package android.app.backup:
//            BackupHelper, BackupDataOutput, BackupDataInputStream

public abstract class BlobBackupHelper
    implements BackupHelper
{

    public transient BlobBackupHelper(int i, String as[])
    {
        mCurrentBlobVersion = i;
        mKeys = as;
    }

    private long checksum(byte abyte0[])
    {
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_51;
        CRC32 crc32;
        ByteArrayInputStream bytearrayinputstream;
        crc32 = JVM INSTR new #33  <Class CRC32>;
        crc32.CRC32();
        bytearrayinputstream = JVM INSTR new #36  <Class ByteArrayInputStream>;
        bytearrayinputstream.ByteArrayInputStream(abyte0);
        abyte0 = new byte[4096];
_L1:
        int i = bytearrayinputstream.read(abyte0);
        if(i < 0)
            break MISSING_BLOCK_LABEL_55;
        crc32.update(abyte0, 0, i);
          goto _L1
        abyte0;
        return -1L;
        long l = crc32.getValue();
        return l;
    }

    private byte[] deflate(byte abyte0[])
    {
        Object obj = null;
        byte abyte1[] = obj;
        if(abyte0 != null)
            try
            {
                abyte1 = JVM INSTR new #59  <Class ByteArrayOutputStream>;
                abyte1.ByteArrayOutputStream();
                Object obj1 = JVM INSTR new #62  <Class DataOutputStream>;
                ((DataOutputStream) (obj1)).DataOutputStream(abyte1);
                ((DataOutputStream) (obj1)).writeInt(mCurrentBlobVersion);
                obj1 = JVM INSTR new #71  <Class DeflaterOutputStream>;
                ((DeflaterOutputStream) (obj1)).DeflaterOutputStream(abyte1);
                ((DeflaterOutputStream) (obj1)).write(abyte0);
                ((DeflaterOutputStream) (obj1)).close();
                abyte1 = abyte1.toByteArray();
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                Log.w("BlobBackupHelper", (new StringBuilder()).append("Unable to process payload: ").append(abyte0.getMessage()).toString());
                abyte1 = obj;
            }
        return abyte1;
    }

    private byte[] inflate(byte abyte0[])
    {
        Object obj;
        byte abyte1[];
        obj = null;
        abyte1 = obj;
        if(abyte0 == null) goto _L2; else goto _L1
_L1:
        abyte1 = JVM INSTR new #36  <Class ByteArrayInputStream>;
        abyte1.ByteArrayInputStream(abyte0);
        abyte0 = JVM INSTR new #107 <Class DataInputStream>;
        abyte0.DataInputStream(abyte1);
        int i = abyte0.readInt();
        if(i <= mCurrentBlobVersion)
            break MISSING_BLOCK_LABEL_71;
        abyte0 = JVM INSTR new #84  <Class StringBuilder>;
        abyte0.StringBuilder();
        Log.w("BlobBackupHelper", abyte0.append("Saved payload from unrecognized version ").append(i).toString());
        return null;
        byte abyte2[];
        abyte0 = JVM INSTR new #121 <Class InflaterInputStream>;
        abyte0.InflaterInputStream(abyte1);
        abyte1 = JVM INSTR new #59  <Class ByteArrayOutputStream>;
        abyte1.ByteArrayOutputStream();
        abyte2 = new byte[4096];
_L5:
        int j = abyte0.read(abyte2);
        if(j <= 0) goto _L4; else goto _L3
_L3:
        abyte1.write(abyte2, 0, j);
          goto _L5
_L2:
        return abyte1;
_L4:
        try
        {
            abyte0.close();
            abyte1.flush();
            abyte1 = abyte1.toByteArray();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.w("BlobBackupHelper", (new StringBuilder()).append("Unable to process restored payload: ").append(abyte0.getMessage()).toString());
            abyte1 = obj;
        }
        if(true) goto _L2; else goto _L6
_L6:
    }

    private ArrayMap readOldState(ParcelFileDescriptor parcelfiledescriptor)
    {
        ArrayMap arraymap;
        arraymap = new ArrayMap();
        parcelfiledescriptor = new DataInputStream(new FileInputStream(parcelfiledescriptor.getFileDescriptor()));
        int i = parcelfiledescriptor.readInt();
        if(i > mCurrentBlobVersion) goto _L2; else goto _L1
_L1:
        int j = parcelfiledescriptor.readInt();
        i = 0;
_L4:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        arraymap.put(parcelfiledescriptor.readUTF(), Long.valueOf(parcelfiledescriptor.readLong()));
        i++;
        if(true) goto _L4; else goto _L3
_L2:
        try
        {
            parcelfiledescriptor = JVM INSTR new #84  <Class StringBuilder>;
            parcelfiledescriptor.StringBuilder();
            Log.w("BlobBackupHelper", parcelfiledescriptor.append("Prior state from unrecognized version ").append(i).toString());
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            arraymap.clear();
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            Log.e("BlobBackupHelper", (new StringBuilder()).append("Error examining prior backup state ").append(parcelfiledescriptor.getMessage()).toString());
            arraymap.clear();
        }
_L3:
        return arraymap;
    }

    private void writeBackupState(ArrayMap arraymap, ParcelFileDescriptor parcelfiledescriptor)
    {
        FileOutputStream fileoutputstream = JVM INSTR new #182 <Class FileOutputStream>;
        fileoutputstream.FileOutputStream(parcelfiledescriptor.getFileDescriptor());
        parcelfiledescriptor = JVM INSTR new #62  <Class DataOutputStream>;
        parcelfiledescriptor.DataOutputStream(fileoutputstream);
        parcelfiledescriptor.writeInt(mCurrentBlobVersion);
        if(arraymap == null) goto _L2; else goto _L1
_L1:
        int i = arraymap.size();
_L5:
        parcelfiledescriptor.writeInt(i);
        int j = 0;
_L4:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        String s = (String)arraymap.keyAt(j);
        long l = ((Long)arraymap.valueAt(j)).longValue();
        parcelfiledescriptor.writeUTF(s);
        parcelfiledescriptor.writeLong(l);
        j++;
        if(true) goto _L4; else goto _L3
_L2:
        i = 0;
          goto _L5
        arraymap;
        Log.e("BlobBackupHelper", "Unable to write updated state", arraymap);
_L3:
    }

    protected abstract void applyRestoredPayload(String s, byte abyte0[]);

    protected abstract byte[] getBackupPayload(String s);

    public void performBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1)
    {
        ArrayMap arraymap;
        arraymap = readOldState(parcelfiledescriptor);
        parcelfiledescriptor = new ArrayMap();
        String as[] = mKeys;
        int i = 0;
        int j = as.length;
_L2:
        String s;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        s = as[i];
        byte abyte0[];
        long l;
        Long long1;
        abyte0 = deflate(getBackupPayload(s));
        l = checksum(abyte0);
        parcelfiledescriptor.put(s, Long.valueOf(l));
        long1 = (Long)arraymap.get(s);
        if(long1 == null)
            break MISSING_BLOCK_LABEL_103;
        if(l == long1.longValue())
            break MISSING_BLOCK_LABEL_128;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_134;
        backupdataoutput.writeEntityHeader(s, abyte0.length);
        backupdataoutput.writeEntityData(abyte0, abyte0.length);
_L3:
        i++;
        if(true) goto _L2; else goto _L1
        backupdataoutput.writeEntityHeader(s, -1);
          goto _L3
        Exception exception;
        exception;
        backupdataoutput = JVM INSTR new #84  <Class StringBuilder>;
        backupdataoutput.StringBuilder();
        Log.w("BlobBackupHelper", backupdataoutput.append("Unable to record notification state: ").append(exception.getMessage()).toString());
        parcelfiledescriptor.clear();
        writeBackupState(parcelfiledescriptor, parcelfiledescriptor1);
_L5:
        return;
_L1:
        writeBackupState(parcelfiledescriptor, parcelfiledescriptor1);
        if(true) goto _L5; else goto _L4
_L4:
        backupdataoutput;
        writeBackupState(parcelfiledescriptor, parcelfiledescriptor1);
        throw backupdataoutput;
    }

    public void restoreEntity(BackupDataInputStream backupdatainputstream)
    {
        String s;
        int i;
        s = backupdatainputstream.getKey();
        i = 0;
_L2:
        if(i >= mKeys.length || s.equals(mKeys[i]))
        {
            if(i >= mKeys.length)
            {
                backupdatainputstream = JVM INSTR new #84  <Class StringBuilder>;
                backupdatainputstream.StringBuilder();
                Log.e("BlobBackupHelper", backupdatainputstream.append("Unrecognized key ").append(s).append(", ignoring").toString());
                return;
            }
            break; /* Loop/switch isn't completed */
        }
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        byte abyte0[] = new byte[backupdatainputstream.size()];
        backupdatainputstream.read(abyte0);
        applyRestoredPayload(s, inflate(abyte0));
_L3:
        return;
        backupdatainputstream;
        Log.e("BlobBackupHelper", (new StringBuilder()).append("Exception restoring entity ").append(s).append(" : ").append(backupdatainputstream.getMessage()).toString());
          goto _L3
    }

    public void writeNewStateDescription(ParcelFileDescriptor parcelfiledescriptor)
    {
        writeBackupState(null, parcelfiledescriptor);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "BlobBackupHelper";
    private final int mCurrentBlobVersion;
    private final String mKeys[];
}
