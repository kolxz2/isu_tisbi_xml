package ru.nikolas_snek.isu_tisbi_xml.presenter.home

import ru.nikolas_snek.isu_tisbi_xml.data.repository.BaseRepository
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseViewModel

class HomeViewModel(
    private val repositoryImpl: UserRepositoryImpl
) : BaseViewModel(repositoryImpl){
}