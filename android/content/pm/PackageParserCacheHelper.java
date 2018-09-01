// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.HashMap;

public class PackageParserCacheHelper
{
    public static class ReadHelper extends android.os.Parcel.ReadWriteHelper
    {

        public String readString(Parcel parcel)
        {
            return (String)mStrings.get(parcel.readInt());
        }

        public void startAndInstall()
        {
            mStrings.clear();
            int i = mParcel.readInt();
            int j = mParcel.dataPosition();
            mParcel.setDataPosition(i);
            mParcel.readStringList(mStrings);
            mParcel.setDataPosition(j);
            mParcel.setReadWriteHelper(this);
        }

        private final Parcel mParcel;
        private final ArrayList mStrings = new ArrayList();

        public ReadHelper(Parcel parcel)
        {
            mParcel = parcel;
        }
    }

    public static class WriteHelper extends android.os.Parcel.ReadWriteHelper
    {

        public void finishAndUninstall()
        {
            mParcel.setReadWriteHelper(null);
            int i = mParcel.dataPosition();
            mParcel.writeStringList(mStrings);
            mParcel.setDataPosition(mStartPos);
            mParcel.writeInt(i);
            mParcel.setDataPosition(mParcel.dataSize());
        }

        public void writeString(Parcel parcel, String s)
        {
            Integer integer = (Integer)mIndexes.get(s);
            if(integer != null)
            {
                parcel.writeInt(integer.intValue());
            } else
            {
                int i = mStrings.size();
                mIndexes.put(s, Integer.valueOf(i));
                mStrings.add(s);
                parcel.writeInt(i);
            }
        }

        private final HashMap mIndexes = new HashMap();
        private final Parcel mParcel;
        private final int mStartPos;
        private final ArrayList mStrings = new ArrayList();

        public WriteHelper(Parcel parcel)
        {
            mParcel = parcel;
            mStartPos = parcel.dataPosition();
            mParcel.writeInt(0);
            mParcel.setReadWriteHelper(this);
        }
    }


    private PackageParserCacheHelper()
    {
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "PackageParserCacheHelper";
}
