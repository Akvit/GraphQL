package azhel.example.graphql.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import azhel.example.graphql.R
import azhel.example.graphql.domain.DetailedCountry
import azhel.example.graphql.domain.SimpleCountry
import azhel.example.graphql.ui.theme.TextDimens.sp16
import azhel.example.graphql.ui.theme.TextDimens.sp30
import azhel.example.graphql.ui.theme.UiDimens.dp16
import azhel.example.graphql.ui.theme.UiDimens.dp5
import azhel.example.graphql.ui.theme.UiDimens.dp8

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountryState,
    onSelectCountry: (code: String) -> Unit,
    onDismissDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) {
                    SimpleCountryItem(modifier = Modifier
                        .fillMaxWidth()
                        .padding(dp16)
                        .clickable {
                            onSelectCountry(it.code)
                        }, it)
                }
            }
        }

        if (state.selectedCountry != null) {
            CountryDialog(country = state.selectedCountry, onDismissDialog = onDismissDialog, modifier = Modifier
                .clip(
                    RoundedCornerShape(dp5)
                )
                .background(Color.White)
                .padding(dp16))
        }
    }
}

@Composable
fun SimpleCountryItem(modifier: Modifier = Modifier, simpleCountry: SimpleCountry) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = simpleCountry.emoji, fontSize = sp30)
        Spacer(modifier = Modifier.width(dp16))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = simpleCountry.name, fontSize = sp16)
            Spacer(modifier = Modifier.width(dp16))
            Text(text = simpleCountry.capital)
        }
    }
}

@Composable
fun CountryDialog(country: DetailedCountry, onDismissDialog: () -> Unit, modifier: Modifier = Modifier) {

    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(onDismissRequest = onDismissDialog) {
        Column(modifier = modifier) {
            Row (modifier = Modifier.fillMaxWidth()){
                Text(text = country.emoji, fontSize = sp30)
                Spacer(modifier = Modifier.width(dp16))
                Text(text = country.name, fontSize = sp16)
            }
            Spacer(modifier = Modifier.width(dp16))
            Text(text = stringResource(id = R.string.continent) + " " + country.continent, fontSize = sp30)
            Spacer(modifier = Modifier.width(dp8))
            Text(text = stringResource(id = R.string.currency) + " " + country.currency, fontSize = sp30)
            Spacer(modifier = Modifier.width(dp8))
            Text(text = stringResource(id = R.string.capital) + " " + country.capital, fontSize = sp30)
            Spacer(modifier = Modifier.width(dp8))
            Text(text = stringResource(id = R.string.language) + " " + joinedLanguages, fontSize = sp30)
            Spacer(modifier = Modifier.width(dp8))

        }
    }
}
