package com.nareshtech.hiltinandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
// TODO 1: After library generation, add this class and have @HiltAndroidApp annotated
// TODO 2: Do not forget to add `MyApplication` to the manifest file.
@HiltAndroidApp
class MyApplication: Application() {
    // This is where the hilt library would generate code for you
}