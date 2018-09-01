// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.view:
//            KeyboardShortcutInfo

public final class KeyboardShortcutGroup
    implements Parcelable
{

    private KeyboardShortcutGroup(Parcel parcel)
    {
        boolean flag = true;
        super();
        mItems = new ArrayList();
        mLabel = parcel.readCharSequence();
        parcel.readTypedList(mItems, KeyboardShortcutInfo.CREATOR);
        if(parcel.readInt() != 1)
            flag = false;
        mSystemGroup = flag;
    }

    KeyboardShortcutGroup(Parcel parcel, KeyboardShortcutGroup keyboardshortcutgroup)
    {
        this(parcel);
    }

    public KeyboardShortcutGroup(CharSequence charsequence)
    {
        this(charsequence, Collections.emptyList());
    }

    public KeyboardShortcutGroup(CharSequence charsequence, List list)
    {
        mLabel = charsequence;
        mItems = new ArrayList((Collection)Preconditions.checkNotNull(list));
    }

    public KeyboardShortcutGroup(CharSequence charsequence, List list, boolean flag)
    {
        mLabel = charsequence;
        mItems = new ArrayList((Collection)Preconditions.checkNotNull(list));
        mSystemGroup = flag;
    }

    public KeyboardShortcutGroup(CharSequence charsequence, boolean flag)
    {
        this(charsequence, Collections.emptyList(), flag);
    }

    public void addItem(KeyboardShortcutInfo keyboardshortcutinfo)
    {
        mItems.add(keyboardshortcutinfo);
    }

    public int describeContents()
    {
        return 0;
    }

    public List getItems()
    {
        return mItems;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public boolean isSystemGroup()
    {
        return mSystemGroup;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeCharSequence(mLabel);
        parcel.writeTypedList(mItems);
        if(mSystemGroup)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeyboardShortcutGroup createFromParcel(Parcel parcel)
        {
            return new KeyboardShortcutGroup(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeyboardShortcutGroup[] newArray(int i)
        {
            return new KeyboardShortcutGroup[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final List mItems;
    private final CharSequence mLabel;
    private boolean mSystemGroup;

}
