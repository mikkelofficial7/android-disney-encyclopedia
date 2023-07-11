package com.ewide.test.mikkel.route

import android.os.Bundle
import com.ewide.test.mikkel.MainActivity
import com.ewide.test.mikkel.fragment.DetailPageFragment

object Routing {
    fun moveToDetailPage(activity: MainActivity, id: String) {
        val bundle = Bundle().apply {
            putString("GAME_ID", id)
        }

        activity.changeFragment(DetailPageFragment().apply {
            arguments = bundle
        })
    }
}