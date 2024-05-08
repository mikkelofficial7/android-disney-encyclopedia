package com.ewide.test.mikkel.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ewide.test.mikkel.base.BaseViewModel
import com.ewide.test.mikkel.base.helper.NetworkHandler
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.extension.getGeneralError
import com.ewide.test.mikkel.model.local.ListCharacter
import com.ewide.test.mikkel.viewmodel.usecase.CharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GamesListVM(
    networkHandler: NetworkHandler? = null,
    private val characterUseCase: CharacterUseCase? = null
) : BaseViewModel(networkHandler) {

    private val _characterListStateEvent = MutableLiveData<UIState>()
    fun getCharacterListStateEvent(): MutableLiveData<UIState> = _characterListStateEvent

    private val _characterFavoriteStateEvent = MutableLiveData<UIState>()
    fun getCharacterFavoriteStateEvent(): MutableLiveData<UIState> = _characterFavoriteStateEvent

    private val _characterFavoriteStateListEvent = MutableLiveData<UIState>()
    fun getCharacterFavoriteListStateEvent(): MutableLiveData<UIState> = _characterFavoriteStateListEvent

    private var _listItemFavorite: List<ListCharacter?>? = listOf()
    fun getListItemFavorite(): List<ListCharacter?>? = _listItemFavorite



    fun getAllCharacterFromAPI(title: String) {
        _characterListStateEvent.postValue(UIState.OnLoading)

        executeJob(getCharacterListStateEvent()) {
            safeScopeFun {
                _characterListStateEvent.postValue(UIState.OnFinishLoading)

                safeScopeFun().launch(Dispatchers.IO) {
                    delay(500)
                    _characterListStateEvent.postValue(UIState.OnFailure(it.getGeneralError()))
                }

            }.launch(Dispatchers.IO) {
                characterUseCase?.getAllCharacter(title)?.collectLatest {
                    _characterListStateEvent.postValue(UIState.OnFinishLoading)

                    safeScopeFun().launch(Dispatchers.IO) {
                        delay(500)
                        _characterListStateEvent.postValue(UIState.OnSuccess(it))
                    }
                }

            }
        }
    }

    fun getAllListLocalOrderBy() {
        safeScopeFun().launch(Dispatchers.IO) {
            val dataFavorite = characterUseCase?.getOneCharacterFromLocalDb(true)
            _characterFavoriteStateListEvent.postValue(UIState.OnSuccess(dataFavorite))
        }
    }

    fun setListItemFavorite(list: List<ListCharacter?>?) {
        _listItemFavorite = list
    }

    fun addToFavorite(dataChar: ListCharacter) {
        safeScopeFun().launch(Dispatchers.IO) {
            characterUseCase?.addCharacterToFavorite(dataChar)
            _characterFavoriteStateEvent.postValue(UIState.OnSuccess(true))
        }
    }

    fun removeFromFavorite(dataChar: ListCharacter) {
        safeScopeFun().launch(Dispatchers.IO) {
            characterUseCase?.removeCharacterFromFavorite(dataChar)
            _characterFavoriteStateEvent.postValue(UIState.OnSuccess(false))
        }
    }
}