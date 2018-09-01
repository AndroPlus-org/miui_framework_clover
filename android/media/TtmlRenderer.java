// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;

// Referenced classes of package android.media:
//            TtmlRenderingWidget, TtmlTrack, MediaFormat, SubtitleTrack

public class TtmlRenderer extends SubtitleController.Renderer
{

    public TtmlRenderer(Context context)
    {
        mContext = context;
    }

    public SubtitleTrack createTrack(MediaFormat mediaformat)
    {
        if(mRenderingWidget == null)
            mRenderingWidget = new TtmlRenderingWidget(mContext);
        return new TtmlTrack(mRenderingWidget, mediaformat);
    }

    public boolean supports(MediaFormat mediaformat)
    {
        if(mediaformat.containsKey("mime"))
            return mediaformat.getString("mime").equals("application/ttml+xml");
        else
            return false;
    }

    private static final String MEDIA_MIMETYPE_TEXT_TTML = "application/ttml+xml";
    private final Context mContext;
    private TtmlRenderingWidget mRenderingWidget;
}
