// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;


// Referenced classes of package android.text:
//            InputFilter, Spanned, SpannableStringBuilder

public abstract class LoginFilter
    implements InputFilter
{
    public static class PasswordFilterGMail extends LoginFilter
    {

        public boolean isAllowed(char c)
        {
            if(' ' <= c && c <= '\177')
                return true;
            return '\240' <= c && c <= '\377';
        }

        public PasswordFilterGMail()
        {
            super(false);
        }

        public PasswordFilterGMail(boolean flag)
        {
            super(flag);
        }
    }

    public static class UsernameFilterGMail extends LoginFilter
    {

        public boolean isAllowed(char c)
        {
            if('0' <= c && c <= '9')
                return true;
            if('a' <= c && c <= 'z')
                return true;
            if('A' <= c && c <= 'Z')
                return true;
            return '.' == c;
        }

        public UsernameFilterGMail()
        {
            super(false);
        }

        public UsernameFilterGMail(boolean flag)
        {
            super(flag);
        }
    }

    public static class UsernameFilterGeneric extends LoginFilter
    {

        public boolean isAllowed(char c)
        {
            if('0' <= c && c <= '9')
                return true;
            if('a' <= c && c <= 'z')
                return true;
            if('A' <= c && c <= 'Z')
                return true;
            return "@_-+.".indexOf(c) != -1;
        }

        private static final String mAllowed = "@_-+.";

        public UsernameFilterGeneric()
        {
            super(false);
        }

        public UsernameFilterGeneric(boolean flag)
        {
            super(flag);
        }
    }


    LoginFilter()
    {
        mAppendInvalid = false;
    }

    LoginFilter(boolean flag)
    {
        mAppendInvalid = flag;
    }

    public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
    {
        onStart();
        for(int i1 = 0; i1 < k; i1++)
        {
            char c = spanned.charAt(i1);
            if(!isAllowed(c))
                onInvalidCharacter(c);
        }

        SpannableStringBuilder spannablestringbuilder = null;
        k = 0;
        int j1 = i;
        while(j1 < j) 
        {
            char c1 = charsequence.charAt(j1);
            if(isAllowed(c1))
            {
                k++;
            } else
            {
                if(mAppendInvalid)
                {
                    k++;
                } else
                {
                    SpannableStringBuilder spannablestringbuilder1 = spannablestringbuilder;
                    if(spannablestringbuilder == null)
                    {
                        spannablestringbuilder1 = new SpannableStringBuilder(charsequence, i, j);
                        k = j1 - i;
                    }
                    spannablestringbuilder1.delete(k, k + 1);
                    spannablestringbuilder = spannablestringbuilder1;
                }
                onInvalidCharacter(c1);
            }
            j1++;
        }
        for(i = l; i < spanned.length(); i++)
        {
            char c2 = spanned.charAt(i);
            if(!isAllowed(c2))
                onInvalidCharacter(c2);
        }

        onStop();
        return spannablestringbuilder;
    }

    public abstract boolean isAllowed(char c);

    public void onInvalidCharacter(char c)
    {
    }

    public void onStart()
    {
    }

    public void onStop()
    {
    }

    private boolean mAppendInvalid;
}
