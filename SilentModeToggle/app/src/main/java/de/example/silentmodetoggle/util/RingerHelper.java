package de.example.silentmodetoggle.util;

import android.media.AudioManager;
import android.provider.MediaStore;

/**
 * Created by Claudi on 24.02.2017.
 * RingerHelper ist einfache Java-Klasse, die nur statische Methoden enthält, um uns dabei zu helfen,
 * die  die AudioManager Klingel zu verarbeiten.
 * Gerade in der MainActivity praktisch, aber auch später in anderen Klassen, daher befinden sie sich
 * in einer separaten Klasse
 */

public class RingerHelper {
    /*privat, um zu verhindern, dass
    Benutzer ein RingerHelper-Objekt erzeugen*/

    private RingerHelper()
    {}
        /*Stummschaltungsmodus am Telefon einschalten*/
    public static void performToggle(AudioManager audioManager)
    {
        /*Wenn Stumm, schalte laut, sonst stumm*/
        audioManager.setRingerMode(
                isPhoneSilent(audioManager)
                ? AudioManager.RINGER_MODE_NORMAL
                : AudioManager.RINGER_MODE_SILENT);
    }

    /*Gib zurück, ob Telefon gerade stumm*/
    public static boolean isPhoneSilent(AudioManager audioManager)
    {
        return audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT;
    }

}
