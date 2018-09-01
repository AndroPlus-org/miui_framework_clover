// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Rational;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.app:
//            RemoteAction, PictureInPictureParams

public final class PictureInPictureArgs
    implements Parcelable
{
    public static class Builder
    {

        public PictureInPictureArgs build()
        {
            return new PictureInPictureArgs(mAspectRatio, mUserActions, mSourceRectHint, null);
        }

        public Builder setActions(List list)
        {
            if(mUserActions != null)
                mUserActions = null;
            if(list != null)
                mUserActions = new ArrayList(list);
            return this;
        }

        public Builder setAspectRatio(Rational rational)
        {
            mAspectRatio = rational;
            return this;
        }

        public Builder setSourceRectHint(Rect rect)
        {
            if(rect == null)
                mSourceRectHint = null;
            else
                mSourceRectHint = new Rect(rect);
            return this;
        }

        private Rational mAspectRatio;
        private Rect mSourceRectHint;
        private List mUserActions;

        public Builder()
        {
        }
    }


    public PictureInPictureArgs()
    {
    }

    public PictureInPictureArgs(float f, List list)
    {
        setAspectRatio(f);
        setActions(list);
    }

    private PictureInPictureArgs(Parcel parcel)
    {
        if(parcel.readInt() != 0)
            mAspectRatio = new Rational(parcel.readInt(), parcel.readInt());
        if(parcel.readInt() != 0)
        {
            mUserActions = new ArrayList();
            parcel.readParcelableList(mUserActions, android/app/RemoteAction.getClassLoader());
        }
        if(parcel.readInt() != 0)
            mSourceRectHint = (Rect)Rect.CREATOR.createFromParcel(parcel);
    }

    PictureInPictureArgs(Parcel parcel, PictureInPictureArgs pictureinpictureargs)
    {
        this(parcel);
    }

    private PictureInPictureArgs(Rational rational, List list, Rect rect)
    {
        mAspectRatio = rational;
        mUserActions = list;
        mSourceRectHint = rect;
    }

    PictureInPictureArgs(Rational rational, List list, Rect rect, PictureInPictureArgs pictureinpictureargs)
    {
        this(rational, list, rect);
    }

    public static PictureInPictureArgs convert(PictureInPictureParams pictureinpictureparams)
    {
        return new PictureInPictureArgs(pictureinpictureparams.getAspectRatioRational(), pictureinpictureparams.getActions(), pictureinpictureparams.getSourceRectHint());
    }

    public static PictureInPictureParams convert(PictureInPictureArgs pictureinpictureargs)
    {
        return new PictureInPictureParams(pictureinpictureargs.getAspectRatioRational(), pictureinpictureargs.getActions(), pictureinpictureargs.getSourceRectHint());
    }

    public void copyOnlySet(PictureInPictureArgs pictureinpictureargs)
    {
        if(pictureinpictureargs.hasSetAspectRatio())
            mAspectRatio = pictureinpictureargs.mAspectRatio;
        if(pictureinpictureargs.hasSetActions())
            mUserActions = pictureinpictureargs.mUserActions;
        if(pictureinpictureargs.hasSourceBoundsHint())
            mSourceRectHint = new Rect(pictureinpictureargs.getSourceRectHint());
    }

    public int describeContents()
    {
        return 0;
    }

    public List getActions()
    {
        return mUserActions;
    }

    public float getAspectRatio()
    {
        if(mAspectRatio != null)
            return mAspectRatio.floatValue();
        else
            return 0.0F;
    }

    public Rational getAspectRatioRational()
    {
        return mAspectRatio;
    }

    public Rect getSourceRectHint()
    {
        return mSourceRectHint;
    }

    public Rect getSourceRectHintInsets()
    {
        return mSourceRectHintInsets;
    }

    public boolean hasSetActions()
    {
        boolean flag;
        if(mUserActions != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasSetAspectRatio()
    {
        boolean flag;
        if(mAspectRatio != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasSourceBoundsHint()
    {
        boolean flag;
        if(mSourceRectHint != null)
            flag = mSourceRectHint.isEmpty() ^ true;
        else
            flag = false;
        return flag;
    }

    public boolean hasSourceBoundsHintInsets()
    {
        boolean flag;
        if(mSourceRectHintInsets != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setActions(List list)
    {
        if(mUserActions != null)
            mUserActions = null;
        if(list != null)
            mUserActions = new ArrayList(list);
    }

    public void setAspectRatio(float f)
    {
        mAspectRatio = new Rational((int)(1E+009F * f), 0x3b9aca00);
    }

    public void setSourceRectHint(Rect rect)
    {
        if(rect == null)
            mSourceRectHint = null;
        else
            mSourceRectHint = new Rect(rect);
    }

    public void setSourceRectHintInsets(Rect rect)
    {
        if(rect == null)
            mSourceRectHintInsets = null;
        else
            mSourceRectHintInsets = new Rect(rect);
    }

    public void truncateActions(int i)
    {
        if(hasSetActions())
            mUserActions = mUserActions.subList(0, Math.min(mUserActions.size(), i));
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mAspectRatio != null)
        {
            parcel.writeInt(1);
            parcel.writeInt(mAspectRatio.getNumerator());
            parcel.writeInt(mAspectRatio.getDenominator());
        } else
        {
            parcel.writeInt(0);
        }
        if(mUserActions != null)
        {
            parcel.writeInt(1);
            parcel.writeParcelableList(mUserActions, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(mSourceRectHint != null)
        {
            parcel.writeInt(1);
            mSourceRectHint.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PictureInPictureArgs createFromParcel(Parcel parcel)
        {
            return new PictureInPictureArgs(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PictureInPictureArgs[] newArray(int i)
        {
            return new PictureInPictureArgs[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private Rational mAspectRatio;
    private Rect mSourceRectHint;
    private Rect mSourceRectHintInsets;
    private List mUserActions;

}
