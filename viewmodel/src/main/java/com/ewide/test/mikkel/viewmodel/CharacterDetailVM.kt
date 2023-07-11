package com.ewide.test.mikkel.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ewide.test.mikkel.base.BaseViewModel
import com.ewide.test.mikkel.base.helper.NetworkHandler
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.extension.getGeneralError
import com.ewide.test.mikkel.viewmodel.usecase.CharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterDetailVM(
    networkHandler: NetworkHandler,
    private val characterUseCase: CharacterUseCase
) : BaseViewModel(networkHandler) {

    private val _characterDetailStateEvent = MutableLiveData<UIState>()
    fun getCharacterDetailStateEvent(): MutableLiveData<UIState> = _characterDetailStateEvent

    fun getDetailDisneyCharacter(id: Int) {
        _characterDetailStateEvent.postValue(UIState.onLoading)

        executeJob {
            safeScopeFun {
                _characterDetailStateEvent.postValue(UIState.onFailure(it.getGeneralError()))

            }.launch(Dispatchers.IO) {
                characterUseCase.getOneCharacter(id).collectLatest {
                    _characterDetailStateEvent.postValue(UIState.onFinishLoading)
                    _characterDetailStateEvent.postValue(UIState.onSuccess(it))
                }

            }
        }
    }
}