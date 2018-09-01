// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

// Referenced classes of package android.telephony.mbms:
//            FileServiceInfo

public final class DownloadRequest
    implements Parcelable
{
    public static class Builder
    {

        public DownloadRequest build()
        {
            return new DownloadRequest(fileServiceId, source, subscriptionId, appIntent, version, null);
        }

        public Builder setAppIntent(Intent intent)
        {
            appIntent = intent.toUri(0);
            if(appIntent.length() > 50000)
                throw new IllegalArgumentException("App intent must not exceed length 50000");
            else
                return this;
        }

        public Builder setOpaqueData(byte abyte0[])
        {
            try
            {
                ObjectInputStream objectinputstream = JVM INSTR new #68  <Class ObjectInputStream>;
                ByteArrayInputStream bytearrayinputstream = JVM INSTR new #70  <Class ByteArrayInputStream>;
                bytearrayinputstream.ByteArrayInputStream(abyte0);
                objectinputstream.ObjectInputStream(bytearrayinputstream);
                abyte0 = (OpaqueDataContainer)objectinputstream.readObject();
                version = OpaqueDataContainer._2D_get1(abyte0);
                appIntent = OpaqueDataContainer._2D_get0(abyte0);
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                Log.e("MbmsDownloadRequest", "Got IOException trying to parse opaque data");
                throw new IllegalArgumentException(abyte0);
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                Log.e("MbmsDownloadRequest", "Got ClassNotFoundException trying to parse opaque data");
                throw new IllegalArgumentException(abyte0);
            }
            return this;
        }

        public Builder setServiceId(String s)
        {
            fileServiceId = s;
            return this;
        }

        public Builder setServiceInfo(FileServiceInfo fileserviceinfo)
        {
            fileServiceId = fileserviceinfo.getServiceId();
            return this;
        }

        public Builder setSubscriptionId(int i)
        {
            subscriptionId = i;
            return this;
        }

        private String appIntent;
        private String fileServiceId;
        private Uri source;
        private int subscriptionId;
        private int version;

        public Builder(Uri uri)
        {
            version = 1;
            if(uri == null)
            {
                throw new IllegalArgumentException("Source URI must be non-null.");
            } else
            {
                source = uri;
                return;
            }
        }
    }

    private static class OpaqueDataContainer
        implements Serializable
    {

        static String _2D_get0(OpaqueDataContainer opaquedatacontainer)
        {
            return opaquedatacontainer.appIntent;
        }

        static int _2D_get1(OpaqueDataContainer opaquedatacontainer)
        {
            return opaquedatacontainer.version;
        }

        private final String appIntent;
        private final int version;

        public OpaqueDataContainer(String s, int i)
        {
            appIntent = s;
            version = i;
        }
    }


    private DownloadRequest(Parcel parcel)
    {
        fileServiceId = parcel.readString();
        sourceUri = (Uri)parcel.readParcelable(getClass().getClassLoader());
        subscriptionId = parcel.readInt();
        serializedResultIntentForApp = parcel.readString();
        version = parcel.readInt();
    }

    DownloadRequest(Parcel parcel, DownloadRequest downloadrequest)
    {
        this(parcel);
    }

    private DownloadRequest(DownloadRequest downloadrequest)
    {
        fileServiceId = downloadrequest.fileServiceId;
        sourceUri = downloadrequest.sourceUri;
        subscriptionId = downloadrequest.subscriptionId;
        serializedResultIntentForApp = downloadrequest.serializedResultIntentForApp;
        version = downloadrequest.version;
    }

    private DownloadRequest(String s, Uri uri, int i, String s1, int j)
    {
        fileServiceId = s;
        sourceUri = uri;
        subscriptionId = i;
        serializedResultIntentForApp = s1;
        version = j;
    }

    DownloadRequest(String s, Uri uri, int i, String s1, int j, DownloadRequest downloadrequest)
    {
        this(s, uri, i, s1, j);
    }

    public static DownloadRequest copy(DownloadRequest downloadrequest)
    {
        return new DownloadRequest(downloadrequest);
    }

    public static int getMaxAppIntentSize()
    {
        return 50000;
    }

    public static int getMaxDestinationUriSize()
    {
        return 50000;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof DownloadRequest))
            return false;
        obj = (DownloadRequest)obj;
        boolean flag1 = flag;
        if(subscriptionId == ((DownloadRequest) (obj)).subscriptionId)
        {
            flag1 = flag;
            if(version == ((DownloadRequest) (obj)).version)
            {
                flag1 = flag;
                if(Objects.equals(fileServiceId, ((DownloadRequest) (obj)).fileServiceId))
                {
                    flag1 = flag;
                    if(Objects.equals(sourceUri, ((DownloadRequest) (obj)).sourceUri))
                        flag1 = Objects.equals(serializedResultIntentForApp, ((DownloadRequest) (obj)).serializedResultIntentForApp);
                }
            }
        }
        return flag1;
    }

    public String getFileServiceId()
    {
        return fileServiceId;
    }

    public String getHash()
    {
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance("SHA-256");
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new RuntimeException("Could not get sha256 hash object");
        }
        if(version >= 1)
        {
            messagedigest.update(sourceUri.toString().getBytes(StandardCharsets.UTF_8));
            if(serializedResultIntentForApp != null)
                messagedigest.update(serializedResultIntentForApp.getBytes(StandardCharsets.UTF_8));
        }
        return Base64.encodeToString(messagedigest.digest(), 10);
    }

    public Intent getIntentForApp()
    {
        Intent intent;
        try
        {
            intent = Intent.parseUri(serializedResultIntentForApp, 0);
        }
        catch(URISyntaxException urisyntaxexception)
        {
            return null;
        }
        return intent;
    }

    public byte[] getOpaqueData()
    {
        byte abyte0[];
        try
        {
            ByteArrayOutputStream bytearrayoutputstream = JVM INSTR new #164 <Class ByteArrayOutputStream>;
            bytearrayoutputstream.ByteArrayOutputStream();
            ObjectOutputStream objectoutputstream = JVM INSTR new #167 <Class ObjectOutputStream>;
            objectoutputstream.ObjectOutputStream(bytearrayoutputstream);
            OpaqueDataContainer opaquedatacontainer = JVM INSTR new #13  <Class DownloadRequest$OpaqueDataContainer>;
            opaquedatacontainer.OpaqueDataContainer(serializedResultIntentForApp, version);
            objectoutputstream.writeObject(opaquedatacontainer);
            objectoutputstream.flush();
            abyte0 = bytearrayoutputstream.toByteArray();
        }
        catch(IOException ioexception)
        {
            Log.e("MbmsDownloadRequest", "Got IOException trying to serialize opaque data");
            return null;
        }
        return abyte0;
    }

    public Uri getSourceUri()
    {
        return sourceUri;
    }

    public int getSubscriptionId()
    {
        return subscriptionId;
    }

    public int getVersion()
    {
        return version;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            fileServiceId, sourceUri, Integer.valueOf(subscriptionId), serializedResultIntentForApp, Integer.valueOf(version)
        });
    }

    public boolean isMultipartDownload()
    {
        boolean flag;
        if(getSourceUri().getLastPathSegment() != null)
            flag = getSourceUri().getLastPathSegment().contains("*");
        else
            flag = false;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(fileServiceId);
        parcel.writeParcelable(sourceUri, i);
        parcel.writeInt(subscriptionId);
        parcel.writeString(serializedResultIntentForApp);
        parcel.writeInt(version);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DownloadRequest createFromParcel(Parcel parcel)
        {
            return new DownloadRequest(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DownloadRequest[] newArray(int i)
        {
            return new DownloadRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int CURRENT_VERSION = 1;
    private static final String LOG_TAG = "MbmsDownloadRequest";
    public static final int MAX_APP_INTENT_SIZE = 50000;
    public static final int MAX_DESTINATION_URI_SIZE = 50000;
    private final String fileServiceId;
    private final String serializedResultIntentForApp;
    private final Uri sourceUri;
    private final int subscriptionId;
    private final int version;

}
