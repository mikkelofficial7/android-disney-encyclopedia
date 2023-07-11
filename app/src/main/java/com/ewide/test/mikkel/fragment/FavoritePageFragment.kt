package com.ewide.test.mikkel.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.ewide.test.mikkel.R
import com.ewide.test.mikkel.base.BaseFragmentVM
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.databinding.FragmentFavoritePageBinding
import com.ewide.test.mikkel.extension.observe
import com.ewide.test.mikkel.viewmodel.CharacterFavoriteVM

class FavoritePageFragment : BaseFragmentVM<FragmentFavoritePageBinding, CharacterFavoriteVM>(CharacterFavoriteVM::class) {
    override fun bindToolbar(): Toolbar? = viewBinding?.customToolbar?.getToolbar()

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): FragmentFavoritePageBinding {
        return FragmentFavoritePageBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        baseViewModel.getAllListOrderBy(true)
    }

    override fun onReExecute() {}

    override fun initUiListener() {
        viewBinding?.rgSort?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rbAsc -> {

                }
                R.id.rbDesc -> {

                }
            }
        }
    }

    override fun observeViewModel(viewModel: CharacterFavoriteVM) {
        observe(viewModel.getCharacterFavoriteStateEvent(), ::handleState)
    }

    private fun handleState(state: UIState?) {

    }
}