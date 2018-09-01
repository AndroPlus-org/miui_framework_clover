// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.soundtrigger;

import android.media.AudioFormat;
import android.os.*;
import android.system.OsConstants;
import java.util.*;

// Referenced classes of package android.hardware.soundtrigger:
//            SoundTriggerModule

public class SoundTrigger
{
    public static class ConfidenceLevel
        implements Parcelable
    {

        static ConfidenceLevel _2D_wrap0(Parcel parcel)
        {
            return fromParcel(parcel);
        }

        private static ConfidenceLevel fromParcel(Parcel parcel)
        {
            return new ConfidenceLevel(parcel.readInt(), parcel.readInt());
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (ConfidenceLevel)obj;
            if(confidenceLevel != ((ConfidenceLevel) (obj)).confidenceLevel)
                return false;
            return userId == ((ConfidenceLevel) (obj)).userId;
        }

        public int hashCode()
        {
            return (confidenceLevel + 31) * 31 + userId;
        }

        public String toString()
        {
            return (new StringBuilder()).append("ConfidenceLevel [userId=").append(userId).append(", confidenceLevel=").append(confidenceLevel).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(userId);
            parcel.writeInt(confidenceLevel);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ConfidenceLevel createFromParcel(Parcel parcel)
            {
                return ConfidenceLevel._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ConfidenceLevel[] newArray(int i)
            {
                return new ConfidenceLevel[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final int confidenceLevel;
        public final int userId;


        public ConfidenceLevel(int i, int j)
        {
            userId = i;
            confidenceLevel = j;
        }
    }

    public static class GenericRecognitionEvent extends RecognitionEvent
    {

        static GenericRecognitionEvent _2D_wrap0(Parcel parcel)
        {
            return fromParcelForGeneric(parcel);
        }

        private static GenericRecognitionEvent fromParcelForGeneric(Parcel parcel)
        {
            parcel = RecognitionEvent.fromParcel(parcel);
            return new GenericRecognitionEvent(((RecognitionEvent) (parcel)).status, ((RecognitionEvent) (parcel)).soundModelHandle, ((RecognitionEvent) (parcel)).captureAvailable, ((RecognitionEvent) (parcel)).captureSession, ((RecognitionEvent) (parcel)).captureDelayMs, ((RecognitionEvent) (parcel)).capturePreambleMs, ((RecognitionEvent) (parcel)).triggerInData, ((RecognitionEvent) (parcel)).captureFormat, ((RecognitionEvent) (parcel)).data);
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
            {
                return false;
            } else
            {
                RecognitionEvent recognitionevent = (RecognitionEvent)obj;
                return super.equals(obj);
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("GenericRecognitionEvent ::").append(super.toString()).toString();
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public GenericRecognitionEvent createFromParcel(Parcel parcel)
            {
                return GenericRecognitionEvent._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public GenericRecognitionEvent[] newArray(int i)
            {
                return new GenericRecognitionEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;


        public GenericRecognitionEvent(int i, int j, boolean flag, int k, int l, int i1, boolean flag1, 
                AudioFormat audioformat, byte abyte0[])
        {
            super(i, j, flag, k, l, i1, flag1, audioformat, abyte0);
        }
    }

    public static class GenericSoundModel extends SoundModel
        implements Parcelable
    {

        static GenericSoundModel _2D_wrap0(Parcel parcel)
        {
            return fromParcel(parcel);
        }

        private static GenericSoundModel fromParcel(Parcel parcel)
        {
            UUID uuid = UUID.fromString(parcel.readString());
            UUID uuid1 = null;
            if(parcel.readInt() >= 0)
                uuid1 = UUID.fromString(parcel.readString());
            return new GenericSoundModel(uuid, uuid1, parcel.readBlob());
        }

        public int describeContents()
        {
            return 0;
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("GenericSoundModel [uuid=").append(uuid).append(", vendorUuid=").append(vendorUuid).append(", type=").append(type).append(", data=");
            int i;
            if(data == null)
                i = 0;
            else
                i = data.length;
            return stringbuilder.append(i).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(uuid.toString());
            if(vendorUuid == null)
            {
                parcel.writeInt(-1);
            } else
            {
                parcel.writeInt(vendorUuid.toString().length());
                parcel.writeString(vendorUuid.toString());
            }
            parcel.writeBlob(data);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public GenericSoundModel createFromParcel(Parcel parcel)
            {
                return GenericSoundModel._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public GenericSoundModel[] newArray(int i)
            {
                return new GenericSoundModel[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;


        public GenericSoundModel(UUID uuid, UUID uuid1, byte abyte0[])
        {
            super(uuid, uuid1, 1, abyte0);
        }
    }

    public static class Keyphrase
        implements Parcelable
    {

        static Keyphrase _2D_wrap0(Parcel parcel)
        {
            return fromParcel(parcel);
        }

        private static Keyphrase fromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            String s = parcel.readString();
            String s1 = parcel.readString();
            int ai[] = null;
            int k = parcel.readInt();
            if(k >= 0)
            {
                ai = new int[k];
                parcel.readIntArray(ai);
            }
            return new Keyphrase(i, j, s, s1, ai);
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (Keyphrase)obj;
            if(text == null)
            {
                if(((Keyphrase) (obj)).text != null)
                    return false;
            } else
            if(!text.equals(((Keyphrase) (obj)).text))
                return false;
            if(id != ((Keyphrase) (obj)).id)
                return false;
            if(locale == null)
            {
                if(((Keyphrase) (obj)).locale != null)
                    return false;
            } else
            if(!locale.equals(((Keyphrase) (obj)).locale))
                return false;
            if(recognitionModes != ((Keyphrase) (obj)).recognitionModes)
                return false;
            return Arrays.equals(users, ((Keyphrase) (obj)).users);
        }

        public int hashCode()
        {
            int i = 0;
            int j;
            int k;
            if(text == null)
                j = 0;
            else
                j = text.hashCode();
            k = id;
            if(locale != null)
                i = locale.hashCode();
            return ((((j + 31) * 31 + k) * 31 + i) * 31 + recognitionModes) * 31 + Arrays.hashCode(users);
        }

        public String toString()
        {
            return (new StringBuilder()).append("Keyphrase [id=").append(id).append(", recognitionModes=").append(recognitionModes).append(", locale=").append(locale).append(", text=").append(text).append(", users=").append(Arrays.toString(users)).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(id);
            parcel.writeInt(recognitionModes);
            parcel.writeString(locale);
            parcel.writeString(text);
            if(users != null)
            {
                parcel.writeInt(users.length);
                parcel.writeIntArray(users);
            } else
            {
                parcel.writeInt(-1);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Keyphrase createFromParcel(Parcel parcel)
            {
                return Keyphrase._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Keyphrase[] newArray(int i)
            {
                return new Keyphrase[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final int id;
        public final String locale;
        public final int recognitionModes;
        public final String text;
        public final int users[];


        public Keyphrase(int i, int j, String s, String s1, int ai[])
        {
            id = i;
            recognitionModes = j;
            locale = s;
            text = s1;
            users = ai;
        }
    }

    public static class KeyphraseRecognitionEvent extends RecognitionEvent
    {

        static KeyphraseRecognitionEvent _2D_wrap0(Parcel parcel)
        {
            return fromParcelForKeyphrase(parcel);
        }

        private static KeyphraseRecognitionEvent fromParcelForKeyphrase(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            boolean flag;
            int k;
            int l;
            int i1;
            boolean flag1;
            AudioFormat audioformat;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            k = parcel.readInt();
            l = parcel.readInt();
            i1 = parcel.readInt();
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            audioformat = null;
            if(parcel.readByte() == 1)
            {
                int j1 = parcel.readInt();
                int k1 = parcel.readInt();
                int l1 = parcel.readInt();
                audioformat = (new android.media.AudioFormat.Builder()).setChannelMask(l1).setEncoding(k1).setSampleRate(j1).build();
            }
            return new KeyphraseRecognitionEvent(i, j, flag, k, l, i1, flag1, audioformat, parcel.readBlob(), (KeyphraseRecognitionExtra[])parcel.createTypedArray(KeyphraseRecognitionExtra.CREATOR));
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!super.equals(obj))
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (KeyphraseRecognitionEvent)obj;
            return Arrays.equals(keyphraseExtras, ((KeyphraseRecognitionEvent) (obj)).keyphraseExtras);
        }

        public int hashCode()
        {
            return super.hashCode() * 31 + Arrays.hashCode(keyphraseExtras);
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("KeyphraseRecognitionEvent [keyphraseExtras=").append(Arrays.toString(keyphraseExtras)).append(", status=").append(status).append(", soundModelHandle=").append(soundModelHandle).append(", captureAvailable=").append(captureAvailable).append(", captureSession=").append(captureSession).append(", captureDelayMs=").append(captureDelayMs).append(", capturePreambleMs=").append(capturePreambleMs).append(", triggerInData=").append(triggerInData);
            Object obj;
            int i;
            if(captureFormat == null)
                obj = "";
            else
                obj = (new StringBuilder()).append(", sampleRate=").append(captureFormat.getSampleRate()).toString();
            stringbuilder = stringbuilder.append(((String) (obj)));
            if(captureFormat == null)
                obj = "";
            else
                obj = (new StringBuilder()).append(", encoding=").append(captureFormat.getEncoding()).toString();
            stringbuilder = stringbuilder.append(((String) (obj)));
            if(captureFormat == null)
                obj = "";
            else
                obj = (new StringBuilder()).append(", channelMask=").append(captureFormat.getChannelMask()).toString();
            obj = stringbuilder.append(((String) (obj))).append(", data=");
            if(data == null)
                i = 0;
            else
                i = data.length;
            return ((StringBuilder) (obj)).append(i).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(status);
            parcel.writeInt(soundModelHandle);
            int j;
            if(captureAvailable)
                j = 1;
            else
                j = 0;
            parcel.writeByte((byte)j);
            parcel.writeInt(captureSession);
            parcel.writeInt(captureDelayMs);
            parcel.writeInt(capturePreambleMs);
            if(triggerInData)
                j = 1;
            else
                j = 0;
            parcel.writeByte((byte)j);
            if(captureFormat != null)
            {
                parcel.writeByte((byte)1);
                parcel.writeInt(captureFormat.getSampleRate());
                parcel.writeInt(captureFormat.getEncoding());
                parcel.writeInt(captureFormat.getChannelMask());
            } else
            {
                parcel.writeByte((byte)0);
            }
            parcel.writeBlob(data);
            parcel.writeTypedArray(keyphraseExtras, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public KeyphraseRecognitionEvent createFromParcel(Parcel parcel)
            {
                return KeyphraseRecognitionEvent._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public KeyphraseRecognitionEvent[] newArray(int i)
            {
                return new KeyphraseRecognitionEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final KeyphraseRecognitionExtra keyphraseExtras[];


        public KeyphraseRecognitionEvent(int i, int j, boolean flag, int k, int l, int i1, boolean flag1, 
                AudioFormat audioformat, byte abyte0[], KeyphraseRecognitionExtra akeyphraserecognitionextra[])
        {
            super(i, j, flag, k, l, i1, flag1, audioformat, abyte0);
            keyphraseExtras = akeyphraserecognitionextra;
        }
    }

    public static class KeyphraseRecognitionExtra
        implements Parcelable
    {

        static KeyphraseRecognitionExtra _2D_wrap0(Parcel parcel)
        {
            return fromParcel(parcel);
        }

        private static KeyphraseRecognitionExtra fromParcel(Parcel parcel)
        {
            return new KeyphraseRecognitionExtra(parcel.readInt(), parcel.readInt(), parcel.readInt(), (ConfidenceLevel[])parcel.createTypedArray(ConfidenceLevel.CREATOR));
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (KeyphraseRecognitionExtra)obj;
            if(!Arrays.equals(confidenceLevels, ((KeyphraseRecognitionExtra) (obj)).confidenceLevels))
                return false;
            if(id != ((KeyphraseRecognitionExtra) (obj)).id)
                return false;
            if(recognitionModes != ((KeyphraseRecognitionExtra) (obj)).recognitionModes)
                return false;
            return coarseConfidenceLevel == ((KeyphraseRecognitionExtra) (obj)).coarseConfidenceLevel;
        }

        public int hashCode()
        {
            return (((Arrays.hashCode(confidenceLevels) + 31) * 31 + id) * 31 + recognitionModes) * 31 + coarseConfidenceLevel;
        }

        public String toString()
        {
            return (new StringBuilder()).append("KeyphraseRecognitionExtra [id=").append(id).append(", recognitionModes=").append(recognitionModes).append(", coarseConfidenceLevel=").append(coarseConfidenceLevel).append(", confidenceLevels=").append(Arrays.toString(confidenceLevels)).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(id);
            parcel.writeInt(recognitionModes);
            parcel.writeInt(coarseConfidenceLevel);
            parcel.writeTypedArray(confidenceLevels, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public KeyphraseRecognitionExtra createFromParcel(Parcel parcel)
            {
                return KeyphraseRecognitionExtra._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public KeyphraseRecognitionExtra[] newArray(int i)
            {
                return new KeyphraseRecognitionExtra[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final int coarseConfidenceLevel;
        public final ConfidenceLevel confidenceLevels[];
        public final int id;
        public final int recognitionModes;


        public KeyphraseRecognitionExtra(int i, int j, int k, ConfidenceLevel aconfidencelevel[])
        {
            id = i;
            recognitionModes = j;
            coarseConfidenceLevel = k;
            confidenceLevels = aconfidencelevel;
        }
    }

    public static class KeyphraseSoundModel extends SoundModel
        implements Parcelable
    {

        static KeyphraseSoundModel _2D_wrap0(Parcel parcel)
        {
            return fromParcel(parcel);
        }

        private static KeyphraseSoundModel fromParcel(Parcel parcel)
        {
            UUID uuid = UUID.fromString(parcel.readString());
            UUID uuid1 = null;
            if(parcel.readInt() >= 0)
                uuid1 = UUID.fromString(parcel.readString());
            return new KeyphraseSoundModel(uuid, uuid1, parcel.readBlob(), (Keyphrase[])parcel.createTypedArray(Keyphrase.CREATOR));
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!super.equals(obj))
                return false;
            if(!(obj instanceof KeyphraseSoundModel))
                return false;
            obj = (KeyphraseSoundModel)obj;
            return Arrays.equals(keyphrases, ((KeyphraseSoundModel) (obj)).keyphrases);
        }

        public int hashCode()
        {
            return super.hashCode() * 31 + Arrays.hashCode(keyphrases);
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("KeyphraseSoundModel [keyphrases=").append(Arrays.toString(keyphrases)).append(", uuid=").append(uuid).append(", vendorUuid=").append(vendorUuid).append(", type=").append(type).append(", data=");
            int i;
            if(data == null)
                i = 0;
            else
                i = data.length;
            return stringbuilder.append(i).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(uuid.toString());
            if(vendorUuid == null)
            {
                parcel.writeInt(-1);
            } else
            {
                parcel.writeInt(vendorUuid.toString().length());
                parcel.writeString(vendorUuid.toString());
            }
            parcel.writeBlob(data);
            parcel.writeTypedArray(keyphrases, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public KeyphraseSoundModel createFromParcel(Parcel parcel)
            {
                return KeyphraseSoundModel._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public KeyphraseSoundModel[] newArray(int i)
            {
                return new KeyphraseSoundModel[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final Keyphrase keyphrases[];


        public KeyphraseSoundModel(UUID uuid, UUID uuid1, byte abyte0[], Keyphrase akeyphrase[])
        {
            super(uuid, uuid1, 0, abyte0);
            keyphrases = akeyphrase;
        }
    }

    public static class ModuleProperties
        implements Parcelable
    {

        static ModuleProperties _2D_wrap0(Parcel parcel)
        {
            return fromParcel(parcel);
        }

        private static ModuleProperties fromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            String s = parcel.readString();
            String s1 = parcel.readString();
            String s2 = parcel.readString();
            int j = parcel.readInt();
            int k = parcel.readInt();
            int l = parcel.readInt();
            int i1 = parcel.readInt();
            int j1 = parcel.readInt();
            boolean flag;
            int k1;
            boolean flag1;
            int l1;
            boolean flag2;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            k1 = parcel.readInt();
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            l1 = parcel.readInt();
            if(parcel.readByte() == 1)
                flag2 = true;
            else
                flag2 = false;
            return new ModuleProperties(i, s, s1, s2, j, k, l, i1, j1, flag, k1, flag1, l1, flag2);
        }

        public int describeContents()
        {
            return 0;
        }

        public String toString()
        {
            return (new StringBuilder()).append("ModuleProperties [id=").append(id).append(", implementor=").append(implementor).append(", description=").append(description).append(", uuid=").append(uuid).append(", version=").append(version).append(", maxSoundModels=").append(maxSoundModels).append(", maxKeyphrases=").append(maxKeyphrases).append(", maxUsers=").append(maxUsers).append(", recognitionModes=").append(recognitionModes).append(", supportsCaptureTransition=").append(supportsCaptureTransition).append(", maxBufferMs=").append(maxBufferMs).append(", supportsConcurrentCapture=").append(supportsConcurrentCapture).append(", powerConsumptionMw=").append(powerConsumptionMw).append(", returnsTriggerInEvent=").append(returnsTriggerInEvent).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeInt(id);
            parcel.writeString(implementor);
            parcel.writeString(description);
            parcel.writeString(uuid.toString());
            parcel.writeInt(version);
            parcel.writeInt(maxSoundModels);
            parcel.writeInt(maxKeyphrases);
            parcel.writeInt(maxUsers);
            parcel.writeInt(recognitionModes);
            if(supportsCaptureTransition)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            parcel.writeInt(maxBufferMs);
            if(supportsConcurrentCapture)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            parcel.writeInt(powerConsumptionMw);
            if(returnsTriggerInEvent)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeByte((byte)i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ModuleProperties createFromParcel(Parcel parcel)
            {
                return ModuleProperties._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ModuleProperties[] newArray(int i)
            {
                return new ModuleProperties[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final String description;
        public final int id;
        public final String implementor;
        public final int maxBufferMs;
        public final int maxKeyphrases;
        public final int maxSoundModels;
        public final int maxUsers;
        public final int powerConsumptionMw;
        public final int recognitionModes;
        public final boolean returnsTriggerInEvent;
        public final boolean supportsCaptureTransition;
        public final boolean supportsConcurrentCapture;
        public final UUID uuid;
        public final int version;


        ModuleProperties(int i, String s, String s1, String s2, int j, int k, int l, 
                int i1, int j1, boolean flag, int k1, boolean flag1, int l1, boolean flag2)
        {
            id = i;
            implementor = s;
            description = s1;
            uuid = UUID.fromString(s2);
            version = j;
            maxSoundModels = k;
            maxKeyphrases = l;
            maxUsers = i1;
            recognitionModes = j1;
            supportsCaptureTransition = flag;
            maxBufferMs = k1;
            supportsConcurrentCapture = flag1;
            powerConsumptionMw = l1;
            returnsTriggerInEvent = flag2;
        }
    }

    public static class RecognitionConfig
        implements Parcelable
    {

        static RecognitionConfig _2D_wrap0(Parcel parcel)
        {
            return fromParcel(parcel);
        }

        private static RecognitionConfig fromParcel(Parcel parcel)
        {
            boolean flag;
            boolean flag1;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            return new RecognitionConfig(flag, flag1, (KeyphraseRecognitionExtra[])parcel.createTypedArray(KeyphraseRecognitionExtra.CREATOR), parcel.readBlob());
        }

        public int describeContents()
        {
            return 0;
        }

        public String toString()
        {
            return (new StringBuilder()).append("RecognitionConfig [captureRequested=").append(captureRequested).append(", allowMultipleTriggers=").append(allowMultipleTriggers).append(", keyphrases=").append(Arrays.toString(keyphrases)).append(", data=").append(Arrays.toString(data)).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            int j;
            if(captureRequested)
                j = 1;
            else
                j = 0;
            parcel.writeByte((byte)j);
            if(allowMultipleTriggers)
                j = ((flag) ? 1 : 0);
            else
                j = 0;
            parcel.writeByte((byte)j);
            parcel.writeTypedArray(keyphrases, i);
            parcel.writeBlob(data);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RecognitionConfig createFromParcel(Parcel parcel)
            {
                return RecognitionConfig._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RecognitionConfig[] newArray(int i)
            {
                return new RecognitionConfig[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final boolean allowMultipleTriggers;
        public final boolean captureRequested;
        public final byte data[];
        public final KeyphraseRecognitionExtra keyphrases[];


        public RecognitionConfig(boolean flag, boolean flag1, KeyphraseRecognitionExtra akeyphraserecognitionextra[], byte abyte0[])
        {
            captureRequested = flag;
            allowMultipleTriggers = flag1;
            keyphrases = akeyphraserecognitionextra;
            data = abyte0;
        }
    }

    public static class RecognitionEvent
        implements Parcelable
    {

        protected static RecognitionEvent fromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            boolean flag;
            int k;
            int l;
            int i1;
            boolean flag1;
            AudioFormat audioformat;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            k = parcel.readInt();
            l = parcel.readInt();
            i1 = parcel.readInt();
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            audioformat = null;
            if(parcel.readByte() == 1)
            {
                int j1 = parcel.readInt();
                int k1 = parcel.readInt();
                int l1 = parcel.readInt();
                audioformat = (new android.media.AudioFormat.Builder()).setChannelMask(l1).setEncoding(k1).setSampleRate(j1).build();
            }
            return new RecognitionEvent(i, j, flag, k, l, i1, flag1, audioformat, parcel.readBlob());
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (RecognitionEvent)obj;
            if(captureAvailable != ((RecognitionEvent) (obj)).captureAvailable)
                return false;
            if(captureDelayMs != ((RecognitionEvent) (obj)).captureDelayMs)
                return false;
            if(capturePreambleMs != ((RecognitionEvent) (obj)).capturePreambleMs)
                return false;
            if(captureSession != ((RecognitionEvent) (obj)).captureSession)
                return false;
            if(!Arrays.equals(data, ((RecognitionEvent) (obj)).data))
                return false;
            if(soundModelHandle != ((RecognitionEvent) (obj)).soundModelHandle)
                return false;
            if(status != ((RecognitionEvent) (obj)).status)
                return false;
            if(triggerInData != ((RecognitionEvent) (obj)).triggerInData)
                return false;
            if(captureFormat == null)
            {
                if(((RecognitionEvent) (obj)).captureFormat != null)
                    return false;
            } else
            {
                if(((RecognitionEvent) (obj)).captureFormat == null)
                    return false;
                if(captureFormat.getSampleRate() != ((RecognitionEvent) (obj)).captureFormat.getSampleRate())
                    return false;
                if(captureFormat.getEncoding() != ((RecognitionEvent) (obj)).captureFormat.getEncoding())
                    return false;
                if(captureFormat.getChannelMask() != ((RecognitionEvent) (obj)).captureFormat.getChannelMask())
                    return false;
            }
            return true;
        }

        public int hashCode()
        {
            int i = 1231;
            int j;
            int k;
            int l;
            int i1;
            if(captureAvailable)
                j = 1231;
            else
                j = 1237;
            k = captureDelayMs;
            l = capturePreambleMs;
            i1 = captureSession;
            if(!triggerInData)
                i = 1237;
            i = ((((j + 31) * 31 + k) * 31 + l) * 31 + i1) * 31 + i;
            j = i;
            if(captureFormat != null)
                j = ((i * 31 + captureFormat.getSampleRate()) * 31 + captureFormat.getEncoding()) * 31 + captureFormat.getChannelMask();
            return ((j * 31 + Arrays.hashCode(data)) * 31 + soundModelHandle) * 31 + status;
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("RecognitionEvent [status=").append(status).append(", soundModelHandle=").append(soundModelHandle).append(", captureAvailable=").append(captureAvailable).append(", captureSession=").append(captureSession).append(", captureDelayMs=").append(captureDelayMs).append(", capturePreambleMs=").append(capturePreambleMs).append(", triggerInData=").append(triggerInData);
            Object obj;
            int i;
            if(captureFormat == null)
                obj = "";
            else
                obj = (new StringBuilder()).append(", sampleRate=").append(captureFormat.getSampleRate()).toString();
            stringbuilder = stringbuilder.append(((String) (obj)));
            if(captureFormat == null)
                obj = "";
            else
                obj = (new StringBuilder()).append(", encoding=").append(captureFormat.getEncoding()).toString();
            stringbuilder = stringbuilder.append(((String) (obj)));
            if(captureFormat == null)
                obj = "";
            else
                obj = (new StringBuilder()).append(", channelMask=").append(captureFormat.getChannelMask()).toString();
            obj = stringbuilder.append(((String) (obj))).append(", data=");
            if(data == null)
                i = 0;
            else
                i = data.length;
            return ((StringBuilder) (obj)).append(i).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(status);
            parcel.writeInt(soundModelHandle);
            if(captureAvailable)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            parcel.writeInt(captureSession);
            parcel.writeInt(captureDelayMs);
            parcel.writeInt(capturePreambleMs);
            if(triggerInData)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(captureFormat != null)
            {
                parcel.writeByte((byte)1);
                parcel.writeInt(captureFormat.getSampleRate());
                parcel.writeInt(captureFormat.getEncoding());
                parcel.writeInt(captureFormat.getChannelMask());
            } else
            {
                parcel.writeByte((byte)0);
            }
            parcel.writeBlob(data);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RecognitionEvent createFromParcel(Parcel parcel)
            {
                return RecognitionEvent.fromParcel(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RecognitionEvent[] newArray(int i)
            {
                return new RecognitionEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final boolean captureAvailable;
        public final int captureDelayMs;
        public AudioFormat captureFormat;
        public final int capturePreambleMs;
        public final int captureSession;
        public final byte data[];
        public final int soundModelHandle;
        public final int status;
        public final boolean triggerInData;


        public RecognitionEvent(int i, int j, boolean flag, int k, int l, int i1, boolean flag1, 
                AudioFormat audioformat, byte abyte0[])
        {
            status = i;
            soundModelHandle = j;
            captureAvailable = flag;
            captureSession = k;
            captureDelayMs = l;
            capturePreambleMs = i1;
            triggerInData = flag1;
            captureFormat = audioformat;
            data = abyte0;
        }
    }

    public static class SoundModel
    {

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(!(obj instanceof SoundModel))
                return false;
            obj = (SoundModel)obj;
            if(!Arrays.equals(data, ((SoundModel) (obj)).data))
                return false;
            if(type != ((SoundModel) (obj)).type)
                return false;
            if(uuid == null)
            {
                if(((SoundModel) (obj)).uuid != null)
                    return false;
            } else
            if(!uuid.equals(((SoundModel) (obj)).uuid))
                return false;
            if(vendorUuid == null)
            {
                if(((SoundModel) (obj)).vendorUuid != null)
                    return false;
            } else
            if(!vendorUuid.equals(((SoundModel) (obj)).vendorUuid))
                return false;
            return true;
        }

        public int hashCode()
        {
            int i = 0;
            int j = Arrays.hashCode(data);
            int k = type;
            int l;
            if(uuid == null)
                l = 0;
            else
                l = uuid.hashCode();
            if(vendorUuid != null)
                i = vendorUuid.hashCode();
            return (((j + 31) * 31 + k) * 31 + l) * 31 + i;
        }

        public static final int TYPE_GENERIC_SOUND = 1;
        public static final int TYPE_KEYPHRASE = 0;
        public static final int TYPE_UNKNOWN = -1;
        public final byte data[];
        public final int type;
        public final UUID uuid;
        public final UUID vendorUuid;

        public SoundModel(UUID uuid1, UUID uuid2, int i, byte abyte0[])
        {
            uuid = uuid1;
            vendorUuid = uuid2;
            type = i;
            data = abyte0;
        }
    }

    public static class SoundModelEvent
        implements Parcelable
    {

        static SoundModelEvent _2D_wrap0(Parcel parcel)
        {
            return fromParcel(parcel);
        }

        private static SoundModelEvent fromParcel(Parcel parcel)
        {
            return new SoundModelEvent(parcel.readInt(), parcel.readInt(), parcel.readBlob());
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (SoundModelEvent)obj;
            if(!Arrays.equals(data, ((SoundModelEvent) (obj)).data))
                return false;
            if(soundModelHandle != ((SoundModelEvent) (obj)).soundModelHandle)
                return false;
            return status == ((SoundModelEvent) (obj)).status;
        }

        public int hashCode()
        {
            return ((Arrays.hashCode(data) + 31) * 31 + soundModelHandle) * 31 + status;
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("SoundModelEvent [status=").append(status).append(", soundModelHandle=").append(soundModelHandle).append(", data=");
            int i;
            if(data == null)
                i = 0;
            else
                i = data.length;
            return stringbuilder.append(i).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(status);
            parcel.writeInt(soundModelHandle);
            parcel.writeBlob(data);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SoundModelEvent createFromParcel(Parcel parcel)
            {
                return SoundModelEvent._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SoundModelEvent[] newArray(int i)
            {
                return new SoundModelEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final byte data[];
        public final int soundModelHandle;
        public final int status;


        SoundModelEvent(int i, int j, byte abyte0[])
        {
            status = i;
            soundModelHandle = j;
            data = abyte0;
        }
    }

    public static interface StatusListener
    {

        public abstract void onRecognition(RecognitionEvent recognitionevent);

        public abstract void onServiceDied();

        public abstract void onServiceStateChange(int i);

        public abstract void onSoundModelUpdate(SoundModelEvent soundmodelevent);
    }


    public SoundTrigger()
    {
    }

    public static SoundTriggerModule attachModule(int i, StatusListener statuslistener, Handler handler)
    {
        if(statuslistener == null)
            return null;
        else
            return new SoundTriggerModule(i, statuslistener, handler);
    }

    public static native int listModules(ArrayList arraylist);

    public static final int RECOGNITION_MODE_USER_AUTHENTICATION = 4;
    public static final int RECOGNITION_MODE_USER_IDENTIFICATION = 2;
    public static final int RECOGNITION_MODE_VOICE_TRIGGER = 1;
    public static final int RECOGNITION_STATUS_ABORT = 1;
    public static final int RECOGNITION_STATUS_FAILURE = 2;
    public static final int RECOGNITION_STATUS_SUCCESS = 0;
    public static final int SERVICE_STATE_DISABLED = 1;
    public static final int SERVICE_STATE_ENABLED = 0;
    public static final int SOUNDMODEL_STATUS_UPDATED = 0;
    public static final int STATUS_BAD_VALUE;
    public static final int STATUS_DEAD_OBJECT;
    public static final int STATUS_ERROR = 0x80000000;
    public static final int STATUS_INVALID_OPERATION;
    public static final int STATUS_NO_INIT;
    public static final int STATUS_OK = 0;
    public static final int STATUS_PERMISSION_DENIED;

    static 
    {
        STATUS_PERMISSION_DENIED = -OsConstants.EPERM;
        STATUS_NO_INIT = -OsConstants.ENODEV;
        STATUS_BAD_VALUE = -OsConstants.EINVAL;
        STATUS_DEAD_OBJECT = -OsConstants.EPIPE;
        STATUS_INVALID_OPERATION = -OsConstants.ENOSYS;
    }
}
