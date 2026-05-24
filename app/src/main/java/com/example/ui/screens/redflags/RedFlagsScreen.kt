package com.example.ui.screens.redflags

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.example.data.staticcontent.RedFlag
import com.example.data.staticcontent.StaticData
import com.example.ui.components.DisclaimerCard
import com.example.ui.components.EditorialMasthead
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun RedFlagsScreen(
    modifier: Modifier = Modifier
) {
    var selectedFlag by remember { mutableStateOf<RedFlag?>(null) }
    val scrollState = rememberScrollState()

    if (selectedFlag != null) {
        BackHandler {
            selectedFlag = null
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Masthead
            EditorialMasthead(
                titleText = "CIVIC ADVISORY RED FLAGS",
                subtitleText = "TRANSACTION INDICATORS THAT WILL SAVE YOUR LIFE"
            )

            // Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.08f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "CIVIC ADVISORY ARCHIVES",
                        style = MaterialTheme.typography.labelSmall,
                        color = SoftGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Common Red Flags",
                        style = MaterialTheme.typography.headlineLarge,
                        color = LightText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rushing is the primary tool used by property scammers. Study these indicators carefully. If any of these signs appear in your transaction, pause immediately.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = LightText.copy(alpha = 0.7f),
                        lineHeight = 20.sp
                    )
                }
            }

            SectionHeader(title = "Red Flag Glossary", subtitle = "Tap on any hazard card to examine explanations and protective actions.", lightStyle = true)

            // Dynamic list of warnings
            StaticData.redFlags.forEach { flag ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("red_flag_card_${flag.id}")
                        .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
                        .clickable { selectedFlag = flag },
                    colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(WarningRed.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Screener warning icon",
                                tint = WarningRed,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = flag.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = LightText,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = flag.shortSummary,
                                style = MaterialTheme.typography.bodyMedium,
                                color = LightText.copy(alpha = 0.6f),
                                lineHeight = 18.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Examine hazard details",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = SoftGold,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Global Safety Info
            DisclaimerCard(isDarkTheme = true)

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Beautiful embedded card overlay instead of Dialog to guarantee zero crashes on early APIs
        AnimatedVisibility(
            visible = selectedFlag != null,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier.fillMaxSize()
        ) {
            selectedFlag?.let { flag ->
                // Clean scrim background to block clicks to behind content and dim the background
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable(enabled = true, onClick = { selectedFlag = null }),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.85f)
                            .padding(top = 16.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                            .clickable(enabled = true, onClick = {}), // Swallows touch/clicks within the panel
                        color = MaterialTheme.colorScheme.surfaceVariant // Warm Ivory
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(24.dp)
                        ) {
                        // Title header
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(WarningRed, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Warning,
                                        contentDescription = "Alert",
                                        tint = LightText,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "HAZARD BRIEFING",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = WarningRed,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }

                            IconButton(
                                onClick = { selectedFlag = null },
                                modifier = Modifier
                                    .size(48.dp)
                                    .testTag("close_hazard_briefing_btn")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close dialog",
                                    tint = DarkText,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Title
                        Text(
                            text = flag.title,
                            style = MaterialTheme.typography.headlineLarge,
                            color = DarkText,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = flag.shortSummary,
                            style = MaterialTheme.typography.titleMedium,
                            color = MutedGreen,
                            fontFamily = FontFamily.Serif,
                            lineHeight = 22.sp,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )

                        HorizontalDivider(color = CardBorder)
                        Spacer(modifier = Modifier.height(16.dp))

                        // Detailed Explanation
                        Text(
                            text = "DEVELOPMENT OUTLINE",
                            style = MaterialTheme.typography.labelMedium,
                            color = MutedGray,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = flag.explanation,
                            style = MaterialTheme.typography.bodyLarge,
                            color = DarkText,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Protection Protocol Steps
                        Text(
                            text = "CIVIC PROTECTION STEPS",
                            style = MaterialTheme.typography.labelMedium,
                            color = MutedGreen,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        flag.actionSteps.forEachIndexed { idx, step ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(top = 4.dp, end = 12.dp)
                                        .size(18.dp)
                                        .background(MutedGreen, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = (idx + 1).toString(),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = LightText,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text(
                                    text = step,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = DarkText,
                                    lineHeight = 18.sp,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        DisclaimerCard(isDarkTheme = false)
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { selectedFlag = null },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MutedGreen,
                                contentColor = LightText
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("I Understand • Back to Library", fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}
}
