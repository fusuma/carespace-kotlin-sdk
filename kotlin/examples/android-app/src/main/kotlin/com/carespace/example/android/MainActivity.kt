package com.carespace.example.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carespace.example.android.ui.theme.CarespaceExampleTheme
import com.carespace.example.android.ui.screens.*
import com.carespace.sdk.android.AuthViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarespaceExampleTheme {
                CarespaceExampleApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarespaceExampleApp() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = koinViewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carespace SDK Example") }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) "home" else "login",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            
            composable("home") {
                HomeScreen(
                    onNavigateToUsers = { navController.navigate("users") },
                    onNavigateToClients = { navController.navigate("clients") },
                    onNavigateToPrograms = { navController.navigate("programs") }
                )
            }
            
            composable("users") {
                UsersScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            
            composable("clients") {
                ClientsScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            
            composable("programs") {
                ProgramsScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarespaceExampleAppPreview() {
    CarespaceExampleTheme {
        CarespaceExampleApp()
    }
}