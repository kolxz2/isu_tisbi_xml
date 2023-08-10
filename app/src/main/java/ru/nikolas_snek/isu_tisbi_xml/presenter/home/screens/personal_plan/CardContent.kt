package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.personal_plan

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HourglassFull
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Checkpoint
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Subject

@Composable
fun CardContent(subject: Subject, changeVisibility: (Subject) -> Unit) {
    /*    var expanded by remember { mutableStateOf(false) }*/

    Row(
        modifier = Modifier
            .padding(10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        ) {
            Text(
                text = subject.name,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
            if (subject.isSelected) {
                for (item in subject.checkpoints) {
                    ModuleInfo(item)
                }
            }
        }
        IconButton(onClick = { changeVisibility(subject) }) {
            Icon(
                imageVector = if (subject.isSelected) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (subject.isSelected) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Preview
@Composable
fun ModuleInfo(
    checkpoint: Checkpoint = Checkpoint(
        checkPointName = "Модульный курс",
        CPFinalResultDate = "2023-02-27",
        typeName = "Прошл",
        finalResult = "90",
        staffName = "Шафигуллина Ю.В.",
        semNumber = 8
    ),
) {
    val icon = if (checkpoint.typeName == "Прошел")
        Icons.Filled.Done
    else
        Icons.Filled.HourglassFull

    val dateOfPassing = if (checkpoint.CPFinalResultDate.isNullOrEmpty())
        ""
    else
        checkpoint.CPFinalResultDate.toString()

    Row(Modifier.padding(vertical = 5.dp)) {
        Icon(
            icon,
            "modulePassing",
            tint = if (checkpoint.typeName == "Прошел") Color(0xFF66E66B) else Color(0xFFEED94E),
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        Column(Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = checkpoint.checkPointName.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Оценка: ${checkpoint.finalResult.toString()}",
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = "Дата сдачи: $dateOfPassing",
                color = MaterialTheme.colorScheme.onPrimary,
            )

        }
    }
}
