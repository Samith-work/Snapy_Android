package com.lavariyalabs.snapy.android.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { CompactBottomNavigation() },
        containerColor = Color(0xFF121212)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            SubjectDropdownSelector()

            Spacer(modifier = Modifier.height(24.dp))

            // Term 1 Flow: 1 -> 2 -> 3
            TermSectionWithArrows(title = "1 st term", unitStart = 1)

            Spacer(modifier = Modifier.height(40.dp))

            // Term 2 Flow: 4 -> 5 -> 6
            TermSectionWithArrows(title = "2 nd term", unitStart = 4)

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun TermSectionWithArrows(title: String, unitStart: Int) {
    Column {
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                .padding(vertical = 10.dp, horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(modifier = Modifier.fillMaxWidth().height(320.dp)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 3.dp.toPx()

                // 1. STRAIGHT ARROW: Unit 1 -> Unit 2
                drawArrow(
                    start = Offset(size.width * 0.32f, size.height * 0.18f),
                    end = Offset(size.width * 0.68f, size.height * 0.18f),
                    color = Color.White,
                    strokeWidth = strokeWidth
                )

                // 2. ELBOW ARROW: Unit 2 -> Unit 3
                // This draws a line down from Unit 2, then left to Unit 3
                val elbowCorner = Offset(size.width * 0.78f, size.height * 0.72f)
                val elbowEnd = Offset(size.width * 0.38f, size.height * 0.72f)

                // Vertical part down
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.78f, size.height * 0.35f),
                    end = elbowCorner,
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )

                // Horizontal part left with Arrow Head
                drawArrow(
                    start = elbowCorner,
                    end = elbowEnd,
                    color = Color.White,
                    strokeWidth = strokeWidth
                )
            }

            // Units positioned to match the elbow flow
            UnitCard(
                modifier = Modifier.size(95.dp).align(Alignment.TopStart).offset(x = 5.dp),
                unitNumber = unitStart
            )

            UnitCard(
                modifier = Modifier.size(95.dp).align(Alignment.TopEnd).offset(x = (-5).dp),
                unitNumber = unitStart + 1
            )

            UnitCard(
                modifier = Modifier.size(95.dp).align(Alignment.BottomStart).offset(x = 5.dp, y = (-20).dp),
                unitNumber = unitStart + 2
            )
        }
    }
}

// --- SUPPORTING COMPONENTS ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDropdownSelector() {
    val subjects = listOf("Mathematics", "Science", "History", "ICT")
    var expanded by remember { mutableStateOf(false) }
    var selectedSubject by remember { mutableStateOf(subjects[0]) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        OutlinedTextField(
            value = selectedSubject,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.menuAnchor().width(180.dp).height(52.dp),
            shape = RoundedCornerShape(8.dp)
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.background(Color(0xFF1E1E1E))) {
            subjects.forEach { subject ->
                DropdownMenuItem(text = { Text(subject, color = Color.White) }, onClick = { selectedSubject = subject; expanded = false })
            }
        }
    }
}

@Composable
fun UnitCard(modifier: Modifier = Modifier, unitNumber: Int) {
    Card(
        modifier = modifier.border(2.dp, Color.White, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "unit - $unitNumber", color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun CompactBottomNavigation() {
    Surface(color = Color(0xFF1E1E1E), modifier = Modifier.height(60.dp)) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
            IconButton(onClick = { }) { Icon(Icons.Default.Home, "Home", tint = Color.White, modifier = Modifier.size(28.dp)) }
            IconButton(onClick = { }) { Icon(Icons.Default.Person, "Profile", tint = Color.White, modifier = Modifier.size(28.dp)) }
        }
    }
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawArrow(
    start: Offset, end: Offset, color: Color, strokeWidth: Float
) {
    drawLine(color = color, start = start, end = end, strokeWidth = strokeWidth, cap = StrokeCap.Round)
    val angle = atan2((end.y - start.y), (end.x - start.x))
    val arrowSize = 25f
    val path = Path().apply {
        moveTo(end.x, end.y)
        lineTo((end.x - arrowSize * cos(angle - 0.5f)).toFloat(), (end.y - arrowSize * sin(angle - 0.5f)).toFloat())
        moveTo(end.x, end.y)
        lineTo((end.x - arrowSize * cos(angle + 0.5f)).toFloat(), (end.y - arrowSize * sin(angle + 0.5f)).toFloat())
    }
    drawPath(path = path, color = color, style = Stroke(width = strokeWidth))
}