package com.peppe130.fireinstaller.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.io.File;

import com.peppe130.fireinstaller.R;
import com.peppe130.fireinstaller.BuildConfig;
import com.peppe130.fireinstaller.core.Utils;
import com.peppe130.fireinstaller.ControlCenter;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.entypo_typeface_library.Entypo;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;


@SuppressWarnings("ResultOfMethodCallIgnored, ConstantConditions")
public class SettingsActivity extends AppCompatActivity {

    public static SharedPreferences SP;
    public static SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.ACTIVITY = this;

        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mEditor = SP.edit();

        setTheme(SP.getInt("theme", 0) == 0 ? R.style.AppTheme_Light : R.style.AppTheme_Dark);

        setContentView(R.layout.activity_settings_layout);

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new SettingsPreferencesFragment())
                .commit();

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

    public static class SettingsPreferencesFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings_fragment);

            Preference DOWNLOAD_CENTER = findPreference("download_center");
            Preference THEME_CHOOSER = findPreference("theme_chooser");
            Preference PROJECT_BUILD_NUMBER = findPreference("project_build_number");
            Preference APP_BUILD_NUMBER = findPreference("app_build_number");
            Preference PROJECT_DEVELOPER = findPreference("project_developer");
            Preference PROJECT_THEMER = findPreference("project_themer");
            Preference PROJECT_THREAD = findPreference("project_thread");
            Preference PROJECT_GITHUB = findPreference("project_github");
            Preference ROM_BUILD_NUMBER = findPreference("rom_build_number");
            Preference ROM_DEVELOPER = findPreference("rom_developer");
            Preference ROM_THEMER = findPreference("rom_themer");
            Preference ROM_THREAD = findPreference("rom_thread");
            Preference APP_GITHUB = findPreference("app_github");
            Preference REVIEW_APP = findPreference("review_app");
            Preference ALL_MY_APPS = findPreference("all_my_apps");

            Integer mIconColor = ContextCompat.getColor(getActivity(), Utils.IconColorChooser());

            IconicsDrawable mDownloadCenterIcon = new IconicsDrawable(getActivity())
                    .icon(Entypo.Icon.ent_download)
                    .color(mIconColor)
                    .sizeDp(40);

            IconicsDrawable mThemeChooserIcon = new IconicsDrawable(getActivity())
                    .icon(CommunityMaterial.Icon.cmd_theme_light_dark)
                    .color(mIconColor)
                    .sizeDp(40);

            IconicsDrawable mThreadIcon = new IconicsDrawable(getActivity())
                    .icon(CommunityMaterial.Icon.cmd_book_open_page_variant)
                    .color(mIconColor)
                    .sizeDp(40);

            IconicsDrawable mInfoIcon = new IconicsDrawable(getActivity())
                    .icon(GoogleMaterial.Icon.gmd_info_outline)
                    .color(mIconColor)
                    .sizeDp(40);

            IconicsDrawable mDeveloperIcon = new IconicsDrawable(getActivity())
                    .icon(GoogleMaterial.Icon.gmd_developer_board)
                    .color(mIconColor)
                    .sizeDp(40);

            IconicsDrawable mThemerIcon = new IconicsDrawable(getActivity())
                    .icon(GoogleMaterial.Icon.gmd_color_lens)
                    .color(mIconColor)
                    .sizeDp(40);

            IconicsDrawable mGitHubIcon = new IconicsDrawable(getActivity())
                    .icon(CommunityMaterial.Icon.cmd_github_circle)
                    .color(mIconColor)
                    .sizeDp(40);

            IconicsDrawable mPlayStoreIcon = new IconicsDrawable(getActivity())
                    .icon(CommunityMaterial.Icon.cmd_google_play)
                    .color(mIconColor)
                    .sizeDp(40);

            DOWNLOAD_CENTER.setIcon(mDownloadCenterIcon);
            DOWNLOAD_CENTER.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), DownloadActivity.class));
                    return false;
                }
            });

            THEME_CHOOSER.setIcon(mThemeChooserIcon);
            final String[] mString = new String[] {getActivity().getString(R.string.light_theme), getActivity().getString(R.string.dark_theme)};
            THEME_CHOOSER.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    new MaterialDialog.Builder(getActivity())
                            .items(mString)
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                    mEditor.putInt("theme", which).apply();
                                    mEditor.putBoolean("default_values", false).apply();
                                    getActivity().finishAffinity();
                                    getActivity().startActivity(new Intent(getActivity(), SplashScreenActivity.class));
                                    getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                }
                            })
                            .show();
                    return false;
                }
            });

            PROJECT_BUILD_NUMBER.setIcon(mInfoIcon);
            PROJECT_BUILD_NUMBER.setSummary(Double.toString(BuildConfig.projectVersion));

            PROJECT_DEVELOPER.setIcon(mDeveloperIcon);
            PROJECT_DEVELOPER.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    String[] mSocial = new String[] {"Google+", "Twitter"};
                    String[] mLinks = new String[] {"http://google.com/+PeppeMontuoro", "https://twitter.com/PeppeMontuoro"};
                    Utils.FollowMeDialog(mSocial, mLinks);
                    return false;
                }
            });

            PROJECT_THEMER.setIcon(mThemerIcon);
            PROJECT_THEMER.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    String[] mSocial = new String[] {"Google+", "Twitter"};
                    String[] mLinks = new String[] {"http://google.com/+MRLOUDT_ONE", "https://twitter.com/MR_LOUD_T_ONE"};
                    Utils.FollowMeDialog(mSocial, mLinks);
                    return false;
                }
            });

            PROJECT_THREAD.setIcon(mThreadIcon);
            PROJECT_THREAD.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Uri mUri = Uri.parse("http://forum.xda-developers.com/android/apps-games/app-rom-installer-to-flash-custom-rom-t3430099");
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, mUri));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        Utils.ToastLong(Utils.ACTIVITY, "Can not find an application to perform this action.");
                    }
                    return false;
                }
            });

            PROJECT_GITHUB.setIcon(mGitHubIcon);
            PROJECT_GITHUB.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Uri mUri = Uri.parse("https://github.com/peppe130/ROMInstaller");
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, mUri));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        Utils.ToastLong(Utils.ACTIVITY, "Can not find an application to perform this action.");
                    }
                    return false;
                }
            });

            APP_BUILD_NUMBER.setIcon(mInfoIcon);
            APP_BUILD_NUMBER.setSummary(BuildConfig.VERSION_NAME);

            ROM_BUILD_NUMBER.setIcon(mInfoIcon);

            ROM_DEVELOPER.setIcon(mDeveloperIcon);
            ROM_DEVELOPER.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    ControlCenter.ROMDeveloperInfoAction();
                    return false;
                }
            });

            ROM_THEMER.setIcon(mThemerIcon);
            ROM_THEMER.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    ControlCenter.ROMThemerInfoAction();
                    return false;
                }
            });

            ROM_THREAD.setIcon(mThreadIcon);
            ROM_THREAD.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    ControlCenter.ROMThreadInfoAction();
                    return false;
                }
            });

            APP_GITHUB.setIcon(mGitHubIcon);
            APP_GITHUB.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Uri mUri = Uri.parse("https://github.com/BenjaminW8/ROMInstaller");
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, mUri));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        Utils.ToastLong(Utils.ACTIVITY, "Can not find an application to perform this action.");
                    }
                    return false;
                }
            });

            APP_GITHUB.setIcon(mGitHubIcon);
            APP_GITHUB.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Uri mUri = Uri.parse("https://github.com/BenjaminW8/ROMInstaller");
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, mUri));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        Utils.ToastLong(Utils.ACTIVITY, "Can not find an application to perform this action.");
                    }
                    return false;
                }
            });

            REVIEW_APP.setIcon(mPlayStoreIcon);
            REVIEW_APP.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName())));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                    }
                    return false;
                }
            });

            ALL_MY_APPS.setIcon(mPlayStoreIcon);
            ALL_MY_APPS.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Giuseppe Montuoro")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/search?q=pub:Giuseppe Montuoro")));
                    }
                    return false;
                }
            });

        }

    }

}