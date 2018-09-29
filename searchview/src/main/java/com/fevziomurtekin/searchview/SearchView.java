package com.fevziomurtekin.searchview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


@SuppressLint("AppCompatCustomView")
public class SearchView extends RelativeLayout implements View.OnClickListener, TextView.OnEditorActionListener {

    private int searchView_animationTime; /*Time for animation.*/

    private String searchView_hint; /*Hint text for Edittext.*/

    private float searchView_textSize; /*Edittext textSize*/

    private float result_textSize;  /*Textview textSize*/

    private int searchView_textColor; /*serchview textcolor.*/

    private int result_textColor; /* */

    private TypedArray typedArray;

    private GradientDrawable bg , cursorbg;

    private float width,height; /*Parent screen.*/

    private boolean isAnimating = false;

    private String textWatcher; /*if called this when Text onChanged*/

    private SharedPreferences sharedPreferences;

    private Context context;

    private SharedPreferences.Editor editor;

    private EditText searchView;

    private ImageView backButton;

    private ImageView searchButton;

    private TextView textSearch;

    private boolean isFind = false;


    public SearchView(Context context) {
        super(context);
        this.context=context;
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context                = context;
        typedArray                  = context.obtainStyledAttributes(attrs, R.styleable.searchView);
        searchView_hint             = typedArray.getString(R.styleable.searchView_searchhint);
        searchView_animationTime    = typedArray.getInt(R.styleable.searchView_animationtime,250);


        typedArray.recycle();
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context                = context;
        typedArray                  = context.obtainStyledAttributes(attrs, R.styleable.searchView);
        searchView_hint             = typedArray.getString(R.styleable.searchView_searchhint);
        searchView_animationTime    = typedArray.getInt(R.styleable.searchView_animationtime,250);
        typedArray.recycle();
    }

    @SuppressLint("ResourceType")
    private void init() {
        /*Shared Prefences used.*/
        sharedPreferences   =  context.getSharedPreferences("searchview",Context.MODE_PRIVATE);
        editor              = sharedPreferences.edit();


        editor  .putBoolean("isAnimating",false).apply();/*Default*/

        /*Background created.*/
        bg = new GradientDrawable();
        bg  .setColor(this.getResources().getColor(R.color.white));

        /*Start to size RelativeLayout*/
        this.getLayoutParams().width    = (int) (width);
        this.getLayoutParams().height   = (int) (height/10);
        this.setBackgroundColor(this.getResources().getColor(R.color.white));

        backButton                          = new ImageView(this.getContext());
        this                                . addView(backButton);
        backButton.getLayoutParams().width  = (int) (width/15);
        backButton.getLayoutParams().height = (int) (height/15);
        backButton                          . setImageResource(R.drawable.back);
        backButton                          . setOnClickListener(this);
        backButton                          . setId(1);
        backButton                          . setTag("back");
        LayoutParams bckparams              = (LayoutParams) backButton.getLayoutParams();
        bckparams                           . setMarginStart((int) (width/15));
        bckparams                           . addRule(RelativeLayout.CENTER_VERTICAL);
        backButton                          . setLayoutParams(bckparams);


        searchButton                        = new ImageView(this.getContext());
        this                                . addView(searchButton);
        searchButton.getLayoutParams().width= (int) (width/15);
        searchButton.getLayoutParams().height= (int) (height/15);
        searchButton                        . setId(2);
        searchButton                        . setImageResource(R.drawable.search);
        searchButton                        . setOnClickListener(this);
        searchButton                        . setTag("search");
        LayoutParams srchparams             = (LayoutParams) searchButton.getLayoutParams();
        srchparams                          . setMarginEnd((int) (width/15));
        srchparams                          . addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        srchparams                          . addRule(RelativeLayout.CENTER_VERTICAL);
//        srchparams                          . addRule(backButton.getId(),RelativeLayout.RIGHT_OF);
        searchButton                        . setLayoutParams(srchparams);


        searchView                          = new EditText(this.getContext());
        this                                . addView(searchView);
        searchView.getLayoutParams().width  = (int) (width/15);
        searchView.getLayoutParams().height = (int) (height/15);

        searchView                          . setPadding(15,5,5,5);
        searchView                          . setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView                          . setTextSize(searchView_textSize);  /*Default text size.*/
        searchView                          . setTextColor(searchView_textColor); /*Default text color.*/
        searchView                          . setCursorVisible(false);
        searchView                          . setHint("");
        searchView                          . setHintTextColor(searchView_textColor);
        searchView                          . setShadowLayer(2f,2f,4f,getResources().getColor(R.color.grey));
        searchView                          . setVisibility(GONE);
        searchView                          . setInputType(EditorInfo.TYPE_CLASS_TEXT);
        searchView                          . setBackground(bg);
        searchView                          . addTextChangedListener((TextWatcher) context);

        searchView                          . setOnEditorActionListener(this);


        LayoutParams params                 = (LayoutParams) searchView.getLayoutParams();
        params                              . addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params                              . setMarginEnd(15);
        params                              . setMarginStart(15);
        params                              . addRule(RelativeLayout.CENTER_VERTICAL);
        params                              . addRule(searchButton.getId(),RelativeLayout.RIGHT_OF);
        searchView                          . setLayoutParams(params);

        textSearch                          = new TextView(this.getContext());
        this                                . addView(textSearch);
        textSearch                          . setId(4);
        textSearch                          . setTextSize(result_textSize);
        textSearch                          . setGravity(Gravity.CENTER);
        textSearch.getLayoutParams().width  = (int) (width/5);
        textSearch.getLayoutParams().height = (int) (height/15);
        textSearch                          . setTextColor(result_textColor);

        LayoutParams txtParams              = (LayoutParams) textSearch.getLayoutParams();
        txtParams                           . addRule(RelativeLayout.CENTER_VERTICAL);
        txtParams                           . addRule(RelativeLayout.CENTER_HORIZONTAL);
        textSearch                          . setLayoutParams(txtParams);


    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        width   = ((View)this.getParent()).getWidth();
        height  = ((View)this.getParent()).getHeight();
        init();
    }

    private void showAnimating() {
        isFind                  = false;
        final ImageView tImg    = searchButton;
        final EditText temp     = searchView;
        textSearch              .setVisibility(GONE);
        temp                    . setVisibility(VISIBLE);
        ValueAnimator animator  = ValueAnimator.ofFloat(searchButton.getX(),(backButton.getX()));
        animator   .setInterpolator(new AccelerateDecelerateInterpolator());
        animator   .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = width-((float)valueAnimator.getAnimatedValue());
                tImg.setX((int)width-value);
                tImg.getParent().requestLayout();
            }
        });

        animator   .addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                backButton.setVisibility(GONE);
                temp.setHint(searchView_hint==null?"Search":searchView_hint);
                temp.getLayoutParams().width= (int) (width-(tImg.getX()+tImg.getLayoutParams().width+width/15));
                showKeyboard(temp,context);
                temp.setFocusable(true);
                ((LayoutParams)temp.getLayoutParams()).setMarginEnd((int) (width/15));
                Log.e("seachview width","width : "+temp.getLayoutParams().width);
                editor.putBoolean("isAnimating",true).apply();
                backButton. setVisibility(GONE);
                tImg.getParent().requestLayout();

            }
        });

        animator.setDuration(searchView_animationTime);
        animator.start();
    }


    private void hideAnimating() {
        final ImageView tImg        = searchButton;
        final EditText temp         = searchView;
        temp                        . setVisibility(GONE);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(searchButton.getX(),(width-(width/15+searchButton.getLayoutParams().width)));
        valueAnimator   .setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator   .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tImg.setX((float)valueAnimator.getAnimatedValue());
                tImg.getParent().requestLayout();
                /*tImg.getLayoutParams().width =(int) ((float) valueAnimator.getAnimatedValue());
                tImg.getParent().requestLayout();*/
            }
        });

        valueAnimator   .addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                backButton.setVisibility(VISIBLE);
                super.onAnimationEnd(animation);
                hideKeyboard(context,temp);
                textSearch.setText(temp.getText().toString());
                textSearch.setVisibility(isFind?VISIBLE:GONE);
                temp.setText("");
                temp.setCursorVisible(false);
                temp.clearFocus();
                temp.setHint("");
                editor.putBoolean("isAnimating",false).apply();

            }
        });

        valueAnimator.setDuration(searchView_animationTime);
        valueAnimator.start();
    }


    public String getTextChanged() {
        return textWatcher;
    }

    /*get-set Method.*/

    public void setTextWatcher(String textWatcher) {
        this.textWatcher = textWatcher;
    }

    public String getTextWatcher() {
        return textWatcher;
    }

    public void setAnimating(boolean animating) {
        isAnimating = animating;
    }

    public void setSearchView_hint(String searchView_hint) {
        this.searchView_hint = searchView_hint;
    }


    public void setSearchView_animationTime(int searchView_animationTime) {
        this.searchView_animationTime = searchView_animationTime;
    }

    public void setSearchView_textColor(int searchView_textColor) {
        this.searchView_textColor = searchView_textColor;
    }

    public float getResult_textSize() {
        return result_textSize;
    }

    public int getResult_textColor() {
        return result_textColor;
    }

    public void setResult_textSize(float result_textSize) {
        this.result_textSize = result_textSize;
    }

    public static void hideKeyboard(Context context, View view){ /*Keyboard hide.*/
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void showKeyboard(EditText editText, Context context) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void onClick(View view) {
        if(view.getTag().equals("search")){
            if(!(sharedPreferences.getBoolean("isAnimating",false))) showAnimating();
            else hideAnimating();
        }else if(view.getTag().equals("back")){
            ((Activity)context).onBackPressed();

        }
    }


    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            isFind = true;
            hideAnimating();
            return true;
        }
        return false;
    }
}
