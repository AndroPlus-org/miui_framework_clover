// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.ActivityThread;
import android.content.*;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.display.*;
import android.media.session.MediaSession;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.media:
//            IAudioService, AudioRoutesInfo, IMediaRouterService, AudioManager, 
//            MediaRouterClientState, IMediaRouterClient, RemoteControlClient, VolumeProvider

public class MediaRouter
{
    public static abstract class Callback
    {

        public abstract void onRouteAdded(MediaRouter mediarouter, RouteInfo routeinfo);

        public abstract void onRouteChanged(MediaRouter mediarouter, RouteInfo routeinfo);

        public abstract void onRouteGrouped(MediaRouter mediarouter, RouteInfo routeinfo, RouteGroup routegroup, int i);

        public void onRoutePresentationDisplayChanged(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public abstract void onRouteRemoved(MediaRouter mediarouter, RouteInfo routeinfo);

        public abstract void onRouteSelected(MediaRouter mediarouter, int i, RouteInfo routeinfo);

        public abstract void onRouteUngrouped(MediaRouter mediarouter, RouteInfo routeinfo, RouteGroup routegroup);

        public abstract void onRouteUnselected(MediaRouter mediarouter, int i, RouteInfo routeinfo);

        public abstract void onRouteVolumeChanged(MediaRouter mediarouter, RouteInfo routeinfo);

        public Callback()
        {
        }
    }

    static class CallbackInfo
    {

        public boolean filterRouteEvent(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if((flags & 2) == 0)
                if((type & i) != 0)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public boolean filterRouteEvent(RouteInfo routeinfo)
        {
            return filterRouteEvent(routeinfo.mSupportedTypes);
        }

        public final Callback cb;
        public int flags;
        public final MediaRouter router;
        public int type;

        public CallbackInfo(Callback callback, int i, int j, MediaRouter mediarouter)
        {
            cb = callback;
            type = i;
            flags = j;
            router = mediarouter;
        }
    }

    public static class RouteCategory
    {

        public CharSequence getName()
        {
            return getName(MediaRouter.sStatic.mResources);
        }

        public CharSequence getName(Context context)
        {
            return getName(context.getResources());
        }

        CharSequence getName(Resources resources)
        {
            if(mNameResId != 0)
                return resources.getText(mNameResId);
            else
                return mName;
        }

        public List getRoutes(List list)
        {
            int i;
            if(list == null)
                list = new ArrayList();
            else
                list.clear();
            i = MediaRouter.getRouteCountStatic();
            for(int j = 0; j < i; j++)
            {
                RouteInfo routeinfo = MediaRouter.getRouteAtStatic(j);
                if(routeinfo.mCategory == this)
                    list.add(routeinfo);
            }

            return list;
        }

        public int getSupportedTypes()
        {
            return mTypes;
        }

        public boolean isGroupable()
        {
            return mGroupable;
        }

        public boolean isSystem()
        {
            return mIsSystem;
        }

        public String toString()
        {
            return (new StringBuilder()).append("RouteCategory{ name=").append(getName()).append(" types=").append(MediaRouter.typesToString(mTypes)).append(" groupable=").append(mGroupable).append(" }").toString();
        }

        final boolean mGroupable;
        boolean mIsSystem;
        CharSequence mName;
        int mNameResId;
        int mTypes;

        RouteCategory(int i, int j, boolean flag)
        {
            mNameResId = i;
            mTypes = j;
            mGroupable = flag;
        }

        RouteCategory(CharSequence charsequence, int i, boolean flag)
        {
            mName = charsequence;
            mTypes = i;
            mGroupable = flag;
        }
    }

    public static class RouteGroup extends RouteInfo
    {

        public void addRoute(RouteInfo routeinfo)
        {
            if(routeinfo.getGroup() != null)
                throw new IllegalStateException((new StringBuilder()).append("Route ").append(routeinfo).append(" is already part of a group.").toString());
            if(routeinfo.getCategory() != mCategory)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Route cannot be added to a group with a different category. (Route category=").append(routeinfo.getCategory()).append(" group category=").append(mCategory).append(")").toString());
            } else
            {
                int i = mRoutes.size();
                mRoutes.add(routeinfo);
                routeinfo.mGroup = this;
                mUpdateName = true;
                updateVolume();
                routeUpdated();
                MediaRouter.dispatchRouteGrouped(routeinfo, this, i);
                return;
            }
        }

        public void addRoute(RouteInfo routeinfo, int i)
        {
            if(routeinfo.getGroup() != null)
                throw new IllegalStateException((new StringBuilder()).append("Route ").append(routeinfo).append(" is already part of a group.").toString());
            if(routeinfo.getCategory() != mCategory)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Route cannot be added to a group with a different category. (Route category=").append(routeinfo.getCategory()).append(" group category=").append(mCategory).append(")").toString());
            } else
            {
                mRoutes.add(i, routeinfo);
                routeinfo.mGroup = this;
                mUpdateName = true;
                updateVolume();
                routeUpdated();
                MediaRouter.dispatchRouteGrouped(routeinfo, this, i);
                return;
            }
        }

        CharSequence getName(Resources resources)
        {
            if(mUpdateName)
                updateName();
            return super.getName(resources);
        }

        public RouteInfo getRouteAt(int i)
        {
            return (RouteInfo)mRoutes.get(i);
        }

        public int getRouteCount()
        {
            return mRoutes.size();
        }

        void memberNameChanged(RouteInfo routeinfo, CharSequence charsequence)
        {
            mUpdateName = true;
            routeUpdated();
        }

        void memberStatusChanged(RouteInfo routeinfo, CharSequence charsequence)
        {
            setStatusInt(charsequence);
        }

        void memberVolumeChanged(RouteInfo routeinfo)
        {
            updateVolume();
        }

        public void removeRoute(int i)
        {
            RouteInfo routeinfo = (RouteInfo)mRoutes.remove(i);
            routeinfo.mGroup = null;
            mUpdateName = true;
            updateVolume();
            MediaRouter.dispatchRouteUngrouped(routeinfo, this);
            routeUpdated();
        }

        public void removeRoute(RouteInfo routeinfo)
        {
            if(routeinfo.getGroup() != this)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Route ").append(routeinfo).append(" is not a member of this group.").toString());
            } else
            {
                mRoutes.remove(routeinfo);
                routeinfo.mGroup = null;
                mUpdateName = true;
                updateVolume();
                MediaRouter.dispatchRouteUngrouped(routeinfo, this);
                routeUpdated();
                return;
            }
        }

        public void requestSetVolume(int i)
        {
            int j = getVolumeMax();
            if(j == 0)
                return;
            float f = (float)i / (float)j;
            int l = getRouteCount();
            for(int k = 0; k < l; k++)
            {
                RouteInfo routeinfo = getRouteAt(k);
                routeinfo.requestSetVolume((int)((float)routeinfo.getVolumeMax() * f));
            }

            if(i != mVolume)
            {
                mVolume = i;
                MediaRouter.dispatchRouteVolumeChanged(this);
            }
        }

        public void requestUpdateVolume(int i)
        {
            if(getVolumeMax() == 0)
                return;
            int j = getRouteCount();
            int k = 0;
            for(int l = 0; l < j;)
            {
                RouteInfo routeinfo = getRouteAt(l);
                routeinfo.requestUpdateVolume(i);
                int i1 = routeinfo.getVolume();
                int j1 = k;
                if(i1 > k)
                    j1 = i1;
                l++;
                k = j1;
            }

            if(k != mVolume)
            {
                mVolume = k;
                MediaRouter.dispatchRouteVolumeChanged(this);
            }
        }

        void routeUpdated()
        {
            int i = 0;
            int j = mRoutes.size();
            if(j == 0)
            {
                MediaRouter.removeRouteStatic(this);
                return;
            }
            int k = 0;
            int l = 1;
            int i1 = 1;
            int j1 = 0;
            while(j1 < j) 
            {
                RouteInfo routeinfo = (RouteInfo)mRoutes.get(j1);
                int k1 = i | routeinfo.mSupportedTypes;
                int l1 = routeinfo.getVolumeMax();
                i = k;
                if(l1 > k)
                    i = l1;
                if(routeinfo.getPlaybackType() == 0)
                    k = 1;
                else
                    k = 0;
                l &= k;
                if(routeinfo.getVolumeHandling() == 0)
                    k = 1;
                else
                    k = 0;
                i1 &= k;
                j1++;
                k = i;
                i = k1;
            }
            Drawable drawable;
            if(l != 0)
                j1 = 0;
            else
                j1 = 1;
            mPlaybackType = j1;
            if(i1 != 0)
                i1 = 0;
            else
                i1 = 1;
            mVolumeHandling = i1;
            mSupportedTypes = i;
            mVolumeMax = k;
            if(j == 1)
                drawable = ((RouteInfo)mRoutes.get(0)).getIconDrawable();
            else
                drawable = null;
            mIcon = drawable;
            super.routeUpdated();
        }

        public void setIconDrawable(Drawable drawable)
        {
            mIcon = drawable;
        }

        public void setIconResource(int i)
        {
            setIconDrawable(MediaRouter.sStatic.mResources.getDrawable(i));
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(super.toString());
            stringbuilder.append('[');
            int i = mRoutes.size();
            for(int j = 0; j < i; j++)
            {
                if(j > 0)
                    stringbuilder.append(", ");
                stringbuilder.append(mRoutes.get(j));
            }

            stringbuilder.append(']');
            return stringbuilder.toString();
        }

        void updateName()
        {
            StringBuilder stringbuilder = new StringBuilder();
            int i = mRoutes.size();
            for(int j = 0; j < i; j++)
            {
                RouteInfo routeinfo = (RouteInfo)mRoutes.get(j);
                if(j > 0)
                    stringbuilder.append(", ");
                stringbuilder.append(routeinfo.getName());
            }

            mName = stringbuilder.toString();
            mUpdateName = false;
        }

        void updateVolume()
        {
            int i = getRouteCount();
            int j = 0;
            for(int k = 0; k < i;)
            {
                int l = getRouteAt(k).getVolume();
                int i1 = j;
                if(l > j)
                    i1 = l;
                k++;
                j = i1;
            }

            if(j != mVolume)
            {
                mVolume = j;
                MediaRouter.dispatchRouteVolumeChanged(this);
            }
        }

        final ArrayList mRoutes = new ArrayList();
        private boolean mUpdateName;

        RouteGroup(RouteCategory routecategory)
        {
            super(routecategory);
            mGroup = this;
            mVolumeHandling = 0;
        }
    }

    public static class RouteInfo
    {

        static int _2D_get0(RouteInfo routeinfo)
        {
            return routeinfo.mRealStatusCode;
        }

        private Display choosePresentationDisplay()
        {
            int i = 0;
            int j = 0;
            if((mSupportedTypes & 2) != 0)
            {
                Display adisplay[] = MediaRouter.sStatic.getAllPresentationDisplays();
                if(mPresentationDisplayId >= 0)
                {
                    for(i = adisplay.length; j < i; j++)
                    {
                        Display display = adisplay[j];
                        if(display.getDisplayId() == mPresentationDisplayId)
                            return display;
                    }

                    return null;
                }
                if(mDeviceAddress != null)
                {
                    int l = adisplay.length;
                    for(int k = i; k < l; k++)
                    {
                        Display display1 = adisplay[k];
                        if(display1.getType() == 3 && mDeviceAddress.equals(display1.getAddress()))
                            return display1;
                    }

                    return null;
                }
                if(this == MediaRouter.sStatic.mDefaultAudioVideo && adisplay.length > 0)
                    return adisplay[0];
            }
            return null;
        }

        public RouteCategory getCategory()
        {
            return mCategory;
        }

        public CharSequence getDescription()
        {
            return mDescription;
        }

        public String getDeviceAddress()
        {
            return mDeviceAddress;
        }

        public int getDeviceType()
        {
            return mDeviceType;
        }

        public RouteGroup getGroup()
        {
            return mGroup;
        }

        public Drawable getIconDrawable()
        {
            return mIcon;
        }

        public CharSequence getName()
        {
            return getName(MediaRouter.sStatic.mResources);
        }

        public CharSequence getName(Context context)
        {
            return getName(context.getResources());
        }

        CharSequence getName(Resources resources)
        {
            if(mNameResId != 0)
                return resources.getText(mNameResId);
            else
                return mName;
        }

        public int getPlaybackStream()
        {
            return mPlaybackStream;
        }

        public int getPlaybackType()
        {
            return mPlaybackType;
        }

        public Display getPresentationDisplay()
        {
            return mPresentationDisplay;
        }

        public CharSequence getStatus()
        {
            return mStatus;
        }

        public int getStatusCode()
        {
            return mResolvedStatusCode;
        }

        public int getSupportedTypes()
        {
            return mSupportedTypes;
        }

        public Object getTag()
        {
            return mTag;
        }

        public int getVolume()
        {
            if(mPlaybackType != 0) goto _L2; else goto _L1
_L1:
            int i = 0;
            int j = MediaRouter.sStatic.mAudioService.getStreamVolume(mPlaybackStream);
            i = j;
_L3:
            return i;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaRouter", "Error getting local stream volume", remoteexception);
            if(true) goto _L3; else goto _L2
_L2:
            return mVolume;
        }

        public int getVolumeHandling()
        {
            return mVolumeHandling;
        }

        public int getVolumeMax()
        {
            if(mPlaybackType != 0) goto _L2; else goto _L1
_L1:
            int i = 0;
            int j = MediaRouter.sStatic.mAudioService.getStreamMaxVolume(mPlaybackStream);
            i = j;
_L3:
            return i;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaRouter", "Error getting local stream volume", remoteexception);
            if(true) goto _L3; else goto _L2
_L2:
            return mVolumeMax;
        }

        public boolean isBluetooth()
        {
            boolean flag;
            if(this == MediaRouter.sStatic.mBluetoothA2dpRoute)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isConnecting()
        {
            boolean flag;
            if(mResolvedStatusCode == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isDefault()
        {
            boolean flag;
            if(this == MediaRouter.sStatic.mDefaultAudioVideo)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isEnabled()
        {
            return mEnabled;
        }

        public boolean isSelected()
        {
            boolean flag;
            if(this == MediaRouter.sStatic.mSelectedRoute)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean matchesTypes(int i)
        {
            boolean flag = false;
            if((mSupportedTypes & i) != 0)
                flag = true;
            return flag;
        }

        public void requestSetVolume(int i)
        {
            if(mPlaybackType != 0)
                break MISSING_BLOCK_LABEL_41;
            MediaRouter.sStatic.mAudioService.setStreamVolume(mPlaybackStream, i, 0, ActivityThread.currentPackageName());
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaRouter", "Error setting local stream volume", remoteexception);
              goto _L1
            MediaRouter.sStatic.requestSetVolume(this, i);
              goto _L1
        }

        public void requestUpdateVolume(int i)
        {
            if(mPlaybackType != 0)
                break MISSING_BLOCK_LABEL_59;
            i = Math.max(0, Math.min(getVolume() + i, getVolumeMax()));
            MediaRouter.sStatic.mAudioService.setStreamVolume(mPlaybackStream, i, 0, ActivityThread.currentPackageName());
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaRouter", "Error setting local stream volume", remoteexception);
              goto _L1
            MediaRouter.sStatic.requestUpdateVolume(this, i);
              goto _L1
        }

        boolean resolveStatusCode()
        {
            int i;
            int j;
            i = mRealStatusCode;
            j = i;
            if(!isSelected()) goto _L2; else goto _L1
_L1:
            j = i;
            i;
            JVM INSTR tableswitch 1 3: default 44
        //                       1 56
        //                       2 46
        //                       3 56;
               goto _L3 _L4 _L5 _L4
_L5:
            break; /* Loop/switch isn't completed */
_L3:
            j = i;
_L2:
            if(mResolvedStatusCode == j)
                return false;
            break; /* Loop/switch isn't completed */
_L4:
            j = 2;
            if(true) goto _L2; else goto _L6
_L6:
            mResolvedStatusCode = j;
            j;
            JVM INSTR tableswitch 1 5: default 100
        //                       1 124
        //                       2 131
        //                       3 138
        //                       4 145
        //                       5 152;
               goto _L7 _L8 _L9 _L10 _L11 _L12
_L12:
            break MISSING_BLOCK_LABEL_152;
_L7:
            j = 0;
_L13:
            CharSequence charsequence;
            if(j != 0)
                charsequence = MediaRouter.sStatic.mResources.getText(j);
            else
                charsequence = null;
            mStatus = charsequence;
            return true;
_L8:
            j = 0x1040350;
              goto _L13
_L9:
            j = 0x104034d;
              goto _L13
_L10:
            j = 0x104034c;
              goto _L13
_L11:
            j = 0x104034f;
              goto _L13
            j = 0x104034e;
              goto _L13
        }

        void routeUpdated()
        {
            MediaRouter.updateRoute(this);
        }

        public void select()
        {
            MediaRouter.selectRouteStatic(mSupportedTypes, this, true);
        }

        boolean setRealStatusCode(int i)
        {
            if(mRealStatusCode != i)
            {
                mRealStatusCode = i;
                return resolveStatusCode();
            } else
            {
                return false;
            }
        }

        void setStatusInt(CharSequence charsequence)
        {
            if(!charsequence.equals(mStatus))
            {
                mStatus = charsequence;
                if(mGroup != null)
                    mGroup.memberStatusChanged(this, charsequence);
                routeUpdated();
            }
        }

        public void setTag(Object obj)
        {
            mTag = obj;
            routeUpdated();
        }

        public String toString()
        {
            String s = MediaRouter.typesToString(getSupportedTypes());
            return (new StringBuilder()).append(getClass().getSimpleName()).append("{ name=").append(getName()).append(", description=").append(getDescription()).append(", status=").append(getStatus()).append(", category=").append(getCategory()).append(", supportedTypes=").append(s).append(", presentationDisplay=").append(mPresentationDisplay).append(" }").toString();
        }

        boolean updatePresentationDisplay()
        {
            Display display = choosePresentationDisplay();
            if(mPresentationDisplay != display)
            {
                mPresentationDisplay = display;
                return true;
            } else
            {
                return false;
            }
        }

        public static final int DEVICE_TYPE_BLUETOOTH = 3;
        public static final int DEVICE_TYPE_SPEAKER = 2;
        public static final int DEVICE_TYPE_TV = 1;
        public static final int DEVICE_TYPE_UNKNOWN = 0;
        public static final int PLAYBACK_TYPE_LOCAL = 0;
        public static final int PLAYBACK_TYPE_REMOTE = 1;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;
        public static final int STATUS_AVAILABLE = 3;
        public static final int STATUS_CONNECTED = 6;
        public static final int STATUS_CONNECTING = 2;
        public static final int STATUS_IN_USE = 5;
        public static final int STATUS_NONE = 0;
        public static final int STATUS_NOT_AVAILABLE = 4;
        public static final int STATUS_SCANNING = 1;
        final RouteCategory mCategory;
        CharSequence mDescription;
        String mDeviceAddress;
        int mDeviceType;
        boolean mEnabled;
        String mGlobalRouteId;
        RouteGroup mGroup;
        Drawable mIcon;
        CharSequence mName;
        int mNameResId;
        int mPlaybackStream;
        int mPlaybackType;
        Display mPresentationDisplay;
        int mPresentationDisplayId;
        private int mRealStatusCode;
        final IRemoteVolumeObserver.Stub mRemoteVolObserver = new _cls1();
        private int mResolvedStatusCode;
        private CharSequence mStatus;
        int mSupportedTypes;
        private Object mTag;
        VolumeCallbackInfo mVcb;
        int mVolume;
        int mVolumeHandling;
        int mVolumeMax;

        RouteInfo(RouteCategory routecategory)
        {
            mPlaybackType = 0;
            mVolumeMax = 15;
            mVolume = 15;
            mVolumeHandling = 1;
            mPlaybackStream = 3;
            mPresentationDisplayId = -1;
            mEnabled = true;
            mCategory = routecategory;
            mDeviceType = 0;
        }
    }

    public static class SimpleCallback extends Callback
    {

        public void onRouteAdded(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRouteChanged(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRouteGrouped(MediaRouter mediarouter, RouteInfo routeinfo, RouteGroup routegroup, int i)
        {
        }

        public void onRouteRemoved(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRouteSelected(MediaRouter mediarouter, int i, RouteInfo routeinfo)
        {
        }

        public void onRouteUngrouped(MediaRouter mediarouter, RouteInfo routeinfo, RouteGroup routegroup)
        {
        }

        public void onRouteUnselected(MediaRouter mediarouter, int i, RouteInfo routeinfo)
        {
        }

        public void onRouteVolumeChanged(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public SimpleCallback()
        {
        }
    }

    static class Static
        implements android.hardware.display.DisplayManager.DisplayListener
    {

        private void updatePresentationDisplays(int i)
        {
            int j = mRoutes.size();
            for(int k = 0; k < j; k++)
            {
                RouteInfo routeinfo = (RouteInfo)mRoutes.get(k);
                if(routeinfo.updatePresentationDisplay() || routeinfo.mPresentationDisplay != null && routeinfo.mPresentationDisplay.getDisplayId() == i)
                    MediaRouter.dispatchRoutePresentationDisplayChanged(routeinfo);
            }

        }

        RouteInfo findGlobalRoute(String s)
        {
            int i = mRoutes.size();
            for(int j = 0; j < i; j++)
            {
                RouteInfo routeinfo = (RouteInfo)mRoutes.get(j);
                if(s.equals(routeinfo.mGlobalRouteId))
                    return routeinfo;
            }

            return null;
        }

        public Display[] getAllPresentationDisplays()
        {
            return mDisplayService.getDisplays("android.hardware.display.category.PRESENTATION");
        }

        boolean isBluetoothA2dpOn()
        {
            boolean flag = false;
            try
            {
                if(mBluetoothA2dpRoute != null)
                    flag = mAudioService.isBluetoothA2dpOn();
            }
            catch(RemoteException remoteexception)
            {
                Log.e("MediaRouter", "Error querying Bluetooth A2DP state", remoteexception);
                return false;
            }
            return flag;
        }

        boolean isPlaybackActive()
        {
            if(mClient == null)
                break MISSING_BLOCK_LABEL_34;
            boolean flag = mMediaRouterService.isPlaybackActive(mClient);
            return flag;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaRouter", "Unable to retrieve playback active state.", remoteexception);
            return false;
        }

        RouteInfo makeGlobalRoute(MediaRouterClientState.RouteInfo routeinfo)
        {
            RouteInfo routeinfo1 = new RouteInfo(mSystemCategory);
            routeinfo1.mGlobalRouteId = routeinfo.id;
            routeinfo1.mName = routeinfo.name;
            routeinfo1.mDescription = routeinfo.description;
            routeinfo1.mSupportedTypes = routeinfo.supportedTypes;
            routeinfo1.mDeviceType = routeinfo.deviceType;
            routeinfo1.mEnabled = routeinfo.enabled;
            routeinfo1.setRealStatusCode(routeinfo.statusCode);
            routeinfo1.mPlaybackType = routeinfo.playbackType;
            routeinfo1.mPlaybackStream = routeinfo.playbackStream;
            routeinfo1.mVolume = routeinfo.volume;
            routeinfo1.mVolumeMax = routeinfo.volumeMax;
            routeinfo1.mVolumeHandling = routeinfo.volumeHandling;
            routeinfo1.mPresentationDisplayId = routeinfo.presentationDisplayId;
            routeinfo1.updatePresentationDisplay();
            return routeinfo1;
        }

        public void onDisplayAdded(int i)
        {
            updatePresentationDisplays(i);
        }

        public void onDisplayChanged(int i)
        {
            updatePresentationDisplays(i);
        }

        public void onDisplayRemoved(int i)
        {
            updatePresentationDisplays(i);
        }

        void publishClientDiscoveryRequest()
        {
            if(mClient == null)
                break MISSING_BLOCK_LABEL_28;
            mMediaRouterService.setDiscoveryRequest(mClient, mDiscoveryRequestRouteTypes, mDiscoverRequestActiveScan);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaRouter", "Unable to publish media router client discovery request.", remoteexception);
              goto _L1
        }

        void publishClientSelectedRoute(boolean flag)
        {
            String s;
            s = null;
            if(mClient == null)
                break MISSING_BLOCK_LABEL_45;
            IMediaRouterService imediarouterservice = mMediaRouterService;
            IMediaRouterClient imediarouterclient = mClient;
            if(mSelectedRoute != null)
                s = mSelectedRoute.mGlobalRouteId;
            imediarouterservice.setSelectedRoute(imediarouterclient, s, flag);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaRouter", "Unable to publish media router client selected route.", remoteexception);
              goto _L1
        }

        void rebindAsUser(int i)
        {
            if(mCurrentUserId != i || i < 0 || mClient == null)
            {
                if(mClient != null)
                {
                    Client client;
                    try
                    {
                        mMediaRouterService.unregisterClient(mClient);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.e("MediaRouter", "Unable to unregister media router client.", remoteexception);
                    }
                    mClient = null;
                }
                mCurrentUserId = i;
                try
                {
                    client = JVM INSTR new #15  <Class MediaRouter$Static$Client>;
                    client.this. Client();
                    mMediaRouterService.registerClientAsUser(client, mPackageName, i);
                    mClient = client;
                }
                catch(RemoteException remoteexception1)
                {
                    Log.e("MediaRouter", "Unable to register media router client.", remoteexception1);
                }
                publishClientDiscoveryRequest();
                publishClientSelectedRoute(false);
                updateClientState();
            }
        }

        void requestSetVolume(RouteInfo routeinfo, int i)
        {
            if(routeinfo.mGlobalRouteId == null || mClient == null)
                break MISSING_BLOCK_LABEL_32;
            mMediaRouterService.requestSetVolume(mClient, routeinfo.mGlobalRouteId, i);
_L1:
            return;
            routeinfo;
            Log.w("MediaRouter", "Unable to request volume change.", routeinfo);
              goto _L1
        }

        void requestUpdateVolume(RouteInfo routeinfo, int i)
        {
            if(routeinfo.mGlobalRouteId == null || mClient == null)
                break MISSING_BLOCK_LABEL_32;
            mMediaRouterService.requestUpdateVolume(mClient, routeinfo.mGlobalRouteId, i);
_L1:
            return;
            routeinfo;
            Log.w("MediaRouter", "Unable to request volume change.", routeinfo);
              goto _L1
        }

        void setSelectedRoute(RouteInfo routeinfo, boolean flag)
        {
            mSelectedRoute = routeinfo;
            publishClientSelectedRoute(flag);
        }

        void startMonitoringRoutes(Context context)
        {
            mDefaultAudioVideo = new RouteInfo(mSystemCategory);
            mDefaultAudioVideo.mNameResId = 0x104019b;
            mDefaultAudioVideo.mSupportedTypes = 3;
            mDefaultAudioVideo.updatePresentationDisplay();
            if(((AudioManager)context.getSystemService("audio")).isVolumeFixed())
                mDefaultAudioVideo.mVolumeHandling = 0;
            MediaRouter.addRouteStatic(mDefaultAudioVideo);
            MediaRouter.updateWifiDisplayStatus(mDisplayService.getWifiDisplayStatus());
            context.registerReceiver(new WifiDisplayStatusChangedReceiver(), new IntentFilter("android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED"));
            context.registerReceiver(new VolumeChangeReceiver(), new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            mDisplayService.registerDisplayListener(this, mHandler);
            context = null;
            AudioRoutesInfo audioroutesinfo = mAudioService.startWatchingRoutes(mAudioRoutesObserver);
            context = audioroutesinfo;
_L2:
            if(context != null)
                updateAudioRoutes(context);
            rebindAsUser(UserHandle.myUserId());
            if(mSelectedRoute == null)
                MediaRouter.selectDefaultRouteStatic();
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        void updateAudioRoutes(AudioRoutesInfo audioroutesinfo)
        {
            boolean flag = false;
            int i = 0;
            boolean flag1 = false;
            if(audioroutesinfo.mainType != mCurAudioRoutesInfo.mainType)
            {
                mCurAudioRoutesInfo.mainType = audioroutesinfo.mainType;
                RouteInfo routeinfo;
                if((audioroutesinfo.mainType & 2) != 0 || (audioroutesinfo.mainType & 1) != 0)
                    i = 0x104019d;
                else
                if((audioroutesinfo.mainType & 4) != 0)
                    i = 0x104019c;
                else
                if((audioroutesinfo.mainType & 8) != 0)
                    i = 0x104019f;
                else
                    i = 0x104019b;
                mDefaultAudioVideo.mNameResId = i;
                MediaRouter.dispatchRouteChanged(mDefaultAudioVideo);
                i = ((flag1) ? 1 : 0);
                if((audioroutesinfo.mainType & 0x13) != 0)
                    i = 1;
                flag = true;
            }
            if(!TextUtils.equals(audioroutesinfo.bluetoothName, mCurAudioRoutesInfo.bluetoothName))
            {
                i = 0;
                mCurAudioRoutesInfo.bluetoothName = audioroutesinfo.bluetoothName;
                if(mCurAudioRoutesInfo.bluetoothName != null)
                {
                    if(mBluetoothA2dpRoute == null)
                    {
                        routeinfo = new RouteInfo(mSystemCategory);
                        routeinfo.mName = mCurAudioRoutesInfo.bluetoothName;
                        routeinfo.mDescription = mResources.getText(0x10400e4);
                        routeinfo.mSupportedTypes = 1;
                        routeinfo.mDeviceType = 3;
                        mBluetoothA2dpRoute = routeinfo;
                        MediaRouter.addRouteStatic(mBluetoothA2dpRoute);
                    } else
                    {
                        mBluetoothA2dpRoute.mName = mCurAudioRoutesInfo.bluetoothName;
                        MediaRouter.dispatchRouteChanged(mBluetoothA2dpRoute);
                    }
                } else
                if(mBluetoothA2dpRoute != null)
                {
                    MediaRouter.removeRouteStatic(mBluetoothA2dpRoute);
                    mBluetoothA2dpRoute = null;
                }
                flag = true;
            }
            if(flag)
            {
                Log.v("MediaRouter", (new StringBuilder()).append("Audio routes updated: ").append(audioroutesinfo).append(", a2dp=").append(isBluetoothA2dpOn()).toString());
                break MISSING_BLOCK_LABEL_243;
            }
            return;
            if(mSelectedRoute == null || mSelectedRoute == mDefaultAudioVideo || mSelectedRoute == mBluetoothA2dpRoute)
                if(i != 0 || mBluetoothA2dpRoute == null)
                    MediaRouter.selectRouteStatic(1, mDefaultAudioVideo, false);
                else
                    MediaRouter.selectRouteStatic(1, mBluetoothA2dpRoute, false);
            break MISSING_BLOCK_LABEL_281;
        }

        void updateClientState()
        {
            Object obj;
            int i;
            int j;
            mClientState = null;
            if(mClient != null)
                try
                {
                    mClientState = mMediaRouterService.getState(mClient);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    Log.e("MediaRouter", "Unable to retrieve media router client state.", ((Throwable) (obj)));
                }
            if(mClientState != null)
                obj = mClientState.routes;
            else
                obj = null;
            if(obj != null)
                i = ((ArrayList) (obj)).size();
            else
                i = 0;
            j = 0;
            while(j < i) 
            {
                MediaRouterClientState.RouteInfo routeinfo = (MediaRouterClientState.RouteInfo)((ArrayList) (obj)).get(j);
                RouteInfo routeinfo1 = findGlobalRoute(routeinfo.id);
                if(routeinfo1 == null)
                    MediaRouter.addRouteStatic(makeGlobalRoute(routeinfo));
                else
                    updateGlobalRoute(routeinfo1, routeinfo);
                j++;
            }
            j = mRoutes.size();
_L6:
            String s;
            RouteInfo routeinfo2;
            int k;
            k = j - 1;
            if(j <= 0)
                break MISSING_BLOCK_LABEL_225;
            routeinfo2 = (RouteInfo)mRoutes.get(k);
            s = routeinfo2.mGlobalRouteId;
            if(s == null) goto _L2; else goto _L1
_L1:
            j = 0;
_L4:
            if(j >= i)
                break MISSING_BLOCK_LABEL_217;
            if(!s.equals(((MediaRouterClientState.RouteInfo)((ArrayList) (obj)).get(j)).id)) goto _L3; else goto _L2
_L2:
            j = k;
            continue; /* Loop/switch isn't completed */
_L3:
            j++;
              goto _L4
            MediaRouter.removeRouteStatic(routeinfo2);
              goto _L2
            return;
            if(true) goto _L6; else goto _L5
_L5:
        }

        void updateDiscoveryRequest()
        {
            boolean flag1;
            boolean flag2;
            int l;
label0:
            {
                int i = 0;
                int j = 0;
                flag1 = false;
                flag2 = false;
                int k = mCallbacks.size();
                l = 0;
                while(l < k) 
                {
                    CallbackInfo callbackinfo = (CallbackInfo)mCallbacks.get(l);
                    boolean flag3;
                    if((callbackinfo.flags & 5) != 0)
                        i |= callbackinfo.type;
                    else
                    if((callbackinfo.flags & 8) != 0)
                        j |= callbackinfo.type;
                    else
                        i |= callbackinfo.type;
                    flag3 = flag2;
                    if((callbackinfo.flags & 1) != 0)
                    {
                        boolean flag4 = true;
                        flag1 = flag4;
                        flag3 = flag2;
                        if((callbackinfo.type & 4) != 0)
                        {
                            flag3 = true;
                            flag1 = flag4;
                        }
                    }
                    l++;
                    flag2 = flag3;
                }
                if(i == 0)
                {
                    l = i;
                    if(!flag1)
                        break label0;
                }
                l = i | j;
            }
            if(!mCanConfigureWifiDisplays) goto _L2; else goto _L1
_L1:
            boolean flag;
            flag = flag2;
            if(mSelectedRoute != null)
            {
                flag = flag2;
                if(mSelectedRoute.matchesTypes(4))
                    flag = false;
            }
            if(!flag) goto _L4; else goto _L3
_L3:
            if(!mActivelyScanningWifiDisplays)
            {
                mActivelyScanningWifiDisplays = true;
                mDisplayService.startWifiDisplayScan();
            }
_L2:
            if(l != mDiscoveryRequestRouteTypes || flag1 != mDiscoverRequestActiveScan)
            {
                mDiscoveryRequestRouteTypes = l;
                mDiscoverRequestActiveScan = flag1;
                publishClientDiscoveryRequest();
            }
            return;
_L4:
            if(mActivelyScanningWifiDisplays)
            {
                mActivelyScanningWifiDisplays = false;
                mDisplayService.stopWifiDisplayScan();
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        void updateGlobalRoute(RouteInfo routeinfo, MediaRouterClientState.RouteInfo routeinfo1)
        {
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            if(!Objects.equals(routeinfo.mName, routeinfo1.name))
            {
                routeinfo.mName = routeinfo1.name;
                flag = true;
            }
            if(!Objects.equals(routeinfo.mDescription, routeinfo1.description))
            {
                routeinfo.mDescription = routeinfo1.description;
                flag = true;
            }
            int i = routeinfo.mSupportedTypes;
            if(i != routeinfo1.supportedTypes)
            {
                routeinfo.mSupportedTypes = routeinfo1.supportedTypes;
                flag = true;
            }
            if(routeinfo.mEnabled != routeinfo1.enabled)
            {
                routeinfo.mEnabled = routeinfo1.enabled;
                flag = true;
            }
            if(RouteInfo._2D_get0(routeinfo) != routeinfo1.statusCode)
            {
                routeinfo.setRealStatusCode(routeinfo1.statusCode);
                flag = true;
            }
            if(routeinfo.mPlaybackType != routeinfo1.playbackType)
            {
                routeinfo.mPlaybackType = routeinfo1.playbackType;
                flag = true;
            }
            if(routeinfo.mPlaybackStream != routeinfo1.playbackStream)
            {
                routeinfo.mPlaybackStream = routeinfo1.playbackStream;
                flag = true;
            }
            boolean flag3 = flag;
            flag = flag1;
            if(routeinfo.mVolume != routeinfo1.volume)
            {
                routeinfo.mVolume = routeinfo1.volume;
                flag3 = true;
                flag = true;
            }
            if(routeinfo.mVolumeMax != routeinfo1.volumeMax)
            {
                routeinfo.mVolumeMax = routeinfo1.volumeMax;
                flag3 = true;
                flag = true;
            }
            if(routeinfo.mVolumeHandling != routeinfo1.volumeHandling)
            {
                routeinfo.mVolumeHandling = routeinfo1.volumeHandling;
                flag3 = true;
                flag = true;
            }
            if(routeinfo.mPresentationDisplayId != routeinfo1.presentationDisplayId)
            {
                routeinfo.mPresentationDisplayId = routeinfo1.presentationDisplayId;
                routeinfo.updatePresentationDisplay();
                flag3 = true;
                flag2 = true;
            }
            if(flag3)
                MediaRouter.dispatchRouteChanged(routeinfo, i);
            if(flag)
                MediaRouter.dispatchRouteVolumeChanged(routeinfo);
            if(flag2)
                MediaRouter.dispatchRoutePresentationDisplayChanged(routeinfo);
        }

        boolean mActivelyScanningWifiDisplays;
        final IAudioRoutesObserver.Stub mAudioRoutesObserver = new _cls1();
        final IAudioService mAudioService = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
        RouteInfo mBluetoothA2dpRoute;
        final CopyOnWriteArrayList mCallbacks = new CopyOnWriteArrayList();
        final boolean mCanConfigureWifiDisplays;
        final ArrayList mCategories = new ArrayList();
        IMediaRouterClient mClient;
        MediaRouterClientState mClientState;
        final AudioRoutesInfo mCurAudioRoutesInfo = new AudioRoutesInfo();
        int mCurrentUserId;
        RouteInfo mDefaultAudioVideo;
        boolean mDiscoverRequestActiveScan;
        int mDiscoveryRequestRouteTypes;
        final DisplayManager mDisplayService;
        final Handler mHandler;
        final IMediaRouterService mMediaRouterService = IMediaRouterService.Stub.asInterface(ServiceManager.getService("media_router"));
        final String mPackageName;
        String mPreviousActiveWifiDisplayAddress;
        final Resources mResources;
        final ArrayList mRoutes = new ArrayList();
        RouteInfo mSelectedRoute;
        final RouteCategory mSystemCategory = new RouteCategory(0x104019a, 3, false);

        Static(Context context)
        {
            mCurrentUserId = -1;
            mPackageName = context.getPackageName();
            mResources = context.getResources();
            mHandler = new Handler(context.getMainLooper());
            mDisplayService = (DisplayManager)context.getSystemService("display");
            mSystemCategory.mIsSystem = true;
            boolean flag;
            if(context.checkPermission("android.permission.CONFIGURE_WIFI_DISPLAY", Process.myPid(), Process.myUid()) == 0)
                flag = true;
            else
                flag = false;
            mCanConfigureWifiDisplays = flag;
        }
    }

    final class Static.Client extends IMediaRouterClient.Stub
    {

        public void onRestoreRoute()
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    while(Static.Client.this != mClient || mSelectedRoute == null || mSelectedRoute != mDefaultAudioVideo && mSelectedRoute != mBluetoothA2dpRoute) 
                        return;
                    Log.v("MediaRouter", (new StringBuilder()).append("onRestoreRoute() : route=").append(mSelectedRoute).toString());
                    mSelectedRoute.select();
                }

                final Static.Client this$2;

            
            {
                this$2 = Static.Client.this;
                super();
            }
            }
);
        }

        public void onStateChanged()
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    if(Static.Client.this == mClient)
                        updateClientState();
                }

                final Static.Client this$2;

            
            {
                this$2 = Static.Client.this;
                super();
            }
            }
);
        }

        final Static this$1;

        Static.Client()
        {
            this$1 = Static.this;
            super();
        }
    }

    public static class UserRouteInfo extends RouteInfo
    {

        private void configureSessionVolume()
        {
            MediaSession mediasession;
            if(mRcc == null)
            {
                if(MediaRouter._2D_get0())
                    Log.d("MediaRouter", (new StringBuilder()).append("No Rcc to configure volume for route ").append(getName()).toString());
                return;
            }
            mediasession = mRcc.getMediaSession();
            if(mediasession == null)
            {
                if(MediaRouter._2D_get0())
                    Log.d("MediaRouter", "Rcc has no session to configure volume");
                return;
            }
            if(mPlaybackType != 1) goto _L2; else goto _L1
_L1:
            int i = 0;
            mVolumeHandling;
            JVM INSTR tableswitch 1 1: default 100
        //                       1 148;
               goto _L3 _L4
_L5:
            return;
_L4:
            i = 2;
_L3:
            if(mSvp == null || mSvp.getVolumeControl() != i || mSvp.getMaxVolume() != mVolumeMax)
            {
                mSvp = new SessionVolumeProvider(i, mVolumeMax, mVolume);
                mediasession.setPlaybackToRemote(mSvp);
            }
              goto _L5
_L2:
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setLegacyStreamType(mPlaybackStream);
            mediasession.setPlaybackToLocal(builder.build());
            mSvp = null;
              goto _L5
        }

        private void updatePlaybackInfoOnRcc()
        {
            configureSessionVolume();
        }

        public RemoteControlClient getRemoteControlClient()
        {
            return mRcc;
        }

        public void requestSetVolume(int i)
        {
            if(mVolumeHandling == 1)
            {
                if(mVcb == null)
                {
                    Log.e("MediaRouter", "Cannot requestSetVolume on user route - no volume callback set");
                    return;
                }
                mVcb.vcb.onVolumeSetRequest(this, i);
            }
        }

        public void requestUpdateVolume(int i)
        {
            if(mVolumeHandling == 1)
            {
                if(mVcb == null)
                {
                    Log.e("MediaRouter", "Cannot requestChangeVolume on user route - no volumec callback set");
                    return;
                }
                mVcb.vcb.onVolumeUpdateRequest(this, i);
            }
        }

        public void setDescription(CharSequence charsequence)
        {
            mDescription = charsequence;
            routeUpdated();
        }

        public void setIconDrawable(Drawable drawable)
        {
            mIcon = drawable;
        }

        public void setIconResource(int i)
        {
            setIconDrawable(MediaRouter.sStatic.mResources.getDrawable(i));
        }

        public void setName(int i)
        {
            mNameResId = i;
            mName = null;
            routeUpdated();
        }

        public void setName(CharSequence charsequence)
        {
            mNameResId = 0;
            mName = charsequence;
            routeUpdated();
        }

        public void setPlaybackStream(int i)
        {
            if(mPlaybackStream != i)
            {
                mPlaybackStream = i;
                configureSessionVolume();
            }
        }

        public void setPlaybackType(int i)
        {
            if(mPlaybackType != i)
            {
                mPlaybackType = i;
                configureSessionVolume();
            }
        }

        public void setRemoteControlClient(RemoteControlClient remotecontrolclient)
        {
            mRcc = remotecontrolclient;
            updatePlaybackInfoOnRcc();
        }

        public void setStatus(CharSequence charsequence)
        {
            setStatusInt(charsequence);
        }

        public void setVolume(int i)
        {
            i = Math.max(0, Math.min(i, getVolumeMax()));
            if(mVolume != i)
            {
                mVolume = i;
                if(mSvp != null)
                    mSvp.setCurrentVolume(mVolume);
                MediaRouter.dispatchRouteVolumeChanged(this);
                if(mGroup != null)
                    mGroup.memberVolumeChanged(this);
            }
        }

        public void setVolumeCallback(VolumeCallback volumecallback)
        {
            mVcb = new VolumeCallbackInfo(volumecallback, this);
        }

        public void setVolumeHandling(int i)
        {
            if(mVolumeHandling != i)
            {
                mVolumeHandling = i;
                configureSessionVolume();
            }
        }

        public void setVolumeMax(int i)
        {
            if(mVolumeMax != i)
            {
                mVolumeMax = i;
                configureSessionVolume();
            }
        }

        RemoteControlClient mRcc;
        SessionVolumeProvider mSvp;

        UserRouteInfo(RouteCategory routecategory)
        {
            super(routecategory);
            mSupportedTypes = 0x800000;
            mPlaybackType = 1;
            mVolumeHandling = 0;
        }
    }

    class UserRouteInfo.SessionVolumeProvider extends VolumeProvider
    {

        public void onAdjustVolume(int i)
        {
            MediaRouter.sStatic.mHandler.post(i. new Runnable() {

                public void run()
                {
                    if(mVcb != null)
                        mVcb.vcb.onVolumeUpdateRequest(mVcb.route, direction);
                }

                final UserRouteInfo.SessionVolumeProvider this$2;
                final int val$direction;

            
            {
                this$2 = final_sessionvolumeprovider;
                direction = I.this;
                super();
            }
            }
);
        }

        public void onSetVolumeTo(int i)
        {
            MediaRouter.sStatic.mHandler.post(i. new Runnable() {

                public void run()
                {
                    if(mVcb != null)
                        mVcb.vcb.onVolumeSetRequest(mVcb.route, volume);
                }

                final UserRouteInfo.SessionVolumeProvider this$2;
                final int val$volume;

            
            {
                this$2 = final_sessionvolumeprovider;
                volume = I.this;
                super();
            }
            }
);
        }

        final UserRouteInfo this$1;

        public UserRouteInfo.SessionVolumeProvider(int i, int j, int k)
        {
            this$1 = UserRouteInfo.this;
            super(i, j, k);
        }
    }

    public static abstract class VolumeCallback
    {

        public abstract void onVolumeSetRequest(RouteInfo routeinfo, int i);

        public abstract void onVolumeUpdateRequest(RouteInfo routeinfo, int i);

        public VolumeCallback()
        {
        }
    }

    static class VolumeCallbackInfo
    {

        public final RouteInfo route;
        public final VolumeCallback vcb;

        public VolumeCallbackInfo(VolumeCallback volumecallback, RouteInfo routeinfo)
        {
            vcb = volumecallback;
            route = routeinfo;
        }
    }

    static class VolumeChangeReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION"))
            {
                if(intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) != 3)
                    return;
                int i = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
                if(i != intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0))
                    MediaRouter.systemVolumeChanged(i);
            }
        }

        VolumeChangeReceiver()
        {
        }
    }

    static class WifiDisplayStatusChangedReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals("android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED"))
                MediaRouter.updateWifiDisplayStatus((WifiDisplayStatus)intent.getParcelableExtra("android.hardware.display.extra.WIFI_DISPLAY_STATUS"));
        }

        WifiDisplayStatusChangedReceiver()
        {
        }
    }


    static boolean _2D_get0()
    {
        return DEBUG;
    }

    public MediaRouter(Context context)
    {
        android/media/MediaRouter$Static;
        JVM INSTR monitorenter ;
        if(sStatic == null)
        {
            Context context1 = context.getApplicationContext();
            context = JVM INSTR new #28  <Class MediaRouter$Static>;
            context.Static(context1);
            sStatic = context;
            sStatic.startMonitoringRoutes(context1);
        }
        android/media/MediaRouter$Static;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
    }

    static void addRouteStatic(RouteInfo routeinfo)
    {
        Log.v("MediaRouter", (new StringBuilder()).append("Adding route: ").append(routeinfo).toString());
        RouteCategory routecategory = routeinfo.getCategory();
        if(!sStatic.mCategories.contains(routecategory))
            sStatic.mCategories.add(routecategory);
        if(routecategory.isGroupable() && (routeinfo instanceof RouteGroup) ^ true)
        {
            RouteGroup routegroup = new RouteGroup(routeinfo.getCategory());
            routegroup.mSupportedTypes = routeinfo.mSupportedTypes;
            sStatic.mRoutes.add(routegroup);
            dispatchRouteAdded(routegroup);
            routegroup.addRoute(routeinfo);
        } else
        {
            sStatic.mRoutes.add(routeinfo);
            dispatchRouteAdded(routeinfo);
        }
    }

    static void dispatchRouteAdded(RouteInfo routeinfo)
    {
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            if(callbackinfo.filterRouteEvent(routeinfo))
                callbackinfo.cb.onRouteAdded(callbackinfo.router, routeinfo);
        } while(true);
    }

    static void dispatchRouteChanged(RouteInfo routeinfo)
    {
        dispatchRouteChanged(routeinfo, routeinfo.mSupportedTypes);
    }

    static void dispatchRouteChanged(RouteInfo routeinfo, int i)
    {
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("Dispatching route change: ").append(routeinfo).toString());
        int j = routeinfo.mSupportedTypes;
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            boolean flag = callbackinfo.filterRouteEvent(i);
            boolean flag1 = callbackinfo.filterRouteEvent(j);
            if(!flag && flag1)
            {
                callbackinfo.cb.onRouteAdded(callbackinfo.router, routeinfo);
                if(routeinfo.isSelected())
                    callbackinfo.cb.onRouteSelected(callbackinfo.router, j, routeinfo);
            }
            if(flag || flag1)
                callbackinfo.cb.onRouteChanged(callbackinfo.router, routeinfo);
            if(flag && flag1 ^ true)
            {
                if(routeinfo.isSelected())
                    callbackinfo.cb.onRouteUnselected(callbackinfo.router, i, routeinfo);
                callbackinfo.cb.onRouteRemoved(callbackinfo.router, routeinfo);
            }
        } while(true);
    }

    static void dispatchRouteGrouped(RouteInfo routeinfo, RouteGroup routegroup, int i)
    {
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            if(callbackinfo.filterRouteEvent(routegroup))
                callbackinfo.cb.onRouteGrouped(callbackinfo.router, routeinfo, routegroup, i);
        } while(true);
    }

    static void dispatchRoutePresentationDisplayChanged(RouteInfo routeinfo)
    {
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            if(callbackinfo.filterRouteEvent(routeinfo))
                callbackinfo.cb.onRoutePresentationDisplayChanged(callbackinfo.router, routeinfo);
        } while(true);
    }

    static void dispatchRouteRemoved(RouteInfo routeinfo)
    {
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            if(callbackinfo.filterRouteEvent(routeinfo))
                callbackinfo.cb.onRouteRemoved(callbackinfo.router, routeinfo);
        } while(true);
    }

    static void dispatchRouteSelected(int i, RouteInfo routeinfo)
    {
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            if(callbackinfo.filterRouteEvent(routeinfo))
                callbackinfo.cb.onRouteSelected(callbackinfo.router, i, routeinfo);
        } while(true);
    }

    static void dispatchRouteUngrouped(RouteInfo routeinfo, RouteGroup routegroup)
    {
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            if(callbackinfo.filterRouteEvent(routegroup))
                callbackinfo.cb.onRouteUngrouped(callbackinfo.router, routeinfo, routegroup);
        } while(true);
    }

    static void dispatchRouteUnselected(int i, RouteInfo routeinfo)
    {
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            if(callbackinfo.filterRouteEvent(routeinfo))
                callbackinfo.cb.onRouteUnselected(callbackinfo.router, i, routeinfo);
        } while(true);
    }

    static void dispatchRouteVolumeChanged(RouteInfo routeinfo)
    {
        Iterator iterator = sStatic.mCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CallbackInfo callbackinfo = (CallbackInfo)iterator.next();
            if(callbackinfo.filterRouteEvent(routeinfo))
                callbackinfo.cb.onRouteVolumeChanged(callbackinfo.router, routeinfo);
        } while(true);
    }

    private int findCallbackInfo(Callback callback)
    {
        int i = sStatic.mCallbacks.size();
        for(int j = 0; j < i; j++)
            if(((CallbackInfo)sStatic.mCallbacks.get(j)).cb == callback)
                return j;

        return -1;
    }

    private static WifiDisplay findWifiDisplay(WifiDisplay awifidisplay[], String s)
    {
        for(int i = 0; i < awifidisplay.length; i++)
        {
            WifiDisplay wifidisplay = awifidisplay[i];
            if(wifidisplay.getDeviceAddress().equals(s))
                return wifidisplay;
        }

        return null;
    }

    private static RouteInfo findWifiDisplayRoute(WifiDisplay wifidisplay)
    {
        int i = sStatic.mRoutes.size();
        for(int j = 0; j < i; j++)
        {
            RouteInfo routeinfo = (RouteInfo)sStatic.mRoutes.get(j);
            if(wifidisplay.getDeviceAddress().equals(routeinfo.mDeviceAddress))
                return routeinfo;
        }

        return null;
    }

    static RouteInfo getRouteAtStatic(int i)
    {
        return (RouteInfo)sStatic.mRoutes.get(i);
    }

    static int getRouteCountStatic()
    {
        return sStatic.mRoutes.size();
    }

    static int getWifiDisplayStatusCode(WifiDisplay wifidisplay, WifiDisplayStatus wifidisplaystatus)
    {
        byte byte0;
        int i;
        if(wifidisplaystatus.getScanState() == 1)
            byte0 = 1;
        else
        if(wifidisplay.isAvailable())
        {
            if(wifidisplay.canConnect())
                byte0 = 3;
            else
                byte0 = 5;
        } else
        {
            byte0 = 4;
        }
        i = byte0;
        if(!wifidisplay.equals(wifidisplaystatus.getActiveDisplay())) goto _L2; else goto _L1
_L1:
        wifidisplaystatus.getActiveDisplayState();
        JVM INSTR tableswitch 0 2: default 52
    //                   0 96
    //                   1 91
    //                   2 85;
           goto _L3 _L4 _L5 _L6
_L3:
        i = byte0;
_L2:
        return i;
_L6:
        i = 6;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        Log.e("MediaRouter", "Active display is not connected!");
        i = byte0;
        if(true) goto _L2; else goto _L7
_L7:
    }

    static boolean isWifiDisplayEnabled(WifiDisplay wifidisplay, WifiDisplayStatus wifidisplaystatus)
    {
        boolean flag;
        if(wifidisplay.isAvailable())
        {
            if(!wifidisplay.canConnect())
                flag = wifidisplay.equals(wifidisplaystatus.getActiveDisplay());
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    static RouteInfo makeWifiDisplayRoute(WifiDisplay wifidisplay, WifiDisplayStatus wifidisplaystatus)
    {
        RouteInfo routeinfo = new RouteInfo(sStatic.mSystemCategory);
        routeinfo.mDeviceAddress = wifidisplay.getDeviceAddress();
        routeinfo.mSupportedTypes = 7;
        routeinfo.mVolumeHandling = 0;
        routeinfo.mPlaybackType = 1;
        routeinfo.setRealStatusCode(getWifiDisplayStatusCode(wifidisplay, wifidisplaystatus));
        routeinfo.mEnabled = isWifiDisplayEnabled(wifidisplay, wifidisplaystatus);
        routeinfo.mName = wifidisplay.getFriendlyDisplayName();
        routeinfo.mDescription = sStatic.mResources.getText(0x10406d6);
        routeinfo.updatePresentationDisplay();
        routeinfo.mDeviceType = 1;
        return routeinfo;
    }

    static boolean matchesDeviceAddress(WifiDisplay wifidisplay, RouteInfo routeinfo)
    {
        boolean flag;
        if(routeinfo != null && routeinfo.mDeviceAddress != null)
            flag = true;
        else
            flag = false;
        if(wifidisplay == null && flag ^ true)
            return true;
        if(wifidisplay != null && flag)
            return wifidisplay.getDeviceAddress().equals(routeinfo.mDeviceAddress);
        else
            return false;
    }

    static void removeRouteStatic(RouteInfo routeinfo)
    {
        Log.v("MediaRouter", (new StringBuilder()).append("Removing route: ").append(routeinfo).toString());
        if(!sStatic.mRoutes.remove(routeinfo)) goto _L2; else goto _L1
_L1:
        RouteCategory routecategory;
        int i;
        boolean flag;
        int j;
        routecategory = routeinfo.getCategory();
        i = sStatic.mRoutes.size();
        flag = false;
        j = 0;
_L8:
        boolean flag1 = flag;
        if(j >= i) goto _L4; else goto _L3
_L3:
        if(routecategory != ((RouteInfo)sStatic.mRoutes.get(j)).getCategory()) goto _L6; else goto _L5
_L5:
        flag1 = true;
_L4:
        if(routeinfo.isSelected())
            selectDefaultRouteStatic();
        if(!flag1)
            sStatic.mCategories.remove(routecategory);
        dispatchRouteRemoved(routeinfo);
_L2:
        return;
_L6:
        j++;
        if(true) goto _L8; else goto _L7
_L7:
    }

    static void selectDefaultRouteStatic()
    {
        if(sStatic.mSelectedRoute != sStatic.mBluetoothA2dpRoute && sStatic.isBluetoothA2dpOn())
            selectRouteStatic(0x800007, sStatic.mBluetoothA2dpRoute, false);
        else
            selectRouteStatic(0x800007, sStatic.mDefaultAudioVideo, false);
    }

    static void selectRouteStatic(int i, RouteInfo routeinfo, boolean flag)
    {
        RouteInfo routeinfo1;
        Object obj;
        Log.v("MediaRouter", (new StringBuilder()).append("Selecting route: ").append(routeinfo).toString());
        if(!_2D_assertionsDisabled && routeinfo == null)
            throw new AssertionError();
        routeinfo1 = sStatic.mSelectedRoute;
        boolean flag1;
        if(sStatic.isBluetoothA2dpOn())
            obj = sStatic.mBluetoothA2dpRoute;
        else
            obj = sStatic.mDefaultAudioVideo;
        if(routeinfo1 != sStatic.mDefaultAudioVideo)
        {
            if(routeinfo1 == sStatic.mBluetoothA2dpRoute)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = true;
        }
        if(routeinfo1 == routeinfo && (!flag1 || routeinfo == obj))
            return;
        if(!routeinfo.matchesTypes(i))
        {
            Log.w("MediaRouter", (new StringBuilder()).append("selectRoute ignored; cannot select route with supported types ").append(typesToString(routeinfo.getSupportedTypes())).append(" into route types ").append(typesToString(i)).toString());
            return;
        }
        obj = sStatic.mBluetoothA2dpRoute;
        if(!sStatic.isPlaybackActive() || obj == null || (i & 1) == 0 || routeinfo != obj && routeinfo != sStatic.mDefaultAudioVideo)
            break MISSING_BLOCK_LABEL_422;
        IAudioService iaudioservice = sStatic.mAudioService;
        int j;
        StackTraceElement astacktraceelement[];
        boolean flag3;
        StackTraceElement stacktraceelement;
        if(routeinfo == obj)
            flag3 = true;
        else
            flag3 = false;
        iaudioservice.setBluetoothA2dpOn(flag3);
        if(routeinfo == obj)
            break MISSING_BLOCK_LABEL_422;
        astacktraceelement = Thread.currentThread().getStackTrace();
        obj = JVM INSTR new #471 <Class StringBuffer>;
        ((StringBuffer) (obj)).StringBuffer();
        j = 3;
_L2:
        if(j >= astacktraceelement.length)
            break; /* Loop/switch isn't completed */
        stacktraceelement = astacktraceelement[j];
        StringBuilder stringbuilder1 = JVM INSTR new #137 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        ((StringBuffer) (obj)).append(stringbuilder1.append(stacktraceelement.getClassName()).append(".").append(stacktraceelement.getMethodName()).append(":").append(stacktraceelement.getLineNumber()).toString()).append("  ");
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        boolean flag2;
        boolean flag4;
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #137 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.w("MediaRouter", stringbuilder.append("Default route is selected while a BT route is available: pkgName=").append(sStatic.mPackageName).append(", callers=").append(((StringBuffer) (obj)).toString()).toString());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("MediaRouter", "Error changing Bluetooth A2DP state", ((Throwable) (obj)));
        }
        obj = sStatic.mDisplayService.getWifiDisplayStatus().getActiveDisplay();
        if(routeinfo1 != null && routeinfo1.mDeviceAddress != null)
            flag2 = true;
        else
            flag2 = false;
        if(routeinfo.mDeviceAddress != null)
            flag4 = true;
        else
            flag4 = false;
        if(obj == null && !flag2 && !flag4) goto _L4; else goto _L3
_L3:
        if(!flag4 || !(matchesDeviceAddress(((WifiDisplay) (obj)), routeinfo) ^ true)) goto _L6; else goto _L5
_L5:
        if(sStatic.mCanConfigureWifiDisplays)
            sStatic.mDisplayService.connectWifiDisplay(routeinfo.mDeviceAddress);
        else
            Log.e("MediaRouter", "Cannot connect to wifi displays because this process is not allowed to do so.");
_L4:
        sStatic.setSelectedRoute(routeinfo, flag);
        if(routeinfo1 != null)
        {
            dispatchRouteUnselected(routeinfo1.getSupportedTypes() & i, routeinfo1);
            if(routeinfo1.resolveStatusCode())
                dispatchRouteChanged(routeinfo1);
        }
        if(routeinfo != null)
        {
            if(routeinfo.resolveStatusCode())
                dispatchRouteChanged(routeinfo);
            dispatchRouteSelected(routeinfo.getSupportedTypes() & i, routeinfo);
        }
        sStatic.updateDiscoveryRequest();
        return;
_L6:
        if(obj != null && flag4 ^ true)
            sStatic.mDisplayService.disconnectWifiDisplay();
        if(true) goto _L4; else goto _L7
_L7:
    }

    private static boolean shouldShowWifiDisplay(WifiDisplay wifidisplay, WifiDisplay wifidisplay1)
    {
        boolean flag;
        if(!wifidisplay.isRemembered())
            flag = wifidisplay.equals(wifidisplay1);
        else
            flag = true;
        return flag;
    }

    static void systemVolumeChanged(int i)
    {
        Object obj;
        obj = sStatic.mSelectedRoute;
        if(obj == null)
            return;
        if(obj != sStatic.mBluetoothA2dpRoute && obj != sStatic.mDefaultAudioVideo) goto _L2; else goto _L1
_L1:
        dispatchRouteVolumeChanged(((RouteInfo) (obj)));
_L9:
        return;
_L2:
        if(sStatic.mBluetoothA2dpRoute == null) goto _L4; else goto _L3
_L3:
        if(!sStatic.mAudioService.isBluetoothA2dpOn()) goto _L6; else goto _L5
_L5:
        obj = sStatic.mBluetoothA2dpRoute;
_L7:
        dispatchRouteVolumeChanged(((RouteInfo) (obj)));
        continue; /* Loop/switch isn't completed */
_L6:
        try
        {
            obj = sStatic.mDefaultAudioVideo;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("MediaRouter", "Error checking Bluetooth A2DP state to report volume change", ((Throwable) (obj)));
            continue; /* Loop/switch isn't completed */
        }
        if(true) goto _L7; else goto _L4
_L4:
        dispatchRouteVolumeChanged(sStatic.mDefaultAudioVideo);
        if(true) goto _L9; else goto _L8
_L8:
    }

    static String typesToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if((i & 1) != 0)
            stringbuilder.append("ROUTE_TYPE_LIVE_AUDIO ");
        if((i & 2) != 0)
            stringbuilder.append("ROUTE_TYPE_LIVE_VIDEO ");
        if((i & 4) != 0)
            stringbuilder.append("ROUTE_TYPE_REMOTE_DISPLAY ");
        if((0x800000 & i) != 0)
            stringbuilder.append("ROUTE_TYPE_USER ");
        return stringbuilder.toString();
    }

    static void updateRoute(RouteInfo routeinfo)
    {
        dispatchRouteChanged(routeinfo);
    }

    private static void updateWifiDisplayRoute(RouteInfo routeinfo, WifiDisplay wifidisplay, WifiDisplayStatus wifidisplaystatus, boolean flag)
    {
        boolean flag1 = false;
        String s = wifidisplay.getFriendlyDisplayName();
        if(!routeinfo.getName().equals(s))
        {
            routeinfo.mName = s;
            flag1 = true;
        }
        boolean flag2 = isWifiDisplayEnabled(wifidisplay, wifidisplaystatus);
        boolean flag3;
        if(routeinfo.mEnabled != flag2)
            flag3 = true;
        else
            flag3 = false;
        routeinfo.mEnabled = flag2;
        if(flag1 | flag3 | routeinfo.setRealStatusCode(getWifiDisplayStatusCode(wifidisplay, wifidisplaystatus)))
            dispatchRouteChanged(routeinfo);
        if((!flag2 || flag) && routeinfo.isSelected())
            selectDefaultRouteStatic();
    }

    static void updateWifiDisplayStatus(WifiDisplayStatus wifidisplaystatus)
    {
        WifiDisplay awifidisplay[];
        Object obj;
        Object obj1;
        if(wifidisplaystatus.getFeatureState() == 3)
        {
            awifidisplay = wifidisplaystatus.getDisplays();
            obj = wifidisplaystatus.getActiveDisplay();
            obj1 = obj;
            int i;
            if(!sStatic.mCanConfigureWifiDisplays)
                if(obj != null)
                {
                    awifidisplay = new WifiDisplay[1];
                    awifidisplay[0] = ((WifiDisplay) (obj));
                    obj1 = obj;
                } else
                {
                    awifidisplay = WifiDisplay.EMPTY_ARRAY;
                    obj1 = obj;
                }
        } else
        {
            awifidisplay = WifiDisplay.EMPTY_ARRAY;
            obj1 = null;
        }
        if(obj1 != null)
            obj = ((WifiDisplay) (obj1)).getDeviceAddress();
        else
            obj = null;
        i = 0;
        do
        {
            while(i < awifidisplay.length) 
            {
                WifiDisplay wifidisplay = awifidisplay[i];
                if(shouldShowWifiDisplay(wifidisplay, ((WifiDisplay) (obj1))))
                {
                    RouteInfo routeinfo = findWifiDisplayRoute(wifidisplay);
                    if(routeinfo == null)
                    {
                        routeinfo = makeWifiDisplayRoute(wifidisplay, wifidisplaystatus);
                        addRouteStatic(routeinfo);
                    } else
                    {
                        String s = wifidisplay.getDeviceAddress();
                        boolean flag;
                        if(!s.equals(obj))
                            flag = s.equals(sStatic.mPreviousActiveWifiDisplayAddress);
                        else
                            flag = false;
                        updateWifiDisplayRoute(routeinfo, wifidisplay, wifidisplaystatus, flag);
                    }
                    if(wifidisplay.equals(((WifiDisplay) (obj1))))
                        selectRouteStatic(routeinfo.getSupportedTypes(), routeinfo, false);
                }
                i++;
            }
            int j = sStatic.mRoutes.size();
            do
            {
                int k = j - 1;
                if(j <= 0)
                    break;
                wifidisplaystatus = (RouteInfo)sStatic.mRoutes.get(k);
                if(((RouteInfo) (wifidisplaystatus)).mDeviceAddress != null)
                {
                    WifiDisplay wifidisplay1 = findWifiDisplay(awifidisplay, ((RouteInfo) (wifidisplaystatus)).mDeviceAddress);
                    if(wifidisplay1 == null || shouldShowWifiDisplay(wifidisplay1, ((WifiDisplay) (obj1))) ^ true)
                        removeRouteStatic(wifidisplaystatus);
                }
                j = k;
            } while(true);
            sStatic.mPreviousActiveWifiDisplayAddress = ((String) (obj));
            return;
        } while(true);
    }

    public void addCallback(int i, Callback callback)
    {
        addCallback(i, callback, 0);
    }

    public void addCallback(int i, Callback callback, int j)
    {
        int k = findCallbackInfo(callback);
        if(k >= 0)
        {
            callback = (CallbackInfo)sStatic.mCallbacks.get(k);
            callback.type = ((CallbackInfo) (callback)).type | i;
            callback.flags = ((CallbackInfo) (callback)).flags | j;
        } else
        {
            callback = new CallbackInfo(callback, i, j, this);
            sStatic.mCallbacks.add(callback);
        }
        sStatic.updateDiscoveryRequest();
    }

    public void addRouteInt(RouteInfo routeinfo)
    {
        addRouteStatic(routeinfo);
    }

    public void addUserRoute(UserRouteInfo userrouteinfo)
    {
        addRouteStatic(userrouteinfo);
    }

    public void clearUserRoutes()
    {
        int j;
        for(int i = 0; i < sStatic.mRoutes.size(); i = j + 1)
        {
            RouteInfo routeinfo = (RouteInfo)sStatic.mRoutes.get(i);
            if(!(routeinfo instanceof UserRouteInfo))
            {
                j = i;
                if(!(routeinfo instanceof RouteGroup))
                    continue;
            }
            removeRouteStatic(routeinfo);
            j = i - 1;
        }

    }

    public RouteCategory createRouteCategory(int i, boolean flag)
    {
        return new RouteCategory(i, 0x800000, flag);
    }

    public RouteCategory createRouteCategory(CharSequence charsequence, boolean flag)
    {
        return new RouteCategory(charsequence, 0x800000, flag);
    }

    public UserRouteInfo createUserRoute(RouteCategory routecategory)
    {
        return new UserRouteInfo(routecategory);
    }

    public RouteCategory getCategoryAt(int i)
    {
        return (RouteCategory)sStatic.mCategories.get(i);
    }

    public int getCategoryCount()
    {
        return sStatic.mCategories.size();
    }

    public RouteInfo getDefaultRoute()
    {
        return sStatic.mDefaultAudioVideo;
    }

    public RouteInfo getFallbackRoute()
    {
        RouteInfo routeinfo;
        if(sStatic.mBluetoothA2dpRoute != null)
            routeinfo = sStatic.mBluetoothA2dpRoute;
        else
            routeinfo = sStatic.mDefaultAudioVideo;
        return routeinfo;
    }

    public RouteInfo getRouteAt(int i)
    {
        return (RouteInfo)sStatic.mRoutes.get(i);
    }

    public int getRouteCount()
    {
        return sStatic.mRoutes.size();
    }

    public RouteInfo getSelectedRoute()
    {
        return getSelectedRoute(0x800007);
    }

    public RouteInfo getSelectedRoute(int i)
    {
        if(sStatic.mSelectedRoute != null && (sStatic.mSelectedRoute.mSupportedTypes & i) != 0)
            return sStatic.mSelectedRoute;
        if(i == 0x800000)
            return null;
        else
            return sStatic.mDefaultAudioVideo;
    }

    public RouteCategory getSystemCategory()
    {
        return sStatic.mSystemCategory;
    }

    public boolean isRouteAvailable(int i, int j)
    {
        int k = sStatic.mRoutes.size();
        for(int l = 0; l < k; l++)
        {
            RouteInfo routeinfo = (RouteInfo)sStatic.mRoutes.get(l);
            if(routeinfo.matchesTypes(i) && ((j & 1) == 0 || routeinfo != sStatic.mDefaultAudioVideo))
                return true;
        }

        return false;
    }

    public void rebindAsUser(int i)
    {
        sStatic.rebindAsUser(i);
    }

    public void removeCallback(Callback callback)
    {
        int i = findCallbackInfo(callback);
        if(i >= 0)
        {
            sStatic.mCallbacks.remove(i);
            sStatic.updateDiscoveryRequest();
        } else
        {
            Log.w("MediaRouter", (new StringBuilder()).append("removeCallback(").append(callback).append("): callback not registered").toString());
        }
    }

    public void removeRouteInt(RouteInfo routeinfo)
    {
        removeRouteStatic(routeinfo);
    }

    public void removeUserRoute(UserRouteInfo userrouteinfo)
    {
        removeRouteStatic(userrouteinfo);
    }

    public void selectRoute(int i, RouteInfo routeinfo)
    {
        if(routeinfo == null)
        {
            throw new IllegalArgumentException("Route cannot be null.");
        } else
        {
            selectRouteStatic(i, routeinfo, true);
            return;
        }
    }

    public void selectRouteInt(int i, RouteInfo routeinfo, boolean flag)
    {
        selectRouteStatic(i, routeinfo, flag);
    }

    static final boolean _2D_assertionsDisabled = android/media/MediaRouter.desiredAssertionStatus() ^ true;
    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int CALLBACK_FLAG_PASSIVE_DISCOVERY = 8;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    private static final boolean DEBUG = Log.isLoggable("MediaRouter", 3);
    static final int ROUTE_TYPE_ANY = 0x800007;
    public static final int ROUTE_TYPE_LIVE_AUDIO = 1;
    public static final int ROUTE_TYPE_LIVE_VIDEO = 2;
    public static final int ROUTE_TYPE_REMOTE_DISPLAY = 4;
    public static final int ROUTE_TYPE_USER = 0x800000;
    private static final String TAG = "MediaRouter";
    static final HashMap sRouters = new HashMap();
    static Static sStatic;


    // Unreferenced inner class android/media/MediaRouter$RouteInfo$1

/* anonymous class */
    class RouteInfo._cls1 extends IRemoteVolumeObserver.Stub
    {

        public void dispatchRemoteVolumeUpdate(final int direction, int i)
        {
            MediaRouter.sStatic.mHandler.post(i. new Runnable() {

                public void run()
                {
    class RouteInfo._cls1 extends IRemoteVolumeObserver.Stub
    {

        public void dispatchRemoteVolumeUpdate(final int direction, int i)
        {
            MediaRouter.sStatic.mHandler.post(i. new Runnable()                     if(mVcb != null)
                        if(direction != 0)
                            mVcb.vcb.onVolumeUpdateRequest(mVcb.route, direction);
                        else
                            mVcb.vcb.onVolumeSetRequest(mVcb.route, value);
                }

                final RouteInfo._cls1 this$2;
                final int val$direction;
                final int val$value;

            
            {
                this$2 = final__pcls1;
                direction = i;
                value = I.this;
                super();
            }
            }
);
        }

        final RouteInfo this$1;

            
            {
                this$1 = RouteInfo.this;
                super();
            }
    }


    // Unreferenced inner class android/media/MediaRouter$Static$1

/* anonymous class */
    class Static._cls1 extends IAudioRoutesObserver.Stub
    {

        public void dispatchAudioRoutesChanged(AudioRoutesInfo audioroutesinfo)
        {
            mHandler.post(audioroutesinfo. new Runnable() {

                public void run()
                {
    class Static._cls1 extends IAudioRoutesObserver.Stub
    {

        public void dispatchAudioRoutesChanged(AudioRoutesInfo audioroutesinfo)
        {
            mHandler.post(audioroutesinfo. new Runnable()                     updateAudioRoutes(newRoutes);
                }

                final Static._cls1 this$2;
                final AudioRoutesInfo val$newRoutes;

            
            {
                this$2 = final__pcls1;
                newRoutes = AudioRoutesInfo.this;
                super();
            }
            }
);
        }

        final Static this$1;

            
            {
                this$1 = Static.this;
                super();
            }
    }

}
