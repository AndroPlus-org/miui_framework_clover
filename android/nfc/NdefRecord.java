// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Referenced classes of package android.nfc:
//            FormatException, NdefMessage

public final class NdefRecord
    implements Parcelable
{

    public NdefRecord(short word0, byte abyte0[], byte abyte1[], byte abyte2[])
    {
        byte abyte3[] = abyte0;
        if(abyte0 == null)
            abyte3 = EMPTY_BYTE_ARRAY;
        abyte0 = abyte1;
        if(abyte1 == null)
            abyte0 = EMPTY_BYTE_ARRAY;
        abyte1 = abyte2;
        if(abyte2 == null)
            abyte1 = EMPTY_BYTE_ARRAY;
        abyte2 = validateTnf(word0, abyte3, abyte0, abyte1);
        if(abyte2 != null)
        {
            throw new IllegalArgumentException(abyte2);
        } else
        {
            mTnf = word0;
            mType = abyte3;
            mId = abyte0;
            mPayload = abyte1;
            return;
        }
    }

    public NdefRecord(byte abyte0[])
        throws FormatException
    {
        abyte0 = ByteBuffer.wrap(abyte0);
        NdefRecord andefrecord[] = parse(abyte0, true);
        if(abyte0.remaining() > 0)
        {
            throw new FormatException("data too long");
        } else
        {
            mTnf = andefrecord[0].mTnf;
            mType = andefrecord[0].mType;
            mId = andefrecord[0].mId;
            mPayload = andefrecord[0].mPayload;
            return;
        }
    }

    private static StringBuilder bytesToString(byte abyte0[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = abyte0.length;
        for(int j = 0; j < i; j++)
            stringbuilder.append(String.format("%02X", new Object[] {
                Byte.valueOf(abyte0[j])
            }));

        return stringbuilder;
    }

    public static NdefRecord createApplicationRecord(String s)
    {
        if(s == null)
            throw new NullPointerException("packageName is null");
        if(s.length() == 0)
            throw new IllegalArgumentException("packageName is empty");
        else
            return new NdefRecord((short)4, RTD_ANDROID_APP, null, s.getBytes(StandardCharsets.UTF_8));
    }

    public static NdefRecord createExternal(String s, String s1, byte abyte0[])
    {
        if(s == null)
            throw new NullPointerException("domain is null");
        if(s1 == null)
            throw new NullPointerException("type is null");
        s = s.trim().toLowerCase(Locale.ROOT);
        s1 = s1.trim().toLowerCase(Locale.ROOT);
        if(s.length() == 0)
            throw new IllegalArgumentException("domain is empty");
        if(s1.length() == 0)
        {
            throw new IllegalArgumentException("type is empty");
        } else
        {
            s = s.getBytes(StandardCharsets.UTF_8);
            s1 = s1.getBytes(StandardCharsets.UTF_8);
            byte abyte1[] = new byte[s.length + 1 + s1.length];
            System.arraycopy(s, 0, abyte1, 0, s.length);
            abyte1[s.length] = (byte)58;
            System.arraycopy(s1, 0, abyte1, s.length + 1, s1.length);
            return new NdefRecord((short)4, abyte1, null, abyte0);
        }
    }

    public static NdefRecord createMime(String s, byte abyte0[])
    {
        if(s == null)
            throw new NullPointerException("mimeType is null");
        s = Intent.normalizeMimeType(s);
        if(s.length() == 0)
            throw new IllegalArgumentException("mimeType is empty");
        int i = s.indexOf('/');
        if(i == 0)
            throw new IllegalArgumentException("mimeType must have major type");
        if(i == s.length() - 1)
            throw new IllegalArgumentException("mimeType must have minor type");
        else
            return new NdefRecord((short)2, s.getBytes(StandardCharsets.US_ASCII), null, abyte0);
    }

    public static NdefRecord createTextRecord(String s, String s1)
    {
        if(s1 == null)
            throw new NullPointerException("text is null");
        s1 = s1.getBytes(StandardCharsets.UTF_8);
        if(s != null && s.isEmpty() ^ true)
            s = s.getBytes(StandardCharsets.US_ASCII);
        else
            s = Locale.getDefault().getLanguage().getBytes(StandardCharsets.US_ASCII);
        if(s.length >= 64)
        {
            throw new IllegalArgumentException("language code is too long, must be <64 bytes.");
        } else
        {
            ByteBuffer bytebuffer = ByteBuffer.allocate(s.length + 1 + s1.length);
            bytebuffer.put((byte)(s.length & 0xff));
            bytebuffer.put(s);
            bytebuffer.put(s1);
            return new NdefRecord((short)1, RTD_TEXT, null, bytebuffer.array());
        }
    }

    public static NdefRecord createUri(Uri uri)
    {
        if(uri == null)
            throw new NullPointerException("uri is null");
        String s = uri.normalizeScheme().toString();
        if(s.length() == 0)
            throw new IllegalArgumentException("uri is empty");
        boolean flag = false;
        int i = 1;
        do
        {
label0:
            {
                int j = ((flag) ? 1 : 0);
                uri = s;
                if(i < URI_PREFIX_MAP.length)
                {
                    if(!s.startsWith(URI_PREFIX_MAP[i]))
                        break label0;
                    j = (byte)i;
                    uri = s.substring(URI_PREFIX_MAP[i].length());
                }
                byte abyte0[] = uri.getBytes(StandardCharsets.UTF_8);
                uri = new byte[abyte0.length + 1];
                uri[0] = (byte)j;
                System.arraycopy(abyte0, 0, uri, 1, abyte0.length);
                return new NdefRecord((short)1, RTD_URI, null, uri);
            }
            i++;
        } while(true);
    }

    public static NdefRecord createUri(String s)
    {
        return createUri(Uri.parse(s));
    }

    private static void ensureSanePayloadSize(long l)
        throws FormatException
    {
        if(l > 0xa00000L)
            throw new FormatException((new StringBuilder()).append("payload above max limit: ").append(l).append(" > ").append(0xa00000).toString());
        else
            return;
    }

    static NdefRecord[] parse(ByteBuffer bytebuffer, boolean flag)
        throws FormatException
    {
        ArrayList arraylist;
        byte abyte0[];
        byte abyte1[];
        ArrayList arraylist1;
        int i;
        short word0;
        boolean flag1;
        short word1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        arraylist = new ArrayList();
        abyte0 = null;
        abyte1 = null;
        try
        {
            arraylist1 = JVM INSTR new #389 <Class ArrayList>;
            arraylist1.ArrayList();
        }
        // Misplaced declaration of an exception variable
        catch(ByteBuffer bytebuffer)
        {
            throw new FormatException("expected more data", bytebuffer);
        }
        i = 0;
        word0 = -1;
        flag1 = false;
_L15:
        if(flag1)
            break; /* Loop/switch isn't completed */
        word1 = bytebuffer.get();
        if((word1 & 0xffffff80) != 0)
            flag2 = true;
        else
            flag2 = false;
        if((word1 & 0x40) != 0)
            flag1 = true;
        else
            flag1 = false;
        if((word1 & 0x20) != 0)
            flag3 = true;
        else
            flag3 = false;
        if((word1 & 0x10) != 0)
            flag4 = true;
        else
            flag4 = false;
        if((word1 & 8) != 0)
            flag5 = true;
        else
            flag5 = false;
        word1 &= 7;
        if(flag2)
            break MISSING_BLOCK_LABEL_189;
        if(arraylist.size() != 0 || !(i ^ true) || !(flag ^ true))
            break MISSING_BLOCK_LABEL_189;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException("expected MB flag");
        throw bytebuffer;
        if(!flag2)
            break MISSING_BLOCK_LABEL_227;
        if(arraylist.size() == 0 && i == 0 || !(flag ^ true))
            break MISSING_BLOCK_LABEL_227;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException("unexpected MB flag");
        throw bytebuffer;
        if(i == 0 || !flag5)
            break MISSING_BLOCK_LABEL_250;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException("unexpected IL flag in non-leading chunk");
        throw bytebuffer;
        if(!flag3 || !flag1)
            break MISSING_BLOCK_LABEL_273;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException("unexpected ME flag in non-trailing chunk");
        throw bytebuffer;
        if(i == 0 || word1 == 6)
            break MISSING_BLOCK_LABEL_298;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException("expected TNF_UNCHANGED in non-leading chunk");
        throw bytebuffer;
        if(i != 0 || word1 != 6)
            break MISSING_BLOCK_LABEL_323;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException("unexpected TNF_UNCHANGED in first chunk or unchunked record");
        throw bytebuffer;
        int k = bytebuffer.get() & 0xff;
        if(!flag4) goto _L2; else goto _L1
_L1:
        long l = bytebuffer.get() & 0xff;
_L3:
        if(!flag5)
            break MISSING_BLOCK_LABEL_401;
        int j = bytebuffer.get() & 0xff;
_L4:
        if(i == 0 || k == 0)
            break MISSING_BLOCK_LABEL_407;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException("expected zero-length type in non-leading chunk");
        throw bytebuffer;
_L2:
        l = (long)bytebuffer.getInt() & 0xffffffffL;
          goto _L3
        j = 0;
          goto _L4
        if(i != 0) goto _L6; else goto _L5
_L5:
        if(k <= 0) goto _L8; else goto _L7
_L7:
        byte abyte2[] = new byte[k];
_L11:
        if(j <= 0) goto _L10; else goto _L9
_L9:
        abyte1 = new byte[j];
_L12:
        bytebuffer.get(abyte2);
        bytebuffer.get(abyte1);
        abyte0 = abyte2;
_L6:
        ensureSanePayloadSize(l);
        if(l <= 0L)
            break MISSING_BLOCK_LABEL_537;
        abyte2 = new byte[(int)l];
_L13:
        bytebuffer.get(abyte2);
        j = word0;
        if(!flag3)
            break MISSING_BLOCK_LABEL_554;
        j = word0;
        if(!(i ^ true))
            break MISSING_BLOCK_LABEL_554;
        if(k != 0 || word1 == 5)
            break MISSING_BLOCK_LABEL_545;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException("expected non-zero type length in first chunk");
        throw bytebuffer;
_L8:
        abyte2 = EMPTY_BYTE_ARRAY;
          goto _L11
_L10:
        abyte1 = EMPTY_BYTE_ARRAY;
          goto _L12
        abyte2 = EMPTY_BYTE_ARRAY;
          goto _L13
        arraylist1.clear();
        j = word1;
        if(!flag3 && i == 0)
            break MISSING_BLOCK_LABEL_572;
        arraylist1.add(abyte2);
        byte abyte3[];
        short word2;
        abyte3 = abyte2;
        word2 = word1;
        if(flag3)
            break MISSING_BLOCK_LABEL_718;
        abyte3 = abyte2;
        word2 = word1;
        if(i == 0)
            break MISSING_BLOCK_LABEL_718;
        l = 0L;
        for(Iterator iterator = arraylist1.iterator(); iterator.hasNext();)
            l += ((byte[])iterator.next()).length;

        ensureSanePayloadSize(l);
        abyte3 = new byte[(int)l];
        i = 0;
        for(Iterator iterator1 = arraylist1.iterator(); iterator1.hasNext();)
        {
            byte abyte4[] = (byte[])iterator1.next();
            System.arraycopy(abyte4, 0, abyte3, i, abyte4.length);
            i += abyte4.length;
        }

        i = j;
        word2 = i;
        if(!flag3)
            break; /* Loop/switch isn't completed */
        i = 1;
        word0 = j;
        if(true) goto _L15; else goto _L14
_L14:
        i = 0;
        String s = validateTnf(word2, abyte0, abyte1, abyte3);
        if(s == null)
            break MISSING_BLOCK_LABEL_765;
        bytebuffer = JVM INSTR new #195 <Class FormatException>;
        bytebuffer.FormatException(s);
        throw bytebuffer;
        NdefRecord ndefrecord = JVM INSTR new #2   <Class NdefRecord>;
        ndefrecord.NdefRecord(word2, abyte0, abyte1, abyte3);
        arraylist.add(ndefrecord);
        word0 = j;
        if(!flag) goto _L15; else goto _L16
_L16:
        return (NdefRecord[])arraylist.toArray(new NdefRecord[arraylist.size()]);
    }

    private Uri parseWktUri()
    {
        if(mPayload.length < 2)
            return null;
        int i = mPayload[0] & -1;
        if(i < 0 || i >= URI_PREFIX_MAP.length)
        {
            return null;
        } else
        {
            String s = URI_PREFIX_MAP[i];
            String s1 = new String(Arrays.copyOfRange(mPayload, 1, mPayload.length), StandardCharsets.UTF_8);
            return Uri.parse((new StringBuilder()).append(s).append(s1).toString());
        }
    }

    private Uri toUri(boolean flag)
    {
        Object obj = null;
        mTnf;
        JVM INSTR tableswitch 1 4: default 36
    //                   1 38
    //                   2 36
    //                   3 138
    //                   4 159;
           goto _L1 _L2 _L1 _L3 _L4
_L1:
        return null;
_L2:
        if(!Arrays.equals(mType, RTD_SMART_POSTER) || !(flag ^ true)) goto _L6; else goto _L5
_L5:
        NdefRecord andefrecord[];
        obj = JVM INSTR new #477 <Class NdefMessage>;
        ((NdefMessage) (obj)).NdefMessage(mPayload);
        andefrecord = ((NdefMessage) (obj)).getRecords();
        int i = 0;
        int j = andefrecord.length;
_L7:
        if(i >= j)
            continue; /* Loop/switch isn't completed */
        obj = andefrecord[i].toUri(true);
        if(obj != null)
            return ((Uri) (obj));
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        if(Arrays.equals(mType, RTD_URI))
        {
            Uri uri = parseWktUri();
            if(uri != null)
                obj = uri.normalizeScheme();
            return ((Uri) (obj));
        }
        continue; /* Loop/switch isn't completed */
_L3:
        return Uri.parse(new String(mType, StandardCharsets.UTF_8)).normalizeScheme();
_L4:
        if(!flag)
            return Uri.parse((new StringBuilder()).append("vnd.android.nfc://ext/").append(new String(mType, StandardCharsets.US_ASCII)).toString());
        continue; /* Loop/switch isn't completed */
        FormatException formatexception;
        formatexception;
        if(true) goto _L1; else goto _L8
_L8:
    }

    static String validateTnf(short word0, byte abyte0[], byte abyte1[], byte abyte2[])
    {
        switch(word0)
        {
        default:
            return String.format("unexpected tnf value: 0x%02x", new Object[] {
                Short.valueOf(word0)
            });

        case 0: // '\0'
            while(abyte0.length != 0 || abyte1.length != 0 || abyte2.length != 0) 
                return "unexpected data in TNF_EMPTY record";
            return null;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return null;

        case 5: // '\005'
        case 7: // '\007'
            if(abyte0.length != 0)
                return "unexpected type field in TNF_UNKNOWN or TNF_RESERVEd record";
            else
                return null;

        case 6: // '\006'
            return "unexpected TNF_UNCHANGED in first chunk or logical record";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (NdefRecord)obj;
        if(!Arrays.equals(mId, ((NdefRecord) (obj)).mId))
            return false;
        if(!Arrays.equals(mPayload, ((NdefRecord) (obj)).mPayload))
            return false;
        if(mTnf != ((NdefRecord) (obj)).mTnf)
            return false;
        else
            return Arrays.equals(mType, ((NdefRecord) (obj)).mType);
    }

    int getByteLength()
    {
        boolean flag;
        int i = mType.length + 3 + mId.length + mPayload.length;
        int j;
        int k;
        if(mPayload.length < 256)
            j = 1;
        else
            j = 0;
        break MISSING_BLOCK_LABEL_33;
        if(mTnf == 0 || mId.length > 0)
            flag = true;
        else
            flag = false;
        k = i;
        if(j == 0)
            k = i + 3;
        j = k;
        if(flag)
            j = k + 1;
        return j;
    }

    public byte[] getId()
    {
        return (byte[])mId.clone();
    }

    public byte[] getPayload()
    {
        return (byte[])mPayload.clone();
    }

    public short getTnf()
    {
        return mTnf;
    }

    public byte[] getType()
    {
        return (byte[])mType.clone();
    }

    public int hashCode()
    {
        return (((Arrays.hashCode(mId) + 31) * 31 + Arrays.hashCode(mPayload)) * 31 + mTnf) * 31 + Arrays.hashCode(mType);
    }

    public byte[] toByteArray()
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(getByteLength());
        writeToByteBuffer(bytebuffer, true, true);
        return bytebuffer.array();
    }

    public String toMimeType()
    {
        mTnf;
        JVM INSTR tableswitch 1 2: default 28
    //                   1 30
    //                   2 47;
           goto _L1 _L2 _L3
_L1:
        return null;
_L2:
        if(Arrays.equals(mType, RTD_TEXT))
            return "text/plain";
          goto _L1
_L3:
        return Intent.normalizeMimeType(new String(mType, StandardCharsets.US_ASCII));
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(String.format("NdefRecord tnf=%X", new Object[] {
            Short.valueOf(mTnf)
        }));
        if(mType.length > 0)
            stringbuilder.append(" type=").append(bytesToString(mType));
        if(mId.length > 0)
            stringbuilder.append(" id=").append(bytesToString(mId));
        if(mPayload.length > 0)
            stringbuilder.append(" payload=").append(bytesToString(mPayload));
        return stringbuilder.toString();
    }

    public Uri toUri()
    {
        return toUri(false);
    }

    void writeToByteBuffer(ByteBuffer bytebuffer, boolean flag, boolean flag1)
    {
        boolean flag3;
        byte byte1;
        byte byte2;
        byte byte3;
        byte byte0 = 0;
        boolean flag2;
        if(mPayload.length < 256)
            flag2 = true;
        else
            flag2 = false;
        break MISSING_BLOCK_LABEL_17;
        if(mTnf == 0 || mId.length > 0)
            flag3 = true;
        else
            flag3 = false;
        if(flag)
            byte1 = -128;
        else
            byte1 = 0;
        if(flag1)
            byte2 = 64;
        else
            byte2 = 0;
        if(flag2)
            byte3 = 16;
        else
            byte3 = 0;
        if(flag3)
            byte0 = 8;
        bytebuffer.put((byte)(byte0 | (byte3 | (byte1 | byte2)) | mTnf));
        bytebuffer.put((byte)mType.length);
        if(flag2)
            bytebuffer.put((byte)mPayload.length);
        else
            bytebuffer.putInt(mPayload.length);
        if(flag3)
            bytebuffer.put((byte)mId.length);
        bytebuffer.put(mType);
        bytebuffer.put(mId);
        bytebuffer.put(mPayload);
        return;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mTnf);
        parcel.writeInt(mType.length);
        parcel.writeByteArray(mType);
        parcel.writeInt(mId.length);
        parcel.writeByteArray(mId);
        parcel.writeInt(mPayload.length);
        parcel.writeByteArray(mPayload);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NdefRecord createFromParcel(Parcel parcel)
        {
            short word0 = (short)parcel.readInt();
            byte abyte0[] = new byte[parcel.readInt()];
            parcel.readByteArray(abyte0);
            byte abyte1[] = new byte[parcel.readInt()];
            parcel.readByteArray(abyte1);
            byte abyte2[] = new byte[parcel.readInt()];
            parcel.readByteArray(abyte2);
            return new NdefRecord(word0, abyte0, abyte1, abyte2);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NdefRecord[] newArray(int i)
        {
            return new NdefRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final byte EMPTY_BYTE_ARRAY[] = new byte[0];
    private static final byte FLAG_CF = 32;
    private static final byte FLAG_IL = 8;
    private static final byte FLAG_MB = -128;
    private static final byte FLAG_ME = 64;
    private static final byte FLAG_SR = 16;
    private static final int MAX_PAYLOAD_SIZE = 0xa00000;
    public static final byte RTD_ALTERNATIVE_CARRIER[] = {
        97, 99
    };
    public static final byte RTD_ANDROID_APP[] = "android.com:pkg".getBytes();
    public static final byte RTD_HANDOVER_CARRIER[] = {
        72, 99
    };
    public static final byte RTD_HANDOVER_REQUEST[] = {
        72, 114
    };
    public static final byte RTD_HANDOVER_SELECT[] = {
        72, 115
    };
    public static final byte RTD_SMART_POSTER[] = {
        83, 112
    };
    public static final byte RTD_TEXT[] = {
        84
    };
    public static final byte RTD_URI[] = {
        85
    };
    public static final short TNF_ABSOLUTE_URI = 3;
    public static final short TNF_EMPTY = 0;
    public static final short TNF_EXTERNAL_TYPE = 4;
    public static final short TNF_MIME_MEDIA = 2;
    public static final short TNF_RESERVED = 7;
    public static final short TNF_UNCHANGED = 6;
    public static final short TNF_UNKNOWN = 5;
    public static final short TNF_WELL_KNOWN = 1;
    private static final String URI_PREFIX_MAP[] = {
        "", "http://www.", "https://www.", "http://", "https://", "tel:", "mailto:", "ftp://anonymous:anonymous@", "ftp://ftp.", "ftps://", 
        "sftp://", "smb://", "nfs://", "ftp://", "dav://", "news:", "telnet://", "imap:", "rtsp://", "urn:", 
        "pop:", "sip:", "sips:", "tftp:", "btspp://", "btl2cap://", "btgoep://", "tcpobex://", "irdaobex://", "file://", 
        "urn:epc:id:", "urn:epc:tag:", "urn:epc:pat:", "urn:epc:raw:", "urn:epc:", "urn:nfc:"
    };
    private final byte mId[];
    private final byte mPayload[];
    private final short mTnf;
    private final byte mType[];

}
