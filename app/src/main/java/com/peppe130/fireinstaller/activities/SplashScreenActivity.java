package com.peppe130.fireinstaller.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.peppe130.fireinstaller.R;
import com.peppe130.fireinstaller.ControlCenter;
import org.michaelevans.colorart.library.ColorArt;


@SuppressWarnings("ConstantConditions")
public class SplashScreenActivity extends Activity {

    Bitmap mBitmap;
    ColorArt mColorArt;
    ImageView mImageView;
    RelativeLayout mRelativeLayout;
    BitmapFactory.Options mOptions;
    Boolean isActivityVisible = true;
    Integer mSplashScreenImage, mFetchHeaderColor, mHeaderColor;
    SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_layout);

        if (ControlCenter.SHOULD_SHOW_SPLASH_SCREEN) {
            mRelativeLayout = (RelativeLayout) findViewById(R.id.splash_screen_layout);
            mImageView = (ImageView) findViewById(R.id.imageView);

            SP = PreferenceManager.getDefaultSharedPreferences(this);

            if (SP.getInt("theme", 0) == 0) {
                mFetchHeaderColor = R.color.colorPrimary_Theme_Light;
                mSplashScreenImage = R.drawable.rom_logo_light;
            } else {
                mFetchHeaderColor = R.color.colorPrimary_Theme_Dark;
                mSplashScreenImage = R.drawable.rom_logo_dark;
            }

            mHeaderColor = ContextCompat.getColor(this, mFetchHeaderColor);
            setTaskDescription(new ActivityManager.TaskDescription(null, null, mHeaderColor));

            mOptions = new BitmapFactory.Options();
            mOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), mSplashScreenImage, mOptions);
            mBitmap = decodeBitmapFromResource(getResources(), mSplashScreenImage, 500, 500);

            mColorArt = new ColorArt(mBitmap);
            mRelativeLayout.setBackgroundColor(mColorArt.getBackgroundColor());
            mImageView.setImageBitmap(mBitmap);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isActivityVisible) {
                        if (ControlCenter.SHOULD_SHOW_DISCLAIMER_SCREEN) {
                            startActivity(new Intent(SplashScreenActivity.this, AgreementActivity.class));
                        } else {
                            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                        }
                    }
                    finish();
                }
            }, ControlCenter.SPLASH_SCREEN_DELAY);
        } else {
            if (ControlCenter.SHOULD_SHOW_DISCLAIMER_SCREEN) {
                startActivity(new Intent(SplashScreenActivity.this, AgreementActivity.class));
            } else {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
            finish();
        }

    }

    public int calculateInSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public Bitmap decodeBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options mOptions = new BitmapFactory.Options();
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, mOptions);
        mOptions.inSampleSize = calculateInSize(mOptions, reqWidth, reqHeight);
        mOptions.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, mOptions);
    }

    @Override
    public void onBackPressed() {
        // Do nothing to lock back softkey
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isActivityVisible) {
            isActivityVisible = true;
            recreate();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActivityVisible = false;
    }

}