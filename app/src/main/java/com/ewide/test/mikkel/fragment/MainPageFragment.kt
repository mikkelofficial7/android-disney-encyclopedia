package com.ewide.test.mikkel.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.mikkel.base.BaseFragmentVM
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.databinding.FragmentMainPageBinding
import com.ewide.test.mikkel.extension.observe
import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.viewmodel.CharacterListVM

class MainPageFragment : BaseFragmentVM<FragmentMainPageBinding, CharacterListVM>(CharacterListVM::class) {
    private val rvAdapter by lazy {
        ItemAdapter<ListCharacterResponse>()
    }

    override fun bindToolbar(): Toolbar? = viewBinding?.customToolbar?.getToolbar()

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        baseViewModel.getAllDisneyCharacter()
    }

    override fun onReExecute() {
    }

    override fun initUiListener() {
        viewBinding?.rvCharacter?.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = rvAdapter
        }
    }

    override fun observeViewModel(viewModel: CharacterListVM) {
        observe(viewModel.getCharacterListStateEvent(), ::handleState)
    }

    private fun handleState(state: UIState?) {
        handleResponseState<List<ListCharacterResponse?>?>(state) {

        }
    }
}