// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.NumberFormat;

// Referenced classes of package android.app:
//            AlertDialog

public class ProgressDialog extends AlertDialog
{

    static ProgressBar _2D_get0(ProgressDialog progressdialog)
    {
        return progressdialog.mProgress;
    }

    static TextView _2D_get1(ProgressDialog progressdialog)
    {
        return progressdialog.mProgressNumber;
    }

    static String _2D_get2(ProgressDialog progressdialog)
    {
        return progressdialog.mProgressNumberFormat;
    }

    static TextView _2D_get3(ProgressDialog progressdialog)
    {
        return progressdialog.mProgressPercent;
    }

    static NumberFormat _2D_get4(ProgressDialog progressdialog)
    {
        return progressdialog.mProgressPercentFormat;
    }

    public ProgressDialog(Context context)
    {
        super(context);
        mProgressStyle = 0;
        initFormats();
    }

    public ProgressDialog(Context context, int i)
    {
        super(context, i);
        mProgressStyle = 0;
        initFormats();
    }

    private void initFormats()
    {
        mProgressNumberFormat = "%1d/%2d";
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
    }

    private void onProgressChanged()
    {
        if(mProgressStyle == 1 && mViewUpdateHandler != null && mViewUpdateHandler.hasMessages(0) ^ true)
            mViewUpdateHandler.sendEmptyMessage(0);
    }

    public static ProgressDialog show(Context context, CharSequence charsequence, CharSequence charsequence1)
    {
        return show(context, charsequence, charsequence1, false);
    }

    public static ProgressDialog show(Context context, CharSequence charsequence, CharSequence charsequence1, boolean flag)
    {
        return show(context, charsequence, charsequence1, flag, false, null);
    }

    public static ProgressDialog show(Context context, CharSequence charsequence, CharSequence charsequence1, boolean flag, boolean flag1)
    {
        return show(context, charsequence, charsequence1, flag, flag1, null);
    }

    public static ProgressDialog show(Context context, CharSequence charsequence, CharSequence charsequence1, boolean flag, boolean flag1, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        context = new ProgressDialog(context);
        context.setTitle(charsequence);
        context.setMessage(charsequence1);
        context.setIndeterminate(flag);
        context.setCancelable(flag1);
        context.setOnCancelListener(oncancellistener);
        context.show();
        return context;
    }

    public int getMax()
    {
        if(mProgress != null)
            return mProgress.getMax();
        else
            return mMax;
    }

    public int getProgress()
    {
        if(mProgress != null)
            return mProgress.getProgress();
        else
            return mProgressVal;
    }

    public int getSecondaryProgress()
    {
        if(mProgress != null)
            return mProgress.getSecondaryProgress();
        else
            return mSecondaryProgressVal;
    }

    public void incrementProgressBy(int i)
    {
        if(mProgress != null)
        {
            mProgress.incrementProgressBy(i);
            onProgressChanged();
        } else
        {
            mIncrementBy = mIncrementBy + i;
        }
    }

    public void incrementSecondaryProgressBy(int i)
    {
        if(mProgress != null)
        {
            mProgress.incrementSecondaryProgressBy(i);
            onProgressChanged();
        } else
        {
            mIncrementSecondaryBy = mIncrementSecondaryBy + i;
        }
    }

    public boolean isIndeterminate()
    {
        if(mProgress != null)
            return mProgress.isIndeterminate();
        else
            return mIndeterminate;
    }

    protected void onCreate(Bundle bundle)
    {
        Object obj = LayoutInflater.from(mContext);
        TypedArray typedarray = mContext.obtainStyledAttributes(null, com.android.internal.R.styleable.AlertDialog, 0x101005d, 0);
        if(mProgressStyle == 1)
        {
            mViewUpdateHandler = new Handler() {

                public void handleMessage(Message message)
                {
                    super.handleMessage(message);
                    int i = ProgressDialog._2D_get0(ProgressDialog.this).getProgress();
                    int j = ProgressDialog._2D_get0(ProgressDialog.this).getMax();
                    if(ProgressDialog._2D_get2(ProgressDialog.this) != null)
                    {
                        message = ProgressDialog._2D_get2(ProgressDialog.this);
                        ProgressDialog._2D_get1(ProgressDialog.this).setText(String.format(message, new Object[] {
                            Integer.valueOf(i), Integer.valueOf(j)
                        }));
                    } else
                    {
                        ProgressDialog._2D_get1(ProgressDialog.this).setText("");
                    }
                    if(ProgressDialog._2D_get4(ProgressDialog.this) != null)
                    {
                        double d = (double)i / (double)j;
                        message = new SpannableString(ProgressDialog._2D_get4(ProgressDialog.this).format(d));
                        message.setSpan(new StyleSpan(1), 0, message.length(), 33);
                        ProgressDialog._2D_get3(ProgressDialog.this).setText(message);
                    } else
                    {
                        ProgressDialog._2D_get3(ProgressDialog.this).setText("");
                    }
                }

                final ProgressDialog this$0;

            
            {
                this$0 = ProgressDialog.this;
                super();
            }
            }
;
            obj = ((LayoutInflater) (obj)).inflate(typedarray.getResourceId(13, 0x109002e), null);
            mProgress = (ProgressBar)((View) (obj)).findViewById(0x102000d);
            mProgressNumber = (TextView)((View) (obj)).findViewById(0x1020396);
            mProgressPercent = (TextView)((View) (obj)).findViewById(0x1020397);
            setView(((View) (obj)));
        } else
        {
            obj = ((LayoutInflater) (obj)).inflate(typedarray.getResourceId(18, 0x10900d2), null);
            mProgress = (ProgressBar)((View) (obj)).findViewById(0x102000d);
            mMessageView = (TextView)((View) (obj)).findViewById(0x102000b);
            setView(((View) (obj)));
        }
        typedarray.recycle();
        if(mMax > 0)
            setMax(mMax);
        if(mProgressVal > 0)
            setProgress(mProgressVal);
        if(mSecondaryProgressVal > 0)
            setSecondaryProgress(mSecondaryProgressVal);
        if(mIncrementBy > 0)
            incrementProgressBy(mIncrementBy);
        if(mIncrementSecondaryBy > 0)
            incrementSecondaryProgressBy(mIncrementSecondaryBy);
        if(mProgressDrawable != null)
            setProgressDrawable(mProgressDrawable);
        if(mIndeterminateDrawable != null)
            setIndeterminateDrawable(mIndeterminateDrawable);
        if(mMessage != null)
            setMessage(mMessage);
        setIndeterminate(mIndeterminate);
        onProgressChanged();
        super.onCreate(bundle);
    }

    public void onStart()
    {
        super.onStart();
        mHasStarted = true;
    }

    protected void onStop()
    {
        super.onStop();
        mHasStarted = false;
    }

    public void setIndeterminate(boolean flag)
    {
        if(mProgress != null)
            mProgress.setIndeterminate(flag);
        else
            mIndeterminate = flag;
    }

    public void setIndeterminateDrawable(Drawable drawable)
    {
        if(mProgress != null)
            mProgress.setIndeterminateDrawable(drawable);
        else
            mIndeterminateDrawable = drawable;
    }

    public void setMax(int i)
    {
        if(mProgress != null)
        {
            mProgress.setMax(i);
            onProgressChanged();
        } else
        {
            mMax = i;
        }
    }

    public void setMessage(CharSequence charsequence)
    {
        if(mProgress != null)
        {
            if(mProgressStyle == 1)
                super.setMessage(charsequence);
            else
                mMessageView.setText(charsequence);
        } else
        {
            mMessage = charsequence;
        }
    }

    public void setProgress(int i)
    {
        if(mHasStarted)
        {
            mProgress.setProgress(i);
            onProgressChanged();
        } else
        {
            mProgressVal = i;
        }
    }

    public void setProgressDrawable(Drawable drawable)
    {
        if(mProgress != null)
            mProgress.setProgressDrawable(drawable);
        else
            mProgressDrawable = drawable;
    }

    public void setProgressNumberFormat(String s)
    {
        mProgressNumberFormat = s;
        onProgressChanged();
    }

    public void setProgressPercentFormat(NumberFormat numberformat)
    {
        mProgressPercentFormat = numberformat;
        onProgressChanged();
    }

    public void setProgressStyle(int i)
    {
        mProgressStyle = i;
    }

    public void setSecondaryProgress(int i)
    {
        if(mProgress != null)
        {
            mProgress.setSecondaryProgress(i);
            onProgressChanged();
        } else
        {
            mSecondaryProgressVal = i;
        }
    }

    public static final int STYLE_HORIZONTAL = 1;
    public static final int STYLE_SPINNER = 0;
    private boolean mHasStarted;
    private int mIncrementBy;
    private int mIncrementSecondaryBy;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private int mMax;
    private CharSequence mMessage;
    private TextView mMessageView;
    private ProgressBar mProgress;
    private Drawable mProgressDrawable;
    private TextView mProgressNumber;
    private String mProgressNumberFormat;
    private TextView mProgressPercent;
    private NumberFormat mProgressPercentFormat;
    private int mProgressStyle;
    private int mProgressVal;
    private int mSecondaryProgressVal;
    private Handler mViewUpdateHandler;
}
