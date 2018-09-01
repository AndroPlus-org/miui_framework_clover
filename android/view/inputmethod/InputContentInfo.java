// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.content.ClipDescription;
import android.content.ContentProvider;
import android.net.Uri;
import android.os.*;
import com.android.internal.inputmethod.IInputContentUriToken;
import java.security.InvalidParameterException;

public final class InputContentInfo
    implements Parcelable
{

    public InputContentInfo(Uri uri, ClipDescription clipdescription)
    {
        this(uri, clipdescription, null);
    }

    public InputContentInfo(Uri uri, ClipDescription clipdescription, Uri uri1)
    {
        validateInternal(uri, clipdescription, uri1, true);
        mContentUri = uri;
        mContentUriOwnerUserId = ContentProvider.getUserIdFromUri(mContentUri, UserHandle.myUserId());
        mDescription = clipdescription;
        mLinkUri = uri1;
    }

    private InputContentInfo(Parcel parcel)
    {
        mContentUri = (Uri)Uri.CREATOR.createFromParcel(parcel);
        mContentUriOwnerUserId = parcel.readInt();
        mDescription = (ClipDescription)ClipDescription.CREATOR.createFromParcel(parcel);
        mLinkUri = (Uri)Uri.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() == 1)
            mUriToken = com.android.internal.inputmethod.IInputContentUriToken.Stub.asInterface(parcel.readStrongBinder());
        else
            mUriToken = null;
    }

    InputContentInfo(Parcel parcel, InputContentInfo inputcontentinfo)
    {
        this(parcel);
    }

    private static boolean validateInternal(Uri uri, ClipDescription clipdescription, Uri uri1, boolean flag)
    {
        if(uri == null)
            if(flag)
                throw new NullPointerException("contentUri");
            else
                return false;
        if(clipdescription == null)
            if(flag)
                throw new NullPointerException("description");
            else
                return false;
        if(!"content".equals(uri.getScheme()))
            if(flag)
                throw new InvalidParameterException("contentUri must have content scheme");
            else
                return false;
        if(uri1 != null)
        {
            uri = uri1.getScheme();
            if(uri == null || !uri.equalsIgnoreCase("http") && uri.equalsIgnoreCase("https") ^ true)
                if(flag)
                    throw new InvalidParameterException("linkUri must have either http or https scheme");
                else
                    return false;
        }
        return true;
    }

    public int describeContents()
    {
        return 0;
    }

    public Uri getContentUri()
    {
        if(mContentUriOwnerUserId != UserHandle.myUserId())
            return ContentProvider.maybeAddUserId(mContentUri, mContentUriOwnerUserId);
        else
            return mContentUri;
    }

    public ClipDescription getDescription()
    {
        return mDescription;
    }

    public Uri getLinkUri()
    {
        return mLinkUri;
    }

    public void releasePermission()
    {
        if(mUriToken == null)
            return;
        mUriToken.release();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.rethrowFromSystemServer();
          goto _L1
    }

    public void requestPermission()
    {
        if(mUriToken == null)
            return;
        mUriToken.take();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.rethrowFromSystemServer();
          goto _L1
    }

    void setUriToken(IInputContentUriToken iinputcontenturitoken)
    {
        if(mUriToken != null)
        {
            throw new IllegalStateException("URI token is already set");
        } else
        {
            mUriToken = iinputcontenturitoken;
            return;
        }
    }

    public boolean validate()
    {
        return validateInternal(mContentUri, mDescription, mLinkUri, false);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Uri.writeToParcel(parcel, mContentUri);
        parcel.writeInt(mContentUriOwnerUserId);
        mDescription.writeToParcel(parcel, i);
        Uri.writeToParcel(parcel, mLinkUri);
        if(mUriToken != null)
        {
            parcel.writeInt(1);
            parcel.writeStrongBinder(mUriToken.asBinder());
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InputContentInfo createFromParcel(Parcel parcel)
        {
            return new InputContentInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InputContentInfo[] newArray(int i)
        {
            return new InputContentInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Uri mContentUri;
    private final int mContentUriOwnerUserId;
    private final ClipDescription mDescription;
    private final Uri mLinkUri;
    private IInputContentUriToken mUriToken;

}
