package com.ewide.test.mikkel.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.ewide.test.mikkel.base.BaseFragmentVM
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.databinding.FragmentMainPageBinding
import com.ewide.test.mikkel.extension.observe
import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.viewmodel.CharacterListVM

class MainPageFragment : BaseFragmentVM<FragmentMainPageBinding, CharacterListVM>(CharacterListVM::class) {
    override fun bindToolbar(): Toolbar? = null

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        baseViewModel.getAllDisneyCharacter(1)
    }

    override fun onReExecute() {
    }

    override fun initUiListener() {
    }

    override fun observeViewModel(viewModel: CharacterListVM) {
        observe(viewModel.getCharacterListStateEvent(), ::handleState)
    }

    private fun handleState(state: UIState?) {
        handleResponseState<ListCharacterResponse>(state) {

        }
    }
}