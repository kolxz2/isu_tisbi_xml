package ru.nikolas_snek.isu_tisbi_xml.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ru.nikolas_snek.isu_tisbi_xml.R.layout.activity_auth)
        val viewPager = findViewById<ViewPager>(ru.nikolas_snek.isu_tisbi_xml.R.id.viewPager)

        val pagerAdapter = AuthenticationPagerAdapter(
            supportFragmentManager
        )
        pagerAdapter.addFragment(LoginFragment())
        pagerAdapter.addFragment(RegisterFragment())
        viewPager.adapter = pagerAdapter
    }
}