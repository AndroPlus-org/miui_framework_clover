// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.content.*;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.util.*;

// Referenced classes of package android.speech.tts:
//            TtsEngines, ITextToSpeechService, Voice, UtteranceProgressListener

public class TextToSpeech
{
    private static interface Action
    {

        public abstract Object run(ITextToSpeechService itexttospeechservice)
            throws RemoteException;
    }

    private class Connection
        implements ServiceConnection
    {

        static ITextToSpeechCallback.Stub _2D_get0(Connection connection)
        {
            return connection.mCallback;
        }

        static SetupConnectionAsyncTask _2D_get1(Connection connection)
        {
            return connection.mOnSetupConnectionAsyncTask;
        }

        static ITextToSpeechService _2D_get2(Connection connection)
        {
            return connection.mService;
        }

        static boolean _2D_set0(Connection connection, boolean flag)
        {
            connection.mEstablished = flag;
            return flag;
        }

        static SetupConnectionAsyncTask _2D_set1(Connection connection, SetupConnectionAsyncTask setupconnectionasynctask)
        {
            connection.mOnSetupConnectionAsyncTask = setupconnectionasynctask;
            return setupconnectionasynctask;
        }

        private boolean clearServiceConnection()
        {
            Object obj = TextToSpeech._2D_get4(TextToSpeech.this);
            obj;
            JVM INSTR monitorenter ;
            boolean flag = false;
            if(mOnSetupConnectionAsyncTask != null)
            {
                flag = mOnSetupConnectionAsyncTask.cancel(false);
                mOnSetupConnectionAsyncTask = null;
            }
            mService = null;
            if(TextToSpeech._2D_get3(TextToSpeech.this) == this)
                TextToSpeech._2D_set2(TextToSpeech.this, null);
            obj;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public void disconnect()
        {
            TextToSpeech._2D_get0(TextToSpeech.this).unbindService(this);
            clearServiceConnection();
        }

        public IBinder getCallerIdentity()
        {
            return mCallback;
        }

        public boolean isEstablished()
        {
            boolean flag;
            if(mService != null)
                flag = mEstablished;
            else
                flag = false;
            return flag;
        }

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Object obj = TextToSpeech._2D_get4(TextToSpeech.this);
            obj;
            JVM INSTR monitorenter ;
            TextToSpeech._2D_set0(TextToSpeech.this, null);
            StringBuilder stringbuilder = JVM INSTR new #95  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("TextToSpeech", stringbuilder.append("Connected to ").append(componentname).toString());
            if(mOnSetupConnectionAsyncTask != null)
                mOnSetupConnectionAsyncTask.cancel(false);
            mService = ITextToSpeechService.Stub.asInterface(ibinder);
            TextToSpeech._2D_set2(TextToSpeech.this, this);
            mEstablished = false;
            ibinder = JVM INSTR new #13  <Class TextToSpeech$Connection$SetupConnectionAsyncTask>;
            ibinder.this. SetupConnectionAsyncTask(componentname);
            mOnSetupConnectionAsyncTask = ibinder;
            mOnSetupConnectionAsyncTask.execute(new Void[0]);
            obj;
            JVM INSTR monitorexit ;
            return;
            componentname;
            throw componentname;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.i("TextToSpeech", (new StringBuilder()).append("Asked to disconnect from ").append(componentname).toString());
            if(clearServiceConnection())
                TextToSpeech._2D_wrap4(TextToSpeech.this, -1);
        }

        public Object runAction(Action action, Object obj, String s, boolean flag, boolean flag1)
        {
            Object obj1 = TextToSpeech._2D_get4(TextToSpeech.this);
            obj1;
            JVM INSTR monitorenter ;
            if(mService != null)
                break MISSING_BLOCK_LABEL_51;
            action = JVM INSTR new #95  <Class StringBuilder>;
            action.StringBuilder();
            Log.w("TextToSpeech", action.append(s).append(" failed: not connected to TTS engine").toString());
            obj1;
            JVM INSTR monitorexit ;
            return obj;
            if(!flag1)
                break MISSING_BLOCK_LABEL_97;
            if(!(isEstablished() ^ true))
                break MISSING_BLOCK_LABEL_97;
            action = JVM INSTR new #95  <Class StringBuilder>;
            action.StringBuilder();
            Log.w("TextToSpeech", action.append(s).append(" failed: TTS engine connection not fully set up").toString());
            obj1;
            JVM INSTR monitorexit ;
            return obj;
            action = ((Action) (action.run(mService)));
            obj1;
            JVM INSTR monitorexit ;
            return action;
            action;
            StringBuilder stringbuilder = JVM INSTR new #95  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("TextToSpeech", stringbuilder.append(s).append(" failed").toString(), action);
            if(!flag)
                break MISSING_BLOCK_LABEL_162;
            disconnect();
            TextToSpeech._2D_wrap3(TextToSpeech.this);
            obj1;
            JVM INSTR monitorexit ;
            return obj;
            action;
            throw action;
        }

        private final ITextToSpeechCallback.Stub mCallback;
        private boolean mEstablished;
        private SetupConnectionAsyncTask mOnSetupConnectionAsyncTask;
        private ITextToSpeechService mService;
        final TextToSpeech this$0;

        private Connection()
        {
            this$0 = TextToSpeech.this;
            super();
            mCallback = new _cls1();
        }

        Connection(Connection connection)
        {
            this();
        }
    }

    private class Connection.SetupConnectionAsyncTask extends AsyncTask
    {

        protected transient Integer doInBackground(Void avoid[])
        {
            avoid = ((Void []) (TextToSpeech._2D_get4(_fld0)));
            avoid;
            JVM INSTR monitorenter ;
            boolean flag = isCancelled();
            if(!flag)
                break MISSING_BLOCK_LABEL_26;
            avoid;
            JVM INSTR monitorexit ;
            return null;
            Connection._2D_get2(Connection.this).setCallback(getCallerIdentity(), Connection._2D_get0(Connection.this));
            if(TextToSpeech._2D_get2(_fld0).getString("language") == null)
            {
                String as[] = Connection._2D_get2(Connection.this).getClientDefaultLanguage();
                TextToSpeech._2D_get2(_fld0).putString("language", as[0]);
                TextToSpeech._2D_get2(_fld0).putString("country", as[1]);
                TextToSpeech._2D_get2(_fld0).putString("variant", as[2]);
                String s = Connection._2D_get2(Connection.this).getDefaultVoiceNameFor(as[0], as[1], as[2]);
                TextToSpeech._2D_get2(_fld0).putString("voiceName", s);
            }
            StringBuilder stringbuilder = JVM INSTR new #90  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("TextToSpeech", stringbuilder.append("Set up connection to ").append(mName).toString());
            avoid;
            JVM INSTR monitorexit ;
            return Integer.valueOf(0);
            Object obj;
            obj;
            Log.e("TextToSpeech", "Error connecting to service, setCallback() failed");
            avoid;
            JVM INSTR monitorexit ;
            return Integer.valueOf(-1);
            obj;
            throw obj;
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected void onPostExecute(Integer integer)
        {
            Object obj = TextToSpeech._2D_get4(_fld0);
            obj;
            JVM INSTR monitorenter ;
            if(Connection._2D_get1(Connection.this) == this)
                Connection._2D_set1(Connection.this, null);
            Connection._2D_set0(Connection.this, true);
            TextToSpeech._2D_wrap4(_fld0, integer.intValue());
            obj;
            JVM INSTR monitorexit ;
            return;
            integer;
            throw integer;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Integer)obj);
        }

        private final ComponentName mName;
        final Connection this$1;

        public Connection.SetupConnectionAsyncTask(ComponentName componentname)
        {
            this$1 = Connection.this;
            super();
            mName = componentname;
        }
    }

    public class Engine
    {

        public static final String ACTION_CHECK_TTS_DATA = "android.speech.tts.engine.CHECK_TTS_DATA";
        public static final String ACTION_GET_SAMPLE_TEXT = "android.speech.tts.engine.GET_SAMPLE_TEXT";
        public static final String ACTION_INSTALL_TTS_DATA = "android.speech.tts.engine.INSTALL_TTS_DATA";
        public static final String ACTION_TTS_DATA_INSTALLED = "android.speech.tts.engine.TTS_DATA_INSTALLED";
        public static final int CHECK_VOICE_DATA_BAD_DATA = -1;
        public static final int CHECK_VOICE_DATA_FAIL = 0;
        public static final int CHECK_VOICE_DATA_MISSING_DATA = -2;
        public static final int CHECK_VOICE_DATA_MISSING_VOLUME = -3;
        public static final int CHECK_VOICE_DATA_PASS = 1;
        public static final String DEFAULT_ENGINE = "com.svox.pico";
        public static final float DEFAULT_PAN = 0F;
        public static final int DEFAULT_PITCH = 100;
        public static final int DEFAULT_RATE = 100;
        public static final int DEFAULT_STREAM = 3;
        public static final float DEFAULT_VOLUME = 1F;
        public static final String EXTRA_AVAILABLE_VOICES = "availableVoices";
        public static final String EXTRA_CHECK_VOICE_DATA_FOR = "checkVoiceDataFor";
        public static final String EXTRA_SAMPLE_TEXT = "sampleText";
        public static final String EXTRA_TTS_DATA_INSTALLED = "dataInstalled";
        public static final String EXTRA_UNAVAILABLE_VOICES = "unavailableVoices";
        public static final String EXTRA_VOICE_DATA_FILES = "dataFiles";
        public static final String EXTRA_VOICE_DATA_FILES_INFO = "dataFilesInfo";
        public static final String EXTRA_VOICE_DATA_ROOT_DIRECTORY = "dataRoot";
        public static final String INTENT_ACTION_TTS_SERVICE = "android.intent.action.TTS_SERVICE";
        public static final String KEY_FEATURE_EMBEDDED_SYNTHESIS = "embeddedTts";
        public static final String KEY_FEATURE_NETWORK_RETRIES_COUNT = "networkRetriesCount";
        public static final String KEY_FEATURE_NETWORK_SYNTHESIS = "networkTts";
        public static final String KEY_FEATURE_NETWORK_TIMEOUT_MS = "networkTimeoutMs";
        public static final String KEY_FEATURE_NOT_INSTALLED = "notInstalled";
        public static final String KEY_PARAM_AUDIO_ATTRIBUTES = "audioAttributes";
        public static final String KEY_PARAM_COUNTRY = "country";
        public static final String KEY_PARAM_ENGINE = "engine";
        public static final String KEY_PARAM_LANGUAGE = "language";
        public static final String KEY_PARAM_PAN = "pan";
        public static final String KEY_PARAM_PITCH = "pitch";
        public static final String KEY_PARAM_RATE = "rate";
        public static final String KEY_PARAM_SESSION_ID = "sessionId";
        public static final String KEY_PARAM_STREAM = "streamType";
        public static final String KEY_PARAM_UTTERANCE_ID = "utteranceId";
        public static final String KEY_PARAM_VARIANT = "variant";
        public static final String KEY_PARAM_VOICE_NAME = "voiceName";
        public static final String KEY_PARAM_VOLUME = "volume";
        public static final String SERVICE_META_DATA = "android.speech.tts";
        public static final int USE_DEFAULTS = 0;
        final TextToSpeech this$0;

        public Engine()
        {
            this$0 = TextToSpeech.this;
            super();
        }
    }

    public static class EngineInfo
    {

        public String toString()
        {
            return (new StringBuilder()).append("EngineInfo{name=").append(name).append("}").toString();
        }

        public int icon;
        public String label;
        public String name;
        public int priority;
        public boolean system;

        public EngineInfo()
        {
        }
    }

    public static interface OnInitListener
    {

        public abstract void onInit(int i);
    }

    public static interface OnUtteranceCompletedListener
    {

        public abstract void onUtteranceCompleted(String s);
    }


    static Context _2D_get0(TextToSpeech texttospeech)
    {
        return texttospeech.mContext;
    }

    static Map _2D_get1(TextToSpeech texttospeech)
    {
        return texttospeech.mEarcons;
    }

    static Bundle _2D_get2(TextToSpeech texttospeech)
    {
        return texttospeech.mParams;
    }

    static Connection _2D_get3(TextToSpeech texttospeech)
    {
        return texttospeech.mServiceConnection;
    }

    static Object _2D_get4(TextToSpeech texttospeech)
    {
        return texttospeech.mStartLock;
    }

    static UtteranceProgressListener _2D_get5(TextToSpeech texttospeech)
    {
        return texttospeech.mUtteranceProgressListener;
    }

    static Map _2D_get6(TextToSpeech texttospeech)
    {
        return texttospeech.mUtterances;
    }

    static Connection _2D_set0(TextToSpeech texttospeech, Connection connection)
    {
        texttospeech.mConnectingServiceConnection = connection;
        return connection;
    }

    static String _2D_set1(TextToSpeech texttospeech, String s)
    {
        texttospeech.mCurrentEngine = s;
        return s;
    }

    static Connection _2D_set2(TextToSpeech texttospeech, Connection connection)
    {
        texttospeech.mServiceConnection = connection;
        return connection;
    }

    static Bundle _2D_wrap0(TextToSpeech texttospeech, Bundle bundle)
    {
        return texttospeech.getParams(bundle);
    }

    static IBinder _2D_wrap1(TextToSpeech texttospeech)
    {
        return texttospeech.getCallerIdentity();
    }

    static Voice _2D_wrap2(TextToSpeech texttospeech, ITextToSpeechService itexttospeechservice, String s)
    {
        return texttospeech.getVoice(itexttospeechservice, s);
    }

    static int _2D_wrap3(TextToSpeech texttospeech)
    {
        return texttospeech.initTts();
    }

    static void _2D_wrap4(TextToSpeech texttospeech, int i)
    {
        texttospeech.dispatchOnInit(i);
    }

    public TextToSpeech(Context context, OnInitListener oninitlistener)
    {
        this(context, oninitlistener, null);
    }

    public TextToSpeech(Context context, OnInitListener oninitlistener, String s)
    {
        this(context, oninitlistener, s, null, true);
    }

    public TextToSpeech(Context context, OnInitListener oninitlistener, String s, String s1, boolean flag)
    {
        mStartLock = new Object();
        mParams = new Bundle();
        mCurrentEngine = null;
        mContext = context;
        mInitListener = oninitlistener;
        mRequestedEngine = s;
        mUseFallback = flag;
        mEarcons = new HashMap();
        mUtterances = new HashMap();
        mUtteranceProgressListener = null;
        mEnginesHelper = new TtsEngines(mContext);
        initTts();
    }

    private boolean connectToEngine(String s)
    {
        Connection connection = new Connection(null);
        Intent intent = new Intent("android.intent.action.TTS_SERVICE");
        intent.setPackage(s);
        if(!mContext.bindService(intent, connection, 1))
        {
            Log.e("TextToSpeech", (new StringBuilder()).append("Failed to bind to ").append(s).toString());
            return false;
        } else
        {
            Log.i("TextToSpeech", (new StringBuilder()).append("Sucessfully bound to ").append(s).toString());
            mConnectingServiceConnection = connection;
            return true;
        }
    }

    private Bundle convertParamsHashMaptoBundle(HashMap hashmap)
    {
        if(hashmap != null && hashmap.isEmpty() ^ true)
        {
            Bundle bundle = new Bundle();
            copyIntParam(bundle, hashmap, "streamType");
            copyIntParam(bundle, hashmap, "sessionId");
            copyStringParam(bundle, hashmap, "utteranceId");
            copyFloatParam(bundle, hashmap, "volume");
            copyFloatParam(bundle, hashmap, "pan");
            copyStringParam(bundle, hashmap, "networkTts");
            copyStringParam(bundle, hashmap, "embeddedTts");
            copyIntParam(bundle, hashmap, "networkTimeoutMs");
            copyIntParam(bundle, hashmap, "networkRetriesCount");
            if(!TextUtils.isEmpty(mCurrentEngine))
            {
                hashmap = hashmap.entrySet().iterator();
                do
                {
                    if(!hashmap.hasNext())
                        break;
                    java.util.Map.Entry entry = (java.util.Map.Entry)hashmap.next();
                    String s = (String)entry.getKey();
                    if(s != null && s.startsWith(mCurrentEngine))
                        bundle.putString(s, (String)entry.getValue());
                } while(true);
            }
            return bundle;
        } else
        {
            return null;
        }
    }

    private void copyFloatParam(Bundle bundle, HashMap hashmap, String s)
    {
        hashmap = (String)hashmap.get(s);
        if(TextUtils.isEmpty(hashmap))
            break MISSING_BLOCK_LABEL_25;
        bundle.putFloat(s, Float.parseFloat(hashmap));
_L2:
        return;
        bundle;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void copyIntParam(Bundle bundle, HashMap hashmap, String s)
    {
        hashmap = (String)hashmap.get(s);
        if(TextUtils.isEmpty(hashmap))
            break MISSING_BLOCK_LABEL_25;
        bundle.putInt(s, Integer.parseInt(hashmap));
_L2:
        return;
        bundle;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void copyStringParam(Bundle bundle, HashMap hashmap, String s)
    {
        hashmap = (String)hashmap.get(s);
        if(hashmap != null)
            bundle.putString(s, hashmap);
    }

    private void dispatchOnInit(int i)
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        if(mInitListener != null)
        {
            mInitListener.onInit(i);
            mInitListener = null;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private IBinder getCallerIdentity()
    {
        return mServiceConnection.getCallerIdentity();
    }

    public static int getMaxSpeechInputLength()
    {
        return 4000;
    }

    private Bundle getParams(Bundle bundle)
    {
        if(bundle != null && bundle.isEmpty() ^ true)
        {
            Bundle bundle1 = new Bundle(mParams);
            bundle1.putAll(bundle);
            verifyIntegerBundleParam(bundle1, "streamType");
            verifyIntegerBundleParam(bundle1, "sessionId");
            verifyStringBundleParam(bundle1, "utteranceId");
            verifyFloatBundleParam(bundle1, "volume");
            verifyFloatBundleParam(bundle1, "pan");
            verifyBooleanBundleParam(bundle1, "networkTts");
            verifyBooleanBundleParam(bundle1, "embeddedTts");
            verifyIntegerBundleParam(bundle1, "networkTimeoutMs");
            verifyIntegerBundleParam(bundle1, "networkRetriesCount");
            return bundle1;
        } else
        {
            return mParams;
        }
    }

    private Voice getVoice(ITextToSpeechService itexttospeechservice, String s)
        throws RemoteException
    {
        itexttospeechservice = itexttospeechservice.getVoices();
        if(itexttospeechservice == null)
        {
            Log.w("TextToSpeech", "getVoices returned null");
            return null;
        }
        for(Iterator iterator = itexttospeechservice.iterator(); iterator.hasNext();)
        {
            itexttospeechservice = (Voice)iterator.next();
            if(itexttospeechservice.getName().equals(s))
                return itexttospeechservice;
        }

        Log.w("TextToSpeech", (new StringBuilder()).append("Could not find voice ").append(s).append(" in voice list").toString());
        return null;
    }

    private int initTts()
    {
        if(mRequestedEngine != null)
            if(mEnginesHelper.isEngineInstalled(mRequestedEngine))
            {
                if(connectToEngine(mRequestedEngine))
                {
                    mCurrentEngine = mRequestedEngine;
                    return 0;
                }
                if(!mUseFallback)
                {
                    mCurrentEngine = null;
                    dispatchOnInit(-1);
                    return -1;
                }
            } else
            if(!mUseFallback)
            {
                Log.i("TextToSpeech", (new StringBuilder()).append("Requested engine not installed: ").append(mRequestedEngine).toString());
                mCurrentEngine = null;
                dispatchOnInit(-1);
                return -1;
            }
        String s = getDefaultEngine();
        if(s != null && s.equals(mRequestedEngine) ^ true && connectToEngine(s))
        {
            mCurrentEngine = s;
            return 0;
        }
        String s1 = mEnginesHelper.getHighestRankedEngineName();
        if(s1 != null && s1.equals(mRequestedEngine) ^ true && s1.equals(s) ^ true && connectToEngine(s1))
        {
            mCurrentEngine = s1;
            return 0;
        } else
        {
            mCurrentEngine = null;
            dispatchOnInit(-1);
            return -1;
        }
    }

    private Uri makeResourceUri(String s, int i)
    {
        return (new android.net.Uri.Builder()).scheme("android.resource").encodedAuthority(s).appendEncodedPath(String.valueOf(i)).build();
    }

    private Object runAction(Action action, Object obj, String s)
    {
        return runAction(action, obj, s, true, true);
    }

    private Object runAction(Action action, Object obj, String s, boolean flag, boolean flag1)
    {
        Object obj1 = mStartLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mServiceConnection != null)
            break MISSING_BLOCK_LABEL_49;
        action = JVM INSTR new #244 <Class StringBuilder>;
        action.StringBuilder();
        Log.w("TextToSpeech", action.append(s).append(" failed: not bound to TTS engine").toString());
        obj1;
        JVM INSTR monitorexit ;
        return obj;
        action = ((Action) (mServiceConnection.runAction(action, obj, s, flag, flag1)));
        obj1;
        JVM INSTR monitorexit ;
        return action;
        action;
        throw action;
    }

    private Object runActionNoReconnect(Action action, Object obj, String s, boolean flag)
    {
        return runAction(action, obj, s, false, flag);
    }

    private static boolean verifyBooleanBundleParam(Bundle bundle, String s)
    {
        if(bundle.containsKey(s))
        {
            boolean flag;
            if(!(bundle.get(s) instanceof Boolean))
                flag = bundle.get(s) instanceof String;
            else
                flag = true;
            if(!flag)
            {
                bundle.remove(s);
                Log.w("TextToSpeech", (new StringBuilder()).append("Synthesis request paramter ").append(s).append(" containst value ").append(" with invalid type. Should be a Boolean or String").toString());
                return false;
            }
        }
        return true;
    }

    private static boolean verifyFloatBundleParam(Bundle bundle, String s)
    {
        if(bundle.containsKey(s))
        {
            boolean flag;
            if(!(bundle.get(s) instanceof Float))
                flag = bundle.get(s) instanceof Double;
            else
                flag = true;
            if(!flag)
            {
                bundle.remove(s);
                Log.w("TextToSpeech", (new StringBuilder()).append("Synthesis request paramter ").append(s).append(" containst value ").append(" with invalid type. Should be a Float or a Double").toString());
                return false;
            }
        }
        return true;
    }

    private static boolean verifyIntegerBundleParam(Bundle bundle, String s)
    {
        if(bundle.containsKey(s))
        {
            boolean flag;
            if(!(bundle.get(s) instanceof Integer))
                flag = bundle.get(s) instanceof Long;
            else
                flag = true;
            if(!flag)
            {
                bundle.remove(s);
                Log.w("TextToSpeech", (new StringBuilder()).append("Synthesis request paramter ").append(s).append(" containst value ").append(" with invalid type. Should be an Integer or a Long").toString());
                return false;
            }
        }
        return true;
    }

    private static boolean verifyStringBundleParam(Bundle bundle, String s)
    {
        if(bundle.containsKey(s) && !(bundle.get(s) instanceof String))
        {
            bundle.remove(s);
            Log.w("TextToSpeech", (new StringBuilder()).append("Synthesis request paramter ").append(s).append(" containst value ").append(" with invalid type. Should be a String").toString());
            return false;
        } else
        {
            return true;
        }
    }

    public int addEarcon(String s, File file)
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mEarcons.put(s, Uri.fromFile(file));
        obj;
        JVM INSTR monitorexit ;
        return 0;
        s;
        throw s;
    }

    public int addEarcon(String s, String s1)
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mEarcons.put(s, Uri.parse(s1));
        obj;
        JVM INSTR monitorexit ;
        return 0;
        s;
        throw s;
    }

    public int addEarcon(String s, String s1, int i)
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mEarcons.put(s, makeResourceUri(s1, i));
        obj;
        JVM INSTR monitorexit ;
        return 0;
        s;
        throw s;
    }

    public int addSpeech(CharSequence charsequence, File file)
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mUtterances.put(charsequence, Uri.fromFile(file));
        obj;
        JVM INSTR monitorexit ;
        return 0;
        charsequence;
        throw charsequence;
    }

    public int addSpeech(CharSequence charsequence, String s, int i)
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mUtterances.put(charsequence, makeResourceUri(s, i));
        obj;
        JVM INSTR monitorexit ;
        return 0;
        charsequence;
        throw charsequence;
    }

    public int addSpeech(String s, String s1)
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mUtterances.put(s, Uri.parse(s1));
        obj;
        JVM INSTR monitorexit ;
        return 0;
        s;
        throw s;
    }

    public int addSpeech(String s, String s1, int i)
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mUtterances.put(s, makeResourceUri(s1, i));
        obj;
        JVM INSTR monitorexit ;
        return 0;
        s;
        throw s;
    }

    public boolean areDefaultsEnforced()
    {
        return false;
    }

    public Set getAvailableLanguages()
    {
        return (Set)runAction(new Action() {

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            public Set run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                Object obj = itexttospeechservice.getVoices();
                if(obj == null)
                    return new HashSet();
                itexttospeechservice = new HashSet();
                for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); itexttospeechservice.add(((Voice)((Iterator) (obj)).next()).getLocale()));
                return itexttospeechservice;
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, null, "getAvailableLanguages");
    }

    public String getCurrentEngine()
    {
        return mCurrentEngine;
    }

    public String getDefaultEngine()
    {
        return mEnginesHelper.getDefaultEngine();
    }

    public Locale getDefaultLanguage()
    {
        return (Locale)runAction(new Action() {

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            public Locale run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                itexttospeechservice = itexttospeechservice.getClientDefaultLanguage();
                return new Locale(itexttospeechservice[0], itexttospeechservice[1], itexttospeechservice[2]);
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, null, "getDefaultLanguage");
    }

    public Voice getDefaultVoice()
    {
        return (Voice)runAction(new Action() {

            public Voice run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                String as[] = itexttospeechservice.getClientDefaultLanguage();
                if(as == null || as.length == 0)
                {
                    Log.e("TextToSpeech", "service.getClientDefaultLanguage() returned empty array");
                    return null;
                }
                String s1 = as[0];
                String s;
                String s2;
                if(as.length > 1)
                    s2 = as[1];
                else
                    s2 = "";
                if(as.length > 2)
                    s = as[2];
                else
                    s = "";
                if(itexttospeechservice.isLanguageAvailable(s1, s2, s) < 0)
                    return null;
                s2 = itexttospeechservice.getDefaultVoiceNameFor(s1, s2, s);
                if(TextUtils.isEmpty(s2))
                    return null;
                itexttospeechservice = itexttospeechservice.getVoices();
                if(itexttospeechservice == null)
                    return null;
                for(itexttospeechservice = itexttospeechservice.iterator(); itexttospeechservice.hasNext();)
                {
                    Voice voice = (Voice)itexttospeechservice.next();
                    if(voice.getName().equals(s2))
                        return voice;
                }

                return null;
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, null, "getDefaultVoice");
    }

    public List getEngines()
    {
        return mEnginesHelper.getEngines();
    }

    public Set getFeatures(final Locale locale)
    {
        return (Set)runAction(new Action() {

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            public Set run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                try
                {
                    itexttospeechservice = itexttospeechservice.getFeaturesForLanguage(locale.getISO3Language(), locale.getISO3Country(), locale.getVariant());
                }
                // Misplaced declaration of an exception variable
                catch(ITextToSpeechService itexttospeechservice)
                {
                    Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve 3 letter ISO 639-2/T language and/or ISO 3166 country code for locale: ").append(locale).toString(), itexttospeechservice);
                    return null;
                }
                if(itexttospeechservice != null)
                {
                    HashSet hashset = new HashSet();
                    Collections.addAll(hashset, itexttospeechservice);
                    return hashset;
                } else
                {
                    return null;
                }
            }

            final TextToSpeech this$0;
            final Locale val$locale;

            
            {
                this$0 = TextToSpeech.this;
                locale = locale1;
                super();
            }
        }
, null, "getFeatures");
    }

    public Locale getLanguage()
    {
        return (Locale)runAction(new Action() {

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            public Locale run(ITextToSpeechService itexttospeechservice)
            {
                return new Locale(TextToSpeech._2D_get2(TextToSpeech.this).getString("language", ""), TextToSpeech._2D_get2(TextToSpeech.this).getString("country", ""), TextToSpeech._2D_get2(TextToSpeech.this).getString("variant", ""));
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, null, "getLanguage");
    }

    public Voice getVoice()
    {
        return (Voice)runAction(new Action() {

            public Voice run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                String s = TextToSpeech._2D_get2(TextToSpeech.this).getString("voiceName", "");
                if(TextUtils.isEmpty(s))
                    return null;
                else
                    return TextToSpeech._2D_wrap2(TextToSpeech.this, itexttospeechservice, s);
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, null, "getVoice");
    }

    public Set getVoices()
    {
        return (Set)runAction(new Action() {

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            public Set run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                itexttospeechservice = itexttospeechservice.getVoices();
                if(itexttospeechservice != null)
                    itexttospeechservice = new HashSet(itexttospeechservice);
                else
                    itexttospeechservice = new HashSet();
                return itexttospeechservice;
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, null, "getVoices");
    }

    public int isLanguageAvailable(final Locale loc)
    {
        return ((Integer)runAction(new Action() {

            public Integer run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                String s;
                String s1;
                try
                {
                    s = loc.getISO3Language();
                }
                // Misplaced declaration of an exception variable
                catch(ITextToSpeechService itexttospeechservice)
                {
                    Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve ISO 639-2/T language code for locale: ").append(loc).toString(), itexttospeechservice);
                    return Integer.valueOf(-2);
                }
                try
                {
                    s1 = loc.getISO3Country();
                }
                // Misplaced declaration of an exception variable
                catch(ITextToSpeechService itexttospeechservice)
                {
                    Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve ISO 3166 country code for locale: ").append(loc).toString(), itexttospeechservice);
                    return Integer.valueOf(-2);
                }
                return Integer.valueOf(itexttospeechservice.isLanguageAvailable(s, s1, loc.getVariant()));
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;
            final Locale val$loc;

            
            {
                this$0 = TextToSpeech.this;
                loc = locale;
                super();
            }
        }
, Integer.valueOf(-2), "isLanguageAvailable")).intValue();
    }

    public boolean isSpeaking()
    {
        return ((Boolean)runAction(new Action() {

            public Boolean run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return Boolean.valueOf(itexttospeechservice.isSpeaking());
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, Boolean.valueOf(false), "isSpeaking")).booleanValue();
    }

    public int playEarcon(final String earcon, final int queueMode, final Bundle params, final String utteranceId)
    {
        return ((Integer)runAction(new Action() {

            public Integer run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                Uri uri = (Uri)TextToSpeech._2D_get1(TextToSpeech.this).get(earcon);
                if(uri == null)
                    return Integer.valueOf(-1);
                else
                    return Integer.valueOf(itexttospeechservice.playAudio(TextToSpeech._2D_wrap1(TextToSpeech.this), uri, queueMode, TextToSpeech._2D_wrap0(TextToSpeech.this, params), utteranceId));
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;
            final String val$earcon;
            final Bundle val$params;
            final int val$queueMode;
            final String val$utteranceId;

            
            {
                this$0 = TextToSpeech.this;
                earcon = s;
                queueMode = i;
                params = bundle;
                utteranceId = s1;
                super();
            }
        }
, Integer.valueOf(-1), "playEarcon")).intValue();
    }

    public int playEarcon(String s, int i, HashMap hashmap)
    {
        Object obj = null;
        Bundle bundle = convertParamsHashMaptoBundle(hashmap);
        if(hashmap == null)
            hashmap = obj;
        else
            hashmap = (String)hashmap.get("utteranceId");
        return playEarcon(s, i, bundle, ((String) (hashmap)));
    }

    public int playSilence(long l, int i, HashMap hashmap)
    {
        Object obj = null;
        if(hashmap == null)
            hashmap = obj;
        else
            hashmap = (String)hashmap.get("utteranceId");
        return playSilentUtterance(l, i, hashmap);
    }

    public int playSilentUtterance(final long durationInMs, final int queueMode, final String utteranceId)
    {
        return ((Integer)runAction(new Action() {

            public Integer run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return Integer.valueOf(itexttospeechservice.playSilence(TextToSpeech._2D_wrap1(TextToSpeech.this), durationInMs, queueMode, utteranceId));
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;
            final long val$durationInMs;
            final int val$queueMode;
            final String val$utteranceId;

            
            {
                this$0 = TextToSpeech.this;
                durationInMs = l;
                queueMode = i;
                utteranceId = s;
                super();
            }
        }
, Integer.valueOf(-1), "playSilentUtterance")).intValue();
    }

    public int setAudioAttributes(AudioAttributes audioattributes)
    {
        if(audioattributes == null)
            break MISSING_BLOCK_LABEL_31;
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mParams.putParcelable("audioAttributes", audioattributes);
        obj;
        JVM INSTR monitorexit ;
        return 0;
        audioattributes;
        throw audioattributes;
        return -1;
    }

    public int setEngineByPackageName(String s)
    {
        mRequestedEngine = s;
        return initTts();
    }

    public int setLanguage(final Locale loc)
    {
        return ((Integer)runAction(new Action() {

            public Integer run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                String s;
                String s1;
                String s2;
                int i;
                String s3;
                if(loc == null)
                    return Integer.valueOf(-2);
                try
                {
                    s = loc.getISO3Language();
                }
                // Misplaced declaration of an exception variable
                catch(ITextToSpeechService itexttospeechservice)
                {
                    Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve ISO 639-2/T language code for locale: ").append(loc).toString(), itexttospeechservice);
                    return Integer.valueOf(-2);
                }
                try
                {
                    s1 = loc.getISO3Country();
                }
                // Misplaced declaration of an exception variable
                catch(ITextToSpeechService itexttospeechservice)
                {
                    Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve ISO 3166 country code for locale: ").append(loc).toString(), itexttospeechservice);
                    return Integer.valueOf(-2);
                }
                s2 = loc.getVariant();
                i = itexttospeechservice.isLanguageAvailable(s, s1, s2);
                if(i < 0) goto _L2; else goto _L1
_L1:
                Voice voice;
                s3 = itexttospeechservice.getDefaultVoiceNameFor(s, s1, s2);
                if(TextUtils.isEmpty(s3))
                {
                    Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't find the default voice for ").append(s).append("-").append(s1).append("-").append(s2).toString());
                    return Integer.valueOf(-2);
                }
                if(itexttospeechservice.loadVoice(TextToSpeech._2D_wrap1(TextToSpeech.this), s3) == -1)
                {
                    Log.w("TextToSpeech", (new StringBuilder()).append("The service claimed ").append(s).append("-").append(s1).append("-").append(s2).append(" was available with voice name ").append(s3).append(" but loadVoice returned ERROR").toString());
                    return Integer.valueOf(-2);
                }
                voice = TextToSpeech._2D_wrap2(TextToSpeech.this, itexttospeechservice, s3);
                if(voice == null)
                {
                    Log.w("TextToSpeech", (new StringBuilder()).append("getDefaultVoiceNameFor returned ").append(s3).append(" for locale ").append(s).append("-").append(s1).append("-").append(s2).append(" but getVoice returns null").toString());
                    return Integer.valueOf(-2);
                }
                itexttospeechservice = "";
                s1 = voice.getLocale().getISO3Language();
                itexttospeechservice = s1;
_L3:
                s1 = "";
                s2 = voice.getLocale().getISO3Country();
                s1 = s2;
_L4:
                TextToSpeech._2D_get2(TextToSpeech.this).putString("voiceName", s3);
                TextToSpeech._2D_get2(TextToSpeech.this).putString("language", itexttospeechservice);
                TextToSpeech._2D_get2(TextToSpeech.this).putString("country", s1);
                TextToSpeech._2D_get2(TextToSpeech.this).putString("variant", voice.getLocale().getVariant());
_L2:
                return Integer.valueOf(i);
                MissingResourceException missingresourceexception;
                missingresourceexception;
                Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve ISO 639-2/T language code for locale: ").append(voice.getLocale()).toString(), missingresourceexception);
                  goto _L3
                MissingResourceException missingresourceexception1;
                missingresourceexception1;
                Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve ISO 3166 country code for locale: ").append(voice.getLocale()).toString(), missingresourceexception1);
                  goto _L4
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;
            final Locale val$loc;

            
            {
                this$0 = TextToSpeech.this;
                loc = locale;
                super();
            }
        }
, Integer.valueOf(-2), "setLanguage")).intValue();
    }

    public int setOnUtteranceCompletedListener(OnUtteranceCompletedListener onutterancecompletedlistener)
    {
        mUtteranceProgressListener = UtteranceProgressListener.from(onutterancecompletedlistener);
        return 0;
    }

    public int setOnUtteranceProgressListener(UtteranceProgressListener utteranceprogresslistener)
    {
        mUtteranceProgressListener = utteranceprogresslistener;
        return 0;
    }

    public int setPitch(float f)
    {
        int i;
        if(f <= 0.0F)
            break MISSING_BLOCK_LABEL_46;
        i = (int)(100F * f);
        if(i <= 0)
            break MISSING_BLOCK_LABEL_46;
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mParams.putInt("pitch", i);
        obj;
        JVM INSTR monitorexit ;
        return 0;
        Exception exception;
        exception;
        throw exception;
        return -1;
    }

    public int setSpeechRate(float f)
    {
        int i;
        if(f <= 0.0F)
            break MISSING_BLOCK_LABEL_46;
        i = (int)(100F * f);
        if(i <= 0)
            break MISSING_BLOCK_LABEL_46;
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        mParams.putInt("rate", i);
        obj;
        JVM INSTR monitorexit ;
        return 0;
        Exception exception;
        exception;
        throw exception;
        return -1;
    }

    public int setVoice(final Voice voice)
    {
        return ((Integer)runAction(new Action() {

            public Integer run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                int i = itexttospeechservice.loadVoice(TextToSpeech._2D_wrap1(TextToSpeech.this), voice.getName());
                if(i != 0) goto _L2; else goto _L1
_L1:
                TextToSpeech._2D_get2(TextToSpeech.this).putString("voiceName", voice.getName());
                itexttospeechservice = "";
                String s = voice.getLocale().getISO3Language();
                itexttospeechservice = s;
_L3:
                s = "";
                String s1 = voice.getLocale().getISO3Country();
                s = s1;
_L4:
                TextToSpeech._2D_get2(TextToSpeech.this).putString("language", itexttospeechservice);
                TextToSpeech._2D_get2(TextToSpeech.this).putString("country", s);
                TextToSpeech._2D_get2(TextToSpeech.this).putString("variant", voice.getLocale().getVariant());
_L2:
                return Integer.valueOf(i);
                MissingResourceException missingresourceexception;
                missingresourceexception;
                Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve ISO 639-2/T language code for locale: ").append(voice.getLocale()).toString(), missingresourceexception);
                  goto _L3
                MissingResourceException missingresourceexception1;
                missingresourceexception1;
                Log.w("TextToSpeech", (new StringBuilder()).append("Couldn't retrieve ISO 3166 country code for locale: ").append(voice.getLocale()).toString(), missingresourceexception1);
                  goto _L4
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;
            final Voice val$voice;

            
            {
                this$0 = TextToSpeech.this;
                voice = voice1;
                super();
            }
        }
, Integer.valueOf(-2), "setVoice")).intValue();
    }

    public void shutdown()
    {
        Object obj = mStartLock;
        obj;
        JVM INSTR monitorenter ;
        if(mConnectingServiceConnection == null)
            break MISSING_BLOCK_LABEL_33;
        mContext.unbindService(mConnectingServiceConnection);
        mConnectingServiceConnection = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        runActionNoReconnect(new Action() {

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            public Void run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                itexttospeechservice.setCallback(TextToSpeech._2D_wrap1(TextToSpeech.this), null);
                itexttospeechservice.stop(TextToSpeech._2D_wrap1(TextToSpeech.this));
                TextToSpeech._2D_get3(TextToSpeech.this).disconnect();
                TextToSpeech._2D_set2(TextToSpeech.this, null);
                TextToSpeech._2D_set1(TextToSpeech.this, null);
                return null;
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, null, "shutdown", false);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int speak(final CharSequence text, final int queueMode, final Bundle params, final String utteranceId)
    {
        return ((Integer)runAction(new Action() {

            public Integer run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                Uri uri = (Uri)TextToSpeech._2D_get6(TextToSpeech.this).get(text);
                if(uri != null)
                    return Integer.valueOf(itexttospeechservice.playAudio(TextToSpeech._2D_wrap1(TextToSpeech.this), uri, queueMode, TextToSpeech._2D_wrap0(TextToSpeech.this, params), utteranceId));
                else
                    return Integer.valueOf(itexttospeechservice.speak(TextToSpeech._2D_wrap1(TextToSpeech.this), text, queueMode, TextToSpeech._2D_wrap0(TextToSpeech.this, params), utteranceId));
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;
            final Bundle val$params;
            final int val$queueMode;
            final CharSequence val$text;
            final String val$utteranceId;

            
            {
                this$0 = TextToSpeech.this;
                text = charsequence;
                queueMode = i;
                params = bundle;
                utteranceId = s;
                super();
            }
        }
, Integer.valueOf(-1), "speak")).intValue();
    }

    public int speak(String s, int i, HashMap hashmap)
    {
        Object obj = null;
        Bundle bundle = convertParamsHashMaptoBundle(hashmap);
        if(hashmap == null)
            hashmap = obj;
        else
            hashmap = (String)hashmap.get("utteranceId");
        return speak(((CharSequence) (s)), i, bundle, ((String) (hashmap)));
    }

    public int stop()
    {
        return ((Integer)runAction(new Action() {

            public Integer run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return Integer.valueOf(itexttospeechservice.stop(TextToSpeech._2D_wrap1(TextToSpeech.this)));
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        }
, Integer.valueOf(-1), "stop")).intValue();
    }

    public int synthesizeToFile(final CharSequence text, final Bundle params, final File file, final String utteranceId)
    {
        return ((Integer)runAction(new Action() {

            public Integer run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                int i;
                try
                {
                    if(file.exists() && file.canWrite() ^ true)
                    {
                        itexttospeechservice = JVM INSTR new #57  <Class StringBuilder>;
                        itexttospeechservice.StringBuilder();
                        Log.e("TextToSpeech", itexttospeechservice.append("Can't write to ").append(file).toString());
                        return Integer.valueOf(-1);
                    }
                    ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.open(file, 0x2c000000);
                    i = itexttospeechservice.synthesizeToFileDescriptor(TextToSpeech._2D_wrap1(TextToSpeech.this), text, parcelfiledescriptor, TextToSpeech._2D_wrap0(TextToSpeech.this, params), utteranceId);
                    parcelfiledescriptor.close();
                }
                // Misplaced declaration of an exception variable
                catch(ITextToSpeechService itexttospeechservice)
                {
                    Log.e("TextToSpeech", (new StringBuilder()).append("Opening file ").append(file).append(" failed").toString(), itexttospeechservice);
                    return Integer.valueOf(-1);
                }
                // Misplaced declaration of an exception variable
                catch(ITextToSpeechService itexttospeechservice)
                {
                    Log.e("TextToSpeech", (new StringBuilder()).append("Closing file ").append(file).append(" failed").toString(), itexttospeechservice);
                    return Integer.valueOf(-1);
                }
                return Integer.valueOf(i);
            }

            public volatile Object run(ITextToSpeechService itexttospeechservice)
                throws RemoteException
            {
                return run(itexttospeechservice);
            }

            final TextToSpeech this$0;
            final File val$file;
            final Bundle val$params;
            final CharSequence val$text;
            final String val$utteranceId;

            
            {
                this$0 = TextToSpeech.this;
                file = file1;
                text = charsequence;
                params = bundle;
                utteranceId = s;
                super();
            }
        }
, Integer.valueOf(-1), "synthesizeToFile")).intValue();
    }

    public int synthesizeToFile(String s, HashMap hashmap, String s1)
    {
        return synthesizeToFile(((CharSequence) (s)), convertParamsHashMaptoBundle(hashmap), new File(s1), (String)hashmap.get("utteranceId"));
    }

    public static final String ACTION_TTS_QUEUE_PROCESSING_COMPLETED = "android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED";
    public static final int ERROR = -1;
    public static final int ERROR_INVALID_REQUEST = -8;
    public static final int ERROR_NETWORK = -6;
    public static final int ERROR_NETWORK_TIMEOUT = -7;
    public static final int ERROR_NOT_INSTALLED_YET = -9;
    public static final int ERROR_OUTPUT = -5;
    public static final int ERROR_SERVICE = -4;
    public static final int ERROR_SYNTHESIS = -3;
    public static final int LANG_AVAILABLE = 0;
    public static final int LANG_COUNTRY_AVAILABLE = 1;
    public static final int LANG_COUNTRY_VAR_AVAILABLE = 2;
    public static final int LANG_MISSING_DATA = -1;
    public static final int LANG_NOT_SUPPORTED = -2;
    public static final int QUEUE_ADD = 1;
    static final int QUEUE_DESTROY = 2;
    public static final int QUEUE_FLUSH = 0;
    public static final int STOPPED = -2;
    public static final int SUCCESS = 0;
    private static final String TAG = "TextToSpeech";
    private Connection mConnectingServiceConnection;
    private final Context mContext;
    private volatile String mCurrentEngine;
    private final Map mEarcons;
    private final TtsEngines mEnginesHelper;
    private OnInitListener mInitListener;
    private final Bundle mParams;
    private String mRequestedEngine;
    private Connection mServiceConnection;
    private final Object mStartLock;
    private final boolean mUseFallback;
    private volatile UtteranceProgressListener mUtteranceProgressListener;
    private final Map mUtterances;

    // Unreferenced inner class android/speech/tts/TextToSpeech$Connection$1

/* anonymous class */
    class Connection._cls1 extends ITextToSpeechCallback.Stub
    {

        public void onAudioAvailable(String s, byte abyte0[])
        {
            UtteranceProgressListener utteranceprogresslistener = TextToSpeech._2D_get5(_fld0);
            if(utteranceprogresslistener != null)
                utteranceprogresslistener.onAudioAvailable(s, abyte0);
        }

        public void onBeginSynthesis(String s, int i, int j, int k)
        {
            UtteranceProgressListener utteranceprogresslistener = TextToSpeech._2D_get5(_fld0);
            if(utteranceprogresslistener != null)
                utteranceprogresslistener.onBeginSynthesis(s, i, j, k);
        }

        public void onError(String s, int i)
        {
            UtteranceProgressListener utteranceprogresslistener = TextToSpeech._2D_get5(_fld0);
            if(utteranceprogresslistener != null)
                utteranceprogresslistener.onError(s);
        }

        public void onRangeStart(String s, int i, int j, int k)
        {
            UtteranceProgressListener utteranceprogresslistener = TextToSpeech._2D_get5(_fld0);
            if(utteranceprogresslistener != null)
                utteranceprogresslistener.onRangeStart(s, i, j, k);
        }

        public void onStart(String s)
        {
            UtteranceProgressListener utteranceprogresslistener = TextToSpeech._2D_get5(_fld0);
            if(utteranceprogresslistener != null)
                utteranceprogresslistener.onStart(s);
        }

        public void onStop(String s, boolean flag)
            throws RemoteException
        {
            UtteranceProgressListener utteranceprogresslistener = TextToSpeech._2D_get5(_fld0);
            if(utteranceprogresslistener != null)
                utteranceprogresslistener.onStop(s, flag);
        }

        public void onSuccess(String s)
        {
            UtteranceProgressListener utteranceprogresslistener = TextToSpeech._2D_get5(_fld0);
            if(utteranceprogresslistener != null)
                utteranceprogresslistener.onDone(s);
        }

        final Connection this$1;

            
            {
                this$1 = Connection.this;
                super();
            }
    }

}
