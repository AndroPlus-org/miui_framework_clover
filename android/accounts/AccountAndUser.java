// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;


// Referenced classes of package android.accounts:
//            Account

public class AccountAndUser
{

    public AccountAndUser(Account account1, int i)
    {
        account = account1;
        userId = i;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(!(obj instanceof AccountAndUser))
            return false;
        obj = (AccountAndUser)obj;
        if(account.equals(((AccountAndUser) (obj)).account))
        {
            if(userId != ((AccountAndUser) (obj)).userId)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int hashCode()
    {
        return account.hashCode() + userId;
    }

    public String toString()
    {
        return (new StringBuilder()).append(account.toString()).append(" u").append(userId).toString();
    }

    public Account account;
    public int userId;
}
