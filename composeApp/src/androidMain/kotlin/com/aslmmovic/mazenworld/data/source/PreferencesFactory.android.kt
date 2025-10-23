
package com.aslmmovic.mazenworld.data.source // MATCH YOUR PACKAGE

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Koin component to access the Context registered in the Koin container
object AndroidContextProvider : KoinComponent {
    val context: Context by inject()
}

// DataStore instance must be a singleton per file, often defined at the top level
private val dataStoreFileName = "mazen_app_prefs.preferences_pb"
private val PREFERENCES_DATASTORE = createDataStore(
    corruptionHandler = ReplaceFileCorruptionHandler(
        produceNewData = { emptyPreferences() }
    ),
    context = AndroidContextProvider.context,
    // Coroutine scope for DataStore operations
    scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    fileName = { dataStoreFileName }
)

actual fun createDataStore(corruptionHandler: DataStore<Preferences>): DataStore<Preferences> {
    // We return the pre-created singleton instance
    return PREFERENCES_DATASTORE
}