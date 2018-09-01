// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano.android;

import android.os.Parcel;
import android.util.Log;
import com.android.framework.protobuf.nano.InvalidProtocolBufferNanoException;
import com.android.framework.protobuf.nano.MessageNano;
import java.lang.reflect.*;

public final class ParcelableMessageNanoCreator
    implements android.os.Parcelable.Creator
{

    public ParcelableMessageNanoCreator(Class class1)
    {
        mClazz = class1;
    }

    static void writeToParcel(Class class1, MessageNano messagenano, Parcel parcel)
    {
        parcel.writeString(class1.getName());
        parcel.writeByteArray(MessageNano.toByteArray(messagenano));
    }

    public MessageNano createFromParcel(Parcel parcel)
    {
        String s;
        byte abyte0[];
        Parcel parcel1;
        Parcel parcel2;
        Parcel parcel3;
        Parcel parcel4;
        Parcel parcel5;
        Parcel parcel6;
        s = parcel.readString();
        abyte0 = parcel.createByteArray();
        parcel1 = null;
        parcel2 = null;
        parcel3 = null;
        parcel4 = null;
        parcel5 = null;
        parcel6 = null;
        parcel = (MessageNano)Class.forName(s, false, getClass().getClassLoader()).asSubclass(com/android/framework/protobuf/nano/MessageNano).getConstructor(new Class[0]).newInstance(new Object[0]);
        parcel6 = parcel;
        parcel1 = parcel;
        parcel2 = parcel;
        parcel3 = parcel;
        parcel4 = parcel;
        parcel5 = parcel;
        try
        {
            MessageNano.mergeFrom(parcel, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            Log.e("PMNCreator", "Exception trying to create proto from parcel", parcel);
            parcel = parcel5;
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            Log.e("PMNCreator", "Exception trying to create proto from parcel", parcel);
            parcel = parcel4;
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            Log.e("PMNCreator", "Exception trying to create proto from parcel", parcel);
            parcel = parcel3;
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            Log.e("PMNCreator", "Exception trying to create proto from parcel", parcel);
            parcel = parcel2;
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            Log.e("PMNCreator", "Exception trying to create proto from parcel", parcel);
            parcel = parcel1;
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            Log.e("PMNCreator", "Exception trying to create proto from parcel", parcel);
            parcel = parcel6;
        }
        return parcel;
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public MessageNano[] newArray(int i)
    {
        return (MessageNano[])Array.newInstance(mClazz, i);
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    private static final String TAG = "PMNCreator";
    private final Class mClazz;
}
