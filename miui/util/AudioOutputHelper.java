// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.*;
import android.os.*;
import android.util.Log;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AudioOutputHelper
{
    public static class AudioOutputClient
    {

        public boolean mActive;
        public final int mProcessId;
        public final int mSessionId;
        public final int mStreamType;

        public AudioOutputClient(int i, int j, int k)
        {
            this(i, j, k, false);
        }

        public AudioOutputClient(int i, int j, int k, boolean flag)
        {
            mSessionId = i;
            mProcessId = j;
            mStreamType = k;
            mActive = flag;
        }
    }

    static final class CompatCollector
        implements TrackCollector
    {

        private String collectTracks(BufferedReader bufferedreader, List list, Map map, boolean flag)
            throws NumberFormatException, IOException
        {
            do
            {
                Matcher matcher;
label0:
                {
                    String s = bufferedreader.readLine();
                    if(s != null)
                    {
                        matcher = TRACK_CONTENT_FINDER_COMPAT.matcher(s);
                        if(matcher.find())
                            break label0;
                    }
                    return s;
                }
                int i = Integer.valueOf(matcher.group(3)).intValue();
                Integer integer = (Integer)map.get(Integer.valueOf(i));
                if(integer != null)
                {
                    int j = integer.intValue();
                    int k = Integer.valueOf(matcher.group(2)).intValue();
                    boolean flag1 = false;
                    boolean flag2 = false;
                    if(flag)
                    {
                        Iterator iterator = list.iterator();
                        do
                        {
                            flag1 = flag2;
                            if(!iterator.hasNext())
                                break;
                            AudioOutputClient audiooutputclient = (AudioOutputClient)iterator.next();
                            if(audiooutputclient.mSessionId == i)
                            {
                                audiooutputclient.mActive = flag;
                                flag2 = true;
                            }
                        } while(true);
                    }
                    if(!flag1)
                        list.add(new AudioOutputClient(i, j, k, flag));
                }
            } while(true);
        }

        public Result collectTracks(BufferedReader bufferedreader, String s, List list, Map map)
            throws IOException
        {
            if(TRACKS_FINDER_COMPAT.matcher(s).find())
            {
                bufferedreader.readLine();
                return new Result(true, collectTracks(bufferedreader, list, map, false));
            }
            if(ACTIVE_TRACKS_FINDER_COMPAT.matcher(s).find())
            {
                bufferedreader.readLine();
                return new Result(true, collectTracks(bufferedreader, list, map, true));
            } else
            {
                return AudioOutputHelper.UNHANDLED;
            }
        }

        public static final Pattern ACTIVE_TRACKS_FINDER_COMPAT = Pattern.compile("^Output thread 0x[\\w]+ active tracks");
        public static final Pattern TRACKS_FINDER_COMPAT = Pattern.compile("^Output thread 0x[\\w]+ tracks");
        public static final Pattern TRACK_CONTENT_FINDER_COMPAT = Pattern.compile("^(\\s|F)+\\d+\\s+\\d+\\s+(\\d+)\\s+\\d+\\s+\\w+\\s+(\\d+)\\s.+");
        public static final int TRACK_SESSION_GRP_IDX = 3;
        public static final int TRACK_STREAM_TYPE_GRP_IDX = 2;


        CompatCollector()
        {
        }
    }

    private static class DUMP_TAG
    {

        public static final int PID_GRP_IDX = 2;
        public static final Pattern SESSIONS_CONTENT_FINDER = Pattern.compile("^\\s+(\\d+)\\s+(\\d+)\\s+\\d+$");
        public static final Pattern SESSIONS_HEAD_FINDER = Pattern.compile("^[\\s]+session[\\s]+pid[\\s]+(cnt|count)");
        public static final int SESSION_GRP_IDX = 1;
        public static final Pattern STANDBY_FINDER = Pattern.compile("^[\\s]*[s|S]tandby: (\\w+)");
        public static final int STANDBY_GRP_IDX = 1;


        private DUMP_TAG()
        {
        }
    }

    static final class LPCollector
        implements TrackCollector
    {

        private String collectTracks(BufferedReader bufferedreader, List list, Map map)
            throws NumberFormatException, IOException
        {
            do
            {
                Matcher matcher;
label0:
                {
                    String s = bufferedreader.readLine();
                    if(s != null)
                    {
                        matcher = TRACK_CONTENT_FINDER.matcher(s);
                        if(matcher.find())
                            break label0;
                    }
                    return s;
                }
                int i = Integer.valueOf(matcher.group(4)).intValue();
                Integer integer = (Integer)map.get(Integer.valueOf(i));
                if(integer != null)
                    list.add(new AudioOutputClient(i, integer.intValue(), Integer.valueOf(matcher.group(3)).intValue(), "yes".equals(matcher.group(2))));
            } while(true);
        }

        public Result collectTracks(BufferedReader bufferedreader, String s, List list, Map map)
            throws IOException
        {
            if(ACTIVE_TRACKS_FINDER.matcher(s).find())
            {
                bufferedreader.readLine();
                return new Result(true, collectTracks(bufferedreader, list, map));
            } else
            {
                return AudioOutputHelper.UNHANDLED;
            }
        }

        public static final Pattern ACTIVE_TRACKS_FINDER = Pattern.compile("^[\\s]+[\\d]+[\\s]+Tracks of which [\\d]+ are active");
        public static final int TRACK_ACTIVE_IDX = 2;
        public static final Pattern TRACK_CONTENT_FINDER = Pattern.compile("^(\\s|F)+\\d+\\s+(\\w+)\\s+\\d+\\s+(\\d+)\\s+\\d+\\s+\\d+\\s+(\\d+)\\s.+");
        public static final int TRACK_SESSION_GRP_IDX = 4;
        public static final int TRACK_STREAM_TYPE_GRP_IDX = 3;


        LPCollector()
        {
        }
    }

    static final class Result
    {

        public final boolean mHandled;
        public final String mSkipped;

        public Result(boolean flag, String s)
        {
            mHandled = flag;
            mSkipped = s;
        }
    }

    static interface TrackCollector
    {

        public abstract Result collectTracks(BufferedReader bufferedreader, String s, List list, Map map)
            throws IOException;
    }


    public AudioOutputHelper()
    {
    }

    private static String collectSessions(BufferedReader bufferedreader, Map map)
        throws NumberFormatException, IOException
    {
        do
        {
            Matcher matcher;
label0:
            {
                String s = bufferedreader.readLine();
                if(s != null)
                {
                    matcher = DUMP_TAG.SESSIONS_CONTENT_FINDER.matcher(s);
                    if(matcher.find())
                        break label0;
                }
                return s;
            }
            map.put(Integer.valueOf(Integer.valueOf(matcher.group(1)).intValue()), Integer.valueOf(Integer.valueOf(matcher.group(2)).intValue()));
        } while(true);
    }

    public static List getActiveClientProcessList(List list, Context context, boolean flag)
    {
        if(list == null)
            return null;
        Object obj = parseAudioFlingerDump(context);
        if(obj == null)
            return null;
        context = new ArrayList();
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            Object obj1 = (AudioOutputClient)((Iterator) (obj)).next();
            if(((AudioOutputClient) (obj1)).mActive)
            {
                int i = ((AudioOutputClient) (obj1)).mProcessId;
                obj1 = list.iterator();
                while(((Iterator) (obj1)).hasNext()) 
                {
                    android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)((Iterator) (obj1)).next();
                    if(runningappprocessinfo.pid == i)
                        context.add(runningappprocessinfo);
                }
            }
        }

        if(flag)
            context.addAll(getMainProcessNames(context, list));
        return context;
    }

    public static List getActiveReceiverNameList(Context context)
    {
        Object obj;
        Object obj1;
        android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo;
        Iterator iterator;
        ResolveInfo resolveinfo;
        try
        {
            obj = JVM INSTR new #168 <Class Intent>;
            ((Intent) (obj)).Intent("android.intent.action.MEDIA_BUTTON");
            obj = AppGlobals.getPackageManager().queryIntentReceivers(((Intent) (obj)), null, 0, 0);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_39;
        if(((ParceledListSlice) (obj)).getList().size() != 0)
            break MISSING_BLOCK_LABEL_41;
        return null;
        obj1 = getActiveClientProcessList(ActivityManagerNative.getDefault().getRunningAppProcesses(), context, true);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_68;
        if(!((List) (obj1)).isEmpty())
            break MISSING_BLOCK_LABEL_70;
        return null;
        context = JVM INSTR new #121 <Class ArrayList>;
        context.ArrayList();
        obj1 = ((Iterable) (obj1)).iterator();
label0:
        do
        {
            if(!((Iterator) (obj1)).hasNext())
                break;
            runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)((Iterator) (obj1)).next();
            iterator = ((ParceledListSlice) (obj)).getList().iterator();
            do
            {
                if(!iterator.hasNext())
                    continue label0;
                resolveinfo = (ResolveInfo)iterator.next();
            } while(resolveinfo.activityInfo == null || !runningappprocessinfo.processName.equals(resolveinfo.activityInfo.processName));
            context.add(runningappprocessinfo.processName);
        } while(true);
        return context;
    }

    private static List getMainProcessNames(List list, List list1)
    {
        ArrayList arraylist = new ArrayList();
        for(list = list.iterator(); list.hasNext();)
        {
            android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)list.next();
            int i = runningappprocessinfo.processName.indexOf(":");
            if(i > 0)
            {
                String s = runningappprocessinfo.processName.substring(0, i);
                Iterator iterator = list1.iterator();
                while(iterator.hasNext()) 
                {
                    android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo1 = (android.app.ActivityManager.RunningAppProcessInfo)iterator.next();
                    if(s.equals(runningappprocessinfo1.processName))
                        arraylist.add(runningappprocessinfo1);
                }
            }
        }

        return arraylist;
    }

    public static boolean hasActiveReceivers(Context context)
    {
        context = getActiveReceiverNameList(context);
        boolean flag;
        if(context != null)
            flag = context.isEmpty() ^ true;
        else
            flag = false;
        return flag;
    }

    private static boolean isStandBy(String s)
    {
        boolean flag = true;
        boolean flag1 = true;
        int i;
        try
        {
            i = Integer.valueOf(s).intValue();
        }
        catch(NumberFormatException numberformatexception)
        {
            boolean flag2 = flag;
            if(!Boolean.valueOf(s).booleanValue())
                flag2 = "yes".equals(s);
            return flag2;
        }
        if(i == 0)
            flag1 = false;
        return flag1;
    }

    public static List parseAudioFlingerDump(Context context)
    {
        Object obj = null;
        int i;
        long l;
        int j;
        try
        {
            context = context.getFilesDir();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context = ((Context) (obj));
        }
        obj = context;
        if(context == null)
            obj = new File("/cache");
        i = Process.myPid();
        l = Thread.currentThread().getId();
        j = 1;
        for(context = new File(((File) (obj)), String.format("audio_flinger_%d_%d_%d.dump", new Object[] {
    Integer.valueOf(i), Long.valueOf(l), Integer.valueOf(0)
})); context.exists();)
        {
            context = new File(((File) (obj)), String.format("audio_flinger_%d_%d_%d.dump", new Object[] {
                Integer.valueOf(i), Long.valueOf(l), Integer.valueOf(j)
            }));
            j++;
        }

        obj = parseAudioFlingerDumpInternal(context);
        context.delete();
        return ((List) (obj));
    }

    private static List parseAudioFlingerDumpInternal(File file)
    {
        String s;
        BufferedReader bufferedreader;
        ArrayList arraylist;
        Object obj;
        Object obj2;
        Object obj3;
        s = null;
        bufferedreader = null;
        arraylist = null;
        obj = null;
        obj2 = null;
        obj3 = obj;
        Object obj4 = JVM INSTR new #313 <Class FileOutputStream>;
        obj3 = obj;
        ((FileOutputStream) (obj4)).FileOutputStream(file);
        ServiceManager.getService("media.audio_flinger").dump(((FileOutputStream) (obj4)).getFD(), null);
        if(obj4 != null)
            try
            {
                ((FileOutputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
_L1:
        obj3 = null;
        obj4 = JVM INSTR new #339 <Class FileInputStream>;
        ((FileInputStream) (obj4)).FileInputStream(file);
        obj3 = obj4;
        break MISSING_BLOCK_LABEL_76;
        Object obj1;
        obj1;
        obj4 = obj2;
_L14:
        obj3 = obj4;
        Log.e(TAG, ((Exception) (obj1)).toString());
        if(obj4 != null)
            try
            {
                ((FileOutputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
          goto _L1
        obj1;
        obj4 = s;
_L13:
        obj3 = obj4;
        Log.e(TAG, ((IOException) (obj1)).toString());
        if(obj4 != null)
            try
            {
                ((FileOutputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
          goto _L1
        obj1;
        obj4 = bufferedreader;
_L12:
        obj3 = obj4;
        Log.e(TAG, ((RemoteException) (obj1)).toString());
        if(obj4 != null)
            try
            {
                ((FileOutputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
          goto _L1
        obj1;
        obj4 = arraylist;
_L11:
        obj3 = obj4;
        Log.e(TAG, ((FileNotFoundException) (obj1)).toString());
        if(obj4 != null)
            try
            {
                ((FileOutputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
          goto _L1
        file;
_L10:
        if(obj3 != null)
            try
            {
                ((FileOutputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
        throw file;
_L9:
        if(obj3 == null)
            return null;
        bufferedreader = new BufferedReader(new InputStreamReader(((InputStream) (obj3))));
        arraylist = JVM INSTR new #121 <Class ArrayList>;
        arraylist.ArrayList();
        boolean flag;
        obj4 = null;
        flag = false;
        file = null;
_L4:
        if(file == null) goto _L3; else goto _L2
_L2:
        obj1 = null;
        obj2 = file;
_L6:
        if(!DUMP_TAG.SESSIONS_HEAD_FINDER.matcher(((CharSequence) (obj2))).matches())
            break MISSING_BLOCK_LABEL_378;
        file = ((File) (obj4));
        if(obj4 != null)
            break MISSING_BLOCK_LABEL_336;
        file = JVM INSTR new #368 <Class HashMap>;
        file.HashMap();
        obj1 = collectSessions(bufferedreader, file);
        obj4 = file;
        file = ((File) (obj1));
          goto _L4
_L3:
        s = bufferedreader.readLine();
        obj2 = s;
        obj1 = file;
        if(s != null) goto _L6; else goto _L5
_L5:
        try
        {
            bufferedreader.close();
        }
        // Misplaced declaration of an exception variable
        catch(File file) { }
        try
        {
            ((InputStream) (obj3)).close();
        }
        // Misplaced declaration of an exception variable
        catch(File file) { }
        return arraylist;
        file = DUMP_TAG.STANDBY_FINDER.matcher(((CharSequence) (obj2)));
        if(!file.find())
            break MISSING_BLOCK_LABEL_413;
        flag = isStandBy(file.group(1).trim());
        file = ((File) (obj1));
          goto _L4
        file = ((File) (obj1));
        if(flag) goto _L4; else goto _L7
_L7:
        file = COLLECTOR;
        if(file == null)
            break MISSING_BLOCK_LABEL_448;
        file = file.collectTracks(bufferedreader, ((String) (obj2)), arraylist, ((Map) (obj4))).mSkipped;
          goto _L4
        file = COLLECTOR_LP.collectTracks(bufferedreader, ((String) (obj2)), arraylist, ((Map) (obj4)));
        if(!((Result) (file)).mHandled)
            break MISSING_BLOCK_LABEL_517;
        file = ((Result) (file)).mSkipped;
        COLLECTOR = COLLECTOR_LP;
        Log.i(TAG, "collector=lp");
          goto _L4
        file;
        Log.e(TAG, file.toString());
        try
        {
            bufferedreader.close();
        }
        // Misplaced declaration of an exception variable
        catch(File file) { }
        try
        {
            ((InputStream) (obj3)).close();
        }
        // Misplaced declaration of an exception variable
        catch(File file) { }
        return null;
        obj2 = COLLECTOR_COMPAT.collectTracks(bufferedreader, ((String) (obj2)), arraylist, ((Map) (obj4)));
        file = ((File) (obj1));
        if(!((Result) (obj2)).mHandled) goto _L4; else goto _L8
_L8:
        file = ((Result) (obj2)).mSkipped;
        COLLECTOR = COLLECTOR_COMPAT;
        Log.i(TAG, "collector=compat");
          goto _L4
        file;
        try
        {
            bufferedreader.close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
        try
        {
            ((InputStream) (obj3)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj3) { }
        throw file;
        file;
          goto _L9
        file;
        obj3 = obj4;
          goto _L10
        obj1;
          goto _L11
        obj1;
          goto _L12
        obj1;
          goto _L13
        obj1;
          goto _L14
    }

    private static TrackCollector COLLECTOR = null;
    private static final TrackCollector COLLECTOR_COMPAT = new CompatCollector();
    private static final TrackCollector COLLECTOR_LP = new LPCollector();
    private static final String DEFAULT_TEMP_FILE = "audio_flinger_%d_%d_%d.dump";
    private static final String TAG = miui/util/AudioOutputHelper.getName();
    static final Result UNHANDLED = new Result(false, null);

}
