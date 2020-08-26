package com.br.ambev.exercise.base

import android.content.Context
import com.br.ambev.exercise.product.ui.ProductActivity
import com.br.ambev.exercise.network.NetworkModule
import com.br.ambev.exercise.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, ViewModelModule::class]
)
interface AppComponent {

    fun inject(application: MyApplication)
    fun inject(productActivity: ProductActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindAppContext(application: Context): Builder
        fun build(): AppComponent
    }
}

fun MyApplication.inject(): AppComponent {
    val component = DaggerAppComponent.builder()
        .bindAppContext(this)
        .build()
    component.inject(this)
    return component
}
