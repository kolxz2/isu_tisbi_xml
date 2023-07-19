package ru.nikolas_snek.isu_tisbi_xml.data.data_store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("USER_AUTH")

class UserDataStore(
    context: Context,
) {

    private val dataStore = context.dataStore

    companion object {
        private val KEY_LOGIN = stringPreferencesKey("LOGIN")
        private val KEY_PASSWORD = stringPreferencesKey("PASSWORD")
        private val KEY_STUDENT_HASH = stringPreferencesKey("STUDENT_HASH")
        private val KEY_PEOPLE_ROLE = stringPreferencesKey("PEOPLE_ROLE")
    }

    val loginStudent: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_LOGIN]
        }

    val passwordStudent: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_PASSWORD]
        }

    val studentHash: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_STUDENT_HASH]
        }

    val peopleRole: Flow<Int?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_PEOPLE_ROLE]?.toInt()
        }

    suspend fun saveLogin(loginStudent: String) {
        dataStore.edit { preferences ->
            preferences[KEY_LOGIN] = loginStudent
        }
    }

    suspend fun savePassword(passwordStudent: String) {
        dataStore.edit { preferences ->
            preferences[KEY_PASSWORD] = passwordStudent
        }
    }

    suspend fun savePeopleRole(peopleRole: Int) {
        dataStore.edit { preferences ->
            preferences[KEY_PEOPLE_ROLE] = peopleRole.toString()
        }
    }

    suspend fun saveStudentHash(studentHash: String) {
        dataStore.edit { preferences ->
            preferences[KEY_STUDENT_HASH] = studentHash
        }
    }

    suspend fun clearUserData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun checkAllVariablesNotNull(): Boolean {
        //  val authToken = authToken.firstOrNull()
        val loginStudent = loginStudent.firstOrNull()
        val passwordStudent = passwordStudent.firstOrNull()
        val studentHash = studentHash.firstOrNull()
        val peopleRole = peopleRole.firstOrNull()

        return loginStudent != null &&
                passwordStudent != null &&
                studentHash != null &&
                peopleRole != null
    }

}