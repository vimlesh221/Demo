package com.reverse.number

import com.reverse.number.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ReverseNumberUnitText {
    private lateinit var viewModel:MainViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun reverseNumberText() {
       val number= viewModel.reverseNumber(12345)
        Assert.assertEquals(54321, number)
    }
}