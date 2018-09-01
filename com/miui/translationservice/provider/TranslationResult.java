// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.translationservice.provider;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class TranslationResult
    implements Parcelable
{
    public static class Part
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public List getMeans()
        {
            return mMeans;
        }

        public String getPart()
        {
            return mPart;
        }

        public void setMeans(List list)
        {
            mMeans = list;
        }

        public void setPart(String s)
        {
            mPart = s;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mPart);
            parcel.writeList(mMeans);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Part createFromParcel(Parcel parcel)
            {
                return new Part(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Part[] newArray(int i)
            {
                return new Part[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private List mMeans;
        private String mPart;


        public Part()
        {
        }

        private Part(Parcel parcel)
        {
            setPart(parcel.readString());
            ArrayList arraylist = new ArrayList();
            parcel.readList(arraylist, com/miui/translationservice/provider/TranslationResult$Part.getClassLoader());
            setMeans(arraylist);
        }

        Part(Parcel parcel, Part part)
        {
            this(parcel);
        }
    }

    public static class Phrase
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public List getExplains()
        {
            return mExplains;
        }

        public String getPhrase()
        {
            return mPhrase;
        }

        public void setExplains(List list)
        {
            mExplains = list;
        }

        public void setPhrase(String s)
        {
            mPhrase = s;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mPhrase);
            parcel.writeList(mExplains);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Phrase createFromParcel(Parcel parcel)
            {
                return new Phrase(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Phrase[] newArray(int i)
            {
                return new Phrase[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private List mExplains;
        private String mPhrase;


        public Phrase()
        {
        }

        private Phrase(Parcel parcel)
        {
            setPhrase(parcel.readString());
            ArrayList arraylist = new ArrayList();
            parcel.readList(arraylist, com/miui/translationservice/provider/TranslationResult$Phrase.getClassLoader());
            setExplains(arraylist);
        }

        Phrase(Parcel parcel, Phrase phrase)
        {
            this(parcel);
        }
    }

    public static class Symbol
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public List getParts()
        {
            return mParts;
        }

        public String getPhAm()
        {
            return mPhAm;
        }

        public String getPhEn()
        {
            return mPhEn;
        }

        public String getWordSymbol()
        {
            return mWordSymbol;
        }

        public void setParts(List list)
        {
            mParts = list;
        }

        public void setPhAm(String s)
        {
            mPhAm = s;
        }

        public void setPhEn(String s)
        {
            mPhEn = s;
        }

        public void setWordSymbol(String s)
        {
            mWordSymbol = s;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mPhEn);
            parcel.writeString(mPhAm);
            parcel.writeString(mWordSymbol);
            parcel.writeList(mParts);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Symbol createFromParcel(Parcel parcel)
            {
                return new Symbol(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Symbol[] newArray(int i)
            {
                return new Symbol[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private List mParts;
        private String mPhAm;
        private String mPhEn;
        private String mWordSymbol;


        public Symbol()
        {
        }

        private Symbol(Parcel parcel)
        {
            setPhEn(parcel.readString());
            setPhAm(parcel.readString());
            setWordSymbol(parcel.readString());
            ArrayList arraylist = new ArrayList();
            parcel.readList(arraylist, com/miui/translationservice/provider/TranslationResult$Symbol.getClassLoader());
            setParts(arraylist);
        }

        Symbol(Parcel parcel, Symbol symbol)
        {
            this(parcel);
        }
    }


    public TranslationResult()
    {
    }

    private TranslationResult(Parcel parcel)
    {
        setCopyright(parcel.readString());
        setDetailLink(parcel.readString());
        setStatus(parcel.readInt());
        setWordName(parcel.readString());
        ArrayList arraylist = new ArrayList();
        parcel.readList(arraylist, com/miui/translationservice/provider/TranslationResult.getClassLoader());
        setSymbols(arraylist);
        arraylist = new ArrayList();
        parcel.readList(arraylist, com/miui/translationservice/provider/TranslationResult.getClassLoader());
        setPhrases(arraylist);
        arraylist = new ArrayList();
        parcel.readList(arraylist, com/miui/translationservice/provider/TranslationResult.getClassLoader());
        setTranslations(arraylist);
    }

    TranslationResult(Parcel parcel, TranslationResult translationresult)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getCopyright()
    {
        return mCopyright;
    }

    public String getDetailLink()
    {
        return mDetailLink;
    }

    public List getPhrases()
    {
        return mPhrases;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public List getSymbols()
    {
        return mSymbols;
    }

    public List getTranslations()
    {
        return mTranslations;
    }

    public String getWordName()
    {
        return mWordName;
    }

    public void setCopyright(String s)
    {
        mCopyright = s;
    }

    public void setDetailLink(String s)
    {
        mDetailLink = s;
    }

    public void setPhrases(List list)
    {
        mPhrases = list;
    }

    public void setStatus(int i)
    {
        mStatus = i;
    }

    public void setSymbols(List list)
    {
        mSymbols = list;
    }

    public void setTranslations(List list)
    {
        mTranslations = list;
    }

    public void setWordName(String s)
    {
        mWordName = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mCopyright);
        parcel.writeString(mDetailLink);
        parcel.writeInt(mStatus);
        parcel.writeString(mWordName);
        parcel.writeList(mSymbols);
        parcel.writeList(mPhrases);
        parcel.writeList(mTranslations);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TranslationResult createFromParcel(Parcel parcel)
        {
            return new TranslationResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TranslationResult[] newArray(int i)
        {
            return new TranslationResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int RESULT_ERROR_NETWORK = -2;
    public static final int RESULT_ERROR_UNKNOWN = -1;
    public static final int RESULT_SUCCESS = 0;
    private String mCopyright;
    private String mDetailLink;
    private List mPhrases;
    private int mStatus;
    private List mSymbols;
    private List mTranslations;
    private String mWordName;

}
