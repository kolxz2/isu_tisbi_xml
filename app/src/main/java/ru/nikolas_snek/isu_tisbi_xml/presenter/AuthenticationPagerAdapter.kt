package ru.nikolas_snek.isu_tisbi_xml.presenter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


internal class AuthenticationPagerAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {
    private val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}