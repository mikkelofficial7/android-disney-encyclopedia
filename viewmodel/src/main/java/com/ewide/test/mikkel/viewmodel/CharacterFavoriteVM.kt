package com.ewide.test.mikkel.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ewide.test.mikkel.base.BaseViewModel
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.room.DBConfig
import com.ewide.test.mikkel.room.queryAllFavoriteCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterFavoriteVM(private val roomConfig: DBConfig) : BaseViewModel() {

    private val _characterFavoriteStateEvent = MutableLiveData<UIState>()
    fun getCharacterFavoriteStateEvent(): MutableLiveData<UIState> = _characterFavoriteStateEvent

    fun getAllListOrderBy(isAscending: Boolean) {
        safeScopeFun().launch(Dispatchers.IO) {
            val data = roomConfig.dataDao().queryAllFavoriteCharacter(isAscending)
            _characterFavoriteStateEvent.postValue(UIState.OnSuccess(data))
        }
    }
}