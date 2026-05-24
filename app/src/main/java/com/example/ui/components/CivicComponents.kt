package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CardBorder
import com.example.ui.theme.DarkText
import com.example.ui.theme.DeepCharcoal
import com.example.ui.theme.LightMutedGreen
import com.example.ui.theme.LightText
import com.example.ui.theme.MutedGray
import com.example.ui.theme.MutedGreen
import com.example.ui.theme.SoftGold
import com.example.ui.theme.SurfaceCharcoal
import com.example.ui.theme.WarningRed

/**
 * Atmospheric Civic Background representing urban cadastral & survey map grids.
 * Creates an elegant, extremely low-opacity texture lines on top of the deep charcoal background.
 */
@Composable
fun CivicBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepCharcoal)
    ) {
        // Draw the soft cadastral lines
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // 1. Draw soft architectural grid lines (every 80dp/pixel equiv)
            val gridSpacing = 160f
            var x = 0f
            while (x < width) {
                drawLine(
                    color = Color.White.copy(alpha = 0.015f),
                    start = Offset(x, 0f),
                    end = Offset(x, height),
                    strokeWidth = 1f
                )
                x += gridSpacing
            }

            var y = 0f
            while (y < height) {
                drawLine(
                    color = Color.White.copy(alpha = 0.015f),
                    start = Offset(0f, y),
                    end = Offset(width, y),
                    strokeWidth = 1f
                )
                y += gridSpacing
            }

            // 2. Draw survey plan diagnostic lines & boundaries
            // We draw a faint series of coordinates representing land lots
            val lotPath1 = Path().apply {
                moveTo(width * 0.1f, height * 0.2f)
                lineTo(width * 0.4f, height * 0.15f)
                lineTo(width * 0.5f, height * 0.35f)
                lineTo(width * 0.2f, height * 0.4f)
                close()
            }
            drawPath(
                path = lotPath1,
                color = SoftGold.copy(alpha = 0.02f),
                style = Stroke(width = 2f)
            )

            val lotPath2 = Path().apply {
                moveTo(width * 0.55f, height * 0.6f)
                lineTo(width * 0.85f, height * 0.5f)
                lineTo(width * 0.9f, height * 0.8f)
                lineTo(width * 0.6f, height * 0.85f)
                close()
            }
            drawPath(
                path = lotPath2,
                color = LightMutedGreen.copy(alpha = 0.02f),
                style = Stroke(width = 2.4f)
            )

            // Draw a subtle coordinate beacon circle
            drawCircle(
                color = SoftGold.copy(alpha = 0.03f),
                radius = 120f,
                center = Offset(width * 0.4f, height * 0.15f),
                style = Stroke(width = 1f)
            )
            drawCircle(
                color = LightMutedGreen.copy(alpha = 0.03f),
                radius = 180f,
                center = Offset(width * 0.6f, height * 0.85f),
                style = Stroke(width = 1f)
            )
        }

        // 3. Floating, barely visible geographical references scattered behind
        Box(modifier = Modifier.fillMaxSize()) {
            val spacing = Modifier.alpha(0.025f)
            Text(
                text = "IKEJA LANDS DEP\nCOORD: 43.11 / 89.24",
                style = MaterialTheme.typography.labelMedium,
                fontFamily = FontFamily.Monospace,
                color = LightText,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 24.dp, top = 120.dp)
            )
            Text(
                text = "LEKKI EXCISION LOT 14B",
                style = MaterialTheme.typography.labelMedium,
                fontFamily = FontFamily.Monospace,
                color = LightText,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 32.dp, bottom = 140.dp)
            )
            Text(
                text = "WUSE DISTRICT COMP / ZONE 4",
                style = MaterialTheme.typography.labelMedium,
                fontFamily = FontFamily.Monospace,
                color = LightText,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 32.dp, bottom = 160.dp)
            )
            Text(
                text = "GARKI SURVEY BEACON 92F",
                style = MaterialTheme.typography.labelMedium,
                fontFamily = FontFamily.Monospace,
                color = LightText,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 40.dp, top = 260.dp)
            )
            Text(
                text = "ALAUSA CADASTRAL SECTOR Alpha",
                style = MaterialTheme.typography.labelMedium,
                fontFamily = FontFamily.Monospace,
                color = LightText,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp)
            )
        }

        // Actual foreground content
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}

/**
 * Editorial Card styled to look like warm premium newsprint paper
 */
@Composable
fun EditorialCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant, // Warm Ivory
    textColor: Color = DarkText,
    borderColor: Color = CardBorder,
    elevation: Dp = 2.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation, RoundedCornerShape(12.dp))
            .border(1.dp, borderColor, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            content()
        }
    }
}

/**
 * Warning Card styled in light or deep container with prominent red accent
 */
@Composable
fun WarningCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: ImageVector = Icons.Default.Warning
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, WarningRed.copy(alpha = 0.8f), RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF6F5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Warning icon",
                tint = WarningRed,
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = WarningRed,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkText,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

/**
 * Highly responsive option button matching 48dp touch target
 */
@Composable
fun OptionButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    testTag: String = "",
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) MutedGreen else SurfaceCharcoal
    val contentColor = if (isSelected) LightText else LightText.copy(alpha = 0.9f)
    val strokeColor = if (isSelected) SoftGold else LightText.copy(alpha = 0.15f)

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .testTag(testTag)
            .border(1.2.dp, strokeColor, RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = contentColor
            )
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(SoftGold, CircleShape)
                )
            }
        }
    }
}

/**
 * Custom checkbox component matching 48dp density requirements
 */
@Composable
fun CheckboxOption(
    modifier: Modifier = Modifier,
    text: String,
    isChecked: Boolean,
    testTag: String = "",
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .testTag(testTag)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isChecked) MutedGreen.copy(alpha = 0.7f) else SurfaceCharcoal)
            .border(
                width = 1.2.dp,
                color = if (isChecked) SoftGold else Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onCheckedChange(!isChecked) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = SoftGold,
                uncheckedColor = Color.White.copy(alpha = 0.6f),
                checkmarkColor = DarkText
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = LightText,
            fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Step Progress Indicator
 */
@Composable
fun StepProgressIndicator(
    modifier: Modifier = Modifier,
    currentStep: Int,
    totalSteps: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val percentage = currentStep.toFloat() / totalSteps.toFloat()

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "STEP $currentStep OF $totalSteps",
                    style = MaterialTheme.typography.labelLarge,
                    color = SoftGold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "${(percentage * 100).toInt()}% COMPLETED",
                    style = MaterialTheme.typography.labelMedium,
                    color = LightText.copy(alpha = 0.6f)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { percentage },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = SoftGold,
                trackColor = Color.White.copy(alpha = 0.15f)
            )
        }
    }
}

/**
 * Disclaimer card placed everywhere safety notices are required
 */
@Composable
fun DisclaimerCard(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = false
) {
    val containerColor = if (isDarkTheme) SurfaceCharcoal else MaterialTheme.colorScheme.surfaceVariant
    val borderColors = if (isDarkTheme) SoftGold.copy(alpha = 0.4f) else CardBorder
    val primaryTextColor = if (isDarkTheme) LightText else DarkText
    val iconColor = if (isDarkTheme) SoftGold else MutedGreen

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, borderColors, RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Security,
                contentDescription = "Security icon for safety disclaimer",
                tint = iconColor,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "CIVIC SAFEGUARD DISCLAIMER",
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isDarkTheme) SoftGold else MutedGreen,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Before You Buy NG provides educational and informational guidance only. Users are encouraged to conduct independent professional and official verification before making property-related payments or decisions.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = primaryTextColor.copy(alpha = 0.9f),
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "This app does not verify land ownership, confirm title validity, or replace legal, surveying, or official government checks.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = primaryTextColor.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

/**
 * Section Header
 */
@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    lightStyle: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = if (lightStyle) LightText else DarkText,
            fontWeight = FontWeight.Bold
        )
        if (subtitle != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = if (lightStyle) LightText.copy(alpha = 0.6f) else MutedGray,
                lineHeight = 16.sp
            )
        }
    }
}

/**
 * Accoridon Card helper for documents dictionary
 */
@Composable
fun DocumentAccordion(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    significance: String,
    verification: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .shadow(1.dp, RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(8.dp))
            .clickable { isExpanded = !isExpanded }
            .padding(16.dp)
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse details" else "Expand details",
                tint = MutedGreen,
                modifier = Modifier.size(24.dp)
            )
        }

        if (isExpanded) {
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = CardBorder.copy(alpha = 0.7f))
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "What is it?",
                style = MaterialTheme.typography.labelLarge,
                color = MutedGreen,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkText,
                lineHeight = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Legal Significance",
                style = MaterialTheme.typography.labelLarge,
                color = MutedGreen,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = significance,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkText,
                lineHeight = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "How to Verify",
                style = MaterialTheme.typography.labelLarge,
                color = SoftGold,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = verification,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkText,
                lineHeight = 18.sp
            )
        }
    }
}

/**
 * Resource Cards for Official Agency connections
 */
@Composable
fun ResourceCard(
    modifier: Modifier = Modifier,
    agency: String,
    title: String,
    description: String,
    url: String?,
    onUrlClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(1.dp, CardBorder, RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = agency,
                    style = MaterialTheme.typography.labelMedium,
                    color = SoftGold,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )
                if (url != null) {
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = "External link",
                        tint = MutedGreen,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(SoftGold, CircleShape)
                        )
                        Text(
                            text = "OFFICIAL LINK BEING VERIFIED",
                            style = MaterialTheme.typography.labelSmall,
                            color = MutedGray,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkText.copy(alpha = 0.9f),
                lineHeight = 18.sp
            )

            if (url != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { onUrlClick(url) },
                    modifier = Modifier
                        .align(Alignment.End)
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MutedGreen,
                        contentColor = LightText
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Text(
                        text = "Visit Official Portal",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

/**
 * Editorial Article Reading Layout for Foundational Guidance articles
 */
@Composable
fun ArticleReadingLayout(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    author: String,
    date: String,
    readTime: String,
    contentBlocks: List<String>,
    pullQuotes: List<String>,
    relatedGuidance: List<String>,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(18.dp)
    ) {
        // Back Header
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp, // Pointing up as modal dismiss trigger
                contentDescription = "Close reading view",
                tint = DarkText,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Metadata Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = author.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = SoftGold,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "$date • $readTime",
                style = MaterialTheme.typography.labelMedium,
                color = MutedGray
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge,
            color = DarkText,
            lineHeight = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleLarge,
            color = MutedGreen,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = CardBorder)
        Spacer(modifier = Modifier.height(16.dp))

        // Render Paragraphs and inject Pull Quotes
        contentBlocks.forEachIndexed { index, paragraph ->
            Text(
                text = paragraph,
                style = MaterialTheme.typography.bodyLarge,
                color = DarkText,
                lineHeight = 22.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Inject first pull quote after paragraph 1
            if (index == 0 && pullQuotes.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .border(
                            2.dp,
                            SoftGold.copy(alpha = 0.8f),
                            RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = SoftGold.copy(alpha = 0.05f)),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "“ ${pullQuotes[0]} ”",
                            style = MaterialTheme.typography.titleLarge,
                            color = MutedGreen,
                            fontWeight = FontWeight.Bold,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                            lineHeight = 22.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Inject second pull quote after paragraph 3
            if (index == 2 && pullQuotes.size > 1) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .border(
                            2.dp,
                            WarningRed.copy(alpha = 0.8f),
                            RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = WarningRed.copy(alpha = 0.03f)),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "“ ${pullQuotes[1]} ”",
                            style = MaterialTheme.typography.titleMedium,
                            color = WarningRed,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        // Related Guidance Area
        if (relatedGuidance.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "CRITICAL PRACTICAL CHECKLIST",
                style = MaterialTheme.typography.labelLarge,
                color = MutedGreen,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            relatedGuidance.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 6.dp, end = 12.dp)
                            .size(6.dp)
                            .background(WarningRed, CircleShape)
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium,
                        color = DarkText,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        DisclaimerCard(isDarkTheme = false)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * Preview Card for the long form editorial article
 */
@Composable
fun ArticlePreviewCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    readTime: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .shadow(2.dp, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "FEATURED CIVIC REPORT",
                    style = MaterialTheme.typography.labelMedium,
                    color = SoftGold,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = readTime,
                    style = MaterialTheme.typography.labelSmall,
                    color = MutedGray,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = DarkText,
                lineHeight = 24.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge,
                color = MutedGreen,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Read Guide",
                    style = MaterialTheme.typography.labelMedium,
                    color = DarkText,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Read full article",
                    tint = DarkText,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

/**
 * Premium Masthead representing a trusted, prestigious news/guidance brand
 */
@Composable
fun EditorialMasthead(
    modifier: Modifier = Modifier,
    titleText: String = "BEFORE YOU BUY NG",
    subtitleText: String = "NIGERIAN CIVIC PROPERTY CITIZEN SAFEGUARD"
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top fine line
        HorizontalDivider(color = SoftGold.copy(alpha = 0.4f), thickness = 1.dp)

        Spacer(modifier = Modifier.height(8.dp))

        // Brand Name in Serif Bold caps
        Text(
            text = titleText,
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 24.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.ExtraBold
            ),
            color = SoftGold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Civic Tag
        Text(
            text = subtitleText,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 9.sp,
                letterSpacing = 1.2.sp,
                fontWeight = FontWeight.Bold
            ),
            color = LightText.copy(alpha = 0.5f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Bottom fine line
        HorizontalDivider(color = SoftGold.copy(alpha = 0.4f), thickness = 1.dp)
    }
}

