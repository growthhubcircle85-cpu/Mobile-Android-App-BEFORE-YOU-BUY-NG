package com.example.ui.screens.propertycheck

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PropertyCheckUiState(
    val currentStep: Int = 1,
    val selectedPropertyType: String = "",
    val location: String = "",
    val sellerType: String = "",
    val availableDocuments: List<String> = emptyList(),
    val isDiaspora: Boolean = false,
    val isUrgentPressure: Boolean = false,
    val showResult: Boolean = false
)

class PropertyCheckViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PropertyCheckUiState())
    val uiState: StateFlow<PropertyCheckUiState> = _uiState.asStateFlow()

    fun updatePropertyType(type: String) {
        _uiState.update { it.copy(selectedPropertyType = type) }
    }

    fun updateLocation(location: String) {
        _uiState.update { it.copy(location = location) }
    }

    fun updateSellerType(sellerType: String) {
        _uiState.update { it.copy(sellerType = sellerType) }
    }

    fun toggleDocument(doc: String) {
        _uiState.update { state ->
            val updatedDocs = if (doc == "None / Not Sure") {
                if (state.availableDocuments.contains(doc)) emptyList() else listOf(doc)
            } else {
                val listWithoutNone = state.availableDocuments.filter { it != "None / Not Sure" }
                if (listWithoutNone.contains(doc)) {
                    listWithoutNone - doc
                } else {
                    listWithoutNone + doc
                }
            }
            state.copy(availableDocuments = updatedDocs)
        }
    }

    fun updateIsDiaspora(isDiaspora: Boolean) {
        _uiState.update { it.copy(isDiaspora = isDiaspora) }
    }

    fun updateIsUrgentPressure(isUrgentPressure: Boolean) {
        _uiState.update { it.copy(isUrgentPressure = isUrgentPressure) }
    }

    fun nextStep() {
        _uiState.update { state ->
            if (state.currentStep < 6) {
                state.copy(currentStep = state.currentStep + 1)
            } else {
                state.copy(showResult = true)
            }
        }
    }

    fun previousStep() {
        _uiState.update { state ->
            if (state.currentStep > 1) {
                state.copy(currentStep = state.currentStep - 1)
            } else {
                state
            }
        }
    }

    fun reset() {
        _uiState.value = PropertyCheckUiState()
    }
}
