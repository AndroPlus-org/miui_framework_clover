// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.hardware.camera2.utils.SurfaceUtils;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.hardware.camera2.params:
//            StreamConfigurationMap

public final class OutputConfiguration
    implements Parcelable
{

    public OutputConfiguration(int i, Surface surface)
    {
        this(i, surface, 0);
    }

    public OutputConfiguration(int i, Surface surface, int j)
    {
        SURFACE_TYPE_UNKNOWN = -1;
        SURFACE_TYPE_SURFACE_VIEW = 0;
        SURFACE_TYPE_SURFACE_TEXTURE = 1;
        Preconditions.checkNotNull(surface, "Surface must not be null");
        Preconditions.checkArgumentInRange(j, 0, 3, "Rotation constant");
        mSurfaceGroupId = i;
        mSurfaceType = -1;
        mSurfaces = new ArrayList();
        mSurfaces.add(surface);
        mRotation = j;
        mConfiguredSize = SurfaceUtils.getSurfaceSize(surface);
        mConfiguredFormat = SurfaceUtils.getSurfaceFormat(surface);
        mConfiguredDataspace = SurfaceUtils.getSurfaceDataspace(surface);
        mConfiguredGenerationId = surface.getGenerationId();
        mIsDeferredConfig = false;
        mIsShared = false;
    }

    public OutputConfiguration(OutputConfiguration outputconfiguration)
    {
        SURFACE_TYPE_UNKNOWN = -1;
        SURFACE_TYPE_SURFACE_VIEW = 0;
        SURFACE_TYPE_SURFACE_TEXTURE = 1;
        if(outputconfiguration == null)
        {
            throw new IllegalArgumentException("OutputConfiguration shouldn't be null");
        } else
        {
            mSurfaces = outputconfiguration.mSurfaces;
            mRotation = outputconfiguration.mRotation;
            mSurfaceGroupId = outputconfiguration.mSurfaceGroupId;
            mSurfaceType = outputconfiguration.mSurfaceType;
            mConfiguredDataspace = outputconfiguration.mConfiguredDataspace;
            mConfiguredFormat = outputconfiguration.mConfiguredFormat;
            mConfiguredSize = outputconfiguration.mConfiguredSize;
            mConfiguredGenerationId = outputconfiguration.mConfiguredGenerationId;
            mIsDeferredConfig = outputconfiguration.mIsDeferredConfig;
            mIsShared = outputconfiguration.mIsShared;
            return;
        }
    }

    private OutputConfiguration(Parcel parcel)
    {
        SURFACE_TYPE_UNKNOWN = -1;
        SURFACE_TYPE_SURFACE_VIEW = 0;
        SURFACE_TYPE_SURFACE_TEXTURE = 1;
        int i = parcel.readInt();
        int j = parcel.readInt();
        int k = parcel.readInt();
        int l = parcel.readInt();
        int i1 = parcel.readInt();
        boolean flag;
        boolean flag1;
        ArrayList arraylist;
        if(parcel.readInt() == 1)
            flag = true;
        else
            flag = false;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        arraylist = new ArrayList();
        parcel.readTypedList(arraylist, Surface.CREATOR);
        Preconditions.checkArgumentInRange(i, 0, 3, "Rotation constant");
        mSurfaceGroupId = j;
        mRotation = i;
        mSurfaces = arraylist;
        mConfiguredSize = new Size(l, i1);
        mIsDeferredConfig = flag;
        mIsShared = flag1;
        mSurfaces = arraylist;
        if(mSurfaces.size() > 0)
        {
            mSurfaceType = -1;
            mConfiguredFormat = SurfaceUtils.getSurfaceFormat((Surface)mSurfaces.get(0));
            mConfiguredDataspace = SurfaceUtils.getSurfaceDataspace((Surface)mSurfaces.get(0));
            mConfiguredGenerationId = ((Surface)mSurfaces.get(0)).getGenerationId();
        } else
        {
            mSurfaceType = k;
            mConfiguredFormat = StreamConfigurationMap.imageFormatToInternal(34);
            mConfiguredDataspace = StreamConfigurationMap.imageFormatToDataspace(34);
            mConfiguredGenerationId = 0;
        }
    }

    OutputConfiguration(Parcel parcel, OutputConfiguration outputconfiguration)
    {
        this(parcel);
    }

    public OutputConfiguration(Size size, Class class1)
    {
        SURFACE_TYPE_UNKNOWN = -1;
        SURFACE_TYPE_SURFACE_VIEW = 0;
        SURFACE_TYPE_SURFACE_TEXTURE = 1;
        Preconditions.checkNotNull(class1, "surfaceSize must not be null");
        Preconditions.checkNotNull(class1, "klass must not be null");
        if(class1 == android/view/SurfaceHolder)
            mSurfaceType = 0;
        else
        if(class1 == android/graphics/SurfaceTexture)
        {
            mSurfaceType = 1;
        } else
        {
            mSurfaceType = -1;
            throw new IllegalArgumentException("Unknow surface source class type");
        }
        if(size.getWidth() == 0 || size.getHeight() == 0)
        {
            throw new IllegalArgumentException("Surface size needs to be non-zero");
        } else
        {
            mSurfaceGroupId = -1;
            mSurfaces = new ArrayList();
            mRotation = 0;
            mConfiguredSize = size;
            mConfiguredFormat = StreamConfigurationMap.imageFormatToInternal(34);
            mConfiguredDataspace = StreamConfigurationMap.imageFormatToDataspace(34);
            mConfiguredGenerationId = 0;
            mIsDeferredConfig = true;
            mIsShared = false;
            return;
        }
    }

    public OutputConfiguration(Surface surface)
    {
        this(-1, surface, 0);
    }

    public OutputConfiguration(Surface surface, int i)
    {
        this(-1, surface, i);
    }

    public void addSurface(Surface surface)
    {
        Preconditions.checkNotNull(surface, "Surface must not be null");
        if(mSurfaces.contains(surface))
            throw new IllegalStateException("Surface is already added!");
        if(mSurfaces.size() == 1 && mIsShared ^ true)
            throw new IllegalStateException("Cannot have 2 surfaces for a non-sharing configuration");
        if(mSurfaces.size() + 1 > 2)
            throw new IllegalArgumentException("Exceeds maximum number of surfaces");
        Size size = SurfaceUtils.getSurfaceSize(surface);
        if(!size.equals(mConfiguredSize))
            Log.w("OutputConfiguration", (new StringBuilder()).append("Added surface size ").append(size).append(" is different than pre-configured size ").append(mConfiguredSize).append(", the pre-configured size will be used.").toString());
        if(mConfiguredFormat != SurfaceUtils.getSurfaceFormat(surface))
            throw new IllegalArgumentException("The format of added surface format doesn't match");
        if(mConfiguredFormat != 34 && mConfiguredDataspace != SurfaceUtils.getSurfaceDataspace(surface))
        {
            throw new IllegalArgumentException("The dataspace of added surface doesn't match");
        } else
        {
            mSurfaces.add(surface);
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void enableSurfaceSharing()
    {
        mIsShared = true;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof OutputConfiguration)
        {
            for(obj = (OutputConfiguration)obj; mRotation != ((OutputConfiguration) (obj)).mRotation || mConfiguredSize.equals(((OutputConfiguration) (obj)).mConfiguredSize) ^ true || mConfiguredFormat != ((OutputConfiguration) (obj)).mConfiguredFormat || mSurfaceGroupId != ((OutputConfiguration) (obj)).mSurfaceGroupId || mSurfaceType != ((OutputConfiguration) (obj)).mSurfaceType || mIsDeferredConfig != ((OutputConfiguration) (obj)).mIsDeferredConfig || mIsShared != ((OutputConfiguration) (obj)).mIsShared || mConfiguredFormat != ((OutputConfiguration) (obj)).mConfiguredFormat || mConfiguredDataspace != ((OutputConfiguration) (obj)).mConfiguredDataspace || mConfiguredGenerationId != ((OutputConfiguration) (obj)).mConfiguredGenerationId;)
                return false;

            int i = Math.min(mSurfaces.size(), ((OutputConfiguration) (obj)).mSurfaces.size());
            for(int j = 0; j < i; j++)
                if(mSurfaces.get(j) != ((OutputConfiguration) (obj)).mSurfaces.get(j))
                    return false;

            return true;
        } else
        {
            return false;
        }
    }

    public int getRotation()
    {
        return mRotation;
    }

    public Surface getSurface()
    {
        if(mSurfaces.size() == 0)
            return null;
        else
            return (Surface)mSurfaces.get(0);
    }

    public int getSurfaceGroupId()
    {
        return mSurfaceGroupId;
    }

    public List getSurfaces()
    {
        return Collections.unmodifiableList(mSurfaces);
    }

    public int hashCode()
    {
        int i = 1;
        int j = 1;
        if(mIsDeferredConfig)
        {
            int k = mRotation;
            int i1 = mConfiguredSize.hashCode();
            int k1 = mConfiguredFormat;
            int i2 = mConfiguredDataspace;
            int k2 = mSurfaceGroupId;
            int i3 = mSurfaceType;
            if(mIsShared)
                i = j;
            else
                i = 0;
            return HashCodeHelpers.hashCode(new int[] {
                k, i1, k1, i2, k2, i3, i
            });
        }
        int l1 = mRotation;
        int j3 = mSurfaces.hashCode();
        int j2 = mConfiguredGenerationId;
        int l = mConfiguredSize.hashCode();
        j = mConfiguredFormat;
        int l2 = mConfiguredDataspace;
        int j1 = mSurfaceGroupId;
        if(!mIsShared)
            i = 0;
        return HashCodeHelpers.hashCode(new int[] {
            l1, j3, j2, l, j, l2, j1, i
        });
    }

    public boolean isDeferredConfiguration()
    {
        return mIsDeferredConfig;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(parcel == null)
            throw new IllegalArgumentException("dest must not be null");
        parcel.writeInt(mRotation);
        parcel.writeInt(mSurfaceGroupId);
        parcel.writeInt(mSurfaceType);
        parcel.writeInt(mConfiguredSize.getWidth());
        parcel.writeInt(mConfiguredSize.getHeight());
        if(mIsDeferredConfig)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsShared)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeTypedList(mSurfaces);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OutputConfiguration createFromParcel(Parcel parcel)
        {
            try
            {
                parcel = new OutputConfiguration(parcel, null);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                Log.e("OutputConfiguration", "Exception creating OutputConfiguration from parcel", parcel);
                return null;
            }
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OutputConfiguration[] newArray(int i)
        {
            return new OutputConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_SURFACES_COUNT = 2;
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    public static final int SURFACE_GROUP_ID_NONE = -1;
    private static final String TAG = "OutputConfiguration";
    private final int SURFACE_TYPE_SURFACE_TEXTURE;
    private final int SURFACE_TYPE_SURFACE_VIEW;
    private final int SURFACE_TYPE_UNKNOWN;
    private final int mConfiguredDataspace;
    private final int mConfiguredFormat;
    private final int mConfiguredGenerationId;
    private final Size mConfiguredSize;
    private final boolean mIsDeferredConfig;
    private boolean mIsShared;
    private final int mRotation;
    private final int mSurfaceGroupId;
    private final int mSurfaceType;
    private ArrayList mSurfaces;

}
