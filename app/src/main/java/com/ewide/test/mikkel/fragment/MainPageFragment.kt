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
import com.ewide.test.mikkel.viewmodel.CharacterListVM

class MainPageFragment : BaseFragmentVM<FragmentMainPageBinding, CharacterListVM>(CharacterListVM::class) {
    private val rvAdapter by lazy {
        ItemAdapter<ListCharacterResponse>()
    }

    private var defaultSearch = "batman"

    override fun bindToolbar(): Toolbar? = viewBinding?.customToolbar?.getToolbar()

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        viewBinding?.searchBar?.setText(defaultSearch)
        baseViewModel.getAllDisneyCharacter(viewBinding?.searchBar?.text.toString())
    }

    override fun onReExecute() {
    }

    override fun initUiListener() {
        viewBinding?.rvCharacter?.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = rvAdapter
        }

        viewBinding?.searchBar?.startTyping {
            baseViewModel.getAllDisneyCharacter(it)
        }

        viewBinding?.customToolbar?.clickOnFavoriteButton {
            moveToFavoriteList()
        }

        rvAdapter.onAddToFavorite = {

        }

        rvAdapter.onClick = {
            moveToDetail(it)
        }
    }

    override fun observeViewModel(viewModel: CharacterListVM) {
        observe(viewModel.getCharacterListStateEvent(), ::handleState)
    }

    private fun handleState(state: UIState?) {
        handleResponseState<List<ListCharacterResponse?>?>(state) {
            rvAdapter.setCharacterData(it)
        }
    }

    private fun moveToDetail(id: String) {
        val bundle = Bundle().apply {
            putString("GAME_ID", id)
        }

        (requireActivity() as MainActivity).changeFragment(DetailPageFragment().apply {
            arguments = bundle
        })
    }

    private fun moveToFavoriteList() {
        (requireActivity() as MainActivity).changeFragment(FavoritePageFragment())
    }
}