package com.nicolasmilliard.playground.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.nicolasmilliard.playground.R

class SettingsScreen : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)
    }
}
