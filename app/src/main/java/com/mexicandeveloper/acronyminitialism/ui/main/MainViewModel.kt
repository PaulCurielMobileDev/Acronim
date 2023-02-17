package com.mexicandeveloper.acronyminitialism.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mexicandeveloper.acronyminitialism.models.AbbreviationResponse
import com.mexicandeveloper.acronyminitialism.models.Resource
import com.mexicandeveloper.acronyminitialism.models.RowToShowModel
import com.mexicandeveloper.acronyminitialism.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val _res = MutableLiveData<Resource<List<RowToShowModel>>>()

    val res: LiveData<Resource<List<RowToShowModel>>>
        get() = _res

    public fun getAbreviationOrFullText(abreviation: String, fullForm: String) =
        viewModelScope.launch {
            _res.postValue(Resource.loading(null))
            mainRepository.getAbreviationOrAcronim(abreviation, fullForm).let {
                if (it.isSuccessful) {
                    val theList = it.body()
                    if (theList == null || theList.isEmpty()) {
                        _res.postValue(Resource.error("No Data to show", null))
                    } else {
                        val theAnswerList: MutableList<RowToShowModel> = mutableListOf()
                        for (i in theList) {
                            val oneRow = RowToShowModel(sf = i.sf, null)
                            theAnswerList.add(oneRow)
                            for (j in i.lfs) {
                                val anotherRow = RowToShowModel(sf = null, lf = j.lf)
                                theAnswerList.add(anotherRow)
                            }
                        }

                        _res.postValue(Resource.success(theAnswerList))
                    }
                } else {
                    _res.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }

        }
}