package com.example.ilmhonazero

import android.util.Log

open class Animal {
    open fun saySomething() {
        Log.d("debug", "Hi!")
    }
}

class Cat: Animal() {
    override fun saySomething() {
        Log.d("debug", "meow!")
    }
}

class Dog: Animal() {
    override fun saySomething() {
        Log.d("debug", "Woof!")
    }
}