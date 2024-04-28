package av.kochekov.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import av.kochekov.firstcomposeproject.ui.theme.FirstComposeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val items = listOf(
                        Contact(
                            name = "Евгений",
                            surname = "Андреевич",
                            familyName = "Лукашин",
                            imageRes = null,
                            isFavorite = true,
                            phone = "+7 495 495 95 95",
                            address = "г. Москва, 3-я улица Строительная, д. 25, кв. 12",
                            email = "ELukashin@practicum.ru",
                        ),
                        Contact(
                            name = "Василий",
                            familyName = "Кузякин",
                            imageRes = R.drawable.omsk_bird,
                            phone = "---",
                            address = "Ивановская область, дер. Крутово, д. 4",
                        ),
                    )
                    ContactDetails(
                        contact = items[1]
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(modifier: Modifier = Modifier, type: Int, text: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = 2.dp),
            text = stringResource(id = type) + ": ",
            textAlign = TextAlign.End,
        )
        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = 2.dp),
            text = text,
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ContactDetails(modifier: Modifier = Modifier, contact: Contact) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {

            contact.imageRes?.let { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                )
            } ?: let {
                Icon(
                    tint = Color.Gray,
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = null,
                )
                Text(
                    text = contact.name.take(1) + contact.familyName.take(1)
                )
            }
        }

        Text(
            text = contact.surname?.let { "${contact.name} $it" } ?: contact.name,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = contact.familyName,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            if (contact.isFavorite) {
                Image(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = "Is favorite"
                )
            }
        }
        Column(
            modifier = Modifier.padding(top = 40.dp)
        ) {
            InfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                type = R.string.phone,
                text = contact.phone
            )
            InfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                type = R.string.address,
                text = contact.address
            )
            contact.email?.let { email ->
                InfoRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    type = R.string.email,
                    text = email
                )
            }
        }
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ShowContactPreview_1() {
    FirstComposeProjectTheme {
        ContactDetails(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 16.dp),
            contact = Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                imageRes = null,
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строительная, д. 25, кв. 12",
                email = "ELukashin@practicum.ru",
            )
        )
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ShowContactPreview_2() {
    FirstComposeProjectTheme {
        ContactDetails(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 16.dp),
            contact = Contact(
                name = "Василий",
                familyName = "Кузякин",
                imageRes = R.drawable.omsk_bird,
                phone = "---",
                address = "Ивановская область, дер. Крутово, д. 4",
            )
        )
    }
}