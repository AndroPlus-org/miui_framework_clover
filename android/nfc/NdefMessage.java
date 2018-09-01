// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.util.Arrays;

// Referenced classes of package android.nfc:
//            NdefRecord, FormatException

public final class NdefMessage
    implements Parcelable
{

    public transient NdefMessage(NdefRecord ndefrecord, NdefRecord andefrecord[])
    {
        if(ndefrecord == null)
            throw new NullPointerException("record cannot be null");
        int i = andefrecord.length;
        for(int j = 0; j < i; j++)
            if(andefrecord[j] == null)
                throw new NullPointerException("record cannot be null");

        mRecords = new NdefRecord[andefrecord.length + 1];
        mRecords[0] = ndefrecord;
        System.arraycopy(andefrecord, 0, mRecords, 1, andefrecord.length);
    }

    public NdefMessage(byte abyte0[])
        throws FormatException
    {
        if(abyte0 == null)
            throw new NullPointerException("data is null");
        abyte0 = ByteBuffer.wrap(abyte0);
        mRecords = NdefRecord.parse(abyte0, false);
        if(abyte0.remaining() > 0)
            throw new FormatException("trailing data");
        else
            return;
    }

    public NdefMessage(NdefRecord andefrecord[])
    {
        if(andefrecord.length < 1)
            throw new IllegalArgumentException("must have at least one record");
        int i = 0;
        for(int j = andefrecord.length; i < j; i++)
            if(andefrecord[i] == null)
                throw new NullPointerException("records cannot contain null");

        mRecords = andefrecord;
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
        {
            return false;
        } else
        {
            obj = (NdefMessage)obj;
            return Arrays.equals(mRecords, ((NdefMessage) (obj)).mRecords);
        }
    }

    public int getByteArrayLength()
    {
        int i = 0;
        NdefRecord andefrecord[] = mRecords;
        int j = 0;
        for(int k = andefrecord.length; j < k; j++)
            i += andefrecord[j].getByteLength();

        return i;
    }

    public NdefRecord[] getRecords()
    {
        return mRecords;
    }

    public int hashCode()
    {
        return Arrays.hashCode(mRecords);
    }

    public byte[] toByteArray()
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(getByteArrayLength());
        int i = 0;
        while(i < mRecords.length) 
        {
            boolean flag;
            boolean flag1;
            if(i == 0)
                flag = true;
            else
                flag = false;
            if(i == mRecords.length - 1)
                flag1 = true;
            else
                flag1 = false;
            mRecords[i].writeToByteBuffer(bytebuffer, flag, flag1);
            i++;
        }
        return bytebuffer.array();
    }

    public String toString()
    {
        return (new StringBuilder()).append("NdefMessage ").append(Arrays.toString(mRecords)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRecords.length);
        parcel.writeTypedArray(mRecords, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NdefMessage createFromParcel(Parcel parcel)
        {
            NdefRecord andefrecord[] = new NdefRecord[parcel.readInt()];
            parcel.readTypedArray(andefrecord, NdefRecord.CREATOR);
            return new NdefMessage(andefrecord);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NdefMessage[] newArray(int i)
        {
            return new NdefMessage[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final NdefRecord mRecords[];

}
