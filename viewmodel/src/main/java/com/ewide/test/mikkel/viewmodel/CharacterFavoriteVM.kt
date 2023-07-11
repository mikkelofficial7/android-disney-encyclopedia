package com.ewide.test.mikkel.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ewide.test.mikkel.base.BaseViewModel
import com.ewide.test.mikkel.base.helper.NetworkHandler
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.extension.getGeneralError
import com.ewide.test.mikkel.viewmodel.usecase.CharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterFavoriteVM() : BaseViewModel() {

    private val _characterFavoriteStateEvent = MutableLiveData<UIState>()
    fun getCharacterFavoriteStateEvent(): MutableLiveData<UIState> = _characterFavoriteStateEvent

    fun getAllList() {
        _characterFavoriteStateEvent.postValue(UIState.OnLoading)

        executeJob(getCharacterFavoriteStateEvent()) {
            safeScopeFun {
                _characterFavoriteStateEvent.postValue(UIState.OnFinishLoading)

                safeScopeFun().launch(Dispatchers.IO) {
                    delay(500)
                    _characterFavoriteStateEvent.postValue(UIState.OnFailure(it.getGeneralError()))
                }

            }.launch(Dispatchers.IO) {
                
            }
        }
    }
}