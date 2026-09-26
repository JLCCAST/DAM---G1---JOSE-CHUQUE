/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.racetracker

import com.example.racetracker.ui.RaceParticipant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RaceParticipantTest {
    private val raceParticipant = RaceParticipant(
        name = "Jugador 1",
        maxProgress = 100,
        progressDelayMillis = 500L,
        initialProgress = 0,
        progressIncrement = 1
    )

    // Validamos el estado inicial antes de ejecutar cualquier acción
    @Test
    fun raceParticipant_Initialization_ProgressIsZero() = runTest {
        // Resultado: No iniciamos la corrutina. Solo verificamos que los valores iniciales sean correctos.
        val expectedProgress = 0
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    // Validar el progreso a la mitad de la carrera
    @Test
    fun raceParticipant_RaceHalfway_ProgressUpdated() = runTest {
        val expectedProgress = 50
        launch { raceParticipant.run() }
        advanceTimeBy(50 * raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    // Caso de Reinicio: Validar que el progreso vuelve a 0 si se reinicia
    @Test
    fun raceParticipant_RaceReset_ProgressIsZero() = runTest {
        launch { raceParticipant.run() }

        // Adelantamos el tiempo para simular que la carrera ha avanzado (20 incrementos)
        advanceTimeBy(20 * raceParticipant.progressDelayMillis)
        runCurrent()

        // Confirmamos que la carrera efectivamente avanzó a 20
        assertEquals(20, raceParticipant.currentProgress)

        // Simulamos una interrupción llamando al método reset()
        raceParticipant.reset()

        // Verificamos que el progreso volvió al estado inicial
        assertEquals(0, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(100, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }
}