package com.ewide.test.mikkel.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.ewide.test.mikkel.base.BaseViewModel
import com.ewide.test.mikkel.base.sharedpreference.BaseSharedPreferences
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.room.DBConfig
import com.ewide.test.mikkel.room.queryAllFavoriteCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterFavoriteVM(
    private val roomConfig: DBConfig,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val sharedPref by lazy {
        BaseSharedPreferences(sharedPreferences)
    }
    private val _characterFavoriteStateEvent = MutableLiveData<UIState>()
    fun getCharacterFavoriteStateEvent(): MutableLiveData<UIState> = _characterFavoriteStateEvent

    fun getAllListOrderBy(isAscending: Boolean) {
        safeScopeFun().launch(Dispatchers.IO) {
            val data = roomConfig.dataDao().queryAllFavoriteCharacter(isAscending)
            _characterFavoriteStateEvent.postValue(UIState.OnSuccess(data))
        }
    }

    fun saveSortingData(isAscending: Boolean) {
        sharedPref.saveData("SORTING", isAscending)
    }

    fun getSortingData(): Boolean {
        return sharedPref.getData("SORTING", false)
    }
}