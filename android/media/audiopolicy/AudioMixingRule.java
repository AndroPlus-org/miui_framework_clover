// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiopolicy;

import android.media.AudioAttributes;
import android.os.Parcel;
import android.util.Log;
import java.util.*;

public class AudioMixingRule
{
    static final class AudioMixMatchCriterion
    {

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                mAttr, Integer.valueOf(mIntProp), Integer.valueOf(mRule)
            });
        }

        void writeToParcel(Parcel parcel)
        {
            int i;
            parcel.writeInt(mRule);
            i = mRule & 0xffff7fff;
            i;
            JVM INSTR tableswitch 1 4: default 48
        //                       1 84
        //                       2 98
        //                       3 48
        //                       4 112;
               goto _L1 _L2 _L3 _L1 _L4
_L1:
            Log.e("AudioMixMatchCriterion", (new StringBuilder()).append("Unknown match rule").append(i).append(" when writing to Parcel").toString());
            parcel.writeInt(-1);
_L6:
            return;
_L2:
            parcel.writeInt(mAttr.getUsage());
            continue; /* Loop/switch isn't completed */
_L3:
            parcel.writeInt(mAttr.getCapturePreset());
            continue; /* Loop/switch isn't completed */
_L4:
            parcel.writeInt(mIntProp);
            if(true) goto _L6; else goto _L5
_L5:
        }

        final AudioAttributes mAttr;
        final int mIntProp;
        final int mRule;

        AudioMixMatchCriterion(AudioAttributes audioattributes, int i)
        {
            mAttr = audioattributes;
            mIntProp = 0x80000000;
            mRule = i;
        }

        AudioMixMatchCriterion(Integer integer, int i)
        {
            mAttr = null;
            mIntProp = integer.intValue();
            mRule = i;
        }
    }

    public static class Builder
    {

        private Builder addRuleInternal(AudioAttributes audioattributes, Integer integer, int i)
            throws IllegalArgumentException
        {
            if(mTargetMixType != -1) goto _L2; else goto _L1
_L1:
            ArrayList arraylist;
            Iterator iterator;
            int j;
            AudioMixMatchCriterion audiomixmatchcriterion;
            if(AudioMixingRule._2D_wrap1(i))
                mTargetMixType = 0;
            else
                mTargetMixType = 1;
_L13:
            arraylist = mCriteria;
            arraylist;
            JVM INSTR monitorenter ;
            iterator = mCriteria.iterator();
            j = i & 0xffff7fff;
_L5:
            if(!iterator.hasNext()) goto _L4; else goto _L3
_L3:
            audiomixmatchcriterion = (AudioMixMatchCriterion)iterator.next();
            j;
            JVM INSTR tableswitch 1 4: default 100
        //                       1 103
        //                       2 225
        //                       3 100
        //                       4 292;
               goto _L5 _L6 _L7 _L5 _L8
_L8:
            continue; /* Loop/switch isn't completed */
_L6:
            if(audiomixmatchcriterion.mAttr.getUsage() != audioattributes.getUsage()) goto _L5; else goto _L9
_L9:
            j = audiomixmatchcriterion.mRule;
            if(j != i) goto _L11; else goto _L10
_L10:
            arraylist;
            JVM INSTR monitorexit ;
            return this;
_L2:
            if((mTargetMixType != 0 || !(AudioMixingRule._2D_wrap1(i) ^ true)) && (mTargetMixType != 1 || !AudioMixingRule._2D_wrap1(i))) goto _L13; else goto _L12
_L12:
            throw new IllegalArgumentException("Incompatible rule for mix");
_L11:
            IllegalArgumentException illegalargumentexception = JVM INSTR new #28  <Class IllegalArgumentException>;
            integer = JVM INSTR new #69  <Class StringBuilder>;
            integer.StringBuilder();
            illegalargumentexception.IllegalArgumentException(integer.append("Contradictory rule exists for ").append(audioattributes).toString());
            throw illegalargumentexception;
            audioattributes;
            arraylist;
            JVM INSTR monitorexit ;
            throw audioattributes;
_L7:
            if(audiomixmatchcriterion.mAttr.getCapturePreset() != audioattributes.getCapturePreset()) goto _L5; else goto _L14
_L14:
            j = audiomixmatchcriterion.mRule;
            if(j != i) goto _L16; else goto _L15
_L15:
            arraylist;
            JVM INSTR monitorexit ;
            return this;
_L16:
            IllegalArgumentException illegalargumentexception1 = JVM INSTR new #28  <Class IllegalArgumentException>;
            integer = JVM INSTR new #69  <Class StringBuilder>;
            integer.StringBuilder();
            illegalargumentexception1.IllegalArgumentException(integer.append("Contradictory rule exists for ").append(audioattributes).toString());
            throw illegalargumentexception1;
            if(audiomixmatchcriterion.mIntProp != integer.intValue()) goto _L5; else goto _L17
_L17:
            j = audiomixmatchcriterion.mRule;
            if(j != i) goto _L19; else goto _L18
_L18:
            arraylist;
            JVM INSTR monitorexit ;
            return this;
_L19:
            IllegalArgumentException illegalargumentexception2 = JVM INSTR new #28  <Class IllegalArgumentException>;
            audioattributes = JVM INSTR new #69  <Class StringBuilder>;
            audioattributes.StringBuilder();
            illegalargumentexception2.IllegalArgumentException(audioattributes.append("Contradictory rule exists for UID ").append(integer).toString());
            throw illegalargumentexception2;
_L4:
            j;
            JVM INSTR tableswitch 1 4: default 388
        //                       1 400
        //                       2 400
        //                       3 388
        //                       4 428;
               goto _L20 _L21 _L21 _L20 _L22
_L20:
            audioattributes = JVM INSTR new #98  <Class IllegalStateException>;
            audioattributes.IllegalStateException("Unreachable code in addRuleInternal()");
            throw audioattributes;
_L21:
            ArrayList arraylist1 = mCriteria;
            integer = JVM INSTR new #49  <Class AudioMixingRule$AudioMixMatchCriterion>;
            integer.AudioMixMatchCriterion(audioattributes, i);
            arraylist1.add(integer);
_L23:
            arraylist;
            JVM INSTR monitorexit ;
            return this;
_L22:
            ArrayList arraylist2 = mCriteria;
            audioattributes = JVM INSTR new #49  <Class AudioMixingRule$AudioMixMatchCriterion>;
            audioattributes.AudioMixMatchCriterion(integer, i);
            arraylist2.add(audioattributes);
              goto _L23
        }

        private Builder checkAddRuleObjInternal(int i, Object obj)
            throws IllegalArgumentException
        {
            if(obj == null)
                throw new IllegalArgumentException("Illegal null argument for mixing rule");
            if(!AudioMixingRule._2D_wrap3(i))
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal rule value ").append(i).toString());
            if(AudioMixingRule._2D_wrap0(i & 0xffff7fff))
                if(!(obj instanceof AudioAttributes))
                    throw new IllegalArgumentException("Invalid AudioAttributes argument");
                else
                    return addRuleInternal((AudioAttributes)obj, null, i);
            if(!(obj instanceof Integer))
                throw new IllegalArgumentException("Invalid Integer argument");
            else
                return addRuleInternal(null, (Integer)obj, i);
        }

        public Builder addMixRule(int i, Object obj)
            throws IllegalArgumentException
        {
            if(!AudioMixingRule._2D_wrap4(i))
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal rule value ").append(i).toString());
            else
                return checkAddRuleObjInternal(i, obj);
        }

        public Builder addRule(AudioAttributes audioattributes, int i)
            throws IllegalArgumentException
        {
            if(!AudioMixingRule._2D_wrap2(i))
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal rule value ").append(i).toString());
            else
                return checkAddRuleObjInternal(i, audioattributes);
        }

        Builder addRuleFromParcel(Parcel parcel)
            throws IllegalArgumentException
        {
            int i;
            Object obj;
            Integer integer;
            i = parcel.readInt();
            obj = null;
            integer = null;
            i & 0xffff7fff;
            JVM INSTR tableswitch 1 4: default 44
        //                       1 81
        //                       2 112
        //                       3 44
        //                       4 137;
               goto _L1 _L2 _L3 _L1 _L4
_L1:
            parcel.readInt();
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal rule value ").append(i).append(" in parcel").toString());
_L2:
            int j = parcel.readInt();
            parcel = (new android.media.AudioAttributes.Builder()).setUsage(j).build();
_L6:
            return addRuleInternal(parcel, integer, i);
_L3:
            int k = parcel.readInt();
            parcel = (new android.media.AudioAttributes.Builder()).setInternalCapturePreset(k).build();
            continue; /* Loop/switch isn't completed */
_L4:
            integer = new Integer(parcel.readInt());
            parcel = obj;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public AudioMixingRule build()
        {
            return new AudioMixingRule(mTargetMixType, mCriteria, null);
        }

        public Builder excludeMixRule(int i, Object obj)
            throws IllegalArgumentException
        {
            if(!AudioMixingRule._2D_wrap4(i))
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal rule value ").append(i).toString());
            else
                return checkAddRuleObjInternal(0x8000 | i, obj);
        }

        public Builder excludeRule(AudioAttributes audioattributes, int i)
            throws IllegalArgumentException
        {
            if(!AudioMixingRule._2D_wrap2(i))
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal rule value ").append(i).toString());
            else
                return checkAddRuleObjInternal(0x8000 | i, audioattributes);
        }

        private ArrayList mCriteria;
        private int mTargetMixType;

        public Builder()
        {
            mTargetMixType = -1;
            mCriteria = new ArrayList();
        }
    }


    static boolean _2D_wrap0(int i)
    {
        return isAudioAttributeRule(i);
    }

    static boolean _2D_wrap1(int i)
    {
        return isPlayerRule(i);
    }

    static boolean _2D_wrap2(int i)
    {
        return isValidAttributesSystemApiRule(i);
    }

    static boolean _2D_wrap3(int i)
    {
        return isValidRule(i);
    }

    static boolean _2D_wrap4(int i)
    {
        return isValidSystemApiRule(i);
    }

    private AudioMixingRule(int i, ArrayList arraylist)
    {
        mCriteria = arraylist;
        mTargetMixType = i;
    }

    AudioMixingRule(int i, ArrayList arraylist, AudioMixingRule audiomixingrule)
    {
        this(i, arraylist);
    }

    private static boolean isAudioAttributeRule(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
            return true;
        }
    }

    private static boolean isPlayerRule(int i)
    {
        switch(i & 0xffff7fff)
        {
        case 2: // '\002'
        case 3: // '\003'
        default:
            return false;

        case 1: // '\001'
        case 4: // '\004'
            return true;
        }
    }

    private static boolean isValidAttributesSystemApiRule(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
            return true;
        }
    }

    private static boolean isValidRule(int i)
    {
        switch(i & 0xffff7fff)
        {
        case 3: // '\003'
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 4: // '\004'
            return true;
        }
    }

    private static boolean isValidSystemApiRule(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 4: // '\004'
            return true;
        }
    }

    ArrayList getCriteria()
    {
        return mCriteria;
    }

    int getTargetMixType()
    {
        return mTargetMixType;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mTargetMixType), mCriteria
        });
    }

    public static final int RULE_EXCLUDE_ATTRIBUTE_CAPTURE_PRESET = 32770;
    public static final int RULE_EXCLUDE_ATTRIBUTE_USAGE = 32769;
    public static final int RULE_EXCLUDE_UID = 32772;
    private static final int RULE_EXCLUSION_MASK = 32768;
    public static final int RULE_MATCH_ATTRIBUTE_CAPTURE_PRESET = 2;
    public static final int RULE_MATCH_ATTRIBUTE_USAGE = 1;
    public static final int RULE_MATCH_UID = 4;
    private final ArrayList mCriteria;
    private final int mTargetMixType;
}
