package com.carespace.sdk.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carespace.sdk.CarespaceClient
import com.carespace.sdk.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel for Carespace SDK operations
 */
abstract class CarespaceViewModel(
    protected val carespace: CarespaceClient
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    protected fun <T> executeAsync(
        onSuccess: (T) -> Unit = {},
        onError: (Exception) -> Unit = {},
        operation: suspend () -> T
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val result = operation()
                onSuccess(result)
            } catch (e: Exception) {
                _error.value = e.message
                onError(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    override fun onCleared() {
        super.onCleared()
        carespace.close()
    }
}

/**
 * ViewModel for authentication operations
 */
class AuthViewModel(carespace: CarespaceClient) : CarespaceViewModel(carespace) {
    
    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun login(email: String, password: String) {
        executeAsync(
            onSuccess = { response: ApiResponse<LoginResponse> ->
                if (response.success && response.data != null) {
                    _loginResponse.value = response.data
                    _isLoggedIn.value = true
                    carespace.setApiKey(response.data.accessToken)
                }
            }
        ) {
            carespace.auth.login(email, password)
        }
    }

    fun logout() {
        executeAsync(
            onSuccess = {
                _loginResponse.value = null
                _isLoggedIn.value = false
            }
        ) {
            carespace.auth.logout()
        }
    }
}

/**
 * ViewModel for user operations
 */
class UsersViewModel(carespace: CarespaceClient) : CarespaceViewModel(carespace) {
    
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun getUsers(page: Int = 1, limit: Int = 20, search: String? = null) {
        executeAsync(
            onSuccess = { response: PaginatedResponse<User> ->
                _users.value = response.data
            }
        ) {
            carespace.users.getUsers(page, limit, search)
        }
    }

    fun getUserProfile() {
        executeAsync(
            onSuccess = { response: ApiResponse<User> ->
                response.data?.let { _currentUser.value = it }
            }
        ) {
            carespace.users.getUserProfile()
        }
    }
}

/**
 * ViewModel for client operations
 */
class ClientsViewModel(carespace: CarespaceClient) : CarespaceViewModel(carespace) {
    
    private val _clients = MutableStateFlow<List<Client>>(emptyList())
    val clients: StateFlow<List<Client>> = _clients.asStateFlow()

    private val _selectedClient = MutableStateFlow<Client?>(null)
    val selectedClient: StateFlow<Client?> = _selectedClient.asStateFlow()

    private val _clientStats = MutableStateFlow<ClientStats?>(null)
    val clientStats: StateFlow<ClientStats?> = _clientStats.asStateFlow()

    fun getClients(page: Int = 1, limit: Int = 20, search: String? = null) {
        executeAsync(
            onSuccess = { response: PaginatedResponse<Client> ->
                _clients.value = response.data
            }
        ) {
            carespace.clients.getClients(page, limit, search)
        }
    }

    fun selectClient(client: Client) {
        _selectedClient.value = client
        getClientStats(client.id)
    }

    private fun getClientStats(clientId: String) {
        executeAsync(
            onSuccess = { response: ApiResponse<ClientStats> ->
                _clientStats.value = response.data
            }
        ) {
            carespace.clients.getClientStats(clientId)
        }
    }
}

/**
 * ViewModel for program operations
 */
class ProgramsViewModel(carespace: CarespaceClient) : CarespaceViewModel(carespace) {
    
    private val _programs = MutableStateFlow<List<Program>>(emptyList())
    val programs: StateFlow<List<Program>> = _programs.asStateFlow()

    private val _selectedProgram = MutableStateFlow<Program?>(null)
    val selectedProgram: StateFlow<Program?> = _selectedProgram.asStateFlow()

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    fun getPrograms(
        page: Int = 1, 
        limit: Int = 20, 
        category: ProgramCategory? = null
    ) {
        executeAsync(
            onSuccess = { response: PaginatedResponse<Program> ->
                _programs.value = response.data
            }
        ) {
            carespace.programs.getPrograms(page, limit, category = category)
        }
    }

    fun selectProgram(program: Program) {
        _selectedProgram.value = program
        getProgramExercises(program.id)
    }

    private fun getProgramExercises(programId: String) {
        executeAsync(
            onSuccess = { response: PaginatedResponse<Exercise> ->
                _exercises.value = response.data
            }
        ) {
            carespace.programs.getProgramExercises(programId)
        }
    }
}