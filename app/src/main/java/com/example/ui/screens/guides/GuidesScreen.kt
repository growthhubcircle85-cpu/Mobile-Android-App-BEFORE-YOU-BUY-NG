package com.example.ui.screens.guides

import androidx.compose.animation.*
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
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MenuBook
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.staticcontent.StaticData
import com.example.ui.components.DisclaimerCard
import com.example.ui.components.DocumentAccordion
import com.example.ui.components.EditorialCard
import com.example.ui.components.EditorialMasthead
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

enum class GuideTab {
    DOCUMENTS,
    WHY_LOSS,
    REGIONAL
}

@Composable
fun GuidesScreen(
    modifier: Modifier = Modifier
) {
    var activeTab by rememberSaveable { mutableStateOf(GuideTab.DOCUMENTS) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Masthead
        EditorialMasthead(
            titleText = "CIVIC PROPERTY DATA ARCHIVE",
            subtitleText = "GUIDES, RISKS, AND LOCAL BUREAU PROTOCOLS"
        )

        // Core Library Header Card
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
                    text = "CIVIC PROPERTY DATA ARCHIVE",
                    style = MaterialTheme.typography.labelSmall,
                    color = SoftGold,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Property Guides",
                    style = MaterialTheme.typography.headlineLarge,
                    color = LightText
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Welcome to the digital civic library. This resource addresses custom laws, documentation significance, and procedural directories to slow down your buying journey safely.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightText.copy(alpha = 0.7f),
                    lineHeight = 20.sp
                )
            }
        }

        // Beautiful custom capsule selector row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(SurfaceCharcoal)
                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(24.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf(
                GuideTab.DOCUMENTS to "Glossary",
                GuideTab.WHY_LOSS to "Risks",
                GuideTab.REGIONAL to "Regional"
            ).forEach { (tab, label) ->
                val isSelected = activeTab == tab
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) MutedGreen else Color.Transparent)
                        .clickable { activeTab = tab }
                        .testTag("guide_tab_${label.lowercase()}"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelLarge,
                        color = if (isSelected) SoftGold else LightText.copy(alpha = 0.6f),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Dynamically toggle tab content holding area
        when (activeTab) {
            GuideTab.DOCUMENTS -> {
                SectionHeader(
                    title = "Property Documents Explained",
                    subtitle = "Demystifying terminology. Tap each official document to study its structural and legal roles.",
                    lightStyle = true
                )

                StaticData.documents.forEach { doc ->
                    DocumentAccordion(
                        title = doc.title,
                        description = doc.description,
                        significance = doc.legalSignificance,
                        verification = doc.verificationChannel
                    )
                }
            }

            GuideTab.WHY_LOSS -> {
                SectionHeader(
                    title = "Why People Lose Money",
                    subtitle = "Analytical breakdown explaining high-frequency scam structures and household purchase leaks.",
                    lightStyle = true
                )

                // Warning cards detailer
                WhyLoseMoneyList().forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(1.dp, CardBorder.copy(alpha = 0.15f), RoundedCornerShape(10.dp)),
                        colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(WarningRed.copy(alpha = 0.15f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Warning,
                                        contentDescription = "Alert icon",
                                        tint = WarningRed,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = SoftGold,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = item.body,
                                style = MaterialTheme.typography.bodyLarge,
                                color = LightText.copy(alpha = 0.82f),
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }

            GuideTab.REGIONAL -> {
                SectionHeader(
                    title = "Regional Guidance Records",
                    subtitle = "State land procedures differ fundamentally. Investigate regional verification rules.",
                    lightStyle = true
                )

                StaticData.regions.forEach { region ->
                    EditorialCard(
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(MutedGreen, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Map,
                                    contentDescription = "Region map pinpoint icon",
                                    tint = SoftGold,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = region.title,
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = DarkText,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = region.subtitle,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MutedGray,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = CardBorder)
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = region.contextText,
                            style = MaterialTheme.typography.bodyLarge,
                            color = DarkText,
                            lineHeight = 20.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Text(
                            text = "COMMON TITLE DOCUMENTS",
                            style = MaterialTheme.typography.labelLarge,
                            color = MutedGreen,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            region.commonDocuments.forEach { doc ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(CardBorder)
                                        .border(1.dp, CardBorder, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = doc,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = DarkText,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Warnings subset
                        Text(
                            text = "REGIONAL HAZARD PITFALLS",
                            style = MaterialTheme.typography.labelLarge,
                            color = WarningRed,
                            fontWeight = FontWeight.Bold
                        )
                        region.warnings.forEach { warn ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(top = 4.dp, end = 8.dp)
                                        .size(6.dp)
                                        .background(WarningRed, CircleShape)
                                )
                                Text(
                                    text = warn,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = DarkText,
                                    lineHeight = 18.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Critical Action Advice
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, SoftGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
                            colors = CardDefaults.cardColors(containerColor = SoftGold.copy(alpha = 0.05f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "ADVOCACY RULE OF THUMB",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = SoftGold,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = region.criticalAdvice,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = DarkText,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        DisclaimerCard(isDarkTheme = true)
        Spacer(modifier = Modifier.height(32.dp))
    }
}

// Model helper for risk list
data class WhyLoseMoneyItem(val title: String, val body: String)

fun WhyLoseMoneyList(): List<WhyLoseMoneyItem> = listOf(
    WhyLoseMoneyItem(
        "Rushing Payment",
        "Buyers fear losing an imaginary bargain. Scammers generate fictitious rival buyers, causing emotional panic so that the victim releases money without coordinate searches."
    ),
    WhyLoseMoneyItem(
        "Fake Urgency Projections",
        "In Nigeria, a genuine landowner has zero issues waiting 4-5 days for legal clearance. High pressure guarantees structural deception is in play."
    ),
    WhyLoseMoneyItem(
        "Missing Registered Survey Coordinates",
        "Failing to hire an independent local surveyor to check beacons locks buyers into unzoned or government-acquired reserve coordinates."
    ),
    WhyLoseMoneyItem(
        "Family Inheritance Land Disputes",
        "One sibling or self-appointed head tries to sell without consensus across family nodes, locking you into prolonged lawsuits and community physical force blocks."
    ),
    WhyLoseMoneyItem(
        "Incomplete Deceased Administration Records",
        "Sellers claiming power of attorney over a deceased elder's land library without formal letters of administration signed by high court registrars are invalid."
    ),
    WhyLoseMoneyItem(
        "Verbal Promises Without Root Deeds",
        "Trusting verbal guarantees: 'The layout of excision is processing, I am our uncle's cousin's associate.' In land law, if it isn't documented and registered under seal, it does not exist."
    ),
    WhyLoseMoneyItem(
        "Middlemen / Unauthorized Agent Pressure",
        "Middlemen and agents only gain direct commissions when a transfer finishes. They hold zero liability for a title dispute in court 10 years later."
    ),
    WhyLoseMoneyItem(
        "Receipt-Only Incomplete transfers",
        "Relying on hand-written receipts alone without executing contract covenants, Deed of Assignment blocks, or filing notices with Lands Ministries."
    )
)
