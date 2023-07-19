package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.student_profile

import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseViewModel

class StudentProfileViewModel (
    private val repositoryImpl: UserRepositoryImpl
) : BaseViewModel(repositoryImpl){
}