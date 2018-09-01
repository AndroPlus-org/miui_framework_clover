// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import com.miui.translationservice.provider.TranslationResult;
import java.util.List;
import miui.widget.ProgressBar;

// Referenced classes of package android.widget:
//            TextView

class TranslationPresenter
{

    static Context _2D_get0(TranslationPresenter translationpresenter)
    {
        return translationpresenter.mContext;
    }

    public TranslationPresenter(Context context, View view)
    {
        mContext = context;
        mTranslationPanel = view;
        mScrollContainer = mTranslationPanel.findViewById(0x110c0011);
        mScrollView = mTranslationPanel.findViewById(0x110c0053);
        mTextContainer = mTranslationPanel.findViewById(0x110c0054);
        mWord = (TextView)mTranslationPanel.findViewById(0x1020014);
        mTranslations = (TextView)mTranslationPanel.findViewById(0x1020015);
        mExtraInfo = mTranslationPanel.findViewById(0x110c0055);
        mCopyright = (TextView)mTranslationPanel.findViewById(0x110c0056);
        mMore = (TextView)mTranslationPanel.findViewById(0x110c0057);
        mProgressBar = (ProgressBar)mTranslationPanel.findViewById(0x102000d);
        mMinHeight = context.getResources().getDimensionPixelSize(0x110b0031);
        mMaxHeight = context.getResources().getDimensionPixelSize(0x110b0032);
        mDisplayMetrics = context.getResources().getDisplayMetrics();
        mDefaultPaddingBottom = context.getResources().getDimensionPixelSize(0x110b0033);
        mPaddingOffset = context.getResources().getDimensionPixelSize(0x110b0034);
    }

    public void setAboveHandle(boolean flag)
    {
        mAboveHandle = flag;
    }

    public void setInProgress()
    {
        mWord.setVisibility(8);
        mTranslations.setVisibility(8);
        mExtraInfo.setVisibility(8);
        mProgressBar.setVisibility(0);
        mScrollView.setPadding(mScrollView.getPaddingLeft(), mScrollView.getPaddingTop(), mScrollView.getPaddingRight(), mDefaultPaddingBottom);
        Object obj = mScrollView.getLayoutParams();
        obj.height = mMinHeight;
        mScrollView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        obj = new Rect();
        mScrollContainer.getBackground().getPadding(((Rect) (obj)));
        int i = ((Rect) (obj)).top;
        int j = ((Rect) (obj)).bottom;
        obj = new RelativeLayout.LayoutParams(-1, mMinHeight + (i + j));
        RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams)obj;
        if(mAboveHandle)
            i = 12;
        else
            i = 10;
        layoutparams.addRule(i);
        mScrollContainer.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
    }

    public void updatePanel(final TranslationResult result)
    {
        mWord.setVisibility(0);
        mProgressBar.setVisibility(8);
        int l;
        if(result == null || result.getStatus() != 0)
        {
            mTranslations.setVisibility(0);
            int i;
            int k;
            int i1;
            int j1;
            int l1;
            int i2;
            Object obj;
            if(result == null)
            {
                mWord.setVisibility(8);
                mTranslations.setText(mContext.getString(0x1108006a));
            } else
            if(result.getStatus() == -2)
            {
                mWord.setText(result.getWordName());
                mTranslations.setText(mContext.getString(0x1108006a));
            } else
            {
                mWord.setText(result.getWordName());
                mTranslations.setText(mContext.getString(0x11080069));
            }
        } else
        {
            mWord.setText(result.getWordName());
            mTranslations.setVisibility(0);
            final String detailLink = new StringBuilder();
            List list = result.getSymbols();
            for(int k1 = 0; k1 < list.size(); k1++)
            {
                Object obj1 = (com.miui.translationservice.provider.TranslationResult.Symbol)list.get(k1);
                l = 0;
                if(!TextUtils.isEmpty(((com.miui.translationservice.provider.TranslationResult.Symbol) (obj1)).getWordSymbol()))
                {
                    detailLink.append("[").append(((com.miui.translationservice.provider.TranslationResult.Symbol) (obj1)).getWordSymbol()).append("]\r\n");
                    l = 1;
                }
                boolean flag = l;
                if(l == 0)
                {
                    flag = l;
                    if(TextUtils.isEmpty(((com.miui.translationservice.provider.TranslationResult.Symbol) (obj1)).getPhEn()) ^ true)
                    {
                        detailLink.append("[").append(((com.miui.translationservice.provider.TranslationResult.Symbol) (obj1)).getPhEn()).append("]\r\n");
                        flag = true;
                    }
                }
                if(!flag && TextUtils.isEmpty(((com.miui.translationservice.provider.TranslationResult.Symbol) (obj1)).getPhAm()) ^ true)
                    detailLink.append("[").append(((com.miui.translationservice.provider.TranslationResult.Symbol) (obj1)).getPhAm()).append("]\r\n");
                obj1 = ((com.miui.translationservice.provider.TranslationResult.Symbol) (obj1)).getParts();
                for(l = 0; l < ((List) (obj1)).size(); l++)
                {
                    Object obj2 = (com.miui.translationservice.provider.TranslationResult.Part)((List) (obj1)).get(l);
                    if(!TextUtils.isEmpty(((com.miui.translationservice.provider.TranslationResult.Part) (obj2)).getPart()))
                        detailLink.append("(").append(((com.miui.translationservice.provider.TranslationResult.Part) (obj2)).getPart()).append(") ");
                    obj2 = ((com.miui.translationservice.provider.TranslationResult.Part) (obj2)).getMeans();
                    int j = 0;
                    while(j < ((List) (obj2)).size()) 
                    {
                        detailLink.append((String)((List) (obj2)).get(j));
                        if(j == ((List) (obj2)).size() - 1 && l != ((List) (obj1)).size() - 1)
                            detailLink.append("\r\n");
                        else
                            detailLink.append("/");
                        j++;
                    }
                }

            }

            mTranslations.setText(detailLink.toString());
            detailLink = result.getCopyright();
            l = TextUtils.isEmpty(detailLink) ^ true;
            if(l != 0)
            {
                mExtraInfo.setVisibility(0);
                mCopyright.setVisibility(0);
                mCopyright.setText(detailLink);
            }
            detailLink = result.getDetailLink();
            if(TextUtils.isEmpty(detailLink) ^ true)
            {
                mExtraInfo.setVisibility(0);
                mMore.setVisibility(0);
                mMore.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        view = new Intent("android.intent.action.VIEW", Uri.parse(String.format(detailLink, new Object[] {
                            result.getWordName()
                        })));
                        TranslationPresenter._2D_get0(TranslationPresenter.this).startActivity(view);
                    }

                    final TranslationPresenter this$0;
                    final String val$detailLink;
                    final TranslationResult val$result;

            
            {
                this$0 = TranslationPresenter.this;
                detailLink = s;
                result = translationresult;
                super();
            }
                }
);
            }
            result = mExtraInfo.getLayoutParams();
            if(l != 0)
                l = ((android.view.ViewGroup.LayoutParams) (result)).height - mPaddingOffset;
            else
                l = mDefaultPaddingBottom;
            mScrollView.setPadding(mScrollView.getPaddingLeft(), mScrollView.getPaddingTop(), mScrollView.getPaddingRight(), l);
        }
        result = new Rect();
        mScrollContainer.getBackground().getPadding(result);
        i = ((Rect) (result)).left + ((Rect) (result)).right;
        k = ((Rect) (result)).top;
        l = ((Rect) (result)).bottom;
        i1 = mTranslationPanel.getMeasuredWidth();
        j1 = mTranslationPanel.getMeasuredHeight();
        l1 = mScrollView.getPaddingLeft();
        i2 = mScrollView.getPaddingRight();
        mTextContainer.measure(android.view.View.MeasureSpec.makeMeasureSpec(i1 - i - l1 - i2, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(mDisplayMetrics.heightPixels, 0));
        l1 = mTextContainer.getMeasuredHeight();
        result = mScrollView.getLayoutParams();
        result.width = i1 - i;
        result.height = Math.min(mMaxHeight, mScrollView.getPaddingTop() + l1 + mScrollView.getPaddingBottom());
        mScrollView.setLayoutParams(result);
        result = mScrollContainer.getLayoutParams();
        result.height = Math.min(mMaxHeight, mScrollView.getLayoutParams().height) + (k + l);
        obj = (RelativeLayout.LayoutParams)result;
        if(mAboveHandle)
            l = 12;
        else
            l = 10;
        ((RelativeLayout.LayoutParams) (obj)).addRule(l);
        mScrollContainer.setLayoutParams(result);
        mScrollContainer.setLeft(0);
        obj = mScrollContainer;
        if(mAboveHandle)
            l = j1 - ((android.view.ViewGroup.LayoutParams) (result)).height;
        else
            l = 0;
        ((View) (obj)).setTop(l);
    }

    private boolean mAboveHandle;
    private Context mContext;
    private TextView mCopyright;
    private int mDefaultPaddingBottom;
    private DisplayMetrics mDisplayMetrics;
    private View mExtraInfo;
    private int mMaxHeight;
    private int mMinHeight;
    private TextView mMore;
    private int mPaddingOffset;
    private ProgressBar mProgressBar;
    private View mScrollContainer;
    private View mScrollView;
    private View mTextContainer;
    private View mTranslationPanel;
    private TextView mTranslations;
    private TextView mWord;
}
