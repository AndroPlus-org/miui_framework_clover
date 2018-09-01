// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.view:
//            KeyEvent

public final class KeyboardShortcutInfo
    implements Parcelable
{

    private KeyboardShortcutInfo(Parcel parcel)
    {
        mLabel = parcel.readCharSequence();
        mIcon = (Icon)parcel.readParcelable(null);
        mBaseCharacter = (char)parcel.readInt();
        mKeycode = parcel.readInt();
        mModifiers = parcel.readInt();
    }

    KeyboardShortcutInfo(Parcel parcel, KeyboardShortcutInfo keyboardshortcutinfo)
    {
        this(parcel);
    }

    public KeyboardShortcutInfo(CharSequence charsequence, char c, int i)
    {
        mLabel = charsequence;
        boolean flag;
        if(c != 0)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag);
        mBaseCharacter = c;
        mKeycode = 0;
        mModifiers = i;
        mIcon = null;
    }

    public KeyboardShortcutInfo(CharSequence charsequence, int i, int j)
    {
        this(charsequence, null, i, j);
    }

    public KeyboardShortcutInfo(CharSequence charsequence, Icon icon, int i, int j)
    {
        boolean flag = false;
        super();
        mLabel = charsequence;
        mIcon = icon;
        mBaseCharacter = (char)0;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= KeyEvent.getMaxKeyCode())
                flag1 = true;
        }
        Preconditions.checkArgument(flag1);
        mKeycode = i;
        mModifiers = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public char getBaseCharacter()
    {
        return mBaseCharacter;
    }

    public Icon getIcon()
    {
        return mIcon;
    }

    public int getKeycode()
    {
        return mKeycode;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public int getModifiers()
    {
        return mModifiers;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeCharSequence(mLabel);
        parcel.writeParcelable(mIcon, 0);
        parcel.writeInt(mBaseCharacter);
        parcel.writeInt(mKeycode);
        parcel.writeInt(mModifiers);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeyboardShortcutInfo createFromParcel(Parcel parcel)
        {
            return new KeyboardShortcutInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeyboardShortcutInfo[] newArray(int i)
        {
            return new KeyboardShortcutInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final char mBaseCharacter;
    private final Icon mIcon;
    private final int mKeycode;
    private final CharSequence mLabel;
    private final int mModifiers;

}
