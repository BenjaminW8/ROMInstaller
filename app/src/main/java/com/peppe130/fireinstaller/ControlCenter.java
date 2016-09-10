package com.peppe130.fireinstaller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.mikepenz.entypo_typeface_library.Entypo;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.peppe130.fireinstaller.core.FragmentsCollector;
import com.peppe130.fireinstaller.core.Utils;

import org.jetbrains.annotations.Contract;
import org.sufficientlysecure.htmltextview.HtmlTextView;


@SuppressWarnings("ConstantConditions")
public class ControlCenter {

    //•• MD5 ••/////////////////////////////////////////////////////////////////////////////////////

    public static String[] DEVICE_COMPATIBILITY_LIST = new String[] {"SM-G935F","SM-G930F,","SM-G930FD,","SM-G935FD,","SM-G935W8,"};
    public static String[] ROM_MD5_LIST = new String[] {"7F56858B8CD3DEE4FE88FDD39BA3ED0D"};
    public static String[] RECOVERY_MD5_LIST = new String[] {"5fb732eea3d3e2b407fa7685c27a5354"};

    //•• Modes & UI ••//////////////////////////////////////////////////////////////////////////////

    public static Boolean TEST_MODE = false;
    public static Boolean TRIAL_MODE = false;
    public static Boolean BUTTON_UI = true;

    //•• Manage Screens ••//////////////////////////////////////////////////////////////////////////

    public static Boolean SHOULD_SHOW_SPLASH_SCREEN = true;
    public static Boolean SHOULD_SHOW_DISCLAIMER_SCREEN = true;

    //•• Splash Screen ••///////////////////////////////////////////////////////////////////////////

    public static Integer SPLASH_SCREEN_DELAY = 4000;
    public static Integer SPLASH_SCREEN_IMAGE_LIGHT = R.drawable.rom_logo_light;
    public static Integer SPLASH_SCREEN_IMAGE_DARK = R.drawable.rom_logo_dark;

    //•• Toolbar Icons ••///////////////////////////////////////////////////////////////////////////

    public static IIcon SETTINGS_ICON = GoogleMaterial.Icon.gmd_settings;
    public static IIcon CHANGELOG_ICON = Ionicons.Icon.ion_ios_paper_outline;
    public static IIcon DEFAULT_VALUES_ICON = GoogleMaterial.Icon.gmd_settings_backup_restore;
    public static IIcon CLEAR_DOWNLOADS_ICON = Entypo.Icon.ent_trash;

    //•• Fragments ••///////////////////////////////////////////////////////////////////////////////

    public static Boolean SHOULD_SHOW_HOME_FRAGMENT = true;

    public static Integer[] FRAGMENTS_RESOURCES = new Integer[] {
            R.xml.first_fragment, R.xml.second_fragment, R.xml.third_fragment, R.xml.fourth_fragment
    };

    @SuppressWarnings("unused")
    public static void HomeFragment(Context context, FragmentsCollector.HomeFragment fragment, View view) {

        HtmlTextView mHtmlTextView = (HtmlTextView) view.findViewById(R.id.htmlWelcome);
        assert mHtmlTextView != null;
        mHtmlTextView.setHtml(R.raw.welcome, null);
        ImageView mImageView = (ImageView) view.findViewById(R.id.imageView);
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        mImageView.setImageResource(Utils.FetchTheme(context) == 0 ? R.drawable.exylight : R.drawable.exydark);

    }

    @SuppressWarnings("unused")
    public static void OverviewFragment(Context context, FragmentsCollector.OverviewFragment fragment, View view) {

        HtmlTextView mHtmlTextView = (HtmlTextView) view.findViewById(R.id.htmlOverview);
        assert mHtmlTextView != null;
        mHtmlTextView.setHtml(R.raw.overview, null);

    }

    //•• Download Center ••/////////////////////////////////////////////////////////////////////////

    public static Integer AVAILABLE_DOWNLOADS_NUMBER = 4;

    public static void DownloadROM() {

        Utils.StartDownloadROM(
                "www.exyfire.com/r/firepower7.zip",
                Utils.ROM_DOWNLOAD_FOLDER, "firepower7.zip");

    }

    @Nullable
    @Contract(pure = true)
    public static String DownloadNameGetter(Integer ID) {

        switch (ID) {

            case 0:

                return "BL & CP for S7 Edge";

            case 1:

                return "BL & CP for S7 Flat";

            case 2:

                return "Rescue Zip S7 Edge";

            case 3:

                return "Rescue Zip S7 Flat";

        }

        return null;

    }

    public static void DownloadActionGetter(Integer ID) {

        String mDownloadLink, mDownloadDirectory, mDownloadedFileFinalName, mDownloadedFileMD5, mRecoveryPartition;

        switch (ID) {

            case 0:

                mDownloadLink = "www.exyfire.com/r/blcps7edge.zip";
                mDownloadDirectory = "/Download";
                mDownloadedFileFinalName = "blcps7edge.zip";
                mDownloadedFileMD5 = "8BFDAB6EBB4E7BE25770387B02EDA27E";

                Utils.StartSingleDownload(mDownloadLink, mDownloadDirectory, mDownloadedFileFinalName, mDownloadedFileMD5);

                break;

            case 1:

                mDownloadLink = "www.exyfire.com/r/blcps7flat.zip";
                mDownloadDirectory = "/Download";
                mDownloadedFileFinalName = "blcps7flat.zip";
                mDownloadedFileMD5 = "9D06C231DDE4451DFF709F5B58FEFA04";

                Utils.StartSingleDownload(mDownloadLink, mDownloadDirectory, mDownloadedFileFinalName, mDownloadedFileMD5);

                break;

            case 2:

                mDownloadLink = "www.exyfire.com/r/Recovers7edge.zip";
                mDownloadDirectory = "/Download";
                mDownloadedFileFinalName = "Recovers7edge.zip";
                mDownloadedFileMD5 = "8EC66E968D15C5078E5C3DEB440A92EE";

                Utils.StartSingleDownload(mDownloadLink, mDownloadDirectory, mDownloadedFileFinalName, mDownloadedFileMD5);

                break;

            case 3:

                mDownloadLink = "www.exyfire.com/r/Recovers7flat.zip";
                mDownloadDirectory = "/Download";
                mDownloadedFileFinalName = "Recovers7flat.zip";
                mDownloadedFileMD5 = "8727B280F9D0059C2DEBAA5ADA66CDD6";

                Utils.StartSingleDownload(mDownloadLink, mDownloadDirectory, mDownloadedFileFinalName, mDownloadedFileMD5);

                break;

        }

    }

    //•• Others ••//////////////////////////////////////////////////////////////////////////////////

    public static void ROMUtils() {
        // Write here the code you want to run in the onResume() method of each activity
    }

    public static void ROMDeveloperInfoAction() {
        // Write here the code you want to run when a user click on the ROM developer header
    }

    public static void ROMThemerInfoAction() {
        // Write here the code you want to run when a user click on the ROM themer header
    }

    public static void ROMThreadInfoAction() {

        String[] mSocial = new String[] {"S7 Edge", "S7 Flat"};

        String[] mLinks = new String[] {"http://forum.xda-developers.com/s7-edge/development/rom-bring-fire-t3400282", "http://forum.xda-developers.com/galaxy-s7/development/rom-bring-fire-t3400294"};

        Utils.FollowMeDialog(mSocial, mLinks);

    }

    public static void ROMGitHubInfoAction() {

        Uri mUri = Uri.parse("https://github.com/benjaminw8/ROMInstaller");

        Utils.ACTIVITY.startActivity(new Intent(Intent.ACTION_VIEW, mUri));

    }

}