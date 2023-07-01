package ru.nikolas_snek.isu_tisbi_xml.presenter.base

import androidx.lifecycle.ViewModel
import ru.nikolas_snek.isu_tisbi_xml.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
): ViewModel() {
}