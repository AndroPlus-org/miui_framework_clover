// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.os.Handler;

// Referenced classes of package android.media:
//            WebVttRenderingWidget, SRTTrack, MediaFormat, SubtitleTrack

public class SRTRenderer extends SubtitleController.Renderer
{

    public SRTRenderer(Context context)
    {
        this(context, null);
    }

    SRTRenderer(Context context, Handler handler)
    {
        mContext = context;
        boolean flag;
        if(handler == null)
            flag = true;
        else
            flag = false;
        mRender = flag;
        mEventHandler = handler;
    }

    public SubtitleTrack createTrack(MediaFormat mediaformat)
    {
        if(mRender && mRenderingWidget == null)
            mRenderingWidget = new WebVttRenderingWidget(mContext);
        if(mRender)
            return new SRTTrack(mRenderingWidget, mediaformat);
        else
            return new SRTTrack(mEventHandler, mediaformat);
    }

    public boolean supports(MediaFormat mediaformat)
    {
        boolean flag = true;
        if(mediaformat.containsKey("mime"))
        {
            if(!mediaformat.getString("mime").equals("application/x-subrip"))
                return false;
            boolean flag1 = mRender;
            boolean flag2;
            if(mediaformat.getInteger("is-timed-text", 0) == 0)
                flag2 = true;
            else
                flag2 = false;
            if(flag1 == flag2)
                flag2 = flag;
            else
                flag2 = false;
            return flag2;
        } else
        {
            return false;
        }
    }

    private final Context mContext;
    private final Handler mEventHandler;
    private final boolean mRender;
    private WebVttRenderingWidget mRenderingWidget;
}
