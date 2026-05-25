package com.example.ui.screens.propertycheck

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.staticcontent.Questionnaire
import com.example.domain.guidance.generateGuidanceResult
import com.example.ui.components.*
import com.example.ui.theme.*

@Composable
fun PropertyCheckScreen(
    modifier: Modifier = Modifier,
    viewModel: PropertyCheckViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    // Generate Guidance Result from our decoupled domain layer when user is on step 6 and hits "Generate Advice"
    val resultReport = remember(uiState.showResult, uiState) {
        if (uiState.showResult) {
            generateGuidanceResult(uiState)
        } else {
            null
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Masthead
        EditorialMasthead()

        if (!uiState.showResult) {
            // STEPPERS
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header Area
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
                            text = "CIVIC PROPERTY SCREENER",
                            style = MaterialTheme.typography.labelSmall,
                            color = SoftGold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Start Your Property Check",
                            style = MaterialTheme.typography.headlineLarge,
                            color = LightText
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Answer 6 simple structural questions to receive a dynamic advice document, custom alerts, and land bureau guidelines before your hard-earned money leaves your hand.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = LightText.copy(alpha = 0.7f),
                            lineHeight = 20.sp
                        )
                    }
                }

                // Step Tracker Indicator
                StepProgressIndicator(
                    currentStep = uiState.currentStep,
                    totalSteps = 6
                )

                // Animated step container displaying option cards
                AnimatedContent(
                    targetState = uiState.currentStep,
                    transitionSpec = {
                        if (targetState > initialState) {
                            (slideInHorizontally(animationSpec = tween(320)) { width -> width } + fadeIn(animationSpec = tween(320))).togetherWith(
                                slideOutHorizontally(animationSpec = tween(320)) { width -> -width } + fadeOut(animationSpec = tween(320))
                            )
                        } else {
                            (slideInHorizontally(animationSpec = tween(320)) { width -> -width } + fadeIn(animationSpec = tween(320))).togetherWith(
                                slideOutHorizontally(animationSpec = tween(320)) { width -> width } + fadeOut(animationSpec = tween(320))
                            )
                        }.using(SizeTransform(clip = false))
                    },
                    label = "property_check_wizard"
                ) { step ->
                    val activeStep = Questionnaire.steps[step - 1]
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
                            // Question Title
                            Text(
                                text = activeStep.questionTitle,
                                style = MaterialTheme.typography.headlineMedium,
                                color = LightText,
                                fontWeight = FontWeight.Bold
                            )
                            // Question Description
                            Text(
                                text = activeStep.supportingText,
                                style = MaterialTheme.typography.bodyMedium,
                                color = LightText.copy(alpha = 0.6f),
                                lineHeight = 18.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Render options based on configuration
                            if (activeStep.isCheckbox) {
                                // Survey Documents check step (Multiple choice check)
                                activeStep.options.forEach { option ->
                                    val isChecked = uiState.availableDocuments.contains(option)
                                    CheckboxOption(
                                        text = option,
                                        isChecked = isChecked,
                                        testTag = "option_doc_${option.replace(" ", "_").replace("’", "").lowercase()}",
                                        onCheckedChange = { checked ->
                                            viewModel.toggleDocument(option)
                                        }
                                    )
                                }
                            } else {
                                // Radio styled selection cards
                                activeStep.options.forEach { option ->
                                    val isSelected = when (step) {
                                        1 -> uiState.selectedPropertyType == option
                                        2 -> uiState.location == option
                                        3 -> uiState.sellerType == option
                                        5 -> (if (option == "Yes") uiState.isDiaspora else if (option == "No") !uiState.isDiaspora else false)
                                        6 -> (if (option == "Yes") uiState.isUrgentPressure else if (option == "No") !uiState.isUrgentPressure else false)
                                        else -> false
                                    }

                                    OptionButton(
                                        text = option,
                                        isSelected = isSelected,
                                        testTag = "option_step_${step}_$option",
                                        onClick = {
                                            when (step) {
                                                1 -> viewModel.updatePropertyType(option)
                                                2 -> viewModel.updateLocation(option)
                                                3 -> viewModel.updateSellerType(option)
                                                5 -> viewModel.updateIsDiaspora(option == "Yes")
                                                6 -> viewModel.updateIsUrgentPressure(option == "Yes")
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                // Step Navigation Direction Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back
                    if (uiState.currentStep > 1) {
                        OutlinedButton(
                            onClick = { viewModel.previousStep() },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("step_back_button"),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = LightText),
                            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back icon",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Previous", fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                    // Next / Calculate
                    val canProceed = when (uiState.currentStep) {
                        1 -> uiState.selectedPropertyType.isNotEmpty()
                        2 -> uiState.location.isNotEmpty()
                        3 -> uiState.sellerType.isNotEmpty()
                        4 -> uiState.availableDocuments.isNotEmpty()
                        5 -> true // Bool option defaulted
                        6 -> true
                        else -> false
                    }

                    Button(
                        onClick = { viewModel.nextStep() },
                        enabled = canProceed,
                        modifier = Modifier
                            .weight(1.5f)
                            .height(48.dp)
                            .testTag("step_next_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canProceed) SoftGold else Color.White.copy(alpha = 0.12f),
                            contentColor = if (canProceed) DarkText else LightText.copy(alpha = 0.3f)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (uiState.currentStep == 6) "Generate Advice" else "Next Step",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = if (uiState.currentStep == 6) Icons.Default.Summarize else Icons.Default.ArrowForward,
                                contentDescription = "Arrow asset icon",
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                DisclaimerCard(isDarkTheme = true)
                Spacer(modifier = Modifier.height(24.dp))
            }
        } else {
            // OUTCOME SCREEN (Dynamic report)
            resultReport?.let { report ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Header Result Info
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
                        border = BorderStroke(1.2.dp, SoftGold.copy(alpha = 0.8f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .background(MutedGreen.copy(alpha = 0.3f), CircleShape)
                                    .border(1.2.dp, SoftGold, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Gavel,
                                    contentDescription = "Analysis complete icon",
                                    tint = SoftGold,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = report.title,
                                style = MaterialTheme.typography.headlineLarge,
                                color = LightText,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = report.confidenceSubtitle,
                                style = MaterialTheme.typography.bodyMedium,
                                color = SoftGold,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // Reset CTA Row early
                    Button(
                        onClick = { viewModel.reset() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("reset_property_check_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MutedGreen,
                            contentColor = LightText
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Restart checklist"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Restart Assessment Screener", fontWeight = FontWeight.Bold)
                        }
                    }

                    // 1. Critical Advisories / Warnings (Red / SoftGold highlight for urgency pressure)
                    if (report.importantWarnings.isNotEmpty()) {
                        SectionHeader(title = "Important Warnings", subtitle = "Critical threats targeting current selections.", lightStyle = true)
                        report.importantWarnings.forEach { alert ->
                            if (alert.startsWith("URGENT PRESSURE ALERT:") || alert.contains("Artificial urgency")) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
                                    border = BorderStroke(2.dp, SoftGold)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Warning,
                                                contentDescription = "Highlight icon",
                                                tint = SoftGold,
                                                modifier = Modifier.size(24.dp)
                                            )
                                            Text(
                                                text = "Urgency Scrutiny Check",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = SoftGold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = alert,
                                            style = MaterialTheme.typography.bodyLarge,
                                            lineHeight = 20.sp,
                                            color = LightText
                                        )
                                    }
                                }
                            } else {
                                WarningCard(
                                    title = "Specific Transaction Alert",
                                    description = alert
                                )
                            }
                        }
                    }

                    // 2. Recommended Next Steps (Ivory)
                    SectionHeader(title = "Recommended Action Guidelines", subtitle = "Slowing down transaction speed prior to finance release.", lightStyle = true)
                    EditorialCard {
                        report.recommendedNextSteps.forEachIndexed { index, step ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(top = 4.dp, end = 12.dp)
                                        .size(24.dp)
                                        .background(MutedGreen, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = (index + 1).toString(),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = LightText,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text(
                                    text = step,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = DarkText,
                                    lineHeight = 20.sp,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }

                    // 3. Suggested Professionals (Ivory)
                    SectionHeader(title = "Contact Independent Professionals", subtitle = "Do not rely on the vendor's or agent's attorney.", lightStyle = true)
                    EditorialCard {
                        report.suggestedProfessionals.forEach { prof ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PersonSearch,
                                    contentDescription = "Professional advocate icon",
                                    tint = MutedGreen,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = prof,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = DarkText,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    // 4. Documents to Request (Ivory)
                    SectionHeader(title = "Request These Documents Immediately", subtitle = "Verify physical copies with surveyors, not virtual mockups.", lightStyle = true)
                    EditorialCard {
                        report.documentsToRequest.forEach { doc ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Description,
                                    contentDescription = "Document asset icon",
                                    tint = SoftGold,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = doc,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = DarkText,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    // 5. Official State Resources (Ivory)
                    if (report.officialResources.isNotEmpty()) {
                        SectionHeader(title = "Recommended Verification Channels", subtitle = "Official administrative bodies overseeing property registry records.", lightStyle = true)
                        EditorialCard {
                            report.officialResources.forEach { agency ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AccountBalance,
                                        contentDescription = "Official agency",
                                        tint = MutedGreen,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = agency,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = DarkText,
                                        lineHeight = 18.sp
                                    )
                                }
                            }
                        }
                    }

                    // Final safety disclaimer
                    DisclaimerCard(isDarkTheme = true)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Final Reset button
                    OutlinedButton(
                        onClick = { viewModel.reset() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("reset_property_check_bottom_btn"),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = SoftGold),
                        border = BorderStroke(1.2.dp, SoftGold),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Reset Screener & Begin New Check", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}
