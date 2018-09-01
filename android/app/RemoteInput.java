// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.net.Uri;
import android.os.*;
import android.util.ArraySet;
import java.util.*;

// Referenced classes of package android.app:
//            Notification

public final class RemoteInput
    implements Parcelable
{
    public static final class Builder
    {

        private void setFlag(int i, boolean flag)
        {
            if(flag)
                mFlags = mFlags | i;
            else
                mFlags = mFlags & i;
        }

        public Builder addExtras(Bundle bundle)
        {
            if(bundle != null)
                mExtras.putAll(bundle);
            return this;
        }

        public RemoteInput build()
        {
            return new RemoteInput(mResultKey, mLabel, mChoices, mFlags, mExtras, mAllowedDataTypes, null);
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public Builder setAllowDataType(String s, boolean flag)
        {
            if(flag)
                mAllowedDataTypes.add(s);
            else
                mAllowedDataTypes.remove(s);
            return this;
        }

        public Builder setAllowFreeFormInput(boolean flag)
        {
            setFlag(mFlags, flag);
            return this;
        }

        public Builder setChoices(CharSequence acharsequence[])
        {
            if(acharsequence == null)
            {
                mChoices = null;
            } else
            {
                mChoices = new CharSequence[acharsequence.length];
                int i = 0;
                while(i < acharsequence.length) 
                {
                    mChoices[i] = Notification.safeCharSequence(acharsequence[i]);
                    i++;
                }
            }
            return this;
        }

        public Builder setLabel(CharSequence charsequence)
        {
            mLabel = Notification.safeCharSequence(charsequence);
            return this;
        }

        private final ArraySet mAllowedDataTypes = new ArraySet();
        private CharSequence mChoices[];
        private Bundle mExtras;
        private int mFlags;
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(String s)
        {
            mFlags = 1;
            mExtras = new Bundle();
            if(s == null)
            {
                throw new IllegalArgumentException("Result key can't be null");
            } else
            {
                mResultKey = s;
                return;
            }
        }
    }


    private RemoteInput(Parcel parcel)
    {
        mResultKey = parcel.readString();
        mLabel = parcel.readCharSequence();
        mChoices = parcel.readCharSequenceArray();
        mFlags = parcel.readInt();
        mExtras = parcel.readBundle();
        mAllowedDataTypes = parcel.readArraySet(null);
    }

    RemoteInput(Parcel parcel, RemoteInput remoteinput)
    {
        this(parcel);
    }

    private RemoteInput(String s, CharSequence charsequence, CharSequence acharsequence[], int i, Bundle bundle, ArraySet arrayset)
    {
        mResultKey = s;
        mLabel = charsequence;
        mChoices = acharsequence;
        mFlags = i;
        mExtras = bundle;
        mAllowedDataTypes = arrayset;
    }

    RemoteInput(String s, CharSequence charsequence, CharSequence acharsequence[], int i, Bundle bundle, ArraySet arrayset, RemoteInput remoteinput)
    {
        this(s, charsequence, acharsequence, i, bundle, arrayset);
    }

    public static void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map)
    {
        Intent intent1 = getClipDataIntentFromIntent(intent);
        Intent intent2 = intent1;
        if(intent1 == null)
            intent2 = new Intent();
        Iterator iterator = map.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            map = (java.util.Map.Entry)iterator.next();
            String s = (String)map.getKey();
            Uri uri = (Uri)map.getValue();
            if(s != null)
            {
                Bundle bundle = intent2.getBundleExtra(getExtraResultsKeyForData(s));
                map = bundle;
                if(bundle == null)
                    map = new Bundle();
                map.putString(remoteinput.getResultKey(), uri.toString());
                intent2.putExtra(getExtraResultsKeyForData(s), map);
            }
        } while(true);
        intent.setClipData(ClipData.newIntent("android.remoteinput.results", intent2));
    }

    public static void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
    {
        Object obj = getClipDataIntentFromIntent(intent);
        Intent intent1 = ((Intent) (obj));
        if(obj == null)
            intent1 = new Intent();
        Bundle bundle1 = intent1.getBundleExtra("android.remoteinput.resultsData");
        obj = bundle1;
        if(bundle1 == null)
            obj = new Bundle();
        int i = 0;
        for(int j = aremoteinput.length; i < j; i++)
        {
            RemoteInput remoteinput = aremoteinput[i];
            Object obj1 = bundle.get(remoteinput.getResultKey());
            if(obj1 instanceof CharSequence)
                ((Bundle) (obj)).putCharSequence(remoteinput.getResultKey(), (CharSequence)obj1);
        }

        intent1.putExtra("android.remoteinput.resultsData", ((Bundle) (obj)));
        intent.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
    }

    private static Intent getClipDataIntentFromIntent(Intent intent)
    {
        ClipData clipdata = intent.getClipData();
        if(clipdata == null)
            return null;
        intent = clipdata.getDescription();
        if(!intent.hasMimeType("text/vnd.android.intent"))
            return null;
        if(!intent.getLabel().equals("android.remoteinput.results"))
            return null;
        else
            return clipdata.getItemAt(0).getIntent();
    }

    public static Map getDataResultsFromIntent(Intent intent, String s)
    {
        intent = getClipDataIntentFromIntent(intent);
        if(intent == null)
            return null;
        HashMap hashmap = new HashMap();
        Iterator iterator = intent.getExtras().keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s1 = (String)iterator.next();
            if(s1.startsWith("android.remoteinput.dataTypeResultsData"))
            {
                String s2 = s1.substring("android.remoteinput.dataTypeResultsData".length());
                if(s2 != null && !s2.isEmpty())
                {
                    s1 = intent.getBundleExtra(s1).getString(s);
                    if(s1 != null && !s1.isEmpty())
                        hashmap.put(s2, Uri.parse(s1));
                }
            }
        } while(true);
        intent = hashmap;
        if(hashmap.isEmpty())
            intent = null;
        return intent;
    }

    private static String getExtraResultsKeyForData(String s)
    {
        return (new StringBuilder()).append("android.remoteinput.dataTypeResultsData").append(s).toString();
    }

    public static Bundle getResultsFromIntent(Intent intent)
    {
        intent = getClipDataIntentFromIntent(intent);
        if(intent == null)
            return null;
        else
            return (Bundle)intent.getExtras().getParcelable("android.remoteinput.resultsData");
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean getAllowFreeFormInput()
    {
        boolean flag = false;
        if((mFlags & 1) != 0)
            flag = true;
        return flag;
    }

    public Set getAllowedDataTypes()
    {
        return mAllowedDataTypes;
    }

    public CharSequence[] getChoices()
    {
        return mChoices;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public String getResultKey()
    {
        return mResultKey;
    }

    public boolean isDataOnly()
    {
        boolean flag1;
label0:
        {
            boolean flag = false;
            flag1 = flag;
            if(getAllowFreeFormInput())
                break label0;
            if(getChoices() != null)
            {
                flag1 = flag;
                if(getChoices().length != 0)
                    break label0;
            }
            flag1 = getAllowedDataTypes().isEmpty() ^ true;
        }
        return flag1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mResultKey);
        parcel.writeCharSequence(mLabel);
        parcel.writeCharSequenceArray(mChoices);
        parcel.writeInt(mFlags);
        parcel.writeBundle(mExtras);
        parcel.writeArraySet(mAllowedDataTypes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RemoteInput createFromParcel(Parcel parcel)
        {
            return new RemoteInput(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RemoteInput[] newArray(int i)
        {
            return new RemoteInput[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int DEFAULT_FLAGS = 1;
    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    private static final int FLAG_ALLOW_FREE_FORM_INPUT = 1;
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private final ArraySet mAllowedDataTypes;
    private final CharSequence mChoices[];
    private final Bundle mExtras;
    private final int mFlags;
    private final CharSequence mLabel;
    private final String mResultKey;

}
