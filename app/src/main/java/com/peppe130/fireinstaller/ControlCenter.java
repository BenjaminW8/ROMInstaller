package com.peppe130.fireinstaller;

import org.jetbrains.annotations.Contract;
import android.support.annotation.Nullable;
import android.content.Intent;
import android.net.Uri;

import com.peppe130.fireinstaller.core.Utils;
import com.mikepenz.entypo_typeface_library.Entypo;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.ionicons_typeface_library.Ionicons;


@SuppressWarnings("ConstantConditions")
public class ControlCenter {

    public static String[] DEVICE_COMPATIBILITY_LIST = new String[] {"SM-G930F","SM-G935F"};
    public static String[] ROM_MD5_LIST = new String[] {"33AE89AB47E2DDA9D54658B9FDD1D41E","165C319C0F5EDF356C1D8D57EFEA9A34"};
    public static String[] RECOVERY_MD5_LIST = new String[] {"5fb732eea3d3e2b407fa7685c27a5354"};

    public static Boolean TEST_MODE = false;
    public static Boolean TRIAL_MODE = false;
    public static Boolean BUTTON_UI = true;
    public static Boolean SHOULD_SHOW_SPLASH_SCREEN = true;
    public static Boolean SHOULD_SHOW_DISCLAIMER_SCREEN = true;

    public static Integer SPLASH_SCREEN_DELAY = 3000;
    public static Integer AVAILABLE_DOWNLOADS_NUMBER = 0;

    public static IIcon SETTINGS_ICON = GoogleMaterial.Icon.gmd_settings;
    public static IIcon CHANGELOG_ICON = Ionicons.Icon.ion_ios_paper_outline;
    public static IIcon DEFAULT_VALUES_ICON = GoogleMaterial.Icon.gmd_settings_backup_restore;
    public static IIcon CLEAR_DOWNLOADS_ICON = Entypo.Icon.ent_trash;


    public static void DownloadROM() {

        Utils.FILE_NAME = "firepower6.zip";

        Utils.StartDownloadROM(
                "http://exyfirerom.com/r/firepower6.zip",
                Utils.ACTIVITY.getString(R.string.rom_folder));

    }

    public static void ROMUtils() {
        // Write here the code you want to run in the onResume() method of each activity
    }

    public static void ROMDeveloperInfoAction() {
        // Do something
    }

    public static void ROMThemerInfoAction() {
        String[] mSocial = new String[] {"GitHub"};
        String[] mLinks = new String[] {"https://github.com/mrhelloyellow/ROMInstaller"};
        Utils.FollowMeDialog(mSocial, mLinks);
    }

    public static void ROMThreadInfoAction() {
        String[] mSocial = new String[] {"S7 Thread", "S7 Edge Thread"};
        String[] mLinks = new String[] {"http://forum.xda-developers.com/galaxy-s7/development/rom-bring-fire-t3400294/page3", "http://forum.xda-developers.com/es7-edg/development/rom-bring-fire-t3400282"};
        Utils.FollowMeDialog(mSocial, mLinks);
    }

    @Nullable
    @Contract(pure = true)
    public static String DownloadNameGetter(Integer mInt) {

        switch (mInt) {
            case 0:
                return "";
        }

        return null;

    }

    public static void DownloadActionGetter(Integer mInt) {

        String mDownloadLink, mDownloadDirectory, mDownloadedFileFinalName, mDownloadedFileMD5, mRecoveryPartition;

        switch (mInt) {
            case 0:
                break;
        }

    }

}