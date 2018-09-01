// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.net.Uri;
import android.os.*;
import android.util.*;
import com.android.internal.util.XmlUtils;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import org.xmlpull.v1.*;

// Referenced classes of package android.content:
//            ComponentName, ClipData, ContentProvider, Context, 
//            ContentResolver, ClipDescription, IntentSender

public class Intent
    implements Parcelable, Cloneable
{
    public static interface CommandOptionHandler
    {

        public abstract boolean handleOption(String s, ShellCommand shellcommand);
    }

    public static final class FilterComparison
    {

        public boolean equals(Object obj)
        {
            if(obj instanceof FilterComparison)
            {
                obj = ((FilterComparison)obj).mIntent;
                return mIntent.filterEquals(((Intent) (obj)));
            } else
            {
                return false;
            }
        }

        public Intent getIntent()
        {
            return mIntent;
        }

        public int hashCode()
        {
            return mHashCode;
        }

        private final int mHashCode;
        private final Intent mIntent;

        public FilterComparison(Intent intent)
        {
            mIntent = intent;
            mHashCode = intent.filterHashCode();
        }
    }

    public static class ShortcutIconResource
        implements Parcelable
    {

        public static ShortcutIconResource fromContext(Context context, int i)
        {
            ShortcutIconResource shortcuticonresource = new ShortcutIconResource();
            shortcuticonresource.packageName = context.getPackageName();
            shortcuticonresource.resourceName = context.getResources().getResourceName(i);
            return shortcuticonresource;
        }

        public int describeContents()
        {
            return 0;
        }

        public String toString()
        {
            return resourceName;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(packageName);
            parcel.writeString(resourceName);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ShortcutIconResource createFromParcel(Parcel parcel)
            {
                ShortcutIconResource shortcuticonresource = new ShortcutIconResource();
                shortcuticonresource.packageName = parcel.readString();
                shortcuticonresource.resourceName = parcel.readString();
                return shortcuticonresource;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ShortcutIconResource[] newArray(int i)
            {
                return new ShortcutIconResource[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public String packageName;
        public String resourceName;


        public ShortcutIconResource()
        {
        }
    }


    public Intent()
    {
        mContentUserHint = -2;
    }

    public Intent(Context context, Class class1)
    {
        mContentUserHint = -2;
        mComponent = new ComponentName(context, class1);
    }

    public Intent(Intent intent)
    {
        this(intent, 0);
    }

    private Intent(Intent intent, int i)
    {
        mContentUserHint = -2;
        mAction = intent.mAction;
        mData = intent.mData;
        mType = intent.mType;
        mPackage = intent.mPackage;
        mComponent = intent.mComponent;
        if(intent.mCategories != null)
            mCategories = new ArraySet(intent.mCategories);
        if(i == 1) goto _L2; else goto _L1
_L1:
        mFlags = intent.mFlags;
        mContentUserHint = intent.mContentUserHint;
        mLaunchToken = intent.mLaunchToken;
        if(intent.mSourceBounds != null)
            mSourceBounds = new Rect(intent.mSourceBounds);
        if(intent.mSelector != null)
            mSelector = new Intent(intent.mSelector);
        if(i == 2) goto _L4; else goto _L3
_L3:
        if(intent.mExtras != null)
            mExtras = new Bundle(intent.mExtras);
        if(intent.mClipData != null)
            mClipData = new ClipData(intent.mClipData);
_L2:
        mSenderPackageName = intent.mSenderPackageName;
        mMiuiFlags = intent.mMiuiFlags;
        return;
_L4:
        if(intent.mExtras != null && intent.mExtras.maybeIsEmpty() ^ true)
            mExtras = Bundle.STRIPPED;
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected Intent(Parcel parcel)
    {
        mContentUserHint = -2;
        readFromParcel(parcel);
    }

    public Intent(String s)
    {
        mContentUserHint = -2;
        setAction(s);
    }

    public Intent(String s, Uri uri)
    {
        mContentUserHint = -2;
        setAction(s);
        mData = uri;
    }

    public Intent(String s, Uri uri, Context context, Class class1)
    {
        mContentUserHint = -2;
        setAction(s);
        mData = uri;
        mComponent = new ComponentName(context, class1);
    }

    public static Intent createChooser(Intent intent, CharSequence charsequence)
    {
        return createChooser(intent, charsequence, null);
    }

    public static Intent createChooser(Intent intent, CharSequence charsequence, IntentSender intentsender)
    {
        Intent intent1 = new Intent("android.intent.action.CHOOSER");
        intent1.putExtra("android.intent.extra.INTENT", intent);
        if(charsequence != null)
            intent1.putExtra("android.intent.extra.TITLE", charsequence);
        if(intentsender != null)
            intent1.putExtra("android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER", intentsender);
        int i = intent.getFlags() & 0xc3;
        if(i != 0)
        {
            intentsender = intent.getClipData();
            charsequence = intentsender;
            if(intentsender == null)
            {
                charsequence = intentsender;
                if(intent.getData() != null)
                {
                    intentsender = new ClipData.Item(intent.getData());
                    if(intent.getType() != null)
                    {
                        charsequence = new String[1];
                        charsequence[0] = intent.getType();
                        intent = charsequence;
                    } else
                    {
                        intent = new String[0];
                    }
                    charsequence = new ClipData(null, intent, intentsender);
                }
            }
            if(charsequence != null)
            {
                intent1.setClipData(charsequence);
                intent1.addFlags(i);
            }
        }
        return intent1;
    }

    public static Intent getIntent(String s)
        throws URISyntaxException
    {
        return parseUri(s, 0);
    }

    public static Intent getIntentOld(String s)
        throws URISyntaxException
    {
        return getIntentOld(s, 0);
    }

    private static Intent getIntentOld(String s, int i)
        throws URISyntaxException
    {
        int j;
        Object obj;
        char c1;
        int k1;
label0:
        {
            j = s.lastIndexOf('#');
            if(j < 0)
                break MISSING_BLOCK_LABEL_961;
            obj = null;
            boolean flag = false;
            k1 = j + 1;
            int j2 = k1;
            if(s.regionMatches(k1, "action(", 0, 7))
            {
                flag = true;
                k1 += 7;
                j2 = s.indexOf(')', k1);
                obj = s.substring(k1, j2);
                j2++;
            }
            obj = new Intent(((String) (obj)));
            k1 = j2;
            c1 = flag;
            if(!s.regionMatches(j2, "categories(", 0, 11))
                break label0;
            c1 = '\001';
            j2 += 11;
            int k;
            for(k1 = s.indexOf(')', j2); j2 < k1; j2 = k + 1)
            {
label1:
                {
                    int i3 = s.indexOf('!', j2);
                    if(i3 >= 0)
                    {
                        k = i3;
                        if(i3 <= k1)
                            break label1;
                    }
                    k = k1;
                }
                if(j2 < k)
                    ((Intent) (obj)).addCategory(s.substring(j2, k));
            }

            k1++;
        }
        int l = k1;
        if(s.regionMatches(k1, "type(", 0, 5))
        {
            c1 = '\001';
            int k2 = k1 + 5;
            l = s.indexOf(')', k2);
            obj.mType = s.substring(k2, l);
            l++;
        }
        int l2 = l;
        if(s.regionMatches(l, "launchFlags(", 0, 12))
        {
            c1 = '\001';
            l += 12;
            l2 = s.indexOf(')', l);
            obj.mFlags = Integer.decode(s.substring(l, l2)).intValue();
            if((i & 4) == 0)
                obj.mFlags = ((Intent) (obj)).mFlags & 0xffffff3c;
            l2++;
        }
        i = l2;
        if(s.regionMatches(l2, "component(", 0, 10))
        {
            c1 = '\001';
            i = l2 + 10;
            int i1 = s.indexOf(')', i);
            l2 = s.indexOf('!', i);
            if(l2 >= 0 && l2 < i1)
                obj.mComponent = new ComponentName(s.substring(i, l2), s.substring(l2 + 1, i1));
            i = i1 + 1;
        }
        if(!s.regionMatches(i, "extras(", 0, 7)) goto _L2; else goto _L1
_L1:
        int j1;
        boolean flag1;
        flag1 = true;
        int l1 = i + 7;
        j1 = s.indexOf(')', l1);
        i = l1;
        if(j1 == -1)
            throw new URISyntaxException(s, "EXTRA missing trailing ')'", l1);
          goto _L3
_L17:
        i++;
_L3:
        c1 = flag1;
        if(i >= j1) goto _L2; else goto _L4
_L4:
        String s1;
        String s2;
        int j3;
label2:
        {
            int i2 = s.indexOf('=', i);
            if(i2 <= i + 1 || i >= j1)
                throw new URISyntaxException(s, "EXTRA missing '='", i);
            c1 = s.charAt(i);
            s1 = s.substring(i + 1, i2);
            j3 = i2 + 1;
            i2 = s.indexOf('!', j3);
            if(i2 != -1)
            {
                i = i2;
                if(i2 < j1)
                    break label2;
            }
            i = j1;
        }
        if(j3 >= i)
            throw new URISyntaxException(s, "EXTRA missing '!'", j3);
        s2 = s.substring(j3, i);
        if(((Intent) (obj)).mExtras == null)
            obj.mExtras = new Bundle();
        c1;
        JVM INSTR lookupswitch 9: default 692
    //                   66: 790
    //                   83: 721
    //                   98: 807
    //                   99: 824
    //                   100: 845
    //                   102: 862
    //                   105: 879
    //                   108: 896
    //                   115: 913;
           goto _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L5:
        try
        {
            obj = JVM INSTR new #1348 <Class URISyntaxException>;
            ((URISyntaxException) (obj)).URISyntaxException(s, "EXTRA has unknown type", i);
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new URISyntaxException(s, "EXTRA value can't be parsed", i);
        }
_L7:
        ((Intent) (obj)).mExtras.putString(s1, Uri.decode(s2));
_L15:
        char c;
        c = s.charAt(i);
        if(c != ')')
            continue; /* Loop/switch isn't completed */
        c1 = flag1;
_L2:
        if(c1 != 0)
            obj.mData = Uri.parse(s.substring(0, j));
        else
            obj.mData = Uri.parse(s);
        s = ((String) (obj));
        if(((Intent) (obj)).mAction == null)
        {
            obj.mAction = "android.intent.action.VIEW";
            s = ((String) (obj));
        }
_L18:
        return s;
_L6:
        ((Intent) (obj)).mExtras.putBoolean(s1, Boolean.parseBoolean(s2));
          goto _L15
_L8:
        ((Intent) (obj)).mExtras.putByte(s1, Byte.parseByte(s2));
          goto _L15
_L9:
        ((Intent) (obj)).mExtras.putChar(s1, Uri.decode(s2).charAt(0));
          goto _L15
_L10:
        ((Intent) (obj)).mExtras.putDouble(s1, Double.parseDouble(s2));
          goto _L15
_L11:
        ((Intent) (obj)).mExtras.putFloat(s1, Float.parseFloat(s2));
          goto _L15
_L12:
        ((Intent) (obj)).mExtras.putInt(s1, Integer.parseInt(s2));
          goto _L15
_L13:
        ((Intent) (obj)).mExtras.putLong(s1, Long.parseLong(s2));
          goto _L15
_L14:
        ((Intent) (obj)).mExtras.putShort(s1, Short.parseShort(s2));
          goto _L15
        if(c == '!') goto _L17; else goto _L16
_L16:
        throw new URISyntaxException(s, "EXTRA missing '!'", i);
        s = new Intent("android.intent.action.VIEW", Uri.parse(s));
          goto _L18
    }

    public static boolean isAccessUriMode(int i)
    {
        boolean flag = false;
        if((i & 3) != 0)
            flag = true;
        return flag;
    }

    private static ClipData.Item makeClipItem(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, int i)
    {
        if(arraylist != null)
            arraylist = (Uri)arraylist.get(i);
        else
            arraylist = null;
        if(arraylist1 != null)
            arraylist1 = (CharSequence)arraylist1.get(i);
        else
            arraylist1 = null;
        if(arraylist2 != null)
            arraylist2 = (String)arraylist2.get(i);
        else
            arraylist2 = null;
        return new ClipData.Item(arraylist1, arraylist2, null, arraylist);
    }

    public static Intent makeMainActivity(ComponentName componentname)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(componentname);
        intent.addCategory("android.intent.category.LAUNCHER");
        return intent;
    }

    public static Intent makeMainSelectorActivity(String s, String s1)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        Intent intent1 = new Intent();
        intent1.setAction(s);
        intent1.addCategory(s1);
        intent.setSelector(intent1);
        return intent;
    }

    public static Intent makeRestartActivityTask(ComponentName componentname)
    {
        componentname = makeMainActivity(componentname);
        componentname.addFlags(0x10008000);
        return componentname;
    }

    public static String normalizeMimeType(String s)
    {
        if(s == null)
            return null;
        String s1 = s.trim().toLowerCase(Locale.ROOT);
        int i = s1.indexOf(';');
        s = s1;
        if(i != -1)
            s = s1.substring(0, i);
        return s;
    }

    public static Intent parseCommandArgs(ShellCommand shellcommand, CommandOptionHandler commandoptionhandler)
        throws URISyntaxException
    {
        Object obj;
        int i;
        Object obj1;
        Object obj2;
        Object obj3;
label0:
        {
            obj = new Intent();
            i = 0;
            obj1 = null;
            obj2 = null;
            obj3 = obj;
            Object obj4;
label1:
            do
label2:
                do
                {
                    String s;
label3:
                    {
label4:
                        {
label5:
                            {
label6:
                                {
label7:
                                    {
label8:
                                        {
label9:
                                            {
label10:
                                                {
                                                    obj4 = shellcommand.getNextOption();
                                                    if(obj4 == null)
                                                        break label0;
                                                    if(((String) (obj4)).equals("-a"))
                                                    {
                                                        ((Intent) (obj3)).setAction(shellcommand.getNextArgRequired());
                                                        if(obj3 == obj)
                                                            i = 1;
                                                        continue;
                                                    }
                                                    if(((String) (obj4)).equals("-d"))
                                                    {
                                                        obj4 = Uri.parse(shellcommand.getNextArgRequired());
                                                        obj1 = obj4;
                                                        if(obj3 == obj)
                                                        {
                                                            i = 1;
                                                            obj1 = obj4;
                                                        }
                                                        continue;
                                                    }
                                                    if(((String) (obj4)).equals("-t"))
                                                    {
                                                        obj4 = shellcommand.getNextArgRequired();
                                                        obj2 = obj4;
                                                        if(obj3 == obj)
                                                        {
                                                            i = 1;
                                                            obj2 = obj4;
                                                        }
                                                        continue;
                                                    }
                                                    if(((String) (obj4)).equals("-c"))
                                                    {
                                                        ((Intent) (obj3)).addCategory(shellcommand.getNextArgRequired());
                                                        if(obj3 == obj)
                                                            i = 1;
                                                        continue;
                                                    }
                                                    while(((String) (obj4)).equals("-e") || ((String) (obj4)).equals("--es")) 
                                                    {
                                                        ((Intent) (obj3)).putExtra(shellcommand.getNextArgRequired(), shellcommand.getNextArgRequired());
                                                        continue label2;
                                                    }
                                                    if(((String) (obj4)).equals("--esn"))
                                                    {
                                                        ((Intent) (obj3)).putExtra(shellcommand.getNextArgRequired(), (String)null);
                                                        continue;
                                                    }
                                                    if(((String) (obj4)).equals("--ei"))
                                                    {
                                                        ((Intent) (obj3)).putExtra(shellcommand.getNextArgRequired(), Integer.decode(shellcommand.getNextArgRequired()));
                                                        continue;
                                                    }
                                                    if(((String) (obj4)).equals("--eu"))
                                                    {
                                                        ((Intent) (obj3)).putExtra(shellcommand.getNextArgRequired(), Uri.parse(shellcommand.getNextArgRequired()));
                                                        continue;
                                                    }
                                                    if(((String) (obj4)).equals("--ecn"))
                                                    {
                                                        s = shellcommand.getNextArgRequired();
                                                        String s1 = shellcommand.getNextArgRequired();
                                                        obj4 = ComponentName.unflattenFromString(s1);
                                                        if(obj4 == null)
                                                            throw new IllegalArgumentException((new StringBuilder()).append("Bad component name: ").append(s1).toString());
                                                    } else
                                                    {
                                                        Serializable serializable;
                                                        if(((String) (obj4)).equals("--eia"))
                                                        {
                                                            obj4 = shellcommand.getNextArgRequired();
                                                            s = shellcommand.getNextArgRequired().split(",");
                                                            serializable = new int[s.length];
                                                            for(int j = 0; j < s.length; j++)
                                                                serializable[j] = Integer.decode(s[j]).intValue();

                                                        } else
                                                        {
                                                            if(((String) (obj4)).equals("--eial"))
                                                            {
                                                                s = shellcommand.getNextArgRequired();
                                                                serializable = shellcommand.getNextArgRequired().split(",");
                                                                obj4 = new ArrayList(serializable.length);
                                                                for(int k = 0; k < serializable.length; k++)
                                                                    ((ArrayList) (obj4)).add(Integer.decode(((String) (serializable[k]))));

                                                            } else
                                                            {
                                                                if(((String) (obj4)).equals("--el"))
                                                                {
                                                                    ((Intent) (obj3)).putExtra(shellcommand.getNextArgRequired(), Long.valueOf(shellcommand.getNextArgRequired()));
                                                                    continue;
                                                                }
                                                                if(((String) (obj4)).equals("--ela"))
                                                                {
                                                                    s = shellcommand.getNextArgRequired();
                                                                    obj4 = shellcommand.getNextArgRequired().split(",");
                                                                    serializable = new long[obj4.length];
                                                                    for(i = 0; i < obj4.length; i++)
                                                                        serializable[i] = Long.valueOf(obj4[i]).longValue();

                                                                } else
                                                                {
                                                                    if(((String) (obj4)).equals("--elal"))
                                                                    {
                                                                        obj4 = shellcommand.getNextArgRequired();
                                                                        serializable = shellcommand.getNextArgRequired().split(",");
                                                                        s = new ArrayList(serializable.length);
                                                                        for(i = 0; i < serializable.length; i++)
                                                                            s.add(Long.valueOf(((String) (serializable[i]))));

                                                                    } else
                                                                    {
                                                                        if(((String) (obj4)).equals("--ef"))
                                                                        {
                                                                            ((Intent) (obj3)).putExtra(shellcommand.getNextArgRequired(), Float.valueOf(shellcommand.getNextArgRequired()));
                                                                            i = 1;
                                                                            continue;
                                                                        }
                                                                        if(((String) (obj4)).equals("--efa"))
                                                                        {
                                                                            obj4 = shellcommand.getNextArgRequired();
                                                                            serializable = shellcommand.getNextArgRequired().split(",");
                                                                            s = new float[serializable.length];
                                                                            for(i = 0; i < serializable.length; i++)
                                                                                s[i] = Float.valueOf(((String) (serializable[i]))).floatValue();

                                                                        } else
                                                                        {
                                                                            if(((String) (obj4)).equals("--efal"))
                                                                            {
                                                                                obj4 = shellcommand.getNextArgRequired();
                                                                                s = shellcommand.getNextArgRequired().split(",");
                                                                                serializable = new ArrayList(s.length);
                                                                                for(i = 0; i < s.length; i++)
                                                                                    serializable.add(Float.valueOf(s[i]));

                                                                            } else
                                                                            {
                                                                                if(((String) (obj4)).equals("--esa"))
                                                                                {
                                                                                    ((Intent) (obj3)).putExtra(shellcommand.getNextArgRequired(), shellcommand.getNextArgRequired().split("(?<!\\\\),"));
                                                                                    i = 1;
                                                                                    continue;
                                                                                }
                                                                                if(((String) (obj4)).equals("--esal"))
                                                                                {
                                                                                    s = shellcommand.getNextArgRequired();
                                                                                    obj4 = shellcommand.getNextArgRequired().split("(?<!\\\\),");
                                                                                    serializable = new ArrayList(obj4.length);
                                                                                    for(i = 0; i < obj4.length; i++)
                                                                                        serializable.add(obj4[i]);

                                                                                } else
                                                                                {
                                                                                    if(((String) (obj4)).equals("--ez"))
                                                                                    {
                                                                                        s = shellcommand.getNextArgRequired();
                                                                                        obj4 = shellcommand.getNextArgRequired().toLowerCase();
                                                                                        boolean flag1;
                                                                                        if("true".equals(obj4) || "t".equals(obj4))
                                                                                            flag1 = true;
                                                                                        else
                                                                                        if("false".equals(obj4) || "f".equals(obj4))
                                                                                        {
                                                                                            flag1 = false;
                                                                                        } else
                                                                                        {
                                                                                            int l;
                                                                                            try
                                                                                            {
                                                                                                l = Integer.decode(((String) (obj4))).intValue();
                                                                                            }
                                                                                            // Misplaced declaration of an exception variable
                                                                                            catch(ShellCommand shellcommand)
                                                                                            {
                                                                                                throw new IllegalArgumentException((new StringBuilder()).append("Invalid boolean value: ").append(((String) (obj4))).toString());
                                                                                            }
                                                                                            if(l != 0)
                                                                                                flag1 = true;
                                                                                            else
                                                                                                flag1 = false;
                                                                                        }
                                                                                        ((Intent) (obj3)).putExtra(s, flag1);
                                                                                        continue;
                                                                                    }
                                                                                    if(((String) (obj4)).equals("-n"))
                                                                                    {
                                                                                        obj4 = shellcommand.getNextArgRequired();
                                                                                        s = ComponentName.unflattenFromString(((String) (obj4)));
                                                                                        if(s == null)
                                                                                            throw new IllegalArgumentException((new StringBuilder()).append("Bad component name: ").append(((String) (obj4))).toString());
                                                                                    } else
                                                                                    {
                                                                                        if(((String) (obj4)).equals("-p"))
                                                                                        {
                                                                                            ((Intent) (obj3)).setPackage(shellcommand.getNextArgRequired());
                                                                                            if(obj3 == obj)
                                                                                                i = 1;
                                                                                        } else
                                                                                        if(((String) (obj4)).equals("-f"))
                                                                                            ((Intent) (obj3)).setFlags(Integer.decode(shellcommand.getNextArgRequired()).intValue());
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--grant-read-uri-permission"))
                                                                                            ((Intent) (obj3)).addFlags(1);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--grant-write-uri-permission"))
                                                                                            ((Intent) (obj3)).addFlags(2);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--grant-persistable-uri-permission"))
                                                                                            ((Intent) (obj3)).addFlags(64);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--grant-prefix-uri-permission"))
                                                                                            ((Intent) (obj3)).addFlags(128);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--exclude-stopped-packages"))
                                                                                            ((Intent) (obj3)).addFlags(16);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--include-stopped-packages"))
                                                                                            ((Intent) (obj3)).addFlags(32);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--debug-log-resolution"))
                                                                                            ((Intent) (obj3)).addFlags(8);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-brought-to-front"))
                                                                                            ((Intent) (obj3)).addFlags(0x400000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-clear-top"))
                                                                                            ((Intent) (obj3)).addFlags(0x4000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-clear-when-task-reset"))
                                                                                            ((Intent) (obj3)).addFlags(0x80000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-exclude-from-recents"))
                                                                                            ((Intent) (obj3)).addFlags(0x800000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-launched-from-history"))
                                                                                            ((Intent) (obj3)).addFlags(0x100000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-multiple-task"))
                                                                                            ((Intent) (obj3)).addFlags(0x8000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-no-animation"))
                                                                                            ((Intent) (obj3)).addFlags(0x10000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-no-history"))
                                                                                            ((Intent) (obj3)).addFlags(0x40000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-no-user-action"))
                                                                                            ((Intent) (obj3)).addFlags(0x40000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-previous-is-top"))
                                                                                            ((Intent) (obj3)).addFlags(0x1000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-reorder-to-front"))
                                                                                            ((Intent) (obj3)).addFlags(0x20000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-reset-task-if-needed"))
                                                                                            ((Intent) (obj3)).addFlags(0x200000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-single-top"))
                                                                                            ((Intent) (obj3)).addFlags(0x20000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-clear-task"))
                                                                                            ((Intent) (obj3)).addFlags(32768);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--activity-task-on-home"))
                                                                                            ((Intent) (obj3)).addFlags(16384);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--receiver-registered-only"))
                                                                                            ((Intent) (obj3)).addFlags(0x40000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--receiver-replace-pending"))
                                                                                            ((Intent) (obj3)).addFlags(0x20000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--receiver-foreground"))
                                                                                            ((Intent) (obj3)).addFlags(0x10000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--receiver-no-abort"))
                                                                                            ((Intent) (obj3)).addFlags(0x8000000);
                                                                                        else
                                                                                        if(((String) (obj4)).equals("--receiver-include-background"))
                                                                                        {
                                                                                            ((Intent) (obj3)).addFlags(0x1000000);
                                                                                        } else
                                                                                        {
                                                                                            if(!((String) (obj4)).equals("--selector"))
                                                                                                continue label1;
                                                                                            ((Intent) (obj3)).setDataAndType(((Uri) (obj1)), ((String) (obj2)));
                                                                                            obj3 = new Intent();
                                                                                        }
                                                                                        continue;
                                                                                    }
                                                                                    break label3;
                                                                                }
                                                                                break label4;
                                                                            }
                                                                            break label5;
                                                                        }
                                                                        break label6;
                                                                    }
                                                                    break label7;
                                                                }
                                                                break label8;
                                                            }
                                                            break label9;
                                                        }
                                                        break label10;
                                                    }
                                                    ((Intent) (obj3)).putExtra(s, ((Parcelable) (obj4)));
                                                    continue;
                                                }
                                                ((Intent) (obj3)).putExtra(((String) (obj4)), serializable);
                                                continue;
                                            }
                                            ((Intent) (obj3)).putExtra(s, ((Serializable) (obj4)));
                                            continue;
                                        }
                                        ((Intent) (obj3)).putExtra(s, serializable);
                                        i = 1;
                                        continue;
                                    }
                                    ((Intent) (obj3)).putExtra(((String) (obj4)), s);
                                    i = 1;
                                    continue;
                                }
                                ((Intent) (obj3)).putExtra(((String) (obj4)), s);
                                i = 1;
                                continue;
                            }
                            ((Intent) (obj3)).putExtra(((String) (obj4)), serializable);
                            i = 1;
                            continue;
                        }
                        ((Intent) (obj3)).putExtra(s, serializable);
                        i = 1;
                        continue;
                    }
                    ((Intent) (obj3)).setComponent(s);
                    if(obj3 == obj)
                        i = 1;
                } while(true);
            while(commandoptionhandler != null && commandoptionhandler.handleOption(((String) (obj4)), shellcommand));
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown option: ").append(((String) (obj4))).toString());
        }
        ((Intent) (obj3)).setDataAndType(((Uri) (obj1)), ((String) (obj2)));
        boolean flag;
        if(obj3 != obj)
            flag = true;
        else
            flag = false;
        if(flag)
            ((Intent) (obj)).setSelector(((Intent) (obj3)));
        else
            obj = obj3;
        commandoptionhandler = shellcommand.getNextArg();
        shellcommand = null;
        if(commandoptionhandler == null)
        {
            if(flag)
            {
                shellcommand = new Intent("android.intent.action.MAIN");
                shellcommand.addCategory("android.intent.category.LAUNCHER");
            }
        } else
        if(commandoptionhandler.indexOf(':') >= 0)
            shellcommand = parseUri(commandoptionhandler, 7);
        else
        if(commandoptionhandler.indexOf('/') >= 0)
        {
            shellcommand = new Intent("android.intent.action.MAIN");
            shellcommand.addCategory("android.intent.category.LAUNCHER");
            shellcommand.setComponent(ComponentName.unflattenFromString(commandoptionhandler));
        } else
        {
            shellcommand = new Intent("android.intent.action.MAIN");
            shellcommand.addCategory("android.intent.category.LAUNCHER");
            shellcommand.setPackage(commandoptionhandler);
        }
        if(shellcommand == null) goto _L2; else goto _L1
_L1:
        obj3 = ((Intent) (obj)).getExtras();
        ((Intent) (obj)).replaceExtras((Bundle)null);
        commandoptionhandler = shellcommand.getExtras();
        shellcommand.replaceExtras((Bundle)null);
        if(((Intent) (obj)).getAction() != null && shellcommand.getCategories() != null)
            for(obj2 = (new HashSet(shellcommand.getCategories())).iterator(); ((Iterator) (obj2)).hasNext(); shellcommand.removeCategory((String)((Iterator) (obj2)).next()));
        ((Intent) (obj)).fillIn(shellcommand, 72);
        if(obj3 != null) goto _L4; else goto _L3
_L3:
        shellcommand = commandoptionhandler;
_L6:
        ((Intent) (obj)).replaceExtras(shellcommand);
        i = 1;
_L2:
        if(i == 0)
            throw new IllegalArgumentException("No intent supplied");
        else
            return ((Intent) (obj));
_L4:
        shellcommand = ((ShellCommand) (obj3));
        if(commandoptionhandler != null)
        {
            commandoptionhandler.putAll(((Bundle) (obj3)));
            shellcommand = commandoptionhandler;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static Intent parseIntent(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        Intent intent = new Intent();
        TypedArray typedarray = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.Intent);
        intent.setAction(typedarray.getString(2));
        Object obj = typedarray.getString(3);
        String s = typedarray.getString(1);
        int i;
        if(obj != null)
            obj = Uri.parse(((String) (obj)));
        else
            obj = null;
        intent.setDataAndType(((Uri) (obj)), s);
        obj = typedarray.getString(0);
        s = typedarray.getString(4);
        if(obj != null && s != null)
            intent.setComponent(new ComponentName(((String) (obj)), s));
        typedarray.recycle();
        i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 1 || j == 3 && xmlpullparser.getDepth() <= i)
                break;
            if(j != 3 && j != 4)
            {
                obj = xmlpullparser.getName();
                if(((String) (obj)).equals("categories"))
                {
                    TypedArray typedarray1 = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.IntentCategory);
                    obj = typedarray1.getString(0);
                    typedarray1.recycle();
                    if(obj != null)
                        intent.addCategory(((String) (obj)));
                    XmlUtils.skipCurrentTag(xmlpullparser);
                } else
                if(((String) (obj)).equals("extra"))
                {
                    if(intent.mExtras == null)
                        intent.mExtras = new Bundle();
                    resources.parseBundleExtra("extra", attributeset, intent.mExtras);
                    XmlUtils.skipCurrentTag(xmlpullparser);
                } else
                {
                    XmlUtils.skipCurrentTag(xmlpullparser);
                }
            }
        } while(true);
        return intent;
    }

    public static Intent parseUri(String s, int i)
        throws URISyntaxException
    {
        int j;
        int k;
        boolean flag;
        j = 0;
        k = j;
        Object obj;
        IllegalArgumentException illegalargumentexception;
        try
        {
            flag = s.startsWith("android-app:");
        }
        catch(IndexOutOfBoundsException indexoutofboundsexception)
        {
            throw new URISyntaxException(s, "illegal Intent URI format", k);
        }
        if((i & 3) == 0)
            break MISSING_BLOCK_LABEL_111;
        k = j;
        if(s.startsWith("intent:") || !(flag ^ true))
            break MISSING_BLOCK_LABEL_111;
        k = j;
        obj = JVM INSTR new #2   <Class Intent>;
        k = j;
        ((Intent) (obj)).Intent("android.intent.action.VIEW");
        k = j;
        ((Intent) (obj)).setData(Uri.parse(s));
        return ((Intent) (obj));
        illegalargumentexception;
        k = j;
        obj = JVM INSTR new #1348 <Class URISyntaxException>;
        k = j;
        ((URISyntaxException) (obj)).URISyntaxException(s, illegalargumentexception.getMessage());
        k = j;
        throw obj;
        k = j;
        int l = s.lastIndexOf("#");
        if(l != -1)
            break MISSING_BLOCK_LABEL_153;
        j = l;
        if(flag)
            break MISSING_BLOCK_LABEL_187;
        k = l;
        return new Intent("android.intent.action.VIEW", Uri.parse(s));
        k = l;
        j = l;
        if(s.startsWith("#Intent;", l))
            break MISSING_BLOCK_LABEL_187;
        if(flag)
            break MISSING_BLOCK_LABEL_185;
        k = l;
        return getIntentOld(s, i);
        j = -1;
        k = j;
        Object obj2 = JVM INSTR new #2   <Class Intent>;
        k = j;
        ((Intent) (obj2)).Intent("android.intent.action.VIEW");
        boolean flag1;
        int i1;
        String s2;
        flag1 = false;
        i1 = 0;
        s2 = null;
        if(j < 0) goto _L2; else goto _L1
_L1:
        k = j;
        String s1 = s.substring(0, j);
        Object obj1;
        j += 8;
        obj1 = obj2;
_L31:
        if(j < 0) goto _L4; else goto _L3
_L3:
        k = j;
        if(!(s.startsWith("end", j) ^ true)) goto _L4; else goto _L5
_L5:
        k = j;
        int j1 = s.indexOf('=', j);
        int k1;
        k1 = j1;
        if(j1 < 0)
            k1 = j - 1;
        k = j;
        j1 = s.indexOf(';', j);
        if(k1 >= j1) goto _L7; else goto _L6
_L6:
        k = j;
        Object obj3 = Uri.decode(s.substring(k1 + 1, j1));
_L10:
        k = j;
        if(!s.startsWith("action=", j)) goto _L9; else goto _L8
_L8:
        k = j;
        ((Intent) (obj1)).setAction(((String) (obj3)));
        Object obj4;
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
        if(i1 == 0)
        {
            k = 1;
            obj4 = s2;
            obj3 = obj1;
            k1 = i1;
        }
_L11:
        j = j1 + 1;
        flag1 = k;
        i1 = k1;
        obj1 = obj3;
        s2 = ((String) (obj4));
        continue; /* Loop/switch isn't completed */
_L2:
        s1 = s;
        obj1 = obj2;
        continue; /* Loop/switch isn't completed */
_L7:
        obj3 = "";
          goto _L10
_L9:
        k = j;
        if(!s.startsWith("category=", j))
            break MISSING_BLOCK_LABEL_451;
        k = j;
        ((Intent) (obj1)).addCategory(((String) (obj3)));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("type=", j))
            break MISSING_BLOCK_LABEL_491;
        k = j;
        obj1.mType = ((String) (obj3));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("launchFlags=", j))
            break MISSING_BLOCK_LABEL_574;
        k = j;
        obj1.mFlags = Integer.decode(((String) (obj3))).intValue();
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
        if((i & 4) != 0) goto _L11; else goto _L12
_L12:
        k = j;
        obj1.mFlags = ((Intent) (obj1)).mFlags & 0xffffff3c;
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("package=", j))
            break MISSING_BLOCK_LABEL_614;
        k = j;
        obj1.mPackage = ((String) (obj3));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("component=", j))
            break MISSING_BLOCK_LABEL_657;
        k = j;
        obj1.mComponent = ComponentName.unflattenFromString(((String) (obj3)));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("scheme=", j))
            break MISSING_BLOCK_LABEL_751;
        if(i1 == 0)
            break MISSING_BLOCK_LABEL_733;
        k = j;
        obj4 = JVM INSTR new #1600 <Class StringBuilder>;
        k = j;
        ((StringBuilder) (obj4)).StringBuilder();
        k = j;
        obj1.mData = Uri.parse(((StringBuilder) (obj4)).append(((String) (obj3))).append(":").toString());
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        obj4 = obj3;
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
          goto _L11
        k = j;
        if(!s.startsWith("sourceBounds=", j))
            break MISSING_BLOCK_LABEL_794;
        k = j;
        obj1.mSourceBounds = Rect.unflattenFromString(((String) (obj3)));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        if(j1 != j + 3)
            break MISSING_BLOCK_LABEL_839;
        k = j;
        if(!s.startsWith("SEL", j))
            break MISSING_BLOCK_LABEL_839;
        k = j;
        obj3 = new Intent();
        k1 = 1;
        k = ((flag1) ? 1 : 0);
        obj4 = s2;
          goto _L11
        k = j;
        obj4 = Uri.decode(s.substring(j + 2, k1));
        k = j;
        if(((Intent) (obj1)).mExtras != null)
            break MISSING_BLOCK_LABEL_888;
        k = j;
        Bundle bundle = JVM INSTR new #1265 <Class Bundle>;
        k = j;
        bundle.Bundle();
        k = j;
        obj1.mExtras = bundle;
        k = j;
        bundle = ((Intent) (obj1)).mExtras;
        k = j;
        if(!s.startsWith("S.", j))
            break MISSING_BLOCK_LABEL_939;
        k = j;
        bundle.putString(((String) (obj4)), ((String) (obj3)));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("B.", j))
            break MISSING_BLOCK_LABEL_984;
        k = j;
        bundle.putBoolean(((String) (obj4)), Boolean.parseBoolean(((String) (obj3))));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("b.", j))
            break MISSING_BLOCK_LABEL_1029;
        k = j;
        bundle.putByte(((String) (obj4)), Byte.parseByte(((String) (obj3))));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("c.", j))
            break MISSING_BLOCK_LABEL_1075;
        k = j;
        bundle.putChar(((String) (obj4)), ((String) (obj3)).charAt(0));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("d.", j))
            break MISSING_BLOCK_LABEL_1120;
        k = j;
        bundle.putDouble(((String) (obj4)), Double.parseDouble(((String) (obj3))));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("f.", j))
            break MISSING_BLOCK_LABEL_1165;
        k = j;
        bundle.putFloat(((String) (obj4)), Float.parseFloat(((String) (obj3))));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("i.", j))
            break MISSING_BLOCK_LABEL_1210;
        k = j;
        bundle.putInt(((String) (obj4)), Integer.parseInt(((String) (obj3))));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("l.", j))
            break MISSING_BLOCK_LABEL_1255;
        k = j;
        bundle.putLong(((String) (obj4)), Long.parseLong(((String) (obj3))));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        if(!s.startsWith("s.", j))
            break MISSING_BLOCK_LABEL_1300;
        k = j;
        bundle.putShort(((String) (obj4)), Short.parseShort(((String) (obj3))));
        k = ((flag1) ? 1 : 0);
        k1 = i1;
        obj3 = obj1;
        obj4 = s2;
          goto _L11
        k = j;
        obj1 = JVM INSTR new #1348 <Class URISyntaxException>;
        k = j;
        ((URISyntaxException) (obj1)).URISyntaxException(s, "unknown EXTRA type", j);
        k = j;
        throw obj1;
_L4:
        if(i1 == 0) goto _L14; else goto _L13
_L13:
        k = j;
        obj3 = obj2;
        if(((Intent) (obj2)).mPackage != null)
            break MISSING_BLOCK_LABEL_1356;
        k = j;
        ((Intent) (obj2)).setSelector(((Intent) (obj1)));
        obj3 = obj2;
_L29:
        if(s1 == null) goto _L16; else goto _L15
_L15:
        k = j;
        if(!s1.startsWith("intent:")) goto _L18; else goto _L17
_L17:
        k = j;
        s1 = s1.substring(7);
        obj1 = s1;
        if(s2 == null)
            break MISSING_BLOCK_LABEL_1432;
        k = j;
        obj1 = JVM INSTR new #1600 <Class StringBuilder>;
        k = j;
        ((StringBuilder) (obj1)).StringBuilder();
        k = j;
        obj1 = ((StringBuilder) (obj1)).append(s2).append(':').append(s1).toString();
_L21:
        k = j;
        i = ((String) (obj1)).length();
        if(i <= 0) goto _L16; else goto _L19
_L19:
        k = j;
        obj3.mData = Uri.parse(((String) (obj1)));
_L16:
        return ((Intent) (obj3));
_L18:
        k = j;
        obj1 = s1;
        if(!s1.startsWith("android-app:")) goto _L21; else goto _L20
_L20:
        k = j;
        if(s1.charAt(12) != '/')
            break MISSING_BLOCK_LABEL_1858;
        k = j;
        if(s1.charAt(13) != '/')
            break MISSING_BLOCK_LABEL_1858;
        k = j;
        i1 = s1.indexOf('/', 14);
        if(i1 >= 0)
            break MISSING_BLOCK_LABEL_1559;
        k = j;
        obj3.mPackage = s1.substring(14);
        if(flag1)
            break MISSING_BLOCK_LABEL_1551;
        k = j;
        ((Intent) (obj3)).setAction("android.intent.action.MAIN");
        obj1 = "";
          goto _L21
        obj2 = null;
        k = j;
        obj3.mPackage = s1.substring(14, i1);
        k = j;
        obj1 = obj2;
        i = i1;
        if(i1 + 1 >= s1.length()) goto _L23; else goto _L22
_L22:
        k = j;
        k1 = s1.indexOf('/', i1 + 1);
        if(k1 < 0) goto _L25; else goto _L24
_L24:
        k = j;
        obj4 = s1.substring(i1 + 1, k1);
        i1 = k1;
        k = j;
        obj1 = obj2;
        i = i1;
        s2 = ((String) (obj4));
        if(k1 >= s1.length()) goto _L23; else goto _L26
_L26:
        k = j;
        j1 = s1.indexOf('/', k1 + 1);
        obj1 = obj2;
        i = i1;
        s2 = ((String) (obj4));
        if(j1 < 0) goto _L23; else goto _L27
_L27:
        k = j;
        obj1 = s1.substring(k1 + 1, j1);
        i = j1;
        s2 = ((String) (obj4));
_L23:
        if(s2 != null)
            break MISSING_BLOCK_LABEL_1765;
        if(flag1)
            break MISSING_BLOCK_LABEL_1734;
        k = j;
        ((Intent) (obj3)).setAction("android.intent.action.MAIN");
        obj1 = "";
          goto _L21
_L25:
        k = j;
        s2 = s1.substring(i1 + 1);
        obj1 = obj2;
        i = i1;
          goto _L23
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_1807;
        k = j;
        obj1 = JVM INSTR new #1600 <Class StringBuilder>;
        k = j;
        ((StringBuilder) (obj1)).StringBuilder();
        k = j;
        obj1 = ((StringBuilder) (obj1)).append(s2).append(":").toString();
          goto _L21
        k = j;
        obj2 = JVM INSTR new #1600 <Class StringBuilder>;
        k = j;
        ((StringBuilder) (obj2)).StringBuilder();
        k = j;
        obj1 = ((StringBuilder) (obj2)).append(s2).append("://").append(((String) (obj1))).append(s1.substring(i)).toString();
          goto _L21
        obj1 = "";
          goto _L21
        IllegalArgumentException illegalargumentexception1;
        illegalargumentexception1;
        k = j;
        obj1 = JVM INSTR new #1348 <Class URISyntaxException>;
        k = j;
        ((URISyntaxException) (obj1)).URISyntaxException(s, illegalargumentexception1.getMessage());
        k = j;
        throw obj1;
_L14:
        obj3 = obj1;
        if(true) goto _L29; else goto _L28
_L28:
        if(true) goto _L31; else goto _L30
_L30:
    }

    public static void printIntentArgsHelp(PrintWriter printwriter, String s)
    {
        int i = 0;
        String as[] = new String[47];
        as[0] = "<INTENT> specifications include these flags and arguments:";
        as[1] = "    [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]";
        as[2] = "    [-c <CATEGORY> [-c <CATEGORY>] ...]";
        as[3] = "    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]";
        as[4] = "    [--esn <EXTRA_KEY> ...]";
        as[5] = "    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]";
        as[6] = "    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]";
        as[7] = "    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]";
        as[8] = "    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]";
        as[9] = "    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]";
        as[10] = "    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]";
        as[11] = "    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]";
        as[12] = "        (mutiple extras passed as Integer[])";
        as[13] = "    [--eial <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]";
        as[14] = "        (mutiple extras passed as List<Integer>)";
        as[15] = "    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]";
        as[16] = "        (mutiple extras passed as Long[])";
        as[17] = "    [--elal <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]";
        as[18] = "        (mutiple extras passed as List<Long>)";
        as[19] = "    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]";
        as[20] = "        (mutiple extras passed as Float[])";
        as[21] = "    [--efal <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]";
        as[22] = "        (mutiple extras passed as List<Float>)";
        as[23] = "    [--esa <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]";
        as[24] = "        (mutiple extras passed as String[]; to embed a comma into a string,";
        as[25] = "         escape it using \"\\,\")";
        as[26] = "    [--esal <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]";
        as[27] = "        (mutiple extras passed as List<String>; to embed a comma into a string,";
        as[28] = "         escape it using \"\\,\")";
        as[29] = "    [-f <FLAG>]";
        as[30] = "    [--grant-read-uri-permission] [--grant-write-uri-permission]";
        as[31] = "    [--grant-persistable-uri-permission] [--grant-prefix-uri-permission]";
        as[32] = "    [--debug-log-resolution] [--exclude-stopped-packages]";
        as[33] = "    [--include-stopped-packages]";
        as[34] = "    [--activity-brought-to-front] [--activity-clear-top]";
        as[35] = "    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]";
        as[36] = "    [--activity-launched-from-history] [--activity-multiple-task]";
        as[37] = "    [--activity-no-animation] [--activity-no-history]";
        as[38] = "    [--activity-no-user-action] [--activity-previous-is-top]";
        as[39] = "    [--activity-reorder-to-front] [--activity-reset-task-if-needed]";
        as[40] = "    [--activity-single-top] [--activity-clear-task]";
        as[41] = "    [--activity-task-on-home]";
        as[42] = "    [--receiver-registered-only] [--receiver-replace-pending]";
        as[43] = "    [--receiver-foreground] [--receiver-no-abort]";
        as[44] = "    [--receiver-include-background]";
        as[45] = "    [--selector]";
        as[46] = "    [<URI> | <PACKAGE> | <COMPONENT>]";
        for(int j = as.length; i < j; i++)
        {
            String s1 = as[i];
            printwriter.print(s);
            printwriter.println(s1);
        }

    }

    public static Intent restoreFromXml(XmlPullParser xmlpullparser)
        throws IOException, XmlPullParserException
    {
        Intent intent = new Intent();
        int i = xmlpullparser.getDepth();
        int j = xmlpullparser.getAttributeCount() - 1;
        while(j >= 0) 
        {
            String s = xmlpullparser.getAttributeName(j);
            String s2 = xmlpullparser.getAttributeValue(j);
            if("action".equals(s))
                intent.setAction(s2);
            else
            if("data".equals(s))
                intent.setData(Uri.parse(s2));
            else
            if("type".equals(s))
                intent.setType(s2);
            else
            if("component".equals(s))
                intent.setComponent(ComponentName.unflattenFromString(s2));
            else
            if("flags".equals(s))
                intent.setFlags(Integer.parseInt(s2, 16));
            else
                Log.e("Intent", (new StringBuilder()).append("restoreFromXml: unknown attribute=").append(s).toString());
            j--;
        }
        do
        {
label0:
            do
            {
label1:
                {
                    int k;
                    do
                    {
                        k = xmlpullparser.next();
                        if(k == 1 || k == 3 && xmlpullparser.getDepth() >= i)
                            break label1;
                    } while(k != 2);
                    s1 = xmlpullparser.getName();
                    if(!"categories".equals(s1))
                        break label0;
                    k = xmlpullparser.getAttributeCount() - 1;
                    while(k >= 0) 
                    {
                        intent.addCategory(xmlpullparser.getAttributeValue(k));
                        k--;
                    }
                }
            } while(true);
            if(true)
            {
                Log.w("Intent", (new StringBuilder()).append("restoreFromXml: unknown name=").append(s1).toString());
                XmlUtils.skipCurrentTag(xmlpullparser);
            } else
            {
                return intent;
            }
        } while(true);
    }

    private void toUriFragment(StringBuilder stringbuilder, String s, String s1, String s2, int i)
    {
        StringBuilder stringbuilder1 = new StringBuilder(128);
        toUriInner(stringbuilder1, s, s1, s2, i);
        if(mSelector != null)
        {
            stringbuilder1.append("SEL;");
            s1 = mSelector;
            if(mSelector.mData != null)
                s = mSelector.mData.getScheme();
            else
                s = null;
            s1.toUriInner(stringbuilder1, s, null, null, i);
        }
        if(stringbuilder1.length() > 0)
        {
            stringbuilder.append("#Intent;");
            stringbuilder.append(stringbuilder1);
            stringbuilder.append("end");
        }
    }

    private void toUriInner(StringBuilder stringbuilder, String s, String s1, String s2, int i)
    {
        if(s != null)
            stringbuilder.append("scheme=").append(s).append(';');
        if(mAction != null && mAction.equals(s1) ^ true)
            stringbuilder.append("action=").append(Uri.encode(mAction)).append(';');
        if(mCategories != null)
            for(i = 0; i < mCategories.size(); i++)
                stringbuilder.append("category=").append(Uri.encode((String)mCategories.valueAt(i))).append(';');

        if(mType != null)
            stringbuilder.append("type=").append(Uri.encode(mType, "/")).append(';');
        if(mFlags != 0)
            stringbuilder.append("launchFlags=0x").append(Integer.toHexString(mFlags)).append(';');
        if(mPackage != null && mPackage.equals(s2) ^ true)
            stringbuilder.append("package=").append(Uri.encode(mPackage)).append(';');
        if(mComponent != null)
            stringbuilder.append("component=").append(Uri.encode(mComponent.flattenToShortString(), "/")).append(';');
        if(mSourceBounds != null)
            stringbuilder.append("sourceBounds=").append(Uri.encode(mSourceBounds.flattenToString())).append(';');
        if(mExtras == null) goto _L2; else goto _L1
_L1:
        s1 = mExtras.keySet().iterator();
_L4:
        if(s1.hasNext())
        {
            s2 = (String)s1.next();
            s = ((String) (mExtras.get(s2)));
            int j;
            if(s instanceof String)
            {
                i = 83;
                j = i;
            } else
            if(s instanceof Boolean)
            {
                i = 66;
                j = i;
            } else
            if(s instanceof Byte)
            {
                i = 98;
                j = i;
            } else
            if(s instanceof Character)
            {
                i = 99;
                j = i;
            } else
            if(s instanceof Double)
            {
                i = 100;
                j = i;
            } else
            if(s instanceof Float)
            {
                i = 102;
                j = i;
            } else
            if(s instanceof Integer)
            {
                i = 105;
                j = i;
            } else
            if(s instanceof Long)
            {
                i = 108;
                j = i;
            } else
            if(s instanceof Short)
            {
                i = 115;
                j = i;
            } else
            {
                i = 0;
                j = i;
            }
            if(j != 0)
            {
                stringbuilder.append(j);
                stringbuilder.append('.');
                stringbuilder.append(Uri.encode(s2));
                stringbuilder.append('=');
                stringbuilder.append(Uri.encode(s.toString()));
                stringbuilder.append(';');
            }
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Intent addCategory(String s)
    {
        if(mCategories == null)
            mCategories = new ArraySet();
        mCategories.add(s.intern());
        return this;
    }

    public Intent addFlags(int i)
    {
        mFlags = mFlags | i;
        return this;
    }

    public Intent addMiuiFlags(int i)
    {
        mMiuiFlags = mMiuiFlags | i;
        return this;
    }

    public boolean canStripForHistory()
    {
label0:
        {
            boolean flag = true;
            boolean flag1;
            if(mExtras != null)
            {
                flag1 = flag;
                if(mExtras.isParcelled())
                    break label0;
            }
            if(mClipData != null)
                flag1 = flag;
            else
                flag1 = false;
        }
        return flag1;
    }

    public Object clone()
    {
        return new Intent(this);
    }

    public Intent cloneFilter()
    {
        return new Intent(this, 1);
    }

    public int describeContents()
    {
        int i;
        if(mExtras != null)
            i = mExtras.describeContents();
        else
            i = 0;
        return i;
    }

    public int fillIn(Intent intent, int i)
    {
        int k;
        int l;
label0:
        {
            boolean flag = false;
            k = 0;
            l = ((flag) ? 1 : 0);
            if(intent.mAction == null)
                break label0;
            if(mAction != null)
            {
                l = ((flag) ? 1 : 0);
                if((i & 1) == 0)
                    break label0;
            }
            mAction = intent.mAction;
            l = 1;
        }
        if(intent.mData != null) goto _L2; else goto _L1
_L1:
        int j;
        int i1;
        i1 = l;
        j = k;
        if(intent.mType == null) goto _L3; else goto _L2
_L2:
        if(mData != null || mType != null) goto _L5; else goto _L4
_L4:
        mData = intent.mData;
        mType = intent.mType;
        i1 = l | 2;
        j = 1;
_L3:
label1:
        {
            l = i1;
            if(intent.mCategories == null)
                break label1;
            if(mCategories != null)
            {
                l = i1;
                if((i & 4) == 0)
                    break label1;
            }
            if(intent.mCategories != null)
                mCategories = new ArraySet(intent.mCategories);
            l = i1 | 4;
        }
label2:
        {
            k = l;
            if(intent.mPackage == null)
                break label2;
            if(mPackage != null)
            {
                k = l;
                if((i & 0x10) == 0)
                    break label2;
            }
            k = l;
            if(mSelector == null)
            {
                mPackage = intent.mPackage;
                k = l | 0x10;
            }
        }
label3:
        {
            i1 = k;
            if(intent.mSelector != null)
            {
                i1 = k;
                if((i & 0x40) != 0)
                {
                    i1 = k;
                    if(mPackage == null)
                    {
                        mSelector = new Intent(intent.mSelector);
                        mPackage = null;
                        i1 = k | 0x40;
                    }
                }
            }
            k = i1;
            l = j;
            if(intent.mClipData == null)
                break label3;
            if(mClipData != null)
            {
                k = i1;
                l = j;
                if((i & 0x80) == 0)
                    break label3;
            }
            mClipData = intent.mClipData;
            k = i1 | 0x80;
            l = 1;
        }
label4:
        {
            j = k;
            if(intent.mComponent != null)
            {
                j = k;
                if((i & 8) != 0)
                {
                    mComponent = intent.mComponent;
                    j = k | 8;
                }
            }
            mFlags = mFlags | intent.mFlags;
            i1 = j;
            if(intent.mSourceBounds == null)
                break label4;
            if(mSourceBounds != null)
            {
                i1 = j;
                if((i & 0x20) == 0)
                    break label4;
            }
            mSourceBounds = new Rect(intent.mSourceBounds);
            i1 = j | 0x20;
        }
        if(mExtras != null) goto _L7; else goto _L6
_L6:
        i = l;
        if(intent.mExtras != null)
        {
            mExtras = new Bundle(intent.mExtras);
            i = 1;
        }
_L9:
        if(i != 0 && mContentUserHint == -2 && intent.mContentUserHint != -2)
            mContentUserHint = intent.mContentUserHint;
        return i1;
_L5:
        i1 = l;
        j = k;
        if((i & 2) == 0) goto _L3; else goto _L4
_L7:
        i = l;
        if(intent.mExtras == null) goto _L9; else goto _L8
_L8:
        Bundle bundle = JVM INSTR new #1265 <Class Bundle>;
        bundle.Bundle(intent.mExtras);
        bundle.putAll(mExtras);
        mExtras = bundle;
        i = 1;
          goto _L9
        RuntimeException runtimeexception;
        runtimeexception;
        Log.w("Intent", "Failure filling in extras", runtimeexception);
        i = l;
          goto _L9
    }

    public boolean filterEquals(Intent intent)
    {
        if(intent == null)
            return false;
        if(!Objects.equals(mAction, intent.mAction))
            return false;
        if(!Objects.equals(mData, intent.mData))
            return false;
        if(!Objects.equals(mType, intent.mType))
            return false;
        if(!Objects.equals(mPackage, intent.mPackage))
            return false;
        if(!Objects.equals(mComponent, intent.mComponent))
            return false;
        return Objects.equals(mCategories, intent.mCategories);
    }

    public int filterHashCode()
    {
        int i = 0;
        if(mAction != null)
            i = mAction.hashCode() + 0;
        int j = i;
        if(mData != null)
            j = i + mData.hashCode();
        i = j;
        if(mType != null)
            i = j + mType.hashCode();
        j = i;
        if(mPackage != null)
            j = i + mPackage.hashCode();
        i = j;
        if(mComponent != null)
            i = j + mComponent.hashCode();
        j = i;
        if(mCategories != null)
            j = i + mCategories.hashCode();
        return j;
    }

    public void fixUris(int i)
    {
        Object obj;
        obj = getData();
        if(obj != null)
            mData = ContentProvider.maybeAddUserId(((Uri) (obj)), i);
        if(mClipData != null)
            mClipData.fixUris(i);
        obj = getAction();
        if(!"android.intent.action.SEND".equals(obj)) goto _L2; else goto _L1
_L1:
        obj = (Uri)getParcelableExtra("android.intent.extra.STREAM");
        if(obj != null)
            putExtra("android.intent.extra.STREAM", ContentProvider.maybeAddUserId(((Uri) (obj)), i));
_L4:
        return;
_L2:
        if("android.intent.action.SEND_MULTIPLE".equals(obj))
        {
            obj = getParcelableArrayListExtra("android.intent.extra.STREAM");
            if(obj != null)
            {
                ArrayList arraylist = new ArrayList();
                for(int j = 0; j < ((ArrayList) (obj)).size(); j++)
                    arraylist.add(ContentProvider.maybeAddUserId((Uri)((ArrayList) (obj)).get(j), i));

                putParcelableArrayListExtra("android.intent.extra.STREAM", arraylist);
            }
        } else
        if("android.media.action.IMAGE_CAPTURE".equals(obj) || "android.media.action.IMAGE_CAPTURE_SECURE".equals(obj) || "android.media.action.VIDEO_CAPTURE".equals(obj))
        {
            Uri uri = (Uri)getParcelableExtra("output");
            if(uri != null)
                putExtra("output", ContentProvider.maybeAddUserId(uri, i));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getAction()
    {
        return mAction;
    }

    public boolean[] getBooleanArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getBooleanArray(s);
        return s;
    }

    public boolean getBooleanExtra(String s, boolean flag)
    {
        if(mExtras != null)
            flag = mExtras.getBoolean(s, flag);
        return flag;
    }

    public Bundle getBundleExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getBundle(s);
        return s;
    }

    public byte[] getByteArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getByteArray(s);
        return s;
    }

    public byte getByteExtra(String s, byte byte0)
    {
        if(mExtras != null)
        {
            byte byte1 = mExtras.getByte(s, byte0).byteValue();
            byte0 = byte1;
        }
        return byte0;
    }

    public Set getCategories()
    {
        return mCategories;
    }

    public char[] getCharArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getCharArray(s);
        return s;
    }

    public char getCharExtra(String s, char c)
    {
        if(mExtras != null)
        {
            char c1 = mExtras.getChar(s, c);
            c = c1;
        }
        return c;
    }

    public CharSequence[] getCharSequenceArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getCharSequenceArray(s);
        return s;
    }

    public ArrayList getCharSequenceArrayListExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getCharSequenceArrayList(s);
        return s;
    }

    public CharSequence getCharSequenceExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getCharSequence(s);
        return s;
    }

    public ClipData getClipData()
    {
        return mClipData;
    }

    public ComponentName getComponent()
    {
        return mComponent;
    }

    public int getContentUserHint()
    {
        return mContentUserHint;
    }

    public Uri getData()
    {
        return mData;
    }

    public String getDataString()
    {
        String s = null;
        if(mData != null)
            s = mData.toString();
        return s;
    }

    public double[] getDoubleArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getDoubleArray(s);
        return s;
    }

    public double getDoubleExtra(String s, double d)
    {
        if(mExtras != null)
            d = mExtras.getDouble(s, d);
        return d;
    }

    public Object getExtra(String s)
    {
        return getExtra(s, null);
    }

    public Object getExtra(String s, Object obj)
    {
        Object obj1 = obj;
        if(mExtras != null)
        {
            s = ((String) (mExtras.get(s)));
            obj1 = obj;
            if(s != null)
                obj1 = s;
        }
        return obj1;
    }

    public Bundle getExtras()
    {
        Bundle bundle = null;
        if(mExtras != null)
            bundle = new Bundle(mExtras);
        return bundle;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public float[] getFloatArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getFloatArray(s);
        return s;
    }

    public float getFloatExtra(String s, float f)
    {
        if(mExtras != null)
            f = mExtras.getFloat(s, f);
        return f;
    }

    public IBinder getIBinderExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getIBinder(s);
        return s;
    }

    public int[] getIntArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getIntArray(s);
        return s;
    }

    public int getIntExtra(String s, int i)
    {
        if(mExtras != null)
            i = mExtras.getInt(s, i);
        return i;
    }

    public ArrayList getIntegerArrayListExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getIntegerArrayList(s);
        return s;
    }

    public String getLaunchToken()
    {
        return mLaunchToken;
    }

    public long[] getLongArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getLongArray(s);
        return s;
    }

    public long getLongExtra(String s, long l)
    {
        if(mExtras != null)
            l = mExtras.getLong(s, l);
        return l;
    }

    public int getMiuiFlags()
    {
        return mMiuiFlags;
    }

    public String getPackage()
    {
        return mPackage;
    }

    public Parcelable[] getParcelableArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getParcelableArray(s);
        return s;
    }

    public ArrayList getParcelableArrayListExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getParcelableArrayList(s);
        return s;
    }

    public Parcelable getParcelableExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getParcelable(s);
        return s;
    }

    public String getScheme()
    {
        String s = null;
        if(mData != null)
            s = mData.getScheme();
        return s;
    }

    public Intent getSelector()
    {
        return mSelector;
    }

    public String getSender()
    {
        return mSenderPackageName;
    }

    public Serializable getSerializableExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getSerializable(s);
        return s;
    }

    public short[] getShortArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getShortArray(s);
        return s;
    }

    public short getShortExtra(String s, short word0)
    {
        if(mExtras != null)
        {
            short word1 = mExtras.getShort(s, word0);
            word0 = word1;
        }
        return word0;
    }

    public Rect getSourceBounds()
    {
        return mSourceBounds;
    }

    public String[] getStringArrayExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getStringArray(s);
        return s;
    }

    public ArrayList getStringArrayListExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getStringArrayList(s);
        return s;
    }

    public String getStringExtra(String s)
    {
        Object obj = null;
        if(mExtras == null)
            s = obj;
        else
            s = mExtras.getString(s);
        return s;
    }

    public String getType()
    {
        return mType;
    }

    public boolean hasCategory(String s)
    {
        boolean flag;
        if(mCategories != null)
            flag = mCategories.contains(s);
        else
            flag = false;
        return flag;
    }

    public boolean hasExtra(String s)
    {
        boolean flag;
        if(mExtras != null)
            flag = mExtras.containsKey(s);
        else
            flag = false;
        return flag;
    }

    public boolean hasFileDescriptors()
    {
        boolean flag;
        if(mExtras != null)
            flag = mExtras.hasFileDescriptors();
        else
            flag = false;
        return flag;
    }

    public boolean isDocument()
    {
        boolean flag;
        if((mFlags & 0x80000) == 0x80000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isExcludingStopped()
    {
        boolean flag;
        if((mFlags & 0x30) == 16)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Intent maybeStripForHistory()
    {
        if(!canStripForHistory())
            return this;
        else
            return new Intent(this, 2);
    }

    public boolean migrateExtraStreamToClipData()
    {
        Object obj;
        boolean flag;
        if(mExtras != null && mExtras.isParcelled())
            return false;
        if(getClipData() != null)
            return false;
        obj = getAction();
        if(!"android.intent.action.CHOOSER".equals(obj))
            break MISSING_BLOCK_LABEL_146;
        flag = false;
        obj = (Intent)getParcelableExtra("android.intent.extra.INTENT");
        boolean flag1 = flag;
        ClassCastException classcastexception;
        Parcelable aparcelable[];
        ClipData.Item item;
        boolean flag2;
        int i;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        int j;
        if(obj != null)
            try
            {
                flag1 = ((Intent) (obj)).migrateExtraStreamToClipData();
            }
            catch(ClassCastException classcastexception1)
            {
                flag1 = flag;
            }
        flag = flag1;
        aparcelable = getParcelableArrayExtra("android.intent.extra.INITIAL_INTENTS");
        flag2 = flag1;
        if(aparcelable == null) goto _L2; else goto _L1
_L1:
        i = 0;
_L3:
        flag = flag1;
        flag2 = flag1;
        if(i >= aparcelable.length)
            break; /* Loop/switch isn't completed */
        flag = flag1;
        obj = (Intent)aparcelable[i];
        flag = flag1;
        if(obj == null)
            break MISSING_BLOCK_LABEL_131;
        flag = flag1;
        flag2 = ((Intent) (obj)).migrateExtraStreamToClipData();
        flag = flag1 | flag2;
        i++;
        flag1 = flag;
        if(true) goto _L3; else goto _L2
        obj;
        flag2 = flag;
_L2:
        return flag2;
        if(!"android.intent.action.SEND".equals(obj)) goto _L5; else goto _L4
_L4:
        obj1 = (Uri)getParcelableExtra("android.intent.extra.STREAM");
        obj2 = getCharSequenceExtra("android.intent.extra.TEXT");
        obj3 = getStringExtra("android.intent.extra.HTML_TEXT");
          goto _L6
_L8:
        obj = JVM INSTR new #1272 <Class ClipData>;
        obj4 = getType();
        item = JVM INSTR new #1326 <Class ClipData$Item>;
        item.ClipData.Item(((CharSequence) (obj2)), ((String) (obj3)), null, ((Uri) (obj1)));
        ((ClipData) (obj)).ClipData(null, new String[] {
            obj4
        }, item);
        setClipData(((ClipData) (obj)));
        addFlags(1);
        return true;
_L6:
        if(obj1 != null || obj2 != null || obj3 != null) goto _L8; else goto _L7
_L7:
        return false;
_L5:
        if(!"android.intent.action.SEND_MULTIPLE".equals(obj))
            break MISSING_BLOCK_LABEL_466;
        try
        {
            obj4 = getParcelableArrayListExtra("android.intent.extra.STREAM");
            obj2 = getCharSequenceArrayListExtra("android.intent.extra.TEXT");
            obj1 = getStringArrayListExtra("android.intent.extra.HTML_TEXT");
        }
        // Misplaced declaration of an exception variable
        catch(ClassCastException classcastexception)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = -1;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_311;
        j = ((ArrayList) (obj4)).size();
        i = j;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_344;
        if(j < 0)
            break MISSING_BLOCK_LABEL_337;
        if(j != ((ArrayList) (obj2)).size())
            return false;
        i = ((ArrayList) (obj2)).size();
        j = i;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_377;
        if(i < 0)
            break MISSING_BLOCK_LABEL_370;
        if(i != ((ArrayList) (obj1)).size())
            return false;
        j = ((ArrayList) (obj1)).size();
        if(j <= 0)
            continue; /* Loop/switch isn't completed */
        obj3 = JVM INSTR new #1272 <Class ClipData>;
        obj = getType();
        item = makeClipItem(((ArrayList) (obj4)), ((ArrayList) (obj2)), ((ArrayList) (obj1)), 0);
        ((ClipData) (obj3)).ClipData(null, new String[] {
            obj
        }, item);
        i = 1;
_L10:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        ((ClipData) (obj3)).addItem(makeClipItem(((ArrayList) (obj4)), ((ArrayList) (obj2)), ((ArrayList) (obj1)), i));
        i++;
        if(true) goto _L10; else goto _L9
_L9:
        setClipData(((ClipData) (obj3)));
        addFlags(1);
        return true;
        if("android.media.action.IMAGE_CAPTURE".equals(obj) || "android.media.action.IMAGE_CAPTURE_SECURE".equals(obj) || "android.media.action.VIDEO_CAPTURE".equals(obj))
        {
            try
            {
                obj = (Uri)getParcelableExtra("output");
            }
            // Misplaced declaration of an exception variable
            catch(ClassCastException classcastexception)
            {
                return false;
            }
            if(obj != null)
            {
                setClipData(ClipData.newRawUri("", ((Uri) (obj))));
                addFlags(3);
                return true;
            }
        }
        continue; /* Loop/switch isn't completed */
        classcastexception;
        if(true) goto _L7; else goto _L11
_L11:
    }

    public void prepareToEnterProcess()
    {
        setDefusable(true);
        if(mSelector != null)
            mSelector.prepareToEnterProcess();
        if(mClipData != null)
            mClipData.prepareToEnterProcess();
        if(mContentUserHint != -2 && UserHandle.getAppId(Process.myUid()) != 1000)
        {
            fixUris(mContentUserHint);
            mContentUserHint = -2;
        }
    }

    public void prepareToLeaveProcess(Context context)
    {
        boolean flag;
        if(mComponent != null)
            flag = Objects.equals(mComponent.getPackageName(), context.getPackageName()) ^ true;
        else
            flag = true;
        prepareToLeaveProcess(flag);
    }

    public void prepareToLeaveProcess(boolean flag)
    {
        String s;
        setAllowFds(false);
        if(mSelector != null)
            mSelector.prepareToLeaveProcess(flag);
        if(mClipData != null)
            mClipData.prepareToLeaveProcess(flag, getFlags());
        if(mExtras != null && mExtras.isParcelled() ^ true)
        {
            Object obj = mExtras.get("android.intent.extra.INTENT");
            if(obj instanceof Intent)
                ((Intent)obj).prepareToLeaveProcess(flag);
        }
        if(mAction != null && mData != null && StrictMode.vmFileUriExposureEnabled() && flag)
        {
            s = mAction;
            break MISSING_BLOCK_LABEL_113;
        }
_L1:
label0:
        {
            if(mAction == null || mData == null || !StrictMode.vmContentUriWithoutPermissionEnabled() || !flag)
                break label0;
            s = mAction;
        }
        break MISSING_BLOCK_LABEL_152;
        if(!s.equals("android.intent.action.PROVIDER_CHANGED") && !s.equals("android.provider.action.QUICK_CONTACT"))
            mData.checkContentUriWithoutPermission("Intent.getData()", getFlags());
        return;
        if(!s.equals("android.intent.action.MEDIA_REMOVED") && !s.equals("android.intent.action.MEDIA_UNMOUNTED") && !s.equals("android.intent.action.MEDIA_CHECKING") && !s.equals("android.intent.action.MEDIA_NOFS") && !s.equals("android.intent.action.MEDIA_MOUNTED") && !s.equals("android.intent.action.MEDIA_SHARED") && !s.equals("android.intent.action.MEDIA_UNSHARED") && !s.equals("android.intent.action.MEDIA_BAD_REMOVAL") && !s.equals("android.intent.action.MEDIA_UNMOUNTABLE") && !s.equals("android.intent.action.MEDIA_EJECT") && !s.equals("android.intent.action.MEDIA_SCANNER_STARTED") && !s.equals("android.intent.action.MEDIA_SCANNER_FINISHED") && !s.equals("android.intent.action.MEDIA_SCANNER_SCAN_FILE") && !s.equals("android.intent.action.PACKAGE_NEEDS_VERIFICATION") && !s.equals("android.intent.action.PACKAGE_VERIFIED") && !s.equals("miui.intent.action.MEDIA_SCANNER_SCAN_FOLDER"))
            mData.checkFileUriExposed("Intent.getData()");
          goto _L1
    }

    public void prepareToLeaveUser(int i)
    {
        if(mContentUserHint == -2)
            mContentUserHint = i;
    }

    public Intent putCharSequenceArrayListExtra(String s, ArrayList arraylist)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putCharSequenceArrayList(s, arraylist);
        return this;
    }

    public Intent putExtra(String s, byte byte0)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putByte(s, byte0);
        return this;
    }

    public Intent putExtra(String s, char c)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putChar(s, c);
        return this;
    }

    public Intent putExtra(String s, double d)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putDouble(s, d);
        return this;
    }

    public Intent putExtra(String s, float f)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putFloat(s, f);
        return this;
    }

    public Intent putExtra(String s, int i)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putInt(s, i);
        return this;
    }

    public Intent putExtra(String s, long l)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putLong(s, l);
        return this;
    }

    public Intent putExtra(String s, Bundle bundle)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putBundle(s, bundle);
        return this;
    }

    public Intent putExtra(String s, IBinder ibinder)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putIBinder(s, ibinder);
        return this;
    }

    public Intent putExtra(String s, Parcelable parcelable)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putParcelable(s, parcelable);
        return this;
    }

    public Intent putExtra(String s, Serializable serializable)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putSerializable(s, serializable);
        return this;
    }

    public Intent putExtra(String s, CharSequence charsequence)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putCharSequence(s, charsequence);
        return this;
    }

    public Intent putExtra(String s, String s1)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putString(s, s1);
        return this;
    }

    public Intent putExtra(String s, short word0)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putShort(s, word0);
        return this;
    }

    public Intent putExtra(String s, boolean flag)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putBoolean(s, flag);
        return this;
    }

    public Intent putExtra(String s, byte abyte0[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putByteArray(s, abyte0);
        return this;
    }

    public Intent putExtra(String s, char ac[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putCharArray(s, ac);
        return this;
    }

    public Intent putExtra(String s, double ad[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putDoubleArray(s, ad);
        return this;
    }

    public Intent putExtra(String s, float af[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putFloatArray(s, af);
        return this;
    }

    public Intent putExtra(String s, int ai[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putIntArray(s, ai);
        return this;
    }

    public Intent putExtra(String s, long al[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putLongArray(s, al);
        return this;
    }

    public Intent putExtra(String s, Parcelable aparcelable[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putParcelableArray(s, aparcelable);
        return this;
    }

    public Intent putExtra(String s, CharSequence acharsequence[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putCharSequenceArray(s, acharsequence);
        return this;
    }

    public Intent putExtra(String s, String as[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putStringArray(s, as);
        return this;
    }

    public Intent putExtra(String s, short aword0[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putShortArray(s, aword0);
        return this;
    }

    public Intent putExtra(String s, boolean aflag[])
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putBooleanArray(s, aflag);
        return this;
    }

    public Intent putExtras(Intent intent)
    {
        if(intent.mExtras != null)
            if(mExtras == null)
                mExtras = new Bundle(intent.mExtras);
            else
                mExtras.putAll(intent.mExtras);
        return this;
    }

    public Intent putExtras(Bundle bundle)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putAll(bundle);
        return this;
    }

    public Intent putIntegerArrayListExtra(String s, ArrayList arraylist)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putIntegerArrayList(s, arraylist);
        return this;
    }

    public Intent putParcelableArrayListExtra(String s, ArrayList arraylist)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putParcelableArrayList(s, arraylist);
        return this;
    }

    public Intent putStringArrayListExtra(String s, ArrayList arraylist)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putStringArrayList(s, arraylist);
        return this;
    }

    public void readFromParcel(Parcel parcel)
    {
        setAction(parcel.readString());
        mData = (Uri)Uri.CREATOR.createFromParcel(parcel);
        mType = parcel.readString();
        mFlags = parcel.readInt();
        mPackage = parcel.readString();
        mComponent = ComponentName.readFromParcel(parcel);
        if(parcel.readInt() != 0)
            mSourceBounds = (Rect)Rect.CREATOR.createFromParcel(parcel);
        int i = parcel.readInt();
        if(i > 0)
        {
            mCategories = new ArraySet();
            for(int j = 0; j < i; j++)
                mCategories.add(parcel.readString().intern());

        } else
        {
            mCategories = null;
        }
        if(parcel.readInt() != 0)
            mSelector = new Intent(parcel);
        if(parcel.readInt() != 0)
            mClipData = new ClipData(parcel);
        mContentUserHint = parcel.readInt();
        mExtras = parcel.readBundle();
        mSenderPackageName = parcel.readString();
    }

    public void removeCategory(String s)
    {
        if(mCategories != null)
        {
            mCategories.remove(s);
            if(mCategories.size() == 0)
                mCategories = null;
        }
    }

    public void removeExtra(String s)
    {
        if(mExtras != null)
        {
            mExtras.remove(s);
            if(mExtras.size() == 0)
                mExtras = null;
        }
    }

    public void removeFlags(int i)
    {
        mFlags = mFlags & i;
    }

    public void removeUnsafeExtras()
    {
        if(mExtras != null)
            mExtras = mExtras.filterValues();
    }

    public Intent replaceExtras(Intent intent)
    {
        Bundle bundle = null;
        if(intent.mExtras != null)
            bundle = new Bundle(intent.mExtras);
        mExtras = bundle;
        return this;
    }

    public Intent replaceExtras(Bundle bundle)
    {
        Bundle bundle1 = null;
        if(bundle != null)
            bundle1 = new Bundle(bundle);
        mExtras = bundle1;
        return this;
    }

    public ComponentName resolveActivity(PackageManager packagemanager)
    {
        if(mComponent != null)
            return mComponent;
        packagemanager = packagemanager.resolveActivity(this, 0x10000);
        if(packagemanager != null)
            return new ComponentName(((ResolveInfo) (packagemanager)).activityInfo.applicationInfo.packageName, ((ResolveInfo) (packagemanager)).activityInfo.name);
        else
            return null;
    }

    public ActivityInfo resolveActivityInfo(PackageManager packagemanager, int i)
    {
label0:
        {
            {
                Object obj = null;
                if(mComponent == null)
                    break label0;
                ResolveInfo resolveinfo;
                try
                {
                    packagemanager = packagemanager.getActivityInfo(mComponent, i);
                }
                // Misplaced declaration of an exception variable
                catch(PackageManager packagemanager)
                {
                    packagemanager = obj;
                }
            }
            return packagemanager;
        }
        resolveinfo = packagemanager.resolveActivity(this, 0x10000 | i);
        packagemanager = obj;
        if(resolveinfo != null)
            packagemanager = resolveinfo.activityInfo;
        if(false)
            ;
        else
            break MISSING_BLOCK_LABEL_19;
    }

    public ComponentName resolveSystemService(PackageManager packagemanager, int i)
    {
        if(mComponent != null)
            return mComponent;
        List list = packagemanager.queryIntentServices(this, i);
        if(list == null)
            return null;
        packagemanager = null;
        i = 0;
        while(i < list.size()) 
        {
            Object obj = (ResolveInfo)list.get(i);
            if((((ResolveInfo) (obj)).serviceInfo.applicationInfo.flags & 1) != 0)
            {
                obj = new ComponentName(((ResolveInfo) (obj)).serviceInfo.applicationInfo.packageName, ((ResolveInfo) (obj)).serviceInfo.name);
                if(packagemanager != null)
                    throw new IllegalStateException((new StringBuilder()).append("Multiple system services handle ").append(this).append(": ").append(packagemanager).append(", ").append(obj).toString());
                packagemanager = ((PackageManager) (obj));
            }
            i++;
        }
        return packagemanager;
    }

    public String resolveType(ContentResolver contentresolver)
    {
        if(mType != null)
            return mType;
        if(mData != null && "content".equals(mData.getScheme()))
            return contentresolver.getType(mData);
        else
            return null;
    }

    public String resolveType(Context context)
    {
        return resolveType(context.getContentResolver());
    }

    public String resolveTypeIfNeeded(ContentResolver contentresolver)
    {
        if(mComponent != null)
            return mType;
        else
            return resolveType(contentresolver);
    }

    public void saveToXml(XmlSerializer xmlserializer)
        throws IOException
    {
        if(mAction != null)
            xmlserializer.attribute(null, "action", mAction);
        if(mData != null)
            xmlserializer.attribute(null, "data", mData.toString());
        if(mType != null)
            xmlserializer.attribute(null, "type", mType);
        if(mComponent != null)
            xmlserializer.attribute(null, "component", mComponent.flattenToShortString());
        xmlserializer.attribute(null, "flags", Integer.toHexString(getFlags()));
        if(mCategories != null)
        {
            xmlserializer.startTag(null, "categories");
            for(int i = mCategories.size() - 1; i >= 0; i--)
                xmlserializer.attribute(null, "category", (String)mCategories.valueAt(i));

            xmlserializer.endTag(null, "categories");
        }
    }

    public Intent setAction(String s)
    {
        String s1 = null;
        if(s != null)
            s1 = s.intern();
        mAction = s1;
        return this;
    }

    public void setAllowFds(boolean flag)
    {
        if(mExtras != null)
            mExtras.setAllowFds(flag);
    }

    public Intent setClass(Context context, Class class1)
    {
        mComponent = new ComponentName(context, class1);
        return this;
    }

    public Intent setClassName(Context context, String s)
    {
        mComponent = new ComponentName(context, s);
        return this;
    }

    public Intent setClassName(String s, String s1)
    {
        mComponent = new ComponentName(s, s1);
        return this;
    }

    public void setClipData(ClipData clipdata)
    {
        mClipData = clipdata;
    }

    public Intent setComponent(ComponentName componentname)
    {
        mComponent = componentname;
        return this;
    }

    public Intent setData(Uri uri)
    {
        mData = uri;
        mType = null;
        return this;
    }

    public Intent setDataAndNormalize(Uri uri)
    {
        return setData(uri.normalizeScheme());
    }

    public Intent setDataAndType(Uri uri, String s)
    {
        mData = uri;
        mType = s;
        return this;
    }

    public Intent setDataAndTypeAndNormalize(Uri uri, String s)
    {
        return setDataAndType(uri.normalizeScheme(), normalizeMimeType(s));
    }

    public void setDefusable(boolean flag)
    {
        if(mExtras != null)
            mExtras.setDefusable(flag);
    }

    public void setExtrasClassLoader(ClassLoader classloader)
    {
        if(mExtras != null)
            mExtras.setClassLoader(classloader);
    }

    public Intent setFlags(int i)
    {
        mFlags = i;
        return this;
    }

    public void setLaunchToken(String s)
    {
        mLaunchToken = s;
    }

    public Intent setMiuiFlags(int i)
    {
        mMiuiFlags = i;
        return this;
    }

    public Intent setPackage(String s)
    {
        if(s != null && mSelector != null)
        {
            throw new IllegalArgumentException("Can't set package name when selector is already set");
        } else
        {
            mPackage = s;
            return this;
        }
    }

    public void setSelector(Intent intent)
    {
        if(intent == this)
            throw new IllegalArgumentException("Intent being set as a selector of itself");
        if(intent != null && mPackage != null)
        {
            throw new IllegalArgumentException("Can't set selector when package name is already set");
        } else
        {
            mSelector = intent;
            return;
        }
    }

    public void setSender(String s)
    {
        mSenderPackageName = s;
    }

    public void setSourceBounds(Rect rect)
    {
        if(rect != null)
            mSourceBounds = new Rect(rect);
        else
            mSourceBounds = null;
    }

    public Intent setType(String s)
    {
        mData = null;
        mType = s;
        return this;
    }

    public Intent setTypeAndNormalize(String s)
    {
        return setType(normalizeMimeType(s));
    }

    public String toInsecureString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("Intent { ");
        toShortString(stringbuilder, false, true, true, false);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public String toInsecureStringWithClip()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("Intent { ");
        toShortString(stringbuilder, false, true, true, true);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public String toShortString(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        toShortString(stringbuilder, flag, flag1, flag2, flag3);
        return stringbuilder.toString();
    }

    public void toShortString(StringBuilder stringbuilder, boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        boolean flag4 = true;
        if(mAction != null)
        {
            stringbuilder.append("act=").append(mAction);
            flag4 = false;
        }
        int j = ((flag4) ? 1 : 0);
        if(mCategories != null)
        {
            if(!flag4)
                stringbuilder.append(' ');
            flag4 = false;
            stringbuilder.append("cat=[");
            for(j = 0; j < mCategories.size(); j++)
            {
                if(j > 0)
                    stringbuilder.append(',');
                stringbuilder.append((String)mCategories.valueAt(j));
            }

            stringbuilder.append("]");
            j = ((flag4) ? 1 : 0);
        }
        int i = j;
        if(mData != null)
        {
            if(j == 0)
                stringbuilder.append(' ');
            i = 0;
            stringbuilder.append("dat=");
            if(flag)
                stringbuilder.append(mData.toSafeString());
            else
                stringbuilder.append(mData);
        }
        j = i;
        if(mType != null)
        {
            if(i == 0)
                stringbuilder.append(' ');
            j = 0;
            stringbuilder.append("typ=").append(mType);
        }
        i = j;
        if(mFlags != 0)
        {
            if(j == 0)
                stringbuilder.append(' ');
            i = 0;
            stringbuilder.append("flg=0x").append(Integer.toHexString(mFlags));
        }
        j = i;
        if(mPackage != null)
        {
            if(i == 0)
                stringbuilder.append(' ');
            j = 0;
            stringbuilder.append("pkg=").append(mPackage);
        }
        i = j;
        if(flag1)
        {
            i = j;
            if(mComponent != null)
            {
                if(j == 0)
                    stringbuilder.append(' ');
                i = 0;
                stringbuilder.append("cmp=").append(mComponent.flattenToShortString());
            }
        }
        j = i;
        if(mSourceBounds != null)
        {
            if(i == 0)
                stringbuilder.append(' ');
            j = 0;
            stringbuilder.append("bnds=").append(mSourceBounds.toShortString());
        }
        i = j;
        if(mClipData != null)
        {
            if(j == 0)
                stringbuilder.append(' ');
            stringbuilder.append("clip={");
            if(flag3)
            {
                mClipData.toShortString(stringbuilder);
            } else
            {
                boolean flag5;
                if(mClipData.getDescription() != null)
                    flag5 = mClipData.getDescription().toShortStringTypesOnly(stringbuilder) ^ true;
                else
                    flag5 = true;
                mClipData.toShortStringShortItems(stringbuilder, flag5);
            }
            i = 0;
            stringbuilder.append('}');
        }
        j = i;
        if(flag2)
        {
            j = i;
            if(mExtras != null)
            {
                if(i == 0)
                    stringbuilder.append(' ');
                j = 0;
                stringbuilder.append("(has extras)");
            }
        }
        if(mContentUserHint != -2)
        {
            if(j == 0)
                stringbuilder.append(' ');
            stringbuilder.append("u=").append(mContentUserHint);
        }
        if(mSelector != null)
        {
            stringbuilder.append(" sel=");
            mSelector.toShortString(stringbuilder, flag, flag1, flag2, flag3);
            stringbuilder.append("}");
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("Intent { ");
        toShortString(stringbuilder, true, true, true, false);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public String toURI()
    {
        return toUri(0);
    }

    public String toUri(int i)
    {
        StringBuilder stringbuilder;
        String s3;
        Object obj;
        stringbuilder = new StringBuilder(128);
        if((i & 2) != 0)
        {
            if(mPackage == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Intent must include an explicit package name to build an android-app: ").append(this).toString());
            stringbuilder.append("android-app://");
            stringbuilder.append(mPackage);
            String s = null;
            if(mData != null)
            {
                String s2 = mData.getScheme();
                s = s2;
                if(s2 != null)
                {
                    stringbuilder.append('/');
                    stringbuilder.append(s2);
                    String s4 = mData.getEncodedAuthority();
                    s = s2;
                    if(s4 != null)
                    {
                        stringbuilder.append('/');
                        stringbuilder.append(s4);
                        s = mData.getEncodedPath();
                        if(s != null)
                            stringbuilder.append(s);
                        s = mData.getEncodedQuery();
                        if(s != null)
                        {
                            stringbuilder.append('?');
                            stringbuilder.append(s);
                        }
                        s4 = mData.getEncodedFragment();
                        s = s2;
                        if(s4 != null)
                        {
                            stringbuilder.append('#');
                            stringbuilder.append(s4);
                            s = s2;
                        }
                    }
                }
            }
            if(s == null)
                s = "android.intent.action.MAIN";
            else
                s = "android.intent.action.VIEW";
            toUriFragment(stringbuilder, null, s, mPackage, i);
            return stringbuilder.toString();
        }
        s3 = null;
        obj = null;
        if(mData == null) goto _L2; else goto _L1
_L1:
        String s1;
        String s5;
        s5 = mData.toString();
        s1 = obj;
        s3 = s5;
        if((i & 1) == 0) goto _L4; else goto _L3
_L3:
        int j;
        int k;
        j = s5.length();
        k = 0;
_L6:
        char c;
        s1 = obj;
        s3 = s5;
        if(k >= j)
            break; /* Loop/switch isn't completed */
        c = s5.charAt(k);
          goto _L5
_L8:
        k++;
        if(true) goto _L6; else goto _L4
_L5:
        if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '.' || c == '-') goto _L8; else goto _L7
_L7:
        s1 = obj;
        s3 = s5;
        if(c == ':')
        {
            s1 = obj;
            s3 = s5;
            if(k > 0)
            {
                s1 = s5.substring(0, k);
                stringbuilder.append("intent:");
                s3 = s5.substring(k + 1);
            }
        }
_L4:
        stringbuilder.append(s3);
_L10:
        toUriFragment(stringbuilder, s1, "android.intent.action.VIEW", null, i);
        return stringbuilder.toString();
_L2:
        s1 = s3;
        if((i & 1) != 0)
        {
            stringbuilder.append("intent:");
            s1 = s3;
        }
        if(true) goto _L10; else goto _L9
_L9:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mAction);
        Uri.writeToParcel(parcel, mData);
        parcel.writeString(mType);
        parcel.writeInt(mFlags);
        parcel.writeString(mPackage);
        ComponentName.writeToParcel(mComponent, parcel);
        if(mSourceBounds != null)
        {
            parcel.writeInt(1);
            mSourceBounds.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(mCategories != null)
        {
            int j = mCategories.size();
            parcel.writeInt(j);
            for(int k = 0; k < j; k++)
                parcel.writeString((String)mCategories.valueAt(k));

        } else
        {
            parcel.writeInt(0);
        }
        if(mSelector != null)
        {
            parcel.writeInt(1);
            mSelector.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(mClipData != null)
        {
            parcel.writeInt(1);
            mClipData.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mContentUserHint);
        parcel.writeBundle(mExtras);
        parcel.writeString(mSenderPackageName);
    }

    public static final String ACTION_ADVANCED_SETTINGS_CHANGED = "android.intent.action.ADVANCED_SETTINGS";
    public static final String ACTION_AIRPLANE_MODE_CHANGED = "android.intent.action.AIRPLANE_MODE";
    public static final String ACTION_ALARM_CHANGED = "android.intent.action.ALARM_CHANGED";
    public static final String ACTION_ALL_APPS = "android.intent.action.ALL_APPS";
    public static final String ACTION_ANSWER = "android.intent.action.ANSWER";
    public static final String ACTION_APPLICATION_PREFERENCES = "android.intent.action.APPLICATION_PREFERENCES";
    public static final String ACTION_APPLICATION_RESTRICTIONS_CHANGED = "android.intent.action.APPLICATION_RESTRICTIONS_CHANGED";
    public static final String ACTION_APP_ERROR = "android.intent.action.APP_ERROR";
    public static final String ACTION_ASSIST = "android.intent.action.ASSIST";
    public static final String ACTION_ATTACH_DATA = "android.intent.action.ATTACH_DATA";
    public static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
    public static final String ACTION_BATTERY_LOW = "android.intent.action.BATTERY_LOW";
    public static final String ACTION_BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";
    public static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
    public static final String ACTION_BUG_REPORT = "android.intent.action.BUG_REPORT";
    public static final String ACTION_CALL = "android.intent.action.CALL";
    public static final String ACTION_CALL_BUTTON = "android.intent.action.CALL_BUTTON";
    public static final String ACTION_CALL_EMERGENCY = "android.intent.action.CALL_EMERGENCY";
    public static final String ACTION_CALL_PRIVILEGED = "android.intent.action.CALL_PRIVILEGED";
    public static final String ACTION_CAMERA_BUTTON = "android.intent.action.CAMERA_BUTTON";
    public static final String ACTION_CARRIER_SETUP = "android.intent.action.CARRIER_SETUP";
    public static final String ACTION_CHOOSER = "android.intent.action.CHOOSER";
    public static final String ACTION_CLEAR_DNS_CACHE = "android.intent.action.CLEAR_DNS_CACHE";
    public static final String ACTION_CLOSE_SYSTEM_DIALOGS = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
    public static final String ACTION_CONFIGURATION_CHANGED = "android.intent.action.CONFIGURATION_CHANGED";
    public static final String ACTION_CREATE_DOCUMENT = "android.intent.action.CREATE_DOCUMENT";
    public static final String ACTION_CREATE_SHORTCUT = "android.intent.action.CREATE_SHORTCUT";
    public static final String ACTION_DATE_CHANGED = "android.intent.action.DATE_CHANGED";
    public static final String ACTION_DEFAULT = "android.intent.action.VIEW";
    public static final String ACTION_DELETE = "android.intent.action.DELETE";
    public static final String ACTION_DEVICE_INITIALIZATION_WIZARD = "android.intent.action.DEVICE_INITIALIZATION_WIZARD";
    public static final String ACTION_DEVICE_LOCKED_CHANGED = "android.intent.action.DEVICE_LOCKED_CHANGED";
    public static final String ACTION_DEVICE_STORAGE_FULL = "android.intent.action.DEVICE_STORAGE_FULL";
    public static final String ACTION_DEVICE_STORAGE_LOW = "android.intent.action.DEVICE_STORAGE_LOW";
    public static final String ACTION_DEVICE_STORAGE_NOT_FULL = "android.intent.action.DEVICE_STORAGE_NOT_FULL";
    public static final String ACTION_DEVICE_STORAGE_OK = "android.intent.action.DEVICE_STORAGE_OK";
    public static final String ACTION_DIAL = "android.intent.action.DIAL";
    public static final String ACTION_DISMISS_KEYBOARD_SHORTCUTS = "com.android.intent.action.DISMISS_KEYBOARD_SHORTCUTS";
    public static final String ACTION_DOCK_EVENT = "android.intent.action.DOCK_EVENT";
    public static final String ACTION_DREAMING_STARTED = "android.intent.action.DREAMING_STARTED";
    public static final String ACTION_DREAMING_STOPPED = "android.intent.action.DREAMING_STOPPED";
    public static final String ACTION_DYNAMIC_SENSOR_CHANGED = "android.intent.action.DYNAMIC_SENSOR_CHANGED";
    public static final String ACTION_EDIT = "android.intent.action.EDIT";
    public static final String ACTION_EPHEMERAL_RESOLVER_SETTINGS = "android.intent.action.EPHEMERAL_RESOLVER_SETTINGS";
    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
    public static final String ACTION_FACTORY_RESET = "android.intent.action.FACTORY_RESET";
    public static final String ACTION_FACTORY_TEST = "android.intent.action.FACTORY_TEST";
    public static final String ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT";
    public static final String ACTION_GET_RESTRICTION_ENTRIES = "android.intent.action.GET_RESTRICTION_ENTRIES";
    public static final String ACTION_GLOBAL_BUTTON = "android.intent.action.GLOBAL_BUTTON";
    public static final String ACTION_GTALK_SERVICE_CONNECTED = "android.intent.action.GTALK_CONNECTED";
    public static final String ACTION_GTALK_SERVICE_DISCONNECTED = "android.intent.action.GTALK_DISCONNECTED";
    public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
    public static final String ACTION_IDLE_MAINTENANCE_END = "android.intent.action.ACTION_IDLE_MAINTENANCE_END";
    public static final String ACTION_IDLE_MAINTENANCE_START = "android.intent.action.ACTION_IDLE_MAINTENANCE_START";
    public static final String ACTION_INPUT_METHOD_CHANGED = "android.intent.action.INPUT_METHOD_CHANGED";
    public static final String ACTION_INSERT = "android.intent.action.INSERT";
    public static final String ACTION_INSERT_OR_EDIT = "android.intent.action.INSERT_OR_EDIT";
    public static final String ACTION_INSTALL_EPHEMERAL_PACKAGE = "android.intent.action.INSTALL_EPHEMERAL_PACKAGE";
    public static final String ACTION_INSTALL_FAILURE = "android.intent.action.INSTALL_FAILURE";
    public static final String ACTION_INSTALL_INSTANT_APP_PACKAGE = "android.intent.action.INSTALL_INSTANT_APP_PACKAGE";
    public static final String ACTION_INSTALL_PACKAGE = "android.intent.action.INSTALL_PACKAGE";
    public static final String ACTION_INSTANT_APP_RESOLVER_SETTINGS = "android.intent.action.INSTANT_APP_RESOLVER_SETTINGS";
    public static final String ACTION_INTENT_FILTER_NEEDS_VERIFICATION = "android.intent.action.INTENT_FILTER_NEEDS_VERIFICATION";
    public static final String ACTION_LOCALE_CHANGED = "android.intent.action.LOCALE_CHANGED";
    public static final String ACTION_LOCKED_BOOT_COMPLETED = "android.intent.action.LOCKED_BOOT_COMPLETED";
    public static final String ACTION_MAIN = "android.intent.action.MAIN";
    public static final String ACTION_MANAGED_PROFILE_ADDED = "android.intent.action.MANAGED_PROFILE_ADDED";
    public static final String ACTION_MANAGED_PROFILE_AVAILABLE = "android.intent.action.MANAGED_PROFILE_AVAILABLE";
    public static final String ACTION_MANAGED_PROFILE_REMOVED = "android.intent.action.MANAGED_PROFILE_REMOVED";
    public static final String ACTION_MANAGED_PROFILE_UNAVAILABLE = "android.intent.action.MANAGED_PROFILE_UNAVAILABLE";
    public static final String ACTION_MANAGED_PROFILE_UNLOCKED = "android.intent.action.MANAGED_PROFILE_UNLOCKED";
    public static final String ACTION_MANAGE_APP_PERMISSIONS = "android.intent.action.MANAGE_APP_PERMISSIONS";
    public static final String ACTION_MANAGE_NETWORK_USAGE = "android.intent.action.MANAGE_NETWORK_USAGE";
    public static final String ACTION_MANAGE_PACKAGE_STORAGE = "android.intent.action.MANAGE_PACKAGE_STORAGE";
    public static final String ACTION_MANAGE_PERMISSIONS = "android.intent.action.MANAGE_PERMISSIONS";
    public static final String ACTION_MANAGE_PERMISSION_APPS = "android.intent.action.MANAGE_PERMISSION_APPS";
    public static final String ACTION_MASTER_CLEAR = "android.intent.action.MASTER_CLEAR";
    public static final String ACTION_MASTER_CLEAR_NOTIFICATION = "android.intent.action.MASTER_CLEAR_NOTIFICATION";
    public static final String ACTION_MEDIA_BAD_REMOVAL = "android.intent.action.MEDIA_BAD_REMOVAL";
    public static final String ACTION_MEDIA_BUTTON = "android.intent.action.MEDIA_BUTTON";
    public static final String ACTION_MEDIA_CHECKING = "android.intent.action.MEDIA_CHECKING";
    public static final String ACTION_MEDIA_EJECT = "android.intent.action.MEDIA_EJECT";
    public static final String ACTION_MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";
    public static final String ACTION_MEDIA_NOFS = "android.intent.action.MEDIA_NOFS";
    public static final String ACTION_MEDIA_REMOVED = "android.intent.action.MEDIA_REMOVED";
    public static final String ACTION_MEDIA_RESOURCE_GRANTED = "android.intent.action.MEDIA_RESOURCE_GRANTED";
    public static final String ACTION_MEDIA_SCANNER_FINISHED = "android.intent.action.MEDIA_SCANNER_FINISHED";
    public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";
    public static final String ACTION_MEDIA_SCANNER_STARTED = "android.intent.action.MEDIA_SCANNER_STARTED";
    public static final String ACTION_MEDIA_SHARED = "android.intent.action.MEDIA_SHARED";
    public static final String ACTION_MEDIA_UNMOUNTABLE = "android.intent.action.MEDIA_UNMOUNTABLE";
    public static final String ACTION_MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";
    public static final String ACTION_MEDIA_UNSHARED = "android.intent.action.MEDIA_UNSHARED";
    public static final String ACTION_MY_PACKAGE_REPLACED = "android.intent.action.MY_PACKAGE_REPLACED";
    public static final String ACTION_NEW_OUTGOING_CALL = "android.intent.action.NEW_OUTGOING_CALL";
    public static final String ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";
    public static final String ACTION_OPEN_DOCUMENT_TREE = "android.intent.action.OPEN_DOCUMENT_TREE";
    public static final String ACTION_OVERLAY_CHANGED = "android.intent.action.OVERLAY_CHANGED";
    public static final String ACTION_PACKAGES_SUSPENDED = "android.intent.action.PACKAGES_SUSPENDED";
    public static final String ACTION_PACKAGES_UNSUSPENDED = "android.intent.action.PACKAGES_UNSUSPENDED";
    public static final String ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";
    public static final String ACTION_PACKAGE_CHANGED = "android.intent.action.PACKAGE_CHANGED";
    public static final String ACTION_PACKAGE_DATA_CLEARED = "android.intent.action.PACKAGE_DATA_CLEARED";
    public static final String ACTION_PACKAGE_FIRST_LAUNCH = "android.intent.action.PACKAGE_FIRST_LAUNCH";
    public static final String ACTION_PACKAGE_FULLY_REMOVED = "android.intent.action.PACKAGE_FULLY_REMOVED";
    public static final String ACTION_PACKAGE_INSTALL = "android.intent.action.PACKAGE_INSTALL";
    public static final String ACTION_PACKAGE_NEEDS_VERIFICATION = "android.intent.action.PACKAGE_NEEDS_VERIFICATION";
    public static final String ACTION_PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";
    public static final String ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";
    public static final String ACTION_PACKAGE_RESTARTED = "android.intent.action.PACKAGE_RESTARTED";
    public static final String ACTION_PACKAGE_VERIFIED = "android.intent.action.PACKAGE_VERIFIED";
    public static final String ACTION_PASTE = "android.intent.action.PASTE";
    public static final String ACTION_PICK = "android.intent.action.PICK";
    public static final String ACTION_PICK_ACTIVITY = "android.intent.action.PICK_ACTIVITY";
    public static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
    public static final String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";
    public static final String ACTION_POWER_USAGE_SUMMARY = "android.intent.action.POWER_USAGE_SUMMARY";
    public static final String ACTION_PREFERRED_ACTIVITY_CHANGED = "android.intent.action.ACTION_PREFERRED_ACTIVITY_CHANGED";
    public static final String ACTION_PRE_BOOT_COMPLETED = "android.intent.action.PRE_BOOT_COMPLETED";
    public static final String ACTION_PROCESS_TEXT = "android.intent.action.PROCESS_TEXT";
    public static final String ACTION_PROVIDER_CHANGED = "android.intent.action.PROVIDER_CHANGED";
    public static final String ACTION_QUERY_PACKAGE_RESTART = "android.intent.action.QUERY_PACKAGE_RESTART";
    public static final String ACTION_QUICK_CLOCK = "android.intent.action.QUICK_CLOCK";
    public static final String ACTION_QUICK_VIEW = "android.intent.action.QUICK_VIEW";
    public static final String ACTION_REBOOT = "android.intent.action.REBOOT";
    public static final String ACTION_REMOTE_INTENT = "com.google.android.c2dm.intent.RECEIVE";
    public static final String ACTION_REQUEST_SHUTDOWN = "com.android.internal.intent.action.REQUEST_SHUTDOWN";
    public static final String ACTION_RESOLVE_EPHEMERAL_PACKAGE = "android.intent.action.RESOLVE_EPHEMERAL_PACKAGE";
    public static final String ACTION_RESOLVE_INSTANT_APP_PACKAGE = "android.intent.action.RESOLVE_INSTANT_APP_PACKAGE";
    public static final String ACTION_REVIEW_PERMISSIONS = "android.intent.action.REVIEW_PERMISSIONS";
    public static final String ACTION_RUN = "android.intent.action.RUN";
    public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";
    public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";
    public static final String ACTION_SEARCH = "android.intent.action.SEARCH";
    public static final String ACTION_SEARCH_LONG_PRESS = "android.intent.action.SEARCH_LONG_PRESS";
    public static final String ACTION_SEND = "android.intent.action.SEND";
    public static final String ACTION_SENDTO = "android.intent.action.SENDTO";
    public static final String ACTION_SEND_MULTIPLE = "android.intent.action.SEND_MULTIPLE";
    public static final String ACTION_SERVICE_STATE = "android.intent.action.SERVICE_STATE";
    public static final String ACTION_SETTING_RESTORED = "android.os.action.SETTING_RESTORED";
    public static final String ACTION_SET_WALLPAPER = "android.intent.action.SET_WALLPAPER";
    public static final String ACTION_SHOW_APP_INFO = "android.intent.action.SHOW_APP_INFO";
    public static final String ACTION_SHOW_BRIGHTNESS_DIALOG = "com.android.intent.action.SHOW_BRIGHTNESS_DIALOG";
    public static final String ACTION_SHOW_KEYBOARD_SHORTCUTS = "com.android.intent.action.SHOW_KEYBOARD_SHORTCUTS";
    public static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    public static final String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    public static final String ACTION_SYNC = "android.intent.action.SYNC";
    public static final String ACTION_SYSTEM_TUTORIAL = "android.intent.action.SYSTEM_TUTORIAL";
    public static final String ACTION_THERMAL_EVENT = "android.intent.action.THERMAL_EVENT";
    public static final String ACTION_TIMEZONE_CHANGED = "android.intent.action.TIMEZONE_CHANGED";
    public static final String ACTION_TIME_CHANGED = "android.intent.action.TIME_SET";
    public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";
    public static final String ACTION_UID_REMOVED = "android.intent.action.UID_REMOVED";
    public static final String ACTION_UMS_CONNECTED = "android.intent.action.UMS_CONNECTED";
    public static final String ACTION_UMS_DISCONNECTED = "android.intent.action.UMS_DISCONNECTED";
    public static final String ACTION_UNINSTALL_PACKAGE = "android.intent.action.UNINSTALL_PACKAGE";
    public static final String ACTION_UPGRADE_SETUP = "android.intent.action.UPGRADE_SETUP";
    public static final String ACTION_USER_ADDED = "android.intent.action.USER_ADDED";
    public static final String ACTION_USER_BACKGROUND = "android.intent.action.USER_BACKGROUND";
    public static final String ACTION_USER_FOREGROUND = "android.intent.action.USER_FOREGROUND";
    public static final String ACTION_USER_INFO_CHANGED = "android.intent.action.USER_INFO_CHANGED";
    public static final String ACTION_USER_INITIALIZE = "android.intent.action.USER_INITIALIZE";
    public static final String ACTION_USER_PRESENT = "android.intent.action.USER_PRESENT";
    public static final String ACTION_USER_REMOVED = "android.intent.action.USER_REMOVED";
    public static final String ACTION_USER_STARTED = "android.intent.action.USER_STARTED";
    public static final String ACTION_USER_STARTING = "android.intent.action.USER_STARTING";
    public static final String ACTION_USER_STOPPED = "android.intent.action.USER_STOPPED";
    public static final String ACTION_USER_STOPPING = "android.intent.action.USER_STOPPING";
    public static final String ACTION_USER_SWITCHED = "android.intent.action.USER_SWITCHED";
    public static final String ACTION_USER_UNLOCKED = "android.intent.action.USER_UNLOCKED";
    public static final String ACTION_VIEW = "android.intent.action.VIEW";
    public static final String ACTION_VOICE_ASSIST = "android.intent.action.VOICE_ASSIST";
    public static final String ACTION_VOICE_COMMAND = "android.intent.action.VOICE_COMMAND";
    public static final String ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED";
    public static final String ACTION_WEB_SEARCH = "android.intent.action.WEB_SEARCH";
    private static final String ATTR_ACTION = "action";
    private static final String ATTR_CATEGORY = "category";
    private static final String ATTR_COMPONENT = "component";
    private static final String ATTR_DATA = "data";
    private static final String ATTR_FLAGS = "flags";
    private static final String ATTR_TYPE = "type";
    public static final String CATEGORY_ALTERNATIVE = "android.intent.category.ALTERNATIVE";
    public static final String CATEGORY_APP_BROWSER = "android.intent.category.APP_BROWSER";
    public static final String CATEGORY_APP_CALCULATOR = "android.intent.category.APP_CALCULATOR";
    public static final String CATEGORY_APP_CALENDAR = "android.intent.category.APP_CALENDAR";
    public static final String CATEGORY_APP_CONTACTS = "android.intent.category.APP_CONTACTS";
    public static final String CATEGORY_APP_EMAIL = "android.intent.category.APP_EMAIL";
    public static final String CATEGORY_APP_GALLERY = "android.intent.category.APP_GALLERY";
    public static final String CATEGORY_APP_MAPS = "android.intent.category.APP_MAPS";
    public static final String CATEGORY_APP_MARKET = "android.intent.category.APP_MARKET";
    public static final String CATEGORY_APP_MESSAGING = "android.intent.category.APP_MESSAGING";
    public static final String CATEGORY_APP_MUSIC = "android.intent.category.APP_MUSIC";
    public static final String CATEGORY_BROWSABLE = "android.intent.category.BROWSABLE";
    public static final String CATEGORY_CAR_DOCK = "android.intent.category.CAR_DOCK";
    public static final String CATEGORY_CAR_MODE = "android.intent.category.CAR_MODE";
    public static final String CATEGORY_DEFAULT = "android.intent.category.DEFAULT";
    public static final String CATEGORY_DESK_DOCK = "android.intent.category.DESK_DOCK";
    public static final String CATEGORY_DEVELOPMENT_PREFERENCE = "android.intent.category.DEVELOPMENT_PREFERENCE";
    public static final String CATEGORY_EMBED = "android.intent.category.EMBED";
    public static final String CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST = "android.intent.category.FRAMEWORK_INSTRUMENTATION_TEST";
    public static final String CATEGORY_HE_DESK_DOCK = "android.intent.category.HE_DESK_DOCK";
    public static final String CATEGORY_HOME = "android.intent.category.HOME";
    public static final String CATEGORY_HOME_MAIN = "android.intent.category.HOME_MAIN";
    public static final String CATEGORY_INFO = "android.intent.category.INFO";
    public static final String CATEGORY_LAUNCHER = "android.intent.category.LAUNCHER";
    public static final String CATEGORY_LAUNCHER_APP = "android.intent.category.LAUNCHER_APP";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String CATEGORY_LEANBACK_SETTINGS = "android.intent.category.LEANBACK_SETTINGS";
    public static final String CATEGORY_LE_DESK_DOCK = "android.intent.category.LE_DESK_DOCK";
    public static final String CATEGORY_MONKEY = "android.intent.category.MONKEY";
    public static final String CATEGORY_OPENABLE = "android.intent.category.OPENABLE";
    public static final String CATEGORY_PREFERENCE = "android.intent.category.PREFERENCE";
    public static final String CATEGORY_SAMPLE_CODE = "android.intent.category.SAMPLE_CODE";
    public static final String CATEGORY_SELECTED_ALTERNATIVE = "android.intent.category.SELECTED_ALTERNATIVE";
    public static final String CATEGORY_SETUP_WIZARD = "android.intent.category.SETUP_WIZARD";
    public static final String CATEGORY_TAB = "android.intent.category.TAB";
    public static final String CATEGORY_TEST = "android.intent.category.TEST";
    public static final String CATEGORY_TYPED_OPENABLE = "android.intent.category.TYPED_OPENABLE";
    public static final String CATEGORY_UNIT_TEST = "android.intent.category.UNIT_TEST";
    public static final String CATEGORY_VOICE = "android.intent.category.VOICE";
    public static final String CATEGORY_VR_HOME = "android.intent.category.VR_HOME";
    private static final int COPY_MODE_ALL = 0;
    private static final int COPY_MODE_FILTER = 1;
    private static final int COPY_MODE_HISTORY = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Intent createFromParcel(Parcel parcel)
        {
            return new Intent(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Intent[] newArray(int i)
        {
            return new Intent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_ALARM_COUNT = "android.intent.extra.ALARM_COUNT";
    public static final String EXTRA_ALLOW_MULTIPLE = "android.intent.extra.ALLOW_MULTIPLE";
    public static final String EXTRA_ALLOW_REPLACE = "android.intent.extra.ALLOW_REPLACE";
    public static final String EXTRA_ALTERNATE_INTENTS = "android.intent.extra.ALTERNATE_INTENTS";
    public static final String EXTRA_ASSIST_CONTEXT = "android.intent.extra.ASSIST_CONTEXT";
    public static final String EXTRA_ASSIST_INPUT_DEVICE_ID = "android.intent.extra.ASSIST_INPUT_DEVICE_ID";
    public static final String EXTRA_ASSIST_INPUT_HINT_KEYBOARD = "android.intent.extra.ASSIST_INPUT_HINT_KEYBOARD";
    public static final String EXTRA_ASSIST_PACKAGE = "android.intent.extra.ASSIST_PACKAGE";
    public static final String EXTRA_ASSIST_UID = "android.intent.extra.ASSIST_UID";
    public static final String EXTRA_AUTO_LAUNCH_SINGLE_CHOICE = "android.intent.extra.AUTO_LAUNCH_SINGLE_CHOICE";
    public static final String EXTRA_BCC = "android.intent.extra.BCC";
    public static final String EXTRA_BUG_REPORT = "android.intent.extra.BUG_REPORT";
    public static final String EXTRA_CALLING_PACKAGE = "android.intent.extra.CALLING_PACKAGE";
    public static final String EXTRA_CC = "android.intent.extra.CC";
    public static final String EXTRA_CDMA_DEFAULT_ROAMING_INDICATOR = "cdmaDefaultRoamingIndicator";
    public static final String EXTRA_CDMA_ROAMING_INDICATOR = "cdmaRoamingIndicator";
    public static final String EXTRA_CHANGED_COMPONENT_NAME = "android.intent.extra.changed_component_name";
    public static final String EXTRA_CHANGED_COMPONENT_NAME_LIST = "android.intent.extra.changed_component_name_list";
    public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
    public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
    public static final String EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER = "android.intent.extra.CHOOSER_REFINEMENT_INTENT_SENDER";
    public static final String EXTRA_CHOOSER_TARGETS = "android.intent.extra.CHOOSER_TARGETS";
    public static final String EXTRA_CHOSEN_COMPONENT = "android.intent.extra.CHOSEN_COMPONENT";
    public static final String EXTRA_CHOSEN_COMPONENT_INTENT_SENDER = "android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER";
    public static final String EXTRA_CLIENT_INTENT = "android.intent.extra.client_intent";
    public static final String EXTRA_CLIENT_LABEL = "android.intent.extra.client_label";
    public static final String EXTRA_COMPONENT_NAME = "android.intent.extra.COMPONENT_NAME";
    public static final String EXTRA_CONTENT_ANNOTATIONS = "android.intent.extra.CONTENT_ANNOTATIONS";
    public static final String EXTRA_CSS_INDICATOR = "cssIndicator";
    public static final String EXTRA_DATA_OPERATOR_ALPHA_LONG = "data-operator-alpha-long";
    public static final String EXTRA_DATA_OPERATOR_ALPHA_SHORT = "data-operator-alpha-short";
    public static final String EXTRA_DATA_OPERATOR_NUMERIC = "data-operator-numeric";
    public static final String EXTRA_DATA_RADIO_TECH = "dataRadioTechnology";
    public static final String EXTRA_DATA_REG_STATE = "dataRegState";
    public static final String EXTRA_DATA_REMOVED = "android.intent.extra.DATA_REMOVED";
    public static final String EXTRA_DATA_ROAMING_TYPE = "dataRoamingType";
    public static final String EXTRA_DOCK_STATE = "android.intent.extra.DOCK_STATE";
    public static final int EXTRA_DOCK_STATE_CAR = 2;
    public static final int EXTRA_DOCK_STATE_DESK = 1;
    public static final int EXTRA_DOCK_STATE_HE_DESK = 4;
    public static final int EXTRA_DOCK_STATE_LE_DESK = 3;
    public static final int EXTRA_DOCK_STATE_UNDOCKED = 0;
    public static final String EXTRA_DONT_KILL_APP = "android.intent.extra.DONT_KILL_APP";
    public static final String EXTRA_EMAIL = "android.intent.extra.EMAIL";
    public static final String EXTRA_EMERGENCY_ONLY = "emergencyOnly";
    public static final String EXTRA_EPHEMERAL_FAILURE = "android.intent.extra.EPHEMERAL_FAILURE";
    public static final String EXTRA_EPHEMERAL_HOSTNAME = "android.intent.extra.EPHEMERAL_HOSTNAME";
    public static final String EXTRA_EPHEMERAL_SUCCESS = "android.intent.extra.EPHEMERAL_SUCCESS";
    public static final String EXTRA_EPHEMERAL_TOKEN = "android.intent.extra.EPHEMERAL_TOKEN";
    public static final String EXTRA_EXCLUDE_COMPONENTS = "android.intent.extra.EXCLUDE_COMPONENTS";
    public static final String EXTRA_FORCE_FACTORY_RESET = "android.intent.extra.FORCE_FACTORY_RESET";
    public static final String EXTRA_FORCE_MASTER_CLEAR = "android.intent.extra.FORCE_MASTER_CLEAR";
    public static final String EXTRA_FROM_STORAGE = "android.intent.extra.FROM_STORAGE";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_INDEX = "android.intent.extra.INDEX";
    public static final String EXTRA_INITIAL_INTENTS = "android.intent.extra.INITIAL_INTENTS";
    public static final String EXTRA_INSTALLER_PACKAGE_NAME = "android.intent.extra.INSTALLER_PACKAGE_NAME";
    public static final String EXTRA_INSTALL_RESULT = "android.intent.extra.INSTALL_RESULT";
    public static final String EXTRA_INTENT = "android.intent.extra.INTENT";
    public static final String EXTRA_IS_DATA_ROAMING_FROM_REGISTRATION = "isDataRoamingFromRegistration";
    public static final String EXTRA_IS_USING_CARRIER_AGGREGATION = "isUsingCarrierAggregation";
    public static final String EXTRA_KEY_CONFIRM = "android.intent.extra.KEY_CONFIRM";
    public static final String EXTRA_KEY_EVENT = "android.intent.extra.KEY_EVENT";
    public static final String EXTRA_LOCAL_ONLY = "android.intent.extra.LOCAL_ONLY";
    public static final String EXTRA_LTE_EARFCN_RSRP_BOOST = "LteEarfcnRsrpBoost";
    public static final String EXTRA_MANUAL = "manual";
    public static final String EXTRA_MEDIA_RESOURCE_TYPE = "android.intent.extra.MEDIA_RESOURCE_TYPE";
    public static final int EXTRA_MEDIA_RESOURCE_TYPE_AUDIO_CODEC = 1;
    public static final int EXTRA_MEDIA_RESOURCE_TYPE_VIDEO_CODEC = 0;
    public static final String EXTRA_MIME_TYPES = "android.intent.extra.MIME_TYPES";
    public static final String EXTRA_NETWORK_ID = "networkId";
    public static final String EXTRA_NOT_UNKNOWN_SOURCE = "android.intent.extra.NOT_UNKNOWN_SOURCE";
    public static final String EXTRA_OPERATOR_ALPHA_LONG = "operator-alpha-long";
    public static final String EXTRA_OPERATOR_ALPHA_SHORT = "operator-alpha-short";
    public static final String EXTRA_OPERATOR_NUMERIC = "operator-numeric";
    public static final String EXTRA_ORIGINATING_UID = "android.intent.extra.ORIGINATING_UID";
    public static final String EXTRA_ORIGINATING_URI = "android.intent.extra.ORIGINATING_URI";
    public static final String EXTRA_PACKAGES = "android.intent.extra.PACKAGES";
    public static final String EXTRA_PACKAGE_NAME = "android.intent.extra.PACKAGE_NAME";
    public static final String EXTRA_PERMISSION_NAME = "android.intent.extra.PERMISSION_NAME";
    public static final String EXTRA_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";
    public static final String EXTRA_PROCESS_TEXT = "android.intent.extra.PROCESS_TEXT";
    public static final String EXTRA_PROCESS_TEXT_READONLY = "android.intent.extra.PROCESS_TEXT_READONLY";
    public static final String EXTRA_QUICK_VIEW_ADVANCED = "android.intent.extra.QUICK_VIEW_ADVANCED";
    public static final String EXTRA_QUICK_VIEW_FEATURES = "android.intent.extra.QUICK_VIEW_FEATURES";
    public static final String EXTRA_QUIET_MODE = "android.intent.extra.QUIET_MODE";
    public static final String EXTRA_REASON = "android.intent.extra.REASON";
    public static final String EXTRA_REFERRER = "android.intent.extra.REFERRER";
    public static final String EXTRA_REFERRER_NAME = "android.intent.extra.REFERRER_NAME";
    public static final String EXTRA_REMOTE_CALLBACK = "android.intent.extra.REMOTE_CALLBACK";
    public static final String EXTRA_REMOTE_INTENT_TOKEN = "android.intent.extra.remote_intent_token";
    public static final String EXTRA_REMOVED_FOR_ALL_USERS = "android.intent.extra.REMOVED_FOR_ALL_USERS";
    public static final String EXTRA_REPLACEMENT_EXTRAS = "android.intent.extra.REPLACEMENT_EXTRAS";
    public static final String EXTRA_REPLACING = "android.intent.extra.REPLACING";
    public static final String EXTRA_RESTRICTIONS_BUNDLE = "android.intent.extra.restrictions_bundle";
    public static final String EXTRA_RESTRICTIONS_INTENT = "android.intent.extra.restrictions_intent";
    public static final String EXTRA_RESTRICTIONS_LIST = "android.intent.extra.restrictions_list";
    public static final String EXTRA_RESULT_NEEDED = "android.intent.extra.RESULT_NEEDED";
    public static final String EXTRA_RESULT_RECEIVER = "android.intent.extra.RESULT_RECEIVER";
    public static final String EXTRA_RETURN_RESULT = "android.intent.extra.RETURN_RESULT";
    public static final String EXTRA_SETTING_NAME = "setting_name";
    public static final String EXTRA_SETTING_NEW_VALUE = "new_value";
    public static final String EXTRA_SETTING_PREVIOUS_VALUE = "previous_value";
    public static final String EXTRA_SETTING_RESTORED_FROM_SDK_INT = "restored_from_sdk_int";
    public static final String EXTRA_SHORTCUT_ICON = "android.intent.extra.shortcut.ICON";
    public static final String EXTRA_SHORTCUT_ICON_RESOURCE = "android.intent.extra.shortcut.ICON_RESOURCE";
    public static final String EXTRA_SHORTCUT_INTENT = "android.intent.extra.shortcut.INTENT";
    public static final String EXTRA_SHORTCUT_NAME = "android.intent.extra.shortcut.NAME";
    public static final String EXTRA_SHUTDOWN_USERSPACE_ONLY = "android.intent.extra.SHUTDOWN_USERSPACE_ONLY";
    public static final String EXTRA_SIM_ACTIVATION_RESPONSE = "android.intent.extra.SIM_ACTIVATION_RESPONSE";
    public static final String EXTRA_SPLIT_NAME = "android.intent.extra.SPLIT_NAME";
    public static final String EXTRA_STREAM = "android.intent.extra.STREAM";
    public static final String EXTRA_SUBJECT = "android.intent.extra.SUBJECT";
    public static final String EXTRA_SYSTEM_ID = "systemId";
    public static final String EXTRA_TASK_ID = "android.intent.extra.TASK_ID";
    public static final String EXTRA_TEMPLATE = "android.intent.extra.TEMPLATE";
    public static final String EXTRA_TEXT = "android.intent.extra.TEXT";
    public static final String EXTRA_THERMAL_STATE = "android.intent.extra.THERMAL_STATE";
    public static final int EXTRA_THERMAL_STATE_EXCEEDED = 2;
    public static final int EXTRA_THERMAL_STATE_NORMAL = 0;
    public static final int EXTRA_THERMAL_STATE_WARNING = 1;
    public static final String EXTRA_TIME_PREF_24_HOUR_FORMAT = "android.intent.extra.TIME_PREF_24_HOUR_FORMAT";
    public static final int EXTRA_TIME_PREF_VALUE_USE_12_HOUR = 0;
    public static final int EXTRA_TIME_PREF_VALUE_USE_24_HOUR = 1;
    public static final int EXTRA_TIME_PREF_VALUE_USE_LOCALE_DEFAULT = 2;
    public static final String EXTRA_TITLE = "android.intent.extra.TITLE";
    public static final String EXTRA_UID = "android.intent.extra.UID";
    public static final String EXTRA_UNINSTALL_ALL_USERS = "android.intent.extra.UNINSTALL_ALL_USERS";
    public static final String EXTRA_USER = "android.intent.extra.USER";
    public static final String EXTRA_USER_HANDLE = "android.intent.extra.user_handle";
    public static final String EXTRA_USER_ID = "android.intent.extra.USER_ID";
    public static final String EXTRA_USER_REQUESTED_SHUTDOWN = "android.intent.extra.USER_REQUESTED_SHUTDOWN";
    public static final String EXTRA_VERIFICATION_BUNDLE = "android.intent.extra.VERIFICATION_BUNDLE";
    public static final String EXTRA_VERSION_CODE = "android.intent.extra.VERSION_CODE";
    public static final String EXTRA_VOICE_RADIO_TECH = "radioTechnology";
    public static final String EXTRA_VOICE_REG_STATE = "voiceRegState";
    public static final String EXTRA_VOICE_ROAMING_TYPE = "voiceRoamingType";
    public static final String EXTRA_WIPE_ESIMS = "com.android.internal.intent.extra.WIPE_ESIMS";
    public static final String EXTRA_WIPE_EXTERNAL_STORAGE = "android.intent.extra.WIPE_EXTERNAL_STORAGE";
    public static final int FILL_IN_ACTION = 1;
    public static final int FILL_IN_CATEGORIES = 4;
    public static final int FILL_IN_CLIP_DATA = 128;
    public static final int FILL_IN_COMPONENT = 8;
    public static final int FILL_IN_DATA = 2;
    public static final int FILL_IN_PACKAGE = 16;
    public static final int FILL_IN_SELECTOR = 64;
    public static final int FILL_IN_SOURCE_BOUNDS = 32;
    public static final int FLAG_ACTIVITY_BROUGHT_TO_FRONT = 0x400000;
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
    public static final int FLAG_ACTIVITY_CLEAR_TOP = 0x4000000;
    public static final int FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET = 0x80000;
    public static final int FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = 0x800000;
    public static final int FLAG_ACTIVITY_FORWARD_RESULT = 0x2000000;
    public static final int FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY = 0x100000;
    public static final int FLAG_ACTIVITY_LAUNCH_ADJACENT = 4096;
    public static final int FLAG_ACTIVITY_MULTIPLE_TASK = 0x8000000;
    public static final int FLAG_ACTIVITY_NEW_DOCUMENT = 0x80000;
    public static final int FLAG_ACTIVITY_NEW_TASK = 0x10000000;
    public static final int FLAG_ACTIVITY_NO_ANIMATION = 0x10000;
    public static final int FLAG_ACTIVITY_NO_HISTORY = 0x40000000;
    public static final int FLAG_ACTIVITY_NO_USER_ACTION = 0x40000;
    public static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = 0x1000000;
    public static final int FLAG_ACTIVITY_REORDER_TO_FRONT = 0x20000;
    public static final int FLAG_ACTIVITY_RESET_TASK_IF_NEEDED = 0x200000;
    public static final int FLAG_ACTIVITY_RETAIN_IN_RECENTS = 8192;
    public static final int FLAG_ACTIVITY_SINGLE_TOP = 0x20000000;
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
    public static final int FLAG_DEBUG_LOG_RESOLUTION = 8;
    public static final int FLAG_DEBUG_TRIAGED_MISSING = 256;
    public static final int FLAG_EXCLUDE_STOPPED_PACKAGES = 16;
    public static final int FLAG_FROM_BACKGROUND = 4;
    public static final int FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 64;
    public static final int FLAG_GRANT_PREFIX_URI_PERMISSION = 128;
    public static final int FLAG_GRANT_READ_URI_PERMISSION = 1;
    public static final int FLAG_GRANT_WRITE_URI_PERMISSION = 2;
    public static final int FLAG_IGNORE_EPHEMERAL = 512;
    public static final int FLAG_INCLUDE_STOPPED_PACKAGES = 32;
    public static final int FLAG_RECEIVER_BOOT_UPGRADE = 0x2000000;
    public static final int FLAG_RECEIVER_EXCLUDE_BACKGROUND = 0x800000;
    public static final int FLAG_RECEIVER_FOREGROUND = 0x10000000;
    public static final int FLAG_RECEIVER_FROM_SHELL = 0x400000;
    public static final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 0x1000000;
    public static final int FLAG_RECEIVER_NO_ABORT = 0x8000000;
    public static final int FLAG_RECEIVER_REGISTERED_ONLY = 0x40000000;
    public static final int FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT = 0x4000000;
    public static final int FLAG_RECEIVER_REPLACE_PENDING = 0x20000000;
    public static final int FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS = 0x200000;
    public static final int IMMUTABLE_FLAGS = 195;
    public static final String METADATA_DOCK_HOME = "android.dock_home";
    public static final String METADATA_SETUP_VERSION = "android.SETUP_VERSION";
    private static final String TAG_CATEGORIES = "categories";
    private static final String TAG_EXTRA = "extra";
    public static final int URI_ALLOW_UNSAFE = 4;
    public static final int URI_ANDROID_APP_SCHEME = 2;
    public static final int URI_INTENT_SCHEME = 1;
    private String mAction;
    private ArraySet mCategories;
    private ClipData mClipData;
    private ComponentName mComponent;
    private int mContentUserHint;
    private Uri mData;
    private Bundle mExtras;
    private int mFlags;
    private String mLaunchToken;
    private int mMiuiFlags;
    private String mPackage;
    private Intent mSelector;
    private String mSenderPackageName;
    private Rect mSourceBounds;
    private String mType;

}
