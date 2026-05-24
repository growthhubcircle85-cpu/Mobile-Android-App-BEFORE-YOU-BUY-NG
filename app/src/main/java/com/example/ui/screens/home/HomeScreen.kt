package com.example.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.DisclaimerCard
import com.example.ui.components.EditorialCard
import com.example.ui.components.EditorialMasthead
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateToCheck: () -> Unit,
    onNavigateToRedFlags: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Masthead
        EditorialMasthead()

        // Hero Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.2.dp, SoftGold.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Editorial Label
                Text(
                    text = "NIGERIAN CONSUMER CIVIC PROTECTION",
                    style = MaterialTheme.typography.labelMedium,
                    color = SoftGold,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Primary Headline
                Text(
                    text = "Before you pay for property, know what to ask.",
                    style = MaterialTheme.typography.headlineLarge,
                    color = LightText,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Emotional Core Message Quote Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(DeepCharcoal)
                        .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "“You do not need to rush. You need to understand before paying.”",
                        style = MaterialTheme.typography.bodyLarge,
                        color = SoftGold,
                        fontWeight = FontWeight.Bold,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Subheadline
                Text(
                    text = "Practical property guidance designed to help Nigerians and diaspora buyers navigate land and property decisions more carefully before making payment.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightText.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Primary CTA
                Button(
                    onClick = onNavigateToCheck,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("start_property_check_cta"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightMutedGreen,
                        contentColor = LightText
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Start Property Check",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Navigate to property questionnaire stepper"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Secondary CTA
                OutlinedButton(
                    onClick = onNavigateToRedFlags,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("explore_red_flags_cta"),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = SoftGold
                    ),
                    border = BorderStroke(1.2.dp, SoftGold.copy(alpha = 0.8f)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Explore Common Red Flags",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Section: Why We Built This
        EditorialCard {
            Text(
                text = "CIVIC MISSION",
                style = MaterialTheme.typography.labelMedium,
                color = MutedGreen,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            SectionHeader(
                title = "Why Before You Buy NG Exists",
                subtitle = "Protecting Nigerian household savings from unverified land transactions."
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Too many Nigerians and diaspora families lose money because they do not know what questions to ask before paying for land or property. Before You Buy NG was created to simplify confusing property processes, highlight common warning signs, and help people make more informed decisions before committing their money.",
                style = MaterialTheme.typography.bodyLarge,
                color = DarkText,
                lineHeight = 22.sp
            )
        }

        // Section: Trust Section (What We Help You Do)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.08f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "CIVIC INFORMATION PORTAL",
                    style = MaterialTheme.typography.labelSmall,
                    color = SoftGold,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "What Before You Buy NG Helps You Do",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LightText,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Card 1
                TrustRowCard(
                    title = "Understand the Process",
                    sentence = "Learn the basic steps involved before making payment for land or property.",
                    icon = Icons.Default.Rule
                )

                // Card 2
                TrustRowCard(
                    title = "Spot Common Red Flags",
                    sentence = "Identify warning signs that commonly appear in risky property transactions.",
                    icon = Icons.Default.Info
                )

                // Card 3
                TrustRowCard(
                    title = "Know Who to Speak To",
                    sentence = "Understand when you may need a lawyer, surveyor, or official agency.",
                    icon = Icons.Default.LocalLibrary
                )
            }
        }

        // Global Static Disclaimer Card
        DisclaimerCard(isDarkTheme = true)

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun TrustRowCard(
    title: String,
    sentence: String,
    icon: ImageVector
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CardBorder.copy(alpha = 0.08f), RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = DeepCharcoal)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MutedGreen.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "$title icon",
                    tint = SoftGold,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = LightText,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = sentence,
                    style = MaterialTheme.typography.bodyMedium,
                    color = LightText.copy(alpha = 0.6f),
                    lineHeight = 18.sp
                )
            }
        }
    }
}
