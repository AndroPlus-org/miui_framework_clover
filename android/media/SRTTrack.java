// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Handler;
import android.os.Parcel;
import android.util.Log;
import java.io.*;
import java.util.*;

// Referenced classes of package android.media:
//            WebVttTrack, TextTrackCue, SubtitleData, TextTrackCueSpan, 
//            WebVttRenderingWidget, MediaFormat

class SRTTrack extends WebVttTrack
{

    SRTTrack(WebVttRenderingWidget webvttrenderingwidget, MediaFormat mediaformat)
    {
        super(webvttrenderingwidget, mediaformat);
        mEventHandler = null;
    }

    SRTTrack(Handler handler, MediaFormat mediaformat)
    {
        super(null, mediaformat);
        mEventHandler = handler;
    }

    private static long parseMs(String s)
    {
        return 60L * Long.parseLong(s.split(":")[0].trim()) * 60L * 1000L + 60L * Long.parseLong(s.split(":")[1].trim()) * 1000L + 1000L * Long.parseLong(s.split(":")[2].split(",")[0].trim()) + Long.parseLong(s.split(":")[2].split(",")[1].trim());
    }

    protected void onData(SubtitleData subtitledata)
    {
        int i = 0;
        TextTrackCue texttrackcue;
        String as[];
        int j;
        texttrackcue = JVM INSTR new #58  <Class TextTrackCue>;
        texttrackcue.TextTrackCue();
        texttrackcue.mStartTimeMs = subtitledata.getStartTimeUs() / 1000L;
        texttrackcue.mEndTimeMs = (subtitledata.getStartTimeUs() + subtitledata.getDurationUs()) / 1000L;
        String s = JVM INSTR new #35  <Class String>;
        s.String(subtitledata.getData(), "UTF-8");
        as = s.split("\\r?\\n");
        texttrackcue.mLines = new TextTrackCueSpan[as.length][];
        j = as.length;
        int k = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        subtitledata = as[i];
        TextTrackCueSpan texttrackcuespan = JVM INSTR new #96  <Class TextTrackCueSpan>;
        texttrackcuespan.TextTrackCueSpan(subtitledata, -1L);
        texttrackcue.mLines[k] = (new TextTrackCueSpan[] {
            texttrackcuespan
        });
        i++;
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        addCue(texttrackcue);
_L3:
        return;
        subtitledata;
        Log.w("SRTTrack", (new StringBuilder()).append("subtitle data is not UTF-8 encoded: ").append(subtitledata).toString());
          goto _L3
    }

    public void onData(byte abyte0[], boolean flag, long l)
    {
        InputStreamReader inputstreamreader = JVM INSTR new #131 <Class InputStreamReader>;
        ByteArrayInputStream bytearrayinputstream = JVM INSTR new #133 <Class ByteArrayInputStream>;
        bytearrayinputstream.ByteArrayInputStream(abyte0);
        inputstreamreader.InputStreamReader(bytearrayinputstream, "UTF-8");
        abyte0 = JVM INSTR new #141 <Class BufferedReader>;
        abyte0.BufferedReader(inputstreamreader);
_L8:
        if(abyte0.readLine() == null) goto _L2; else goto _L1
_L1:
        Object obj = abyte0.readLine();
        if(obj != null) goto _L3; else goto _L2
_L2:
        return;
_L3:
        TextTrackCue texttrackcue;
        ArrayList arraylist;
        texttrackcue = JVM INSTR new #58  <Class TextTrackCue>;
        texttrackcue.TextTrackCue();
        String as[] = ((String) (obj)).split("-->");
        texttrackcue.mStartTimeMs = parseMs(as[0]);
        texttrackcue.mEndTimeMs = parseMs(as[1]);
        arraylist = JVM INSTR new #153 <Class ArrayList>;
        arraylist.ArrayList();
_L4:
        as = abyte0.readLine();
        if(as == null)
            break MISSING_BLOCK_LABEL_175;
        flag = as.trim().equals("");
_L5:
        if(flag)
            break MISSING_BLOCK_LABEL_180;
        arraylist.add(as);
          goto _L4
        abyte0;
        Log.w("SRTTrack", (new StringBuilder()).append("subtitle data is not UTF-8 encoded: ").append(abyte0).toString());
          goto _L2
        flag = true;
          goto _L5
        texttrackcue.mLines = new TextTrackCueSpan[arraylist.size()][];
        texttrackcue.mStrings = (String[])arraylist.toArray(new String[0]);
        as = arraylist.iterator();
        int i = 0;
_L7:
        if(!as.hasNext())
            break; /* Loop/switch isn't completed */
        String s = (String)as.next();
        TextTrackCueSpan texttrackcuespan = JVM INSTR new #96  <Class TextTrackCueSpan>;
        texttrackcuespan.TextTrackCueSpan(s, -1L);
        texttrackcue.mStrings[i] = s;
        texttrackcue.mLines[i] = (new TextTrackCueSpan[] {
            texttrackcuespan
        });
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        addCue(texttrackcue);
          goto _L8
        abyte0;
        Log.e("SRTTrack", abyte0.getMessage(), abyte0);
          goto _L2
    }

    public void updateView(Vector vector)
    {
        if(getRenderingWidget() != null)
        {
            super.updateView(vector);
            return;
        }
        if(mEventHandler == null)
            return;
        Object obj1;
        for(Iterator iterator = vector.iterator(); iterator.hasNext(); mEventHandler.sendMessage(((android.os.Message) (obj1))))
        {
            Object obj = (SubtitleTrack.Cue)iterator.next();
            TextTrackCue texttrackcue = (TextTrackCue)obj;
            obj1 = Parcel.obtain();
            ((Parcel) (obj1)).writeInt(102);
            ((Parcel) (obj1)).writeInt(7);
            ((Parcel) (obj1)).writeInt((int)((SubtitleTrack.Cue) (obj)).mStartTimeMs);
            ((Parcel) (obj1)).writeInt(16);
            obj = new StringBuilder();
            String as[] = texttrackcue.mStrings;
            int i = as.length;
            for(int j = 0; j < i; j++)
                ((StringBuilder) (obj)).append(as[j]).append('\n');

            byte abyte0[] = ((StringBuilder) (obj)).toString().getBytes();
            ((Parcel) (obj1)).writeInt(abyte0.length);
            ((Parcel) (obj1)).writeByteArray(abyte0);
            obj1 = mEventHandler.obtainMessage(99, 0, 0, obj1);
        }

        vector.clear();
    }

    private static final int KEY_LOCAL_SETTING = 102;
    private static final int KEY_START_TIME = 7;
    private static final int KEY_STRUCT_TEXT = 16;
    private static final int MEDIA_TIMED_TEXT = 99;
    private static final String TAG = "SRTTrack";
    private final Handler mEventHandler;
}
