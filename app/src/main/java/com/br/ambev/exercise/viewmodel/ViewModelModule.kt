package com.br.ambev.exercise.viewmodel

import androidx.lifecycle.ViewModel
import com.br.ambev.exercise.product.viewmodel.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindProductViewmodel(viewModel: ProductViewModel): ViewModel
}
