package com.ewide.test.mikkel.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ewide.test.mikkel.base.BaseViewModel
import com.ewide.test.mikkel.base.datastore.BasePreferenceDataStore
import com.ewide.test.mikkel.base.sharedpreference.BaseSharedPreferences
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.model.local.ListCharacter
import com.ewide.test.mikkel.room.DBConfig
import com.ewide.test.mikkel.room.queryAllFavoriteCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GamesFavoriteVM(
    private val roomConfig: DBConfig,
    private val sharedPreferences: SharedPreferences,
    private val context: Context,
) : BaseViewModel() {

    private val prefDataStore by lazy {
        BasePreferenceDataStore(context)
    }
    private val sharedPref by lazy {
        BaseSharedPreferences(sharedPreferences)
    }

    private val _characterFavoriteStateListEvent = MutableLiveData<UIState>()
    fun getCharacterFavoriteListStateEvent(): MutableLiveData<UIState> = _characterFavoriteStateListEvent

    private val _characterFavoriteStateEvent = MutableLiveData<UIState>()
    fun getCharacterFavoriteStateEvent(): MutableLiveData<UIState> = _characterFavoriteStateEvent

    fun getAllListLocalOrderBy(isAscending: Boolean) {
        safeScopeFun().launch(Dispatchers.IO) {
            val data = roomConfig.dataDao().queryAllFavoriteCharacter(isAscending)
            _characterFavoriteStateListEvent.postValue(UIState.OnSuccess(data))
        }
    }

    fun saveSortingData(isAscending: Boolean) {
        sharedPref.saveData("SORTING", isAscending)
    }

    fun saveSortingDataUsingDataStore(isAscending: Boolean) {
        safeScopeFun().launch(Dispatchers.IO) {
            prefDataStore.writeData("SORTING", isAscending)
        }
    }

    fun getSortingData(): Boolean {
        return sharedPref.getData("SORTING", false)
    }

    fun getSortingDataUsingDataStore() {
        safeScopeFun().launch(Dispatchers.IO) {
            prefDataStore.readData("SORTING", false) {
                Log.d("TAG", "Data store get: $it")
            }
        }
    }

    fun removeFromFavorite(dataChar: ListCharacter) {
        safeScopeFun().launch(Dispatchers.IO) {
            roomConfig.dataDao().deleteFavoriteItemById(dataChar.gameID.orEmpty())
            _characterFavoriteStateEvent.postValue(UIState.OnSuccess(false))
        }
    }
}