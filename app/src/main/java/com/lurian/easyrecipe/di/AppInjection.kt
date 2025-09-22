package com.lurian.easyrecipe.di

import com.lurian.search.di.searchModule
import com.lurian.shared.SharedInjection
import org.koin.core.module.Module

object AppInjection {
    fun modules(): List<Module> = arrayListOf<Module>().apply {
        addAll(SharedInjection.modules())
        add(searchModule)
    }
}