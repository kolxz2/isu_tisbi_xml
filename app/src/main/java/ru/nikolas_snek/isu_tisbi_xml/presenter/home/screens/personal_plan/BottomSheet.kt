package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.personal_plan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Semester

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestingBottomSheet(
    listSemester: List<Semester>,
    selectedSemester: Semester,
    changeSemester: (Int) -> Unit,
) {
    // var selectedSemester by remember { mutableStateOf(viewModel.name) }
    val expanded = remember { mutableStateOf(false) }
    // val semesterTitles by viewModel.semesterTitles.observeAsState()

    val icon = if (expanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)) {
        Card(
            //  border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
            onClick = {
                expanded.value = !expanded.value
            },
            shape = RoundedCornerShape(size = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds()

        ) {
            Column(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondary

                    )
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),

                    ) {
                    Icon(
                        Icons.Filled.List,
                        "list",
                        Modifier
                            .clickable { expanded.value = !expanded.value },
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        color = MaterialTheme.colorScheme.primary,
                        text = selectedSemester.name,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                    Spacer(Modifier.weight(1f))
                }
                TutorialModalBottomSheet(
                    expanded,
                    listSemester,
                    selectedSemester,
                    changeSemester = {
                        changeSemester(it)
                    })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialModalBottomSheet(
    showModalBottomSheet: MutableState<Boolean>,
    listSemester: List<Semester>,
    selectedSemester: Semester,
    changeSemester: (Int) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val skipPartially by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartially)

    if (showModalBottomSheet.value)
        ModalBottomSheet(
            onDismissRequest = { showModalBottomSheet.value = false },
            sheetState = bottomSheetState,
            containerColor = MaterialTheme.colorScheme.secondary,
        ) {
            Column(Modifier.fillMaxSize()) {
                LazyColumn() {
                    itemsIndexed(items = listSemester, itemContent = { index, dataItem ->
                        ListItem(
                            modifier = Modifier
                                .height(57.dp)
                                .clickable {
                                    changeSemester(index)
                                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                        if (!bottomSheetState.isVisible) {
                                            showModalBottomSheet.value = false
                                        }
                                    }
                                },
                            supportingContent = {
                                if (dataItem.current) Text(
                                    "(текущий семестр)",
                                    color = MaterialTheme.colorScheme.primary
                                )
                            },
                            headlineContent = {
                                Text(
                                    dataItem.name,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            },
                            leadingContent = {
                                if (selectedSemester.name == dataItem.name) {
                                    Icon(
                                        Icons.Filled.NavigateNext,
                                        "contentDescription",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),

                        )
                        Divider(color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.4f), thickness = 1.dp)

                    })
                }
            }
        }
}

