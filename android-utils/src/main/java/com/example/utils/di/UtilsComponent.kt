package com.example.utils.di

import com.example.utils.dialog.CustomDialog

interface UtilsComponent {
    fun inject(dialog: CustomDialog)
}