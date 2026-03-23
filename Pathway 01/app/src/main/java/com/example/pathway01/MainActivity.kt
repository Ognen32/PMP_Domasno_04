package com.example.pathway01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pathway01.ui.theme.Orange
import com.example.pathway01.ui.theme.Pathway01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pathway01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Pathway1Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class Item(val id: Int, val title: String, val description: String)

val sampleItems = (1..15).map { i ->
    Item(id = i, title = "Ставка #$i", description = "Опис за ставка број $i")
}

@Composable
fun Pathway1Screen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item { Spacer(Modifier.height(8.dp)) }

        item {
            Text(
                text = "Pathway 1",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Text(
                text = "Compose Essentials",
                fontSize = 16.sp,
                color = Orange,
                fontWeight = FontWeight.Medium
            )
        }

        item {
            SectionLabel("Вежба 1 — Composable функции")
            Spacer(Modifier.height(8.dp))
            GreetingCard(name = "Огнен")
        }

        item {
            SectionLabel("Вежба 2 — State и remember")
            Spacer(Modifier.height(8.dp))
            CounterCard()
        }

        item {
            SectionLabel("Вежба 3 — AnimatedVisibility")
            Spacer(Modifier.height(8.dp))
            ToggleCard()
        }

        item {
            SectionLabel("Вежба 4 — LazyColumn листа")
            Spacer(Modifier.height(8.dp))
        }

        items(sampleItems) { item ->
            ListItemCard(item = item)
        }

        item { Spacer(Modifier.height(16.dp)) }
    }
}

@Composable
fun SectionLabel(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(18.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Orange)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,  // <-- ова промени го
            letterSpacing = 0.5.sp
        )
    }
}

@Composable
fun GreetingCard(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C1C1C))
            .border(1.dp, Color(0xFF2E2E2E), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "Здраво, $name! 👋",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Добредојде во Jetpack Compose",
                fontSize = 14.sp,
                color = Color(0xFF9E9E9E)
            )
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF2A1500))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "🔥 Compose активен",
                    fontSize = 12.sp,
                    color = Orange,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun CounterCard() {
    var count by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C1C1C))
            .border(1.dp, Color(0xFF2E2E2E), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$count",
                fontSize = 56.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Orange
            )
            Text(
                text = "пати кликнато",
                fontSize = 14.sp,
                color = Color(0xFF9E9E9E)
            )
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = { count++ },
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+ Додај", fontWeight = FontWeight.Bold, color = Color.White)
                }
                OutlinedButton(
                    onClick = { count = 0 },
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Orange),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ресетирај", color = Orange)
                }
            }
        }
    }
}

@Composable
fun ToggleCard() {
    var visible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C1C1C))
            .border(1.dp, Color(0xFF2E2E2E), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Button(
            onClick = { visible = !visible },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Orange),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                if (visible) "▲ Скриј содржина" else "▼ Прикажи содржина",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        AnimatedVisibility(visible = visible) {
            Column {
                Spacer(Modifier.height(12.dp))
                HorizontalDivider(color = Color(0xFF2E2E2E))
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "✅ Оваа содржина се прикажува со анимација!",
                    fontSize = 14.sp,
                    color = Color(0xFFCCCCCC),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun ListItemCard(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1C1C1C))
            .border(1.dp, Color(0xFF2A2A2A), RoundedCornerShape(12.dp))
            .clickable { }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFF2A1500)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${item.id}",
                color = Orange,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Spacer(Modifier.width(14.dp))
        Column {
            Text(
                text = item.title,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 15.sp
            )
            Text(
                text = item.description,
                color = Color(0xFF777777),
                fontSize = 12.sp
            )
        }
        Spacer(Modifier.weight(1f))
        Text(text = "›", color = Color(0xFF444444), fontSize = 20.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun GreetingCardPreview() {
    Pathway01Theme { GreetingCard(name = "Огнен") }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun CounterCardPreview() {
    Pathway01Theme { CounterCard() }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun ToggleCardPreview() {
    Pathway01Theme { ToggleCard() }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun ListItemCardPreview() {
    Pathway01Theme {
        ListItemCard(item = Item(1, "Ставка #1", "Опис за ставка број 1"))
    }
}