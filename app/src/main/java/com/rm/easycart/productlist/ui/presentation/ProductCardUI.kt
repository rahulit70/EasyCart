package com.rm.easycart.productlist.ui.presentation

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.easycart.R
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.CoilImageLoader
import com.rm.easycart.utils.onItemClick

@Composable
fun ProductCardUI(
    product: Product,
    onClick: onItemClick
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick.invoke(product.id)
            },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondaryContainer),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 9.dp
        )
    ) {
        Row(Modifier.padding(5.dp)) {
            CoilImageLoader(
                imageUrl = product.thumbnail,
                contentDesc = product.title,
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(12.dp)),
                placeholder = R.drawable.baseline_image_not_supported_24,
                contentScale = ContentScale.FillBounds
            )

            Column {
                Rating(product = product)
                Title(product = product)
                PriceAndDiscount(product = product)
            }

        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Title(product: Product) {
    Text(
        text = product.title,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp)
            .basicMarquee()
    )
}

@Composable
private fun PriceAndDiscount(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(
                top = 8.dp
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "₹${(product.price)}",
            color = MaterialTheme.colorScheme.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "(${product.discountPercentage}% Off)",
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )

    }

}

@Composable
private fun Rating(product: Product) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp, top = 8.dp)
    ) {
        Icon(
            Icons.Default.Star,
            contentDescription = null,
            tint = Color.Yellow
        )

        Text(
            text = product.rating.toString(),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(start = 5.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        )
    }


}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
private fun DrawCardViewPreview() {
    ProductCardUI(
        product = Product(
            brand = "Apple",
            category = "Phone",
            title = "iPhone 15 Pro",
            id = 1,
            rating = 4.0,
            price = 499,
            discountPercentage = 19.0,
            thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"

        )
    ) {}
}