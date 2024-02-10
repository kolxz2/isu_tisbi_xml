@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.personal_plan


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Semester
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Subject
@Composable
fun MyApp(modifier: Modifier = Modifier, viewModel: PersonalPlanViewModel) {


    val subjects by viewModel.semesterSubjects.observeAsState()
    val selectedSemester by viewModel.selectedSemester.observeAsState(Semester(current = true, name = "Неопределён", number = 100))
    val listSemesters by viewModel.semesterTitles.observeAsState()
    val loadingStatus by viewModel.loadingStatus.observeAsState(false)

    Surface(
        modifier,
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 0.dp
    ) {
        if (!loadingStatus){
            Column(modifier = Modifier.fillMaxWidth()) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp),
                    color = Color.Red //progress color
                )
            }
        } else {
            Column {
                TestingBottomSheet(
                    selectedSemester = selectedSemester,
                    listSemester = listSemesters!!,
                    changeSemester = {viewModel.changeSelectedSemester(it)}
                )
                LazyColumn(
                    modifier = modifier
                        .fillMaxHeight(),

                    ) {
                    itemsIndexed(subjects!!) {index, subject ->
                        //todo ролик про ключи и список с подписками
                        Greeting(subject = subject, changeVisibility = {
                            viewModel.changeSubjectVisibility(it)
                        })
                        if (subjects!!.size == index+1) Spacer(Modifier.height(100.dp))
                    }
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Greeting(subject: Subject, changeVisibility: (Subject) -> Unit) {
    Card(

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        onClick = { changeVisibility(subject) }
    ) {
        CardContent(subject, changeVisibility = {
            changeVisibility(it)
        })
    }
}


/*@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)*/

/*@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}
*/

