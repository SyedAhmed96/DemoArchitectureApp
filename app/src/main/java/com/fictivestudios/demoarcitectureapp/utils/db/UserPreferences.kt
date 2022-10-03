package com.fictivestudios.demoarcitectureapp.utils.db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fictivestudios.demoarcitectureapp.data.model.response.login.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = "user_preferences"
)

class UserPreferences @Inject constructor(private val context: Context) {

    suspend fun saveUserData(email:String, firstName: String,gender: String,id: Int,image: String,lastName: String,username: String,token: String ) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = token
            preferences[EAMIL] = email
            preferences[FIRSTNAME] = firstName
            preferences[LASTNAME] = lastName
            preferences[GENDER] = gender
            preferences[IMAGE] = image
            preferences[ID] = id
            preferences[USERNAME] = username


        }
    }


    val useData: Flow<LoginResponse?> = context.dataStore.data.map {
        val email = it[EAMIL] ?: return@map null
        val username = it[USERNAME] ?: return@map null
        val firstName = it[FIRSTNAME] ?: return@map null
        val lastName = it[LASTNAME] ?: return@map null
        val gender = it[GENDER] ?: return@map null
        val id = it[ID] ?: return@map null
        val image = it[IMAGE] ?: return@map null
        val token = it[TOKEN] ?: return@map null
        LoginResponse(email, firstName, gender, id, image, lastName, token, username)

    }

    suspend fun clearData() {
        context.dataStore.edit { prefrences ->

            kotlin.run {
                prefrences.clear()
            }
        }
    }

    companion object {

        val EAMIL = stringPreferencesKey("email")
        var FIRSTNAME = stringPreferencesKey("firstName")
        var GENDER = stringPreferencesKey("gender")
        var ID = intPreferencesKey("id")
        var IMAGE  = stringPreferencesKey("image")
        var LASTNAME = stringPreferencesKey("lastName")
        var USERNAME  = stringPreferencesKey("username")
        val TOKEN = stringPreferencesKey("token")
    }
}