// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.os.*;
import android.util.*;
import com.android.internal.app.*;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            Activity

public final class VoiceInteractor
{
    public static class AbortVoiceRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mPrompt=");
            printwriter.println(mPrompt);
            if(mExtras != null)
            {
                printwriter.print(s);
                printwriter.print("mExtras=");
                printwriter.println(mExtras);
            }
        }

        String getRequestTypeName()
        {
            return "AbortVoice";
        }

        public void onAbortResult(Bundle bundle)
        {
        }

        IVoiceInteractorRequest submit(IVoiceInteractor ivoiceinteractor, String s, IVoiceInteractorCallback ivoiceinteractorcallback)
            throws RemoteException
        {
            return ivoiceinteractor.startAbortVoice(s, ivoiceinteractorcallback, mPrompt, mExtras);
        }

        final Bundle mExtras;
        final Prompt mPrompt;

        public AbortVoiceRequest(Prompt prompt, Bundle bundle)
        {
            mPrompt = prompt;
            mExtras = bundle;
        }

        public AbortVoiceRequest(CharSequence charsequence, Bundle bundle)
        {
            Prompt prompt = null;
            super();
            if(charsequence != null)
                prompt = new Prompt(charsequence);
            mPrompt = prompt;
            mExtras = bundle;
        }
    }

    public static class CommandRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mCommand=");
            printwriter.println(mCommand);
            if(mArgs != null)
            {
                printwriter.print(s);
                printwriter.print("mArgs=");
                printwriter.println(mArgs);
            }
        }

        String getRequestTypeName()
        {
            return "Command";
        }

        public void onCommandResult(boolean flag, Bundle bundle)
        {
        }

        IVoiceInteractorRequest submit(IVoiceInteractor ivoiceinteractor, String s, IVoiceInteractorCallback ivoiceinteractorcallback)
            throws RemoteException
        {
            return ivoiceinteractor.startCommand(s, ivoiceinteractorcallback, mCommand, mArgs);
        }

        final Bundle mArgs;
        final String mCommand;

        public CommandRequest(String s, Bundle bundle)
        {
            mCommand = s;
            mArgs = bundle;
        }
    }

    public static class CompleteVoiceRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mPrompt=");
            printwriter.println(mPrompt);
            if(mExtras != null)
            {
                printwriter.print(s);
                printwriter.print("mExtras=");
                printwriter.println(mExtras);
            }
        }

        String getRequestTypeName()
        {
            return "CompleteVoice";
        }

        public void onCompleteResult(Bundle bundle)
        {
        }

        IVoiceInteractorRequest submit(IVoiceInteractor ivoiceinteractor, String s, IVoiceInteractorCallback ivoiceinteractorcallback)
            throws RemoteException
        {
            return ivoiceinteractor.startCompleteVoice(s, ivoiceinteractorcallback, mPrompt, mExtras);
        }

        final Bundle mExtras;
        final Prompt mPrompt;

        public CompleteVoiceRequest(Prompt prompt, Bundle bundle)
        {
            mPrompt = prompt;
            mExtras = bundle;
        }

        public CompleteVoiceRequest(CharSequence charsequence, Bundle bundle)
        {
            Prompt prompt = null;
            super();
            if(charsequence != null)
                prompt = new Prompt(charsequence);
            mPrompt = prompt;
            mExtras = bundle;
        }
    }

    public static class ConfirmationRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mPrompt=");
            printwriter.println(mPrompt);
            if(mExtras != null)
            {
                printwriter.print(s);
                printwriter.print("mExtras=");
                printwriter.println(mExtras);
            }
        }

        String getRequestTypeName()
        {
            return "Confirmation";
        }

        public void onConfirmationResult(boolean flag, Bundle bundle)
        {
        }

        IVoiceInteractorRequest submit(IVoiceInteractor ivoiceinteractor, String s, IVoiceInteractorCallback ivoiceinteractorcallback)
            throws RemoteException
        {
            return ivoiceinteractor.startConfirmation(s, ivoiceinteractorcallback, mPrompt, mExtras);
        }

        final Bundle mExtras;
        final Prompt mPrompt;

        public ConfirmationRequest(Prompt prompt, Bundle bundle)
        {
            mPrompt = prompt;
            mExtras = bundle;
        }

        public ConfirmationRequest(CharSequence charsequence, Bundle bundle)
        {
            Prompt prompt = null;
            super();
            if(charsequence != null)
                prompt = new Prompt(charsequence);
            mPrompt = prompt;
            mExtras = bundle;
        }
    }

    public static class PickOptionRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mPrompt=");
            printwriter.println(mPrompt);
            if(mOptions != null)
            {
                printwriter.print(s);
                printwriter.println("Options:");
                for(int i = 0; i < mOptions.length; i++)
                {
                    filedescriptor = mOptions[i];
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(i);
                    printwriter.println(":");
                    printwriter.print(s);
                    printwriter.print("    mLabel=");
                    printwriter.println(((Option) (filedescriptor)).mLabel);
                    printwriter.print(s);
                    printwriter.print("    mIndex=");
                    printwriter.println(((Option) (filedescriptor)).mIndex);
                    if(((Option) (filedescriptor)).mSynonyms != null && ((Option) (filedescriptor)).mSynonyms.size() > 0)
                    {
                        printwriter.print(s);
                        printwriter.println("    Synonyms:");
                        for(int j = 0; j < ((Option) (filedescriptor)).mSynonyms.size(); j++)
                        {
                            printwriter.print(s);
                            printwriter.print("      #");
                            printwriter.print(j);
                            printwriter.print(": ");
                            printwriter.println(((Option) (filedescriptor)).mSynonyms.get(j));
                        }

                    }
                    if(((Option) (filedescriptor)).mExtras != null)
                    {
                        printwriter.print(s);
                        printwriter.print("    mExtras=");
                        printwriter.println(((Option) (filedescriptor)).mExtras);
                    }
                }

            }
            if(mExtras != null)
            {
                printwriter.print(s);
                printwriter.print("mExtras=");
                printwriter.println(mExtras);
            }
        }

        String getRequestTypeName()
        {
            return "PickOption";
        }

        public void onPickOptionResult(boolean flag, Option aoption[], Bundle bundle)
        {
        }

        IVoiceInteractorRequest submit(IVoiceInteractor ivoiceinteractor, String s, IVoiceInteractorCallback ivoiceinteractorcallback)
            throws RemoteException
        {
            return ivoiceinteractor.startPickOption(s, ivoiceinteractorcallback, mPrompt, mOptions, mExtras);
        }

        final Bundle mExtras;
        final Option mOptions[];
        final Prompt mPrompt;

        public PickOptionRequest(Prompt prompt, Option aoption[], Bundle bundle)
        {
            mPrompt = prompt;
            mOptions = aoption;
            mExtras = bundle;
        }

        public PickOptionRequest(CharSequence charsequence, Option aoption[], Bundle bundle)
        {
            Prompt prompt = null;
            super();
            if(charsequence != null)
                prompt = new Prompt(charsequence);
            mPrompt = prompt;
            mOptions = aoption;
            mExtras = bundle;
        }
    }

    public static final class PickOptionRequest.Option
        implements Parcelable
    {

        public PickOptionRequest.Option addSynonym(CharSequence charsequence)
        {
            if(mSynonyms == null)
                mSynonyms = new ArrayList();
            mSynonyms.add(charsequence);
            return this;
        }

        public int countSynonyms()
        {
            int i;
            if(mSynonyms != null)
                i = mSynonyms.size();
            else
                i = 0;
            return i;
        }

        public int describeContents()
        {
            return 0;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public int getIndex()
        {
            return mIndex;
        }

        public CharSequence getLabel()
        {
            return mLabel;
        }

        public CharSequence getSynonymAt(int i)
        {
            CharSequence charsequence = null;
            if(mSynonyms != null)
                charsequence = (CharSequence)mSynonyms.get(i);
            return charsequence;
        }

        public void setExtras(Bundle bundle)
        {
            mExtras = bundle;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeCharSequence(mLabel);
            parcel.writeInt(mIndex);
            parcel.writeCharSequenceList(mSynonyms);
            parcel.writeBundle(mExtras);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public PickOptionRequest.Option createFromParcel(Parcel parcel)
            {
                return new PickOptionRequest.Option(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public PickOptionRequest.Option[] newArray(int i)
            {
                return new PickOptionRequest.Option[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        Bundle mExtras;
        final int mIndex;
        final CharSequence mLabel;
        ArrayList mSynonyms;


        PickOptionRequest.Option(Parcel parcel)
        {
            mLabel = parcel.readCharSequence();
            mIndex = parcel.readInt();
            mSynonyms = parcel.readCharSequenceList();
            mExtras = parcel.readBundle();
        }

        public PickOptionRequest.Option(CharSequence charsequence)
        {
            mLabel = charsequence;
            mIndex = -1;
        }

        public PickOptionRequest.Option(CharSequence charsequence, int i)
        {
            mLabel = charsequence;
            mIndex = i;
        }
    }

    public static class Prompt
        implements Parcelable
    {

        public int countVoicePrompts()
        {
            return mVoicePrompts.length;
        }

        public int describeContents()
        {
            return 0;
        }

        public CharSequence getVisualPrompt()
        {
            return mVisualPrompt;
        }

        public CharSequence getVoicePromptAt(int i)
        {
            return mVoicePrompts[i];
        }

        public String toString()
        {
            StringBuilder stringbuilder;
            stringbuilder = new StringBuilder(128);
            DebugUtils.buildShortClassTag(this, stringbuilder);
            if(mVisualPrompt == null || mVoicePrompts == null || mVoicePrompts.length != 1 || !mVisualPrompt.equals(mVoicePrompts[0])) goto _L2; else goto _L1
_L1:
            stringbuilder.append(" ");
            stringbuilder.append(mVisualPrompt);
_L4:
            stringbuilder.append('}');
            return stringbuilder.toString();
_L2:
            if(mVisualPrompt != null)
            {
                stringbuilder.append(" visual=");
                stringbuilder.append(mVisualPrompt);
            }
            if(mVoicePrompts != null)
            {
                stringbuilder.append(", voice=");
                int i = 0;
                while(i < mVoicePrompts.length) 
                {
                    if(i > 0)
                        stringbuilder.append(" | ");
                    stringbuilder.append(mVoicePrompts[i]);
                    i++;
                }
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeCharSequenceArray(mVoicePrompts);
            parcel.writeCharSequence(mVisualPrompt);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Prompt createFromParcel(Parcel parcel)
            {
                return new Prompt(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Prompt[] newArray(int i)
            {
                return new Prompt[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final CharSequence mVisualPrompt;
        private final CharSequence mVoicePrompts[];


        Prompt(Parcel parcel)
        {
            mVoicePrompts = parcel.readCharSequenceArray();
            mVisualPrompt = parcel.readCharSequence();
        }

        public Prompt(CharSequence charsequence)
        {
            mVoicePrompts = (new CharSequence[] {
                charsequence
            });
            mVisualPrompt = charsequence;
        }

        public Prompt(CharSequence acharsequence[], CharSequence charsequence)
        {
            if(acharsequence == null)
                throw new NullPointerException("voicePrompts must not be null");
            if(acharsequence.length == 0)
                throw new IllegalArgumentException("voicePrompts must not be empty");
            if(charsequence == null)
            {
                throw new NullPointerException("visualPrompt must not be null");
            } else
            {
                mVoicePrompts = acharsequence;
                mVisualPrompt = charsequence;
                return;
            }
        }
    }

    public static abstract class Request
    {

        public void cancel()
        {
            if(mRequestInterface == null)
                throw new IllegalStateException((new StringBuilder()).append("Request ").append(this).append(" is no longer active").toString());
            mRequestInterface.cancel();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.w("VoiceInteractor", "Voice interactor has died", remoteexception);
              goto _L1
        }

        void clear()
        {
            mRequestInterface = null;
            mContext = null;
            mActivity = null;
            mName = null;
        }

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            printwriter.print(s);
            printwriter.print("mRequestInterface=");
            printwriter.println(mRequestInterface.asBinder());
            printwriter.print(s);
            printwriter.print("mActivity=");
            printwriter.println(mActivity);
            printwriter.print(s);
            printwriter.print("mName=");
            printwriter.println(mName);
        }

        public Activity getActivity()
        {
            return mActivity;
        }

        public Context getContext()
        {
            return mContext;
        }

        public String getName()
        {
            return mName;
        }

        String getRequestTypeName()
        {
            return "Request";
        }

        public void onAttached(Activity activity)
        {
        }

        public void onCancel()
        {
        }

        public void onDetached()
        {
        }

        abstract IVoiceInteractorRequest submit(IVoiceInteractor ivoiceinteractor, String s, IVoiceInteractorCallback ivoiceinteractorcallback)
            throws RemoteException;

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            DebugUtils.buildShortClassTag(this, stringbuilder);
            stringbuilder.append(" ");
            stringbuilder.append(getRequestTypeName());
            stringbuilder.append(" name=");
            stringbuilder.append(mName);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        Activity mActivity;
        Context mContext;
        String mName;
        IVoiceInteractorRequest mRequestInterface;

        Request()
        {
        }
    }


    VoiceInteractor(IVoiceInteractor ivoiceinteractor, Context context, Activity activity, Looper looper)
    {
        mInteractor = ivoiceinteractor;
        mContext = context;
        mActivity = activity;
        mHandlerCaller = new HandlerCaller(context, looper, mHandlerCallerCallback, true);
    }

    private ArrayList makeRequestList()
    {
        int i = mActiveRequests.size();
        if(i < 1)
            return null;
        ArrayList arraylist = new ArrayList(i);
        for(int j = 0; j < i; j++)
            arraylist.add((Request)mActiveRequests.valueAt(j));

        return arraylist;
    }

    void attachActivity(Activity activity)
    {
        mRetaining = false;
        if(mActivity == activity)
            return;
        mContext = activity;
        mActivity = activity;
        ArrayList arraylist = makeRequestList();
        if(arraylist != null)
        {
            for(int i = 0; i < arraylist.size(); i++)
            {
                Request request = (Request)arraylist.get(i);
                request.mContext = activity;
                request.mActivity = activity;
                request.onAttached(activity);
            }

        }
    }

    void detachActivity()
    {
        ArrayList arraylist = makeRequestList();
        if(arraylist != null)
        {
            for(int i = 0; i < arraylist.size(); i++)
            {
                Request request = (Request)arraylist.get(i);
                request.onDetached();
                request.mActivity = null;
                request.mContext = null;
            }

        }
        if(!mRetaining)
        {
            ArrayList arraylist1 = makeRequestList();
            if(arraylist1 != null)
            {
                for(int j = 0; j < arraylist1.size(); j++)
                    ((Request)arraylist1.get(j)).cancel();

            }
            mActiveRequests.clear();
        }
        mContext = null;
        mActivity = null;
    }

    void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        String s1 = (new StringBuilder()).append(s).append("    ").toString();
        if(mActiveRequests.size() > 0)
        {
            printwriter.print(s);
            printwriter.println("Active voice requests:");
            for(int i = 0; i < mActiveRequests.size(); i++)
            {
                Request request = (Request)mActiveRequests.valueAt(i);
                printwriter.print(s);
                printwriter.print("  #");
                printwriter.print(i);
                printwriter.print(": ");
                printwriter.println(request);
                request.dump(s1, filedescriptor, printwriter, as);
            }

        }
        printwriter.print(s);
        printwriter.println("VoiceInteractor misc state:");
        printwriter.print(s);
        printwriter.print("  mInteractor=");
        printwriter.println(mInteractor.asBinder());
        printwriter.print(s);
        printwriter.print("  mActivity=");
        printwriter.println(mActivity);
    }

    public Request getActiveRequest(String s)
    {
        ArrayMap arraymap = mActiveRequests;
        arraymap;
        JVM INSTR monitorenter ;
        int i = mActiveRequests.size();
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        Request request;
        request = (Request)mActiveRequests.valueAt(j);
        if(s == request.getName())
            break MISSING_BLOCK_LABEL_67;
        if(s == null)
            continue; /* Loop/switch isn't completed */
        boolean flag = s.equals(request.getName());
        if(!flag)
            continue; /* Loop/switch isn't completed */
        arraymap;
        JVM INSTR monitorexit ;
        return request;
          goto _L3
_L2:
        return null;
        s;
        throw s;
    }

    public Request[] getActiveRequests()
    {
        ArrayMap arraymap = mActiveRequests;
        arraymap;
        JVM INSTR monitorenter ;
        int i = mActiveRequests.size();
        if(i > 0)
            break MISSING_BLOCK_LABEL_27;
        Request arequest[] = NO_REQUESTS;
        arraymap;
        JVM INSTR monitorexit ;
        return arequest;
        arequest = new Request[i];
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        arequest[j] = (Request)mActiveRequests.valueAt(j);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return arequest;
        Exception exception;
        exception;
        throw exception;
    }

    Request pullRequest(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag)
    {
        ArrayMap arraymap = mActiveRequests;
        arraymap;
        JVM INSTR monitorenter ;
        Request request = (Request)mActiveRequests.get(ivoiceinteractorrequest.asBinder());
        if(request == null || !flag)
            break MISSING_BLOCK_LABEL_48;
        mActiveRequests.remove(ivoiceinteractorrequest.asBinder());
        arraymap;
        JVM INSTR monitorexit ;
        return request;
        ivoiceinteractorrequest;
        throw ivoiceinteractorrequest;
    }

    void retainInstance()
    {
        mRetaining = true;
    }

    public boolean submitRequest(Request request)
    {
        return submitRequest(request, null);
    }

    public boolean submitRequest(Request request, String s)
    {
        try
        {
            if(request.mRequestInterface != null)
            {
                s = JVM INSTR new #241 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #159 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                s.IllegalStateException(stringbuilder.append("Given ").append(request).append(" is already active").toString());
                throw s;
            }
        }
        // Misplaced declaration of an exception variable
        catch(Request request)
        {
            Log.w("VoiceInteractor", "Remove voice interactor service died", request);
            return false;
        }
        IVoiceInteractorRequest ivoiceinteractorrequest;
        ivoiceinteractorrequest = request.submit(mInteractor, mContext.getOpPackageName(), mCallback);
        request.mRequestInterface = ivoiceinteractorrequest;
        request.mContext = mContext;
        request.mActivity = mActivity;
        request.mName = s;
        s = mActiveRequests;
        s;
        JVM INSTR monitorenter ;
        mActiveRequests.put(ivoiceinteractorrequest.asBinder(), request);
        s;
        JVM INSTR monitorexit ;
        return true;
        request;
        s;
        JVM INSTR monitorexit ;
        throw request;
    }

    public boolean[] supportsCommands(String as[])
    {
        try
        {
            as = mInteractor.supportsCommands(mContext.getOpPackageName(), as);
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw new RuntimeException("Voice interactor has died", as);
        }
        return as;
    }

    static final boolean DEBUG = false;
    static final int MSG_ABORT_VOICE_RESULT = 4;
    static final int MSG_CANCEL_RESULT = 6;
    static final int MSG_COMMAND_RESULT = 5;
    static final int MSG_COMPLETE_VOICE_RESULT = 3;
    static final int MSG_CONFIRMATION_RESULT = 1;
    static final int MSG_PICK_OPTION_RESULT = 2;
    static final Request NO_REQUESTS[] = new Request[0];
    static final String TAG = "VoiceInteractor";
    final ArrayMap mActiveRequests = new ArrayMap();
    Activity mActivity;
    final com.android.internal.app.IVoiceInteractorCallback.Stub mCallback = new com.android.internal.app.IVoiceInteractorCallback.Stub() {

        public void deliverAbortVoiceResult(IVoiceInteractorRequest ivoiceinteractorrequest, Bundle bundle)
        {
            mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageOO(4, ivoiceinteractorrequest, bundle));
        }

        public void deliverCancel(IVoiceInteractorRequest ivoiceinteractorrequest)
            throws RemoteException
        {
            mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageOO(6, ivoiceinteractorrequest, null));
        }

        public void deliverCommandResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, Bundle bundle)
        {
            HandlerCaller handlercaller = mHandlerCaller;
            HandlerCaller handlercaller1 = mHandlerCaller;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            handlercaller.sendMessage(handlercaller1.obtainMessageIOO(5, i, ivoiceinteractorrequest, bundle));
        }

        public void deliverCompleteVoiceResult(IVoiceInteractorRequest ivoiceinteractorrequest, Bundle bundle)
        {
            mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageOO(3, ivoiceinteractorrequest, bundle));
        }

        public void deliverConfirmationResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, Bundle bundle)
        {
            HandlerCaller handlercaller = mHandlerCaller;
            HandlerCaller handlercaller1 = mHandlerCaller;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            handlercaller.sendMessage(handlercaller1.obtainMessageIOO(1, i, ivoiceinteractorrequest, bundle));
        }

        public void deliverPickOptionResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, PickOptionRequest.Option aoption[], Bundle bundle)
        {
            HandlerCaller handlercaller = mHandlerCaller;
            HandlerCaller handlercaller1 = mHandlerCaller;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            handlercaller.sendMessage(handlercaller1.obtainMessageIOOO(2, i, ivoiceinteractorrequest, aoption, bundle));
        }

        final VoiceInteractor this$0;

            
            {
                this$0 = VoiceInteractor.this;
                super();
            }
    }
;
    Context mContext;
    final HandlerCaller mHandlerCaller;
    final com.android.internal.os.HandlerCaller.Callback mHandlerCallerCallback = new com.android.internal.os.HandlerCaller.Callback() {

        public void executeMessage(Message message)
        {
            boolean flag;
            boolean flag1;
            SomeArgs someargs;
            flag = false;
            flag1 = false;
            someargs = (SomeArgs)message.obj;
            message.what;
            JVM INSTR tableswitch 1 6: default 56
        //                       1 57
        //                       2 118
        //                       3 188
        //                       4 231
        //                       5 274
        //                       6 353;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
            return;
_L2:
            Request request = pullRequest((IVoiceInteractorRequest)someargs.arg1, true);
            if(request != null)
            {
                ConfirmationRequest confirmationrequest = (ConfirmationRequest)request;
                if(message.arg1 != 0)
                    flag1 = true;
                confirmationrequest.onConfirmationResult(flag1, (Bundle)someargs.arg2);
                request.clear();
            }
            continue; /* Loop/switch isn't completed */
_L3:
            boolean flag2;
            if(message.arg1 != 0)
                flag2 = true;
            else
                flag2 = false;
            message = pullRequest((IVoiceInteractorRequest)someargs.arg1, flag2);
            if(message != null)
            {
                ((PickOptionRequest)message).onPickOptionResult(flag2, (PickOptionRequest.Option[])someargs.arg2, (Bundle)someargs.arg3);
                if(flag2)
                    message.clear();
            }
            continue; /* Loop/switch isn't completed */
_L4:
            message = pullRequest((IVoiceInteractorRequest)someargs.arg1, true);
            if(message != null)
            {
                ((CompleteVoiceRequest)message).onCompleteResult((Bundle)someargs.arg2);
                message.clear();
            }
            continue; /* Loop/switch isn't completed */
_L5:
            message = pullRequest((IVoiceInteractorRequest)someargs.arg1, true);
            if(message != null)
            {
                ((AbortVoiceRequest)message).onAbortResult((Bundle)someargs.arg2);
                message.clear();
            }
            continue; /* Loop/switch isn't completed */
_L6:
            boolean flag3;
            Request request1;
            if(message.arg1 != 0)
                flag3 = true;
            else
                flag3 = false;
            request1 = pullRequest((IVoiceInteractorRequest)someargs.arg1, flag3);
            if(request1 != null)
            {
                CommandRequest commandrequest = (CommandRequest)request1;
                if(message.arg1 != 0)
                    flag = true;
                commandrequest.onCommandResult(flag, (Bundle)someargs.arg2);
                if(flag3)
                    request1.clear();
            }
            continue; /* Loop/switch isn't completed */
_L7:
            message = pullRequest((IVoiceInteractorRequest)someargs.arg1, true);
            if(message != null)
            {
                message.onCancel();
                message.clear();
            }
            if(true) goto _L1; else goto _L8
_L8:
        }

        final VoiceInteractor this$0;

            
            {
                this$0 = VoiceInteractor.this;
                super();
            }
    }
;
    final IVoiceInteractor mInteractor;
    boolean mRetaining;

}
