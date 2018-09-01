// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiopolicy;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.media.audiopolicy:
//            AudioMix, AudioMixingRule

public class AudioPolicyConfig
    implements Parcelable
{

    protected AudioPolicyConfig(AudioPolicyConfig audiopolicyconfig)
    {
        mDuckingPolicy = 0;
        mRegistrationId = null;
        mMixes = audiopolicyconfig.mMixes;
    }

    private AudioPolicyConfig(Parcel parcel)
    {
        mDuckingPolicy = 0;
        mRegistrationId = null;
        mMixes = new ArrayList();
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            AudioMix.Builder builder = new AudioMix.Builder();
            builder.setRouteFlags(parcel.readInt());
            builder.setCallbackFlags(parcel.readInt());
            builder.setDevice(parcel.readInt(), parcel.readString());
            int k = parcel.readInt();
            int l = parcel.readInt();
            int j1 = parcel.readInt();
            builder.setFormat((new android.media.AudioFormat.Builder()).setSampleRate(k).setChannelMask(j1).setEncoding(l).build());
            j1 = parcel.readInt();
            AudioMixingRule.Builder builder1 = new AudioMixingRule.Builder();
            for(int i1 = 0; i1 < j1; i1++)
                builder1.addRuleFromParcel(parcel);

            builder.setMixingRule(builder1.build());
            mMixes.add(builder.build());
        }

    }

    AudioPolicyConfig(Parcel parcel, AudioPolicyConfig audiopolicyconfig)
    {
        this(parcel);
    }

    AudioPolicyConfig(ArrayList arraylist)
    {
        mDuckingPolicy = 0;
        mRegistrationId = null;
        mMixes = arraylist;
    }

    private static String mixTypeId(int i)
    {
        if(i == 0)
            return "p";
        if(i == 1)
            return "r";
        else
            return "i";
    }

    public void addMix(AudioMix audiomix)
        throws IllegalArgumentException
    {
        if(audiomix == null)
        {
            throw new IllegalArgumentException("Illegal null AudioMix argument");
        } else
        {
            mMixes.add(audiomix);
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public ArrayList getMixes()
    {
        return mMixes;
    }

    protected String getRegistration()
    {
        return mRegistrationId;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mMixes
        });
    }

    protected void setRegistration(String s)
    {
        boolean flag;
        boolean flag1;
        if(mRegistrationId != null)
            flag = mRegistrationId.isEmpty();
        else
            flag = true;
        if(s != null)
            flag1 = s.isEmpty();
        else
            flag1 = true;
        if(!flag && flag1 ^ true && mRegistrationId.equals(s) ^ true)
        {
            Log.e("AudioPolicyConfig", (new StringBuilder()).append("Invalid registration transition from ").append(mRegistrationId).append(" to ").append(s).toString());
            return;
        }
        String s1 = s;
        if(s == null)
            s1 = "";
        mRegistrationId = s1;
        int i = 0;
        s = mMixes.iterator();
        do
        {
            if(!s.hasNext())
                break;
            AudioMix audiomix = (AudioMix)s.next();
            if(!mRegistrationId.isEmpty())
            {
                if((audiomix.getRouteFlags() & 2) == 2)
                {
                    audiomix.setRegistration((new StringBuilder()).append(mRegistrationId).append("mix").append(mixTypeId(audiomix.getMixType())).append(":").append(i).toString());
                    i++;
                } else
                if((audiomix.getRouteFlags() & 1) == 1)
                    audiomix.setRegistration(audiomix.mDeviceAddress);
            } else
            {
                audiomix.setRegistration("");
            }
        } while(true);
    }

    public String toLogFriendlyString()
    {
        Object obj;
        Iterator iterator;
        String s = new String("android.media.audiopolicy.AudioPolicyConfig:\n");
        obj = (new StringBuilder()).append(s).append(mMixes.size()).append(" AudioMix: ").append(mRegistrationId).append("\n").toString();
        iterator = mMixes.iterator();
_L2:
        String s1;
        Object obj1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_680;
        obj1 = (AudioMix)iterator.next();
        s1 = (new StringBuilder()).append(((String) (obj))).append("* route flags=0x").append(Integer.toHexString(((AudioMix) (obj1)).getRouteFlags())).append("\n").toString();
        s1 = (new StringBuilder()).append(s1).append("  rate=").append(((AudioMix) (obj1)).getFormat().getSampleRate()).append("Hz\n").toString();
        s1 = (new StringBuilder()).append(s1).append("  encoding=").append(((AudioMix) (obj1)).getFormat().getEncoding()).append("\n").toString();
        s1 = (new StringBuilder()).append(s1).append("  channels=0x").toString();
        s1 = (new StringBuilder()).append(s1).append(Integer.toHexString(((AudioMix) (obj1)).getFormat().getChannelMask()).toUpperCase()).append("\n").toString();
        obj1 = ((AudioMix) (obj1)).getRule().getCriteria().iterator();
_L10:
        obj = s1;
        if(!((Iterator) (obj1)).hasNext()) goto _L2; else goto _L1
_L1:
        obj = (AudioMixingRule.AudioMixMatchCriterion)((Iterator) (obj1)).next();
        ((AudioMixingRule.AudioMixMatchCriterion) (obj)).mRule;
        JVM INSTR lookupswitch 6: default 348
    //                   1: 441
    //                   2: 539
    //                   4: 588
    //                   32769: 392
    //                   32770: 490
    //                   32772: 634;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_634;
_L3:
        s1 = (new StringBuilder()).append(s1).append("invalid rule!").toString();
_L11:
        s1 = (new StringBuilder()).append(s1).append("\n").toString();
          goto _L10
_L7:
        s1 = (new StringBuilder()).append(s1).append("  exclude usage ").toString();
        s1 = (new StringBuilder()).append(s1).append(((AudioMixingRule.AudioMixMatchCriterion) (obj)).mAttr.usageToString()).toString();
          goto _L11
_L4:
        s1 = (new StringBuilder()).append(s1).append("  match usage ").toString();
        s1 = (new StringBuilder()).append(s1).append(((AudioMixingRule.AudioMixMatchCriterion) (obj)).mAttr.usageToString()).toString();
          goto _L11
_L8:
        s1 = (new StringBuilder()).append(s1).append("  exclude capture preset ").toString();
        s1 = (new StringBuilder()).append(s1).append(((AudioMixingRule.AudioMixMatchCriterion) (obj)).mAttr.getCapturePreset()).toString();
          goto _L11
_L5:
        s1 = (new StringBuilder()).append(s1).append("  match capture preset ").toString();
        s1 = (new StringBuilder()).append(s1).append(((AudioMixingRule.AudioMixMatchCriterion) (obj)).mAttr.getCapturePreset()).toString();
          goto _L11
_L6:
        s1 = (new StringBuilder()).append(s1).append("  match UID ").toString();
        s1 = (new StringBuilder()).append(s1).append(((AudioMixingRule.AudioMixMatchCriterion) (obj)).mIntProp).toString();
          goto _L11
        s1 = (new StringBuilder()).append(s1).append("  exclude UID ").toString();
        s1 = (new StringBuilder()).append(s1).append(((AudioMixingRule.AudioMixMatchCriterion) (obj)).mIntProp).toString();
          goto _L11
        return ((String) (obj));
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMixes.size());
        for(Iterator iterator = mMixes.iterator(); iterator.hasNext();)
        {
            Object obj = (AudioMix)iterator.next();
            parcel.writeInt(((AudioMix) (obj)).getRouteFlags());
            parcel.writeInt(((AudioMix) (obj)).mCallbackFlags);
            parcel.writeInt(((AudioMix) (obj)).mDeviceSystemType);
            parcel.writeString(((AudioMix) (obj)).mDeviceAddress);
            parcel.writeInt(((AudioMix) (obj)).getFormat().getSampleRate());
            parcel.writeInt(((AudioMix) (obj)).getFormat().getEncoding());
            parcel.writeInt(((AudioMix) (obj)).getFormat().getChannelMask());
            obj = ((AudioMix) (obj)).getRule().getCriteria();
            parcel.writeInt(((ArrayList) (obj)).size());
            obj = ((Iterable) (obj)).iterator();
            while(((Iterator) (obj)).hasNext()) 
                ((AudioMixingRule.AudioMixMatchCriterion)((Iterator) (obj)).next()).writeToParcel(parcel);
        }

    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AudioPolicyConfig createFromParcel(Parcel parcel)
        {
            return new AudioPolicyConfig(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AudioPolicyConfig[] newArray(int i)
        {
            return new AudioPolicyConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "AudioPolicyConfig";
    protected int mDuckingPolicy;
    protected ArrayList mMixes;
    private String mRegistrationId;

}
