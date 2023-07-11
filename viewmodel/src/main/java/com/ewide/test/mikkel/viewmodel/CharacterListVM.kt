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

class CharacterListVM(
    networkHandler: NetworkHandler,
    private val characterUseCase: CharacterUseCase
) : BaseViewModel(networkHandler) {

    private val _characterListStateEvent = MutableLiveData<UIState>()
    fun getCharacterListStateEvent(): MutableLiveData<UIState> = _characterListStateEvent

    private val _characterSearchStateEvent = MutableLiveData<UIState>()
    fun getCharacterSearchStateEvent(): MutableLiveData<UIState> = _characterSearchStateEvent

    fun getAllDisneyCharacter(page: Int) {
        _characterListStateEvent.postValue(UIState.OnLoading)

        executeJob(getCharacterListStateEvent()) {
            safeScopeFun {
                _characterListStateEvent.postValue(UIState.OnFinishLoading)
                _characterListStateEvent.postValue(UIState.OnFailure(it.getGeneralError()))

            }.launch(Dispatchers.IO) {
                characterUseCase.getAllCharacter(page).collectLatest {
                    _characterListStateEvent.postValue(UIState.OnFinishLoading)
                    _characterListStateEvent.postValue(UIState.OnSuccess(it))
                }

            }
        }
    }

    fun searchDisneyCharacter(name: String) {
        _characterSearchStateEvent.postValue(UIState.OnLoading)

        executeJob(getCharacterSearchStateEvent()) {
            safeScopeFun {
                _characterSearchStateEvent.postValue(UIState.OnFinishLoading)
                _characterSearchStateEvent.postValue(UIState.OnFailure(it.getGeneralError()))

            }.launch(Dispatchers.IO) {
                characterUseCase.searchCharacter(name).collectLatest {
                    _characterSearchStateEvent.postValue(UIState.OnFinishLoading)
                    _characterSearchStateEvent.postValue(UIState.OnSuccess(it))
                }

            }
        }
    }
}