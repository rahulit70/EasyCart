package com.rm.easycart.cart.di

import com.rm.easycart.cart.repository.CartRepository
import com.rm.easycart.cart.repository.CartRepositoryImpl
import com.rm.easycart.cart.usecase.LoadCartUseCase
import com.rm.easycart.cart.usecase.LoadCartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class CartListModule {
    @Binds
    abstract fun bindCartRepository(cartRepository: CartRepositoryImpl): CartRepository

    @Binds
    abstract fun bindLoadCartUseCase(useCase: LoadCartUseCaseImpl): LoadCartUseCase

}