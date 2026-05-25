package com.example.ui.screens.resources

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.staticcontent.StaticData
import com.example.ui.components.ArticlePreviewCard
import com.example.ui.components.ArticleReadingLayout
import com.example.ui.components.DisclaimerCard
import com.example.ui.components.EditorialMasthead
import com.example.ui.components.ResourceCard
import com.example.ui.components.SectionHeader
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.graphics.toArgb
import com.example.ui.theme.*

@Composable
fun ResourcesScreen(
    modifier: Modifier = Modifier
) {
    var isReadingArticle by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Handle physical android back navigation safely when article reading is active
    if (isReadingArticle) {
        BackHandler {
            isReadingArticle = false
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedContent(
            targetState = isReadingArticle,
            transitionSpec = {
                if (targetState) {
                    // Slide in from bottom when reading card is tapped
                    slideInVertically(initialOffsetY = { IntSize -> IntSize }) + fadeIn() togetherWith
                            slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                } else {
                    // Slide out down when dismissed
                    slideInVertically(initialOffsetY = { -it }) + fadeIn() togetherWith
                            slideOutVertically(targetOffsetY = { it }) + fadeOut()
                }
            },
            label = "article_reading_view_transition"
        ) { readingModeActive ->
            if (readingModeActive) {
                // Reading mode took over
                val article = StaticData.foundationalArticle
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    ArticleReadingLayout(
                        title = article.title,
                        subtitle = article.subtitle,
                        author = article.author,
                        date = article.publishDate,
                        readTime = article.readTime,
                        contentBlocks = article.contentBlocks,
                        pullQuotes = article.pullQuotes,
                        relatedGuidance = article.relatedGuidance,
                        onBackClick = { isReadingArticle = false }
                    )
                }
            } else {
                // Standard Links Directory View
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Masthead
                    EditorialMasthead(
                        titleText = "CIVIC RESOURCE ARCHIVES",
                        subtitleText = "OFFICIAL REGISTRIES AND LANDS DEPARTMENT DIRECTORIES"
                    )

                    // Header Card
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
                                text = "OFFICIAL GOVERNMENT REGISTRIES",
                                style = MaterialTheme.typography.labelSmall,
                                color = SoftGold,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Official Resources",
                                style = MaterialTheme.typography.headlineLarge,
                                color = LightText
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Before You Buy NG provides guidance only; it is not a state department and cannot formalize searches for you. Always verify documents with official channels.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = LightText.copy(alpha = 0.7f),
                                lineHeight = 20.sp
                            )
                        }
                    }

                    // Section: Foundational Article Announcement
                    SectionHeader(
                        title = "Featured Civic Guidance",
                        subtitle = "Immersive editorial report authored by property law practitioners.",
                        lightStyle = true
                    )

                    // Article Preview Card
                    ArticlePreviewCard(
                        modifier = Modifier.testTag("featured_article_preview"),
                        title = StaticData.foundationalArticle.title,
                        subtitle = StaticData.foundationalArticle.subtitle,
                        readTime = StaticData.foundationalArticle.readTime,
                        onClick = { isReadingArticle = true }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Section: Registry portals
                    SectionHeader(
                        title = "State Registry Portals",
                        subtitle = "Verified web links to formal land registration and estate regulatory bodies.",
                        lightStyle = true
                    )

                    // Database Links Card iteration
                    StaticData.resources.forEach { resource ->
                        ResourceCard(
                            modifier = Modifier.testTag("resource_card_${resource.agency.replace(" ", "_").lowercase()}"),
                            agency = resource.agency,
                            title = resource.title,
                            description = resource.description,
                            url = resource.url,
                            onUrlClick = { url ->
                                launchSafeUrlIntent(context, url)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    DisclaimerCard(isDarkTheme = true)
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

/**
 * Launches external links securely through Chrome Custom Tabs styled with native DeepCharcoal
 */
private fun launchSafeUrlIntent(context: Context, url: String) {
    try {
        val parsedUri = Uri.parse(url)
        val toolbarColor = DeepCharcoal.toArgb()
        
        val defaultColors = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(toolbarColor)
            .build()
            
        val customTabsIntent = CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(defaultColors)
            .build()
            
        customTabsIntent.launchUrl(context, parsedUri)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "Could not launch browser. Resource URL: $url",
            Toast.LENGTH_LONG
        ).show()
    }
}
