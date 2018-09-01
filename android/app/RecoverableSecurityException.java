// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Icon;
import android.os.*;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.app:
//            RemoteAction, PendingIntent, Activity, FragmentManager, 
//            FragmentTransaction, NotificationManager, NotificationChannel, DialogFragment, 
//            Dialog

public final class RecoverableSecurityException extends SecurityException
    implements Parcelable
{
    public static class LocalDialog extends DialogFragment
    {

        static void lambda$_2D_android_app_RecoverableSecurityException$LocalDialog_8805(RecoverableSecurityException recoverablesecurityexception, DialogInterface dialoginterface, int i)
        {
            RecoverableSecurityException._2D_get0(recoverablesecurityexception).getActionIntent().send();
_L2:
            return;
            recoverablesecurityexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            bundle = (RecoverableSecurityException)getArguments().getParcelable("RecoverableSecurityException");
            return (new AlertDialog.Builder(getActivity())).setMessage(RecoverableSecurityException._2D_get1(bundle)).setPositiveButton(RecoverableSecurityException._2D_get0(bundle).getTitle(), new _.Lambda.CTFS8_1U0ymoTt8ccmb1hIh486s(bundle)).setNegativeButton(0x1040000, null).create();
        }

        public LocalDialog()
        {
        }
    }


    static RemoteAction _2D_get0(RecoverableSecurityException recoverablesecurityexception)
    {
        return recoverablesecurityexception.mUserAction;
    }

    static CharSequence _2D_get1(RecoverableSecurityException recoverablesecurityexception)
    {
        return recoverablesecurityexception.mUserMessage;
    }

    public RecoverableSecurityException(Parcel parcel)
    {
        this(((Throwable) (new SecurityException(parcel.readString()))), parcel.readCharSequence(), (RemoteAction)RemoteAction.CREATOR.createFromParcel(parcel));
    }

    public RecoverableSecurityException(Throwable throwable, CharSequence charsequence, RemoteAction remoteaction)
    {
        super(throwable.getMessage());
        mUserMessage = (CharSequence)Preconditions.checkNotNull(charsequence);
        mUserAction = (RemoteAction)Preconditions.checkNotNull(remoteaction);
    }

    public RecoverableSecurityException(Throwable throwable, CharSequence charsequence, CharSequence charsequence1, PendingIntent pendingintent)
    {
        this(throwable, charsequence, new RemoteAction(Icon.createWithResource("android", 0x10804c8), charsequence1, charsequence1, pendingintent));
    }

    public int describeContents()
    {
        return 0;
    }

    public RemoteAction getUserAction()
    {
        return mUserAction;
    }

    public CharSequence getUserMessage()
    {
        return mUserMessage;
    }

    public void showAsDialog(Activity activity)
    {
        LocalDialog localdialog = new LocalDialog();
        Object obj = new Bundle();
        ((Bundle) (obj)).putParcelable("RecoverableSecurityException", this);
        localdialog.setArguments(((Bundle) (obj)));
        obj = (new StringBuilder()).append("RecoverableSecurityException_").append(mUserAction.getActionIntent().getCreatorUid()).toString();
        Object obj1 = activity.getFragmentManager();
        activity = ((FragmentManager) (obj1)).beginTransaction();
        obj1 = ((FragmentManager) (obj1)).findFragmentByTag(((String) (obj)));
        if(obj1 != null)
            activity.remove(((Fragment) (obj1)));
        activity.add(localdialog, ((String) (obj)));
        activity.commitAllowingStateLoss();
    }

    public void showAsNotification(Context context)
    {
        NotificationManager notificationmanager = (NotificationManager)context.getSystemService(android/app/NotificationManager);
        String s = (new StringBuilder()).append("RecoverableSecurityException_").append(mUserAction.getActionIntent().getCreatorUid()).toString();
        notificationmanager.createNotificationChannel(new NotificationChannel(s, "RecoverableSecurityException", 3));
        showAsNotification(context, s);
    }

    public void showAsNotification(Context context, String s)
    {
        NotificationManager notificationmanager = (NotificationManager)context.getSystemService(android/app/NotificationManager);
        context = (new Notification.Builder(context, s)).setSmallIcon(0x10804c5).setContentTitle(mUserAction.getTitle()).setContentText(mUserMessage).setContentIntent(mUserAction.getActionIntent()).setCategory("err");
        notificationmanager.notify("RecoverableSecurityException", mUserAction.getActionIntent().getCreatorUid(), context.build());
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getMessage());
        parcel.writeCharSequence(mUserMessage);
        mUserAction.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RecoverableSecurityException createFromParcel(Parcel parcel)
        {
            return new RecoverableSecurityException(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RecoverableSecurityException[] newArray(int i)
        {
            return new RecoverableSecurityException[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "RecoverableSecurityException";
    private final RemoteAction mUserAction;
    private final CharSequence mUserMessage;

}
