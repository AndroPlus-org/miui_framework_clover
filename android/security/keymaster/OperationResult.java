// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.*;

// Referenced classes of package android.security.keymaster:
//            KeymasterArguments

public class OperationResult
    implements Parcelable
{

    public OperationResult(int i, IBinder ibinder, long l, int j, byte abyte0[], KeymasterArguments keymasterarguments)
    {
        resultCode = i;
        token = ibinder;
        operationHandle = l;
        inputConsumed = j;
        output = abyte0;
        outParams = keymasterarguments;
    }

    protected OperationResult(Parcel parcel)
    {
        resultCode = parcel.readInt();
        token = parcel.readStrongBinder();
        operationHandle = parcel.readLong();
        inputConsumed = parcel.readInt();
        output = parcel.createByteArray();
        outParams = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(resultCode);
        parcel.writeStrongBinder(token);
        parcel.writeLong(operationHandle);
        parcel.writeInt(inputConsumed);
        parcel.writeByteArray(output);
        outParams.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OperationResult createFromParcel(Parcel parcel)
        {
            return new OperationResult(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OperationResult[] newArray(int i)
        {
            return new OperationResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int inputConsumed;
    public final long operationHandle;
    public final KeymasterArguments outParams;
    public final byte output[];
    public final int resultCode;
    public final IBinder token;

}
