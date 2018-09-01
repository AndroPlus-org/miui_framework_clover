// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.net.Uri;
import android.os.*;

// Referenced classes of package android.nfc:
//            NdefMessage

public final class BeamShareData
    implements Parcelable
{

    public BeamShareData(NdefMessage ndefmessage, Uri auri[], UserHandle userhandle, int i)
    {
        ndefMessage = ndefmessage;
        uris = auri;
        userHandle = userhandle;
        flags = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(uris != null)
            i = uris.length;
        else
            i = 0;
        parcel.writeParcelable(ndefMessage, 0);
        parcel.writeInt(i);
        if(i > 0)
            parcel.writeTypedArray(uris, 0);
        parcel.writeParcelable(userHandle, 0);
        parcel.writeInt(flags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BeamShareData createFromParcel(Parcel parcel)
        {
            Uri auri[] = null;
            NdefMessage ndefmessage = (NdefMessage)parcel.readParcelable(android/nfc/NdefMessage.getClassLoader());
            int i = parcel.readInt();
            if(i > 0)
            {
                auri = new Uri[i];
                parcel.readTypedArray(auri, Uri.CREATOR);
            }
            return new BeamShareData(ndefmessage, auri, (UserHandle)parcel.readParcelable(android/os/UserHandle.getClassLoader()), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BeamShareData[] newArray(int i)
        {
            return new BeamShareData[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int flags;
    public final NdefMessage ndefMessage;
    public final Uri uris[];
    public final UserHandle userHandle;

}
