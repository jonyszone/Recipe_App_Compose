package com.shafi.conposetestapp.ui.theme

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun shimmerBrush(): Brush {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surfaceVariant,
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
        MaterialTheme.colorScheme.surfaceVariant,
    )
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000, easing = LinearEasing),
            RepeatMode.Restart
        ),
        label = "shimmer"
    )
    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim - 200f, 0f),
        end = Offset(translateAnim, 0f)
    )
}

/** Skeleton for a single recipe card (used in Feed/Favorites loading) */
@Composable
fun RecipeCardSkeleton(brush: Brush = shimmerBrush()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(brush)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(Modifier.width(80.dp).height(20.dp).clip(RoundedCornerShape(4.dp)).background(Color.White.copy(alpha = 0.3f)))
            Spacer(Modifier.height(8.dp))
            Box(Modifier.fillMaxWidth(0.7f).height(24.dp).clip(RoundedCornerShape(4.dp)).background(Color.White.copy(alpha = 0.3f)))
        }
    }
}

/** Skeleton for the detail screen hero + content */
@Composable
fun RecipeDetailSkeleton(brush: Brush = shimmerBrush()) {
    Column(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxWidth().height(320.dp).background(brush))
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(Modifier.fillMaxWidth(0.8f).height(28.dp).clip(RoundedCornerShape(6.dp)).background(brush))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(3) {
                    Box(Modifier.width(80.dp).height(32.dp).clip(RoundedCornerShape(50)).background(brush))
                }
            }
            repeat(4) {
                Box(Modifier.fillMaxWidth(if (it % 2 == 0) 1f else 0.75f).height(16.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            }
        }
    }
}
