// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.*;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.*;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.Iterator;
import java.util.Set;

public final class ShortcutInfo
    implements Parcelable
{
    public static class Builder
    {

        static ComponentName _2D_get0(Builder builder)
        {
            return builder.mActivity;
        }

        static Set _2D_get1(Builder builder)
        {
            return builder.mCategories;
        }

        static CharSequence _2D_get10(Builder builder)
        {
            return builder.mText;
        }

        static int _2D_get11(Builder builder)
        {
            return builder.mTextResId;
        }

        static CharSequence _2D_get12(Builder builder)
        {
            return builder.mTitle;
        }

        static int _2D_get13(Builder builder)
        {
            return builder.mTitleResId;
        }

        static Context _2D_get2(Builder builder)
        {
            return builder.mContext;
        }

        static CharSequence _2D_get3(Builder builder)
        {
            return builder.mDisabledMessage;
        }

        static int _2D_get4(Builder builder)
        {
            return builder.mDisabledMessageResId;
        }

        static PersistableBundle _2D_get5(Builder builder)
        {
            return builder.mExtras;
        }

        static Icon _2D_get6(Builder builder)
        {
            return builder.mIcon;
        }

        static String _2D_get7(Builder builder)
        {
            return builder.mId;
        }

        static Intent[] _2D_get8(Builder builder)
        {
            return builder.mIntents;
        }

        static int _2D_get9(Builder builder)
        {
            return builder.mRank;
        }

        public ShortcutInfo build()
        {
            return new ShortcutInfo(this, null);
        }

        public Builder setActivity(ComponentName componentname)
        {
            mActivity = (ComponentName)Preconditions.checkNotNull(componentname, "activity cannot be null");
            return this;
        }

        public Builder setCategories(Set set)
        {
            mCategories = set;
            return this;
        }

        public Builder setDisabledMessage(CharSequence charsequence)
        {
            boolean flag = false;
            if(mDisabledMessageResId == 0)
                flag = true;
            Preconditions.checkState(flag, "disabledMessageResId already set");
            mDisabledMessage = Preconditions.checkStringNotEmpty(charsequence, "disabledMessage cannot be empty");
            return this;
        }

        public Builder setDisabledMessageResId(int i)
        {
            boolean flag;
            if(mDisabledMessage == null)
                flag = true;
            else
                flag = false;
            Preconditions.checkState(flag, "disabledMessage already set");
            mDisabledMessageResId = i;
            return this;
        }

        public Builder setExtras(PersistableBundle persistablebundle)
        {
            mExtras = persistablebundle;
            return this;
        }

        public Builder setIcon(Icon icon)
        {
            mIcon = ShortcutInfo.validateIcon(icon);
            return this;
        }

        public Builder setId(String s)
        {
            mId = (String)Preconditions.checkStringNotEmpty(s, "id cannot be empty");
            return this;
        }

        public Builder setIntent(Intent intent)
        {
            return setIntents(new Intent[] {
                intent
            });
        }

        public Builder setIntents(Intent aintent[])
        {
            Preconditions.checkNotNull(aintent, "intents cannot be null");
            Preconditions.checkNotNull(Integer.valueOf(aintent.length), "intents cannot be empty");
            int i = 0;
            for(int j = aintent.length; i < j; i++)
            {
                Intent intent = aintent[i];
                Preconditions.checkNotNull(intent, "intents cannot contain null");
                Preconditions.checkNotNull(intent.getAction(), "intent's action must be set");
            }

            mIntents = ShortcutInfo._2D_wrap0(aintent);
            return this;
        }

        public Builder setLongLabel(CharSequence charsequence)
        {
            boolean flag = false;
            if(mTextResId == 0)
                flag = true;
            Preconditions.checkState(flag, "longLabelResId already set");
            mText = Preconditions.checkStringNotEmpty(charsequence, "longLabel cannot be empty");
            return this;
        }

        public Builder setLongLabelResId(int i)
        {
            boolean flag;
            if(mText == null)
                flag = true;
            else
                flag = false;
            Preconditions.checkState(flag, "longLabel already set");
            mTextResId = i;
            return this;
        }

        public Builder setRank(int i)
        {
            boolean flag = false;
            if(i >= 0)
                flag = true;
            Preconditions.checkArgument(flag, "Rank cannot be negative or bigger than MAX_RANK");
            mRank = i;
            return this;
        }

        public Builder setShortLabel(CharSequence charsequence)
        {
            boolean flag = false;
            if(mTitleResId == 0)
                flag = true;
            Preconditions.checkState(flag, "shortLabelResId already set");
            mTitle = Preconditions.checkStringNotEmpty(charsequence, "shortLabel cannot be empty");
            return this;
        }

        public Builder setShortLabelResId(int i)
        {
            boolean flag;
            if(mTitle == null)
                flag = true;
            else
                flag = false;
            Preconditions.checkState(flag, "shortLabel already set");
            mTitleResId = i;
            return this;
        }

        public Builder setText(CharSequence charsequence)
        {
            return setLongLabel(charsequence);
        }

        public Builder setTextResId(int i)
        {
            return setLongLabelResId(i);
        }

        public Builder setTitle(CharSequence charsequence)
        {
            return setShortLabel(charsequence);
        }

        public Builder setTitleResId(int i)
        {
            return setShortLabelResId(i);
        }

        private ComponentName mActivity;
        private Set mCategories;
        private final Context mContext;
        private CharSequence mDisabledMessage;
        private int mDisabledMessageResId;
        private PersistableBundle mExtras;
        private Icon mIcon;
        private String mId;
        private Intent mIntents[];
        private int mRank;
        private CharSequence mText;
        private int mTextResId;
        private CharSequence mTitle;
        private int mTitleResId;

        public Builder(Context context)
        {
            mRank = 0x7fffffff;
            mContext = context;
        }

        public Builder(Context context, String s)
        {
            mRank = 0x7fffffff;
            mContext = context;
            mId = (String)Preconditions.checkStringNotEmpty(s, "id cannot be empty");
        }
    }


    static Intent[] _2D_wrap0(Intent aintent[])
    {
        return cloneIntents(aintent);
    }

    public ShortcutInfo(int i, String s, String s1, ComponentName componentname, Icon icon, CharSequence charsequence, int j, 
            String s2, CharSequence charsequence1, int k, String s3, CharSequence charsequence2, int l, String s4, 
            Set set, Intent aintent[], int i1, PersistableBundle persistablebundle, long l1, int j1, 
            int k1, String s5, String s6)
    {
        mUserId = i;
        mId = s;
        mPackageName = s1;
        mActivity = componentname;
        mIcon = icon;
        mTitle = charsequence;
        mTitleResId = j;
        mTitleResName = s2;
        mText = charsequence1;
        mTextResId = k;
        mTextResName = s3;
        mDisabledMessage = charsequence2;
        mDisabledMessageResId = l;
        mDisabledMessageResName = s4;
        mCategories = cloneCategories(set);
        mIntents = cloneIntents(aintent);
        fixUpIntentExtras();
        mRank = i1;
        mExtras = persistablebundle;
        mLastChangedTimestamp = l1;
        mFlags = j1;
        mIconResId = k1;
        mIconResName = s5;
        mBitmapPath = s6;
    }

    private ShortcutInfo(Builder builder)
    {
        mUserId = Builder._2D_get2(builder).getUserId();
        mId = (String)Preconditions.checkStringNotEmpty(Builder._2D_get7(builder), "Shortcut ID must be provided");
        mPackageName = Builder._2D_get2(builder).getPackageName();
        mActivity = Builder._2D_get0(builder);
        mIcon = Builder._2D_get6(builder);
        mTitle = Builder._2D_get12(builder);
        mTitleResId = Builder._2D_get13(builder);
        mText = Builder._2D_get10(builder);
        mTextResId = Builder._2D_get11(builder);
        mDisabledMessage = Builder._2D_get3(builder);
        mDisabledMessageResId = Builder._2D_get4(builder);
        mCategories = cloneCategories(Builder._2D_get1(builder));
        mIntents = cloneIntents(Builder._2D_get8(builder));
        fixUpIntentExtras();
        mRank = Builder._2D_get9(builder);
        mExtras = Builder._2D_get5(builder);
        updateTimestamp();
    }

    ShortcutInfo(Builder builder, ShortcutInfo shortcutinfo)
    {
        this(builder);
    }

    private ShortcutInfo(ShortcutInfo shortcutinfo, int i)
    {
        mUserId = shortcutinfo.mUserId;
        mId = shortcutinfo.mId;
        mPackageName = shortcutinfo.mPackageName;
        mActivity = shortcutinfo.mActivity;
        mFlags = shortcutinfo.mFlags;
        mLastChangedTimestamp = shortcutinfo.mLastChangedTimestamp;
        mIconResId = shortcutinfo.mIconResId;
        if((i & 4) == 0)
        {
            if((i & 1) == 0)
            {
                mIcon = shortcutinfo.mIcon;
                mBitmapPath = shortcutinfo.mBitmapPath;
            }
            mTitle = shortcutinfo.mTitle;
            mTitleResId = shortcutinfo.mTitleResId;
            mText = shortcutinfo.mText;
            mTextResId = shortcutinfo.mTextResId;
            mDisabledMessage = shortcutinfo.mDisabledMessage;
            mDisabledMessageResId = shortcutinfo.mDisabledMessageResId;
            mCategories = cloneCategories(shortcutinfo.mCategories);
            if((i & 2) == 0)
            {
                mIntents = cloneIntents(shortcutinfo.mIntents);
                mIntentPersistableExtrases = clonePersistableBundle(shortcutinfo.mIntentPersistableExtrases);
            }
            mRank = shortcutinfo.mRank;
            mExtras = shortcutinfo.mExtras;
            if((i & 8) == 0)
            {
                mTitleResName = shortcutinfo.mTitleResName;
                mTextResName = shortcutinfo.mTextResName;
                mDisabledMessageResName = shortcutinfo.mDisabledMessageResName;
                mIconResName = shortcutinfo.mIconResName;
            }
        } else
        {
            mFlags = mFlags | 0x10;
        }
    }

    private ShortcutInfo(Parcel parcel)
    {
        ClassLoader classloader = getClass().getClassLoader();
        mUserId = parcel.readInt();
        mId = parcel.readString();
        mPackageName = parcel.readString();
        mActivity = (ComponentName)parcel.readParcelable(classloader);
        mFlags = parcel.readInt();
        mIconResId = parcel.readInt();
        mLastChangedTimestamp = parcel.readLong();
        if(parcel.readInt() == 0)
            return;
        mIcon = (Icon)parcel.readParcelable(classloader);
        mTitle = parcel.readCharSequence();
        mTitleResId = parcel.readInt();
        mText = parcel.readCharSequence();
        mTextResId = parcel.readInt();
        mDisabledMessage = parcel.readCharSequence();
        mDisabledMessageResId = parcel.readInt();
        mIntents = (Intent[])parcel.readParcelableArray(classloader, android/content/Intent);
        mIntentPersistableExtrases = (PersistableBundle[])parcel.readParcelableArray(classloader, android/os/PersistableBundle);
        mRank = parcel.readInt();
        mExtras = (PersistableBundle)parcel.readParcelable(classloader);
        mBitmapPath = parcel.readString();
        mIconResName = parcel.readString();
        mTitleResName = parcel.readString();
        mTextResName = parcel.readString();
        mDisabledMessageResName = parcel.readString();
        int i = parcel.readInt();
        if(i == 0)
        {
            mCategories = null;
        } else
        {
            mCategories = new ArraySet(i);
            int j = 0;
            while(j < i) 
            {
                mCategories.add(parcel.readString().intern());
                j++;
            }
        }
    }

    ShortcutInfo(Parcel parcel, ShortcutInfo shortcutinfo)
    {
        this(parcel);
    }

    private static ArraySet cloneCategories(Set set)
    {
        if(set == null)
            return null;
        ArraySet arrayset = new ArraySet(set.size());
        Iterator iterator = set.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            set = (String)iterator.next();
            if(!TextUtils.isEmpty(set))
                arrayset.add(set.toString().intern());
        } while(true);
        return arrayset;
    }

    private static Intent[] cloneIntents(Intent aintent[])
    {
        if(aintent == null)
            return null;
        Intent aintent1[] = new Intent[aintent.length];
        for(int i = 0; i < aintent1.length; i++)
            if(aintent[i] != null)
                aintent1[i] = new Intent(aintent[i]);

        return aintent1;
    }

    private static PersistableBundle[] clonePersistableBundle(PersistableBundle apersistablebundle[])
    {
        if(apersistablebundle == null)
            return null;
        PersistableBundle apersistablebundle1[] = new PersistableBundle[apersistablebundle.length];
        for(int i = 0; i < apersistablebundle1.length; i++)
            if(apersistablebundle[i] != null)
                apersistablebundle1[i] = new PersistableBundle(apersistablebundle[i]);

        return apersistablebundle1;
    }

    private void fixUpIntentExtras()
    {
        if(mIntents == null)
        {
            mIntentPersistableExtrases = null;
            return;
        }
        mIntentPersistableExtrases = new PersistableBundle[mIntents.length];
        int i = 0;
        while(i < mIntents.length) 
        {
            Intent intent = mIntents[i];
            Bundle bundle = intent.getExtras();
            if(bundle == null)
            {
                mIntentPersistableExtrases[i] = null;
            } else
            {
                mIntentPersistableExtrases[i] = new PersistableBundle(bundle);
                intent.replaceExtras((Bundle)null);
            }
            i++;
        }
    }

    public static IllegalArgumentException getInvalidIconException()
    {
        return new IllegalArgumentException("Unsupported icon type: only the bitmap and resource types are supported");
    }

    public static String getResourceEntryName(String s)
    {
        int i = s.indexOf('/');
        if(i < 0)
            return null;
        else
            return s.substring(i + 1);
    }

    public static String getResourcePackageName(String s)
    {
        int i = s.indexOf(':');
        if(i < 0)
            return null;
        else
            return s.substring(0, i);
    }

    private CharSequence getResourceString(Resources resources, int i, CharSequence charsequence)
    {
        try
        {
            resources = resources.getString(i);
        }
        // Misplaced declaration of an exception variable
        catch(Resources resources)
        {
            Log.e("Shortcut", (new StringBuilder()).append("Resource for ID=").append(i).append(" not found in package ").append(mPackageName).toString());
            return charsequence;
        }
        return resources;
    }

    public static String getResourceTypeAndEntryName(String s)
    {
        int i = s.indexOf(':');
        if(i < 0)
            return null;
        else
            return s.substring(i + 1);
    }

    public static String getResourceTypeName(String s)
    {
        int i = s.indexOf(':');
        if(i < 0)
            return null;
        int j = s.indexOf('/', i + 1);
        if(j < 0)
            return null;
        else
            return s.substring(i + 1, j);
    }

    public static int lookUpResourceId(Resources resources, String s, String s1, String s2)
    {
        if(s == null)
            return 0;
        int i = Integer.parseInt(s);
        return i;
        NumberFormatException numberformatexception;
        numberformatexception;
        int j;
        try
        {
            j = resources.getIdentifier(s, s1, s2);
        }
        // Misplaced declaration of an exception variable
        catch(Resources resources)
        {
            Log.e("Shortcut", (new StringBuilder()).append("Resource ID for name=").append(s).append(" not found in package ").append(s2).toString());
            return 0;
        }
        return j;
    }

    public static String lookUpResourceName(Resources resources, int i, boolean flag, String s)
    {
        if(i == 0)
            return null;
        try
        {
            resources = resources.getResourceName(i);
            if("android".equals(getResourcePackageName(resources)))
                return String.valueOf(i);
        }
        // Misplaced declaration of an exception variable
        catch(Resources resources)
        {
            Log.e("Shortcut", (new StringBuilder()).append("Resource name for ID=").append(i).append(" not found in package ").append(s).append(". Resource IDs may change when the application is upgraded, and the system").append(" may not be able to find the correct resource.").toString());
            return null;
        }
        if(!flag) goto _L2; else goto _L1
_L1:
        resources = getResourceTypeAndEntryName(resources);
_L4:
        return resources;
_L2:
        resources = getResourceEntryName(resources);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Intent setIntentExtras(Intent intent, PersistableBundle persistablebundle)
    {
        if(persistablebundle == null)
            intent.replaceExtras((Bundle)null);
        else
            intent.replaceExtras(new Bundle(persistablebundle));
        return intent;
    }

    private String toStringInner(boolean flag, boolean flag1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("ShortcutInfo {");
        stringbuilder.append("id=");
        Object obj;
        if(flag)
            obj = "***";
        else
            obj = mId;
        stringbuilder.append(((String) (obj)));
        stringbuilder.append(", flags=0x");
        stringbuilder.append(Integer.toHexString(mFlags));
        stringbuilder.append(" [");
        if(!isEnabled())
            stringbuilder.append("X");
        if(isImmutable())
            stringbuilder.append("Im");
        if(isManifestShortcut())
            stringbuilder.append("M");
        if(isDynamic())
            stringbuilder.append("D");
        if(isPinned())
            stringbuilder.append("P");
        if(hasIconFile())
            stringbuilder.append("If");
        if(isIconPendingSave())
            stringbuilder.append("^");
        if(hasIconResource())
            stringbuilder.append("Ir");
        if(hasKeyFieldsOnly())
            stringbuilder.append("K");
        if(hasStringResourcesResolved())
            stringbuilder.append("Sr");
        if(isReturnedByServer())
            stringbuilder.append("V");
        stringbuilder.append("]");
        stringbuilder.append(", packageName=");
        stringbuilder.append(mPackageName);
        stringbuilder.append(", activity=");
        stringbuilder.append(mActivity);
        stringbuilder.append(", shortLabel=");
        if(flag)
            obj = "***";
        else
            obj = mTitle;
        stringbuilder.append(((CharSequence) (obj)));
        stringbuilder.append(", resId=");
        stringbuilder.append(mTitleResId);
        stringbuilder.append("[");
        stringbuilder.append(mTitleResName);
        stringbuilder.append("]");
        stringbuilder.append(", longLabel=");
        if(flag)
            obj = "***";
        else
            obj = mText;
        stringbuilder.append(((CharSequence) (obj)));
        stringbuilder.append(", resId=");
        stringbuilder.append(mTextResId);
        stringbuilder.append("[");
        stringbuilder.append(mTextResName);
        stringbuilder.append("]");
        stringbuilder.append(", disabledMessage=");
        if(flag)
            obj = "***";
        else
            obj = mDisabledMessage;
        stringbuilder.append(((CharSequence) (obj)));
        stringbuilder.append(", resId=");
        stringbuilder.append(mDisabledMessageResId);
        stringbuilder.append("[");
        stringbuilder.append(mDisabledMessageResName);
        stringbuilder.append("]");
        stringbuilder.append(", categories=");
        stringbuilder.append(mCategories);
        stringbuilder.append(", icon=");
        stringbuilder.append(mIcon);
        stringbuilder.append(", rank=");
        stringbuilder.append(mRank);
        stringbuilder.append(", timestamp=");
        stringbuilder.append(mLastChangedTimestamp);
        stringbuilder.append(", intents=");
        if(mIntents == null)
            stringbuilder.append("null");
        else
        if(flag)
        {
            stringbuilder.append("size:");
            stringbuilder.append(mIntents.length);
        } else
        {
            int i = mIntents.length;
            stringbuilder.append("[");
            String s = "";
            for(int j = 0; j < i; j++)
            {
                stringbuilder.append(s);
                s = ", ";
                stringbuilder.append(mIntents[j]);
                stringbuilder.append("/");
                stringbuilder.append(mIntentPersistableExtrases[j]);
            }

            stringbuilder.append("]");
        }
        stringbuilder.append(", extras=");
        stringbuilder.append(mExtras);
        if(flag1)
        {
            stringbuilder.append(", iconRes=");
            stringbuilder.append(mIconResId);
            stringbuilder.append("[");
            stringbuilder.append(mIconResName);
            stringbuilder.append("]");
            stringbuilder.append(", bitmapPath=");
            stringbuilder.append(mBitmapPath);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public static Icon validateIcon(Icon icon)
    {
        switch(icon.getType())
        {
        case 3: // '\003'
        case 4: // '\004'
        default:
            throw getInvalidIconException();

        case 1: // '\001'
        case 2: // '\002'
        case 5: // '\005'
            break;
        }
        if(icon.hasTint())
            throw new IllegalArgumentException("Icons with tints are not supported");
        else
            return icon;
    }

    public void addFlags(int i)
    {
        mFlags = mFlags | i;
    }

    public void clearFlags(int i)
    {
        mFlags = mFlags & i;
    }

    public void clearIcon()
    {
        mIcon = null;
    }

    public void clearIconPendingSave()
    {
        clearFlags(2048);
    }

    public void clearImplicitRankAndRankChangedFlag()
    {
        mImplicitRank = 0;
    }

    public ShortcutInfo clone(int i)
    {
        return new ShortcutInfo(this, i);
    }

    public void copyNonNullFieldsFrom(ShortcutInfo shortcutinfo)
    {
        ensureUpdatableWith(shortcutinfo);
        if(shortcutinfo.mActivity != null)
            mActivity = shortcutinfo.mActivity;
        if(shortcutinfo.mIcon != null)
        {
            mIcon = shortcutinfo.mIcon;
            mIconResId = 0;
            mIconResName = null;
            mBitmapPath = null;
        }
        if(shortcutinfo.mTitle != null)
        {
            mTitle = shortcutinfo.mTitle;
            mTitleResId = 0;
            mTitleResName = null;
        } else
        if(shortcutinfo.mTitleResId != 0)
        {
            mTitle = null;
            mTitleResId = shortcutinfo.mTitleResId;
            mTitleResName = null;
        }
        if(shortcutinfo.mText != null)
        {
            mText = shortcutinfo.mText;
            mTextResId = 0;
            mTextResName = null;
        } else
        if(shortcutinfo.mTextResId != 0)
        {
            mText = null;
            mTextResId = shortcutinfo.mTextResId;
            mTextResName = null;
        }
        if(shortcutinfo.mDisabledMessage != null)
        {
            mDisabledMessage = shortcutinfo.mDisabledMessage;
            mDisabledMessageResId = 0;
            mDisabledMessageResName = null;
        } else
        if(shortcutinfo.mDisabledMessageResId != 0)
        {
            mDisabledMessage = null;
            mDisabledMessageResId = shortcutinfo.mDisabledMessageResId;
            mDisabledMessageResName = null;
        }
        if(shortcutinfo.mCategories != null)
            mCategories = cloneCategories(shortcutinfo.mCategories);
        if(shortcutinfo.mIntents != null)
        {
            mIntents = cloneIntents(shortcutinfo.mIntents);
            mIntentPersistableExtrases = clonePersistableBundle(shortcutinfo.mIntentPersistableExtrases);
        }
        if(shortcutinfo.mRank != 0x7fffffff)
            mRank = shortcutinfo.mRank;
        if(shortcutinfo.mExtras != null)
            mExtras = shortcutinfo.mExtras;
    }

    public int describeContents()
    {
        return 0;
    }

    public void enforceMandatoryFields(boolean flag)
    {
        boolean flag1 = false;
        Preconditions.checkStringNotEmpty(mId, "Shortcut ID must be provided");
        if(!flag)
            Preconditions.checkNotNull(mActivity, "Activity must be provided");
        if(mTitle == null && mTitleResId == 0)
            throw new IllegalArgumentException("Short label must be provided");
        Preconditions.checkNotNull(mIntents, "Shortcut Intent must be provided");
        flag = flag1;
        if(mIntents.length > 0)
            flag = true;
        Preconditions.checkArgument(flag, "Shortcut Intent must be provided");
    }

    public void ensureUpdatableWith(ShortcutInfo shortcutinfo)
    {
        boolean flag;
        if(mUserId == shortcutinfo.mUserId)
            flag = true;
        else
            flag = false;
        Preconditions.checkState(flag, "Owner User ID must match");
        Preconditions.checkState(mId.equals(shortcutinfo.mId), "ID must match");
        Preconditions.checkState(mPackageName.equals(shortcutinfo.mPackageName), "Package name must match");
        Preconditions.checkState(isImmutable() ^ true, "Target ShortcutInfo is immutable");
    }

    public ComponentName getActivity()
    {
        return mActivity;
    }

    public String getBitmapPath()
    {
        return mBitmapPath;
    }

    public Set getCategories()
    {
        return mCategories;
    }

    public CharSequence getDisabledMessage()
    {
        return mDisabledMessage;
    }

    public String getDisabledMessageResName()
    {
        return mDisabledMessageResName;
    }

    public int getDisabledMessageResourceId()
    {
        return mDisabledMessageResId;
    }

    public PersistableBundle getExtras()
    {
        return mExtras;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public Icon getIcon()
    {
        return mIcon;
    }

    public String getIconResName()
    {
        return mIconResName;
    }

    public int getIconResourceId()
    {
        return mIconResId;
    }

    public String getId()
    {
        return mId;
    }

    public int getImplicitRank()
    {
        return mImplicitRank & 0x7fffffff;
    }

    public Intent getIntent()
    {
        if(mIntents == null || mIntents.length == 0)
        {
            return null;
        } else
        {
            int i = mIntents.length - 1;
            return setIntentExtras(new Intent(mIntents[i]), mIntentPersistableExtrases[i]);
        }
    }

    public PersistableBundle[] getIntentPersistableExtrases()
    {
        return mIntentPersistableExtrases;
    }

    public Intent[] getIntents()
    {
        Intent aintent[] = new Intent[mIntents.length];
        for(int i = 0; i < aintent.length; i++)
        {
            aintent[i] = new Intent(mIntents[i]);
            setIntentExtras(aintent[i], mIntentPersistableExtrases[i]);
        }

        return aintent;
    }

    public Intent[] getIntentsNoExtras()
    {
        return mIntents;
    }

    public long getLastChangedTimestamp()
    {
        return mLastChangedTimestamp;
    }

    public CharSequence getLongLabel()
    {
        return mText;
    }

    public int getLongLabelResourceId()
    {
        return mTextResId;
    }

    public String getPackage()
    {
        return mPackageName;
    }

    public int getRank()
    {
        return mRank;
    }

    public CharSequence getShortLabel()
    {
        return mTitle;
    }

    public int getShortLabelResourceId()
    {
        return mTitleResId;
    }

    public CharSequence getText()
    {
        return mText;
    }

    public int getTextResId()
    {
        return mTextResId;
    }

    public String getTextResName()
    {
        return mTextResName;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public int getTitleResId()
    {
        return mTitleResId;
    }

    public String getTitleResName()
    {
        return mTitleResName;
    }

    public UserHandle getUserHandle()
    {
        return UserHandle.of(mUserId);
    }

    public int getUserId()
    {
        return mUserId;
    }

    public boolean hasAdaptiveBitmap()
    {
        return hasFlags(512);
    }

    public boolean hasAnyResources()
    {
        boolean flag;
        if(!hasIconResource())
            flag = hasStringResources();
        else
            flag = true;
        return flag;
    }

    public boolean hasFlags(int i)
    {
        boolean flag;
        if((mFlags & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasIconFile()
    {
        return hasFlags(8);
    }

    public boolean hasIconResource()
    {
        return hasFlags(4);
    }

    public boolean hasKeyFieldsOnly()
    {
        return hasFlags(16);
    }

    public boolean hasRank()
    {
        boolean flag;
        if(mRank != 0x7fffffff)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasStringResources()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mTitleResId != 0) goto _L2; else goto _L1
_L1:
        if(mTextResId == 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mDisabledMessageResId == 0)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean hasStringResourcesResolved()
    {
        return hasFlags(128);
    }

    public boolean isAlive()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!hasFlags(2))
        {
            flag1 = flag;
            if(!hasFlags(1))
                flag1 = hasFlags(32);
        }
        return flag1;
    }

    public boolean isDeclaredInManifest()
    {
        return hasFlags(32);
    }

    public boolean isDynamic()
    {
        return hasFlags(1);
    }

    public boolean isEnabled()
    {
        return hasFlags(64) ^ true;
    }

    public boolean isFloating()
    {
        boolean flag;
        if(isPinned())
        {
            if(!isDynamic())
                flag = isManifestShortcut();
            else
                flag = true;
            flag ^= true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean isIconPendingSave()
    {
        return hasFlags(2048);
    }

    public boolean isImmutable()
    {
        return hasFlags(256);
    }

    public boolean isManifestShortcut()
    {
        return isDeclaredInManifest();
    }

    public boolean isOriginallyFromManifest()
    {
        return hasFlags(256);
    }

    public boolean isPinned()
    {
        return hasFlags(2);
    }

    public boolean isRankChanged()
    {
        boolean flag = false;
        if((mImplicitRank & 0x80000000) != 0)
            flag = true;
        return flag;
    }

    public boolean isReturnedByServer()
    {
        return hasFlags(1024);
    }

    public void lookupAndFillInResourceIds(Resources resources)
    {
        if(mTitleResName == null && mTextResName == null && mDisabledMessageResName == null && mIconResName == null)
        {
            return;
        } else
        {
            mTitleResId = lookUpResourceId(resources, mTitleResName, "string", mPackageName);
            mTextResId = lookUpResourceId(resources, mTextResName, "string", mPackageName);
            mDisabledMessageResId = lookUpResourceId(resources, mDisabledMessageResName, "string", mPackageName);
            mIconResId = lookUpResourceId(resources, mIconResName, null, mPackageName);
            return;
        }
    }

    public void lookupAndFillInResourceNames(Resources resources)
    {
        if(mTitleResId == 0 && mTextResId == 0 && mDisabledMessageResId == 0 && mIconResId == 0)
        {
            return;
        } else
        {
            mTitleResName = lookUpResourceName(resources, mTitleResId, false, mPackageName);
            mTextResName = lookUpResourceName(resources, mTextResId, false, mPackageName);
            mDisabledMessageResName = lookUpResourceName(resources, mDisabledMessageResId, false, mPackageName);
            mIconResName = lookUpResourceName(resources, mIconResId, true, mPackageName);
            return;
        }
    }

    public void replaceFlags(int i)
    {
        mFlags = i;
    }

    public void resolveResourceStrings(Resources resources)
    {
        mFlags = mFlags | 0x80;
        if(mTitleResId == 0 && mTextResId == 0 && mDisabledMessageResId == 0)
            return;
        if(mTitleResId != 0)
            mTitle = getResourceString(resources, mTitleResId, mTitle);
        if(mTextResId != 0)
            mText = getResourceString(resources, mTextResId, mText);
        if(mDisabledMessageResId != 0)
            mDisabledMessage = getResourceString(resources, mDisabledMessageResId, mDisabledMessage);
    }

    public void setActivity(ComponentName componentname)
    {
        mActivity = componentname;
    }

    public void setBitmapPath(String s)
    {
        mBitmapPath = s;
    }

    public void setCategories(Set set)
    {
        mCategories = cloneCategories(set);
    }

    public void setDisabledMessage(String s)
    {
        mDisabledMessage = s;
        mDisabledMessageResId = 0;
        mDisabledMessageResName = null;
    }

    public void setDisabledMessageResId(int i)
    {
        if(mDisabledMessageResId != i)
            mDisabledMessageResName = null;
        mDisabledMessageResId = i;
        mDisabledMessage = null;
    }

    public void setDisabledMessageResName(String s)
    {
        mDisabledMessageResName = s;
    }

    public void setIconPendingSave()
    {
        addFlags(2048);
    }

    public void setIconResName(String s)
    {
        mIconResName = s;
    }

    public void setIconResourceId(int i)
    {
        if(mIconResId != i)
            mIconResName = null;
        mIconResId = i;
    }

    public void setImplicitRank(int i)
    {
        mImplicitRank = mImplicitRank & 0x80000000 | 0x7fffffff & i;
    }

    public void setIntents(Intent aintent[])
        throws IllegalArgumentException
    {
        boolean flag = false;
        Preconditions.checkNotNull(aintent);
        if(aintent.length > 0)
            flag = true;
        Preconditions.checkArgument(flag);
        mIntents = cloneIntents(aintent);
        fixUpIntentExtras();
    }

    public void setRank(int i)
    {
        mRank = i;
    }

    public void setRankChanged()
    {
        mImplicitRank = mImplicitRank | 0x80000000;
    }

    public void setReturnedByServer()
    {
        addFlags(1024);
    }

    public void setTextResName(String s)
    {
        mTextResName = s;
    }

    public void setTimestamp(long l)
    {
        mLastChangedTimestamp = l;
    }

    public void setTitleResName(String s)
    {
        mTitleResName = s;
    }

    public String toInsecureString()
    {
        return toStringInner(false, true);
    }

    public String toString()
    {
        return toStringInner(true, false);
    }

    public void updateTimestamp()
    {
        mLastChangedTimestamp = System.currentTimeMillis();
    }

    public boolean usesQuota()
    {
        boolean flag = true;
        if(!hasFlags(1))
            flag = hasFlags(32);
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mUserId);
        parcel.writeString(mId);
        parcel.writeString(mPackageName);
        parcel.writeParcelable(mActivity, i);
        parcel.writeInt(mFlags);
        parcel.writeInt(mIconResId);
        parcel.writeLong(mLastChangedTimestamp);
        if(hasKeyFieldsOnly())
        {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeParcelable(mIcon, i);
        parcel.writeCharSequence(mTitle);
        parcel.writeInt(mTitleResId);
        parcel.writeCharSequence(mText);
        parcel.writeInt(mTextResId);
        parcel.writeCharSequence(mDisabledMessage);
        parcel.writeInt(mDisabledMessageResId);
        parcel.writeParcelableArray(mIntents, i);
        parcel.writeParcelableArray(mIntentPersistableExtrases, i);
        parcel.writeInt(mRank);
        parcel.writeParcelable(mExtras, i);
        parcel.writeString(mBitmapPath);
        parcel.writeString(mIconResName);
        parcel.writeString(mTitleResName);
        parcel.writeString(mTextResName);
        parcel.writeString(mDisabledMessageResName);
        if(mCategories != null)
        {
            int j = mCategories.size();
            parcel.writeInt(j);
            for(i = 0; i < j; i++)
                parcel.writeString((String)mCategories.valueAt(i));

        } else
        {
            parcel.writeInt(0);
        }
    }

    private static final String ANDROID_PACKAGE_NAME = "android";
    public static final int CLONE_REMOVE_FOR_CREATOR = 9;
    public static final int CLONE_REMOVE_FOR_LAUNCHER = 11;
    public static final int CLONE_REMOVE_FOR_LAUNCHER_APPROVAL = 10;
    private static final int CLONE_REMOVE_ICON = 1;
    private static final int CLONE_REMOVE_INTENT = 2;
    public static final int CLONE_REMOVE_NON_KEY_INFO = 4;
    public static final int CLONE_REMOVE_RES_NAMES = 8;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ShortcutInfo createFromParcel(Parcel parcel)
        {
            return new ShortcutInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ShortcutInfo[] newArray(int i)
        {
            return new ShortcutInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_ADAPTIVE_BITMAP = 512;
    public static final int FLAG_DISABLED = 64;
    public static final int FLAG_DYNAMIC = 1;
    public static final int FLAG_HAS_ICON_FILE = 8;
    public static final int FLAG_HAS_ICON_RES = 4;
    public static final int FLAG_ICON_FILE_PENDING_SAVE = 2048;
    public static final int FLAG_IMMUTABLE = 256;
    public static final int FLAG_KEY_FIELDS_ONLY = 16;
    public static final int FLAG_MANIFEST = 32;
    public static final int FLAG_PINNED = 2;
    public static final int FLAG_RETURNED_BY_SERVICE = 1024;
    public static final int FLAG_STRINGS_RESOLVED = 128;
    private static final int IMPLICIT_RANK_MASK = 0x7fffffff;
    private static final int RANK_CHANGED_BIT = 0x80000000;
    public static final int RANK_NOT_SET = 0x7fffffff;
    private static final String RES_TYPE_STRING = "string";
    public static final String SHORTCUT_CATEGORY_CONVERSATION = "android.shortcut.conversation";
    static final String TAG = "Shortcut";
    private ComponentName mActivity;
    private String mBitmapPath;
    private ArraySet mCategories;
    private CharSequence mDisabledMessage;
    private int mDisabledMessageResId;
    private String mDisabledMessageResName;
    private PersistableBundle mExtras;
    private int mFlags;
    private Icon mIcon;
    private int mIconResId;
    private String mIconResName;
    private final String mId;
    private int mImplicitRank;
    private PersistableBundle mIntentPersistableExtrases[];
    private Intent mIntents[];
    private long mLastChangedTimestamp;
    private final String mPackageName;
    private int mRank;
    private CharSequence mText;
    private int mTextResId;
    private String mTextResName;
    private CharSequence mTitle;
    private int mTitleResId;
    private String mTitleResName;
    private final int mUserId;

}
