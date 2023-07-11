package com.ewide.test.mikkel.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.ewide.test.mikkel.R
import com.ewide.test.mikkel.base.BaseFragmentVM
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.databinding.FragmentDetailPageBinding
import com.ewide.test.mikkel.extension.observe
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.viewmodel.GamesDetailVM

class DetailPageFragment : BaseFragmentVM<FragmentDetailPageBinding, GamesDetailVM>(GamesDetailVM::class) {
    private val gameId by lazy {
        arguments?.getString("GAME_ID", "").orEmpty()
    }

    override fun bindToolbar(): Toolbar? = viewBinding?.customToolbar?.getToolbar()

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): FragmentDetailPageBinding {
        return FragmentDetailPageBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        baseViewModel.getDetailDisneyCharacter(gameId)
    }

    override fun onReExecute() {}

    override fun initUiListener() {}

    override fun observeViewModel(viewModel: GamesDetailVM) {
        observe(viewModel.getCharacterDetailStateEvent(), ::handleState)
    }

    private fun handleState(state: UIState?) {
        handleResponseState<OneCharacterResponse>(state) {
            viewBinding?.apply {
                customToolbar.setToolbarTitle(it.info.title)

                Glide.with(requireContext()).load(it.info.thumb).into(characterLogo)
                characterName.text = it.info.title
                characterPrice.text = resources.getString(R.string.cheapest_price, it.cheapestPriceEver.price)
            }
        }
    }
}