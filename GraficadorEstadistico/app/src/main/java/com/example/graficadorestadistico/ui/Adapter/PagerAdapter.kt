package com.example.graficadorestadistico.ui.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var fragments:ArrayList<Fragment> = ArrayList()
    private var titulos:ArrayList<String> = ArrayList()

    fun resetList(){
        this.fragments.clear()
        this.titulos.clear()
    }

    public fun setTab(fragment:Fragment, titulo:String){
        this.fragments.add(fragment)
        this.titulos.add(titulo)
    }

    fun setearListas(fragments:ArrayList<Fragment>, titulos:ArrayList<String>){
        this.fragments = fragments
        this.titulos = titulos
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titulos.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }
}