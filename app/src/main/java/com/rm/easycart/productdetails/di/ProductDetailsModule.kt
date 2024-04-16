package com.rm.easycart.productdetails.di

import com.rm.easycart.productdetails.repository.CartRepository
import com.rm.easycart.productdetails.repository.CartRepositoryImpl
import com.rm.easycart.productdetails.repository.ProductDetailsRepository
import com.rm.easycart.productdetails.repository.ProductDetailsRepositoryImpl
import com.rm.easycart.productdetails.usecase.AddToCartUseCase
import com.rm.easycart.productdetails.usecase.AddToCartUseCaseImpl
import com.rm.easycart.productdetails.usecase.GetProductDetailsByIdUseCase
import com.rm.easycart.productdetails.usecase.GetProductDetailsByIdUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ProductDetailsModule {
    @Binds
    abstract fun bindProductDetailsRepository(repo: ProductDetailsRepositoryImpl): ProductDetailsRepository

    @Binds
    abstract fun bindGetProductDetailsByIdUseCase(useCase: GetProductDetailsByIdUseCaseImpl): GetProductDetailsByIdUseCase

    @Binds
    abstract fun bindAddToCartUseCase(useCase: AddToCartUseCaseImpl): AddToCartUseCase

    @Binds
    abstract fun bindCartRepository(repo: CartRepositoryImpl): CartRepository

}