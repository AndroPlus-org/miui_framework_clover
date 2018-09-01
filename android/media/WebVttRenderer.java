// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;

// Referenced classes of package android.media:
//            WebVttRenderingWidget, WebVttTrack, MediaFormat, SubtitleTrack

public class WebVttRenderer extends SubtitleController.Renderer
{

    public WebVttRenderer(Context context)
    {
        mContext = context;
    }

    public SubtitleTrack createTrack(MediaFormat mediaformat)
    {
        if(mRenderingWidget == null)
            mRenderingWidget = new WebVttRenderingWidget(mContext);
        return new WebVttTrack(mRenderingWidget, mediaformat);
    }

    public boolean supports(MediaFormat mediaformat)
    {
        if(mediaformat.containsKey("mime"))
            return mediaformat.getString("mime").equals("text/vtt");
        else
            return false;
    }

    private final Context mContext;
    private WebVttRenderingWidget mRenderingWidget;
}
