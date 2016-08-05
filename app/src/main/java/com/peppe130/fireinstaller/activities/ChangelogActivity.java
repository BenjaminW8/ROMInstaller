package com.peppe130.fireinstaller.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import java.io.File;

import com.peppe130.fireinstaller.R;
import com.peppe130.fireinstaller.core.Utils;
import com.peppe130.fireinstaller.ControlCenter;
import org.sufficientlysecure.htmltextview.HtmlRemoteImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;


@SuppressWarnings("ResultOfMethodCallIgnored")
public class ChangelogActivity extends AppCompatActivity {

    SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.ACTIVITY = this;

        SP = PreferenceManager.getDefaultSharedPreferences(this);

        setTheme(SP.getInt("theme", 0) == 0 ? R.style.AppTheme_Light : R.style.AppTheme_Dark);

        setContentView(R.layout.activity_changelog_layout);

        HtmlTextView mHtmlTextView = (HtmlTextView) findViewById(R.id.changelog_html_text);
        assert mHtmlTextView != null;
        mHtmlTextView.setHtml(R.raw.changelog, new HtmlRemoteImageGetter(mHtmlTextView));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Utils.ACTIVITY = this;

        File mROMFolder = new File(Environment.getExternalStorageDirectory().getPath() + "/" + getString(R.string.rom_folder));
        File mSampleZIP = new File(mROMFolder.getPath() + "/" + "Sample.zip");

        if(!mROMFolder.exists()) {
            mROMFolder.mkdirs();
        }

        if(ControlCenter.TRIAL_MODE && !mSampleZIP.exists()) {
            Utils.CopyAssetFolder(getAssets(), "sample", mROMFolder.toString());
        }

        ControlCenter.ROMUtils();

    }

}