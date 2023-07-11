package com.ewide.test.mikkel.base

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ewide.test.mikkel.base.state.FailureState

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    var viewBinding: VB? = null
    private var mToolbar: Toolbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = getUiBinding()
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onFirstLaunch(savedInstanceState, view)
        setupToolbar()
        initUiListener()
    }

    override fun onStart() {
        super.onStart()
        onReExecute()
    }

    private fun setupToolbar() {
        bindToolbar()?.let {
            mToolbar = it
            (activity as AppCompatActivity).setSupportActionBar(mToolbar)

            (activity as AppCompatActivity).supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(enableBackButton())
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            }
        }
    }

    abstract fun bindToolbar(): Toolbar?
    abstract fun enableBackButton(): Boolean
    abstract fun getUiBinding(): VB
    abstract fun onFirstLaunch(savedInstanceState: Bundle?, view: View)
    abstract fun onReExecute()
    abstract fun initUiListener()

    fun getParentFm() = requireActivity().supportFragmentManager

    fun getChildFm() = childFragmentManager

    fun onBackPressed() {
        requireActivity().onBackPressed()
    }

    fun showProgress() {
        (requireActivity() as BaseActivity<*>).showProgressDialog()
    }

    fun hideProgress() {
        (requireActivity() as BaseActivity<*>).hideProgressDialog()
    }

    fun showToast(message: String) {
        (requireActivity() as BaseActivity<*>).showToast(message)
    }

    open fun handleFailure(failureState: FailureState?) {
        (requireActivity() as BaseActivity<*>).handleFailure(failureState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}