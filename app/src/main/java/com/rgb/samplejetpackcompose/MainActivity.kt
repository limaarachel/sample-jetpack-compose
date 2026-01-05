package com.rgb.samplejetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val pagerState = rememberPagerState {
        7
    }

    val pagerItems = listOf(
        R.drawable.foto1,
        R.drawable.foto2,
        R.drawable.foto3,
        R.drawable.foto4,
        R.drawable.foto5,
        R.drawable.foto6,
        R.drawable.foto7
    )
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            contentPadding = PaddingValues(32.dp)
        ) { page ->
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(pagerItems[page]),
                    contentDescription = "carrossel",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(
                            shape = RoundedCornerShape(30.dp)
                        )
                        .graphicsLayer {
                            val pageOffset = pagerState.offsetForPage(page)
                            val offScreenRight = pageOffset < 0f
                            val deg = 105f
                            val interpolated =
                                FastOutLinearInEasing.transform(pageOffset.absoluteValue)
                            rotationY = min(interpolated * if (offScreenRight) deg else -deg, 90f)

                            transformOrigin = TransformOrigin(
                                pivotFractionX = if (offScreenRight) 0f else 1f,
                                pivotFractionY = .5f
                            )
                        }
                        .clickable {
                            Toast.makeText(context, "Imagem: $page", Toast.LENGTH_SHORT).show()
                        }
                        .padding(5.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = "$page",
                    fontSize = 50.sp,
                    modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .size(30.dp)
                        .padding(0.dp, 5.dp, 0.dp, 0.dp), textAlign = TextAlign.Center
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            repeat(pagerState.pageCount) { index ->
                val color = if (pagerState.currentPage == index) Color.DarkGray else Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color = color)
                        .size(16.dp)
                )
            }
        }
    }

}

fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction


@Preview(name = "HomePreview")
@Composable
fun HomePreview() {
    Home()
}
