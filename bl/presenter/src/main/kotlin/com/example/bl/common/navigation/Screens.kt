package com.example.bl.common.navigation

import com.example.domain.dto.media_test.Program
import ru.terrakok.cicerone.android.support.SupportAppScreen
import java.io.Serializable

abstract class ScreenArgs : Serializable

class ProgramListScreen : SupportAppScreen()
class PlayListScreen (val program: Program): SupportAppScreen()