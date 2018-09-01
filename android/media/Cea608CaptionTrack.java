// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.util.Vector;

// Referenced classes of package android.media:
//            SubtitleTrack, Cea608CCParser, Cea608CCWidget, MediaFormat

class Cea608CaptionTrack extends SubtitleTrack
{

    Cea608CaptionTrack(Cea608CCWidget cea608ccwidget, MediaFormat mediaformat)
    {
        super(mediaformat);
        mRenderingWidget = cea608ccwidget;
        mCCParser = new Cea608CCParser(mRenderingWidget);
    }

    public SubtitleTrack.RenderingWidget getRenderingWidget()
    {
        return mRenderingWidget;
    }

    public void onData(byte abyte0[], boolean flag, long l)
    {
        mCCParser.parse(abyte0);
    }

    public void updateView(Vector vector)
    {
    }

    private final Cea608CCParser mCCParser;
    private final Cea608CCWidget mRenderingWidget;
}
