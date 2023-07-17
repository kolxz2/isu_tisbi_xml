package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens

import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseViewModel

class PersonalPlanViewModel (
    private val repositoryImpl: UserRepositoryImpl
) : BaseViewModel(repositoryImpl){
}