package com.reverse.number.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/*
 *      MainViewModel
 *      - viewModel that updates the MainFragment (the visible UI)
 *      - gets the data from model
 */
class MainViewModel: ViewModel() {
    var number: Int = 0
    var reverse: Int = 0


    // Create MutableLiveData which MainFragment can subscribe to
    // When this data changes, it triggers the UI to do an update
    val uiTextLiveData = MutableLiveData<String>()

    // Get the updated text from our model and post the value to MainFragment
    fun getReverseNumber(num: Int) {
      var reverse = reverseNumber(num)
        uiTextLiveData.postValue(reverse.toString())
    }
    fun reverseNumber(num: Int) : Int{
        number=num;
        while (number != 0) {
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }
        return reverse
    }
}