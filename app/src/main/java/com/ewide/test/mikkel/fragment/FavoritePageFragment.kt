package com.ewide.test.mikkel.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.ewide.test.mikkel.base.BaseFragmentVM
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.databinding.FragmentDetailPageBinding
import com.ewide.test.mikkel.extension.observe
import com.ewide.test.mikkel.viewmodel.CharacterFavoriteVM

class FavoritePageFragment : BaseFragmentVM<FragmentDetailPageBinding, CharacterFavoriteVM>(CharacterFavoriteVM::class) {
    override fun bindToolbar(): Toolbar? = viewBinding?.customToolbar?.getToolbar()

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): FragmentDetailPageBinding {
        return FragmentDetailPageBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        baseViewModel.getAllListOrderBy(true)
    }

    override fun onReExecute() {}

    override fun initUiListener() {}

    override fun observeViewModel(viewModel: CharacterFavoriteVM) {
        observe(viewModel.getCharacterFavoriteStateEvent(), ::handleState)
    }

    private fun handleState(state: UIState?) {

    }
}