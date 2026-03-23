package com.example.pathway02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pathway02.ui.theme.Pathway02Theme

val Orange = Color(0xFFFF6B00)
val DarkBg = Color(0xFF0A0A0A)
val CardBg = Color(0xFF1C1C1C)
val BorderColor = Color(0xFF2E2E2E)
val TextSecondary = Color(0xFF9E9E9E)
val OrangeDim = Color(0xFF2A1500)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pathway02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Pathway2Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Pathway2Screen(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Layouts", "Animation", "Theming")

    Column(modifier = modifier.fillMaxSize()) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBg)
                .padding(16.dp)
        ) {
            Column {
                Text("Pathway 2", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                Text("Layouts, Theming & Animation", fontSize = 16.sp, color = Orange, fontWeight = FontWeight.Medium)
            }
        }

        // Tab Row
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = CardBg,
            contentColor = Orange
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            title,
                            color = if (selectedTab == index) Orange else TextSecondary,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        // Content
        when (selectedTab) {
            0 -> LayoutsTab()
            1 -> AnimationTab()
            2 -> ThemingTab()
        }
    }
}

// =====================
// TAB 1 — LAYOUTS
// =====================

@Composable
fun LayoutsTab() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SectionLabel("Row Layout")
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(3) { i ->
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(OrangeDim)
                            .border(1.dp, Orange, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("${i + 1}", color = Orange, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                }
            }
        }

        item {
            SectionLabel("Column Layout")
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardBg)
                    .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Прв елемент", "Втор елемент", "Трет елемент").forEach { text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Orange)
                        )
                        Text(text, color = Color.White, fontSize = 14.sp)
                    }
                }
            }
        }

        item {
            SectionLabel("Box Layout (Overlapping)")
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardBg)
                    .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
            ) {
                Text("Позади", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.padding(12.dp))
                Text(
                    "Центар",
                    color = Orange,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
                Text(
                    "Дно-десно",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                )
            }
        }

        item {
            SectionLabel("Weight Layout")
            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(OrangeDim)
                        .border(1.dp, Orange, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("1/4", color = Orange, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .weight(3f)
                        .height(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardBg)
                        .border(1.dp, BorderColor, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("3/4", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// =====================
// TAB 2 — ANIMATION
// =====================

@Composable
fun AnimationTab() {
    var visible by remember { mutableStateOf(true) }
    var isHighlighted by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var rotated by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isHighlighted) OrangeDim else CardBg,
        label = "bg_color"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isHighlighted) Orange else BorderColor,
        label = "border_color"
    )
    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 160.dp else 70.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "card_height"
    )
    val rotation by animateFloatAsState(
        targetValue = if (rotated) 360f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "rotation"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SectionLabel("AnimatedVisibility")
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { visible = !visible },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(if (visible) "▲ Скриј" else "▼ Прикажи", color = Color.White, fontWeight = FontWeight.Bold)
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardBg)
                        .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Text("✅ Елементот се анимира!", color = Color.White)
                }
            }
        }

        item {
            SectionLabel("animateColorAsState")
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor)
                    .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                    .clickable { isHighlighted = !isHighlighted }
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Кликни за промена на боја 🎨",
                    color = if (isHighlighted) Orange else Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        item {
            SectionLabel("animateDpAsState (expand/collapse)")
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardBg)
                    .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                    .clickable { expanded = !expanded }
                    .padding(16.dp)
            ) {
                Column {
                    Text("Кликни за проширување ↕", color = Orange, fontWeight = FontWeight.SemiBold)
                    if (expanded) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Ова е дополнителна содржина која се прикажува кога картичката е проширена. Пример за animateDpAsState со spring анимација.",
                            color = TextSecondary,
                            fontSize = 13.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }

        item {
            SectionLabel("animateFloatAsState (rotation)")
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(OrangeDim)
                        .border(1.dp, Orange, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "🔥",
                        fontSize = 32.sp,
                        modifier = Modifier.graphicsLayer { rotationZ = rotation }
                    )
                }
                Button(
                    onClick = { rotated = !rotated },
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Ротирај!", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// =====================
// TAB 3 — THEMING
// =====================

@Composable
fun ThemingTab() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SectionLabel("Typography")
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardBg)
                    .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Display Large", style = MaterialTheme.typography.displayLarge, color = Orange, fontSize = 20.sp)
                Text("Headline Medium", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Text("Title Large", style = MaterialTheme.typography.titleLarge, color = Color.White)
                Text("Body Medium", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                Text("Label Small", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            }
        }

        item {
            SectionLabel("Color Palette — 60:30:10")
            Spacer(Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ColorRow("60% — Crna (позадина)", DarkBg, "0xFF0A0A0A")
                ColorRow("30% — Темно сива (картички)", CardBg, "0xFF1C1C1C")
                ColorRow("10% — Портокалова (акцент)", Orange, "0xFFFF6B00")
            }
        }

        item {
            SectionLabel("Material Components")
            Spacer(Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Filled Button", color = Color.White, fontWeight = FontWeight.Bold) }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Orange)
                ) { Text("Outlined Button", color = Orange) }

                TextButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Text Button", color = Orange) }
            }
        }
    }
}

@Composable
fun ColorRow(label: String, color: Color, hex: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CardBg)
            .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color)
                .border(1.dp, BorderColor, RoundedCornerShape(8.dp))
        )
        Column {
            Text(label, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(hex, color = TextSecondary, fontSize = 11.sp)
        }
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
        Text(text, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White, letterSpacing = 0.5.sp)
    }
}

// =====================
// PREVIEWS
// =====================

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun LayoutsTabPreview() {
    Pathway02Theme { LayoutsTab() }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun AnimationTabPreview() {
    Pathway02Theme { AnimationTab() }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun ThemingTabPreview() {
    Pathway02Theme { ThemingTab() }
}