package com.rm.easycart.productlist.di

import com.rm.easycart.productlist.repository.ProductListRepo
import com.rm.easycart.productlist.repository.ProductListRepoImpl
import com.rm.easycart.productlist.usecase.GetProductListUseCase
import com.rm.easycart.productlist.usecase.GetProductListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ProductListModule {
    @Binds
    abstract fun bindProductListRepo(repo: ProductListRepoImpl): ProductListRepo

    @Binds
    abstract fun bindGetProductsUseCase(useCase: GetProductListUseCaseImpl): GetProductListUseCase
}