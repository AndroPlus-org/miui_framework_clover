// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import java.util.List;

// Referenced classes of package android.speech.tts:
//            ITextToSpeechCallback, Voice

public interface ITextToSpeechService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITextToSpeechService
    {

        public static ITextToSpeechService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.speech.tts.ITextToSpeechService");
            if(iinterface != null && (iinterface instanceof ITextToSpeechService))
                return (ITextToSpeechService)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.speech.tts.ITextToSpeechService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                IBinder ibinder = parcel.readStrongBinder();
                CharSequence charsequence;
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    charsequence = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                i = speak(ibinder, charsequence, i, bundle1, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                IBinder ibinder2 = parcel.readStrongBinder();
                Bundle bundle;
                CharSequence charsequence1;
                ParcelFileDescriptor parcelfiledescriptor;
                if(parcel.readInt() != 0)
                    charsequence1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    charsequence1 = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                i = synthesizeToFileDescriptor(ibinder2, charsequence1, parcelfiledescriptor, bundle, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                IBinder ibinder1 = parcel.readStrongBinder();
                Uri uri;
                Bundle bundle2;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                i = playAudio(ibinder1, uri, i, bundle2, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                i = playSilence(parcel.readStrongBinder(), parcel.readLong(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                boolean flag = isSpeaking();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                i = stop(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                parcel = getLanguage();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                parcel = getClientDefaultLanguage();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                i = isLanguageAvailable(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                parcel = getFeaturesForLanguage(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                i = loadLanguage(parcel.readStrongBinder(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                setCallback(parcel.readStrongBinder(), ITextToSpeechCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                parcel = getVoices();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                i = loadVoice(parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechService");
                parcel = getDefaultVoiceNameFor(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.speech.tts.ITextToSpeechService";
        static final int TRANSACTION_getClientDefaultLanguage = 8;
        static final int TRANSACTION_getDefaultVoiceNameFor = 15;
        static final int TRANSACTION_getFeaturesForLanguage = 10;
        static final int TRANSACTION_getLanguage = 7;
        static final int TRANSACTION_getVoices = 13;
        static final int TRANSACTION_isLanguageAvailable = 9;
        static final int TRANSACTION_isSpeaking = 5;
        static final int TRANSACTION_loadLanguage = 11;
        static final int TRANSACTION_loadVoice = 14;
        static final int TRANSACTION_playAudio = 3;
        static final int TRANSACTION_playSilence = 4;
        static final int TRANSACTION_setCallback = 12;
        static final int TRANSACTION_speak = 1;
        static final int TRANSACTION_stop = 6;
        static final int TRANSACTION_synthesizeToFileDescriptor = 2;

        public Stub()
        {
            attachInterface(this, "android.speech.tts.ITextToSpeechService");
        }
    }

    private static class Stub.Proxy
        implements ITextToSpeechService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String[] getClientDefaultLanguage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDefaultVoiceNameFor(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String[] getFeaturesForLanguage(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.speech.tts.ITextToSpeechService";
        }

        public String[] getLanguage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getVoices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(Voice.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int isLanguageAvailable(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isSpeaking()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int loadLanguage(IBinder ibinder, String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int loadVoice(IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int playAudio(IBinder ibinder, Uri uri, int i, Bundle bundle, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeStrongBinder(ibinder);
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_133;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            parcel.writeInt(0);
              goto _L4
        }

        public int playSilence(IBinder ibinder, long l, int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setCallback(IBinder ibinder, ITextToSpeechCallback itexttospeechcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(itexttospeechcallback == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = itexttospeechcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int speak(IBinder ibinder, CharSequence charsequence, int i, Bundle bundle, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeStrongBinder(ibinder);
            if(charsequence == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L3:
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_133;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            parcel.writeInt(0);
              goto _L4
        }

        public int stop(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int synthesizeToFileDescriptor(IBinder ibinder, CharSequence charsequence, ParcelFileDescriptor parcelfiledescriptor, Bundle bundle, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechService");
            parcel.writeStrongBinder(ibinder);
            if(charsequence == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L5:
            if(parcelfiledescriptor == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L6:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_155;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L7:
            int i;
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L5
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract String[] getClientDefaultLanguage()
        throws RemoteException;

    public abstract String getDefaultVoiceNameFor(String s, String s1, String s2)
        throws RemoteException;

    public abstract String[] getFeaturesForLanguage(String s, String s1, String s2)
        throws RemoteException;

    public abstract String[] getLanguage()
        throws RemoteException;

    public abstract List getVoices()
        throws RemoteException;

    public abstract int isLanguageAvailable(String s, String s1, String s2)
        throws RemoteException;

    public abstract boolean isSpeaking()
        throws RemoteException;

    public abstract int loadLanguage(IBinder ibinder, String s, String s1, String s2)
        throws RemoteException;

    public abstract int loadVoice(IBinder ibinder, String s)
        throws RemoteException;

    public abstract int playAudio(IBinder ibinder, Uri uri, int i, Bundle bundle, String s)
        throws RemoteException;

    public abstract int playSilence(IBinder ibinder, long l, int i, String s)
        throws RemoteException;

    public abstract void setCallback(IBinder ibinder, ITextToSpeechCallback itexttospeechcallback)
        throws RemoteException;

    public abstract int speak(IBinder ibinder, CharSequence charsequence, int i, Bundle bundle, String s)
        throws RemoteException;

    public abstract int stop(IBinder ibinder)
        throws RemoteException;

    public abstract int synthesizeToFileDescriptor(IBinder ibinder, CharSequence charsequence, ParcelFileDescriptor parcelfiledescriptor, Bundle bundle, String s)
        throws RemoteException;
}
