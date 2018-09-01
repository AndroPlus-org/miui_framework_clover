// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.app:
//            PendingIntent

public final class AuthenticationRequiredException extends SecurityException
    implements Parcelable
{

    public AuthenticationRequiredException(Parcel parcel)
    {
        this(((Throwable) (new SecurityException(parcel.readString()))), (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel));
    }

    public AuthenticationRequiredException(Throwable throwable, PendingIntent pendingintent)
    {
        super(throwable.getMessage());
        mUserAction = (PendingIntent)Preconditions.checkNotNull(pendingintent);
    }

    public int describeContents()
    {
        return 0;
    }

    public PendingIntent getUserAction()
    {
        return mUserAction;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getMessage());
        mUserAction.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AuthenticationRequiredException createFromParcel(Parcel parcel)
        {
            return new AuthenticationRequiredException(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AuthenticationRequiredException[] newArray(int i)
        {
            return new AuthenticationRequiredException[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "AuthenticationRequiredException";
    private final PendingIntent mUserAction;

}
