// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.GraphicBuffer;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

public class AppTransitionAnimationSpec
    implements Parcelable
{

    public AppTransitionAnimationSpec(int i, GraphicBuffer graphicbuffer, Rect rect1)
    {
        taskId = i;
        rect = rect1;
        buffer = graphicbuffer;
    }

    public AppTransitionAnimationSpec(Parcel parcel)
    {
        taskId = parcel.readInt();
        rect = (Rect)parcel.readParcelable(null);
        buffer = (GraphicBuffer)parcel.readParcelable(null);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{taskId: ").append(taskId).append(", buffer: ").append(buffer).append(", rect: ").append(rect).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(taskId);
        parcel.writeParcelable(rect, 0);
        parcel.writeParcelable(buffer, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AppTransitionAnimationSpec createFromParcel(Parcel parcel)
        {
            return new AppTransitionAnimationSpec(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AppTransitionAnimationSpec[] newArray(int i)
        {
            return new AppTransitionAnimationSpec[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final GraphicBuffer buffer;
    public final Rect rect;
    public final int taskId;

}
