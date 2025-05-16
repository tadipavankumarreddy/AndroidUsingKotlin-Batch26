package com.nareshtech.shoemart.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nareshtech.shoemart.networking.ShoeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoeViewModel: ViewModel() {
    var shoe_list by mutableStateOf<List<Shoe>>(emptyList())
    var isLoading by mutableStateOf(true)

    init {
        fetchShoes()
    }

    fun fetchShoes(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val shoes = ShoeRepository.fetchShoes()
                shoe_list = shoes
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                isLoading = false
            }
        }
    }

    // TODO 9.0: Implementing DetailsScreen to display all the related information about a certain shoe item.
    fun getShoeById(id:Int?):Shoe?{
        return shoe_list.find { it.id == id }
    }
}