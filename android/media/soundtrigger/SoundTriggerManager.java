// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.soundtrigger;

import android.app.PendingIntent;
import android.content.Context;
import android.os.*;
import android.util.Slog;
import com.android.internal.app.ISoundTriggerService;
import java.util.HashMap;
import java.util.UUID;

// Referenced classes of package android.media.soundtrigger:
//            SoundTriggerDetector

public final class SoundTriggerManager
{
    public static class Model
    {

        public static Model create(UUID uuid, UUID uuid1, byte abyte0[])
        {
            return new Model(new android.hardware.soundtrigger.SoundTrigger.GenericSoundModel(uuid, uuid1, abyte0));
        }

        android.hardware.soundtrigger.SoundTrigger.GenericSoundModel getGenericSoundModel()
        {
            return mGenericSoundModel;
        }

        public byte[] getModelData()
        {
            return mGenericSoundModel.data;
        }

        public UUID getModelUuid()
        {
            return mGenericSoundModel.uuid;
        }

        public UUID getVendorUuid()
        {
            return mGenericSoundModel.vendorUuid;
        }

        private android.hardware.soundtrigger.SoundTrigger.GenericSoundModel mGenericSoundModel;

        Model(android.hardware.soundtrigger.SoundTrigger.GenericSoundModel genericsoundmodel)
        {
            mGenericSoundModel = genericsoundmodel;
        }
    }


    public SoundTriggerManager(Context context, ISoundTriggerService isoundtriggerservice)
    {
        mSoundTriggerService = isoundtriggerservice;
        mContext = context;
    }

    public SoundTriggerDetector createSoundTriggerDetector(UUID uuid, SoundTriggerDetector.Callback callback, Handler handler)
    {
        if(uuid == null)
        {
            return null;
        } else
        {
            SoundTriggerDetector soundtriggerdetector = (SoundTriggerDetector)mReceiverInstanceMap.get(uuid);
            callback = new SoundTriggerDetector(mSoundTriggerService, uuid, callback, handler);
            mReceiverInstanceMap.put(uuid, callback);
            return callback;
        }
    }

    public void deleteModel(UUID uuid)
    {
        try
        {
            ISoundTriggerService isoundtriggerservice = mSoundTriggerService;
            ParcelUuid parceluuid = JVM INSTR new #76  <Class ParcelUuid>;
            parceluuid.ParcelUuid(uuid);
            isoundtriggerservice.deleteSoundModel(parceluuid);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
    }

    public Model getModel(UUID uuid)
    {
        try
        {
            ISoundTriggerService isoundtriggerservice = mSoundTriggerService;
            ParcelUuid parceluuid = JVM INSTR new #76  <Class ParcelUuid>;
            parceluuid.ParcelUuid(uuid);
            uuid = new Model(isoundtriggerservice.getSoundModel(parceluuid));
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return uuid;
    }

    public boolean isRecognitionActive(UUID uuid)
    {
        if(uuid == null)
            return false;
        boolean flag;
        try
        {
            ISoundTriggerService isoundtriggerservice = mSoundTriggerService;
            ParcelUuid parceluuid = JVM INSTR new #76  <Class ParcelUuid>;
            parceluuid.ParcelUuid(uuid);
            flag = isoundtriggerservice.isRecognitionActive(parceluuid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return flag;
    }

    public int loadSoundModel(android.hardware.soundtrigger.SoundTrigger.SoundModel soundmodel)
    {
        if(soundmodel == null)
            return 0x80000000;
        soundmodel.type;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 57
    //                   1 43;
           goto _L1 _L2 _L3
_L1:
        Slog.e("SoundTriggerManager", "Unkown model type");
        return 0x80000000;
_L3:
        return mSoundTriggerService.loadGenericSoundModel((android.hardware.soundtrigger.SoundTrigger.GenericSoundModel)soundmodel);
_L2:
        int i = mSoundTriggerService.loadKeyphraseSoundModel((android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel)soundmodel);
        return i;
        soundmodel;
        throw soundmodel.rethrowFromSystemServer();
    }

    public int startRecognition(UUID uuid, PendingIntent pendingintent, android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig)
    {
        while(uuid == null || pendingintent == null || recognitionconfig == null) 
            return 0x80000000;
        int i;
        try
        {
            ISoundTriggerService isoundtriggerservice = mSoundTriggerService;
            ParcelUuid parceluuid = JVM INSTR new #76  <Class ParcelUuid>;
            parceluuid.ParcelUuid(uuid);
            i = isoundtriggerservice.startRecognitionForIntent(parceluuid, pendingintent, recognitionconfig);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return i;
    }

    public int stopRecognition(UUID uuid)
    {
        if(uuid == null)
            return 0x80000000;
        int i;
        try
        {
            ISoundTriggerService isoundtriggerservice = mSoundTriggerService;
            ParcelUuid parceluuid = JVM INSTR new #76  <Class ParcelUuid>;
            parceluuid.ParcelUuid(uuid);
            i = isoundtriggerservice.stopRecognitionForIntent(parceluuid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return i;
    }

    public int unloadSoundModel(UUID uuid)
    {
        if(uuid == null)
            return 0x80000000;
        int i;
        try
        {
            ISoundTriggerService isoundtriggerservice = mSoundTriggerService;
            ParcelUuid parceluuid = JVM INSTR new #76  <Class ParcelUuid>;
            parceluuid.ParcelUuid(uuid);
            i = isoundtriggerservice.unloadSoundModel(parceluuid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return i;
    }

    public void updateModel(Model model)
    {
        try
        {
            mSoundTriggerService.updateSoundModel(model.getGenericSoundModel());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Model model)
        {
            throw model.rethrowFromSystemServer();
        }
    }

    private static final boolean DBG = false;
    public static final String EXTRA_MESSAGE_TYPE = "android.media.soundtrigger.MESSAGE_TYPE";
    public static final String EXTRA_RECOGNITION_EVENT = "android.media.soundtrigger.RECOGNITION_EVENT";
    public static final String EXTRA_STATUS = "android.media.soundtrigger.STATUS";
    public static final int FLAG_MESSAGE_TYPE_RECOGNITION_ERROR = 1;
    public static final int FLAG_MESSAGE_TYPE_RECOGNITION_EVENT = 0;
    public static final int FLAG_MESSAGE_TYPE_RECOGNITION_PAUSED = 2;
    public static final int FLAG_MESSAGE_TYPE_RECOGNITION_RESUMED = 3;
    public static final int FLAG_MESSAGE_TYPE_UNKNOWN = -1;
    private static final String TAG = "SoundTriggerManager";
    private final Context mContext;
    private final HashMap mReceiverInstanceMap = new HashMap();
    private final ISoundTriggerService mSoundTriggerService;
}
