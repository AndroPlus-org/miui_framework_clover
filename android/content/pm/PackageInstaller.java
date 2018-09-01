// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.app.AppGlobals;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.system.ErrnoException;
import android.system.Os;
import android.util.ExceptionUtils;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.util.*;

// Referenced classes of package android.content.pm:
//            IPackageInstaller, ParceledListSlice, VersionedPackage, IPackageInstallerSession, 
//            IPackageManager

public class PackageInstaller
{
    public static class Session
        implements Closeable
    {

        public void abandon()
        {
            try
            {
                mSession.abandon();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public void addProgress(float f)
        {
            try
            {
                mSession.addClientProgress(f);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public void close()
        {
            try
            {
                mSession.close();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public void commit(IntentSender intentsender)
        {
            try
            {
                mSession.commit(intentsender, false);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(IntentSender intentsender)
            {
                throw intentsender.rethrowFromSystemServer();
            }
        }

        public void commitTransferred(IntentSender intentsender)
        {
            try
            {
                mSession.commit(intentsender, true);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(IntentSender intentsender)
            {
                throw intentsender.rethrowFromSystemServer();
            }
        }

        public void fsync(OutputStream outputstream)
            throws IOException
        {
            if(!PackageInstaller.ENABLE_REVOCABLE_FD)
                break MISSING_BLOCK_LABEL_40;
            if(!(outputstream instanceof android.os.ParcelFileDescriptor.AutoCloseOutputStream)) goto _L2; else goto _L1
_L1:
            Os.fsync(((android.os.ParcelFileDescriptor.AutoCloseOutputStream)outputstream).getFD());
_L3:
            return;
            outputstream;
            throw outputstream.rethrowAsIOException();
_L2:
            throw new IllegalArgumentException("Unrecognized stream");
            if(outputstream instanceof android.os.FileBridge.FileBridgeOutputStream)
                ((android.os.FileBridge.FileBridgeOutputStream)outputstream).fsync();
            else
                throw new IllegalArgumentException("Unrecognized stream");
              goto _L3
        }

        public String[] getNames()
            throws IOException
        {
            String as[];
            try
            {
                as = mSession.getNames();
            }
            catch(RuntimeException runtimeexception)
            {
                ExceptionUtils.maybeUnwrapIOException(runtimeexception);
                throw runtimeexception;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return as;
        }

        public InputStream openRead(String s)
            throws IOException
        {
            try
            {
                s = new android.os.ParcelFileDescriptor.AutoCloseInputStream(mSession.openRead(s));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                ExceptionUtils.maybeUnwrapIOException(s);
                throw s;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return s;
        }

        public OutputStream openWrite(String s, long l, long l1)
            throws IOException
        {
            try
            {
                if(PackageInstaller.ENABLE_REVOCABLE_FD)
                    return new android.os.ParcelFileDescriptor.AutoCloseOutputStream(mSession.openWrite(s, l, l1));
                s = new android.os.FileBridge.FileBridgeOutputStream(mSession.openWrite(s, l, l1));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                ExceptionUtils.maybeUnwrapIOException(s);
                throw s;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return s;
        }

        public void removeSplit(String s)
            throws IOException
        {
            try
            {
                mSession.removeSplit(s);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                ExceptionUtils.maybeUnwrapIOException(s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            throw s;
        }

        public void setProgress(float f)
        {
            setStagingProgress(f);
        }

        public void setStagingProgress(float f)
        {
            try
            {
                mSession.setClientProgress(f);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public void transfer(String s)
            throws PackageManager.NameNotFoundException
        {
            Preconditions.checkNotNull(s);
            try
            {
                mSession.transfer(s);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.maybeRethrow(android/content/pm/PackageManager$NameNotFoundException);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            throw new RuntimeException(s);
        }

        private IPackageInstallerSession mSession;

        public Session(IPackageInstallerSession ipackageinstallersession)
        {
            mSession = ipackageinstallersession;
        }
    }

    public static abstract class SessionCallback
    {

        public abstract void onActiveChanged(int i, boolean flag);

        public abstract void onBadgingChanged(int i);

        public abstract void onCreated(int i);

        public abstract void onFinished(int i, boolean flag);

        public abstract void onProgressChanged(int i, float f);

        public SessionCallback()
        {
        }
    }

    private static class SessionCallbackDelegate extends IPackageInstallerCallback.Stub
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
            boolean flag = false;
            int i = message.arg1;
            SessionCallback sessioncallback;
            switch(message.what)
            {
            default:
                return false;

            case 1: // '\001'
                mCallback.onCreated(i);
                return true;

            case 2: // '\002'
                mCallback.onBadgingChanged(i);
                return true;

            case 3: // '\003'
                if(message.arg2 != 0)
                    flag = true;
                else
                    flag = false;
                mCallback.onActiveChanged(i, flag);
                return true;

            case 4: // '\004'
                mCallback.onProgressChanged(i, ((Float)message.obj).floatValue());
                return true;

            case 5: // '\005'
                sessioncallback = mCallback;
                break;
            }
            if(message.arg2 != 0)
                flag = true;
            sessioncallback.onFinished(i, flag);
            return true;
        }

        public void onSessionActiveChanged(int i, boolean flag)
        {
            Handler handler = mHandler;
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            handler.obtainMessage(3, i, j).sendToTarget();
        }

        public void onSessionBadgingChanged(int i)
        {
            mHandler.obtainMessage(2, i, 0).sendToTarget();
        }

        public void onSessionCreated(int i)
        {
            mHandler.obtainMessage(1, i, 0).sendToTarget();
        }

        public void onSessionFinished(int i, boolean flag)
        {
            Handler handler = mHandler;
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            handler.obtainMessage(5, i, j).sendToTarget();
        }

        public void onSessionProgressChanged(int i, float f)
        {
            mHandler.obtainMessage(4, i, 0, Float.valueOf(f)).sendToTarget();
        }

        private static final int MSG_SESSION_ACTIVE_CHANGED = 3;
        private static final int MSG_SESSION_BADGING_CHANGED = 2;
        private static final int MSG_SESSION_CREATED = 1;
        private static final int MSG_SESSION_FINISHED = 5;
        private static final int MSG_SESSION_PROGRESS_CHANGED = 4;
        final SessionCallback mCallback;
        final Handler mHandler;

        public SessionCallbackDelegate(SessionCallback sessioncallback, Looper looper)
        {
            mCallback = sessioncallback;
            mHandler = new Handler(looper, this);
        }
    }

    public static class SessionInfo
        implements Parcelable
    {

        public Intent createDetailsIntent()
        {
            Intent intent = new Intent("android.content.pm.action.SESSION_DETAILS");
            intent.putExtra("android.content.pm.extra.SESSION_ID", sessionId);
            intent.setPackage(installerPackageName);
            intent.setFlags(0x10000000);
            return intent;
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean getAllocateAggressive()
        {
            boolean flag = false;
            if((installFlags & 0x8000) != 0)
                flag = true;
            return flag;
        }

        public boolean getAllowDowngrade()
        {
            boolean flag = false;
            if((installFlags & 0x80) != 0)
                flag = true;
            return flag;
        }

        public Bitmap getAppIcon()
        {
            Bitmap bitmap = null;
            if(appIcon != null)
                break MISSING_BLOCK_LABEL_41;
            SessionInfo sessioninfo;
            try
            {
                sessioninfo = AppGlobals.getPackageManager().getPackageInstaller().getSessionInfo(sessionId);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            if(sessioninfo == null)
                break MISSING_BLOCK_LABEL_36;
            bitmap = sessioninfo.appIcon;
            appIcon = bitmap;
            return appIcon;
        }

        public CharSequence getAppLabel()
        {
            return appLabel;
        }

        public String getAppPackageName()
        {
            return appPackageName;
        }

        public Intent getDetailsIntent()
        {
            return createDetailsIntent();
        }

        public boolean getDontKillApp()
        {
            boolean flag = false;
            if((installFlags & 0x1000) != 0)
                flag = true;
            return flag;
        }

        public String[] getGrantedRuntimePermissions()
        {
            return grantedRuntimePermissions;
        }

        public boolean getInstallAsFullApp(boolean flag)
        {
            flag = false;
            if((installFlags & 0x4000) != 0)
                flag = true;
            return flag;
        }

        public boolean getInstallAsInstantApp(boolean flag)
        {
            flag = false;
            if((installFlags & 0x800) != 0)
                flag = true;
            return flag;
        }

        public boolean getInstallAsVirtualPreload()
        {
            boolean flag = false;
            if((installFlags & 0x10000) != 0)
                flag = true;
            return flag;
        }

        public int getInstallLocation()
        {
            return installLocation;
        }

        public int getInstallReason()
        {
            return installReason;
        }

        public String getInstallerPackageName()
        {
            return installerPackageName;
        }

        public int getMode()
        {
            return mode;
        }

        public int getOriginatingUid()
        {
            return originatingUid;
        }

        public Uri getOriginatingUri()
        {
            return originatingUri;
        }

        public float getProgress()
        {
            return progress;
        }

        public Uri getReferrerUri()
        {
            return referrerUri;
        }

        public int getSessionId()
        {
            return sessionId;
        }

        public long getSize()
        {
            return sizeBytes;
        }

        public boolean isActive()
        {
            return active;
        }

        public boolean isOpen()
        {
            return isActive();
        }

        public boolean isSealed()
        {
            return sealed;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeInt(sessionId);
            parcel.writeString(installerPackageName);
            parcel.writeString(resolvedBaseCodePath);
            parcel.writeFloat(progress);
            int j;
            String s;
            if(sealed)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            if(active)
                j = ((flag) ? 1 : 0);
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(mode);
            parcel.writeInt(installReason);
            parcel.writeLong(sizeBytes);
            parcel.writeString(appPackageName);
            parcel.writeParcelable(appIcon, i);
            if(appLabel != null)
                s = appLabel.toString();
            else
                s = null;
            parcel.writeString(s);
            parcel.writeInt(installLocation);
            parcel.writeParcelable(originatingUri, i);
            parcel.writeInt(originatingUid);
            parcel.writeParcelable(referrerUri, i);
            parcel.writeStringArray(grantedRuntimePermissions);
            parcel.writeInt(installFlags);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SessionInfo createFromParcel(Parcel parcel)
            {
                return new SessionInfo(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SessionInfo[] newArray(int i)
            {
                return new SessionInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public boolean active;
        public Bitmap appIcon;
        public CharSequence appLabel;
        public String appPackageName;
        public String grantedRuntimePermissions[];
        public int installFlags;
        public int installLocation;
        public int installReason;
        public String installerPackageName;
        public int mode;
        public int originatingUid;
        public Uri originatingUri;
        public float progress;
        public Uri referrerUri;
        public String resolvedBaseCodePath;
        public boolean sealed;
        public int sessionId;
        public long sizeBytes;


        public SessionInfo()
        {
        }

        public SessionInfo(Parcel parcel)
        {
            boolean flag = true;
            super();
            sessionId = parcel.readInt();
            installerPackageName = parcel.readString();
            resolvedBaseCodePath = parcel.readString();
            progress = parcel.readFloat();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            sealed = flag1;
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            active = flag1;
            mode = parcel.readInt();
            installReason = parcel.readInt();
            sizeBytes = parcel.readLong();
            appPackageName = parcel.readString();
            appIcon = (Bitmap)parcel.readParcelable(null);
            appLabel = parcel.readString();
            installLocation = parcel.readInt();
            originatingUri = (Uri)parcel.readParcelable(null);
            originatingUid = parcel.readInt();
            referrerUri = (Uri)parcel.readParcelable(null);
            grantedRuntimePermissions = parcel.readStringArray();
            installFlags = parcel.readInt();
        }
    }

    public static class SessionParams
        implements Parcelable
    {

        public boolean areHiddenOptionsSet()
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if((installFlags & 0x1d880) != installFlags) goto _L2; else goto _L1
_L1:
            if(abiOverride == null) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(volumeUuid == null)
                flag1 = false;
            if(true) goto _L2; else goto _L5
_L5:
        }

        public int describeContents()
        {
            return 0;
        }

        public void dump(IndentingPrintWriter indentingprintwriter)
        {
            indentingprintwriter.printPair("mode", Integer.valueOf(mode));
            indentingprintwriter.printHexPair("installFlags", installFlags);
            indentingprintwriter.printPair("installLocation", Integer.valueOf(installLocation));
            indentingprintwriter.printPair("sizeBytes", Long.valueOf(sizeBytes));
            indentingprintwriter.printPair("appPackageName", appPackageName);
            boolean flag;
            if(appIcon != null)
                flag = true;
            else
                flag = false;
            indentingprintwriter.printPair("appIcon", Boolean.valueOf(flag));
            indentingprintwriter.printPair("appLabel", appLabel);
            indentingprintwriter.printPair("originatingUri", originatingUri);
            indentingprintwriter.printPair("originatingUid", Integer.valueOf(originatingUid));
            indentingprintwriter.printPair("referrerUri", referrerUri);
            indentingprintwriter.printPair("abiOverride", abiOverride);
            indentingprintwriter.printPair("volumeUuid", volumeUuid);
            indentingprintwriter.printPair("grantedRuntimePermissions", grantedRuntimePermissions);
            indentingprintwriter.println();
        }

        public void setAllocateAggressive(boolean flag)
        {
            if(flag)
                installFlags = installFlags | 0x8000;
            else
                installFlags = installFlags & 0xffff7fff;
        }

        public void setAllowDowngrade(boolean flag)
        {
            if(flag)
                installFlags = installFlags | 0x80;
            else
                installFlags = installFlags & 0xffffff7f;
        }

        public void setAppIcon(Bitmap bitmap)
        {
            appIcon = bitmap;
        }

        public void setAppLabel(CharSequence charsequence)
        {
            String s = null;
            if(charsequence != null)
                s = charsequence.toString();
            appLabel = s;
        }

        public void setAppPackageName(String s)
        {
            appPackageName = s;
        }

        public void setDontKillApp(boolean flag)
        {
            if(flag)
                installFlags = installFlags | 0x1000;
            else
                installFlags = installFlags & 0xffffefff;
        }

        public void setGrantedRuntimePermissions(String as[])
        {
            installFlags = installFlags | 0x100;
            grantedRuntimePermissions = as;
        }

        public void setInstallAsInstantApp(boolean flag)
        {
            if(flag)
            {
                installFlags = installFlags | 0x800;
                installFlags = installFlags & 0xffffbfff;
            } else
            {
                installFlags = installFlags & 0xfffff7ff;
                installFlags = installFlags | 0x4000;
            }
        }

        public void setInstallAsVirtualPreload()
        {
            installFlags = installFlags | 0x10000;
        }

        public void setInstallFlagsExternal()
        {
            installFlags = installFlags | 8;
            installFlags = installFlags & 0xffffffef;
        }

        public void setInstallFlagsForcePermissionPrompt()
        {
            installFlags = installFlags | 0x400;
        }

        public void setInstallFlagsInternal()
        {
            installFlags = installFlags | 0x10;
            installFlags = installFlags & -9;
        }

        public void setInstallLocation(int i)
        {
            installLocation = i;
        }

        public void setInstallReason(int i)
        {
            installReason = i;
        }

        public void setOriginatingUid(int i)
        {
            originatingUid = i;
        }

        public void setOriginatingUri(Uri uri)
        {
            originatingUri = uri;
        }

        public void setReferrerUri(Uri uri)
        {
            referrerUri = uri;
        }

        public void setSize(long l)
        {
            sizeBytes = l;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mode);
            parcel.writeInt(installFlags);
            parcel.writeInt(installLocation);
            parcel.writeInt(installReason);
            parcel.writeLong(sizeBytes);
            parcel.writeString(appPackageName);
            parcel.writeParcelable(appIcon, i);
            parcel.writeString(appLabel);
            parcel.writeParcelable(originatingUri, i);
            parcel.writeInt(originatingUid);
            parcel.writeParcelable(referrerUri, i);
            parcel.writeString(abiOverride);
            parcel.writeString(volumeUuid);
            parcel.writeStringArray(grantedRuntimePermissions);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SessionParams createFromParcel(Parcel parcel)
            {
                return new SessionParams(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SessionParams[] newArray(int i)
            {
                return new SessionParams[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int MODE_FULL_INSTALL = 1;
        public static final int MODE_INHERIT_EXISTING = 2;
        public static final int MODE_INVALID = -1;
        public static final int UID_UNKNOWN = -1;
        public String abiOverride;
        public Bitmap appIcon;
        public long appIconLastModified;
        public String appLabel;
        public String appPackageName;
        public String grantedRuntimePermissions[];
        public int installFlags;
        public int installLocation;
        public int installReason;
        public int mode;
        public int originatingUid;
        public Uri originatingUri;
        public Uri referrerUri;
        public long sizeBytes;
        public String volumeUuid;


        public SessionParams(int i)
        {
            mode = -1;
            installLocation = 1;
            installReason = 0;
            sizeBytes = -1L;
            appIconLastModified = -1L;
            originatingUid = -1;
            mode = i;
        }

        public SessionParams(Parcel parcel)
        {
            mode = -1;
            installLocation = 1;
            installReason = 0;
            sizeBytes = -1L;
            appIconLastModified = -1L;
            originatingUid = -1;
            mode = parcel.readInt();
            installFlags = parcel.readInt();
            installLocation = parcel.readInt();
            installReason = parcel.readInt();
            sizeBytes = parcel.readLong();
            appPackageName = parcel.readString();
            appIcon = (Bitmap)parcel.readParcelable(null);
            appLabel = parcel.readString();
            originatingUri = (Uri)parcel.readParcelable(null);
            originatingUid = parcel.readInt();
            referrerUri = (Uri)parcel.readParcelable(null);
            abiOverride = parcel.readString();
            volumeUuid = parcel.readString();
            grantedRuntimePermissions = parcel.readStringArray();
        }
    }


    public PackageInstaller(IPackageInstaller ipackageinstaller, String s, int i)
    {
        mInstaller = ipackageinstaller;
        mInstallerPackageName = s;
        mUserId = i;
    }

    public void abandonSession(int i)
    {
        try
        {
            mInstaller.abandonSession(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void addSessionCallback(SessionCallback sessioncallback)
    {
        registerSessionCallback(sessioncallback);
    }

    public void addSessionCallback(SessionCallback sessioncallback, Handler handler)
    {
        registerSessionCallback(sessioncallback, handler);
    }

    public int createSession(SessionParams sessionparams)
        throws IOException
    {
        int i;
        try
        {
            i = mInstaller.createSession(sessionparams, mInstallerPackageName, mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(SessionParams sessionparams)
        {
            ExceptionUtils.maybeUnwrapIOException(sessionparams);
            throw sessionparams;
        }
        // Misplaced declaration of an exception variable
        catch(SessionParams sessionparams)
        {
            throw sessionparams.rethrowFromSystemServer();
        }
        return i;
    }

    public List getAllSessions()
    {
        List list;
        try
        {
            list = mInstaller.getAllSessions(mUserId).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getMySessions()
    {
        List list;
        try
        {
            list = mInstaller.getMySessions(mInstallerPackageName, mUserId).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public SessionInfo getSessionInfo(int i)
    {
        SessionInfo sessioninfo;
        try
        {
            sessioninfo = mInstaller.getSessionInfo(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return sessioninfo;
    }

    public Session openSession(int i)
        throws IOException
    {
        Session session;
        try
        {
            session = new Session(mInstaller.openSession(i));
        }
        catch(RuntimeException runtimeexception)
        {
            ExceptionUtils.maybeUnwrapIOException(runtimeexception);
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return session;
    }

    public void registerSessionCallback(SessionCallback sessioncallback)
    {
        registerSessionCallback(sessioncallback, new Handler());
    }

    public void registerSessionCallback(SessionCallback sessioncallback, Handler handler)
    {
        ArrayList arraylist = mDelegates;
        arraylist;
        JVM INSTR monitorenter ;
        SessionCallbackDelegate sessioncallbackdelegate;
        sessioncallbackdelegate = JVM INSTR new #12  <Class PackageInstaller$SessionCallbackDelegate>;
        sessioncallbackdelegate.SessionCallbackDelegate(sessioncallback, handler.getLooper());
        mInstaller.registerCallback(sessioncallbackdelegate, mUserId);
        mDelegates.add(sessioncallbackdelegate);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        sessioncallback;
        throw sessioncallback.rethrowFromSystemServer();
        sessioncallback;
        arraylist;
        JVM INSTR monitorexit ;
        throw sessioncallback;
    }

    public void removeSessionCallback(SessionCallback sessioncallback)
    {
        unregisterSessionCallback(sessioncallback);
    }

    public void setPermissionsResult(int i, boolean flag)
    {
        try
        {
            mInstaller.setPermissionsResult(i, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void uninstall(VersionedPackage versionedpackage, int i, IntentSender intentsender)
    {
        Preconditions.checkNotNull(versionedpackage, "versionedPackage cannot be null");
        try
        {
            mInstaller.uninstall(versionedpackage, mInstallerPackageName, i, intentsender, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(VersionedPackage versionedpackage)
        {
            throw versionedpackage.rethrowFromSystemServer();
        }
    }

    public void uninstall(VersionedPackage versionedpackage, IntentSender intentsender)
    {
        uninstall(versionedpackage, 0, intentsender);
    }

    public void uninstall(String s, int i, IntentSender intentsender)
    {
        uninstall(new VersionedPackage(s, -1), i, intentsender);
    }

    public void uninstall(String s, IntentSender intentsender)
    {
        uninstall(s, 0, intentsender);
    }

    public void unregisterSessionCallback(SessionCallback sessioncallback)
    {
        ArrayList arraylist = mDelegates;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mDelegates.iterator();
_L2:
        SessionCallbackDelegate sessioncallbackdelegate;
        SessionCallback sessioncallback1;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_79;
            sessioncallbackdelegate = (SessionCallbackDelegate)iterator.next();
            sessioncallback1 = sessioncallbackdelegate.mCallback;
        } while(sessioncallback1 != sessioncallback);
        mInstaller.unregisterCallback(sessioncallbackdelegate);
        iterator.remove();
        if(true) goto _L2; else goto _L1
_L1:
        sessioncallback;
        throw sessioncallback;
        sessioncallback;
        throw sessioncallback.rethrowFromSystemServer();
        arraylist;
        JVM INSTR monitorexit ;
    }

    public void updateSessionAppIcon(int i, Bitmap bitmap)
    {
        try
        {
            mInstaller.updateSessionAppIcon(i, bitmap);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Bitmap bitmap)
        {
            throw bitmap.rethrowFromSystemServer();
        }
    }

    public void updateSessionAppLabel(int i, CharSequence charsequence)
    {
        if(charsequence == null)
            break MISSING_BLOCK_LABEL_23;
        charsequence = charsequence.toString();
_L1:
        mInstaller.updateSessionAppLabel(i, charsequence);
        return;
        charsequence = null;
          goto _L1
        charsequence;
        throw charsequence.rethrowFromSystemServer();
    }

    public static final String ACTION_CONFIRM_PERMISSIONS = "android.content.pm.action.CONFIRM_PERMISSIONS";
    public static final String ACTION_SESSION_COMMITTED = "android.content.pm.action.SESSION_COMMITTED";
    public static final String ACTION_SESSION_DETAILS = "android.content.pm.action.SESSION_DETAILS";
    public static final boolean ENABLE_REVOCABLE_FD = SystemProperties.getBoolean("fw.revocable_fd", false);
    public static final String EXTRA_CALLBACK = "android.content.pm.extra.CALLBACK";
    public static final String EXTRA_LEGACY_BUNDLE = "android.content.pm.extra.LEGACY_BUNDLE";
    public static final String EXTRA_LEGACY_STATUS = "android.content.pm.extra.LEGACY_STATUS";
    public static final String EXTRA_OTHER_PACKAGE_NAME = "android.content.pm.extra.OTHER_PACKAGE_NAME";
    public static final String EXTRA_PACKAGE_NAME = "android.content.pm.extra.PACKAGE_NAME";
    public static final String EXTRA_PACKAGE_NAMES = "android.content.pm.extra.PACKAGE_NAMES";
    public static final String EXTRA_SESSION = "android.content.pm.extra.SESSION";
    public static final String EXTRA_SESSION_ID = "android.content.pm.extra.SESSION_ID";
    public static final String EXTRA_STATUS = "android.content.pm.extra.STATUS";
    public static final String EXTRA_STATUS_MESSAGE = "android.content.pm.extra.STATUS_MESSAGE";
    public static final String EXTRA_STORAGE_PATH = "android.content.pm.extra.STORAGE_PATH";
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_FAILURE_ABORTED = 3;
    public static final int STATUS_FAILURE_BLOCKED = 2;
    public static final int STATUS_FAILURE_CONFLICT = 5;
    public static final int STATUS_FAILURE_INCOMPATIBLE = 7;
    public static final int STATUS_FAILURE_INVALID = 4;
    public static final int STATUS_FAILURE_STORAGE = 6;
    public static final int STATUS_PENDING_USER_ACTION = -1;
    public static final int STATUS_SUCCESS = 0;
    private static final String TAG = "PackageInstaller";
    private final ArrayList mDelegates = new ArrayList();
    private final IPackageInstaller mInstaller;
    private final String mInstallerPackageName;
    private final int mUserId;

}
