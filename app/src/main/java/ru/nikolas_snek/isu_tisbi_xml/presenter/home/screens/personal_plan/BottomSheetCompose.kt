import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Semester


data class BottomSheetItem(val title: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun BottomSheetDemo(
    listSemester: List<Semester>,
    selectedSemester: Semester,
    changeSemester: (Int) -> Unit,
) {
    val bottomSheetItems = listOf(
        BottomSheetItem(title = "Notification", icon = Icons.Default.Notifications),
        BottomSheetItem(title = "Mail", icon = Icons.Default.MailOutline),
        BottomSheetItem(title = "Scan", icon = Icons.Default.Search),
        BottomSheetItem(title = "Edit", icon = Icons.Default.Edit),
        BottomSheetItem(title = "Favorite", icon = Icons.Default.Favorite),
        BottomSheetItem(title = "Settings", icon = Icons.Default.Settings)
    )

    //Lets define bottomSheetScaffoldState which will hold the state of Scaffold
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState =  rememberStandardBottomSheetState()
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(30.dp),
        sheetContent = {
            Column(
                content = {

                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "Bottom Sheet",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    LazyVerticalGrid(
                        //cells = GridCells.Fixed(3)
                        columns = GridCells.Fixed(3), //https://developer.android.com/jetpack/compose/lists
                    ) {
                        items(bottomSheetItems.size, itemContent = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .clickable {


                                    },
                            ) {
                                Spacer(modifier = Modifier.padding(8.dp))
                                Icon(
                                    bottomSheetItems[it].icon,
                                    bottomSheetItems[it].title,
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(text = bottomSheetItems[it].title, color = Color.White)
                            }
                            if (bottomSheetItems.size == it) Spacer(modifier = Modifier.height(100.dp))

                        })
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(350.dp)

                    //.background(Color(0xFF6650a4))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF8E2DE2),
                                Color(0xFF4A00E0)
                            )
                        ),
                        // shape = RoundedCornerShape(cornerRadius)
                    )
                    .padding(16.dp),

                )
        },
        sheetPeekHeight = 0.dp,

    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)) {
            Card(
                //  border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
                onClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isVisible) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.hide()
                        }
                    }
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
                                .clickable { coroutineScope.launch {
                                    if (bottomSheetScaffoldState.bottomSheetState.isVisible) {
                                        bottomSheetScaffoldState.bottomSheetState.expand()
                                    } else {
                                        bottomSheetScaffoldState.bottomSheetState.hide()
                                    }
                                } },
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
                }
            }
        }
    }
}