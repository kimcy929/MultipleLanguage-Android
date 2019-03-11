package com.example.multiplelanguage.activity

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.example.multiplelanguage.R
import com.example.multiplelanguage.Utils.getDefaultLocale
import com.example.multiplelanguage.Utils.localeList
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    private val languagePosition: Int
        get() {
            val location = getDefaultLocale()
            val position = localeList.indexOf(location)
            if (position == -1) return 0
            return position
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        showBackButton()

        setActivityTitle(R.string.settings)

        setTextLanguage()

        btnChangeLanguage.setOnClickListener {
            showDialogLanguage()
        }
    }

    private fun setTextLanguage() {
        try {
            txtLanguage.text = resources.getStringArray(R.array.languages)[languagePosition]
        } catch (e: ArrayIndexOutOfBoundsException) {
        }
    }

    private fun showDialogLanguage() {
        AlertDialog.Builder(this)
            .setTitle(R.string.language)
            .setSingleChoiceItems(R.array.languages, languagePosition)
            { dialogInterface, which ->
                if (languagePosition != which) {
                    changeLanguage(localeList[which])
                    //setTextLanguage()
                }
                dialogInterface.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun changeLanguage(language: String) {
        PreferenceManager.getDefaultSharedPreferences(this).edit { putString("LANG", language) }
        this.apply {
            application.onCreate()
            // when restart activity after press back language will set because activity not destroy
            // if your app only have one activity
            //recreate()

            startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }
}
