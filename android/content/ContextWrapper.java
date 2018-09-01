// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.app.IApplicationThread;
import android.app.IServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.view.Display;
import android.view.DisplayAdjustments;
import java.io.*;

// Referenced classes of package android.content:
//            Context, Intent, ServiceConnection, ContentResolver, 
//            SharedPreferences, BroadcastReceiver, IntentFilter, ComponentName, 
//            IntentSender

public class ContextWrapper extends Context
{

    public ContextWrapper(Context context)
    {
        mBase = context;
    }

    protected void attachBaseContext(Context context)
    {
        if(mBase != null)
        {
            throw new IllegalStateException("Base context already set");
        } else
        {
            mBase = context;
            return;
        }
    }

    public boolean bindService(Intent intent, ServiceConnection serviceconnection, int i)
    {
        return mBase.bindService(intent, serviceconnection, i);
    }

    public boolean bindServiceAsUser(Intent intent, ServiceConnection serviceconnection, int i, Handler handler, UserHandle userhandle)
    {
        return mBase.bindServiceAsUser(intent, serviceconnection, i, handler, userhandle);
    }

    public boolean bindServiceAsUser(Intent intent, ServiceConnection serviceconnection, int i, UserHandle userhandle)
    {
        return mBase.bindServiceAsUser(intent, serviceconnection, i, userhandle);
    }

    public boolean canLoadUnsafeResources()
    {
        return mBase.canLoadUnsafeResources();
    }

    public boolean canStartActivityForResult()
    {
        return mBase.canStartActivityForResult();
    }

    public int checkCallingOrSelfPermission(String s)
    {
        return mBase.checkCallingOrSelfPermission(s);
    }

    public int checkCallingOrSelfUriPermission(Uri uri, int i)
    {
        return mBase.checkCallingOrSelfUriPermission(uri, i);
    }

    public int checkCallingPermission(String s)
    {
        return mBase.checkCallingPermission(s);
    }

    public int checkCallingUriPermission(Uri uri, int i)
    {
        return mBase.checkCallingUriPermission(uri, i);
    }

    public int checkPermission(String s, int i, int j)
    {
        return mBase.checkPermission(s, i, j);
    }

    public int checkPermission(String s, int i, int j, IBinder ibinder)
    {
        return mBase.checkPermission(s, i, j, ibinder);
    }

    public int checkSelfPermission(String s)
    {
        return mBase.checkSelfPermission(s);
    }

    public int checkUriPermission(Uri uri, int i, int j, int k)
    {
        return mBase.checkUriPermission(uri, i, j, k);
    }

    public int checkUriPermission(Uri uri, int i, int j, int k, IBinder ibinder)
    {
        return mBase.checkUriPermission(uri, i, j, k, ibinder);
    }

    public int checkUriPermission(Uri uri, String s, String s1, int i, int j, int k)
    {
        return mBase.checkUriPermission(uri, s, s1, i, j, k);
    }

    public void clearWallpaper()
        throws IOException
    {
        mBase.clearWallpaper();
    }

    public Context createApplicationContext(ApplicationInfo applicationinfo, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return mBase.createApplicationContext(applicationinfo, i);
    }

    public Context createConfigurationContext(Configuration configuration)
    {
        return mBase.createConfigurationContext(configuration);
    }

    public Context createContextForSplit(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return mBase.createContextForSplit(s);
    }

    public Context createCredentialProtectedStorageContext()
    {
        return mBase.createCredentialProtectedStorageContext();
    }

    public Context createDeviceProtectedStorageContext()
    {
        return mBase.createDeviceProtectedStorageContext();
    }

    public Context createDisplayContext(Display display)
    {
        return mBase.createDisplayContext(display);
    }

    public Context createPackageContext(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return mBase.createPackageContext(s, i);
    }

    public Context createPackageContextAsUser(String s, int i, UserHandle userhandle)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return mBase.createPackageContextAsUser(s, i, userhandle);
    }

    public String[] databaseList()
    {
        return mBase.databaseList();
    }

    public boolean deleteDatabase(String s)
    {
        return mBase.deleteDatabase(s);
    }

    public boolean deleteFile(String s)
    {
        return mBase.deleteFile(s);
    }

    public boolean deleteSharedPreferences(String s)
    {
        return mBase.deleteSharedPreferences(s);
    }

    public void enforceCallingOrSelfPermission(String s, String s1)
    {
        mBase.enforceCallingOrSelfPermission(s, s1);
    }

    public void enforceCallingOrSelfUriPermission(Uri uri, int i, String s)
    {
        mBase.enforceCallingOrSelfUriPermission(uri, i, s);
    }

    public void enforceCallingPermission(String s, String s1)
    {
        mBase.enforceCallingPermission(s, s1);
    }

    public void enforceCallingUriPermission(Uri uri, int i, String s)
    {
        mBase.enforceCallingUriPermission(uri, i, s);
    }

    public void enforcePermission(String s, int i, int j, String s1)
    {
        mBase.enforcePermission(s, i, j, s1);
    }

    public void enforceUriPermission(Uri uri, int i, int j, int k, String s)
    {
        mBase.enforceUriPermission(uri, i, j, k, s);
    }

    public void enforceUriPermission(Uri uri, String s, String s1, int i, int j, int k, String s2)
    {
        mBase.enforceUriPermission(uri, s, s1, i, j, k, s2);
    }

    public String[] fileList()
    {
        return mBase.fileList();
    }

    public IBinder getActivityToken()
    {
        return mBase.getActivityToken();
    }

    public Context getApplicationContext()
    {
        return mBase.getApplicationContext();
    }

    public ApplicationInfo getApplicationInfo()
    {
        return mBase.getApplicationInfo();
    }

    public AssetManager getAssets()
    {
        return mBase.getAssets();
    }

    public android.view.autofill.AutofillManager.AutofillClient getAutofillClient()
    {
        return mBase.getAutofillClient();
    }

    public Context getBaseContext()
    {
        return mBase;
    }

    public String getBasePackageName()
    {
        return mBase.getBasePackageName();
    }

    public File getCacheDir()
    {
        return mBase.getCacheDir();
    }

    public ClassLoader getClassLoader()
    {
        return mBase.getClassLoader();
    }

    public File getCodeCacheDir()
    {
        return mBase.getCodeCacheDir();
    }

    public ContentResolver getContentResolver()
    {
        return mBase.getContentResolver();
    }

    public ContentResolver getContentResolverForUser(UserHandle userhandle)
    {
        return mBase.getContentResolverForUser(userhandle);
    }

    public File getDataDir()
    {
        return mBase.getDataDir();
    }

    public File getDatabasePath(String s)
    {
        return mBase.getDatabasePath(s);
    }

    public File getDir(String s, int i)
    {
        return mBase.getDir(s, i);
    }

    public Display getDisplay()
    {
        return mBase.getDisplay();
    }

    public DisplayAdjustments getDisplayAdjustments(int i)
    {
        return mBase.getDisplayAdjustments(i);
    }

    public File getExternalCacheDir()
    {
        return mBase.getExternalCacheDir();
    }

    public File[] getExternalCacheDirs()
    {
        return mBase.getExternalCacheDirs();
    }

    public File getExternalFilesDir(String s)
    {
        return mBase.getExternalFilesDir(s);
    }

    public File[] getExternalFilesDirs(String s)
    {
        return mBase.getExternalFilesDirs(s);
    }

    public File[] getExternalMediaDirs()
    {
        return mBase.getExternalMediaDirs();
    }

    public File getFileStreamPath(String s)
    {
        return mBase.getFileStreamPath(s);
    }

    public File getFilesDir()
    {
        return mBase.getFilesDir();
    }

    public IApplicationThread getIApplicationThread()
    {
        return mBase.getIApplicationThread();
    }

    public Looper getMainLooper()
    {
        return mBase.getMainLooper();
    }

    public Handler getMainThreadHandler()
    {
        return mBase.getMainThreadHandler();
    }

    public int getNextAutofillId()
    {
        return mBase.getNextAutofillId();
    }

    public File getNoBackupFilesDir()
    {
        return mBase.getNoBackupFilesDir();
    }

    public File getObbDir()
    {
        return mBase.getObbDir();
    }

    public File[] getObbDirs()
    {
        return mBase.getObbDirs();
    }

    public String getOpPackageName()
    {
        return mBase.getOpPackageName();
    }

    public String getPackageCodePath()
    {
        return mBase.getPackageCodePath();
    }

    public PackageManager getPackageManager()
    {
        return mBase.getPackageManager();
    }

    public String getPackageName()
    {
        return mBase.getPackageName();
    }

    public String getPackageResourcePath()
    {
        return mBase.getPackageResourcePath();
    }

    public File getPreloadsFileCache()
    {
        return mBase.getPreloadsFileCache();
    }

    public Resources getResources()
    {
        return mBase.getResources();
    }

    public IServiceConnection getServiceDispatcher(ServiceConnection serviceconnection, Handler handler, int i)
    {
        return mBase.getServiceDispatcher(serviceconnection, handler, i);
    }

    public SharedPreferences getSharedPreferences(File file, int i)
    {
        return mBase.getSharedPreferences(file, i);
    }

    public SharedPreferences getSharedPreferences(String s, int i)
    {
        return mBase.getSharedPreferences(s, i);
    }

    public File getSharedPreferencesPath(String s)
    {
        return mBase.getSharedPreferencesPath(s);
    }

    public Object getSystemService(String s)
    {
        return mBase.getSystemService(s);
    }

    public String getSystemServiceName(Class class1)
    {
        return mBase.getSystemServiceName(class1);
    }

    public android.content.res.Resources.Theme getTheme()
    {
        return mBase.getTheme();
    }

    public int getThemeResId()
    {
        return mBase.getThemeResId();
    }

    public int getUserId()
    {
        return mBase.getUserId();
    }

    public Drawable getWallpaper()
    {
        return mBase.getWallpaper();
    }

    public int getWallpaperDesiredMinimumHeight()
    {
        return mBase.getWallpaperDesiredMinimumHeight();
    }

    public int getWallpaperDesiredMinimumWidth()
    {
        return mBase.getWallpaperDesiredMinimumWidth();
    }

    public void grantUriPermission(String s, Uri uri, int i)
    {
        mBase.grantUriPermission(s, uri, i);
    }

    public boolean isCredentialProtectedStorage()
    {
        return mBase.isCredentialProtectedStorage();
    }

    public boolean isDeviceProtectedStorage()
    {
        return mBase.isDeviceProtectedStorage();
    }

    public boolean isRestricted()
    {
        return mBase.isRestricted();
    }

    public boolean moveDatabaseFrom(Context context, String s)
    {
        return mBase.moveDatabaseFrom(context, s);
    }

    public boolean moveSharedPreferencesFrom(Context context, String s)
    {
        return mBase.moveSharedPreferencesFrom(context, s);
    }

    public FileInputStream openFileInput(String s)
        throws FileNotFoundException
    {
        return mBase.openFileInput(s);
    }

    public FileOutputStream openFileOutput(String s, int i)
        throws FileNotFoundException
    {
        return mBase.openFileOutput(s, i);
    }

    public SQLiteDatabase openOrCreateDatabase(String s, int i, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory)
    {
        return mBase.openOrCreateDatabase(s, i, cursorfactory);
    }

    public SQLiteDatabase openOrCreateDatabase(String s, int i, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, DatabaseErrorHandler databaseerrorhandler)
    {
        return mBase.openOrCreateDatabase(s, i, cursorfactory, databaseerrorhandler);
    }

    public Drawable peekWallpaper()
    {
        return mBase.peekWallpaper();
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter)
    {
        return mBase.registerReceiver(broadcastreceiver, intentfilter);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, int i)
    {
        return mBase.registerReceiver(broadcastreceiver, intentfilter, i);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, String s, Handler handler)
    {
        return mBase.registerReceiver(broadcastreceiver, intentfilter, s, handler);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, String s, Handler handler, int i)
    {
        return mBase.registerReceiver(broadcastreceiver, intentfilter, s, handler, i);
    }

    public Intent registerReceiverAsUser(BroadcastReceiver broadcastreceiver, UserHandle userhandle, IntentFilter intentfilter, String s, Handler handler)
    {
        return mBase.registerReceiverAsUser(broadcastreceiver, userhandle, intentfilter, s, handler);
    }

    public void reloadSharedPreferences()
    {
        mBase.reloadSharedPreferences();
    }

    public void removeStickyBroadcast(Intent intent)
    {
        mBase.removeStickyBroadcast(intent);
    }

    public void removeStickyBroadcastAsUser(Intent intent, UserHandle userhandle)
    {
        mBase.removeStickyBroadcastAsUser(intent, userhandle);
    }

    public void revokeUriPermission(Uri uri, int i)
    {
        mBase.revokeUriPermission(uri, i);
    }

    public void revokeUriPermission(String s, Uri uri, int i)
    {
        mBase.revokeUriPermission(s, uri, i);
    }

    public void sendBroadcast(Intent intent)
    {
        mBase.sendBroadcast(intent);
    }

    public void sendBroadcast(Intent intent, String s)
    {
        mBase.sendBroadcast(intent, s);
    }

    public void sendBroadcast(Intent intent, String s, int i)
    {
        mBase.sendBroadcast(intent, s, i);
    }

    public void sendBroadcast(Intent intent, String s, Bundle bundle)
    {
        mBase.sendBroadcast(intent, s, bundle);
    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userhandle)
    {
        mBase.sendBroadcastAsUser(intent, userhandle);
    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s)
    {
        mBase.sendBroadcastAsUser(intent, userhandle, s);
    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i)
    {
        mBase.sendBroadcastAsUser(intent, userhandle, s, i);
    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s, Bundle bundle)
    {
        mBase.sendBroadcastAsUser(intent, userhandle, s, bundle);
    }

    public void sendBroadcastMultiplePermissions(Intent intent, String as[])
    {
        mBase.sendBroadcastMultiplePermissions(intent, as);
    }

    public void sendOrderedBroadcast(Intent intent, String s)
    {
        mBase.sendOrderedBroadcast(intent, s);
    }

    public void sendOrderedBroadcast(Intent intent, String s, int i, BroadcastReceiver broadcastreceiver, Handler handler, int j, String s1, 
            Bundle bundle)
    {
        mBase.sendOrderedBroadcast(intent, s, i, broadcastreceiver, handler, j, s1, bundle);
    }

    public void sendOrderedBroadcast(Intent intent, String s, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, Bundle bundle)
    {
        mBase.sendOrderedBroadcast(intent, s, broadcastreceiver, handler, i, s1, bundle);
    }

    public void sendOrderedBroadcast(Intent intent, String s, Bundle bundle, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, 
            Bundle bundle1)
    {
        mBase.sendOrderedBroadcast(intent, s, bundle, broadcastreceiver, handler, i, s1, bundle1);
    }

    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i, BroadcastReceiver broadcastreceiver, Handler handler, int j, 
            String s1, Bundle bundle)
    {
        mBase.sendOrderedBroadcastAsUser(intent, userhandle, s, i, broadcastreceiver, handler, j, s1, bundle);
    }

    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i, Bundle bundle, BroadcastReceiver broadcastreceiver, Handler handler, 
            int j, String s1, Bundle bundle1)
    {
        mBase.sendOrderedBroadcastAsUser(intent, userhandle, s, i, bundle, broadcastreceiver, handler, j, s1, bundle1);
    }

    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, 
            Bundle bundle)
    {
        mBase.sendOrderedBroadcastAsUser(intent, userhandle, s, broadcastreceiver, handler, i, s1, bundle);
    }

    public void sendStickyBroadcast(Intent intent)
    {
        mBase.sendStickyBroadcast(intent);
    }

    public void sendStickyBroadcastAsUser(Intent intent, UserHandle userhandle)
    {
        mBase.sendStickyBroadcastAsUser(intent, userhandle);
    }

    public void sendStickyBroadcastAsUser(Intent intent, UserHandle userhandle, Bundle bundle)
    {
        mBase.sendStickyBroadcastAsUser(intent, userhandle, bundle);
    }

    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s, Bundle bundle)
    {
        mBase.sendStickyOrderedBroadcast(intent, broadcastreceiver, handler, i, s, bundle);
    }

    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s, Bundle bundle)
    {
        mBase.sendStickyOrderedBroadcastAsUser(intent, userhandle, broadcastreceiver, handler, i, s, bundle);
    }

    public void setAutofillClient(android.view.autofill.AutofillManager.AutofillClient autofillclient)
    {
        mBase.setAutofillClient(autofillclient);
    }

    public void setTheme(int i)
    {
        mBase.setTheme(i);
    }

    public void setWallpaper(Bitmap bitmap)
        throws IOException
    {
        mBase.setWallpaper(bitmap);
    }

    public void setWallpaper(InputStream inputstream)
        throws IOException
    {
        mBase.setWallpaper(inputstream);
    }

    public void startActivities(Intent aintent[])
    {
        mBase.startActivities(aintent);
    }

    public void startActivities(Intent aintent[], Bundle bundle)
    {
        mBase.startActivities(aintent, bundle);
    }

    public void startActivitiesAsUser(Intent aintent[], Bundle bundle, UserHandle userhandle)
    {
        mBase.startActivitiesAsUser(aintent, bundle, userhandle);
    }

    public void startActivity(Intent intent)
    {
        mBase.startActivity(intent);
    }

    public void startActivity(Intent intent, Bundle bundle)
    {
        mBase.startActivity(intent, bundle);
    }

    public void startActivityAsUser(Intent intent, Bundle bundle, UserHandle userhandle)
    {
        mBase.startActivityAsUser(intent, bundle, userhandle);
    }

    public void startActivityAsUser(Intent intent, UserHandle userhandle)
    {
        mBase.startActivityAsUser(intent, userhandle);
    }

    public void startActivityForResult(String s, Intent intent, int i, Bundle bundle)
    {
        mBase.startActivityForResult(s, intent, i, bundle);
    }

    public ComponentName startForegroundService(Intent intent)
    {
        return mBase.startForegroundService(intent);
    }

    public ComponentName startForegroundServiceAsUser(Intent intent, UserHandle userhandle)
    {
        return mBase.startForegroundServiceAsUser(intent, userhandle);
    }

    public boolean startInstrumentation(ComponentName componentname, String s, Bundle bundle)
    {
        return mBase.startInstrumentation(componentname, s, bundle);
    }

    public void startIntentSender(IntentSender intentsender, Intent intent, int i, int j, int k)
        throws IntentSender.SendIntentException
    {
        mBase.startIntentSender(intentsender, intent, i, j, k);
    }

    public void startIntentSender(IntentSender intentsender, Intent intent, int i, int j, int k, Bundle bundle)
        throws IntentSender.SendIntentException
    {
        mBase.startIntentSender(intentsender, intent, i, j, k, bundle);
    }

    public ComponentName startService(Intent intent)
    {
        return mBase.startService(intent);
    }

    public ComponentName startServiceAsUser(Intent intent, UserHandle userhandle)
    {
        return mBase.startServiceAsUser(intent, userhandle);
    }

    public boolean stopService(Intent intent)
    {
        return mBase.stopService(intent);
    }

    public boolean stopServiceAsUser(Intent intent, UserHandle userhandle)
    {
        return mBase.stopServiceAsUser(intent, userhandle);
    }

    public void unbindService(ServiceConnection serviceconnection)
    {
        mBase.unbindService(serviceconnection);
    }

    public void unregisterReceiver(BroadcastReceiver broadcastreceiver)
    {
        mBase.unregisterReceiver(broadcastreceiver);
    }

    public void updateDisplay(int i)
    {
        mBase.updateDisplay(i);
    }

    Context mBase;
}
