// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.statusbar;

import android.graphics.drawable.Icon;
import android.os.*;
import android.text.TextUtils;

public class StatusBarIcon
    implements Parcelable
{

    public StatusBarIcon(Parcel parcel)
    {
        visible = true;
        readFromParcel(parcel);
    }

    public StatusBarIcon(UserHandle userhandle, String s, Icon icon1, int i, int j, CharSequence charsequence)
    {
        visible = true;
        Icon icon2 = icon1;
        if(icon1.getType() == 2)
        {
            icon2 = icon1;
            if(TextUtils.isEmpty(icon1.getResPackage()))
                icon2 = Icon.createWithResource(s, icon1.getResId());
        }
        pkg = s;
        user = userhandle;
        icon = icon2;
        iconLevel = i;
        number = j;
        contentDescription = charsequence;
    }

    public StatusBarIcon(String s, UserHandle userhandle, int i, int j, int k, CharSequence charsequence)
    {
        this(userhandle, s, Icon.createWithResource(s, i), j, k, charsequence);
    }

    public StatusBarIcon clone()
    {
        StatusBarIcon statusbaricon = new StatusBarIcon(user, pkg, icon, iconLevel, number, contentDescription);
        statusbaricon.visible = visible;
        return statusbaricon;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public void readFromParcel(Parcel parcel)
    {
        icon = (Icon)parcel.readParcelable(null);
        pkg = parcel.readString();
        user = (UserHandle)parcel.readParcelable(null);
        iconLevel = parcel.readInt();
        boolean flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        visible = flag;
        number = parcel.readInt();
        contentDescription = parcel.readCharSequence();
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("StatusBarIcon(icon=").append(icon);
        String s;
        if(iconLevel != 0)
            s = (new StringBuilder()).append(" level=").append(iconLevel).toString();
        else
            s = "";
        stringbuilder = stringbuilder.append(s);
        if(visible)
            s = " visible";
        else
            s = "";
        stringbuilder = stringbuilder.append(s).append(" user=").append(user.getIdentifier());
        if(number != 0)
            s = (new StringBuilder()).append(" num=").append(number).toString();
        else
            s = "";
        return stringbuilder.append(s).append(" )").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 0;
        parcel.writeParcelable(icon, 0);
        parcel.writeString(pkg);
        parcel.writeParcelable(user, 0);
        parcel.writeInt(iconLevel);
        if(visible)
            i = 1;
        parcel.writeInt(i);
        parcel.writeInt(number);
        parcel.writeCharSequence(contentDescription);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StatusBarIcon createFromParcel(Parcel parcel)
        {
            return new StatusBarIcon(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StatusBarIcon[] newArray(int i)
        {
            return new StatusBarIcon[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public CharSequence contentDescription;
    public Icon icon;
    public int iconLevel;
    public int number;
    public String pkg;
    public UserHandle user;
    public boolean visible;

}
