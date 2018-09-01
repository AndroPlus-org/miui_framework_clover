// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.os.*;
import android.view.accessibility.CaptioningManager;
import java.util.*;

// Referenced classes of package android.media:
//            SubtitleTrack, MediaFormat, MediaTimeProvider

public class SubtitleController
{
    public static interface Anchor
    {

        public abstract Looper getSubtitleLooper();

        public abstract void setSubtitleWidget(SubtitleTrack.RenderingWidget renderingwidget);
    }

    public static interface Listener
    {

        public abstract void onSubtitleTrackSelected(SubtitleTrack subtitletrack);
    }

    public static abstract class Renderer
    {

        public abstract SubtitleTrack createTrack(MediaFormat mediaformat);

        public abstract boolean supports(MediaFormat mediaformat);

        public Renderer()
        {
        }
    }


    static void _2D_wrap0(SubtitleController subtitlecontroller)
    {
        subtitlecontroller.doHide();
    }

    static void _2D_wrap1(SubtitleController subtitlecontroller)
    {
        subtitlecontroller.doSelectDefaultTrack();
    }

    static void _2D_wrap2(SubtitleController subtitlecontroller, SubtitleTrack subtitletrack)
    {
        subtitlecontroller.doSelectTrack(subtitletrack);
    }

    static void _2D_wrap3(SubtitleController subtitlecontroller)
    {
        subtitlecontroller.doShow();
    }

    public SubtitleController(Context context, MediaTimeProvider mediatimeprovider, Listener listener)
    {
        mCaptioningChangeListener = new android.view.accessibility.CaptioningManager.CaptioningChangeListener() {

            public void onEnabledChanged(boolean flag)
            {
                selectDefaultTrack();
            }

            public void onLocaleChanged(Locale locale)
            {
                selectDefaultTrack();
            }

            final SubtitleController this$0;

            
            {
                this$0 = SubtitleController.this;
                super();
            }
        }
;
        mTrackIsExplicit = false;
        mVisibilityIsExplicit = false;
        mTimeProvider = mediatimeprovider;
        mListener = listener;
        mRenderers = new Vector();
        mShowing = false;
        mTracks = new Vector();
        mCaptioningManager = (CaptioningManager)context.getSystemService("captioning");
    }

    private void checkAnchorLooper()
    {
        if(!_2D_assertionsDisabled && mHandler == null)
            throw new AssertionError("Should have a looper already");
        if(!_2D_assertionsDisabled && Looper.myLooper() != mHandler.getLooper())
            throw new AssertionError("Must be called from the anchor's looper");
        else
            return;
    }

    private void doHide()
    {
        mVisibilityIsExplicit = true;
        if(mSelectedTrack != null)
            mSelectedTrack.hide();
        mShowing = false;
    }

    private void doSelectDefaultTrack()
    {
        if(!mTrackIsExplicit) goto _L2; else goto _L1
_L1:
        if(mVisibilityIsExplicit) goto _L4; else goto _L3
_L3:
        if(!mCaptioningManager.isEnabled() && (mSelectedTrack == null || mSelectedTrack.getFormat().getInteger("is-forced-subtitle", 0) == 0)) goto _L6; else goto _L5
_L5:
        show();
_L7:
        mVisibilityIsExplicit = false;
_L4:
        return;
_L6:
        if(mSelectedTrack != null && mSelectedTrack.getTrackType() == 4)
            hide();
        if(true) goto _L7; else goto _L2
_L2:
        SubtitleTrack subtitletrack = getDefaultTrack();
        if(subtitletrack != null)
        {
            selectTrack(subtitletrack);
            mTrackIsExplicit = false;
            if(!mVisibilityIsExplicit)
            {
                show();
                mVisibilityIsExplicit = false;
            }
        }
        return;
    }

    private void doSelectTrack(SubtitleTrack subtitletrack)
    {
        mTrackIsExplicit = true;
        if(mSelectedTrack == subtitletrack)
            return;
        if(mSelectedTrack != null)
        {
            mSelectedTrack.hide();
            mSelectedTrack.setTimeProvider(null);
        }
        mSelectedTrack = subtitletrack;
        if(mAnchor != null)
            mAnchor.setSubtitleWidget(getRenderingWidget());
        if(mSelectedTrack != null)
        {
            mSelectedTrack.setTimeProvider(mTimeProvider);
            mSelectedTrack.show();
        }
        if(mListener != null)
            mListener.onSubtitleTrackSelected(subtitletrack);
    }

    private void doShow()
    {
        mShowing = true;
        mVisibilityIsExplicit = true;
        if(mSelectedTrack != null)
            mSelectedTrack.show();
    }

    private SubtitleTrack.RenderingWidget getRenderingWidget()
    {
        if(mSelectedTrack == null)
            return null;
        else
            return mSelectedTrack.getRenderingWidget();
    }

    private void processOnAnchor(Message message)
    {
        if(!_2D_assertionsDisabled && mHandler == null)
            throw new AssertionError("Should have a looper already");
        if(Looper.myLooper() == mHandler.getLooper())
            mHandler.dispatchMessage(message);
        else
            mHandler.sendMessage(message);
    }

    public SubtitleTrack addTrack(MediaFormat mediaformat)
    {
        Vector vector = mRenderers;
        vector;
        JVM INSTR monitorenter ;
        Iterator iterator = mRenderers.iterator();
        Object obj;
        do
        {
            do
            {
                if(!iterator.hasNext())
                    break MISSING_BLOCK_LABEL_116;
                obj = (Renderer)iterator.next();
            } while(!((Renderer) (obj)).supports(mediaformat));
            obj = ((Renderer) (obj)).createTrack(mediaformat);
        } while(obj == null);
        mediaformat = mTracks;
        mediaformat;
        JVM INSTR monitorenter ;
        if(mTracks.size() == 0)
            mCaptioningManager.addCaptioningChangeListener(mCaptioningChangeListener);
        mTracks.add(obj);
        mediaformat;
        JVM INSTR monitorexit ;
        vector;
        JVM INSTR monitorexit ;
        return ((SubtitleTrack) (obj));
        Exception exception;
        exception;
        mediaformat;
        JVM INSTR monitorexit ;
        throw exception;
        mediaformat;
        vector;
        JVM INSTR monitorexit ;
        throw mediaformat;
        vector;
        JVM INSTR monitorexit ;
        return null;
    }

    protected void finalize()
        throws Throwable
    {
        mCaptioningManager.removeCaptioningChangeListener(mCaptioningChangeListener);
        super.finalize();
    }

    public SubtitleTrack getDefaultTrack()
    {
        SubtitleTrack subtitletrack;
        int i;
        Locale locale;
        Locale locale1;
        boolean flag;
        subtitletrack = null;
        i = -1;
        locale = mCaptioningManager.getLocale();
        locale1 = locale;
        if(locale == null)
            locale1 = Locale.getDefault();
        flag = mCaptioningManager.isEnabled();
        Vector vector = mTracks;
        vector;
        JVM INSTR monitorenter ;
        Iterator iterator = mTracks.iterator();
_L3:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        SubtitleTrack subtitletrack1;
        MediaFormat mediaformat;
        String s;
        subtitletrack1 = (SubtitleTrack)iterator.next();
        mediaformat = subtitletrack1.getFormat();
        s = mediaformat.getString("language");
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        if(mediaformat.getInteger("is-forced-subtitle", 0) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(mediaformat.getInteger("is-autoselect", 1) != 0)
            flag2 = true;
        else
            flag2 = false;
        if(mediaformat.getInteger("is-default", 0) != 0)
            flag3 = true;
        else
            flag3 = false;
        if(locale1 == null)
            break MISSING_BLOCK_LABEL_304;
        if(locale1.getLanguage().equals("") || locale1.getISO3Language().equals(s))
            break MISSING_BLOCK_LABEL_304;
        flag4 = locale1.getLanguage().equals(s);
_L4:
        int j;
        byte byte0;
        int k;
        int l;
        if(flag1)
            j = 0;
        else
            j = 8;
        if(locale == null && flag3)
            byte0 = 4;
        else
            byte0 = 0;
        if(flag2)
            k = 0;
        else
            k = 2;
        if(flag4)
            l = 1;
        else
            l = 0;
        j = j + byte0 + k + l;
        if((!(flag ^ true) || !(flag1 ^ true)) && (locale == null && flag3 || flag4 && (flag2 || flag1 || locale != null)) && j > i)
        {
            i = j;
            subtitletrack = subtitletrack1;
        }
          goto _L3
        flag4 = true;
          goto _L4
_L2:
        vector;
        JVM INSTR monitorexit ;
        return subtitletrack;
        Exception exception;
        exception;
        throw exception;
          goto _L3
    }

    public SubtitleTrack getSelectedTrack()
    {
        return mSelectedTrack;
    }

    public SubtitleTrack[] getTracks()
    {
        Vector vector = mTracks;
        vector;
        JVM INSTR monitorenter ;
        SubtitleTrack asubtitletrack[];
        asubtitletrack = new SubtitleTrack[mTracks.size()];
        mTracks.toArray(asubtitletrack);
        vector;
        JVM INSTR monitorexit ;
        return asubtitletrack;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean hasRendererFor(MediaFormat mediaformat)
    {
        Vector vector = mRenderers;
        vector;
        JVM INSTR monitorenter ;
        Iterator iterator = mRenderers.iterator();
        boolean flag;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_50;
            flag = ((Renderer)iterator.next()).supports(mediaformat);
        } while(!flag);
        vector;
        JVM INSTR monitorexit ;
        return true;
        vector;
        JVM INSTR monitorexit ;
        return false;
        mediaformat;
        throw mediaformat;
    }

    public void hide()
    {
        processOnAnchor(mHandler.obtainMessage(2));
    }

    public void registerRenderer(Renderer renderer)
    {
        Vector vector = mRenderers;
        vector;
        JVM INSTR monitorenter ;
        if(!mRenderers.contains(renderer))
            mRenderers.add(renderer);
        vector;
        JVM INSTR monitorexit ;
        return;
        renderer;
        throw renderer;
    }

    public void reset()
    {
        checkAnchorLooper();
        hide();
        selectTrack(null);
        mTracks.clear();
        mTrackIsExplicit = false;
        mVisibilityIsExplicit = false;
        mCaptioningManager.removeCaptioningChangeListener(mCaptioningChangeListener);
    }

    public void selectDefaultTrack()
    {
        processOnAnchor(mHandler.obtainMessage(4));
    }

    public boolean selectTrack(SubtitleTrack subtitletrack)
    {
        if(subtitletrack != null && mTracks.contains(subtitletrack) ^ true)
        {
            return false;
        } else
        {
            processOnAnchor(mHandler.obtainMessage(3, subtitletrack));
            return true;
        }
    }

    public void setAnchor(Anchor anchor)
    {
        if(mAnchor == anchor)
            return;
        if(mAnchor != null)
        {
            checkAnchorLooper();
            mAnchor.setSubtitleWidget(null);
        }
        mAnchor = anchor;
        mHandler = null;
        if(mAnchor != null)
        {
            mHandler = new Handler(mAnchor.getSubtitleLooper(), mCallback);
            checkAnchorLooper();
            mAnchor.setSubtitleWidget(getRenderingWidget());
        }
    }

    public void show()
    {
        processOnAnchor(mHandler.obtainMessage(1));
    }

    static final boolean _2D_assertionsDisabled = android/media/SubtitleController.desiredAssertionStatus() ^ true;
    private static final int WHAT_HIDE = 2;
    private static final int WHAT_SELECT_DEFAULT_TRACK = 4;
    private static final int WHAT_SELECT_TRACK = 3;
    private static final int WHAT_SHOW = 1;
    private Anchor mAnchor;
    private final android.os.Handler.Callback mCallback = new android.os.Handler.Callback() {

        public boolean handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                return false;

            case 1: // '\001'
                SubtitleController._2D_wrap3(SubtitleController.this);
                return true;

            case 2: // '\002'
                SubtitleController._2D_wrap0(SubtitleController.this);
                return true;

            case 3: // '\003'
                SubtitleController._2D_wrap2(SubtitleController.this, (SubtitleTrack)message.obj);
                return true;

            case 4: // '\004'
                SubtitleController._2D_wrap1(SubtitleController.this);
                break;
            }
            return true;
        }

        final SubtitleController this$0;

            
            {
                this$0 = SubtitleController.this;
                super();
            }
    }
;
    private android.view.accessibility.CaptioningManager.CaptioningChangeListener mCaptioningChangeListener;
    private CaptioningManager mCaptioningManager;
    private Handler mHandler;
    private Listener mListener;
    private Vector mRenderers;
    private SubtitleTrack mSelectedTrack;
    private boolean mShowing;
    private MediaTimeProvider mTimeProvider;
    private boolean mTrackIsExplicit;
    private Vector mTracks;
    private boolean mVisibilityIsExplicit;

}
