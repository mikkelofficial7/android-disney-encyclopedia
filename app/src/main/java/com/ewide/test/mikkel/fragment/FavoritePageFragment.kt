package com.ewide.test.mikkel.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.mikkel.MainActivity
import com.ewide.test.mikkel.R
import com.ewide.test.mikkel.adapter.ItemAdapter
import com.ewide.test.mikkel.base.BaseFragmentVM
import com.ewide.test.mikkel.base.state.UIState
import com.ewide.test.mikkel.databinding.FragmentFavoritePageBinding
import com.ewide.test.mikkel.extension.observe
import com.ewide.test.mikkel.model.local.ListCharacter
import com.ewide.test.mikkel.route.Routing
import com.ewide.test.mikkel.viewmodel.CharacterFavoriteVM

class FavoritePageFragment : BaseFragmentVM<FragmentFavoritePageBinding, CharacterFavoriteVM>(CharacterFavoriteVM::class) {
    private val rvAdapter by lazy {
        ItemAdapter<ListCharacter>()
    }

    override fun bindToolbar(): Toolbar? = viewBinding?.customToolbar?.getToolbar()

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): FragmentFavoritePageBinding {
        return FragmentFavoritePageBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        handleDefaultSelectSort()
    }

    override fun onReExecute() {}

    override fun initUiListener() {
        viewBinding?.rgSort?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rbAsc -> {
                    baseViewModel.saveSortingData(true)
                    handleDefaultSelectSort()
                }
                R.id.rbDesc -> {
                    baseViewModel.saveSortingData(false)
                    handleDefaultSelectSort()
                }
            }
        }

        viewBinding?.rvCharacter?.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = rvAdapter
        }

        rvAdapter.onAddToFavorite = {

        }

        rvAdapter.onClick = {
            Routing.moveToDetailPage(requireActivity() as MainActivity, it)
        }
    }

    override fun observeViewModel(viewModel: CharacterFavoriteVM) {
        observe(viewModel.getCharacterFavoriteStateEvent(), ::handleState)
    }

    private fun handleState(state: UIState?) {
        handleResponseState<List<ListCharacter?>?>(state) {
            rvAdapter.setCharacterData(it)
        }
    }

    private fun handleDefaultSelectSort() {
        val isAscendingSort = baseViewModel.getSortingData()
        baseViewModel.getAllListOrderBy(isAscendingSort)

        when(isAscendingSort) {
            true -> viewBinding?.rbAsc?.isChecked = true
            false -> viewBinding?.rbDesc?.isChecked = true
        }
    }
}