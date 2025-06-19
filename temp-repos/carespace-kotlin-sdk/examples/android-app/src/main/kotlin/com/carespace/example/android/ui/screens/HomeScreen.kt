package com.carespace.example.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carespace.sdk.android.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToUsers: () -> Unit,
    onNavigateToClients: () -> Unit,
    onNavigateToPrograms: () -> Unit,
    authViewModel: AuthViewModel = koinViewModel()
) {
    val loginResponse by authViewModel.loginResponse.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        loginResponse?.user?.let { user ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Welcome back!",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Name: ${user.name}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Email: ${user.email}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Role: ${user.role.name.lowercase().replaceFirstChar { it.uppercase() }}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Text(
            text = "Carespace SDK Features",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Navigation Cards
        NavigationCard(
            title = "Users",
            description = "Manage users, profiles, and authentication",
            onClick = onNavigateToUsers
        )

        Spacer(modifier = Modifier.height(16.dp))

        NavigationCard(
            title = "Clients", 
            description = "Manage patients and their medical information",
            onClick = onNavigateToClients
        )

        Spacer(modifier = Modifier.height(16.dp))

        NavigationCard(
            title = "Programs",
            description = "Browse and manage exercise programs",
            onClick = onNavigateToPrograms
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                authViewModel.logout()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}