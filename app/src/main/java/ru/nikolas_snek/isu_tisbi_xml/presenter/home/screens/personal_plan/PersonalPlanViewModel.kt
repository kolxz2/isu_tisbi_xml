package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.personal_plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.domain.GetPersonalPlan
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.PersonalPlan
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Semester
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Subject
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseViewModel

class PersonalPlanViewModel(
    private val repositoryImpl: UserRepositoryImpl,
) : BaseViewModel(repositoryImpl) {

    private val getPersonalPlan = GetPersonalPlan(repositoryImpl)

    private lateinit var _personalPlan: HashMap<Semester, MutableList<Subject>>

    private var _semesterTitles: MutableLiveData<List<Semester>> = MutableLiveData<List<Semester>>()
    val semesterTitles: LiveData<List<Semester>>
        get() = _semesterTitles

    private var _selectedSemester: MutableLiveData<Semester> = MutableLiveData<Semester>()
    val selectedSemester: LiveData<Semester>
        get() = _selectedSemester

    private var _semesterSubjects: MutableLiveData<List<Subject>> = MutableLiveData<List<Subject>>()
    val semesterSubjects: LiveData<List<Subject>>
        get() = _semesterSubjects

     var  loadingStatus: MutableLiveData<Boolean> = MutableLiveData(false)

    suspend fun initViewModal() {

        // todo просмотреть на оптимизацию
        getPersonalPlanList()
        initSelectedSemester()
        getSemesterTitle()
    }

    suspend fun getPersonalPlanList() {
       /* delay(2000)
        _personalPlan = semester*/
         _personalPlan = getPersonalPlan.getPersonalPlan()
    }

    private fun getSemesterTitle() {
//        val semesterTitles = mutableListOf<Semester>()
//        _personalPlan.map { (key, _) ->
//            semesterTitles.add(key)
//        }
//
        _semesterTitles.value = _personalPlan.keys.toList().sortedBy { it.number }
        loadingStatus.value = true
    }

    private fun initSelectedSemester() {
        _personalPlan
        _personalPlan.map { (key, value) ->
            if (key.current) {
                _selectedSemester.value = key
                _semesterSubjects.value = value
                return
            }
        }
        throw Exception("Нет текущего семестра")
    }

    fun changeSelectedSemester(position: Int) {
        _selectedSemester.value = _semesterTitles.value!![position]
        _semesterSubjects.value = _personalPlan[_selectedSemester.value]
    }

    fun changeSubjectVisibility(editedSubject: Subject) {
        // todo метод изменения в предмете поля isVisible
        val semesterSubjects = _semesterSubjects.value!!.toMutableList()
        semesterSubjects.replaceAll {
            if (it == editedSubject) {
                it.copy(isSelected = !it.isSelected)
            } else {
                it
            }
        }
        _semesterSubjects.value = semesterSubjects
    }
}