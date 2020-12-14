package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import ru.kozirfm.f1news.data.repositories.Repository

class NewsViewModel : ViewModel() {

    val repositoryNews = Repository.getArticlesPage().cachedIn(viewModelScope)

}