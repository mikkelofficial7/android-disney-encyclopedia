package com.ewide.test.mikkel.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.mikkel.MainActivity
import com.ewide.test.mikkel.adapter.ItemAdapter
import com.ewide.test.mikkel.base.BaseFragmentVM
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.databinding.FragmentMainPageBinding
import com.ewide.test.mikkel.extension.observe
import com.ewide.test.mikkel.extension.startTyping
import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.local.ListCharacter
import com.ewide.test.mikkel.route.Routing
import com.ewide.test.mikkel.viewmodel.GamesListVM

class MainPageFragment : BaseFragmentVM<FragmentMainPageBinding, GamesListVM>(GamesListVM::class) {
    private val rvAdapter by lazy {
        ItemAdapter<ListCharacterResponse>()
    }

    override fun bindToolbar(): Toolbar? = viewBinding?.customToolbar?.getToolbar()

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        baseViewModel.getAllListLocalOrderBy()
    }

    override fun onReExecute() {}

    override fun initUiListener() {
        viewBinding?.rvCharacter?.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = rvAdapter
        }

        viewBinding?.searchBar?.startTyping {
            baseViewModel.getAllCharacterFromAPI(it)
        }

        viewBinding?.customToolbar?.clickOnFavoriteButton {
            moveToFavoriteList()
        }

        rvAdapter.onAddToFavorite = {
            showToastAddRemoveFavorite(!it.isFavorite)
            if(it.isFavorite) {
                baseViewModel.removeFromFavorite(it.setItemToFavoriteCharacterLocal())
            } else {
                baseViewModel.addToFavorite(it.setItemToFavoriteCharacterLocal())
            }
        }

        rvAdapter.onClick = {
            Routing.moveToDetailPage(requireActivity() as MainActivity, it)
        }
    }

    override fun observeViewModel(viewModel: GamesListVM) {
        observe(viewModel.getCharacterListStateEvent(), ::handleState)
        observe(viewModel.getCharacterFavoriteStateEvent(), ::handleStateFavorite)
        observe(viewModel.getCharacterFavoriteListStateEvent(), ::handleStateFavoriteList)
    }

    private fun handleState(state: UIState?) {
        handleResponseState<List<ListCharacterResponse?>?>(state) {
            it?.map { item ->
                item?.apply {
                    isFavorite = baseViewModel.getListItemFavorite()?.find { it?.gameID == item.gameID } != null
                }
            }
            rvAdapter.setCharacterData(it)
        }
    }

    private fun handleStateFavorite(state: UIState?) {
        handleResponseState<Boolean>(state) {
            baseViewModel.getAllListLocalOrderBy()
        }
    }

    private fun handleStateFavoriteList(state: UIState?) {
        handleResponseState<List<ListCharacter?>?>(state) {
            baseViewModel.setListItemFavorite(it)
            baseViewModel.getAllCharacterFromAPI(viewBinding?.searchBar?.text.toString())
        }
    }

    private fun moveToFavoriteList() {
        (requireActivity() as MainActivity).changeFragment(FavoritePageFragment())
    }
}