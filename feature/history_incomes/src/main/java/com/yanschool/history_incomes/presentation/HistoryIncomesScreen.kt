package com.yanschool.history_incomes.presentation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yanschool.common_screen_layout.history.HistoryScreenContent
import com.yanschool.components.core.DefaultTopAppBar
import com.yanschool.components.core.ErrorScreen
import com.yanschool.components.core.Loader
import com.yanschool.components.core.date_picker.DateType
import com.yanschool.finapp.ui.R

@Composable
fun HistoryIncomesScreenRoot(
    paddingValues: PaddingValues,
    viewModel: HistoryIncomesScreenViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()

    HistoryIncomesScreen(
        paddingValues = paddingValues,
        screenState = screenState,
        onNavigateBack = navigateBack,
        onDateSelected = { date, type ->
            viewModel.selectDate(date, type)
        }
    )
}

@Composable
private fun HistoryIncomesScreen(
    paddingValues: PaddingValues,
    onNavigateBack: () -> Unit,
    onDateSelected: (Long?, DateType) -> Unit,
    screenState: State<HistoryIncomesScreenState>,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DefaultTopAppBar(
                titleRes = R.string.my_history,
                actions = { TopBarActionIcon() },
                navigationIcon = {
                    TopBarNavigationIcon(
                        onClick = onNavigateBack
                    )
                })
        },
    ) { innerPaddingValues ->
        when (val currentState = screenState.value) {
            is HistoryIncomesScreenState.Content -> {
                HistoryScreenContent(
                    paddingValues = innerPaddingValues,
                    screenState = currentState,
                    onDateSelected = onDateSelected,
                )
            }

            is HistoryIncomesScreenState.Error -> {
                Log.d("HistoryIncomesScreen", currentState.msg)
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
            }

            is HistoryIncomesScreenState.Loading -> {
                Loader(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPaddingValues.calculateTopPadding())
                )
            }
        }
    }
}

@Composable
private fun RowScope.TopBarActionIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(48.dp),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.history_outline_ic),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = null,
        )
    }
}

@Composable
private fun TopBarNavigationIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(48.dp),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.arrow_back_ic),
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null,
        )
    }
}


